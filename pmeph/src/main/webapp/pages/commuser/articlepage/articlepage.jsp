<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <title>文章首页</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/articlepage/articlepage.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/articlepage/articlepage.js"></script>
</head>
<body>
    <jsp:include page="/pages/comm/head.jsp">
    	<jsp:param value="articlepage" name="pageTitle" />
    </jsp:include>
    <div class="body">
        <!-- <div class="apply-book"><div class="text">教材<br>申报</div></div> -->
        <div class="content-wrapper">
            <%-- <div class="news">
                <div class="focus-news">
                    <div class="focus-title">
                    <span class="text1">FOCUS
                        <span  class="text2">NEWS</span>
                    </span>
                        <span class="text3">焦点信息</span>
                        <a href="#" >全部 &gt;&gt;</a>
                    </div>
                    <div class="focus-img">
                        <img src="${ctx}/statics/testfile/lunbo.png" alt="" onclick=""/>
                    </div>
                    <div class="focus-content">
                        <div style="float:left">
                            <img src="${ctx}/statics/testfile/picture1.png"  />
                        </div>
                        <div class="content-text" onclick="">
                            <p class="title" >人民出版社2个项目成功入选 2017年人民出版社2个项目成功入选 2017年人民出版社2个项目成功入选 2017年</p>
                            <p class="date">5个月前</p>
                            <p class="text" >今日，国家新闻出版广电总局公布了
                                《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                                会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                                就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空白地方级饭店被
                                海景房的活动恢复的计划好的复合机房和肌肤的合计合计大姐夫警方发动机和肌肤的环境的防
                                火防盗尽快发的肥胖纹迫IJE偶饿莞尔后儿念奴娇困扰界湖惹惹i
                            </p>
                        </div>
                    </div>
                    <div class="line">
                    </div>
                    <div class="focus-content">
                        <div style="float:left">
                            <img src="${ctx}/statics/testfile/picture1.png"  />
                        </div>
                        <div class="content-text" onclick="">
                            <p class="title">人民出版社2个项目成功入选 2017年度新闻出版改革发展项目库</p>
                            <p class="date">5个月前</p>
                            <p  class="text" >今日，国家新闻出版广电总局公布了
                                《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                                会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                                就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空白地方级饭店被
                                海景房的活动恢复的计划好的复合机房和肌肤的合计合计大姐夫警方发动机和肌肤的环境的防
                                火防盗尽快发的肥胖纹迫IJE偶饿莞尔后儿念奴娇困扰界湖惹惹i
                            </p>
                        </div>
                    </div>
                </div>






                <div class="notice ">
                    <div class="title">
                        <div class="line"></div>
                        <div class="name">遴选公告</div>
                    </div>
                    <div class="item" onclick="">
                        <p class="head1 running">进行中
                            <span class="time">截止日期：2017年11月10日</span>
                        </p>
                        <p class="notice_title">
                            《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                            会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                            就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空
                        </p>
                    </div>
                    <div class="item" onclick="">
                        <p class="head1 running">进行中
                            <span class="time">截止日期：2017年11月10日</span>
                        </p>
                        <p class="notice_title">
                            《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                            会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                            就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空
                        </p>
                    </div>
                    <div class="item" onclick="">
                        <p class="head1 running">进行中
                            <span class="time">截止日期：2017年11月10日</span>
                        </p>
                        <p class="notice_title">
                            《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                            会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                            就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空
                        </p>
                    </div>
                    <div class="item" onclick="">
                        <p class="head1 running">进行中
                            <span class="time">截止日期：2017年11月10日</span>
                        </p>
                        <p class="notice_title">
                            《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                            会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                            就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空
                        </p>
                    </div>
                    <div class="item" onclick="">
                        <p class="head1 running">进行中
                            <span class="time">截止日期：2017年11月10日</span>
                        </p>
                        <p class="notice_title">
                            《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                            会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                            就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空
                        </p>
                    </div>
                    <div class="item" onclick="">
                        <p class="head1 end">已结束
                            <span class="time">截止日期：2017年11月10日</span>
                        </p>
                        <p class="notice_title">
                            《合计合计的设计和东方红 》电话经济负担和地方哈哈积分兑换肌肤的恢复的环境地方很多
                            会计的房间放多少会计师点击房间和地方很近的复合机房的j手机的科技开发的环境符合的话
                            就反对恢复电话积分兑换会计放大放大后肌肤的计划开发的北京饭店北方的空
                        </p>
                    </div>
                </div>
            </div> --%>
            <div class="notes" style="margin-top: 50px">
                <div class="title">
                    <div class="line"></div>
                    <div class="name">医学随笔  <a  style="cursor: pointer;"  onclick="window.location.href='${ctx}/cms/list.action'">全部&gt;&gt;</a></div>
                </div>
                <c:forEach items="${listArt}" var="list" varStatus="status">
	                <div class="${status.index==0 or status.index==4 ?'item' :'item behind'}" onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                    <div class="command">
                        <span style="margin-left: 5px">推荐</span>
                    </div>
                    <div  class="content" >
                        <div class="content-image">
                            <img src="${ctx}/statics/testfile/p2.png" />
                        </div>
                        <p   class="content-title" style="cursor: pointer;">${list.title}</p>
                        <p  class="content-text">${list.summary}</p>
                        <div  class="foot">
                            <div style="float:left">
                                <c:if test="${list.avatar=='DEFAULT'}">
                                <img src="${ctx}/statics/testfile/mi.png" class="personicon"></c:if>
                				<c:if test="${list.avatar!='DEFAULT'}">
                				<img src="${ctx}/image/${list.avatar}.action" class="personicon"></c:if>
                            </div>
                            <div  class="msg">
                                <span  class="name" style="cursor:pointer;" onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${list.userId }'">${list.realname}</span>
                                <span  class="name">${list.auth_date}</span>
                            </div>
                        </div>
                    </div>
                    </div>
                </c:forEach>
            </div>
            <div class="writer">
                <div class="title">推荐作者</div>
                <div style="margin-top: 11px">

                <c:forEach items="${listAut}" var="list" varStatus="status">
                    <div class="item">
                        <div class="content">
                            <div class="content-img" >
                                <c:choose>
	                            	<c:when test="${list.avatar=='DEFAULT'}">
	                            		<img src="${ctx}/statics/image/default_image.png" class="a6_head">
	                            	</c:when>
	                            	<c:otherwise>
	                            		<img src="${ctx}/image/${list.avatar}.action" class="a6_head">
	                            	</c:otherwise>
	                            </c:choose>
                            </div>
                            <div class="msg">
                                <div class="name">${list.realname}</div>
                                <div class="text">${list.title}</div>
                            </div>
                            <div class="add">
                                <div>
                                	<c:choose>
					                	<c:when test="${list.status == null }">
					                		<span class="friend add_btn" title="申请加为好友！" onclick="addFriendfun(${list.id},'${list.realname}',0)" id="friend${list.id}"><B>+</B>好友</span>
					                	</c:when>
					                	<c:when test="${list.status  == 2 }">
					                		<span class="friend isFriend" title="已是您的好友！" id="friend${list.id}"><B>好友</B></span>
					                	</c:when>
					                	<c:when test="${list.status == 0 && list.isBeenRequest==1}">
					                		<span class="friend isBeenRequest" title="对方也想加您为好友，点击马上成为好友！" onclick="addFriendfun(${list.id},'${list.realname}',2)" id="friend${list.id}"><B>+</B>好友</span>
					                	</c:when>
					                	<c:when test="${list.status == 0 && list.hasRequest==1}">
					                		<span class="friend hasRequest" title="已申请加为好友，请等待对方同意。" id="friend${list.id}"><B>+</B>好友</span>
					                	</c:when>
					                </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>
    <jsp:include page="/pages/comm/tail.jsp">
        <jsp:param name="linked" value="linked"></jsp:param>
    </jsp:include>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?c8927680e561788c668c5e891dfe322c";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</body>
</html>
