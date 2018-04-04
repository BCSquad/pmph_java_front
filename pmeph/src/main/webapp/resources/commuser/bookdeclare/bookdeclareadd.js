//定义一个全局变量
var jsonStr = "";
jsonStr = "{\"id\":\"bookname\",\"content\":\"选题名称不能为空\"}," ;
$(function () {
    $('#dzdx').selectlist({
        width: 213,
        height: 30,
        optionHeight: 30
    });
    $('#xzly').selectlist({
    	width: 213,
    	height: 30,
    	optionHeight: 30
    });
    $('#sex').selectlist({
    	width: 213,
    	height: 30,
    	optionHeight: 30
    });
    $('#position_profession').selectlist({
    	width: 213,
    	height: 30,
    	optionHeight: 30
    });
    $('#degree').selectlist({
    	width: 213,
    	height: 30,
    	optionHeight: 30
    });
    $('#write_sex').selectlist({
    	width: 90,
    	height: 30,
    	optionHeight: 30
    });
    $('#write_degree').selectlist({
    	width: 90,
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
			"<td><input class='sb_input' style='width: 120px;' maxlength='40' id='write_realname_"+num+"' name='write_realname' placeholder='编者姓名' value=''/></td>"+
			"<td><select id='write_sex_"+num+"'  name='write_sex'>"+
					"<option value='0'>男</option>"+
					"<option value='1'>女</option>"+
				"</select></td>"+
			"<td><input class='sb_input' style='width: 70px;' id='write_price_"+num+"' name='write_price' placeholder='年龄' value=''" +
			"onkeyup='this.value=this.value.replace(/\\D/g,&#39;&#39;)' onafterpaste='this.value=this.value.replace(/\\D/g,&#39;&#39;)'"+
				"onBlur='checkAge(this)' maxlength='3' /></td>"+
			"<td><input class='sb_input' style='width: 120px;' name='write_phone' placeholder='电话' value='' id='write_phone' maxlength='36'/></td>"+
			"<td><select id='write_degree_"+num+"' name='write_degree'>" +
					"<option value='0' selected='selected'>博士</option>" +
					"<option value='1'>硕士</option>" +
					"<option value='2'>学士</option>" +
					"<option value='3'>其他</option>" +
		         "</select></td>"+
			"<td><input class='sb_input' style='width: 180px;' maxlength='36' id='write_position_"+num+"' name='write_position' placeholder='行政职务' value=''/></td>"+
			"<td><input class='sb_input' style='width: 280px;' maxlength='36' id='write_workplace_"+num+"' name='write_workplace' placeholder='工作单位' value=''/>" +
					"<input type='hidden' name='checkbzqk' value='write_realname_"+num+",write_price_"+num+",write_phone_"+num+",write_position_"+num+",write_workplace_"+num+"'/>" +
					"</td>"+
			"<td><div class='add_div'><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('sbbz_"+num+"')\"></div></td>"+
		"</tr>");
	  $table.append($tr);
	  $('#write_sex_'+num).selectlist({
		  	width: 90,
	    	height: 30,
	    	optionHeight: 30
	    });
	  $('#write_degree_'+num).selectlist({
		  	width: 90,
	    	height: 30,
	    	optionHeight: 30
	    });
}

//社外同类书情况表
function add_similar(){
    var num = fnt();
    var $table = $("#similar");
    var $tr = $("<tr id='similar_"+num+"'>"+
        "<td><input class='sb_input' style='width: 230px;' id='similar_bookname_"+num+"' name='similar_bookname'  maxlength='40' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' id='similar_edition_"+num+"' name='similar_edition'  maxlength='2' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' id='similar_author_"+num+"' name='similar_author'  maxlength='100' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' id='similar_booksize_"+num+"' name='similar_booksize'  maxlength='20' value=''/></td>"+
        "<td><input class='sb_input' style='width: 160px;' id='similar_publisher_"+num+"' name='similar_publisher'  maxlength='100' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' id='similar_print_number_"+num+"' name='similar_print_number'  maxlength='20' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' id='similar_price_"+num+"' name='similar_price'  maxlength='20' value=''/></td>"+
        "<td><input class='sb_input' style='width: 130px;' id='similar_publish_date_"+num+"' name='similar_publish_date'  calendar format=\"'yyyy-mm-dd'\" value=''/></td>"+
		"<input type='hidden' name='checkbzqk' value='similar_bookname_"+num+",similar_edition_"+num+",similar_author_"+num+",similar_booksize_"+num+",similar_publisher_"+num+",similar_publish_date_"+num+"'/>" +
        "<td><div class='add_div'><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('similar_"+num+"')\"></div></td>"+
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
    if(type == '2') { //表示暂存
        $.ajax({
            type: "POST",
            url:contextpath+'bookdeclare/doBookdeclareAdd.action?stype='+type,
            data:$('#objForm').serialize(),// 你的formid
            async: false,
            dataType:"json",
            success: function(json) {
                if(json.msg=='OK'){
                    //避免重复点击
                    document.getElementById('buzc').onclick=function(){window.message.warning("请不要重复点击");};
                    document.getElementById('butj').onclick=function(){window.message.warning("请不要重复点击");};

                    window.message.success("添加成功,正在跳转页面");
                    window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
                }
            }
        });
    }else{
        checkLb();
        if(checkNull(jsonStr)){
            //避免重复点击
            document.getElementById('buzc').onclick=function(){window.message.warning("请不要重复点击");};
            document.getElementById('butj').onclick=function(){window.message.warning("请不要重复点击");};
            $.ajax({
                type: "POST",
                url:contextpath+'bookdeclare/doBookdeclareAdd.action?stype='+type,
                data:$('#objForm').serialize(),// 你的formid
                async: false,
                dataType:"json",
                success: function(json) {
                    if(json.msg=='OK'){
                        window.message.success("添加成功,正在跳转页面");
                        window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
                    }
                }
            });
        }
	}
}

//放弃
function buttGive(){
	window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
}

//判断是否为空
function checkNull(jsonStr){
	var s = "["+jsonStr.substring(0, jsonStr.length-1)+"]";
	var objs = $.parseJSON(s);
	var b = true;
	$.each(objs, function(k, obj){
	    var value = $("#"+obj.id).val();
	    if(value == ""){
			layer.tips(obj.content, '#'+obj.id);
			$("#"+obj.id)[0].focus();  //聚焦2
			b = false;
			window.message.warning(obj.content);
			return false; 
		}
	});
	return b;
}

function cleadate(){
	$("#deadline").val(null);
}
//判断年龄是否符合标准
function checkAge(obj){
	var va = obj.value;
	if(va >= 200){
		window.message.warning("年龄不能大于200,请输入正确的年龄");
		$("#"+obj.id)[0].focus();  //聚焦2
		$("#"+obj.id).val(null);
	}
}

//列表填报校验
function checkLb(){
	var map = $('input[name^="checkbzqk"]').map(
			function(){return this.value
		}).get();
	if(map!=null){
		for(var i=0;i<map.length;i++){ 
			var strs= new Array(); //定义一数组 
			strs=map[i].split(","); //字符分割
			
			//遍历
			for ( var j = 0; j < strs.length; j++) {
				if($("#"+strs[j]).val() != ""){//表示有值，		
					for(var k = 0; k < strs.length; k++){
						jsonStr=jsonStr+"{\"id\":\""+strs[k]+"\",\"content\":\"该项不能为空,请填写完整\"},";
					}
					break;
				}
			}
		}
	}
}