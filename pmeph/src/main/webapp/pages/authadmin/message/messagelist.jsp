<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="<%=path %>/statics/css/base.css?t=${_timestamp}" type="text/css">
   <link rel="stylesheet" href="<%=path %>/statics/orgmessage/messagelist.css?t=${_timestamp}" type="text/css">
</head>

<body>
<iframe style="width: 100%;padding: 0;margin: 0;height:110px;border: none" src="../accountSettings/masters/head_1.html"></iframe>
 <div class="message-body" >
        <div class="message">
            <a href="organizationMessage.html">
                <div class="item">
                    <div class="on-text">全部消息</div>
                    <div class="on-line" ></div>
                </div>
            </a>
            <a href="sendNewMessage.html">
                <div class="item">
                    <div class="off-text" >发送消息</div>
                    <div class="off-line" ></div>
                </div >
            </a>
        </div>
        <div class="message-list" >
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                     <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                         <span class="msg">申报资料审核</span>
                         <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                     </p>
                     <p class="text">
                         张三提交了《全国高等学校心理学研究教材》
                     </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="item">
                <div class="item-img">
                    <img src="<%=path %>/statics/testfile/tttt.png" />
                </div>
                <div class="content" >
                    <p class="title" style="margin-top:6px;margin-bottom: 16px ">
                        <span class="msg">申报资料审核</span>
                        <span class="time">20175.17&nbsp;&nbsp;15:13</span>
                    </p>
                    <p class="text">
                        张三提交了《全国高等学校心理学研究教材》
                    </p>
                </div>
            </div>
            <div class="item-line"></div>
            <div class="load-more">
                <p class="load-text">加载更多...</p>
            </div>
        </div>
 </div>
<iframe style="width: 100%;clear:both;padding: 0;margin: 0;height: 200px;border: none" src="../comm/tail.html"></iframe>
</body>
</html>