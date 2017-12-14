  $(document).ready(function () {

	  $("#wdsbssk").keyup(function(event){
			if(event.keyCode ==13){ //回车键弹起事件
				window.location.href=contextpath+'personalhomepage/tohomepageonelist.action?bookname='+$("#wdsbssk").val();
			  }
		});
	  
            $("#gengduo").hide();   

            $("#jiazaigengduo").click(function () {
            	$("#jiazaigengduo").hide();
                $("#gengduo").show();
            });
        });
  function abc() {
  var itemMax=4;
  var n=1;
  for(var i=n;i<=itemMax;i++)document.getElementById('tabone'+i).style.display='none';
  function taba(){
      var n=1;
      var original=new Array;
      for (var i=0;i<itemMax;i++){
      original=i+1;
      }
      for(var i=1;i<=itemMax;i++){document.getElementById('tabone'+i).style.display='none';}
      for (i=0;i<n;i++){
      var index=Math.floor(Math.random() * original.length);
      document.getElementById('tabone'+original).style.display='block';
      original.splice(index,1);
      }
  }
  taba();
  }

function listoction(){
	window.location.href=contextpath+'personalhomepage/tohomepageone.action?bookname='+$("#wdsbssk").val();
	
}
function listoction1(){
	window.location.href=contextpath+'personalhomepage/tohomepageonelist.action?bookname='+$("#wdsbssk").val()+"&&dateinfo=1";
	
}
function listoction2(){
	window.location.href=contextpath+'personalhomepage/tohomepageonelist.action?bookname='+$("#wdsbssk").val()+"&&dateinfo=2";
	
}
function listoction3(){
	window.location.href=contextpath+'personalhomepage/tohomepageonelist.action?bookname='+$("#wdsbssk").val()+"&&is_staging=1&&online_progress=1";
	
}
function listoction4(){
	window.location.href=contextpath+'personalhomepage/tohomepageonelist.action?bookname='+$("#wdsbssk").val()+"&&online_progress=1";
	
}
