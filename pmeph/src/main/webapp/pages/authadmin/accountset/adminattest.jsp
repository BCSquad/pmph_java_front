<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/30
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>学校管理员认证</title>
    <script src="${ctx}/resources/comm/jquery/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/base.js" type="text/javascript"></script>
    <script src="${ctx}/resources/authadmin/accountset/adminattest.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/json2.js" type="text/javascript"></script>
    <link href="${ctx}/statics/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/adminattest.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        $(function () {
            $('select').selectlist({
                zIndex: 10,
                width: 284,
                height: 40,
                optionHeight: 40,
                initValue:$("#titlehidden").val()
            });
        })
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
<jsp:include page="/pages/comm/headGreenBackGround.jsp">
    <jsp:param name="pageTitle" value="account"></jsp:param>
</jsp:include>
<div class="body">
    <div class="content-wrapper">
    <div style="height:30px"></div>
    <div class="div-content">
        <div id="div-child">
            <span class="span1"></span>
            <span class="div-menu-main">学校管理员认证</span>
            <a href="#" class="return-menu-main"
                onclick="window.location='${ctx}/admininfocontroller/toadmininfo.action'"><< 返回个人资料</a>
        </div>
    </div>
    <div style="height:14px"></div>
    <div class="div-content">
        <div><font class="td-title">机构管理员认证说明</font></div>
        <div id="divtop">
            <div>如果你是机构管理员，为了更好保证你和广大平台用户的合法权益，请你认真填写以下信息。</div>
            <div>认证信息审核通过后，你可以：</div>
            <div>1、协助平台管理自己学校的全体教师与学生；</div>
            <div>2、协助平台管理自己学校的教师进行规划教材申报的资料审核；</div>
            <div>3、享有机构管理员权限的其他功能。</div>
        </div>
        <div>
            <table border="0">

                    <input type="hidden" id="id" value="${admininfo.id}"/>
                    <input type="hidden" id="sex" value="${admininfo.sex}"/>
                    <input type="hidden" id="handphone" value="${admininfo.handphone}"/>
                    <input type="hidden" id="postCode" value="${admininfo.postCode}"/>
                    <input type="hidden" id="fax" value="${admininfo.fax}"/>
                    <tr>
                        <td colspan="3" align="center"><img alt="" src="${ctx}/statics/image/_cupline.jpg"/></td>
                    </tr>
                    <tr>
                        <td colspan="3"><font class="td-title">机构管理员信息登记（<font color="#fd9a2e">未审核</font>）</font></td>
                    </tr>
                    <tr>
                        <td align="right" style="width:110px;"><font class="td-font-1">*学校名称</font></td>
                        <td style="width:280px;"><input class="txt" type="text" value="${admininfo.org_name}" disabled/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*管理员姓名</font></td>
                        <td><input class="txt" type="text" id="realName" required value="${admininfo.realname}"/></td>
                        <td><font class="td-font-3">请填写与上次委托书上一致的管理员真实姓名</font></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*邮箱地址</font></td>
                        <td><input class="txt" type="text" id="email" value="${admininfo.email}"/></td>
                        <td><font class="td-font-3">请填写正确的邮件地址，我们有些资料有可能会通过邮箱发送给您</font></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*联系电话</font></td>
                        <td><input class="txt" type="text" id="telephone" value="${admininfo.telephone}"/></td>
                        <td><font class="td-font-3">请填写正确的电话，以使我们能及时联系到您</font></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*上传委托书</font></td>
                        <td>
                            &nbsp;&nbsp;<img alt="" src="${ctx}/statics/testfile/_al.jpg"/>&nbsp;
                            <font class="a-td">委托书.jpg</font>&nbsp;
                            <font class="td-font-2">
                                <a href="#"><font class="a-td">重新上传</font></a>
                                <%--<form action="${ctx}/admininfocontroller/uploadProxy.action" method="post" enctype="multipart/form-data">
                                    <a href="#" onclick="$('#submitfile').click();"><font class="a-td">重新上传</font></a>
                                    <input type="file" id="submitfile" name="submitfile" style="display: none;"/>
                                </form>--%>
                            </font>
                        </td>
                        <td>
                            <img alt="" src="${ctx}/statics/testfile/a01.png"/>&nbsp;
                            <a href="${ctx}/statics/files/weituoshu.doc">
                                <font class="a-td">
                                    委托书模板下载
                                </font>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2"><font class="td-font-3">请下载委托书模板，按要求填写后打印盖章，并上传bmp，jpg，jif，jpge，png的照片或者扫描件</font></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*职务</font></td>
                        <td><input class="txt" type="text" id="position" value="${admininfo.position}"/></td>
                        <td><font class="td-font-3">请填写您的职务名称</font></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*职称</font></td>
                        <input type="hidden" id="titlehidden" name="titlehidden" value="${admininfo.title}">
                        <td><select class="select-td" id="title" name="title">
                            <option value="teacher1">教师1</option>
                            <option value="teacher2">教师2</option>
                            <option value="teacher3">教师3</option>
                            <option value="teacher4">教师4</option>
                            <option value="teacher5">教师5</option>
                        </select>
                        </td>
                        <td><font class="td-font-3">请选择您的职称名称</font></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font-1">*地址</font></td>
                        <td colspan="2"><input class="txt" type="text" id="address" value="${admininfo.address}"/></td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center"><input type="button" class="btn-2" value="提交" onclick="submit()"/></td>
                    </tr>
            </table>
        </div>
    </div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
