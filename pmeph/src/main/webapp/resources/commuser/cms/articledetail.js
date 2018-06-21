$(function(){

    $('#content').tipso({validator: "isNonEmpty", message: "评论内容不能为空"});

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
	   
	   changepage(1);

       change();
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
			wid:$('#wid').val()
			
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
						str+=contextpath+'statics/image/default_image.png';

                    }else{
						str+=contextpath+'image/'+n.avatar+'.action';
					}
					str+='" height="30"  width="30"/></div><div style="float: left;margin-left: 10px;margin-top: 5px;">'+
					n.nickname
					+'</div>';
           
           	str+='<div class="date_content"><div class="date">'
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
			          changepage(n);
			        }
		    });
		}
	});
   
};


//1.新增评论
function insert(){
	if($.fireValidator()){
	var json={
		 content:$("#content").val(),
		 wid:$("#wid").val(),
		 title:$("#title").val()
		/* score:$("#last_score").html(),*/
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
					  
				}else if (json.returncode == 'ERROR'){
					var words = json.value;
					var content = document.getElementById("content");
					var contentValue = $("#content").val();
					for (var i = 0 ; i < words.length ; i++){
						if (json.content.indexOf(words[i]) > -1){
							content.style.border = '3px solid red';
							window.message.error("文章评论中含有敏感词,请检查修改后再保存或提交");
	            			return;
						}
					}
				}
			}
		});
    }
}



//1.相关文章
function change(){
	var count=0;
	var startrow=$("#startrow").val();
	var json={
			 wid:$("#wid").val(),
		     startrow:startrow,
		};
	 $.ajax({
			type:'post',
			url:contextpath+'articledetail/change.action',
			async:false,
			data:json,
			success:function(json){
				var ste='';
				$.each(json,function(i,n){
					ste+='<div class="right_20"><div class="right_21">'+n.title+'</div><div class="right_22">'+n.author_name+'</div></div>';
				    count=n.end;
				});
				$("#comment").html(ste);
				var newrow=parseInt(startrow)+5;
				debugger
				if(newrow>count){
                    $("#startrow").val(0);
				}else{
                    $("#startrow").val(newrow);
				}
			}
		});
}


//点赞
function addlikes(){
	var json={
			 wid:$("#wid").val()
		};
	 $.ajax({
			type:'post',
			url:contextpath+'articledetail/addlikes.action',
			async:false,
			dataType:'json',
			data:json,
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

//跳转到详情文章
function todetail(id) { 
    location.href = contextpath + 'articledetail/toPage.action?wid=' + id;
}

//评论检查出敏感词时，用户修改文本域获取焦点，则把红边去掉
$(function(){
	$("#content").focus(function(){
		$("#content").css("border","none");
	});
});

