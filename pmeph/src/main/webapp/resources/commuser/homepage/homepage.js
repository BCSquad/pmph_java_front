$(function() {
	$("#book_type").val(1);

	$('.banner').scroll({
		picElem : $('#move'), // 图片父级
		ctrlElem : $('#ctrl'), // 控制条父级(包括小圆点和左右箭头)
		isLibs : true, // 是否创建底部小圆点(样式均可自定义调整),默认向lib添加单独类名，详情见调用后dom结构
		isArrows : true, // 是否创建左右箭头(样式均可自定义调整)
		autoPlay : $("auto_play").val(), // 是否自动播放
		playTime : $("animation_interval").val(), // 自动播放间隔时间
		playSpeed : 700, // 图片切换速度
		effect : 'left' // 轮播的改变方式 top(向上) left(向左) fade(淡入淡出)
	});
});
// 下一页
function on(state) {
	var startrows = $("#before").text();
	var type = $("#book_type").val();
	if (state == "next") {
		var flag = $("#next").text();
		if (flag == startrows) {
			message.info("已经是最后一页了！");
			return;
		}
	} else if (state == "before") {
		var flag = $("#before").text();
		if (flag == 1) {
			message.info("已经是第一页了！");
			return;
		}
	}
	$.ajax({
		type : 'post',
		url : contextpath + 'homepage/changerows.action?startrows=' + startrows
				+ '&&state=' + state + '&&type=' + type,
		async : false,
		dataType : 'json',
		success : function(json) {
			$("#homepagebook").html(json.homepagebook);
			$("#before").html(json.thisrows);
		}
	});
}

// 书籍分类
function chooseType(state) {
	$.ajax({
		type : 'post',
		url : contextpath + 'homepage/chooseType.action?state=' + state,
		async : false,
		dataType : 'json',
		success : function(json) {
			$("#homepagebook").html(json.homepagebook);
			$("#next").html(json.allrows);
			$.each(json.listType, function(i, x) {
				if (i == 0) {
					$("#typeOne").html(x.type_name);
				} else {
					$("#typeTwo").html(x.type_name);
				}
			});
			$(".tab").removeClass("active");
			$("#" + state).addClass("active");
			$("#book_type").val(state);
			$("#before").html(1);
			var labelHtml = "";
			for ( var i = 0; i < json.listLabel.length; i++) {
				labelHtml += '<a href="' + json.listLabel[i].type
						+ '" class="little"><span class="little_content">'
						+ json.listLabel[i].note + '</span></a>';
			}
			$(".div_photo1 .div1").html(labelHtml);
		}
	});
}

// 根据分类改变图书畅销榜
function changesale(type) {

	$.ajax({
		type : 'post',
		url : contextpath + 'homepage/changesale.action?type=' + type,
		async : false,
		dataType : 'json',
		success : function(json) {
			$.each(json.type, function(i, x) {
				if (i == 0) {
					$("#sale_book1").html(x.bookname);
					$("#right_book1").attr('src', x.image_url);
					if(x.image_url=='DEFAULT'){
						$("#right_book1").attr('src', contextpath+"/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
					}else{
						$("#right_book1").attr('src', x.image_url);
					}
				} else if (i == 1) {
					$("#sale_book2").html(x.bookname);
					if(x.image_url=='DEFAULT'){
						$("#right_book2").attr('src', contextpath+"/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
					}else{
						$("#right_book2").attr('src', x.image_url);
					}
				} else if (i == 2) {
					$("#sale_book3").html(x.bookname);
					if(x.image_url=='DEFAULT'){
						$("#right_book3").attr('src', contextpath+"/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
					}else{
						$("#right_book3").attr('src', x.image_url);
					}
				} else if (i == 3) {
					$("#right_book4").attr('src', x.image_url);
					if(x.image_url=='DEFAULT'){
						$("#right_book4").attr('src', contextpath+"/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
					}else{
						$("#right_book4").attr('src', x.image_url);
					}
				} else if (i == 4) {
					$("#right_book5").attr('src', x.image_url);
					if(x.image_url=='DEFAULT'){
						$("#right_book5").attr('src', contextpath+"/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
					}else{
						$("#right_book5").attr('src', x.image_url);
					}
				} else if (i == 5) {
					$("#sale_book6").html(x.bookname);
					if(x.image_url=='DEFAULT'){
						$("#right_book6").attr('src', contextpath+"/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg");
					}else{
						$("#right_book6").attr('src', x.image_url);
					}
				}
			});
		}
	});
	$(".right_div1").removeClass("active");
	$("#typeid-" + type).addClass("active");
}

// 关闭问卷调查
function cancel() {
	$("#test_float").hide();
	$("#test_float").attr('onclick', '');
	return false;
}

// 跳转到问卷调查页面
function tosurvey() {
	location.href = contextpath + 'survey/surveyList.action';
}

// 跳转公告详情页面
function todou(mid) {
	location.href = contextpath + 'community/tolist.action?id=' + mid;
}

//添加好友 按钮触发
function addFriendfun(uid,realname,status){
	var data={uid:uid
			,status:status};
	$.ajax({
		type:'post',
		url:contextpath+'addFriend/addFriendfun.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			if (status == 2) {
				window.message.success("已和 "+realname+" 成为好友！");
				$("#friend"+uid).removeClass("isBeenRequest").addClass("isFriend").html("<B>好友</B>").attr("title","已是您的好友！").unbind();
			} else {
				window.message.success("已向 "+realname+" 发起好友申请！");
				$("#friend"+uid).removeClass("add").addClass("hasRequest").attr("title","已申请加为好友，请等待对方同意。").unbind();
				
			}
			
		}
	});
}

/*// 添加好友
function addfriend(target_id) {
	$.ajax({
		type : 'post',
		url : contextpath + 'homepage/addfriend.action?target_id=' + target_id,
		async : false,
		dataType : 'json',
		success : function(json) {
			if(json=='OK'){
				window.message.success("消息发送成功！");
				$("#friend"+target_id).remove();
			}
		}
	});
}*/