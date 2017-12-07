package com.bc.pmpheep.back.commuser.reportprogress.service;

import java.util.List;

import com.bc.pmpheep.back.commuser.reportprogress.bean.Declaration;
import com.bc.pmpheep.back.commuser.reportprogress.bean.TextBookCheckVO;
import com.bc.pmpheep.back.commuser.reportprogress.bean.UserMessageVO;

/**
 * 
 * <pre>
 * 功能描述：申报进度业务访问层接口
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
public interface ReportProgressService {
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
    TextBookCheckVO getMaterialProgress(Long userId, Long materialId) throws Exception;

    /**
     * 
     * <pre>
     * 功能描述：获取申报教材中书籍审核结果
     * 使用示范：
     *
     * @param userId 作家ID
     * @param materialId 教材ID
     * @return
     * </pre>
     */
    List<TextBookCheckVO> getTextBookCheckResult(Long userId, Long materialId) throws Exception;

    /**
     * 
     * <pre>
     * 功能描述：获取作家用户申报消息
     * 使用示范：
     *
     * @param userId 作家ID
     * @param materialId 教材ID
     * @return
     * @throws Exception
     * </pre>
     */
    List<UserMessageVO> getUserMessageByMaterialId(Long userId, Long materialId) throws Exception;

    /**
     * 
     * <pre>
     * 功能描述：获取作家用户申报教材信息
     * 使用示范：
     *
     * @param userId 作家ID
     * @param materialId 教材ID
     * @return
     * @throws Exception
     * </pre>
     */
    Declaration getDeclarationByMaterialIdAndUserId(Long userId, Long materialId) throws Exception;

}
