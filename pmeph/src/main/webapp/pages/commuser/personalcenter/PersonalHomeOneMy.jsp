<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath();%>
<html>
<head>
<script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/personalcenter/PersonalHome.css" type="text/css">
        <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js"></script>
        <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/commuser/personalcenter/PersonalHomeOneMy.js"></script>
  <script src="${ctx}/resources/comm/base.js"></script>


</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">
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
            </div>
            <div class="headae">
                <img src="${ctx}/statics/image/tx.png" alt="头像" height="164" width="165">
            </div>
        </div>

        <div class="content">
            <div class="left">
                <ul class="dhl">
                    <li id="dt" class="dtl"><a class="aher" href="${ctx}/personalhomepage/tohomepage.action">动态</a></li>
                    <li id="jcsb" class="xz"><a class="aher" href="${ctx}/personalhomepage/tohomepageone.action">教材申报</a></li>
                    <li id="sbwz" class="dtl"><a class="aher" href="${ctx}/personalhomepage/tohomepagetwo.action">随笔文章</a></li>
                    <li id="zxsp" class="dtl"><a class="aher" href="${ctx}/personalhomepage/tohomepagethe.action">最新书评</a></li>
                </ul>
                <div id="dhxian"></div>
               
                <div id="jiaocaishenbao">
                    <div class="jcsbssl">
                        <span id="jcsbqbmy"  onclick="listoction();">全部</span><span id="jcsbwdsbmy">我的申报</span><span class="jcsbsbzt"  onclick="listoction1();">正在进行</span><span
                            class="jcsbsbzt"  onclick="listoction2();">已结束</span>
                        <span class="jcsbsbzt"  onclick="listoction3();">暂存</span><span class="jcsbsbzt"  onclick="listoction4();">已提交</span><input type="text" id="wdsbssk" value="${serchbox}">
                    </div>
                    <div class="shenbaoliebiao">
                        <ul class="scul">
                            <c:forEach items="${listbookjoins}" begin='0' end='7' var="list" varStatus="status">
                                <li class="leftlb">

                                    <c:if test="${list.iamin==0}">
                                        <div class="mleft">
                                            <div class="ysbrs">
                                                <div class="baomingrenshu"><span
                                                        class="canjiarenshu">${list.exmember}</span><span>人</span>
                                                    <br/><span class="ybmcj">已报名参加</span></div>
                                            </div>
                                            <div class="bmcj">
                                                <div class="bmcjan"><span><a class="abmcj" href="${ctx}/material/toMaterialAdd.action?material_id=${list.material_id}">报名参加</a></span></div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${list.iamin==1&&list.online_progress==1}">
                                        <div class="mleft1">
                                            <div class="ysbrs1">
                                                <div class="baomingrenshu1"><span
                                                        class="canjiarenshu1">${list.exmember}</span><span>人</span>
                                                    <br/><span class="ybmcj1">已报名参加</span></div>
                                            </div>
                                            <div class="bmcj1">
                                                <div class="bmcjan1"><span>进度查询</span></div>
                                                <div class="cksbb1">查看申报表</div>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${list.iamin==1&&list.online_progress==2}">
                                        <div class="mleft2">
                                            <div class="ysbrs2">
                                                <div class="baomingrenshu2"><span
                                                        class="canjiarenshu2">${list.exmember}</span><span>人</span>
                                                    <br/><span class="ybmcj2">已报名参加</span></div>
                                            </div>
                                            <div class="bmcj2">
                                                <div class="bmcjan2"><span>查看申报表</span></div>
                                            </div>
                                        </div>
                                    </c:if>


                                    <div class="mright">
                                        <div class="rshang">
                                            <div class="rshangn">${list.material_name}
                                           <c:if test="${list.is_staging==1}"><img src="${ctx}/statics/image/zancun.png"></c:if></div>
                                        </div>
                                        <div class="rxia">
                                            <div class="rxian">截止日期：${list.age_deadline}</div>
                                        </div>
                                    </div>

                                </li>
                            </c:forEach>

                            <li class="rightlb">
                                <div class="mleft1">
                                    <div class="ysbrs1">
                                        <div class="baomingrenshu1"><span class="canjiarenshu1">137</span><span>人</span>
                                            <br/><span class="ybmcj1">已报名参加</span></div>
                                    </div>
                                    <div class="bmcj1">
                                        <div class="bmcjan1"><span>进度查询</span></div>
                                        <div class="cksbb1">查看申报表</div>
                                    </div>
                                </div>

                                <div class="mright">
                                    <div class="rshang">
                                        <div class="rshangn">全国高等学校本科应用心理学专业第三轮规划教材</div>
                                    </div>
                                    <div class="rxia">
                                        <div class="rxian">截止日期： 2017.12.13</div>
                                    </div>
                                </div>


                            </li>
                            <!-- </ul>
                         </div>-->

                            <!--<div class="shenbaoliebiao">
                                <ul class="scul">-->
                            <li class="leftlb">

                                <div class="mleft2">
                                    <div class="ysbrs2">
                                        <div class="baomingrenshu2"><span class="canjiarenshu2">360</span><span>人</span>
                                            <br/><span class="ybmcj2">已报名参加</span></div>
                                    </div>
                                    <div class="bmcj2">
                                        <div class="bmcjan2"><span>查看申报表</span></div>
                                    </div>
                                </div>

                                <div class="mright">
                                    <div class="rshang">
                                        <div class="rshangn">全国高等学校五年制临床医学专业第九轮规划材料<img
                                                src="${ctx}/statics/image/zancun.png"></div>
                                    </div>
                                    <div class="rxia">
                                        <div class="rxian">截止日期： 2017.12.31</div>
                                    </div>
                                </div>

                            </li>

                            <li class="rightlb">
                                <div class="mleft3">
                                    <div class="ysbrs3">
                                        <div class="baomingrenshu3"><span class="canjiarenshu3">531</span><span>人</span>
                                            <br/><span class="ybmcj3">已报名参加</span></div>
                                    </div>
                                    <div class="bmcj3">
                                        <div class="bmcjan3"><span>已结束</span></div>
                                    </div>
                                </div>

                                <div class="mright">
                                    <div class="rshang">
                                        <div class="rshangn">全国高等学校五年制临床医学专业第九轮规划材料<img
                                                src="${ctx}/statics/image/zancun.png"></div>
                                    </div>
                                    <div class="rxia">
                                        <div class="rxian">截止日期： 2017.11.21</div>
                                    </div>
                                </div>

                            </li>

                        </ul>
                        
                                            
                    </div>
                    
                    
                    


                </div>


                

            </div>


            <div class="right">
                <div id="wdsc"><span id="wdscx"></span> <span class="rlan">我的收藏</span> <span id="hyp">换一批</span><span
                        id="jiantou"></span>
                    <br/>
                    <ul class="scul">
                        <c:forEach items="${listmycol}" begin='0' end='5' var="list" varStatus="status">
                            <li class="sclb"><img src="${ctx}/statics/image/sp_01.png" class="sctp">${list.book_name}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="wdhy"><span id="hytb"></span> <span class="rlan">我的好友</span> <span
                        id="qbhy">全部好友>>&nbsp;</span>
                    <br/>
                    <ul class="scul">
                        <c:forEach items="${listmyfriend}" begin='0' end='11' var="listmyf" varStatus="status">
                            <li class="hylb"><img src="${ctx}/statics/image/haoyoutouxiang1.png"
                                                  class="hytp">${listmyf.realname}</li>
                        </c:forEach>
                    </ul>
                </div>
                <div id="wdxz"><span id="xztb"></span><span class="rlan">我加入的小组</span><span
                        id="qbhy">全部小组>>&nbsp;</span>
                    <br/>
                    <ul class="scul">
                        <c:forEach items="${listmygroup}" begin='0' end='8' var="listmyg" varStatus="status">
                            <li class="wdxz"><img src="${ctx}${listmyg.group_image}"
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