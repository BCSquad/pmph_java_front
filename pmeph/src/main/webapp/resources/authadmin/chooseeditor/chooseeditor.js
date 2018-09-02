var selectedIds_original=[1];
var selectedIds = [];
var selectedNumIds = [];//数字编委id集合

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
	if ($("#is_published").val() == "true"|| $("#is_locked").val() == "true"||$("#is_force_end").val() == "true"||$("#isFirstEditorLogIn").val() == "false") {
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
        	$("#page-num-temp").val(1);
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
	selectedNumIds=eval($("#selectedNumIds").val());
	queryMain();
	//切换分页每页数据数量时 刷新
	$("#page-size-select").find("li").bind("click",function(){
		$("#page-num-temp").val(1);
		queryMain();
	});
	//切换机构时查询 刷新
	/*$("#select-search-org").find("li").bind("click",function(){
		$("#page-num-temp").val(1);
		queryMain();
	});*/
	$("#search-name").keyup(function(event){
		if(event.keyCode ==13){ //姓名输入框 回车键弹起事件 查询
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
	$("#page-num-temp").val("1");
	queryMain();
}

//初始化复选框的点击事件
function checkboxInit(){
	$("#userTbody").find("input.editor").each(function(i,n){
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
	$("#userTbody").find("input.numEditor").each(function(i,n){
		
		var $m = $(this);
		//刷新 搜索 翻页等后 根据selectedNumIds回填复选框
		if ($.inArray($m.val(),selectedNumIds)>-1) {
			$m.prop("checked",true);
		}else{
			$m.prop("checked",false);
		}
		//复选框被点击
		$m.unbind().bind("click",function(){
			if ($m.prop("checked")) {
				selectedNumIds.push($m.val());
			}else{
				selectedNumIds.removeByValue($m.val());
			}
		});
	});
}

//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	var data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find("input[name='page-size-select']").val(),
			queryName:$("#search-name-temp").val(),
			queryOrg:$("#select-search-org").find("input[name='select-search-org']").val(),
			textBookId:$("#textBookId").val(),
			isFirstEditorLogIn:$("#isFirstEditorLogIn").val(),
			is_digital_editor_optional:$("#is_digital_editor_optional").val(),
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
				$(".no-more").show();
			}else{
				$(".no-more").hide();
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
	data={
			textBookId:$("#textBookId").val(),
			selectedIds:selectedIds.toString(),
			selectedNumIds:selectedNumIds.toString()
			};
	$.ajax({
		type:'post',
		url:contextpath+'chooseEditor/tempSave.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			$("#selectedIds").val(json.selectedIds);
			$("#selectedNumIds").val(json.selectedNumIds);
			if (json.msg!=null) {
				window.message.success(json.msg);
				queryMain();
			}
		}
	});
}

//重置
function selectReset(){
	window.message.confirm(
			'确定要重置吗？将回到最近一次保存后的状态！'
			,{icon: 3, title:'提示',btn:["确定","取消"]}
			,function(index){
				selectedIds=eval($("#selectedIds").val());
				selectedNumIds=eval($("#selectedNumIds").val());
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
	
	if (selectedIds.length>0||selectedNumIds.length>0) {
		window.message.confirm(
				'确定要提交吗？'
				,{icon: 3, title:'编委选定',btn:["确定","取消"]}
				,function(index){
		
					data={
							textBookId:$("#textBookId").val(),
							selectedIds:selectedIds.toString(), //提交时选择前台最新数据，即使未暂存的勾选，也应该提交
							selectedNumIds:selectedNumIds.toString() //数字编委集合
							};
					$.ajax({
						type:'post',
						url:contextpath+'chooseEditor/selectSubmit.action?t='+new Date().getTime(),
						async:false,
						dataType:'json',
						data:data,
						success:function(json){
							$("#selectedIds").val(json.selectedIds);
							$("#selectedNumIds").val(json.selectedNumIds);
							if (json.msg!=null) {
								window.message.success(json.msg);
								
								var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep";
								var textBookId = $("#textBookId").val();
								var logUserId= $("#logUserId").val();

                                /**企业微信消息**/
		                    	$.ajax({
		                            type: 'get',
		                            url: exportWordBaseUrl + '/frontWxMsg/firstEditorChooseSubmit/'+textBookId+"/"+logUserId,
		                            dataType: 'jsonp',
		                            jsonp:"callback", //这里定义了callback在后台controller的的参数名
		                			jsonpCallback:"getMessage", //这里定义了jsonp的回调函数名。 那么在后台controller的相应方法其参数“callback”的值就是getMessage
		                            success:function(wxResult){
		                            	//console.log("success "+wxResult);
		                            	if(wxResult=="1"){
		                            		//window.message.success("微信消息发送成功");
		                            		setTimeout(function(){
		    									window.location.href=contextpath+'chooseEditor/toPage.action?textBookId='+$("#textBookId").val();
		    								}, 800);
		                            	}
		                            },
		                            error:function(XMLHttpRequest, textStatus){
		                            	//console.log("error "+wxResult);
		                            	setTimeout(function(){
											window.location.href=contextpath+'chooseEditor/toPage.action?textBookId='+$("#textBookId").val();
										}, 800);
		                            }
		                            });

                                //window.location.href=contextpath+'chooseEditor/toPage.action?textBookId='+$("#textBookId").val();
								
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

function bfRedirect(uri){
	window.message.confirm(
			"将离开本页面，未保存的操作将丢失，确定离开吗？"
			,{btn:["确定离开","留在本页"]}
			,function(index){
				layer.close(index);
				window.location.href=contextpath+uri;
			}
			,function(index){
				layer.close(index);
			}
	);
}

/**
 * 导出excel表格
 * @param coa 1被选中的 2全部
 */
function getExcel(coa){
	var excelChoosen ="";
	if (coa=='1') {
		excelChoosen = 1;
	}
	window.location.href = contextpath+'excel/download.action?service=ChooseEditorExcelServiceImpl&t='+new Date().getTime()
							+'&textBookId='+$("#textBookId").val()
							+'&isFirstEditorLogIn='+$("#isFirstEditorLogIn").val()
							+'&is_digital_editor_optional='+$("#is_digital_editor_optional").val()
							+'&excelChoosen='+excelChoosen
							+'&textBookName='+encodeURI(encodeURI(($(".top2").text())));
}

