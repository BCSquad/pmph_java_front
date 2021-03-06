<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>读书首页</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/readpage/readpage.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.scroll.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/readpage/readpage.js?t=${_timestamp}" type="text/javascript"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="readpage" name="pageTitle"/>
</jsp:include>
<input type="hidden" id="auto_play" value="${adInfo.auto_play}">
<input type="hidden" id="animation_interval" value="${adInfo.animation_interval}">
<div class="body">
    <div class="content-wrapper">
        <div class="area1">
            <div class="banner">
                <!--广告轮播区域-->
                <div class="move" id="move">
                    <ul>
                        <c:forEach var="ad" items="${adInfo.detailList}">
                            <li><img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${ad.image_jump_url}')" class="img-move"/></li>
                        </c:forEach>


                    </ul>
                </div>
                <div class="ctrl" id="ctrl"></div>
            </div>
            <!--重点学科推荐-->
            <div class="op-link">
                <div class="re_6" style="margin-top: 10px;margin-left: 32px;">重点学科</div>
                <c:forEach var="ad3" items="${adInfo3.detailList}" varStatus="status">
                    <c:if test="${status.index==0}">
                        <div class="re_1">
                            <c:if test="${adInfo3.type==0}">
                                <img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${adInfo3.url}')"
                                     style="width: 310px;height: 130px;border-radius: 5px">
                            </c:if>
                            <c:if test="${adInfo3.type==1}">
                                <img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${ad3.image_jump_url}')"
                                     style="width: 310px;height: 130px;border-radius: 5px">
                            </c:if>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach var="ad3" items="${adInfo4.detailList}" varStatus="status">
                    <c:if test="${status.index==0}">
                        <div class="re_2">
                            <c:if test="${adInfo4.type==0}">
                                <img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${adInfo4.url}')"
                                     style="width: 310px;height: 130px;border-radius: 5px">
                            </c:if>
                            <c:if test="${adInfo4.type==1}">
                                <img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${adInfo4.image_jump_url}')"
                                     style="width: 310px;height: 130px;border-radius: 5px">
                            </c:if>
                        </div>
                    </c:if>
                </c:forEach>
                <div class="re_3">
                    <div style="margin-top: 37px">
                        <!-- <div class="re_4">还在为资格考试烦恼？</div> -->
                        <div class="re_5" onclick="window.open('${ctx}/books/promoteList.action?type=1')">更多>></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="area2">
            <div class="area3">
                <div class="block">
                    <div class="tab-bar">
                    	<div class="type_bar_float_right">
                        <c:forEach items="${bookTypes}" var="type" varStatus="status">
                            <%--<div class="tab ${status.index==0?'active':''}" id="${type.id}"
                                 onclick='chooseType("${type.id}")'>${type.type_name}</div>--%>

                            <div class="tab recommend ${status.index==0?'active':''}"
                                 typeid="${type.id}"
                                 id="ZKDiv_${type.id}" onclick="javaScript:ChangeDiv(${type.id})">
                                    ${type.type_name}
                            </div>
                        </c:forEach>
                        </div>
                        <div class="remark">
                            <!--<img class="img_zdtj"/>-->
                            <span class="span_1"></span>
                            <span class="mkbt">重点推荐</span>
                        </div>
                    </div>
                    <!-- 学校教育 -->
                    <div class="content" id="JKDiv_0">

                    </div>
                </div>

                <div class="block">
                    <div class="tab-bar" style="border-bottom-color: #FC9C03">
                        <%-- <div class="tab type active" id="FYDiv_0" onclick="javaScript:ChangeFYDiv('0','JKFYDiv_',3)">
                             学校教育
                         </div>
                         <div class="tab type" id="FYDiv_1" onclick="javaScript:ChangeFYDiv('1','JKFYDiv_',3)">
                             毕业后教育
                         </div>
                         <div class="tab type" id="FYDiv_2" onclick="javaScript:ChangeFYDiv('2','JKFYDiv_',3)">
                             继续教育
                         </div>
                         <div class="tab type" id="FYDiv_3" onclick="javaScript:ChangeFYDiv('3','JKFYDiv_',3)">
                             考试用书
                         </div>--%>
                         <div class="type_bar_float_right">
	                        <c:forEach items="${bookTypes}" var="type" varStatus="status">
	                            <%--<div class="tab ${status.index==0?'active':''}" id="${type.id}"
	                                 onclick='chooseType("${type.id}")'>${type.type_name}</div>--%>
	
	                            <div class="tab type ${status.index==0?'active':''}"
	                                 typeid="${type.id}"
	                                 id="JKFYDiv_${type.id}" onclick="javaScript:ChangeFYDiv(${type.id})">
	                                    ${type.type_name}
	                            </div>
	                        </c:forEach>
						</div>
                        <div class="remark">
                            <img class="span_1" src="${ctx}/statics/image/xstj.png"/>
                            <!--<span id="span_1"></span>-->
                            <span class="mkbt">新书推荐</span>
                        </div>
                    </div>
                    <div class="content" id="JKFYDiv_0">
                    </div>
                </div>


                <div class="block">
                    <div class="title">
                        <div class="line"></div>
                        <div class="name">热门书评</div>
                    </div>
                    <div class="items">
                        <c:forEach var="rmsp" items="${rmspList}">
                            <div class="item1" onclick="openUrl('${rmsp.id1}')">
                                <div class="sp_01">
                                        <%-- <img src="${rmsp.image_url}"/> --%>
                                    <c:if test="${rmsp.image_url=='DEFAULT'}"><img
                                            src="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"></c:if>
                                    <c:if test="${rmsp.image_url!='DEFAULT'}"><img src="${rmsp.image_url}"/></c:if>
                                </div>
                                <div class="sp_02">
                                    <div class="sp_title">${rmsp.bookname}</div>
                                    <div class="sp_pl">
                                        <span style="float: left;">${rmsp.nickname} 评论了 《${rmsp.bookname}》</span>
                                    </div>
                                    <div style="clear: both"></div>
                                    <div class="sp_remark">${rmsp.content}</div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>

            </div>
            <!--描述：右侧区域-->
            <div class="area4">
                <!--描述：图书分类-->
                <div class="rg_content">
                    <span id="span_3" class="tsfl">图书分类</span>
                    <hr style=" height:1px;border:none;border-top:1px solid #f0f0f0;">
                </div>
                <div style="width: 350px;background: #F7F7F7;">
                    <c:forEach var="type1" items="${materialType}" varStatus="status">
                        <div class="ts_type ${status.index=='0'?'ts_type1':''}" id="FLDiv_${type1.id}"
                             onclick="javaScript:ChangeFLDiv('${type1.id}','ChangeFLDiv_',3)"
                             style="width: ${350/materialType.size()}px">
                            <span>${type1.type_name}</span>
                            <div class="arrow" style="left:${350/materialType.size()/2-5}px"></div>
                        </div>
                    </c:forEach>
                    <div style="clear: both;"></div>
                </div>
                <div style="clear: both;"></div>
                <c:forEach items="${materialType}" var="type1" varStatus="status">
                    <div id="ChangeFLDiv_${type1.id}"
                         class="tsfl_1 ChangeFLDiv" ${status.index!='0'?'style="display: none;"':''}>
                        <c:forEach items="${type1.dataList}" var="type2">
                            <div class="part_1">
                            	
                                <span><a target="_blank"
			                                       href="<c:url value="/booksearch/toPage.action?type=${type2.id}"/>">${type2.type_name}</a></span>
                                
	                                <c:if test="${type2.dataList.size()>0}">
	                                
		                                <div class="part_2">
			                                <c:forEach var="type3" items="${type2.dataList}">
			                                    <a target="_blank"
			                                       href="<c:url value="/booksearch/toPage.action?type=${type3.id}"/>">${type3.type_name}</a>
			                                	<c:if test="${type3.dataList.size()>0}">
				                                	<%-- <div class="part_3">
				                                		
				                                		<c:forEach var="type4" items="${type3.dataList}">
				                                			<a target="_blank"
				                                       			href="<c:url value="/booksearch/toPage.action?type=${type4.id}"/>">${type4.type_name}</a>
				                                		</c:forEach>
				                                	</div> --%>
			                                	</c:if>
			                                </c:forEach>
			                            </div>
			                            
		                            </c:if>
	                            
                            </div>
                            
                        </c:forEach>
                    </div>
                </c:forEach>
                <!--描述：图书畅销-->
                <div class="rg_content">
                    <div class="doc"></div>
                    <span class="tsfl" style="margin-left: 10px">图书畅销榜</span>
                    <%--<hr style=" height:1px;border:none;border-top:1px solid #f0f0f0;">--%>
                </div>
                <div style="float: left;width: 360px;border-bottom: 1px solid #DEDEDE;margin-top: 15px;height: 28px">
                    <c:forEach items="${bookTypes}" var="type" varStatus="status">
                        <div class="tscx_type ${status.index==0?'tscx_type1':''}" id="CXDiv_${type.id}"
                             typeid="${type.id}"
                             onclick="javaScript:ChangeCXDiv(${type.id})">
                            <div>${type.type_name}</div>
                        </div>
                        <div class="div_center"><img src="${ctx}/statics/image/shu.png"
                                                     style="margin-top: 5px;float: left">
                        </div>
                    </c:forEach>
                </div>
                <%--<div style="clear: both;height: 14px;"></div>--%>
                <div class="hot-list" id="JKCXDiv_0">
                </div>
                <div style="clear: both"></div>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>
    <jsp:include page="/pages/comm/tail.jsp">
        <jsp:param name="linked" value="linked"></jsp:param>
    </jsp:include>
</div>
<script>
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?c8927680e561788c668c5e891dfe322c";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>

