//加载更多通知公告
function loadMore(){
	
	var pathName=window.document.location.pathname;  
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		var para = $("#startPara").val();
		var startPara;
		if(""==para){
			startPara=8;
		}else{
			startPara = parseInt(para)+8;
		}
		
		 $.ajax({
			type:'post',
			url:"loadMore.action?",
			data:{"startPara":startPara,"condition":$("input[name='select']") .val()},
			dataType:'json',
			success:function(json){
				var list = json;
				$.each(list,function(i,m){
					if(i==0){
						var count = m.count;
						if(count<=0){
							$("#loadMoreDiv").hide();
						}
					}
				});
				$("#startPara").val(startPara);
				var str='';
				$.each(list,function(i,n){
					var unixTimestamp = new Date(n.time) ;
					commonTime = unixTimestamp.toLocaleString();
					
					str+= "<tr style='width: 70%'>"+
		               "<th rowspan='2' class='headPortrait'><img  class ='pictureNotice' src='"+projectName+"/statics/pictures/head.png'></th>"+
		               "<td class='type1'><span>" ;
					
		               if(n.msgType==4){
		            	   str+= "公告";
		               }
		               if(n.msgType==0||n.msgType==1){
		            	   str+= "系统消息";
		               }
		              str+= "</span><span class='time1'>"+commonTime+"</span></td></tr><tr style='width: 30%'>"+
		               "<td colspan='2' class='title'>"+n.title+"</td><td class='buttonDetail'>";
		              if(n.msgType==4){
		            	 str+="<div class='buttonAccept'><a href='"+projectName+"/message/noticeMessageDetail.action?msgId="+n.fId+"&&cmsId="+n.id+"'>查看详情</a></div>"; 
		              }else if(n.msgType==0||n.msgType==1){
		            	  str+= "<span class='deleteButton' onclick='deleteNotice("+n.id+")'><span style='font-size:18px;'>×</span> 删除</span>";
		              }
		              
		              str+= "</td></tr><tr><td colspan='4' align='center' ><hr class='line'></td></tr>";
				});
				$("#messageTable").append(str);
				
				
			}
		}); 
	}

//加载更多申请
function loadMoreApply(){
	var pathName=window.document.location.pathname;  
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	
	
	alert($("input[name='condition']") .val());
		var para = $("#applyPara").val();
		var startPara;
		if(""==para){
			startPara=8;
		}else{
			startPara = parseInt(para)+8;
		}
		 $.ajax({
			type:'post',
			url:"loadMoreApply.action?startPara="+startPara+"&condition="+$("input[name='condition']") .val(),
			async:false,
			dataType:'json',
			success:function(json){
				var list = json;
				$("#applyPara").val(startPara);
				var str='';
				$.each(list,function(i,n){
					var unixTimestamp = new Date(n.gmt_create) ;
					commonTime = unixTimestamp.toLocaleString();
					
					str+="<tr><th rowspan='2' class='headPortrait'><img src='"+projectName+"/statics/pictures/head.png' class='picture' ></th></tr>"+
			        "<tr><td ><span class='apply'>"+n.realname+"申请加我为好友</span><span class='time'>"+commonTime+"</span></td>"+
			            "<td class='buttonSpan'>";
						if(n.status==0){
							str+= "<button class='buttonIgnore' onclick='ignore("+n.id+")'>忽略</button><button class='buttonAccept' onclick='accept("+n.id+")'>接受</button>";
						}else if(n.status==2){
							str+="<span class='accept-text'>已接受</span>";
						}else if(n.status==1){
							 str+="<span class='ignore-text'>已忽略</span>";
						}
			               
						 str+="</td></tr><tr> <td colspan='4'  ><hr class='line'></td></tr>";
					
				$("applyTable").append(str);
				
			});
			}	
		}); 
	}

//时间格式化
Date.prototype.toLocaleString = function() {
	var second=this.getSeconds();
	if(this.getSeconds()<10){
		second="0"+this.getSeconds();
	}
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + "-" + this.getHours() + ":" + this.getMinutes() + ":" + second;
};

/*//删除通知
function deleteNotice(id){
	
	window.message.confirm(
			'确认删除吗?',
			{btn:['确定','取消']},
			function(id){
				$.ajax({
					type:'post',
					url:"deleteNoticeMessage.action?id="+id,
					async:false,
					dataType:'json',
					success:function(json){
						window.message.success("删除成功");
					}
					});
			},
			function(){
				
			}
	);
	 
	 
	 
	 
	//window.location.href="${ctx}/message/deleteNoticeMessage.action?id="+id;
}*/
