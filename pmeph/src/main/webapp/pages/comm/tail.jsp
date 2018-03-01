<%--
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
    <div class="tail">

        <%
            String linked = request.getParameter("linked");
            request.setAttribute("linked", linked);
        %>
        <c:if test="${linked!=null}">
            <div style="text-align: center;height: 50px; font-size: 20px;">
                友情链接
            </div>
            <div class="about">
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
            </div>
        </c:if>


        <div class="copyright">
            京公网安备 11010502031111号 备案号：京ICP备05067302号 ©2007-2014 All Rights Reserved 版权所有 人民卫生出版社有限公司
        </div>
        <div class="copyright">
            基建工程监督电话：59787010 基建工程监督信箱：jjjd@pmph.com
        </div>
    </div>
</div>


