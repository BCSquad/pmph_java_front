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
<style type="text/css">
   a{text-decoration: none}
</style>
<body>
      <jsp:include page="/pages/comm/head.jsp"></jsp:include> 
       <div class="content-wrapper">
           <div style="height:40px;margin-top:20px;font-size: 16px;color:#9c9c9c">
                <a href="#" style="color:#9c9c9c">首页</a>&gt;多喝水的环境的环境对方的回复电话机房的肌肤的发动机和放大和京东方
           </div>
           <div  style="width:900px;float:left;">
           <div style="border-left:10px solid #499299;width:890px;float:left;height:178px;background-color: #EEEEEE;font-size: 16px;color:#4d4e4e">
                <div style="width:46px;height:100px;float:left;text-align: center;height:178px">都<br>红<br>红</div>
                <div style="width:748px;float:left;margin-top:20px;height:158px">
                     <ul style="list-style:none;">
                        <c:forEach items="${reportlist}" var="report">
		                        <li style="border-bottom:1px dashed black;height:35px;vertical-align: bottom">
		                                <div>
		                                <div style="float:left;width:20px;height:35px;line-height:35px ">&gt;</div>
		                                <a href="inforeport/toinforeport.action?id=${report.id }"><div style="float:left;width:630px;height:35px;line-height:35px;overflow: hidden;color:#4d4e4e">${report.title}</div></a>
		                                <div style="float:right;width:80px;height:35px;line-height:35px">
		         
		                                <fmt:formatDate value="${report.gmt_create }" pattern="yyyy-MM-dd"/>
		                                </div>
		                                </div>
		                        </li>
                        </c:forEach>  
                     </ul>
                </div>
                <div style="float:left;text-align: right;width:80px;height:168px;margin-top:10px">全部&gt;&gt;</div>
           </div>
           <div style="width:900px;float:left;">
           <div style="float:left;width:900px;height:50px;line-height:50px;font-size: 16px;">
	           <div style="background-image:url(statics/image/css_sprites.png);background-position:-10px -340px;width:20px;height:20px;float:left; margin-top: 15px;"></div>
               <div style="float:left;margin-left:10px">本套教材图书</div>
           </div>
           <div style="margin:0px auto 0px;width:840px;font-size: 16px;">
               <c:forEach items="${booklist }" var="book">
                    <div style="width:210px;float:left;">
		                  <div style="width:126px;height:126px;margin:20px auto 0px;text-align: center">
		                     <img alt="" src="${book.image_url=='DEFAULT'? 'statics/image/564f34b00cf2b738819e9c35_122x122!.jpg':book.image_url }" style="height:126px;">
		                  </div>
                 	<div style="width:190px;height:60px;line-height: 30px;text-align: center;margin:10px auto 0px"><a href="readdetail/todetail.action?id=${book.bookId }" style="color:#343434;">${book.textbook_name }</a> </div>
               </div>
               
               </c:forEach>
               </div>    
           </div>
           </div>
           <div style="float:left;width:300px;height:650px; ">
             <div style="height:40px;font-size: 16px;">
                 <div style="height:38px;border-bottom:2px solid #5A9DA3;line-height: 38px;float:left;margin-left:20px;" id="comment" onclick="comments()">
                                                                             精彩书评
                 </div>
                 <div style="height:38px;border-bottom:2px solid #FFFFFF;line-height: 38px;float:left;margin-left:20px" id="smallvideo" onclick="smallvideos()">
                                                                              微视频                                                       
                 </div>
             </div>
             <div style="margin-left:20px;width:230px;">
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
                   
                     <li style="border-bottom:1px dashed #CCCCCC;height:130px">
                          <p style="font-size: 16px;margin: 10px auto 0px;color:#606060">计算机上计算机技术</p>
                          <p  style="font-size: 12px;margin: 5px auto 5px;color:#AFAFAF">
	                           <span style="float:left;margin-right:10px">余艾莲  发表了评论</span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
                          
                          </p>
                          <p  style="font-size: 14px;margin: 0;height:66px;width:230px;overflow: hidden;line-height: 22px;color:#5C6878">计算机上计算机技术进口机点击放大机房的进口发动机可放大尽快发的会计的房价负担的咖啡机说多少遍的速度高速的公司编号大航海时代</p>
                      </li>
                       <li style="border-bottom:1px dashed #CCCCCC;height:130px">
                          <p style="font-size: 16px;margin: 10px auto 0px;color:#606060">计算机上计算机技术</p>
                          <p  style="font-size: 12px;margin: 5px auto 5px;color:#AFAFAF">
	                           <span style="float:left;margin-right:10px">余艾莲  发表了评论</span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
                          
                          </p>
                          <p  style="font-size: 14px;margin: 0;height:66px;width:230px;overflow: hidden;line-height: 22px;color:#5C6878">计算机上计算机技术进口机点击放大机房的进口发动机可放大尽快发的会计的房价负担的咖啡机说多少遍的速度高速的公司编号大航海时代</p>
                      </li>
                       <li style="border-bottom:1px dashed #CCCCCC;height:130px">
                          <p style="font-size: 16px;margin: 10px auto 0px;color:#606060">计算机上计算机技术</p>
                          <p  style="font-size: 12px;margin: 5px auto 5px;color:#AFAFAF">
	                           <span style="float:left;margin-right:10px">余艾莲  发表了评论</span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
                          
                          </p>
                          <p  style="font-size: 14px;margin: 0;height:66px;width:230px;overflow: hidden;line-height: 22px;color:#5C6878">计算机上计算机技术进口机点击放大机房的进口发动机可放大尽快发的会计的房价负担的咖啡机说多少遍的速度高速的公司编号大航海时代</p>
                      </li>
                        <li style="border-bottom:1px dashed #CCCCCC;height:130px">
                          <p style="font-size: 16px;margin: 10px auto 0px;color:#606060">计算机上计算机技术</p>
                          <p  style="font-size: 12px;margin: 5px auto 5px;color:#AFAFAF">
	                           <span style="float:left;margin-right:10px">余艾莲  发表了评论</span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
	                           <span style="background-image: url(<%=path%>/statics/image/css_sprites.png);
                                             background-position: -183px -169px; background-repeat: no-repeat;
    										height: 24px;width: 15px;float: left;"></span>
                          
                          </p>
                          <p  style="font-size: 14px;margin: 0;height:66px;width:230px;overflow: hidden;line-height: 22px;color:#5C6878">计算机上计算机技术进口机点击放大机房的进口发动机可放大尽快发的会计的房价负担的咖啡机说多少遍的速度高速的公司编号大航海时代</p>
                      </li> 
                 </ul>
             </div>
             <div id="" style="margin-top:10px;margin-left:20px;height:20px;width:230px;text-align: center;color:#404243">查看更多精彩书评</div>
           </div> 
            <div style="clear:both;width:100%"></div>
       </div>
      
      <jsp:include page="/pages/comm/tail.jsp"></jsp:include> 
</body>
<script type="text/javascript">
  function comments(){
	  $("#comment").css({"border-bottom":"2px solid #5A9DA3"});
	  $("#smallvideo").css({"border-bottom":"2px solid #FFFFFF"});
	  $("#ullist").html("");
	  for ( var i = 0; i<4; i++) {
		  $("#ullist").append('<li style="border-bottom:1px dashed #CCCCCC;height:130px">'+
                  '<p style="font-size: 16px;margin: 10px auto 0px;color:#606060">计算机上计算机技术</p>'+
                  '<p  style="font-size: 12px;margin: 5px auto 5px;color:#AFAFAF">'+
                       '<span style="float:left;margin-right:10px">余艾莲  发表了评论</span>'+
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
              '</li>');
	}
	
  }
  function smallvideos(){
	  $("#comment").css({"border-bottom":"2px solid #FFFFFF"});
	  $("#smallvideo").css({"border-bottom":"2px solid #5A9DA3"});
	  $("#ullist").html("");
	  for(var i=0;i<2;i++){
	  $("#ullist").append('<div style="width:230px;border:1px solid #CCCCCC;height:260px;margin-top:15px">'+
			  '<div id="popDiv" style="z-index:99;position:absolute;background-color: #000;height:184px;width:230px;opacity:0.5;"> </div>'+
              '<div style="width:230px;background-color: #EEEEEE;height:184px">'+
                 '<img src="'+contextpath+'/statics/testfile/testvideoimage.png"/>'+
              '</div>'+
              '<div style="width:210px;border-bottom:1px solid #EEEEEE;height:44px;margin:0px auto 0px;line-height: 44px;font-size: 16px;color:#333333">'+
                               '解剖学讲解'+
              '</div>'+
              '<div  style="height:30px;width:210px;margin:0px auto 0px;color:#999999;font-size: 14px">'+
                 '<span style="float:left;line-height: 30px">2017-12-22</span>'+
                 '<span style="float:right;line-height: 30px;margin-left:5px">98</span>'+
                 '<span style="float:right;width:20px;height:30px;'+ 
                  'background-image: url('+contextpath+'/statics/image/css_sprites.png);'+
                  'background-position:-418px -144px;"></span>'+
              '</div>'+
           '</div>');
	  }
  }
</script>
</html>