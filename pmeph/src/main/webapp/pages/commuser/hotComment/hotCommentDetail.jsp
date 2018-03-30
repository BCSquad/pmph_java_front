<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <title>热门书评列表</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link href="${ctx}/statics/css/base.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/hotComment/hotCommentDetail.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="body"> 
		<div class="content-wrapper">
			<div class="bigLeft">
				<div class="navDiv">
					<div class="nav">文章 > 热门书评</div>
				</div>
				<c:choose>
					<c:when test="${map==null}">
						<div class="no-more">
		                    <img src="<c:url value='/statics/image/aaa4.png'></c:url>">
		                    <span>木有内容呀~~</span>
		                </div>		
					</c:when>
					<c:otherwise>
							<div class="xqbf1">
			                <div class="xl1"><img src="${map.image_url }"
			                                      style="display: block;margin-bottom: 25px;height: 150px;margin: auto"/></div>
			                <div class="xl2">
			                    <ul>
			                        <li><span class="author">作者：</span><span class="writer"
			                                                                 style="color: #489299">${map.author}</span></li>
			                        <li class="author">出版社： ${map.publisher}</li>
			                        <li class="author">出版日期：${map.publish_date}</li>
			                        <li class="author">ISBN：${map.isbn}</li>
			                    </ul>
			                </div>
			                <div class="xl3"></div>
			                <div class="xl4">
			                    <ul>
			                        <li class="author">读者对象：${map.reader}</li>
			                        <li class="author">版次：${map.revision}</li>
			                        <li class="author">被浏览：${map.clicks}次</li>
			                        <div class="author">评分&nbsp;&nbsp;:</div>
			                        <c:if test="${map.score<=3}">
			                            <div class="star">
			                                <span class="rwtx1"></span>
			                                <span class="rwtx2"></span>
			                                <span class="rwtx2"></span>
			                                <span class="rwtx2"></span>
			                                <span class="rwtx2"></span>
			                            </div>
			                        </c:if>
			                        <c:if test="${map.score<=5 and map.score>3}">
			                            <div class="star">
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx2"></span>
			                                <span class="rwtx2"></span>
			                                <span class="rwtx2"></span>
			                            </div>
			                        </c:if>
			                        <c:if test="${map.score<=7 and map.score>5}">
			                            <div class="star">
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx2"></span>
			                                <span class="rwtx2"></span>
			                            </div>
			                        </c:if>
			                        <c:if test="${map.score<=9 and map.score>7}">
			                            <div class="star">
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx2"></span>
			                            </div>
			                        </c:if>
			                        <c:if test="${map.score>9}">
			                            <div class="star">
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                                <span class="rwtx1"></span>
			                            </div>
			                        </c:if>
			                        <div class="eight">${map.score}</div>
			                        <!-- <div class="zero">0</div> -->
			                    </ul>
			                </div>
			            </div>	
			            <div style="width: 100%;height: auto;margin-top: 36px">
			            	<div class="top">
										<div class="pic"><img style="width: 32px;height: 32px" src="${ctx}/statics/image/default_image.png"></div>
										<div class="nameAndTime">
											<div class="name">${map.userName}</div>
											<div class="commentStar">
												<c:if test="${map.score>=9}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${map.score>=7&&map.score<9}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${map.score>=5&&map.score<7}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${map.score>=3&&map.score<5}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${map.score>=1&&map.score<3}">
													<div class="oneStar">
													</div>
												</c:if>
											</div>
											<div class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${map.createTime}" /></div>
										</div>
									</div>
			            </div>		
			            <div  class="contentDiv">
			            	<p class="Bigcontent">${map.content}</p>
			            </div>
					</c:otherwise>
				</c:choose>
				
			</div>
			<div class="bigRight">
				<div class="right_1">
	                <div>
	                    <span id="ptts"></span>
	                    <span style="font-size: 16px;color: #000000;margin-left: 5px;"><B>配套图书</B></span>
	                </div>
	                <div style="margin-top: 20px;">
	                    <div style="float: left;width: 90px;height: 116px">
	                        <img src="<%-- ${supMap.image_url} --%>${ctx}/statics/image/ts_06.png" class="righttopbook"/>
	                    </div>
	                    <div style="float: left;width: 170px;margin-left: 10px;">
	                        <div class="ptts_sp1">${supMap.book_name}麻醉解剖学学习指导与习题集（第3版）</div>
	                        <div class="watch" onclick="todetail('<%-- ${supMap.id} --%>168')">
	                            <div class="lookbook">去看看</div>
	                        </div>
	                        <div class="ptts_sp2">${supMap.realname}张励才</div>
	                        <div class="ptts_sp3">${supMap.detail}全国高等学校麻醉学专业第四轮规划教材配套教材，根据麻醉...</div>
	                    </div>
	                </div>
	            </div>
	            
	           <%--  <div class="right_3">
	                <div class="right_4">
	                    <div class="right_5">
	                        <div class="right_6a"></div>
	                        <div class="right_7">
	                            <span id="span_3">相关推荐</span>
	                        </div>
	                    </div>
	                    <div class="right_8">
	                        <img src="../statics/image/refresh.png" style="float:left;margin-left:80px">
	                        <div class="refresh" onclick='fresh("9")'>换一批</div>
	                    </div>
	                </div>
	                <div id="change">
	                    <c:forEach items="${auList}" var="list">
	                        <div class="right_9" onclick="todetail('${list.id}')">
	                            <div class="right_10">
	                                <img src="${list.image_url}" class="right_12">
	                            </div>
	                            <div class="right_11">${list.bookname}</div>
	                        </div>
	                    </c:forEach>
	                    <c:forEach items="${tMaps}" var="list">
	                        <div class="right_9" onclick="todetail('${list.id}')">
	                            <div class="right_10">
	                                <img src="${list.image_url}" class="right_12">
	                            </div>
	                            <div class="right_11">${list.bookname}</div>
	                        </div>
	                    </c:forEach>
	                </div>
	            </div> --%>
	            
	            <div class="right_13"></div>
	            <div class="right_14">
	                <div class="right_15">
	                    <div class="right_16">
	                        <div class="right_17"></div>
	                        <div class="right_18">人卫推荐</div>
	                    </div>
	                </div>
	                <div class="right_19">
	                    <div class="picture1"></div>
	                    <div class="refresh1" onclick="change()">&nbsp;换一批</div>
	                </div>
	                <div id="comment">
	                    <c:forEach items="${eMap}" var="list">
	                        <div class="right_20">
	                            <div class="right_21" onclick="todetail('${list.id}')">${list.bookname}</div>
	                            <div class="right_22">（${list.author}）</div>
	                        </div>
	                    </c:forEach>
	                </div>
	            </div>
            
			</div>
		</div>
	</div>
<div style="clear:both;"></div>
<div style="background-color: #f8f8f8">
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
<script>
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

</script>

</body>
</html>