<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath=request.getContextPath();
%>
<html>
<head>
    <title>个人资料</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="<%=path%>/statics/css/base.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/statics/home/read.css" rel="stylesheet" type="text/css" />
    <script src="<%=path%>/resources/comm/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="<%=path%>/statics/js/main/read/read.js" type="text/javascript"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js" type="text/javascript"></script>
    <link href="<%=path%>/statics/css/jquery.selectlist.css" rel="stylesheet" type="text/css" />

    <link href="<%=path%>/statics/authadmin/teacherauth/teacherAttest.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        $(function () {
            $('select').selectlist({
                zIndex: 10,
                width: 280,
                height: 40,
                optionHeight: 40
            });
        })

        $(function () {
            $(".sxy-div-menu").bind("click", function () {
                $(".sxy-span-menu").removeClass("sxy-span-menu");
                $(this).addClass("sxy-span-menu");
            });
        });

        $(function () {
            $("#span-menu1").click(function () {

            });
        });
        $(function () {
            $("#span-menu2").click(function () {

            });
        });
        $(function () {
            $("#span-menu3").click(function () {

            });
        });
    </script>
    <style type="text/css">
        .select-wrapper{
            background:#f1f1f1 ;
        }
        .select-button{
            background:#f1f1f1 ;
            padding-left: 20px;
        }
    </style>
</head>
<body>
	<div style="width: 100%;padding: 0;margin: 0;height: 81px;border: none">
		<jsp:include page="/pages/comm/head.jsp"></jsp:include> 
	</div>
    <div style="height:30px"></div>
    <div class="sxy-div-content">
        <div style="height:50px;">
            <span style="width:20px;"></span>
            <span class="sxy-div-menu">学校教师认证</span>
            <span id="sxy-spantopright">〈〈返回个人资料&nbsp&nbsp</span>
        </div>
    </div>
    <div style="height:14px"></div>
    <div class="sxy-div-content">
        <div style="height:15px"></div>
        <div><font id="sxy-td-title">机构管理员认证说明</font></div>
        <div id="sxy-divtop">
            <div>如果你是学校教师，为了更好的保障你和广大平台用户的合法权益，请你认真填写以下认证信息。</div>
            <div>认证信息审核通过后，你可以在线进行规划教材申报</div>
        </div>

         <div>
            <table border="0" style="margin-left:20px;">
                <tr>
                    <td colspan="3" align="center"><img alt="" src="<%=path%>/statics/image/_cupline.jpg"/></td>
                </tr>
                <tr  class="sxy-tr">
                    <td colspan="3"><font class="td-title">学校教师信息登记（<font color="#fd9a2e">未审核</font>）</font></td>
                </tr>
                <tr  class="sxy-tr">
                    <td align="right" style="width:100px;"><font class="td-font-1">*姓名</font></td>
                    <td style="width:280px;"><input class="sxy-txt" type="text"/></td>
                    <td><font class="td-font-3">请填写与与教师资格证上一致的真实姓名</font></td>
                </tr>
                <tr  class="sxy-tr">
                    <td align="right"><font class="td-font-1">*身份证号</font></td>
                    <td><input class="sxy-txt" type="text"/></td>
                    <td><font class="td-font-3">请填正确的身份证信息</font></td>
                </tr>
                <tr  class="sxy-tr">
                    <td align="right"><font class="td-font-1">*选择学校</font></td>
                    <td>
                        <select class="sxy-select-td" id="Select1" name="position">
                            <option value="teacher1">华中师范大学</option>
                            <option value="teacher2">北京师范大学</option>
                            <option value="teacher3">湖北师范大学</option>
                        </select>
                    </td>
                    <td><font class="td-font-3">请选择您所在的学校</font></td>
                </tr>
                <tr  class="sxy-tr">
                    <td align="right"><font class="td-font-1">*手机</font></td>
                    <td><input class="sxy-txt" type="text"/></td>
                    <td><font class="td-font-3">请填写正确的手机号码，以使我们能及时联系到您</font></td>
                </tr>
                <tr  class="sxy-tr">
                    <td align="right"><font class="td-font-1">*教师资格证</font></td>
                    <td><input class="sxy-txt" type="text"/></td>                    
                    <td><input id="sxy-btn-upload" type="button" value="上传文件"/></td>
                </tr>               
                <tr  class="sxy-tr">
                    <td></td>
                    <td colspan="2"><font class="td-font-last">请上传bmp、jpg、gif、jpge、png格式的照片或扫描件</font></td>
                </tr>
                <tr><td colspan="3" align="center"><img alt="" src="../../image/_cupline.jpg"/></td></tr>
                <tr  class="sxy-tr">
                    <td colspan="3" align="center"><input type="submit" class="btn-2" value="提交"/></td>
                </tr>
            </table>
        </div>
    </div>    

	<div style="background-color: white;width: 100%;padding: 0;margin: 0;height: 220px;border: none;overflow: hidden;">
		<jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
	</div>
</body>
</html>