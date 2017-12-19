$(function() {
	// 合计
	totalcount();
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

// 查看全部 我校申报情况统计
function selectAll() {
	var material_id = $("#material_id").val();
	$.ajax({
		type : 'post',
		url : contextpath + "declareCount/selectAll.action?material_id=" + material_id,
		async : false,
		dataType : 'json',
		success : function(json) {
			var list = json;
			var str = '';
			$.each(list,
					function(i, n) {

						str += "<tr><td   >" + (i + 1) + "</td><td   >"
								+ (n.textbook_name ? n.textbook_name : '--')
								+ "</td><td   >" + (n.decid1 ? n.decid1 : '0')
								+ "</td><td   >" + (n.decid2 ? n.decid2 : '0')
								+ "</td><td   >" + (n.decid3 ? n.decid3 : '0')
								+ "</td><td   >" + (n.dp1 ? n.dp1 : '0')
								+ "</td><td   >" + (n.dp2 ? n.dp2 : '0')
								+ "</td><td   >" + (n.dp3 ? n.dp3 : '0')
								+ "</td></tr>";
					});
			$("#queryTable").html("");
			$("#queryTable").append(str);
			totalcount();
		}
	});
}

// 合计
function totalcount() {
	$("#queryTable").append("<tr></tr>");
	var len = $('#tableCount tr:eq(0) td').length;
	var colnum = $('#queryTable tr').length;
	for ( var i = 0; i < len; i++) {
		var sum = 0;
		if (i == 0) {
			$('#queryTable tr:last').append("<td>" + colnum + "</td>");
		} else if (i == 1) {
			$('#queryTable tr:last').append("<td>合计</td>");
		} else {
			$('#tableCount tr:gt(0) td:nth-child(' + (i + 1) + ')').each(
					function(j, dom) {
						sum += parseFloat($(this).text());
					});
			$('#tableCount tr:last').append("<td>" + sum + "</td>");
		}
	}
}

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

// 查看全部 最终结果名单
function selectResults() {
	var material_id = $("#material_id").val();
	$.ajax({
		type : 'post',
		url : contextpath + 'declareCount/selectResults.action?material_id=' + material_id,
		async : false,
		dataType : 'json',
		success : function(json) {
			var sum = json.length;
			if(sum==0){
				$(".no-more").show();
			}else{
				$(".no-more").hide();
			}
			$("#messageTable").html("");
			var list = json;
			var str = '';
			$.each(list, function(i, n) {

				str += "<tr><td   >" + (i + 1) + "</td><td   >"
						+ (n.textbook_name ? n.textbook_name : '--')
						+ "</td><td   >" + (n.zb ? n.zb : '--')
						+ "</td><td   >" + (n.fb ? n.fb : '--')
						+ "</td><td   >" + (n.bw ? n.bw : '--') + "</td></tr>";
			});
			$("#messageTable").append(str);
		}
	});
}

// 导出excel 我校申报情况统计

function exportExcel() {
	window.location.href = contextpath
			+ 'excel/download.action?service=declareCountExcel&material_id='
			+ $("#material_id").val();
}

// 导出excel 最终结果名单

function exportResultExcel() {
	window.location.href = contextpath
			+ 'excel/download.action?service=declareResultExcel&material_id='
			+ $("#material_id").val();
}
