//定义一个全局变量
var jsonStr = "";
jsonStr = "{\"id\":\"realname\",\"content\":\"姓名不能为空\"}," +
	"{\"id\":\"birthday\",\"content\":\"出生日期不能为空\"},{\"id\":\"experience\",\"content\":\"教龄不能为空\"},"+
	"{\"id\":\"handphone\",\"content\":\"手机号码不能为空\"},{\"id\":\"idcard\",\"content\":\"证件号码不能为空\"},{\"id\":\"sbdw_name\",\"content\":\"申报单位不能为空\"},";

$(function () {
	var id = $("#material_id").val();
	upload("1"); //附件上传
	queryMaterialMap(id);  //执行查询方法
	
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
    $('#jcxz').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });
    $('#jcjb').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });
    $('#qtjcjb').selectlist({
    	zIndex: 10,
    	width: 110,
    	height: 30,
    	optionHeight: 30
    });

  /*  $("#apply-org").selectlist({
        zIndex: 10,
        width: 200,
        height: 30,
        fiter:true,
        optionHeight: 30
    });*/
});

//附件上传方法
function upload(id){
	$("#scjxdg_"+id).uploadFile({
	    start: function () {
	        console.log("开始上传。。。");
	    },
	    done: function (filename, fileid) {
	    	$("#fileNameDiv_"+id).empty(); //清楚内容
	    	$("#fileNameDiv_"+id).append("<span>"+filename+"</span>");
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
		jsonStr=jsonStr+"{\"id\":\"xx_kssj\",\"content\":\"学习经历必填\"},"; 
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
		jsonStr=jsonStr+"{\"id\":\"gz_kssj\",\"content\":\"工作经历必填\"},";
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
		jsonStr=jsonStr+"{\"id\":\"jx_kssj\",\"content\":\"教学经历必填\"},";
	}else{
		$("#jxjl_xt").css("display","inline");
	}
	//主要学术兼职
	if(data.is_acade_used == "1"){
		$("#xsjz").css("display","block");
	}
	//主要学术兼职必填
	if(data.is_acade_required == "1"){
		$("#xsjz_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"xs_org_name\",\"content\":\"主要学术必填\"},";
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
		jsonStr=jsonStr+"{\"id\":\"jc_material_name\",\"content\":\"上版教材参编情况必填\"},";
	}else{
		$("#sbjccb_xt").css("display","inline");
	}
	//精品课程建设情况
	if(data.is_course_used == "1"){
		$("#gjjpkcjs").css("display","block");
	}
	//精品课程建设情况必填
	if(data.is_course_required == "1"){
		$("#gjjpkcjs_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"gj_course_name\",\"content\":\"国家级精品课程建设情况\"},";
	}else{
		$("#gjjpkcjs_xt").css("display","inline");
	}
	//主编国家规划教材情况
	if(data.is_national_plan_used == "1"){
		$("#zbgjjgh").css("display","block");
	}
	//主编国家规划教材情况必填
	if(data.is_national_plan_required == "1"){
		$("#zbgjjgh_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"hj_material_name\",\"content\":\"主编国家规划教材情况必填\"},";
	}else{
		$("#zbgjjgh_xt").css("display","inline");
	}
	//教材编写情况
	if(data.is_textbook_used == "1"){
		$("#jcbxqk").css("display","block");
	}
	//教材编写情况必填
	if(data.is_textbook_required == "1"){
		$("#jcbxqk_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"jcb_material_name\",\"content\":\"教材编写情况\"},";
	}else{
		$("#jcbxqk_xt").css("display","inline");
	}
	//其他教材编写情况
	if(data.is_other_textbook_used == "1"){
		$("#qtjcbxqk").css("display","block");
	}
	//其他教材编写情况必填
	if(data.is_other_textbook_required == "1"){
		$("#qtjcbx_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"qt_jcb_material_name\",\"content\":\"其他教材编写情况必填\"},";
	}else{
		$("#qtjcbx_xt").css("display","inline");
	}
	//科研情况
	if(data.is_research_used == "1"){
		$("#zjkyqk").css("display","block");
	}
	//科研情况必填
	if(data.is_research_required == "1"){
		$("#zjkyqk_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"zjk_research_name\",\"content\":\"作家科研情况必填\"},";
	}else{
		$("#zjkyqk_xt").css("display","inline");
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
	//主编学术专著情况
	if(data.is_monograph_used == "1"){
		$("#zbxszz").css("display","block");
	}
	//主编学术专著情况必填
	if(data.is_monograph_required == "1"){
		$("#zbxszz_bt").css("display","inline");
		jsonStr=jsonStr+"{\"id\":\"zb_monograph_name\",\"content\":\"主编学术专著情况必填\"},";
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
		jsonStr=jsonStr+"{\"id\":\"pu_reward_name\",\"content\":\"出版行业获奖情况必填\"},";
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
		jsonStr=jsonStr+"{\"id\":\"sci_paper_name\",\"content\":\"SCI论文投稿及影响因子情况必填\"},";
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
		jsonStr=jsonStr+"{\"id\":\"cl_reward_name\",\"content\":\"临床医学获奖情况必填\"},";
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
		jsonStr=jsonStr+"{\"id\":\"ac_reward_name\",\"content\":\"学术荣誉授予情况必填\"},";
	}else{
		$("#acade_xt").css("display","inline");
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
	var select_nr = $("#select_nr").val();
	var sfbw = $("#sfbw").val();
	var str = fnt();
	if(sfbw == "1"){
		$("#tsxz").append("<div class='item' id='xz_"+str+"'>"+
				"<span style='float: left;'>图书：</span>"+
				"<select id='edu_"+str+"' name='textbook_id' class='st book' style='float: left;'>"+
					"<option value=''>请选择书籍</option>"+
					select_nr+
				"</select>"+
				"<div style='float: left;margin-left: 30px;' class='ts_radio'>"+
				"<table style='width: 280px;'><tr>"+
					"<td><input type='radio' name='zw_"+str+"' checked='checked' value='1'/>主编</td>"+
					"<td><input type='radio' name='zw_"+str+"' value='2'/>副主编</td>"+
					"<td><input type='radio' name='zw_"+str+"' value='3'/>编委</td>"+
					"<td><input type='radio' name='zw_"+str+"' value='4'/>数字编委</td>"+
				"</tr></table>"+
					"<input type='hidden' name='preset_position' value='zw_"+str+"'>"+
				"</div>"+
				"<div style='float: left;margin-left: 30px;'>"+
					"<span style='float: left;'>上传教学大纲：</span>"+
					"<div id='fileNameDiv_"+str+"' class='fileNameDiv'></div>"+
					"<input type='hidden' name='syllabus_id' id='syllabus_id_"+str+"'/>"+
					"<input type='hidden' name='syllabus_name' id='syllabus_name_"+str+"'/>"+
					"<div class='scys' id='scjxdg_"+str+"'><span>上传文件</span></div>"+
				"</div>"+
				"<div class='delBtn pull-right' onclick=\"javascript:delTsxz( 'xz_"+str+"')\"><span>删除</span></div>"+
			"</div>");
	}else{
		$("#tsxz").append("<div class='item' id='xz_"+str+"'>"+
				"<span style='float: left;'>图书：</span>"+
				"<select id='edu_"+str+"' name='textbook_id' class='st book' style='float: left;'>"+
					"<option value=''>请选择书籍</option>"+
					select_nr+
				"</select>"+
				"<div style='float: left;margin-left: 30px;' class='ts_radio'>"+
				"<table style='width: 280px;'><tr>"+
					"<td><input type='radio' name='zw_"+str+"' checked='checked' value='1'/>主编</td>"+
					"<td><input type='radio' name='zw_"+str+"' value='2'/>副主编</td>"+
					"<td><input type='radio' name='zw_"+str+"' value='3'/>编委</td>"+
				"</tr></table>"+
					"<input type='hidden' name='preset_position' value='zw_"+str+"'>"+
				"</div>"+
				"<div style='float: left;margin-left: 30px;'>"+
					"<span style='float: left;'>上传教学大纲：</span>"+
					"<div id='fileNameDiv_"+str+"' class='fileNameDiv'></div>"+
					"<input type='hidden' name='syllabus_id' id='syllabus_id_"+str+"'/>"+
					"<input type='hidden' name='syllabus_name' id='syllabus_name_"+str+"'/>"+
					"<div class='scys' id='scjxdg_"+str+"'><span>上传文件</span></div>"+
				"</div>"+
				"<div class='delBtn pull-right' onclick=\"javascript:delTsxz( 'xz_"+str+"')\"><span>删除</span></div>"+
			"</div>");
	}
	
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
	"- <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' min=\"'$#xx_kssj_"+num+"'\" id='xx_jssj_"+num+"' name='xx_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='xx_school_name' value='' placeholder='学校名称'/></td>"+
	"<td><input class='cg_input' name='xx_major' value='' placeholder='所学专业'/></td>"+
	"<td><input class='cg_input' name='xx_degree' value='' style='width: 120px;' placeholder='学历'/></td>"+
	"<td><input class='cg_input' name='xx_note' value='' style='width: 310px;' placeholder='备注'/></td>"+
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
	"- <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' min=\"'$#gz_kssj_"+num+"'\" id='gz_jssj_"+num+"'  name='gz_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='gz_org_name' value='' placeholder='工作单位'/></td>"+
	"<td><input class='cg_input' name='gz_position' value='' placeholder='职位'/></td>"+
	"<td><input class='cg_input' name='gz_note' value='' style='width: 370px;' placeholder='备注'/></td>"+
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
	"- <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" min=\"'$#jx_kssj_"+num+"'\" id='jx_jssj_"+num+"'  z-index='100' name='jx_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='jx_school_name' value='' placeholder='学校名称'/></td>"+
	"<td><input class='cg_input' name='jx_subject' value='' placeholder='教学科目'/></td>"+
	"<td><input class='cg_input' name='jx_note' value='' style='width: 370px;' placeholder='备注'/></td>"+
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
	"<td><input class='cg_input' name='xs_org_name' value='' placeholder='学术组织'/></td>"+
	"<td style='color: #333333;'>"+
		"<table class='radio_tb' style='width: 220px;'><tr>"+
			"<td><input type='radio' name='xs_rank_"+num+"' checked='checked' value='1'/>国际</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='2'/>国家</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='3'/>省部</td>"+
			"<td><input type='radio' name='xs_rank_"+num+"' value='4'/>其他</td>"+
		"</tr></table>"+
		"<input type='hidden' name='xs_rank' value='xs_rank_"+num+"' />"+
	"<td><input class='cg_input' name='xs_position' value='' placeholder='职务'/></td>"+
	"<td><input class='cg_input' name='xs_note' value='' style='width: 370px;' placeholder='备注'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xsjz_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//上版教材参编情况
function add_jccb(){
	var num = fnt();
	var $table = $("#tab_jccb");
	var $tr = $("<tr id='jccb_"+num+"'>"+
	"<td><input class='cg_input' name='jc_material_name' value='' style='width: 360px;' placeholder='教材名称'/></td>"+
	"<td style='color: #333333;'>"+
	"<table class='radio_tb' style='width: 230px;'><tr>"+
		"<td><input type='radio' name='jc_position_"+num+"' checked='checked' value='0'/>无</td>"+
		"<td><input type='radio' name='jc_position_"+num+"' value='1'/>主编</td>"+
		"<td><input type='radio' name='jc_position_"+num+"' value='2'/>编委</td>"+
		"<td><input type='radio' name='jc_position_"+num+"' value='3'/>副编委</td>"+
	"</tr></table>"+
	"<input type='hidden' name='jc_position' value='jc_position_"+num+"'/></td>"+
	"<td style='color: #333333;'>"+
	"<table class='radio_tb' style='width: 80px;'><tr>"+
		"<td><input type='radio' name='jc_is_digital_editor_"+num+"' value='1' />是</td>"+
		"<td><input type='radio' name='jc_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
	"</tr></table>"+
	"<input type='hidden' name='jc_is_digital_editor' value='jc_is_digital_editor_"+num+"' /></td>"+
	"<td><input class='cg_input' name='jc_note' value='' style='width: 230px;' placeholder='职务'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jccb_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//精品课程建设情况
function add_jpkcjs(str,dim){
	var num = fnt();
	var $table = $("#"+str);
	var $tr = $("<tr id='jpkcjs_"+num+"'>"+
	"<td><input class='cg_input' name='gj_course_name' value='' style='width: 300px;' placeholder='课程名称'/></td>"+
	"<td><input class='cg_input' name='gj_class_hour' value='' style='width: 130px;' placeholder='课时数'/></td>"+
	"<td style='color: #333333;'>"+
	"<table class='radio_tb' style='width:180px;'><tr>"+
		"<td><input type='radio' name='gj_type_"+num+"' checked='checked' value='1'/>国家</td>"+
		"<td><input type='radio' name='gj_type_"+num+"' value='2'/>省部</td>"+
		"<td><input type='radio' name='gj_type_"+num+"' value='3'/>学校</td>"+
	"</tr></table>"+
		"<input type='hidden' name='gj_type' value='gj_type_"+num+"' /></td>"+
	"<td><input class='cg_input' name='gj_note' value='' style='width: 330px;' placeholder='备注'/>" +
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
	"<td><input class='cg_input' name='hj_material_name' value='' style='width: 300px;' placeholder='教材名称'/></td>"+
	"<td><input class='cg_input' name='hj_isbn' value='' style='width: 110px;' placeholder='标准书号'/></td>"+
	"<td style='color: #333333;'>"+
	"<table class='radio_tb' style='width:320px;'><tr>"+
		"<td><input type='radio' name='hj_rank_"+num+"' checked='checked' value='1'/>教育部十二五</td>"+
		"<td><input type='radio' name='hj_rank_"+num+"' value='2'/>国家卫计委十二五</td>"+
		"<td><input type='radio' name='hj_rank_"+num+"' value='3'/>其他</td>"+
	"</tr></table>"+
		"<input type='hidden' name='hj_rank' value='hj_rank_"+num+"' /></td>"+
	"<td><input class='cg_input' name='hj_note' value='' style='width: 250px;' placeholder='备注'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('gjghjc_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//教材编写情况
function add_jcbx(){
	var num = fnt();
	var $table = $("#tab_jcbx");
	var $tr = $("<tr id='jcbx_"+num+"'>"+
		"<td><input class='cg_input' name='jcb_material_name' value='' style='width: 200px;' placeholder='教材名称'/></td>"+
		"<td><select id='jcxz_"+num+"' name='jcb_rank'>"+
	           "<option value='1'>教育部规划</option>"+
	            "<option value='2'>卫计委规划</option>"+
	            "<option value='3'>区域规划</option>"+
	            "<option value='4'>创新教材</option>"+
	    	"</select></td>"+
		"<td><select id='jcjb_"+num+"' name='jcb_position'>"+
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
		"<td><input class='cg_input' name='jcb_publisher' value='' style='width: 100px;' placeholder='出版社'/></td>"+
		"<td><input class='cg_input' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='jcb_publish_date' value='' style='width: 110px;'/></td>"+
		"<td><input class='cg_input' name='jcb_isbn' value='' style='width: 100px;' placeholder='标准书号'/></td>"+
		"<td><input class='cg_input' name='jcb_note' value='' placeholder='备注' style='width: 130px;'/></td>"+
		"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jcbx_"+num+"')\"/></td>"+
		"</tr>");
	$table.append($tr);
	  $('#jcjb_'+num).selectlist({
	    	width: 110,
	    	height: 30,
	    	optionHeight: 30
	    });
	   $('#jcxz_'+num).selectlist({
	    	width: 110,
	    	height: 30,
	    	optionHeight: 30
	    });
	   $tr.calendar();
}
//其他教材编写情况
function add_qtjcbx(){
	var num = fnt();
	var $table = $("#tab_qtjcbx");
	var $tr = $("<tr id='qtjcbx_"+num+"'>"+
			"<td><input class='cg_input' name='jcb_material_name' value='' style='width: 210px;' placeholder='教材名称'/></td>"+
			"<td><select id='qtjcjb_"+num+"' name='jcb_position'>"+
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
			"<td><input class='cg_input' name='jcb_publisher' value='' style='width: 120px;' placeholder='出版社'/></td>"+
			"<td><input class='cg_input' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='jcb_publish_date' value='' style='width: 110px;'/></td>"+
			"<td><input class='cg_input' name='jcb_isbn' value='' style='width: 110px;' placeholder='标准书号'/></td>"+
			"<td><input class='cg_input' name='jcb_note' value='' placeholder='备注' style='width:180px;'/>" +
			"<input type='hidden' name='jcb_rank' value='0'/></td>"+
			"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('qtjcbx_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
	$('#qtjcjb_'+num).selectlist({
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
			"<td><input class='cg_input' name='zjk_research_name' value='' style='width: 200px;' placeholder='课题名称'/></td>"+
			"<td><input class='cg_input' name='zjk_approval_unit' value='' style='width: 200px;' placeholder='审批单位'/></td>"+
			"<td><input class='cg_input' name='zjk_award' value='' style='width: 300px;' placeholder='获奖情况'/></td>"+
			"<td><input class='cg_input' name='zjk_note' value='' style='width: 300px;' placeholder='备注'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zjky_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);	
}
//主编学术专著情况表
function add_zbxszz(){
	var num = fnt();
	var $table = $("#tab_zbxszz");
	var $tr = $("<tr id='zbxszz_"+num+"'>"+
			"<td><input class='cg_input' name='zb_monograph_name' value='' style='width: 300px;' placeholder='教材名称' maxlength='16'/></td>"+
			"<td style='color: #333333;'>"+
				"<table class='radio_tb' style='width: 100px;'><tr>"+
					"<td><input type='radio' name='is_self_paid_'"+num+" value='1' checked='checked'/>是</td>"+
					"<td><input type='radio' name='is_self_paid_'"+num+" value='2' />否</td>"+
				"</tr></table>"+
				"<input type='hidden' name='is_self_paid' value='is_self_paid_'"+num+" /></td>"+
			"<td><input class='cg_input' name='zb_publisher' value='' style='width: 180px;' placeholder='出版单位'  maxlength='16'/></td>"+
			"<td><input class='cg_input' name='zb_publish_date' value='' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\" placeholder='出版时间'/></td>"+
			"<td><input class='cg_input' name='zb_note' value='' style='width: 250px;' placeholder='备注'  maxlength='33'/></td>"+
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
	"<td><input class='cg_input' name='pu_reward_name' value='' style='width: 300px;' placeholder='奖项名称' maxlength='16'/></td>"+
	"<td><input class='cg_input' name='pu_award_unit' value='' style='width: 300px;' placeholder='评奖单位' maxlength='16'/></td>"+
	"<td><input class='cg_input' name='pu_reward_date' value='' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\"  placeholder='获奖时间'/>"+
	"</td>"+
	"<td><input class='cg_input' name='pu_note' value='' style='width: 250px;' placeholder='备注' maxlength='33'/></td>"+
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
			"<td><input class='cg_input' name='sci_paper_name' value='' style='width: 300px;' placeholder='论文名称' maxlength='16'/></td>"+
			"<td><input class='cg_input' name='sci_journal_name' value='' style='width: 130px;' placeholder='期刊名称' maxlength='16'/></td>"+
			"<td><input class='cg_input' name='sci_factor' value='' style='width: 170px;' placeholder='期刊SCI影响因子' maxlength='7'/></td>"+
			"<td><input class='cg_input' name='sci_publish_date' value='' style='width: 110px;' calendar format=\"'yyyy-mm-dd'\" placeholder='发表时间'/></td>"+
			"<td><input class='cg_input' name='sci_note' value='' style='width: 250px;' placeholder='备注' maxlength='33'/></td>"+
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
	"<td><input class='cg_input' name='cl_reward_name' maxlength='16' value='' style='width: 300px;' placeholder='奖项名称'/></td>"+
	"<td style='color: #333333;'>"+
		"<table class='radio_tb' style='width: 180px;'><tr>"+
			"<td><input type='radio' name='cl_award_unit_'"+num+" value='0' checked='checked'/>无</td>"+
			"<td><input type='radio' name='cl_award_unit_'"+num+" value='1'/>国际</td>"+
			"<td><input type='radio' name='cl_award_unit_'"+num+" value='2' />国家</td>"+
		"</tr></table>"+
		"<input type='hidden' name='cl_award_unit' value='cl_award_unit_'"+num+" /></td>"+
	"<td><input class='cg_input' name='cl_reward_date' value='' style='width: 180px;' calendar format=\"'yyyy-mm-dd'\" placeholder='获奖时间'/></td>"+
	"<td><input class='cg_input' name='cl_note' value='' style='width: 330px;' placeholder='备注' maxlength='33'/></td>"+
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
			"<td><input class='cg_input' name='ac_reward_name' maxlength='16' id='acade_reward_name' value='' style='width: 300px;' placeholder='荣誉名称'/></td>"+
			"<td style='color: #333333;'>"+
				"<table class='radio_tb' style='width:280px;'><tr>"+
					"<td><input type='radio' name='ac_award_unit_'"+num+" value='0' checked='checked'/>无</td>"+
					"<td><input type='radio' name='ac_award_unit_'"+num+" value='1'/>国际</td>"+
					"<td><input type='radio' name='ac_award_unit_'"+num+" value='2'/>国家</td>"+
					"<td><input type='radio' name='ac_award_unit_'"+num+" value='3'/>省部</td>"+
					"<td><input type='radio' name='ac_award_unit_'"+num+" value='4' />市</td>"+
				"</tr></table>"+
				"<input type='hidden' name='ac_award_unit' value='ac_award_unit_'"+num+" /></td>"+
			"<td><input class='cg_input' name='ac_reward_date' value='' style='width: 150px;' calendar format=\"'yyyy-mm-dd'\" placeholder='授予时间'/></td>"+
			"<td><input class='cg_input' name='ac_note' value='' style='width: 280px;' placeholder='备注' maxlength='33'/></td>"+
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
	if(checkNull(jsonStr)){
		$.ajax({
			type: "POST",
			url:contextpath+'material/doMaterialAdd.action?type='+type,
		//	url:contextpath+'material/doMaterialTest.action',
			data:$('#objForm').serialize(),// 你的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg=='OK'){
			    	window.message.success("添加成功,正在跳转页面");
			    	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=jcsb";
			    }
		    }
		});
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
	    if(obj.id=="zjlx" && value=="0"){ //判断是否为身份证
	    	checkIdCard("idcard");
	    }else if(obj.id=="handphone"){ //手机号码
	    	checkHandphone("handphone");
	    }else if(value == ""){
			layer.tips(obj.content, '#'+obj.id);
			$("#"+obj.id)[0].focus();  //聚焦2
			b = false;
			window.message.error(obj.content);
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
