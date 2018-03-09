$(function(){
	if ($("#pageNumber").val()>1) {
		$("body").scrollTop($("body").css("height").substring(0,$("body").css("height").length-2));
	}
});


//加载更多
function doMore(count){
	window.location.href=contextpath+"group/list.action?pageNumber="+count;
}


