$(function () {
	$("#start").val(2);//火狐浏览器点击刷新按钮 不刷新el表达式 此处用js初始化
	$("#longstart").val(2);
	
    $('form').validate({
        onFocus: function () {
            this.removeClass("input-error");
            return false;
        },
        onBlur: function () {
            var $parent = this.parent();
            var _status = parseInt(this.attr('data-status'));
            if (!_status) {
                this.addClass("input-error");
            }
            return false;
        },
    });
    //文件上传
    $("#uploadFile").uploadFile({
        start: function () {
            // console.log("开始上传。。。");
            $("#upload_status").val('start');
            $("#uploadFile").addClass("upload-loading").html('<div class="shade" style="border-radius: 4px;"></div>' +
                ' <img src="' + contextpath + 'statics/image/20140524124238403.gif" style="height: 19.2px; width: 19.2px; margin-top: 2.4px; margin-left: 2.4px;">' +
                ' <div class="loading-text" style="height: 24px; line-height: 24px;">上传中</div>');

        },
        done: function (filename, fileid) {
            //console.log("上传完成：name " + filename + " fileid " + fileid);
            window.message.success("上传成功！");
            $("#uploadFile").removeClass("upload-loading").text("选择文件");
            $("#upload_status").val('');
            $("#upname").html(filename);
            $("#attachment").val(fileid);
            $("#attachment_name").val(filename);
        },
        progressall: function (loaded, total, bitrate) {

            /*console.log("正在上传。。。" + loaded / total);*/
        }
    });

    var $pop = $("<div id='video-upload'><div class='pop-body'>" +
        "<div class='title'>当前上传：" + $("#bookname").val() + "</div>" +
        "<div class='remark' style='display: none'>视频上传中，请勿关闭页面</div>" +
        "<div class='layui-progress layui-progress-big' lay-filter='demo' lay-showPercent='true'>" +
        "   <div class='layui-progress-bar' lay-percent='0%'></div> </div> " +
        "<div class='relate-img'>" +
        "   <img  alt='' value='' />" +
        "   <div class='shade'>请上传视频封面</div> " +
        "   <div class='add-icon' id='add-icon'>+</div> " +
        "</div>" +
        " <div class='lable-input title'><div class='label'>标 题</div><input class='input' type='text'></div>" +
        /*   " <div class='lable-input type'><div class='label'>分 类</div><select id='select'><option>教育</option><option>培训</option></select></div>" +*/
        "<div class='button-area'><button class='submit disable'>开始上传</button><button class='cancel' onclick='hidevideo()'>取消</button></div>" +
        "</div> </div>");

    var element;
    var $uploadvideo = $("#upload-video").fileupload({
        url: 'http://120.76.221.250/v/play/fileUp',
        dataType: 'json',
        autoUpload: true,
        formData: function () {
            return [
                {name: 'userId', value: $("#userid").val()},
                {name: 'userType', value: 2},
                {name: 'bookId', value: $("#bookid").val()},
                {name: 'sn', value: $("#booksn").val()},
                {name: 'title', value: $(".pop-body").find("input[type='text']").val()},
                {name: 'cover', value: $("#add-icon").parent().children("img").attr("value")}
            ];
        },
        replaceFileInput: false,
        singleFileUploads: true,
        limitMultiFileUploads: 1,
        limitMultiFileUploadSize: 1048576000,
        add: function (e, data) {
            if (data.files[0].name) {
                var videolist = ['aiv', 'mpg', 'wmv', '3gp', 'mov', 'mp4', 'asf', 'asx', 'flv'];
                var arr = data.files[0].name.split(".");
                var file_suffix = arr[arr.length - 1];

                var f = false;
                for (var i = 0; i < videolist.length; i++) {
                    if (videolist[i] == file_suffix) {
                        f = true;
                        break;
                    }
                }
                /*if (!f) {
                 message.warning("您上传的文件格式不支持！");

                 return;
                 }*/
                layer.open({
                    type: 1,
                    title: false,
                    area: ['553px', '412px'],
                    closeBtn: 0,
                    skin: 'pop-upload',
                    content: $pop.html()
                });


                layui.use('element', function () {

                    var valid = function () {
                        if ($("#add-icon").parent().children("img").attr("value") && $(".pop-body").find("input[type='text']").val()) {
                            $(".pop-body").find("button.submit").removeClass("disable");
                            return true;
                        } else {
                            $(".pop-body").find("button.submit").addClass("disable");
                            return false;
                        }
                    }

                    $(".pop-body").find("input[type='text']").change(function () {
                        valid();
                    });

                    $(".pop-body").find("button.submit").click(function () {
                        if (valid()) {

                            var o = data.submit()
                            o.fail=function () {
                                message.error("上传失败,请联系管理员！");
                            }

                            $(".pop-body").find(".remark").css("display", "block");
                            $(".pop-body").find("button.submit").text("正在上传");
                        }
                    })


                    element = layui.element;
                    $("#add-icon").uploadFile({
                        accept: 'image/png,image/gif,image/jpeg',
                        done: function (filename, fileid) {
                            // console.log("上传完成：name " + filename + " fileid " + fileid);
                            $("#add-icon").parent().children("img").attr('src', contextpath + 'image/' + fileid + ".action").attr("value", fileid);
                            valid()
                        },
                        progressall: function (loaded, total, bitrate) {
                            // console.log("正在上传。。。" + loaded / total);
                        }
                    });

                    /* $('.pop-body select').selectlist({
                     zIndex: 10,
                     width: 437,
                     height: 30,
                     optionHeight: 20
                     });*/

                });


                // data.submit();
            }
        },
        start: function (e) {

        },
        /*always: function (e, data) {
            message.error("上传失败,请联系管理员！");
        },*/
        done: function (e, data, b, c, d) {
            if (data.result) {
                if (data.result.code == '1') {
                    // $(".pop-body .submit").removeClass("disable");
                    message.success("上传成功！");
                    hidevideo()
                } else {
                    message.error("上传失败！");
                }
            } else {
                message.success("上传成功！");
                hidevideo()
            }

        },
        progressall: function (e, data) {
            // console.log(e.delegatedEvent, data);
            element.progress('demo', ( Math.round((data.loaded / data.total * 1000), 1) / 10).toFixed(1) + '%')
        }
    });

    //隐藏/显示配套图书
    if($("#sup-hidden").val()=='no'){

    }else {
        $(".right_1").hide();
    }

    //隐藏PDF阅读按钮
    if($("#pdf-hidden").val()=='no'){
        $("#dpf").hide();
    }

    morecontent();



});
//展开、收起
function morecontent() {
    var a = document.getElementsByName("item_content");
    for (var i = 0; i < a.length; i++) {
        if ((a[i].offsetHeight) > 47) {
            $("#" + i + "more").show();
            $("#" + i + "con").css({"height": "45px"});
        }
    }
}

//点击取消上传视频弹窗
function hidevideo() {
    layer.closeAll('page');
}


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
            if (json.length < 3) {
                $("#moreothers").html('加载完毕');
            }else{
            	json.remove(2);
            }
            var str = '';
            $.each(json, function (i, n) {
                $("#start").val(n.start);
                str += '<div class="item"><div class="item_title">'
                    + '<div style="float: left;"><img src="';
                if (n.avatar == '' || n.avatar == 'DEFAULT' || n.avatar == null) {
                    str += contextpath + 'statics/image/default_image.png';
                } else {
                    str += contextpath + 'image/' + n.avatar + '.action';
                }
                str += '" class="picturesize"/></div><div style="float: left;margin-left: 10px;margin-top: 5px;">' +
                    n.nickname
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

//长评加载更多
function longcom() {
    $(".morecom").show();
    $(".moreothers").hide();
    var json = {
        pageNumber: $("#longstart").val(),
        id: $("#book_id").val(),
    };
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/longcom.action?',
        async: false,
        dataType: 'json',
        data: json,
        success: function (json) {
            $(".morecom").hide();
            $(".moreothers").show();
            
            if (json.length < 3) {
                $("#longothers").html('加载完毕');
            }else{
            	json.remove(2);
            }
            var str = '';
            $.each(json, function (i, n) {
                //获取从第几条开始分页的数据
                $("#longstart").val(n.longstart);
                str += '<div class="item"><div class="item_title">'
                    + '<div style="float: left;"><img src="';
                if (n.avatar == '' || n.avatar == 'DEFAULT' || n.avatar == null) {
                    str += contextpath + 'statics/image/default_image.png';
                } else {
                    str += n.avatar;
                }
                str += '" class="picturesize"/></div><div style="float: left;margin-left: 10px;margin-top: 5px;">' +
                    n.nickname
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
                    + '</div></div></div>'
                    + '<div class="longcom_title">"' + n.title + '"</div>'
                    + '<div id="' + $("#long-hidden").val() + 'con" class="item_content" name="item_content">'
                    + n.content
                    + '</div><span id="' + $("#long-hidden").val() + 'more" style="cursor: pointer;color: #666666;display: none;"'
                    + 'onclick="more(\'' + $("#long-hidden").val() + 'con\',\'' + $("#long-hidden").val() + 'more\')" >...(展开)</span>'
                    + '<hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;"></div>';
                //给隐藏于赋值，用于展开以及收起功能过
                var x = (parseInt($("#long-hidden").val()) + 1);
                $("#long-hidden").val(x);
            });
            $("#longcompage").append(str);
            //将过长的长评折叠，显示展开按钮
            morecontent();
        },
    });
}

//新增评论
function insert() {
    if ($("#content_book").val() == '' || $("#content_book").val() == null) {
        window.message.info("请输入评论！");
        return;
    }
    var json = {
        content: $("#content_book").val(),
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
                $("#content_book").val(null);
                window.message.success("评论成功");
            } else if (json.returncode == "error") {
                var words = json.value;
                var content = document.getElementById("content_book");
                var contentValue = $("#content_book").val();
                for (var i = 0; i < words.length; i++) {
                    if (json.content.indexOf(words[i]) > -1) {
                        content.style.border = '3px solid red';
                        window.message.error("图书评论中含有敏感词,请检查修改后再保存或提交");
                        return;
                    }
                }
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
function writeablut() {
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/tologin.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            location.href = contextpath + 'readdetail/todetail.action?id=' + $("#book_id").val() + "&state=write";
        }
    });
}

//点击显示纠错弹窗
function showup() {
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/tologin.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json == "OK") {
                $("#bookmistake").show();
            }
        }
    });
}

//点击纠错弹窗隐藏
function hideup() {
    $("#bookmistake").hide();
}

//图书纠错
function correction() {
    if ($("#bookmistakeform").validate('submitValidate')) {
        if ($("#upload_status").val() == '') {
            page = $("#page").val();
            line = $("#line").val();
            content = $("#content").val();
            if (!Empty(page) && !Empty(line) && !Empty(content)) {//非空判断
                var json = {
                    book_id: $("#book_id").val(),
                    page: page,
                    line: line,
                    content: content,
                    attachment: $("#attachment").val(),
                    attachment_name: $("#attachment_name").val(),
                };
                $.ajax({
                    type: 'post',
                    url: contextpath + 'readdetail/correction.action',
                    data: json,
                    async: false,
                    dataType: 'json',
                    success: function (json) {
                        if (json == "OK") {
                            window.message.success("数据已提交！");
                            $("#bookmistake").hide();
                            $("#page").val(null);
                            $("#line").val(null);
                            $("#content").val(null);
                            $("#upname").html('未选择任何文件!');
                            $("#upload_status").val(null);
                        } else {
                            window.message.info("错误，请填写完所有内容！");
                        }
                    }
                });
            } else {
                window.message.info("错误，请填写完所有内容！");
            }
        } else {
            window.message.info("错误，请等待文件上传完毕再提交！");
        }
    }

}

//展开功能
function more(con, more) {
    if ($("#" + more).html() == '...(展开)') {
        $("#" + con).css({"height": ""});
        $("#" + more).html("(收起)");
    } else {
        $("#" + con).css({"height": "45px"});
        $("#" + more).html("...(展开)");
    }

}


//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj, ml) {

    var va = obj.value;
    var vat = "";
    for (var i = 1; i <= va.length; i++) {
        vat = va.substring(0, i);
        //把双字节的替换成两个单字节的然后再获得长度，与限制比较
        if (vat.replace(/[^\x00-\xff]/g, "aa").length <= ml) {
            maxStrlength = i;
        } else {

            break;
        }
    }
    obj.maxlength = maxStrlength;
    //把双字节的替换成两个单字节的然后再获得长度，与限制比较
    if (va.replace(/[^\x00-\xff]/g, "aa").length > ml) {
        obj.value = va.substring(0, maxStrlength);
        window.message.warning("不可超过最大长度");
    }
}

//评论检查出敏感词时，用户修改文本域获取焦点，则把红边去掉
$(function () {
    $("#content_book").focus(function () {
        $("#content_book").css("border", "none");
    });

});

