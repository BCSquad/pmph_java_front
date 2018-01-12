$(function(){
	$('#auth_status_select').selectlist({
        zIndex: 10,
        width: 80,
        height: 30,
        optionHeight: 30
    });     
	$("#auth_status_select").css("border","none");
  //切换审批状态时 刷新
	$("#auth_status_select").find("li").bind("click",function(){
		var staging_status = $(this).attr("data-value");
		var ss = staging_status.split("-");
		$("#is_staging").val(ss[0]);
		$("#auth_status").val(ss[1]);
		$("#pageNum").val(1);
		queryMain();
	});
});