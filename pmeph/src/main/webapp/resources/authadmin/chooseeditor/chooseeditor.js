var selectedIds_original=[1];
var selectedIds = [];

//为数组对象增加删除某元素的方法
Array.prototype.removeByValue = function(val) {
	  for(var i=0; i<this.length; i++) {
	    if(this[i] == val) {
	      this.splice(i, 1);
	      break;
	    }
	  }
	};
	
$(function(){
	if ($("#is_list_selected").val() == "true") {
		$("#handleBtn").hide();
	}else{
		$("#handleBtn").show();
	}
	
	$('#select-search-org').selectlist({
        zIndex: 10,
        width: 208,
        height: 40,
        optionHeight: 40,
        fiter: true,
        placeholder:'输入申报单位名称或下拉选择',
        onChange: function () {
        	/*var sel=$("#select-search-org").find("input[name='select-search-org']").val();
        	$("#search-name-org").find("input[class='select-button']").val(sel);*/
        	queryMain();
        } 
    });
	$('#page-size-select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });
	selectedIds=eval($("#selectedIds").val());
	queryMain();
	//切换分页每页数据数量时 刷新
	$("#page-size-select").find("li").bind("click",function(){
		$("#page-num-temp").val(1);
		queryMain();
	});
	//切换机构时查询 刷新
	$("#select-search-org").find("li").bind("click",function(){
		$("#page-num-temp").val(1);
		queryMain();
	});
	$("#search-name").keyup(function(event){
		if(event.keyCode ==13){ //回车键弹起事件
			queryBtnClick();
		  }
	});
	
	
});

//查询按钮点击事件触发 
function queryBtnClick(){
	$("#page-num-temp").val(1);
	$("#search-name-temp").val($("#search-name").val());
	$("#select-search-org").find("input[name='select-search-org']").val($("#select-search-org").find("li[class='selected']").attr("data-value"));
	queryMain();
}

//选择每页数据数量
function selectPageSize(){
	queryMain();
}

//初始化复选框的点击事件
function checkboxInit(){
	$("#userTbody").find("input[type='checkbox']").each(function(i,n){
		var $t = $(this);
		//刷新 搜索 翻页等后 根据selectedIds回填复选框
		if ($.inArray($t.val(),selectedIds)>-1) {
			$t.prop("checked",true);
		}else{
			$t.prop("checked",false);
		}
		//复选框被点击
		$t.unbind().bind("click",function(){
			if ($t.prop("checked")) {
				selectedIds.push($t.val());
			}else{
				selectedIds.removeByValue($t.val());
			}
			//alert(selectedIds);
		});
	});
}

//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find("input[name='page-size-select']").val(),
			queryName:$("#search-name-temp").val(),
			queryOrg:$("#select-search-org").find("input[name='select-search-org']").val(),
			textBookId:$("#textBookId").val(),
			contextpath:contextpath
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'chooseEditor/queryUserList.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			$("#userTbody").html(json.html);
			
			if (json.html.trim() == "") {
				$(".pagination-wrapper").hide();
			}else{
				$(".pagination-wrapper").show();
				$(".pagination").css("display","inline-block");
				$(".pageJump").css("display","inline-block");
				$(".pagination").next("div").css("display","inline-block");
			}
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
			 //重新初始化复选框
			 checkboxInit();
		}
	});
}

//暂存
function tempSave(){
	//alert(selectedIds);
	data={
			textBookId:$("#textBookId").val(),
			selectedIds:selectedIds.toString()
			};
	$.ajax({
		type:'post',
		url:contextpath+'chooseEditor/tempSave.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			$("#selectedIds").val(json.selectedIds);
			if (json.msg!=null) {
				window.message.success(json.msg);
			}
		}
	});
}

//重置
function selectReset(){
	window.message.confirm(
			'确定要重置吗？将回到最近一次暂存状态！'
			,{icon: 3, title:'提示',btn:["确定","取消"]}
			,function(index){
				selectedIds=eval($("#selectedIds").val());
				//重新初始化复选框
				checkboxInit();
				layer.close(index);
			}
			,function(index){
				layer.close(index);
			}
			);
}

//提交
function selectRubmit(){
	if (selectedIds.length>0) {
		window.message.confirm(
				'确定要提交吗？'
				,{icon: 3, title:'编委选定',btn:["确定","取消"]}
				,function(index){
		
					data={
							textBookId:$("#textBookId").val(),
							selectedIds:selectedIds.toString() //提交时选择前台最新数据，即使未暂存的勾选，也应该提交
							};
					$.ajax({
						type:'post',
						url:contextpath+'chooseEditor/selectSubmit.action?t='+new Date().getTime(),
						async:false,
						dataType:'json',
						data:data,
						success:function(json){
							$("#selectedIds").val(json.selectedIds);
							if (json.msg!=null) {
								window.message.success(json.msg);
							}
						}
					});
					layer.close(index);
				}
				,function(index){
					layer.close(index);
				}
				);
	}else{
		window.message.warning("未选择任何一个编委！");
	}
	
	
	
}