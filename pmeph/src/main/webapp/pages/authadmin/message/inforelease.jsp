<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
    <script type="text/javascript">
        var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>消息列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/message/inforelease.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
     <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
     <script src="${ctx}/resources/authadmin/message/inforelease.js?t=${_timestamp}"></script>
     <script src="${ctx}/resources/comm/layer/layer.js?t=${_timestamp}"></script>

</head>
<body >

	<!-- 隐藏域 -->
	
	<input type="hidden" id="search-name-temp" value="">
	<input type="hidden" id="page-num-temp" value="1">
	
<div style="width: 100%;padding: 0;margin: 0;height: 110px;border: none;overflow: hidden;">
	<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include> 
</div>
<div class="body" >
    <div class="content-wrapper" >
    		 <div class="box">
            <div class="boxsm">
                <div class="item1">
                    <span  class="title2" >选择教材</span>
                    <select   class="st_2"  id="select-search-condition" >
                    <%--<option value="">请选择</option> --%>
                    	<c:forEach items="${listMenu}" var="item">
								<option value="${item.textbook_name}">${item.textbook_name}</option>
							</c:forEach>
                    </select>
                </div>

                <div class="message">
                    <span class="title1" >书籍列表</span>
                    <table class="table">
                        <thead>
                        <tr>
                            <td style="text-align: center;"><input  type="checkbox" class="check_box" name="allChecked" id="allChecked" onclick="DoCheck()" value="0"/></td>
                            <td>书序</td>
                            <td>书籍名称</td>
                            <td>版次</td>
                        </tr>
                        </thead>
                        <tbody id="tbody1">
                        
                        </tbody>
                    </table>
                </div>
					 
					 <div class="pagination-wrapper">
						<ul class="pagination" id="page1">
                        </ul>
                        <div style="display: inline-block;    vertical-align: top">
                            <select id="page-size-select" name="page-size-select" onchange="selectPageSize();">
                                <option value="10">每页10条</option>
                                <option value="20">每页20条</option>
                                <option value="50">每页50条</option>
                            </select>
                        </div>
                        <div class="pageJump">
                            <span>共<span ><input type="text" id="totoal_count" value="${totoal_count }"></span>页，跳转到</span>
                            <input type="text"/>
                            <span class="pp">页</span>
                            <button type="button" class="button">确定</button>
                        </div>
						</div>
						
					<div class="bom_btn">
                    <button class="bom_btn_01" id="backupdate">返回编辑</button>
                    <button class="bom_btn_02" id="send2">确定</button>
                </div>
              </div>
            </div>
        </div>
    </div>


<div style="width: 100%;padding: 0;margin: auto;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</div>
</body>
</html>