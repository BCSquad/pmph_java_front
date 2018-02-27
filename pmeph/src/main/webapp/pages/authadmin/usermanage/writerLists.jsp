<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
        var contextpath2 = '${pageContext.request.contextPath}';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>用户管理</title>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/usermanage/writerLists.css" type="text/css">

    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">


</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="usermanage"></jsp:param>
</jsp:include>
<div class="b hidden" id="box" style="display: none;margin-left: -15%;margin-top: -10%">
    <div class="hiddenX hidden" id="close">
        <img onclick="hide()" style="width:100%;height:100%;cursor: pointer;" src="${ctx}/statics/image/closediv.png">
    </div>
    <span class="personMessageTitle" id="realname"></span>
    <div class="contentBox" id="dialogue">
    </div>
    <div>
        <div style="float: left;width: 80%;height: 100%">
            <textarea id="content" class="inputBox"
                      style="width: 100%;height: 98%;border: none;outline:0;font-size:15px;"
                      placeholder="请输入消息内容,按回车键发送"></textarea>
        </div>
        <div style="float: left;width: 20%;height: 100%">
            <div class="div_btn11" style="cursor: pointer;">
                <span class="button11" onclick="sendxiaoxi()">发送</span>
            </div>
        </div>
    </div>
</div>
<div class="content-wrapper">
    <div class="message">
        <div class="sousuokuang">
            <%--   <select id="sstj" name="sstj">
                   <option value="0">姓名</option>
                   <option value="1">用户代码</option>
               </select>--%>
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">用户代码/姓名:</div>
            <input type="text" value="${username}" placeholder='请输入' id="ssk" name="username">
            <input type="button" value="查询" id="cxan" onclick="query()">
            <!--  <div id="gjss"><a href="">高级搜素</a></div>-->
        </div>
        <table class="table">
            <thead>
            <tr>
                <td>序号</td>
                <td>用户代码</td>
                <td>姓名</td>
                <td>手机</td>
                <td>邮箱</td>
                <td>职务</td>
                <td>职称</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody id='tb'>
            <c:forEach items="${page.rows }" var="item" varStatus="vs">
                <tr>
                    <c:choose>
                        <c:when test="${(vs.index+1)%10 == 1}">
                            <td>${vs.count}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${vs.count}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>${item.username } </td>
                    <td>${item.realname } </td>
                    <td>${item.handphone } </td>
                    <td>${item.email } </td>
                    <td>${item.position } </td>
                    <td>${item.title } </td>
                    <td><a onclick="show('${item.realname}','${item.id}')" style="cursor: pointer;">发消息</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="fenyelan">
            <ul class="pagination" id="page1">
            </ul>
            <div style="display: inline-block; vertical-align: top">
                <select id="pages" name="pages">
                    <option value="10"  ${pageSize=='10'?'selected':'' }>每页10条</option>
                    <option value="20"  ${pageSize=='20'?'selected':'' }>每页20条</option>
                    <option value="50"  ${pageSize=='50'?'selected':'' }>每页50条</option>
                </select>
            </div>
            <div class="pageJump">
                <span>共<span id="totoal_count">${page.pageTotal}</span>页</span>
                <span>跳转到</span>
                <input type="text"/>
                <span>页</span>
                <button type="button" class="button">确定</button>
            </div>
        </div>
        <div class="clear"></div>

    </div>
</div>
<input type="hidden" id="frendId">
</div>
<script type="text/javascript">
    //发送消息
    function sendxiaoxi() {
        var content = $("#content").val();
        if (content == null || content.trim() == '') {
            window.message.warning("请键入消息");
        } else {
            var frendId = $("#frendId").val();
            $.ajax({
                type: 'post',
                url: contextpath + 'organizationuser/senNewMsg.action',
                async: false,
                dataType: 'json',
                data: {
                    friendIdType: '2',
                    friendId: frendId,
                    content: content,
                    title: $(".personMessageTitle").html()
                },
                success: function (responsebean) {
                    if (responsebean == 'success') {
                        setTimeout(function () {
                            $("#content").val("");
                        },0);

                        refreshmessage();
                    }
                }
            });
        }
    }

    //刷新消息
    function refreshmessage() {
        var frendid = $("#frendId").val();
        $.ajax({
            type: 'get',
            url: contextpath + 'mymessage/getDialogue.action',
            contentType: 'application/json',
            dataType: 'json',
            data: {
                friendId: frendid,
                friendType: '2'
            },
            success: function (responsebean) {
                $("#dialogue").html('');
                if (null != responsebean && responsebean.length >= 0) {
                    var html = "<div id='dialogContent'>";
                    /*for (var i = 0; i < responsebean.length; i++) {

                     if (responsebean[i].isMy) {//我发送的
                     html +=
                     "<div class='oneTalk'> " +
                     "<div class='headAndNameRight float_right'> " +
                     "<div class='headDiv'><img class='headPicture' src='" + contextpath2 + responsebean[i].avatar + "'/></div> " +
                     "<div class='talkName'><text>" + responsebean[i].senderName + "&nbsp;&nbsp;&nbsp;&nbsp;" + formatDate(responsebean[i].sendTime, 'yyyy.MM.dd hh:ss:mm') + "</text></div> " +
                     "</div> " +
                     "<div class='talkDivRight float_right' > " +
                     "<div class='sendMessage'> " +
                     "<div class='textDiv float_right'> " + responsebean[i].content + "</div> " +
                     "</div> " +
                     "</div> " +
                     "</div> ";
                     } else {
                     html +=
                     "<div class='oneTalk'> " +
                     "<div class='headAndNameLeft float_left'> " +
                     "<div class='headDiv'><img class='headPicture' src='" + contextpath2 + "" + responsebean[i].avatar + "'/></div> " +
                     "<div class='talkName'><text>" + responsebean[i].senderName + "</text></div> " +
                     "</div> " +
                     "<div class='talkDiv float_left' > " +
                     "<div class='sendMessage'> " +
                     "<!-- <div class='triangle leftTriangle float_left'></div>--> " +
                     "<div class='textDiv float_left'> " + responsebean[i].content + "</div> " +
                     "</div> " +
                     "<div class='talkTime talkTimeAlignLeft'>" + formatDate(responsebean[i].sendTime, 'yyyy.MM.dd hh:ss:mm') + "</div> " +
                     "</div> " +
                     "</div> ";
                     }


                     }*/
                    $.each(responsebean, function (i, n) {

                        if (n.isMy) {//我发送的
                            avatar = n.avatar;
                            html += "<div class='oneTalk'> " +
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
                            html += "<div class='oneTalk'> " +
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
                    });

                    html += "</div>"
                    $("#dialogue").append(html);
                }
                //更新消息状态
                $.ajax({
                    type: 'get',
                    url: contextpath + 'mymessage/updateMyTalk.action',
                    contentType: 'application/json',
                    dataType: 'json',
                    data: {
                        senderId: frendid,
                        senderType: 2
                    },
                    success: function (responsebean) {

                    }
                });
                setTimeout(function () {
                    var content = $("#dialogContent")[0];
                    $("#dialogue")[0].scrollTop = content.scrollHeight;
                }, 0);

            }

            /*$("#dialogue").scrollTop = div.scrollHeight;*/
        });
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
            return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day);
        } else if (str == 'yyyy.MM.dd') {
            return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day);
        } else if (str == 'yyyy.MM.dd hh:ss:mm') {
            return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
        } else {
            return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
        }

    }
    //点击显示聊天窗口
    function show(name, id) {
        $("#box").show();
        $("#frendId").val(id);
        $("#realname").html('与' + name + '的私信窗口');
        refreshmessage();
    }
    //隐藏聊天窗口
    function hide() {
        $("#box").hide();
    }
    //点击查询
    function query() {
        var username = encodeURI(encodeURI($("#ssk").val()));
        window.location.href = contextpath + 'organizationuser/writerLists.action?username=' + username;
    }
    var pageSize = $("#pages").val();
    Page({
        num: parseInt('${page.pageTotal}'),					//页码数
        startnum: parseInt('${pageNumber}'),				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (pageNumber) {	//回调函数
            pageFun(pageSize, pageNumber);
        }
    });
    //初始化页面
    $(function () {
        $(".inputBox").keypress(function (event) {
            if (event.which == 13) {
                console.log(event)
                sendxiaoxi();
            }
        });

        /*$('#sstj').selectlist({
         zIndex: 10,
         width: 208,
         height: 40,
         optionHeight: 40
         });*/

        $('#pages').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function () {
                //                   	var pageSize =this.getSelectedOptionValue(pages);
                var pageSize = $('input[name=pages]').val();
                pageFun(pageSize, '${pageNumber}');
            }
        });
        $('#ssk').keyup(function (event) {
            if (event.keyCode == 13) { //回车键弹起事件
                query();
            }
        });
        $(window).scroll(function () {
            var top = $(window).scrollTop() + 200;
            var left = $(window).scrollLeft() + 605;
            $("#box").css({left: left + "px", top: top + "px"});
        });
    });
    //分页
    function pageFun(pageSize, pageNumber) {
        window.location.href = contextpath + 'organizationuser/writerLists.action?pageSize=' + pageSize + '&pageNumber=' + pageNumber + "&username=" + encodeURI(encodeURI($("#ssk").val()));
    }
</script>
<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
    <jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
</body>
</html>