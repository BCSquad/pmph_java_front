package com.bc.pmpheep.back.commuser.readpage.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bc.pmpheep.back.commuser.homepage.service.HomeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.commuser.readpage.service.ReadService;
import com.bc.pmpheep.back.template.service.TemplateService;

/**
 * 注释:读书主页面
 *
 * @Author:黄琼飞
 * @date 2017-11-22下午2:18:13
 */
@Controller
@RequestMapping("/readpage")
public class ReadController {

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.readpage.service.ReadServiceImpl")
    private ReadService readService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.homepage.service.HomeServiceImpl")
    private HomeService homeService;

    //首页跳转
    @RequestMapping("/main")
    public ModelAndView toreadMain(HttpServletRequest request,
                                   HttpServletResponse res
                                   ) {
        ModelAndView mv = new ModelAndView("commuser/readpage/readpage");
        //热门书评
        List<Map<String, Object>> zdtjXxjyList = new ArrayList<Map<String, Object>>();
        Map<String, Object> zdtjXxjyMap = new HashMap<String, Object>();
        Map<String,Object> adInfo3=homeService.getPageAdInfo("首页原重点推荐1");
        Map<String,Object> adInfo4=homeService.getPageAdInfo("首页原重点推荐2");
        //查询条数
        zdtjXxjyMap.put("startrows", "0");
        zdtjXxjyMap.put("endrows", "4");
        zdtjXxjyList = readService.queryRmspReadList(zdtjXxjyMap);
        mv.addObject("adInfo3",adInfo3);
        mv.addObject("adInfo4",adInfo4);
        mv.addObject("rmspList", zdtjXxjyList);

        Map<String, Object> adInfo = homeService.getPageAdInfo("读书首页轮播 ");
        mv.addObject("adInfo", adInfo);

        List<Map<String, Object>> materialType = readService.queryMaterialType();

        List<Map<String, Object>> gradeMaterialType = new ArrayList<Map<String, Object>>();

        //循环取出分类信息，按层次排列好
        for (Map<String, Object> type : materialType) {
            if (MapUtils.getString(type, "path", "").split("-").length == 1) {
                List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
                type.put("dataList", dataList);
                for (Map<String, Object> type2 : materialType) {
                    if (MapUtils.getString(type, "id").equals(MapUtils.getString(type2, "parent_id"))) {
                        List<Map<String, Object>> dataList2 = new ArrayList<Map<String, Object>>();
                        type2.put("dataList", dataList2);
                        for (Map<String, Object> type3 : materialType) {
                            if (MapUtils.getString(type2, "id").equals(MapUtils.getString(type3, "parent_id"))) {
                                dataList2.add(type3);
                            }
                        }
                        dataList.add(type2);
                    }
                }
                gradeMaterialType.add(type);
            }
        }
        List<Map<String, Object>> types = homeService.queryBookType(0);
        Collections.sort(types, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return MapUtils.getIntValue(o1, "sort") - MapUtils.getIntValue(o2, "sort");
            }
        });
        mv.addObject("bookTypes", types);
        mv.addObject("materialType", gradeMaterialType);

        return mv;
    }

    //查询某类下的重点推荐图书
    @RequestMapping("searchZdtjBook")
    @ResponseBody
    public Map<String, Object> searchZdtjBook(HttpServletRequest request) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        List<Map<String, Object>> zdtjXxjyList = new ArrayList<Map<String, Object>>();
        Map<String, Object> zdtjXxjyMap = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String html = "";
        String vm = "commuser/readpage/readpage.vm";
        //图书类型
        zdtjXxjyMap.put("type", type);
        /*if (type.equals("0")) {
            zdtjXxjyMap.put("type", 633); //学校教育
        } else if (type.equals("1")) {
            zdtjXxjyMap.put("type", 634); //毕业教育
        } else if (type.equals("2")) {
            zdtjXxjyMap.put("type", 635); //继续教育
        } else if (type.equals("3")) {
            zdtjXxjyMap.put("type", 636); //考试用书
        }*/
        //查询条数
        zdtjXxjyMap.put("startrows", "0");
        zdtjXxjyMap.put("endrows", "10");
        zdtjXxjyList = readService.queryZdtjReadList(zdtjXxjyMap);
        for (int i = 0; i < zdtjXxjyList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("author", zdtjXxjyList.get(i).get("author"));
            map.put("bookname", zdtjXxjyList.get(i).get("bookname"));
            if (zdtjXxjyList.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
            } else {
                map.put("img", zdtjXxjyList.get(i).get("image_url"));
            }
/*			 map.put("img", zdtjXxjyList.get(i).get("image_url"));*/
            map.put("url", zdtjXxjyList.get(i).get("buy_url"));
            map.put("id", zdtjXxjyList.get(i).get("id"));
            html += templateService.mergeTemplateIntoString(vm, map);
            pMap.put("pagebook", html);
        }
        return pMap;
    }

    //查询某类下的新书推荐
    @RequestMapping("searchXstjBook")
    @ResponseBody
    public Map<String, Object> searchXstjBook(HttpServletRequest request) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        List<Map<String, Object>> zdtjXxjyList = new ArrayList<Map<String, Object>>();
        Map<String, Object> zdtjXxjyMap = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String html = "";
        String vm = "commuser/readpage/readpage.vm";
        //图书类型
        zdtjXxjyMap.put("type", type);
        //查询条数
        zdtjXxjyMap.put("startrows", "0");
        zdtjXxjyMap.put("endrows", "10");
        zdtjXxjyList = readService.queryXstjReadList(zdtjXxjyMap);
        for (int i = 0; i < zdtjXxjyList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("author", zdtjXxjyList.get(i).get("author"));
            map.put("bookname", zdtjXxjyList.get(i).get("bookname"));
            if (zdtjXxjyList.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
            } else {
                map.put("img", zdtjXxjyList.get(i).get("image_url"));
            }
            /*map.put("img", zdtjXxjyList.get(i).get("image_url"));*/
            map.put("url", zdtjXxjyList.get(i).get("buy_url"));
            map.put("id", zdtjXxjyList.get(i).get("id"));
            html += templateService.mergeTemplateIntoString(vm, map);
            pMap.put("pagebook", html);
        }
        return pMap;
    }

    //查询某类下的图书畅销
    @RequestMapping("searchTscxBook")
    @ResponseBody
    public Map<String, Object> searchTscxBook(HttpServletRequest request) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        List<Map<String, Object>> zdtjXxjyList = new ArrayList<Map<String, Object>>();
        Map<String, Object> zdtjXxjyMap = new HashMap<String, Object>();
        String type = request.getParameter("type");
        String html = "";
        String vm = "commuser/readpage/tscxreadpage.vm";
        //图书类型
        zdtjXxjyMap.put("type", type);
        //查询条数
        zdtjXxjyMap.put("startrows", "0");
        zdtjXxjyMap.put("endrows", "6");
        zdtjXxjyList = readService.queryTscxReadList(zdtjXxjyMap);
        for (int i = 0; i < zdtjXxjyList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ordernum", i + 1);
            map.put("author", zdtjXxjyList.get(i).get("author"));
            map.put("bookname", zdtjXxjyList.get(i).get("bookname"));
            if (zdtjXxjyList.get(i).get("image_url").equals("DEFAULT")) {//显示默认图片
                map.put("img", request.getContextPath() + "/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
            } else {
                map.put("img", zdtjXxjyList.get(i).get("image_url"));
            }
            /*map.put("img", zdtjXxjyList.get(i).get("image_url"));*/
            map.put("url", zdtjXxjyList.get(i).get("buy_url"));
            map.put("id", zdtjXxjyList.get(i).get("id"));
            html += templateService.mergeTemplateIntoString(vm, map);
            pMap.put("pagebook", html);
        }
        return pMap;
    }
}
