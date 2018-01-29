package com.bc.pmpheep.back.commuser.survey.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SurveyDao {

    // 调查列表
    List<Map<String, Object>> surveyList(Map<String, Object> map);

    // 查询的单个调查对应的问题
    List<Map<String, Object>> getSurvey(long surveyId);

    // 查询问题对应的选项
    List<Map<String, Object>> getOptions(Long questionId);

    // 保存单选答案
    void saveRadioAnswer(Map<String, Object> map);

    // 保存多选答案
    void saveCheckboxAnswer(Map<String, Object> map1);

    // 保存输入框答案
    void saveInputAnswer(Map<String, Object> map2);

    /**
     * 
     * <pre>
     * 功能描述：按用户ID删除
     * 使用示范：
     *
     * @param userId 用户ID
     * @return 影响行数
     * </pre>
     */
    Integer deleteUserAnswer(@Param("userId") Long userId);

}
