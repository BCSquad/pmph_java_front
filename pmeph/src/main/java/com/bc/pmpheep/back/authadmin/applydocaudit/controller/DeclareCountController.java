package com.bc.pmpheep.back.authadmin.applydocaudit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountService;
import com.bc.pmpheep.back.template.service.TemplateService;

/**
 * 
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @modify (最后修改时间) 
 * @修改人 ：
 * @审核人 ：
 * </pre>
 */
@Controller
@RequestMapping(value = "/declareCount")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DeclareCountController {
    @Autowired
    @Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DeclareCountServiceImpl")
    DeclareCountService declareCountService;
    
	@Autowired
    @Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
    private TemplateService templateService;

    /**
     * 
     * <pre>
     * 功能描述：申报统计（机构用户）
     * 使用示范：
     *
     * @param sessionId
     * @return 分页数据集
     * </pre>
     */
    @RequestMapping(value = "/findDeclareCount")  
    @ResponseBody
    public ModelAndView findDataAudit(HttpServletRequest  request) {
		
		List<Map<String, Object>> list=	declareCountService.findDeclareCount();
		//最终结果名单列表
		List<Map<String, Object>> listName=	declareCountService.findNameList();
      		ModelAndView mv = new ModelAndView();
      		mv.addObject("listMap",list);
      		mv.addObject("listName",listName);
        mv.setViewName("authadmin/applydocaudit/declarecount");
        return mv;
    }
    
	//查询更多通知列表
	@RequestMapping(value="/loadMore")
	@ResponseBody
	public List<Map<String,Object>> loadMore(HttpServletRequest request){
		String para=request.getParameter("startPara");
		int startPara=0;
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(null!=para&&!para.equals("")){
			startPara = Integer.parseInt(para);
			
			paraMap.put("startPara",startPara);
			
		}else{
			startPara=15;
			paraMap.put("startPara",startPara);
		}
		
	
		List<Map<String,Object>> list = declareCountService.selectNoticeMessage(paraMap);
		
		return list;
	}
    
    
}
