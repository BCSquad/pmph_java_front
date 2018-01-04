<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String path = request.getContextPath();%>

<%@ page import="java.util.*"%> <!-- //获取系统时间必须导入的 --> 
<%@ page import="java.text.*"%> <!-- //获取系统时间必须导入的  -->
<% 
Calendar now = Calendar.getInstance();
now.add(Calendar.DAY_OF_MONTH, -3);
String datetime=new SimpleDateFormat("yyyyMMddHHmm").format(now.getTime()); //获取系统时间 
request.setAttribute("currentTime",datetime);
%>


<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
    <link rel="stylesheet" href="${ctx}/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/personalcenter/PersonalHome.css" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/commuser/personalcenter/PersonalHome.js"></script>
    <%-- <script src="${ctx}/resources/commuser/personalcenter/PersonalHomeJCSB.js"></script> --%>
    <script src="${ctx}/resources/comm/base.js"></script>


</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div class="body">

	<!-- 隐藏域 -->
	<input type="hidden" class="" id="pagetag" value="${pagetag }"><!-- 页签拼音缩写标记 -->
	<input type="hidden" class="" id="pageNum" value="${pageNum }">
	<input type="hidden" class="" id="maxPageNum" value="${maxPageNum }">
	<input type="hidden" class="" id="pageSize" value="${pageSize }">
	<input type="hidden" class="" id="pageType" value="${pageType }">
	
	<!-- 查询条件 -->
		<!-- 我要出书 -->
	<%-- <input type="hidden" class="queryCondition" id="queryName" value="${queryName }">
	<input type="hidden" class="queryCondition" id="auth_progress" value="${auth_progress }">
	<input type="hidden" class="queryCondition" id="is_staging" value="${is_staging }"> --%>
	
		<!-- 教材申报 -->
	<%-- <input type="hidden" class="queryCondition" id="s" value="${s }">
	<input type="hidden" class="queryCondition" id="pageinfo" value="${pageinfo }">
	<input type="hidden" class="queryCondition" id="dateinfo" value="${dateinfo }">
	<input type="hidden" class="queryCondition" id="online_progress" value="${online_progress }">
	<input type="hidden" class="queryCondition" id="bookname" value="${bookname }"> --%>

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
                    <li id="dt" class="dtl pagetag"><a class="aher paged" href="${ctx}/personalhomepage/tohomepage.action?pagetag=dt">动态</a></li>
                    <li id="jcsb" class="dtl pagetag"><a class="aher paged" >教材申报</a></li>
                    <li id="sbwz" class="dtl pagetag"><a class="aher paged">随笔文章</a></li>
                    <li id="tsjc" class="dtl pagetag"><a class="aher paged">图书纠错</a></li>
                    <li id="wycs" class="dtl pagetag"><a class="aher paged"  >我要出书</a></li>
					<li id="wdjc" class="dtl pagetag"><a class="aher paged" href="${ctx}/personalhomepage/tohomepage.action?pagetag=wdjc">我的纠错</a></li>
                    <li id="wdpl" class="dtl pagetag"><a class="aher paged" >我的评论</a></li>
                    <li id="wdwj" class="dtl pagetag"><a class="aher paged" >我的问卷</a></li>
                    <%-- <li id="zxsp" class="dtl"><a class="aher"
                                                 href="${ctx}/personalhomepage/tohomepagethe.action">最新书评</a></li> --%>
                </ul>
                <div id="dhxian"></div>

                <div id="leftContent">
                	${html }
                	
                	<link rel="stylesheet" href="${ctx}/statics/commuser/personalcenter/UserTrendst.css" type="text/css">
                	<%-- <script src="${ctx}/resources/commuser/personalcenter/PersonalHomeDT.js"></script> --%>
                	
                	
                	<div class="trendstListContent">
                		<c:forEach items="${List_map }" var="c">
                			<div class="trendstWrapper"> 
                			<c:choose>
                				<%-- 通过动态 --%>
               						<c:when test="${c.table_name == 'jcsb' && c.trendst_type == 1} "><%-- 教材申报 通过 --%>
               							<div class="issue_line"><span class="issue_name">通过了审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									恭喜！您于${c.jcsb_create_time}申报的《${c.jcsb_textbook_name}》${c.jcsb_chosen_position }审核已通过。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               							
               						</c:when>
               						<c:when test="${c.table_name == 'sbwz' && c.trendst_type == 1}"><%-- 随笔文章 通过 --%>
               							<div class="issue_line"><span class="issue_name">通过了审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									恭喜！您于${c.sbwz_create_time}发表的随笔文章《${c.sbwz_title}》审核已通过。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'tsjc' && c.trendst_type == 1}"><%-- 图书纠错 通过 --%>
               							<div class="issue_line"><span class="issue_name">通过了审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									${c.tsjc_realname} 于${c.tsjc_create_time}对您主编的图书《${c.tsjc_bookname}》第${c.tsjc_page }页${c.tsjc_line }提出的纠错：“${c.tsjc_content }”审核已通过。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wycs' && c.trendst_type == 1}"><%-- 我要出书 通过 --%>
               							<div class="issue_line"><span class="issue_name">通过了审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									恭喜！您于${c.wycs_create_time}提交的《${c.wycs_bookname}》选题审核已通过。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdjc' && c.trendst_type == 1}"><%-- 我要纠错 通过 --%>
               							<div class="issue_line"><span class="issue_name">通过了审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常感谢 ${c.wdjc_realname }！您于${c.wdjc_create_time}对图书《${c.wdjc_bookname}》第${c.wdjc_page }页${c.wdjc_line }提出的纠错：“${c.wdjc_content }”审核已通过。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdsp' && c.trendst_type == 1}"><%-- 我的书评 通过 --%>
                						<div class="issue_line"><span class="issue_name">通过了审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									恭喜！您于${c.wdsp_create_time}对《${c.wdsp_bookname}》的评价 “${c.wdsp_content }” 审核已通过。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdwj' && c.trendst_type == 1}"><%-- 我的问卷 通过 --%>
               							
               						</c:when>
                				
                				<%-- 未通过动态 --%>
                				
                					<c:when test="${c.table_name == 'jcsb' && c.trendst_type == 2}"><%-- 教材申报 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
            							<div class="msg_line">
            								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.jcsb_create_time}申报的《${c.jcsb_textbook_name}》${c.jcsb_chosen_position }审核未通过。
               								</div>		
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'sbwz' && c.trendst_type == 2}"><%-- 随笔文章 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.sbwz_create_time}发表的随笔文章《${c.sbwz_title}》审核未通过。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'tsjc' && c.trendst_type == 2}"><%-- 图书纠错 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									${c.tsjc_realname} 于${c.tsjc_create_time}对您主编的图书《${c.tsjc_bookname}》第${c.tsjc_page }页${c.tsjc_line }提出的纠错：“${c.tsjc_content }”审核未通过。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wycs' && c.trendst_type == 2}"><%-- 我要出书 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.wycs_create_time}提交的《${c.wycs_bookname}》选题被退回，请尽快检查修改！
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdjc' && c.trendst_type == 2}"><%-- 我要纠错 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉 ${c.wdjc_realname }！您于${c.wdjc_create_time}对图书《${c.wdjc_bookname}》第${c.wdjc_page }页${c.wdjc_line }提出的纠错：“${c.wdjc_content }”审核结果为无问题，仍然感谢您的关注。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdsp' && c.trendst_type == 2}"><%-- 我的书评 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.wdsp_create_time}对《${c.wdsp_bookname}》的评价 “${c.wdsp_content }” 审核未通过。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdwj' && c.trendst_type == 2}"><%-- 我的问卷 未通过 --%>
               							
               						</c:when>
                				
                				<%-- 发表动态 --%>
                				
                					<c:when test="${c.table_name == 'jcsb' && c.trendst_type == 0}"><%-- 教材申报 发表 --%>
               							<div class="issue_line"><span class="issue_name">申报了编写教材</span><span class="issue_time">${c.trendst_date }</span></div>
                						<div>您申报的《${c.jcsb_textbook_name}》${c.preset_position }已提交成功，请耐心等待遴选结果。</div>		
               						</c:when>
               						<c:when test="${c.table_name == 'sbwz' && c.trendst_type == 0}"><%-- 随笔文章 发表 --%>
               							<div class="issue_line"><span class="issue_name">发表了随笔文章<img></span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								<div class="img_wrapper"><img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"></div>
               								<div class="content_wrapper">
               									
               										<c:if test="${c.sbwz_auth_status ==0}"><div class="status_tag toAudit">待审核</div></c:if>
               										<c:if test="${c.sbwz_auth_status ==1}"><div class="status_tag reject">未通过</div></c:if>
               										<c:if test="${c.sbwz_auth_status ==2}"><div class="status_tag Audited">已通过</div></c:if>
               									
               									<div class="article_title">${c.sbwz_title }</div>
               									<div class="article_summary">${c.sbwz_summary }</div>
               								</div>
               							</div>
               							<div class="operate_wrapper">
	               							<a><div class="img img_edit" ></div><div>编辑</div></a> 
	               							<a><div class="img img_delete"></div><div>删除</div></a>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'tsjc' && c.trendst_type == 0}"><%-- 图书纠错 发表 --%>
               							<div class="issue_line"><span class="issue_name">收到了纠错</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								<div class="img_wrapper">
               									<c:if test="${c.tsjc_image_url==null || c.tsjc_image_url ==''||c.tsjc_image_url=='default'||c.tsjc_image_url=='DEFAULT' }">
               										<img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
               									</c:if>
               									<c:if test="${c.tsjc_image_url!=null && c.tsjc_image_url !=''&& c.tsjc_image_url!='default' && c.tsjc_image_url!='DEFAULT'}">
               										<img class="book_img" src="${c.tsjc_image_url}">
               									</c:if>
               								</div>
               								<div class="content_wrapper">
               									<div class="bookc_title">${c.tsjc_bookname }</div>
               									<div class="sub_title">${c.tsjc_realname } 纠正了《${c.tsjc_bookname }》第${c.tsjc_page }页${c.tsjc_line },提出纠错：“${c.tsjc_content }”。</div>
               									<div class="rank_stars"></div>
               									<div class="book_detail">${c.tsjc_detail }</div>
               								</div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wycs' && c.trendst_type == 0}"><%-- 我要出书 发表 --%>
               							<div class="issue_line"><span class="issue_name">提交了选题</span><span class="issue_time">${c.trendst_date }</span></div>
										<div class="msg_line">
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									您发起的《${c.wycs_bookname}》选题已提交，请耐心等待审核结果。
               								</div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdjc' && c.trendst_type == 0}"><%-- 我要纠错 发表 --%>
               							<div class="issue_line"><span class="issue_name">纠正了教材</span><span class="issue_time">${c.trendst_date }</span></div>
										<div class="content_line">
               								<div class="img_wrapper">
               									<c:if test="${c.wdjc_image_url==null || c.wdjc_image_url ==''||c.wdjc_image_url=='default'||c.wdjc_image_url=='DEFAULT' }">
               										<img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
               									</c:if>
               									<c:if test="${c.wdjc_image_url!=null && c.wdjc_image_url !=''&& c.wdjc_image_url!='default' && c.wdjc_image_url!='DEFAULT'}">
               										<img class="book_img" src="${c.wdjc_image_url}">
               									</c:if>
               								</div>
               								<div class="content_wrapper">
               									<div class="bookc_title">${c.wdjc_bookname }</div>
               									<div class="sub_title">${c.wdjc_realname } 纠正了《${c.wdjc_bookname }》第${c.wdjc_page }页${c.wdjc_line },提出纠错：“${c.wdjc_content }”。</div>
               									<div class="rank_stars"></div>
               									<div class="book_detail">${c.wdjc_detail }</div>
               								</div>
               							</div>
               							<div class="operate_wrapper">
	               							<a><div class="img img_delete"></div><div>删除</div></a>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdsp' && c.trendst_type == 0}"><%-- 我的书评 发表 --%>
               							<div class="issue_line"><span class="issue_name">发表了评论</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								<div class="img_wrapper">
               									<c:if test="${c.wdsp_image_url==null || c.wdsp_image_url ==''||c.wdsp_image_url=='default'||c.wdsp_image_url=='DEFAULT' }">
               										<img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
               									</c:if>
               									<c:if test="${c.wdsp_image_url!=null && c.wdsp_image_url !=''&& c.wdsp_image_url!='default' && c.wdsp_image_url!='DEFAULT'}">
               										<img class="book_img" src="${c.wdsp_image_url}">
               									</c:if>
               								</div>
               								<div class="content_wrapper">
               									<div class="bookc_title">${c.wdsp_bookname }</div>
               									<div class="sub_title">${c.wdsp_realname } 评论了《${c.wdsp_bookname }》：“${c.wdsp_content }”。</div>
               									<div class="rank_stars">
               										<c:forEach begin="1" end="${c.wdsp_score/2}">
               											<div class="scorestar1"></div>
               										</c:forEach>
               										<c:forEach begin="1" end="${5-c.wdsp_score/2}">
               											<div class="scorestar2"></div>
               										</c:forEach>
               									</div>
               									<div class="book_detail">${c.wdsp_detail }</div>
               								</div>
               							</div>
               							<div class="operate_wrapper">
	               							<a><div class="img img_delete"></div><div>删除</div></a>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdwj' && c.trendst_type == 0}"><%-- 我的问卷 发表 --%>
               							
               						</c:when>

                			</c:choose>
                		</div>
                		</c:forEach>
                	</div>
                	
                	
                	<c:if test="${listCount == 0 }">
                		<div class="no-more">
	                        <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
	                        <span>木有内容呀~~</span>
	                    </div>
                	</c:if>
                	
                	<div class="pageDiv" >
	                    <ul class="pagination" id="page1">
	                    </ul>
	                    <div style="display: inline-block;    vertical-align: top">
	                        <select id="page-size-select" name="page-size-select">
	                        	<option value="5" <c:if test ="${pageSize=='5'}" >selected</c:if> >每页5条</option>
	                            <option value="10"<c:if test ="${pageSize=='10'}" >selected</c:if> >每页10条</option>
	                            <option value="15"<c:if test ="${pageSize=='15'}" >selected</c:if> >每页15条</option>
	                            <option value="20"<c:if test ="${pageSize=='20'}" >selected</c:if> >每页20条</option>
	                            
	                        </select>
	                    </div>
	                    <div class="pageJump">
	                        <span>共<span id="totoal_count" >${totoal_count }</span>页，跳转到</span>
	                        <input type="text"/>
	                        <span class="pp">页</span>
	                        <button type="button" class="button">确定</button>
	                    </div>
	                </div> 
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