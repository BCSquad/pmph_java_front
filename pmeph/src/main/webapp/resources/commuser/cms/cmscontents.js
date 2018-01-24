$(function() {
	/* 初始化 */
	var counter = 0; /* 计数器 */
	var pageStart = 0; /* offset */
	var pageSize = 10; /* size */

	/* 首次加载 最终名单初始化 */
	getData(pageStart, pageSize);

	/* 监听加载更多 */
	$(document).on('click', '.js-load-more', function() {
		counter++;
		pageStart = counter * pageSize;

		getData(pageStart, pageSize);
	});
});



function getData(offset, size) {
	var material_id = $("#material_id").val();
	$.ajax({
				type : 'post',
				url : "loadMore.action?material_id=" + material_id,
				dataType : 'json',
				success : function(json) {
					
					var data = json;
					var sum = json.length;
					if(sum==0){
						$(".no-more").show();
					}else{
						$(".no-more").hide();
					}
					var result = '';

					/** **业务逻辑块：实现拼接html内容并append到页面******** */

					if (sum - offset < size) {
						size = sum - offset;
					}
					/* 使用for循环模拟SQL里的limit(offset,size) */
					for ( var i = offset; i < (offset + size); i++) {
						result += "<tr><td   >"
								+ (i + 1)
								+ "</td><td   >"
								+ (data[i].textbook_name ? data[i].textbook_name
										: '--') + "</td><td   >"
								+ (data[i].zb ? data[i].zb : '--')
								+ "</td><td   >"
								+ (data[i].fb ? data[i].fb : '--')
								+ "</td><td   >"
								+ (data[i].bw ? data[i].bw : '--')
								+ "</td></tr>";
					}
					$("#messageTable").append(result);
					/* 隐藏more按钮 */
					if ((offset + size) >= sum) {
						$(".js-load-more").hide();
					} else {
						$(".js-load-more").show();
					}
				},
				error : function(xhr, type) {

				}
			});
}