 $(function () {
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
    	        	var favoriteId=$("#favoriteId").val();
    	            window.location.href=contextpath+'articlecollection/toarticlecollectionlist.action?pagenum='+n+'&pagesize='+pagesize+
    	            		'&favoriteId='+favoriteId;
    	        }
    	 });
      $('select').selectlist({
            zIndex: 100,
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange: function () {
            	var pagesize=$("input[name='edu']").val();
              	var favoriteId=$("#favoriteId").val();
              	 window.location.href=contextpath+'articlecollection/toarticlecollectionlist.action?pagenum=1&pagesize='+pagesize+
           		'&favoriteId='+favoriteId;
            }  //自定义模拟选择列表项chang
        });
    }); 
    
    
    //点赞或取消点赞  
    function addlike(id){
    	var likes=$("#like"+id).text();
    	$.ajax({
			type:'post',
			url:contextpath+'articlecollection/changelike.action',
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
    	window.message.confirm("您确定取消收藏吗？",{btn:["确定","取消"]},function(){
    		$.ajax({
    			type:'post',
    			url:contextpath+'articlecollection/cancelmark.action',
    			data:{markId:id,favoriteId:favoriteId,contentId:cmsId,markes:markes},
    			async:false,
    			dataType:'json',
    			success:function(json){
    				var pagesize=$("input[name='edu']").val();
                  	var favoriteId=$("#favoriteId").val();
                  	 window.location.href=contextpath+'articlecollection/toarticlecollectionlist.action?pagenum=1&pagesize='+pagesize+
               		'&favoriteId='+favoriteId;
    			}
    		});	
    	},function(){});
    }
    //删除收藏夹
    function delFavorite(id){
    	window.message.confirm("您确定删除吗？",{btn:["确定","取消"]},function(){
    		 window.location.href=contextpath+'articlecollection/delfavorite.action?favoriteId='+id;
    	},function(){});
    }
    