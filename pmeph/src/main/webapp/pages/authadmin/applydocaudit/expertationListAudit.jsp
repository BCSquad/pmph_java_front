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
    <jsp:param name="pageTitle" value="experAudit"></jsp:param>
</jsp:include>
<div class="content-wrapper">
    临床决策专家申报审核
    <hr style="border: 0.5px dashed">
    <div class="message">
        <div class="sousuokuang">
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">姓名:</div>
            <input type="text" value="${username}" placeholder='请输入' id="ssk" name="username" >
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">产品分类:</div>
            <div style="display: inline-block;float:left;padding-right: 20px;">
                <select class="search-condition" id="expertType">
                    <option value="">全部</option>
                    <option value="1" ${"1"==expertType ? 'selected':''}>人卫临床助手</option>
                    <option value="2" ${"2"==expertType ? 'selected':''}>人卫用药助手</option>
                    <option value="3" ${expertType=='3'? 'selected':'' }>人卫中医助手</option>
                </select>
            </div>
            <div style="float: left;height: 42px;line-height: 42px;padding-right: 20px;color: #4e4e4e;">状态:</div>
            <div style="display: inline-block;float:left;padding-right: 20px;">
                <select class="search-condition" id="xxsh">
                    <option value="">全部</option>
                    <option value="01" <c:if test="${'01'==xxsh}">selected</c:if>>待审核</option>
                    <option value="02" <c:if test="${'02'==xxsh}">selected</c:if>>已退回</option>
                    <option value="03" <c:if test="${'03'==xxsh}">selected</c:if>>已通过</option>
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
                <td>出版社审核</td>
                <td>是否上传附件</td>
                <td>打印状态</td>

                <%--学校审核状态online_progress：0=未提交/1=已提交但是待审核/2=被申报单位退回/3=申报单位通过/4=出版社退回申报单位/5=出版社退回个人
                人卫社是否审核通过pmphAudit： 0-未操作/1-审核通过/2=审核未通过
                finalResult 是否最终结果公布:0-未公布、1-已公布--%>


            <%--学校审核状态：
                待审核（online_progress==1） ，已退回（online_progress==2，4，5），已通过（online_progress==3）
                出版社审核：
                正在遴选((online_progress==1||online_progress==3) && finalResult == 0)、
                出版社退回（online_progress=4 || online_progress=5）、
                遴选结束（finalResult ==1 && pmphAudit!=1）、
                报名成功（finalResult =1&&pmphAudit=1）--%>

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
                    <td>${item.xxsh } </td>
                    <td>${item.cbssh } </td>
                    <td>${item.isUpload }</td>
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
    //点击查询
    function query() {
        var url = contextpath + 'expertationList/toPageList.action?username=' + encodeURI(encodeURI($("#ssk").val()))+'&expertType='+$("#expertType").val()+'&xxsh='+$("#xxsh").val();
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
            zIndex: 100,
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
        window.location.href = contextpath + 'expertationList/toPageList.action?pageSize=' + pageSize + '&pageNumber=' + pageNumber + "&username=" + encodeURI(encodeURI($("#ssk").val()))+'&expertType='+$("#expertType").val()+'&xxsh='+$("#xxsh").val();
    }

    //清空条件重查询
    function cclear(){
        setTimeout(function(){
            $("#ssk").val("");
            $("#expertType").val("");
            $("#xxsh").val("");
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