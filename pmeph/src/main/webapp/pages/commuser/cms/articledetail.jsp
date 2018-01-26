<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	var contextpath = '${pageContext.request.contextPath}/';
</script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>医学随笔文章详情</title>
<link rel="stylesheet" href="${ctx}/statics/css/base.css"
	type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css" />
<link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/statics/commuser/cms/articledetail.css" type="text/css">
<script src="${ctx}/resources/comm/jquery/jquery.js"></script>
<script src="${ctx}/resources/comm/base.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
<script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
<script src="${ctx}/resources/commuser/cms/articledetail.js"></script>
</head>
<body>
<input type="hidden" id="wid" value="${wid}">
<input type="hidden" id="marks" value="${map.bookmarks}">
	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="articlepage" name="pageTitle" />
	</jsp:include>
	<div class="body">
		<div class="cms-writer"><div class="text" onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">写文章</div></div>
		<div class="content-wrapper">
			<div class="area1">
	    	<div class="_title">
				<span>文章&gt; 文章详情</span>
			</div>
				<div class="wz_title">
					<span>${map.title }</span>
					<div style="width: 100%;">
        		<div class="dzsc">
        		   <c:if test="${flag=='no'}">
        		     <img src="${ctx}/statics/image/dz02.png" onclick="addlikes()" id="dz"/>
        		   </c:if>
        		   <c:if test="${flag=='yes'}">
        			 <img src="${ctx}/statics/image/${mark=='yes' ? 'dz02.png':'dz01.png'}" onclick="addlikes()" id="dz"/>
        		   </c:if>	  
        		      <img src="${ctx}/statics/image/${mark=='yes' ? 'sc101(1).png':'s102(1).png'}" onclick="addmark()" id="sc"/>
        			  <div style="display: inline-block;vertical-align: top;margin-right: 8px;text-align:left;">
                    </div>
        		</div>
        	</div>
				</div>
				<div class="th_title">
	    		<span>医学随笔</span>
	    		<span>${map.gmt_create }</span>
	    		<img style="float: left;margin-top: 5px;margin-right: 5px;" src="${ctx}/statics/image/scan.png"/>
	    		<span id="img_span">${map.clicks }</span>
	    	</div>
	    	<!--内容-->
	    	
	    	<div class="yxsb_content">
				${UEContent }
	    	</div>
	    	<div class="block">
                    
                    <div class="title">
                        <div class="line"></div>
                           <div class="rd_name">文章评论<%-- （共${ComNum}条） --%></div>
                        <!-- <div class="scorestar" id="star">
                             <div class="scorestar1" id="score1"></div>
                             <div class="scorestar1" id="score2"></div>
                             <div class="scorestar1" id="score3"></div>
                             <div class="scorestar1" id="score4"></div>
                             <div class="scorestar1" id="score5"></div>
	                    </div>
	                    <div class="user_score">
                              <span>评分：</span>
                              <span style="color: #FFD200" id="last_score">10.0</span>
                        </div> -->
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <div class="pl_add">
                    	<textarea class="tarea" id="content"></textarea>
                    </div>
                    <div class="buttonb"><button id="span_4" onclick="insert()">发表</button></div>
            </div>  
             <div class="block">
              <div id="changepage">
               <c:forEach items="${listCom.rows}" var="list">
                    <div class="item" >
                        <div class="item_title">
                        	<div style="float: left;"><img src="${ctx}/statics/image/rwtx.png" class="picturesize"/></div>
                        	<div style="float: left;margin-left: 10px;margin-top: 5px;">${list.realname}</div>
                        	<%-- <div style="float: left;margin-left: 10px;">
                        	<c:if test="${list.score<=3}">
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx2"></span>
	                        	<span class="rwtx2"></span>
	                        	<span class="rwtx2"></span>
	                        	<span class="rwtx2"></span>
	                        </c:if>
	                        <c:if test="${list.score<=5 and list.score>3}">
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx2"></span>
	                        	<span class="rwtx2"></span>
	                        	<span class="rwtx2"></span>
	                        </c:if>
	                        <c:if test="${list.score<=7 and list.score>5}">
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx2"></span>
	                        	<span class="rwtx2"></span>
	                        </c:if>
	                        <c:if test="${list.score<=9 and list.score>7}">
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx2"></span>
	                        </c:if>
	                        <c:if test="${list.score>9}">
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        	<span class="rwtx1"></span>
	                        </c:if>
                        	</div> --%>
                            <div class="date_content"><div class="date">${list.gmt_create}</div></div>
                        </div>
                        <div class="item_content">${list.content}</div>
                        <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    </div>
              </c:forEach> 
              </div>
              <div class="pageDiv" style="float: right;">
	                  <ul class="pagination" id="page1"></ul>
	                  <div style="display: inline-block;    vertical-align: top;text-align: left">
	                      <select id="edu" name="edu">
	                          <option value="2" >每页2条</option>
	                          <option value="5" >每页5条</option>
	                          <option value="10">每页10条</option>
	                          <option value="15">每页15条</option>
	                          <option value="20">每页20条</option>
	                      </select>
	                  </div>
	                  <div class="pageJump">
	                      <input type="hidden" id="pageNumber" value="${listCom.pageNumber}">
	                      <span>共<span id="allppage">${listCom.pageTotal}</span>页，跳转到</span>
	                      <input type="text" id="jumpId"/>
	                      <span class="pp">页</span>
	                      <button type="button" class="button" onclick="beforechange()">确定</button>
	                  </div>
                </div>  
            </div> 
        	<!--猜你喜欢-->
        	<div class="block">
                    <div class="title">
                        <div class="line"></div>
                        <div class="rd_name">猜你喜欢</div>
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <c:forEach items="${listArtSix}" var="list" varStatus="status">
                    <c:if test="${status.index!=0}">
                    <div class="content" id="JKFYDiv_0">
                        <div class="item">
                            <div class="ir_01"> 
                            <div class="tt"><div class="a6_content">${list.title}</div></div>
                           </div>
                        </div>
                    </div>
                     </c:if>
                    </c:forEach>
            </div>
    	</div>
    	<div class="area2">
    		<div style="position:relative;width: 275px;height: 410px;">
    			<div class="right_2">
	    			<c:if test="${Art.avatar=='DEFAULT'}"><img src="${ctx}/statics/image/tx.png" alt="头像" height="60"
                                                              width="60"></c:if>
                	<c:if test="${Art.avatar!='DEFAULT'}"><img src="${ctx}/image/${Art.avatar}.action" alt="头像" height="60"
                                                              width="60"></c:if>
	    			
	    			<span>作者：${Art.realname}</span>
	    		</div>
	    		<div class="right_1" style=" position:absolute;top:20px;z-index:80;">
	    			<div class="right_div1">
	    				<div style="height: 40px;width: 100%;"></div>
	    				<div class="right_div2">
	    					<span>共发表${numArt }篇随笔文章</span>
	    				</div>
	    			</div>
	    			<div class="right_3">
	    				<span>最近文章</span>
	    				<c:forEach items="${listArt}" var="art">
	    				<ul>
	    					<li><span id="right_3_bt" style="cursor: pointer;" onclick="todetail('${art.id}')">${art.title}</span></li>
	    					<li><span id="right_3_sj">${art.gmt_create}</span></li>
	    					<li><hr style=" height:1px;border:none;border-top:1px dashed #dedede;margin-top: 10px;"></li>
	    				</ul>
	    				</c:forEach>
	    			</div>
	    		</div>
    		</div>
    		<!--文章链接-->
    		<div class="right_4">
    			<div style="cursor: pointer;"  onclick="window.location.href='${ctx}/cms/list.action'"><span>想要阅读更多文章？狠戳这里</span></div>
    		</div>
    		<!--相关文章-->
    		<div class="right_5">
    			<div style="width: 275px;height: 20px;margin-bottom: 20px;">
    				<div id="xgwz_1"></div>
    				<div id="xgwz_2">相关文章</div>
                    <div id="xgwz_4" onclick="change()">换一批</div>
                    <div id="xgwz_3"></div>
    			</div>
    			<div  id="comment">
	                <c:forEach items="${eMap}" var="list">
		    			<div class="right_20" >
			    			<div class="right_21" onclick="todetail('${list.wid}')">${list.title}</div>
			    			<div class="right_22">（${list.realname}）</div>
			    		</div>
	    		 </c:forEach>
	            </div>
    		</div>
    	</div>	

			<div style="clear: both"></div>
		</div>
	</div>
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
