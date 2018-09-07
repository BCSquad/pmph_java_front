package com.bc.pmpheep.back.commuser.materialdec.controller;

import com.alibaba.fastjson.JSON;
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
	public ModelAndView toMaterialAdd(HttpServletRequest request,String... arrProduct_id){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toExpertationAdd");

		//获取参数
		String product_id = request.getParameter("product_id");
		if(arrProduct_id !=null && arrProduct_id.length>0){
			product_id = arrProduct_id[0];
		}
		Map<String,Object> productMap = new HashMap<String,Object>();
		productMap = this.etService.queryProduct(product_id);

		//3.作家学习经历表
		List<Map<String,Object>> perstuList = new ArrayList<Map<String,Object>>();
		//4.作家工作经历表
		List<Map<String,Object>> perworkList = new ArrayList<Map<String,Object>>();
		//6.作家兼职学术表
		List<Map<String,Object>> perzjxsList = new ArrayList<Map<String,Object>>();
		//14.主编学术专著情况表
		List<Map<String,Object>> permonographList = new ArrayList<Map<String,Object>>();
		//19.人卫社教材编写情况表
		List<Map<String,Object>> perpmphList = new ArrayList<Map<String,Object>>();
		//20.主编或参编图书情况
		List<Map<String,Object>> pereditorList = new ArrayList<Map<String,Object>>();
		//16.文章发表情况
		List<Map<String,Object>> wzfbqkList = new ArrayList<Map<String,Object>>();
		//16.本专业获奖情况
		List<Map<String,Object>> bzyhjqkList = new ArrayList<Map<String,Object>>();

		Map<String,Object> queryMap = new HashMap<String,Object>();

		queryMap.put("expert_type",productMap.get("product_type"));
		queryMap.put("product_id",productMap.get("id"));

		//查询个人信息库信息
		Map<String,Object> userMap=new HashMap<>();
		Map<String,Object> userinfo =  this.getUserInfo();
		userMap =  this.mdService.queryUserInfo(MapUtils.getString(userinfo,"id",""));
//		userMap = JSON.parseObject(JSON.toJSONString(userMap).replaceAll("-",""),java.util.HashMap.class);
		//个人信息中带有横杠的去掉
		if(userMap!=null){
			Set set = userMap.keySet();
			Iterator it = set.iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value=userMap.get(key).toString();
				if(value!=null&&value.equals("-")){
					userMap.put(key,"");
				}
			}
		}
		queryMap.put("user_id",userMap.get("id"));
		//个人资料库信息
		perstuList = this.perService.queryPerStu(queryMap);
        perworkList= this.perService.queryPerWork(queryMap);
        perzjxsList=this.perService.queryPerZjxs(queryMap);
        permonographList=this.perService.queryPerMonograph(queryMap);
        perpmphList=this.perService.rwsjcPerList(queryMap);
		pereditorList=this.perService.queryPerEditor(queryMap);
		wzfbqkList = this.etService.queryWzfbqk(queryMap);
		bzyhjqkList= this.etService.queryBzyhjqk(queryMap);

		//作家扩展信息
		List<Map<String,Object>> zjkzxxList = this.etService.queryZjkzxxById(productMap.get("id").toString());

		mav.addObject("userMap", userMap);
		mav.addObject("queryMap", queryMap);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("perstuList",perstuList);
		mav.addObject("perworkList",perworkList);
		mav.addObject("perzjxsList",perzjxsList);
		mav.addObject("permonographList",permonographList);
		mav.addObject("perpmphList",perpmphList);
		mav.addObject("pereditorList",pereditorList);
		mav.addObject("wzfbqkList",wzfbqkList);
		mav.addObject("bzyhjqkList",bzyhjqkList);
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
		String expert_type = request.getParameter("expert_type");
		String id = request.getParameter("product_id");
		Map<String,Object> productMap = new HashMap<String,Object>();
		productMap = this.etService.queryProductbyId(id,expert_type);
		return productMap;
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
		String expert_type = request.getParameter("expert_type");
		String expertation_id = request.getParameter("expertation_id");
		String user_id = request.getParameter("user_id"); //系统用户(暂存人)
		String type = request.getParameter("type"); //类型
		String sjump = request.getParameter("sjump"); //页面来源
		String product_id = request.getParameter("product_id"); //页面来源

		Map<String,Object> productMap =  new HashMap<String,Object>();
		//查看灵床信息
		productMap = this.etService.queryProduct(product_id);
		//求出信息集合
		//1.作家申报表
		Map<String,Object> perMap = new HashMap<String,Object>();
		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		//14.主编学术专著情况表
		List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
		//19.人卫社教材编写情况表
		List<Map<String,Object>> pmphList = new ArrayList<Map<String,Object>>();
		//19.主编或参编图书情况
		List<Map<String,Object>> editorList = new ArrayList<Map<String,Object>>();
		//20.文章发表情况
		List<Map<String,Object>> wzfbqkList = new ArrayList<Map<String,Object>>();
		//21.本专业获奖情况
		List<Map<String,Object>> bzyhjqkList = new ArrayList<Map<String,Object>>();

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
        materialMap = this.etService.queryMaterialbyId(expert_type);

        perMap.put("materialName", MapUtils.getString(materialMap, "material_name"));
		perMap.put("realname", request.getParameter("realname"));
		perMap.put("user_id", user_id);
		perMap.put("type", type);
		perMap.put("is_teacher", userMap.get("is_teacher"));
		perMap.put("product_id", productMap.get("id").toString());
		perMap.put("expert_type", expert_type);
		perMap.put("sex","".equals(request.getParameter("sex")) ? null:request.getParameter("sex"));
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
		perMap.put("degree", "".equals(request.getParameter("degree")) ? null:request.getParameter("degree"));
		perMap.put("rank","2");
		perMap.put("education", request.getParameter("education"));
		perMap.put("expertise", request.getParameter("expertise"));
		perMap.put("gmt_create", date);
		perMap.put("banknumber", "".equals(request.getParameter("banknumber")) ? null:request.getParameter("banknumber"));
		perMap.put("bankaddress", request.getParameter("bankaddress"));
		perMap.put("remark", request.getParameter("remark"));
		perMap.put("unit_advise", request.getParameter("syllabus_id"));
		perMap.put("syllabus_name", request.getParameter("syllabus_name"));
		perMap.put("remark", request.getParameter("remark"));
		String s=request.getParameter("sbdw_id");
		perMap.put("org_id","".equals(request.getParameter("sbdw_id")) ? null:request.getParameter("sbdw_id"));

		
		//获取学科及内容分类id
		String subjectIds[] = request.getParameterValues("subjectId");
        String contentIds[] = request.getParameterValues("contentId");
		String sbzyIds[] = request.getParameterValues("sbzyId");
		List<Map<String,Object>> subjectList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> contentList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> sbzyList = new ArrayList<Map<String,Object>>();
		if(subjectIds!=null&&subjectIds.length>0){
			for(int i=0;i<subjectIds.length;i++) { //遍历数组
				Map<String,Object> subjectMap = new HashMap<String,Object>();
				subjectMap.put("subjectId", subjectIds[i]);
				subjectList.add(subjectMap);
			}
		}
		if(contentIds!=null&&contentIds.length>0){
			for(int i=0;i<contentIds.length;i++) { //遍历数组
				Map<String,Object> contentMap = new HashMap<String,Object>();
				contentMap.put("contentId", contentIds[i]);
				contentList.add(contentMap);
			}
		}
		if(sbzyIds!=null&&sbzyIds.length>0){
			for(int i=0;i<sbzyIds.length;i++) { //遍历数组
				Map<String,Object> sbzyMap = new HashMap<String,Object>();
				sbzyMap.put("sbzyId", sbzyIds[i]);
				sbzyList.add(sbzyMap);
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
		//主编或参编图书情况
		String zbts_material_name[] = request.getParameterValues("zbts_material_name");
		String zbts_note[] = request.getParameterValues("zbts_note");
		String zbts_publish_date[] = request.getParameterValues("zbts_publish_date");
		String zbts_publisher[] = request.getParameterValues("zbts_publisher");
		String zbts_id[] = request.getParameterValues("zbts_id");
		for(int i=0;i<zbts_material_name.length;i++) { //遍历数组
			if(!zbts_material_name[i].equals("")){ //判断是否存在
				Map<String,Object> JcbjMap = new HashMap<String,Object>();
				JcbjMap.put("material_name", zbts_material_name[i]);
				JcbjMap.put("note", zbts_note[i]);
				JcbjMap.put("publisher", zbts_publisher[i]);
				JcbjMap.put("publish_date", "".equals(zbts_publish_date[i]) ? null:zbts_publish_date[i]);

				JcbjMap.put("sort", i);
				JcbjMap.put("per_id", zbts_id[i]);
				//作家上套教材参编
				editorList.add(JcbjMap);
			}
		}
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

		//文章发表情况
		String wzfb_name[] = request.getParameterValues("wzfb_name");
		String wzfb_qkmc[] = request.getParameterValues("wzfb_qkmc");
		String wzfb_njq[] = request.getParameterValues("wzfb_njq");
		String wzfb_qklb[] = request.getParameterValues("wzfb_qklb");
		String wzfb_note[] = request.getParameterValues("wzfb_note");
        String wzfbxq_id[] = request.getParameterValues("wzfbxq_id");
		for(int i=0;i<wzfb_name.length;i++){//遍历数组
			if(!wzfb_name[i].equals("")){ //判断是否存在
				Map<String,Object> WzfbMap = new HashMap<String,Object>();
				WzfbMap.put("title", wzfb_name[i]);
				WzfbMap.put("periodical_title", "".equals(wzfb_qkmc[i]) ? null:wzfb_qkmc[i]);
				WzfbMap.put("year_volume_period", "".equals(wzfb_njq[i]) ? null:wzfb_njq[i]);
				WzfbMap.put("periodical_level", "".equals(wzfb_qklb[i]) ? null:wzfb_qklb[i]);
				WzfbMap.put("note", "".equals(wzfb_note[i]) ? null:wzfb_note[i]);
				WzfbMap.put("sort", i);
				WzfbMap.put("per_id", wzfbxq_id[i]);
				wzfbqkList.add(WzfbMap);
			}
		}

		//本专业获奖情况
		String hjqk_name[] = request.getParameterValues("hjqk_name");
		String hjqk_jb[] = request.getParameterValues("hjqk_jb");
		String hjqk_note[] = request.getParameterValues("hjqk_note");
        String bzyhqqk_id[] = request.getParameterValues("bzyhqqk_id");
		for(int i=0;i<hjqk_name.length;i++){//遍历数组
			if(!hjqk_name[i].equals("")){ //判断是否存在
				Map<String,Object> HjqkMap = new HashMap<String,Object>();
				HjqkMap.put("title", hjqk_name[i]);
				HjqkMap.put("rank", "".equals(hjqk_jb[i]) ? null:hjqk_jb[i]);
				HjqkMap.put("note", "".equals(hjqk_note[i]) ? null:hjqk_note[i]);
				HjqkMap.put("sort", i);
				HjqkMap.put("per_id", bzyhqqk_id[i]);
				bzyhjqkList.add(HjqkMap);
			}
		}

        Map<String,Object> returnMap =  new HashMap<String,Object>();
		if(expertation_id == null || expertation_id.length() <= 0){//表示新增
            returnMap = this.etService.insertJcsbxx(perMap, stuList, workList, zjxsList, zjkzqkList, monographList, pmphList,subjectList,contentList,sbzyList,editorList,wzfbqkList,bzyhjqkList);
		}else{
            returnMap=this.etService.updateJcsbxx(perMap, stuList, workList, zjxsList, zjkzqkList, monographList, pmphList,subjectList,contentList,sbzyList,editorList,wzfbqkList,bzyhjqkList,expertation_id);
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
        String state=request.getParameter("state");
        String userType = request.getParameter("userType");
        String online_progress=request.getParameter("online_progress");
        if(state!=null){
			mav.addObject("state",state);
		}
		if(online_progress!=null){
			mav.addObject("online_progress",online_progress);
		}
        if(userType!=null){
        	mav.addObject("userType",userType);
		}
        if(arrMaterial_id !=null && arrMaterial_id.length>0){
            declaration_id = arrMaterial_id[0];
        }
		Map<String,Object> queryMap = new HashMap<String,Object>();
		queryMap.put("declaration_id", declaration_id);
		Map<String, Object> logUser = getUserInfo();

		//1.作家申报表
		List<Map<String,Object>> gezlList = new ArrayList<Map<String,Object>>();
		gezlList = this.etService.queryPerson(queryMap);
		Map<String,Object> productMap =  new HashMap<String,Object>();
		productMap = this.etService.queryProduct(gezlList.get(0).get("product_id").toString());
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
		queryMap.put("product_id", gezlList.get(0).get("product_id").toString());
		queryMap.put("expert_type", gezlList.get(0).get("expert_type").toString());

		//学科
		List<Map<String,Object>> subjectList = this.etService.selectSubject(queryMap);
		List<Map<String,Object>> contentList = this.etService.selectContent(queryMap);
		List<Map<String,Object>> sbzyList = this.etService.selectSbzy(queryMap);
		//3.作家学习经历表
		List<Map<String,Object>> stuList = new ArrayList<Map<String,Object>>();
		stuList=this.etService.queryStu(queryMap);
		//4.作家工作经历表
		List<Map<String,Object>> workList = new ArrayList<Map<String,Object>>();
		workList=this.etService.queryWork(queryMap);
		//6.作家兼职学术表
		List<Map<String,Object>> zjxsList = new ArrayList<Map<String,Object>>();
		zjxsList=this.etService.queryZjxs(queryMap);
        //19.人卫社编写情况
        List<Map<String,Object>> rwsjcList = new ArrayList<Map<String,Object>>();
		rwsjcList=this.etService.rwsjcList(queryMap);
		//12.作家扩展项填报表
		List<Map<String,Object>> zjkzqkList = new ArrayList<Map<String,Object>>();
		zjkzqkList = this.etService.queryZjkzbb(queryMap);
		List<Map<String,Object>> zjkzxxList = this.etService.queryZjkzxxById(productMap.get("id").toString());
		//14.主编学术专著情况表
		List<Map<String,Object>> monographList = new ArrayList<Map<String,Object>>();
		monographList = this.etService.queryMonograph(queryMap);
		//6.主编或参编图书情况
		List<Map<String,Object>> editorList = new ArrayList<Map<String,Object>>();
		editorList=this.etService.queryEditor(queryMap);

		//16.文章发表情况（须第一作者，与本专业相关）
		List<Map<String,Object>> wzfbqkList = new ArrayList<Map<String,Object>>();
		wzfbqkList = this.etService.queryWzfbqk(queryMap);
		//17.本专业获奖情况
		List<Map<String,Object>> bzyhjqkList = new ArrayList<Map<String,Object>>();
		bzyhjqkList = this.etService.queryBzyhjqk(queryMap);

		//所选申报单位
		Map<String,Object> org =etService.queryOrgById(MapUtils.getString(gezlList.get(0),"org_id"));

		if(MapUtils.getString(gezlList.get(0),"expert_type").equals("1")){
			mav.addObject("title","人卫临床助手专家申报表");
		}else if(MapUtils.getString(gezlList.get(0),"expert_type").equals("2")){
			mav.addObject("title","人卫用药助手专家申报表");
		}else if(MapUtils.getString(gezlList.get(0),"expert_type").equals("3")){
			mav.addObject("title","人卫中医助手专家申报表");
		}
		//填充
		mav.addObject("queryMap", queryMap);
		mav.addObject("gezlList", gezlList.get(0));
		mav.addObject("stuList", stuList);
		mav.addObject("workList", workList);
		mav.addObject("editorList", editorList);
		mav.addObject("rwsjcList", rwsjcList);
		mav.addObject("zjxsList", zjxsList);
		mav.addObject("zjkzqkList", zjkzqkList);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("subjectList", subjectList);
		mav.addObject("contentList", contentList);
		mav.addObject("sbzyList",sbzyList);
		mav.addObject("wzfbqkList", wzfbqkList);
		mav.addObject("bzyhjqkList", bzyhjqkList);

		mav.addObject("declaration_id", declaration_id);
		mav.addObject("monographList", monographList);
		mav.addObject("org", org);
		return mav;
	}

	private void queryOrgById(String string) {
		// TODO Auto-generated method stub
		
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
		List<Map<String,Object>> sbzyList = this.etService.selectSbzy(queryMap);

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
		//6.主编或参编图书情况
		List<Map<String,Object>> editorList = new ArrayList<Map<String,Object>>();
		editorList=this.etService.queryEditor(queryMap);
		//文章发表详情
		List<Map<String,Object>> wzfbqkList = new ArrayList<Map<String,Object>>();
		wzfbqkList=this.etService.queryWzfbqk(queryMap);
		//本专业获奖情况
		List<Map<String,Object>> bzyhjqkList = new ArrayList<Map<String,Object>>();
		bzyhjqkList=this.etService.queryBzyhjqk(queryMap);
		//所选申报单位
		Map<String,Object> org = new HashMap<String, Object>();
		if(gezlList.get(0).get("org_id")==null){
			
		}else{
			org =etService.queryOrgById(gezlList.get(0).get("org_id").toString());
		}
		

		//填充
		mav.addObject("wzfbqkList",wzfbqkList);
		mav.addObject("bzyhjqkList",bzyhjqkList);
		mav.addObject("subjectList", subjectList);
		mav.addObject("contentList", contentList);
		mav.addObject("sbzyList",sbzyList);
		mav.addObject("gezlList", gezlList.get(0));
		mav.addObject("stuList", stuList);
		mav.addObject("editorList", editorList);
		mav.addObject("workList", workList);
		mav.addObject("rwsjcList", rwsjcList);
		mav.addObject("zjxsList", zjxsList);
		mav.addObject("zjkzqkList", zjkzqkList);
		mav.addObject("zjkzxxList", zjkzxxList);
		mav.addObject("monographList", monographList);
		mav.addObject("materialMap", queryMap);
		mav.addObject("userMap", userMap);
        mav.addObject("return_cause", MapUtils.getString(gezlList.get(0), "return_cause"));
        mav.addObject("org", org);

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
		String product_type = request.getParameter("product_type");
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  typename = request.getParameter("typename");
		String chooseId = request.getParameter("chooseId"); // 已选id 格式如 66,65
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

		paraMap.put("product_type", product_type);
		paraMap.put("chooseId", chooseId);
		paraMap.put("endPage", pageSize);
		paraMap.put("currentPage", currentPage);
		if(typename!=null && !typename.equals("")){
			try {
				typename = URLDecoder.decode(typename,"UTF-8");
				paraMap.put("type_name", "%"+typename.trim()+"%");
				paraMap.put("typename", typename);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageParameter.setParameter(paraMap);
		PageResult<Map<String,Object>> pageResult = this.etService.selectSubjectList(pageParameter);
		mav.addObject("chooseId", chooseId);
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
		String product_type = request.getParameter("product_type");
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  namepath = request.getParameter("namepath");
		Map<String,Object> paraMap = new HashMap<String,Object>();
		String chooseId = request.getParameter("chooseId"); // 已选id 格式如 66,65
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

		paraMap.put("product_type", product_type);
		paraMap.put("endPage", pageSize);
		paraMap.put("currentPage", currentPage);
		paraMap.put("chooseId", chooseId);
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
		mav.addObject("chooseId", chooseId);
		mav.addObject("pageResult", pageResult);
		mav.addObject("paraMap", paraMap);
		return mav;
	}

	//查询申报专业
	@RequestMapping("toSearchZy")
	public ModelAndView toSearchZy(HttpServletRequest request,
								   HttpServletResponse response){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toSbzyList");
		//机构信息
		String  product_type = request.getParameter("product_type");
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  namepath = request.getParameter("namepath");
		String chooseId = request.getParameter("chooseId"); // 已选id 格式如 66,65
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

		paraMap.put("product_type", product_type);
		paraMap.put("endPage", pageSize);
		paraMap.put("currentPage", currentPage);
		paraMap.put("chooseId", chooseId);
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
		PageResult<Map<String,Object>> pageResult = this.etService.querySbzyList(pageParameter);

		mav.addObject("pageResult", pageResult);
		mav.addObject("productOrMaterial", "product");
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
            String product_id=request.getParameter("product_id");
            Map<String, Object> user=getUserInfo();
            Map<String, Object> map=etService.queryExpertationDetail(user.get("id").toString(),product_id);
            if(map==null){
                modelAndView=this.toMaterialAdd(request,product_id);
            }else if(map.get("online_progress").toString().equals("0")  //未提交
            		||map.get("online_progress").toString().equals("2") //被申报单位退回
            		||map.get("online_progress").toString().equals("5") //被出版社退回
            		){
                //审核状态为未提交和被退回，跳转至编辑界面
                modelAndView=this.toMaterialZc(request,map.get("id").toString());
            }else if(map.get("online_progress").toString().equals("1") //提交待审
            		||map.get("online_progress").toString().equals("3") //申报单位通过
            		||map.get("online_progress").toString().equals("4") //出版社退回申报单位
            		){
                //审核状态为代审核和审核通过，跳转至查看界面
                modelAndView=this.showMaterial(request,map.get("id").toString());
            }
            return modelAndView;
	}

	//个人中心跳转链接
	@RequestMapping("toPersondetail")
	@ResponseBody
	public String toPersondetail(HttpServletRequest request){
		ModelAndView modelAndView=new ModelAndView();
		String product_id=request.getParameter("product_id");
		Map<String, Object> user=getUserInfo();
		Map<String, Object> map=etService.queryExpertationDetail(user.get("id").toString(),product_id);
		String returncode="";
		if(map==null){
			returncode="no";
		}else{
			returncode="yes";
		}
		return returncode;
	}
	
	
	//申报审核通过
	@RequestMapping("doExpertationAuditPass")
	@ResponseBody
	public Map<String,Object> doExpertationAuditPass(HttpServletRequest request,
			HttpServletResponse response){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		String expertation_id = request.getParameter("expertation_id");
		String online_progress = request.getParameter("online_progress");  //类型
		String return_cause = request.getParameter("return_cause");
		String unit_advise = request.getParameter("unit_advise");   //判断是否有附件上传
        String syllabus_name = request.getParameter("syllabus_name");   //判断是否有附件上传
		String writer_id = request.getParameter("user_id");  //作家用户Id
		String unit_advise_online = request.getParameter("unit_advise_online");
		
		Map<String,Object> userMap =  this.getUserInfo();
		String user_id = userMap.get("id").toString();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		String msg = "";
		
		paramMap.put("expertation_id", expertation_id);
		paramMap.put("online_progress", online_progress);
		paramMap.put("auth_user_id", user_id);
		paramMap.put("auth_date", date);
		paramMap.put("writer_id", writer_id);
		paramMap.put("return_cause", return_cause);
		paramMap.put("unit_advise_online", unit_advise_online);
		if(unit_advise!=null){
			paramMap.put("unit_advise", unit_advise);
		}else{
			paramMap.put("unit_advise", "");
		}
        if(syllabus_name!=null){
            paramMap.put("syllabus_name", syllabus_name);
        }else{
            paramMap.put("syllabus_name", "");
        }

		int count = this.etService.updateExpertationPass(paramMap);
		if(count>0){
			msg = "OK";
		}
		resultMap.put("msg", msg);
		return resultMap;
	}
	
	//机构信息选择
	@RequestMapping("toSearchOrg")
	public ModelAndView toSearchOrg(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mav = new ModelAndView("commuser/materialdec/toOrgList");
		//机构信息
		String product_id = request.getParameter("product_id");
		String  currentPageStr = (String) request.getParameter("currentPage");
		String  pageSizeStr = request.getParameter("pageSize");
		String  orgname = request.getParameter("orgname");
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
		
		paraMap.put("product_id", product_id);
		paraMap.put("endPage", pageSize);
		paraMap.put("currentPage", currentPage);
		if(orgname!=null && !orgname.equals("")){
			try {
				orgname = URLDecoder.decode(orgname,"UTF-8");
				paraMap.put("org_name", "%"+orgname+"%");
				paraMap.put("orgname", orgname);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		pageParameter.setParameter(paraMap);
		PageResult<Map<String,Object>> pageResult = this.etService.selectOrgList(pageParameter);
		mav.addObject("pageResult", pageResult);
		mav.addObject("productOrMaterial", "product");
		mav.addObject("paraMap", paraMap);
		return mav;
	}



}
