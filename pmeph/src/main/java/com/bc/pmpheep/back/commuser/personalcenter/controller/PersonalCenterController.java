package com.bc.pmpheep.back.commuser.personalcenter.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.personalcenter.bean.PersonalNewMessage;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.back.util.MD5;
import com.bc.pmpheep.general.controller.BaseController;

//首页controller
@Controller
@RequestMapping("/personalhomepage")
public class PersonalCenterController extends BaseController {

	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;
    

    @RequestMapping("/tohomepage")//个人中心动态
    public ModelAndView move(@RequestParam(value="pagetag",defaultValue="dt")String pagetag
    						,HttpServletRequest request
    						,@RequestParam(value="pageNum",defaultValue="1")Integer pageNum
    						,@RequestParam(value="pageSize",defaultValue="10")Integer pageSize
    						) throws UnsupportedEncodingException {

    	ModelAndView mv = new ModelAndView();
        Map<String, Object> permap = this.getUserInfo();//个人信息
        mv.addObject("permap", permap);
        //查询个人主页共用部分 收藏 好友 小组
        queryPersonalRightPageInfo(mv, permap);

        Map<String, Object> paraMap = new HashMap<String, Object>();
        String contextpath = request.getContextPath()+"/";
		paraMap.put("logUserId", getUserInfo().get("id"));
		//数据总数初始化
		int count = 0;
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<Map<String,Object>>(pageNum,pageSize);
		Map<String, Object> vm_map = new HashMap<String, Object>();
        
    	//页签分支
    	if ("dt".equals(pagetag)) { //动态
    		//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={};
			String[] namesChi = {};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.queryWriterUserTrendst(pageParameter);
			count = personalService.queryWriterUserTrendstCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/writerUserTrendst.vm",contextpath, pageParameter, List_map,vm_map);
			mv.addObject("List_map",List_map);//测试
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if("tsjc".equals(pagetag)){ //图书纠错 (我是第一主编)
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={"is_replied"};
			String[] namesChi = {};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.queryBookCorrectd(pageParameter);
			count = personalService.queryBookCorrectdCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/bookCorrected.vm",contextpath, pageParameter, List_map,vm_map);
			/*mv.addObject("List_map",List_map);*///测试
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if("wdjc".equals(pagetag)){  //我的纠错(我是纠错人)
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={"is_replied"};
			String[] namesChi = {};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.queryMyCorrection(pageParameter);
			count = personalService.queryMyCorrectionCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myCorrection.vm",contextpath, pageParameter, List_map,vm_map);
			/*mv.addObject("List_map",List_map);*///测试
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if("sbwz".equals(pagetag)){ //随笔文章
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={"auth_status","is_staging"};
			String[] namesChi = {};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.queryMyWritingsNew(pageParameter);
			count = personalService.queryMyWritingsNewCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myWritingsList.vm",contextpath, pageParameter, List_map,vm_map);
			/*mv.addObject("List_map",List_map);*///测试
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
			
			
		}else if("jcsb".equals(pagetag)){ //教材申报
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={"s","pageinfo","dateinfo","online_progress","is_staging","pageinfo1"};
			String[] namesChi = {"bookname"};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.queryMyBooksJoin(pageParameter);//教材申报最新消息
			count = personalService.queryMyBooksJoinCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/materialDeclarationList.vm",contextpath, pageParameter, List_map,vm_map);
			/*mv.addObject("List_map",List_map);*///测试
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
			
			
		}else if("wycs".equals(pagetag)){  //我要出书
			
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView，放入模版空间
			//设定条件名数组 
			String[] names={"auth_progress","is_staging","isMine","pageinfo1"};
			String[] namesChi = {"bookname"};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			
			pageParameter.setParameter(paraMap);
			List<Map<String,Object>> List_map =personalService.queryMyTopicChoose(pageParameter);
			count = personalService.queryMyTopicChooseCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myTopicChoose.vm",contextpath, pageParameter, List_map,vm_map);
			/*mv.addObject("List_map",List_map);*///测试
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if("wdpl".equals(pagetag)){ //我的评论
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={"is_replied"};
			String[] namesChi = {};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.myComment(pageParameter);
			count = personalService.myCommentCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/myComment.vm",contextpath, pageParameter, List_map,vm_map);
			
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else if("wdwj".equals(pagetag)){  //我的问卷
			//从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView,放入模版空间
			//设定条件名数组 
			String[] names={};
			String[] namesChi = {};
			queryConditionOperation(names,namesChi,request, mv, paraMap,vm_map);
			pageParameter.setParameter(paraMap);

			List<Map<String,Object>> List_map = personalService.mySurvey(pageParameter);
			count = personalService.myCommentCount(pageParameter);
			//分页数据代码块
			String html = this.mergeToHtml("commuser/personalcenter/mySurvey.vm",contextpath, pageParameter, List_map,vm_map);
			
			mv.addObject("html",html);
			mv.setViewName("commuser/personalcenter/PersonalHomeWYCS");
		}else{
			
		}
    	
    	//总页数
    	Integer maxPageNum = (int) Math.ceil(1.0*count/pageSize);
    	mv.addObject("listCount",count);
    	mv.addObject("maxPageNum",maxPageNum);
    	mv.addObject("pagetag",pagetag);
    	mv.addObject("pageNum",pageNum);
    	mv.addObject("pageSize",pageSize);
        
        return mv;
    }

    @RequestMapping(value="authorReply",method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> authorReply(HttpServletRequest request){
    	String id = request.getParameter("id");
    	String author_reply = request.getParameter("author_reply");
    	
    	Map<String,Object> result_map = personalService.authorReply(id,author_reply);

    	return result_map;
    }

    /**
     * 分页数据执行模版转化为html
     * @param vm 模版地址
     * @param contextpath
     * @param pageParameter
     * @param List_map
     * @return
     */
	private String mergeToHtml(String vm,String contextpath,
			PageParameter<Map<String, Object>> pageParameter,
			List<Map<String, Object>> List_map,Map<String, Object> vm_map) {
		
		vm_map.put("List_map", List_map);
		vm_map.put("startNum", pageParameter.getStart()+1);
		vm_map.put("para", pageParameter.getParameter());
		vm_map.put("contextpath", contextpath);
		String html ="";
		html = templateService.mergeTemplateIntoString(vm, vm_map);
		return html;
	}


    /**
     * 从request中取出查询条件，封装到pageParameter用于查询，传回到modelAndView，放入模版空间
     * @param names 数字字母查询条件
     * @param namesChi js中编码过的中文查询条件
     * @param request
     * @param modelAndView
     * @param paraMap
     * @throws UnsupportedEncodingException
     */
	private void queryConditionOperation(String[] names,String[] namesChi,HttpServletRequest request,ModelAndView modelAndView, Map<String, Object> paraMap,Map<String, Object> vm_map) throws UnsupportedEncodingException {
		for (String queryName : names) {
			//查询条件
			String queryValue = request.getParameter(queryName);
			//封装查询条件入pageParameter 用以查询
			paraMap.put(queryName, queryValue);
			//传回查询条件
			modelAndView.addObject(queryName, queryValue);
			//放入模版空间
			vm_map.put(queryName, queryValue);
		}
		for (String queryName : namesChi) {
			//查询条件
			String queryValue = request.getParameter(queryName);
			queryName = java.net.URLDecoder.decode((queryName!=null?queryName:""),"UTF-8"); 
			//封装查询条件入pageParameter 用以查询
			paraMap.put(queryName, queryValue);
			//传回查询条件
			modelAndView.addObject(queryName, queryValue);
			//放入模版空间
			vm_map.put(queryName, queryValue);
		}
	}


    /*@RequestMapping("/tohomepageout")//个人中心展示页面
    public ModelAndView moveout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String uid = request.getParameter("uid");
        Map<String, Object> permap = this.getUserInfo();
        ;//个人信息
        permap.put("id", uid);
        List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);//我的收藏
        List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);//我的好友
        List<PersonalNewMessage> listmywritingsnew = personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
        List<PersonalNewMessage> listmybooknews = personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
        modelAndView.addObject("permap", permap);
        modelAndView.addObject("listmycol", listmycol);
        modelAndView.addObject("listmyfriend", listmyfriend);
        modelAndView.addObject("listmywritingsnew", listmywritingsnew);
        modelAndView.addObject("listmybooknews", listmybooknews);
        modelAndView.setViewName("commuser/personalcenter/PersonalHomeOut");
        return modelAndView;
    }*/

    /*@RequestMapping("/tohomepageone")//个人中心教材申报列表
    public ModelAndView moveoutone(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String bookname = null;
        if (request.getParameter("bookname") != null) {
            try {
                bookname = new String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        String s = request.getParameter("s");
        String pageinfo = request.getParameter("pageinfo");
        String dateinfo = request.getParameter("dateinfo");
        String online_progress = request.getParameter("online_progress");
        String is_staging = request.getParameter("is_staging");
        Map<String, Object> permap = this.getUserInfo();//个人信息
        List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);//我的收藏
        List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);//我的好友
        permap.put("s", s);
        permap.put("pageinfo", pageinfo);
        permap.put("dateinfo", dateinfo);
        permap.put("online_progress", online_progress);
        permap.put("is_staging", is_staging);
        permap.put("bookname", bookname);
        List<PersonalNewMessage> listbookjoins = personalService.queryMyBooksJoin(permap);//教材申报最新消息
        modelAndView.addObject("permap", permap);
        modelAndView.addObject("listmycol", listmycol);
        modelAndView.addObject("listmyfriend", listmyfriend);
        modelAndView.addObject("listbookjoins", listbookjoins);
        modelAndView.setViewName("commuser/personalcenter/PersonalHomeOne");
        modelAndView.addObject("serchbox", bookname);
        return modelAndView;
    }*/

    /*@RequestMapping("/tohomepageonelist")//我的申请个人中心教材申报列表
    public ModelAndView moveoutone1(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String bookname = null;
        if (request.getParameter("bookname") != null) {
            try {
                bookname = new String(request.getParameter("bookname").getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
        String s = "1";
        String pageinfo = request.getParameter("pageinfo");
        String dateinfo = request.getParameter("dateinfo");
        String online_progress = request.getParameter("online_progress");
        String is_staging = request.getParameter("is_staging");
        Map<String, Object> permap = this.getUserInfo();//个人信息
        List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);//我的收藏
        List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);//我的好友
        permap.put("s", s);
        permap.put("pageinfo", pageinfo);
        permap.put("dateinfo", dateinfo);
        permap.put("online_progress", online_progress);
        permap.put("is_staging", is_staging);
        permap.put("bookname", bookname);
        List<PersonalNewMessage> listbookjoins = personalService.queryMyBooksJoin(permap);//教材申报最新消息
        modelAndView.addObject("permap", permap);
        modelAndView.addObject("listmycol", listmycol);
        modelAndView.addObject("listmyfriend", listmyfriend);
        modelAndView.addObject("listbookjoins", listbookjoins);
        modelAndView.setViewName("commuser/personalcenter/PersonalHomeOneMy");
        modelAndView.addObject("serchbox", bookname);
        return modelAndView;
    }*/


    /*@RequestMapping("/tohomepagetwo")//个人中心随笔文章列表
    public ModelAndView moveouttwo() {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> permap = this.getUserInfo();//个人信息
        List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);//我的收藏
        List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);//我的好友
        List<PersonalNewMessage> listmywritingsnew = personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
        modelAndView.addObject("permap", permap);
        modelAndView.addObject("listmycol", listmycol);
        modelAndView.addObject("listmyfriend", listmyfriend);
        modelAndView.addObject("listmywritingsnew", listmywritingsnew);
        modelAndView.setViewName("commuser/personalcenter/PersonalHomeTwo");
        return modelAndView;
    }*/

    /*@RequestMapping("/tohomepagethe")//个人中心我的书评列表
    public ModelAndView moveoutthe() {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, Object> permap = this.getUserInfo();//个人信息
        List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);//我的收藏
        List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);//我的好友
        List<PersonalNewMessage> listmybooknews = personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
        modelAndView.addObject("permap", permap);
        modelAndView.addObject("listmycol", listmycol);
        modelAndView.addObject("listmyfriend", listmyfriend);
        modelAndView.addObject("listmybooknews", listmybooknews);
        modelAndView.setViewName("commuser/personalcenter/PersonalHomeThere");
        return modelAndView;
    }*/
    
    /**
     * 我要出书
     *//*
    @RequestMapping("/toBookList")
    public ModelAndView toBookList(HttpServletRequest request,
    		HttpServletResponse response){
    	ModelAndView mav = new ModelAndView("commuser/personalcenter/toBookList");
    	
    	return mav;
    }*/
    
    
    //内部方法***************************************************************************
    
    /**
     * 查询动态
     * @param modelAndView
     * @param permap
     */
	private void queryPersonalNewMessage(ModelAndView modelAndView,
			Map<String, Object> permap) {/*
		List<PersonalNewMessage> listmyofeernew = personalService.queryMyOfeerNew(permap);//我的申请动态最新消息
		List<PersonalNewMessage> listmywritingsnew = personalService.queryMyWritingsNew(permap);//我的随笔文章动态最新消息
		List<PersonalNewMessage> listmybooknews = personalService.queryMyBooksNew(permap);//我的书评消息动态最新消息
		//List<PersonalNewMessage> listbookjoins = personalService.queryMyBooksJoin(permap);//教材申报最新消息

		List<PersonalNewMessage> newMessages = new ArrayList<PersonalNewMessage>();
		for (PersonalNewMessage m : listmyofeernew) {
		    newMessages.add(m);
		}

		for (PersonalNewMessage m : listmywritingsnew) {
		    newMessages.add(m);
		}

		for (PersonalNewMessage m : listmybooknews) {
		    newMessages.add(m);
		}

		Collections.sort(newMessages, new Comparator<PersonalNewMessage>() {
		    @Override
		    public int compare(PersonalNewMessage o1, PersonalNewMessage o2) {
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		        try {
		            return format.parse(o1.getGmt_update()).compareTo(format.parse(o2.getGmt_update()));
		        } catch (ParseException e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
		        }
		    }
		});
		modelAndView.addObject("newMessages", newMessages);
	*/}
	
	/**
     * 查询个人主页共用部分 收藏 好友 小组
     * @param modelAndView
     * @param permap
     */
	private void queryPersonalRightPageInfo(ModelAndView modelAndView,
			Map<String, Object> permap) {
		List<PersonalNewMessage> listmycol = personalService.queryMyCol(permap);//我的收藏
        List<PersonalNewMessage> listmyfriend = personalService.queryMyFriend(permap);//我的好友
        List<PersonalNewMessage> listmygroup = personalService.queryMyGroup(permap);//我的小组
        modelAndView.addObject("listmycol", listmycol);
        modelAndView.addObject("listmyfriend", listmyfriend);
        modelAndView.addObject("listmygroup", listmygroup);
        
        /*modelAndView.addObject("listmyofeernew", listmyofeernew);
        modelAndView.addObject("listmywritingsnew", listmywritingsnew);
        modelAndView.addObject("listmybooknews", listmybooknews);
        modelAndView.addObject("listbookjoins", listbookjoins);*/
	}

	
}
