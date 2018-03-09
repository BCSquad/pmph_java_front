function ChangeDiv(type){
	    if(type=='infoReport'){
	    	document.getElementById("infoReport").setAttribute("class","clicked");
	        document.getElementById("selectAnnco").setAttribute("class","clickbefore");
	        //document.getElementById("filesgx").setAttribute("class","hidden");
	        //document.getElementById("commnions").setAttribute("class","show");
	        window.location.href=contxtpath+"/cmsinfoletters/tolist.action?materialId="+$("#materialId").val();
	    }else{
	        document.getElementById("selectAnnco").setAttribute("class","clicked");
	        document.getElementById("infoReport").setAttribute("class","clickbefore");
	        //document.getElementById("commnions").setAttribute("class","hidden");
	        //document.getElementById("filesgx").setAttribute("class","show");
	        window.location.href=contxtpath+"/cmsnotice/tolist.action?materialId="+$("#materialId").val(); 
	    }


}
$(function(){
	var pageSize   = 10;
	var pageNumber = 1 ;
	var order;
	 $('#sort-wrapper').selectlist({
         zIndex: 10,
         width: 70,
         height: 20,
         optionHeight: 20,
         triangleColor: '#333333',
         onChange:function(){
        	order = $('input[name=sort-wrapper]').val();
        	pageNumber = 1;
    		$("#content").html("");
    		$("#loadMore").show();
    		loadData ();
         }
     });
	 order = $('input[name=sort-wrapper]').val();
	
	loadData();
	
	//加载更错
	$("#loadMore").click(function(){
		loadData();
	});
	
	function loadData(){
		
		$.ajax({
	        type:'get',
	        url :contxtpath+'/cmsnotice/list.action',
	        async:false,
	        contentType: 'application/json',
	        dataType:'json',
	        data:{
	        	pageNumber : pageNumber,
	        	pageSize   : pageSize,
	        	order      : order,
	        	materialId:$("#materialId").val()
	        },
	        success:function(responsebean){
	        	if(null != responsebean && responsebean.length >= 0){
	        		pageNumber ++ ;
	        		var i= 0;
	        		for( ; i<responsebean.length ; i++ ){
	        			var deadline =  responsebean[i].deadline != null ?
	        					"<div style=\"float: left;\">截止日期:"+formatDate(responsebean[i].deadline,"yyyy-MM-dd")+"</div>  ":
	        						"";
	        			var html = 
	                		"<div class=\"items\"> "+ 
	                		    (responsebean[i].isPromote?"<div class='items_img'>推荐</div> ":"")+
	        	                "<div class=\"item1 cutmore\">" +
	        	                "<a href='"+contextpath+"cmsnotice/noticeMessageDetail.action?id="+responsebean[i].mid+"&&materialId="+responsebean[i].materialId+"&&csmId="+responsebean[i].id+"'>" +responsebean[i].title+"</a></div> "+
	        	                "<div class=\"item2 cutmore\"><p style='margin:0;height:40px;line-height:20px'>";
	        	                if(responsebean[i].isMaterialEntry==true){
	        	                	html+=responsebean[i].notice;
	        	                }else{
	        	                	html+=(responsebean[i].contentxt==null || responsebean[i].contentxt==''?"（内容为空）":responsebean[i].contentxt);
	        	                }
	        			       html+="</p></div> "+
	        	                "<div class=\"item3\">  "+
	        	                     deadline+
	        	                    "<div style=\"float:right\">发布日期："+formatDate(responsebean[i].gmtCreate,"yyyy.MM.dd")+"</div> "+ 
	        	                "</div> "+
	                        "</div> ";
	                	$("#content").append(html);
	        		}
	        		if(responsebean.length < pageSize){
	        			$("#loadMore").hide();
	        		}
	        	}else{
	        		if(list.length==0){
	        			$("#nomore").css({"display":"block"});
	        			}
	        	}
	        }
	    });
	}
	
});
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