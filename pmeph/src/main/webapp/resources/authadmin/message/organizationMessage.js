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
				str+='<div class="item" id="item'+n.msg_id+'">'+
    			'<div class="item-img">'+
                '<img src="'+contextpath+'statics/testfile/tttt.png" />'+
            '</div>'+
            '<div class="content" style="position:relative;">'+
            ' <p class="title" >'+
            '<span class="msg">'+n.NAME+'</span>'+
            '<span class="time">'+n.TIME+'</span>'+
            ' </p>'+
            '<p class="text" id="txt'+n.msg_id+'" style="width:1000px" onclick="system(\''+n.msg_id+'\',\''+n.NAME+'\',\''+n.TIME+'\')">'+n.TYPE+
            (n.isread==1?'<img src="'+contextpath+'/statics/image/readyes.png"  id="isread'+n.msg_id+'"/>':'')+
            '</p>' +
            (n.NAME=='系统消息'?'<div style="position: absolute;bottom:0px;right: 0px;color: #999999;font-size: 14px;height:20px;" onclick="delmsg(\''+n.msg_id+'\')">'+
	                        '<span style="width:20px;height:20px;float:left;" class="deltag"></span>'+
	                        '<span style="line-height: 20px;">删除</span>'+
	                    '</div>':'')+
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
                var obj= document.getElementById('isread'+str);
                if(!obj&&json.isread=="yes"){
                  	$("#txt"+str).append('<img src="'+contextpath+'/statics/image/readyes.png"  id="isread'+str+'"/>');
                }
                if(json.isread=="yes"){
                	
                }
                $("#bookmistake").show();
            }
        });
    }
}

//点击关闭消息详情窗口
function hideup() {
    $("#bookmistake").hide();
}
//
function delmsg(id){
	  $.ajax({
          type:"post",
          url:contextpath+"AllMessage/delmsg.action?mid="+id,
          async:false,
          dataType:'json',
          success:function(json){
        	  if(json.isdel=="yes"){
        		  $("#item"+id).remove();
        	  }
          }});
}