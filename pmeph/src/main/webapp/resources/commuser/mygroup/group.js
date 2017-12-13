
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
	        			var html = "<div class='items'> "+
				                        "<div class='item1' style='clear:both;'> "+
				                            "<span><img src='"+contxtpath+"/statics/image/word-(1).png' alt='文件类型' class='item_img1'/><text>"+responsebean[i].fileName+"</text></span> "+
				                            "<span><img src='"+contxtpath+"/statics/image/xztp.png' class='item_img2'/><text style='color: #70bcc3;'>"+responsebean[i].download+"</text></span> "+
				                        "</div> "+
				                        "<div class='item2' style='clear:both;'> "+
				                            "<div class='item2_div1'>"+responsebean[i].displayName+" 于 "+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd hh:ss:mm")+"上传文件</div> "+
				                            "<div style='color: #b0b0b0;float: right;'><span class='del_span'></span> "+
				                            (responsebean[i].deletePower?'<text>删除</text> ':'')
				                            "</div> "+
				                         "</div> "+
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