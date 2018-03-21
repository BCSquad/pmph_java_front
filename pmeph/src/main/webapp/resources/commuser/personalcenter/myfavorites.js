
    //文章点赞或取消点赞
    function addlikec(id){
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
    //文章取消收藏
    function cancelMarkc(id,markes,cmsid,favorite_id){
    	// var cmsId=$("#cms"+cmsid).val();
    	window.message.confirm("你确定取消收藏吗？",{btn:["确定","取消"]},function(){
    		$.ajax({
    			type:'post',
    			url:contextpath+'articlecollection/cancelmark.action',
    			data:{markId:id,favoriteId:favorite_id,contentId:cmsid,markes:markes},
    			async:false,
    			dataType:'json',
    			success:function(json){
    				var pagesize=$("#pageSize").val();
                  	// window.location.href=contextpath+'articlecollection/toarticlecollectionlist.action?pagenum=1&pagesize='+pagesize+
               		//'&favoriteId='+favorite_id;
                    window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=grsc&pageNum=1&pagesize='+pagesize+
                        '&favoriteId='+favorite_id;
    			}
    		});	
    	},function(){});
    }


    //书籍点赞或取消点赞
    function addlike(id){
        var likes=$("#like"+id).text();
        $.ajax({
            type:'post',
            url:contextpath+'bookcollection/changelike.action',
            data:{bookId:id,likes:likes},
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
    //取消书籍收藏
    function cancelMark(id,markes,bkid,favorite_id){
        // var bookId=$("#book"+bkid).val();
        window.message.confirm("你确定取消收藏吗？",{btn:["确定","取消"]},function(){
            $.ajax({
                type:'post',
                url:contextpath+'bookcollection/cancelmark.action',
                data:{markId:id,favoriteId:favorite_id,bookId:bkid,markes:markes},
                async:false,
                dataType:'json',
                success:function(json){
                    var pagesize=$("#pageSize").val();
                    window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=grsc&pageNum=1&pagesize='+pagesize+
                        '&favoriteId='+favorite_id;
                }
            });
        },function(){});
    }