package com.bc.pmpheep.back.commuser.homepage.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
    public ModelAndView move(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        int flag = 0;
        List<Map<String, Object>> listNot = homeService.queryNotice();
        List<Map<String, Object>> listArt = homeService.queryArticle(4);
        List<Map<String, Object>> listAut = homeService.queryAuthor();
        List<Map<String, Object>> listCom = homeService.queryComment();

        Map<String,Object> adInfo1=homeService.getPageAdInfo("首页轮播");
        Map<String,Object> adInfo2=homeService.getPageAdInfo("首页中部");

        //根据登录人查询可见公告，未登录查询所有人可见公告
        List<Map<String, Object>> listDou=new ArrayList<Map<String,Object>>();
        Map<String, Object> user=getUserInfo();
        if(user!=null){
        	 listDou = homeService.queryDocument(user.get("id").toString());
        }else{
        	 listDou = homeService.queryDocument("0");
        }
        
        modelAndView.addObject("listDou", listDou);
        modelAndView.addObject("listNot", listNot);
        modelAndView.addObject("listArt", listArt);
        modelAndView.addObject("listAut", listAut);
        modelAndView.addObject("listCom", listCom);
        modelAndView.addObject("adInfo1", adInfo1);
        modelAndView.addObject("adInfo2", adInfo2);
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
        List<Map<String, Object>> listrows = homeService.queryBook(rowsmap);
        //模板(首页默认显示学校教育下的书籍,从第一条开始显示，每页10条数据)
        Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("startrows", 0);
        pmap.put("type", MapUtils.getIntValue(types.get(0), "id"));
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
            if (listBok.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
            } else {
                map.put("img", listBok.get(i).get("image_url"));
            }
            ((List) map2.get("books")).add(map);

        }
        html += templateService.mergeTemplateIntoString(vm, map2);

        if (listrows.size() % 10 == 0) {
            flag = listrows.size() / 10;
        } else {
            flag = listrows.size() / 10 + 1;
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
            typeMap.put("startrows", startrows * 10 + 1);
            List<Map<String, Object>> listRow = homeService.queryBook(typeMap);
            for (int i = 0; i < listRow.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", listRow.get(i).get("id"));
                map.put("author", listRow.get(i).get("author"));
                map.put("bookname", listRow.get(i).get("bookname"));
                if (listRow.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                    map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
                } else {
                    map.put("img", listRow.get(i).get("image_url"));
                }
                ((List) map2.get("books")).add(map);
            }
        } else {
            typeMap.put("startrows", (startrows - 2) * 10);
            List<Map<String, Object>> listRow = homeService.queryBook(typeMap);
            for (int i = 0; i < listRow.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", listRow.get(i).get("id"));
                map.put("author", listRow.get(i).get("author"));
                map.put("bookname", listRow.get(i).get("bookname"));
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
     * 查询下一页数据
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
        Map<String, Object> cmap = new HashMap<String, Object>();
        cmap.put("startrows", -1);
        cmap.put("type", Integer.parseInt(state));
        List<Map<String, Object>> sizerow = homeService.queryBook(cmap);
        int flag = 0;
        if (sizerow.size() % 10 == 0) {
            flag = sizerow.size() / 10;
        } else {
            flag = sizerow.size() / 10 + 1;
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
    public Map<String, Object> changesale(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String type = request.getParameter("type");
        List<Map<String, Object>> listSal = homeService.querySale(Integer.parseInt(type));
        map.put("type", listSal);
        return map;
    }
}
