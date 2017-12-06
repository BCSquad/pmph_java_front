
$(function(){

	queryMain();
	$("#search-name").keyup(function(event){
		if(event.keyCode ==13){ //回车键弹起事件
			queryBtnClick();
		  }
	});
});


//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:15,
			queryName:$("#search-name-temp").val(),
			contextpath:contextpath
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'addFriend/addFriendList.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			$("#table-15").html(json.html);
			if (json.html == "") {
				$(".pagination-wrapper").hide();
			}else{
				$(".pagination-wrapper").show();
			}
			$('#page1').html("");	
			$("#totoal_count").html(json.totoal_count);
			//刷新分页栏
			 Page({
                num: json.totoal_count,					//页码数
                startnum: $("#page-num-temp").val(),				//指定页码
                elem: $('#page1'),
                callback: function (n){     //点击页码后触发的回调函数
                	$("#page-num-temp").val(n);
                	queryMain();
                }
                });
		}
	});
}


//查询按钮点击事件触发 
function queryBtnClick(){
	$("#page-num-temp").val(1);
	$("#search-name-temp").val($("#search-name").val());
	queryMain();
}



//添加好友 按钮触发
function addFriendfun(uid,status){
	var data={uid:uid
			,status:status};
	$.ajax({
		type:'post',
		url:contextpath+'addFriend/addFriendfun.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			queryMain();
		}
	});
}

//个人主页 按钮触发
function personalPage(uid){
	window.location.href = contextpath + "personalhomepage/tohomepage.action?userId="+uid;
}

