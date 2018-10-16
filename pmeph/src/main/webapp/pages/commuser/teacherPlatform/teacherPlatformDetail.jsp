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
        <div class="title">高等医学院校协作年会在成都成功召开</div>
    </div>
    <div style="margin-top: 20px">
        <div class="time">活动日期：2018-05-07</div>
        <div class="picture"></div>
        <div class="read">5</div>
    </div>
    <div style="margin-top: 50px">
        <div class="bigpicture"></div>
        <div style="float: left;height: 300px">
            <div class="part1">2018年度全国省（市、区）属高等医学院校协作年会在成都成功召开</div>
            <div class="part2"> 2018年7月7日，由全国省（市、区）属高等医学院校协作会、协作会秘书长单位广西医科大学、
                协作会联络处人民卫生出版社主办，成都医学院承办的“2018年度全国省（市、区）属高等医学院校协作年会”在成都
                召开。四川省教育厅副厅长姜亚军，四川省卫计委人事科教处监察专员方晓明，人民卫生出版社董事长、党委书记郝阳，
                人民卫生出版社总编辑杜贤，成都医学院党委书记余小平、校长樊均明、副校长潘克俭，广西医科大学校长赵劲民、副
                校长伍伟锋，河北医科大学校长崔慧先，内蒙古医科大学校长杜茂林，湖北医药学院党委书记涂汉军，河北北方学院院
                长宋鸿儒，川北医学院院长杜勇，包头医学院院长赵云山等全国50所省（市、区）属高等医学院校的领导、教授和专家，
                以及人卫社医学教育中心、市场营销中心、人民卫生电子音像出版社有限公司、人卫（北京）医学传媒科技有限公司相关
                人员等200余人参加了会议。</div>
        </div>
    </div>
    <div class="part5">
        <div class="part4"></div>
        <div class="part3">活动视频</div>
        <div style="float: right;cursor: pointer">更多>></div>
    </div>
    <div class="part6">
        <div class="video" style="margin-top: 20px;float: left">
            <div class="video-a" id="video-5" type="mp4">
            </div>
        </div>
    </div>
    <div class="video-list">
        <div class="video-list-name">视频列表</div>
        <div style="margin-top: 10px;float: left">
            <div class="video-img"><img src="${ctx}/image/5b36f572879ab892c4c9d633.action" class="avatar"></div>
            <div style="float: left;margin-left: 5px;width: 290px">
                <div class="video-title"  onclick="toplay('video1')" id="video1"
                     src="http://${_remoteUrl}/v/play/21a9e39c-e2c2-4f91-83b2-f1f52f5965de.mp4"
                     poster="${ctx}/image/5b36f572879ab892c4c9d633.action">视频一
                </div>
                <div class="video-time">上传时间：2018-03-30</div>
            </div>
        </div>
        <div style="margin-top: 10px;float: left">
            <div class="video-img"><img src="${ctx}/image/5b36f6c9879ab892c4c9d637.action" class="avatar"></div>
            <div style="float: left;margin-left: 5px;width: 290px">
                <div class="video-title"  onclick="toplay('video2')" id="video2"
                     src="http://${_remoteUrl}/v/play/f912da1b-ee0d-4ea0-b911-960a7aae2f07.mp4"
                     poster="${ctx}/image/5b36f6c9879ab892c4c9d637.action">视频二
                </div>
                <div class="video-time">上传时间：2018-03-30</div>
            </div>
        </div>
        <div style="margin-top: 10px;float: left">
            <div class="video-img"><img src="${ctx}/image/5af544fd879aae7254ec4fd5.action" class="avatar"></div>
            <div style="float: left;margin-left: 5px;width: 290px">
                <div class="video-title"  onclick="toplay('video3')" id="video3"
                     src="http://${_remoteUrl}/v/play/399ebf85-032f-4d86-a7fb-0bf2c25879f0.mp4"
                     poster="${ctx}/image/5af544fd879aae7254ec4fd5.action">视频三
                </div>
                <div class="video-time">上传时间：2018-03-30</div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function toplay(id) {
            $(".video-title").removeClass('selected');
            $("#"+id).addClass('selected');
            var videoObject = {
                container: "#video-5",
                variable: 'player',
                autoplay: true, //默认加载完毕开始播放
                /*flashplayer: true,*/  //是否调用flash播放器
                video: $("#"+id).attr('src'),
                poster: $("#"+id).attr('poster'),

            };
            var player = new ckplayer(videoObject);
        };

        $(function () {
                var videoObject = {
                    container: "#video-5",
                    variable: 'player',
                    autoplay: false,
                    /*flashplayer: true,*/
                    video: 'http://119.254.226.115/v/play/21a9e39c-e2c2-4f91-83b2-f1f52f5965de.mp4',
                    poster: '/pmeph/image/5b36f572879ab892c4c9d633.action'

                };
                var player = new ckplayer(videoObject);
        })

    </script>
    <div class="part5">
        <div class="part4"></div>
        <div class="part3">相关资源</div>
        <div style="float: right;cursor: pointer">更多>></div>
    </div>
    <div class="part5" style="margin-bottom: 30px">
        <div class="part8">1.伍伟锋—乘本科教学审核评估东风  打造高水平本科医学教育.pdf</div>
        <div class="down"></div>
        <div class="part8">2.唐红梅—医教协同 探索院校全科医学.pdf</div>
        <div class="down"></div>
        <div class="part8">3.张春辉—SMU以学生为中心教育信息化推进实践.pdf</div>
        <div class="down"></div>
        <div class="part8">4.葛磊—人工智能在医疗及教育中的应用探索.pdf </div>
        <div class="down"></div>
        <div class="part8">5.董良广—中国医学教育题库建设研究与实践.pdf</div>
        <div class="down"></div>
    </div>
    <div style="clear: both"></div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
