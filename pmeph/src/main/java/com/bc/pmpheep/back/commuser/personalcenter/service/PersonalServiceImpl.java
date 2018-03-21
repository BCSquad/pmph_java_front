package com.bc.pmpheep.back.commuser.personalcenter.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bc.pmpheep.back.commuser.collection.dao.BookCollectionDao;
import org.apache.ibatis.javassist.expr.Instanceof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.dao.PersonalDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.util.RouteUtil;
import com.bc.pmpheep.general.pojo.Content;
import com.bc.pmpheep.general.service.ContentService;
import com.bc.pmpheep.general.service.FileService;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
public class PersonalServiceImpl implements PersonalService {

	@Autowired
	private PersonalDao personaldao;

	@Autowired
	private ContentService contentService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;

	@Autowired
	@Qualifier("com.bc.pmpheep.general.service.FileService")
	FileService fileService;



	@Override
	public List<PersonalNewMessage> queryMyCol(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的收藏列表

		List<PersonalNewMessage> list1 = personaldao.ListMyConlection(permap);
		return list1;
	}

	@Override
	public List<PersonalNewMessage> queryMyFriend(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的好友
		List<PersonalNewMessage> list2 = personaldao.ListMyFriend(permap);
		return list2;
	}

	@Override
	public List<Map<String, Object>> queryMyGroup(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询我的小组信息
		List<Map<String, Object>> list3 = personaldao.ListMyGroup(permap);
		for (Map<String, Object> map : list3) {
			String img = map.get("group_image").toString();
			if (img.equals("/static/default_image.png")) {
				map.put("group_image", "statics/image/default_image.png");
			}
		}
		return list3;
	}

	@Override
	public List<PersonalNewMessage> queryMyOfeerNew(Map<String, Object> permap) {
		// TODO 自动生成的方法存根 查询最新申请动态信息
		List<PersonalNewMessage> list4 = personaldao.ListMyOfeerTwo(permap);
		return list4;
	}

	@Override
	public List<Map<String, Object>> queryMyWritingsNew(PageParameter<Map<String, Object>> pageParameter) {
		// 查询我的随笔文章最新信息
		List<Map<String, Object>> list5 = personaldao.ListMyWritingsTwo(pageParameter);
		return list5;
	}

	@Override
	public int queryMyWritingsNewCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.queryMyWritingsNewCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyBooksNew(PageParameter<Map<String, Object>> pageParameter) {
		// TODO 自动生成的方法存根 查询我的书评最新信息
		List<Map<String, Object>> list6 = personaldao.ListMyBookNewsTwo(pageParameter);
		return list6;
	}

	@Override
	public List<Map<String, Object>> queryMyBooksJoin(PageParameter<Map<String, Object>> pageParameter) {
		// 查询我的教材申报最新信息
		List<Map<String, Object>> list7 = personaldao.ListAllBookJoin(pageParameter);

		for (Map<String, Object> m : list7) {
			List<Map<String, Object>> textbook_list = new ArrayList<Map<String, Object>>();
			String textbook_list_str = (String) m.get("textbook_list_str");
			String[] textbook_arr = textbook_list_str.split("_,_");
			if (!"".equals(textbook_list_str)) {
				for (int i = 0; i < textbook_arr.length; i++) {
					Map<String, Object> textbook = new HashMap<String, Object>();
					String tb = textbook_arr[i];
					textbook.put("id", tb.split("_/_")[0]);
					textbook.put("textbook_name", tb.split("_/_")[1]);
					textbook.put("is_locked", "1".equals(tb.split("_/_")[2]));
					textbook_list.add(textbook);
				}
			}

			m.put("textbook_list", textbook_list);

		}

		return list7;
	}

	@Override
	public int queryMyBooksJoinCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.queryMyBooksJoinCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryMyTopicChoose(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryMyTopicChoose(pageParameter);
		return result_list;
	}

	@Override
	public int queryMyTopicChooseCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.queryMyTopicChooseCount(pageParameter);
		return count;
	}

	@Override
	public List<Map<String, Object>> queryBookCorrectd(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryBookCorrectd(pageParameter);
		return result_list;
	}

	@Override
	public int queryBookCorrectdCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.queryBookCorrectdCount(pageParameter);
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
		if (author_reply != null && author_reply.length() > 0) {

			int length = author_reply.replaceAll("[^\\x00-\\xff]", "**").length();
			if (length <= ml) {
				int count = personaldao.authorReply(paraMap);
				resultMap.put("msg", "回复已提交成功！");
				resultMap.put("code", "OK");
			} else {
				resultMap.put("msg", "不可超过输入最大长度" + ml + "字节！");
				resultMap.put("code", "WARNING");
			}
		} else {
			resultMap.put("msg", "回复内容不可为空！");
			resultMap.put("code", "WARNING");
		}

		return resultMap;
	}

	@Override
	public List<Map<String, Object>> queryMyCorrection(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryMyCorrection(pageParameter);
		return result_list;
	}

	@Override
	public int queryMyCorrectionCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.queryMyCorrectionCount(pageParameter);
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> queryWriterUserTrendst(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.queryWriterUserTrendst(pageParameter);
		String contextpath = pageParameter.getParameter().get("contextpath").toString();
		for (Map<String, Object> map : result_list) {
			if (map.get("cms") != null && ((Map<?, ?>) map.get("cms")).get("mid") != null) {
				Content content = contentService.get(((Map<?, ?>) map.get("cms")).get("mid").toString());
				content = ifContentIsNullFun(map, content, "cms");
				Map<String, Object> cms = ((Map<String, Object>) map.get("cms"));
				String content_text = removeHtml(content.getContent());
				String cover = ((Map<?, ?>) map.get("cms")).get("cover").toString();
				String img_url = getFirstImgUrlFromHtmlStr(contextpath, cover);
				cms.put("first_img_url", img_url);
				cms.put("Content", content);
				cms.put("content_text", content_text);
				map.put("cms", cms);
			}
			if (map.get("p_cms") != null && ((Map<?, ?>) map.get("p_cms")).get("mid") != null) {
				Content content = contentService.get(((Map<?, ?>) map.get("p_cms")).get("mid").toString());
				Map<String, Object> p_cms = ((Map<String, Object>) map.get("p_cms"));
				content = ifContentIsNullFun(map, content, "p_cms");
				String content_text = removeHtml(content.getContent());
				String cover = ((Map<?, ?>) map.get("p_cms")).get("cover").toString();
				String img_url = getFirstImgUrlFromHtmlStr(contextpath, cover);
				p_cms.put("first_img_url", img_url);
				p_cms.put("Content", content);
				p_cms.put("content_text", content_text);
				map.put("p_cms", p_cms);
			}
		}
		return result_list;
	}

	/**
	 * 当content为空 返回摘要 若摘要也为空 返回没有内容
	 * 
	 * @param map
	 * @param content
	 * @param key
	 * @return
	 */
	private Content ifContentIsNullFun(Map<String, Object> map, Content content, String key) {
		if (content == null) {
			content = new Content();
			if (((Map<?, ?>) map.get(key)).get("summary") != null) {
				content.setContent(((Map<?, ?>) map.get(key)).get("summary").toString());
			} else {
				content.setContent("没有内容！");
			}
			content.setId(((Map<?, ?>) map.get(key)).get("mid").toString());
		}
		return content;
	}

	private String getFirstImgUrlFromHtmlStr(String contextpath, String cover) {
		// contextpath =
		// "/".equals(contextpath)||"\\".equals(contextpath)?"":contextpath;
		cover = RouteUtil.articleAvatar(cover);
		"/".equals(cover.substring(0, 1));

		/*
		 * String img_url = contextpath
		 * +"statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"; if(content!=null){
		 * List<String> imglist = articleSearchService.getImgSrc(content.getContent());
		 * 
		 * if(imglist.size()>0){
		 * 
		 * img_url = imglist.get(0); if (img_url.length()>0 &&
		 * "/image/".equals(img_url.substring(0,7)) &&
		 * ".action".equals(img_url.substring(img_url.lastIndexOf("."))) ) { contextpath
		 * = "/".equals(contextpath)||"\\".equals(contextpath)?"":contextpath; img_url =
		 * contextpath+img_url; } } }
		 */
		return contextpath + cover;
	}

	@Override
	public int queryWriterUserTrendstCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.queryWriterUserTrendstCount(pageParameter);
		return count;
	}

	/*
	 * 基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了
	 */
	public static int getWordCountRegex(String s) {

		s = s.replaceAll("[^\\x00-\\xff]", "**");
		int length = s.length();
		return length;
	}

	/**
	 * 我的评论
	 */
	@Override
	public List<Map<String, Object>> myComment(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.myComment(pageParameter);
		return result_list;
	}

	/**
	 * 我的评论总数
	 */

	@Override
	public int myCommentCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.myCommentCount(pageParameter);
		return count;
	}

	/**
	 * 我的评论修改
	 */
	@Override
	public Map<String, Object> updateComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Map<String, Object> rmap = new HashMap<String, Object>();
		String score_tem = map.get("score").toString();
		/* double score=Double.parseDouble(score_tem); */
		map.put("score", score_tem != null && score_tem != "" ? score_tem : 10);
		personaldao.updateComment(map);
		rmap.put("returncode", "OK");
		return rmap;
	}

	/**
	 * 我的评论删除
	 */
	@Override
	public void deleteComment(Map<String, Object> map) {
		personaldao.deleteComment(map);
	}

	@Override
	public void updateDownComments(Long id) {
		Long bookId = personaldao.getCommentById(id);
		personaldao.updateDownComments(bookId);
	}
	
	@Override
	public Long getCommentBycid(Long id) {
		Long comm = personaldao.getCommentBycid(id);
		return comm;
	}



	/**
	 * 我的问卷
	 */
	@Override
	public List<Map<String, Object>> mySurvey(PageParameter<Map<String, Object>> pageParameter) {
		List<Map<String, Object>> result_list = personaldao.mySurvey(pageParameter);
		return result_list;
	}

	/**
	 * 我的问卷总数
	 */

	@Override
	public int mySurveyCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.mySurveyCount(pageParameter);
		return count;
	}

	// 查询调查对应的题目
	@Override
	public List<Map<String, Object>> getSurvey(long surveyId) {
		List<Map<String, Object>> list = personaldao.getSurvey(surveyId);
		return list;
	}

	// 查询单选和多选对应的选项
	@Override
	public List<Map<String, Object>> getOptions(Long questionId) {
		List<Map<String, Object>> list = personaldao.getOptions(questionId);
		return list;
	}

	// 查询单选对应的选项答案
	@Override
	public String getAnswers(Map<String, Object> map) {
		String slist = personaldao.getAnswers(map);
		return slist;
	}

	// 查询多选对应的选项答案
	@Override
	public String getCheckAnswers(Map<String, Object> map) {
		String slist = personaldao.getCheckAnswers(map);
		return slist;
	}

	// 查询填空对应的选项答案
	@Override
	public String getInpAnswers(Map<String, Object> map) {
		String slist = personaldao.getInpAnswers(map);
		return slist;
	}

	// 回显答案保存按钮判断是否消失
	@Override
	public Map<String, Object> btnSaveOrHidden(Map<String, Object> map) {
		Map<String, Object> sMap = personaldao.btnSaveOrHidden(map);
		return sMap;
	}

	// 弹框回显短评内容
	@Override
	public Map<String, Object> shortComment(Map<String, Object> map) {
		Map<String, Object> sMap = personaldao.shortComment(map);
		return sMap;
	}

	@Override
	public void deleteUserTrendst(WriterUserTrendst writerUserTrendst) {
		// 删除同类型同id动态
		int dcount = personaldao.deleteUserTrendst(writerUserTrendst);
	}

	@Override
	public void saveUserTrendst(WriterUserTrendst writerUserTrendst) {
		// 删除同类型同id动态
		/* int dcount = personaldao.deleteUserTrendst(writerUserTrendst); */
		int count = personaldao.saveUserTrendst(writerUserTrendst);

		/*
		 * Map<String, Object> paraMap = new HashMap<String, Object>();
		 * paraMap.put("TrendstName", TrendstName); paraMap.put("tableId", tableId);
		 * paraMap.put("trendstType", trendstType); paraMap.put("writer_user_id",
		 * writer_user_id);
		 * 
		 * int count = personaldao.saveUserTrendst(paraMap); // 如果是提纠错
		 * 同时也应该为书籍第一主编生成一条动态 if ("wdjc".equals(TrendstName)) { String firstEditorId =
		 * personaldao .queryFirstEditorByBookCorrectionId(tableId);
		 * paraMap.put("TrendstName", "tsjc"); paraMap.put("writer_user_id",
		 * firstEditorId); int count_corrected = personaldao.saveUserTrendst(paraMap); }
		 */}

	@Override
	public Map<String, Object> deleteMyCorrection(String id, String logUserId) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		Map<String, Object> para_map = new HashMap<String, Object>();
		para_map.put("id", id);
		para_map.put("logUserId", logUserId);
		int count = personaldao.deleteMyCorrection(para_map);
		if (count > 0) {
			result_map.put("code", "OK");
			result_map.put("msg", "删除成功！");
		} else {
			result_map.put("code", "FAIL");
			result_map.put("msg", "删除失败！");
		}

		return result_map;
	}

	@Override
	public Map<String, Object> deleteMyBookComment(String id, String logUserId) {
		Map<String, Object> result_map = new HashMap<String, Object>();
		Map<String, Object> para_map = new HashMap<String, Object>();
		para_map.put("id", id);
		para_map.put("logUserId", logUserId);
		int count = personaldao.deleteMyBookComment(para_map);
		if (count > 0) {
			result_map.put("code", "OK");
			result_map.put("msg", "删除成功！");
		} else {
			result_map.put("code", "FAIL");
			result_map.put("msg", "删除失败！");
		}
		return result_map;
	}

	@Override
	public Map<String, Object> queryUserById(String userId) {
		Map<String, Object> result_map = personaldao.queryUserById(userId);
		return result_map;
	}

	@Override
	public Map<String, Object> queryOurFriendShip(String userId, String logUserId) {
		// friendShip初始化为未申请好友状态
		Map<String, Object> friendShip = new HashMap<String, Object>();
		friendShip.put("id", 0);
		friendShip.put("isBeenRequest", 0);
		friendShip.put("hasRequest", 0);
		friendShip.put("status", -1);
		// 查询数据库中两人好友状态

		List<Map<String, Object>> r = personaldao.queryOurFriendShip(userId, logUserId);
		friendShip = r != null && r.size() > 0 ? r.get(0) : friendShip;
		return friendShip;
	}

	// 去掉字符串中的html标签
	public String removeHtml(String str) {
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(str);
		str = m_html.replaceAll(""); // 过滤html标签
		return str;
	}

	@Override
	public Map<String, Object> queryDeclarationById(String declaration_id) {
		Map<String, Object> result_map = personaldao.queryDeclarationById(declaration_id);
		return result_map;
	}


	@Override
	public List<Map<String, Object>> whetherSurvey(PageParameter pageParameter) {
		List<Map<String, Object>> list=personaldao.whetherSurvey(pageParameter);
		List<Map<String,Object>> mylist=new ArrayList<>();
		if(list!=null){
			Map<String,Object> map=new HashMap<>();
			for (int i=0;i<list.size();i++){
				map=personaldao.joinSurvey(list.get(i).get("survey_id").toString());
				mylist.add(map);
			}
		}
		return mylist;
	}

	@Override
	public List<Map<String, Object>> listMyFavorites(PageParameter<Map<String, Object>> pageParameter,String contextpath) {
		// 查询我的收藏
		List<Map<String, Object>> list = personaldao.listMyFavorites(pageParameter);
		if(list.size()>0){
			for (Map<String, Object> map : list) {
				if("DEFAULT".equals(map.get("image_url"))){
					map.put("image_url", contextpath+"statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
				}
				if((map.get("f_type").toString()).equals("1")){
					int like=personaldao.queryLikesb(map.get("id").toString(),pageParameter.getParameter().get("logUserId").toString());
					map.put("like", like);
				}else if((map.get("f_type").toString()).equals("2")){
					int like=personaldao.queryLikesc(map.get("id").toString(),pageParameter.getParameter().get("logUserId").toString());
					map.put("like", like);
				}
				if("DEFAULT".equals(map.get("avatar"))||"".equals(map.get("avatar"))|| StringUtils.isEmpty(map.get("avatar"))){
					map.put("avatar", "statics/image/deficon.png");
				}else{
					map.put("avatar", "file/download/"+map.get("avatar")+".action");
				}
				if(map.get("category_id")!=null&& "2".equals(map.get("category_id").toString())){
					map.put("skip", "inforeport/toinforeport.action?id="+map.get("id"));
				}else if(map.get("category_id")!=null&& "1".equals(map.get("category_id").toString())){
					map.put("skip", "articledetail/toPage.action?wid="+map.get("id"));
				}
				if(null!=map.get("cover")){
					map.put("imgpath", RouteUtil.articleAvatar(map.get("cover").toString()));
				}
			}
		}
		return list;
	}

	@Override
	public int listMyFavoritesCount(PageParameter<Map<String, Object>> pageParameter) {
		Integer count = personaldao.listMyFavoritesCount(pageParameter);
		return count;
	}


}
