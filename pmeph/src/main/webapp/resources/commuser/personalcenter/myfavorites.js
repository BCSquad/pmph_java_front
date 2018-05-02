//收藏夹切换
$(function(){
    $(".replytag").each(function(){
        var $t = $(this);
        $t.unbind().bind("click",function(){
            var tid = $t.attr("id");
            if (tid=="replytag_all") {
                $("#is_long").val("");
            }else if(tid=="replytag_toreply"){
                $("#is_long").val("1");
            }else if(tid=="replytag_replied"){
                $("#is_long").val("0");
            }else if(tid=="replytag_replied2"){
                $("#is_long").val("2");
            }
            $(".replytag").removeClass("active");
            $t.addClass("active");
            $("#pageNum").val(1);
            queryMain();
        });
    });
});


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
    	window.message.confirm("您确定取消收藏吗？",{btn:["确定","取消"]},function(){
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
        window.message.confirm("您确定取消收藏吗？",{btn:["确定","取消"]},function(){
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

//信息快报点赞或取消点赞
function addlikex(id){
    var likes=$("#likex"+id).text();
    $.ajax({
        type:'post',
        url:contextpath+'articlecollection/changelike.action',
        data:{contentId:id,likes:likes},
        async:false,
        dataType:'json',
        success:function(json){
            if(json.returncode=="OK"){
                if($("#goodx"+id).hasClass("good")){
                    $("#goodx"+id).removeClass("good");
                    $("#goodx"+id).addClass("nogood");
                    $("#likex"+id).css({"color":"#b5b5b5"});
                }else{
                    $("#goodx"+id).removeClass("nogood");
                    $("#goodx"+id).addClass("good");
                    $("#likex"+id).css({"color":"#1abd44"});
                }
                $("#likex"+id).text(json.likes);
            }

        }
    });
}
//信息快报取消收藏
function cancelMarkx(id,markes,cmsid,favorite_id){
    // var cmsId=$("#cms"+cmsid).val();
    window.message.confirm("您确定取消收藏吗？",{btn:["确定","取消"]},function(){
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
