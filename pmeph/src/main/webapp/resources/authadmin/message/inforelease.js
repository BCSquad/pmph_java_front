function DoCheck() {
	var ch = document.getElementsByName("choose");
	if (document.getElementsByName("allChecked")[0].checked == true) {
		for ( var i = 0; i < ch.length; i++) {
			ch[i].checked = true;
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





$(function(){
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	//给父页面传值
	$('#send2').on('click', function(){
	var chk_value =[]; 
		$('input[name="choose"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
		}); 
		/*if(chk_value.length==0){
			window.message.error("请选择教材报名者");
			return false;
		}*/
	    parent.$('#radioValue').val(chk_value);
	   // parent.layer.tips('Look here', '#parentIframe', {time: 5000});
	   
	    parent.layer.close(index);
	});
	$('#backupdate').on('click', function(){
		    parent.layer.close(index);
		});
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
    $('#select-search-condition').selectlist({
    	zIndex: 10,
        width: 600,
        height: 40,
        fiter: true,
        placeholder:'输入教材名称或下拉选择教材',
        optionHeight: 30,
        onChange: function () {
        	var sel=$("#select-search-condition").find($("input[name='select-search-condition']")).val();
        	$("#search-name-temp").val(sel);
        	queryMain();
        } 
    });
   
	queryMain();
	
	
});


//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find($("input[name='page-size-select']")).val(),
			queryName:$("#search-name-temp").val(),
			contextpath:contextpath
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'/info/infoRelease.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			$("#totoal_count").val(json.totoal_count);
			addColum(json.listMap,json.startNum);
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



//选择每页数据数量
function selectPageSize(){
	queryMain();
}

function addColum(list,startNum){
	
	//清空
	$("#tbody1").html("");
	var str='';
	$.each(list,function(i,n){
		str+= "<tr><td style='text-align: center;'><input  name='choose' type='checkbox' class='check_box' value='"+n.id+"' /></td><td   class='moren'>"+startNum+"</td><td   class='moren'>"+n.textbook_name+"</td><td   class='moren'>"+n.textbook_round+"</td></tr>";
		startNum += 1;
	});
	$("#tbody1").append(str);
}


