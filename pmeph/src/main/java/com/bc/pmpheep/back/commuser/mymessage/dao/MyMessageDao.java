package com.bc.pmpheep.back.commuser.mymessage.dao;

import java.util.List;

import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.service.exception.CheckedServiceException;

public interface MyMessageDao {
    /**
     * 
     * 
     * 功能描述：获取我的消息列表
     * 
     * @param pageParameter 分页参数,title 消息标题,isRead 是否已读,userId 用户id,userType 用户类型
     * @return
     * @throws CheckedServiceException
     * 
     */
    List<MyMessageVO> listMyMessage(PageParameter<MyMessageVO> pageParameter);

    /**
     * 
     * 
     * 功能描述：获取总条数
     * 
     * @param pageParameter
     * @return
     * 
     */
    Integer listMyMessageTotal(PageParameter<MyMessageVO> pageParameter);

    /**
     * 
     * 
     * 功能描述：获取消息详情
     * 
     * @param id 消息id
     * @return
     * @throws CheckedServiceException
     * 
     */
    MyMessageVO getMyMessageDetail(Long id);

    /**
     * 
     * 
     * 功能描述：逻辑删除我的消息
     * 
     * @param ids 需要删除的消息id
     * @return
     * @throws CheckedServiceException
     * 
     */
    Integer updateMyMessage(Long[] ids);
    
    /**
     * 通过id 动态更新UserMessage
     */
    Integer readMyMessage(MyMessageVO messageVO);
}
