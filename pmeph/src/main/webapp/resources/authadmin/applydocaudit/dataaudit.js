$(function(){
	
	
	
	$('#page-size-select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });
	//切换分页每页数据数量时 刷新
	$("#page-size-select").find("li").bind("click",function(){
		
		$("#page-num-temp").val(1);
		queryMain();
	});
	queryMain();
});


//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find("input[name='page-size-select']").val(),
			queryName:$("#search-name-temp").val(),
			material_id:$("#material_id").val(),
			contextpath:contextpath
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'dataaudit/findDataAudit.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			if (json.html.trim() == "") {
				$("#fenye").hide();
			}else{
				$("#fenye").show();
			}
			$("#zebra-table").html(json.html);
			$('#page1').html("");	
			$("#totoal_count").html(json.totoal_count);
			//刷新分页栏
			 Page({
                num: json.totoal_count,					//页码数
                startnum: $("#page-num-temp").val(),				//指定页码
                elem: $('#page1'),
                callback: function (n){     //点击页码后触发的回调函数
                	$("#page-num-temp").val(n);
                	queryMain();
                }
                });
		}
	});
}

//点击名字跳转页面
function toName(material_id,declaration_id){
	window.location.href = contextpath + 'material/showMaterial.action?material_id='+material_id+'&declaration_id='+declaration_id;
}


//选择每页数据数量
function selectPageSize(){
	queryMain();
}

//查询按钮点击事件触发 
function queryBtnClick(){
	
	$("#page-num-temp").val(1);
	$("#search-name-temp").val();
	queryMain();
	
}



