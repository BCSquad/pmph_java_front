
var addAvatar="";
var addName="";
var addhtml="";
$(function() {

	var pageSize = 5;
	var pageNumber = 1;
	
	
	$(window).scroll(function() {
        var top = $(window).scrollTop()+150;
        var left= $(window).scrollLeft()+300;
        $("#box").css({ left:left + "px", top: top + "px" });
    });

	$('select').selectlist({
        zIndex: 10,
        width: 70,
        height: 20,
        optionHeight: 20,
		triangleColor:'#333333',
		onChange:function (){
			pageNumber = 1 ;
			$("#loadMore").show();
			$("#list").html('');
			init();
		}	
	});
	//初始化数据
	init();
	// 更多
	$("#loadMore").click(function() {
		init();
	});
	//状态改变
	/*$("#select").change(function(){
		pageNumber = 1 ;
		$("#loadMore").show();
		$("#list").html('');
		init();
	});*/
	//打开对话框
	$('.openTallk').click(function(){
		var frendid = this.id;
		var type     = $("#type_"+frendid).val();
		var username = $("#name_"+frendid).val();
		$("#dialogue").html('');
		$(".personMessageTitle").html("你与"+username+"的私信窗口");
		$("#box"  ).attr("class","b show");
		$("#close").attr("class","hiddenX show");
		$("#talk").val(frendid) ;
		$.ajax({
	        type:'get',
	        url :contextpath+'/mymessage/getDialogue.action',
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	friendId   : frendid,
	        	friendType : type
	        },
	        success:function(responsebean){
	        	$("#dialogue").html('');
	        	if(null != responsebean && responsebean.length >= 0){
	        		var content="";
	        		for( var i= 0; i<responsebean.length ; i++ ){
	        			var html ="";
	        			if(responsebean[i].isMy){//我发送的
	        				html = "<div class='oneTalk'> "+
						                "<div class='headAndNameRight float_right'> "+
						                /*"<div class='headDiv'><img class='headPicture' src='"+contextpath+'/'+responsebean[i].avatar+"'/></div> "+*/
						                    "<div class='headDiv'><img class='headPicture' src='"+contextpath+"statics/image/putongyhtouxiang.png"+"'/></div> "+
						                    "<div class='talkName'><text>"+responsebean[i].senderName+"</text></div> "+
						                "</div> "+
						
						                "<div class='talkDivRight float_right' > "+
						                    "<div class='sendMessage'> "+
						                    	"<div class='textDiv float_right'> "+
						                    	   responsebean[i].content+
						                        "</div> "+
						                      "</div> "+
						                    "<div class='talkTime talkTimeAlignRight'>"+formatDate(responsebean[i].sendTime)+"</div> "+
						                "</div> "+
						            "</div> ";
		        				
	        			}else{
	        				html = "<div class='oneTalk'> "+
						                "<div class='headAndNameLeft float_left'> "+
						                   /* "<div class='headDiv'><img class='headPicture' src='"+contextpath+'/'+responsebean[i].avatar+"'/></div> "+*/
						                  "<div class='headDiv'><img class='headPicture' src='"+contextpath+"statics/image/putongyhtouxiang.png"+"'/></div> "+
						                    "<div class='talkName'><text>"+responsebean[i].senderName+"</text></div> "+
						                "</div> "+
						                "<div class='talkDiv float_left'> "+
						                    "<div class='sendMessage'> "+
						                       "<div class='textDiv float_left'> "+
						                       responsebean[i].content+
						                        "</div> "+
						                    "</div> "+
						                    "<div class='talkTime talkTimeAlignLeft'>"+formatDate(responsebean[i].sendTime)+"</div> "+
						                "</div> "+
						            "</div> ";
	        			}
	        			
	        			$("#dialogue").append(html);
	        			content+=html;
	        		}
	        		
	        		addhtml=content;
	        		//更新消息状态
		        	$.ajax({
		    	        type:'get',
		    	        url :contextpath+'/mymessage/updateMyTalk.action',
		    	        contentType: 'application/json',
		    	        dataType:'json',
		    	        data:{
		    	        	senderId   : frendid,
		    	        	senderType : type
		    	        },
		    	        success:function(responsebean){
		    	        	
		    	        }
		    	    });
	        	}
	        }
	    });
		//document.getElementById("box").setAttribute("class", "b show");
		//document.getElementById("close").setAttribute("class", "hiddenX show");
	});
	//回车事件
	$(".inputBox").keypress(function (e){ 
		var code = event.keyCode; 
		if (13 == code) { 
			sendNewMsg(addhtml); 
		} 
	}); 
	//发送信息
	$("#sendNewMsg").click(function(){
		sendNewMsg (addhtml) ;
	});
	//关闭对话框 
	$('#hide').click(function(){
		hide();
	});
	
	function hide(){
		//document.getElementById("box").setAttribute("class", "b hidden");
		//document.getElementById("close").setAttribute("class", "hiddenX hidden");
		$("#box"  ).attr("class","b hidden");
		$("#close").attr("class","hiddenX hidden");
	}
	
	function init() {
		$.ajax({
					type : 'get',
					url : contextpath + '/mymessage/tolist.action',
					async : false,
					contentType : 'application/json',
					dataType : 'json',
					data : {
						pageNumber : pageNumber,
						pageSize : pageSize,
						state : $("input[name='select']") .val()
					},
					success : function(res) {
						if ( res) {
							pageNumber++ ;
							if (res.length < pageSize) {
								$("#loadMore").hide();
							}
							if(res.length>0){
								
								for (var i = 0; i < res.length; i++) {
									
									var  html ="";
									html += "<tr><th rowspan='2' class='headPortrait'><img class='pictureNotice' src='"+contextpath+"statics/image/putongyhtouxiang.png"
											/*+ res[i].avatar*/
											+ "'></th><td class='name'><span>"
											+ res[i].name
											+ "</span><span class='time1'>"
											+ formatDate(res[i].sendTime, "")
											+ "</span></td></tr>";
									html += "<tr><td colspan='2' class='personMessageContent'>私信内容："
											+ res[i].content
											+ '</td><td class="buttonDetail"><div class="buttonAccept" ><a class="a openTallk" id="'+res[i].talkId+'" href="javascript:" >查看详情</a></div></td></tr>';
									html += "<tr><td colspan='4' align='center'><hr class='line'></td></tr>";
									html +="<input id='name_"+res[i].talkId+"' type='hidden' value='"+res[i].name+"'/><input id='type_"+res[i].talkId+"' type='hidden'value='"+res[i].type+"' />";
									$("#list").append(html);
								}
							}else{
								var  html ="";
									 html+="<tr><td><div class='no-more'><img src='"+contextpath+"statics/image/aaa4.png'/><span>木有内容呀~~</span></div></td></tr>";
								     $("#list").append(html);
							}
						}
					}
				})
	}
	
	function sendNewMsg (addhtml){
		$("#dialogue").html('');
		var content=$("#content").val();
		var sendTime = new Date();
		if(!content || content.trim() ==''){
			window.message.warning("请键入消息");
		}else{
			var frendId =$("#talk").val();
			$.ajax({
		        type:'post',
		        url :contextpath+'/mymessage/senNewMsg.action',
		        async:false,
		        dataType:'json',
		        data:{
		        	friendId : frendId,
		        	friendIdType : $('#type_'+frendId).val(),
		        	content : content,
		        	title: $(".personMessageTitle").html()
		        },
		        success:function(map){
		        	if(map.code=='success'){
		        		//window.message.success('发送成功');
			        	//hide();
		        		
		        		html = "<div class='oneTalk'> "+
		                "<div class='headAndNameRight float_right'> "+
		                    /*"<div class='headDiv'><img class='headPicture' src='"+contextpath+'/'+map.avatar+"'/></div> "+*/
		                	"<div class='headDiv'><img class='headPicture' src='"+contextpath+"statics/image/putongyhtouxiang.png'/></div>"+
		                    "<div class='talkName'><text>"+map.name+"</text></div> "+
		                "</div> "+
		
		                "<div class='talkDivRight float_right' > "+
		                    "<div class='sendMessage'> "+
		                    	"<div class='textDiv float_right'> "+
		                    	content+
		                        "</div> "+
		                      "</div> "+
		                    "<div class='talkTime talkTimeAlignRight'>"+formatDate(sendTime)+"</div> "+
		                "</div> "+
		            "</div> ";
		        		
		        		$("#dialogue").append(addhtml);	
		        		$("#dialogue").append(html);	
			        	$("#content").val('');
		        	}
		        }
			});
		}
	}
	
	function formatDate(nS, str) {
		if (!nS) {
			return "";
		}
		var date = new Date(nS);
		var year = date.getFullYear();
		var mon = date.getMonth() + 1;
		var day = date.getDate();
		var hours = date.getHours();
		var minu = date.getMinutes();
		var sec = date.getSeconds();
		if (str == 'yyyy-MM-dd') {
			return year + '-' + (mon < 10 ? '0' + mon : mon) + '-'
					+ (day < 10 ? '0' + day : day);
		} else if (str == 'yyyy.MM.dd') {
			return year + '.' + (mon < 10 ? '0' + mon : mon) + '.'
					+ (day < 10 ? '0' + day : day);
		} else {
			return year + '-' + (mon < 10 ? '0' + mon : mon) + '-'
					+ (day < 10 ? '0' + day : day) + ' '
					+ (hours < 10 ? '0' + hours : hours) + ':'
					+ (minu < 10 ? '0' + minu : minu) + ':'
					+ (sec < 10 ? '0' + sec : sec);
		}
	}
	
});


