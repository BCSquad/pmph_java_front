  $(document).ready(function () {
            document.getElementById("dt").className = "xz";
            $("#suibiwenzhang").hide();
            $("#zuixinshuping").hide();
            $("#jiaocaishenbao").hide();
            $("#gengduo").hide();
            $("#jcsb").click(function () {
                $("#dongtai").hide();
                $("#suibiwenzhang").hide();
                $("#zuixinshuping").hide();
                document.getElementById("dt").className = "dtl";
                document.getElementById("sbwz").className = "dtl";
                document.getElementById("zxsp").className = "dtl";
                $("#jiaocaishenbao").show();
                document.getElementById("jcsb").className = "xz";
            });

            $("#dt").click(function () {
                $("#dongtai").show();
                document.getElementById("dt").className = "xz";
                $("#jiaocaishenbao").hide();
                $("#suibiwenzhang").hide();
                $("#zuixinshuping").hide();
                document.getElementById("jcsb").className = "dtl";
                document.getElementById("sbwz").className = "dtl";
                document.getElementById("zxsp").className = "dtl";
            });

            $("#sbwz").click(function () {
                $("#suibiwenzhang").show();
                document.getElementById("sbwz").className = "xz";
                $("#jiaocaishenbao").hide();
                $("#dongtai").hide();
                $("#zuixinshuping").hide();
                document.getElementById("jcsb").className = "dtl";
                document.getElementById("dt").className = "dtl";
                document.getElementById("zxsp").className = "dtl";
            });

            $("#zxsp").click(function () {
                $("#zuixinshuping").show();
                document.getElementById("zxsp").className = "xz";
                $("#jiaocaishenbao").hide();
                $("#suibiwenzhang").hide();
                $("#dongtai").hide();
                document.getElementById("jcsb").className = "dtl";
                document.getElementById("sbwz").className = "dtl";
                document.getElementById("dt").className = "dtl";
            });

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

