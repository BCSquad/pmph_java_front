package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessage;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.dao.MyMessageDao;
import com.bc.pmpheep.back.commuser.user.bean.CommuserPmphUser;
import com.bc.pmpheep.back.commuser.user.bean.CommuserWriterUser;
import com.bc.pmpheep.back.commuser.user.service.PmphUserCommuserService;
import com.bc.pmpheep.back.commuser.user.service.WriterUserCommuserService;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;

@Service("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
public class MyMessageServiceImpl implements MyMessageService {

    @Autowired
    MyMessageDao myMessageDao;
    @Autowired
    @Qualifier("com.bc.pmpheep.general.service.MessageService")
    MessageService messageService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MessageAttachmentServiceImpl")
    MessageAttachmentService messageAttachmentService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.user.service.commPmphUserServiceImpl")
    PmphUserCommuserService pmphUserService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.user.service.commWriterUserServiceImpl")
    WriterUserCommuserService writerUserService;

    @Override
    public List<MyMessageVO> listMyMessage(Integer pageNumber, Integer pageSize, String state, Long userId) throws CheckedServiceException {
        if (null == userId) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                    "用户id为空！");
        }
        if (null == pageNumber || pageNumber < 1) {
            pageNumber = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 999999;
        }
        List<MyMessageVO> list = myMessageDao.listMyMessage((pageNumber - 1) * pageSize, pageSize, state, userId);
        if (null == list) {
            list = new ArrayList<MyMessageVO>(1);
        }
        for (MyMessageVO myMessageVO : list) {
            String msgId = myMessageVO.getMsgId();
            Message message = messageService.get(msgId);
            myMessageVO.setContent(null == message ? null : message.getContent());
            myMessageVO.setAvatar(RouteUtil.userAvatar(myMessageVO.getAvatar()));
        }
        return list;
    }

    /**
     * 功能描述：给消息赋予头像
     *
     * @param myMessageVO
     * @return
     */
    public MyMessageVO setAvatar(MyMessageVO myMessageVO) {
        CommuserWriterUser user = writerUserService.get(myMessageVO.getUserId());
        myMessageVO.setUserAvatar(RouteUtil.userAvatar(user.getAvatar()));
        myMessageVO.setUserName(user.getRealname());
        if (myMessageVO.getSenderId().equals(user.getId()) && myMessageVO.getSenderType().equals(2)) {
            switch (myMessageVO.getReceiverType()) {
                case 0:
                    myMessageVO.setName("系统");
                    break;
                case 1:
                    CommuserPmphUser pmphUser = pmphUserService.get(myMessageVO.getReceiverId());
                    myMessageVO.setAvatar(RouteUtil.userAvatar(pmphUser.getAvatar()));
                    myMessageVO.setName(pmphUser.getRealname());
                    break;

                case 2:
                    CommuserWriterUser writerUser = writerUserService.get(myMessageVO.getReceiverId());
                    myMessageVO.setAvatar(RouteUtil.userAvatar(writerUser.getAvatar()));
                    myMessageVO.setName(writerUser.getRealname());
                    break;

                case 3:
                    // 现在没有机构用户
                    break;

                default:
                    throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                            "发送者类型不正确！");
            }
        } else {
            switch (myMessageVO.getSenderType()) {
                case 0:
                    myMessageVO.setName("系统");
                    break;
                case 1:
                    CommuserPmphUser pmphUser = pmphUserService.get(myMessageVO.getSenderId());
                    myMessageVO.setAvatar(RouteUtil.userAvatar(pmphUser.getAvatar()));
                    myMessageVO.setName(pmphUser.getRealname());
                    break;

                case 2:
                    CommuserWriterUser writerUser = writerUserService.get(myMessageVO.getSenderId());
                    myMessageVO.setAvatar(RouteUtil.userAvatar(writerUser.getAvatar()));
                    myMessageVO.setName(writerUser.getRealname());
                    break;

                case 3:
                    // 现在没有机构用户
                    break;

                default:
                    throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                            "发送者类型不正确！");
            }
        }

        if (myMessageVO.getUserId().equals(myMessageVO.getReceiverId())
                && myMessageVO.getUserType().equals(myMessageVO.getReceiverType())) {// 当接收者是当前用户时，表示不是我发送的
            myMessageVO.setIsMy(false);

        }
        if (myMessageVO.getUserId().equals(myMessageVO.getSenderId())
                && myMessageVO.getUserType().equals(myMessageVO.getSenderType())) {// 当发送者是当前用户时，表示是我发送的
            myMessageVO.setIsMy(true);
        }

        return myMessageVO;
    }

    @Override
    public List<MyMessageVO> updateMyMessage(Long senderId, Integer senderType, Long userId, Integer userType)
            throws CheckedServiceException {
        if (ObjectUtil.isNull(senderId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                    "对话者id为空");
        }
        if (ObjectUtil.isNull(senderType)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                    "对话者类型为空");
        }
        if (ObjectUtil.isNull(userId)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                    "用户id为空");
        }
        if (ObjectUtil.isNull(userType)) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE, CheckedExceptionResult.NULL_PARAM,
                    "用户类型为空");
        }
        List<MyMessageVO> list = myMessageDao.listMyMessageDetail(senderId, senderType, userId, userType);
        for (MyMessageVO myMessageVO : list) {
            myMessageVO.setUserId(userId);
            myMessageVO.setUserType(userType);
            myMessageVO = setAvatar(myMessageVO);
            // Message message = messageService.get(myMessageVO.getMsgId());
            // if (ObjectUtil.isNull(message)) {
            // throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
            // CheckedExceptionResult.NULL_PARAM,
            // "没有获取到内容！");
            // }
            // myMessageVO.setContent(message.getContent());

        }
        return list;
    }

    @Override
    public List<DialogueVO> findMyDialogue(Long thisId, Long friendId, Integer friendType) {
        if (null == friendType) {
            friendType = 2; //默认作家用户
        }
        List<DialogueVO> lst = myMessageDao.findMyDialogue(thisId, friendId, friendType);
        // 装入详情
        List<String> ids = new ArrayList<String>();
        Map<String, DialogueVO> map = new HashMap<String, DialogueVO>();
        for (DialogueVO dialogueVO : lst) {
            ids.add(dialogueVO.getMsgId());
            map.put(dialogueVO.getMsgId(), dialogueVO);
            String avatar = dialogueVO.getAvatar();
            dialogueVO.setAvatar(RouteUtil.userAvatar(avatar));
        }
        List<Message>  messageLists = messageService.list(ids);
        for (Message message : messageLists) {
            DialogueVO dialogueVO = map.get(message.getId());
            if (null != message) {
                dialogueVO.setContent(message.getContent());
            }

        }

        return lst;
    }

    @Override
    public Integer updateMyTalk(Long senderId, Short senderType, Long receiverId, Short receiverType) {
        return myMessageDao.updateMyTalk(senderId, senderType, receiverId, receiverType);
    }

    @Override
    public void senNewMsg(Long thisId, Short thisType, Long frendId, Short friendIdType, String title, String content) {
        Message message = new Message();
        message.setContent(content);
        messageService.add(message);
        MyMessage userMessage = new MyMessage();
        userMessage.setMsgId(message.getId());
        userMessage.setMsgType(new Short("2"));
        userMessage.setTitle(title);
        userMessage.setSenderId(thisId);
        if (null == thisType) {
            userMessage.setSenderType(new Short("2"));
        } else {
            userMessage.setSenderType(thisType);
        }
        userMessage.setReceiverId(frendId);
        if (null == friendIdType) {
            userMessage.setReceiverType(new Short("2"));
        } else {
            userMessage.setReceiverType(friendIdType);
        }
        myMessageDao.addUserMessage(userMessage);
    }

    @Override
    public void sendMsg(Short msgType, Short senderType, Long senderId, Short receiverType, Long receiverId, String title, String content) {
        Message message = new Message();
        message.setContent(content);
        messageService.add(message);
        MyMessage userMessage = new MyMessage();
        userMessage.setMsgId(message.getId());
        userMessage.setMsgType(msgType);
        userMessage.setTitle(title);
        userMessage.setSenderId(senderId);

        userMessage.setSenderType(senderType);

        userMessage.setReceiverId(receiverId);

        userMessage.setReceiverType(receiverType);

        if (myMessageDao.addUserMessage(userMessage) == 0) {
            throw new RuntimeException("发送消息失败！");
        }
    }


    @Override
	public void sendNewMsgWriterToOrg(Long orgId, String teacherName, String materialName,String user_id) {
    	
    	
        for (Map<String, Object> item : myMessageDao.getOrgUser(orgId)) {
            this.sendMsg(new Short("0"), new Short("2"), Long.valueOf(user_id), new Short("3"), MapUtils.getLong(item, "id"), "系统消息", "贵校老师[" + teacherName + "]提交了《" + materialName + "》申报表，请及时进行资料审核、打印并快递申报纸质表");
        }
    }

    @Override
    public void sendNewMsgWriterToPublisher(Long materialId, String teacherName, String materialName,String user_id) {
        List<Map<String, Object>> list = myMessageDao.getMaterialManagers(materialId);
        for (Map<String, Object> item : list) {
            this.sendMsg(new Short("0"), new Short("2"), Long.valueOf(user_id), new Short("1"), MapUtils.getLong(item, "director"), "系统消息", "[" + teacherName + "]提交了《" + materialName + "》申报表，请及时进行资料审核");
        }
    }

    @Override
    public void sendNewMsgOrgToWriter(Long senderOrgId, Long receiverWriterId, String title, String content) {
        this.sendMsg(new Short("1"), new Short("3"), senderOrgId, new Short("2"), receiverWriterId, title, content);
    }

    @Override
    public void sendNewNoticeOrgToWriter(String type, Long receiverWriterId, Map<String, Object> userInfo) {
    	String schoolName = MapUtils.getString(userInfo, "org_name");
    	Long senderId = Long.parseLong(MapUtils.getString(userInfo, "id")!=null?MapUtils.getString(userInfo, "id"):"0");
        if (type.equals("1")) {
            this.sendMsg(new Short("0"), new Short("3"), senderId, new Short("2"), receiverWriterId, "系统消息", "恭喜！您提交的教师认证资料已通过[" + schoolName + "]管理员审核");
        } else {
            this.sendMsg(new Short("0"), new Short("3"), senderId, new Short("2"), receiverWriterId, "系统消息", "抱歉，您提交的教师认证资料已被[" + schoolName + "]管理员退回，请您核对后重试");
        }

    }

    @Override
    public void sendNewNoticeDeclare(String type, Long receiverWriterId, String materialName,Map<String, Object> userInfo) {
        if (type.equals("1")) {
            this.sendMsg(new Short("0"), new Short("3"), 0L, new Short("2"), receiverWriterId, "系统消息", "恭喜！您提交的《" + materialName + "》申报表已通过[学校管理员/出版社]审核");
        } else {
            this.sendMsg(new Short("0"), new Short("3"), 0L, new Short("2"), receiverWriterId, "系统消息", " 抱歉，您提交的《" + materialName + "》申报表被[学校管理员/出版社]退回，请您核对后重试");
        }

    }

    @Override
    public void sendTeacherMsg(Long orgId, String teacherName,String date,Long senderId) {
        for (Map<String, Object> item : myMessageDao.getOrgUser(orgId)) {
            this.sendMsg(new Short("0"), new Short("2"), senderId, new Short("3"), MapUtils.getLong(item, "id"), "系统消息",  teacherName + "于"+date+"提交了教师资格认证，请您尽快审核。");
        }
    }

}
