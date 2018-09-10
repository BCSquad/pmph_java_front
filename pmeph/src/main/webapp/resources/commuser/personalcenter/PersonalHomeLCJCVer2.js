  $(document).ready(function () {

//进行选中
	  var page=$("#pageinfo").val();
	  $("#clnicalTag"+$("#tag_num").val()).addClass("active");

	  $("#wdsbssk").keyup(function(event){
			if(event.keyCode ==13){ //回车键弹起事件
				querySearch();
			  }
		});

});


  function toproductdetail(product_id) {
      location.href = contextpath + 'expertation/lookforward.action?product_id='+product_id;
  }
  function tojoin(product_id) {
      location.href = contextpath + 'homepage/toproductdetail.action?state='+product_id;
  }


  function toPersondetail(product_id){
      $.ajax({
          type: "POST",
          url:contextpath+'expertation/toPersondetail.action?product_id='+product_id,
          dataType:"json",
          success: function(json) {
              if(json=="yes"){
                  toproductdetail(product_id);
              }else{
                  window.message.info("您没有申报过当前公告")
              }
          }
      });
  }

function listoction(tag_num){
	$("#tag_num").val(tag_num);
	$(".clnicalTag").removeClass("active");
	$("#clnicalTag"+$("#tag_num").val()).addClass("active");
	
	if(tag_num == "1"){ //暂存
		$("#online_progress").val("0");
		$("#finalResult").val("0");
		$("#pmphAudit").val("");
	}else if(tag_num == "2"){ //正在遴选
		$("#online_progress").val("1,3");
		$("#finalResult").val("0");
		$("#pmphAudit").val("");
	}else if(tag_num == "3"){ //遴选结束
		$("#online_progress").val("");
		$("#finalResult").val("1");
		$("#pmphAudit").val("");
	}else if(tag_num == "4"){ //报名成功
		$("#online_progress").val("");
		$("#finalResult").val("1");
		$("#pmphAudit").val("1");
	}else{ //全部
		$("#online_progress").val("");
		$("#finalResult").val("");
		$("#pmphAudit").val("");
	}
	
	$("#pageNum").val(1);
	queryMain();
}

function querySearch(){
  var str=$("#wdsbssk").val();
  $("#search").val(str);
  $("#pageNum").val(1);
  queryMain();
}




