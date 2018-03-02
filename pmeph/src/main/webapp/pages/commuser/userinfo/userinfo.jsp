﻿<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}" />
    <title>个人资料</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/statics/commuser/userinfo/userinfo.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.calendar.js"></script>
    <script src="${ctx}/resources/commuser/userinfo/userinfo.js" type="text/javascript"></script>
    <script type="text/javascript">
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
            $(function() {
                $('select').selectlist({
                    zIndex: 10,
                    width: 280,
                    height: 40,
                    optionHeight: 40
                });
            });
          
    </script>
</head>
<body>
<input type="hidden" id="fileid" >
<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="homepage" name="pageTitle" />
	</jsp:include>
	<div class="body" style="background: #f6f6f6;">
		<div class="content-wrapper" >
			<div style="height: 30px"></div>
			<input type="hidden" id="id" value="${map.id}">

    <div class="sxy-div-content">
        <div style="height:50px;">
            <span style="width:20px;"></span>
            <span class="sxy-div-menu">个人资料</span>
        </div>
    </div>
    <div style="height:14px"></div>
    <div class="sxy-div-content">
        <div>
            <div id="sxy-userinfo-div">
                <c:if test="${map.avatar=='DEFAULT'}"><img id="sxy-img1" src="${ctx}/statics/image/putongyhtouxiang.png"/></c:if>
                <c:if test="${map.avatar!='DEFAULT'}"><img id="sxy-img1" src="${ctx}/image/${map.avatar}.action" ></c:if>
                
                <div class="shade" id="uploadFile" avatar="${map.avatar}">修改头像</div>
            </div>
            <div style="height:35px;"></div>
            <div style="height:120px;">
                <div class="sxy-userinfo-row"></div>
                <div class="sxy-userinfo-row"></div>
                <div class="sxy-userinfo-row"></div>
                <div class="sxy-userinfo-row"></div>
                <div class="sxy-userinfo-row"></div>

                <div class="sxy-userinfo-row">
                	<a id="sxy-font-1" style="cursor: pointer;" onclick="window.location='${ctx}/teacherCertification/showTeacherCertification.action'">教师认证</a>
                </div>
                <div class="sxy-userinfo-row">
                	<a id="sxy-font-2" style="cursor: pointer;" onclick="window.location='${ctx}/userinfo/comchangepwd.action'">修改密码</a>
                </div>
            </div>
            <div style="height:35px;"></div>
        </div>
           <span class="sxy-font-3" style="margin-left: 20px"><B>基本信息</B></span>
        <form id="orgForm">
        <table border="0" class="form-table">
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label class="require">真实姓名</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty"  data-error="真实姓名不能为空"  onblur="javascript:LengthLimit(this,20);"
                           placeholder="填写真实姓名" type="text" id="realname" name="realname"  value="${map.realname }"/>
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label class="require">教龄</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty||onlyInt||minLength:3"  data-error="教龄不能为空||教龄只能为整数||教龄不能大于3位"
                           placeholder="填写教龄" type="text" id="experience" name="experience" value="${map.experience }" 
                           />
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label class="require">传真</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty"  data-error="传真不能为空"  onblur="javascript:LengthLimit(this,50);"
                           placeholder="填写传真" type="text" id="fax" name="fax" value="${map.fax }"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td >
                  <div  style="width: 400px;padding:0px;">
                  <label class="require" >性别</label>
                  <div class="input-wrapper" style="margin-top:10px;">
                    <input type="hidden" value="${map.sex }" id="sex_hidden">
                    <input type="radio" value="1" class="sxy-radio" name="radio-set" id="sex1"/>男&nbsp;&nbsp;
                    <input type="radio" value="2" class="sxy-radio" name="radio-set" id="sex2"/>女
                  </div>
                  </div>
                </td>
                <td >
                  <div style="width: 400px;padding:0px;margin-top:-25px;">
                    <label class="require" >职称</label>
                    <select class="sxy-select-td" id="title" name="title" >
                    	<option value="" >-请选择-</option>
                        <option value="教授" ${map.title=='教授' ?'selected':''}>教授</option>
                        <option value="讲师" ${map.title=='讲师' ?'selected':''}>讲师</option>
                        <option value="副教授" ${map.title=='副教授' ?'selected':''}>副教授</option>
                        <option value="院士" ${map.title=='院士' ?'selected':''}>院士</option>
                    </select>
                  </div>
                </td>
                <td >
                  <div  style="width: 400px">
                       <label class="require">手机</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty||isMobile"  
                          data-error="手机号码不能为空||请填写正确的手机号码" placeholder="手机" type="text" id="handphone" 
                          name="handphone" value="${map.handphone}"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label class="require">出生年月</label>
                       <div class="input-wrapper">
                        <input type="text" id="birthday" name="birthday" class="sxy-txt" calendar format="'yyyy-mm-dd'" z-index="100"
                         onselected="(function(view, date, value){this.value=date.toLocaleDateString()})" value="${map.birthday }">
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label class="require">邮编</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty||onlyInt"  data-error="邮编不能为空||邮编只能是数字"  onblur="javascript:LengthLimit(this,20);"
                           placeholder="填写邮政编码" type="text" id="postcode" name="postcode" value="${map.postcode }"/>
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label class="require">E-mail</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty||isEmail"  data-error="邮箱地址不能为空||请填写正确的邮箱地址"
                           placeholder="填写邮箱地址" type="text" id="email" name="email" value="${map.email }"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label class="require">工作单位</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty"  data-error="工作单位不能为空" onblur="javascript:LengthLimit(this,36);"
                           placeholder="填写工作单位" type="text" id="workplace" name="workplace" value="${map.workplace }"/>
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label class="require">联系电话</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty"  data-error="联系电话不能为空"  onblur="javascript:LengthLimit(this,26);"
                           placeholder="填写联系电话" type="text" id="telephone" name="telephone" value="${map.telephone }"/>
                       </div>
                  </div>
                </td>
                <td>
                <div  style="width: 400px;padding:0px;margin-top:-25px;">
                    <label >所属机构</label>
	                <div class="input-wrapper">
                          <input class="sxy-txt " data-valid="isNonEmpty" disabled="disabled"  type="text" id="org_id" name="org_id" value="${map.org_name }"/>
                      </div> 
                </div>
                </td>
            </tr>
            <tr class="sxy-tr">
               <td>
                  <div  style="width: 400px">
                       <label class="require">职务</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt required" data-valid="isNonEmpty"  data-error="职务不能为空"  onblur="javascript:LengthLimit(this,30);"
                           placeholder="填写职务" type="text" id="position" name="position" value="${map.position }"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label class="require">地址</label>
                       <div class="input-wrapper">
                          <input type="text" class="required" data-valid="isNonEmpty" data-error="地址不能为空"  onblur="javascript:LengthLimit(this,50);"
                            placeholder="填写地址"   id="address" name="address" style="width: 1040px" value="${map.address }">
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td colspan="3">
                  <div style="width: 400px">
                      <label class="require">个人简介</label>
                      <div class="input-wrapper">
                          <input type="text" class="required" data-valid="isNonEmpty" data-error="个人简介不能为空" onblur="javascript:LengthLimit(this,100);"
                            placeholder="填写个人简介"  id="note" name="note" style="width: 1040px" value="${map.profile }">
                      </div>
                  </div>
                </td>
            </tr>
            <tr  class="sxy-tr">
                <td colspan="3">
                   <div style="width: 400px">
                       <span class="sxy_span">个性签名</span>
                       <div class="input-wrapper" style="margin-left: 20px">
                           <input type="text" placeholder="填写个人签名" id="signature"  onblur="javascript:LengthLimit(this,50);"
                           name="signature" style="width: 1040px" value="${map.signature }">
                       </div>
                   </div>
                </td>
            </tr>
            <tr  class="sxy-tr" style="height: 0px">
                <td colspan="7"  style="height: 0px">
                    <div style="width: 1200px;">
                        <span class="span_sign" style="margin-top:5px">我的标签</span>
                        <input type="hidden" id="hastag" value="${map.hastag }"/>
                        
                        <div id="tags"> 
                            <c:forEach items="${fn:split(map.tag,'%') }" var="yt">
                             <c:if test="${yt !=null }">
                                  <div class="sxy-bottom-div" style="margin-left: 17px;margin-top:5px"><span class="sxy-bottom-font" style="float:left">${yt }</span><span class="deltag" onclick="deltag(this.parentElement)"> </span></div>
                             </c:if>
                             </c:forEach>
                         </div>
                        <input type="text" placeholder="请输入标签名，10字以内" style="height:28px;margin-left: 20px;margin-top:5px" id="mytag" onkeyup="LengthLimit(this,20)"/>
                        <span style="margin-top:5px;height: 30px;line-height: 30px;display: inline-block;border: 1px solid #f1f1f1;border-radius: 5px;cursor: pointer;" onclick="addtag()"><font class="sxy-bottom-font">添加</font></span>
                   </div>
               </td>
            </tr>
            <tr class="sxy-tr">
                <td colspan="9" align="center">
                     <button type="button" class="div1" onclick="save()">保存</button>
                     <button type="button" class="div1" onclick="cancel()">取消</button>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td colspan="9"></td>
            </tr>
        </table>
        </form>
    </div>
			
		</div>
		<div style="height: 60px; width: 100%;"></div>
	</div>
	<div style="clear: both"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</html>