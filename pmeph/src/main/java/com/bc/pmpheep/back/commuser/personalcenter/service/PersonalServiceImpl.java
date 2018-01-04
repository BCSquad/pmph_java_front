package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.dao.PersonalDao;
import com.bc.pmpheep.back.plugin.PageParameter;

@Service("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao personaldao;
	
	@Override
	public List<PersonalNewMessage> queryMyCol(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的收藏列表
		
		List<PersonalNewMessage> list1=personaldao.ListMyConlection(permap);
		return list1;
	}

	@Override
	public List<PersonalNewMessage> queryMyFriend(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的好友
		List<PersonalNewMessage> list2=personaldao.ListMyFriend(permap);
		return list2;
	}

	@Override
	public List<PersonalNewMessage> queryMyGroup(Map<String, Object> permap) {
		// TODO 自动生成的方法存根  查询我的小组信息
		List<PersonalNewMessage> list3=personaldao.ListMyGroup(permap);
		return list3;
	}

	@Override
	public List<PersonalNewMessage> queryMyOfeerNew(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询最新申请动态信息
		List<PersonalNewMessage> list4=personaldao.ListMyOfeerTwo(permap);
		return list4;
	}

	@Override
	public List<Map<String,Object>> queryMyWritingsNew(PageParameter<Map<String, Object>> pageParameter) {
		// 查询我的随笔文章最新信息
		List<Map<String,Object>> list5=personaldao.ListMyWritingsTwo(pageParameter);
		return list5;
	}
	
	@Override
	public int queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyWritingsNewCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String,Object>> queryMyBooksNew(PageParameter<Map<String, Object>> pageParameter) {
		// TODO 自动生成的方法存根 查询我的书评最新信息
		List<Map<String,Object>> list6=personaldao.ListMyBookNewsTwo(pageParameter);
		return list6;
	}

	@Override
	public List<Map<String, Object>>  queryMyBooksJoin(PageParameter<Map<String, Object>> pageParameter) {
		// 查询我的教材申报最新信息
		List<Map<String, Object>> list7=personaldao.ListAllBookJoin(pageParameter);
		return list7;
	}
	@Override
	public int queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyBooksJoinCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryMyTopicChoose(pageParameter);
		return result_list;
	}

	@Override
	public int queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyTopicChooseCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryBookCorrectd(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryBookCorrectd(pageParameter);
		return result_list;
	}

	@Override
	public int queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryBookCorrectdCount(pageParameter);
		return count;
	}

	@Override
	public Map<String, Object> authorReply(String id, String author_reply) {
		Map<String, String> paraMap = new HashMap<String, String>();
		author_reply = author_reply.trim();
		int ml = 500;
		
		paraMap.put("id", id);
		paraMap.put("author_reply", author_reply);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (author_reply!=null && author_reply.length()>0 ) {
			
			int length = author_reply.replaceAll("[^\\x00-\\xff]", "**").length();  
			if (length <= ml) {
				int count = personaldao.authorReply(paraMap);
				resultMap.put("msg", "回复已提交成功！");
				resultMap.put("code", "OK");
			}else{
				resultMap.put("msg", "不可超过输入最大长度"+ml+"字节！");
				resultMap.put("code", "WARNING");
			}
		}else{
			resultMap.put("msg", "回复内容不可为空！");
			resultMap.put("code", "WARNING");
		}
		
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> queryMyCorrection(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list=  personaldao.queryMyCorrection(pageParameter);
		return result_list;
	}

	@Override
	public int queryMyCorrectionCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryMyCorrectionCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryWriterUserTrendst(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list=  personaldao.queryWriterUserTrendst(pageParameter);
		return result_list;
	}

	@Override
	public int queryWriterUserTrendstCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count =personaldao.queryWriterUserTrendstCount(pageParameter);
		return count;
	}  
	
	/*基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了*/  
    public static  int getWordCountRegex(String s)  
    {  
  
        s = s.replaceAll("[^\\x00-\\xff]", "**");  
        int length = s.length();  
        return length;  
    }

}
