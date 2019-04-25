package com.bc.pmpheep.back.authadmin.applydocaudit.controller;

import com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.template.service.TemplateService;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.back.util.ObjectUtil;
import com.bc.pmpheep.controller.bean.ResponseBean;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName: DataAuditController
 * @Description: 申报资料审核（机构用户）
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:25:53
 * 
 */
@Controller
@RequestMapping(value = "/dataaudit")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DataAuditController extends BaseController{
	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
	DataAuditService dataAuditService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.template.service.TemplateService")
	private TemplateService templateService;

	@Autowired
	DataDictionaryService dataDictionaryService;


	/**
	 * 
	 * @Title: toPage
	 * @Description: 跳转到主页
	 * @param @param request
	 * @param @return
	 * @param @throws UnsupportedEncodingException
	 * @return ModelAndView 返回类型
	 * @throws
	 */
	@RequestMapping("toPage")
	public ModelAndView toPage(HttpServletRequest request)
			throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView();
		String material_id = request.getParameter("material_id");
		String view_audit = request.getParameter("view_audit");
		Map<String,Object> userMap =  this.getUserInfo();
		String user_id = userMap.get("id").toString();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("material_id", material_id);
		//获取标题
		String	material_name = dataAuditService.findTitleName(map);
		mv.addObject("material_id", material_id);
		mv.addObject("view_audit", view_audit);
		mv.addObject("material_name", material_name);
		mv.addObject("userId", user_id);
		mv.setViewName("authadmin/applydocaudit/dataaudit");
		return mv;
	}

	/**
	 * 
	 * @Title: findDataAudit
	 * @Description: 申报资料审核（机构用户）
	 * @param @param request
	 * @param @return
	 * @return ResponseBean<Map<String,Object>> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findDataAudit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findDataAudit(
			HttpServletRequest request) {

		ResponseBean<Map<String, Object>> rb = new ResponseBean<Map<String, Object>>();
		Integer pageNum = Integer.parseInt(request.getParameter("pageNum"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		//String queryName = request.getParameter("queryName");
		String realname = request.getParameter("realname");
		String textbook_name = request.getParameter("textbook_name");
		String title = request.getParameter("title");
		String online_progress = request.getParameter("online_progress");
		
		String material_id = request.getParameter("material_id");
		String contextpath = request.getParameter("contextpath");

		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("realname", realname);
		paraMap.put("textbook_name", textbook_name);
		paraMap.put("title", title);
		paraMap.put("online_progress", online_progress);
		paraMap.put("material_id", material_id);
		paraMap.put("userId", this.getUserInfo().get("id").toString());
		
		PageParameter<Map<String, Object>> pageParameter = new PageParameter<Map<String, Object>>(
				pageNum, pageSize);
		pageParameter.setParameter(paraMap);
		List<Map<String, Object>> List_map = dataAuditService.findDataAudit(pageParameter);
        if(List_map.size()>0){
            for(Map<String,Object> map:List_map){

            	   String post =map.get("preset_position").toString();
            String tit = map.get("dtitle").toString();
            if(tit!=null){
                if(ObjectUtil.isNumber(tit)){
                    tit=dataDictionaryService.getDataDictionaryItemNameByCode(Const.WRITER_USER_TITLE,tit);
                }
            }
            if(post!=null){
                if(ObjectUtil.isNumber(post)){
                    post = dataDictionaryService.getDataDictionaryItemNameByCode(Const.PMPH_POSITION,post);
                }
            }

				map.put("bpp",map.get("textbook_name")+"-"+post);
                map.put("dtitle",tit);
            }
        }

		int totoal_count = dataAuditService.findDataAuditCount(pageParameter);

		Map<String, Object> vm_map = new HashMap<String, Object>();
		vm_map.put("List_map", List_map);
		vm_map.put("material_id", material_id);
		vm_map.put("startNum", pageParameter.getStart() + 1);
		vm_map.put("contextpath", contextpath);
		String html = "";
		String vm = "authadmin/applydocaudit/dataaudit.vm";
		html = templateService.mergeTemplateIntoString(vm, vm_map);

		Map<String, Object> data_map = new HashMap<String, Object>();
		data_map.put("html", html);
		data_map.put("totoal_count", totoal_count);
		
		return data_map;
	}
	
		//申报审核页面
		@RequestMapping("toMaterialAudit")
		public ModelAndView toMaterialAudit(HttpServletRequest request,
				HttpServletResponse response){
			ModelAndView mav = new ModelAndView("authadmin/applydocaudit/toMaterialAudit");
			//传参  user_id  material_id
			String material_id = request.getParameter("material_id");
			String declaration_id = request.getParameter("declaration_id");
			String view_audit = request.getParameter("view_audit");
			Map<String,Object> queryMap = new HashMap<String,Object>();
			queryMap.put("material_id", material_id); 
			queryMap.put("declaration_id", declaration_id); 
			
			//1.作家申报表
			List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
			gezlList = this.dataAuditService.queryPerson(queryMap);
			if(gezlList.size()>0){
				for (Map<String, Object> map : gezlList) {
					String tit=map.get("title").toString();
					if(tit!=null){
						if(ObjectUtil.isNumber(tit)){
							tit=dataDictionaryService.getDataDictionaryItemNameByCode(Const.WRITER_USER_TITLE,tit);
						}
					}

					map.put("title", tit);

				}
			}
			String return_cause = "";
			if(declaration_id == null){
			queryMap.put("declaration_id", gezlList.get(0).get("id"));
			}else{
				queryMap.put("declaration_id", declaration_id);
				if ("4".equals(gezlList.get(0).get("online_progress").toString())) {
					if(gezlList.get(0).get("return_cause")==null){
						return_cause="";
					}else{
						return_cause = gezlList.get(0).get("return_cause").toString();
					}
					mav.addObject("return_cause", return_cause);
				}
			}
			if(material_id == null){
				material_id= gezlList.get(0).get("material_id").toString();
			}
			queryMap.put("material_id", material_id);
			//教材信息
			Map<String,Object> materialMap = new HashMap<String,Object>();
			materialMap = this.dataAuditService.queryMaterialbyId(material_id);
			//2.作家申报职位暂存
			List<Map<String,Object>> tssbList = new ArrayList<Map<String,Object>>();
			if(gezlList.get(0).get("online_progress").toString().equals("0")){ //表示未提交
				tssbList=this.dataAuditService.queryTssbZc(queryMap);
			}else{//退回，通过，提交 都在正式申请表
				tssbList=this.dataAuditService.queryTsxz(queryMap);
			}
			if(tssbList.size()>0){
				for (Map<String, Object> map : tssbList) {

					String post=map.get("preset_position").toString();
					if(post!=null){
						if(ObjectUtil.isNumber(post)){
							post=dataDictionaryService.getDataDictionaryItemNameByCode(Const.PMPH_POSITION,post);
						}
					}


						map.put("preset_position", post);

				}
			}
			//3.作家学习经历表
			List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
			stuList=this.dataAuditService.queryStu(queryMap);

			//4.作家工作经历表
			List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
			workList=this.dataAuditService.queryWork(queryMap);
			//5.作家教学经历表
			List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
			steaList=this.dataAuditService.queryStea(queryMap);
			//6.作家兼职学术表
			List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
			zjxsList=this.dataAuditService.queryZjxs(queryMap);
			//7.作家上套教材参编情况表
			List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
			jcbjList=this.dataAuditService.queryJcbj(queryMap);
			//8.作家精品课程建设情况表
			List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
			gjkcjsList=this.dataAuditService.queryGjkcjs(queryMap);
			//9.作家主编国家级规划教材情况表
			List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
			gjghjcList = this.dataAuditService.queryGjghjc(queryMap);
			 //10.作家教材编写情况表
			List<Map<String,Object>> rwsjcList = new ArrayList<Map<String,Object>>();
			List<Map<String,Object>> jcbxqtList = new ArrayList<Map<String,Object>>();
			//其他社教材编写情况
			jcbxqtList=this.dataAuditService.queryqtJcbx(queryMap);
			
			//11.作家科研情况表
			List<Map<String,Object>> zjkyList = new ArrayList<Map<String,Object>>();
			zjkyList = this.dataAuditService.queryZjkyqk(queryMap);
			//12.作家扩展项填报表
			List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
			zjkzqkList = this.dataAuditService.queryZjkzbb(queryMap);
			//13.个人成就
			Map<String,Object> achievementMap = new HashMap<String,Object>();
			achievementMap = this.dataAuditService.queryAchievement(queryMap);
			//14.主编学术专著情况表
			List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
			monographList = this.dataAuditService.queryMonograph(queryMap);
			//15.出版行业获奖情况表
			List<Map<String,Object>> publishList = new ArrayList<Map<String,Object>>();
			publishList = this.dataAuditService.queryPublish(queryMap);
			//16.SCI论文投稿及影响因子情况表
			List<Map<String,Object>> sciList = new ArrayList<Map<String,Object>>();
			sciList = this.dataAuditService.querySci(queryMap);
			//17.临床医学获奖情况表
			List<Map<String,Object>> clinicalList = new ArrayList<Map<String,Object>>();
			clinicalList = this.dataAuditService.queryClinicalreward(queryMap);
			//18.学术荣誉授予情况表
			List<Map<String,Object>> acadeList = new ArrayList<Map<String,Object>>();
			acadeList = this.dataAuditService.queryAcadereward(queryMap);
			//19.人卫社编写情况
			rwsjcList=this.dataAuditService.rwsjcList(queryMap);
			//20.参加人卫慕课、数字教材编写情况
			Map<String,Object> moocMap = new HashMap<String,Object>();
			moocMap = this.dataAuditService.queryMoocdigital(queryMap);
			//21.编写内容意向表
			Map<String,Object> intentionMap = new HashMap<String,Object>();
			intentionMap = this.dataAuditService.queryIntention(queryMap);
			
			//是否已被遴选，用于退回前提示。
			declaration_id = queryMap.get("declaration_id").toString();
			List<Map<String,Object>> choosenList = dataAuditService.queryChoosenListByDeclarationId(declaration_id);
			Boolean expertChoosen = choosenList!=null&&choosenList.size()>0;
			mav.addObject("choosenList", choosenList);
			mav.addObject("expertChoosen", expertChoosen);

			mav.addObject("material", materialMap);
			mav.addObject("gezlList", gezlList.get(0));
			mav.addObject("tssbList", tssbList);
			mav.addObject("stuList", stuList);
			mav.addObject("workList", workList);
			mav.addObject("steaList", steaList);
			mav.addObject("jcbjList", jcbjList);
			mav.addObject("gjkcjsList", gjkcjsList);
			mav.addObject("rwsjcList", rwsjcList);
			mav.addObject("gjghjcList", gjghjcList);
			mav.addObject("zjkyList", zjkyList);
			mav.addObject("zjxsList", zjxsList);
			mav.addObject("zjkzqkList", zjkzqkList);
			mav.addObject("achievementMap", achievementMap);
			mav.addObject("monographList", monographList);
			mav.addObject("publishList", publishList);
			mav.addObject("sciList", sciList);
			mav.addObject("clinicalList", clinicalList);
			mav.addObject("acadeList", acadeList);
			mav.addObject("jcbxqtList", jcbxqtList);
			mav.addObject("digitalMap", moocMap);
			mav.addObject("intentionMap", intentionMap);
			
			
			mav.addObject("view_audit", view_audit);
			mav.addObject("material_id", material_id);
			mav.addObject("online_progress", gezlList.get(0).get("online_progress").toString());//判断审核通过、退回按钮是否隐藏
			return mav;
		}
		
		
		//申报审核通过
				@RequestMapping("doMaterialAuditPass")
				@ResponseBody
				public Map<String,Object> doMaterialAuditPass(HttpServletRequest request,
						HttpServletResponse response){
					Map<String,Object> paramMap = new Hashtable<String,Object>();
					Map<String,Object> resultMap = new HashMap<String,Object>();
					String declaration_id = request.getParameter("declaration_id");
					String online_progress = request.getParameter("online_progress");  //类型
					String writer_id = request.getParameter("user_id");  //作家用户Id
					Map<String,Object> userMap =  this.getUserInfo();
					String user_id = userMap.get("id").toString();
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					String date = df.format(new Date());
					String msg = "";
					
					paramMap.put("declaration_id", declaration_id);
					paramMap.put("online_progress", online_progress);
					paramMap.put("auth_user_id", user_id);
					paramMap.put("auth_date", date);
					paramMap.put("writer_id", writer_id);
					
					int count = this.dataAuditService.updateDeclarationPass(paramMap);
					if(count>0){
						msg = "OK";
					}
					resultMap.put("msg", msg);
					return resultMap;
				}
				
		
		//申报审核退回
		@RequestMapping("doMaterialAudit")
		@ResponseBody
		public Map<String,Object> doMaterialAudit(HttpServletRequest request,
				HttpServletResponse response){
			Map<String,Object> paramMap = new HashMap<String,Object>();
			Map<String,Object> resultMap = new HashMap<String,Object>();
			String declaration_id = request.getParameter("declaration_id");
			String online_progress = request.getParameter("online_progress");  //类型
			String return_cause = request.getParameter("return_cause");  //退回原因
			String writer_id = request.getParameter("user_id");  //作家用户Id
			Map<String,Object> userMap =  this.getUserInfo();
			String user_id = userMap.get("id").toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String date = df.format(new Date());
			String msg = "";
			
			paramMap.put("declaration_id", declaration_id);
			paramMap.put("online_progress", online_progress);
			paramMap.put("auth_user_id", user_id);
			paramMap.put("auth_date", date);
			paramMap.put("return_cause", return_cause);
			paramMap.put("writer_id", writer_id);
			int count = this.dataAuditService.updateDeclaration(paramMap);
			if(count>0){
				msg = "OK";
			}
			resultMap.put("msg", msg);
			return resultMap;
		}
		

		
		/**
		 * 页面组合方法，主要js中通过ajax传值对新增页面模块进行初始化操作
		 * @param request
		 * @return
		 */
		@RequestMapping("queryMaterialMap")
		@ResponseBody
		public Map<String,Object> queryMaterialMap(HttpServletRequest request){
			//教材信息
			String material_id = request.getParameter("material_id");
			Map<String,Object> materialMap = new HashMap<String,Object>();
			materialMap = this.dataAuditService.queryMaterialbyId(material_id);
			if(materialMap==null){
				materialMap=new HashMap<String,Object>();
			}
			return materialMap;
		}
		
		/**
		 * 强制登录方法
		 * @return
		 */
		@RequestMapping("tologin")
		@ResponseBody
		public String tologin(){
			String returncode="OK";
			return returncode;
		}

	//点击打印按钮改变打印状态
	@RequestMapping(value = "/updPrintStatus")
	@ResponseBody
	public String updPrintStatus(HttpServletRequest request) {
		String id = request.getParameter("did");
		this.dataAuditService.updPrintStatus(id);
		return "OK";
	}

	//机构用户认证审核
	@RequestMapping(value = "/checkAuthen")
	@ResponseBody
	public String checkAuthen(HttpServletRequest request) {
		return "OK";
	}

}
