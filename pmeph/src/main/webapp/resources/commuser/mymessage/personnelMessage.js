var data;
$(function() {
	$('#select').selectlist({
		zIndex : 10,
		width : 70,
		height : 20,
		optionHeight : 20,
		triangleColor : '#333333',
		onChange : function() {
			$("#list").html("");
			$("#loadMore").show();
			isRead = $("#select").val();
			pageSize = 1;
			pageNumber = 1;
			init();
		}
	});
	var pageSize = 1;
	var pageNumber = 1;
	var isRead = null;
	init();

	// 更多
	$("#loadMore").click(function() {
		init();
	});

	function init() {
		$
				.ajax({
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
							data = res.rows;
							var html = "";
							for (var i = 0; i < data.length; i++) {
								html += "<tr><th rowspan='2' class='headPortrait'><img class='pictureNotice' src='"
										+ contxtpath
										+ data[i].avatar
										+ "'></th><td class='name'><span>"
										+ data[i].name
										+ "</span><span class='time1'>"
										+ formatDate(data[i].sendTime, "")
										+ "</span></td></tr>";
								html += "<tr><td colspan='2' class='personMessageContent'>私信内容："
										+ data[i].content
										+ "</td><td class='buttonDetail'><div class='buttonAccept'><a class='a' href='javascript:void(0)' onclick='detail("
										+ i + ")'>查看详情</a></div></td></tr>";
								html += "<tr><td colspan='4' align='center'><hr class='line'></td></tr>";
							}
							$("#list").append(html);
							pageNumber++;
							if (data.length < pageSize) {
								$("#loadMore").hide();
							}
						}
					}
				})
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
})

function detail(i) {
	$
			.ajax({
				type : 'put',
				url : contxtpath + '/mymessage/todetail.action',
				async : false,
				contentType : 'application/json',
				dataType : 'json',
				data : {
					senderId : data[i].senderId,
					senderType : data[i].senderType,
					receiverId : data[i].receiverId,
					receiverType : data[i].receiverType
				},
				success : function(res) {
					if (null != res && res.length >= 0) {
						var html = "<div><span class='personMessageTitle'>你与"
								+ res[0].name
								+ "的私信窗口</span><div class='contentBox'><div class='oneTalk'>";
						for (var i; i < res.length; i++) {
							if (res[i].isMy) {
								html += "<div class='headAndNameRight float_right'><div class='headDiv'><img class='headPicture' src='"
										+ contxtpath
										+ res[i].userAvatar
										+ "'/></div><div class='talkName'><text>"
										+ res[i].userName
										+ "</text></div></div>";
								html += "<div class='talkDiv float_right'><div class='sendMessage'><div class='textDiv float_right'>"
										+ res[i].content
										+ "</div></div><div class='talkTime headAndNameRight'>"
										+ formatDate(res[i].sendTime, "")
										+ "</div></div>";
							} else {
								html += "<div class='headAndNameLeft float_left'><div class='headDiv'><img class='headPicture' src='"
										+ contxtpath
										+ res[i].avatar
										+ "'/></div><div class='talkName'><text>"
										+ res[i].name + "</text></div></div>";
								html += "<div class='talkDiv float_left'><div class='sendMessage'><div class='textDiv float_left'>"
										+ res[i].content
										+ "</div></div><div class='talkTime talkTimeAlignLeft'>"
										+ formatDate(res[i].sendTime, "")
										+ "</div></div>";
							}
						}
						html += "</div></div></div>";
						html += "<div class='inputBox'><div style='float: left; width: 80%; height: 100%'><textarea style='width: 100%; height: 98%; border: none; outline: 0; font-size: 15px;'	type='text' placeholder='请输入消息内容,按回车键发送'></textarea></div><div style='float: left; width: 20%; height: 100%'><div class='div_btn11' style='cursor: pointer;'><span class='button11'>发送</span></div></div></div>";
						$("#box").append(html);
						$("#box").setAttribute("class", "b show");
						$("#close").setAttribute("class", "hiddenX show");
					}
				}
			})

}