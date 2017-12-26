<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
     <script>
		var contextpath = "${pageContext.request.contextPath}/";
	</script>
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css"/>
     <link rel="stylesheet" href="<%=path%>/statics/commuser/community/community.css" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js"></script>
    <script src="<%=path%>/resources/comm/base.js"></script>
    <script src="<%=path%>/resources/commuser/community/community.js"></script>
</head>

<body>
      <jsp:include page="/pages/comm/head.jsp"></jsp:include> 
       <div class="content-wrapper">
           <div class="navigation">
                <a href="#" >首页</a>&gt;${notice.title }
           </div>
           <div  class="pagecontent">
           <div class="report">
                <div class="left">
                   <div style="background-image: url(${ctx}/statics/image/css_sprites.png);background-position:-10px -150px;width:25px;height:110px;
                   margin-left:10px;margin-top:34px"></div>
                </div>
                <div class="center">
                     <ul style="list-style:none;">
                        <c:forEach items="${reportlist}" var="report">
		                        <li>
		                                <div>
		                                <div class="tag">&gt;</div>
		                                <a href="inforeport/toinforeport.action?id=${report.id }">
		                                    <div class="inleft">${report.title}</div>
		                                    <div style="float:left;width:40px;height:35px;background-image: url(${ctx}/statics/image/css_sprites.png);background-position: -40px -202px;"></div>
		                                </a>
		                                <div class="inright" >
		         
		                                <fmt:formatDate value="${report.gmt_create }" pattern="yyyy-MM-dd"/>
		                                </div>
		                                </div>
		                        </li>
                        </c:forEach>  
                     </ul>
                </div>
                <div class="right" >全部&gt;&gt;</div>
           </div>
           <div class="book">
           <div class="bhead">
	           <div class="headicon"></div>
               <div class="headtext">本套教材图书</div>
           </div>
           <div class="booklist">
               <c:forEach items="${booklist }" var="book">
                    <div class="item">
		                  <div class="itemimg" style="width:126px;height:126px;margin:20px auto 0px;text-align: center">
		                     <img alt="" src="${book.image_url=='DEFAULT'? 'statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':book.image_url }" >
		                  </div>
                 	<div class="bookname"><a href="readdetail/todetail.action?id=${book.bookId }">${book.textbook_name }</a> </div>
               </div>
               
               </c:forEach>
               </div>    
           </div>
           </div>
           <div class="pageright">
             <div class="rhead">
                 <div class="item select"  id="comment" onclick="comments('${notice.material_id }')">
                                                                             精彩书评
                 </div>
                 <div class="item noselect"  id="smallvideo" onclick="smallvideos()">
                                                                              微视频                                                       
                 </div>
             </div>
             <div class="list" style="margin-left:20px;width:230px;">
                 <ul id="ullist">
                      <%-- <li>
                         <div style="width:230px;border:1px solid #CCCCCC;height:260px;margin-top:15px">
                            <div id="popDiv" style="z-index:99;position:absolute;background-color: #000;height:184px;width:230px;opacity:0.5;"> </div>
                            <div style="width:230px;height:184px">
                               <img src="${ctx }/statics/testfile/testvideoimage.png"/>
                            </div>
                            <div style="width:210px;border-bottom:1px solid #EEEEEE;height:44px;margin:0px auto 0px;line-height: 44px;font-size: 16px;color:#333333">
                                                                                                                           解剖学讲解
                            </div>
                            <div  style="height:30px;width:210px;margin:0px auto 0px;color:#999999;font-size: 14px">
                               <span style="float:left;line-height: 30px">2017-12-22</span>
                               <span style="float:right;line-height: 30px;margin-left:5px">98</span>
                               <span style="float:right;width:20px;height:30px; 
                                background-image: url(<%=path %>/statics/image/css_sprites.png);
                                background-position:-418px -144px;"></span>
                            </div>
                         </div> 
                      </li>  --%>
                     <c:forEach items="${someComments }" var="comment">
                        <li class="commentli">
                          <p class="title">${comment.bookname }</p>
                          <p  class="message">
	                           <span class="name" >${comment.realname }  发表了评论</span>
	                           <span class="scoreimg ${comment.score >=2.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=4.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=6.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=8.0 ? 'yellowstar':'graystar'}"></span>
	                           <span class="scoreimg ${comment.score >=10.0 ? 'yellowstar':'graystar'}"></span>
                          
                          </p>
                          <p  class="contentext">${comment.content }</p>
                      </li>
                     
                     </c:forEach>
                 </ul>
             </div>
             <div class="more" id="more"><a href="#">查看更多精彩书评</a></div>
           </div> 
            <div style="clear:both;width:100%"></div>
       </div>
      
      <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
<script type="text/javascript">
  function comments(id){
	  $.ajax({
			type:'post',
			url:contextpath+'community/getComments.action',
			data:{materialId:id},
			async:false,
			dataType:'json',
			success:function(json){
				$("#comment").css({"border-bottom":"2px solid #5A9DA3","color":"#444544"});
				  $("#smallvideo").css({"border-bottom":"2px solid #FFFFFF","color":"#9c9c9c"});
				  $("#more").html('<a href="#">查看更多精彩书评</a>');
				  $("#ullist").html("");
				  $.each(json.comments,function(i,n){
					  $("#ullist").append('<li class="commentli"><p class="title">'+
					  n.bookname
					  +'</p><p  class="message"><span class="name" >'+
					    n.realname
					  +'  发表了评论</span><span class="scoreimg '+
					    (n.score>=2.0 ? "yellowstar":"graystar")
					  +'"></span><span class="scoreimg '+
					    (n.score>=4.0 ? "yellowstar":"graystar")
						  +'"></span><span class="scoreimg '+
						    (n.score>=6.0 ? "yellowstar":"graystar")
							  +'"></span><span class="scoreimg '+
							    (n.score>=8.0 ? "yellowstar":"graystar")
								  +'"></span><span class="scoreimg '+
								    (n.score>=10.0 ? "yellowstar":"graystar")
									  +'"></span></p><p  class="contentext" >'+n.content+'</p></li>');
					  /* $("#ullist").append('<li style="border-bottom:1px dashed #CCCCCC;height:130px">'+
			                  '<p style="font-size: 16px;margin: 10px auto 0px;color:#606060">'+n.bookname+'</p>'+
			                  '<p  style="font-size: 12px;margin: 5px auto 5px;color:#AFAFAF">'+
			                       '<span style="float:left;margin-right:10px">'+n.realname+'  发表了评论</span>'+
			                       '<span style="background-image: url('+contextpath+'/statics/image/css_sprites.png);'+
			                                     'background-position: -183px -169px; background-repeat: no-repeat;'+
												'height: 24px;width: 15px;float: left;"></span>'+
			                       '<span style="background-image: url('+contextpath+'/statics/image/css_sprites.png);'+
							                     'background-position: -183px -169px; background-repeat: no-repeat;'+
												'height: 24px;width: 15px;float: left;"></span>'+
								   '<span style="background-image: url('+contextpath+'/statics/image/css_sprites.png);'+
							                     'background-position: -183px -169px; background-repeat: no-repeat;'+
												'height: 24px;width: 15px;float: left;"></span>'+
									'<span style="background-image: url('+contextpath+'/statics/image/css_sprites.png);'+
							                     'background-position: -183px -169px; background-repeat: no-repeat;'+
												'height: 24px;width: 15px;float: left;"></span>'+
									'<span style="background-image: url('+contextpath+'/statics/image/css_sprites.png);'+
							                     'background-position: -183px -169px; background-repeat: no-repeat;'+
												'height: 24px;width: 15px;float: left;"></span>'+
			                  
			                  '</p>'+
			                  '<p  style="font-size: 14px;margin: 0;height:66px;width:230px;overflow: hidden;line-height: 22px;color:#5C6878">计算机上计算机技术进口机点击放大机房的进口发动机可放大尽快发的会计的房价负担的咖啡机说多少遍的速度高速的公司编号大航海时代</p>'+
			              '</li>'); */
				  });	
			}
	  }); 
  }
  function smallvideos(){
	  $("#comment").css({"border-bottom":"2px solid #FFFFFF","color":"#9c9c9c"});
	  $("#smallvideo").css({"border-bottom":"2px solid #5A9DA3","color":"#444544"});
	  $("#more").html('<a href="#">查看更多微视频</a>');
	  $("#ullist").html("");
	  for(var i=0;i<2;i++){
	  $("#ullist").append('<div class="play" ></div>'+
	    '<div class="video-a">'+
			  '<div id="popDiv" class="video-b"> </div>'+
              '<div class="video-c">'+
                 '<img src="'+contextpath+'/statics/testfile/testvideoimage.png"/>'+
              '</div>'+
              '<div class="video-d" >'+
                               '解剖学讲解'+
              '</div>'+
              '<div  class="video-e">'+
                 '<span class="video-f" >2017-12-22</span>'+
                 '<span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>'+
                 '<span class="video-h"></span>'+
              '</div>'+
           '</div>');
	  }
  }
</script>
</html>