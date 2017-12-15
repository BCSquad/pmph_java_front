  $(document).ready(function () {

	    

          document.getElementById("dt").className = "xz";
          $("#suibiwenzhang").hide();
          $("#zuixinshuping").hide();

          $("#dt").click(function () {
              $("#dongtai").show();
              document.getElementById("dt").className = "xz";
              $("#suibiwenzhang").hide();
              $("#zuixinshuping").hide();
              document.getElementById("sbwz").className = "dtl";
              document.getElementById("zxsp").className = "dtl";
          });

          $("#sbwz").click(function () {
              $("#suibiwenzhang").show();
              document.getElementById("sbwz").className = "xz";
              $("#dongtai").hide();
              $("#zuixinshuping").hide();
              document.getElementById("dt").className = "dtl";
              document.getElementById("zxsp").className = "dtl";
          });

          $("#zxsp").click(function () {
              $("#zuixinshuping").show();
              document.getElementById("zxsp").className = "xz";
              $("#suibiwenzhang").hide();
              $("#dongtai").hide();
              document.getElementById("sbwz").className = "dtl";
              document.getElementById("dt").className = "dtl";
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

//删除随笔文章
  function DelMyWriter(id){
	  
	  var msg = "您真的确定要删除这条随笔文章吗？\n\n请确认！";
	  if (confirm(msg)==true){
			var json={
					 id : id,
			  	};
			  	 $.ajax({
			  			type:'post',
			  			url:contextpath+'writerArticle/updateDelWriter.action',
			  			async:false,
			  			dataType:'json',
			  			data:json,
			  			success:function(json){
			  				if(json.flag=="0"){
			  					  $("#content").val(null);
			  					  window.message.success("删除成功!");
			  				}else{
			  					window.message.error("删除失败!");
			  				}
			  			}
			  		});
		  return location.reload;
	  
	  }else{
	  return false;
	  }
  }
  
//删除书评内容
  function DelMyBookWriter(id){
	  
	  var msg = "您真的确定要删除这条书评吗？\n\n请确认！";
	  if (confirm(msg)==true){
			var json={
			  		 id : id, 
			  	};
			  	 $.ajax({
			  			type:'post',
			  			url:contextpath+'readdetail/updateDelBookWriter.action',
			  			async:false,
			  			dataType:'json',
			  			data:json,
			  			success:function(json){
			  				if(json.flag=="0"){
			  					  $("#content").val(null);
			  					  window.message.success("删除成功!");
			  				}else{
			  					window.message.error("删除失败!");
			  				}
			  			}
			  		});
		  return location.reload;
	  
	  }else{
	  return false;
	  }
  }