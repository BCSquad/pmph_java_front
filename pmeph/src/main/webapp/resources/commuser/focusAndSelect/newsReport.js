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
	        window.location.href=contxtpath+"/cmsnotice/tolist.action?materialId="+$("#materialId").val(); 
	    }


}
var pageSize   = 10;
var pageNumber = 1 ;
var order;
$(function(){
    setTimeout(function () {
        $("#sort-wrapper").attr("style","width:68px;height:20px");
    },0)

	 $('#sort-wrapper').selectlist({
         zIndex: 10,
         width: 100,
         height: 20,
         optionHeight: 20,
         triangleColor: '#333333',
         onChange:function(){
        	order = $("input[name=sort-wrapper]").val();
     		pageNumber = 1;
     		$("#content").html("");
     		$("#loadMore").show();
     		loadData ();
         }
     });
	 order=$("input[name=sort-wrapper]").val();
	loadData();
	//加载更多
	$("#loadMore").click(function(){
		loadData();
	});
	

});

function loadData(){
    var materialId=$("#materialId").val();
    $.ajax({
        type:'get',
        url :contxtpath+'/cmsinfoletters/list.action',
        async:false,
        contentType: 'application/json',
        dataType:'json',
        data:{
            pageNumber : pageNumber,
            pageSize   : pageSize,
            order      : order,
            materialId:$("#materialId").val()
        },
        success:function(json){
            var list=json.list;
            if(null != list && list.length >= 0){
                pageNumber ++ ;
                var i= 0;
                for( ; i<list.length ; i++ ){
                    var tarId="cms"+list[i].id;
                    var html =
                        "<div class=\"items\"> "+
                        (list[i].isPromote?"<div class='items_img'>推荐</div> ":"")+
                        "<div class=\"item1\">"+
                        "<a href='"+contextpath;
                    if(list[i].categoryId==1){
                        html+="articledetail/toPage.action?wid="+list[i].id;
                    }else if(list[i].categoryId==2){
                        html+="inforeport/toinforeport.action?id="+list[i].id+'&&materialId='+materialId;
                    }else{
                        html+="cmsnotice/noticeMessageDetail.action?id="+list[i].mid+"&&materialId="+list[i].materialId+"&&csmId="+list[i].id ;
                    }
                    html+="'>"+list[i].title+"</a></div> "+
                        "<div class=\"item2 cutmore\"><p >";
                    if(list[i].categoryId==3 && list[i].isMaterialEntry){
                        html+=list[i].notice;
                    }else{
                        html+= (list[i].summary==null?"(摘要为空)":list[i].summary);
                    }
                    html+="</p></div> "+
                        "<div class=\"item3\">  "+
                        "<div style=\"float: left;\">" ;
                    if(list[i].categoryId==3 ){
                        html+="<div style='float:left;margin-right: 20px;'>截止时间："+list[i].deadline+"</div>";
                    }else{
                        html+= "<div style='float:left;height:57px'><span class='cms-icon look'></span></div>" +
                            "<div style='float:left;margin-right: 20px;'>"+list[i].clicks+"</div>" +
                            "<div style='float:left;height:57px'>" +
                            "<span class='cms-icon "+(json[tarId]>0? "good":"nogood")+"' id='like"+list[i].id+"' onclick=\"addlike('"+list[i].id+"')\"></span></div>" +
                            "<div style='float:left;color:"+(json[tarId]>0? "#1abd44":"#b5b5b5")+"' id='likes"+list[i].id+"'>"+list[i].likes+"</div>" ;
                    }
                    html+="</div>"+
                        "<div style='float:right'>发布时间："+formatDate((list[i].authDate!=null?list[i].authDate:list[i].gmtCreate),'yyyy.MM.dd')+"</div> "+
                        "</div> "+
                        "</div> ";
                    $("#content").append(html);
                }
                if(list.length < pageSize){
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
	
//点赞或取消点赞
function addlike(id){
	$.ajax({
        type:'get',
        url :contxtpath+'/cmsinfoletters/addlike.action',
        async:false,
        contentType: 'application/json',
        dataType:'json',
        data:{
        	id:id
        },
        success:function(json){
        	 if(json.returncode=="OK"){
 				if($("#like"+id).hasClass("good")){
 					$("#like"+id).removeClass("good");
 					$("#like"+id).addClass("nogood");
 					$("#likes"+id).css({"color":"#b5b5b5"});
 		    	}else{
 		    		$("#like"+id).removeClass("nogood");
 					$("#like"+id).addClass("good");
 					$("#likes"+id).css({"color":"#1abd44"});
 		    	}
 				$("#likes"+id).html(json.likes);
 			   }
        }});
}