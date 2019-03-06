package com.bc.pmpheep.back.commuser.reportprogress.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.reportprogress.bean.Textbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.reportprogress.bean.Declaration;
import com.bc.pmpheep.back.commuser.reportprogress.bean.TextBookCheckVO;
import com.bc.pmpheep.back.commuser.reportprogress.bean.UserMessageVO;
import com.bc.pmpheep.back.commuser.reportprogress.dao.ReportProgressDao;
import com.bc.pmpheep.back.commuser.reportprogress.service.ReportProgressService;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

/**
 * 
 * <pre>
 * 功能描述：申报进度业务访问层接口实现类
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
@Service("com.bc.pmpheep.back.commuser.reportprogress.service.impl.ReportProgressServiceImpl")
public class ReportProgressServiceImpl implements ReportProgressService {
    @Autowired
    ReportProgressDao reportProgressDao;
    @Autowired
    ContentService contentService;

    @Override
    public TextBookCheckVO getMaterialProgress(Long userId, Long materialId) throws Exception {
        if (ObjectUtil.isNull(userId) || ObjectUtil.isNull(materialId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MATERIAL,
                                              CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        TextBookCheckVO textBookCheckVO = reportProgressDao.getMaterialProgress(userId, materialId);
        if (ObjectUtil.notNull(textBookCheckVO)) {
            // 审核进度 0=未提交/1=已提交/2=被退回/3=通过
            Integer online_progress_1 = 1;
            Integer online_progress_3 = 3;
            if (online_progress_1.intValue() == textBookCheckVO.getOnlineProgress().intValue()
                || online_progress_3 == textBookCheckVO.getOnlineProgress().intValue()) {
                textBookCheckVO.setOnlineSubmit(online_progress_1);// 已提交
            }
        }
        return textBookCheckVO;
    }

    @Override
    public List<TextBookCheckVO> getTextBookCheckResult(Long userId, Long materialId)
    throws Exception {
        if (ObjectUtil.isNull(userId) || ObjectUtil.isNull(materialId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MATERIAL,
                                              CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        return reportProgressDao.getTextBookCheckResult(userId, materialId);
    }

    @Override
    public List<UserMessageVO> getUserMessageByMaterialId(Long userId, Long materialId)
    throws Exception {
        if (ObjectUtil.isNull(userId) || ObjectUtil.isNull(materialId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MATERIAL,
                                              CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        List<UserMessageVO> userMessageList = new ArrayList<UserMessageVO>(4);
        List<UserMessageVO> lists =
        reportProgressDao.getUserMessageByMaterialId(userId, materialId);// 取近4条消息
//        List<String> listString = new ArrayList<String>();
//        for(UserMessageVO list:lists){
//        	listString.add(list.getMsgId());
//        	
//        }
//        List<Content> listContent = null;
//        try {
//        	listContent = contentService.list(listString);
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("user_message中数据异常，未找到mongon对应消息");
//		}
//       
//        for(UserMessageVO list:lists){
//        	for(Content content:listContent){
//        		if(list.getMsgId().equals(content.getId())){
//        			list.setMsgContent(content.getContent());
//        		}
//        	}
//        }
//        if (lists.size() == 4) {
//            userMessageList.addAll(lists);
//        } else {
         /*   Declaration declaration = this.getDeclarationByMaterialIdAndUserId(userId, materialId);
            if (ObjectUtil.notNull(declaration)) {
                userMessageList.add(new UserMessageVO(materialId, "您的申报已提交，请耐心等待审核...",
                                                      declaration.getGmtCreate()));
            }*/
            userMessageList.addAll(lists);
//        }
        return userMessageList;
    }

    @Override
    public Declaration getDeclarationByMaterialIdAndUserId(Long userId, Long materialId)
    throws Exception {
        if (ObjectUtil.isNull(userId) || ObjectUtil.isNull(materialId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MATERIAL,
                                              CheckedExceptionResult.NULL_PARAM, "参数为空");
        }
        return reportProgressDao.getDeclarationByMaterialIdAndUserId(userId, materialId);
    }

    /**
     * 获取教材下图书列表
     * @ materialId 教材id
     * @ userId 当前登录人用户id 若为机构用户传入null 表示不受此参数限制
     * @return
     */
    @Override
    public List<Textbook> getTextBookListByMaterialId(Map<String,Object> paraMap) {

        List<Textbook> list = reportProgressDao.getTextBookListByMaterialId(paraMap);

        return list;
    }

}
