$(function () {
	$("#book_type").val(633);
});
//下一页
function on(state){
	var startrows=$("#before").text();
	var type=$("#book_type").val();
	if(state=="next"){
		 var flag=$("#next").text();
		 if(flag==startrows){
			 message.info("已经是最后一页了！");
			 return;
		 }
	}else if(state=="before"){
		 var flag=$("#before").text();
		 if(flag==1){
			 message.info("已经是第一页了！");
			 return;
		 }
	}
    $.ajax({
		type:'post',
		url:contextpath+'homepage/changerows.action?startrows='+startrows+'&&state='+state+'&&type='+type,
		async:false,
		dataType:'json',
		success:function(json){
			$("#homepagebook").html(json.homepagebook);
			$("#before").html(json.thisrows);
		}
	});
}

//书籍分类
function chooseType(state){
    $.ajax({
		type:'post',
		url:contextpath+'homepage/chooseType.action?state='+state,
		async:false,
		dataType:'json',
		success:function(json){
			$("#homepagebook").html(json.homepagebook);
			$("#next").html(json.allrows);
			$.each(json.listType,function(i,x){
				if(i==0){
					$("#typeOne").html(x.type_name);
				}else{
					$("#typeTwo").html(x.type_name);
				}
			});
			$(".tab").removeClass("active");
			$("#"+state).addClass("active");
			$("#book_type").val(state);
			$("#before").html(1);
			var labelHtml="";
			for(var i=0;i<json.listLabel.length;i++){
				labelHtml+='<a href="'+json.listLabel[i].type+'" class="little"><span class="little_content">'+json.listLabel[i].note+'</span></a>';
			}
			$(".div_photo1 .div1").html(labelHtml);
		}
	});
}

//根据分类改变图书畅销榜
function changesale(state){

	if(state=='school'){
		type=633;
	}else if(state=='graduate'){
		type=634;
	}else if(state=='continue'){
		type=635;
	}else if(state=='exam'){
		type=636;
	}$.ajax({
			type:'post',
			url:contextpath+'homepage/changesale.action?type='+type,
			async:false,
			dataType:'json',
			success:function(json){
				$.each(json.type,function(i,x){
					if(i==0){
						$("#sale_book1").html(x.bookname);
						$("#right_book1").attr('src',x.image_url);
					}else if(i==1){
						$("#sale_book2").html(x.bookname);
						$("#right_book2").attr('src',x.image_url);
					}else if(i==2){
						$("#sale_book3").html(x.bookname);
						$("#right_book3").attr('src',x.image_url);
					}else if(i==3){
						$("#sale_book4").html(x.bookname);
						$("#right_book4").attr('src',x.image_url);
					}else if(i==4){
						$("#sale_book5").html(x.bookname);
						$("#right_book5").attr('src',x.image_url);
					}else if(i==5){
						$("#sale_book6").html(x.bookname);
						$("#right_book6").attr('src',x.image_url);
					}
				});
			}
		});
	$(".right_div1").removeClass("active");
	$("#"+state).addClass("active");
}