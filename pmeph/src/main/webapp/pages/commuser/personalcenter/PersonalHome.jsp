<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String path = request.getContextPath();%>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/personalcenter/PersonalHome.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/commuser/personalcenter/PersonalHome.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>


</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">

	<!-- 隐藏域 -->
	<input type="hidden" id="pageTag" value="${pageTag }">
	


    <div class="content-wrapper">
        <div class="wrapper">
            <div class="myinfo">
                <div class="headr">
                    <div id="name">${permap.realname}</div>
                    <c:if test="${permap.sex==1}">
                        <div id="mansex"></div>
                    </c:if>
                    <c:if test="${permap.sex==2}">
                        <div id="sex"></div>
                    </c:if>
                    <br/>
                    <br/>
                    <span id="grqm"><c:if test="${permap.signature == null}">暂无个性签名</c:if>${permap.signature}</span>
                </div>
                <br/>
                <c:if test="${permap.rank==0}"><span id="zjrz"></span><span class="grsx">普通用户</span></c:if>
                <c:if test="${permap.rank==1}"><span id="zjrz"></span><span class="grsx">教师用户</span></c:if>
                <c:if test="${permap.rank==2}"><span id="zjrz"></span><span class="grsx">作家用户</span></c:if>
                <c:if test="${permap.rank==3}"><span id="zjrz"></span> <span class="grsx">专家用户</span></c:if>
                <a href="<c:url value="/userinfo/touser.action"/>"><span id="zhsz"></span><span class="grsx">账户设置</span></a>
            </div>
            <div class="headae">
                <c:if test="${permap.avatar=='DEFAULT'}"><img src="${ctx}/statics/image/tx.png" alt="头像" height="164"
                                                              width="165"></c:if>
                <c:if test="${permap.avatar!='DEFAULT'}"><img src="<%=path %>/image/${permap.avatar}.action" alt="头像" height="164"
                                                              width="165"></c:if>
            </div>
        </div>

        <div class="content">
            <div class="left">
                <ul class="dhl">
                    <li id="dt" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepage.action">动态</a></li>
                    <li id="jcsb" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepageone.action">教材申报</a></li>
                    <li id="sbwz" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepagetwo.action">随笔文章</a></li>
                    <li id="tsjc" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepage.action?pagetag=tsjc">图书纠错</a></li>
                    <li id="wycs" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepage.action?pagetag=wycs">我要出书</a></li>
					<li id="wdjc" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepage.action?pagetag=wdjc">我的纠错</a></li>
                    <li id="wdpl" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepagethe.action">我的评论</a></li>
                    <li id="wdwj" class="dtl pagetag"><a class="aher" href="${ctx}/personalhomepage/tohomepage.action?pagetag=wdwj">我的问卷</a></li>
                    <%-- <li id="zxsp" class="dtl"><a class="aher"
                                                 href="${ctx}/personalhomepage/tohomepagethe.action">最新书评</a></li> --%>
                    <li id="wycs" class="dtl"><a class="aher"
                                                 href="${ctx}/personalhomepage/toBookList.action">我要出书</a></li>
                </ul>
                <div id="dhxian"></div>

                <div id="dongtai">

                    <c:forEach items="${newMessages}" var="listmon">

                        <c:if test="${listmon.msg_type=='1'}">
                            <div class="xiaoxi">
                                <div class="dtshang">
                                    <c:if test="${listmon.online_progress==3}">
                                        <div class="shangleft">通过了审核</div>
                                    </c:if>
                                    <c:if test="${listmon.online_progress==2}">
                                        <div class="shangleft">退回了选题</div>
                                    </c:if>
                                    <div class="shangright">${listmon.gmt_update}</div>
                                </div>
                                <div class="dtzhong">
                                    <c:if test="${listmon.online_progress==3}">
                                        <span class="rlan"> <span id="xinxiaoxi"></span><span id="xiaoxitongguo"></span>恭喜！您于${listmon.gmt_create}申报的《${listmon.material_name}》编委审核已通过。</span>
                                    </c:if>
                                    <c:if test="${listmon.online_progress==2}">
                                        <span class="rlan"><span id="xinxiaoxi"></span><span id="xiaoxituihui"></span>非常抱歉！您于${listmon.gmt_create} 提交的《${listmon.material_name}》选题被退回，请您尽快检查修改！</span>
                                    </c:if>
                                </div>
                                <div class="dtxia"></div>
                                <div id="tzxian"></div>
                            </div>

                        </c:if>

                        <c:if test="${listmon.msg_type=='2'}">

                            <div class="xiaoxi">
                                <div class="dtshang">
                                    <div class="shangleft">发表了随笔文章</div>
                                    <span id="xiewenzhang"></span>
                                    <div class="shangright">${listmon.gmt_update}</div>
                                </div>
                                <div class="dtzhong">
                                    <div id="suibiwenzhangtp"></div>
                                    <div id="suibiwenzhanneirong">
                                        <div id="shenhezhong"></div>
                                        <div class="suibibt">
                                            <c:if test="${listmon.auth_status==0}"><span
                                                    id="shenhezhongwd"></span></c:if>
                                            <c:if test="${listmon.auth_status==1}"><span
                                                    id="shenheweitonguo"></span></c:if>
                                            <c:if test="${listmon.auth_status==2}"><span
                                                    id="shenheyitongguo"></span></c:if>
                                            <span class="suibibiaoti">${listmon.title}</span></div>
                                        <br/>
                                        <span class="suibineirong">${listmon.summary}</span>
                                    </div>
                                </div>
                                <div class="dtxia">
                                    <c:if test="${listmon.auth_status == 1&&listmon.auth_status == 0}">
                                        <span id="bianji"></span>
                                        <span class="dtxiawz"><a
                                                herf="${ctx}/writerArticle/initWriteArticle.action?id=${listmon.id}&&userid=${permap.id}">编辑</a></span>
                                    </c:if>
                                    <span id="sanchu"></span><span class="dtxiawz"
                                                                   onclick="DelMyWriter('${listmon.id}');">删除</span>
                                </div>
                                <div id="tzxian"></div>
                            </div>

                        </c:if>


                        <c:if test="${listmon.msg_type=='3'}">
                            <div class="xiaoxi">
                                <div class="dtshang">
                                    <div class="shangleft">发表了评论</div>
                                    <div class="shangright">${listmon.gmt_update}</div>
                                </div>
                                <div class="dtzhong">
                                    <div id="suibiwenzhangtp"></div>
                                    <div id="suibiwenzhanneirong">
                                        <div id="pingluntu"><c:if test="${listmon.image_url=='DEFAULT'}"><img
                                                src="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"
                                                class="dtpltpl"></c:if>
                                            <c:if test="${listmon.image_url!='DEFAULT'}"><img src="${listmon.image_url}"
                                                                                              class="dtpltpl"></c:if>
                                        </div>
                                        <div class="pinlunbt"><span class="suibibiaoti">${listmon.book_name}</span>
                                            <br/><span
                                                    class="pinglunlr">${permap.realname}评论了 《${listmon.book_name}》</span>
                                            <br/>${listmon.score}
                                            <span class="pinlunxinji"></span><span class="pinlunxinji"></span><span
                                                    class="pinlunxinji"></span><span class="pinlunxinjihei"></span>
                                        </div>
                                        <br/>
                                        <div class="pinlunbt"><span class="suibineirong">${listmon.content}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="dtxia">
                                    <span id="sanchu"></span><span class="dtxiawz"
                                                                   onclick="DelMyBookWriter('${listmon.id}');">删除</span>
                                </div>
                                <div id="tzxian"></div>
                            </div>
                        </c:if>

                    </c:forEach>

                    <c:if test="${fn:length(newMessages)>0}">
                        <div class="jiazaigengduo" id="jiazaigengduo"><span style="cursor:pointer">加载更多……</span></div>
                    </c:if>

                    <c:if test="${fn:length(newMessages)==0}">
                        <div class="no-more">
                            <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                            <span>木有内容呀~~</span>
                        </div>
                    </c:if>
                    
                </div>


            </div>


            <div class="right">
                <div id="wdsc"><span id="wdscx"></span> <span class="rlan">我的收藏</span> <span id="hyp">换一批</span><span
                        id="jiantou"></span>
                    <br/>
                    <ul class="scul">
                        <c:forEach items="${listmycol}" begin='0' end='5' var="list" varStatus="status">
                            <li class="sclb">
                                <div class="sctpdiv">
                                    <c:if test="${list.image_url=='DEFAULT'}"><img
                                            src="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"
                                            class="sctp"></c:if>
                                    <c:if test="${list.image_url!='DEFAULT'}"><img src="${list.image_url}"
                                                                                   class="sctp"></c:if>
                                </div>
                                    ${list.book_name}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="wdhy"><span id="hytb"></span> <span class="rlan">我的好友</span> <span
                        id="qbhy"><a href="${ctx}/myFriend/listMyFriend.action" class="aright">全部好友>>&nbsp;</a></span>
                    <br/>
                    <ul class="scul">
                        <c:forEach items="${listmyfriend}" begin='0' end='11' var="listmyf" varStatus="status">
                            <li class="hylb">
                                <div class="hytxdiv">
                                    <c:if test="${listmyf.avatar=='DEFAULT'}"><img
                                            src="${ctx}/statics/image/haoyoutouxiang1.png" class="hytp"></c:if>
                                    <c:if test="${listmyf.avatar!='DEFAULT'}"><img src="${list.image_url}"
                                                                                   class="hytp"></c:if>
                                </div>
                                    ${listmyf.realname}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="wdxz"><span id="xztb"></span><span class="rlan">我加入的小组</span><span
                        id="qbhy"><a href="${ctx}/group/list.action" class="aright">全部小组>>&nbsp;</a></span>
                    <br/>
                    <ul class="scul">
                        <c:forEach items="${listmygroup}" begin='0' end='8' var="listmyg" varStatus="status">
                            <li class="wdxz"><img src="${listmyg.group_image}"
                                                  class="xztp"><br/>${listmyg.group_name}<br/><span
                                    class="xzrs">(${listmyg.grouppeo}人)</span></li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="bzzx">
                    <div id="bzxxherd"></div>
                    <span class="bzzxwz">帮助中心</span>
                    <div id="bzxxherd2"></div>
                    <input type="text" id="bzzxcxk" value="请输入您要咨询的问题">
                    <ul class="scul">
                        <li class="bzzxlb"><span id="dianhua"></span><span class="zzfw">自助服务</span></li>
                        <li class="bzzxlb"><span id="shou"></span><span class="zzfw">投诉举报</span></li>
                        <li class="bzzxlb"><span id="kefu"></span><span class="zzfw">我的客服反馈</span></li>
                    </ul>
                </div>
            </div>
            <div style="clear: both"></div>
        </div>
    </div>
</div>
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>