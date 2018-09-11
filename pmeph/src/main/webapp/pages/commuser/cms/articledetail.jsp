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
<link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}"
	type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" />
<link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}"
	type="text/css">
<link rel="stylesheet"
	href="${ctx}/statics/commuser/cms/articledetail.css?t=${_timestamp}" type="text/css">
<link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
<script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
<script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
<script src="${ctx}/resources/commuser/cms/articledetail.js?t=${_timestamp}"></script>
</head>
<body>
<input type="hidden" id="wid" value="${wid}">
<input type="hidden" id="marks" value="${map.bookmarks}">
<input type="hidden" id="title" value="${map.title}">
	<jsp:include page="/pages/comm/head.jsp">
		<jsp:param value="articlepage" name="pageTitle" />
	</jsp:include>
	<div class="body">
		<div class="cms-writer"><div class="text00" onclick="window.location.href='${ctx}/writerArticle/initWriteArticle.action'">写文章</div></div>
		<div class="content-wrapper">
			<div class="area1">
		    	<div class="_title">
					<span><a href="${ctx}/articlepage/toarticlepage.action" style="text-decoration: none;color:#999999">文章</a>&gt; ${map.title }</span>
				</div>
				<div class="wz_title">
					<span>${map.title }</span>
					<div style="width: 100%;">
	        		<div class="dzsc">
						<div class="addlikediv" ${is_audit=='false'?'disabled="disabled" style="opacity: 0.6;" title="文章未通过审核，仅供预览。" ':'onclick="addlikes()"'}>
							<div class="${like=='yes' ? 'left2':'addlike'}"  id="dz"></div>
							<div class="addliketext">点赞</div>
						</div>

						<div class="addlikediv" style="margin-left: 10px" ${is_audit=='false'?'disabled="disabled" style="opacity: 0.6;" title="文章未通过审核，仅供预览。" ':'onclick="addmark()"'}>
							<div class="${mark=='yes' ? 'left3':'left4'}"  id="sc"></div>
							<div class="addliketext">收藏</div>
						</div>

						<div style="display: inline-block;vertical-align: top;margin-right: 8px;text-align:left;"></div>
	        		</div>
        			</div>
				</div>
				<div class="th_title">
	    			<span>医学随笔</span>
	    			<span>${map.auth_date}</span>
	    			<img style="float: left;margin-top: 5px;margin-right: 5px;" src="${ctx}/statics/image/scan.png"/>
	    			<span id="img_span">${map.clicks }</span>
	    		</div>
	    	<!--内容-->
		    	<div class="yxsb_content"> ${UEContent } </div>
		    <!--文章附件-->
		    	  <c:if test="${not empty cmsAttach}">
				        <div class="attlist">
				            <div class="atttitle2">
				                	附件：
				            </div>
				            <div class="attlistContent">
				                 <c:forEach items="${cmsAttach }" var="cattach">
				                 	<c:if test="${cattach.attachment!=map.cover}">
				            	  		<span ><a   href="${ctx}/file/download/${cattach.attachment}.action" ><img class="pictureSize" src="${ctx}/statics/pictures/attachment.png">&nbsp;&nbsp;${cattach.attachment_name}</a></span><br>
				                	</c:if>
				                </c:forEach>
				            </div>
				        </div>
		        	</c:if>
	    	<div class="block">
                    
                    <div class="title">
                    	
                        <div class="line"></div>
                        
                           <div class="rd_name">文章评论</div>
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <div class="pl_add">
                    	<a id="001" id="001"></a>
                    	
                    	<textarea class="tarea" id="content" style="outline: #B6EFCE" ${is_audit=='false'?' style="background-color: #d0d0d0;" title="文章未通过审核，仅供预览。" ':''} maxlength="225760"></textarea>
                    	
                    </div>
                    <div class="buttonb"><button id="span_4" onclick="insert()" ${is_audit=='false'?'disabled="disabled" style="background-color: #d0d0d0;" title="文章未通过审核，仅供预览。" ':''}>发表</button></div>
            </div>  
             <div class="block">
              <div id="changepage">
               <c:forEach items="${listCom.rows}" var="list">
                    <div class="item" >
                        <div class="item_title">
                        	<div style="float: left;">
                        		<c:if test="${list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null}">
                        		<img  src="${ctx}/statics/image/default_image.png" height="30"  width="30" />
                        		</c:if>
                				<c:if test="${!(list.avatar == '' || list.avatar == 'DEFAULT' || list.avatar == null)}">
                				<img  src="${ctx}/image/${list.avatar}.action" height="30"  width="30" >
                				</c:if>
                        	</div>
							<c:if test="${list.nickname==''|| list.nickname==null}">
                        	<div style="float: left;margin-left: 10px;margin-top: 5px;">${list.username}</div>
							</c:if>
							<c:if test="${list.nickname!=''and list.nickname!=null}">
								<div style="float: left;margin-left: 10px;margin-top: 5px;">${list.nickname}</div>
							</c:if>
                            <div class="date_content"><div class="date"></div></div>
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
        	<!--猜您喜欢-->
        	<div class="block">
                    <div class="title">
                        <div class="line"></div>
                        <div class="rd_name">猜您喜欢</div>
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <c:forEach items="${listArtSix}" var="list" varStatus="status">
	                <div class="${status.index==0 or status.index==3 ?'item' :'item behind'}" onclick="window.location.href='${ctx}/articledetail/toPage.action?wid=${list.id}'">
                    
                    <div  class="xihuan" >
                        <div class="content-image">
                            <img  src="${ctx}/${list.cover}" />
                        </div>
                        <p   class="content-title" style="cursor: pointer;">${list.title}</p>
                    </div>
                    </div>
                </c:forEach>
                    
            </div>
    	</div>
    		<div class="area2">
    		<%-- <div style="position:relative;width: 275px;/* height: 410px; */">
    			<div class="right_2">
	    			<c:if test="${Art.avatar == '' || Art.avatar == 'DEFAULT' || Art.avatar == null}">
	    			
	    			<img src="${ctx}/statics/image/default_image.png" height="60" width="60">
	    			
	    			</c:if>
                	<c:if test="${!(Art.avatar == '' || Art.avatar == 'DEFAULT' || Art.avatar == null)}">
                	<img src="${ctx}/image/${Art.avatar}.action"  height="60"
                                                              width="60"></c:if>
	    			
	    			<span>发布人：${Art.realname}</span>
	    		</div>
	    		<div class="right_1" style=" position:relative;top:20px;">
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
    		</div> --%>
    		
    		<!--相关文章-->
    		<div class="right_5">
    			<div style="width: 275px;height: 20px;margin-bottom: 20px;">
    				<div id="xgwz_1"></div>
    				<div id="xgwz_2">相关文章</div>
                    <div id="xgwz_4" onclick="change()">换一批</div>
                    <div id="xgwz_3"></div>
    			</div>
				<input type="hidden" id="startrow" value="0">
    			<div  id="comment"></div>
    		
    		</div>
    		<!--文章链接-->
    		<div class="right_4">
    			<div style="cursor: pointer;"  onclick="window.location.href='${ctx}/cms/list.action'"><span>想要阅读更多文章？狠戳这里</span></div>
    		</div>
    	</div>	

			<div style="clear: both"></div>
		</div>
	</div>
	
	<!-- 退回原因及审批意见 显示悬浮框 -->
	<%-- <div class="bookmistake" id="return_cause_div">
	    <div class="apache">
	        <div class="mistitle">退回原因:</div>
	        <div class="xx" onclick="$('#return_cause_div').fadeOut(500);"></div>
	    </div>
	
	    <div class="info">
	        <input id="return_cause_hidden" type="hidden" value="${map }">
	        <textarea class="misarea" disabled="disabled">${map }</textarea>
	    </div>
	
	    <div class="">
	        <button class="btn" type="button" onclick="$('#return_cause_div').fadeOut(500);">确认</button>
	    </div>
	</div>
	<script type="text/javascript">
		if ("${(map.auth_status==1)?'on':'off' }"=="on" && $("#return_cause_hidden").val().length>0) {
	
	        $("#return_cause_div").fadeIn(800);
	
	    }
	</script> --%>
	
	<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
