<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="com.bc.pmpheep.general.service.SiteLinkService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="footer">
    <div style="height: 30px;"></div>
    <div class="tail" style="margin-top: 20px">

        <%
            String linked = request.getParameter("linked");
            request.setAttribute("linked", linked);

            ApplicationContext applicationContext =
                    WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
            SiteLinkService siteLinkService =applicationContext.getBean("com.bc.pmpheep.general.service.SiteLinkService", SiteLinkService.class);

            List<String> linkHtmlList = siteLinkService.getSiteHtmlPerRow();
            request.setAttribute("linkHtmlList",linkHtmlList);
        %>
        <c:if test="${linked!=null}">
            <div style="text-align: center;height: 50px; font-size: 20px;">
                友情链接
            </div>
            <c:forEach items="${linkHtmlList}" var="linkHtml">
                <div class="about">${linkHtml}</div>
            </c:forEach>

            <%--<div class="about">
                <a class="item" href="http://www.nhfpc.gov.cn/">中华人民共和国国家卫生和计划生育委员会</a>
                <a class="item">|</a><a class="item" href="http://www.nmec.org.cn/">国家医学考试网</a>
                <a class="item">|</a><a class="item" href="http://www.21wecan.com/">中国卫生人才网</a>
                <a class="item">|</a><a class="item" href="http://www.chinabookinternational.org/">中国图书对外推广网</a>
                <a class="item">|</a><a class="item" href="http://www.ipmph.com/journals-electronic">电子期刊</a>
                <a class="item">|</a><a class="item" href="http://www.ipmph.com/">人卫智网</a>
            </div>
            <div class="about">
                <a class="item" href="http://www.ipmph.com/exam">人卫智网考试</a>
                <a class="item">|</a><a class="item" href="http://lib.pmph.com/">人卫智网数据库</a>
                <a class="item">|</a><a class="item" href="http://www.ipmph.com/edu">人卫智网教育</a>
                <a class="item">|</a><a class="item" href="">全国高等医药教材建设研究会·人民卫生出版社专家咨询委员会</a>
                <a class="item">|</a><a class="item" href="">人卫医学网健康</a>
            </div>
            <div class="about">
                <a class="item" href="">人卫社美国分公司</a>
                <a class="item">|</a><a class="item" href="">人卫酒店</a>
                <a class="item">|</a><a class="item" href="">中国学生健康报</a>
                <a class="item">|</a><a class="item" href="">世界中医药学会联合会出版编辑专业委员会</a>
            </div>--%>
        </c:if>


        <div class="copyright">
            北京市朝阳区潘家园南里19号人卫大厦
        </div>
        <div class="copyright">
            ©2014-2019  人民卫生出版社主办 京ICP备 05067302号 
            <div style="width:300px;margin:0 auto; padding:20px 0;">
                     <a target=“_blank” href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010502031111" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">  <img src="${ctx}/statics/image/ghs.png" style="float:left;"/> <p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">京公网安备 11010502031111号</p ></a>
                    </div>
        </div>
    </div>
</div>


