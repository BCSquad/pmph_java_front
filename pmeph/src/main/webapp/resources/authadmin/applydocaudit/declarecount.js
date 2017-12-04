
$(function(){
	totalcount();
});

//加载更多
function loadMore(){
	
		var para = $("#startPara").val();
		var startPara;
		
		if(""==para){
			startPara=5;
		}else{
			startPara = parseInt(para)+5;
		}
		
		 $.ajax({
			type:'post',
			url:"loadMore.action?startPara="+startPara,
			async:false,
			dataType:'json',
			success:function(json){
				var list = json;
				$("#startPara").val(startPara);
				
				var str='';
				$.each(list,function(i,n){
					
					str+= "<tr><td   >"+(i+Number(startPara)+1+10)+"</td><td   >"+n.textbook_name+"</td><td   >"+(n.zb?n.zb:'') +"</td><td   >"+(n.fb?n.fb:'')+"</td><td   >"+(n.bw?n.bw:'')+"</td></tr>";
				});
				$("#messageTable").append(str);
			}
		}); 
	}


//查看全部
function selectAll(){
	
	$.ajax({
		type:'post',
		url:contextpath+'/declareCount/selectAll.action',
		async:false,
		dataType:'json',
		success:function(json){
			var list = json;
			var str='';
			$.each(list,function(i,n){
				
				str+= "<tr><td   >"+(i+1)+"</td><td   >"+n.textbook_name+"</td><td   >"+n.decid1+"</td><td   >"+n.decid2+"</td><td   >"+n.decid3+"</td><td   >"+n.dp1+"</td><td   >"+n.dp2+"</td><td   >"+n.dp3+"</td></tr>";
			});
			$("#queryTable").html("");
			$("#queryTable").append(str);
			totalcount();
		}
	}); 
}

//合计
function totalcount(){
	$("#queryTable").append ("<tr></tr>");
     var len = $ ('#tableCount tr:eq(0) td').length;
     var colnum = $ ('#queryTable tr').length;
     var sum = 0;
     for ( var i = 0; i < len; i++)
     {	
    	 sum = 0;
    	 if (i==0){
    		 $ ('#queryTable tr:last').append ("<td>" + colnum + "</td>");
		}else if(i==1){
			$ ('#queryTable tr:last').append ("<td>合计</td>");
		}else{
			
	         $ ('#queryTable tr:gt(0) td:nth-child(' + (i + 1) + ')').each (function (j, dom)
	         {
	             sum += parseFloat ($ (this).text ());
	         });
	         $ ('#queryTable tr:last').append ("<td>" + sum + "</td>");
		}

     }
	
}
