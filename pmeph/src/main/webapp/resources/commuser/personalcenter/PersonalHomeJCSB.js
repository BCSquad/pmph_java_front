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
	  
	  $("#wdsbssk").keyup(function(event){
			if(event.keyCode ==13){ //回车键弹起事件
				window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val();
			  }
		});

});

function listoction(thisclicked,s){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&s="+s;
	$("#jcsbqb").removeClass("active");
	$("#jcsbwdsb").removeClass("active");
	$(thisclicked).addClass("active");
}
function listoction1(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&dateinfo=1&&pageinfo=1";
	document.getElementById("sbzzjx").className = "jcsbsbztxz";
}
function listoction2(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&dateinfo=2&&pageinfo=2";
	document.getElementById("sbyjs").className = "jcsbsbztxz";
}
function listoction3(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&is_staging=1&&online_progress=0&&pageinfo=3&&s=1";
	document.getElementById("sbzc").className = "jcsbsbztxz";
}
function listoction4(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&online_progress=1&&pageinfo=4&&s=1";
	document.getElementById("sbytj").className = "jcsbsbztxz";
}
function listoction5(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val()+"&&pageinfo=5";
	document.getElementById("qktj").className = "jcsbsbztxz";
}
