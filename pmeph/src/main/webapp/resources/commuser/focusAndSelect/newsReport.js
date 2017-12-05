function ChangeDiv(type){
	    if(type=='infoReport'){
	    	document.getElementById("infoReport").setAttribute("class","clicked");
	        document.getElementById("selectAnnco").setAttribute("class","clickbefore");
	        //document.getElementById("filesgx").setAttribute("class","hidden");
	        //document.getElementById("commnions").setAttribute("class","show");
	    }else{
	        document.getElementById("selectAnnco").setAttribute("class","clicked");
	        document.getElementById("infoReport").setAttribute("class","clickbefore");
	        //document.getElementById("commnions").setAttribute("class","hidden");
	        //document.getElementById("filesgx").setAttribute("class","show");
	        window.location.href=contxtpath+"/cmsnotice/tolist.action"; 
	    }


}
$(function(){
	var pageSize   = 10;
	var pageNumber = 1 ;
	var order      = $('#sort-wrapper').val();
	
	loadData();
	
	//排序切换
	$('#sort-wrapper').change(function(event){
		order = $(this).val();
		pageNumber = 1;
		$("#content").html("");
		$("#loadMore").show();
		loadData ();
	});
	//加载更错
	$("#loadMore").click(function(){
		loadData();
	});
	
	function loadData(){
		$.ajax({
	        type:'get',
	        url :contxtpath+'/cmsinfoletters/list.action',
	        async:false,
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	pageNumber : pageNumber,
	        	pageSize   : pageSize,
	        	order      : order
	        },
	        success:function(responsebean){
	        	if(null != responsebean && responsebean.length >= 0){
	        		pageNumber ++ ;
	        		var i= 0;
	        		for( ; i<responsebean.length ; i++ ){
	        			var html =
	                		"<div class=\"items\"> "+ 
	                		    (responsebean[i].isPromote?"<div class='items_img'>推荐</div> ":"")+
	        	                "<div class=\"item1\">"   +responsebean[i].title+"</div> "+
	        	                "<div class=\"item2\"><p>"+responsebean[i].summary+"</p></div> "+
	        	                "<div class=\"item3\">  "+
	        	                    //"<div style=\"float: left;\">截止日期:2017-06-30</div>  "+
	        	                    "<div style=\"float:right\">发布日期："+formatDate(responsebean[i].authDate,"yyyy.MM.dd")+"</div> "+ 
	        	                "</div> "+
	                        "</div> ";
	                	$("#content").append(html);
	        		}
	        		if(responsebean.length < pageSize){
	        			$("#loadMore").hide();
	        		}
	        	}
	        }
	    });
	}
	
	function formatDate(nS,str) {
		  if(!nS){
		    return "";
		  }
		  var date=new Date(nS);
		  var year=date.getFullYear();
		  var mon = date.getMonth()+1;
		  var day = date.getDate();
		  var hours = date.getHours();
		  var minu = date.getMinutes();
		  var sec = date.getSeconds();
		  if(str=='yyyy-MM-dd'){
		   return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day);
		  }else if(str=='yyyy.MM.dd'){
		   return year + '.' + (mon < 10 ? '0' + mon : mon) + '.' + (day < 10 ? '0' + day : day);
		  }else{
		   return year + '-' + (mon < 10 ? '0' + mon : mon) + '-' + (day < 10 ? '0' + day : day) + ' ' + (hours < 10 ? '0' + hours : hours) + ':' + (minu < 10 ? '0' + minu : minu) + ':' + (sec < 10 ? '0' + sec : sec);
		  }

	}
})