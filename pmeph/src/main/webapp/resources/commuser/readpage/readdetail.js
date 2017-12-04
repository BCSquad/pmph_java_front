
$(function(){
$("select").selectlist({
    		zIndex: 10,
            width: 110,
            height: 24,
            optionHeight: 30,
            onChange: function () {
            	
            }  //自定义模拟选择列表项chang
    	});
});
//新增评论
function insert(){
	if($("#content").val()==''){
		alert("请输入评论！");
		return;
	}
	var json={
		 content:$("#content").val(),
		 score:$("#score").val(),
		 book_id:$("#book_id").val(), 
	};
	 $.ajax({
			type:'post',
			url:contxtpath+'/readdetail/insertComment.action',
			async:false,
			dataType:'json',
			data:json,
			success:function(json){
				if(json.returncode=="OK"){
					  alert("评论成功！");
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
			url:contxtpath+'/readdetail/fresh.action?type='+type,
			async:false,
			dataType:'json',
			success:function(json){
				$.each(json,function(i,x){
					str+='<div class="right_9"> <div class="right_10"><img class="right_12" src='+
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
			url:contxtpath+'/readdetail/change.action',
			async:false,
			dataType:'json',
			success:function(json){
				var ste='';
				$.each(json,function(i,x){
					ste+='<div class="right_20"><div class="right_21">'+
						x.bookname+
					'</div><div class="right_22">（'+
					    x.author+
					'）</div></div>';
				});
				$("#comment").html(ste);
			}
		});
}

//点赞
function addlikes(){
	var book_id=$("#book_id").val();
	 $.ajax({
			type:'post',
			url:contxtpath+'/readdetail/addlikes.action?id='+book_id,
			async:false,
			dataType:'json',
			success:function(json){
				if(json.data.returncode=="yes"){
					alert("已经点过赞了！");
				}else{
					alert("点赞成功");
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
			url:contxtpath+'/readdetail/addmark.action',
			data:{favoriteId:favoriteId,bookId:bookId,marks:marks},
			async:false,
			dataType:'json',
			success:function(json){
					window.message.info("已添加收藏");
			}
		});
}
