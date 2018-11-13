<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>活动列表</title>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/teacherPlatform/activityList.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/reload.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<%--身体--%>
<div class="body" style="background: #F8F8F8">
    <div class="content-wrapper">
        <%--活动列表--%>
        <div class="nav">
            <div class="namehead" >
                <span class="hdlb">活动列表</span>
            </div>
        </div>
        <div id="content">

        </div>
        <div class="nav">
            <div class="namejzgd" id="loadMore">
                <span class="jzgd">加载更多...</span>
            </div>
        </div>
    </div>
</div>
<%--结束--%>
<div style="clear: both"></div>
<input type="hidden" id="state" value="${state}">
<input type="hidden" id="material_id" value="${material_id}">
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
<script>
    //定义全局变量
    var startrow=0;
    var state=$("#state").val();
    var material_id=$("#material_id").val();

    $(function () {
        //查询列表数据
        loadData();

        //加载更多
        $("#loadMore").click(function(){
            loadData();
        });
    });

    function asd(id) {
        window.location.href=contextpath+'teacherPlatform/todetail.action?activity_id='+id;
    }


    function loadData() {
        $.ajax({
            type:'post',
            url:contextpath+'teacherPlatform/sourcelist.action?startrow='+startrow+'&state='+state+'&material_id='+material_id,
            async:false,
            dataType:'json',
            success:function(json){
                $.each(json,function (i,n) {
                    var html=
                        '<div class="collection">\n' +
                        '                <div class="content">\n' +
                        '                    <div class="content-img" onclick="asd('+n.id+')">\n' ;
                                             if(n.cover==null || n.cover == '' ||n.cover=='DEFAULT'){
                                                 html+='<img src='+contextpath +'statics/image/564f34b00cf2b738819e9c35_122x122!.jpg class="autoimg"></div>';
                                             }else{
                                                 html+='<img src='+contextpath +'image/' + n.cover + '.action class="autoimg"></div>';
                                             }
                                            html+='<div class="content-text">\n' +
                        '                        <div class="text">\n' +
                        '                        <span style="cursor: pointer;" onclick="asd('+n.id+')">'+n.activity_name+'</span>\n' +
                        '                        </div>\n' +
                        '                        <div class="message">\n' +
                        '                            <div class="username">'+n.content+'</div>\n' +
                        '                        </div>\n' +
                        '                        <div class="pictureDiv" >\n' +
                        '                            <div class="number2">活动日期：'+n.activity_date+'</div>\n' +
                        '                            <div class="eyePicture" title="点击数"></div>\n' +
                        '                            <div class="number1">'+n.count+'</div>\n' +
                        '                        </div>\n' +
                        '                    </div>\n' +
                        '                </div>\n' +
                        '            </div>';
                    $("#content").append(html);
                });
                startrow=startrow+10;
                if(json.length<10){
                    $("#loadMore").hide();
                }
            }
        });
    }
</script>