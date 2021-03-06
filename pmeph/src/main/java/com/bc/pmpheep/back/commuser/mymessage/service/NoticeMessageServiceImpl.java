package com.bc.pmpheep.back.commuser.mymessage.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.mymessage.dao.NoticeMessageDao;


@Service("com.bc.pmpheep.back.commuser.mymessage.service.NoticeMessageServiceImpl")
public class NoticeMessageServiceImpl implements NoticeMessageService {

    @Autowired
    NoticeMessageDao noticeMessageDao;

    @Autowired
    MessageService messageService;

    //申请信息列表
    @Override
    public List<Map<String, Object>> selectApplyMessage(Map<String, Object> paraMap) {
        List<Map<String, Object>> list = noticeMessageDao.selectApplyMessage(paraMap);
        return list;
    }

    //更新申请消息表
    @Override
    public void updateApplyMessage(Map<String, Object> paraMap) {
        noticeMessageDao.updateApplyMessage(paraMap);
    }

    //消息通知列表
    @Override
    public List<Map<String, Object>> selectNoticeMessage(Map<String, Object> paraMap) {
        List<Map<String, Object>> list = noticeMessageDao.selectNoticeMessage(paraMap);
        List<String> messageids = new ArrayList<String>();
        Map<String, Map<String, Object>> messageMap = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> message : list) {
            messageids.add(MapUtils.getString(message, "fId"));
            messageMap.put(MapUtils.getString(message, "fId"), message);
        }
        List<Message> messages = messageService.list(messageids);
        for (Message message : messages) {
            messageMap.get(message.getId()).put("messageContent", message.getContent());
        }
        return list;
    }

    //删除通知
    @Override
    public void deleteNoticeMessage(Map<String, Object> paraMap) {
        noticeMessageDao.deleteNoticeMessage(paraMap);

    }

    //教材申报通知详情
    @Override
    public Map<String, Object> queryNoticeMessageDetail(Map<String, Object> paraMap) {
        Map<String, Object> map = noticeMessageDao.noticeMessageDetail(paraMap);
        return map;
    }


    //查询教材备注相关的附件
    @Override
    public List<Map<String, Object>> queryNoticeMessageDetailAttachment(Map<String, Object> paraMap) {
        List<Map<String, Object>> list = noticeMessageDao.queryNoticeMessageDetailAttachment(paraMap);
        return list;
    }

    //查询教材相关联系人
    @Override
    public List<Map<String, Object>> queryNoticeMessageDetailContact(Map<String, Object> paraMap) {
        List<Map<String, Object>> list = noticeMessageDao.queryNoticeMessageDetailContact(paraMap);
        return list;
    }

    //查询遴选公告的内容id的查询公告详情
    @Override
    public Map<String, Object> queryCMSNotice(Map<String, Object> paraMap) {
        // TODO Auto-generated method stub
        return noticeMessageDao.queryCMSNotice(paraMap);
    }

    //更新通知点击量
    @Override
    public void updateNoticeClicks(String cmsId) {
        noticeMessageDao.updateNoticeClicks(cmsId);

    }

    //查询通知数据总量
    @Override
    public int selectNoticeMessageTotalCount(Map<String, Object> paraMap) {
        //系统消息数量
        int count1 = noticeMessageDao.selectNoticeMessageSysCount(paraMap);
        //公告数量
        //int count2 = noticeMessageDao.selectNoticeMessageCount(paraMap);
        String condition = (String) paraMap.get("condition");
//        if (null == condition) {
           // return count1 + count2;
            return count1;
//        } else if (condition.equals("4")) {
//            return count2;
//        } else if (condition.equals("1") || condition.equals("0")) {
//            return count1;
//        } else {
//            return count1 + count2;
//        }
    }

    //查询系统消息总量
    @Override
    public int selectSysMessageTotalCount(Map<String, Object> paraMap) {
        int count = noticeMessageDao.selectSysMessageTotalCount(paraMap);
        return count;
    }

    /**
     * 查询公告附件
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryCMSAttach(Map<String, Object> paraMap) {
        // TODO Auto-generated method stub
        return noticeMessageDao.queryCMSAttach(paraMap);
    }

    //系统消息标题弹框回显
    @Override
    public Map<String, Object> queryTitleMessage(Map<String, Object> paraMap) {
        // TODO Auto-generated method stub
        return noticeMessageDao.queryTitleMessage(paraMap);
    }

    /**
     * 查询统消息标题弹框回显的附件
     *
     * @param paraMap
     * @return
     */
    @Override
    public List<Map<String, Object>> queryTitleMessageAttach(Map<String, Object> paraMap) {
        return noticeMessageDao.queryTitleMessageAttach(paraMap);
    }

    //点击标题已读
    @Override
    public void updateTitleMessage(String id) {
        noticeMessageDao.updateTitleMessage(id);

    }


}
