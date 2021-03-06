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
    <link href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/hotComment/hotCommentList.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/hotComment/hotCommentList.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
</head>
<style type="text/css">

        #right .select-wrapper {
            border: none;
        }
         #right .select-button {
            background: #f6f6f6;
        }
</style>
<body>


<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="body"> 
		<div class="content-wrapper">
			<div class="bigLeft">
				<div class="navDiv">
					<div class="nav">文章 > 热门书评</div>
				</div>
				<c:choose>
					<c:when test="${listHotComment.total>0}">
						<c:forEach items="${listHotComment.rows}" var="one">
							<div class="oneComment">
								<div style="width: 90px;height: 100%;float:left;">
									<img style="width: 90px;height:auto" src="${one.image_url}"/>
								</div>
								<div style="width: 88%;height: 100%;float:left;">
									<div style="width: 100%;height: 30px;clear: both"><span style="font-size: 16px;font-weight:500">《${one.bookname}》</span></div>
									<div class="top">
										<div class="pic"><img style="width: 24px;height: 24px" src="${ctx}/statics/image/default_image.png"></div>
										<div class="nameAndTime">
											<div class="name">${one.userName}</div>
											<div class="star">
												<c:if test="${one.score>=9}">
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
												<c:if test="${one.score>=7&&one.score<9}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${one.score>=5&&one.score<7}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${one.score>=3&&one.score<5}">
													<div class="oneStar">
													</div>
													<div class="oneStar">
													</div>
												</c:if>
												<c:if test="${one.score>=1&&one.score<3}">
													<div class="oneStar">
													</div>
												</c:if>
											</div>
											<div class="time"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${one.gmt_create}" /></div>
										</div>
									</div>
									
									<div class="bottom">
										<div class="contentDiv">
											${one.content}
				  						</div>
				  						<div class="open"><span style="cursor: pointer" onclick="detail(${one.id})">(展开)</span></div>
									</div> 
								</div>
							</div>
						</c:forEach> 	
					</c:when>
					<c:otherwise>
						<div class="no-more">
		                    <img src="<c:url value='/statics/image/aaa4.png'></c:url>">
		                    <span>木有内容呀~~</span>
		                </div>					
					</c:otherwise>
				</c:choose>
				
				
				<c:if test="${listHotComment.total>2}">
					<div class="pageDiv">
		                <div style="float: right;">
		                    <ul class="pagination" id="page1">
		                    </ul>
		                    <div style="display: inline-block;vertical-align: top">
		                        <select id="edu" name="edu" >
		                            <option value="2" ${listHotComment.pageSize=='2' ?'selected':''}>每页2条</option>
		                            <option value="3" ${listHotComment.pageSize=='3' ?'selected':''}>每页3条</option>
		                            <option value="4" ${listHotComment.pageSize=='4' ?'selected':''}>每页4条</option>
		                        </select>
		                    </div>
		                    <div class="pageJump">
		                        <span>共${listHotComment.pageTotal}页，跳转到</span>
		                        <input type="text" id="toPage"/>
		                        <span class="pp">页</span>
		                        <button type="button" class="button">确定</button>
		                    </div>
		                </div>
		            </div>
				</c:if>
				
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
    Page({
    	num: parseInt("${listHotComment.pageTotal}"),					//页码数
        startnum: parseInt("${listHotComment.pageNumber}"),				//指定页码
        elem: $('#page1'),															//指定的元素
        callback: function (n) {													//回调函数
            console.log(n);
            window.location.href="${ctx}/hotComment/hotCommentList.action?"; 
        }
    });
    $(function () {
        $('#edu').selectlist({
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange:function (){
            	window.location.href="${ctx}/hotComment/hotCommentList.action?pageSize="+$("input[name='edu']").val();
            }
            
        });
    })
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
    
    //展开
    function detail(id){
    	window.location.href="${ctx}/hotComment/hotCommentDetail.action?id="+id;
    }
</script>
</body>
</html>