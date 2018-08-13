package com.bc.pmpheep.back.commuser.homepage.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService;
import com.bc.pmpheep.back.commuser.homepage.service.HomeService;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;


//首页controller
@Controller
@RequestMapping("/homepage")
public class HomeController extends BaseController{

    @Autowired
    @Qualifier("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
    private HomeService homeService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;
	@Autowired
	private MessageService messageService;
	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.articlepage.service.ArticleSearchService")
	private ArticleSearchService articleSearchService;

    @RequestMapping("/tohomepage")
    public ModelAndView move(HttpServletRequest request)throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        int flag = 0;
        Map<String, Object> user=getUserInfo();
        String logUserId= null;
        if (user!=null && user.get("id")!=null && !"".equals(user.get("id"))) {
        	logUserId = user.get("id").toString();
		}
        
        List<Map<String, Object>> listNot = homeService.queryNotice(request.getContextPath());
        List<Map<String, Object>> listArt = homeService.queryArticle(4);
        List<Map<String, Object>> listAut = homeService.queryAuthor(logUserId);
        List<Map<String, Object>> listCom = homeService.queryComment();
        int countSurvey=homeService.countSurvey(logUserId);

        Map<String,Object> adInfo1=homeService.getPageAdInfo("pc首页轮播");
        Map<String,Object> adInfo2=homeService.getPageAdInfo("pc首页中部1");
        Map<String,Object> adInfo3=homeService.getPageAdInfo("pc首页中部2");
        Map<String,Object> adInfo4=homeService.getPageAdInfo("pc首页中部3");
        Map<String,Object> adInfo5=homeService.getPageAdInfo("pc首页中部4");
        Map<String,Object> adInfo6=homeService.getPageAdInfo("pc首页原重点推荐1");
        Map<String,Object> adInfo7=homeService.getPageAdInfo("pc首页原重点推荐2");
        for (Map<String, Object> map : listCom) {
            String url=MapUtils.getString(map,"image_url","/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
		//	String url=map.get("image_url").toString();
			if(url.equals("DEFAULT")){
				map.put("image_url", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}
        //根据登录人查询可见公告，未登录查询所有人可见公告
        
        List<Map<String, Object>> listDou= homeService.queryDocument(user==null?"":user.get("id").toString());
        modelAndView.addObject("listDou", listDou);
        modelAndView.addObject("listNot", listNot);
        modelAndView.addObject("listArt", listArt);
        modelAndView.addObject("listAut", listAut);
        modelAndView.addObject("listCom", listCom);
        modelAndView.addObject("adInfo1", adInfo1);
        modelAndView.addObject("adInfo2", adInfo2);
        modelAndView.addObject("adInfo3", adInfo3);
        modelAndView.addObject("adInfo4", adInfo4);
        modelAndView.addObject("adInfo5", adInfo5);
        modelAndView.addObject("adInfo6", adInfo6);
        modelAndView.addObject("adInfo7", adInfo7);
        modelAndView.addObject("countSurvey", countSurvey);
        List<Map<String, Object>> listM =new ArrayList<Map<String,Object>>();
        if(user==null){
        	listM = homeService.queryMaterial("0");
        }else{
        	listM = homeService.queryMaterial(user.get("id").toString());
        }
        modelAndView.addObject("listM", listM);
        //读取mongeldb里面的图片 
        for (Map<String, Object> pmap : listArt) {
			Message message=messageService.get((String) pmap.get("mid"));
			if(message!=null){
				List<String> imglist = articleSearchService.getImgSrc(message.getContent());
			    if(imglist.size()>0){
			    	pmap.put("imgpath", imglist.get(0));
			    }else{//没有图片放置默认图片
			    	pmap.put("imgpath",request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			    }
			}else{//没有图片放置默认图片
				pmap.put("imgpath", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
			}
		}

        List<Map<String, Object>> types = homeService.queryBookType(0);
        modelAndView.addObject("bookTypes", types);
        if (types.size() > 0) {
            List<Map<String, Object>> listSal = homeService.querySale(MapUtils.getIntValue(types.get(0), "id"));
            List<Map<String, Object>> listType = homeService.queryBookType(MapUtils.getIntValue(types.get(0), "id"));
            List<Map<String, Object>> listLabel = homeService.queryLabel(MapUtils.getIntValue(types.get(0), "id"));
            modelAndView.addObject("listSal", listSal);
            modelAndView.addObject("listType", listType);
            modelAndView.addObject("listLabel", listLabel);
        }

        Map<String, Object> rowsmap = new HashMap<String, Object>();
        rowsmap.put("startrows", -1);
//      rowsmap.put("type", MapUtils.getIntValue(types.get(0), "id"));
        rowsmap.put("type",1);
    //    List<Map<String, Object>> listrows = homeService.queryBook(rowsmap);
        int listrows = homeService.countBookByType("1");
        //模板(首页默认显示学校教育下的书籍,从第一条开始显示，每页10条数据)
        Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("startrows", 0);
        if(types.size()>0) {
            pmap.put("type", MapUtils.getIntValue(types.get(0), "id"));
        }
        List<Map<String, Object>> listBok = homeService.queryBook(pmap);
        String html = "";
        String vm = "commuser/homepage/homepage.vm";

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("books", new ArrayList());
        for (int i = 0; i < listBok.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", listBok.get(i).get("id"));
            map.put("author", listBok.get(i).get("author"));
            map.put("bookname", listBok.get(i).get("bookname"));
            map.put("score", listBok.get(i).get("score"));
            if (listBok.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
            } else {
                map.put("img", listBok.get(i).get("image_url"));
            }
            ((List) map2.get("books")).add(map);

        }
        html += templateService.mergeTemplateIntoString(vm, map2);

        if (listrows % 14 == 0) {
            flag = listrows / 14;
        } else {
            flag = listrows / 14 + 1;
        }
        modelAndView.addObject("allrows", flag);
        modelAndView.addObject("thisrows", "1");
        modelAndView.addObject("homepagebook", html);
        modelAndView.setViewName("commuser/homepage/homepage");
        return modelAndView;
    }

    /**
     * 查询下一页数据
     *
     * @param request
     * @return map
     */
    @RequestMapping("/changerows")
    @ResponseBody
    public Map<String, Object> changerows(HttpServletRequest request) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        String state = request.getParameter("state");
        String type = request.getParameter("type");
        int startrows = Integer.parseInt(request.getParameter("startrows"));
        String html = "";
        String vm = "commuser/homepage/homepage.vm";
        Map<String, Object> typeMap = new HashMap<String, Object>();
        typeMap.put("type", type);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("books", new ArrayList());
        if (state.equals("next")) {
            typeMap.put("startrows", startrows * 14 );
            List<Map<String, Object>> listRow = homeService.queryBook(typeMap);
            for (int i = 0; i < listRow.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", listRow.get(i).get("id"));
                map.put("author", listRow.get(i).get("author"));
                map.put("bookname", listRow.get(i).get("bookname"));
                map.put("score", listRow.get(i).get("score"));
                if (listRow.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                    map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
                } else {
                    map.put("img", listRow.get(i).get("image_url"));
                }
                ((List) map2.get("books")).add(map);
            }
        } else {
            typeMap.put("startrows", (startrows - 2) * 14);
            List<Map<String, Object>> listRow = homeService.queryBook(typeMap);
            for (int i = 0; i < listRow.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", listRow.get(i).get("id"));
                map.put("author", listRow.get(i).get("author"));
                map.put("bookname", listRow.get(i).get("bookname"));
                map.put("score", listRow.get(i).get("score"));
                if (listRow.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                    map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
                } else {
                    map.put("img", listRow.get(i).get("image_url"));
                }
                ((List) map2.get("books")).add(map);

            }
        }
        html += templateService.mergeTemplateIntoString(vm, map2);
        pMap.put("homepagebook", html);
        pMap.put("thisrows", state.endsWith("next") ? startrows + 1 : startrows - 1);
        return pMap;
    }

    /**
     * 查询不同类型的书籍
     *
     * @param request
     * @return map
     */
    @RequestMapping("/chooseType")
    @ResponseBody
    public Map<String, Object> chooseType(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String state = request.getParameter("state");
        Map<String, Object> rowsmap = new HashMap<String, Object>();
        rowsmap.put("startrows", 0);
        rowsmap.put("type", Integer.parseInt(state));
        List<Map<String, Object>> listrows = homeService.queryBook(rowsmap);
        String html = "";
        String vm = "commuser/homepage/homepage.vm";
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("books", new ArrayList());
        for (int i = 0; i < listrows.size(); i++) {
            Map<String, Object> pmap = new HashMap<String, Object>();
            pmap.put("id", listrows.get(i).get("id"));
            pmap.put("author", listrows.get(i).get("author"));
            pmap.put("bookname", listrows.get(i).get("bookname"));
            pmap.put("score", listrows.get(i).get("score"));
            if (listrows.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                pmap.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
            } else {
                pmap.put("img", listrows.get(i).get("image_url"));
            }
            ((List) map2.get("books")).add(pmap);

        }
        html += templateService.mergeTemplateIntoString(vm, map2);
        map.put("homepagebook", html);

        List<Map<String, Object>> listType = homeService.queryBookType(Integer.parseInt(state));
        List<Map<String, Object>> listLabel = homeService.queryLabel(Integer.parseInt(state));
        map.put("listType", listType);
        map.put("listLabel", listLabel);
        int sizerow = homeService.querySize(state);
        int flag = 0;
        if (sizerow % 14 == 0) {
            flag = sizerow / 14;
        } else {
            flag = sizerow / 14 + 1;
        }
        map.put("allrows", flag);
        return map;
    }

    /**
     * 根据书籍类型查询图书畅销榜
     *
     * @param request
     * @return map;
     */
    @RequestMapping("changesale")
    @ResponseBody
    public Map<String, Object> changesale(HttpServletRequest request) throws UnsupportedEncodingException{
        Map<String, Object> map = new HashMap<String, Object>();
        String type = request.getParameter("type");
        List<Map<String, Object>> listSal = homeService.querySale(Integer.parseInt(type));
        map.put("type", listSal);
        return map;
    }
    
    /**
     * 添加好友
     * @param request
     * @return
     */
    @RequestMapping("addfriend")
    @ResponseBody
    public String addfriend(HttpServletRequest request){
    	String target_id=request.getParameter("target_id");
    	Map<String, Object> user=getUserInfo();
    	String returncode=homeService.addfriend(user.get("id").toString(), target_id);
    	return returncode;
    }

    /**
     * 查询更多热门书评列表
     */
    @RequestMapping("/morecomments")
    public ModelAndView getMoreComments(HttpServletRequest req) throws UnsupportedEncodingException{
        Map<String,Object> map=new HashMap<String, Object>();
        String pagenum=req.getParameter("pagenum");
        String pagesize=req.getParameter("size");
        int startnum=0;
        int size=5;
        if(pagenum!=null && !"".equals(pagenum) && pagesize!=null && !"".equals(pagesize)){
            startnum=(Integer.parseInt(pagenum)-1)*Integer.parseInt(pagesize);
            size=Integer.parseInt(pagesize);
        }else{
            pagenum="1";
            pagesize="5";
        }
        List<Map<String, Object>> comments = homeService.queryHotCommentList(startnum,size,req.getContextPath());
        int total=homeService.queryHotCommentListCount();
        int pagetotal=total/size;
        if(total%size!=0){
            pagetotal=pagetotal+1;
        }
        map.put("total", total);
        map.put("pagetotal", pagetotal);
        map.put("comments",comments);
        map.put("pagenum", pagenum);
        map.put("pagesize", pagesize);
        return new ModelAndView("commuser/homepage/hotbookcomments",map);
    }

    //跳转到三个产品详情界面
    @RequestMapping("todeclaredetail")
    public ModelAndView todeclaredetail(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        String state=request.getParameter("state");
        List<Map<String,Object>> list=homeService.quertProductByType(state);
        //取出申报通知扫描图片
        List<Map<String,Object>> list_scanimg=new ArrayList<>();
        List<Map<String,Object>> list_unscanimg=new ArrayList<>();
        for (int i=0;i<list.size();i++){
            Boolean s1=Boolean.valueOf(list.get(i).get("is_scan_img").toString());
            if(s1==true){
                Map<String,Object> map=new HashMap();
//                map.put("attachment","image/" + list.get(i).get("attachment") + ".action");
                map.put("attachment","image/5b581f99e4b01f9d7abc3d61.action");
                list_scanimg.add(map);
            }else{
                Map<String,Object> map=new HashMap();
                map.put("attachment","file/download/" + list.get(i).get("attachment") + ".action");
                map.put("attachment_name",list.get(i).get("attachment_name").toString());
                list_unscanimg.add(map);
            }
        }
        modelAndView.addObject("list_scanimg",list_scanimg);
        modelAndView.addObject("list_unscanimg",list_unscanimg);
        modelAndView.addObject("note_detail",list.get(0).get("note_detail"));
        modelAndView.addObject("description",list.get(0).get("description_detail"));
        modelAndView.addObject("state",state);
        modelAndView.setViewName("commuser/cms/declaredatail");
        return modelAndView;
    }
}
