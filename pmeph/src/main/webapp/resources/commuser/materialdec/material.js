var contextpath="http://localhost:8080/pmeph/";
$(function () {
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
    $('#edu').selectlist({
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

    $("#apply-org").selectlist({
        zIndex: 10,
        width: 200,
        height: 30,
        fiter:true,
        optionHeight: 30
    });
});
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
//追加div
function addTsxz(){
	var select_nr = $("#select_nr").val();
	var str = fnt();
	$("#tsxz").append("<div class='item' id='xz_"+str+"'>"+
				"<span style='float: left;'>图书：</span>"+
				"<select id='edu' name='edu' class='st book' style='float: left;'>"+
					"<option value=''>请选择书籍</option>"+
					select_nr+
				"</select>"+
				"<div style='float: left;margin-left: 30px;' class='ts_radio'>"+
					"<input type='radio' name='zw_"+str+"' checked='checked' />主编"+
					"<input type='radio' name='zw_"+str+"' />副编委"+
					"<input type='radio' name='zw_"+str+"' />编委"+
				"</div>"+
				"<div style='float: left;margin-left: 30px;'>"+
					"<span style='float: left;'>上传教学大纲：</span>"+
					"<div class='scys'><span>上传文件</span></div>"+
				"</div>"+
				"<div class='delBtn pull-right' onclick=\"javascript:delTsxz( 'xz_"+str+"')\"><span>删除</span></div>"+
			"</div>");
	$('#edu').selectlist({
        zIndex: 10,
        width: 200,
        height: 30,
        optionHeight: 30
    });
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
		"<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100'  name='xx_kssj' value='' style='width: 80px;'/>"+
	"- <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' name='xx_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='xx_school_name' value='' /></td>"+
	"<td><input class='cg_input' name='xx_major' value='' /></td>"+
	"<td><input class='cg_input' name='xx_degree' value='' style='width: 120px;'/></td>"+
	"<td><input class='cg_input' name='xx_note' value='' style='width: 310px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('xxjl_"+num+"')\"/></td>"
	);
	$table.append($tr);
	$tr.calendar();
}

//追加工作经历tr
function add_gzjl(){
	var num = fnt();
	var $table = $("#tab_gzjl");
	var $tr = $("<tr id='gzjl_"+num+"'>"+
		"<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100'  name='gz_kssj' value='' style='width: 80px;'/>"+
	"- <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' name='gz_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='gz_org_name' value='' /></td>"+
	"<td><input class='cg_input' name='gz_position' value='' /></td>"+
	"<td><input class='cg_input' name='gz_note' value='' style='width: 370px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('gzjl_"+num+"')\"/></td>"
	);
	$table.append($tr);
}

//追加教学经历
function add_jxjl(){
	var num = fnt();
	var $table = $("#tab_jxjz");
	var $tr = $("<tr id='jxjz_"+num+"'>"+
		"<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100'  name='jx_kssj' value='' style='width: 80px;'/>"+
	"- <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' name='jx_jssj' value='' style='width: 80px;'/></td>"+
	"<td><input class='cg_input' name='jx_school_name' value='' /></td>"+
	"<td><input class='cg_input' name='jx_subject' value='' /></td>"+
	"<td><input class='cg_input' name='jx_note' value='' style='width: 370px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('jxjz_"+num+"')\"/></td>"
	);
	$table.append($tr);
}

//追加学术兼职
function add_xsjz(){
	var num = fnt();
	var $table = $("#tab_xsjz");
	var $tr = $("<tr id='xsjz_"+num+"'>"+
	"<td><input class='cg_input' name='xs_org_name' value='' /></td>"+
	"<td style='color: #333333;'>"+
		"<input type='radio' name='xs_rank_"+num+"' value='1' checked='checked'/>国际"+
		"<input type='radio' name='xs_rank_"+num+"' value='2' />国家"+
		"<input type='radio' name='xs_rank_"+num+"' value='3' />省部"+
		"<input type='radio' name='xs_rank_"+num+"' value='4' />其他</td>"+
	"<td><input class='cg_input' name='xs_position' value='' /></td>"+
	"<td><input class='cg_input' name='xs_note' value='' style='width: 370px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('xsjz_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//上版教材参编情况
function add_jccb(){
	var num = fnt();
	var $table = $("#tab_jccb");
	var $tr = $("<tr id='jccb_"+num+"'>"+
	"<td><input class='cg_input' name='jc_material_name' value='' style='width: 360px;'/></td>"+
	"<td style='color: #333333;'>"+
		"<input type='radio' name='jc_position_"+num+"' value='0' checked='checked'/>无"+
		"<input type='radio' name='jc_position_"+num+"' value='1' />主编"+
		"<input type='radio' name='jc_position_"+num+"' value='2' />编委"+
		"<input type='radio' name='jc_position_"+num+"' value='3' />副编委</td>"+
	"<td><input class='cg_input' name='jc_note' value='' style='width: 330px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('jccb_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//精品课程建设情况
function add_jpkcjs(str,dim){
	var num = fnt();
	var $table = $("#"+str);
	var $tr = $("<tr id='jpkcjs_"+num+"'>"+
	"<td><input class='cg_input' name='gj_course_name' value='' style='width: 370px;'/></td>"+
	"<td><input class='cg_input' name='gj_class_hour' value='' style='width: 170px;'/></td>"+
	"<td><input class='cg_input' name='gj_note' value='' style='width: 450px;'/>" +
	"<input type='hidden' name='gj_type' value='"+dim+"' /></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('jpkcjs_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//国家级规划教材
function add_gjghjc(){
	var num = fnt();
	var $table = $("#tab_gjghjc");
	var $tr = $("<tr id='gjghjc_"+num+"'>"+
	"<td><input class='cg_input' name='hj_material_name' value='' style='width: 300px;'/></td>"+
	"<td><input class='cg_input' name='hj_isbn' value='' style='width: 110px;'/></td>"+
	"<td style='color: #333333;'>"+
		"<input type='radio' name='hj_rank_"+num+"' value='1' checked='checked' />教育部十二五"+
		"<input type='radio' name='hj_rank_"+num+"' value='2' />国家卫计委十二五"+
		"<input type='radio' name='hj_rank_"+num+"' value='3' />其他</td>"+
	"<td><input class='cg_input' name='hj_note' value='' style='width: 250px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('gjghjc_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);
}

//教材编写情况
function add_jcbx(){
	var num = fnt();
	var $table = $("#tab_jcbx");
	var $tr = $("<tr id='jcbx_"+num+"'>"+
		"<td><input class='cg_input' name='jcb_material_name' value='' style='width: 210px;'/></td>"+
		"<td><select id='jcxz' name='jcb_rank'>"+
	            "<option value='0'>其他教材</option>"+
	           "<option value='1'>教育部规划</option>"+
	            "<option value='2'>卫计委规划</option>"+
	            "<option value='3'>区域规划</option>"+
	            "<option value='4'>创新教材</option>"+
	    	"</select></td>"+
		"<td><select id='jcjb' name='jcb_position'>"+
	            "<option value='0'>无</option>"+
	            "<option value='1'>主编</option>"+
	            "<option value='2'>副主编</option>"+
	            "<option value='3'>编委</option>"+
	    	"</select></td>"+
		"<td><input class='cg_input' name='jcb_publisher' value='' style='width: 120px;'/></td>"+
		"<td><input class='cg_input' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='jcb_publish_date' value='' style='width: 110px;'/></td>"+
		"<td><input class='cg_input' name='jcb_isbn' value='' style='width: 110px;'/></td>"+
		"<td><input class='cg_input' name='jcb_note' value=''/></td>"+
		"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('jcbx_"+num+"')\"/></td>"+
		"</tr>");
	$table.append($tr);
	  $('#jcjb').selectlist({
	    	zIndex: 10,
	    	width: 110,
	    	height: 30,
	    	optionHeight: 30
	    });
	   $('#jcxz').selectlist({
	    	zIndex: 10,
	    	width: 110,
	    	height: 30,
	    	optionHeight: 30
	    });
}
//作家科研
function add_zjky(){
	var num = fnt();
	var $table = $("#tab_zjky");
	var $tr = $("<tr id='zjky_"+num+"'>"+
			"<td><input class='cg_input' name='zjk_research_name' value='' style='width: 200px;'/></td>"+
			"<td><input class='cg_input' name='zjk_approval_unit' value='' style='width: 200px;'/></td>"+
			"<td><input class='cg_input' name='zjk_award' value='' style='width: 300px;'/></td>"+
			"<td><input class='cg_input' name='zjk_note' value='' style='width: 300px;'/></td>"+
	"<td><img class='add_img' src='"+contextpath+"/statics/image/del.png' onclick=\"javascript:del_tr('zjky_"+num+"')\"/></td>"+
	"</tr>");
	$table.append($tr);	
}

//删除表格tr
function del_tr(trId){
	document.getElementById(trId).remove();
}

//提交
function buttAdd(){
	$.ajax({
		type: "POST",
		url:contextpath+'/material/doMaterialAdd.action',
		data:$('#objForm').serialize(),// 你的formid
		async: false,
	    success: function(data) {
		    if(data.msg=='OK'){
		    	alert("新增成功！");
		    }else{
		    	alert("新增失败！");
		    }
	    }
	});
}