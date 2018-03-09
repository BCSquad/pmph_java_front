var selectedIds = [];

$(function(){
//	selectedIds=eval($("#selectedIds").val());//重置
	
	
	
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

// 复选框选中导出word、excel
//全选
function DoCheck() {
	selectedIds=[];
	var ch = document.getElementsByName("choose");
	if (document.getElementsByName("allChecked")[0].checked == true) {
		for ( var i = 0; i < ch.length; i++) {
			ch[i].checked = true;
			selectedIds.push(ch[i].value);
		}
	} else {
		for ( var i = 0; i < ch.length; i++) {
			ch[i].checked = false;
			
		}
		
	}
	
	
	//子复选框有一个未选中时，去掉全选按钮的选中状态
	for ( var i = 0; i < ch.length; i++) {
		ch[i].onclick = function() {
			if (!this.checked) {
				document.getElementById("allChecked").checked = false;
			}
		};
	}
}

// 为数组对象增加删除某元素的方法
Array.prototype.removeByValue = function(val) {
	for ( var i = 0; i < this.length; i++) {
		if (this[i] == val) {
			this.splice(i, 1);
			break;
		}
	}
};

// 初始化复选框的点击事件
function checkboxInit() {
	$("#zebra-table").find("input.editor").each(function(i, n) {
		var $t = $(this);
		// 刷新 搜索 翻页等后 根据selectedIds回填复选框
		if ($.inArray($t.val(), selectedIds) > -1) {
			$t.prop("checked", true);
		} else {
			$t.prop("checked", false);
		}
		// 复选框被点击
		$t.unbind().bind("click", function() {
			if ($t.prop("checked")) {
				selectedIds.push($t.val());
				
			} else {
				selectedIds.removeByValue($t.val());
			}
			
			// alert(selectedIds);
		});
	});
}



// 条件设定完成后查询的实现 点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
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
			if (json.html.trim()!= "") {
				$(".no-more").hide();
			}else{
				$(".no-more").show();
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
			 checkboxInit();
		}
	});
}

//点击名字跳转页面
function toName(material_id,declaration_id){
	/*window.location.href = contextpath + 'material/toMaterialAudit.action?material_id='+material_id+'&declaration_id='+declaration_id;*/
	window.location.href = contextpath + 'dataaudit/toMaterialAudit.action?material_id='+material_id+'&declaration_id='+declaration_id+'&view_audit='+$("#view_audit").val();

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


//导出word

function exportWord() {
//	window.location.href = contextpath+ 'word/declaration.action?selectedIds=' + selectedIds.toString();
}


//导出excel

function exportExcel() {
	window.location.href = contextpath
			+ 'excel/download.action?service=dataAuditExcel&queryName='
			+ $("#search-name-temp").val() + '&material_id='
			+ $("#material_id").val() + '&userId=' + $("#userId").val()
			+ '&selectedIds=' + selectedIds.toString();
}

//返回
function return_on(){
	window.location.href=contextpath+"applyDocAudit/toPage.action";
}






