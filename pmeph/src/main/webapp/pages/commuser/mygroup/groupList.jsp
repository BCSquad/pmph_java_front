<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/26
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>小组列表</title>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <link rel="stylesheet" href="${ctx}/statics/commuser/mygroup/groupList.css" type="text/css">
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>

<div class="content-body">
    <div class="div_top">我加入的小组</div>
    <div class="items">
        <div class="item">
            <img src="${ctx}/statics/testfile/tx.png" alt="头像" class="img1"/>
            <div class="item_content">
                <text class="txt1">生物化学与生物分子学</text>
                <br/>
                <text class="txt2">
                    <text class="color64">创建J</text>
                    :&nbsp;<text class="color99">2017年10月27日</text>
                </text>
                <div class="imgs">
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                </div>
                <text class="txt3">
                    人数
                    <text class="txt30">251</text>
                    <label></label>
                    文件
                    <text class="txt30">43</text>
                </text>
                <div style="margin-top:10px;"><a class="item_link" href="communication.html">
                    <div class="item_content_button">小组主页></div>
                </a></div>
            </div>
            <div class="item_bc">
                <div class="item_bc_top">
                    <text>最新动态</text>
                    <div></div>
                </div>
                <div>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                </div>

            </div>
        </div>
        <div class="item item_center">
            <img src="${ctx}/statics/testfile/tx.png" alt="头像" class="img1"/>
            <div class="item_content">
                <text class="txt1">生物化学与生物分子学</text>
                <br/>
                <text class="txt2">
                    <text class="color64">创建J</text>
                    :&nbsp;<text class="color99">2017年10月27日</text>
                </text>
                <div class="imgs">
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                </div>
                <text class="txt3">
                    人数
                    <text class="txt30">251</text>
                    <label></label>
                    文件
                    <text class="txt30">43</text>
                </text>
                <div style="margin-top:10px;"><a class="item_link" href="communication.html">
                    <div class="item_content_button">小组主页></div>
                </a></div>
            </div>
            <div class="item_bc">
                <div class="item_bc_top">
                    <text>最新动态</text>
                    <div></div>
                </div>
                <div>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                </div>

            </div>
        </div>
        <div class="item">
            <img src="${ctx}/statics/testfile/tx.png" alt="头像" class="img1"/>
            <div class="item_content">
                <text class="txt1">生物化学与生物分子学</text>
                <br/>
                <text class="txt2">
                    <text class="color64">创建J</text>
                    :&nbsp;<text class="color99">2017年10月27日</text>
                </text>
                <div class="imgs">
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                </div>
                <text class="txt3">
                    人数
                    <text class="txt30">251</text>
                    <label></label>
                    文件
                    <text class="txt30">43</text>
                </text>
                <div style="margin-top:10px;"><a class="item_link" href="communication.html">
                    <div class="item_content_button">小组主页></div>
                </a></div>
            </div>
            <div class="item_bc">
                <div class="item_bc_top">
                    <text>最新动态</text>
                    <div></div>
                </div>
                <div>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                </div>

            </div>
        </div>


        <div class="item">
            <img src="${ctx}/statics/testfile/tx.png" alt="头像" class="img1"/>
            <div class="item_content">
                <text class="txt1">生物化学与生物分子学</text>
                <br/>
                <text class="txt2">
                    <text class="color64">创建J</text>
                    :&nbsp;<text class="color99">2017年10月27日</text>
                </text>
                <div class="imgs">
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                </div>
                <text class="txt3">
                    人数
                    <text class="txt30">251</text>
                    <label></label>
                    文件
                    <text class="txt30">43</text>
                </text>
                <div style="margin-top:10px;"><a class="item_link" href="communication.html">
                    <div class="item_content_button">小组主页></div>
                </a></div>
            </div>
            <div class="item_bc">
                <div class="item_bc_top">
                    <text>最新动态</text>
                    <div></div>
                </div>
                <div>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                </div>

            </div>
        </div>
        <div class="item item_center">
            <img src="${ctx}/statics/testfile/tx.png" alt="头像" class="img1"/>
            <div class="item_content">
                <text class="txt1">生物化学与生物分子学</text>
                <br/>
                <text class="txt2">
                    <text class="color64">创建J</text>
                    :&nbsp;<text class="color99">2017年10月27日</text>
                </text>
                <div class="imgs">
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                </div>
                <text class="txt3">
                    人数
                    <text class="txt30">251</text>
                    <label></label>
                    文件
                    <text class="txt30">43</text>
                </text>
                <div style="margin-top:10px;"><a class="item_link" href="communication.html">
                    <div class="item_content_button">小组主页></div>
                </a></div>
            </div>
            <div class="item_bc">
                <div class="item_bc_top">
                    <text>最新动态</text>
                    <div></div>
                </div>
                <div>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                </div>

            </div>
        </div>
        <div class="item">
            <img src="${ctx}/statics/testfile/tx.png" alt="头像" class="img1"/>
            <div class="item_content">
                <text class="txt1">生物化学与生物分子学</text>
                <br/>
                <text class="txt2">
                    <text class="color64">创建J</text>
                    :&nbsp;<text class="color99">2017年10月27日</text>
                </text>
                <div class="imgs">
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                    <img src="${ctx}/statics/testfile/tx.png"/>
                </div>
                <text class="txt3">
                    人数
                    <text class="txt30">251</text>
                    <label></label>
                    文件
                    <text class="txt30">43</text>
                </text>
                <div style="margin-top:10px;"><a class="item_link" href="communication.html">
                    <div class="item_content_button">小组主页></div>
                </a></div>
            </div>
            <div class="item_bc">
                <div class="item_bc_top">
                    <text>最新动态</text>
                    <div></div>
                </div>
                <div>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                    <p>
                        <text>张天志共享了文件 "主编遴选结果.xls"</text>
                    </p>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

</body>
</html>
