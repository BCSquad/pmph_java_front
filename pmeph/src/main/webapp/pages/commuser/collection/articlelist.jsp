<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
		var pathName=window.document.location.pathname;  
		var contxtpath="${pageContext.request.contextPath }";
		var contextpath="${pageContext.request.contextPath }/";
</script>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <link rel="stylesheet" href="<%=path %>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path %>/statics/css/jquery.selectlist.css"/>
    <link rel="stylesheet" href="<%=path %>/statics/commuser/collection/articlelist.css"/>
    <script src="<%=path %>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path %>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path %>/resources/comm/layer/layer.js"></script>
   <script src="<%=path %>/resources/comm/base.js"></script>
   <script src="${ctx }/resources/commuser/collection/articlelist.js"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
   <div class="content-wrapper">
        <div class="area1"><a href="personalhomepage/tohomepage.action">个人中心</a> &gt; <a href="javascript:;">我的收藏</a> &gt; <a href="articlecollection/toarticlecollection.action">文章收藏夹</a> &gt; ${favoriteName }</div>
    <div class="area2">
        <span class="name" >${favoriteName }</span>
         <input type="hidden" id="favoriteName" value="${favoriteName }"/>
         <input type="hidden" id="favoriteId" value="${favoriteId }"/>
        <span class="del" onclick="delFavorite('${favoriteId }')">删除收藏夹</span>
    </div>
    <c:forEach items="${articlelist.rows }" var="article"> 
    <div class="collection" >
        <div class="title">
               <span class="title-text">
                   <a href="#"> ${article.title }</a>
               </span>
               <input type="hidden" id="cms${article.cid }" value="${article.cid }"/>
               <span class="tm">
                   <span class="author-icon" style="background-image: url(${article.avatar=='DEFAULT'?'statics/image/deficon.png':article.avatar}); ">
                   </span>
                   <span class="name">${article.realname }</span>
                   <span class="time"><fmt:formatDate  value="${article.gmt_create}" pattern="yyyy.MM.dd"/></span>
               </span>
        </div>
        <div class="content">
            <div  class="content-img">
                <img src="${article.imgpath }"/>
            </div>
            <div  class="content-text" >
                <div class="text">
                ${article.summary }
                </div>
                <div class="end">
                    <div class="foot">
                        <span class="span1" onclick="cancelMark('${article.markId}','${article.bookmarks }','${article.cid }')">取消收藏</span>
                        <span class="span2" >${article.comments }</span>
                        <span class="smicon comment"></span>
                        <span class="${article.like>0?'span3':'span2' }" id="like${article.cid}">${article.likes }</span>
                        <span class="smicon ${article.like>0?'good':'nogood' }"  onclick="addlike('${article.cid}')" id="good${article.cid}"></span>
                        <span class="span2">${article.clicks }</span>
                        <span class="smicon look"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
       <input type="hidden" id="bookcount" value="${articlelist.total }" />
       <input type="hidden" id="pagenum" value="${articlelist.pageNumber }" />
       <input type="hidden" id="pagesize" value="${articlelist.pageSize }" />
       <input type="hidden" id="pages" value="${articlelist.pageTotal }" />
    <div style="margin-top: 30px;text-align: right">
        <ul class="pagination" id="page1">
        </ul>
        <div style="display: inline-block;vertical-align: top;text-align:left;">
            <select id="edu" name="edu">
                <option value="5" ${articlelist.pageSize=='5'?'selected':'' } >每页5条</option>
                <option value="10"  ${articlelist.pageSize=='10'?'selected':'' }>每页10条</option>
                <option value="15"  ${articlelist.pageSize=='15'?'selected':'' }>每页15条</option>
                <option value="20"  ${articlelist.pageSize=='20'?'selected':'' }>每页20条</option>
            </select>
        </div>
        <div class="pageJump">
            <span>共${articlelist.pageTotal }页，共${articlelist.total }条数据，跳转到</span>
            <input type="text"/>
            <span class="pp">页</span>
            <button type="button" class="button">确定</button>
        </div>
    </div>
   </div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
<script>
   
    /*    Page({
     num: 17,					//页码数
     startnum: 6,				//指定页码
     elem: $('#page2'),		//指定的元素
     callback: function (n) {	//回调函数
     console.log(n);
     }
     });*/
   /*  $(function () {
    	var pagenum;
    	if($("#pagenum").val()==""){
    		pagenum=1;
    	}else{
    		pagenum=parseInt($("#pagenum").val());
    	}
    	var pagecount;
    	if(!($("#pages").val()=='')){
    		pagecount=parseInt($("#pages").val());
    	}else{
    		pagecount=1;
    	}
    	
    	Page({
    	        num:pagecount,					
    	        startnum: pagenum,
    	        elem:$("#page1"),
    	        callback: function (n) {
    	        	var pagesize=$("input[name='edu']").val();
    	        	var favoriteName=$("#favoriteName").val();
    	        	var favoriteId=$("#favoriteId").val();
    	            window.location.href=contxtpath+'/articlecollection/toarticlecollectionlist.action?pagenum='+n+'&pagesize='+pagesize+
    	            		'&favoriteName='+favoriteName+'&favoriteId='+favoriteId;
    	        }
    	 });
      $('select').selectlist({
            zIndex: 10,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function () {
            	var pagesize=$("input[name='edu']").val();
              	var favoriteName=$("#favoriteName").val();
              	var favoriteId=$("#favoriteId").val();
              	 window.location.href=contxtpath+'/articlecollection/toarticlecollectionlist.action?pagenum=1&pagesize='+pagesize+
           		'&favoriteName='+favoriteName+'&favoriteId='+favoriteId;
            }  //自定义模拟选择列表项chang
        });
    }); 
    
    
    //点赞或取消点赞  
    function addlike(id){
    	var likes=$("#like"+id).text();
    	$.ajax({
			type:'post',
			url:contxtpath+'/articlecollection/changelike.action',
			data:{contentId:id,likes:likes},
			async:false,
			dataType:'json',
			success:function(json){
			   if(json.returncode=="OK"){
				if($("#good"+id).hasClass("good")){
		    		$("#good"+id).removeClass("good");
		    		$("#good"+id).addClass("nogood");
		    		$("#like"+id).css({"color":"#b5b5b5"});
		    	}else{
		    		$("#good"+id).removeClass("nogood");
		    		$("#good"+id).addClass("good");
		    		$("#like"+id).css({"color":"#1abd44"});
		    	}
				$("#like"+id).text(json.likes);
			   }
				     
			}
		});
    }
    //取消收藏
    function cancelMark(id,markes,cmsid){
    	var favoriteId=$("#favoriteId").val();
    	var cmsId=$("#cms"+cmsid).val();
    	if(confirm("你确定取消收藏吗？")){
    	$.ajax({
			type:'post',
			url:contxtpath+'/articlecollection/cancelmark.action',
			data:{markId:id,favoriteId:favoriteId,contentId:cmsId,markes:markes},
			async:false,
			dataType:'json',
			success:function(json){
				var pagesize=$("input[name='edu']").val();
              	var favoriteName=$("#favoriteName").val();
              	var favoriteId=$("#favoriteId").val();
              	 window.location.href=contxtpath+'/articlecollection/toarticlecollectionlist.action?pagenum=1&pagesize='+pagesize+
           		'&favoriteName='+favoriteName+'&favoriteId='+favoriteId;
			}
		});
    	}
    }
    //删除收藏夹
    function delFavorite(id){
    	if(confirm("你确定删除吗？")){
    	     window.location.href=contxtpath+'/articlecollection/delfavorite.action?favoriteId='+id;
    	}
    } */
</script>