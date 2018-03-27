  $(document).ready(function () {
//进行选中
	  var page=$("#auth_progress").val();
	  var is_handled = $("#is_handled").val();
	  if(page!=null && is_handled != "1"){
		  if(page=='0'){
			  document.getElementById("sbzzjx").className = "jcsbsbztxz";
		  }
		  if(page==1){
			  document.getElementById("sbyjs").className = "jcsbsbztxz";
		  }
		  if(page==3){
			  document.getElementById("sbzc").className = "jcsbsbztxz";
		  }
		  if(page==2){
			  document.getElementById("sbytj").className = "jcsbsbztxz";
		  }
		  if(page==""){
			  document.getElementById("allStatus").className = "jcsbsbztxz";
		  }
	  }else{
		  $("#sbysl").addClass("jcsbsbztxz");
	  }
	  
	  if ($("#pageinfo1").val()!=null && $("#pageinfo1").val()!='') {
		$("#jcsbqb").removeClass("active");
		$("#jcsbwdsb").removeClass("active");
		$("#"+$("#pageinfo1").val()).addClass("active");
	  }else{
		  $("#jcsbqb").addClass("active");
	  }
	  
	  $(".jcsbsbzt").each(function(){
		  var $t = $(this);
		  $t.unbind().bind("click",function(){
			if ($t.val()=="ysl") { //已受理按钮点击
				$("#is_handled").val(1);
				$("#auth_progress").val(1);
			}else if($t.val()=="0"||$t.val()=="1"){ //暂存 提交 按钮点击
				$("#auth_progress").val($t.val());
				$("#is_handled").val(0);           //此时不能筛选已受理的数据
			}else{
				$("#auth_progress").val($t.val()); //通过未通过 无所谓是否受理
				$("#is_handled").val("");
			}
			  $("#pageNum").val(1);
			  queryMain();
		  });
	  });
	  
	  $("#wdsbssk").keyup(function(event){
			if(event.keyCode ==13){ //回车键弹起事件
				$("#bookname").val($("#wdsbssk").val());
				$("#pageNum").val(1);
				queryMain();
			  }
		});
	  $("#btn_wdsbssk").click(function(event){
		  $("#bookname").val($("#wdsbssk").val());
			$("#pageNum").val(1);
			queryMain();
	  });

});

function listoction(thisclicked,s){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=wycs&bookname='+$("#wdsbssk").val()+"&&isMine="+s+"&pageinfo1="+$(thisclicked).attr('id');
}





/**
 * 查看选题
 * @param id
 */
function viewTopic(id){
	window.open(contextpath+"bookdeclare/toBookdeclareDetail.action?topic_id="+id);
}

//修改
function updateTopic(id){
	window.open(contextpath+"bookdeclare/toBookdeclareZc.action?topic_id="+id);
}