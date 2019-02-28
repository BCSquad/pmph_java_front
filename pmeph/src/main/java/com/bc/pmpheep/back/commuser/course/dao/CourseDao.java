package com.bc.pmpheep.back.commuser.course.dao;

import com.bc.pmpheep.back.commuser.course.bean.CourseBookStudentVO;
import com.bc.pmpheep.back.commuser.course.bean.CourseBookVO;
import com.bc.pmpheep.back.commuser.course.bean.CourseVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CourseDao {

    List<CourseVO> getTeacherCourseList(PageParameter<CourseVO> pageParameter);

    int getTeacherCourseListCount(PageParameter<CourseVO> pageParameter);

    CourseVO getCourseById(Long id);

    List<CourseBookVO> getCourseBookListByCourseId(Long id);

    int getCourseBookListCountByCourseId(Long id);

    /**
     * 获取 某课程 所有已选图书的bookid
     * @param id 课程id
     * @return
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
     * @param
     * @return
     */
    int listBookUnSelectedCount(Map<String, Object> parameter);

    /**
     * 批量添加课程图书
     * @param bookIds
     * @param courseId
     * @return
     */
    int addCourseBookBatch(@Param("bookIds") String[] bookIds,@Param("courseId") Long courseId);

    /**
     * 删除课程图书
     * @param id
     * @return
     */
    int courseBookDelete(Long id);

    /**
     *
     * @param courseBook
     * @return
     */
    int updateCourseBook(CourseBookVO courseBook);

    /**
     * 新增或更新课程
     * @param course
     * @return
     */
    int saveCourse(CourseVO course);

    /**
     * 批量新增或更新课程
     * @param courseBookList
     * @return
     */
    int saveCourseBookList(List<CourseBookVO> courseBookList);

    /**
     * 删除本课程所有图书
     * @param id
     * @return
     */
    int courseBookDeleteByCourseId(Long id);

    /**
     * 此用户名的用户总数
     * @param username
     * @return
     */
    int queryCountWriterUserByUsername(String username);

    /**
     * 课程信息修改
     * @param course
     * @return
     */
    int courseStatusModify(CourseVO course);

    int querybookStudentListCount(PageParameter<CourseBookStudentVO> pageParameter);

    List<CourseBookStudentVO> querybookStudentList(PageParameter<CourseBookStudentVO> pageParameter);

    /**
     *
     * @param courseBookStudent
     * @return
     */
    int updateCourseBookStudent(CourseBookStudentVO courseBookStudent);

    /**
     * 返回图书订单总数
     * @param courseBookId
     * @return
     */
    int getCountResByCourseBookId(Long courseBookId);
}
