<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
 <%
String path = request.getContextPath();
String basePath =path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
		var pathName=window.document.location.pathname;  
		var contxtpath="${pageContext.request.contextPath }";
		var contextpath="${pageContext.request.contextPath }/";
</script>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>信息快报详情</title>
 <link rel="stylesheet" href="<%=path %>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path %>/statics/commuser/messagereport/inforeport.css?t=${_timestamp}"/>
    <script src="<%=path %>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path %>/resources/comm/layer/layer.js?t=${_timestamp}"></script>
    <script src="<%=path %>/resources/comm/base.js?t=${_timestamp}"></script>
     <script src="<%=path %>/resources/commuser/messagereport/inforeport.js?t=${_timestamp}"></script>


</head>
<body>
<div style="background-color:White;">
   <jsp:include page="/pages/comm/head.jsp"></jsp:include>
   <input type="hidden" id="count" value="${count }"/>
   <input type="hidden" id="materialId" value="${materialId}"/>
</div>
    <div style="height:30px"></div>
    <div class="sxy-div-content">
        <div id="sxy-div-left">
            <div class="sxy-navigate"><a href="${ctx }/homepage/tohomepage.action">首页</a>〉<a href="${ctx }/cmsinfoletters/tolist.action?materialId=${materialId}">${materialId !=null && materialId !='' ? '社区':'信息'  }快报</a>〉${materialId !=null && materialId !='' ? '社区':'信息'  }快报详情</div>
            <div style="width: 800px;">
                <div>
                    <div id="sxy-title">
                        <div style="float:left">${rmap.title }</div>
                        
                    </div>
                </div>
                <div style="clear: both"></div>
                <div id="sxy-title2">
                	<div id="sxy-like">
                       <span style="float:left;"><img alt="" src="${ctx}/statics/image/${rmap.likecount gt 0 ? 'dz01.png':'dz02.png'}" onclick="addlike('${rmap.id}')" id="like"/></span>
                       <span style="float:right;"><img alt="" src="${ctx }/statics/image/${rmap.markcount gt 0 ? 'sc101(1).png':'s102(1).png' }" onclick="addmark('${rmap.id}')" id="mark"/></span>
                   </div>
                   <span style="float:left">信息快报&emsp;<fmt:formatDate value="${rmap.auth_date}" pattern="yyyy-MM-dd"/></span>
                   <span style="background-image: url(${ctx }/statics/image/css_sprites.png);
                               background-position: -418px -170px;width:20px;height:20px;float:left;
                               margin-left:20px;margin-right:5px"></span>
                    <span style="float:left;color: #1abd44;">${rmap.clicks }</span>
                             
                </div>
            </div>

        <div class="sxy-content" style="width: 800px;">
             ${rmap.cmsText }
        </div>

        </div>
        <div id="sxy-div-right">
            <table border="0" style="width:100%;">
            <thead>
                <tr>
                    <td style="height:65px;line-height:65px;">
                        <div style="float:left"><img alt="" src="${ctx }/statics/image/zuixin.png" />&nbsp;<font id="sxy-font-top1">最新快报</font></div>
                        <div style="float:right;height:65px">
                           <div style="float:left;width:20px;height:20px;margin-top: 23px">
                              <img src="${ctx }/statics/image/refresh.png"/>
                           </div>
                           <div style="float:left;"><span id="sxy-font-top2" onclick="trychange()">换一批</span></div>
                           </div>
                    </td>
                </tr>
              </thead>
              <tbody id="trows">
                <c:forEach items="${list }" var="report">
	                <tr class="sxy-tr"><td><div onclick="lookDetail('${report.id}')" style="width:275px">${report.title }</div></div></td></tr>
	                <tr class="sxy-tr"><td><img alt="" src="${ctx }/statics/image/cupline.jpg" /></td></tr>
                </c:forEach>
              </tbody>
               <!--  <tr><td><input id="sxy-btn" type="button" value="想要阅读更多快报？很戳这里" /></td></tr> -->
            </table>
        </div>
    </div>
    
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>