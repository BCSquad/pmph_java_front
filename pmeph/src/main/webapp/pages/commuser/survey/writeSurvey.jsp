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
				<div class="emptyDiv"></div>
				<div class="whiteDiv">
					<div class="titleDiv">
						<span class="titleFont">${mapSurvey.title}</span>
					</div>
					<div class="bigContent">
						<div class="tipDiv">
							<span class="tipFont">${mapSurvey.intro}</span>
						</div>
						<form id="contentForm">
						<div class="questions">
							<input type="hidden" name="surveyId" value="${surveyId}">
							<c:choose>
								<c:when test="${listSize>0 }">
									<c:forEach items="${listSesult}" var="question" varStatus="code">
										<c:if test="${question.type==1 }">
											<div class="oneQuestion">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px">
														<c:forEach items="${question.listOptions }" var="option">
															<div style="height: 40px">
														    	<c:choose>
																	<c:when test="${(question.answer) ==(option.id)}">
																		<div style="float: left"><input type="radio"  name="radio_${code.index+1}" value="${option.id}" checked="checked"  disabled="disabled"></div>
															    		<div  style="float: left;marging-top: 10px;margin-left: 5px"><label>${option.option_content }</label></div><br/>
																	</c:when>
																	<c:otherwise>
																		<div style="float: left"><input type="radio" name="radio_${code.index+1}" value="${option.id}"/></div>
														    			<div  style="float: left;marging-top: 10px;margin-left: 5px"><label>${option.option_content }</label></div><br/>
																	</c:otherwise>
																</c:choose>
													    	</div>
														</c:forEach>
														<input type="hidden" name="radioValues" value="radio_${code.index+1}">
														<input type="hidden" name="questionIds" value="${question.id}">
												</div>
											</div>
										</c:if>
										<c:if test="${question.type==2}">
											<div class="oneQuestion">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px" id="type">
													<c:forEach items="${question.listOptions }" var="option">
														<div style="height: 40px">
														<c:choose>
																	<c:when test="${(option.flag)==true}">
																		<input type='checkbox'  name='checkbox_${code.index+1}' value="${option.id}" checked="checked"  disabled="disabled"> 
																		<label>${option.option_content }</label>
																	</c:when>
																	<c:otherwise>
																		<input   type='checkbox' name='checkbox_${code.index+1}' value="${option.id}" > 
																		<label>${option.option_content }</label> 
																	</c:otherwise>
																</c:choose>
														</div>
													</c:forEach>
													<input type="hidden" name="checkboxValues" value="checkbox_${code.index+1}">	
													<input type="hidden" name="checkboxQuestionIds" value="${question.id}">
												</div>							
											</div>
										</c:if>
										<c:if test="${question.type==4}">
											<div class="oneQuestion">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px">
													<input id="${question.id}" name="input_${code.index+1}" class="inputStyle"  maxlength="190"  value="${question.inp }"  >
												</div>
												<input type="hidden" name="inputValues" value="input_${code.index+1}">
												<input type="hidden" name="inputQuestionIds" value="${question.id}">
											</div>
										</c:if>
										<c:if test="${question.type==5}">
											<div class="oneQuestion">
												<p>Q${code.index+1} : ${question.title}</p>
												<div style="padding-left: 22px">
													<textarea id="${question.id}" name="input_${code.index+1}" class="textAreaStyle" maxlength="190" >${question.inp }</textarea>
												</div>
												<input type="hidden" name="textValues" value="input_${code.index+1}">
												<input type="hidden" name="textQuestionIds" value="${question.id}">
											</div>
										</c:if>
									</c:forEach>
									
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
					<c:choose>
						<c:when test="${((btn.sid) !=surveyId) && ((btn.uid) !=logUserId)}">
							<div style="width: 100%;height:50px;">
								<div onclick="submit1()" class="buttonDiv">
									<span class="submitFont">完成</span>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div style="width: 100%;height:50px;">
								<div onclick="toList()" class="buttonDiv">
									<span class="submitFont">返回</span>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="emptyDiv"></div>
		</div>
	</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>