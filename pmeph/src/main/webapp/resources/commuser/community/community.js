//初始化页面
$(function () {
    var startnum= parseInt($("#pagenum").val());
    var  pagetotal=parseInt($("#pagetotal").val());
    Page({
        num:pagetotal,
        startnum: startnum,
        elem:$("#page1"),
        callback: function (n) {
            var size=$("input[name='edu']").val();

            location.href=contextpath+"community/toCommunity.action?pageNumber="+n+"&pageSize="+size+"&id="+$("#sid").val();
        }
    });
    $('select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30,
        onChange: function () {
            var size=$("input[name='edu']").val();
            location.href=contextpath+"community/toCommunity.action?pageNumber=1&pageSize="+size+"&id="+$("#sid").val();
        }
    });

});



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
				  $("#more").html('<a href="'+contextpath+'community/morecomments.action?materialId='+$("#materialId").val()+'">查看更多精选书评</a>');
				  $("#ullist").html("");
				  $.each(json.comments,function(i,n){
					  $("#ullist").append('<li class="commentli"><p class="title">'+
					  n.bookname
					  +'</p><p  class="message"><span class="name" >'+
					    n.nickname
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
				  html+='<li style="margin-bottom:10px">'+
	              '<div class="video-c">'+
	                 '<div class="videou" id="videou'+n.id+'" src="http://'+remoteVideoUrl+'/v/play/'+n.file_name+'" style="width:230px;height:184px" controls type="mp4"'+
	              'poster="'+contextpath+'image/'+n.cover+'.action"></div>'+
	              '</div>'+
	              '<div class="video-d" >'+
	                               n.title+
	              '</div>'+
	              '<div  class="video-e">'+
					  '<input type="hidden" id="playclicks'+n.id+'" value="0">'+
	                 '<span class="video-f" >'+getDate(n.gmt_create)+'</span>'+
	                 '<span class="video-g" style="float:right;line-height:30px;margin-left:5px" id="c'+n.id+'">'+n.clicks+'</span>'+
	                 '<span class="video-h"></span>'+
	              '</div>'+
	           '</div></li>';
			  });
			

			  $("#ullist").append(html);
			  
			  $(".videou").each(function () {
			        var $this = $(this);
			        var videoObject = {
			            container: "#" + $this.attr("id"),
			            variable: 'player',
			            autoplay: false,
			            /*flashplayer: true,*/
			            video:$this.attr("src"),
			            poster: $this.attr("poster")

			        };
			        var player = new ckplayer(videoObject);
                  player.addListener("play",function () {
					  var vid=$this.attr("id").substring(6);
					  var c=$("#playclicks"+vid).val();
					  if(c==0){
                          $.ajax({
                              type:"post",
                              url:contextpath+'community/videoCount.action',
                              data:{vid:vid},
                              success:function(data){
                                  // console.log(data.clicks);
                                  $("#c"+vid).text(data.clicks);
                                  $("#playclicks"+vid).val(1);
                              },
                              error:function(){
                                  alert('服务器错误');
                              }
                          });
					  }
                  });
			    });
			  
			}
	  });
  }
  
  function getDate(mtime) {  
	  //shijianchuo是整数，否则要parseInt转换  
	  var time = new Date(mtime);  
	  var y = time.getFullYear();  
	  var m = time.getMonth()+1;  
	  var d = time.getDate();  
	  var h = time.getHours();  
	  var mm = time.getMinutes();  
	  var s = time.getSeconds();  
	  return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);  
	}
  function add0(m){return m<10?'0'+m:m };

 function toMain(){
     window.location.href=contextpath+"community/tolist.action";
 }

