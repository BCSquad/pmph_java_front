
$(function () {
    $('.dzdx').selectlist({
        width: 308,
        height: 30,
        optionHeight: 30
    });
    $('.xzly').selectlist({
    	width: 308,
    	height: 30,
    	optionHeight: 30
    });
    $('#r_sex').selectlist({
    	width: 90,
    	height: 30,
    	optionHeight: 30
    });
    $('#yhxxid').selectlist({
    	width: 308,
    	height: 30,
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
//
function add_zjky(){
	var num = fnt();
	var $table = $("#sbbzqk");
	var $tr = $("<tr id='sbbz_"+num+"'>"+
			"<td><input class='sb_input' style='width: 100px;' name='write_realname' placeholder='编者姓名' value=''/></td>"+
			"<td><select id='r_sex_"+num+"'  name='sex'>"+
					"<option value='0'>男</option>"+
					"<option value='1'>女</option>"+
				"</select></td>"+
			"<td><input class='sb_input' style='width: 80px;' name='write_price' placeholder='年龄' value=''/></td>"+
			"<td><input class='sb_input' style='width: 320px;' name='write_position' placeholder='行政职务' value=''/></td>"+
			"<td><input class='sb_input' style='width: 200px;' name='workplace' placeholder='工作单位' value=''/></td>"+
			"<td><div class='add_div'><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('sbbz_"+num+"')\"></div></td>"+
		"</tr>");
	$table.append($tr);
	  $('#r_sex_'+num).selectlist({
		  	width: 90,
	    	height: 30,
	    	optionHeight: 30
	    });
}

//删除表格tr
function del_tr(trId){
	document.getElementById(trId).remove();
}
//提交   类型1 表示提交  2 表示暂存
function buttAdd(type){
	$.ajax({
		type: "POST",
		url:contextpath+'bookdeclare/doBookdeclareAdd.action?stype='+type,
		data:$('#objForm').serialize(),// 你的formid
		async: false,
		dataType:"json",
	    success: function(msg) {
		    if(msg=='OK'){
		    	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
		    }
	    }
	});
}	

//放弃
function buttGive(){
	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
}
