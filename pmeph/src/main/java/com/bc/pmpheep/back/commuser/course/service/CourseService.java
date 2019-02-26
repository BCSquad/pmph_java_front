package com.bc.pmpheep.back.commuser.course.service;

import com.bc.pmpheep.back.commuser.course.bean.CourseBookVO;
import com.bc.pmpheep.back.commuser.course.bean.CourseVO;
import com.bc.pmpheep.back.plugin.PageParameter;

import java.util.List;
import java.util.Map;

public interface CourseService  {

    /**
     * 教师查询课程列表总数
     * @param pageParameter
     * @return
     */
    int getTeacherCourseListCount(PageParameter<CourseVO> pageParameter);

    /**
     * 教师查询课程列表
     * @param pageParameter
     * @return
     */
    List<CourseVO> getTeacherCourseList(PageParameter<CourseVO> pageParameter);

    /**
     * 通过主键查询课程
     * @param id
     * @return
     */
    CourseVO getCourseById(Long id);

    /**
     * 获取课程已选图书列表
     * @param id
     * @return
     */
    List<CourseBookVO> getCourseBookListByCourseId(Long id);

    /**
     * 获取课程已选图书列表总数
     * @param id
     * @return
     */
    int getCourseBookListCountByCourseId(Long id);

    /**
     * 获取课程已选图书 bookId
     * @param id
     */
    List<Long> getCourseAllBookId(Long id);

    /**
     * 列出未选择的图书 供选择
     * @param pageParameter
     * @return
     */
    List<CourseBookVO> listBookUnSelected(PageParameter<Map<String, Object>> pageParameter);

    /**
     * 未选择的图书总数 供选择
     * @param pageParameter
     * @return
     */
    int listBookUnSelectedCount(PageParameter<Map<String, Object>> pageParameter);

    /**
     * 批量添加课程图书
     * @param addBookIdStrArr
     * @param courseId
     * @return
     */
    int addCourseBookBatch(String[] addBookIdStrArr, Long courseId);

    /**
     * 删除课程图书
     * @param id
     * @return
     */
    int courseBookDelete(Long id);

    /**
     * 修改
     * @param courseBook
     * @return
     */
    int updateCourseBook(CourseBookVO courseBook);

    /**
     * 插入或更新
     * @param course
     * @param courseBookList
     * @return
     */
    int saveCourse(CourseVO course, List<CourseBookVO> courseBookList);

    /**
     * 是否存在此用户名的writer_user用户
     * @param username
     * @return
     */
    Boolean existWriterUserByUsername(String username);

    /**
     * 修改课程状态
     * @param course
     * @return
     */
    int courseStatusModify(CourseVO course);
}
