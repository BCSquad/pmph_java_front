function loadMore(){
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
		data:{"startPara":startPara},
		async:false,
		dataType:'json',
		success:function(json){
			$("#startPara").val(startPara);
			var str= '';
			$.each(json.list,function(i,n){
                str += '<div class="item" onclick="system(\'' + n.id + '\',\'' + n.NAME + '\',\'' + n.TIME + '\')">' +
                    '<div class="item-img">' +
                    '<img src="' + contextpath + n.avatar + '" />' +
                    '</div>' +
                    '<div class="content" >' +
                    ' <p class="title" >' +
                    '<span class="msg">' + n.title + '</span>' +
                    '<span class="time">' + n.TIME + '</span>' +
                    ' </p>' +
                    '<div class="text" >' + n.msg_content + '</div>' +
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
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    area: '967px',
                    skin: 'layui-layer-nobg', //没有背景色
                    shadeClose: true,
                    content: $("#bookmistake")
                });
            }
        });
    }
}

//点击关闭消息详情窗口
function hideup() {
    $("#bookmistake").hide();
}
//
function delmsg(id){
	  $.ajax({
          type:"post",
          url:contextpath+"AllMessage/delmsg.action?mid="+id,
          async:false,
          dataType:'json',
          success:function(json){
        	  if(json.isdel=="yes"){
        		  $("#item"+id).remove();
        	  }
          }});
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