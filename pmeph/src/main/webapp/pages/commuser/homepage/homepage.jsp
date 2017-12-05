<%--
  Created by IntelliJ IDEA.
  User: SuiXinYang
  Date: 2017/11/21
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contxtpath = '${pageContext.request.contextPath}';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>首页</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/homepage/homepage.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
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
                <img src="${ctx}/statics/testfile/index.png" style="width: 1050px; height: 380px">
            </div>
            <div class="op-link">
                <div class="title">教材申报</div>
                <div class="link">
                    <span>最新公告</span>
                    <span class="icon">></span>
                </div>
                <div class="link">
                    <span>学校审核</span>
                    <span class="icon">></span>
                </div>
                <div class="link">
                    <span>专家申报</span>
                    <span class="icon">></span>
                </div>
                <div class="link">
                    <span>结果公布</span>
                    <span class="icon">></span>
                </div>

                <div class="line"></div>

                <div class="title">交互服务</div>
                <div class="link">
                    <span>医学小组</span>
                    <span class="icon">></span>
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
                        <p class="content-size">${list.title}</p>
                        <p class="time-size">${list.gmt_create}</p>
                    </div>
                </c:if>
                <c:if test="${status.index!=0}">
                    <div class="content-left">
                        <p class="content-size">${list.title}</p>
                        <p class="time-size">${list.gmt_create}</p>
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
                        <p class="content-size">${list.title}</p>
                        <p class="time-size">发布时间:${list.gmt_create}</p>
                    </div>
                </c:if>
            </c:forEach>
            <div class="content">
                <ul class="table">
                    <c:forEach items="${listNot}" var="list" varStatus="status">
                        <c:if test="${status.index!=0}">
                            <li>> ${list.title}</li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
            <div class="more" onclick="top.location='${ctx}/cmsinfoletters/tolist.action'">全部>></div>
        </div>
        <div class="area-4">
            <div class="tab-bar">
                <div class="tab active" id="633" onclick='chooseType("633")'>学校教育</div>
                <div class="tab" id="634" onclick='chooseType("634")'>毕业后教育</div>
                <div class="tab" id="635" onclick='chooseType("635")'>继续教育</div>
                <div class="tab" id="636" onclick='chooseType("636")'>教育用书</div>
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
                    <c:if test="${status.index!=0}">
                        <div class="point"></div>
                        <div class="type" id="typeTwo">${list.type_name}</div>
                    </c:if>
                </c:forEach>
            </div>
            <div class="content">
                <div class="left">
                    <div class="div_photo1">
                        <div class="label"><label style="font-size: 24px;color: #489399">热门标签</label></div>
                        <div class="div1">
                            <c:forEach items="${listLabel}" var="item" varStatus="status">
                                <a href="${item.type}" class="little"><span
                                        class="little_content">${item.note}</span></a>
                            </c:forEach>
                            <%--<div class="little"><span class="little_content">护理</span></div>
                            <div class="little"><span class="little_content">应用心理</span></div>
                            <div class="little"><span class="little_content">法医</span></div>
                            <div class="little"><span class="little_content">口腔医学</span></div>
                            <div class="little"><span class="little_content">医学影像</span></div>
                            <div class="little"><span class="little_content">医学美容</span></div>
                            <div class="little"><span class="little_content">护理</span></div>
                            <div class="little"><span class="little_content">应用心理</span></div>
                            <div class="little"><span class="little_content">口腔医学</span></div>
                            <div class="little"><span class="little_content">眼视光</span></div>
                            <div class="little"><span class="little_content">临床</span></div>--%>
                        </div>
                    </div>
                    <div class="div_photo"></div>
                    <div class="div_photo"></div>
                </div>
                <div class="right" id="homepagebook">${homepagebook}</div>
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
                    <div class="item1">
                        <div class="big">
                            <div class="small"><span class="recommend">推荐</span></div>
                            <div class="pc1"><img src="${ctx}/statics/testfile/a6_photo.png"></div>
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
                    <div class="item">
                        <div class="big">
                            <div class="small"><span class="recommend">推荐</span></div>
                            <div class="pc1"><img src="${ctx}/statics/testfile/a6_photo.png"></div>
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
                                    <span class="a7_write">${list.author_id}</span>
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
                                    <span class="a7_write">${list.author_id}</span>
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
                        <div class="item1" onclick="openUrl('${list.id1}')">
                            <div class="sp_01"><img src="${list.image_url}"/></div>
                            <div class="sp_02">
                                <div class="sp_title">${list.bookname}</div>
                                <div class="sp_pl">
                                    <span style="float: left;">${rmsp.realname} 评论了 《${list.bookname}》</span>
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
                        <%--<c:if test="${status.index==0}">
                            <div class="last_left1">
                                <div class="sp_01"><img src="${list.image_url}"/></div>
                                <div class="last_two_font">
                                    <div class="last_title_head"><span class="last_title_font">${list.bookname}</span></div>
                                    <div class="last_comment">
                                        <div class="last_advice_head">${list.author}评论了《${list.bookname}》</div>
                                       &lt;%&ndash; <div class="last_content_picture1">
                                            <span class="last_star_true"></span>
                                            <span class="last_star_true"></span>
                                            <span class="last_star_true"></span>
                                            <span class="last_star_true"></span>
                                            <span class="last_star_false"></span>
                                        </div>&ndash;%&gt;
                                    </div>
                                    <div class="last_content_head"><span class="last_content_font">${list.content}</span>
                                    </div>
                                </div>
                            </div>
                        </c:if>--%>
                    </c:forEach>
                </div>
                <%--<c:forEach items="${listCom}" var="list" varStatus="status">
                    <c:if test="${status.index==1}">
                        <div class="last_left1 last_behind">
                            <div><img src="${ctx}/statics/testfile/rightbook.png" class="last_book"></div>
                            <div class="last_two_font">
                                <div class="last_title_head"><span class="last_title_font">${list.bookname}</span></div>
                                <div class="last_comment">
                                    <div class="last_advice_head">${list.author}评论了《${list.bookname}》</div>
                                    <div class="last_content_picture1">
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_false"></span>
                                    </div>
                                </div>
                                <div class="last_content_head"><span class="last_content_font">${list.content}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach items="${listCom}" var="list" varStatus="status">
                    <c:if test="${status.index==2}">
                        <div class="last_left">
                            <div><img src="${ctx}/statics/testfile/rightbook.png" class="last_book"></div>
                            <div class="last_last_font">
                                <div class="last_title"><span class="last_title_font">${list.bookname}</span></div>
                                <div class="last_comment">
                                    <div class="last_advice">${list.author}评论了《${list.bookname}》</div>
                                    <div class="last_content_picture">
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_false"></span>
                                    </div>
                                </div>
                                <div class="last_content"><span class="last_content_font">${list.content}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach items="${listCom}" var="list" varStatus="status">
                    <c:if test="${status.index==3}">
                        <div class="last_left last_behind">
                            <div><img src="${ctx}/statics/testfile/rightbook.png" class="last_book1"></div>
                            <div class="last_last_font">
                                <div class="last_title"><span class="last_title_font">${list.bookname}</span></div>
                                <div class="last_comment">
                                    <div class="last_advice">${list.author}评论了《${list.bookname}》</div>
                                    <div class="last_content_picture">
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_true"></span>
                                        <span class="last_star_false"></span>
                                    </div>
                                </div>
                                <div class="last_content"><span class="last_content_font">${list.content}</span>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>--%>
                <div id="last_div"></div>
            </div>
            <div class="right">
                <div class="title">
                    <div class="line"></div>
                    <div class="name">图书畅销榜</div>
                    <div class="last_right">
                        <div class="last_right_head">
                            <div class="right_div1 active" onclick='changesale("school")' id="school">
                                学校教育
                            </div>
                            <div class="div_center"><img src="${ctx}/statics/image/shu.png" style="margin-top: 13px">
                            </div>
                            <div class="right_div1" onclick='changesale("graduate")' id="graduate">毕业后教育</div>
                            <div class="div_center"><img src="${ctx}/statics/image/shu.png"
                                                         style="margin-top: 13px;margin-left: 3px"></div>
                            <div class="right_div1" onclick='changesale("continue")' id="continue">继续教育</div>
                            <div class="div_center"><img src="${ctx}/statics/image/shu.png" style="margin-top: 13px">
                            </div>
                            <div class="right_div1" onclick='changesale("exam")' id="exam">考试用书</div>
                        </div>
                        <c:forEach items="${listSal}" var="list" varStatus="status">
                            <div class="last_right_body">
                                <div class="last_right_book"
                                     onclick="window.open(contxtpath+'/readdetail/todetail.action?id=${list.id}')">
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
                        <%--<c:forEach items="${listSal}" var="list" varStatus="status">
                            <c:if test="${status.index==1}">
			                        <div class="last_right_body">
			                            <div class="last_right_book">
			                                <img src="${list.image_url=='DEFAULT'?ctx+'/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':list.image_url}" class="book_style" id="right_book2">
			                            </div>
			                            <div class="num2">
			                                <div class="num_book">2</div>
			                            </div>
			                            <div class="last_right_content">
			                                <div class="sale_book" id="sale_book2">${list.bookname}</div>
			                            </div>
			                        </div>
			                </c:if>
                        </c:forEach>
                        <c:forEach items="${listSal}" var="list" varStatus="status">
                            <c:if test="${status.index==2}">
			                        <div class="last_right_body">
			                            <div class="last_right_book">
			                                <img src="${list.image_url=='DEFAULT'?ctx+'/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':list.image_url}" class="book_style" id="right_book3">
			                            </div>
			                            <div class="num3">
			                                <div class="num_book">3</div>
			                            </div>
			                            <div class="last_right_content">
			                                <div class="sale_book" id="sale_book3">${list.bookname}</div>
			                            </div>
			                        </div>
			                </c:if>
                        </c:forEach>
                        <c:forEach items="${listSal}" var="list" varStatus="status">
                            <c:if test="${status.index==3}">
		                        <div class="last_right_body body_top">
		                            <div class="last_right_book">
		                                <img src="${list.image_url=='DEFAULT'?ctx+'/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':list.image_url}" class="book_style" id="right_book4">
		                            </div>
		                            <div class="num4">
		                                <div class="num_book">4</div>
		                            </div>
		                            <div class="last_right_content">
		                                <div class="sale_book" id="sale_book4">${list.bookname}</div>
		                            </div>
		                        </div>
		                   </c:if>
                        </c:forEach>
                        <c:forEach items="${listSal}" var="list" varStatus="status">
                            <c:if test="${status.index==4}">
		                        <div class="last_right_body body_top">
		                            <div class="last_right_book">
		                                <img src="${list.image_url=='DEFAULT'?ctx+'/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':list.image_url}" class="book_style" id="right_book5">
		                            </div>
		                            <div class="num4">
		                                <div class="num_book">5</div>
		                            </div>
		                            <div class="last_right_content">
		                                <div class="sale_book" id="sale_book5">${list.bookname}</div>
		                            </div>
		                        </div>
		                    </c:if>
                        </c:forEach>
                        <c:forEach items="${listSal}" var="list" varStatus="status">
                            <c:if test="${status.index==5}">
		                        <div class="last_right_body body_top">
		                            <div class="last_right_book">
		                                <img src="${list.image_url=='DEFAULT'?ctx+'/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':list.image_url}" class="book_style" id="right_book6">
		                            </div>
		                            <div class="num4">
		                                <div class="num_book">6</div>
		                            </div>
		                            <div class="last_right_content">
		                                <div class="sale_book" id="sale_book6">${list.bookname}</div>
		                            </div>
		                        </div>
		                 </c:if>
                        </c:forEach>--%>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear: both"></div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
