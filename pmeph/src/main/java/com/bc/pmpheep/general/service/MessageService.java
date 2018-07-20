package com.bc.pmpheep.general.service;

import com.bc.pmpheep.general.dao.MessageDao;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.service.exception.CheckedExceptionBusiness;
import com.bc.pmpheep.service.exception.CheckedExceptionResult;
import com.bc.pmpheep.service.exception.CheckedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: SuiXinYang
 * @Description: MongoDB 消息服务
 * @Date: Created in 10:45 2017/11/15
 * @Modified: SuiXinYang
 **/
@Service("com.bc.pmpheep.general.service.MessageService")
public class MessageService {
    @Autowired
    MessageDao messageDao;
    /**
     * 新增Message对象
     *
     * @param message 消息对象
     * @return 返回插入对象(包括MongoDB生成的id)
     */
    public Message add(Message message) {
        if (null == message) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "消息更新对象为空");
        }
        if (null == message.getContent() || message.getContent().isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "消息更新对象内容为空");
        }
        return messageDao.save(message);
    }
    /**
     * 根据id查找Message对象
     *
     * @param id Message主键
     * @return 查找结果，未找到时返回null
     */
    public Message get(String id) {
        if (null == id || id.isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "消息获取时ID为空");
        }
        Message message = messageDao.findOne(id);
        if(message==null){
        	message = new Message("", "");
        }else if(message.getContent()==null){
        	message.setContent("");
        }else{
        	this.replaceIp(message);
        }
        
        
        return message;
    }
    /**
     * 批量查找Message对象
     *
     * @param ids 主键集合
     * @return 返回Message对象集合
     */
    public List<Message> list(List<String> ids) {
    	List<Message> list = (List<Message>) messageDao.findAll(ids);
    	for (Message message : list) {
			this.replaceIp(message);
		}
        return list;
    }
  //去掉如下正则匹配的192.168.100.135/
  	private void replaceIp(Message message) {
  		Pattern pa = Pattern.compile("(?<=<img .{0,2000}?src=\")(http://192.168.100.135)(?=/.*?\".*?/>)");
  		Matcher ma = pa.matcher(message.getContent());
  		String re = ma.replaceAll("");
  		message.setContent(re);
  	}
    /**
     * 更新Message对象
     *
     * @param message 消息对象
     */
    public void update(Message message) {
        if (null == message) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "消息更新对象为空");
        }
        if (null == message.getId() || message.getId().isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "消息更新对象id为空");
        }
        if (null == message.getContent() || message.getContent().isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "消息更新对象内容为空");
        }
        Message msg = get(message.getId());
        if (null == msg) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.OBJECT_NOT_FOUND, "未找到更新对象");
        }
        msg.setContent(message.getContent());
        messageDao.save(msg);
    }
    /**
     * 删除给定id的Message对象
     *
     * @param id Message主键
     */
    public void delete(String id) {
        if (null == id || id.isEmpty()) {
            throw new CheckedServiceException(CheckedExceptionBusiness.MESSAGE,
                    CheckedExceptionResult.NULL_PARAM, "删除消息时ID为空");
        }
        messageDao.delete(id);
    }
    /**
     * 本方法在业务中不提供，仅用于测试
     */
    @Deprecated
    public void removeAll() {
        messageDao.deleteAll();
    }
}
