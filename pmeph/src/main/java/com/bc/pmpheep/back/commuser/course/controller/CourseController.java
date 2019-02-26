package com.bc.pmpheep.back.commuser.course.controller;


import com.bc.pmpheep.back.commuser.booksearch.service.BookSearchService;
import com.bc.pmpheep.back.commuser.course.bean.CourseBookVO;
import com.bc.pmpheep.back.commuser.course.bean.CourseVO;
import com.bc.pmpheep.back.commuser.course.service.CourseService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 课程选书 控制层
 */
@Controller
@RequestMapping("course")
public class CourseController  extends BaseController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.course.service.CourseServiceImpl")
    CourseService courseService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.booksearch.service.BookSearchServiceImpl")
    BookSearchService bookSearchService;

    /**
     * 进入 教师的 课程列表界面
     * @param request
     * @return
     */
    @RequestMapping(value = "teacher/toCourseList",method = RequestMethod.GET)
    public ModelAndView teacherToCourseList(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("commuser/course/teacher/courseList");
        Map<String, Object> user = getUserInfo();
        if(MapUtils.getBooleanValue(user,"is_teacher")){
            throw new CheckedServiceException(CheckedExceptionBusiness.COURSE, CheckedExceptionResult.NULL_PARAM,"用户并非教师");
        }
        return mv;
    }

    /**
     * 查询 教师的 课程列表数据
     * @param request
     * @param course
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "teacher/getTeacherCourseList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTeacherCourseList(HttpServletRequest request
            ,CourseVO course
            ,@RequestParam(defaultValue = "1") Integer pageNumber
            ,@RequestParam(defaultValue = "10") Integer pageSize
    ){
        Map<String, Object> user = getUserInfo();
        course.setTeacherId(MapUtils.getLong(user,"id"));
        String contextpath = request.getParameter("contextpath");

        PageParameter<CourseVO> pageParameter = new PageParameter<>(pageNumber,pageSize);
        pageParameter.setParameter(course);
        int totalCount = courseService.getTeacherCourseListCount(pageParameter);
        List<CourseVO> list = new ArrayList<CourseVO>();
        if(totalCount>0){
            list = courseService.getTeacherCourseList(pageParameter);
        }

        Map<String, Object> vm_map = new HashMap<String, Object>();
        vm_map.put("List_map", list);
        vm_map.put("startNum", pageParameter.getStart() + 1);
        vm_map.put("contextpath", contextpath);
        String html = "";
        String vm = "commuser/personalcenter/courseList.vm";
        html = templateService.mergeTemplateIntoString(vm, vm_map);

        Map<String, Object> data_map = new HashMap<String, Object>();
        data_map.put("html", html);
        data_map.put("totoal_count", totalCount);

        return data_map;
    }

    /**
     * 跳转到课程详情页面，包括了 新增/修改/复制新增
     * @param request
     * @param id
     * @param copyNew
     * @return
     */
    @RequestMapping(value = "teacher/toCourseDetail",method = RequestMethod.GET)
    public ModelAndView teacherToCourseDetail(HttpServletRequest request,
                                              Long id,
                                              @RequestParam(defaultValue = "false") Boolean copyNew){
        ModelAndView mv = new ModelAndView();

        CourseVO course = new CourseVO();
        int bookCount = 0;
        List<CourseBookVO> courseBookList = new ArrayList<CourseBookVO>();

        Boolean readOnly = false;
        Boolean isNew = false;

        //id 非空 可能为修改或复制新增 需加载数据
        if(ObjectUtil.notNull(id)){
            bookCount = courseService.getCourseBookListCountByCourseId(id);
            if(bookCount>0){
                courseBookList = courseService.getCourseBookListByCourseId(id);
            }
            course = courseService.getCourseById(id);
            if(ObjectUtil.notNull(course.getPublished()) && course.getPublished()){
                readOnly = true;
            }
        }

        //新增 将courseId和选书id置空
        if(ObjectUtil.isNull(id) || copyNew){
            course.setId(null);
            for (CourseBookVO cb:courseBookList) {
                cb.setId(null);
                cb.setCourseId(null);
            }
            isNew = true;
        }

        mv.addObject("course",course);
        mv.addObject("courseBookList",courseBookList);
        mv.addObject("bookCount",bookCount);

        mv.addObject("isNew",isNew);
        mv.addObject("readOnly",readOnly);
        mv.setViewName("commuser/course/teacher/course");

        return mv;
    }

    /**
     * 获取课程下所有图书
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCourseBookList",method = RequestMethod.POST)
    @ResponseBody
    public List<CourseBookVO> getCourseBookList(HttpServletRequest request,
                                                @RequestParam(required = true) Long id){
        List<CourseBookVO> list = new ArrayList<CourseBookVO>();
        list = courseService.getCourseBookListByCourseId(id);
        return list;
    }

    /**
     * 获取课程下所有图书的id
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCourseAllBookId",method = RequestMethod.POST)
    @ResponseBody
    public List<Long> getCourseAllBookId(HttpServletRequest request,
                                                @RequestParam(required = true) Long id){
        List<Long> list = courseService.getCourseAllBookId(id);
        return list;
    }


    /**
     * 查询所有图书列表刷新
     */
    @RequestMapping(value = "/querybooklist",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> querybooklist(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String bookname = request.getParameter("bookname");
        String isbn = request.getParameter("isbn");
        String courseId = request.getParameter("courseId");
        String contextpath = request.getParameter("contextpath");


        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("bookname",bookname);
        paraMap.put("isbn",isbn);
        paraMap.put("courseId",courseId);
        PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
        pageParameter.setParameter(paraMap);

        List<CourseBookVO> list = courseService.listBookUnSelected(pageParameter);
        int totoal_count =courseService.listBookUnSelectedCount(pageParameter);

        Map<String, Object> vm_map = new HashMap<String, Object>();
        vm_map.put("List_map", list);
        vm_map.put("startNum", pageParameter.getStart() + 1);
        vm_map.put("contextpath", contextpath);
        String html = "";
        String vm = "commuser/personalcenter/courseQueryBookList.vm";
        html = templateService.mergeTemplateIntoString(vm, vm_map);

        Map<String, Object> data_map = new HashMap<String, Object>();
        data_map.put("html", html);
        data_map.put("totoal_count", totoal_count);

        return data_map;
    }

    /**
     * 教师新增如上图书
     * @param request
     * @param addBookIdStr
     * @return
     */
    @RequestMapping(value = "teacher/addBook",method = RequestMethod.POST)
    @ResponseBody
    public int teacherAddBook(HttpServletRequest request,
                              @RequestParam(required = true)String addBookIdStr,
                              @RequestParam(required = true) Long courseId){
        String[] addBookIdStrArr = addBookIdStr.split(",");
        int count = courseService.addCourseBookBatch(addBookIdStrArr,courseId);
        return count;
    }

    /**
     * 删除单个课程图书
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "courseBookDelete",method = RequestMethod.POST)
    @ResponseBody
    public int teacherAddBook(HttpServletRequest request,
                              @RequestParam(required = true) Long id){
        int count = courseService.courseBookDelete(id);
        return count;
    }

    /**
     * 更新课程图书的备注
     * @param request
     * @param courseBook
     * @return
     */
    @RequestMapping(value = "updateCourseBookNote",method = RequestMethod.POST)
    @ResponseBody
    public int updateCourseBookNote(HttpServletRequest request,
                              CourseBookVO courseBook){
        int count = courseService.updateCourseBook(courseBook);
        return count;
    }

    /**
     * 新建或保存课程及课程图书
     * @param request
     * @param course
     * @param books
     * @return
     */
    @RequestMapping(value = "saveCourse",method = RequestMethod.POST)
    @ResponseBody
    public int saveCourse(HttpServletRequest request,CourseVO course,String books){
        Gson gson = new Gson();
        Map<String, Object> user = getUserInfo();
        course.setTeacherId(MapUtils.getLong(user,"id"));
        List<CourseBookVO> courseBookList = gson.fromJson(books,
                new TypeToken<ArrayList<CourseBookVO>>() {
                }.getType());

        int count = 0;

        count = courseService.saveCourse(course,courseBookList);

        return count;
    }

    /**
     * 是否存在此用户名的用户
     * @param username
     * @return
     */
    @RequestMapping(value = "existWriterUserByUsername",method = RequestMethod.GET)
    @ResponseBody
    public Boolean existWriterUserByUsername(String username){
        Boolean result = true;

        result = courseService.existWriterUserByUsername(username);

        return result;
    }

    @RequestMapping(value = "courseStatusModify")
    @ResponseBody
    public Map<String,Object> courseStatusModify(HttpServletRequest request,
                                                 CourseVO course){
        Map<String,Object> resultMap = new HashMap<String,Object>();

        int modCount= courseService.courseStatusModify(course);

        resultMap.put("modCount",modCount);

        return resultMap;
    }

}
