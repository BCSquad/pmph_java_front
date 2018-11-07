package com.bc.pmpheep.back.commuser.research.controller;

import com.bc.pmpheep.back.commuser.research.service.ReSearchService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("research")
public class ReSearchController extends BaseController{

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.research.service.ReSearchServiceImpl")
    private ReSearchService reSearchService;

    //查询列表方法
    @RequestMapping("tolist")
    public ModelAndView tolist(HttpServletRequest request,
          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        ModelAndView modelAndView=new ModelAndView();
        String state = request.getParameter("state");
        Map<String,Object> map = new HashMap<>();
        Map<String, Object> user=getUserInfo();
        map.put("id",user.get("id"));
        map.put("state",state);
        PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(pageNum, pageSize);
        pageParameter.setParameter(map);
        PageResult<Map<String, Object>> pageResult = reSearchService.querySearchList(pageParameter);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("pageSize",pageSize);
        modelAndView.addObject("pageResult",pageResult);
        modelAndView.addObject("state",state);
        modelAndView.setViewName("commuser/research/researchList");
        return modelAndView;
    }

    //根据教材ID查询调研表
    @RequestMapping("querySearch")
    @ResponseBody
    public List<Map<String,Object>> querySearch(HttpServletRequest request){
        String material_id=request.getParameter("material_id");
        List<Map<String,Object>> list = reSearchService.querySearchByMaterialId(material_id);
        return list;
    }
}
