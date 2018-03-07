function loadMore(){
	//var pathName=window.document.location.pathname;  
	var para = $("#startPara").val();
	var startPara;
	if(""==para){
		startPara=8;
	}else{
		startPara = parseInt(para)+8;
	}
	$.ajax({
		type:"post",
		url:contextpath+"AllMessage/loadMore.action",
		data:{"startPara":startPara},
		async:false,
		dataType:'json',
		success:function(json){
			$("#startPara").val(startPara);
			var str= '';
			$.each(json.list,function(i,n){
				str+='<div class="item">'+
    			'<div class="item-img">'+
                '<img src="'+contextpath+'statics/testfile/tttt.png" />'+
            '</div>'+
            '<div class="content" >'+
            ' <p class="title" >'+
            '<span class="msg">'+n.NAME+'</span>'+
            '<span class="time">'+n.TIME+'</span>'+
            ' </p>'+
            '<p class="text" onclick="system(\''+n.msg_id+'\',\''+n.NAME+'\',\''+n.TIME+'\')">'+n.TYPE+'</p>' +
            '</div>'+
        '</div>';
		});
			$("#message-list").append(str);
			if(json.listSize<8){
				$("#load-more").hide();
			}
			
		}
	});
}

//点击显示系统消息
function system(str,name,time) {
    if(str!=''&&str!=''){
        $.ajax({
            type:"post",
            url:contextpath+"AllMessage/msg.action?mid="+str,
            async:false,
            dataType:'json',
            success:function(json){
                $("#titlec").html(name);
                $("#timec").html(time);
                $("#tcontent").html(json.msg);
                $("#bookmistake").show();
            }
        });
    }
}

//点击关闭消息详情窗口
function hideup() {
    $("#bookmistake").hide();
}