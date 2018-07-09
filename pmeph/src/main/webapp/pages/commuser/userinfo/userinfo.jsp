<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
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
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" rel="stylesheet" type="text/css" />
    <link href="${ctx}/statics/commuser/userinfo/userinfo.css?t=${_timestamp}" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.calendar.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/userinfo/userinfo.js?t=${_timestamp}" type="text/javascript"></script>
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
                <div class="sxy-userinfo-row" style="margin-top: 15px">
                    <div id="person_SB" style="cursor: pointer;margin-left: 35px" onclick="testOne()">&nbsp;&nbsp;个人申报信息</div>
                    <div id="sxy-font-1"  onclick="window.location='${ctx}/teacherCertification/showTeacherCertification.action'">教师认证</div>
                    <div id="sxy-font-2" style="cursor: pointer;margin-left: 288px" onclick="window.location='${ctx}/userinfo/comchangepwd.action'">&nbsp;&nbsp;修改密码</div>
                </div>
            </div>
            <div style="height:35px;"></div>
        </div>
           <span class="sxy-font-3" style="margin-left: 20px"><B>基本信息</B></span>
        <table border="0" class="form-table">
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label class="require">真实姓名</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"onblur="javascript:LengthLimit(this,20);"
                           placeholder="真实姓名必填" type="text" id="realname" name="realname"  value="${map.realname }"/>
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label >教龄</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"
                                 onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')"
                                 type="text" id="experience" name="experience" value="${map.experience}"  maxlength="2" />
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label >传真</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"   maxlength="20"  onblur="LengthLimit(this,50)"
                                  type="text" id="fax" name="fax" value="${map.fax }"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td >
                  <div  style="width: 400px;padding:0px;">
                  <label >性别</label>
                  <div class="input-wrapper" style="margin-top:10px;">
                    <input type="hidden" value="${map.sex }" id="sex_hidden" class="sxy-txt" >
                    <input type="radio" value="1" class="sxy-radio" name="radio-set" id="sex1" checked />男&nbsp;&nbsp;
                    <input type="radio" value="2" class="sxy-radio" name="radio-set" id="sex2" />女
                  </div>
                  </div>
                </td>
                <td >
                  <div style="width: 400px;padding:0px;">
                    <label >职称</label>
                    <div class="input-wrapper" >
	                    <input type="hidden" >
	                    <select class="sxy-select-td" id="title" name="title">
	                    	<option value="" >请选择</option>
	                    	<option value="院士" ${map.title=='院士' ?'selected':''}>院士</option>
	                        <option value="教授" ${map.title=='教授' ?'selected':''}>教授</option>
	                        <option value="正高" ${map.title=='正高' ?'selected':''}>正高</option>
	                        <option value="副教授" ${map.title=='副教授' ?'selected':''}>副教授</option>
	                        <option value="副高" ${map.title=='副高' ?'selected':''}>副高</option>
	                        <option value="高级讲师" ${map.title=='高级讲师' ?'selected':''}>高级讲师</option>
	                        <option value="讲师" ${map.title=='讲师' ?'selected':''}>讲师</option>
	                        <option value="其他" ${map.title=='其他' ?'selected':''}>其他</option>
	                    </select>
                    </div>
                  </div>
                </td>
                <td >
                  <div  style="width: 400px">
                       <label class="require">手机</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt" placeholder="手机号码必填" type="text" id="handphone"
                          name="handphone" value="${map.handphone}"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label >出生年月</label>
                       <div class="input-wrapper">
                        <input type="text" id="birthday" name="birthday" class="sxy-txt" calendar format="'yyyy-mm-dd'" z-index="100"
                         onselected="(function(view, date, value){this.value=date.toLocaleDateString()})" value="${map.birthday }">
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label >邮编</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"   maxlength="20"
                                 onkeyup="this.value=this.value.replace(/\D/g,'')" onblur="this.value=this.value.replace(/\D/g,'')"
                           type="text" id="postcode" name="postcode" value="${map.postcode }"/>
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label >E-mail</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"  onkeyup="value=value.replace(/[^\a-\z\A-\Z0-9\@\.]/g,'')"
                            type="text" id="email" name="email" value="${map.email }" maxlength="40"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label class="require">工作单位</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt" onblur="javascript:LengthLimit(this,36);"
                           placeholder="工作单位必填" type="text" id="workplace" name="workplace" value="${map.workplace }"/>
                       </div>
                  </div>
                </td>
                <td>
                  <div  style="width: 400px">
                       <label >联系电话</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"   onblur="javascript:LengthLimit(this,26);"
                           type="text" id="telephone" name="telephone" value="${map.telephone }"/>
                       </div>
                  </div>
                </td>
                <td>
                <div  style="width: 400px;">
                    <label >申报单位</label>
	                <div class="input-wrapper">
                          <input class="sxy-txt "  disabled="disabled"  type="text" id="org_id" name="org_id" value="${map.org_name }"/>
                      </div> 
                </div>
                </td>
            </tr>
            <tr class="sxy-tr">
               <td>
                  <div  style="width: 400px">
                       <label >职务</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"  onblur="javascript:LengthLimit(this,30);"
                            type="text" id="position" name="position" value="${map.position }"/>
                       </div>
                  </div>
                </td>
               <td>
                  <div  style="width: 400px">
                       <label >昵称</label>
                       <div class="input-wrapper">
                          <input class="sxy-txt"  onblur="javascript:LengthLimit(this,30);"
                            type="text" id="nickname" name="nickname" value="${map.nickname }"/>
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td>
                  <div  style="width: 400px">
                       <label >地址</label>
                       <div class="input-wrapper">
                          <input type="text"    onblur="javascript:LengthLimit(this,50);"
                              id="address" name="address" style="width: 1040px" value="${map.address }">
                       </div>
                  </div>
                </td>
            </tr>
            <tr class="sxy-tr">
                <td colspan="3">
                  <div style="width: 400px">
                      <label >个人简介</label>
                      <div class="input-wrapper">
                          <input type="text"   onblur="javascript:LengthLimit(this,100);"
                             id="note" name="note" style="width: 1040px" value="${map.profile }" placeholder="请输入个性签名，最大长度不能超过100汉字">
                      </div>
                  </div>
                </td>
            </tr>
            <tr  class="sxy-tr">
                <td colspan="3">
                   <div style="width: 400px">
                       <span class="sxy_span">个性签名</span>
                       <div class="input-wrapper" style="margin-left: 20px">
                           <input type="text"  id="signature"  onblur="javascript:LengthLimit(this,50);"
                           name="signature" style="width: 1040px" value="${map.signature }" placeholder="请输入个性签名，最大长度不能超过50汉字">
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
                             <c:if test="${yt !=null && yt !='' }">
                                  <div class="sxy-bottom-div" style="margin-left: 17px;margin-top:5px"><span class="sxy-bottom-font" style="float:left">${yt }</span>
                                      <span class="deltag" onclick="deltag(this.parentElement)"> </span>
                                  </div>
                             </c:if>
                             </c:forEach>
                         </div>
                        <input type="text" placeholder="请输入标签名，10字以内" style="height:28px;margin-left: 20px;margin-top:5px" id="mytag" onkeyup="LengthLimit(this,10)"/>
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
    </div>
			
		</div>
		<div style="height: 60px; width: 100%;"></div>
	</div>
	<div style="clear: both"></div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</html>