//定义一个全局变量

// var jsonRequiredEleId = [
//                         {id:"bookname",content:"选题名称不能为空"},
//                         {id:"realname",content:"主编姓名不能为空"},
//                         {id:"price",content:"年龄不能为空"},
//                         {id:"position",content:"行政职务不能为空"},
//                         {id:"workplace",content:"工作单位不能为空"},
//                         {id:"phone",content:"电话不能为空"},
//                         {id:"email",content:"邮箱不能为空"},
//                         {id:"address",content:"通讯地址不能为空"},
//                         {id:"extra_achievement",content:"主要专业成就及学术地位不能为空"},
//                         {id:"extra_reason",content:"选题理由及出版价值不能为空"},
//                         {id:"extra_score",content:"主要内及特色不能为空"},
//                          ];

$(function () {

    setTimeout(function () {
        $('#bookname').tipso({validator: "isNonEmpty", message: "选题名称不能为空"});
        $('#realname').tipso({validator: "isNonEmpty", message: "主编姓名不能为空"});
        $('#price').tipso({validator: "isNonEmpty", message: "年龄不能为空"});
        $('#position').tipso({validator: "isNonEmpty", message: "行政职务不能为空"});
        $('#workplace').tipso({validator: "isNonEmpty", message: "工作单位不能为空"});
        $('#email').tipso({validator: "isNonEmpty|isEmail", message: "邮箱不能为空|邮箱格式不正确"});
        $('#postcode').tipso({validator: "isNonEmpty", message: "邮编不能为空"});
        $('#address').tipso({validator: "isNonEmpty", message: "姓名不能为空"});
        $('#extra_achievement').tipso({validator: "isNonEmpty", message: "主要专业成就及学术地位不能为空"});
        $('#extra_reason').tipso({validator: "isNonEmpty", message: "选题理由及出版价值不能为空"});
        $('#extra_score').tipso({validator: "isNonEmpty", message: "主要内及特色不能为空"});
        $('#phone').tipso({validator: "isNonEmpty", message: "电话号码不能为空"})
        $('#dzdx').tipso({validator: "isNonEmpty", message: "请选择读者对象"});
        $('#xzly').tipso({validator: "isNonEmpty", message: "请选择选题来源"});
        $('#sex').tipso({validator: "isNonEmpty", message: "请选择性别"});
        $('#position_profession').tipso({validator: "isNonEmpty", message: "请选择专业职务"});
        $('#degree').tipso({validator: "isNonEmpty", message: "请选择专业职务"});
    },0)


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
    var twriteCount = $("#twriteCount").val();
    for(var i =1 ;i<=twriteCount;i++){
        $('#write_sex_'+i).selectlist({
            width: 90,
            height: 30,
            optionHeight: 30
        });
        $('#write_degree_'+i).selectlist({
            width: 90,
            height: 30,
            optionHeight: 30
        });
    }
    

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
        "<option value=''>-请选择-</option>" +
        "<option value='0'>男</option>"+
        "<option value='1'>女</option>"+
        "</select></td>"+
        "<td><input class='sb_input' style='width: 70px;' id='write_price_"+num+"' name='write_price' placeholder='年龄' value=''" +
        "onkeyup='this.value=this.value.replace(/(\\D|^0+)/g,&#39;&#39;);this.value=this.value.replace(/^[^0-1]\\d{2}$/g,199);' onafterpaste='this.value=this.value.replace(/(\\D|^0+)/g,&#39;&#39;);this.value=this.value.replace(/^[^0-1]\\d{2}$/g,199);'"+
        "onBlur='checkAge(this)' maxlength='3' /></td>"+
		"<td><input class='sb_input' style='width: 120px;' name='write_phone' placeholder='电话' value='' id='write_phone_"+num+"' maxlength='36'/></td>"+
        "<td><select id='write_degree_"+num+"' name='write_degree'>" +
        "<option value=''>-请选择-</option>" +
        "<option value='0'>博士</option>" +
        "<option value='1'>硕士</option>" +
        "<option value='2'>学士</option>" +
        "<option value='3'>其他</option>" +
        "</select></td>"+
        "<td><input class='sb_input' style='width: 180px;' maxlength='36' id='write_position_"+num+"' name='write_position' placeholder='行政职务' value=''/></td>"+
        "<td><input class='sb_input' style='width: 280px;' maxlength='36' id='write_workplace_"+num+"' name='write_workplace' placeholder='工作单位' value=''/>" +
        "<input type='hidden' name='checkbzqk' value='write_realname_"+num+",write_sex_"+num+",write_price_"+num+",write_phone_"+num+",write_degree_"+num+",write_position_"+num+",write_workplace_"+num+"'/>" +
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
        "<td><input class='sb_input' style='width: 230px;' placeholder=\"书名\" id='similar_bookname_"+num+"' name='similar_bookname'  maxlength='40' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' placeholder=\"版次\" id='similar_edition_"+num+"' name='similar_edition'  maxlength='2' value='' onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\"/></td>"+
        "<td><input class='sb_input' style='width: 80px;' placeholder=\"作者\" id='similar_author_"+num+"' name='similar_author'  maxlength='100' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' placeholder=\"开本\" id='similar_booksize_"+num+"' name='similar_booksize'  maxlength='20' value=''/></td>"+
        "<td><input class='sb_input' style='width: 160px;' placeholder=\"出版单位\" id='similar_publisher_"+num+"' name='similar_publisher'  maxlength='100' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' placeholder=\"印数\" id='similar_print_number_"+num+"' name='similar_print_number'  maxlength='20' value=''/></td>"+
        "<td><input class='sb_input' style='width: 80px;' placeholder=\"定价\" id='similar_price_"+num+"' name='similar_price'  maxlength='20' value=''/></td>"+
        "<td><input class='sb_input' style='width: 130px;' placeholder=\"出版时间\" id='similar_publish_date_"+num+"' name='similar_publish_date'  calendar format=\"'yyyy-mm-dd'\" value=''/></td>"+
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
            data:$('#objForm').serialize(),// 您的formid
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
        //checkLb();
        if($.fireValidator()){
            //避免重复点击
            document.getElementById('buzc').onclick=function(){window.message.warning("请不要重复点击");};
            document.getElementById('butj').onclick=function(){window.message.warning("请不要重复点击");};
            $.ajax({
                type: "POST",
                url:contextpath+'bookdeclare/doBookdeclareAdd.action?stype='+type,
                data:$('#objForm').serialize(),// 您的formid
                async: false,
                dataType:"json",
                success: function(json) {
                    if(json.msg=='OK'){
                        window.message.success("添加成功,正在跳转页面");
                        
                        var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep";
                    	$.ajax({
                            type: 'get',
                            url: exportWordBaseUrl + '/frontWxMsg/topicSubmit/'+json.topic_id+"/"+json.user_id,
                            dataType: 'jsonp',
                            success:function(wxResult){
                            	if(wxResult){
                            		window.message.success("微信消息发送成功");
                            		setTimeout(function(){
                                        window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
    								}, 800);
                            	}
                            },
                            error:function(XMLHttpRequest, textStatus){
                            	setTimeout(function(){
                                    window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wycs";
								}, 800);
                            }
                            });
                    	
                        
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
function checkNull(jsonRequiredEleId){
	var objs = jsonRequiredEleId;
    var b = true;
    $.each(objs, function(k, obj){
		var $t = $("#"+obj.id);
		var value = "";
		if ($t.hasClass("select-button")) { //selectlist插件生成的显性input，仅作定位
			value = $t.siblings("input").val(); //取值应从同级的隐藏input里取
		}else{
			value = $("#"+obj.id).val();
		}
	    
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
	//下拉类必填项的name数组
    var mustSelectEleName = ['reader','source']/*,'sex','write_sex','position_profession','degree','write_degree']*/;
    for ( var i in mustSelectEleName) {
    	//找到使用过selectlist插件后生成的外包div
    	var $div = $("input[name='"+mustSelectEleName[i]+"']").parent("div.select-wrapper");
    	//可能有多个外包，循环遍历
    	if ($div.length>0) {
    		$div.each(function(){
        		var $t = $(this); //单个div
        		//找到其中的可见的input作为提示框定位元素
        		$t.find("input[type='button']").attr("id",$t.attr('id')+"_input");
        		//找到label的名字
        		var label = $.trim($t.parent("td").find(".btbs,.btbs1").parent("span").text().replace(/[：|\*|\s]*/g,""));
        		//将定位元素id放入非空判断json组，在调用非空判断时，若为selectlist类的input，其value值将从隐藏input而非此定位input取
        		jsonRequiredEleId.push({id:($t.attr('id')+"_input"),content:"请选择"+label+"！"});
        	});
		}
	}
    
    var map = $('input[name^="checkbzqk"]').map(
        function(){return this.value
        }).get();
    if(map!=null){
        for(var i=0;i<map.length;i++){
            var strs= new Array(); //定义一数组
            strs=map[i].split(","); //字符分割

            //遍历
            for ( var j = 0; j < strs.length; j++) {
                if($("#"+strs[j]).length>0 && $("#"+strs[j]).val().length>0){//表示有值，
                    for(var k = 0; k < strs.length; k++){
                    	//判断是否是下拉选择框
                    	var $selec = $("#"+strs[k]).find("input.select-button");
                    	$selec.attr("id",strs[k]+"_input");
                    	if ($selec.length>0) {
                    		jsonRequiredEleId.push({id:$selec.attr("id"),content:"请选择"});
						}else{
							jsonRequiredEleId.push({id:strs[k],content:"该项不能为空,请填写完整"});
						}
                    }
                    break;
                }
            }
        }
    }
}