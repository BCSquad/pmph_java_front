<%@ page import="com.bc.pmpheep.back.util.Const" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.collections.MapUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="org-head">
    <div>
        <%--<div style="width: 100%;background-color:White;">--%>
        <div class="div-content">
            <div id="div-titletop">
                <span class="top-lable1">欢迎访问人教e卫平台！</span>
                <a class="top-lable2" href='<c:url value="/logout.action"/>'>[退出登录]</a>
                <span class="top-lable2">&nbsp</span>
				<%--<span class="top-lable2">退出登录</span>--%>
                <span class="top-lable2">
                    <%
                        Map<String, Object> userInfo = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_ORGUSER);
                        out.println(MapUtils.getString(userInfo, "org_name"));
                    %>
                </span>
                <%--<span class="top-lable2">&nbsp&nbsp&nbsp&nbsp&nbsp</span>
                <span class="top-lable2">下载手机客户端！</span>--%>
            </div>
        </div>
    </div>
    <div class="div-menu">
        <div class="div-content">
            <div style="width:176px;float:left;"><img alt="" src="<c:url value="/statics/image/_logo.jpg"/>"/></div>
            <div style="width:20px;float:left;">&nbsp</div>
            <div class="div-menu-child <%="backlog".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
                 onclick="window.location.href='<c:url value="/schedule/scheduleList.action"/>'">待办事项
            </div>
            <%--<c:choose>
     			<c:when test="${ SESSION_USER_CONST_ORGUSER.progress==1}">--%>
				<div class="div-menu-child <%="audit".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
					 onclick="window.location.href='<c:url value="/applyDocAudit/toPage.action"/>'">教材申报资料审核
				</div>

            <div class="div-menu-child <%="experAudit".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
                 onclick="window.location.href='<c:url value="/expertationList/toPageList.action"/>'">临床决策专家申报审核
            </div>
			<%--</c:when>
				<c:otherwise>
     			<div class="div-menu-child <%="audit".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 onclick="window.location.href='<c:url value="/userinfo/toNoAccessToAuthority.action?pageType=audit"/>'">申报资料审核
	            	</div>    
  				</c:otherwise>
			</c:choose>--%>
            <%--<c:choose>
     			<c:when test="${ SESSION_USER_CONST_ORGUSER.progress==1}">   --%>
	     			 	<div class="div-menu-child <%="teachercert".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 	onclick="window.location.href='<c:url value="/teacherauth/toPage.action"/>'">教师认证
	            		</div>
 				<%--</c:when>
     			<c:otherwise>  
     			<div class="div-menu-child <%="teachercert".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 onclick="window.location.href='<c:url value="/userinfo/toNoAccessToAuthority.action?pageType=teachercert"/>'">教师认证
	            	</div>    
  				</c:otherwise> 
			</c:choose>--%>
			<%--<c:choose>
     			<c:when test="${ SESSION_USER_CONST_ORGUSER.progress==1}"> --%>
	     			 	<div class="div-menu-child <%="message".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 	onclick="window.location.href='<c:url value="/AllMessage/init.action"/>'">消息
	            		</div>
 				<%--</c:when>
     			<c:otherwise>  
     			<div class="div-menu-child <%="message".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 onclick="window.location.href='<c:url value="/userinfo/toNoAccessToAuthority.action?pageType=message"/>'">消息
	            	</div>    
  				</c:otherwise> 
			</c:choose>--%>
           <%--<c:choose>
     			<c:when test="${ SESSION_USER_CONST_ORGUSER.progress==1}">   --%>
	     			 	<div class="div-menu-child <%="usermanage".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 	onclick="window.location.href='<c:url value="/organizationuser/writerLists.action"/>'">用户管理
	            		</div>
 				<%--</c:when>
     			<c:otherwise>  
     			<div class="div-menu-child <%="usermanage".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 onclick="window.location.href='<c:url value="/userinfo/toNoAccessToAuthority.action?pageType=usermanage"/>'">用户管理
	            	</div>    
  				</c:otherwise> 
			</c:choose>--%>
			<%--<c:choose>
     			<c:when test="${ SESSION_USER_CONST_ORGUSER.progress==1}">   --%>
	     			 	<div class="div-menu-child <%="account".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 	onclick="window.location.href='<c:url value="/admininfocontroller/toadmininfo.action"/>'">修改资料
	            		</div>
            <div style="display: none" class="div-menu-child <%="home".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
                 onclick="loginHome('${ SESSION_USER_CONST_ORGUSER.username}')">个人首页
            </div>



        <%--</c:when>
     			<c:otherwise>  
     			<div class="div-menu-child <%="account".equals(request.getParameter("pageTitle"))?"div-menu-child-click":""%>"
	                 onclick="window.location.href='<c:url value="/userinfo/toNoAccessToAuthority.action?pageType=account"/>'">修改资料
	            	</div>    
  				</c:otherwise> 
			</c:choose>--%>
       
        </div>
    </div>
</div>
<script>
   function loginHome(username){

       $.ajax({
           type: "POST",
           url:contextpath+'/innerlogin.action',
           data:{username:username,usertype:'1'},
           success: function(json) {
               setTimeout(function() { window.location.href=contextpath; }, 10);
           },
           beforeSend(XHR){
               setTimeout(function() { window.location.href=contextpath; }, 1000);
           }
       });

   }
</script>


