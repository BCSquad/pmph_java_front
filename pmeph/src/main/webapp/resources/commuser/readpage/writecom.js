var ue='';
$(function(){
	ue = UE.getEditor('mText');
		UE.getEditor('mText');
		ue.ready(function() {
			// 查询登陆人是否写过长评论
			// queryLoginLong();
			ue.setContent($("#contentLongDIV").html());
			$(".sxy-btn").show();
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
					//$(".sxy-btn").attr("disabled",true);
					$("#TitleValue").attr("placeholder",null);
					$("#TitleValue").attr("disabled",true);
					$.each(json.list,function(i,n){
						ue.setContent(n.content);
						$("#TitleValue").val(n.title);
						
					});
					ue.setDisabled();
				} else {
                    $(".sxy-btn").show();
				}
			}
		});
}

function validate(title,content){
	if(title.length>50||content.length>3000){

		return false;
	}
	return true;
}
//写长评
function insertlong(){
	var title=$("#TitleValue").val();
	var book_id=$("#book_id").val();
	var score=$("#last_score").val();
	var content= ue.getContent();

	if(!Empty(book_id)&&!Empty(score)&&!Empty(content)&&!Empty(title)) {
        if (validate(title, content)){
            var json = {
                book_id: book_id,
                score: score,
                content: content,
                title: title,
            };
        //修改长评
        if ($("#submitTypeCode").val() == '1') { //submitTypeCode 状态码 1表示修改
            var id = $("#myid").val();
            $.ajax({
                type: 'post',
                url: contextpath + 'readdetail/updateCommentLong.action?comm_id=' + id,
                async: false,
                dataType: 'json',
                data: json,
                success: function (json) {
                    if (json.returncode == "OK") {
                        window.message.success("成功！");
                        toPercen();
                    } else {
                        window.message.error("失败！");
                    }
                }
            });
        } else {
            $.ajax({
                type: 'post',
                url: contextpath + 'readdetail/insertlong.action',
                async: false,
                data: json,
                dataType: 'json',
                success: function (json) {
                    var state = json.state;
                    if (state == 'OK') {
                        window.message.success("保存成功！");
//						$(".sxy-btn").attr("disabled",true);
//						$("#TitleValue").attr("disabled",true);
//						ue.setDisabled();
                        toPercen();
                    } else if (state == 'ERROR') {
                        var words = json.value;
                        var title = document.getElementById("TitleValue");
                        var TitleValue = $("#TitleValue").val();
                        var content = ue.getContent().replace('<span style="background : yellow">', '').replace('</span>', '');
                        for (var i = 0; i < words.length; i++) {
                            var reg = new RegExp(words[i], 'g');
                            if (TitleValue.indexOf(words[i]) > -1) {
                                title.style.border = '3px solid red';
                            }
                            if (json.UEContent.indexOf(words[i]) > -1) {
                                content = content.replace(reg, '<span style="background : yellow">' + words[i] + '</span>');
                            }
                        }
                        ue.setContent(content);
                        window.message.error("标题或内容中含有敏感词,请修改后再保存或提交");
                    } else {
                        window.message.success("请填写完所有内容");
                    }
                }
            });
        }


    }else{
            window.message.warning("您的内容输入过长,请检查您输入的内容!");
	}
	}else{
		window.message.info("请填写完所有内容！");
	}
}

//跳转到长评页面
function toPercen() {
	 window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=wdpl&is_long=1";
}


$(function(){
	$("#TitleValue").focus(function(){
		$("#TitleValue").css("border","none");
	});
});

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){

	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"a").length <= ml) {
			maxStrlength=i;
		}else{

			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"a").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		window.message.warning("不可超过输入最大长度"+ml+"字！");
	}
}
