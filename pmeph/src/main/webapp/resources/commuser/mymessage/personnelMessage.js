$(function() {
	var pageSize = 10;
	var pageNumber = 1;
	var isRead = null;
	init();
	
	function init() {
		$.ajax({
					type : 'get',
					url : contxtpath + '/mymessage/tolist.action',
					async : false,
					contentType : 'application/json',
					dataType : 'json',
					data : {
						pageNumber : pageNumber,
						pageSize : pageSize,
						isRead : isRead
					},
					success : function(res) {
						if (null != res.rows && res.total >= 0) {
							var data = res.rows;
							pageNumber++;
							var html = "<table class='table'>";
							for (var i = 0; i < res.total; i++) {
								html+="<tr><th rowspan='2' class='headPortrait'><img class='pictureNotice' src="
												+ data[i].avatar
												+ "></th><td class='name'><span>"
												+ data[i].name
												+ "</span><span class='time1'>"
												+ data[i].sendTime
												+ "</span></td></tr>";
								html+="<tr><td colspan='2' class='personMessageContent'>私信内容："
												+ data[i].content
												+ "</td><td class='buttonDetail'><div class='buttonAccept'><a class='a' href='javascript:void(0)' onclick='show()'>查看详情</a></div></td></tr>";
								html+="<tr><td colspan='4' align='center'><hr class='line'></td></tr>";
							}
							html+="</table>";
							$("#list").append(html);
							if(res.total < pageSize){
			        			$("#loadMore").hide();
			        		}
						}
					}
				})
	}
})
