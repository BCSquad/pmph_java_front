  $(document).ready(function () {
//进行选中
	  var page=$("#auth_progress").val();
	  if(page!=null){
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
			  
			  $("#auth_progress").val($t.val());
			  queryMain();
		  });
	  });
	  
	  $("#wdsbssk").keyup(function(event){
			if(event.keyCode ==13){ //回车键弹起事件
				$("#bookname").val($("#wdsbssk").val());
				queryMain();
			  }
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
	window.open(contextpath+"");
}