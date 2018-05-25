var selectedIds = [];

$(function () {
//	selectedIds=eval($("#selectedIds").val());//重置


    $('#page-size-select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30,
        onChange:function(){
        	$("#page-num-temp").val(1);
        	$("#page-size-select-hidden").val($("#page-size-select").find("input[name='page-size-select']").val());
            queryMain();
        }
    });
    
    
    
    $('#search-status-select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30,
        onChange:function(){
        	$("#page-num-temp").val(1);
            $("#search-status").val($("#search-status-select").find("input[name='search-status-select']").val());
            queryMain();
        }
    });
    
    $("input.query_input").each(function(){
    	var $t = $(this);
    	$t.unbind().bind("keyup",function(event){
    		if (event.keyCode=="13") {
    			$("#page-num-temp").val(1);
    			queryMain();
			}
    	});
    });
    
    queryMain();
    
    
});

// 复选框选中导出word、excel
//全选
function DoCheck() {
    selectedIds = [];
    var ch = document.getElementsByName("choose");
    if (document.getElementsByName("allChecked")[0].checked == true) {
        for (var i = 0; i < ch.length; i++) {
            ch[i].checked = true;
            selectedIds.push(ch[i].value);
        }
    } else {
        for (var i = 0; i < ch.length; i++) {
            ch[i].checked = false;

        }

    }


    //子复选框有一个未选中时，去掉全选按钮的选中状态
    for (var i = 0; i < ch.length; i++) {
        ch[i].onclick = function () {
            if (!this.checked) {
                document.getElementById("allChecked").checked = false;
            }
        };
    }
}

// 为数组对象增加删除某元素的方法
Array.prototype.removeByValue = function (val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) {
            this.splice(i, 1);
            break;
        }
    }
};

// 初始化复选框的点击事件
function checkboxInit() {
    $("#zebra-table").find("input.editor").each(function (i, n) {
        var $t = $(this);
        // 刷新 搜索 翻页等后 根据selectedIds回填复选框
        if ($.inArray($t.val(), selectedIds) > -1) {
            $t.prop("checked", true);
        } else {
            $t.prop("checked", false);
        }
        // 复选框被点击
        $t.unbind().bind("click", function () {
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
function queryMain() {
    data = {
        pageNum: $("#page-num-temp").val(),
        pageSize: $("#page-size-select-hidden").val(),
        //queryName: $("#search-name-temp").val(),
        material_id: $("#material_id").val(),
        contextpath: contextpath
    };

    data = queryConditionInitFun(data);
    
    $.ajax({
        type: 'post',
        url: contextpath + 'dataaudit/findDataAudit.action?t=' + new Date().getTime(),
        async: false,
        dataType: 'json',
        data: data,
        success: function (json) {
            
            if ($.trim(json.html) == "") {
				$(".fenye").hide();
				$(".no-more").show();
			}else{
				$(".no-more").hide();
				$(".fenye").show();
				$(".pagination").css("display","inline-block");
				$(".pageJump").css("display","inline-block");
				$(".pagination").next("div").css("display","inline-block");
			}
            
            $("#zebra-table").html(json.html);
            //$('#page1').html("");
            $("#totoal_count").html(json.totoal_count);
            //刷新分页栏
            Page({
                num: json.totoal_count,					//页码数
                startnum: $("#page-num-temp").val(),				//指定页码
                elem: $('#page1'),
                callback: function (n) {     //点击页码后触发的回调函数
                    $("#page-num-temp").val(n);
                    queryMain();
                }
            });
            checkboxInit();
            $(".btn_1").attr("disabled",false);
        },
        complete:function(){
        	$(".btn_1").attr("disabled",false);
        }
    });
}

//将不为空的查询条件放入data
function queryConditionInitFun(data){
	$(".query_input").each(function(){
		var $t=$(this);
		//if ($t.val() != null && $t.val() != "") {
		data[$t.attr("name")]=$.trim($t.val());
		//data[$t.attr("name")]=encodeURI(encodeURI($t.val()));
			//编码 到controller层再解码 避免乱码
		//}
	});
	return data;
}

//点击名字跳转页面
function toName(material_id, declaration_id) {
    /*window.location.href = contextpath + 'material/toMaterialAudit.action?material_id='+material_id+'&declaration_id='+declaration_id;*/
    window.location.href = contextpath + 'dataaudit/toMaterialAudit.action?material_id=' + material_id + '&declaration_id=' + declaration_id + '&view_audit=' + $("#view_audit").val();

}


//选择每页数据数量
function selectPageSize() {
    queryMain();
}

//清空条件重查询
function queryClear(){
	$(".btn_1").attr("disabled",true);
	$("#page-num-temp").val(1);
    // var options = document.getElementById('search-status-select').children;
    // options[0].selected=true;
    setTimeout(function(){
        $(".query_input").val("");
     //   $("#search-status-select option:first").prop("selected", 'selected');
         $("#search-status-select").trigger("click");
	     $("#search-status-select").find("li[data-value='']").trigger("click");
    },100);
    //queryMain();
    
}

//查询按钮点击事件触发 
function queryBtnClick() {
	$(".btn_1").attr("disabled",true);
    $("#page-num-temp").val(1);
    
    queryMain();
}


//导出word
var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep"
function exportWord() {
    if (!selectedIds || selectedIds.length <= 0) {
        message.warning("请选择要导出的作家")
        return;
    }
    $.ajax({
        type: 'get',
        url: exportWordBaseUrl + '/front/word/declaration?selectedIds=' + selectedIds.join(","),
        dataType: 'text',
        success: function (json) {
            var wordId = json.replace(/\"/g, "").replace("\\", "");
            if (wordId.indexOf("{") == -1) {
                var index = layer.load(1, {
                    shade: [0.4, '#000'] //0.1透明度的白色背景
                });
                var id = setInterval(function () {
                    $.ajax({
                        type: 'get',
                        url: exportWordBaseUrl + '/word/progress?id=' + wordId,
                        dataType: 'text',
                        success: function (data) {
                            //var data = json.replace(/\"/g, "").replace("\\", "");
                            if (data.indexOf("{") != -1) {
                                data = JSON.parse(data);
                                if (data['state'] == 1) {
                                    window.open(exportWordBaseUrl + "/zip/download?id=" + wordId);
                                    layer.close(index);
                                    clearInterval(id);
                                }
                            } else {
                                message.error("出错啦");
                                layer.close(index);
                                clearInterval(id);
                            }
                        }
                    });
                }, 3000);

            } else {
                message.error("出错啦");
            }
        }
    });

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
function return_on() {
    window.location.href = contextpath + "applyDocAudit/toPage.action";
}






