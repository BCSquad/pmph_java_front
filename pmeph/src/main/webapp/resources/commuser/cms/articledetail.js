$(document).ready(function(){
	 //为所有的class为scorestar1绑定mouseout和mouseover事件。bind({事件名：function(){},事件名：function(){}})的方法绑定多个事件
	 $(".scorestar1").bind({
	  mouseover: function () {
		  $(this).css("background-position", "-183px -174px").prevAll().css("background-position", "-183px -174px");
		  $(this).nextAll().css({"background-position": "-183px -153px"});
		  var score=parseInt($(this).attr("id").substring(5))*2+'.0';
		  $("#last_score").html(score);
	  }
	  });
});

$(function(){
	 Page({
	        num: parseInt($("#allppage").html()),	//页码数
	        startnum: parseInt($("#pageNumber").val()),	//指定页码
	        elem: $('#page1'),		    //指定的元素
	        callback: function (n) {    //回调函数
	          console.log(n);
	        	changepage(n);
	        },
	    });
	   $('select').selectlist({
           zIndex: 10,
           width: 110,
           height: 30,
           optionHeight: 30,
           onChange:function(){
        	   var n=1;
        	   changepage(n);
           }
       });
});
//分页前的初始化
function beforechange(){
	var n=$("#jumpId").val();
	changepage(n);
	$("#jumpId").val(null);
}

//分页的具体实现
function changepage(n){
	var json={
			pageNumber:n,	
			allppage:$('input[name=edu]').val(),
			/*id:$("#book_id").val(),*/
	};
	 $.ajax({
		type:'post',
		url:contextpath+'articledetail/changepage.action',
		async:false,
		dataType:'json',
		data:json,
		success:function(json){
			var str='';
			$.each(json.rows,function(i,n){
				str+='<div class="item"><div class="item_title">'
					+'<div style="float: left;"><img src="';
					if(n.avatar==''||n.avatar=='DEFAULT'||n.avatar==null){
						str+=contextpath+'statics/image/rwtx.png';
					}else{
						str+=n.avatar;
					}
					str+='" class="picturesize"/></div><div style="float: left;margin-left: 10px;margin-top: 5px;">'+
					n.realname
					+'</div><div style="float: left;margin-left: 10px;">';
           	if(n.score<=3){
           		str+='<span class="rwtx1"></span>'
           		+'<span class="rwtx2"></span>'
           		+'<span class="rwtx2"></span>'
           		+'<span class="rwtx2"></span>'
           		+'<span class="rwtx2"></span>'
           	}else if(n.score<=5){
           		str+='<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx2"></span>'
           		+'<span class="rwtx2"></span>'
           		+'<span class="rwtx2"></span>'
           	}else if(n.score<=7){
           		str+='<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx2"></span>'
           		+'<span class="rwtx2"></span>'
           	}else if(n.score<=9){
           		str+='<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx2"></span>'
           	}else if(n.score==10){
           		str+='<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           		+'<span class="rwtx1"></span>'
           	}
           	str+='</div><div class="date_content"><div class="date">'
           	+n.gmt_create
           	+'</div></div></div><div class="item_content">'
           	+n.mid
           	+'</div><hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;"></div>';
			});
			$("#changepage").html(str);
			$("#allppage").html(json.pageTotal);
			Page({
		        num: json.pageTotal,	    //页码数
		        startnum: json.pageNumber,	//指定页码
		        elem: $('#page1'),	        //指定的元素
		        callback: function (n) {    //回调函数
			          console.log(n);
			          changepage(n);
			        },
		    });
		},
	});
   
};


//1.新增评论
function insert(){
	if($("#content").val()==''){
		window.message.info("发表我的言论...");
		return;
	}
	var json={
		 content:$("#content").val(),
		 score:$("#last_score").html(),
	};
	 $.ajax({
			type:'post',
			url:contextpath+'articledetail/insertComment.action',
			async:false,
			dataType:'json',
			data:json,
			success:function(json){
				if(json.returncode=="OK"){
					  $("#content").val(null);
					  window.message.success("发表评论成功");
				}
			}
		});
}



//1.相关文章换一换
function change(){
	 $.ajax({
			type:'post',
			url:contextpath+'articledetail/change.action',
			async:false,
			dataType:'json',
			success:function(json){
				var ste='';
				$.each(json,function(i,n){
					ste+='<div class="right_20"><div class="right_21">'+n.title+'</div><div class="right_22">'+n.realname+'</div></div>';
				});
				$("#comment").html(ste);
			}
		});
}


//点赞
function addlikes(){
	/*var book_id=$("#book_id").val();*/
	 $.ajax({
			type:'post',
			url:contextpath+'articledetail/addlikes.action',
			async:false,
			dataType:'json',
			success:function(json){
				if(json.returncode=="yes"){
					$("#dz").attr("src",contextpath+"statics/image/dz02.png");
				}else{
					$("#dz").attr("src",contextpath+"statics/image/dz01.png");
				}
			}
		});
}
//收藏
function addmark(){
//	var favoriteId=$("input[name=edu]").val();
	var wid=$("#wid").val();
	var marks=$("#marks").val();
//	if(favoriteId==""){
//		window.message.info("请选择收藏夹");
//		return
//	}
	 $.ajax({
			type:'post',
			url:contextpath+'articledetail/addmark.action',
			data:{wid:wid},
			async:false,
			dataType:'json',
			success:function(json){
					if(json.returncode=="OK"){
						$("#sc").attr("src",contextpath+"statics/image/sc101(1).png");
					}else{
						$("#sc").attr("src",contextpath+"statics/image/s102(1).png");
					}
			}
		});
}




