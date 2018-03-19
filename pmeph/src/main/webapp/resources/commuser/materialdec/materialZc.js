//定义一个全局变量
var jsonStr = "";
jsonStr = "{\"id\":\"realname\",\"content\":\"姓名不能为空\"},{\"id\":\"birthday\",\"content\":\"出生日期不能为空\"}," +
	"{\"id\":\"experience\",\"content\":\"教龄不能为空\"},{\"id\":\"org_name\",\"content\":\"工作单位不能为空\"},{\"id\":\"position\",\"content\":\"职务不能为空\"},"+
	"{\"id\":\"zc\",\"content\":\"职称不能为空\"},{\"id\":\"address\",\"content\":\"地址不能为空\"},{\"id\":\"email\",\"content\":\"邮箱不能为空\"},"+
	"{\"id\":\"handphone\",\"content\":\"手机号码不能为空\"},{\"id\":\"zjlx\",\"content\":\"证件类型不能为空\"},{\"id\":\"idcard\",\"content\":\"证件号码不能为空\"},{\"id\":\"sbdw_name\",\"content\":\"申报单位不能为空\"},";

$(function () {
	var id = $("#material_id").val();
	queryMaterialMap(id);  //执行查询方法
	//图书选择
	var sjxz =document.getElementsByName("sjxz");
	for (var i = 0, j = sjxz.length; i < j; i++){
		upload(i+1); //附件上传
		$('#'+sjxz[i].value).selectlist({
	        width: 200,
	        height: 30,
	        optionHeight: 30
	    });
	}
	$('#pmph_rank').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });
	$('#pmph_position').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });
	$('#jcb_rank').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });
	$('#jcb_position').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });
	
	$('.select-input').selectlist({
		zIndex: 10,
		width: 192,
		height: 30,
		optionHeight: 30
	});
    $('.book').selectlist({
        zIndex: 10,
        width: 200,
        height: 30,
        optionHeight: 30
    });
    $('#zclx').selectlist({
    	width: 192,
    	height: 30,
    	optionHeight: 30
    });
    $('#degree').selectlist({
    	width: 192,
    	height: 30,
    	optionHeight: 30
    });
    //人卫社教材编写-级别
	selectOption("pmph_rank_sl");
	//人卫社教材编写-职务
	selectOption("pmph_sl");
	//其他社教材-级别
	selectOption("jcb_rank_sl");
	//其他社教材-职务
	selectOption("jcjb_sl");
	
	if ($("#return_cause_hidden").val().length>0) {
		
		$("#return_cause_div").fadeIn(800);
	
	}
});

//下拉框格式优化
function selectOption(name){
	var els =document.getElementsByName(name);
	for (var i = 0, j = els.length; i < j; i++){
		$('#'+els[i].value).selectlist({
			width: 110,
			height: 30,
			optionHeight: 30
		});
	}
}

//附件上传方法
function upload(id){
	$("#scjxdg_"+id).uploadFile({
	    start: function () {
	        console.log("开始上传。。。");
	    },
	    done: function (filename, fileid) {
	    	$("#fjxq_"+id).css("display","none");
	    	$("#fileNameDiv_"+id).empty(); //清楚内容
	    	$("#fileNameDiv_"+id).append("<span><a href='javascript:' class='filename'  onclick='downLoadProxy("+fileid+")'>"+filename+"</a></span>");
	    	$("#fileNameDiv_"+id).css("display","inline");
	    	$("#syllabus_id_"+id).val(fileid);
	    	$("#syllabus_name_"+id).val(filename);
	        console.log("上传完成：name " + filename + " fileid " + fileid);
	    },
	    valid:function(file){
	    	if(file.size/1024/1024>=100){ //判断文件上传大小
	    		window.message.warning("不得上传100M以上文件!");
	    		return false;
	    	}
	    	return true;
	    }	
	});
}

//页面组合方法
function queryMaterialMap(id){
	$.ajax({
		type: "POST",
		url:contextpath+'material/queryMaterialMap.action',
		data:{material_id:id},// 你的formid
		dataType:"json",
	    success: function(json) {
	    	chooseModel(json);
	    }
	});
}

//模块显示与隐藏判断
function chooseModel(data){
    //学习经历
    if(data.is_edu_exp_used == "1"){
        $("#zyxxjl").css("display","block");
    }
    //学习经历必填
    if(data.is_edu_exp_required == "1"){
        $("#zyxxjl_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"xx_kssj\",\"content\":\"学习经历起止时间必填\"},{\"id\":\"xx_major\",\"content\":\"所学专业不能为空\"},{\"id\":\"xx_degree\",\"content\":\"学历不能为空\"},";
    }else{
        $("#zyxxjl_xt").css("display","inline");
    }
    //工作经历
    if(data.is_work_exp_used == "1"){
        $("#gzjl").css("display","block");
    }
    //工作经历必填
    if(data.is_work_exp_required == "1"){
        $("#gzjl_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"gz_kssj\",\"content\":\"工作经历必填\"},{\"id\":\"gz_jssj\",\"content\":\"工作起止时间不能为空\"},{\"id\":\"gz_org_name\",\"content\":\"工作单位不能为空\"},{\"id\":\"gz_position\",\"content\":\"工作职位不能为空\"},";
    }else{
        $("#gzjl_xt").css("display","inline");
    }
    //教学经历
    if(data.is_teach_exp_used == "1"){
        $("#jxjl").css("display","block");
    }
    //教学经历必填
    if(data.is_teach_exp_required == "1"){
        $("#jxjl_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"jx_kssj\",\"content\":\"教学经历必填\"},{\"id\":\"jx_jssj\",\"content\":\"教学起止时间不能为空\"},{\"id\":\"jx_school_name\",\"content\":\"学校名称不能为空\"},{\"id\":\"jx_subject\",\"content\":\"教学科目不能为空\"},";
    }else{
        $("#jxjl_xt").css("display","inline");
    }
    //个人成就情况
    if(data.is_achievement_used == "1"){
        $("#grcjqk").css("display","block");
    }
    //个人成就必填
    if(data.is_achievement_required == "1"){
        $("#grcj_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"gr_content\",\"content\":\"个人成就必填\"},";
    }else{
        $("#grcj_xt").css("display","inline");
    }
    //主要学术兼职
    if(data.is_acade_used == "1"){
        $("#xsjz").css("display","block");
    }
    //主要学术兼职必填
    if(data.is_acade_required == "1"){
        $("#xsjz_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"xs_org_name\",\"content\":\"学术兼职必填\"},{\"id\":\"xs_position\",\"content\":\"兼职职务不能为空\"},";
    }else{
        $("#xsjz_xt").css("display","inline");
    }
    //上版教材参编情况
    if(data.is_last_position_used == "1"){
        $("#sbjccb").css("display","block");
    }
    //上版教材参编情况必填
    if(data.is_last_position_required == "1"){
        $("#sbjccb_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"jc_material_name\",\"content\":\"本套上板教材参编情况必填\"},{\"id\":\"jc_publish_date\",\"content\":\"发版时间必填\"},";
    }else{
        $("#sbjccb_xt").css("display","inline");
    }
    //主编国家规划教材情况
    if(data.is_national_plan_used == "1"){
        $("#zbgjjgh").css("display","block");
    }
    //主编国家规划教材情况必填
    if(data.is_national_plan_required == "1"){
        $("#zbgjjgh_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"hj_material_name\",\"content\":\"主编国家规划教材情况必填\"},{\"id\":\"hj_isbn\",\"content\":\"教材标准书号不能为空\"},";
    }else{
        $("#zbgjjgh_xt").css("display","inline");
    }
    //人卫教材编写情况
    if(data.is_pmph_textbook_used == "1"){
        $("#rwsjcbx").css("display","block");
    }
    //人卫教材编写情况必填
    if(data.is_pmph_textbook_required == "1"){
        $("#rwsjcbx_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"pmph_material_name\",\"content\":\"人卫社教材编写情况必填\"},{\"id\":\"pmph_publish_date\",\"content\":\"出版时间不能为空\"},{\"id\":\"pmph_isbn\",\"content\":\"教材标准书号不能为空\"},";
    }else{
        $("#rwsjcbx_xt").css("display","inline");
    }
    //其他社教材编写情况
    if(data.is_textbook_used == "1"){
        $("#qtjcbxqk").css("display","block");
    }
    //其他社教材编写情况必填
    if(data.is_textbook_required == "1"){
        $("#qtjcbxqk_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"jcb_material_name\",\"content\":\"其他社教材名称不能为空\"},{\"id\":\"jcb_publisher\",\"content\":\"出版单位不能为空\"},{\"id\":\"jcb_publish_date\",\"content\":\"出版时间不能为空\"},{\"id\":\"jcb_isbn\",\"content\":\"教材标准书号不能为空\"},";
    }else{
        $("#qtjcbxqk_xt").css("display","inline");
    }
    //参加人卫慕课、数字教材编写情况
    if(data.is_mooc_digital_used == "1"){
        $("#digital").css("display","block");
    }
    //参加人卫慕课、数字教材编写情况必填
    if(data.is_mooc_digital_required == "1"){
        $("#digital_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"mooc_content\",\"content\":\"人卫慕课、数字教材编写情况必填\"},";
    }else{
        $("#digital_xt").css("display","inline");
    }
    //精品课程建设情况
    if(data.is_course_used == "1"){
        $("#gjjpkcjs").css("display","block");
    }
    //精品课程建设情况必填
    if(data.is_course_required == "1"){
        $("#gjjpkcjs_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"gj_course_name\",\"content\":\"精品课程建设情况必填\"},{\"id\":\"gj_class_hour\",\"content\":\"课程全年课时不能为空\"},";
    }else{
        $("#gjjpkcjs_xt").css("display","inline");
    }
    //科研情况
    if(data.is_research_used == "1"){
        $("#zjkyqk").css("display","block");
    }
    //科研情况必填
    if(data.is_research_required == "1"){
        $("#zjkyqk_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"zjk_research_name\",\"content\":\"科研情况必填\"},{\"id\":\"zjk_award\",\"content\":\"获奖情况不能为空\"},{\"id\":\"zjk_approval_unit\",\"content\":\"获奖审批单位不能为空\"},";
    }else{
        $("#zjkyqk_xt").css("display","inline");
    }
    //主编学术专著情况
    if(data.is_monograph_used == "1"){
        $("#zbxszz").css("display","block");
    }
    //主编学术专著情况必填
    if(data.is_monograph_required == "1"){
        $("#zbxszz_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"zb_monograph_name\",\"content\":\"主编学术专著情况必填\"},{\"id\":\"zb_publisher\",\"content\":\"专著出版单位不能为空\"},{\"id\":\"zb_publish_date\",\"content\":\"专著出版时间不能为空\"},";
    }else{
        $("#zbxszz_xt").css("display","inline");
    }
    //出版行业获奖情况
    if(data.is_publish_reward_used == "1"){
        $("#publish").css("display","block");
    }
    //出版行业获奖情况必填
    if(data.is_publish_reward_required == "1"){
        $("#publish_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"pu_reward_name\",\"content\":\"出版行业获奖情况必填\"},{\"id\":\"pu_award_unit\",\"content\":\"评奖单位不能为空\"},{\"id\":\"pu_reward_date\",\"content\":\"获奖时间不能为空\"},";
    }else{
        $("#publish_xt").css("display","inline");
    }
    //SCI论文投稿及影响因子情况
    if(data.is_sci_used == "1"){
        $("#sci").css("display","block");
    }
    //SCI论文投稿及影响因子情况必填
    if(data.is_sci_required == "1"){
        $("#sci_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"sci_paper_name\",\"content\":\"SCI论文投稿及影响因子情况必填\"},{\"id\":\"sci_journal_name\",\"content\":\"期刊名称不能为空\"},{\"id\":\"sci_factor\",\"content\":\"sci影响因子不能为空\"},{\"id\":\"sci_publish_date\",\"content\":\"发表时间不能为空\"},";
    }else{
        $("#sci_xt").css("display","inline");
    }
    //临床医学获奖情况
    if(data.is_clinical_reward_used == "1"){
        $("#clinical").css("display","block");
    }
    //临床医学获奖情况必填
    if(data.is_clinical_reward_required == "1"){
        $("#clinical_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"cl_reward_name\",\"content\":\"临床医学获奖情况必填\"},{\"id\":\"cl_reward_date\",\"content\":\"获奖时间不能为空\"},";
    }else{
        $("#clinical_xt").css("display","inline");
    }
    //学术荣誉授予情况
    if(data.is_acade_reward_used == "1"){
        $("#acade").css("display","block");
    }
    //学术荣誉授予情况必填
    if(data.is_acade_reward_required == "1"){
        $("#acade_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"ac_reward_name\",\"content\":\"学术荣誉授予情况必填\"},{\"id\":\"ac_reward_date\",\"content\":\"荣誉授予时间不能为空\"},";
    }else{
        $("#acade_xt").css("display","inline");
    }
    //编写内容意向表
    if(data.is_intention_used == "1"){
        $("#intention").css("display","block");
    }
    //编写内容意向表必填
    if(data.is_intention_required == "1"){
        $("#intention_bt").css("display","inline");
        jsonStr=jsonStr+"{\"id\":\"intention_content\",\"content\":\"意向内容必填\"},";
    }else{
        $("#intention_xt").css("display","inline");
    }

}

//生成随机数
function only(ele,arr){ 
	 if(arr.length==0){ 
	  return true; 
	 } 
	 for(var j=0;j<arr.length;j++){ 
	  if(ele==arr[j]){ 
	   return false; 
	  }else{ 
	   return true; 
	  } 
	 } 
	} 
	  
	var arr=[0,1,2,3,4,5,6,"a","b","c","d","e","f","g"]; 
	  
	function fnt(){
	 var randNum=null; 
	 var old=[]; 
	 var str=""; 
	 function done(){ 
	  randNum=Math.floor(Math.random()*14); 
	  if(only(randNum,old)){ 
	   str=str+arr[randNum]; 
	   old.push(randNum); 
	  } 
	  else{ 
	   done(); 
	  } 
	 } 
	 for(var index=0;index<4;index++){ 
	  done(); 
	 } 
	 return str; 
	};
	//追加图书添加div
	function addTsxz(){
		//是否职位多选
		var is_multi_position = $("#is_multi_position").val();
		var select_nr = $("#select_nr").val();
		var sfbw = $("#sfbw").val();
		var str = fnt();
		var thtml = "";
		thtml=	"<div class='item' id='xz_"+str+"'>"+
				"<span style='float: left;line-height: 30px;'>图书：</span>"+
				"<select id='edu_"+str+"' name='textbook_id' class='st book' style='float: left;'>"+
					select_nr+
				"</select>"+
				"<div style='float: left;margin-left: 30px;' class='ts_radio'>"+
				"<table style='width: 280px;border:0' cellspacing='0' cellpadding='0'><tr>";
					if(is_multi_position=='1'){
						thtml += "<td height='30px'><input type='checkbox' name='zw_"+str+"' checked='checked' value='4'/>主编</td>"+
						"<td><input type='checkbox' name='zw_"+str+"' value='2'/>副主编</td>"+
						"<td><input type='checkbox' name='zw_"+str+"' value='1'/>编委</td>";
						if(sfbw == "1"){
							thtml += "<td><input type='checkbox' name='zw_"+str+"' value='8'/>数字编委</td>";
						}
					}else{
						thtml +=
						"<td height='30px'><input type='radio' name='zw_"+str+"' checked='checked' value='4'/>主编</td>"+
						"<td><input type='radio' name='zw_"+str+"' value='2'/>副主编</td>"+
						"<td><input type='radio' name='zw_"+str+"' value='1'/>编委</td>";
						if(sfbw == "1"){
							thtml += "<td><input type='radio' name='zw_"+str+"' value='8'/>数字编委</td>";
						}
					}
				thtml +=
				"</tr></table>"+
					"<input type='hidden' name='preset_position' value='zw_"+str+"'>"+
				"</div>"+
				"<div style='float: left;margin-left: 30px;height: 30px;'>"+
					"<span style='float: left;line-height: 30px;'>上传教学大纲：</span>"+
					"<div id='fileNameDiv_"+str+"' class='fileNameDiv'></div>"+
					"<input type='hidden' name='syllabus_id' id='syllabus_id_"+str+"'/>"+
					"<input type='hidden' name='syllabus_name' id='syllabus_name_"+str+"'/>"+
					"<div class='scys' id='scjxdg_"+str+"'><span>上传文件</span></div>"+
				"</div>"+
				"<div class='delBtn pull-right' onclick=\"javascript:delTsxz( 'xz_"+str+"')\"><span>删除</span></div>"+
			"</div>";
		$("#tsxz").append(thtml);
		$('#edu_'+str).selectlist({
	        width: 200,
	        height: 30,
	        optionHeight: 30
	    });
		upload(str);
	}

//删除内容
function delTsxz(str){
	$("#"+str).remove();
}

//追加学习经历tr
function add_xxjl(){
	var num = fnt();
	var $table = $("#tab_xxjl");
	var $tr = $("<tr id='xxjl_"+num+"'>"+
		"<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' max=\"'$#xx_jssj_"+num+"'\" id='xx_kssj_"+num+"'  name='xx_kssj' value='' style='width: 80px;'/>"+
	" - <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' min=\"'$#xx_kssj_"+num+"'\" id='xx_jssj_"+num+"' name='xx_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='xx_school_name' id='xx_school_name_"+num+"' value='' placeholder='学校名称'/></td>"+
	"<td><input class='cg_input' name='xx_major' value='' id='xx_major_"+num+"' placeholder='所学专业'/></td>"+
	"<td><input class='cg_input' name='xx_degree' value='' id='xx_degree_"+num+"' style='width: 110px;' placeholder='学历'/></td>"+
	"<td><input class='cg_input' name='xx_note' value='' style='width: 290px;' placeholder='备注'/>" +
	"<input type='hidden' name='zdjy' value='xx_kssj_"+num+",xx_jssj_"+num+",xx_school_name_"+num+",xx_major_"+num+",xx_degree_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xxjl_"+num+"')\"/></td>"
	);
	$table.append($tr);
	$tr.calendar();
}

//追加工作经历tr
function add_gzjl(){
	var num = fnt();
	var $table = $("#tab_gzjl");
	var $tr = $("<tr id='gzjl_"+num+"'>"+
		"<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' max=\"'$#gz_jssj_"+num+"'\" id='gz_kssj_"+num+"'  name='gz_kssj' value='' style='width: 80px;'/>"+
	" - <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' min=\"'$#gz_kssj_"+num+"'\" id='gz_jssj_"+num+"'  name='gz_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='gz_org_name' id='gz_org_name_"+num+"' value='' placeholder='工作单位'/></td>"+
	"<td><input class='cg_input' name='gz_position' id='gz_position_"+num+"' value='' placeholder='职位'/></td>"+
	"<td><input class='cg_input' name='gz_note' value='' style='width: 410px;' placeholder='备注'/>" +
	"<input type='hidden' name='zdjy' value='gz_kssj_"+num+",gz_jssj_"+num+",gz_org_name_"+num+",gz_position_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('gzjl_"+num+"')\"/></td>"
	);
	$table.append($tr);
	$tr.calendar();
}

//追加教学经历
function add_jxjl(){
	var num = fnt();
	var $table = $("#tab_jxjz");
	var $tr = $("<tr id='jxjz_"+num+"'>"+
		"<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\" max=\"'$#jx_jssj_"+num+"'\" id='jx_kssj_"+num+"'   z-index='100'  name='jx_kssj' value='' style='width: 80px;'/>"+
	" - <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" min=\"'$#jx_kssj_"+num+"'\" id='jx_jssj_"+num+"'  z-index='100' name='jx_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='jx_school_name' id='jx_school_name_"+num+"' value='' placeholder='学校名称'/></td>"+
	"<td><input class='cg_input' name='jx_subject' id='jx_subject_"+num+"' value='' placeholder='教学科目'/></td>"+
	"<td><input class='cg_input' name='jx_note' value='' style='width: 410px;' placeholder='备注'/>" +
	"<input type='hidden' name='zdjy' value='jx_kssj_"+num+",jx_jssj_"+num+",jx_school_name_"+num+",jx_subject_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jxjz_"+num+"')\"/></td>"
	);
	$table.append($tr);
	$tr.calendar();
}

//追加学术兼职
function add_xsjz(){
	var num = fnt();
	var $table = $("#tab_xsjz");
	var $tr = $("<tr id='xsjz_"+num+"'>"+
	"<td><input class='cg_input' name='xs_org_name' id='xs_org_name_"+num+"' value='' placeholder='学术组织'/></td>"+
	"<td style='color: #333333;'>"+
		"<table class='radio_tb' style='width: 100%;'><tr>"+
			"<td><input type='radio' name='xs_rank_"+num+"' checked='checked' value='0'/>无</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='1'/>国际</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='2'/>国家</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='3'/>省部</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='4'/>市级</td>"+
		"</tr></table>"+
		"<input type='hidden' name='xs_rank' value='xs_rank_"+num+"' />"+
	"<td><input class='cg_input' name='xs_position' id='xs_position_"+num+"' value='' placeholder='职务'/></td>"+
	"<td><input class='cg_input' name='xs_note' value='' style='width: 370px;' placeholder='备注'/>" +
	"<input type='hidden' name='zdjy' value='xs_org_name_"+num+",xs_position_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xsjz_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//上版教材参编情况
function add_jccb(){
	var num = fnt();
	var $table = $("#tab_jccb");
	var $tr = $("<tr id='jccb_"+num+"'>"+
	"<td><input class='cg_input' id='jc_material_name_"+num+"' name='jc_material_name' value='' style='width: 360px;' placeholder='教材名称'/></td>"+
	"<td style='color: #333333;'>"+
	"<table class='radio_tb' style='width: 100%;'><tr>"+
		"<td><input type='radio' name='jc_position_"+num+"' checked='checked' value='0'/>无</td>"+
		"<td><input type='radio' name='jc_position_"+num+"' value='1'/>主编</td>"+
		"<td><input type='radio' name='jc_position_"+num+"' value='2'/>副主编</td>"+
		"<td><input type='radio' name='jc_position_"+num+"' value='3'/>编委</td>"+
	"</tr></table>"+
	"<input type='hidden' name='jc_position' value='jc_position_"+num+"'/></td>"+
	"<td style='color: #333333;'>"+
	"<table class='radio_tb' style='width: 80px;'><tr>"+
		"<td><input type='radio' name='jc_is_digital_editor_"+num+"' value='1' />是</td>"+
		"<td><input type='radio' name='jc_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
	"</tr></table>"+
	"<input type='hidden' name='jc_is_digital_editor' value='jc_is_digital_editor_"+num+"' /></td>"+
	"<td><input class='cg_input' name='jc_publish_date' id='jc_publish_date"+num+"' value='' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100'  style='width: 100px;'/></td>"+
	"<td><input class='cg_input' name='jc_note' value='' style='width: 190px;' placeholder='备注'/>" +
	"<input type='hidden' name='zdjy' value='jc_material_name_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jccb_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
    $tr.calendar();
}

//精品课程建设情况
function add_jpkcjs(str,dim){
	var num = fnt();
	var $table = $("#"+str);
	var $tr = $("<tr id='jpkcjs_"+num+"'>"+
	"<td><input class='cg_input' name='gj_course_name' id='gj_course_name_"+num+"' value='' style='width: 300px;' placeholder='课程名称'/></td>"+
	"<td><input class='cg_input' name='gj_class_hour'  id='gj_class_hour_"+num+"' value='' style='width: 130px;' placeholder='课时数'/></td>"+
	"<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width:100%;'><tr>"+
        "<td><input type='radio' name='gj_type_"+num+"' checked='checked' value='0'/>无</td>"+
        "<td><input type='radio' name='gj_type_"+num+"' value='1'/>国际</td>"+
        "<td><input type='radio' name='gj_type_"+num+"' value='2'/>国家</td>"+
        "<td><input type='radio' name='gj_type_"+num+"' value='3'/>省部</td>"+
        "</tr></table>"+
		"<input type='hidden' name='gj_type' value='gj_type_"+num+"' /></td>"+
	"<td><input class='cg_input' name='gj_note' value='' style='width: 340px;' placeholder='备注'/>" +
	"<input type='hidden' name='zdjy' value='gj_course_name_"+num+",gj_class_hour_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jpkcjs_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//主编国家级规划教材
function add_gjghjc(){
    var num = fnt();
    var $table = $("#tab_gjghjc");
    var $tr = $("<tr id='gjghjc_"+num+"'>"+
        "<td><input class='cg_input' name='hj_material_name' id='hj_material_name_"+num+"' value='' style='width: 300px;' placeholder='教材名称'/></td>"+
        "<td><input class='cg_input' name='hj_isbn' value='' id='hj_isbn_"+num+"' style='width: 110px;' placeholder='标准书号'/></td>"+
        "<td><input class='cg_input' name='hj_rank_text' id='hj_rank_text_"+num+"' value='' style='width: 300px;' placeholder='教材级别' maxlength='50'/></td>"+
        "<td><input class='cg_input' name='hj_note' value='' style='width: 250px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='hj_material_name_"+num+",hj_isbn_"+num+",hj_rank_text_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('gjghjc_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
}

//人卫社教材编写情况
function add_rwsjcbx(){
	var num = fnt();
	var $table = $("#tab_rwsjcbx");
	var $tr = $("<tr id='pmph_"+num+"'>"+
		"<td><input class='cg_input' name='pmph_material_name' id='pmph_material_name_"+num+"' value='' style='width: 200px;' placeholder='教材名称'/></td>"+
		"<td><select id='pmph_rank_"+num+"' name='pmph_rank'>"+
	           "<option value='0'>无</option>"+
	           "<option value='1'>国家</option>"+
	           "<option value='2'>省部</option>"+
	           "<option value='3'>协编</option>"+
	           "<option value='4'>校本</option>"+
	           "<option value='5'>其他</option>"+
	    	"</select></td>"+
		"<td><select id='pmph_position_"+num+"' name='pmph_position'>"+
	            "<option value='0'>无</option>"+
	            "<option value='1'>主编</option>"+
	            "<option value='2'>副主编</option>"+
	            "<option value='3'>编委</option>"+
	    	"</select></td>"+
    	"<td style='color: #333333;'>"+
	    	"<table class='radio_tb' style='width: 80px;'><tr>"+
	    		"<td><input type='radio' name='pmph_is_digital_editor_"+num+"' value='1' />是</td>"+
	    		"<td><input type='radio' name='pmph_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
	    	"</tr></table>"+
    	"<input type='hidden' name='pmph_is_digital_editor' value='pmph_is_digital_editor_"+num+"' /></td>"+
		"<td><input class='cg_input' id='pmph_publish_date_"+num+"' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='pmph_publish_date' value='' style='width: 100px;'/></td>"+
		"<td><input class='cg_input' name='pmph_isbn' value='' id='pmph_isbn_"+num+"'  style='width: 100px;' placeholder='标准书号'/></td>"+
		"<td><input class='cg_input' name='pmph_note' value='' placeholder='备注' style='width: 260px;'/>" +
		"<input type='hidden' name='zdjy' value='pmph_material_name_"+num+",pmph_isbn_"+num+",pmph_publish_date_"+num+"' />" +
		"</td>"+
		"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('pmph_"+num+"')\"/></td>"+
		"</tr>");
	$table.append($tr);
	  $('#pmph_rank_'+num).selectlist({
	    	width: 110,
	    	height: 30,
	    	optionHeight: 30
	    });
	   $('#pmph_position_'+num).selectlist({
	    	width: 110,
	    	height: 30,
	    	optionHeight: 30
	    });
	   $tr.calendar();
}
//其他社教材编写情况
function add_jcbx(){
	var num = fnt();
	var $table = $("#tab_qtjcbxqk");
	var $tr = $("<tr id='qtjcbxqk_"+num+"'>"+
			"<td><input class='cg_input' name='jcb_material_name' id='jcb_material_name_"+num+"' value='' style='width: 200px;' placeholder='教材名称'/></td>"+
			"<td><select id='jcb_rank_"+num+"' name='jcb_rank'>"+
				"<option value='0'>无</option>"+
	            "<option value='1'>国家</option>"+
	            "<option value='2'>省部</option>"+
	            "<option value='3'>协编</option>"+
	            "<option value='4'>校本</option>"+
	            "<option value='5'>其他</option>"+
			"</select></td>"+
		"<td><select id='jcb_position_"+num+"' name='jcb_position'>"+
            "<option value='0'>无</option>"+
            "<option value='1'>主编</option>"+
            "<option value='2'>副主编</option>"+
            "<option value='3'>编委</option>"+
    	"</select></td>"+
			"<td style='color: #333333;'>"+
	    	"<table class='radio_tb' style='width: 80px;'><tr>"+
	    		"<td><input type='radio' name='jcb_is_digital_editor_"+num+"' value='1' />是</td>"+
	    		"<td><input type='radio' name='jcb_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
	    	"</tr></table>"+
	    	"<input type='hidden' name='jcb_is_digital_editor' value='jcb_is_digital_editor_"+num+"' /></td>"+
			"<td><input class='cg_input' name='jcb_publisher' id='jcb_publisher_"+num+"' value='' style='width: 100px;' placeholder='出版社'/></td>"+
			"<td><input class='cg_input' placeholder='出版时间' id='jcb_publish_date_"+num+"' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='jcb_publish_date' value='' style='width: 100px;'/></td>"+
			"<td><input class='cg_input' name='jcb_isbn' id='jcb_isbn_"+num+"' value='' style='width: 100px;' placeholder='标准书号'/></td>"+
			"<td><input class='cg_input' name='jcb_note' value='' placeholder='备注' style='width:130px;'/>" +
			"<input type='hidden' name='jcb_rank' value='0'/>" +
			"<input type='hidden' name='zdjy' value='jcb_material_name_"+num+",jcb_publisher_"+num+",jcb_isbn_"+num+",jcb_publish_date_"+num+"' />" +
			"</td>"+
			"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('qtjcbxqk_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
	$('#jcb_position_'+num).selectlist({
		width: 110,
		height: 30,
		optionHeight: 30
	});
	$('#jcb_rank_'+num).selectlist({
		width: 110,
		height: 30,
		optionHeight: 30
	});
	$tr.calendar();
}
//作家科研
function add_zjky(){
	var num = fnt();
	var $table = $("#tab_zjky");
	var $tr = $("<tr id='zjky_"+num+"'>"+
			"<td><input class='cg_input' name='zjk_research_name' value='' id='zjk_research_name_"+num+"' style='width: 200px;' placeholder='课题名称'/></td>"+
			"<td><input class='cg_input' name='zjk_approval_unit' value='' id='zjk_approval_unit_"+num+"' style='width: 200px;' placeholder='审批单位'/></td>"+
			"<td><input class='cg_input' name='zjk_award' value='' id='zjk_award_"+num+"' style='width: 300px;' placeholder='获奖情况'/></td>"+
			"<td><input class='cg_input' name='zjk_note' value='' style='width: 300px;' placeholder='备注'/>" +
			"<input type='hidden' name='zdjy' value='zjk_research_name_"+num+",zjk_approval_unit_"+num+",zjk_award_"+num+"' />" +
			"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zjky_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}
//主编学术专著情况表
function add_zbxszz(){
    var num = fnt();
    var $table = $("#tab_zbxszz");
    var $tr = $("<tr id='zbxszz_"+num+"'>"+
        "<td><input class='cg_input' name='zb_monograph_name' id='zb_monograph_name_"+num+"' value='' style='width: 200px;' placeholder='教材名称' maxlength='16'/></td>"+
        "<td><input class='cg_input' name='zb_monograph_date' id='zb_monograph_date_"+num+"' value='' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\" placeholder='发表日期'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 140px;'><tr>"+
        "<td><input type='radio' name='is_self_paid_"+num+"' value='0' checked='checked'/>自费</td>"+
        "<td><input type='radio' name='is_self_paid_"+num+"' value='1' />公费</td>"+
        "</tr></table>"+
        "<input type='hidden' name='is_self_paid' value='is_self_paid_"+num+"' /></td>"+
        "<td><input class='cg_input' name='zb_publisher' value='' id='zb_publisher_"+num+"' style='width: 180px;' placeholder='出版单位'  maxlength='16'/></td>"+
        "<td><input class='cg_input' name='zb_publish_date' value='' id='zb_publish_date_"+num+"' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\" placeholder='出版时间'/></td>"+
        "<td><input class='cg_input' name='zb_note' value='' style='width: 200px;' placeholder='备注'  maxlength='33'/>" +
        "<input type='hidden' name='zdjy' value='zb_monograph_name_"+num+",zb_publisher_"+num+",zb_publish_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zbxszz_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}
//出版行业获奖情况表
function add_publish(){
	var num = fnt();
	var $table = $("#tab_publish");
	var $tr = $("<tr id='publish_"+num+"'>"+
	"<td><input class='cg_input' name='pu_reward_name' id='pu_reward_name_"+num+"' value='' style='width: 300px;' placeholder='奖项名称' maxlength='16'/></td>"+
	"<td><input class='cg_input' name='pu_award_unit' id='pu_award_unit_"+num+"' value='' style='width: 300px;' placeholder='评奖单位' maxlength='16'/></td>"+
	"<td><input class='cg_input' name='pu_reward_date' id='pu_reward_date_"+num+"' value='' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\"  placeholder='获奖时间'/>"+
	"</td>"+
	"<td><input class='cg_input' name='pu_note' value='' style='width: 250px;' placeholder='备注' maxlength='33'/>" +
	"<input type='hidden' name='zdjy' value='pu_reward_name_"+num+",pu_award_unit_"+num+",pu_reward_date_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('publish_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
	$tr.calendar();
}
//SCI论文投稿及影响因子情况表
function add_sci(){
	var num = fnt();
	var $table = $("#tab_sci");
	var $tr = $("<tr id='sci_"+num+"'>"+
			"<td><input class='cg_input' name='sci_paper_name' id='sci_paper_name_"+num+"' value='' style='width: 300px;' placeholder='论文名称' maxlength='16'/></td>"+
			"<td><input class='cg_input' name='sci_journal_name' id='sci_journal_name_"+num+"' value='' style='width: 130px;' placeholder='期刊名称' maxlength='16'/></td>"+
			"<td><input class='cg_input' name='sci_factor' id='sci_factor_"+num+"' value='' style='width: 170px;' placeholder='期刊SCI影响因子' maxlength='7'/></td>"+
			"<td><input class='cg_input' name='sci_publish_date' id='sci_publish_date_"+num+"' value='' style='width: 110px;' calendar format=\"'yyyy-mm-dd'\" placeholder='发表时间'/></td>"+
			"<td><input class='cg_input' name='sci_note' value='' style='width: 250px;' placeholder='备注' maxlength='33'/>" +
			"<input type='hidden' name='zdjy' value='sci_paper_name_"+num+",sci_journal_name_"+num+",sci_factor_"+num+",sci_publish_date_"+num+"' />" +
			"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('sci_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
	$tr.calendar();
}

//临床医学获奖情况表
function add_clinical(){
	var num = fnt();
	var $table = $("#tab_clinical");
	var $tr = $("<tr id='clinical_"+num+"'>"+
	"<td><input class='cg_input' name='cl_reward_name' id='cl_reward_name_"+num+"' maxlength='16' value='' style='width: 300px;' placeholder='奖项名称'/></td>"+
	"<td style='color: #333333;'>"+
		"<table class='radio_tb' style='width: 180px;'><tr>"+
			"<td><input type='radio' name='cl_award_unit_"+num+"' value='0' checked='checked'/>无</td>"+
			"<td><input type='radio' name='cl_award_unit_"+num+"' value='1'/>国际</td>"+
			"<td><input type='radio' name='cl_award_unit_"+num+"' value='2' />国家</td>"+
		"</tr></table>"+
		"<input type='hidden' name='cl_award_unit' value='cl_award_unit_"+num+"' /></td>"+
	"<td><input class='cg_input' name='cl_reward_date' id='cl_reward_date_"+num+"' value='' style='width: 180px;' calendar format=\"'yyyy-mm-dd'\" placeholder='获奖时间'/></td>"+
	"<td><input class='cg_input' name='cl_note' value='' style='width: 330px;' placeholder='备注' maxlength='33'/>" +
	"<input type='hidden' name='zdjy' value='cl_reward_name_"+num+",cl_reward_date_"+num+"' />" +
	"</td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('clinical_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
	$tr.calendar();
}
//学术荣誉授予情况表
function add_acade(){
	var num = fnt();
	var $table = $("#tab_acade");
	var $tr = $("<tr id='acade_"+num+"'>"+
			"<td><input class='cg_input' name='ac_reward_name' id='ac_reward_name_"+num+"' maxlength='16' id='acade_reward_name' value='' style='width: 300px;' placeholder='荣誉名称'/></td>"+
			"<td style='color: #333333;'>"+
				"<table class='radio_tb' style='width:280px;'><tr>"+
					"<td><input type='radio' name='ac_award_unit_"+num+"' value='0' checked='checked'/>无</td>"+
					"<td><input type='radio' name='ac_award_unit_"+num+"' value='1'/>国际</td>"+
					"<td><input type='radio' name='ac_award_unit_"+num+"' value='2'/>国家</td>"+
					"<td><input type='radio' name='ac_award_unit_"+num+"' value='3'/>省部</td>"+
					"<td><input type='radio' name='ac_award_unit_"+num+"' value='4' />市</td>"+
				"</tr></table>"+
				"<input type='hidden' name='ac_award_unit' value='ac_award_unit_"+num+"' /></td>"+
			"<td><input class='cg_input' name='ac_reward_date' id='ac_reward_date_"+num+"' value='' style='width: 150px;' calendar format=\"'yyyy-mm-dd'\" placeholder='授予时间'/></td>"+
			"<td><input class='cg_input' name='ac_note' value='' style='width: 280px;' placeholder='备注' maxlength='33'/>" +
			"<input type='hidden' name='zdjy' value='ac_reward_date_"+num+",ac_reward_name_"+num+"' />" +
			"</td>"+
			"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('acade_"+num+"')\"/></td>"+
		"</tr>");
	$table.append($tr);
	$tr.calendar();
}

//删除表格tr
function del_tr(trId){
	document.getElementById(trId).remove();
}

//提交   类型1 表示提交  2 表示暂存
function buttAdd(type){
		if(type == '2'){ //表示暂存
        //避免重复点击
        document.getElementById('buzc').onclick=function(){window.message.warning("请不要重复点击");};
        document.getElementById('butj').onclick=function(){window.message.warning("请不要重复点击");};
        $.ajax({
            type: "POST",
            url:contextpath+'material/doMaterialAdd.action?sjump=2&type='+type,
            data:$('#objForm').serialize(),// 你的formid
            async: false,
            success: function (json) {
                if (json.msg == 'OK') {
                    window.message.success("操作成功,正在跳转页面");
                    window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=jcsb";
                }
            }
        });
	}else{ //表示提交
        checkLb();
        if (checkEqual("textbook_id") && checkNull(jsonStr) && checkExtra()) {
            //避免重复点击
            document.getElementById('buzc').onclick = function () {
                window.message.warning("请不要重复点击");
            };
            document.getElementById('butj').onclick = function () {
                window.message.warning("请不要重复点击");
            };
            $.ajax({
                type: "POST",
                url: contextpath + 'material/doMaterialAdd.action?sjump=2&type=' + type,
                data: $('#objForm').serialize(),// 你的formid
                async: false,
                success: function (json) {
                    if (json.msg == 'OK') {
                        window.message.success("操作成功,正在跳转页面");
                        window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=jcsb";
                    }
                }
            });
        }
    }
}

//放弃
function buttGive(){
	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=jcsb";
}	
/**
 * 表单校验方法
 */
//必填校验  layer.tips('默认就是向右的', '#id或者.class');
function toisNah(content,id){
	var value = $("#"+id).val();
	if(value == ""){
		layer.tips(content, '#'+id);
		$("#"+id)[0].focus();  //聚焦
	}
}

//固定电话号码校验
function checkTel(id){
	var value = $("#"+id).val();
	if(!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(value)){
		layer.tips('固定电话有误，请重填', '#'+id);
		$("#"+id)[0].focus();  //聚焦
		return false; 
		}
}

//手机号码校验
function checkHandphone(id){
	var value = $("#"+id).val();
	if(!(/^1(3|4|5|7|8)\d{9}$/.test(value))){ 
		layer.tips('手机号码有误，请重填', '#'+id);
		$("#"+id)[0].focus();  //聚焦
		return false; 
    } 
}
//身份证号码校验
function checkIdCard(id){
	var num = $("#"+id).val();
	if ( !(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)) ){
		layer.tips('身份证号码错误，请重填', '#'+id);
		$("#"+id)[0].focus();  //聚焦
		return false; 
	}
}

//非空验证   首先获取 非空模块的值进行非空 判断，必填模块通过方法获取ID进行判断
function checkNull(jsonStr){
	var s = "["+jsonStr.substring(0, jsonStr.length-1)+"]";
	var objs = $.parseJSON(s);
	var b = true;
	$.each(objs, function(k, obj){
	    var value = $("#"+obj.id).val();
	    if(obj.id=="zjlx"){ //判断是否为身份证
	    	if($("input[name='idtype']").val() == '0'){
		    	var num = $("#idcard").val();
		    	if ( !(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)) ){
		    		layer.tips('身份证号码错误，请重填', '#idcard');
		    		$("#idcard")[0].focus();  //聚焦
		    		b = false;
		    		return false;
		    	}
	    	}
	    }else if(obj.id=="handphone"){ //手机号码
	    	var num = $("#handphone").val();
	    	if(!(/^1(3|4|5|7|8)\d{9}$/.test(num))){ 
	    		layer.tips('手机号码有误，请重填', '#handphone');
	    		$("#handphone")[0].focus();  //聚焦
	    		b = false;
	    		return false;
	        } 
	    }else if(value == ""){
			layer.tips(obj.content, '#'+obj.id);
			$("#"+obj.id)[0].focus();  //聚焦2
			b = false;
			window.message.warning(obj.content);
			return false; 
		}
	});
	return b;
}

//机构选择
function orgAdd(material_id){
	layer.open({
		  type: 2,
		  area: ['800px', '600px'],
		  fixed: false, //不固定
		  title:'申报单位选择',
		  maxmin: true,
		  content: contextpath+"material/toSearchOrg.action?material_id="+material_id
		});
}

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){
	
	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"aaa").length <= ml) {
			maxStrlength=i;
		}else{
			
			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"aaa").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		//window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}

//根据name判断获取的值是否有重复的
function checkEqual(name){
	//获取name属性的对象数组(节点数组)
	var map = $('input[name^="textbook_id"]').map(
			function(){return this.value
		}).get();
	//遍历数组并比较是否存在相同值
	var nary=map.sort(); 
	for(var i=0;i<map.length;i++){ 
		if (nary[i]==nary[i+1]){ 
			window.message.warning("不能选择相同书籍!请重新选择书籍");
			return false;
		} 
	}	
	return true;
}

//验证扩展项必填
function checkExtra(){
	var map = $('input[name^="zjkzxx"]').map(
			function(){return this.value
		}).get();
	if(map!=null){
		for(var i=0;i<map.length;i++){ 
			var strs= new Array(); //定义一数组 
			strs=map[i].split("_"); //字符分割
			if(strs[0] == "true"){ //说明必填
				var value = $("#"+map[i]).val();
				if(value == ""){
					window.message.warning("该扩展项必填");
					$("#"+map[i])[0].focus();  //聚焦
					return false;
				}
			}
		}	
	}
	return true;
}
//列表填报校验
function checkLb(){
	var map = $('input[name^="zdjy"]').map(
			function(){return this.value
		}).get();
	if(map!=null){
		for(var i=0;i<map.length;i++){ 
			var strs= new Array(); //定义一数组 
			strs=map[i].split(","); //字符分割
			//遍历
			for ( var j = 0; j < strs.length; j++) {
				jsonStr=jsonStr+"{\"id\":\""+strs[j]+"\",\"content\":\"该项不能为空\"},";
			}
		}	
	}
}

//文件下载
function downLoadProxy(fileId){
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}