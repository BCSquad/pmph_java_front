//初始化加载
$(function () {
	//初始化分页器
	var flag=$("#n").val();
	if(flag==''){
		flag=1;
	}else{
		flag=parseInt(flag);
	}
	 Page({
	        num: $("#allpage").val(),	//页码数
	        startnum: flag,	//指定页码
	        elem: $('#page1'),		    //指定的元素
	        callback: function (n) {	//回调函数
	           console.log(n);
	           location.href=contextpath+'articlesearch/change.action?n='+n+'&&m='+$('input[name=edu]').val();
	        }
	    });
	   $('select').selectlist({
           zIndex: 10,
           width: 110,
           height: 30,
           optionHeight: 30,
           onChange:function(){
        	   var m=$('input[name=edu]').val();
        	   var n=1;
        	   location.href=contextpath+'articlesearch/change.action?n='+n+'&&m='+$('input[name=edu]').val();
           }
       });
});
//跳转到书籍搜索页面
function tobookpage(){
	window.location=contextpath+'booksearch/toPage.action?search='+$("#selectall").val()+'&&real_search=""';
}

//点赞/取消赞
function changelikes(flag){
	var status='';
	var id=$("#mainid"+flag).val();
	var str=$("#likes"+flag).val();
	if($("#praise"+flag).hasClass("nohandPicture")){
		status='no';
	}else{
		status='add';
	}
	   $.ajax({
			type:'post',
			url:contextpath+'articlesearch/changelikes.action?id='+id+'&&status='+status,
			async:false,
			dataType:'json',
			success:function(json){
				if(json.returncode=="OK"){
					$("#likenum"+flag).html(json.likes);
					if(str=='nohandPicture'){
						$("#praise"+flag).removeClass("nohandPicture");
						$("#praise"+flag).addClass("handPicture");
						$("#likes"+flag).val("handPicture");
					}else{
						$("#praise"+flag).removeClass("handPicture");
						$("#praise"+flag).addClass("nohandPicture");
						$("#likes"+flag).val("nohandPicture");
					}
				}
			}
		});
}

//跳转页面
function jump(){
	 var n=$("#jumpId").val();
	 if(n>$("#allppage").html()){
		 alert("超过了最大页数！");
		 return;
	 }
	 location.href=contextpath+'articlesearch/change.action?n='+n+'&&m='+$('input[name=edu]').val();
}

//模糊查询
function queryall(){
	var title=$("#selectall").val();
	location.href=contextpath+'/articlesearch/queryall.action?title='+title;
}