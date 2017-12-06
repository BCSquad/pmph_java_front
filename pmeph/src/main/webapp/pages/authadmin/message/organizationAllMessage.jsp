<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>Insert title here</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet"
          href="${ctx}/statics/authadmin/message/organizationMessage.css"
          type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/authadmin/message/organizationMessage.js" type="text/javascript"></script>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp"></jsp:include>
 <div class="message-body" >
        <div class="message">
           <a href="${pageContext.request.contextPath}/authSendMessage/initAllMessage.action">
                <div class="item">
                    <div class="on-text">全部消息</div>
                    <div class="on-line" ></div>
                </div>
            </a>
            <a href="${pageContext.request.contextPath}/authSendMessage/init.action">
                <div class="item">
                    <div class="off-text" >发送消息</div>
                    <div class="off-line" ></div>
                </div >
            </a>
        </div>
        <div class="message-list" >
            <div style="height: 20px;"></div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                     <p class="title" >
                         <span class="msg">申报资料审核</span>
                         <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                     </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》
                        张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》张三提交了《全国高等学校心理学研究教材》

                    </p>
                </div>
            </div>

            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="item">
                <div class="item-img">
                    <img src="${ctx}/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" >
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》张三提交了
                    </p>
                </div>
            </div>
            <div class="load-more">
                <a href=""><p class="load-text">加载更多...</p></a>
            </div>
        </div>
 </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
<script type="text/javascript">
    UE.getEditor('mText');
</script>
