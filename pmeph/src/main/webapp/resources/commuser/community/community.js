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
				  $("#more").html('<a href="'+contextpath+'community/morecomments.action?materialId='+$("#materialId").val()+'">查看更多精彩书评</a>');
				  $("#ullist").html("");
				  $.each(json.comments,function(i,n){
					  $("#ullist").append('<li class="commentli"><p class="title">'+
					  n.bookname
					  +'</p><p  class="message"><span class="name" >'+
					    n.username
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
									  +'"></span></p><p  class="contentext" >'+n.contentxt+'</p></li>');  
				  });	
			}
	  }); 
  }
  function smallvideos(id){

	  $.ajax({
			type:'post',
			url:contextpath+'community/videos.action',
			data:{materialId:id},
			async:false,
			dataType:'json',
			success:function(json){
			  $("#comment").css({"border-bottom":"2px solid #FFFFFF","color":"#9c9c9c"});
			  $("#smallvideo").css({"border-bottom":"2px solid #5A9DA3","color":"#444544"});
			  $("#more").html('<a href="'+contextpath+'community/morevideo.action?materialId='+id+'">查看更多微视频</a>');
			  $("#ullist").html("");
			 var html="";
			  $.each(json.result,function(i,n){
				  html+='<li>'+
	              '<div class="video-c">'+
	                 '<video src="http://119.254.226.115:11000/pmph_vedio/file/'+n.file_name+'" style="width:230px;height:184px" controls type="mp4"'+
	              'poster="'+contextpath+'image/'+n.cover+'.action"></video>'+
	              '</div>'+
	              '<div class="video-d" >'+
	                               n.title+
	              '</div>'+
	              '<div  class="video-e">'+
	                 '<span class="video-f" >'+n.gmt_create+'</span>'+
	                 '<span class="video-g" style="float:right;line-height:30px;margin-left:5px">'+n.clicks+'</span>'+
	                 '<span class="video-h"></span>'+
	              '</div>'+
	           '</div></li>';
			  });
			

			  $("#ullist").append(html);
			  
			 /* '<li>'+
			              '<div class="video-c">'+
			                 '<video src="" style="width:230px;height:184px" controls type="mp4"'+ 
			              'poster="'+contextpath+'statics/testfile/testvideoimage.png"></video>'+
			              '</div>'+
			              '<div class="video-d" >'+
			                               '解剖学讲解'+
			              '</div>'+
			              '<div  class="video-e">'+
			                 '<span class="video-f" >2017-12-22</span>'+
			                 '<span class="video-g" style="float:right;line-height:30px;margin-left:5px">98</span>'+
			                 '<span class="video-h"></span>'+
			              '</div>'+
			      '</div></li>'*/
			  
			}
	  });
  }