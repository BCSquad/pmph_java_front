


//点赞按钮 点击 触发 点赞和取消赞
function likeSwitch(bookId,icon){
	var $icon = $(icon);
	var data={
			bookId:bookId
	};
	$.ajax({
		type:'post',
		url:contextpath+'booksearch/likeSwitch.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			if (json != null && json.switchResult != null) {
				if (json.switchResult == "active") {
					$icon.addClass("active");
					$icon.attr("title","取消赞");
				}else if(json.switchResult == "non-active"){
					$icon.removeClass("active");
					$icon.attr("title","点赞");
				}
				$icon.prev("div").html(json.like_count);
			}
		}
	});
}

