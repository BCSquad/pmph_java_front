package com.bc.pmpheep.back.commuser.course.service;

import com.bc.pmpheep.back.commuser.course.bean.CourseBookVO;
import com.bc.pmpheep.back.commuser.course.dao.CourseDao;
import com.bc.pmpheep.general.service.ExcelDownloadService;
import jxl.format.Colour;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Service("CourseBillExcelServiceImpl")
public class CourseBillExcelServiceImpl implements ExcelDownloadService{

    @Autowired
    CourseDao courseDao;

    @Override
    public String getTitle(Map<String, Object> param) {
        String courseName ="";
        try {
            courseName = java.net.URLDecoder.decode(param.get("courseName").toString(),"utf-8");
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
        return courseName+"-教材选购清单";
    }

    @Override
    public String[][] getColTitle(Map<String, Object> param) {
        String[][] orginalCol = new String[][]{
                {"学生姓名","studentName"},
                {"学号","studentId"},
                {"班级","className"},
                {"电话","telephone"},
        };
        Long courseId = MapUtils.getLong(param,"courseId");
        List<CourseBookVO> booklist = courseDao.getCourseBookListByCourseId(courseId);
        String[][] result = new String[4+booklist.size()][2];
        System.arraycopy(orginalCol,0,result,0,4);
        int i = 4;
        for (CourseBookVO b: booklist) {
            result[i++] = new String[]{b.getBookname(),"bookname"+b.getBookId()};
        }

        return result;
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {
        Long courseId = MapUtils.getLong(param,"courseId");

        List<Map<String, Object>> result = courseDao.getCourseStudentByCourseId(courseId);
        List<CourseBookVO> booklist = courseDao.getCourseBookListByCourseId(courseId);

        List<String> chain = courseDao.queryCourseBook2WriterUserChain(courseId);

        for (Map<String, Object> stu:result) {
            for (CourseBookVO b: booklist) {
                stu.put("bookname"+b.getBookId(),choosen(chain,stu.get("writerUserId"),b.getId()));
            }
        }

        return result;
    }

    /**
     * 判断学生是否选中图书
     * @param chain
     * @param writerUserId
     * @param id
     * @return
     */
    private String choosen(List<String> chain,Object writerUserId, Long id) {
        if(chain.contains(id+"-"+writerUserId.toString())){
            return "√";
        }
        return "";
    }
}
