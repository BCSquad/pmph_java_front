<%@ page import="java.util.Map" %>
<%@ page import="com.bc.pmpheep.back.util.Const" %><%--
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
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>人卫E教平台</title>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/homepage/homepage.css?t=${_timestampJKFYDiv_0}" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.scroll.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/menu.js?t=${_timestamp}"></script>

    <script src="${ctx}/resources/commuser/homepage/homepage.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<input type="hidden" id="auto_play" value="${adInfo1.auto_play}">
<input type="hidden" id="animation_interval" value="${adInfo1.animation_interval}">
<input type="hidden" id="is_org_user" value="${userInfo.is_org_user}">
<div class="body">
    <div class="content-wrapper">
        <div class="area-1">
            <div class="banner">
                <!--广告轮播区域-->
                <div class="move" id="move">
                    <ul>
                        <c:forEach var="ad" items="${adInfo1.detailList}">
                            <li><img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${ad.image_jump_url}')"  style="width: 922px; height: 380px"/></li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="ctrl" id="ctrl"></div>
            </div>
            <div class="op-link">
                <div class="transaction">
                    <div class="labeling">教材申报</div>
                    <div class="bin consol" onclick="window.location.href='${ctx}/research/tolist.action'">
                        <div class="lab-pic1"></div>
                        调研表
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

                        <c:if test="${userInfo.is_org_user==1}">
                            <div class="binone consol" style="pointer-events: none;background-color: gray"
                                 onclick="window.location.href='${ctx}/bookdeclare/toBookdeclareAdd.action'">
                                <div class="lab-pic5"></div>
                                医学专著
                            </div>
                            <div class="binone marks" style="pointer-events: none;background-color: gray"
                                 onclick="window.location.href='${ctx}/bookdeclare/toBookdeclareAdd.action'">
                                <div class="lab-pic6"></div>
                                科普图书
                            </div>
                            <div class="binone consol" style="pointer-events: none;background-color: gray"
                                 onclick="window.location.href='${ctx}/bookdeclare/toBookdeclareAdd.action'">
                                <div class="lab-pic7"></div>
                                创新教材
                            </div>
                        </c:if>

                        <c:if test="${userInfo.is_org_user!=1}">
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
                        </c:if>


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
        </div>
        <div class="notice area-2">
            <div class="title" style="float: left">
                <div class="line bgcolor-blue" style="float: left"></div>
                <div class="name color-blue" style="float: left">
                    <div class="ggpng"style="margin-left: 30px;margin-top: 42px"></div>
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
                            href="${ctx}/message/noticeMessageDetail.action?cmsId=${list.id }&&materialId=${list.material_id}&&notEnd=${list.notEnd}&&is_material_entry=${is_material_entry}&&tag=homepage"
                            class="astyle">${list.title}</a></p>
                    <p class="time-size">截止日期：<fmt:formatDate value="${list.deadline}" type="date"
                                                              pattern="yyyy-MM-dd"/></p>
                    <input type="hidden" value="${list.notEnd}">

                    <%-- <c:if test="${list.notEnd ==1 and list.is_material_entry==true and list.declaration_id==null }">
                        <div class="left_join"
                             onclick="window.location.href='${ctx}/material/toMaterialAdd.action?material_id=${list.material_id}'">
                            报名参加
                        </div>
                    </c:if>
                    <c:if test="${list.notEnd ==1 and list.is_material_entry==true and list.declaration_id != null and list.dec_editable==1 }">
                        <div class="left_join"
                             onclick="window.location.href='${ctx}/material/toMaterialZc.action?declaration_id=${list.declaration_id}'">
                            报名参加
                        </div>
                    </c:if>
                    <c:if test="${list.notEnd ==1 and list.is_material_entry==true and list.declaration_id != null and list.dec_editable==0}">
                        <div class="left_join" onclick="window.location.href='${ctx}/material/showMaterial.action?declaration_id=${list.declaration_id}'">
                            报名参加
                        </div>
                    </c:if>
                    <c:if test="${list.notEnd ==0 and list.is_material_entry==true}">
                        <div class="left_join end">报名结束</div>
                    </c:if> --%>

                    <%
                        Map<String, Object> userInfo = null;
                        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                            userInfo = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
                        }

                        if (userInfo == null || userInfo.isEmpty()) {
                            request.setAttribute("userInfo", null);
                        } else {
                            request.setAttribute("userInfo", userInfo);
                        }
                    %>
                    <c:choose>
                    	<c:when test="${list.is_material_entry!=true}"></c:when>
                    	<c:when test="${list.notEnd ==0 and list.is_material_entry==true}">
                    		<div class="left_join end">报名结束</div>
                    	</c:when>

                    	<c:otherwise>
                            <c:if test="${userInfo.is_org_user==1}">
                                    <div class="left_join" style="pointer-events: none;background-color: gray"
                                         onclick="window.location.href='${ctx}/material/MaterialDetailRedirect.action?material_id=${list.material_id}'">
                                        报名参加
                                    </div>
                                </c:if>

                            <c:if test="${userInfo.is_org_user!=1}">
                                <div class="left_join"
                                     onclick="window.location.href='${ctx}/material/MaterialDetailRedirect.action?material_id=${list.material_id}'">
                                    报名参加
                                </div>
                            </c:if>


                    	</c:otherwise>
                    </c:choose>
                    
                    
                </div>

                <%-- </c:if> --%>
            </c:forEach>
            <div class="more" onclick="top.location='${ctx}/cmsnotice/tolist.action'">更多>></div>
        </div>
        <div class="notice area-2-1" style="width: 50.1%;display: inline-block;margin-top: 10px">
            <div class="bgcolor-blue" style="float: left;width: 7px;height: 100%"></div>
            <div class="lcjc-img"></div>
            <div class="lcjc">
                <div class="lcjc-a" style="margin-left: 10px">
                    <div class="lcjc-1" onclick="todeclaredetail(1)"></div>
                    <div class="lcjc-n">人卫临床助手</div>
                </div>
                <div class="lcjc-a">
                    <div class="lcjc-2" onclick="todeclaredetail(2)"></div>
                    <div class="lcjc-n">人卫用药助手</div>
                </div>
                <div class="lcjc-a">
                    <div class="lcjc-3" onclick="todeclaredetail(3)"></div>
                    <div class="lcjc-n">人卫中医助手</div>
                </div>
            </div>
        </div>
        <div class="notice area-2-2" style="width: 49.5%;display: inline-block;float: right;margin-top: 10px">
            <div class="bgcolor-blue" style="float: left;width: 7px;height: 100%"></div>
            <div class="lcjc-img-1"></div>
            <div style="height: 100%">
                <c:forEach items="${listSzpt}" var="list">
                    <div class="szpt" onclick="toszptdetail(${list.activity_id})">
                        <div class="szpt-top">
                            <c:choose>
                                <c:when test="${list.cover == '' || list.cover == 'DEFAULT' || list.cover == null}">
                                    <img src="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
                                </c:when>
                                <c:otherwise>
                                    <img src="${ctx}/image/${list.cover}.action">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="szpt-buttom">
                            ${list.activity_name}
                        </div>
                    </div>
                </c:forEach>
                <div class="szpt-more" onclick="toteacherPlatform()">更多>></div>
            </div>
        </div>
        <div class="notice area-3" style="margin-top: 5px">
            <div class="title">
                <div class="line bgcolor-blue"></div>
                <div class="name color-blue" style="width: 93px;height: 100%;float: left">
                    <div class="xxkb" style="margin-left: 32px;margin-top: 15px"></div>
                </div>
            </div>
            <c:if test="${listNot[0].first_img_url != 'none' }">
                <div class="photo-size">
                    <img src="${listNot[0].first_img_url}">
                    <%-- <img src="${ctx}/image/${listNot[0].first_img_url}.action"> --%>
                        <%-- <img src="${ctx}/statics/testfile/content.png"> --%>
                </div>
            </c:if>
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
            <div class="more" onclick="top.location='${ctx}/cmsinfoletters/tolist.action'">更多>></div>
        </div>
        <div class="area-4">
            <div class="content">
                <div class="tab-bar" style="margin-top: 0px;">
                    <div class="newdoc"></div>
                    <div class="type_bar_float_right typebar doc_content" style="margin-left: 13px;margin-top: 10px">新书推荐</div>
                    <div class="page" style="width: auto;float: right;margin-right: 327px;margin-left: 10px;
                        height: 100%;color: #999999;">
                        <c:forEach items="${bookTypes}" var="type" varStatus="status">
                            <div class="new tab ${status.index==0?'active':''}" id="new${type.id}"
                                 onclick='searchXstjBook("${type.id}")'>${type.type_name}</div>
                            <c:if test="${bookTypes !=null && status.index==0}">
                                <input type="hidden" id="typeid" value="${type.id}">
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="left">
                    <div class="left_one">
                        <div class="textbook_left">教材社区</div>
                        <div class="all_left" onclick="window.location.href='${ctx}/community/tolist.action'">更多>>
                        </div>
                        <c:forEach items="${listM}" var="list" varStatus="status">
                            <%--<div class="left_con1" onclick="todou('${list.mid}','${list.material_id}','${list.id}')">${list.title}</div>--%>
                            <div class="left_con1" onclick="todou('${list.id}')">${list.title}</div>
                        </c:forEach>
                    </div>
                    <div class="left_two">
                        <div class="textbook_left">重点学科推荐</div>
                        <div class="all_left" onclick="window.location.href='<c:url value="/books/promoteList.action?type=1" />'">更多>></div>
                        <c:forEach var="ad3" items="${adInfo6.detailList}" varStatus="status">
                            <c:if test="${status.index==0}">
                                <div class="p1_left">
                                    <c:if test="${adInfo6.type==0}">
                                        <a id="a5"><img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${adInfo6.url}')"
                                                        style="width: 216px;height: 89px;border-radius: 5px"></a>
                                    </c:if>
                                    <c:if test="${adInfo6.type==1}">
                                        <a id="a5"><img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${ad3.image_jump_url}')"
                                                        style="width: 216px;height: 89px;border-radius: 5px"></a>
                                    </c:if>

                                </div>
                            </c:if>
                        </c:forEach>
                        <c:forEach var="ad3" items="${adInfo7.detailList}" varStatus="status">
                            <c:if test="${status.index==0}">
                                <div class="p2_left">
                                    <c:if test="${adInfo7.type==0}">
                                        <a id="a6"><img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${adInfo7.url}')"
                                                                              style="width: 216px;height: 89px;border-radius: 5px"></a>
                                    </c:if>
                                    <c:if test="${adInfo7.type==1}">
                                        <a  id="a6"><img src="${ctx}/image/${ad3.image}.action" onclick="toImageUrl('${ad3.image_jump_url}')"
                                                                              style="width: 216px;height: 89px;border-radius: 5px"></a>
                                    </c:if>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
                <div class="content" id="JKFYDiv_0">${pMap}</div>
            </div>
        </div>
        <div class="tab-bar">
            <div class="type_bar_float_right">
                <c:forEach items="${bookTypes}" var="type" varStatus="status">
                    <div class="oldtab tab ${status.index==0?'active':''}" id="${type.id}"
                         onclick='chooseType("${type.id}")'>${type.type_name}</div>
                </c:forEach>
            </div>
            <!-- <div class="tab active" id="1" onclick='chooseType("1")'>学校教育</div>
            <div class="tab" id="2" onclick='chooseType("2")'>毕业后教育</div>
            <div class="tab" id="3" onclick='chooseType("3")'>继续教育</div>
            <div class="tab" id="4" onclick='chooseType("4")'>考试用书</div> -->
            <div class="page  asdf">
                <div class="page-num">
                    <input type="hidden" id="book_type">
                    <span class="beforepage" id="before">${thisrows}</span>/
                    <span class="beforepage" id="next">${allrows}</span>
                    <span class="icon-right" onclick='on("next")'></span>
                    <span class="icon-left" onclick='on("before")'></span>
                </div>
            </div>
            <c:forEach items="${listType}" var="list" varStatus="status">
                <%-- <c:if test="${status.index==0}">
                    <div class="type" id="typeOne">${list.type_name}</div>
                </c:if> --%>

                <c:if test="${status.index!=0}">
                    <div class="point"></div>
                </c:if>
                <div class="type"  id="type_${list.id}" onclick="chooseTypeSecond(${list.id})">${list.type_name}</div>


            </c:forEach>
            <%-- <c:forEach items="${listType}" var="list" varStatus="status">
                <c:if test="${status.index==1 or status.index==2 }">
                    <div class="point"></div>
                    <div class="type" id="typeTwo">${list.type_name}</div>
                </c:if>
            </c:forEach> --%>
        </div>
        <div class="right" id="homepagebook">${homepagebook}</div>
        <div class="area-5">
            <c:forEach var="ad" items="${adInfo2.detailList}">
                <div class="item">
                    <c:if test="${adInfo2.type==0}">
                    <a id="a1">
                        <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${adInfo2.url}')" height="82" width="285" class="book1">
                    </a>
                    </c:if>
                    <c:if test="${adInfo2.type==1}">
                        <a id="a1">
                            <img src="${ctx}/image/${ad.image}.action"  onclick="toImageUrl('${ad.image_jump_url}')" height="82" width="285" class="book1">
                        </a>
                    </c:if>
                </div>
            </c:forEach>
            <c:forEach var="ad" items="${adInfo3.detailList}">
                <div class="item">
                    <c:if test="${adInfo3.type==0}">
                    <a  id="a2">
                        <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${adInfo3.url}')" height="82" width="285" class="book1">
                    </a>
                    </c:if>
                    <c:if test="${adInfo3.type==1}">
                        <a  id="a2">
                            <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${ad.image_jump_url}')" height="82" width="285" class="book1">
                        </a>
                    </c:if>
                </div>
            </c:forEach>
            <c:forEach var="ad" items="${adInfo4.detailList}">
                <div class="item">
                    <c:if test="${adInfo4.type==0}">
                        <a  id="a3">
                            <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${adInfo4.url}')" height="82" width="285" class="book1">
                        </a>
                    </c:if>
                    <c:if test="${adInfo4.type==1}">
                        <a  id="a3">
                            <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${ad.image_jump_url}')" height="82" width="285" class="book1">
                        </a>
                    </c:if>
                </div>
            </c:forEach>
            <c:forEach var="ad" items="${adInfo5.detailList}">
                <div class="item">
                    <c:if test="${adInfo5.type==0}">
                        <a  id="a4">
                            <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${adInfo5.url}')" height="82" width="285" class="book1">
                        </a>
                    </c:if>
                    <c:if test="${adInfo5.type==1}">
                        <a  id="a4">
                            <img src="${ctx}/image/${ad.image}.action" onclick="toImageUrl('${ad.image_jump_url}')" height="82" width="285" class="book1">
                        </a>
                    </c:if>
                </div>
            </c:forEach>
        </div>
    </div>
    <div style="background-color: #F6F6F6;margin-top: 630px;padding-top: 30px;">
        <div class="content-wrapper">
            <div class="area-6">
                <div class="doc"></div>
                <div class="title">
                    <span class="doc_content">医学随笔</span>
                    <span class="total" style="cursor: pointer;"
                          onclick="window.location.href='${ctx}/cms/list.action'">更多>></span>
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
                                    </div>
                                    <div class="a6_name_div" style="cursor:pointer;"
                                         onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${list.userId }'">
                                        <span>文章来源：${list.author_name==null?list.realname:list.author_name}</span></div>
                                    <div class="a6_time_div"><span>${list.auth_date}</span></div>
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

                                        <%-- <c:if test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">
                                            <img src="${ctx}/statics/image/default_image.png" class="a6_head"></c:if>
                                        <c:if test="${!(list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null)}">
                                            <img src="${ctx}/image/${list.avatar}.action" class="a6_head"></c:if> --%>
                                    </div>
                                    <div class="a6_name_div" style="cursor:pointer;"
                                         onclick="window.location.href='${ctx}/personalhomepage/tohomepage.action?userId=${list.userId }'">
                                        <span>文章来源：${list.author_name==null?list.realname:list.author_name}</span></div>
                                    <div class="a6_time_div"><span>${list.auth_date}</span></div>
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
                                    <span class="a7_write" title="${list.realname}">${list.realname}</span>
                                </div>
                                    <%--    <br>
                                        <div class="a7_cont">
                                            <span class="a7_cont">${list.title}</span>
                                        </div>--%>
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
                        <div class="name">热门书评
                            <div onclick="window.location.href='${ctx}/homepage/morecomments.action'"  style="float: right;margin-right: 79%;color: #489299;font-size: 14px;cursor: pointer;">更多>></div>
                        </div>

                    </div>


                    <div class="items">
                        <c:forEach items="${listCom}" var="list" varStatus="status">
                            <div class="item1"
                                 onclick="window.open(contextpath+'readdetail/todetail.action?id=${list.id1}')">
                                <div class="sp_01"><img src="${list.image_url}"/></div>
                                <div class="sp_02">
                                    <div class="sp_title" title="${list.bookname}">${list.bookname}</div>
                                    <div style="float: left;" class="co">${list.nickname} 评论了 《${list.bookname}》</div>
                                    <div class="sp_pl">
                                        <c:if test="${list.score<=3}">
                                            <div class="star">
                                                <span class="yellow"></span>
                                                <span class="behind"></span>
                                                <span class="behind"></span>
                                                <span class="behind"></span>
                                                <span class="behind"></span>
                                            </div>
                                        </c:if>
                                        <c:if test="${list.score<=5 and list.score>3}">
                                            <div class="star">
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="behind"></span>
                                                <span class="behind"></span>
                                                <span class="behind"></span>
                                            </div>
                                        </c:if>
                                        <c:if test="${list.score<=7 and list.score>5}">
                                            <div class="star">
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="behind"></span>
                                                <span class="behind"></span>
                                            </div>
                                        </c:if>
                                        <c:if test="${list.score<=9 and list.score>7}">
                                            <div class="star">
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="behind"></span>
                                            </div>
                                        </c:if>
                                        <c:if test="${list.score>9}">
                                            <div class="star">
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                                <span class="yellow"></span>
                                            </div>
                                        </c:if>
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
                                    <input id="last_right_book_id_${status.count}" value="${list.id}" type="hidden">
                                    <div class="last_right_book"
                                         onclick="window.open(contextpath+'readdetail/todetail.action?id='+$('#last_right_book_id_${status.count}').val())">
                                        <c:set var="default_url"
                                               value="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"></c:set>
                                        <a title="${list.bookname}"><img src='${list.image_url == "DEFAULT"?default_url:list.image_url}'
                                                           class="book_style" id="right_book${status.count}" ></a>
                                    </div>
                                    <div class="num${status.index>2?'4':status.index+1+''}">
                                        <div class="num_book">${status.index+1}</div>
                                    </div>
                                    <div class="last_right_content"  style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;text-align: center">
                                        <div class="sale_book" id="sale_book${status.count}" title="${list.bookname}">${list.bookname}</div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>

    <c:if test="${countSurvey>0}">
        <div class="test" id="test_float" onclick="tosurvey()">
            <div class="cancel" onclick="cancel()">关闭</div>
        </div>
    </c:if>

    <!--公众号悬浮框-->
    <div id="EWM" onmouseover="showEWM()" onmouseout="hideEWM()" class="emw"><img src="${ctx}/statics/image/wxgzh.jpg"></div>
</div>
<jsp:include page="/pages/comm/tail.jsp">
    <jsp:param name="linked" value="linked"></jsp:param>
</jsp:include>


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


