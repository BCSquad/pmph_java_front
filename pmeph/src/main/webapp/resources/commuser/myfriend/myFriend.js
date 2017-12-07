
function show(){
	    document.getElementById("box").setAttribute("class","b show");
        document.getElementById("close").setAttribute("class","hiddenX show");
}
function hide(){
        document.getElementById("box").setAttribute("class","b hidden");
        document.getElementById("close").setAttribute("class","hiddenX hidden");
}
$(function(){
	//点击按钮
//	$("#sendPersonalMsg").on("click",function(){
//		alert();
//	});
//	
	function q1(id,username){
		alert(id);
		alert(username);
	}
	
	$(".showTalk").click(function(){
		var frendid = this.id;
		$("#frendId").val(frendid);
		var username = $("#t_"+frendid).val();
		$(".personMessageTitle").html("你与"+username+"的私信窗口");
		$.ajax({
	        type:'get',
	        url :contxtpath+'/mymessage/getDialogue.action',
	        async:false,
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	friendId : frendid,
	        },
	        success:function(responsebean){
	        	$("#dialogue").html('');
	        	if(null != responsebean && responsebean.length >= 0){
	        		for( var i= 0; i<responsebean.length ; i++ ){
	        			var html ="";
	        			if(responsebean[i].isMy){//我发送的
	        				html = 
		        				"<div class='oneTalk'> "+
		                        "<div class='headAndNameRight float_right'> "+
		                            "<div class='headDiv'><img class='headPicture' src='"+responsebean[i].avatar+"'/></div> "+
		                            "<div class='talkName'><text>"+responsebean[i].senderName+"</text></div> "+
		                        "</div> "+
		                        "<div class='talkDivRight float_right' > "+
		                            "<div class='sendMessage'> "+
	
		                                "<div class='textDiv float_right'> "+responsebean[i].content+"</div> "+
	
		                            "</div> "+
		                            "<div class='talkTime talkTimeAlignRight'>"+formatDate(responsebean[i].sendTime,'yyyy.MM.dd hh:ss:mm')+"</div> "+
		                        "</div> "+
		                        "</div> ";
	        			}else{
	        				html =
	        				"<div class='oneTalk'> "+
		                        "<div class='headAndNameLeft float_left'> "+
		                            "<div class='headDiv'><img class='headPicture' src='"+responsebean[i].avatar+"'/></div> "+
		                            "<div class='talkName'><text>"+responsebean[i].senderName+"</text></div> "+
		                        "</div> "+
	
		                        "<div class='talkDiv float_left' > "+
		                            "<div class='sendMessage'> "+
		                               "<!-- <div class='triangle leftTriangle float_left'></div>--> "+
		                                "<div class='textDiv float_left'> "+responsebean[i].content+"</div> "+
		                            "</div> "+
		                            "<div class='talkTime talkTimeAlignLeft'>"+formatDate(responsebean[i].sendTime,'yyyy.MM.dd hh:ss:mm')+"</div> "+
		                        "</div> "+
	                        "</div> ";
	        			}
	        			$("#dialogue").append(html);
	        		}
	        	}
	        	show();
	        }
	    });
		
	});
	
	$("#sendNewMsg").click(function(){
		var content=$("#content").val();
		if(!content || content.trim() ==''){
			window.message.warning("请键入消息");
		}else{
			var frendId =$("#frendId").val();
			$.ajax({
		        type:'get',
		        url :contxtpath+'/mymessage/senNewMsg.action',
		        async:false,
		        contentType: 'application/json',
		        dataType:'json',
		        data:{
		        	friendId : frendId,
		        	content  : content,
		        	title    : $(".personMessageTitle").html()
		        },
		        success:function(responsebean){
		        	if(responsebean=='success'){
		        		window.message.success('发送成功');
			        	hide();
			        	$("#content").val('')
		        	}
		        }
			});
		}
	});
	
	function formatDate(nS,str) {
		  if(!nS){
		    return "";
		  }
		  var date=new Date(nS);
		  var year=date.getFullYear();
		  var mon = date.getMonth()+1;
		  var day = date.getDate();
		  var hours = date.getHours();
		  var minu = date.getMinutes();
		  var sec = date.getSeconds();
		  if(str=='yyyy-MM-dd'){
			  return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day);
		  }else if(str=='yyyy.MM.dd'){
			  return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day);
		  }else if(str=='yyyy.MM.dd hh:ss:mm'){
			  return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
		  }else{
			  return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
		  }

	}
	
})

