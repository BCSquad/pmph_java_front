<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>Title</title>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/commuser/focusAndSelect/annoceSelect.css" type="text/css">
    <script src="<%=path %>/resources/commuser/focusAndSelect/announcement.js" type="text/javascript"></script>
    <link rel="stylesheet" href="<%=path %>/resources/css/jquery.selectlist.css" type="text/css">
    <script type="text/javascript" src="<%=path %>/resources/comm/jquery/jquery.selectlist.js"></script>
    <style type="text/css">
        .div_content_right .select-button {
            background: #f6f6f6;
        }

        .div_content_right .select-wrapper {
            border: none;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $('#sort-wrapper').selectlist({
                zIndex: 10,
                width: 70,
                height: 20,
                optionHeight: 20,
                triangleColor: '#333333'
            });
        })
    </script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="content-body">
    <div class="div_top">
        首页>遴选公告
    </div>
    <div class="div_content">
        <div class="div_content_left">
            <span class="clicked" id="infoReport" style="margin-right: 15px;"
                  onclick="ChangeDiv('infoReport')">信息快报</span>
            <span class="clickbefore" id="selectAnnco" onclick="ChangeDiv('selectAnnco')">遴选公告</span>
        </div>
        <div class="div_content_right">
            <span style="color: #999999;">排序：</span>
            <span style="color: #333333;">
                <select id="sort-wrapper">
				    <option value="1">综合</option>
                <option value="2">最新</option>
                <option value="3">最热</option>
				</select>
            </span>
        </div>
    </div>
    <div style="margin-top:25px;">
        <div class="left">
            <div class="items">
                <div class="items_img">
                    推荐
                </div>
                <div class="item1">全国高等学校健康服务与管理专业第一轮规划教材</div>
                <div class="item2"><p>"各有关院校： 为了推动我国健康服务与管理专业的发展和学科建设，规范健康服务与管理专业的教学模式，适应新时期健康服务与管理专业人才培养和
                    健康服务与管理专业高等教育发展的需要，在2016年教育部本科专业目录调整中，新增设本科健康服务与管理专业（专业代码120410T）。"</p></div>
                <div class="item3">
                    <div style="float: left;">截止日期:2017-06-30</div>
                    <div style="float:right">发布日期：2017.03.21</div>
                </div>
            </div>
            <div class="items">
                <div class="items_img">
                    推荐
                </div>
                <div class="item1">全国高等学校健康服务与管理专业第一轮规划教材</div>
                <div class="item2"><p>"各有关院校： 为了推动我国健康服务与管理专业的发展和学科建设，规范健康服务与管理专业的教学模式，适应新时期健康服务与管理专业人才培养和
                    健康服务与管理专业高等教育发展的需要，在2016年教育部本科专业目录调整中，新增设本科健康服务与管理专业（专业代码120410T）。"</p></div>
                <div class="item3">
                    <div style="float: left;">截止日期:2017-06-30</div>
                    <div style="float:right">发布日期：2017.03.21</div>
                </div>
            </div>
            <div class="items">
                <div class="items_img">
                    推荐
                </div>
                <div class="item1">全国高等学校健康服务与管理专业第一轮规划教材</div>
                <div class="item2"><p>"各有关院校： 为了推动我国健康服务与管理专业的发展和学科建设，规范健康服务与管理专业的教学模式，适应新时期健康服务与管理专业人才培养和
                    健康服务与管理专业高等教育发展的需要，在2016年教育部本科专业目录调整中，新增设本科健康服务与管理专业（专业代码120410T）。"</p></div>
                <div class="item3">
                    <div style="float: left;">截止日期:2017-06-30</div>
                    <div style="float:right">发布日期：2017.03.21</div>
                </div>
            </div>
            <div class="items">
                <div class="items_img">
                    推荐
                </div>
                <div class="item1">全国高等学校健康服务与管理专业第一轮规划教材</div>
                <div class="item2"><p>"各有关院校： 为了推动我国健康服务与管理专业的发展和学科建设，规范健康服务与管理专业的教学模式，适应新时期健康服务与管理专业人才培养和
                    健康服务与管理专业高等教育发展的需要，在2016年教育部本科专业目录调整中，新增设本科健康服务与管理专业（专业代码120410T）。"</p></div>
                <div class="item3">
                    <div style="float: left;">截止日期:2017-06-30</div>
                    <div style="float:right">发布日期：2017.03.21</div>
                </div>
            </div>
            <div class="items">
                <div class="items_img">
                    推荐
                </div>
                <div class="item1">全国高等学校健康服务与管理专业第一轮规划教材</div>
                <div class="item2"><p>"各有关院校： 为了推动我国健康服务与管理专业的发展和学科建设，规范健康服务与管理专业的教学模式，适应新时期健康服务与管理专业人才培养和
                    健康服务与管理专业高等教育发展的需要，在2016年教育部本科专业目录调整中，新增设本科健康服务与管理专业（专业代码120410T）。"</p></div>
                <div class="item3">
                    <div style="float: left;">截止日期:2017-06-30</div>
                    <div style="float:right">发布日期：2017.03.21</div>
                </div>
            </div>

            <div class="load-more clearfix">加载更多...</div>
        </div>
        <div class="right" style="width: 335px;float: right;">
            <img src="${ctx}/statics/image/caode.png" style="width: 335px;height: 335px;"/>
        </div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</body>
</html>
