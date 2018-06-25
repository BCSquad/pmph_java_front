$(function () {
    $('#content_book').tipso({validator: "isNonEmpty", message: "图书评论不能为空"});

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
        "<div class='remark' >视频上传中，请勿关闭页面</div>" +
        "<div class='layui-progress layui-progress-big' lay-filter='demo' lay-showPercent='true'>" +
        "   <div class='layui-progress-bar' lay-percent='0%'></div> </div> " +
        "<div class='relate-img'>" +
        "  <div id='imgPreview'><img id='cover_image' alt='' value='' /></div>" +
        "   <div class='shade'>请上传视频封面</div> " +
        "   <div class='add-icon' id='add-icon'>+</div> " +
        "</div>" +
        " <div class='lable-input title'><div class='label'>标 题</div><input class='input' type='text'></div>" +
        /*   " <div class='lable-input type'><div class='label'>分 类</div><select id='select'><option>教育</option><option>培训</option></select></div>" +*/
        "<div class='button-area'><button class='submit disable'>保存</button><button class='cancel' onclick='hidevideo()'>取消</button></div>" +
        "</div> </div>");

    var element;
    var returnInfo;
    var $uploadvideo = $("#upload-video").fileupload({
        url: 'http://' + remoteUrl + '/v/upload',
        dataType: 'json',
        autoUpload: true,
        /*    formData: function () {
         return [
         {name: 'userId', value: $("#userid").val()},
         {name: 'userType', value: 2},
         {name: 'bookId', value: $("#bookid").val()},
         {name: 'sn', value: $("#booksn").val()},
         {name: 'title', value: $(".pop-body").find("input[type='text']").val()},
         {name: 'cover', value: $("#add-icon").parent().children("img").attr("value")}
         ];
         },*/
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
                if (!f) {
                    message.error("您上传的文件格式不支持！");
                    return;
                }
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
                    /*  $("#add-icon").uploadFile({
                     accept: 'image/png,image/gif,image/jpeg',
                     done: function (filename, fileid) {
                     // console.log("上传完成：name " + filename + " fileid " + fileid);
                     $("#add-icon").parent().children("img").attr('src', contextpath + 'image/' + fileid + ".action").attr("value", fileid);
                     valid()
                     },
                     progressall: function (loaded, total, bitrate) {
                     // console.log("正在上传。。。" + loaded / total);
                     }
                     });*/

                    /* $('.pop-body select').selectlist({
                     zIndex: 10,
                     width: 437,
                     height: 30,
                     optionHeight: 20
                     });*/

                });


                data.submit();
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
                    //message.success("上传成功！");
                    // hidevideo()
                    $(".pop-body").find(".remark").text("正在压缩视频请稍后")
                    var intervalId = setInterval(function () {
                        $.ajax({
                            type: 'get',
                            url: "http://" + remoteUrl + "/v/query?key=" + data.result.data,
                            async: false,
                            dataType: 'json',
                            beforeSend: function (xhr, global) {
                            },
                            success: function (json) {
                                if (json.code == '1' && (json.data.error == true || json.data.message == '转码成功')) {
                                    clearInterval(intervalId);
                                    if (json.data.error == true) {
                                        message.error("转码失败,请联系管理员");
                                    } else {

                                        $(".pop-body").find(".remark").css("display", "none");

                                        element.progress('demo', 100 + '%')
                                        returnInfo = json.data;
                                        var file_input;
                                        var add_input = function ($ele) {
                                            $ele.parent().find("#" + $ele.attr("id") + "_upload").remove();


                                            file_input = $("<input class='hidden-upload' onchange='PreviewImage(this)' name='file' type='file' id='" + $ele.attr("id") + "_upload'/>");


                                            file_input.css("width", $ele.outerWidth());
                                            file_input.css("height", $ele.outerHeight());
                                            file_input.css("padding-bottom", $ele.outerHeight());
                                            file_input.css("padding-left", $ele.outerWidth());
                                            file_input.css("top", $ele[0].offsetTop);
                                            file_input.css("left", $ele[0].offsetLeft);
                                            file_input.css("cursor", "pointer");
                                            file_input.attr("accept", 'image/png,image/gif,image/jpeg');
                                            file_input.appendTo($ele.parent());
                                        }

                                        add_input($("#add-icon"));


                                        $("#add-icon_upload").fileupload({
                                            url: 'http://' + remoteUrl + '/pmpheep/bookVideo/addVideo',
                                            dataType: 'json',
                                            type: 'post',
                                            autoUpload: true,
                                            paramName: 'cover',
                                            formData: function () {
                                                return [
                                                    {name: 'userId', value: $("#userid").val()},
                                                    /* {name: 'userType', value: 2},*/
                                                    {name: 'bookId', value: $("#bookid").val()},
                                                    {
                                                        name: 'title',
                                                        value: $(".pop-body").find("input[type='text']").val()
                                                    },
                                                    {name: 'origPath', value: json.data.origPath},
                                                    {name: 'origFileName', value: json.data.origFileName},
                                                    {name: 'origFileSize', value: json.data.origFileSize},
                                                    {name: 'path', value: json.data.path},
                                                    {name: 'fileName', value: json.data.fileName},
                                                    {name: 'fileSize', value: json.data.fileSize}
                                                ];
                                            },
                                            replaceFileInput: false,
                                            singleFileUploads: true,
                                            limitMultiFileUploads: 1,
                                            limitMultiFileUploadSize: 1048576000,
                                            add: function (e, data) {

                                                var valid = function () {
                                                    if ($(".pop-body").find("input[type='text']").val()
                                                        && $(".pop-body").find("input[type='text']").val().length <= 20) {

                                                        $(".pop-body").find("button.submit").removeClass("disable");
                                                        return true;
                                                    } else {
                                                        $(".pop-body").find("button.submit").addClass("disable");
                                                        if ($(".pop-body").find("input[type='text']").val()
                                                            && $(".pop-body").find("input[type='text']").val().length > 20) {
                                                            message.warning("标题长度不能超过20个字符");
                                                        }
                                                        return false;
                                                    }
                                                }

                                                $(".pop-body").find("input[type='text']").change(function () {
                                                    valid();
                                                });
                                                $(".pop-body").find("button.submit").unbind("click")
                                                $(".pop-body").find("button.submit").click(function () {
                                                    if (valid()) {

                                                        var o = data.submit()
                                                        o.fail = function () {
                                                            message.error("上传失败,请联系管理员！");
                                                            hidevideo()
                                                        }

                                                    }
                                                })
                                            },
                                            done: function (e, data) {
                                                if (data.result.code == '1') {
                                                    message.success("保存成功");
                                                } else {
                                                    message.error("保存失败");
                                                }
                                                hidevideo()
                                            }
                                        })

                                    }
                                }

                            }
                        })
                    }, 3000);

                } else {
                    message.error("上传失败！");
                    hidevideo()
                }
            } else {
                message.error("上传失败！");
                hidevideo()
            }

        },
        progressall: function (e, data) {
            // console.log(e.delegatedEvent, data);
            if (element) {
                element.progress('demo', ( Math.round((data.loaded / (data.total + data.loaded / 10) * 1000), 1) / 10).toFixed(1) + '%')
            }
        }
    });

    //隐藏/显示配套图书
    if ($("#sup-hidden").val() == 'no') {

    } else {
        $(".right_1").hide();
    }

    //隐藏PDF阅读按钮
    if ($("#pdf-hidden").val() == 'no') {
        //$("#dpf").hide();
        //$("#dpf").attr("disabled",true);
        $('#pdf_a').removeAttr('href');//去掉a标签中的href属性
        $('#pdf_a').removeAttr('onclick');//去掉a标签中的onclick事件
        $('#pdf_a .xsp2').css({"color":"#999"});
        $("#dpf").css({"background-color":"#f2f2f2"});

    }

    morecontent();


    relatiedBookPageSwitch('1');
    relatiedBookPageSwitch('2');
    relatiedBookPageSwitch('3');
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
                $("#moreothers").html('');
            } else {
                json = json.slice(0, 2);
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
                $("#longothers").html('');
            } else {
                json = json.slice(0, 2);
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
    if ($.fireValidator()) {
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

/**
 * 后台配置相关图书的"换一批按钮触发"
 * relation_type ： 1.教材关联图书 2.相关推荐 3.人卫推荐
 * nextPage: 换到第几页
 */
function relatiedBookPageSwitch(relation_type){
	var id =$('#bookid').val();
	var tagetElId= "";
	var nextPage = 0;
	var totalPage = 100;
	if (relation_type == '1' ) {
		tagetElId = "about";
		if($("#"+tagetElId).children(".relation_totalPage").length==0
				||$("#"+tagetElId).children(".relation_totalPage").val()==0){
			fresh("6");
			return;
		}
	}else if(relation_type == '2' ){
		tagetElId = "change";
		if($("#"+tagetElId).children(".relation_totalPage").length==0
				||$("#"+tagetElId).children(".relation_totalPage").val()==0){
			fresh("9");
			return;
		}
	}else if(relation_type == '3'){
		tagetElId = "comment";
		if($("#"+tagetElId).children(".relation_totalPage").length==0
				||$("#"+tagetElId).children(".relation_totalPage").val()==0){
			change();
			return;
		}
	}
	nextPage = $("#"+tagetElId).children(".relation_page").val();
	totalPage = $("#"+tagetElId).children(".relation_totalPage").val();
	var str="";
	$.ajax({
        type: 'post',
        url: contextpath + 'readdetail/relatiedBookPageSwitch.action?type=' + relation_type + '&page=' + nextPage+'&id='+id+'&t='+new Date(),
        async: false,
        dataType: 'json',
        success: function (json) {
        	str += '<input class="relation_page" value="'+json.nextPage+'" type="hidden"></input>';
        	str += '<input class="relation_totalPage" value="'+json.totalPage+'" type="hidden"></input>';
        	if (relation_type == '1'||relation_type == '2') {
        		$.each(json.list, function (i, x) {
                    str += '<div class="right_9" onclick="todetail(' + x.id + ')"> <div class="right_10"><img class="right_12" src=' +
                        x.image_url +
                        '></div><div class="right_11">' +
                        x.bookname +
                        '</div></div>';
                });
			}else if(relation_type == '3'){
				$.each(json.list, function (i, x) {
	                str += '<div class="right_20"><div class="right_21" onclick="todetail(' +
	                    x.id
	                    + ')">' +
	                    x.bookname +
	                    '</div><div class="right_22">（' +
	                    x.author +
	                    '）</div></div>';
	            });
			}

        	if (json.totalPage==0) {
        		$(".relatiedBookPageSwitchWrapper."+relation_type).show();
        		if (relation_type == '1' ) {
        				fresh("6");
        				return;
        		}else if(relation_type == '2' ){
        				fresh("9");
        				return;
        		}else if(relation_type == '3'){
        				change();
        				return;
        		}
			}


            $("#"+tagetElId).html(str);
            if (json.totalPage==1) {
				$(".relatiedBookPageSwitchWrapper."+relation_type).hide();
			}else{
				$(".relatiedBookPageSwitchWrapper."+relation_type).show();

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
function showup(tag) {
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/tologin.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json == "OK") {
            	if (tag==1) { //纠错
            		$("#bookmistake").show();
				}else if(tag==2){ //反馈
					$("#bookfeedback").show();
				}
                
            }
        }
    });
}

//点击纠错弹窗隐藏
function hideup() {
    $(".bookmistake").hide();
    
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
                        if (json.returnCode == "OK") {
                            window.message.success("数据已提交！");
                            $("#bookmistake").hide();
                            $("#page").val(null);
                            $("#line").val(null);
                            $("#content").val(null);
                            $("#upname").html('未选择任何文件!');
                            $("#upload_status").val(null);
                            
                            var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep";
                            var bookId = $("#book_id").val();
                            var userId = $("#userid").val();
                            var correctId = json.correctId;
                            $.ajax({
                                type: 'get',
                                url: exportWordBaseUrl + '/frontWxMsg/bookError/'+bookId+"/"+userId+"/"+correctId,
                                dataType: 'jsonp',
                                success:function(wxResult){
                                	if(wxResult){
                                		window.message.success("微信消息发送成功");
                                		
                                	}
                                },
                                error:function(XMLHttpRequest, textStatus){
                                	
                                }
                                });
                            
                            
                            
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

function bookfeedback(){
	
    if ($("#bookfeedbackform").validate('submitValidate')) {
            content = $("#bookfeedback_content").val();
            if (!Empty(content)) {//非空判断
            	$(".btn").attr("disabled",true);
                var json = {
                    book_id: $("#book_id").val(),
                    content: content
                };
                $.ajax({
                    type: 'post',
                    url: contextpath + 'readdetail/bookfeedback.action',
                    data: json,
                    async: false,
                    dataType: 'json',
                    success: function (json) {
                        if (json == "OK") {
                            window.message.success("数据已提交！");
                            $("#bookfeedback").hide();
                            $("#bookfeedback_content").val(null);
                        } else {
                            window.message.info("反馈内容不能为空！");
                        }
                    },
                    complete:function(){
                    	$(".btn").attr("disabled",false);
                    }
                });
            } else {
                window.message.info("反馈内容不能为空！");
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
    var maxStrlength;
    var va = obj.value;
    var vat = "";
    for (var i = 1; i <= va.length; i++) {
        vat = va.substring(0, i);
        //把双字节的替换成两个单字节的然后再获得长度，与限制比较
        if (vat.replace(/[^\x00-\xff]/g, "a").length <= ml) {
            maxStrlength = i;
        } else {

            break;
        }
    }
    obj.maxlength = maxStrlength;
    //把双字节的替换成两个单字节的然后再获得长度，与限制比较
    if (va.replace(/[^\x00-\xff]/g, "a").length > ml) {
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

function PreviewImage(imgFile) {
    var filextension = imgFile.value.substring(imgFile.value.lastIndexOf("."), imgFile.value.length);
    filextension = filextension.toLowerCase();
    if ((filextension != '.jpg') && (filextension != '.gif') && (filextension != '.jpeg') && (filextension != '.png') && (filextension != '.bmp')) {
        message.error("对不起，系统仅支持标准格式的照片，请您调整格式后重新上传，谢谢 !");
        imgFile.focus();
    }
    else {
        var path;
        if (document.all)//IE
        {
            imgFile.select();
            path = document.selection.createRange().text;
            document.getElementById("imgPreview").innerHTML = "";
            document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//使用滤镜效果
        }
        else//FF
        {
            path = window.URL.createObjectURL(imgFile.files[0]);// FF 7.0以上
            //path = imgFile.files[0].getAsDataURL();// FF 3.0
            document.getElementById("imgPreview").innerHTML = "<img id='cover_image' src='" + path + "'/>";
            //document.getElementById("cover_image").src = path;
        }
    }
}

function validLogin() {
    //校验登录情况
    $.ajax({
        type: 'post',
        url: contextpath + 'readdetail/tologin.action',
        async: false,
        dataType: 'json',
        success: function (json) {
            if (json == "OK") {

            }
        }
    });
}