/**
 * create by xcy on 2017-12-7
 */
package com.bc.pmpheep.back.authadmin.message.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bc.pmpheep.back.commuser.mymessage.bean.DialogueVO;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.dao.AllMessageDao;

/**
 * @author xcy
 * @ClassName: AllMessageServiceImpl
 * @Description: TODO
 * @date 2017-12-7 上午11:33:05
 */
@Service("com.bc.pmpheep.back.authadmin.message.service.AllMessageServiceImpl")
public class AllMessageServiceImpl implements AllMessageService {
   @Autowired
    MessageService messageService;


    /**
     * 根据系统消息内容id更改系统消息是否已读
     * @param mid
     * @param userid 
     */
	@Override
	public int updateIsRead(String mid, String userid) {
		// TODO Auto-generated method stub
		return allMessageDao.updateIsRead(mid,userid);
	}
	/**
	 * 删除消息
	 * @param parameter
	 * @param string
	 */
	@Override
	public int deletemsg(String mid, String userid) {
		// TODO Auto-generated method stub
		return allMessageDao.deletemsg(mid,userid);
	}


    /**
     * @return
     * @throws
     * @Title: getAllMessageInit
     * @Description: 获取机构用户全部消息——初始化
     */
    @Override
    public List<Map<String, Object>> getAllMessageInit(Map<String, Object> params) {


        List<Map<String, Object>> messages = allMessageDao.getAllMessageInit(params);
        List<String> ids = new ArrayList<String>();
        Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        for (Map<String, Object> message : messages) {
            ids.add(MapUtils.getString(message, "msg_id"));
            map.put(MapUtils.getString(message, "msg_id"), message);
            message.put("avatar", RouteUtil.userAvatar(MapUtils.getString(message, "avatar")));
        }

        for (Message message : messageService.list(ids)) {
            String str = message.getContent();
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(str);
            str = m_html.replaceAll(""); //过滤html标签
            map.get(message.getId()).put("msg_content",str );
        }

        return messages;
    }
}
