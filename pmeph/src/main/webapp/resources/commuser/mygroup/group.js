
function ChangeDiv(type){
    if(type=='commnions'){
        document.getElementById("commnions_top").setAttribute("class","clicked");
        document.getElementById("filesgx_top").setAttribute("class","clickbefore");
        document.getElementById("filesgx").setAttribute("class","hidden");
        document.getElementById("commnions").setAttribute("class","show");
    }else{
        document.getElementById("filesgx_top").setAttribute("class","clicked");
        document.getElementById("commnions_top").setAttribute("class","clickbefore");
        document.getElementById("commnions").setAttribute("class","hidden");
        document.getElementById("filesgx").setAttribute("class","show");
    }


}


$(function(){
	var webSocket = new WebSocket("ws:120.76.221.250:11000/pmpheep/websocket?userType=" +2+"&userId="+$("#userId").val());
	//var webSocket = new WebSocket("ws:127.0.0.1:8036/pmpheep/websocket?userType=" +2+"&userId="+$("#userId").val());
	webSocket.onopen = function(event){
	    console.log("连接成功");
	    console.log(event);
	};
	webSocket.onerror = function(event){
	    console.log("连接失败");
	    console.log(event);
	};
	webSocket.onclose = function(event){
	    console.log("Socket连接断开");
	    console.log(event);
	};
	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function(){
        websocket.close();
    };
	webSocket.onmessage = function(event){
	    //接受来自服务器的消息
	    //...
	    console.log("Socket新消息:"+event.data);
	    var data = $.parseJSON(event.data); 
	    var sender = 2 ; //他人的
	    if(data.senderId == 0){//系统消息
	    	sender = 0; 
	    }else if(data.senderId == $("#userId").val() && data.senderType == 2 ){//我自己的
	    	sender = 1 ;
	    }
	    //是小组消息，小组id相同，类型是新增
	    if(data.msgType == 3 && data.groupId == $("#groupId").val() && data.sendType == 0){
	    	loadNewGroupMsg(sender,data.senderName,data.senderIcon,data.content,data.time);
	    }
	}
	$("#sendMsg").click(function(){
		var content=$("#msgContent").val();
		if(!content || content.trim() ==''){
			window.message.warning("请键入消息");
			return ;
		}
		var s = webSocket.send("{senderId:"+$("#userId").val()+",senderType:"+2+",content:'"+content+"',groupId:"+$("#groupId").val()+",sendType:0}");
		$("#msgContent").val('');
	});
	//-------------------------------
	var talkPagesize  = 5 ;
	var talkPagenumber= 1  ;
	var maxTime       = 0  ;
	//加载对话
	initTalk();
	//加载更对历史消息
	$("#history").click(initTalk);
	
	
	var filePagesize  = 5 ;
	var filePagenumber = 1  ;
	var fileName  =  $("#fileName").val(); 
	//加载文件
	initFile();
	//加载更多文件
	$("#fileMore").click(initFile);
	//搜索文件
	$(".search").click(function(){
		fileName  =  $("#fileName").val();
		filePagenumber = 1  ;
		$("#fileContent").html('');
		initFile();
	});
	//回车搜索文件
	$("#fileName").keypress(function (e){ 
		var code = event.keyCode; 
		if (13 == code) { 
			fileName  =  $("#fileName").val();
			filePagenumber = 1  ;
			$("#fileContent").html('');
			initFile();
		} 
	}); 
	//$("#uploadForm").submit();
	$("#file_upload").change(function(){
	//	$("#uploadForm").submit();
		//var formData = new FormData();
        //formData.append("file",document.getElementById("file_upload").files[0]);
        //formData.append("groupId", $("#groupId").val());
        //formData.append("filee",$("#file_upload"));
		//alert();
		$("#uploadForm").submit();
		return ;
		$.ajax({
			type:'post',
	        url :contxtpath+'/group/d.action',
	        dataType:'json',
	        //Content-Type:'multipart/form-data',
	        data:$('#uploadForm').serialize(),
	        success:function(responsebean){
	        	if(responsebean){
	        		window.message.success("成功");
	        	}else{
	        		window.message.error("失败");
	        	}
	        }
	     });
	});

	//退出小组
	$("#quitGroup").click(function(){
		var yes=confirm("确认退出?");
		if(!yes){
			return ;
		}
		$.ajax({
			type:'get',
	        url :contxtpath+'/group/quitGroup.action',
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	groupId   : $("#groupId").val()
	        },
	        success:function(responsebean){
	        	if(responsebean){
	        		window.message.success("退出成功");
	        		setTimeout(function(){
	        			window.location.href = contxtpath+'/group/list.action';
	        		},800);
	        	}else{
	        		window.message.error("退出失败");
	        	}
	        }
	     });
	});
	//*删除文件
	$('body').on('click','.deleteThis',function(){
		var btId =  this.id;
		var id = btId.replace('bt_','');
		var fileId = $("#"+id).val() ;
		$.ajax({
			type:'get',
	        url :contxtpath+'/group/deleteFile.action',
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	groupId   : $("#groupId").val(),
	        	id: id ,
	        	fileId  : fileId ,
	        },
	        success:function(responsebean){
	        	if(responsebean){
	        		window.message.success("删除成功");
		        	$("#item_"+id).remove();
	        	}else{
	        		window.message.error("删除失败");
	        	}
	        }
	     });
	});
	$('body').on('click','.item_img2',function(){
		var imgId =  this.id;
		var id = imgId.replace('img_','');
		var fileId = $("#"+id).val() ;
		window.location.href = contxtpath+'/groupfile/download/'+fileId+'.action?groupId='+$("#groupId").val() ;
		var num = parseInt($("#dw_"+id).html())+1;
		$("#dw_"+id).html(num);
		return ;
	});
	
	//初始化文件
	function initFile(){
		$.ajax({
			type:'get',
	        url :contxtpath+'/group/getFiles.action',
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	groupId   : $("#groupId").val(),
	        	pageNumber: filePagenumber,
	        	pageSize  : filePagesize ,
	        	fileName  : fileName
	        },
	        success:function(responsebean){
	        	if(responsebean){
	        		for(var i= 0 ; i< responsebean.length ;i++){
	        			var html = "<div class='items' id='item_"+responsebean[i].id+"'> "+
				                        "<div class='item1' style='clear:both;'> "+
				                            "<span><img src='"+contxtpath+"/statics/image/word-(1).png' alt='文件类型' class='item_img1'/><text>"+responsebean[i].fileName+"</text></span> "+
				                            "<span><img src='"+contxtpath+"/statics/image/xztp.png' id='img_"+responsebean[i].id+"' class='item_img2'/><text id='dw_"+responsebean[i].id+"' style='color: #70bcc3;'>"+responsebean[i].download+"</text></span> "+
				                        "</div> "+
				                        "<div class='item2' style='clear:both;'> "+
				                            "<div class='item2_div1'>"+responsebean[i].displayName+" 于 "+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd hh:ss:mm")+"上传文件</div> "+
				                            "<div style='color: #b0b0b0;float: right;'><span class='del_span'></span> "+
				                            (responsebean[i].deletePower?'<a href="javascript:void()"  id="bt_'+responsebean[i].id+'" class="deleteThis" >删除</a> ':'') +
				                            "</div> "+
				                         "</div> "+
				                         "<input type='hidden' id='"+responsebean[i].id+"' value='"+responsebean[i].fileId+"' />"+
				                    "</div> ";
	        			$("#fileContent").append(html);
	        		}
	        		//判断是否还有下一页
	        		if(filePagesize > responsebean.length){
	        			$("#fileMore").hide();
	        		}else{
	        			filePagenumber ++ ;
	        		}
	        	}
	        }
		});
	}
	
	//加载对话的方法
	function initTalk (){
		$.ajax({
			type:'get',
	        url :contxtpath+'/group/getTalks.action',
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	groupId   : $("#groupId").val(),
	        	pageNumber: talkPagenumber,
	        	pageSize  : talkPagesize
	        },
	        success:function(responsebean){
	        	if(responsebean){
	        		
	        		for(var i= 0 ; i< responsebean.length ;i++){
	        			//设置最大时间供后面查询新消息
	        			if ( i== 0) {
	        				maxTime = responsebean[i].gmtCreate;
	        			}
	        			var html ="";
	        			//系统消息
	        			if(responsebean[i].msgType == 0){
	        				html = "<div>"+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd hh:ss:mm")+"  "+responsebean[i].displayName+":"+responsebean[i].msgContent+"</div>";
	        			}else if(responsebean[i].msgType == 1){ //我发送的消息
	        				html = "<div class='chat_items mine'> "+
			                            "<div class='chat_item1'> "+
			                                "<div class='div_item1_img'> "+
			                                    "<img src='"+contxtpath+"/"+responsebean[i].avatar+"'/> "+
			                                    "<text>"+responsebean[i].displayName+"</text> "+
			                                "</div> "+
			                                "<div class='arrows'></div> "+
			                            "</div> "+
			                            "<div class='chat_item2'> "+
			                                "<div class='sender'> "+responsebean[i].msgContent+" </div> "+
			                                "<div class='chat_item2_time'>"+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd hh:ss:mm")+"</div> "+
			                            "</div> "+
			                            "<div class='clear'></div> "+
			                        "</div> ";
	        			}else if(responsebean[i].msgType == 2){ //他人发送的消息
	        				html = "<div class='chat_items other'> "+
			                            "<div class='chat_item1'> "+
			                                "<div class='div_item1_img'> "+
			                                    "<img src='"+contxtpath+"/"+responsebean[i].avatar+"'/> "+
			                                    "<text>"+responsebean[i].displayName+"</text> "+
			                                "</div> "+
			                                "<div class='arrows'></div> "+
			                            "</div> "+
			                            "<div class='chat_item2'> "+
			                                "<div class='sender'>"+responsebean[i].msgContent+"</div> "+
			                                "<div class='chat_item2_time'>"+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd hh:ss:mm")+"</div> "+
			                            "</div> "+
			                            "<div class='clear'></div> "+
			                        "</div> ";
	        			}
	        			$("#history").after(html);
	        		}
	        		//判断是否还有下一页
	        		if(talkPagesize > responsebean.length){
	        			$("#history").hide();
	        		}else{
	        			talkPagenumber ++ ;
	        		}
	        	}
	        }
		});
	}
	//加载小组新的消息
	function loadNewGroupMsg(msgType,senderName,senderIcon,content,time){
		var html ="";
		//系统消息
		if(msgType == 0){
			html = "<div>"+formatDate(time,"yyyy.MM.dd hh:ss:mm")+"  "+senderName+":"+content+"</div>";
		}else if(msgType == 1){ //我发送的消息
			html = "<div class='chat_items mine'> "+
                        "<div class='chat_item1'> "+
                            "<div class='div_item1_img'> "+
                                "<img src='"+contxtpath+"/"+senderIcon+"'/> "+
                                "<text>"+senderName+"</text> "+
                            "</div> "+
                            "<div class='arrows'></div> "+
                        "</div> "+
                        "<div class='chat_item2'> "+
                            "<div class='sender'> "+content+" </div> "+
                            "<div class='chat_item2_time'>"+formatDate(time,"yyyy.MM.dd hh:ss:mm")+"</div> "+
                        "</div> "+
                        "<div class='clear'></div> "+
                    "</div> ";
		}else if(msgType == 2){ //他人发送的消息
			html = "<div class='chat_items other'> "+
                        "<div class='chat_item1'> "+
                            "<div class='div_item1_img'> "+
                                "<img src='"+contxtpath+"/"+senderIcon+"'/> "+
                                "<text>"+senderName+"</text> "+
                            "</div> "+
                            "<div class='arrows'></div> "+
                        "</div> "+
                        "<div class='chat_item2'> "+
                            "<div class='sender'>"+content+"</div> "+
                            "<div class='chat_item2_time'>"+formatDate(time,"yyyy.MM.dd hh:ss:mm")+"</div> "+
                        "</div> "+
                        "<div class='clear'></div> "+
                    "</div> ";
		}
		$(".iframe1").append(html);
	}
	
	//转换时间戳的方法
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