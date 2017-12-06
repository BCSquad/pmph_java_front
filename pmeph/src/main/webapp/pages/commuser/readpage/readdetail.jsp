<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
	<head>
	    <script type="text/javascript">
           var contextpath = '${pageContext.request.contextPath}/';
        </script>
		<title>读书详情</title>
		<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	    <link  href="${ctx}/statics/css/base.css" type="text/css" rel="stylesheet">
	    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css"/>
	    <link  href="${ctx}/statics/commuser/readpage/readdetail.css" type="text/css" rel="stylesheet">
	    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
	    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js"></script>
	     <script src="${ctx}/resources/comm/base.js"></script>
        <script src="${ctx}/resources/commuser/readpage/readdetail.js"></script>
</head>
<body>
<div class="body">
    <jsp:include page="/pages/comm/head.jsp"></jsp:include>
    <input type="hidden" value="${map.type}" id="type_id">
	<div class="content-wrapper">
	     <input type="hidden" id="book_id" value="${id}">
	     <input type="hidden" id="marks" value="${map.bookmarks}">
		<!--左边区域-->
        <div class="leftarea">
        	<div class="title" style="margin-top: -20px"><span>读书 > 病理生理学</span></div>
        	<div class="bt"><span>${map.bookname}</span></div>
        	<div class="lj"  style="margin-top: -10px">
        		<span class="span_1">分类路径：</span>
        		<span class="span_2">学校教育>高职高专教材>护理类专业>规划教材>涉外护理  </span>
        	</div>
        	<div style="width: 100%;">
        		<!--<div class="dzsc">
        			<span id="span_1"></span><span style="float:left">点赞</span><span id="span_2"></span><span style="float:left">收藏</span>
        		</div>-->
        		<div class="dzsc">
        			<img src="../statics/image/dz.png" onclick="addlikes()"/>
        			<img src="../statics/image/sc1.png" onclick="addmark()" />
        			 <div style="display: inline-block;vertical-align: top;margin-right: 8px;text-align:left;">
					            <select id="edu" name="edu">
					                 <option value="">选择收藏夹</option>
			        			     <c:forEach items="${flist }" var="favorite">
			        			        <option value="${favorite.id }">${favorite.favorite_name }</option>
			        			     </c:forEach>
					            </select>
                    </div>
        		</div>
        	</div>
        	<div class="xqbf1">
        		<div class="xl1"><img src="${map.image_url }" style="margin-left: 20px;margin-bottom: 25px;height: 150px;width: 115px"/></div>
        		<div class="xl2">
        			<ul>
                        <li><span class="author">作者：</span><span class="writer" style="color: #489299">${map.author}</span></li>
	        			<li class="author">出版社： ${map.publisher}</li>
	        			<li class="author">出版日期：${map.publish_date}</li>
	        			<li class="author">ISBN：${map.isbn}</li>
	        		</ul>	
        		</div>
        		<div class="xl3"></div>
        		<div class="xl4">
        			<ul>
        				<li class="author">读者对象：${map.reader}</li>
        				<li class="author">版次：${map.revision}</li>
        				<li class="author">被浏览：${map.clicks}次</li>
        				<div class="author">评分&nbsp;&nbsp;:</div>
        				<c:if test="${map.score<=3}">
	                        <div class="star">
	                                <span class="rwtx1"></span>
	                                <span class="rwtx2"></span>
	                                <span class="rwtx2"></span>
	                                <span class="rwtx2"></span>
	                                <span class="rwtx2"></span>
	                        </div>
	                    </c:if>  
	                    <c:if test="${map.score<=5 and map.score>3}">
	                        <div class="star">
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx2"></span>
	                                <span class="rwtx2"></span>
	                                <span class="rwtx2"></span>
	                        </div>
	                    </c:if>
	                    <c:if test="${map.score<=7 and map.score>5}">
	                        <div class="star">
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx2"></span>
	                                <span class="rwtx2"></span>
	                        </div>
	                    </c:if>
	                    <c:if test="${map.score<=9 and map.score>7}">
	                        <div class="star">
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx2"></span>
	                        </div>
	                    </c:if>
	                    <c:if test="${map.score>9}">
	                        <div class="star">
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                                <span class="rwtx1"></span>
	                        </div>
	                    </c:if>
	                     
                        <div class="eight">${map.score}</div>
                        <!-- <div class="zero">0</div> -->
        			</ul>
        		</div>
        	</div>
        	<div class="xqbf2">
        	    <a name="001" id="001" ></a>
        		<div class="xsp" style="float: left;">
        		    <div id="xsp"></div><a href="#001" style="text-decoration: none"><span id="xsp1">写书评</span></a>
        		</div>
        		<div class="left1" >
        		    <div id="xsp3"></div>
        		      <a href="${map.pdf_url}" style="text-decoration: none"><span class="xsp2">PDF试读</span>
        		    </a>
        		</div>
        		<div class="left1" style="margin-right: 10px;">
        		    <div id="xsp4"></div>
        		      <a href="${map.buy_url}" style="text-decoration: none"><span class="xsp2">立即购买</span>
                    </a>
        		</div>
        	</div>
        	<div class="block">
                    <div class="title">
                        <div class="line"></div>
                        <div class="rd_name">图书详情</div>
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <div class="aticle">
                    	${map.detail}
                    </div>
            </div>  
            <div class="block">
                    <div class="title">
                        <div class="line"></div>
                           <div class="rd_name">图书评论(共${ComNum}条)</div>
                        <div class="scorestar" id="star">
                             <div class="scorestar1" id="score1"></div>
                             <div class="scorestar1" id="score2"></div>
                             <div class="scorestar1" id="score3"></div>
                             <div class="scorestar1" id="score4"></div>
                             <div class="scorestar1" id="score5"></div>
	                    </div>
	                    <div class="user_score">
                              <span>评分：</span>
                              <span style="color: #FFD200" id="last_score">10.00</span>
                        </div>
                    </div>
                    <!-- <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;"> -->
                    <div class="pl_add">
                    	   <textarea class="tarea" id="content"></textarea>
                    </div>
                    <div class="button"><button id="span_4" onclick="insert()">发表</button></div>
            </div>  
            <div class="block">
               <c:forEach items="${listCom}" var="list">
                    <div class="item">
                        <div class="item_title">
                        	<div style="float: left;"><img src="../statics/image/rwtx.png" /></div>
                        	<div style="float: left;margin-left: 10px;margin-top: 5px;">${list.realname}</div>
                        	<div style="float: left;margin-left: 10px;">
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
                        	</div>
                            <div class="date_content"><div class="date">${list.gmt_create}</div></div>
                        </div>
                        <div class="item_content">${list.content}</div>
                        <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    </div>
              </c:forEach>      
            </div>
        </div>
        <!--右边区域-->
        <div class="rightarea">
        	<div class="right_1">
        		<div>
        			<span id="ptts"></span>
        			<span style="font-family: MicrosoftYaHei;font-size: 16px;color: #000000;margin-left: 5px;"><B>配套图书</B></span>
        		</div>
        		<div style="margin-top: 20px;">
        			<div >
        			    <%-- <img src="${supMap.image_url}" style="float: left;width: 90px;height: 116px"/> --%>
        			</div>
        			<div style="float: left;width: 170px;margin-left: 10px;">
        				<span class="ptts_sp1">${supMap.book_name}</span>
        				<div class="watch"><div class="lookbook">去看看</div></div>
        				<span class="ptts_sp2">${supMap.realname}</span>
        				<span class="ptts_sp3">${supMap.detail}</span>
        			</div>
        		</div>
        	</div>
        	<div class="right_3">
                <div class="right_4">
                    <div class="right_5">
                        <div class="right_6"></div>
                        <div class="right_7">
                            <span id="span_3">相关推荐</span>
                        </div>
                    </div>
                    <div class="right_8">
                        <img src="../statics/image/refresh.png" style="float:left;margin-left:80px">
                        <div class="refresh" onclick="fresh()">换一批</div>
                    </div>
                </div>
                <div id="change">
	                <c:forEach items="${auList}" var="list">
		                <div class="right_9">
		                    <div class="right_10">
		                        <img src="${list.image_url}" class="right_12">
		                    </div>
		                    <div class="right_11">${list.bookname}</div>
		                </div>
	                </c:forEach>
	                <c:forEach items="${tMaps}" var="list">
		                <div class="right_9">
		                    <div class="right_10">
		                        <img src="${list.image_url}" class="right_12">
		                    </div>
		                    <div class="right_11">${list.bookname}</div>
		                </div>
	                </c:forEach>
	             </div> 
            </div>
            <div class="right_13"></div>
            <div class="right_14">
                <div class="right_15">
                    <div class="right_16">
                        <div class="right_17"></div>
                        <div class="right_18">人卫推荐</div>
                    </div>
                </div>
                <div class="right_19">
                    <div class="picture1"></div>
                    <div class="refresh1" onclick="change()">换一批</div>
                </div>
                <div  id="comment">
	                <c:forEach items="${eMap}" var="list">
		                <div class="right_20">
		                    <div class="right_21">${list.bookname}</div>
		                    <div class="right_22">（${list.author}）</div>
		                </div>
	                </c:forEach>
	            </div>
            </div>
        </div>
	</div>
</div>
<div style="clear:both;"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	 //为所有的class为scorestar1绑定mouseout和mouseover事件。bind({事件名：function(){},事件名：function(){}})的方法绑定多个事件
	 $(".scorestar1").bind({
	  mouseover: function () {
		  $(this).css("background-position", "-183px -174px").prevAll().css("background-position", "-183px -174px");
		  $(this).nextAll().css({"background-position": "-183px -153px"});
		  var score=parseInt($(this).attr("id").substring(5))*2+'.00';
		  $("#last_score").html(score);
	  }
	  });
});
</script> 
</body>
</html>

