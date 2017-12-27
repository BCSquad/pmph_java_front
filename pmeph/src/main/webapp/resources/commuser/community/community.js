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
				  $("#more").html('<a href="'+contextpath+'community/morecomments.action">查看更多精彩书评</a>');
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
	  $("#ullist").append('<li><div class="play" ></div>'+
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
           '</div></li>');
	  }
  }