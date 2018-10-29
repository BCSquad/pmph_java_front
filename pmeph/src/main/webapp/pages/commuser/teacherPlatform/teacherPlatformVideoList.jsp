<%--
  Created by IntelliJ IDEA.
  User: xieming
  Date: 2018/10/11
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>活动视频-更多</title>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/teacherPlatform/teacherPlatformList.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <script type="text/javascript" src="${ctx}/resources/comm/ckplayer/ckplayer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="most">
    <div class="content-wrapper">
        <div class="top">
            <div class="top1">活动视频</div>
            <div class="top2" onclick="window.history.back()"><<返回活动</div>
        </div>
        <div class="mid">
            <div class="video-count">
                <c:forEach items="${list}" var="list" varStatus="status">
                    <c:if test="${status.index<4}">
                        <div class="video">
                            <div class="video-a" id="video-${status.index+1}"
                                 src="http://${_remoteUrl}/v/play/${list.path}"
                                 poster="${ctx}/image/${list.cover}.action" type="mp4">
                            </div>
                            <div class="video-name">${list.title}</div>
                        </div>
                        </c:if>
                </c:forEach>
            </div>
            <div class="video-count">
                <c:forEach items="${list}" var="list" varStatus="status">
                    <c:if test="${status.index>3 and status.index<8}">
                        <div class="video">
                            <div class="video-a" id="video-${status.index+1}"
                                 src="http://${_remoteUrl}/v/play/${list.path}"
                                 poster="${ctx}/image/${list.cover}.action" type="mp4">
                            </div>
                            <div class="video-name">${list.title}</div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="video-count">
                <c:forEach items="${list}" var="list" varStatus="status">
                    <c:if test="${status.index>7 and status.index<11}">
                        <div class="video">
                            <div class="video-a" id="video-${status.index+1}"
                                 src="http://${_remoteUrl}/v/play/${list.path}"
                                 poster="${ctx}/image/${list.cover}.action" type="mp4">
                            </div>
                            <div class="video-name">${list.title}</div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="pageDiv" style="float: right">
                <ul class="pagination" id="page1"></ul>
                <div style="display: inline-block; vertical-align: top;">
                    <select id="pages" name="pages">
                        <option value="12 "  ${endrow=='12'?'selected':'' }>每页12条</option>
                        <option value="24 "  ${endrow=='24'?'selected':'' }>每页24条</option>
                        <option value="48 "  ${endrow=='48'?'selected':'' }>每页48条</option>
                    </select>
                </div>
                <div class="pageJump">
                    <span>共<span id="totoal_count" >${pagesize}</span>页</span>
                    <span>跳转到</span>
                    <input type="text"/>
                    <span>页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
<input type="hidden" value="${id}" id="id"><!--活动ID-->
<input type="hidden" value="${startrow}" id="startrow"><!--起始页码-->
<input type="hidden" value="${endrow}" id="endrow"><!--每页显示多少条-->
<input type="hidden" value="${pagesize}" id="pagesize"><!--当前页码-->
</body>
</html>
<script type="text/javascript">
    $(function () {
        var startrow=$("#startrow").val();
        var endrow=$("#endrow").val();
        var id=$("#id").val();
        var pagesize=$("#pagesize").val();

        $('#pages').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function (n) {
                //指定一页多少条数据
                queryList(id,1,n);
            }
        });
        Page({
            num: pagesize,		         //页码数
            startnum: startrow,			//指定页码
            elem: $('#page1'),		//指定的元素
            callback: function (n) {	//点击页码后触发的回调函数
                //选定哪一页
                queryList(id,n,endrow);
            }
        });

        $(".video-a").each(function () {
            var $this = $(this);
            var videoObject = {
                container: "#" + $this.attr("id"),
                variable: 'player',
                autoplay: false,
                /*flashplayer: true,*/
                video: $this.attr("src"),
                poster: $this.attr("poster")

            };
            var player = new ckplayer(videoObject);
        })
    })

    function queryList(id,startrow,endrow) {
        window.location.href=contextpath+'teacherPlatform/videotolist.action?id='+id+'&startrow='+startrow+'&endrow='+endrow;
    }
</script>