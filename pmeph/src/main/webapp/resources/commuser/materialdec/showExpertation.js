$(function () {
	var id = $("#expert_type").val();
	queryMaterialMap(id);  //执行查询方法

	var state=$("#printout").val();

        if(state=="out"){
            $("#print").css("display","block");
            $("#return_cause_div").css("display","none");
            //$("#bookmistake").remove();
        }

    var online_progress = $("#online_progress").val();
	if ($("#return_cause_hidden").val().length>0 && state!="out" && online_progress == "4") {

		$("#return_cause_div").fadeIn(800);
	}


    setTimeout(function (){
        //附件上传
        upload();
    },1000);

});

//页面组合方法
function queryMaterialMap(expert_type){
	$.ajax({
		type: "POST",
		url:contextpath+'expertation/queryMaterialMap.action',
		data:{expert_type:expert_type},// 您的formid
		dataType:"json",
	    success: function(json) {
	    	chooseModel(json);
	    }
	});
}

//模块显示与隐藏判断
function chooseModel(data){
    //所在单位意见
    if(data.is_unit_advise_used == "1" && $("#printout").val()!='out'){
        $("#szdwyj").css("display","block");
    }
	//学习经历
	if(data.is_edu_exp_used == "1"){
		$("#zyxxjl").css("display","block");
	}
	//工作经历
	if(data.is_work_exp_used == "1"){
		$("#gzjl").css("display","block");
	}
	//教学经历
	if(data.is_teach_exp_used == "1"){
		$("#jxjl").css("display","block");
	}
	//主要学术兼职
	if(data.is_acade_used == "1"){
		$("#xsjz").css("display","block");
	}
	//上版教材参编情况
	if(data.is_last_position_used == "1"){
		$("#sbjccb").css("display","block");
	}
	//精品课程建设情况
	if(data.is_course_used == "1"){
		$("#gjjpkcjs").css("display","block");
	}
	//主编国家规划教材情况
	if(data.is_national_plan_used == "1"){
		$("#zbgjjgh").css("display","block");
	}
	//其他教材编写情况
	if(data.is_textbook_used == "1"){
        $("#qtjcbxqk").css("display","block");
	}
    //人卫教材编写情况
    if(data.is_pmph_textbook_used == "1"){
        $("#rwsjcbx").css("display","block");
    }
	//科研情况
	if(data.is_research_used == "1"){
		$("#zjkyqk").css("display","block");
	}
	//个人成就情况
	if(data.is_achievement_used == "1"){
		$("#grcjqk").css("display","block");
	}
	//主编学术专著情况
	if(data.is_monograph_used == "1"){
		$("#zbxszz").css("display","block");
	}
	//出版行业获奖情况
	if(data.is_publish_reward_used == "1"){
		$("#publish").css("display","block");
	}
	//SCI论文投稿及影响因子情况
	if(data.is_sci_used == "1"){
		$("#sci").css("display","block");
	}
	//临床医学获奖情况
	if(data.is_clinical_reward_used == "1"){
		$("#clinical").css("display","block");
	}
	//学术荣誉授予情况
	if(data.is_acade_reward_used == "1"){
		$("#acade").css("display","block");
	}
    //参加人卫慕课、数字教材编写情况
    if(data.is_mooc_digital_used == "1"){
        $("#digital").css("display","block");
    }
    //编写内容意向表
    if(data.is_intention_used == "1"){
        $("#intention").css("display","block");
    }
    //主编或参编图书情况
    if(data.is_edit_book_used == "1"){
        $("#zbcbtsqk").css("display","block");
    }

    //文章发表情况（须第一作者，与本专业相关）
    if(data.is_article_published_used == "1"){
        $("#wzfbqk").css("display","block");
    }
    //本专业获奖情况
    if(data.is_profession_award_used == "1"){
        $("#bzyhjqk").css("display","block");
    }

    //学科分类
    if(data.is_subject_type_used == "1"){
        $("#xkflxs").css("display","block");
    }

    //内容分类
    if(data.is_content_type_used == "1"){
        $("#nrflxs").css("display","block");
    }

    //申报专业
    if(data.is_profession_type_used == "1"){
        $("#sbzyxs").css("display","block");
    }

    //文章发表情况
    if(data.is_article_published_used == "1"){
        $("#wzfbqk").css("display","block");
    }

    //本专业获奖情况
    if(data.is_profession_award_used == "1"){
        $("#bzyhjqk").css("display","block");
    }


}

//文件下载
function downLoadProxy(fileId){
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}

//附件上传方法
function upload(){
    $("#dwyjsc").uploadFile({
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
            $("#fileNameDiv").empty(); //清楚内容
            $("#fileNameDiv").append("<span><div class=\"filename whetherfile\"><a href='javascript:' class='filename'  onclick='downLoadProxy(\""+fileid+"\")' title=\""+filename+"\">"+filename+"</a></div></span>");
            $("#fileNameDiv").css("display","inline");
            $("#syllabus_id").val(fileid);
            $("#syllabus_name").val(filename);
            toAudit($("#expertation_id").val(),null,"doNotReLocate");
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

//放弃
function buttGive(){
	window.location.href=contextpath+"expertation/declare.action";
}

//打印按钮
function toprint(eid) {
    $("#return_cause_div").css("display","none");
    $("#tujian00").html($("#unit_advise_online").val());
    $("#ddd").jqprint({
    	  //debug: true, 
          importCSS: true ,
    });
//打印状态
    $.ajax({
        type: 'post',
        url: contextpath + 'expertationList/updPrintStatus.action?t=' + new Date().getTime()+'&did=' + eid ,
        async: false,
        dataType: 'json',
        success: function (json) {

        }
    });

}


//提交   通过3 
function toAudit(id,type,doLocation){
	var user_id=$("#user_id").val();
    var syllabus_id=$("#syllabus_id").val();
    var syllabus_name=$("#syllabus_name").val();
    var unit_advise_online = $("#unit_advise_online").val();
		$.ajax({
			type: "POST",
			url:contextpath+'expertation/doExpertationAuditPass.action',
			data:{expertation_id:id,online_progress:type,user_id:user_id,unit_advise:syllabus_id,syllabus_name:syllabus_name,unit_advise_online:unit_advise_online},// 您的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg.msg=='OK'){
			    	 message.success("成功！");
			    	 
			    	 if(type=='3'){/*
                    	var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep";
                    	$.ajax({
                            type: 'get',
                            url: exportWordBaseUrl + '/frontWxMsg/projectEditorPleaseAdit/'+id,
                            dataType: 'jsonp',
                            jsonp:"callback", //这里定义了callback在后台controller的的参数名
                			jsonpCallback:"getMessage", //这里定义了jsonp的回调函数名。 那么在后台controller的相应方法其参数“callback”的值就是getMessage
                            success:function(wxResult){
                            	if(wxResult=="1"){
                            		//window.message.success("微信消息发送成功");
                            		setTimeout(function(){
                            			toMain();
                            		},800);
                            	}
                            },
                            error:function(XMLHttpRequest, textStatus){
                            	toMain();
                            }
                            });
                         //toMain();
			    	 */
			    		 
			    	 }
			    	 if(!doLocation){
			    		 window.location.href = contextpath+"schedule/scheduleList.action";
			    	 }
			    }else{
			    	message.success("失败了！");
			    }
		    }
		});
}


//点击显示纠错弹窗
function showup(id,type) {
	$("#return_id").val(id);
	$("#return_type").val(type);
    $.ajax({
        type: 'post',
        url: contextpath + 'dataaudit/tologin.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json == "OK") {
                $("#bookmistake").show();
            }
        }
    });
}


//点击弹窗隐藏
function hideup() {
    $("#bookmistake").hide();
}

//退回原因确认 
function correction() {
	var user_id=$("#user_id").val();
	var expertation_id = $("#return_id").val();
	var online_progress = $("#return_type").val();//表示驳回  2 
	var return_cause = $("#return_cause").val();

            if (!Empty(return_cause)) {//非空判断
                var json = {
                		expertation_id: expertation_id,
                		online_progress: online_progress,
                		return_cause: return_cause,
                		user_id:user_id
                };
                $.ajax({
                    type: 'post',
                    url: contextpath+'expertation/doExpertationAuditPass.action',
                    data: json,
                    async: false,
                    dataType: 'json',
	                success: function(msg) {
	    			    if(msg.msg=='OK'){
	    			    	 message.success("成功！");
	    			    	 $("#return_cause").val(null);
	    			    	 $("#bookmistake").hide();
	    			    	 window.location.href = contextpath+"schedule/scheduleList.action";
	    			    }else{
	    			    	message.success("失败！");
	    			    }
	    		    }
                });
            } else {
                window.message.info("请输入退回原因！");
            }
}

//单位所在意见为空  附件上传
function toAuditPass(id,type) {
   var sid= $("#syllabus_id").val();
	if (sid==null||sid=="") {
        var msg ='<font color="red" >单位所在意见为空</font>&nbsp;【确认】进行审核,【取消】可继续上传!';
        window.message.confirm(msg,{icon: 7, title:'提示',btn:["确定","取消"]}
            ,function(index){
                layer.close(index);
                toAudit(id,type);
            }
        );
	}else{
        toAudit(id,type);
	}
}

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){
	var maxStrlength ;
	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"a").length <= ml) {
			maxStrlength=i;
		}else{

			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"a").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		//window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}

