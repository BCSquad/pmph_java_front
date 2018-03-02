/*window.onload = function(){
 if($("#UEContent").val()!=null&&$("#UEContent").val()!='undefined'&&$("#UEContent").val()!=undefined&&$("#UEContent").val().length!=0){
 var ue = UE.getEditor('mText');
 ue.setContent($("#UEContent").val());
 }
 }
 */



//写文章表单验证
function writeArticleValidate() {
//	debugger;
    var UEContent = UE.getEditor('mText').getContent();
    $("#UEContent").val(UEContent);

    if ($("#TitleValue").val().length == 0) {
        window.message.warning("请输入标题");
        //$('input[type="submit"]').prop('disabled', true);
        return false;
    }
    if (UEContent.length == 0) {
        window.message.warning("请输入内容");
        //$('input[type="submit"]').prop('disabled', true);
        return false;
    }
    if ($("#image").val().length == 0) {
    	window.message.warning("请上传封面");
        //$('input[type="submit"]').prop('disabled', true);
        return false;
	}

    return true;
}


/*var options = {   
 type: 'POST',
 url: '提交路径',
 success:function(data){

 },
 dataType: 'json',
 error : function(xhr, status, err) {
 alert("操作失败");
 }
 };  */
//确定 点击的按钮类型
function btntype(btn_this) {
    $("#btn_type").val(btn_this);
    if ($("#submitTypeCode").val() == '0') { //submitTypeCode 状态码为0表示新增 1表示修改
        if (writeArticleValidate()) {
            $("#topub").attr("disabled",true);
            $.ajax({
                url: contextpath + "writerArticle/writeArticle.action",
                type: "post",
                data: $("#form1").serialize(),
                success: function (json) {
                    var data = json.flag;
                    $("#atrticle_id").val(json.atrticle_id);
                    if (data == '2' || data == '3') {
                        $("#TitleValue").val(json.titleValue);
                        UE.getEditor('mText').setContent(json.UEContent);
                        window.message.error(json.isValidate);
                    } else if (data == '4') {
                        var words = json.value;
                        var title = document.getElementById("TitleValue");
                        var TitleValue = $("#TitleValue").val();
                        var content = $("#UEContent").val().replace('<span style="background : yellow">','').replace('</span>','');
                        for (var i = 0; i < words.length; i++) {
                            var reg = new RegExp(words[i], 'g');
                            if (TitleValue.indexOf(words[i]) > -1) {
                                title.style.border = '3px solid red';
                            }
                            if (content.indexOf(words[i]) > -1) {
                                content = content.replace(reg, '<span style="background : yellow">' + words[i] + '</span>');
                            }
                        }
                        UE.getEditor('mText').setContent(content);
                        window.message.error("标题或内容中含有敏感词,请修改后再保存或提交");
                    } else {
                        if (data != '1') {
                            if (btn_this == '1') {
                                $("#submitTypeCode").val("1");
                                $("#msg_id").val(data);
                            } else if (btn_this == '0') {
                                document.getElementById("form1").reset();
                                $("#TitleValue").val("");
                                UE.getEditor('mText').setContent("");
                                $("#submitTypeCode").val("0");
                            }
                            window.message.success("成功");
                            setTimeout(function () {
                                window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=sbwz";
                            }, 800);
                        } else {
                            $("#submitTypeCode").val("0");
                            window.message.error("失败");
                        }
                    }


                }
            });
        }
    } else if ($("#submitTypeCode").val() == '1') {
        $("#topub").attr("disabled",true);
        /*if(btn_this=='0'){
         //window.message.info("您已保存过了，请不要重复保存");
         return false;
         }else if(btn_this=='1'){*/
        if (writeArticleValidate()) {
            $.ajax({
                url: contextpath + "writerArticle/updateIsStaging.action",
                type: "post",
                data: $("#form1").serialize(),
                success: function (json) {
                    var data = json.flag;
                    if (data == '2' || data == '3') {
                        $("#TitleValue").val(json.titleValue);
                        UE.getEditor('mText').setContent(json.UEContent);
                        window.message.error(json.isValidate);
                    } else if (data == '4') {
                        var words = json.value;
                        var title = document.getElementById("TitleValue");
                        var TitleValue = $("#TitleValue").val();
                        var content = $("#UEContent").val().replace('<span style="background : yellow">','').replace('</span>','');
                        for (var i = 0; i < words.length; i++) {
                            var reg = new RegExp(words[i], 'g');
                            if (TitleValue.indexOf(words[i]) > -1) {
                                title.style.border = '3px solid red';
                            }
                            if (json.UEContent.indexOf(words[i]) > -1) {
                                content = content.replace(reg, '<span style="background : yellow">' + words[i] + '</span>');
                            }
                        }
                        UE.getEditor('mText').setContent(content);
                        window.message.error("标题或内容中含有敏感词,请修改后再保存或提交");
                    } else {
                        if (data != '1') {
                            if (btn_this == '1') {
                                $("#submitTypeCode").val("1");
                                $("#msg_id").val(data);
                            } else if (btn_this == '0') {
                                document.getElementById("form1").reset();
                                $("#TitleValue").val("");
                                UE.getEditor('mText').setContent("");
                                $("#msg_id").val("");
                                $("#submitTypeCode").val("0");
                            }
                            /*	document.getElementById("form1").reset();
                             UE.getEditor('mText').setContent("");
                             $("#submitTypeCode").val("0");*/
                            window.message.success("成功");
                            setTimeout(function () {
                                window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=sbwz";
                            }, 800);
                        } else {
                            $("#submitTypeCode").val("1");
                            window.message.error("失败");
                        }
                    }

                }
            });
        }
//		}
    }


    //$("#form1").submit();
}

//评论检查出敏感词时，用户修改文本域获取焦点，则把红边去掉
$(function () {
    $("#TitleValue").focus(function () {
        $("#TitleValue").css("border", "none");
    });


    $(".upload-image button").uploadFile({
        accept: "image/*",
        start: function () {
        },
        done: function (filename, fileid) {
            $("#image").val(fileid);
            $(".upload-image .fileinfo .filename").text(filename);
            $(".upload-image .fileinfo .preview").show();
        }
    });
    
    if ($("#image").val().length>0) {
    	$(".upload-image .fileinfo .preview").show();
	}
    
    $(".upload-image .fileinfo .preview").click(function () {
        var image = new Image();
        image.src = contextpath + "image/" + $("#image").val() + ".action";
        $(image).one('load', function () {
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                area: '800px',
                offset: 'auto',
                skin: 'layui-layer-nobg', //没有背景色
                shadeClose: true,
                content: "<img style='display: block;margin: auto;max-width: 800px' src='" + image.src + "'/>"
            });
        });

    })


});
