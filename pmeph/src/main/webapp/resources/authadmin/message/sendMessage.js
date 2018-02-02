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
/**
 * 发送请求后 界面初始化
 */
function send_init(){
	//教材选择界面隐藏 表单显示
	$("#message-body").show();
	$("#selectBook").hide();
}

/**
 * 发送请求
 */
function sending(){
	//发送表单 请求
	document.getElementById("theForm").submit();
	document.getElementById("theForm").reset();
	window.message.success("发送成功");
}
/**
 * 选择的是教材报名者 点击发送后 显示教材选择界面
 */
function send_after(){
	//发送请求 显示 教材选择界面 隐藏表单界面
	//$("#message-body").hide();
	//$("#selectBook").show();
	
	$("#message-body").css({"display":"none"});
	$("#selectBook").css({"display":"block"});
}
/**
 * 发送请求
 */
function sendf(){
	if($('input:radio[name="sendObject"]:checked').val()!='0'){
		send_after();
		queryMain();
	}else{
		getValue();
		sending();
	}
}

$(function(){
  /*  $(".icon").hide();
    $(".file-tip").hide();
    $("#file_id").change(function(){  // 当 id 为 file 的对象发生变化时
        if(($("#file_id").val()).length==0){
            $("#file_name").text();  //将 #file 的值赋给 #a
            $("#up_txt").text('重新上传');
        }else{
            $("#file_name").text($("#file_id").val());  //将 #file 的值赋给 #a
            $(".icon").show();
            $("#up_txt").text('重新上传');

            if(((this.files[0].size).toFixed(2))>=(100*1024*1024)){
                			$(".file-tip").show();
                                return false;
                          }
        }


    })*/
	if($("#validate").val()=="0"){
		window.message.warning("必选项不为空");
	}else if($("#validate").val()=="1"){
		window.message.warning("标题长度不能超过30个字");
	}
	if($('input:radio[name="sendObject"]:checked').val()!='0'){
		$("#send").val("下一步");
	}
	$('input:radio[name="sendObject"]').change(function(){
    	if($('input:radio[name="sendObject"]:checked').val()!='0'){
    		$("#send").val("下一步");
    	}else{
    		$("#send").val("发送");
    	}
    	});
	send_init();
	$('#send2').on('click', function(){
		var chk_value =[]; 
			$('input[name="choose"]:checked').each(function(){ 
			chk_value.push($(this).val()); 
			}); 
			if(chk_value.length==0){
				window.message.error("请选择教材报名者");
				return false;
			}
		    $('#radioValue').val(chk_value);
		    sending();
		    send_init();
		});

	$('#backupdate').on('click', function(){
		//教材选择界面隐藏 表单显示
		send_init();
		$("#send").val("下一步");
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
   
	
	
    // 单选框 change时间
   /* $('input:radio[name="sendObject"]').change(function(){
    	if($('input:radio[name="sendObject"]:checked').val()!='0'){
    		//location.href = "http://localhost:8080/pmeph/info/toPage.action";
    		//$("#send").val("下一步")
    		var index =layer.open({
    			  type: 2,
    			  content: contextpath+'/info/toPage.action',
    			  area: ['320px', '195px'],
    			  maxmin: true
    			});
    		layer.full(index);
    		
    	}
    	});*/
});


//$("input[name='submit']").onclick =
	
/*	function message(){
	var titleValue = $("#TitleValue").val();
	var radioValue ; //获取单选按钮的值
	if($('#one').attr("checked")){
		//所有人
		radioValue = $('#one').val();
		}else{
			//教材所有者
		}
	var UEContent = UE.getEditor('mText').getContent();
	
	//文件对象  this.files[0] 文件名 file.name 文件类型  file.type  文件大小  file.size  文件内容
	
	//var file = $("#file_id").files[0];
	var file = $('input[name="fileTrans"]').prop('files')[0];//获取到文件列表  IE 下未传文件是null chorme 下未传文件是undifine
	
	//定义一个参数对象  不能这么做 理由 File 是一个对象 无法通过ajax 请求 formData 对象用于html5 不能兼容多浏览器
	var  params ={
			"titleValue":titleValue,
			"radioValue":radioValue,
			"UEContent":UEContent,
			"file":file
	}
	
	//发送AJAx请求
	$.ajax({
	      type: "POST",
	      url: "authSendMessage/sendMessage.action",
	      data: params,
	      dataType : "json",
	      success: function(respMsg){
	      }
	   });
	
	
}*/

//表单提交前 给表单域中赋值
function getValue(){
	
	//var UEContent = UE.getEditor('mText').getContent();
	var UEContent = $("#UEContent").val();
	 var radioval=$('input:radio[name="sendObject"]:checked').val();
	 if(radioval=='0'){
		 $("#radioValue").val(radioval);
		 return true;
	 }
	if($("#radioValue").val().length==0){
		window.message.error("请选择教材报名者");
		return false;
	}
	//$("#UEContent").val(UEContent);
	
	if($("#TitleValue").val().length==0){
		window.message.warning("请输入标题");
		//$('input[type="submit"]').prop('disabled', true);
		return false;
	}
	if(UEContent.length==0){
		window.message.warning("请输入内容");
		//$('input[type="submit"]').prop('disabled', true);
		return false;
	}
	
	
}



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
	//alert(typeof list);
	//清空
	$("#tbody1").html("");
	var str='';
	$.each(list,function(i,n){
		str+= "<tr><td style='text-align: center;'><input  name='choose' type='checkbox' class='check_box' value='"+n.id+"' /></td><td   class='moren'>"+startNum+"</td><td   class='moren'>"+n.textbook_name+"</td><td   class='moren'>"+n.textbook_round+"</td></tr>";
		startNum += 1;
	});
	$("#tbody1").append(str);
}




