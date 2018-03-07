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
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
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
<input type="hidden" id="auto_play" value="${adInfo1.auto_play}">
<input type="hidden" id="animation_interval" value="${adInfo1.animation_interval}">
<div class="body">
    <div class="content-wrapper">
        <div class="area-1">
            <div class="banner">
                <div class="move" id="move">
                    <ul>
                        <c:forEach var="ad" items="${adInfo1.detailList}">
                            <li><img src="${ctx}/image/${ad.image}.action" style="width: 922px; height: 380px"/></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="ctrl" id="ctrl"></div>
            </div>
            <div class="op-link">
                <div class="transaction">
                    <div class="labeling">教材申报</div>
                    <div class="bin consol" onclick="window.location.href='${ctx}/cmsnotice/tolist.action'">
                        <div class="lab-pic1"></div>
                        最新公告
                    </div>
                    <div class="bin marks"
                         onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?pagetag=jcsb'">
                        <div class="lab-pic2"></div>
                        专家申报
                    </div>
                    <div class="bin consol" onclick="window.location.href='${ctx}/schedule/scheduleList.action'">
                        <div class="lab-pic3"></div>
                        学校审核
                    </div>
                    <div class="bin marks"
                         onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?pagetag=jcsb'">
                        <div class="lab-pic4"></div>
                        结果公布
                    </div>
                    <div class="transaction" style="margin-top: 18px;">
                        <div class="labeling">我要出书</div>
                        <div class="binone consol"
                             onclick="window.location.href='${ctx}/bookdeclare/toBookdeclareAdd.action'">
                            <div class="lab-pic5"></div>
                            医学专著
                        </div>
                        <div class="binone marks"
                             onclick="window.location.href='${ctx}/bookdeclare/toBookdeclareAdd.action'">
                            <div class="lab-pic6"></div>
                            科普图书
                        </div>
                        <div class="binone consol"
                             onclick="window.location.href='${ctx}/bookdeclare/toBookdeclareAdd.action'">
                            <div class="lab-pic7"></div>
                            创新教材
                        </div>
                        <div class="binone marks"
                             onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?pagetag=wycs'">
                            <div class="lab-pic8"></div>
                            进度查询
                        </div>
                    </div>
                    <div class="transaction" style="margin-top: 8px;">
                        <div class="labeling">交互服务</div>
                        <div class="bintwo consol" onclick="window.location.href='${ctx}/group/list.action'">
                            <div class="lab-pic9"></div>
                            医学小组
                        </div>
                        <div class="bintwo marks"
                             onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?pagetag=wdjc'">
                            <div class="lab-pic10"></div>
                            图书纠错
                        </div>
                        <div class="bintwo consol" onclick="window.location.href='${ctx}/survey/surveyList.action'">
                            <div class="lab-pic11"></div>
                            问卷调查
                        </div>
                        <div class="bintwo marks"
                             onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">
                            <div class="lab-pic12"></div>
                            经验交流
                        </div>
                    </div>
                </div>
            </div>
            <div class="notice area-2">
                <div class="title" style="float: left">
                    <div class="line bgcolor-blue" style="float: left"></div>
                    <div class="name color-blue" style="float: left"><img src="${ctx}/statics/testfile/gg.png"
                                                                          style="margin-left: 30px;margin-top: 42px">
                    </div>
                </div>
                <c:forEach items="${listDou}" var="list" varStatus="status">

                    <%-- <c:if test="${status.index==0}">
                        <div class="content-left" style="margin-left: 30px">
                            <p class="content-size"><a href="${ctx}/message/noticeMessageDetail.action?cmsId=${list.id }&&materialId=${list.material_id}" class="astyle">${list.title}</a></p>
                            <p class="time-size">发布时间：<fmt:formatDate value="${list.gmt_create}" type="date" pattern="yyyy-MM-dd"/></p>
                            <div class="left_join"
                            onclick="window.location.href='${ctx}/material/toMaterialAdd.action?material_id=${list.material_id}'">报名参加</div>
                        </div>
                    </c:if> --%>
                    <%-- <c:if test="${status.index!=0}"> --%>
                    <div class="content-left">
                        <p class="content-size"><a
                                href="${ctx}/message/noticeMessageDetail.action?cmsId=${list.id }&&materialId=${list.material_id}&&notEnd=${list.notEnd}&&is_material_entry=${is_material_entry}"
                                class="astyle">${list.title}</a></p>
                        <p class="time-size">发布时间：<fmt:formatDate value="${list.gmt_create}" type="date"
                                                                  pattern="yyyy-MM-dd"/></p>
                        <input type="hidden" value="${list.notEnd}">
                        <c:if test="${list.notEnd ==1 and list.is_material_entry==true and list.isapply=='no' }">
                            <div class="left_join"
                                 onclick="window.location.href='${ctx}/material/toMaterialAdd.action?material_id=${list.material_id}'">
                                报名参加
                            </div>
                        </c:if>
                        <c:if test="${list.notEnd ==0 and list.is_material_entry==true}">
                            <div class="left_join end">已结束</div>
                        </c:if>
                    </div>

                    <%-- </c:if> --%>
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
                            <p class="content-size"><a href="${ctx}/inforeport/toinforeport.action?id=${list.id}"
                                                       class="astyle" style="color: #333333">${list.title}</a></p>
                            <p class="time-size">发布时间：<fmt:formatDate value="${list.gmt_create}" type="date"
                                                                      pattern="yyyy-MM-dd"/></p>
                        </div>
                    </c:if>
                </c:forEach>
                <div class="content">
                    <ul class="table">
                        <c:forEach items="${listNot}" var="list" varStatus="status">
                            <c:if test="${status.index!=0}">
                                <li><a href="${ctx}/inforeport/toinforeport.action?id=${list.id}" class="astyle"
                                       style="color: #333333">> ${list.title}</a></li>
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
                            <div class="all_left" onclick="window.location.href='${ctx}/community/tolist.action'">全部>>
                            </div>
                            <c:forEach items="${listM}" var="list" varStatus="status">
                                <div class="left_con1" onclick="todou('${list.mid}')">${list.title}</div>
                            </c:forEach>
                        </div>
                        <div class="left_two">
                            <div class="textbook_left">重点学科推荐</div>
                            <div class="all_left"></div>
                            <c:forEach var="ad3" items="${adInfo3.detailList}" varStatus="status">
                                <c:if test="${status.index==0}">
                                    <div class="p1_left">
                                        <a href="${adInfo3.url}"><img src="${ctx}/image/${ad3.image}.action" style="width: 216px;height: 89px;border-radius: 5px"></a>
                                    </div>
                                </c:if>
                                <c:if test="${status.index==1}">
                                    <div class="p2_left">
                                        <a href="${adInfo3.url}"><img src="${ctx}/image/${ad3.image}.action" style="width: 216px;height: 89px;border-radius: 5px"></a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="right" id="homepagebook" style="float: right;">${homepagebook}</div>
                </div>
            </div>
            <div class="area-5">
                <c:forEach var="ad" items="${adInfo2.detailList}">
                    <div class="item">
                        <img src="${ctx}/image/${ad.image}.action" height="82" width="285" class="book1">
                    </div>
                </c:forEach>
            </div>
            <div class="area-6">
                <div class="doc"></div>
                <div class="title">
                    <span class="doc_content">医学随笔</span>
                    <span class="total" style="cursor: pointer;"
                          onclick="window.location.href='${ctx}/cms/list.action'">全部>></span>
                </div>
                <c:forEach items="${listArt}" var="list" varStatus="status">
                    <c:if test="${status.index==0}">
                        <div class="item1"
                             onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                            <div class="big">
                                <c:choose>
                                    <c:when test="${list.is_promote!=false}">
                                        <div class="small"><span class="recommend">推荐</span></div>
                                    </c:when>
                                </c:choose>
                                <div class="pc1"><img  src="${ctx}/${list.cover}" /></div>
                                <div class="tt"><span class="a6_content">${list.title}</span></div>
                                <div class="a6_div2">${list.summary}</div>
                                <div>
                                    <div class="a6_head_div">
                                        <c:if test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">
                                            <img src="${ctx}/statics/image/default_image.png" class="a6_head"></c:if>
                                       <c:if test="${!(list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null)}">
                                            <img src="${ctx}/image/${list.avatar}.action" class="a6_head"></c:if>
                                    </div>
                                    <div class="a6_name_div" style="cursor:pointer;"
                                         onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${list.userId }'">
                                        <span>${list.realname}</span></div>
                                    <div class="a6_time_div"><span>${list.gmt_create}</span></div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach items="${listArt}" var="list" varStatus="status">
                    <c:if test="${status.index!=0}">
                        <div class="item"
                             onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                            <div class="big">
                                <c:choose>
                                    <c:when test="${list.is_promote!=false}">
                                        <div class="small"><span class="recommend">推荐</span></div>
                                    </c:when>
                                </c:choose>
                                <div class="pc1"><img  src="${ctx}/${list.cover}"></div>
                                <div class="tt"><span class="a6_content">${list.title}</span></div>
                                <div class="a6_div2">${list.summary}</div>
                                <div>
                                    <div class="a6_head_div">

                                        <c:if test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">
                                            <img src="${ctx}/statics/image/default_image.png" class="a6_head"></c:if>
                                        <c:if test="${!(list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null)}">
                                            <img src="${ctx}/image/${list.avatar}.action" class="a6_head"></c:if>
                                    </div>
                                    <div class="a6_name_div" style="cursor:pointer;"
                                         onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${list.userId }'">
                                        <span>${list.realname}</span></div>
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
                        <div class="${status.index==0?'author1':'author2'}">
                            <div class="a7_head_div">
                                <c:choose>
                                    <c:when test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">
                                        <img src="${ctx}/statics/image/default_image.png" class="a6_head">
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${ctx}/image/${list.avatar}.action" class="a6_head">
                                    </c:otherwise>
                                </c:choose>
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
                                <c:choose>
                                    <c:when test="${list.status == null }">
                                        <span class="friend add" title="申请加为好友！"
                                              onclick="addFriendfun(${list.id},'${list.realname}',0)"
                                              id="friend${list.id}"><B>+</B>好友</span>
                                    </c:when>
                                    <c:when test="${list.status  == 2 }">
                                        <span class="friend isFriend" title="已是您的好友！"
                                              id="friend${list.id}"><B>好友</B></span>
                                    </c:when>
                                    <c:when test="${list.status == 0 && list.isBeenRequest==1}">
                                        <span class="friend isBeenRequest" title="对方也想加您为好友，点击马上成为好友！"
                                              onclick="addFriendfun(${list.id},'${list.realname}',2)"
                                              id="friend${list.id}"><B>+</B>好友</span>
                                    </c:when>
                                    <c:when test="${list.status == 0 && list.hasRequest==1}">
                                        <span class="friend hasRequest" title="已申请加为好友，请等待对方同意。"
                                              id="friend${list.id}"><B>+</B>好友</span>
                                    </c:when>
                                </c:choose>
                                    <%-- <span class="friend" onclick="addfriend('${list.id}')" id="friend${list.id}"><B>+</B>好友</span> --%>
                            </div>
                        </div>
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
                                        <span style="float: left;">${list.nickname} 评论了 《${list.bookname}》</span>
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
                                             class="book_style" id="right_book${status.count}">
                                    </div>
                                    <div class="num${status.index>2?'4':status.index+1+''}">
                                        <div class="num_book">${status.index+1}</div>
                                    </div>
                                    <div class="last_right_content">
                                        <div class="sale_book" id="sale_book${status.count}">${list.bookname}</div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
        <c:if test="${countSurvey>0}">
            <div class="test" id="test_float" onclick="tosurvey()">
                <div class="cancel" onclick="cancel()">关闭</div>
            </div>
        </c:if>

    </div>
    <jsp:include page="/pages/comm/tail.jsp">
        <jsp:param name="linked" value="linked"></jsp:param>
    </jsp:include>
</div>

<script>
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?c8927680e561788c668c5e891dfe322c";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>



</html>


