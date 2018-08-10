package com.bc.pmpheep.back.commuser.materialdec.controller;

import com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl;
import com.bc.pmpheep.back.commuser.materialdec.service.ExpertationService;
import com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailService;
import com.bc.pmpheep.back.commuser.materialdec.service.PersonInfoService;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.controller.BaseController;
import com.bc.pmpheep.general.service.FileService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 注释:教材申报
 *
 * @Author:黄琼飞
 * @date 2017-11-27上午10:10:34
 */
@Controller
@RequestMapping("/expertation/")
public class ExpertationController extends BaseController{


	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailServiceImpl")
	private MaterialDetailService mdService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.materialdec.service.ExpertationServiceImpl")
	private ExpertationService etService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.commuser.materialdec.service.PersonInfoServiceImpl")
	private PersonInfoService perService;

	@Autowired
	@Qualifier("com.bc.pmpheep.back.authadmin.message.service.SendMessageServiceImpl")
	private SendMessageServiceImpl sendMessageServiceImpl;
	@Autowired
    @Qualifier("com.bc.pmpheep.general.service.FileService")
    FileService fileService;
	
	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;
	
	/**
	 * 跳转到申报新增页面
     *
	 * @param request
	 * @return
	 */
	@RequestMapping("toExpertationAdd")
	public ModelAndView toMaterialAdd(HttpServletRequest request,String... arrMaterial_id){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toExpertationAdd");
		//3.作家学习经历表
		List<Map<String,Object>> perstuList = new ArrayList<Map<String,Object>>();
		//4.作家工作经历表
		List<Map<String,Object>> perworkList = new ArrayList<Map<String,Object>>();
		//5.作家教学经历表
		List<Map<String,Object>> persteaList = new ArrayList<Map<String,Object>>();
		//6.作家兼职学术表
		List<Map<String,Object>> perzjxsList = new ArrayList<Map<String,Object>>();
		//7.作家上套教材参编情况表
		List<Map<String,Object>> perjcbjList = new ArrayList<Map<String,Object>>();
		//8.作家精品课程建设情况表
		List<Map<String,Object>> pergjkcjsList = new ArrayList<Map<String,Object>>();
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> pergjghjcList = new ArrayList<Map<String,Object>>();
		//10.其他社教材编写情况
		List<Map<String,Object>> perjcbxList = new ArrayList<Map<String,Object>>();
		//11.作家科研情况表
		List<Map<String,Object>> perzjkyList = new ArrayList<Map<String,Object>>();
		//14.主编学术专著情况表
		List<Map<String,Object>> permonographList = new ArrayList<Map<String,Object>>();
		//15.出版行业获奖情况表
		List<Map<String,Object>> perpublishList = new ArrayList<Map<String,Object>>();
		//16.SCI论文投稿及影响因子情况表
		List<Map<String,Object>> persciList = new ArrayList<Map<String,Object>>();
		//17.临床医学获奖情况表
		List<Map<String,Object>> perclinicalList = new ArrayList<Map<String,Object>>();
		//18.学术荣誉授予情况表
		List<Map<String,Object>> peracadeList = new ArrayList<Map<String,Object>>();
		//19.人卫社教材编写情况表
		List<Map<String,Object>> perpmphList = new ArrayList<Map<String,Object>>();

		Map<String,Object> queryMap = new HashMap<String,Object>();
		//查询个人信息库信息
		//个人资料
		Map<String,Object> userinfo =  this.getUserInfo();
		Map<String,Object> userMap =  this.mdService.queryUserInfo(MapUtils.getString(userinfo,"id",""));
        queryMap.put("user_id",userMap.get("id"));
		//个人资料库信息
		perstuList = this.perService.queryPerStu(queryMap);
        perworkList= this.perService.queryPerWork(queryMap);
        persteaList=this.perService.queryPerStea(queryMap);
        perzjxsList=this.perService.queryPerZjxs(queryMap);
        perjcbjList=this.perService.queryPerJcbj(queryMap);
        pergjkcjsList=this.perService.queryPerGjkcjs(queryMap);
        pergjghjcList=this.perService.queryPerGjghjc(queryMap);
        perjcbxList=this.perService.queryqtPerJcbx(queryMap);
        perzjkyList=this.perService.queryPerZjkyqk(queryMap);
        permonographList=this.perService.queryPerMonograph(queryMap);
        perpublishList=this.perService.queryPerPublish(queryMap);
        persciList=this.perService.queryPerSci(queryMap);
        perclinicalList=this.perService.queryPerClinicalreward(queryMap);
        peracadeList=this.perService.queryPerAcadereward(queryMap);
        perpmphList=this.perService.rwsjcPerList(queryMap);


        String material_id = request.getParameter("material_id"); //教材ID
        if(arrMaterial_id !=null && arrMaterial_id.length>0){
            material_id = arrMaterial_id[0];
        }
		//作家扩展信息
		List<Map<String,Object>> zjkzxxList = this.etService.queryZjkzxxById(material_id);

		mav.addObject("userMap", userMap);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("material_id", material_id);

		mav.addObject("perstuList",perstuList);
		mav.addObject("perworkList",perworkList);
		mav.addObject("persteaList",persteaList);
		mav.addObject("perzjxsList",perzjxsList);
		mav.addObject("perjcbjList",perjcbjList);
		mav.addObject("pergjkcjsList",pergjkcjsList);
		mav.addObject("pergjghjcList",pergjghjcList);
		mav.addObject("perjcbxList",perjcbxList);
		mav.addObject("perzjkyList",perzjkyList);
		mav.addObject("permonographList",permonographList);
		mav.addObject("perpublishList",perpublishList);
		mav.addObject("perclinicalList",perclinicalList);
		mav.addObject("peracadeList",peracadeList);
		mav.addObject("persciList",persciList);
		mav.addObject("perpmphList",perpmphList);
		return mav;
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
		String product_id = request.getParameter("material_id");
		Map<String,Object> materialMap = new HashMap<String,Object>();
		materialMap = this.etService.queryMaterialbyId(product_id);
		return materialMap;
	}
	
	/**
	 * 添加申报信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doExpertationAdd")
	@ResponseBody
	public Map<String,Object> doMaterialAdd(HttpServletRequest request,
			HttpServletResponse response){
		//公共参数
		Map<String,Object> userMap =  this.getUserInfo();
		String material_id = request.getParameter("material_id");
		String expert_type = "1"; //申报类型
		String expertation_id = request.getParameter("expertation_id");
		String user_id = request.getParameter("user_id"); //系统用户(暂存人)
		String type = request.getParameter("type"); //类型
		String sjump = request.getParameter("sjump"); //页面来源
		//求出信息集合
		//1.作家申报表
		Map<String,Object> perMap = new HashMap<String,Object>();
		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		//5.作家教学经历表
		List<Map<String,Object>> steaList = new ArrayList<Map<String,Object>>();
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		//7.作家上套教材参编情况表
		List<Map<String,Object>> jcbjList = new ArrayList<Map<String,Object>>();
		//8.作家精品课程建设情况表
		List<Map<String,Object>> gjkcjsList = new ArrayList<Map<String,Object>>();
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
		//10.其他社教材编写情况
		List<Map<String,Object>> jcbxList = new ArrayList<Map<String,Object>>();
		//11.作家科研情况表
		List<Map<String,Object>> zjkyList = new ArrayList<Map<String,Object>>();
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		//13.个人成就
		Map<String,Object> achievementMap = new HashMap<String,Object>();
		//14.主编学术专著情况表
		List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
		//15.出版行业获奖情况表
		List<Map<String,Object>> publishList = new ArrayList<Map<String,Object>>();
		//16.SCI论文投稿及影响因子情况表
		List<Map<String,Object>> sciList = new ArrayList<Map<String,Object>>();
		//17.临床医学获奖情况表
		List<Map<String,Object>> clinicalList = new ArrayList<Map<String,Object>>();
		//18.学术荣誉授予情况表
		List<Map<String,Object>> acadeList = new ArrayList<Map<String,Object>>();
		//19.人卫社教材编写情况表
		List<Map<String,Object>> pmphList = new ArrayList<Map<String,Object>>();
		//20.参加人卫慕课、数字教材编写情况
		Map<String,Object> digitalMap = new HashMap<String,Object>();
		//21.编写内容意向表
		Map<String,Object> intentionlMap = new HashMap<String,Object>();
		
		String is_background = "0";
		String msg = "";
		String online_progress = request.getParameter("online_progress");
		//创建时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		
		//专家信息
		if(type.equals("1")){ //提交
			perMap.put("is_staging", "0");
			perMap.put("online_progress", "1");
			perMap.put("commit_date", date);
		}else{
			if(online_progress != null && online_progress.equals("2")){ //被退回
				perMap.put("is_staging", "0");
				perMap.put("online_progress", "2");
			}else{//未提交
				perMap.put("is_staging", "1");
				perMap.put("online_progress", "0");
			}
		}
        Map<String, Object> materialMap = new HashMap<String, Object>();
        materialMap = this.etService.queryMaterialbyId(material_id);
        perMap.put("materialName", MapUtils.getString(materialMap, "material_name"));
		perMap.put("realname", request.getParameter("realname"));
		perMap.put("user_id", user_id);
		perMap.put("type", type);
		perMap.put("is_teacher", userMap.get("is_teacher"));
		perMap.put("product_id", material_id);
		perMap.put("expert_type", expert_type);
		String sex = request.getParameter("sex");
		if(sex == null || sex.length() <= 0){
			sex = "1";
		}
		perMap.put("sex",sex);
		perMap.put("birthday", "".equals(request.getParameter("birthday")) ? null:request.getParameter("birthday"));
		perMap.put("experience", "".equals(request.getParameter("experience")) ? null:request.getParameter("experience"));
		perMap.put("org_name", request.getParameter("org_name"));
		perMap.put("position", request.getParameter("position"));
		perMap.put("fax", request.getParameter("fax"));
		perMap.put("title", request.getParameter("title"));
		perMap.put("address", request.getParameter("address"));
		perMap.put("postcode", request.getParameter("postcode"));
		perMap.put("telephone", request.getParameter("telephone"));
		perMap.put("handphone", request.getParameter("handphone"));
		perMap.put("email", request.getParameter("email"));
        perMap.put("idtype", "".equals(request.getParameter("idtype")) ? null:request.getParameter("idtype"));
		perMap.put("idcard", request.getParameter("idcard"));
		perMap.put("org_id", "".equals(request.getParameter("sbdw_id")) ? null:request.getParameter("sbdw_id"));
		perMap.put("degree", "".equals(request.getParameter("degree")) ? null:request.getParameter("degree"));
		perMap.put("rank","2");
		perMap.put("expertise", request.getParameter("expertise"));
		perMap.put("gmt_create", date);
		perMap.put("sub_classification ", request.getParameter("sub_classification"));
		perMap.put("cont_classification ", request.getParameter("cont_classification"));
		perMap.put("remark", request.getParameter("remark"));
		perMap.put("unit_advise", request.getParameter("syllabus_id"));
		perMap.put("syllabus_name", request.getParameter("syllabus_name"));
		//获取学科及内容分类id
		String subjectIds[] = request.getParameterValues("subjectId");
        String contentIds[] = request.getParameterValues("contentId");
		List<Map<String,Object>> subjectList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> contentList = new ArrayList<Map<String,Object>>();
		if(subjectIds.length>0){
			for(int i=0;i<subjectIds.length;i++) { //遍历数组
				Map<String,Object> subjectMap = new HashMap<String,Object>();
				subjectMap.put("subjectId", subjectIds[i]);
				subjectList.add(subjectMap);
			}
		}
		if(contentIds.length>0){
			for(int i=0;i<contentIds.length;i++) { //遍历数组
				Map<String,Object> contentMap = new HashMap<String,Object>();
				contentMap.put("contentId", contentIds[i]);
				contentList.add(contentMap);
			}
		}

		//主要学习经历
		String xx_kssj[] = request.getParameterValues("xx_kssj");
		String xx_jssj[] = request.getParameterValues("xx_jssj");
		String xx_major[] = request.getParameterValues("xx_major");
		String xx_degree[] = request.getParameterValues("xx_degree");
		String xx_note[] = request.getParameterValues("xx_note");
		String xx_id[] = request.getParameterValues("xx_id");
		String xx_school_name[] = request.getParameterValues("xx_school_name");
		for(int i=0;i<xx_kssj.length;i++) { //遍历数组
			if(!xx_kssj[i].equals("")){
				Map<String,Object> xxjlMap = new HashMap<String,Object>();
				xxjlMap.put("major", xx_major[i]);
				xxjlMap.put("school_name", xx_school_name[i]);
				xxjlMap.put("degree", xx_degree[i]);
				xxjlMap.put("note", xx_note[i]);
				xxjlMap.put("date_begin", "".equals(xx_kssj[i]) ? null:xx_kssj[i]);
				xxjlMap.put("date_end", "".equals(xx_jssj[i]) ? null:xx_jssj[i]);
				xxjlMap.put("sort", i);
				xxjlMap.put("per_id", xx_id[i]);
				//学习经历
				stuList.add(xxjlMap);
			}
		}
		//作家工作经历
		String gz_kssj[] = request.getParameterValues("gz_kssj");
		String gz_jssj[] = request.getParameterValues("gz_jssj");
		String gz_org_name[] = request.getParameterValues("gz_org_name");
		String gz_position[] = request.getParameterValues("gz_position");
		String gz_note[] = request.getParameterValues("gz_note");
		String gz_id[] = request.getParameterValues("gz_id");
		for(int i=0;i<gz_kssj.length;i++) { //遍历数组
			if(!gz_kssj[i].equals("")){ //判断是否存在
				Map<String,Object> workjlMap = new HashMap<String,Object>();
				workjlMap.put("org_name", gz_org_name[i]);
				workjlMap.put("position", gz_position[i]);
				workjlMap.put("note", gz_note[i]);
				workjlMap.put("date_begin", "".equals(gz_kssj[i]) ? null:gz_kssj[i]);
				workjlMap.put("date_end", "".equals(gz_jssj[i]) ? null:gz_jssj[i]);
				workjlMap.put("sort", i);
				workjlMap.put("per_id", gz_id[i]);
				//工作经历
				workList.add(workjlMap);
			}
		}
		//教学经历表
		String jx_kssj[] = request.getParameterValues("jx_kssj");
		String jx_jssj[] = request.getParameterValues("jx_jssj");
		String jx_school_name[] = request.getParameterValues("jx_school_name");
		String jx_subject[] = request.getParameterValues("jx_subject");
		String jx_note[] = request.getParameterValues("jx_note");
		String jx_id[] = request.getParameterValues("jx_id");
		for(int i=0;i<jx_kssj.length;i++) { //遍历数组
			if(!jx_kssj[i].equals("")){ //判断是否存在
				Map<String,Object> jxjlMap = new HashMap<String,Object>();
				jxjlMap.put("school_name", jx_school_name[i]);
				jxjlMap.put("subject", jx_subject[i]);
				jxjlMap.put("note", jx_note[i]);
				jxjlMap.put("date_begin", "".equals(jx_kssj[i]) ? null:jx_kssj[i]);
				jxjlMap.put("date_end", "".equals(jx_jssj[i]) ? null:jx_jssj[i]);
				jxjlMap.put("sort", i);
				jxjlMap.put("per_id", jx_id[i]);
				//教学经历
				steaList.add(jxjlMap);
			}
		}
		//作家兼职学术
		String xs_org_name[] = request.getParameterValues("xs_org_name");
		String xs_rank[] = request.getParameterValues("xs_rank");
		String xs_position[] = request.getParameterValues("xs_position");
		String xs_note[] = request.getParameterValues("xs_note");
		String xs_id[] = request.getParameterValues("xs_id");
		for(int i=0;i<xs_org_name.length;i++) { //遍历数组
			if(!xs_org_name[i].equals("")){ //判断是否存在
				Map<String,Object> xsjzMap = new HashMap<String,Object>();
				xsjzMap.put("org_name", xs_org_name[i]);
				xsjzMap.put("rank", "".equals(request.getParameter(xs_rank[i])) ? null:request.getParameter(xs_rank[i]));
				xsjzMap.put("note", xs_note[i]);
				xsjzMap.put("position", xs_position[i]);
				xsjzMap.put("sort", i);
				xsjzMap.put("per_id", xs_id[i]);
				//作家兼职学术
				zjxsList.add(xsjzMap);
			}
		}
		//上套教材参编情况
		String jc_material_name[] = request.getParameterValues("jc_material_name");
		String jc_position[] = request.getParameterValues("jc_position");
		String jc_note[] = request.getParameterValues("jc_note");
		String jc_publish_date[] = request.getParameterValues("jc_publish_date");
		String jc_publisher[] = request.getParameterValues("jc_publisher");
		String jc_is_digital_editor[] = request.getParameterValues("jc_is_digital_editor");
		String jc_id[] = request.getParameterValues("jc_id");
		for(int i=0;i<jc_material_name.length;i++) { //遍历数组
			if(!jc_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbjMap = new HashMap<String,Object>();
				JcbjMap.put("material_name", jc_material_name[i]);
                JcbjMap.put("is_digital_editor", "".equals(request.getParameter(jc_is_digital_editor[i])) ? null:request.getParameter(jc_is_digital_editor[i]));
                JcbjMap.put("position", "".equals(request.getParameter(jc_position[i])) ? null:request.getParameter(jc_position[i]));
				JcbjMap.put("note", jc_note[i]);
				JcbjMap.put("publisher", jc_publisher[i]);
				JcbjMap.put("publish_date", "".equals(jc_publish_date[i]) ? null:jc_publish_date[i]);

				JcbjMap.put("sort", i);
				JcbjMap.put("per_id", jc_id[i]);
				//作家上套教材参编
				jcbjList.add(JcbjMap);
			}
		}
		//精品课程建设情况
		String gj_course_name[] = request.getParameterValues("gj_course_name");
		String gj_class_hour[] = request.getParameterValues("gj_class_hour");
		String gj_note[] = request.getParameterValues("gj_note");
		String gj_type[] = request.getParameterValues("gj_type");
		String gj_id[] = request.getParameterValues("gj_id");
		for(int i=0;i<gj_type.length;i++) { //遍历数组
			if(!gj_course_name[i].equals("")){ //判断是否存在
				Map<String,Object> GjkcjsMap = new HashMap<String,Object>();
				GjkcjsMap.put("course_name", gj_course_name[i]);
				GjkcjsMap.put("class_hour", gj_class_hour[i]);
				GjkcjsMap.put("type", "".equals(request.getParameter(gj_type[i])) ? null:request.getParameter(gj_type[i]));
				GjkcjsMap.put("note", gj_note[i]);
				GjkcjsMap.put("sort", i);
				GjkcjsMap.put("per_id", gj_id[i]);
				//精品课程建设
				gjkcjsList.add(GjkcjsMap);
			}
		}
		//主编国家级规划教材情况
		String hj_material_name[] = request.getParameterValues("hj_material_name");
		String hj_isbn[] = request.getParameterValues("hj_isbn");
		String hj_rank_text[] = request.getParameterValues("hj_rank_text");
		String hj_note[] = request.getParameterValues("hj_note");
		String hj_id[] = request.getParameterValues("hj_id");
		for(int i=0;i<hj_material_name.length;i++) { //遍历数组
			if(!hj_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> GjghjcMap = new HashMap<String,Object>();
				GjghjcMap.put("material_name",hj_material_name[i]);
				GjghjcMap.put("isbn",hj_isbn[i]);
				GjghjcMap.put("rank_text",hj_rank_text[i]);
				GjghjcMap.put("note", hj_note[i]);
				GjghjcMap.put("sort", i);
				GjghjcMap.put("per_id",  hj_id[i]);
				//主编国家级规划
				gjghjcList.add(GjghjcMap);
			}
		}
		//其他社教材编写情况
		String jcb_material_name[] = request.getParameterValues("jcb_material_name");
		String jcb_rank[] = request.getParameterValues("jcb_rank");
		String jcb_position[] = request.getParameterValues("jcb_position");
		String jcb_publisher[] = request.getParameterValues("jcb_publisher");
		String jcb_publish_date[] = request.getParameterValues("jcb_publish_date");
		String jcb_isbn[] = request.getParameterValues("jcb_isbn");
		String jcb_is_digital_editor[] = request.getParameterValues("jcb_is_digital_editor");
		String jcb_note[] = request.getParameterValues("jcb_note");
		String jcb_id[] = request.getParameterValues("jcb_id");
		for(int i=0;i<jcb_material_name.length;i++) { //遍历数组
			if(!jcb_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbxMap = new HashMap<String,Object>();
				JcbxMap.put("material_name", jcb_material_name[i]);
				JcbxMap.put("rank", "".equals(jcb_rank[i]) ? null:jcb_rank[i]);
				JcbxMap.put("position", "".equals(jcb_position[i]) ? null:jcb_position[i]);
				JcbxMap.put("publisher", jcb_publisher[i]);
				JcbxMap.put("is_digital_editor", "".equals(request.getParameter(jcb_is_digital_editor[i])) ? null:request.getParameter(jcb_is_digital_editor[i]));
				JcbxMap.put("publish_date", "".equals(jcb_publish_date[i]) ? null:jcb_publish_date[i]);
				JcbxMap.put("isbn", jcb_isbn[i]);
				JcbxMap.put("note", jcb_note[i]);
				JcbxMap.put("sort", i);
				JcbxMap.put("per_id", jcb_id[i]);
				//教材其他情况编写
				jcbxList.add(JcbxMap);
			}
		}
		//作家科研情况
		String zjk_research_name[] = request.getParameterValues("zjk_research_name");
		String zjk_approval_unit[] = request.getParameterValues("zjk_approval_unit");
		String zjk_award[] = request.getParameterValues("zjk_award");
		String zjk_note[] = request.getParameterValues("zjk_note");
		String zjk_id[] = request.getParameterValues("zjk_id");
		for(int i=0;i<zjk_research_name.length;i++) { //遍历数组
			if(!zjk_research_name[i].equals("")){ //判断是否存在
				Map<String,Object> ZjkyqkMap = new HashMap<String,Object>();
				ZjkyqkMap.put("research_name", zjk_research_name[i]);
				ZjkyqkMap.put("approval_unit", zjk_approval_unit[i]);
				ZjkyqkMap.put("award", zjk_award[i]);
				ZjkyqkMap.put("note", zjk_note[i]);
				ZjkyqkMap.put("sort", i);
				ZjkyqkMap.put("per_id", zjk_id[i]);
				//作家科研情况
				zjkyList.add(ZjkyqkMap);
			}
		}
		//个人成就
		String gr_content = request.getParameter("gr_content");
		achievementMap.put("content", gr_content);
		achievementMap.put("grcj_id", request.getParameter("grcj_id"));
		//扩展信息
		String kz_content[] = request.getParameterValues("kz_content");
		String extension_id[] = request.getParameterValues("extension_id");
		if(extension_id!=null && extension_id.length>0){
			for(int i=0;i<extension_id.length;i++) { //遍历数组
				if(!extension_id[i].equals("")){ //判断是否存在
					Map<String,Object> ExtensionMap = new HashMap<String,Object>();
					ExtensionMap.put("extension_id", extension_id[i]);
					ExtensionMap.put("content", kz_content[i]);
					//扩展信息
					zjkzqkList.add(ExtensionMap);
				}
			}
		}
		//主编学术专著情况
		String zb_monograph_name[] = request.getParameterValues("zb_monograph_name");
		String zb_monograph_date[] = request.getParameterValues("zb_monograph_date");
		String zb_publisher[] = request.getParameterValues("zb_publisher");
		String zb_publish_date[] = request.getParameterValues("zb_publish_date");
		String zb_note[] = request.getParameterValues("zb_note");
		String is_self_paid[] = request.getParameterValues("is_self_paid");
		String zb_id[] = request.getParameterValues("zb_id");
		for(int i=0;i<zb_monograph_name.length;i++) { //遍历数组
			if(!zb_monograph_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("monograph_name", zb_monograph_name[i]);
                MonographMap.put("monograph_date", "".equals(zb_monograph_date[i]) ? null:zb_monograph_date[i]);
                MonographMap.put("is_self_paid", "".equals(request.getParameter(is_self_paid[i])) ? null:request.getParameter(is_self_paid[i]));
                MonographMap.put("publisher", zb_publisher[i]);
                //	MonographMap.put("publish_date", zb_publish_date[i]);
                MonographMap.put("publish_date", "".equals(zb_publish_date[i]) ? null:zb_publish_date[i]);
				MonographMap.put("note", zb_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", zb_id[i]);
				//主编学术
				monographList.add(MonographMap);
			}
		}
		//出版行业获奖情况表
		String pu_reward_name[] = request.getParameterValues("pu_reward_name");
		String pu_award_unit[] = request.getParameterValues("pu_award_unit");
		String pu_reward_date[] = request.getParameterValues("pu_reward_date");
		String pu_note[] = request.getParameterValues("pu_note");
		String pu_id[] = request.getParameterValues("pu_id");
		for(int i=0;i<pu_reward_name.length;i++) { //遍历数组
			if(!pu_reward_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("reward_name", pu_reward_name[i]);
				MonographMap.put("award_unit", pu_award_unit[i]);
				MonographMap.put("reward_date", "".equals(pu_reward_date[i]) ? null:pu_reward_date[i]);
				MonographMap.put("note", pu_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", pu_id[i]);
				publishList.add(MonographMap);
			}
		}
		//SCI论文投稿及影响因子情况表
		String sci_paper_name[] = request.getParameterValues("sci_paper_name");
		String sci_journal_name[] = request.getParameterValues("sci_journal_name");
		String sci_factor[] = request.getParameterValues("sci_factor");
		String sci_publish_date[] = request.getParameterValues("sci_publish_date");
		String sci_note[] = request.getParameterValues("sci_note");
		String sci_id[] = request.getParameterValues("sci_id");
		for(int i=0;i<sci_paper_name.length;i++) { //遍历数组
			if(!sci_paper_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("paper_name", sci_paper_name[i]);
				MonographMap.put("journal_name", sci_journal_name[i]);
				MonographMap.put("factor", sci_factor[i]);
				MonographMap.put("publish_date", "".equals(sci_publish_date[i]) ? null:sci_publish_date[i]);
				MonographMap.put("note", sci_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", sci_id[i]);
				sciList.add(MonographMap);
			}
		}
		//临床医学获奖情况表
		String cl_reward_name[] = request.getParameterValues("cl_reward_name");
		String cl_award_unit[] = request.getParameterValues("cl_award_unit");
		String cl_reward_date[] = request.getParameterValues("cl_reward_date");
		String cl_note[] = request.getParameterValues("cl_note");
		String cl_id[] = request.getParameterValues("cl_id");
		for(int i=0;i<cl_reward_name.length;i++) { //遍历数组
			if(!cl_reward_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("reward_name", cl_reward_name[i]);
				MonographMap.put("award_unit", request.getParameter( cl_award_unit[i]));
				MonographMap.put("reward_date", "".equals(cl_reward_date[i]) ? null:cl_reward_date[i]);
				MonographMap.put("note", cl_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", cl_id[i]);
				clinicalList.add(MonographMap);
			}
		}
		//学术荣誉授予情况表
		String ac_reward_name[] = request.getParameterValues("ac_reward_name");
		String ac_award_unit[] = request.getParameterValues("ac_award_unit");
		String ac_reward_date[] = request.getParameterValues("ac_reward_date");
		String ac_note[] = request.getParameterValues("ac_note");
		String ac_id[] = request.getParameterValues("ac_id");
		for(int i=0;i<ac_reward_name.length;i++) { //遍历数组
			if(!ac_reward_name[i].equals("")){ //判断是否存在
				Map<String,Object> MonographMap = new HashMap<String,Object>();
				MonographMap.put("reward_name", ac_reward_name[i]);
				MonographMap.put("award_unit", request.getParameter( ac_award_unit[i]));
				MonographMap.put("reward_date", "".equals(ac_reward_date[i]) ? null:ac_reward_date[i]);
				MonographMap.put("note", ac_note[i]);
				MonographMap.put("sort", i);
				MonographMap.put("per_id", ac_id[i]);
				acadeList.add(MonographMap);
			}
		}
		//人卫社教材编写情况表
		String pmph_material_name[] = request.getParameterValues("pmph_material_name");
		String pmph_rank[] = request.getParameterValues("pmph_rank");
		String pmph_position[] = request.getParameterValues("pmph_position");
		String pmph_publish_date[] = request.getParameterValues("pmph_publish_date");
		String pmph_isbn[] = request.getParameterValues("pmph_isbn");
		String pmph_is_digital_editor[] = request.getParameterValues("pmph_is_digital_editor");
		String pmph_note[] = request.getParameterValues("pmph_note");
		String pmph_id[] = request.getParameterValues("pmph_id");
		for(int i=0;i<pmph_material_name.length;i++) { //遍历数组
			if(!pmph_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbxMap = new HashMap<String,Object>();
				JcbxMap.put("material_name", pmph_material_name[i]);
				JcbxMap.put("rank", "".equals(pmph_rank[i]) ? null:pmph_rank[i]);
				JcbxMap.put("position", "".equals(pmph_position[i]) ? null:pmph_position[i]);
				JcbxMap.put("is_digital_editor", "".equals(request.getParameter(pmph_is_digital_editor[i])) ? null:request.getParameter(pmph_is_digital_editor[i]));
				JcbxMap.put("publish_date", "".equals(pmph_publish_date[i]) ? null:pmph_publish_date[i]);
				JcbxMap.put("isbn", pmph_isbn[i]);
				JcbxMap.put("note", pmph_note[i]);
				JcbxMap.put("per_id", pmph_id[i]);
				JcbxMap.put("sort", i);
				pmphList.add(JcbxMap);
			}
		}
		//参加人卫慕课、数字教材编写情况
		String mooc_content = request.getParameter("mooc_content");
		digitalMap.put("content", mooc_content);
		//编写内容意向表
		String intention_content = request.getParameter("intention_content");
		intentionlMap.put("content", intention_content);
        Map<String,Object> returnMap =  new HashMap<String,Object>();
		if(expertation_id == null || expertation_id.length() <= 0){//表示新增
            returnMap = this.etService.insertJcsbxx(perMap, stuList, workList, steaList, zjxsList, jcbjList, gjkcjsList, gjghjcList, jcbxList, zjkyList, zjkzqkList, achievementMap, monographList, publishList, sciList, clinicalList, acadeList, pmphList, digitalMap, intentionlMap,subjectList,contentList);
		}else{
            returnMap=this.etService.updateJcsbxx(perMap, stuList, workList, expertation_id, steaList, zjxsList, jcbjList, gjkcjsList, gjghjcList, jcbxList, zjkyList, zjkzqkList, achievementMap, monographList, publishList, sciList, clinicalList, acadeList, pmphList, digitalMap, intentionlMap,subjectList,contentList);
		}
		
		/*if(type.equals("1")){ //提交
			//TODO 教材申报提交 生成动态
			//Map<String, Object> materialMap = this.mdService.queryMaterialbyId(material_id);
			WriterUserTrendst wut = new WriterUserTrendst(userMap.get("id").toString(), 8, material_id);
			wut.setDetail("提交教材申报", "您申报的《"+materialMap.get("material_name").toString()+"》申报表已提交,请耐心等待 \\\""+returnMap.get("org_name").toString()+"\\\" 审核。", 0);
			personalService.saveUserTrendst(wut);//教材申报提交 生成动态 被覆盖两次了
		}*/
		

		return returnMap;
	}

	/**
	 * 查看申报详情
     *
	 * @param request
	 * @return
	 */
	@RequestMapping("showExpertation")
	public ModelAndView showMaterial(HttpServletRequest request,String... arrMaterial_id){
		ModelAndView mav = new ModelAndView("commuser/materialdec/showExpertation");
		//传参  user_id  material_id
        String declaration_id = request.getParameter("declaration_id");
        String material_id = request.getParameter("material_id");
        if(arrMaterial_id !=null && arrMaterial_id.length>0){
            declaration_id = arrMaterial_id[0];
        }
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("material_id", material_id);
		queryMap.put("declaration_id", declaration_id);
		Map<String, Object> logUser = getUserInfo();

		//1.作家申报表
		List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
		gezlList = this.etService.queryPerson(queryMap);
		Boolean isSelfLog = false;
		if (logUser.get("id").toString().equals(gezlList.get(0).get("user_id").toString())) {
			isSelfLog = true;
		}
		mav.addObject("isSelfLog", isSelfLog);
		if(declaration_id == null){
		queryMap.put("declaration_id", gezlList.get(0).get("id"));
		}else{
			queryMap.put("declaration_id", declaration_id);
		}
		if(material_id == null){
			material_id= gezlList.get(0).get("product_id").toString();
		}
		queryMap.put("product_id", material_id);
		queryMap.put("expert_type", gezlList.get(0).get("expert_type").toString());

		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		stuList=this.etService.queryStu(queryMap);
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		workList=this.etService.queryWork(queryMap);
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		zjxsList=this.etService.queryZjxs(queryMap);
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
		gjghjcList = this.etService.queryGjghjc(queryMap);
        //19.人卫社编写情况
        List<Map<String,Object>> rwsjcList = new ArrayList<Map<String,Object>>();
		rwsjcList=this.etService.rwsjcList(queryMap);
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		zjkzqkList = this.etService.queryZjkzbb(queryMap);
		List<Map<String,Object>> zjkzxxList = this.etService.queryZjkzxxById(material_id);
		//14.主编学术专著情况表
		List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
		monographList = this.etService.queryMonograph(queryMap);
		//填充
		mav.addObject("queryMap", queryMap);
		mav.addObject("gezlList", gezlList.get(0));
		mav.addObject("stuList", stuList);
		mav.addObject("workList", workList);
		mav.addObject("rwsjcList", rwsjcList);
		mav.addObject("gjghjcList", gjghjcList);
		mav.addObject("zjxsList", zjxsList);
		mav.addObject("zjkzqkList", zjkzqkList);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("monographList", monographList);
		return mav;
	}

	/**
	 * 跳转到暂存页面
     *
	 * @param request
	 * @return
	 */
	@RequestMapping("toExpertationZc")
	@ResponseBody
	public ModelAndView toMaterialZc(HttpServletRequest request,
			String... arrMaterial_id){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toExpertationZc");
		//传参   declaration_id
		Map<String,Object> userMap =  this.getUserInfo();
		String declaration_id = request.getParameter("declaration_id");
        if(arrMaterial_id !=null && arrMaterial_id.length>0){
			declaration_id = arrMaterial_id[0];
        }
		String user_id = userMap.get("id").toString();
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("user_id", user_id);
		queryMap.put("declaration_id", declaration_id);
		//学科
		List<Map<String,Object>> subjectList = this.etService.selectSubject(queryMap);
		List<Map<String,Object>> contentList = this.etService.selectContent(queryMap);

		//1.作家申报信息表
		List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
		gezlList = this.etService.queryPerson(queryMap);
        for (Map.Entry<String, Object> entry : gezlList.get(0).entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            if(value.equals("-")){
                gezlList.get(0).put(key,"");
            }
        }
		String product_id = gezlList.get(0).get("product_id").toString();
		queryMap.put("product_id", product_id);
		queryMap.put("expert_type", gezlList.get(0).get("expert_type").toString());

		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		stuList=this.etService.queryStu(queryMap);
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		workList=this.etService.queryWork(queryMap);
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		zjxsList=this.etService.queryZjxs(queryMap);
		//9.作家主编国家级规划教材情况表
		List<Map<String,Object>> gjghjcList = new ArrayList<Map<String,Object>>();
		gjghjcList = this.etService.queryGjghjc(queryMap);
        //19.人卫社编写情况
		List<Map<String,Object>> rwsjcList = new ArrayList<Map<String,Object>>();
		rwsjcList=this.etService.rwsjcList(queryMap);
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		zjkzqkList = this.etService.queryZjkzbb(queryMap);
		List<Map<String,Object>> zjkzxxList = this.etService.queryZjkzxxById(product_id);
		//14.主编学术专著情况表
		List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
		monographList = this.etService.queryMonograph(queryMap);

		//填充
		mav.addObject("subjectList", subjectList);
		mav.addObject("contentList", contentList);
		mav.addObject("gezlList", gezlList.get(0));
		mav.addObject("stuList", stuList);
		mav.addObject("workList", workList);
		mav.addObject("rwsjcList", rwsjcList);
		mav.addObject("gjghjcList", gjghjcList);
		mav.addObject("zjxsList", zjxsList);
		mav.addObject("zjkzqkList", zjkzqkList);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("monographList", monographList);
		mav.addObject("materialMap", queryMap);
		mav.addObject("userMap", userMap);
        mav.addObject("return_cause", MapUtils.getString(gezlList.get(0), "return_cause"));

		return mav;
	}
	
	/**
	 * 查询学科分类
	 */
	@RequestMapping("querySubject")
	public ModelAndView querySubject(HttpServletRequest request,
				HttpServletResponse response){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toSubjectList");
		//机构信息
		String material_id = request.getParameter("material_id");
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  typename = request.getParameter("typename");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		//分页查询
		int currentPage = 0;
		int pageSize = 10;

		if(null!=currentPageStr&&!currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(null!=pageSizeStr&&!pageSizeStr.equals("")){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<>(currentPage,pageSize);

		paraMap.put("material_id", material_id);
		paraMap.put("endPage", pageSize);
		paraMap.put("currentPage", currentPage);
		if(typename!=null && !typename.equals("")){
			try {
				typename = URLDecoder.decode(typename,"UTF-8");
				paraMap.put("typename", "%"+typename+"%");
				paraMap.put("type_name", typename);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageParameter.setParameter(paraMap);
		PageResult<Map<String,Object>> pageResult = this.etService.selectSubjectList(pageParameter);
		mav.addObject("pageResult", pageResult);
		mav.addObject("paraMap", paraMap);
		return mav;
	}

	/**
	 * 查询内容分类
	 */
	@RequestMapping("queryContent")
	public ModelAndView queryContent(HttpServletRequest request,
				HttpServletResponse response){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toContentList");
		//机构信息
		String material_id = request.getParameter("material_id");
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  namepath = request.getParameter("namepath");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		//分页查询
		int currentPage = 0;
		int pageSize = 10;

		if(null!=currentPageStr&&!currentPageStr.equals("")){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(null!=pageSizeStr&&!pageSizeStr.equals("")){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		PageParameter<Map<String,Object>> pageParameter = new PageParameter<>(currentPage,pageSize);

		paraMap.put("material_id", material_id);
		paraMap.put("endPage", pageSize);
		paraMap.put("currentPage", currentPage);
		if(namepath!=null && !namepath.equals("")){
			try {
				namepath = URLDecoder.decode(namepath,"UTF-8");
				paraMap.put("name_path", "%"+namepath+"%");
				paraMap.put("namepath", namepath);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageParameter.setParameter(paraMap);
		PageResult<Map<String,Object>> pageResult = this.etService.selectContentList(pageParameter);
		mav.addObject("pageResult", pageResult);
		mav.addObject("paraMap", paraMap);
		return mav;
	}

    //跳转到个人申报列表页面
	@RequestMapping(value="/declare")
	public ModelAndView topz(){
		ModelAndView modelAndView=new ModelAndView();
		Map<String, Object> user=getUserInfo();
		List<Map<String,Object>> list=etService.queryExpertation(user.get("id").toString());
		modelAndView.addObject("list",list);
		modelAndView.setViewName("commuser/personalcenter/declare");
		return modelAndView;
	}

    //重定向方法
	@RequestMapping("lookforward")
	public ModelAndView lookforward(HttpServletRequest request){
            ModelAndView modelAndView=new ModelAndView();
            String expert_type=request.getParameter("expert_type");
            Map<String, Object> user=getUserInfo();
            Map<String, Object> map=etService.queryExpertationDetail(user.get("id").toString(),expert_type);
            if(map==null){
                modelAndView=this.toMaterialAdd(request,expert_type);
            }else if(map.get("online_progress").toString().equals("0") ||map.get("online_progress").toString().equals("2")){
                //审核状态为未提交和被退回，跳转至编辑界面
                modelAndView=this.toMaterialZc(request,map.get("id").toString());
            }else if(map.get("online_progress").toString().equals("1") ||map.get("online_progress").toString().equals("3") ){
                //审核状态为代审核和审核通过，跳转至查看界面
                modelAndView=this.showMaterial(request,map.get("id").toString());
            }
            return modelAndView;
	}

}
