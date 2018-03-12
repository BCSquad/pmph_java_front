



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

function showAllGroupMember(){
	
	$(".show_Allgroupmember").css({"display":"block"});
	$("#show_All_Memeber").text("");
}
$(function(){
    /*if(!WebSocket){
	      console.error('浏览器不支持websocket')
     };*/
	var userId    = $("#userId").val(); 
    var webSocket = undefined;
    
    try {
        if (WebSocket) {
            webSocket = new WebSocket("ws://120.76.221.250:11000/pmpheep/websocket?userType=2&userId=" + userId);
        }
    } catch (e) {
    	 //不支持websocket ie10以下版本 
    	webSocket = new Object();
    	
    	webSocket.send=function(sendJson){
    		var data= eval('(' + sendJson + ')');
    		$.ajax({
    			type:'post',
    	        url :contxtpath+'/group/webSocketSentForIE.action?t=' + new Date(),
    	        
    	        dataType:'json',
    	        data:data,
    	        success:function(json){
    	        	//{sender=1, content=1, logUserId=2, groupId=55, time=2018-03-12 21:21:47.0, senderName=曾若男, senderIcon=DEFAULT, senderType=2, msgId=4200, sendType=0, senderId=2, member_id=11181}
    	        	if(json.senderIcon==''||json.senderIcon=='DEFAULT'||json.senderIcon.indexOf('statics')!=-1||json.senderIcon.indexOf('default_image')!=-1||json.senderIcon.indexOf('png')!=-1){
    	        		json.senderIcon = contxtpath+'/statics/image/default_image.png';
    		    	}else{
    		    		json.senderIcon = contxtpath+'/image/'+json.senderIcon+'.action';
    		    	}
    	        	loadNewGroupMsg(json.sender,json.senderName,json.senderIcon,json.content,json.time);
    	        }
    	     });
    		
    	}
    	webSocket.close = function(){};
    	//("{senderId:"+userId+",senderType:"+0+",content:'\""+$("#userName").val()+"\"退出了小组"+"',groupId:"+$("#groupId").val()+",sendType:0}");
	
    }
    
    

	//var webSocket = new WebSocket("ws:127.0.0.1:8036/pmpheep/websocket?userType=" +2+"&userId="+$("#userId").val());
    if (webSocket) {
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
    	webSocket.close();
    };
	webSocket.onmessage = function(event){
	    //接受来自服务器的消息
	    //...
	    console.log("Socket新消息:"+event.data);
	    var data = $.parseJSON(event.data);  
	    var sender = 2 ; //他人的
	    if(data.senderType == 0 || data.senderId == 0 ){//系统消息
	    	sender = 0; 
	    }else if(data.senderType == 2 && data.senderId == $("#userId").val()  ){//我自己的
	    	sender = 1 ;
	    }
	    //是小组消息，小组id相同，类型是新增
	    if(data.msgType == 3 && data.groupId == $("#groupId").val() && data.sendType == 0){
	    	if(data.senderIcon==''||data.senderIcon=='DEFAULT'||data.senderIcon.indexOf('statics')!=-1||data.senderIcon.indexOf('default_image')!=-1||data.senderIcon.indexOf('png')!=-1){
	    		data.senderIcon = contxtpath+'/statics/image/default_image.png';
	    	}else{
	    		data.senderIcon = contxtpath+'/image/'+data.senderIcon+'.action';
	    	}
	    	loadNewGroupMsg(sender,data.senderName,data.senderIcon,data.content,data.time);
	    }
	}
    }
     
    $('#edu').selectlist({
        zIndex: 10,
        width: 130,
        height: 37,
        triangleSize:3,
        optionHeight: 30,
        onChange: function () {
        	var pagesize=$("input[name='edu']").val();
        	filePagenumber = 1  ;
    		$("#fileContent").html('');
    		initFile();
        }  //自定义模拟选择列表项chang
       });
    
    
	//按钮发送消息
	$("#sendMsg").click(function(){
        $('#wechat').scrollTop( $('#wechat')[0].scrollHeight );
		sendSocktMsg();
	});
	
	var sendSocktMsg = function(){
		var content=$("#msgContent").val();
		var content2=content.replace(/(^\s*)|(\s*$)/g, "");//兼容ie8
		if(!content || content2 ==''){
			window.message.warning("请键入消息");
			return ;
		}
		if(content.length > 255){
			window.message.error("发送失败:键入消息过长");
			return ;
		}
        if (webSocket) {
            webSocket.send("{senderId:"+$("#userId").val()+",senderType:"+2+",content:'"+content+"',groupId:"+$("#groupId").val()+",sendType:0}");
        }
		$("#msgContent").val("");
	}
	
	//回车发送消息
	$("#msgContent").keydown(function (event){ 
		var code = event.keyCode; 
		if (13 == code) { //回车
			sendSocktMsg();
		}else{
			var content=$("#msgContent").val();
			if(content && content.length > 255){
				window.message.error("发送失败:键入消息过长");
				$("#msgContent").val(content.substring(0,255));
			}
		} 
	}); 
	//粘贴
    $(document).on("paste", "#msgContent", function () {
    	var content=$("#msgContent").val();
		if(content && content.length > 255){
			window.message.error("发送失败:键入消息过长");
			$("#msgContent").val(content.substring(0,255));
		}
    });
    $(document).on("keyup input", "#msgContent",function (e) {
    	var content=$("#msgContent").val();
		if(content && content.length > 255){
			window.message.error("发送失败:键入消息过长");
			$("#msgContent").val(content.substring(0,255));
		}
    });
	
	
	//-------------------------------
    $("#filesgx_top").html('文件共享<span style="display: inline-block;position:absolute;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;' +
        'line-height: 13px;padding: 3px 6px;border-radius: 50%;right: 0;top: 0">' + $("#file_count").val() + '</span>');
    
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
	var fileName = $("#fileName").val();
	$(".search").click(function(){
		fileName = $("#fileName").val();
		filePagenumber = 1  ;
		$("#fileContent").html('');
		initFile();
	});
	//回车搜索文件
	$("#fileName").keydown(function (event){ 
		var code = event.keyCode; 
		if (13 == code) { 
			fileName = $("#fileName").val();
			filePagenumber = 1  ;
			$("#fileContent").html('');
			initFile();
		} 
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
	        		//推送消息
                    if (webSocket) {
			    	webSocket.send("{senderId:"+userId+",senderType:"+0+",content:'\""+$("#userName").val()+"\"退出了小组"+"',groupId:"+$("#groupId").val()+",sendType:0}");
                    }
	        		setTimeout(function(){
	        			window.location.href = contxtpath+'/group/list.action';
	        		},800);
	        	}else{
	        		window.message.error("退出失败");
	        	}
	        }
	     });
	});
	
	//换一拨
	$("#anotherBatcHButton").click(function(){
		var html =$("#anotherBatcH").html();
		if(html && html.length > 2 ){
			$.ajax({
				type:'get',
		        url :contxtpath+'/group/anotherBatch.action',
		        contentType:    'application/json',
		        dataType:       'json',
		        data:{
		        	groupId   : $("#groupId").val()
		        },
		        success:function(responsebean){
		        	if(responsebean && responsebean.length > 0 ){
		        		$("#anotherBatcH").html("");
		        		for(var i= 0 ; i< responsebean.length ; i++  ){
		        			var  html = "<a style=\"display:block;\"  href=\""+contxtpath+"/group/toMyGroup.action?groupId="+responsebean[i].id+"\">"+
			        				        "<li style=\"margin-bottom: 25px;\"> "+
							                    "<div class=\"init_center w85_h50\"><img src=\""+contxtpath+"/"+responsebean[i].groupImage+"\"/></div>"+
							                    "<div class=\"init_center w85_h36_line18\">"+
							                        "<text class=\"color03\">"+responsebean[i].groupName+"</text>"+
							                        "<br/>"+
							                        "<text class=\"color99\">("+responsebean[i].members+"人)</text>"+
							                    "</div>"+
						                    "</li>"+
		        			            "</a>"
	                    	$("#anotherBatcH").append(html);
	                    	if(i >= 11){//加载12个
		        				break;
		        			}
		        		}
		        	}
		        }
		     });
		}
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
	        	fileId  : fileId 
	        },
	        success:function(responsebean){
	        	if(responsebean=='0'){
	        		window.message.success("删除成功");
		        	$("#item_"+id).remove();
		        	var old = parseInt($("#fileTotal").html());
		        	$("#fileTotal").html(old-1);
			    	$("#filesgx_top").html('文件共享<span style="display: inline-block;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;'+
			        		'line-height: 13px;padding: 3px 6px;border-radius: 50%;">'+(old-1)+'</span>');
	        	}else if(responsebean=='2'){
                    window.message.warning("您没有权限");
				}else {
	        		window.message.error("删除失败");
	        	}
	        }
	     });
	});
	$('body').on('click','.item_img2',function(){
		var imgId =  this.id;
		var id = imgId.replace('img_','');
		var fileId = $("#"+id).val() ;
		window.location.href = contxtpath+'/group/download/'+fileId+'.action?groupId='+$("#groupId").val() ;
		var num = parseInt($("#dw_"+id).html())+1;
		$("#dw_"+id).html(num);
		return ;
	});
	
	/*$("#order").on('change',function(){
		filePagenumber = 1  ;
		$("#fileContent").html('');
		initFile();
	});*/
	
	//初始化文件
    function initFile(){
        $("#fileMore").show();
        var order =$("input[name='edu']").val().split(':');
        $.ajax({
            type:'get',
            url :contxtpath+'/group/getFiles.action',
            contentType: 'application/json',
            dataType:'json',
            data:{
                groupId   : $("#groupId").val(),
                pageNumber: filePagenumber,
                pageSize  : filePagesize ,
                fileName  : fileName ,
                order     : order[0],
                rank      : order[1]
            },
            success:function(responsebean){
                if(responsebean){
                    for(var i= 0 ; i< responsebean.length ;i++){
                        var html = "<div class='items' id='item_"+responsebean[i].id+"'> "+
                            "<div class='item1' style='clear:both;'> "+
                            "<span><img src='"+contxtpath+"/statics/image/word-(1).png' alt='文件类型' class='item_img1'/><text>"+responsebean[i].fileName+"</text></span> <span style='color:#70bcc3;margin-left:20px'>"+responsebean[i].fileSize+"KB</span>"+
                            "<span><img src='"+contxtpath+"/statics/image/xztp.png' id='img_"+responsebean[i].id+"' class='item_img2'/><text id='dw_"+responsebean[i].id+"' style='color: #70bcc3;'>"+responsebean[i].download+"</text></span> "+
                            "</div> "+
                            "<div class='item2' style='clear:both;'> "+
                            "<div class='item2_div1'>"+responsebean[i].displayName+" 于 "+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd hh:ss:mm")+"上传文件</div> "+
                            "<div style='color: #b0b0b0;float: right;'> "+
                            (responsebean[i].deletePower?'<span style=\"cursor:pointer\" class="del_span deleteThis" id="bt_'+responsebean[i].id+'" ></span> ':'') +
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
                                "<img src= '"+senderIcon+"'  /> "+
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
                            "<img src= '"+senderIcon+"'  /> "+
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
		//var a=document.getElementsByClassName("chat_items mine");
		var $a = $(".chat_items.mine");
		var $b = $(".chat_items.other");
        //var b=document.getElementsByClassName("chat_items other");
        //var t=a.length+b.length;
		var t=$a.length+$b.length;
		$(".iframe1").scrollTop(100*t);
	}
	

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
	
	
	//文件上传方法
	$("#scwj1").uploadFile({
	    start: function () {
	        console.log("开始上传。。。");
	    },
	    done: function (fileName, fileId,fileSize) {//
	    	$.ajax({
				type: "POST",
				url:   contxtpath+'/group/fileup.action',
				data:  {
					    groupId  : $("#groupId").val(),
					    fileId   : fileId,
					    fileSize : fileSize,
                    fileName: fileName
                },// 你的form
				async: false,
				dataType:"json",
                complete:function () {

                        setTimeout(function () {
                            $("#uploadFileTips").hide();
                        },2000)
                },
			    success: function(msg) {
				    if(msg =='OK'){
				    	window.message.success("上传成功");
				    	console.log("上传完成：name " + fileName + " fileid " + fileId);
				    	var userId= $("#userId").val();
				    	//刷新文件列表
				    	filePagenumber = 1  ;
						$("#fileContent").html('');
						initFile();
				    	//推送消息
                        if (webSocket) {
				    	webSocket.send("{senderId:"+userId+",senderType:"+0+",content:'\""+$("#"+userId+"_2").val()+"\"上传了文件"+"',groupId:"+$("#groupId").val()+",sendType:0}");
                        }
				    	var old = parseInt($("#fileTotal").html());
                        $("#fileTotal").html(old+1);
                        $("#filesgx_top").html('文件共享<span style="display: inline-block;background: #ff0000 !important;color: #fff;font-size: 10px;font-weight: 400;'+
                            'line-height: 13px;padding: 3px 6px;border-radius: 50%;">'+(old+1)+'</span>');
				    }else{
				    	window.message.error("上传失败");
				    }
			    }
			});
	    },
	    progressall: function (loaded, total, bitrate) {
             var progress = parseInt(loaded /total * 100, 10);
             var percentage = loaded / total;
            // $('#progress .bar').css(
            //     'width',
            //     progress + '%'
            // );
            //layui.element.active.setPercent(progress);
			//$('.layui-progress-bar').setAttribute("lay-percent",loaded / total);
          //  var percentage = 0;
          //   var interval = setInterval(function () {
          //       if (percentage==1) {
          //
          //           var widthTemp = percentage.toFixed(1) + '%';
          //           $('#progressBar').css('width', widthTemp);
          //           $('#progressBar').text(widthTemp);
          //       } else {
          //           clearInterval(interval);
          //           $('h3').text('上传完成');
          //           setTimeout(function () {
          //               $("#process").hide();
          //           }, 500);
          //       }
          //   }, 10);
            $("#uploadFileTips").text("正在上传...  "+((loaded / total).toFixed(2)*100)+'%');
			//if
			//}

	        console.log(loaded+"  "+ total+"正在上传..." + loaded / total);
	    }
	});
	
});



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



