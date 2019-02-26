package com.bc.pmpheep.back.commuser.course.service;

import com.bc.pmpheep.back.commuser.course.bean.CourseBookVO;
import com.bc.pmpheep.back.commuser.course.bean.CourseVO;
import com.bc.pmpheep.back.commuser.course.dao.CourseDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.util.CollectionUtil;
import com.bc.pmpheep.back.util.DateUtil;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("com.bc.pmpheep.back.commuser.course.service.CourseServiceImpl")
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Override
    public int getTeacherCourseListCount(PageParameter<CourseVO> pageParameter) {
        if(ObjectUtil.isNull(pageParameter.getParameter())){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE, CheckedExceptionResult.NULL_PARAM
                    ,"课程查询参数为空");
        }

        if(ObjectUtil.isNull(pageParameter.getParameter().getTeacherId())){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE, CheckedExceptionResult.NULL_PARAM
                    ,"教师id为空");
        }

        int count = courseDao.getTeacherCourseListCount(pageParameter);
        return count;
    }

    @Override
    public List<CourseVO> getTeacherCourseList(PageParameter<CourseVO> pageParameter) {

        if(ObjectUtil.isNull(pageParameter.getParameter())){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE, CheckedExceptionResult.NULL_PARAM
                    ,"课程查询参数为空");
        }

        if(ObjectUtil.isNull(pageParameter.getParameter().getTeacherId())){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE, CheckedExceptionResult.NULL_PARAM
                    ,"教师id为空");
        }

        List<CourseVO> list = courseDao.getTeacherCourseList(pageParameter);

        return list;
    }

    @Override
    public CourseVO getCourseById(Long id) {
        CourseVO course = courseDao.getCourseById(id);
        return course;
    }

    @Override
    public List<CourseBookVO> getCourseBookListByCourseId(Long id) {
        List<CourseBookVO> list = courseDao.getCourseBookListByCourseId(id);
        return list;
    }

    @Override
    public int getCourseBookListCountByCourseId(Long id) {
        int count = courseDao.getCourseBookListCountByCourseId(id);
        return count;
    }

    @Override
    public List<Long> getCourseAllBookId(Long id) {
        List<Long> list = courseDao.getCourseAllBookId(id);
        return list;
    }

    /**
     * 列出未选择的图书 供选择
     * @param pageParameter
     * @return
     */
    @Override
    public List<CourseBookVO> listBookUnSelected(PageParameter<Map<String, Object>> pageParameter) {
        List<CourseBookVO> list = courseDao.listBookUnSelected(pageParameter);
        return list;
    }

    /**
     * 未选择的图书总数 供选择
     * @param pageParameter
     * @return
     */
    @Override
    public int listBookUnSelectedCount(PageParameter<Map<String, Object>> pageParameter) {
        int count = courseDao.listBookUnSelectedCount(pageParameter.getParameter());
        return count;
    }

    @Override
    public int addCourseBookBatch(String[] addBookIdStrArr, Long courseId) {
        int c = 0;
        if(ObjectUtil.notNull(addBookIdStrArr) && addBookIdStrArr.length>0){
            c = courseDao.addCourseBookBatch(addBookIdStrArr,courseId);
        }
        return c;
    }

    @Override
    public int courseBookDelete(Long id) {
        int c = courseDao.courseBookDelete(id);
        return c;
    }

    @Override
    public int updateCourseBook(CourseBookVO courseBook) {
        if(ObjectUtil.isNull(courseBook) || ObjectUtil.isNull(courseBook.getId())){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE,CheckedExceptionResult.NULL_PARAM,"更新的课程选书参数为空");
        }

        int result = courseDao.updateCourseBook(courseBook);
        return result;
    }

    @Override
    public int saveCourse(CourseVO course, List<CourseBookVO> courseBookList) {


        int main_result = courseDao.saveCourse(course);

        if(ObjectUtil.isNull(course) || ObjectUtil.isNull(course.getId())){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE,CheckedExceptionResult.NULL_PARAM,"课程信息为空");
        }

        for (CourseBookVO cb: courseBookList) {
            cb.setCourseId(course.getId());
        }

        int delete_result = courseDao.courseBookDeleteByCourseId(course.getId());

        if(CollectionUtil.isNotEmpty(courseBookList)){
            int fellow_result = courseDao.saveCourseBookList(courseBookList);
        }


        return main_result;
    }

    @Override
    public Boolean existWriterUserByUsername(String username) {

        Boolean result = true;
        int i= courseDao.queryCountWriterUserByUsername(username);
        result = (i>0);
        return result;
    }

    @Override
    public int courseStatusModify(CourseVO course) {

        if(ObjectUtil.isNull(course) ){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE, CheckedExceptionResult.NULL_PARAM
                    ,"课程查询参数为空");
        }

        int modCount = courseDao.courseStatusModify(course);

        return modCount;
    }
}
