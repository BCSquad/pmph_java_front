<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
        var contextpath2 = '${pageContext.request.contextPath}';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>临床决策专家申报审核</title>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}?t=${_timestamp}"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/authadmin/applydocaudit/expertationListAudit.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">


</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="audit"></jsp:param>
</jsp:include>
<div class="content-wrapper">
    临床决策专家申报审核
    <hr style="border: 0.5px dashed">
    <div class="message">
        <div class="sousuokuang">
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">姓名:</div>
            <input type="text" value="${username}" placeholder='请输入' id="ssk" name="username" >
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">产品名称:</div>
            <div style="display: inline-block;float:left;padding-right: 20px;">
                <select class="search-condition" id="product_id">
                    <option value="">全部</option>
                    <option value="1">人卫临床助手</option>
                    <option value="2">人卫用药助手</option>
                    <option value="3">人卫中医助手</option>

                <%--<c:forEach var="obj" items="${productIdList}" varStatus="i">
                        <c:if test="${obj.product_type=='1'||obj.product_type=='2'||obj.product_type=='3'}">
                            <option value="${obj.id}" ${obj.id==product_id ? 'selected':''}>${obj.product_name}</option>
                        </c:if>
                    </c:forEach>--%>
                </select>
            </div>
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">状态:</div>
            <div style="display: inline-block;float:left;padding-right: 20px;">
                <select class="search-condition" id="online_progress">
                    <option value="">全部</option>
                    <option value="00" <c:if test="${'00'==online_progress}">selected</c:if>>未提交</option>
                    <option value="01" <c:if test="${'01'==online_progress}">selected</c:if>>未审核</option>
                    <option value="02" <c:if test="${'02'==online_progress}">selected</c:if>>已退回</option>
                    <option value="03" <c:if test="${'03'==online_progress}">selected</c:if>>已审核</option>
                    <%--<option value="4">未审核</option>--%>
                   <%-- <option value="5">已退回</option>--%>
                </select>
            </div>
            <input type="button"  value="清空" id="cclear" onclick="cclear()">
            <input type="button" value="查询" id="cxan" onclick="query()">
            <input type="button" value="返回" id="backparent" onclick="backparent()">
        </div>
        <table class="table">
            <thead>
            <tr>
                <td>序号</td>
                <td >姓名</td>
                <td>产品名称</td>
                <td>职务</td>
                <td>职称</td>
                <td>学校审核</td>
                <td>打印状态</td>
            </tr>
            </thead>
            <tbody id='tb'>
            <c:forEach items="${page.rows }" var="item" varStatus="vs">
                <tr>
                    <c:choose>
                        <c:when test="${(vs.index+1)%10 == 1}">
                            <td>${vs.count}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${vs.count}</td>
                        </c:otherwise>
                    </c:choose>
                    <td class="font_name" onclick="toName(${item.product_id },${item.id },${item.online_progress })">${item.realname } </td>
                    <td>${item.product_name } </td>
                    <td>${item.position } </td>
                    <td>${item.title } </td>
                    <td>
                        <c:if test="${item.online_progress =='0'}">未审核</c:if>
                        <c:if test="${item.online_progress =='1'}">未审核</c:if>
                        <c:if test="${item.online_progress =='2'}">已退回</c:if>
                        <c:if test="${item.online_progress =='3'}">已通过</c:if>
                        <c:if test="${item.online_progress =='4'}">出版社退回给单位</c:if>
                        <c:if test="${item.online_progress =='5'}">出版社退回给个人</c:if>
                    </td>
                    <td>${item.isprint } </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="fenyelan">
            <ul class="pagination" id="page1">
            </ul>
            <div style="display: inline-block; vertical-align: top">
                <select id="pages" name="pages">
                    <option value="10"  ${pageSize=='10'?'selected':'' }>每页10条</option>
                    <option value="20"  ${pageSize=='20'?'selected':'' }>每页20条</option>
                    <option value="50"  ${pageSize=='50'?'selected':'' }>每页50条</option>
                </select>
            </div>
            <div class="pageJump">
                <span>共<span id="totoal_count">${page.pageTotal}</span>页</span>
                <span>跳转到</span>
                <input type="text"/>
                <span>页</span>
                <button type="button" class="button">确定</button>
            </div>
        </div>
        <div class="clear"></div>

    </div>
</div>
</div>
<script type="text/javascript">


    function formatDate(nS, str) {
        if (!nS) {
            return "";
        }
        var date = new Date(nS);
        var year = date.getFullYear();
        var mon = date.getMonth() + 1;
        var day = date.getDate();
        var hours = date.getHours();
        var minu = date.getMinutes();
        var sec = date.getSeconds();
        if (str == 'yyyy-MM-dd') {
            return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day);
        } else if (str == 'yyyy.MM.dd') {
            return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day);
        } else if (str == 'yyyy.MM.dd hh:ss:mm') {
            return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
        } else {
            return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
        }

    }

    //点击查询
    function query() {
        var url = contextpath + 'expertationList/toPageList.action?username=' + encodeURI(encodeURI($("#ssk").val()))+'&product_id='+$("#product_id").val()+'&online_progress='+$("#online_progress").val();
        window.location.href = url;
    }
    var pageSize = $("#pages").val();
    Page({
        num: parseInt('${page.pageTotal}'),					//页码数
        startnum: parseInt('${pageNumber}'),				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (pageNumber) {	//回调函数
            pageFun(pageSize, pageNumber);
        }
    });
    //初始化页面
    $(function () {
        $('#pages').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function () {
                var pageSize = $('input[name=pages]').val();
                pageFun(pageSize, '${pageNumber}');
            }
        });
        $('#ssk').keyup(function (event) {
            if (event.keyCode == 13) {//回车键弹起事件
                query();
            }
        });
        $(window).scroll(function () {
            var top = $(window).scrollTop() + 200;
            var left = $(window).scrollLeft() + 605;
            $("#box").css({left: left + "px", top: top + "px"});
        });
    });
    //分页
    function pageFun(pageSize, pageNumber) {
        window.location.href = contextpath + 'expertationList/toPageList.action?pageSize=' + pageSize + '&pageNumber=' + pageNumber + "&username=" + encodeURI(encodeURI($("#ssk").val()))+'&product_id='+$("#product_id").val()+'&online_progress='+$("#online_progress").val();
    }

    //清空条件重查询
    function cclear(){
        setTimeout(function(){
            $("#ssk").val("");
            $("#product_id").val("");
            $("#online_progress").val("");
        },100);

    }

    //点击名字跳转
    function toName(product_id,expertation_id,online_progress) {
        window.location.href = contextpath +'expertation/showExpertation.action?product_id='+product_id+'&declaration_id='+expertation_id+'&state=audit&userType=org&online_progress='+online_progress;
            }
    //返回
   function backparent() {
       var url = contextpath + 'applyDocAudit/toPage.action';
       window.location.href = url;
   }


</script>
<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
    <jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
</body>
</html>