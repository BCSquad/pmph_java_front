package com.bc.pmpheep.back.commuser.reportprogress.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bc.pmpheep.back.commuser.reportprogress.bean.TextBookCheckVO;

/**
 * 
 * <pre>
 * 功能描述：申报进度数据访问层接口
 * 使用示范：
 * 
 * 
 * @author (作者) nyz
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-11-30
 * @modify (最后修改时间) 
 * @修改人 ：nyz 
 * @审核人 ：
 * </pre>
 */
public interface ReportProgressDao {
    /**
     * 
     * <pre>
     * 功能描述：获取申报教材审核进度
     * 使用示范：
     *
     * @param userId 作家ID
     * @param materialId 教材ID
     * @return
     * </pre>
     */
    TextBookCheckVO getMaterialProgress(@Param("userId") Long userId,
    @Param("materialId") Long materialId);

    /**
     * 
     * <pre>
     * 功能描述：获取申报教材中书籍审核结果
     * 使用示范：
     *
     * @param userId
     * @param materialId
     * @return
     * </pre>
     */
    List<TextBookCheckVO> getTextBookCheckResult(@Param("userId") Long userId,
    @Param("materialId") Long materialId);

}
