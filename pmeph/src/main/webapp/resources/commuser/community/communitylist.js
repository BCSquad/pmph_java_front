var pageSize=10   ;
var pageNumber;
$(function(){
	pageNumber=1;
	load("");	
});

//加载数据
function  load(tag){
	if(tag=="search"){
		pageNumber = 1 ;	
	}
	$.ajax({
        type:'post',
        url :contextpath+'community/list.action',
        async:false,
        dataType:'json',
        data:{
        	pageNumber : pageNumber,
        	pageSize   : pageSize,
	        searchText:$("#search-name").val()
        },
        success:function(json){
        	if(pageNumber==1&& json.length==0){
        		$("#more").hide();
        		$("#nomore").show();
        	}else if(pageNumber==1&& (json.length>0&&json.length<10)){
        		$("#more").hide();
        		$("#nomore").hide();
        	}else if(json.length==0){
        		$("#more").html("没有更多了");
        		$("#nomore").hide();
        		$("#more").show();
        	}else{
        		$("#more").html('<a href="javascript:loadMore();" style="color:#6F7070">点击获取更多精彩内容</a>');
        		$("#more").show();
        		$("#nomore").hide();
        	}
        	var html='';
        	if(tag=="search"){
        		$("#changediv").html("");
        	}
        	$.each(json,function(i,n){
        		html='<div class="items" >'+
        					'<div class="left" >'+
     		   					'<div class="items_img">最新</div>'+
     		   					'<div class="item1 cutmore">'+ 
     		   						'<a href="'+contextpath+'cmsnotice/noticeMessageDetail.action?id='+n.mid+'&&tag=FromCommunityList">'+n.title+'</a>'+  
     		   					'</div>'+
     		   					'<div class="item2 cutmore">'+
     		   					    '<p style="margin:0">'+
     		   						n.summary+
     		   						'</p>'+
     		   					'</div>'+
     		   				'</div>'+
     		   				'<div  class="right" style="text-align: center;line-height: 136px">'+
     		   					'<a href="'+contextpath+'Community/tocommunity.action?id='+n.id+'" style="color:#43B0A5">进入社区</a>'+
     		   				'</div>'+               
     		   			'</div>'; 
        		$("#changediv").append(html);
        	});
        	pageNumber++;
        }	
    });
}

//加载更多
function loadMore(){
	load("");	
}

//进入社区

function enterCommunity(){
	
}