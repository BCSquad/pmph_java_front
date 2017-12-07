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
			id:$("#book_id").val(),
	};
	 $.ajax({
		type:'post',
		url:contextpath+'readdetail/changepage.action?',
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
           	+n.content
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
//新增评论
function insert(){
	if($("#content").val()==''){
		window.message.info("请输入评论！");
		return;
	}
	var json={
		 content:$("#content").val(),
		 score:$("#last_score").html(),
		 book_id:$("#book_id").val(), 
	};
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/insertComment.action',
			async:false,
			dataType:'json',
			data:json,
			success:function(json){
				if(json.returncode=="OK"){
					  $("#content").val(null);
					  window.message.success("评论成功");
				}
			}
		});
}

//相关推荐换一换
function fresh(){
	var type=$("#type_id").val();
	var str='';
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/fresh.action?type='+type,
			async:false,
			dataType:'json',
			success:function(json){
				$.each(json,function(i,x){
					str+='<div class="right_9" onclick="todetail('+x.id+')"> <div class="right_10"><img class="right_12" src='+
                    	x.image_url+ 
						'></div><div class="right_11">'+
                        x.bookname+
                     '</div></div>';
				});
				$("#change").html(str);
			}
		});
}

//人卫推荐换一换
function change(){
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/change.action',
			async:false,
			dataType:'json',
			success:function(json){
				var ste='';
				$.each(json,function(i,x){
					ste+='<div class="right_20"><div class="right_21" onclick="todetail('+
					    x.id
				       +')">'+
						x.bookname+
					   '</div><div class="right_22">（'+
					    x.author+
					   '）</div></div>';
				});
				$("#comment").html(ste);
			}
		});
}

//人卫推荐跳转到详情书
function todetail(flag){
	location.href=contextpath+'readdetail/todetail.action?id='+flag;
}
//点赞
function addlikes(){
	var book_id=$("#book_id").val();
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/addlikes.action?id='+book_id,
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
	var favoriteId=$("input[name=edu]").val();
	var bookId=$("#book_id").val();
	var marks=$("#marks").val();
	if(favoriteId==""){
		window.message.info("请选择收藏夹");
		return
	}
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/addmark.action',
			data:{favoriteId:favoriteId,bookId:bookId,marks:marks},
			async:false,
			dataType:'json',
			success:function(json){
					window.message.info("已添加收藏");
			}
		});
}

