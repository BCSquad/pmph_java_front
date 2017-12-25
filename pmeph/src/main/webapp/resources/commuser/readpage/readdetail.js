$(function () {
    //文件上传
    $("#uploadFile").uploadFile({
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
            console.log("上传完成：name " + filename + " fileid " + fileid);
            $("#upname").html(filename);
        },
        progressall: function (loaded, total, bitrate) {
            console.log("正在上传。。。" + loaded / total);
        }
    });

    var $pop = $("<div><div class='pop-body'>" +
        "<div class='title'>当前上传：幼儿护理学</div>" +
        "<div class='remark'>视频上传中，请勿关闭页面</div>" +
        "<div class='layui-progress layui-progress-big' lay-filter='demo' lay-showPercent='true'>" +
        "   <div class='layui-progress-bar' lay-percent='0%'></div> </div> " +
        "<div class='relate-img'>" +
        "   <img  alt=''/>" +
        "   <div class='shade'></div> " +
        "   <div class='add-icon' id='add-icon'>+</div> " +
        "</div>" +
        " <div class='lable-input title'><div class='label'>标 题</div><input class='input' type='text'></div>" +
        " <div class='lable-input type'><div class='label'>分 类</div><select id='select'><option>教育</option><option>培训</option></select></div>" +
        "<div class='button-area'><button class='submit disable'>提交</button><button class='cancel'>取消</button></div>" +
        "</div> </div>");

    var element;
    $("#upload-video").fileupload({
        url: contextpath + 'file/upload.action',
        dataType: 'json',
        autoUpload: true,
        replaceFileInput: false,
        singleFileUploads: true,
        limitMultiFileUploads: 1,
        limitMultiFileUploadSize: 1048576000,
        add: function (e, data) {
            if (data.files[0].name) {

                layer.open({
                    type: 1,
                    title: false,
                    area: ['553px', '412px'],
                    closeBtn: 0,
                    skin: 'pop-upload',
                    content: $pop.html()
                });

                layui.use('element', function () {
                    element = layui.element;
                    $("#add-icon").uploadFile({
                        accept: 'image/png,image/gif,image/jpeg',
                        done: function (filename, fileid) {
                            // console.log("上传完成：name " + filename + " fileid " + fileid);
                            $("#add-icon").parent().children("img").attr('src', contextpath + 'image/' + fileid + ".action")

                        },
                        progressall: function (loaded, total, bitrate) {
                            // console.log("正在上传。。。" + loaded / total);
                        }
                    });

                    $('.pop-body select').selectlist({
                        zIndex: 10,
                        width: 437,
                        height: 30,
                        optionHeight: 20
                    });

                });


                data.submit();
            }
        },
        start: function (e) {

        },
        done: function (e, data) {

            if (data.result.code == '1') {
                $(".pop-body .submit").removeClass("disable");
            } else {
                message.error("上传失败！");
            }
        },
        progressall: function (e, data) {
            console.log(e.delegatedEvent, data);
            element.progress('demo', ( Math.round((data.loaded / data.total * 1000), 1) / 10).toFixed(1) + '%')
        }
    });


});


//分页的具体实现
function changepage() {
    $(".morecom").show();
    $(".moreothers").hide();
    var json = {
        pageNumber: $("#start").val(),
        id: $("#book_id").val(),
    };
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/changepage.action?',
        async: false,
        dataType: 'json',
        data: json,
        success: function (json) {
            $(".morecom").hide();
            $(".moreothers").show();
            if (json.length < 2) {
                $(".moreothers").html('没有更多了~~~');
            }
            var str = '';
            $.each(json, function (i, n) {
                $("#start").val(n.start);
                str += '<div class="item"><div class="item_title">'
                    + '<div style="float: left;"><img src="';
                if (n.avatar == '' || n.avatar == 'DEFAULT' || n.avatar == null) {
                    str += contextpath + 'statics/image/rwtx.png';
                } else {
                    str += n.avatar;
                }
                str += '" class="picturesize"/></div><div style="float: left;margin-left: 10px;margin-top: 5px;">' +
                    n.realname
                    + '</div><div style="float: left;margin-left: 10px;">';
                if (n.score <= 3) {
                    str += '<span class="rwtx1"></span>'
                        + '<span class="rwtx2"></span>'
                        + '<span class="rwtx2"></span>'
                        + '<span class="rwtx2"></span>'
                        + '<span class="rwtx2"></span>'
                } else if (n.score <= 5) {
                    str += '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx2"></span>'
                        + '<span class="rwtx2"></span>'
                        + '<span class="rwtx2"></span>'
                } else if (n.score <= 7) {
                    str += '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx2"></span>'
                        + '<span class="rwtx2"></span>'
                } else if (n.score <= 9) {
                    str += '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx2"></span>'
                } else if (n.score == 10) {
                    str += '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                        + '<span class="rwtx1"></span>'
                }
                str += '</div><div class="date_content"><div class="date">'
                    + n.gmt_create
                    + '</div></div></div><div class="item_content">'
                    + n.content
                    + '</div><hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;"></div>';
            });
            $("#changepage").append(str);
        },
    });

};
//新增评论
function insert() {
    if ($("#content").val() == '') {
        window.message.info("请输入评论！");
        return;
    }
    var json = {
        content: $("#content").val(),
        score: $("#last_score").html(),
        book_id: $("#book_id").val(),
    };
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/insertComment.action',
        async: false,
        dataType: 'json',
        data: json,
        success: function (json) {
            if (json.returncode == "OK") {
                $("#content").val(null);
                window.message.success("评论成功");
            }
        }
    });
}

//相关推荐换一换
function fresh(row) {
    var type = $("#type_id").val();
    var str = '';
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/fresh.action?type=' + type + '&&row=' + row,
        async: false,
        dataType: 'json',
        success: function (json) {
            $.each(json, function (i, x) {
                str += '<div class="right_9" onclick="todetail(' + x.id + ')"> <div class="right_10"><img class="right_12" src=' +
                    x.image_url +
                    '></div><div class="right_11">' +
                    x.bookname +
                    '</div></div>';
            });
            if (row == 6) {
                $("#about").html(str);
            } else {
                $("#change").html(str);
            }
        }
    });
}

//人卫推荐换一换
function change() {
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/change.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            var ste = '';
            $.each(json, function (i, x) {
                ste += '<div class="right_20"><div class="right_21" onclick="todetail(' +
                    x.id
                    + ')">' +
                    x.bookname +
                    '</div><div class="right_22">（' +
                    x.author +
                    '）</div></div>';
            });
            $("#comment").html(ste);
        }
    });
}

//人卫推荐跳转到详情书
function todetail(flag) {
    location.href = contextpath + 'readdetail/todetail.action?id=' + flag;
}
//点赞
function addlikes() {
    var book_id = $("#book_id").val();
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/addlikes.action?id=' + book_id,
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json.returncode == "yes") {
                $("#dz").attr("src", contextpath + "statics/image/dz02.png");
            } else {
                $("#dz").attr("src", contextpath + "statics/image/dz01.png");
            }
        }
    });
}
//收藏
function addmark() {
//	var favoriteId=$("input[name=edu]").val();
    var bookId = $("#book_id").val();
    var marks = $("#marks").val();
//	if(favoriteId==""){
//		window.message.info("请选择收藏夹");
//		return
//	}
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/addmark.action',
        data: {bookId: bookId},
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json.returncode == "OK") {
                $("#sc").attr("src", contextpath + "statics/image/sc101(1).png");
            } else {
                $("#sc").attr("src", contextpath + "statics/image/s102(1).png");
            }
        }
    });
}

//跳转到写文章页面
<<<<<<< .mine
function writeablut(){
	location.href=contextpath+'readdetail/todetail.action?state=write&&id='+$("#book_id").val();
=======
function writeablut() {
    location.href = contextpath + 'readdetail/towritecom.action';
>>>>>>> .r776
}

//点击显示纠错弹窗
function showup() {
    $("#bookmistake").show();
}

//点击纠错弹窗隐藏
function hideup() {
    $("#bookmistake").hide();
}

//图书纠错
function correction(){
	var json={
		book_id:$("book_id").val(),
		page:$("page").val(),
		content:$("content").val(),
		attachment_name:$("upname").val(),
	};
	$.ajax({
		type:'post',
		url:contextpath+'readdetail/correction.action',
		data:json,
		async:false,
		dataType:'json',
		success:function(json){
				if(json=="OK"){
					alert('成功');
				}else{
					alert(132);
				}
		}
	});
}

