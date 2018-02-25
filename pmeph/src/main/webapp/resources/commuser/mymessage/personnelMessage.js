var avatar = "";
var addAvatar = "";
var addName = "";
var addhtml = "";
$(function () {

        var pageSize = 5;
        var pageNumber = 1;


        $(window).scroll(function () {
            var top = $(window).scrollTop() + 150;
            var left = $(window).scrollLeft() + 300;
            $("#box").css({left: left + "px", top: top + "px"});
        });

        $('select').selectlist({
            zIndex: 10,
            width: 70,
            height: 20,
            optionHeight: 20,
            triangleColor: '#333333',
            onChange: function () {
                pageNumber = 1;
                $("#loadMore").show();
                $("#list").html('');
                init();
            }
        });
        //初始化数据
        init();
        // 更多
        $("#loadMore").click(function () {
            init();
        });
        //状态改变
        /*$("#select").change(function(){
         pageNumber = 1 ;
         $("#loadMore").show();
         $("#list").html('');
         init();
         });*/
        //打开对话框

        var openTallk = function () {
            var frendid = this.id;
            var type = $("#type_" + frendid).val();
            var username = $("#name_" + frendid).val();
            $("#talkList").html('');
            $(".personMessageTitle").html("你与" + username + "的私信窗口");
            $("#box").attr("class", "b show");
            $("#close").attr("class", "hiddenX show");
            $("#talk").val(frendid);
            $.ajax({
                type: 'get',
                url: contextpath + 'mymessage/getDialogue.action',
                contentType: 'application/json',
                dataType: 'json',
                data: {
                    friendId: frendid,
                    friendType: type
                },
                success: function (responsebean) {
                    $("#talkList").html('');
                    if (null != responsebean && responsebean.length >= 0) {
                        var content = "";
                        $.each(responsebean, function (i, n) {
                            var html = "";
                            if (n.isMy) {//我发送的
                                avatar = n.avatar;
                                html = "<div class='oneTalk'> " +
                                    "<div class='headAndNameRight float_right'> " +
                                    "<div class='headDiv'><img class='headPicture' src='" + contextpath + n.avatar + "'/></div> " +
                                    "<div class='talkName'><text>" + n.senderName + "</text></div> " +
                                    "</div> " +

                                    "<div class='talkDivRight float_right' > " +
                                    "<div class='sendMessage'> " +
                                    "<div class='textDiv float_right'> " +
                                    n.content +
                                    "</div> " +
                                    "</div> " +
                                    "<div class='talkTime talkTimeAlignRight'>" + formatDate(n.sendTime) + "</div> " +
                                    "</div> " +
                                    "</div> ";

                            } else {
                                html = "<div class='oneTalk'> " +
                                    "<div class='headAndNameLeft float_left'> " +
                                    "<div class='headDiv'><img class='headPicture' src='" + contextpath + n.avatar + "'/></div> " +
                                    "<div class='talkName'><text>" + n.senderName + "</text></div> " +
                                    "</div> " +
                                    "<div class='talkDiv float_left'> " +
                                    "<div class='sendMessage'> " +
                                    "<div class='textDiv float_left'> " +
                                    n.content +
                                    "</div> " +
                                    "</div> " +
                                    "<div class='talkTime talkTimeAlignLeft'>" + formatDate(n.sendTime) + "</div> " +
                                    "</div> " +
                                    "</div> ";
                            }

                            $("#talkList").append(html);
                            content += html;
                            //$("#dialogue").scrollTop($("#dialogue")[0].scrollHeight);
                            //$("#dialogue").animate({scrollTop: '700px'},500);
                            setTimeout(function () {
                                $("#dialogue").scrollTop($("#talkList").height());
                            }, 0);
                        });

                        addhtml = content;
                        //更新消息状态
                        $.ajax({
                            type: 'get',
                            url: contextpath + 'mymessage/updateMyTalk.action',
                            contentType: 'application/json',
                            dataType: 'json',
                            data: {
                                senderId: frendid,
                                senderType: type
                            },
                            success: function (responsebean) {

                            }
                        });
                    }
                }
            })
        };
        //document.getElementById("box").setAttribute("class", "b show");
        //document.getElementById("close").setAttribute("class", "hiddenX show");

        $('.openTallk').click(openTallk);
        //回车事件
        $(".inputBox").keypress(function (e) {
//		var code = event.keyCode;
            /*e = e || window.event;
             var key = e ? (e.charCode || e.keyCode) : 0;
             if (13 == key) {
             sendNewMsg(addhtml);
             } */

            var theEvent = window.event || e;
            var code = theEvent.keyCode || theEvent.which;
            if (13 == code) {
                sendNewMsg();
            }

        });
        //发送信息
        $("#sendNewMsg").click(function () {
            sendNewMsg(addhtml);
        });
        //关闭对话框
        $('#hide').click(function () {
            hide();
        });


        function hide() {
            //document.getElementById("box").setAttribute("class", "b hidden");
            //document.getElementById("close").setAttribute("class", "hiddenX hidden");
            $("#box").attr("class", "b hidden");
            $("#close").attr("class", "hiddenX hidden");
            $("#content").val("");
        }

        function init() {
            $.ajax({
                type: 'get',
                url: contextpath + 'mymessage/tolist.action',
                async: false,
                contentType: 'application/json',
                dataType: 'json',
                data: {
                    pageNumber: pageNumber,
                    pageSize: pageSize,
                    state: $("input[name='select']").val()
                },
                success: function (res) {
                    if (res) {
                        pageNumber++;
                        if (res.length < pageSize) {
                            $("#loadMore").hide();
                        }
                        if (res.length > 0) {

                            $.each(res, function (i, n) {
                                var html = "";
                                html += "<tr><th rowspan='2' class='headPortrait'><img class='pictureNotice' src='" + contextpath + n.avatar

                                    + "'></th><td class='name'><span>"
                                    + n.name
                                    + "</span><span class='time1'>"
                                    + formatDate(n.sendTime, "")
                                    + "</span></td></tr>";
                                html += "<tr><td colspan='2' class='personMessageContent'>私信内容："
                                    + n.content
                                    + '</td><td class="buttonDetail"><div class="buttonAccept" ><a class="a openTallk" id="' + n.talkId + '" href="javascript:" >查看详情</a></div></td></tr>';
                                html += "<tr><td colspan='4' align='center'><hr class='line'></td></tr>";
                                html += "<input id='name_" + n.talkId + "' type='hidden' value='" + n.name + "'/><input id='type_" + n.talkId + "' type='hidden'value='" + n.type + "' />";
                                $("#list").append(html);

                            });
                            $('#list .openTallk').click(openTallk);
                        } else {
                            var html = "";
                            html += "<tr><td><div class='no-more'><img src='" + contextpath + "statics/image/aaa4.png'/><span>木有内容呀~~</span></div></td></tr>";
                            $("#list").append(html);
                        }
                    }
                }
            })
        }

        function sendNewMsg(addhtml) {
            //$("#talkList").html('');
            var content = $("#content").val();
            var sendTime = new Date();
            if (!content || content.trim() == '') {
                window.message.warning("请键入消息");
            } else {
                var frendId = $("#talk").val();
                $.ajax({
                    type: 'post',
                    url: contextpath + 'mymessage/senNewMsg.action',
                    async: false,
                    dataType: 'json',
                    data: {
                        friendId: frendId,
                        friendIdType: $('#type_' + frendId).val(),
                        content: content,
                        title: $(".personMessageTitle").html()
                    },
                    success: function (map) {
                        if (map.code == 'success') {
                            //window.message.success('发送成功');
                            //hide();

                            html = "<div class='oneTalk'> " +
                                "<div class='headAndNameRight float_right'> " +
                                "<div class='headDiv'><img class='headPicture' src='" + contextpath + '/' + avatar + "'/></div> " +
                                /*"<div class='headDiv'><img class='headPicture' src='"+contextpath+"statics/image/putongyhtouxiang.png'/></div>"+*/
                                "<div class='talkName'><text>" + map.name + "</text></div> " +
                                "</div> " +

                                "<div class='talkDivRight float_right' > " +
                                "<div class='sendMessage'> " +
                                "<div class='textDiv float_right'> " +
                                content +
                                "</div> " +
                                "</div> " +
                                "<div class='talkTime talkTimeAlignRight'>" + formatDate(sendTime) + "</div> " +
                                "</div> " +
                                "</div> ";

                            $("#talkList").append(addhtml);
                            $("#talkList").append(html);
                            $("#content").val('');
                            //$("#dialogue").scrollTop($("#dialogue")[0].scrollHeight);
                            //$("#dialogue").animate({scrollTop: '700px'},500);
                            setTimeout(function () {
                                $("#dialogue").scrollTop($("#talkList").height());
                            }, 0);
                        }
                    }
                });
            }
        }

        function formatDate(nS, str) {
            if (!nS) {
                return "";
            }
            var date = new Date(nS);
            var year = date.getFullYear();
            var mon = date.getMonth() + 1;
            var day = date.getDate();
            var hours = date.getHours();
            var minu = date.getMinutes();
            var sec = date.getSeconds();
            if (str == 'yyyy-MM-dd') {
                return year + '-' + (mon < 10 ? '0' + mon : mon) + '-'
                    + (day < 10 ? '0' + day : day);
            } else if (str == 'yyyy.MM.dd') {
                return year + '.' + (mon < 10 ? '0' + mon : mon) + '.'
                    + (day < 10 ? '0' + day : day);
            } else {
                return year + '-' + (mon < 10 ? '0' + mon : mon) + '-'
                    + (day < 10 ? '0' + day : day) + ' '
                    + (hours < 10 ? '0' + hours : hours) + ':'
                    + (minu < 10 ? '0' + minu : minu) + ':'
                    + (sec < 10 ? '0' + sec : sec);
            }
        }

    }
);


