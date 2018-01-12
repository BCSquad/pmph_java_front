//跳转
function ChangeDiv(type,groupId){
    if(type=='commnions'){ //互动交流
    	window.location.href=contextpath+"group/toMyGroup.action?groupId="+groupId+"&type=hdjl";
    }else{ //文件共享
    	window.location.href=contextpath+"group/toMyGroup.action?groupId="+groupId+"&type=wjgx";
    }
}

//加载更多
function doMore(count){
	var groupId =$('#groupId').val();
	window.location.href=contextpath+"group/toMyGroup.action?groupId="+groupId+"&type=wjgx&pageSize="+count;
}


$(function(){
	$("#sendMsg").click(function(){
		var content=$("#msgContent").val();
		var group_id=$("#groupId").val();
		if(!content || content.trim() ==''){
			window.message.warning("请键入消息");
			return ;
		}
		$.ajax({
			type: "POST",
			url:contextpath+'group/sendMessage.action',
			data:{group_id:group_id,msg_content:content},// 你的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg=='OK'){
			    	window.location.href=contextpath+"group/toMyGroup.action?groupId="+group_id+"&type=hdjl";
			    }
		    }
		});
	});
	//加载上传方法
	upload();
});

//附件上传方法
function upload(){
	var group_id=$("#groupId").val();
	var type=$("#type").val();
	$("#scwj1").uploadFile({
	    start: function () {
	        console.log("开始上传。。。");
	    },
	    done: function (filename, fileid) {
	    	$.ajax({
				type: "POST",
				url:contextpath+'group/uploadFile.action',
				data:{syllabus_name:filename,syllabus_id:fileid,group_id:group_id},// 你的formid
				async: false,
				dataType:"json",
			    success: function(msg) {
				    if(msg=='OK'){
				    	console.log("上传完成：name " + filename + " fileid " + fileid);
				    	window.location.href=contextpath+"group/toMyGroup.action?groupId="+group_id+"&type=hdjl";
				    }
			    }
			});
	    },
	    progressall: function (loaded, total, bitrate) {
	        console.log("正在上传。。。" + loaded / total);
	    }
	});
}

//退出小组
function quitGroup(groupId){
	$.ajax({
		type: "POST",
		url:contextpath+'group/quitGroup.action',
		data:{groupId:groupId},// 你的formid
		async: false,
		dataType:"json",
	    success: function(msg) {
		    if(msg=='OK'){
		    	window.location.href=contextpath+"group/list.action";
		    }
	    }
	});
}


//邀请好友
function visitFriends(){
	window.location.href=contextpath+"/myFriend/listMyFriend.action";
}

//文件查询
function doSearch(){
	var groupId=$("#groupId").val();
	var search_file=$("#search_file").val();
	var file_name = encodeURI(encodeURI(search_file)); 
	window.location.href=contextpath+"group/toMyGroup.action?groupId="+groupId+"&type=wjgx&file_name="+file_name;
}

//文件下载
function downLoadProxy(fileId){
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}
