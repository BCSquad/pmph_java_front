//document.write('<script src="/pmeph/resources/comm/jquery/jquery.pager.js"></script>');

$(function(){
	var num = parseInt($("#totoal_count").val());
	applyDocAuditQuery(1,"","0");
	
	$("#search_condition_input").keyup(function(event){
		if(event.keyCode ==13){ //回车键弹起事件
			search();
		  }
	});

	//状态选择 全部 进行中 己结束
    $(".status-tab").bind("click", function () {
        $(".selected").removeClass("selected");
        $(this).addClass("selected");
        $("#page-num-temp").val(1);
        if ($("#search_condition_input").val()=="") {
        	$("#query-condition-temp").val("");
		}
        $("#status-temp").val($(this).val());
        applyDocAuditQuery($("#page-num-temp").val(),$("#query-condition-temp").val(),$("#status-temp").val());
        
    });
   
  
});
                	
//向后台请求查询 三个参数是： 当前页  ，查询条件 ， 查询状态           	
function applyDocAuditQuery(n,query_condition,status){
    
    
	$("#page-num-temp").val(n);
	data = {
			pageNum:n,
			queryCon:$("#query-condition-temp").val(),
			queryStatus:$("#status-temp").val(),
			contextpath:contextpath
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'applyDocAudit/approvelninelist.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			$("#nine-block-box-container").html(json.html);
			if ($.trim(json.html) == "") {
				$(".pagination-wrapper").hide();
				$(".no-more").show();
			}else{
				$(".no-more").hide();
				$(".pagination-wrapper").show();
				$(".pagination").css("display","inline-block");
				$(".pageJump").css("display","inline-block");
				$(".pagination").next("div").css("display","inline-block");
			}
			
			$('#page1').html("");	
			$("#totoal_count").html('共'+json.totoal_count+'页');
			//刷新分页栏
			 Page({
                num: json.totoal_count,					//页码数
                startnum: n,				//指定页码
                elem: $('#page1'),
                callback: function (n){     //点击页码后触发的回调函数
                	$("#page-num-temp").val(n);
                	applyDocAuditQuery(n,$("#query-condition-temp").val(),$("#status-temp").val());
                }
                });
		}
	});
};
   
//放大镜按钮（搜索）点击触发事件
function search(){
	//temp才是真正的查询条件 仅仅输入输入框而不点击查询按钮 输入框中的内容不应作为查询条件
	$("#query-condition-temp").val($("#search_condition_input").val());
	$("#page-num-temp").val(1);
	applyDocAuditQuery($("#page-num-temp").val(),$("#query-condition-temp").val(),$("#status-temp").val());
}

//结果统计 按钮点击触发事件 跳转页面
function resultStatistics(id,name){
	window.location.href = contextpath + "declareCount/findDeclareCount.action?material_id="+id;
}

//办理 按钮点击触发事件 跳转页面
function dealWithAudit(id,name,view_audit){
	window.location.href = contextpath + "dataaudit/toPage.action?material_id="+id+"&view_audit="+view_audit;
}

function checkAuthen(id,name,view_audit){
    $.ajax({
        type: "POST",
        url:contextpath+'dataaudit/checkAuthen.action',
        dataType:"json",
        success: function(json) {
            if(json=="OK"){
                dealWithAudit(val,type,auditId,decId);
            }
        }
    });
}