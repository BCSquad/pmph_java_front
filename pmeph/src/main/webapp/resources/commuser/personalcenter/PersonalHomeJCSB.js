  $(document).ready(function () {
//进行选中
	  var page=$("#pageinfo").val();
	  if(page!=null){
		  if(page==1){
			  document.getElementById("sbzzjx").className = "jcsbsbztxz";
		  }
		  if(page==2){
			  document.getElementById("sbyjs").className = "jcsbsbztxz";
		  }
		  if(page==3){
			  document.getElementById("sbzc").className = "jcsbsbztxz";
		  }
		  if(page==4){
			  document.getElementById("sbytj").className = "jcsbsbztxz";
		  }
		  if(page==5){
			  document.getElementById("qktj").className = "jcsbsbztxz";
		  }
	  }
	  
	  if ($("#pageinfo1").val()!=null && $("#pageinfo1").val()!='') {
		$("#jcsbqb").removeClass("active");
		$("#jcsbwdsb").removeClass("active");
		$("#"+$("#pageinfo1").val()).addClass("active");
	}
	  
	  
	  
	  $("#wdsbssk").keyup(function(event){
			if(event.keyCode ==13){ //回车键弹起事件
				//window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val();
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
	  
	  /*$('#select_textbook').selectlist({
	        zIndex: 5000,
	        width: 240,
	        height: 30,
	        optionHeight: 30
	    });*/
	//选择书籍 选主编
		/*$("#select_textbook").find("li").bind("click",function(){
			window.open(contextpath+"chooseEditor/toPage.action?textBookId="+$(this).attr("data-value"));
		});*/
	  if($(".select_textbook").length>0){
		  $(".select_textbook").val("-1");
			$(".select_textbook").change(function(){
				window.open(contextpath+"chooseEditor/toPage.action?textBookId="+$(this).val());
				$(".select_textbook").val("-1");
			});
	  }
	  	

});
  

function listoction(thisclicked,s){
	/*window.location.href=
		contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()
		+"&&s="+s+"&pageinfo1="+$(thisclicked).attr('id');*/
	$("#s").val(s);
	$("#pageinfo1").val($(thisclicked).attr('id'));
	$("#pageNum").val(1);
	queryMain();
}
function listoction1(pageinfo){
	/*window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&dateinfo=1&&pageinfo=1";
	document.getElementById("sbzzjx").className = "jcsbsbztxz";*/
	$("#is_staging").val("");
	$("#online_progress").val("");
	$("#dateinfo").val(pageinfo);
	$("#pageinfo").val(pageinfo);
	$("#pageNum").val(1);
	queryMain();
}
/*function listoction2(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&dateinfo=2&&pageinfo=2";
	document.getElementById("sbyjs").className = "jcsbsbztxz";
}*/
function listoction3(){
/*	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()
	+"&&is_staging=1&&online_progress=0&&pageinfo=3&&s=1";
	document.getElementById("sbzc").className = "jcsbsbztxz";*/
	$("#s").val(1);
	$("#dateinfo").val("");
	$("#is_staging").val(1);
	$("#online_progress").val("(0)");
	$("#pageinfo1").val("jcsbwdsb");
	$("#pageinfo").val(3);
	$("#pageNum").val(1);
	queryMain();
}
function listoction4(){
	/*window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()
	+"&&online_progress=1&&pageinfo=4&&s=1";
	document.getElementById("sbytj").className = "jcsbsbztxz";*/
	
	$("#s").val(1);
	$("#dateinfo").val("");
	$("#is_staging").val("");
	$("#online_progress").val("(1,2,3)");
	$("#pageinfo1").val("jcsbwdsb");
	$("#pageinfo").val(4);
	$("#pageNum").val(1);
	queryMain();
	
}
function listoction5(){
	/*window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&pageinfo=5";
	document.getElementById("qktj").className = "jcsbsbztxz";*/
	
	
	$("#is_staging").val("");
	$("#online_progress").val("");
	$("#dateinfo").val("");
	$("#pageinfo").val(5);
	$("#pageNum").val(1);
	queryMain();
}

/**
 * 打开第一主编选择编委界面
 * @param textbook_id
 */
function chooseEditor(textbook_id,btn){
	//$('#select_textbook').focus();
	window.open(contextpath+"chooseEditor/toPage.action?textBookId="+textbook_id);
	
}