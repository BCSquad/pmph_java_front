
	
	


// 已读 未读
function getMessage(is_read,id1,id2){
    $("#"+id1).css({"background-color":"#1d8ca0","color":"#fff"});
    $("#"+id2).css({"background-color":"#E6F1F3","color":"#03717b"});
    $("#is_read_hidden").val(is_read);

    $.ajax({
        type: 'post',
        url: "messageIsRead.action",
        data: {"startPara": 0, "condition": $("input[name='select']").val(),"is_read":is_read},
        dataType: 'json',
        success: function (json) {
            console.log(json);
           document.getElementById("message_content").innerHTML='';
            var tableHtml = " <table class=\"table\" id=\"messageTable\" ></table>";
            $("#message_content").append(tableHtml);
            var str = '';
            var strMessage = '';
            if(json.listSize>0){

                $.each(json.list, function (i, n) {
                    var unixTimestamp = new Date(n.time);
                    commonTime = unixTimestamp.toLocaleString();

                    str += "<tr style='width: 70%' class='tr_"+n.id +"'>" +
                        "<th rowspan='2' class='headPortrait'><img  class ='pictureNotice' src='" + /*projectName + "/"*/ contextpath + n.avatar + "'></th>" +
                        "<td class='type1'><span>";

                    str += n.title;

                    str += "</span><span class='time1'>" + commonTime + "</span></td></tr><tr style='width: 30%' class='tr_"+n.id +"'>";

                    if ((n.msgType==0||n.msgType==1)) {
                        str += "<td colspan='2' class='title' style='cursor: pointer;' onclick='showup(" + n.id + ")'>"
                        if (n.is_read == 1) {
                            str += '<img src="' + contextpath + 'statics/image/readyes.png"  id="readyes'+n.id+'" class="readyes"/>';
                        } else {
                            str += '<img src="' + contextpath + 'statics/image/readno.png"  id="readno'+n.id+'" class="readyes"/>';
                        }
                        str+='<span class="fixwidth">'+ n.tcontent+'</span>';
                    }

                    str += "</td><td class='buttonDetail'>";
                    if (n.msgType == 0 && n.material_id != 0) {
                        /*str += "<div class='buttonAccept'><a href='" + contextpath + "message/noticeMessageDetail.action?materialId=" + n.material_id + "'>查看详情</a></div>";*/
                        str += "<div class=\"buttonAccept\" ><a href=\"javascript:lookDetailInfo('" + n.id + "','" + n.cmsid + "','" + n.is_read + "')\">查看详情</a></div>";
                    }
                    if (n.msgType == 0 || n.msgType == 1) {
                        str += "<span class='deleteButton' onclick='deleteNotice(" + n.id + ")'><span style='font-size:18px;'>×</span> 删除</span>";
                    }

                    str += "</td></tr><tr class='tr_"+n.id +"'><td colspan='4' align='center' ><hr class='line'></td></tr>";
                });
            }else{
                str +=" <div class=\"no-more\">\n" +
                    "               \t\t</div>";
            }
            $("#messageTable").append(str);

            if(json.count>0){
                strMessage+=" <div id=\"loadMoreDiv\" class=\"load-more clearfix\" onclick='loadMore()'>加载更多...</div>\n" +
                    "            <input id=\"startPara\" name=\"startPara\" type=\"hidden\">";
            }
            $("#message_content").append(strMessage);

        }
    })
}

//加载更多通知公告
function loadMore() {

    var pathName = window.document.location.pathname;
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    var para = $("#startPara").val();
    var startPara;
    if ("" == para) {
        startPara = 8;
    } else {
        startPara = parseInt(para) + 8;
    }

    $.ajax({
        type: 'post',
        url: "loadMore.action?",
        data: {"startPara": startPara, "condition": $("input[name='select']").val(),"is_read":$("#is_read_hidden").val()},
        dataType: 'json',
        success: function (json) {
            var list = json;
            $.each(list, function (i, m) {
                if (i == 0) {
                    var count = m.count;
                    if (count <= 0) {
                        $("#loadMoreDiv").hide();
                    }
                }
            });
            $("#startPara").val(startPara);
            var str = '';
            if (list.length == 0) {
                $("#loadMoreDiv").text('没有更多了');
                return
            }
            $.each(list, function (i, n) {
                var unixTimestamp = new Date(n.time);
                commonTime = unixTimestamp.toLocaleString();

                str += "<tr style='width: 70%' class='tr_"+n.id +"'>" +
                    "<th rowspan='2' class='headPortrait'><img  class ='pictureNotice' src='" + /*projectName + "/"*/ contextpath + n.avatar + "'></th>" +
                    "<td class='type1'><span>";

                str += n.title;

                str += "</span><span class='time1'>" + commonTime + "</span></td></tr><tr style='width: 30%' class='tr_"+n.id +"'>";
                
                if ((n.msgType==0||n.msgType==1)) {
	                str += "<td colspan='2' class='title' style='cursor: pointer;' onclick='showup(" + n.id + ")'>"
	                if (n.is_read == 1) {
	                    str += '<img src="' + contextpath + 'statics/image/readyes.png"  id="readyes'+n.id+'" class="readyes"/>';
	                } else {
	                    str += '<img src="' + contextpath + 'statics/image/readno.png"  id="readno'+n.id+'" class="readyes"/>';
	                }
                    str+='<span class="fixwidth">'+ n.tcontent+'</span>';
                }
                /*if ((n.msgType==0||n.msgType==4)&& n.material_id !=0) {
	                str += "<td colspan='2' class='title' >"
	                    +'<span class="fixwidth">'+ n.tcontent+'</span>';
                }*/
                
                str += "</td><td class='buttonDetail'>";
                if (n.msgType == 0 && n.material_id != 0) {
                    /*str += "<div class='buttonAccept'><a href='javascript:lookDetailInfo('${message.id}','${message.cmsid}','${message.is_read}')'>查看详情</a></div>";*/
                    str += "<div class=\"buttonAccept\" ><a href=\"javascript:lookDetailInfo('"+n.id+"','"+n.cmsid+"','"+n.is_read+"')\">查看详情</a></div>";
                }
                if (n.msgType == 0 || n.msgType == 1) {
                    str += "<span class='deleteButton' onclick='deleteNotice(" + n.id + ")'><span style='font-size:18px;'>×</span> 删除</span>";
                }

                str += "</td></tr><tr class='tr_"+n.id +"'><td colspan='4' align='center' ><hr class='line'></td></tr>";
            });
            $("#messageTable").append(str);


        }
    });
}

//加载更多申请
function loadMoreApply() {
    var pathName = window.document.location.pathname;
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    var para = $("#applyPara").val();
    var startPara;
    if ("" == para) {
        startPara = 8;
    } else {
        startPara = parseInt(para) + 8;
    }
    $.ajax({
        type: 'post',
        url: "loadMoreApply.action?startPara=" + startPara + "&condition=" + $("input[name='condition']").val(),
        async: false,
        dataType: 'json',
        success: function (json) {
            var list = json;
            $.each(list, function (i, m) {
                if (i == 0) {
                    var count = m.count;
                    if (count <= 0) {
                        $("#loadMoreDiv").hide();
                    }
                }
            });
            $("#applyPara").val(startPara);
            var str = '';
            $.each(list, function (i, n) {
                var unixTimestamp = new Date(n.gmt_create);
                commonTime = unixTimestamp.toLocaleString();

                str += "<tr><th rowspan='2' class='headPortrait'><img src='" + projectName + "/" + n.avatar + "' class='picture' ></th></tr>" +
                    "<tr><td ><span class='apply'>" + n.realname + "申请加我为好友</span><span class='time'>" + commonTime + "</span></td>" +
                    "<td class='buttonSpan'>";
                if (n.status == 0) {
                    str += "<button class='buttonIgnore' onclick='ignore(" + n.id + ")'>忽略</button><button class='buttonAccept' onclick='accept(" + n.id + ")'>接受</button>";
                } else if (n.status == 2) {
                    str += "<span class='accept-text'>已接受</span>";
                } else if (n.status == 1) {
                    str += "<span class='ignore-text'>已忽略</span>";
                }

                str += "</td></tr><tr> <td colspan='4'  ><hr class='line'></td></tr>";

                $("#applyTable").append(str);

            });
        }
    });
}

//时间格式化
Date.prototype.toLocaleString = function () {
    var second = this.getSeconds();
    if (this.getSeconds() < 10) {
        second = "0" + this.getSeconds();
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


function toproductdetail(product_id) {
    location.href = contextpath + "homepage/toproductdetail.action?state=1&product_id="+product_id;
}

function lookDetailInfo(id,cmsid,is_read){
    if(parseInt(is_read)==1){
        location.href=contextpath+"message/noticeMessageDetail.action?cmsId="+cmsid+"&umid="+id;
    }else{
        $.ajax({
            url:contextpath+"message/updateIsreaded.action?uid="+id,
            type:'post',
            dataType:'json',
            async:false,
            success:function(flag){
                if(flag!='error'){
                    location.href=contextpath+"message/noticeMessageDetail.action?cmsId="+cmsid+"&umid="+id;
                }else if(flag=='error'){
                    window.message.error("出错了!");
                }
            }
        })

    }


}
//系统消息title标题弹窗

function showup(id) {
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
    	
   
    $.ajax({
        type: 'post',
        url: contextpath + 'message/queryTitleMessage.action?uid=' + id,
        async: false,
        dataType: 'json',
        success: function (json) {
            if(json.messageNum<=0){
                if($(".sign"))$(".sign").hide();
                $(".notice-icon").attr("src",contextpath+ "statics/image/message.png");
            }

            $("#readno"+json.id).attr('src', contextpath + "statics/image/readyes.png");
            $("#messid").val(id);
            $("#titlec").html(json.title);
            $("#sendc").html(json.realname);
            $("#timec").html(formatDate(json.gmt_create));
            $("#tcontent").html(json.tContent);
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
            
        }
    });
}

//点击纠错弹窗隐藏
function hideup() {
    $("#bookmistake").hide();
    /*	var messid=$("#messid").val();
     $.ajax({
     type: 'post',
     url: contextpath + 'message/updateTitleMessage.action?messid='+messid,
     async: false,
     dataType: 'json',
     success: function (json) {
     if(json=="OK"){
     $("#bookmistake").hide();
     toList();
     }
     }
     });*/
}
//刷新消息页
function toList() {
    location.replace(location.href);
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

//下拉刷新
function loadData(){
    var jsptype = $("#jsptype").val();
    if(jsptype=="1"){
        loadMoreApply();
    }
    if(jsptype=="2"){
        loadMore();
    }
}