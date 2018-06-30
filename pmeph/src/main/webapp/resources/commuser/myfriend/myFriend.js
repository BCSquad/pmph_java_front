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

	var updateDialog = function (frendid, type) {
		$.ajax({
			type: 'get',
			url: contextpath + 'mymessage/getDialogue.action',
			contentType: 'application/json',
			dataType: 'json',
			data: {
				friendId: frendid,
				friendType: '2'
			},
			success: function (responsebean) {
				$("#talkList").html('');
				if (null != responsebean && responsebean.length >= 0) {
					var content = "";
					$.each(responsebean, function (i, n) {
						var html = "";
						if (n.isMy) {//我发送的
							avatar = n.avatar;
							html = "<div class='oneTalk'> " +
								"<div class='headAndNameRight float_right'> " +
								"<div class='headDiv'><img class='headPicture' src='" + contextpath + n.avatar + "'/></div> " +
								"<div class='talkName'><text>" + n.senderName + "</text></div> " +
								"</div> " +

								"<div class='talkDivRight float_right' > " +
								"<div class='sendMessage'> " +
								"<div class='textDiv float_right'> " +
								n.content +
								"</div> " +
								"</div> " +
								"<div class='talkTime talkTimeAlignRight'>" + formatDate(n.sendTime) + "</div> " +
								"</div> " +
								"</div> ";

						} else {
							html = "<div class='oneTalk'> " +
								"<div class='headAndNameLeft float_left'> " +
								"<div class='headDiv'><img class='headPicture' src='" + contextpath + n.avatar + "'/></div> " +
								"<div class='talkName'><text>" + n.senderName + "</text></div> " +
								"</div> " +
								"<div class='talkDiv float_left'> " +
								"<div class='sendMessage'> " +
								"<div class='textDiv float_left'> " +
								n.content +
								"</div> " +
								"</div> " +
								"<div class='talkTime talkTimeAlignLeft'>" + formatDate(n.sendTime) + "</div> " +
								"</div> " +
								"</div> ";
						}

						$("#talkList").append(html);
						content += html;
						//$("#dialogue").scrollTop($("#dialogue")[0].scrollHeight);
						//$("#dialogue").animate({scrollTop: '700px'},500);
						setTimeout(function () {
							$("#dialogue").scrollTop($("#talkList").height());
						}, 0);
					});

					addhtml = content;
					//更新消息状态
					$.ajax({
						type: 'get',
						url: contextpath + 'mymessage/updateMyTalk.action',
						contentType: 'application/json',
						dataType: 'json',
						data: {
							senderId: frendid,
							senderType: '2'
						},
						success: function (responsebean) {

						}
					});
				}
			}
		})
	}


	$(".showTalk").click(function(){
		var frendid = this.id;
		$("#frendId").val(frendid);
		var username = $("#t_"+frendid).val();
		$(".personMessageTitle").html("您与"+username+"的私信窗口");
		$("#box").attr("class", "b show");
		$("#close").attr("class", "hiddenX show");
		$("#talk").val(frendid);
		updateDialog(frendid);
		
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
            layer.msg('请键入消息');
		}else{
			var frendId =$("#frendId").val();
			$.ajax({
		        type:'post',
		        url :contextpath+'/mymessage/senNewMsg.action',
		        async:false,
		        dataType:'json',
		        data:{
		        	friendId : frendId,
					friendIdType : '2',
		        	content  : content,
		        	title    : $(".personMessageTitle").html()
		        },
		        success:function(map){
		        	if(map.code=='success'){
		        		//window.message.success('发送成功');
						updateDialog(frendId);
						setTimeout(function () {
							$("#content").val("");
						}, 0);
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

