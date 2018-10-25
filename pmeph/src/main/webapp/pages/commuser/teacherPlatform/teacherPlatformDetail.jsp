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
    <title>活动详情页</title>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/teacherPlatform/teacherPlatformDetail.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/ckplayer/ckplayer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="content-wrapper">
    <div class="pagedatail">首页>活动详情</div>
    <div style="margin-top: 20px">
        <div class="name">师资培训：</div>
        <div class="title">${map.activity_name}</div>
    </div>
    <div style="margin-top: 20px">
        <div class="time">活动日期：${map.activity_date}</div>
        <div class="picture"></div>
        <div class="read">5</div>
    </div>
    <div style="margin-top: 50px">
        <div class="bigpicture">
            <img src="${ctx}/image/${map.cover}.action" class="szpt-img">
        </div>
        <div style="float: left;height: 300px">
            <%--<div class="part1" onclick="toxikb(${map.cms_id})">${map.title}</div>--%>
            <div class="part2">${map.content}</div>
        </div>
    </div>
    <div class="part5">
        <div class="part4"></div>
        <div class="part3">活动视频</div>
        <div style="float: right;cursor: pointer" onclick="tovideotolist(${map.id})">更多>></div>
    </div>
    <div class="part6">
        <c:forEach var="list" items="${voideolist}" varStatus="status">
            <c:if test="${status.index<3}">
                <div class="video" style="margin-top: 20px;float: left;margin-left: 30px">
                    <div class="video-a" id="video-${status.index+1}" type="mp4"
                         src="http://${_remoteUrl}/v/play/${list.path}"
                         poster="${ctx}/image/${list.cover}.action" onclick="addid('${list.id}','${list.clicks}')">
                    </div>
                    <div class="detail">
                        <div class="video-name">${list.title}</div>
                        <div class="clicks">${list.clicks}</div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="part6" style="margin-top: 40px">
        <c:forEach var="list" items="${voideolist}" varStatus="status">
            <c:if test="${status.index>2}">
                <div class="video" style="margin-top: 20px;float: left;margin-left: 30px">
                    <div class="video-a" id="video-${status.index+1}" type="mp4"
                         src="http://${_remoteUrl}/v/play/${list.path}"
                         poster="${ctx}/image/${list.cover}.action" onclick="addid('${list.id}','${list.clicks}')">
                    </div>
                    <div class="detail">
                        <div class="video-name">${list.title}</div>
                        <div class="clicks">${list.clicks}</div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <input type="hidden" id="video_id">
    <input type="hidden" id="clicks">
    <script type="text/javascript">
        //定义一个数组，用来存储已经播放过的视频
        var ids=[];

        $(function () {
            $(function () {
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

                     //添加监听，监听播放事件
                     player.addListener("play",function () {
                         //延时执行，防止ID没有注入到隐藏域，后台报空指针
                         setTimeout(function () {
                             beforeadd();
                         },0)
                     });
                     //监听暂停事件
                     player.addListener("paused",function (paused) {
                        //console.log(paused);
                       // throw paused
                    });
                })
            });
        })

        function addid(id,clicks) {
            $("#video_id").val(id);
            $("#clicks").val(clicks);
        }

        //添加播放次数，只添加一次
        function beforeadd() {
            console.log(ids)
            //判断是否已经播放过此视频
            var id='';
            if(ids.length>0){
                for(var i=0;i<ids.length;i++){
                    id=ids[i];
                    if($("#video_id").val()==id){
                        console.log('播放次数已经增加，不予处理');
                    }else{
                        addclicks();
                    }
                }
            }else{
                addclicks();
            }
        }

        //增加播放次数
        function addclicks() {
            $.ajax({
                type:'post',
                url:contextpath+'teacherPlatform/updateclicks.action?clicks='+$("#clicks").val()+'&id='+$("#video_id").val(),
                async:false,
                dataType:'json',
                success:function(json){
                    console.log(json)
                    ids.push($("#video_id").val());
                }
            });
        }

        //跳转到视频列表页面
        function tovideotolist(id) {
            window.location.href=contextpath+'teacherPlatform/videotolist.action?id='+id+'&startrow=0&endrow=12';
        }
        //跳转到相关资源页面
        function tosourcelist(id) {
            window.location.href=contextpath+'teacherPlatform/tosourcelist.action?id='+id+'&startrow=0&endrow=10';
        }
        //跳转到信息快报详情页面
        function toxikb(id) {
            window.location.href=contextpath+'inforeport/toinforeport.action?id='+id;
        }
    </script>
    <div class="part5" style="margin-top: 60px">
        <div class="part4"></div>
        <div class="part3">相关资源</div>
        <div style="float: right;cursor: pointer" onclick="tosourcelist(${activity_id})">更多>></div>
    </div>
    <div class="part5" style="margin-bottom: 30px">
        <c:forEach items="${sourcelist}" var="list" varStatus="status">
            <div class="part8">${status.index+1}.${list.source_name}</div>
            <div class="down" onclick="window.location.href='${ctx}/file/download/${list.file_id}.action'"></div>
        </c:forEach>
    </div>
    <div style="clear: both"></div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
