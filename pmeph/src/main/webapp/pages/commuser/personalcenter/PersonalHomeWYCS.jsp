<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath=request.getContextPath();
%>

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
	<title>个人中心</title>
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
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/commuser/personalcenter/PersonalHome.js"></script>
    <%-- <script src="${ctx}/resources/commuser/personalcenter/PersonalHomeJCSB.js"></script> --%>
    

	<!-- <script type="text/javascript">
		$(function(){
			$(".img_mongoDB").each(function(){
				var $t = $(this);
				var data ={mid:$t.attr("name")};
				$.ajax({
					type:'post',
					url:contextpath+"personalhomepage/getFirstImgByMid.action?t="+new Date().getTime(),
					async:false,
					dataType:'json',
					data:data,
					success:function(json){
						$t.attr("src",json);
					}
				});
				
			});
		});
	</script> -->
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
	<input type="hidden" class="" id="logUserId" value="${logUserId }">
	<input type="hidden" class="" id="selfLog" value="${selfLog }">

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
                <c:choose>
                	<c:when test="${selfLog == false && friendShip.status == -1 }">
                		<button class="btn_addFriend add" title="申请加为好友！" onclick="addFriendfun(${friendShip.logUserId},'${permap.realname}',0)">加好友</button>
                	</c:when>
                	<c:when test="${selfLog == false && friendShip.status == 2 }">
                		<button class="btn_addFriend isFriend" title="已是您的好友！">好友</button>
                	</c:when>
                	<c:when test="${selfLog == false && friendShip.status == 0 && friendShip.isBeenRequest==1}">
                		<button class="btn_addFriend isBeenRequest" title="对方也想加您为好友，点击马上成为好友！" onclick="addFriendfun(${friendShip.logUserId},'${permap.realname}',2)">加好友</button>
                	</c:when>
                	<c:when test="${selfLog == false && friendShip.status == 0 && friendShip.hasRequest==1}">
                		<button class="btn_addFriend hasRequest" title="已申请加为好友，请等待对方同意。">加好友</button>
                	</c:when>
                	
                </c:choose>
                <br/>
                <c:if test="${permap.rank==0}"><span id="zjrz"></span><span class="grsx">普通用户</span></c:if>
                <c:if test="${permap.rank==1}"><span id="zjrz"></span><span class="grsx">教师用户</span></c:if>
                <c:if test="${permap.rank==2}"><span id="zjrz"></span><span class="grsx">作家用户</span></c:if>
                <c:if test="${permap.rank==3}"><span id="zjrz"></span> <span class="grsx">专家用户</span></c:if>
                <c:if test="${selfLog == true}">
                	<a href="<c:url value="/userinfo/touser.action"/>"><span id="zhsz"></span><span class="grsx">账户设置</span></a>
                	<a href="<c:url value="/integral/toPage.action"/>"><span id="jftb"></span><span class="grsx">积分</span></a>
                </c:if>
                
                
                
                
                
                
            </div>
            <div class="headae">
                <c:if test="${permap.avatar=='DEFAULT'}"><img src="${ctx}/statics/image/default_image.png" alt="头像" height="164"
                                                              width="165"></c:if>
                <c:if test="${permap.avatar!='DEFAULT'}"><img src="<%=path %>/image/${permap.avatar}.action" alt="头像" height="164"
                                                              width="165"></c:if>
            </div>
        </div>

        <div class="content">
            <div class="left">
                <ul class="dhl">
                    <li id="dt" class="dtl pagetag"><a class="aher paged" >动态</a></li>
                    <c:if test="${selfLog == true }">
                    	<li id="jcsb" class="dtl pagetag"><a class="aher paged" >教材申报</a></li>
                    </c:if>
                    <li id="sbwz" class="dtl pagetag"><a class="aher paged">随笔文章</a></li>
                    <c:if test="${selfLog == true }">
                    	<li id="tsjc" class="dtl pagetag"><a class="aher paged">图书纠错</a></li>
                    </c:if>
                    <c:if test="${selfLog == true }">
                    	<li id="wycs" class="dtl pagetag"><a class="aher paged"  >我要出书</a></li>
                    </c:if>
					<li id="wdjc" class="dtl pagetag"><a class="aher paged" >个人纠错</a></li>
                    <li id="wdpl" class="dtl pagetag"><a class="aher paged" >个人评论</a></li>
                    <li id="wdwj" class="dtl pagetag"><a class="aher paged" >个人问卷</a></li>
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
                				<%-- 0动态 0类  --%>
                				<c:when test="${c.type == 0}">	
                					<div class="issue_line"><span class="issue_name">${c.detail.title }</span><span class="issue_time">${c.trendst_date }</span></div>
           							<div class="msg_line">
           								<div class="msg_content">
           									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
           									${c.detail.content }					
           								</div>
           								<div class="${c.detail.img == 1?'success_smile ':'' + c.detail.img == 2?'fail_unhappy ':''}"></div>
           							</div> 
                				</c:when>
                				<%-- 0动态 0类 end --%>
                				
                				<%-- 1动态 文章发表 --%>
                				<c:when test="${c.type == 1}">	
                					<div class="issue_line"><span class="issue_name">发表了随笔文章</span><img class="img_xiewenzhang" src="${ctx }/statics/image/xiewenzhang.png"><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								
               								<c:if test="${c.cms.is_deleted ==false}">
	               								<div class="img_wrapper ">
	               									<img class="img_mongoDB" name="${c.cms.mid }" src="${c.cms.first_img_url }">
	               								</div>
               								</c:if>
               								<div class="content_wrapper">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new article_new"></div></c:if>
               									<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== true}"><div class="status_tag toApply">未提交</div></c:if>
            									<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== false && c.cms.auth_status ==0}"><div class="status_tag toAudit">待审核</div></c:if>
            									<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== false && c.cms.auth_status ==1}"><div class="status_tag reject">未通过</div></c:if>
            									<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== false && c.cms.auth_status ==2}"><div class="status_tag Audited">已通过</div></c:if>
               									<c:if test="${selfLog == true && c.cms.is_deleted !=false}"><div class="status_tag reject">已删除</div></c:if>
               									
               									<c:if test="${c.cms.is_deleted == false}">
               										<div class="article_title"><a class="not-like-an-a" target="_blank" href="${ctx }/articledetail/toPage.action?wid=${c.cms.id}">${c.cms.title }</a></div>
               									</c:if>
               									<c:if test="${c.cms.is_deleted == true}">
               										<div class="article_title">该文章已删除</div>
               									</c:if>
               									<div class="article_summary">${c.cms.is_deleted == "0"?c.cms.content_text:"该文章已删除" } ...</div>
               								</div>
               							</div>
               							<div class="operate_wrapper">
               								<c:if test="${c.cms.is_deleted ==false && selfLog == true && c.cms.auth_status != 2}">
	               								<a target="_blank" href="${ctx }/writerArticle/initWriteArticle.action?id=${c.cms.id}&userid=${logUserId}"><div class="img img_edit" ></div><div>编辑</div></a> 
	               							</c:if>
	               							<c:if test="${c.cms.is_deleted ==false && selfLog == true}">
	               								<a onclick="deleteArticle('${c.cms.id}','${c.cms.title }','0')" ><div class="img img_delete"></div><div>删除</div></a>
               								</c:if>
               							</div>
                				</c:when>
                				<%-- 1动态 文章发表end --%>
                				
                				<%-- 2动态 文章评论 --%>
                				<c:when test="${c.type == 2}">	
                					<div class="issue_line"><span class="issue_name">评论了文章</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
											<c:if test="${c.p_cms.is_deleted ==false}">
	               								<div class="img_wrapper ">
	               									<img class="img_mongoDB" name="${c.p_cms.mid }" src="${c.p_cms.first_img_url }">
	               								</div>
               								</c:if>
               								<div class="content_wrapper">
               								
               									
               									<c:if test="${selfLog == true && c.p_cms.is_deleted !=false}"><div class="status_tag reject">已删除</div></c:if>
               									
               									<c:if test="${c.p_cms.is_deleted == false}">
               										<div class="article_title"><a class="not-like-an-a" target="_blank" href="${ctx }/articledetail/toPage.action?wid=${c.p_cms.id}">${c.p_cms.title }</a></div>
               									</c:if>
               									
               									<c:if test="${c.p_cms.is_deleted == true}">
               										<div class="article_title">该文章已删除</div>
               									</c:if>
               									
               									<div class="sub_title">
	               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
	               									<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== true}"><div class="status_tag toApply">未提交</div></c:if>
            										<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== false && c.cms.auth_status ==0}"><div class="status_tag toAudit">待审核</div></c:if>
            										<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== false && c.cms.auth_status ==1}"><div class="status_tag reject">未通过</div></c:if>
            										<c:if test="${selfLog == true && c.cms.is_deleted==false && c.cms.is_staging== false && c.cms.auth_status ==2}"><div class="status_tag Audited">已通过</div></c:if>
	               									<c:if test="${c.cms.is_deleted !=false}"><div class="status_tag reject">已删除</div></c:if>
		               								${c.realname } 评论了《${(c.p_cms.is_deleted ==false)?c.p_cms.title:'已删除'}》：“${(c.p_cms.is_deleted ==true || c.cms.is_deleted ==true)?'该条评论已删除':c.cms.content_text }”。
               									</div>
               									
               									
               									<div class="article_summary">${c.p_cms.is_deleted == false?c.p_cms.content_text:"该文章已删除" } ...</div>
               								</div>
               							</div>
               							<div class="operate_wrapper">
	               							<c:if test="${(c.p_cms.is_deleted ==false && c.cms.is_deleted ==false) && selfLog == true}">
	               								<a onclick="deleteArticle('${c.cms.id}','${c.p_cms.title }','1')" ><div class="img img_delete"></div><div>删除</div></a>
               								</c:if>
               							</div>
                				</c:when>
                				<%-- 2动态 文章评论 end --%>
                				
                				<%-- 3 4 动态 文章收藏 文章点赞--%>
                				<c:when test="${c.type == 3 || c.type == 4}">	
                					<div class="issue_line"><span class="issue_name">${c.type == 3?'收藏':'点赞' }了随笔文章</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								
               								<c:if test="${c.cms.is_deleted ==false}">
	               								<div class="img_wrapper ">
	               									<img class="img_mongoDB" name="${c.cms.mid }" src="${c.cms.first_img_url }">
	               								</div>
               								</c:if>
               								<div class="content_wrapper">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new article_new"></div></c:if>
               									<c:if test="${selfLog == true && c.cms.is_deleted !=false}"><div class="status_tag reject">已删除</div></c:if>
               									
               									<c:if test="${c.cms.is_deleted == false}">
               										<div class="article_title"><a class="not-like-an-a" target="_blank" href="${ctx }/articledetail/toPage.action?wid=${c.cms.id}">${c.cms.title }</a></div>
               									</c:if>
               									<c:if test="${c.cms.is_deleted == true}">
               										<div class="article_title">该文章已删除</div>
               									</c:if>
               									<div class="article_summary">${c.cms.is_deleted == "0"?c.cms.content_text:"该文章已删除" } ...</div>
               								</div>
               							</div>
                				</c:when>
                				<%-- 3 4 动态 文章收藏 文章点赞end--%>
                				
                				<%-- 5动态 发表书评 --%>
                				<c:when test="${c.type == 5}">	
                					<div class="issue_line"><span class="issue_name">评论了图书</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								<div class="img_wrapper">
               									<c:if test="${c.book.image_url==null || c.book.image_url ==''||c.book.image_url=='default'||c.book.image_url=='DEFAULT' }">
               										<img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
               									</c:if>
               									<c:if test="${c.book.image_url!=null && c.book.image_url !=''&& c.book.image_url!='default' && c.book.image_url!='DEFAULT'}">
               										<img class="book_img" src="${c.book.image_url}">
               									</c:if>
               								</div>
               								<div class="content_wrapper">
               									<div class="bookc_title">
	               									<a class="not-like-an-a" target="_blank" href="${ctx }/readdetail/todetail.action?id=${c.book.id }"
	               											>${c.book.bookname }</a>
               									</div>
               									<div class="sub_title">
	               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
	               									<c:if test="${c.b_comment.is_deleted !=false}"><div class="status_tag reject">已删除</div></c:if>
	               									${c.realname } 评论了《${c.book.bookname }》：“${c.b_comment.is_deleted !=false ?'该条评论已删除':c.b_comment.content }”。
               									</div>
               									<div class="rank_stars">
               										<c:forEach begin="1" end="${c.b_comment.score/2}">
               											<div class="scorestar1"></div>
               										</c:forEach>
               										<c:forEach begin="1" end="${5-c.b_comment.score/2}">
               											<div class="scorestar2"></div>
               										</c:forEach>
               									</div>
               									<div class="book_detail">${c.book.detail }</div>
               								</div>
               							</div>
               							<c:if test="${c.b_comment.is_deleted == false && selfLog == true}">
	               							<div class="operate_wrapper">
		               							<a onclick="deleteMyBookComment(${c.b_comment.id})"><div class="img img_delete"></div><div>删除</div></a>
	               							</div>
               							</c:if>
                				</c:when>
                				<%-- 5动态 发表书评 end --%>
                				
                				<%-- 6 7动态 收藏图书 图书点赞--%>
                				<c:when test="${c.type == 6 || c.type == 7}">	
                					<div class="issue_line"><span class="issue_name">${c.type == 6?'收藏':'点赞' }了图书</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								<div class="img_wrapper">
               									<c:if test="${c.book.image_url==null || c.book.image_url ==''||c.book.image_url=='default'||c.book.image_url=='DEFAULT' }">
               										<img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
               									</c:if>
               									<c:if test="${c.book.image_url!=null && c.book.image_url !=''&& c.book.image_url!='default' && c.book.image_url!='DEFAULT'}">
               										<img class="book_img" src="${c.book.image_url}">
               									</c:if>
               								</div>
               								<div class="content_wrapper">
               									<div class="bookc_title">
	               									<a class="not-like-an-a" target="_blank" href="${ctx }/readdetail/todetail.action?id=${c.book.id }"
	               											>${c.book.bookname }</a>
               									</div>
               									<div class="sub_title">
	               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
	               									${c.realname } ${c.type == 6?'收藏':'点赞' }了《${c.book.bookname }》。
               									</div>
               									<div class="book_detail">${c.book.detail }</div>
               								</div>
               							</div>
                				</c:when>
                				<%-- 6 7动态 收藏图书 图书点赞end --%>
                				
                				<%-- 8 9 10审 11动态 教材申报 选题申报 图书纠错审核 问卷调查 --%>
                				<c:when test="${c.type == 8 || c.type == 9 || c.type == 11 ||(c.type == 10 && c.detail.img != 0)}">	
                					<div class="issue_line"><span class="issue_name">${c.detail.title }</span><span class="issue_time">${c.trendst_date }</span></div>
           							<div class="msg_line">
           								<div class="msg_content">
           									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
           									${c.detail.content }					
           								</div>
           								<div class="${c.detail.img == 1?'success_smile ':'' + c.detail.img == 2?'fail_unhappy ':''}"></div>
           							</div> 
                				</c:when>
                				<%-- 8 9 10审 11动态 教材申报 选题申报 图书纠错审核 问卷调查  end --%>
                				
                				<%-- 10动态 图书纠错 生成--%>
                				<c:when test="${c.type == 10 && c.detail.img == 0}">	
                					<div class="issue_line"><span class="issue_name">${c.detail.title }</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								<div class="img_wrapper">
               									<c:if test="${c.book.image_url==null || c.book.image_url ==''||c.book.image_url=='default'||c.book.image_url=='DEFAULT' }">
               										<img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg">
               									</c:if>
               									<c:if test="${c.book.image_url!=null && c.book.image_url !=''&& c.book.image_url!='default' && c.book.image_url!='DEFAULT'}">
               										<img class="book_img" src="${c.book.image_url}">
               									</c:if>
               								</div>
               								<div class="content_wrapper">
               									<div class="bookc_title">
	               									<a class="not-like-an-a" target="_blank" href="${ctx }/readdetail/todetail.action?id=${c.book.id }"
	               											>${c.book.bookname }</a>
               									</div>
               									<div class="sub_title">
	               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
	               									${c.realname } 纠正了《${c.book.bookname }》第${c.detail.content.page }页，第${c.detail.content.line }行:"${c.detail.content.content }"
               									</div>
               									<div class="book_detail">${c.book.detail }</div>
               								</div>
               							</div>
                				</c:when>
                				<%-- 10动态 图书纠错 生成 end--%>
                				
                			</c:choose>
                			
                			
                			
                			
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
                				
                					<c:when test="${c.table_name == 'jcsb' && c.trendst_type == 2&& selfLog == true}"><%-- 教材申报 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
            							<div class="msg_line">
            								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.jcsb_create_time}申报的《${c.jcsb_textbook_name}》${c.jcsb_chosen_position }审核未通过。
               								</div>		
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'sbwz' && c.trendst_type == 2&& selfLog == true}"><%-- 随笔文章 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.sbwz_create_time}发表的随笔文章《${c.sbwz_title}》审核未通过。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'tsjc' && c.trendst_type == 2&& selfLog == true}"><%-- 图书纠错 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									${c.tsjc_realname} 于${c.tsjc_create_time}对您主编的图书《${c.tsjc_bookname}》第${c.tsjc_page }页${c.tsjc_line }提出的纠错：“${c.tsjc_content }”审核结果为“无问题”。
               								</div>
               								<div class="success_smile"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wycs' && c.trendst_type == 2&& selfLog == true}"><%-- 我要出书 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.wycs_create_time}提交的《${c.wycs_bookname}》选题被退回，请尽快检查修改！
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdjc' && c.trendst_type == 2&& selfLog == true}"><%-- 我要纠错 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉 ${c.wdjc_realname }！您于${c.wdjc_create_time}对图书《${c.wdjc_bookname}》第${c.wdjc_page }页${c.wdjc_line }提出的纠错：“${c.wdjc_content }”审核结果为无问题，仍然感谢您的关注。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdsp' && c.trendst_type == 2&& selfLog == true}"><%-- 我的书评 未通过 --%>
               							<div class="issue_line"><span class="issue_name">未通过审核</span><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="msg_line">
               								
               								<div class="msg_content">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               									非常抱歉！您于${c.wdsp_create_time}对《${c.wdsp_bookname}》的评价 “${c.wdsp_content }” 审核未通过。
               								</div>
               								<div class="fail_unhappy"></div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wdwj' && c.trendst_type == 2&& selfLog == true}"><%-- 我的问卷 未通过 --%>
               							
               						</c:when>
                				
                				
                				<%-- 发表动态 --%>
                				
                					<c:when test="${c.table_name == 'jcsb' && c.trendst_type == 0&& selfLog == true}"><%-- 教材申报 发表 --%>
               							<div class="issue_line"><span class="issue_name">申报了编写教材</span><span class="issue_time">${c.trendst_date }</span></div>
                						<div>您申报的《${c.jcsb_textbook_name}》${c.preset_position }已提交成功，请耐心等待遴选结果。</div>		
               						</c:when>
               						<c:when test="${c.table_name == 'sbwz' && c.trendst_type == 0}"><%-- 随笔文章 发表 --%>
               							<div class="issue_line"><span class="issue_name">发表了随笔文章</span><img class="img_xiewenzhang" src="${ctx }/statics/image/xiewenzhang.png"><span class="issue_time">${c.trendst_date }</span></div>
               							<div class="content_line">
               								
               								
               								<div class="img_wrapper ">
               									
               									<img class="img_mongoDB" name="${c.sbwz_mid }" src="">
               									<%-- <img src="${ctx }/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"> --%>
               								</div>
               								<div class="content_wrapper">
               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new article_new"></div></c:if>
               									<c:if test="${c.sbwz_is_deleted=='0' && c.sbwz_is_staging== true}"><div class="status_tag toApply">未提交</div></c:if>
            									<c:if test="${c.sbwz_is_deleted=='0' && c.sbwz_is_staging== false && c.sbwz_auth_status ==0}"><div class="status_tag toAudit">待审核</div></c:if>
            									<c:if test="${c.sbwz_is_deleted=='0' && c.sbwz_is_staging== false && c.sbwz_auth_status ==1}"><div class="status_tag reject">未通过</div></c:if>
            									<c:if test="${c.sbwz_is_deleted=='0' && c.sbwz_is_staging== false && c.sbwz_auth_status ==2}"><div class="status_tag Audited">已通过</div></c:if>
               									<c:if test="${c.sbwz_is_deleted !='0'}"><div class="status_tag reject">已删除</div></c:if>
               									
               									<div class="article_title">${c.sbwz_is_deleted == "0"?c.sbwz_title:"该文章已删除" }</div>
               									<div class="article_summary">${c.sbwz_is_deleted == "0"?c.sbwz_summary:"该文章已删除" } ...</div>
               								</div>
               							</div>
               							<c:if test="${c.sbwz_is_deleted =='0' && selfLog == true}">
	               							<div class="operate_wrapper">
		               							<a target="_blank" href="${ctx }/writerArticle/initWriteArticle.action?id=${c.sbwz_id}&userid=${logUserId}"><div class="img img_edit" ></div><div>编辑</div></a> 
		               							<a onclick="deleteArticle('${c.sbwz_id}','${c.sbwz_title }')" ><div class="img img_delete"></div><div>删除</div></a>
	               							</div>
               							</c:if>
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
               									<div class="bookc_title">
               										<a class="not-like-an-a" target="_blank" href="${ctx }/readdetail/todetail.action?id=${c.tsjc_book_id }"
               											>${c.tsjc_bookname }</a>
               									</div>
               									<div class="sub_title"><c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>${c.tsjc_realname } 纠正了《${c.tsjc_bookname }》第${c.tsjc_page }页${c.tsjc_line },提出纠错：“${c.tsjc_content }”。</div>
               									<div class="rank_stars"></div>
               									<div class="book_detail">${c.tsjc_detail }</div>
               								</div>
               							</div>
               						</c:when>
               						<c:when test="${c.table_name == 'wycs' && c.trendst_type == 0&& selfLog == true}"><%-- 我要出书 发表 --%>
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
               									<div class="bookc_title">
               										<a class="not-like-an-a" target="_blank" href="${ctx }/readdetail/todetail.action?id=${c.wdjc_book_id }"
               											>${c.wdjc_bookname }</a>
												</div>
               									<div class="sub_title">
               										<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
               										<c:if test="${c.wdjc_is_deleted !='0'}"><div class="status_tag reject">已删除</div></c:if>
               										${c.wdjc_realname } 纠正了《${c.wdjc_bookname }》第${c.wdjc_page }页${c.wdjc_line },提出纠错：“${c.wdjc_content }”。
               									</div>
               									<div class="rank_stars"></div>
               									<div class="book_detail">${c.wdjc_detail }</div>
               								</div>
               							</div>
               							<c:if test="${c.wdjc_is_deleted =='0'&& selfLog == true}">
	               							<div class="operate_wrapper">
		               							<a onclick="deleteMyCorrect(${c.wdjc_id})"><div class="img img_delete"></div><div>删除</div></a>
	               							</div>
               							</c:if>
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
               									<div class="bookc_title">
	               									<a class="not-like-an-a" target="_blank" href="${ctx }/readdetail/todetail.action?id=${c.wdsp_book_id }"
	               											>${c.wdsp_bookname }</a>
               									</div>
               									<div class="sub_title">
	               									<c:if test="${c.trendst_date_num >= currentTime}"><div class="tag_new"></div></c:if>
	               									<c:if test="${c.wdsp_is_deleted !='0'}"><div class="status_tag reject">已删除</div></c:if>
	               									${c.wdsp_realname } 评论了《${c.wdsp_bookname }》：“${c.wdsp_is_deleted !='0'?'该条评论已删除':c.wdsp_content }”。
               									</div>
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
               							<c:if test="${c.wdsp_is_deleted =='0'&& selfLog == true}">
	               							<div class="operate_wrapper">
		               							<a onclick="deleteMyBookComment(${c.wdsp_id})"><div class="img img_delete"></div><div>删除</div></a>
	               							</div>
               							</c:if>
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
	                        <span>共<span id="totoal_count" >${maxPageNum }</span>页，跳转到</span>
	                        <input type="text"/>
	                        <span class="pp">页</span>
	                        <button type="button" class="button">确定</button>
	                    </div>
	                </div> 
                </div>


            </div>


            <div class="right">
            	<div id="wdxz"><span id="xztb"></span><span class="rlan">加入的小组</span><span
                        id="qbhy"><a href="${ctx}/group/list.action" class="aright">全部小组>>&nbsp;</a></span>
                    <br style="clear: both;"/>
                    <c:if test="${listmygroup == null || listmygroup.size()==0  }">
                		<div style="padding-top: 10px;">
	                        <img src="<c:url value="/statics/image/no_group.png"></c:url>">
	                    </div>
                	</c:if>
                    <ul class="scul">
                        <c:forEach items="${listmygroup}" begin='0' end='8' var="listmyg" varStatus="status">
                            <a  class="not-like-an-a" href="${ctx}/group/toMyGroup.action?groupId=${listmyg.group_id}">
	                            <li class="wdxz" title="${listmyg.group_name}">
	                            	<img src="${ctx}/${listmyg.group_image}" class="xztp">
	                                <br/>
	                                <span class="group_name">${listmyg.group_name}</span>
	                                <span class="xzrs">(${listmyg.grouppeo}人)</span>
	                            </li>
                            </a>
                        </c:forEach>
                    </ul>
                </div>
                
                <div id="wdhy">
                <span id="hytb"></span> 
                <span class="rlan">好友</span> 
                <span id="qbhy">
                	<a href="${ctx}/myFriend/listMyFriend.action" class="aright">全部好友>>&nbsp;</a>
                </span>
                    <br style="clear: both;"/>
                    <c:if test="${listmyfriend == null || listmyfriend.size()==0  }">
                		<div style="padding-top: 10px;">
	                        <img src="<c:url value="/statics/image/no_friends.png"></c:url>">
	                    </div>
                	</c:if>
                    <ul class="scul">
                        <c:forEach items="${listmyfriend}" begin='0' end='11' var="listmyf" varStatus="status">
                            <a target="_blank" class="not-like-an-a" href="${ctx}/personalhomepage/tohomepage.action?userId=${listmyf.id}">
	                            <li class="hylb">
	                                <div class="hytxdiv">
	                                    <c:if test="${listmyf.avatar=='DEFAULT'||listmyf.avatar==''||listmyf.avatar== NULL}"><img
	                                            src="${ctx}/statics/image/default_image.png" class="hytp"></c:if>
	                                    <c:if test="${listmyf.avatar!='DEFAULT'}"><img src="<%=path %>/image/${listmyf.avatar}.action"
	                                                                                   class="hytp"></c:if>
	                                </div>
	                                    ${listmyf.realname}
	                             </li>
                             </a>
                        </c:forEach>
                    </ul>
                </div>
                
                <div id="wdsc"><span id="wdscx"></span> <span class="rlan">个人收藏</span> 
                <span id="qbhy">
                	<a href="${ctx}/bookcollection/tobookcollectionlist.action" class="aright">全部收藏>>&nbsp;</a>
                </span>
                <!-- <span id="hyp">换一批</span>
                <span id="jiantou"></span> -->
                    <br/>
                    <c:if test="${listmycol == null || listmycol.size()==0  }">
                		<div style="padding-top: 10px;">
	                        <img src="<c:url value="/statics/image/no_collect.png"></c:url>">
	                    </div>
                	</c:if>
                    <ul class="scul">
                        <c:forEach items="${listmycol}" begin='0' end='5' var="list" varStatus="status">
                            <a target="_blank" class="not-like-an-a" href="${ctx}/readdetail/todetail.action?id=${list.book_id}">
	                            <li class="sclb" title="${list.book_name}">
	                                <div class="sctpdiv">
	                                    <c:if test="${list.image_url=='DEFAULT' }"><img
	                                            src="${ctx}/statics/image/564f34b00cf2b738819e9c35_122x122!.jpg"
	                                            class="sctp"></c:if>
	                                    <c:if test="${list.image_url!='DEFAULT'}"><img src="${list.image_url}"
	                                                                                   class="sctp"></c:if>
	                                </div>
	                                    ${list.book_name}
	                            </li>
                            </a>
                        </c:forEach>
                    </ul>
                </div>
                
                <div id="bzzx">
                    <div id="bzxxherd"></div>
                    <span class="bzzxwz">帮助中心</span>
                    <div id="bzxxherd2"></div>
                    <input type="text" id="bzzxcxk" placeholder="请输入您要咨询的问题">
                    <ul class="scul">
                        <li class="bzzxlb"><span id="dianhua"></span><span class="zzfw">自助服务</span></li>
                        <li class="bzzxlb"><span id="shou"></span><span class="zzfw">投诉举报</span></li>
                        <li class="bzzxlb"><span id="kefu"></span><span class="zzfw">客服反馈</span></li>
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