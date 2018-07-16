/* 初始化 */
var counter = 0; /* 计数器 */
var pageStart = 0; /* offset */
var pageSize = 3; /* size */

$(function() {
    	//积分筛选
        $('#sele').selectlist({
            zIndex: 10,
            width: 80,
            height: 20,
            optionHeight: 20,
            triangleColor:'#333333'
//            onChange:function (){
//				window.location.href=contextpath +"integral/findPointByMonth.action?condition="+$("input[name='sele']") .val();
//            	queryTime();
//            }
        });
        
        $("#sele").find("li").bind("click",function(){
        	queryTime();
    	});
        

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
	var condition=$("input[name='sele']") .val();
	$.ajax({
				type : 'post',
				url : "loadMore.action?condition="+condition,
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
					var $td = $("#messageTable").find('td');
					/* 使用for循环模拟SQL里的limit(offset,size) */
					for ( var i = offset; i < (offset + size); i++) {
						/*if(data[i].point>0){
							$(".col").addClass("red");
						}else{
							$(".col").addClass("green");
						}*/
						result += "<tr><td   >"
								+ (data[i].rule_name ? data[i].rule_name
										: '--') + "</td><td  class='col' style='font-size: 18px;'>"
								+ (data[i].point ? data[i].point : '--')
								+ "</td><td   >"
								+formatDate(data[i].gmt_create,'yyyy-MM-dd hh:ss:mm')
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


//查询三个月内的积分
function queryTime(){
	var condition=$("input[name='sele']") .val();
	$.ajax({
		type : 'post',
		url : contextpath + "integral/findPointByMonth.action?condition="+condition,
		async : false,
		dataType : 'json',
		success : function(json) {
			var sum = json.length;
			if(sum==0){
				$(".no-more").show();
			}else{
				$(".no-more").hide();
			}
			$(".js-load-more").hide();
			$("#messageTable").html("");
			var list = json;
			var str = '';
			$.each(list, function(i, n) {

				str += "<tr><td   >"
					+ (n.rule_name ? n.rule_name
							: '--') + "</td><td  class='col' style='font-size: 18px;'>"
					+ (n.point ? n.point : '--')
					+ "</td><td   >"
					+formatDate(n.gmt_create,'yyyy-MM-dd hh:ss:mm')
					+ "</td></tr>";
			});
			$("#messageTable").append(str);
		}
	});

}


//时间转化
function formatDate(nS,str) {
	  if(!nS){
	    return "";
	  }
	  var date=new Date(nS);
	  var year=date.getFullYear();
	  var mon = date.getMonth()+1;
	  var day = date.getDate();
	  var hours = date.getHours();
	  var minu = date.getMinutes();
	  var sec = date.getSeconds();
	  if(str=='yyyy-MM-dd'){
		  return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day);
	  }else if(str=='yyyy.MM.dd'){
		  return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day);
	  }else if(str=='yyyy.MM.dd hh:ss:mm'){
		  return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
	  }else{
		  return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
	  }

}

/**
 * 加载更多
 */

 function  loadData() {
     counter++;
     pageStart = counter * pageSize;
	 getData(pageStart, pageSize);
}
