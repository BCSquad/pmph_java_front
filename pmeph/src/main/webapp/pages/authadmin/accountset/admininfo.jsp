<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/29
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>个人资料修改(机构用户)</title>
    <script src="${ctx}/resources/comm/jquery/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/base.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/json2.js" type="text/javascript"></script>
    <script src="${ctx}/resources/authadmin/accountset/admininfo.js" type="text/javascript"></script>
    <link href="${ctx}/statics/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/admininfo.css" rel="stylesheet" type="text/css"/>
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
            $('form').validate({
                onFocus: function () {
                    this.removeClass("input-error");
                    return false;
                },
                onBlur: function () {
                    var $parent = this.parent();
                    var _status = parseInt(this.attr('data-status'));
                    if (!_status) {
                        this.addClass("input-error");
                    }
                    return false;
                },
                submitHandler:function(){
                    saveobject();

                }
            });
            function saveobject() {
                alert(123);
            }
           /* $('form').on('submit', function (event) {
                alert(111);
                //save();
               /!* event.preventDefault();
                $(this).validate('submitValidate'); //return boolean;*!/
            });*/
        })
    </script>
    <style type="text/css">
        .select-wrapper {
            background: #f1f1f1;
        }

        .select-button {
            background: #f1f1f1;
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
                <span class="div-menu-main">个人资料</span>
            </div>
        </div>
        <div style="height:14px"></div>
<%--        <div class="div-content" style="height:25px;"></div>--%>
        <div class="div-content">
            <div class="he">
                <img src="${ctx}/statics/image/putongyhtouxiang.png" alt=""/>
                <span class="modify">修改头像</span>
                <div class="links">
                    <a href="#" class="manager"
                       onclick="window.location='${ctx}/admininfocontroller/toadminattest.action'">学校管理员认证</a>
                    <a href="#" class="password"
                        onclick="window.location='${ctx}/admininfocontroller/tochangepwd.action'">修改密码</a>
                </div>
                <div style="clear: both"></div>
            </div>
            <div><font class="td-title">基本信息</font></div>
            <form id="orgForm">
'                <table border="0">
                    <tr>
                        <td valign="top" align="right" width="110">
                            <font class="td-font">
                                <span class="red">*</span>真实姓名
                            </font>
                        </td>
                        <td valign="top">
                            <input class="txt required" data-valid="isNonEmpty" data-error="真实姓名不能为空" type="text" id="realName" value="${admininfo.realname}"/>
                        </td>
                        <td valign="top" align="right">
                            <font class="td-font">职务</font>
                        </td>
                        <td valign="top">
                            <input class="txt" type="text" id="position" value="${admininfo.position}"/>
                        </td>

                        <td valign="top" align="right" width="110">
                            <font class="td-font">联系电话</font>
                        </td>
                        <td valign="top">
                            <input class="txt" type="text" id="telephone" placeholder="填写联系电话" value="${admininfo.telephone}"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="right">
                            <font class="td-font"><span class="red">*</span>性别</font>
                        </td>
                        <td valign="top">
                            <c:choose>
                                <c:when test="${admininfo.sex=='1'}">
                                    <input type="radio" value="1" name="sex" checked/><font class="font-1" color="Black">男</font>
                                    <input type="radio" value="2" name="sex"/><font class="font-1" color="Black">女</font>
                                </c:when>
                                <c:when test="${admininfo.sex=='2'}">
                                    <input type="radio" value="1" name="sex"/><font class="font-1" color="Black">男</font>
                                    <input type="radio" value="2" name="sex" checked/><font class="font-1" color="Black">女</font>
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" value="1" name="sex"/><font class="font-1" color="Black">男</font>
                                    <input type="radio" value="2" name="sex"/><font class="font-1" color="Black">女</font>
                                </c:otherwise>
                            </c:choose>

                        </td>

                        <td align="right" width="110" valign="top">
                            <font class="td-font"><span class="red"></span>职称</font>
                        </td>
                        <td valign="top">
                            <input type="hidden" id="titlehidden" name="titlehidden" value="${admininfo.title}">
                            <select class="select-td" name="title" id="title">
                                <option value="teacher1">教师1</option>
                                <option value="teacher2">教师2</option>
                                <option value="teacher3">教师3</option>
                                <option value="teacher4">教师4</option>
                                <option value="teacher5">教师5</option>
                            </select>
                        </td>
                        <td align="right" valign="top">
                            <font class="td-font"><span class="red"></span>手机</font>
                        </td>
                        <td valign="top">
                            <input class="txt" type="text" id="handphone" value="${admininfo.handphone}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top">
                            <font class="td-font"><span class="red"></span>邮编</font>
                        </td>
                        <td valign="top">
                            <input class="txt" type="text" id="postCode" value="${admininfo.postcode}"/>
                        </td>
                        <td valign="top" align="right">
                            <font class="td-font">E-mail</font>
                        </td>
                        <td valign="top">
                            <input class="txt" type="text" id="email" value="${admininfo.email}"/>
                        </td>
                        <td valign="top" align="right">
                            <font class="td-font">传真</font>
                        </td>
                        <td valign="top">
                            <input class="txt" type="text" id="fax" value="${admininfo.fax}"/>
                        </td>
                    </tr>

                    <tr>
                        <td valign="top" align="right">
                            <font class="td-font">地址</font>
                        </td>
                        <td colspan="5"><input class="txt-L" type="text" id="address" value="${admininfo.address}"/></td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center"><img alt="" width="100%" src="${ctx}/statics/image/_cupline.jpg"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center">
                            <input class="btn-2" type="button" value="保存" onclick="save()"/>
                            <input class="btn-2" type="button" value="提交"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div style="height: 60px;width: 100%;"></div>
</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
