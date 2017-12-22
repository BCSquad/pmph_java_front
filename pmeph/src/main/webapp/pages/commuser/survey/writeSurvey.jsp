<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <title>填写问卷</title>
    <script>
        var contextpath='${pageContext.request.contextPath}/';
    </script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/survey/writeSurvey.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/layer/layer.js"></script>
    <script src="${ctx}/resources/commuser/survey/writeSurvey.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
</head>
<body>
	<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="body" style="background-image: url('../statics/image/ypzj_bg.png');height:auto">
	
		<div class="content-wrapper">
				<div style="height: 60px;width: 100%"></div>
				<div style="width:1200px;height:auto;background-color: white">
					<div style="padding-top: 60px;width: 100%;text-align: center">
						<span style="font-size: x-large;color: #757575;">图书问卷调查</span>
					</div>
					<div style="width: 1200px;height: auto;padding-top: 60px;padding-bottom: 60px">
						<div style="width: 1120px;height: auto;margin-left: 80px">
							<span style="color: #757575;font-size: 16px">此调查旨在了解用户的图书阅读习惯，希望参与者根据实际情况认真填写！</span>
						</div>
						<form id="contentForm">
						<div style="width: 1120px;height:auto;margin-left: 80px;color: #757575;font-size: 16px">
							<c:choose>
								<c:when test="${listSize>0 }">
									<c:forEach items="${listSesult}" var="question" varStatus="code">
										<c:if test="${question.type==1 }">
											<div style="width: 100%;height: auto">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px">
														<c:forEach items="${question.listOptions }" var="option">
															<div style="height: 40px">
														    	<input type="radio" name="radio_${code.index+1}" value="${option.id}"/>
														    	<label>${option.option_content }</label><br/>
													    	</div>
														</c:forEach>
														<input type="hidden" name="radioValues" value="radio_${code.index+1}">
														<input type="hidden" name="questionIds" value="${question.id}">
												</div>
											</div>
										</c:if>
										<c:if test="${question.type==2}">
											<div style="width: 100%;height: auto">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px" id="type">
													<c:forEach items="${question.listOptions }" var="option">
														<div style="height: 40px">
															<input type='checkbox' name='checkbox_${code.index+1}' value="${option.id}"> 
															<label>${option.option_content }</label> 
														</div>
													</c:forEach>
													<input type="hidden" name="checkboxValues" value="checkbox_${code.index+1}">	
													<input type="hidden" name="checkboxQuestionIds" value="${question.id}">
												</div>							
											</div>
										</c:if>
										<c:if test="${question.type==3}">
											<div style="width: 100%;height: auto">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px">
													<input id="${question.id}" name="input_${code.index+1}" style="width: 500px;height:20px; border-style:none;border-bottom: dashed 2px #d8d8d8">
												</div>
												<input type="hidden" name="inputValues" value="input_${code.index+1}">
												<input type="hidden" name="inputQuestionIds" value="${question.id}">
											</div>
										</c:if>
									</c:forEach>
									
								<!-- 	<div style="width: 100%;height: auto">
										<p>Q2 : 学历</p>
										<div id="sexForm" style="padding-left: 22px">
												<div style="height: 40px">
												    <input type="radio"  name="radio_2" value="0"/>
												    <label for="education">高中</label>
											    </div>
											    <div style="height: 40px">
												    <input type="radio"  name="radio_2" value="1"/>
												    <label for="education">大专</label>
											    </div>
											    <div style="height: 40px">
												    <input type="radio"  name="radio_2" value="2"/>
												    <label for="education">大本</label>
											    </div>
											    <input type="hidden" name="radioValues" value="radio_2">
										</div>
									</div> -->
									
									<!-- <div style="width: 100%;height: auto">
										<p>Q4 : 读书媒介</p>
										<div style="padding-left: 22px" id="type">
												<div style="height: 40px">
													<input type='checkbox' id='checkbox1' name='checkbox_2' value="1"> 
													<label for='checkbox1'>纸质书</label> 
												</div>
												<div style="height: 40px">
													<input type='checkbox' id='checkbox2' name='checkbox_2' value="2"> 
													<label for='checkbox2'>电子书</label> 
												</div>
												<div style="height: 40px">
													<input type='checkbox' id='checkbox3' name='checkbox_2' value="3"> 
													<label for='checkbox3'>有声读物</label> 
												</div>
												<input type="hidden" name="checkboxValues" value="checkbox_2">
										</div>							
									</div> -->
									<!-- <div style="width: 100%;height: auto">
										<p>Q5 : 最喜欢的一本书</p>
										<div style="padding-left: 22px">
											<input id="bookName" name="bookName" style="width: 500px;height:20px; border-style:none;border-bottom: dashed 2px #d8d8d8">
										</div>
									</div> -->
								</c:when>
								<c:otherwise>
									<div class="no-more">
					                    <img src="<c:url value='/statics/image/aaa4.png'></c:url>">
					                    <span>木有内容呀~~</span>
				                	</div>
								</c:otherwise>
							</c:choose>
							
						</div>
						</form>
					</div>
					
					<div style="width: 100%;height:50px;">
						<div onclick="submit()" style="cursor:pointer;margin-left:530px;width: 140px;height: 28px;background-color: #6FBCC2;border-radius:4px;padding-top: 4px;">
							<span style="color: white;font-size:18px;margin-left: 52px">完成</span>
						</div>
					</div>
					
				</div>
				<div style="height: 60px;width: 100%"></div>
		</div>
	</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>