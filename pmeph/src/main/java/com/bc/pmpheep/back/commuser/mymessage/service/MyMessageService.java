package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.List;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.service.exception.CheckedServiceException;

public interface MyMessageService {

    /**
     * 功能描述：获取我的消息列表
     *
     * @param pageParameter 分页参数,title 消息标题,isRead 是否已读,userId 用户id,userType 用户类型
     * @return
     * @throws CheckedServiceException
     */
    List<MyMessageVO> listMyMessage(Integer pageNumber, Integer pageSize, String state, Long userId) throws CheckedServiceException;

    /**
     * 功能描述：
     *
     * @param senderId   发送者id
     * @param senderType
     * @param userId
     * @param userType
     * @return
     * @throws CheckedServiceException
     */
    List<MyMessageVO> updateMyMessage(Long senderId, Integer senderType, Long userId, Integer userType)
            throws CheckedServiceException;

    /**
     * 获取我和朋友的对话记录
     *
     * @param thisId
     * @param friendId
     * @return
     * @author Mryang
     * @createDate 2017年12月7日 下午2:20:33
     */
    List<DialogueVO> findMyDialogue(Long thisId, Long friendId, Integer friendType);

    /**
     * 更新我和对话者的对话状态
     *
     * @param senderId
     * @param senderType
     * @param receiverId
     * @param receiverType
     * @return
     * @author Mryang
     * @createDate 2017年12月11日 下午4:57:50
     */
    Integer updateMyTalk(Long senderId, Short senderType, Long receiverId, Short receiverType);

    /**
     * 发送私信
     *
     * @param frendId
     * @param content
     * @introduction
     * @author Mryang
     * @createDate 2017年12月7日 下午3:51:11
     */
    void senNewMsg(Long thisId, Short thisType, Long frendId, Short friendIdType, String title, String content);


    /**
     * 作家用户发送通知给管理员
     *
     * @param orgId   机构用户ID
     * @param title   消息标题
     * @param content 消息内容
     */
    void sendNewMsgWriterToOrg(Long orgId, String title, String content);

    /**
     * 作家用户发送通知给出版社
     *
     * @param publisherId 出版社用户ID
     * @param title       消息标题
     * @param content     消息内容
     */
    void sendNewMsgWriterToPublisher(Long materialId, String title, String content);


    /**
     * 机构用户发送消息给作家
     *
     * @param senderOrgId      机构用户ID
     * @param receiverWriterId 作家用户ID
     * @param title            消息标题
     * @param content          消息内容
     */
    void sendNewMsgOrgToWriter(Long senderOrgId, Long receiverWriterId, String title, String content);


    /**
     * 机构用户发送通知给作家
     *
     * @param receiverWriterId 作家用户ID
     * @param schoolName       学校名称
     */
    void sendNewNoticeOrgToWriter(String type, Long receiverWriterId, String schoolName);


    /**
     * 申报表审核发送通知
     *
     * @param type
     * @param receiverWriterId
     * @param materialName
     */
    public void sendNewNoticeDeclare(String type, Long receiverWriterId, String materialName);

    public void sendMsg(Short msgType, Short senderType, Long senderId, Short receiverType, Long receiverId, String title, String content);

    /**
     * 教师认证发送通知
     */
    public void sendTeacherMsg(Long orgId, String teacherName,String date);
}
