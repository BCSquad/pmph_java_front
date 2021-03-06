<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = path + "/";
    String contextpath = request.getContextPath();
%>
<!DOCTYPE html >
<html>
<head>
    <script>
        var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>社区主页</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/commuser/community/community.css?t=${_timestamp}" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/ckplayer/ckplayer.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/community/community.js?t=${_timestamp}"></script>
</head>
<style type="text/css">
    .lastest {
        background: url(${ctx}/statics/image/css_sprites.png) -45px -212px;
    }
</style>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="content-wrapper">
    <input type="hidden" id="sid" value="${sid }"/>

    <input type="hidden" id="pagenum" value="${pagenum }"/>
    <input type="hidden" id="pagesize" value="${pagesize }"/>
    <input type="hidden" id="total" value="${total }"/>
    <input type="hidden" id="pagetotal" value="${pagetotal }"/>
    <div class="navigation">
        <a href="homepage/tohomepage.action">首页</a>&gt;
        <a href="community/tolist.action">教材社区</a>&gt;${notice.title }
    </div>
    <input type="hidden" id="materialId" value="${notice.material_id }"/>
    <div class="pagecontent">
        <div class="report">
            <div class="left">
                <div style="background-image: url(${ctx}/statics/image/css_sprites.png);background-position:-291px -85px;width:25px;height:110px;
                        margin-left:10px;margin-top:34px"></div>
            </div>
            <div class="center">
                <ul style="list-style:none;">
                    <c:forEach items="${reportlist}" var="report" varStatus="status">
                        <li>
                            <div>
                                <div class="tag">&gt;</div>
                                <c:choose>
                                <c:when test="${report.category_id==1 }">
                                <a href="articledetail/toPage.action?wid=${report.id }&&materialId=${notice.material_id }">
                                    </c:when>
                                    <c:when test="${report.category_id==2 }">
                                    <a href="inforeport/toinforeport.action?id=${report.id }&&materialId=${notice.material_id }">
                                        </c:when>
                                        <c:otherwise>
                                        <a href="cmsnotice/noticeMessageDetail.action?id=${report.mid }&&materialId=${report.material_id }&&csmId=${report.id }">
                                            </c:otherwise>
                                            </c:choose>
                                            <div class="inleft">${report.title}</div>
                                            <div class="${status.index==1 || status.index==0? 'lastest':''}"
                                                 style="float: left;width: 29px;height: 15px;margin: 10px;"></div>
                                        </a>
                                        <div class="inright">

                                            <fmt:formatDate value="${report.gmt_create }" pattern="yyyy-MM-dd"/>
                                        </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="right"><a href="cmsinfoletters/tolist.action?materialId=${notice.material_id }">全部&gt;&gt;</a>
            </div>
        </div>
        <div class="report"  style="float:right ">
            <div class="left">
                <div style="background-image: url(${ctx}/statics/image/r2.png);width:25px;height:100px;
                        margin-left:10px;margin-top:34px"></div>
            </div>
            <div class="center">
                <c:forEach items="${activitiList}" var="list" varStatus="status">
                    <div title=""   class="center-up"
                         onclick="window.location.href='<c:url value="/teacherPlatform/todetail.action?id=${list.cms_id}&activity_id=${list.activity_id}"/>'">
                        <div class="center-img" >
                            <img alt="" src="${ctx}/image/${list.cover}.action" style="display:block;width:120px;max-height:80px;margin: auto">
                            <%--<img alt="" src="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg" style="width:120px;height:80px;">--%>
                        </div>
                        <div class="center-down" >${list.activity_name}</div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${size>3}">
                <div class="right"><a href="community/toactivitylist.action?material_id=${material_id}">更多&gt;&gt;</a></div>
            </c:if>
        </div>
    </div>
    <div class="pagecontent">
        <div class="book">
            <div class="bhead">
                <div class="headicon"></div>
                <div class="headtext">本套教材图书</div>
            </div>
            <div class="booklist">
                <c:set var="count" value="${booklist.size() }"></c:set>
                <c:forEach items="${booklist }" var="book">
                    <c:if test="${book !=null }">
                        <div title="${book.bookname }" class="item" onclick="window.location.href='<c:url
                                value="/readdetail/todetail.action?id=${book.id }"/>'">
                            <div class="itemimg"
                                 style="width:126px;height:126px;margin:20px auto 0px;text-align: center">
                                <img alt=""
                                     src="${book.image_url=='DEFAULT'? 'statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':book.image_url }">
                            </div>
                            <div class="bookname" >${book.bookname } </div>
                        </div>
                        <c:set var="count" value="${count-1 }"></c:set>
                    </c:if>
                </c:forEach>
                <c:if test="${booklist.size() ==count }">
                    <div style="float:left;text-align: center;width:100%;" id="nomore">
                        <div class="no-more">
                            <img src="<c:url value="/statics/image/aaa4.png"></c:url>"
                                 style="display: block;margin: 0px auto 0px;">
                            <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
                        </div>
                    </div>
                </c:if>
            </div>
            <div style="clear: both"></div>


            <div style="text-align: right;">
                <ul class="pagination" id="page1">
                </ul>
                <div style="display: inline-block;vertical-align: top;text-align:left;">
                    <select id="edu" name="edu">
                        <option value="5"  ${pagesize=='5'? 'selected':'' }>每页5条</option>
                        <option value="10" ${pagesize=='10'? 'selected':'' }>每页10条</option>
                        <option value="15" ${pagesize=='15'? 'selected':'' } >每页15条</option>
                        <option value="20" ${pagesize=='20'? 'selected':'' }>每页20条</option>
                    </select>
                </div>
                <div class="pageJump">
                    <span>共${pagetotal }页，共${total }条数据，跳转到</span>
                    <input type="text"/>
                    <span class="pp">页</span>
                    <button type="button" class="button">确定</button>
                </div>
            </div>

            <div class="div_butt">
                <div class="bt_tj" onclick="toMain()">返回</div>
            </div>
        </div>
        <div class="pageright">
        <div class="rhead">
            <div class="item select" id="comment" onclick="comments('${notice.material_id }')">
                精选书评
            </div>
            <div class="item noselect" id="smallvideo" onclick="smallvideos('${notice.material_id }')">
                微视频
            </div>
        </div>
        <div class="list" style="margin-left:20px;width:230px;">
            <ul id="ullist">
                <c:forEach items="${someComments }" var="comment">
                    <li class="commentli">
                        <p class="title">${comment.bookname }</p>
                        <p class="message">
                            <span class="name">${comment.username }  发表了评论</span>
                            <span class="scoreimg ${comment.score >=2.0 ? 'yellowstar':'graystar'}"></span>
                            <span class="scoreimg ${comment.score >=4.0 ? 'yellowstar':'graystar'}"></span>
                            <span class="scoreimg ${comment.score >=6.0 ? 'yellowstar':'graystar'}"></span>
                            <span class="scoreimg ${comment.score >=8.0 ? 'yellowstar':'graystar'}"></span>
                            <span class="scoreimg ${comment.score >=10.0 ? 'yellowstar':'graystar'}"></span>

                        </p>
                        <p class="contentext">${comment.contentxt }</p>
                    </li>

                </c:forEach>
            </ul>
        </div>
        <div class="more" id="more"><a
                href="community/morecomments.action?materialId=${notice.material_id}">查看更多精选书评</a></div>
    </div>
    </div>
    <div style="clear:both;width:100%"></div>
</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>