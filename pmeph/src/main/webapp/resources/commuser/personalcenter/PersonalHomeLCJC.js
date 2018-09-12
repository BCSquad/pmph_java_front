//   $(document).ready(function () {
//
//       $("#myall").val(null);
//       $("#search").val(null);
//
// //进行选中
// 	  var page=$("#pageinfo").val();
// 	  if(page!=null){
//           if(page==1){
//               document.getElementById("sbzzjx").className = "jcsbsbztxz";
//           }
//           if(page==2){
//               document.getElementById("sbyjs").className = "jcsbsbztxz";
//           }
//           if(page==3){
//               document.getElementById("sbzc").className = "jcsbsbztxz";
//           }
//           if(page==4){
//               document.getElementById("sbytj").className = "jcsbsbztxz";
//           }
//           if(page==5){
//               document.getElementById("qktj").className = "jcsbsbztxz";
//           }
// 	  }
//
// 	  if ($("#pageinfo1").val()!=null && $("#pageinfo1").val()!='') {
// 		$("#lcjcwd").removeClass("active");
// 		$("#lcjcqb").removeClass("active");
// 		$("#"+$("#pageinfo1").val()).addClass("active");
// 	}
//
//
//
// 	  $("#wdsbssk").keyup(function(event){
// 			if(event.keyCode ==13){ //回车键弹起事件
// 				//window.location.href=contextpath+'personalhomepage/tohomepage.action?pagetag=jcsb&bookname='+$("#wdsbssk").val();
// 				$("#bookname").val($("#wdsbssk").val());
// 				$("#pageNum").val(1);
// 				queryMain();
// 			  }
// 		});
// 	 /* $("#btn_wdsbssk").click(function(event){
// 		  $("#bookname").val($("#wdsbssk").val());
// 			$("#pageNum").val(1);
// 			queryMain();
// 	  });*/
//
// /*	  if($(".select_textbook").length>0){
// 		  $(".select_textbook").val("-1");
// 			$(".select_textbook").change(function(){
// 				window.open(contextpath+"chooseEditor/toPage.action?textBookId="+$(this).val());
// 				$(".select_textbook").val("-1");
// 			});
// 	  }
// 	  	*/
//
// });
//
//
//   function toproductdetail(product_id) {
//       location.href = contextpath + 'expertation/lookforward.action?product_id='+product_id;
//   }
//   function tojoin(product_id) {
//       location.href = contextpath + 'homepage/toproductdetail.action?state='+product_id;
//   }
//
//
//   function toPersondetail(product_id){
//       $.ajax({
//           type: "POST",
//           url:contextpath+'expertation/toPersondetail.action?product_id='+product_id,
//           dataType:"json",
//           success: function(json) {
//               if(json=="yes"){
//                   toproductdetail(product_id);
//               }else{
//                   window.message.info("您没有申报过当前公告")
//               }
//           }
//       });
//   }
//
// function listoction(){
//     delCondition();
// 	$("#myall").val("my");
// 	$("#pageNum").val(1);
// 	queryMain();
// }
//
// function listoction0(){
//     delCondition();
//   $("#myall").val("all");
//   $("#pageNum").val(1);
//   queryMain();
// }
//
// //当选择全部或者个人的时候，将隐藏域的值置空
// function delCondition(){
//   $(".queryCondition").each(function(){
//       var $t=$(this);
//       $t.val('');
//   });
// }
//
// function querySearch(){
//   var str=$("#wdsbssk").val();
//   $("#search").val(str);
//   $("#pageNum").val(1);
//   queryMain();
// }
//
// function listoction1(str){
// 	$("#online_progress").val(str);
// 	$("#pageNum").val(1);
// 	queryMain();
// }
//
// /*
// function listoction3(){
// 	$("#s").val("");
// 	$("#dateinfo").val("");
// 	$("#is_staging").val(1);
// 	$("#online_progress").val("(0)");
// 	$("#pageinfo1").val("jcsbwdsb");
// 	$("#pageinfo").val(3);
// 	$("#pageNum").val(1);
// 	queryMain();
// }
// function listoction4(){
// 	$("#s").val("");
// 	$("#dateinfo").val("");
// 	$("#is_staging").val("");
// 	$("#online_progress").val("(1,2,3)");
// 	$("#pageinfo1").val("jcsbwdsb");
// 	$("#pageinfo").val(4);
// 	$("#pageNum").val(1);
// 	queryMain();
//
// }
// function listoction5(){
// 	$("#is_staging").val("");
// 	$("#online_progress").val("");
// 	$("#dateinfo").val("");
// 	$("#pageinfo").val(5);
// 	$("#pageNum").val(1);
// 	queryMain();
// }
// */
