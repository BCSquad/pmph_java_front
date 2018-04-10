<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript">
       var contxtpath = '${pageContext.request.contextPath}';
       var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>遴选公告列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/focusAndSelect/newsReportAndMaterialNotice.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/focusAndSelect/materialNotice.js?t=${_timestamp}" ></script>
    

    
    <style type="text/css">
        .div_content_right .select-button {
            background: #f6f6f6;
        }

        .div_content_right .select-wrapper {
            border: none;
        }
    </style>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<input type="hidden" id="materialId" value="${materialId }"/>
<div class="content-body">
    <div class="div_top">
    <a href="${ctx }/homepage/tohomepage.action">首页</a>&gt;遴选公告
    </div>
    <div class="div_content">
        <div class="div_content_left">
            <span class="clickbefore mouse-gesture" id="infoReport" 
                  onclick="ChangeDiv('infoReport')">${materialId!=null && materialId!=''? '社区':'信息' }快报</span>
            <span class="clicked"     style="margin-left: 15px;" id="selectAnnco" onclick="ChangeDiv('selectAnnco')">遴选公告</span>
        </div>
        <div class="div_content_right">
            <span style="color: #999999;">排序：</span>
             <div style="display: inline-block;text-align:left;color: #333333;">
                <select id="sort-wrapper" name="sort-wrapper">
				    <option value="0">综合</option>
                	<option value="1">最新</option>
               		 <option value="2">最热</option>
				</select>
             </div>
        </div>
    </div>
    <div style="margin-top:25px;">
        <div class="left" >
        <div id = "content"></div>  
        <div class="load-more clearfix" id="loadMore">加载更多...</div>
         <div class="no-more" style="display:none" id="nomore">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>" style="display: block;margin: 0px auto 0px;">
                    <span style="display: block;width: 100px;margin: 0px auto 0px;">木有内容呀~~</span>
              </div>
        </div>
        <div class="right" style="width: 335px;float: right;">
            <img src="${ctx}/image/${fn:length(adInfo.detailList)>1?adInfo.detailList[1].image:adInfo.detailList[0].image}.action" style="width: 335px;height: 335px;"/>
        </div>
    </div>
</div>
<div style="width: 100%;clear:both;padding: 0;margin: 0;height: 160px;border: none">
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>

</body>
</html>
