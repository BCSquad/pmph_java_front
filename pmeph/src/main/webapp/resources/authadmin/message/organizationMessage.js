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
                '<img src="'+contextpath+'/statics/testfile/tttt.png" />'+
            '</div>'+
            '<div class="content" >'+
            ' <p class="title" >'+
            '<span class="msg">'+n.NAME+'</span>'+
            '<span class="time">'+n.TIME+'</span>'+
            ' </p>'+
            '<p class="text">'+n.TYPE+'</p>' +
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