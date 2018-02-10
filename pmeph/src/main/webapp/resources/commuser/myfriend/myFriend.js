var addContent="";
var senderName="";
var avatar="";
function show(){
	    document.getElementById("box").setAttribute("class","b show");
        document.getElementById("close").setAttribute("class","hiddenX show");
}
function hide(){
        document.getElementById("box").setAttribute("class","b hidden");
        document.getElementById("close").setAttribute("class","hiddenX hidden");
        $("#content").val("");
}
$(function(){
	//点击按钮
//	$("#sendPersonalMsg").on("click",function(){
//		alert();
//	});
//	
	/*function q1(id,username){
		alert(id);
		alert(username);
	}*/
	
	$(".showTalk").click(function(){
		var frendid = this.id;
		$("#frendId").val(frendid);
		var username = $("#t_"+frendid).val();
		$(".personMessageTitle").html("你与"+username+"的私信窗口");
		$.ajax({
	        type:'get',
	        url :contextpath+'/mymessage/getDialogue.action',
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	friendId : frendid,            
	        },
	        success:function(responsebean){
	        	var content="";
	        	$("#talkList").html('');
	        	if(null != responsebean && responsebean.length >= 0){
	        		for( var i= 0; i<responsebean.length ; i++ ){
	        			var html ="";
	        			if(responsebean[i].isMy){//我发送的
	        				
	        				senderName=responsebean[i].senderName;
	        				avatar=responsebean[i].avatar;
	        				
	        				html = 
		        				"<div class='oneTalk'> "+
		                        "<div class='headAndNameRight float_right'> "+
		                            "<div class='headDiv'><img class='headPicture' src='"+contextpath+responsebean[i].avatar+"'/></div> "+
		                            "<div class='talkName'><text>"+responsebean[i].senderName+"</text></div> "+
		                             /*"<div style=''><text>"+responsebean[i].senderName+"</text></div> "+*/
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
		                            "<div class='headDiv'><img class='headPicture' src='"+contextpath+responsebean[i].avatar+"'/></div> "+
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
	        			$("#talkList").append(html);
	        			//$("#dialogue").animate({scrollTop: '5000px'},500);
	        			setTimeout(function(){
		        			$("#dialogue").scrollTop($("#talkList").height());
		        			//$("#dialogue").scrollTop($("#dialogue")[0].scrollHeight);  
		        			console.log($("#talkList").height());
	        			},0);

	        			content+=html;
	        		}
	        			
	        		
	        		addContent=content;
	        	}
	        	show();
	        	//更新消息状态
	        	$.ajax({
	    	        type:'get',
	    	        url :contextpath+'/mymessage/updateMyTalk.action',
	    	        contentType: 'application/json',
	    	        dataType:'json',
	    	        data:{
	    	        	senderId   : frendid,
	    	        	senderType : 2
	    	        },
	    	        success:function(responsebean){
	    	        	
	    	        }
	    	    });
	        }
	    });
		
	});
	//回车事件
	$(".inputBox").keypress(function (e){ 
//		var code = event.keyCode;
		var theEvent = window.event || e;
		var code = theEvent.keyCode || theEvent.which; 
		if (13 == code) { 
			sendNewMsg(); 
		} 
	}); 
	//发送消息
	$("#sendNewMsg").click(function(){
		sendNewMsg () ;
	});
	
	function sendNewMsg (){
		var content=$("#content").val();
		var sendTime = new Date();
		if(!content || content.trim() ==''){
			window.message.warning("请键入消息");
		}else{
			var frendId =$("#frendId").val();
			$.ajax({
		        type:'post',
		        url :contextpath+'/mymessage/senNewMsg.action',
		        async:false,
		        dataType:'json',
		        data:{
		        	friendId : frendId,
		        	content  : content,
		        	title    : $(".personMessageTitle").html()
		        },
		        success:function(map){
		        	if(map.code=='success'){
		        		//window.message.success('发送成功');
		        		
		        		//var currentContent=$("#dialogue").html();
		        		//$("#dialogue").append(addContent);
			        	//hide();
		        		html = 
	        				"<div class='oneTalk'> "+
	                        "<div class='headAndNameRight float_right'> "+
	                            "<div class='headDiv'><img class='headPicture' src='"+contextpath+avatar+"'/></div> "+
	                            "<div class='talkName'><text>"+map.name+"</text></div> "+
/*	                            "<div class='headDiv'><img class='headPicture' src='"+contextpath+responsebean.avatar+"'/></div> "+
	                            "<div class='talkName'><text>"+responsebean.senderName+"</text></div> "+
*/	                        "</div> "+
	                        "<div class='talkDivRight float_right' > "+
	                            "<div class='sendMessage'> "+

	                                "<div class='textDiv float_right'> "+content+"</div> "+

	                            "</div> "+
	                            "<div class='talkTime talkTimeAlignRight'>"+formatDate(sendTime,'yyyy.MM.dd hh:ss:mm')+"</div> "+
	                        "</div> "+
	                        "</div> ";
		        		//currentContent+=html;
		        		$("#talkList").append(html);
			        	$("#content").val('');
			        	//$("#dialogue").animate({scrollTop: '700px'},500);
			        	//$("#dialogue").scrollTop($("#dialogue")[0].scrollHeight);  
			        	setTimeout(function(){
		        			$("#dialogue").scrollTop($("#talkList").height());
		        			//$("#dialogue").scrollTop($("#dialogue")[0].scrollHeight);  
		        			console.log($("#talkList").height());
	        			},0);
			        	
		        	}
		        }
			});
		}
	}
	
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
	
	
});

