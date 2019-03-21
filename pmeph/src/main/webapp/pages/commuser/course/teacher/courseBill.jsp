<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String contextpath=request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script>
        var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <title>课程选书详细清单</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/commuser/course/teacher/courseBill.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.calendar.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">

    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.jqprint-0.3.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.calendar.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/course/teacher/courseBill.js?t=${_timestamp}"></script>



</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="content-wrapper" >
    <div class="wrapper">

        <div id="print">
            <div class="course_title">${courseName}-教材选购清单</div>
            <div class="description">请各位同学核对个人信息及购书清单，若有不需要的请以醒目的×划掉√，若有遗漏的请打上相应的√</div>
            <div class="table-area">
                <table class="course_bill">
                    <tr>
                        <th>序号</th>
                        <c:forEach items="${colTitle}" var="col" varStatus="status">
                            <th>${col[0]}</th>
                        </c:forEach>
                    </tr>
                    <tbody id="zebra-table">
                    <c:forEach items="${tbody_list}" var="tr_data" varStatus="status">
                        <tr>
                            <td>
                                    ${status.index+1}
                            </td>
                            <c:forEach items="${tr_data}" var="data">
                                <td>
                                        ${data}
                                </td>
                            </c:forEach>
                        </tr>
                        <%--<tr>${data.studentName}</tr>--%>
                    </c:forEach>


                    </tbody>
                </table>
            </div>
        </div>


        <div class="btn-wrapper">

            <button class="btn print"  onclick="javascript:$('#print').jqprint();">打印</button>
            <button class="btn excel"  onclick="getExcel(${courseId},'${courseName}')">excel</button>

            <button class="btn"  onclick="javascript :history.back(-1)">返回</button>
        </div>
    </div>
</div>


</body>
</html>
