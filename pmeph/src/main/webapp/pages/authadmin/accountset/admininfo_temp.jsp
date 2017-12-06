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
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>个人资料修改(机构用户)</title>
    <link href="${ctx}/statics/css/base.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/admininfo.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css" rel="stylesheet" type="text/css"/>
    <script src="${ctx}/resources/comm/jquery/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $('select').selectlist({
                zIndex: 10,
                width: 280,
                height: 40,
                optionHeight: 40
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
                }
            });

            $('form').on('submit', function (event) {
                event.preventDefault();
                $(this).validate('submitValidate'); //return boolean;
            });

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
                    <a href="adminAttest.html" class="manager">学校管理员认证</a>
                    <a href="changePwd.html" class="password">修改密码</a>
                </div>
                <div style="clear: both"></div>
            </div>
            <div><font class="td-title">基本信息</font></div>
            <form>
                <table border="0">
                    <tr>
                        <td valign="top" align="right" width="110"><font class="td-font"><span class="red">*</span>真实姓名</font></td>
                        <td valign="top">
                            <input data-valid="isNonEmpty" data-error="真实姓名不能为空"
                                   class="txt required" type="text"/>
                        </td>
                        <td valign="top" align="right"><font class="td-font">职务</font></td>
                        <td valign="top"><input data-valid="isNonEmpty" data-error="职务" class="txt required" type="text"/></td>

                        <td valign="top" align="right" width="110"><font class="td-font">联系电话</font></td>
                        <td valign="top">
                            <input class="txt" type="text" placeholder="填写联系电话"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="top" align="right"><font class="td-font"><span class="red">*</span>性别</font></td>
                        <td valign="top">
                            <input type="radio" value="男" name="radio-set"/><font class="font-1" color="Black">男</font>
                            <input type="radio" value="女" name="radio-set"/><font class="font-1" color="Black">女</font>
                        </td>

                        <td valign="top" align="right" width="110"><font class="td-font"><span class="red"></span>职称</font></td>
                        <td valign="top">
                            <select data-valid="isNonEmpty" data-error="职务" class=" required" id="1" name="position">
                                <option value="teacher1">教师1</option>
                                <option value="teacher2">教师2</option>
                                <option value="teacher3">教师3</option>
                                <option value="teacher4">教师4</option>
                                <option value="teacher5">教师5</option>
                            </select>
                        </td>
                        <td valign="top" align="right"><font class="td-font"><span class="red"></span>手机</font></td>
                        <td valign="top"><input class="txt" type="text"/></td>
                    </tr>
                    <tr>
                        <td align="right"><font class="td-font"><span class="red"></span>邮编</font></td>
                        <td><input class="txt" type="text"/></td>
                        <td align="right"><font class="td-font">E-mail</font></td>
                        <td><input class="txt" type="text"/></td>
                        <td align="right"><font class="td-font">传真</font></td>
                        <td><input class="txt" type="text"/></td>
                    </tr>

                    <tr>
                        <td align="right"><font class="td-font">地址</font></td>
                        <td colspan="5"><input class="txt-L" type="text"/></td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center"><img alt="" width="100%"
                                                            src="${ctx}/statics/image/_cupline.jpg"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="6" align="center">
                            <input class="btn-2" type="submit" value="保存"/>
                            <input class="btn-2" type="submit" value="提交"/>
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
