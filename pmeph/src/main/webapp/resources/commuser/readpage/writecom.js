var ue='';
$(function(){
    ue = UE.getEditor('mText');
	UE.getEditor('mText');
	ue.ready(function(){
		//查询登陆人是否写过长评论
		queryLoginLong();
	});
	
	//默认分数满分
	$("#last_score").val(10);
});

//相关推荐换一换
function fresh(row){
	var type=$("#type_id").val();
	var str='';
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/fresh.action?type='+type+'&&row='+row,
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
				if(row==6){
					$("#about").html(str);
				}else{
					$("#change").html(str);
				}
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

//查询登录人是否写过长评
function queryLoginLong(){
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/queryLoginLong.action?book_id='+$("#book_id").val(),
			async:false,
			dataType:'json',
			success:function(json){
				if(json.returncode=="yes"){
					$(".sxy-btn").attr("disabled",true);
					$("#TitleValue").attr("placeholder",null);
					$("#TitleValue").attr("disabled",true);
					$.each(json.list,function(i,n){
						ue.setContent(n.content);
						$("#TitleValue").val(n.title);
					});
					ue.setDisabled();
				}
			}
		});
}


//写长评
function insertlong(){
	var title=$("#TitleValue").val();
	var book_id=$("#book_id").val();
	var score=$("#last_score").val();
	var content= ue.getContent();
	if(!Empty(book_id)&&!Empty(score)&&!Empty(content)&&!Empty(title)){
	var json={
		book_id:book_id,
		score:score,
		content:content,
		title:title,
	};
	 $.ajax({
			type:'post',
			url:contextpath+'readdetail/insertlong.action',
			async:false,
			data:json,
			dataType:'json',
			success:function(json){
				window.message.success("保存成功！");
				$(".sxy-btn").attr("disabled",true);
				$("#TitleValue").attr("disabled",true);
				ue.setDisabled();
			}
		});
	}else{
		window.message.info("请填写完所有内容！");
	}
}


