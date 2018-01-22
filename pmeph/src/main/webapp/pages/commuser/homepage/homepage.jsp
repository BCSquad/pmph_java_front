<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>首页</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/homepage/homepage.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.scroll.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/menu.js"></script>

    <script src="${ctx}/resources/commuser/homepage/homepage.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">
    <div class="content-wrapper">
        <div class="area-1">
            <div class="banner">
                <div class="move" id="move">
                    <ul>
                        <li><img src="${ctx}/statics/testfile/index.png" style="width: 922px; height: 380px"></li>
                        <li><img src="${ctx}/statics/testfile/index.png" style="width: 922px; height: 380px"></li>
                        <li><img src="${ctx}/statics/testfile/index.png" style="width: 922px; height: 380px"></li>
                        <li><img src="${ctx}/statics/testfile/index.png" style="width: 922px; height: 380px"></li>
                        <li><img src="${ctx}/statics/testfile/index.png" style="width: 922px; height: 380px"></li>
                    </ul>
                </div>
                <div class="ctrl" id="ctrl"></div>
            </div>
            <div class="op-link">
                <div class="transaction">
                    <div class="labeling">教材申报</div>
                    <div class="bin consol"><div class="lab-pic1"></div>最新公告</div>
                    <div class="bin marks" ><div class="lab-pic2"></div>专家申报</div>
                    <div class="bin consol"><div class="lab-pic3"></div>学校审核</div>
                    <div class="bin marks" ><div class="lab-pic4"></div>结果公布</div>
                <div class="transaction" style="margin-top: 18px;">
                    <div class="labeling">我要出书</div>
                    <div class="binone consol"><div class="lab-pic5"></div>医学专著</div>
                    <div class="binone marks" ><div class="lab-pic6"></div>科普图书</div>
                    <div class="binone consol"><div class="lab-pic7"></div>创新教材</div>
                    <div class="binone marks" ><div class="lab-pic8"></div>进度查询</div>
                </div>
                <div class="transaction" style="margin-top: 8px;">
                    <div class="labeling">交互服务</div>
                    <div class="bintwo consol"><div class="lab-pic9"></div>医学小组</div>
                    <div class="bintwo marks" ><div class="lab-pic10"></div>图书纠错</div>
                    <div class="bintwo consol"><div class="lab-pic11"></div>问卷调查</div>
                    <div class="bintwo marks" ><div class="lab-pic12"></div>经验交流</div>
                </div>
            </div>
        </div>
        <div class="notice area-2">
            <div class="title" style="float: left">
                <div class="line bgcolor-blue" style="float: left"></div>
                <div class="name color-blue" style="float: left"><img src="${ctx}/statics/testfile/gg.png"
                                                                      style="margin-left: 30px;margin-top: 42px"></div>
            </div>
            <c:forEach items="${listDou}" var="list" varStatus="status">
                <c:if test="${status.index==0}">
                    <div class="content-left" style="margin-left: 30px">
                        <p class="content-size"><a href="${ctx}/message/noticeMessageDetail.action?id=${list.id }" class="astyle">${list.title}</a></p>
                        <p class="time-size">发布时间：<fmt:formatDate value="${list.gmt_create}" type="date" pattern="yyyy-MM-dd"/></p>
                        <div class="left_join"
                        onclick="window.location.href='${ctx}/material/toMaterialAdd.action?material_id=${list.material_id}'">报名参加</div>
                    </div>
                </c:if>
                <c:if test="${status.index!=0}">
                    <div class="content-left">
                        <p class="content-size"><a href="${ctx}/message/noticeMessageDetail.action?id=${list.id }" class="astyle">${list.title}</a></p>
                        <p class="time-size">发布时间：<fmt:formatDate value="${list.gmt_create}" type="date" pattern="yyyy-MM-dd"/></p>
                        <div class="left_join"
                        onclick="window.location.href='${ctx}/material/toMaterialAdd.action?material_id=${list.material_id}'">报名参加</div>
                    </div>
                </c:if>
            </c:forEach>
            <div class="more" onclick="top.location='${ctx}/cmsnotice/tolist.action'">全部>></div>
        </div>
        <div class="notice area-3">
            <div class="title" style="float: left">
                <div class="line bgcolor-blue"></div>
                <div class="name color-blue"><img src="${ctx}/statics/testfile/xx.png"
                                                  style="margin-left: 30px;margin-top: 15px"></div>
            </div>
            <div class="photo-size">
                <img src="${ctx}/statics/testfile/content.png">
            </div>
            <c:forEach items="${listNot}" var="list" varStatus="status">
                <c:if test="${status.index==0}">
                    <div class="content-photo">
                        <p class="content-size"><a href="${ctx}/inforeport/toinforeport.action?id=${list.id}" class="astyle" style="color: #333333">${list.title}</a></p>
                        <p class="time-size">发布时间：<fmt:formatDate value="${list.gmt_create}" type="date"
                                                                  pattern="yyyy-MM-dd"/></p>
                    </div>
                </c:if>
            </c:forEach>
            <div class="content">
                <ul class="table">
                    <c:forEach items="${listNot}" var="list" varStatus="status">
                        <c:if test="${status.index!=0}">
                            <li><a href="${ctx}/inforeport/toinforeport.action?id=${list.id}" class="astyle" style="color: #333333">> ${list.title}</a></li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <div class="more" onclick="top.location='${ctx}/cmsinfoletters/tolist.action'">全部>></div>
        </div>
        <div class="area-4">
            <div class="tab-bar">
                <%-- <c:forEach items="${bookTypes}" var="type" varStatus="status">
                    <div class="tab ${status.index==0?'active':''}" id="${type.id}"
                         onclick='chooseType("${type.id}")'>${type.type_name}</div>
                </c:forEach> --%>
                <div class="tab active" id="1" onclick='chooseType("1")'>学校教育</div>
                <div class="tab" id="2" onclick='chooseType("2")'>毕业后教育</div>
                <div class="tab" id="3" onclick='chooseType("3")'>继续教育</div>
                <div class="tab" id="4" onclick='chooseType("4")'>考试用书</div>
                <div class="page ">
                    <div class="page-num">
                        <input type="hidden" id="book_type">
                        <span class="beforepage" id="before">${thisrows}</span>/
                        <span class="beforepage" id="next">${allrows}</span>
                        <span class="icon-right" onclick='on("next")'></span>
                        <span class="icon-left" onclick='on("before")'></span>
                    </div>
                </div>
                <c:forEach items="${listType}" var="list" varStatus="status">
                    <c:if test="${status.index==0}">
                        <div class="type" id="typeOne">${list.type_name}</div>
                    </c:if>
                </c:forEach>
                <c:forEach items="${listType}" var="list" varStatus="status">
                    <c:if test="${status.index==1 or status.index==2 }">
                        <div class="point"></div>
                        <div class="type" id="typeTwo">${list.type_name}</div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="content">
                <div class="left">
                    <div class="left_one">
                        <div class="textbook_left">教材社区</div>
                        <div class="all_left" onclick="window.location.href='${ctx}/community/tolist.action'">全部>></div>
                        <c:forEach items="${listDou}" var="list" varStatus="status">
	                      <c:if test="${status.index==0}">
	                        <div class="left_con1" style="margin-top: 63px;" onclick="todou('${list.mid}')">${list.title}</div>
	                      </c:if>
	                      <c:if test="${status.index!=0}">
	                        <div class="left_con1" onclick="todou('${list.mid}')">${list.title}</div>
                          </c:if>
                        </c:forEach>
                    </div>
                    <div class="left_two">
                        <div class="textbook_left">重点学科推荐</div>  
                        <div class="all_left">全部>></div>
                        <div class="p1_left"></div>
                        <div class="p2_left"></div>
                    </div>
                </div>
                <div class="right" id="homepagebook" style="float: right;">${homepagebook}</div>
            </div>
        </div>
        <div class="area-5">
            <div class="photo"><img src="${ctx}/statics/testfile/adv.png" class="book1"></div>
            <div class="item"><img src="${ctx}/statics/testfile/adv.png" class="book1"></div>
            <div class="item"><img src="${ctx}/statics/testfile/adv.png" class="book1"></div>
            <div class="item"><img src="${ctx}/statics/testfile/adv.png" class="book1"></div>
        </div>
        <div class="area-6">
            <div class="doc"></div>
            <div class="title">
                <span class="doc_content">医学随笔</span>
                <span class="total">全部>></span>
            </div>
            <c:forEach items="${listArt}" var="list" varStatus="status">
                <c:if test="${status.index==0}">
                    <div class="item1" onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                        <div class="big">
                            <div class="small"><span class="recommend">推荐</span></div>
                            <div class="pc1"><img src="${imgpath }"></div>
                            <div class="tt"><span class="a6_content">${list.title}</span></div>
                            <div class="a6_div2">${list.summary}</div>
                            <div>
                                <div class="a6_head_div"><img src="${ctx}/statics/testfile/a6_photo.png"
                                                              class="a6_head"></div>
                                <div class="a6_name_div"><span>${list.realname}</span></div>
                                <div class="a6_time_div"><span>${list.gmt_create}</span></div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <c:forEach items="${listArt}" var="list" varStatus="status">
                <c:if test="${status.index!=0}">
                    <div class="item" onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                        <div class="big">
                            <div class="small"><span class="recommend">推荐</span></div>
                            <div class="pc1"><img src="${imgpath }"></div>
                            <div class="tt"><span class="a6_content">${list.title}</span></div>
                            <div class="a6_div2">${list.summary}</div>
                            <div>
                                <div class="a6_head_div"><img src="${ctx}/statics/testfile/a6_photo.png"class="a6_head"></div>
                                <div class="a6_name_div"><span>${list.realname}</span></div>
                                <div class="a6_time_div"><span>${list.gmt_create}</span></div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
        <div class="area-7">
            <div class="title"></div>
            <div class="item">
                <div class="can"><span class="author">推荐作者</span></div>
                <c:forEach items="${listAut}" var="list" varStatus="status">
                    <c:if test="${status.index==0}">
                        <div class="author1">
                            <div class="a7_head_div"><img src="${ctx}/statics/testfile/a6_photo.png" class="a6_head">
                            </div>
                            <div class="a7_name_div">
                                <div class="a7_author">
                                    <span class="a7_write">${list.realname}</span>
                                </div>
                                <br>
                                <div class="a7_cont">
                                    <span class="a7_cont">${list.title}</span>
                                </div>
                            </div>
                            <div class="a7_friend">
                                <span class="friend"><B>+</B>好友</span>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach items="${listAut}" var="list" varStatus="status">
                    <c:if test="${status.index!=0}">
                        <div class="author2">
                            <div class="a7_head_div"><img src="${ctx}/statics/testfile/a6_photo.png" class="a6_head">
                            </div>
                            <div class="a7_name_div">
                                <div class="a7_author">
                                    <span class="a7_write">${list.realname}</span>
                                </div>
                                <br>
                                <div class="a7_cont">
                                    <span class="a7_cont">${list.title}</span>
                                </div>
                            </div>
                            <div class="a7_friend">
                                <span class="friend"><B>+</B>好友</span>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <div class="area-8">
            <div class="left">
                <div class="title">
                    <div class="line"></div>
                    <div class="name">热门书评</div>
                </div>
                <div class="items">
                    <c:forEach items="${listCom}" var="list" varStatus="status">
                        <div class="item1"
                             onclick="window.open(contextpath+'readdetail/todetail.action?id=${list.id1}')">
                            <div class="sp_01"><img src="${list.image_url}"/></div>
                            <div class="sp_02">
                                <div class="sp_title">${list.bookname}</div>
                                <div class="sp_pl">
                                    <span style="float: left;">${list.realname} 评论了 《${list.bookname}》</span>
                                        <%--	<span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>--%>
                                </div>
                                <div style="clear: both"></div>
                                <div class="sp_remark">${list.content}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div id="last_div"></div>
            </div>
            <div class="right">
                <div class="title">
                    <div class="line"></div>
                    <div class="name">图书畅销榜</div>
                    <div class="last_right">
                        <div class="last_right_head">

                            <c:forEach items="${bookTypes}" var="type" varStatus="status">
                                <%--<div class="tab ${status.index==0?'active':''}" id="${type.id}"
                                     onclick='chooseType("${type.id}")'>${type.type_name}</div>--%>

                                <div class="right_div1 ${status.index==0?'active':''}"
                                     onclick='changesale("${type.id}")' id="typeid-${type.id}">
                                        ${type.type_name}
                                </div>
                                <c:if test="${status.index!=3}">
                                    <div class="div_center"><img src="${ctx}/statics/image/shu.png"
                                                                 style="margin-top: 13px">
                                    </div>
                                </c:if>
                            </c:forEach>

                            <%--

                                                        <div class="right_div1" onclick='changesale("graduate")' id="graduate">毕业后教育</div>
                                                        <div class="div_center"><img src="${ctx}/statics/image/shu.png"
                                                                                     style="margin-top: 13px;margin-left: 3px"></div>
                                                        <div class="right_div1" onclick='changesale("continue")' id="continue">继续教育</div>
                                                        <div class="div_center"><img src="${ctx}/statics/image/shu.png" style="margin-top: 13px">
                                                        </div>
                                                        <div class="right_div1" onclick='changesale("exam")' id="exam">考试用书</div>--%>
                        </div>
                        <c:forEach items="${listSal}" var="list" varStatus="status">
                            <div class="last_right_body">
                                <div class="last_right_book"
                                     onclick="window.open(contextpath+'readdetail/todetail.action?id=${list.id}')">
                                    <c:set var="default_url"
                                           value="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"></c:set>
                                    <img src='${list.image_url == "DEFAULT"?default_url:list.image_url}'
                                         class="book_style" id="right_book1">
                                </div>
                                <div class="num${status.index>2?'4':status.index+1+''}">
                                    <div class="num_book">${status.index+1}</div>
                                </div>
                                <div class="last_right_content">
                                    <div class="sale_book" id="sale_book1">${list.bookname}</div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>
    <div class="test" id="test_float" onclick="tosurvey()">
        <div class="cancel" onclick="cancel()">关闭</div>
    </div>
</div>
    <jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
</body>
</html>
