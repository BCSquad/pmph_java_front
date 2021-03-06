function loadMore(tag){
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
		data:{"startPara":startPara,tag:tag},
		async:false,
		dataType:'json',
		success:function(json){
			$("#startPara").val(startPara);
			var str= '';
			$.each(json.list,function(i,n){
                str += '<div class="item" id="item'+n.msg_id+'">' +
                    '<div class="item-img">' +
                    '<img src="' + contextpath + n.avatar + '" />' +
                    '</div>' +
                    '<div class="content" onclick="system(\'' + n.id + '\',\'' + n.NAME + '\',\'' + n.TIME + '\')" style="width:1000px">' +
                    ' <p class="title" >' +
                    '<span class="msg">' + n.title + '</span>' +
                    '<span class="time">' + n.TIME + '</span>' +
                    ' </p>' +
                    '<div class="text" id="txt'+n.id+'">' +
                    (n.isread==true?'<img src="'+contextpath+'statics/image/readyes.png"  id="readyes'+n.id+'"/>':'') +
                    (n.isread==false?'<img src="'+contextpath+'statics/image/readno.png"  id="readno'+n.id+'"/>':'') +
                    (n.msg_content?n.msg_content:'') +
                    '</div></div>'+
            '<div style="float:left;color: #999999;font-size: 14px;height:20px;margin-top: 45px;" onclick="delmsg(\''+(tag=='receive'?n.id:n.msg_id)+'\','+(tag=='receive'?'1':'2')+')">'+
                    '<span style="width:20px;height:20px;float:left;" class="deltag"></span>'+
                    '<span style="line-height: 20px;">删除</span>'+
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
function system(str, name, time) {
	var loaded = false;
	var imgTimeOut = false;
	var clickToEndLoad = false;
	
	//10秒不论是否加载完成，都跳出弹窗；
	setTimeout(function(){
		imgTimeOut = true;
	}, 10000);
	
	//加载过程中点击window 阻止弹窗弹出（然而点不到window，只有加载图案的一小块有点击效果，问题待解决）
	$(window).one("click",function(){
		$(window).one("click",function(){
			clickToEndLoad = true;
		});
	});
	
    if (str != '' && str != '') {
        $.ajax({
            type: "post",
            url: contextpath + "message/queryTitleMessage.action?uid=" + str,
            async: false,
            dataType: 'json',
            success: function (json) {
                $("#titlec").html(json.title);
                $("#sendc").html(json.realname);
                $("#timec").html(formatDate(json.gmt_create));
                $("#tcontent").html(json.tContent);
                /*$("#bookmistake").show();*/
                var ste = '';
                $.each(json.attaList, function (i, x) {
                    ste += '<a  href="' + contextpath + '' + x.attachment + '">' + x.attachment_name + '</a><br/>';
                });
                if(ste==''){
                    ste +="<span>无</span>"
                }
                $("#tattachment").html(ste);
                var imgTick;
                /**
                 * 轮询$content下的图片是否加载完成，直到完成后回调
                 * @param loaded 是否加载完成
                 * @param id 所需判断的块的id
                 * @param callback  加载完成的回调函数 
                 */
                function isImgLoad(loaded,id,callback) {
                	//清除延时
                	clearTimeout(imgTick);
                	if ($("#"+id).find("img").length>0) { //有图片需要加载
                		//是否所有图片加载完成
                    	$("#"+id).find("img").each(function(){
                    		var $t = $(this);
                    		if(imgTimeOut||$t[0].complete){
                    			loaded = true;
                    		}else{
                    			loaded = false;
                    			return false; 
                    		}
                    	});
    				}else{ //无图片需要加载
    					loaded = true;
    				}
                	
                	if (clickToEndLoad) {
                		layer.closeAll("loading"); 
    				}else{
    					if (loaded) { // 若已全部加载 执行回调
    	            		callback();
    					}else{  //若还未全部加载完成 间隔每10毫秒再次轮询 直到加载完成执行回调 不再轮询0
    						imgTick = setTimeout(function(){
    							 layer.load(1); //加载图样载
    							 isImgLoad(loaded,id,callback); //轮询
    						}, 10);
    					}
    				}
                	
                }
                
                //开始轮询
                isImgLoad(loaded,"bookmistake", function() {
                            layer.closeAll("loading"); //关闭所有layer.load(2);加载图样载
                            layer.open({
                                type: 1,
                                title: false,
                                closeBtn: 1,
                                area: '967px',
                                skin: 'layui-layer-nobg', //没有背景色
                                shadeClose: true,
                                content: $("#bookmistake")
                            });
                        });
                
                var obj= document.getElementById('readyes'+str);
                var readno=document.getElementById('readno'+str);
                if(!obj&&json.isread=="yes"){
                	if(readno){
                		$("#readno"+str).remove();
                	}
                  	$("#txt"+json.id).html('<img src="'+contextpath+'statics/image/readyes.png"  id="readyes'+str+'"/>'+$("#txt"+json.id).html());
                }
            }
        });
    }
}

//点击关闭消息详情窗口
function hideup() {
    $("#bookmistake").hide();
}
/**
 * tag :
 * 1删除收到的消息 根据主键id删除此条
 * 2删除发送的信息 根据msg_id删除此次发送的消息 应该对多人发送所以有多条
 */
function delmsg(id,tag){
	window.message.confirm("您确定删除此消息吗？",{btn:["确定","取消"]},function(index){
		
	  $.ajax({
          type:"post",
          url:contextpath+"AllMessage/delmsg.action?mid="+id+"&tag="+tag,
          async:false,
          dataType:'json',
          success:function(json){
        	  if(json.isdel=="yes"){
        		  $("#item"+id).remove();
        	  }
          }});
	  layer.close(index);
	},function(){});
}
//时间格式化
function formatDate(value) {
    if (value) {
        Number.prototype.padLeft = function (base, chr) {
            var len = (String(base || 10).length - String(this).length) + 1;// 获取value值的长度，如果长度大于0，就创建一个同等长度的数组
            return len > 0 ? new Array(len).join(chr || '0') + this : this;
        }
        var d = new Date(value), // 创建一个当前日期对象d
            dformat = [d.getFullYear(),// 把年格式化填充
                    (d.getMonth() + 1).padLeft(),// 把月格式化填充
                    d.getDate().padLeft()].join('-') + // 把日格式化填充
                ' ' + [d.getHours().padLeft(),// 把小时格式化填充
                    d.getMinutes().padLeft(),// 把分钟格式化填充
                    d.getSeconds().padLeft()].join(':');// 把秒格式化填充
        return dformat;// 最后返回格式化好的日期和时间
    }
    }