
$(function(){
	totalcount();
	
});

//加载更多  作废
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


//查看全部    我校申报情况统计
function selectAll(){
	
	$.ajax({
		type:'post',
		url:contextpath+'declareCount/selectAll.action',
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


//查看全部    最终结果名单
function selectResults(){
	$.ajax({
		type:'post',
		url:contextpath+'declareCount/selectResults.action',
		async:false,
		dataType:'json',
		success:function(json){
//			var list = json;
//			var str='';
//			$.each(list,function(i,n){
//				
//				str+= "<tr><td   >"+(i+1)+"</td><td   >"+n.textbook_name+"</td><td   >"+(n.zb?n.zb:'') +"</td><td   >"+(n.fb?n.fb:'')+"</td><td   >"+(n.bw?n.bw:'')+"</td></tr>";
//			});
//			$("#messageTable").html("");
//			$("#messageTable").append(str);
			$("#messageTable").html("");
			getData(0,15);
		}
	}); 
}

//合计
function totalcount(){
	$("#queryTable").append ("<tr></tr>");
     var len = $ ('#tableCount tr:eq(0) td').length;
     var colnum = $ ('#queryTable tr').length;
     for ( var i = 0; i < len; i++)
     {	
    	 var sum=0;
    	 if (i==0){
    		 $ ('#queryTable tr:last').append ("<td>" + colnum + "</td>");
		}else if(i==1){
			$ ('#queryTable tr:last').append ("<td>合计</td>");
		}else{
	         $ ('#tableCount tr:gt(0) td:nth-child(' +(i+1)+ ')').each (function (j, dom)
	         {
	        	  sum += parseFloat ($ (this).text ());
	         });
	         $ ('#tableCount tr:last').append ("<td>" + sum + "</td>");
		}

     }
	
}

//加载更多  
$(function(){
	 
	  /*初始化*/
	  var counter = 0; /*计数器*/
	  var pageStart = 0; /*offset*/
	  var pageSize = 15; /*size*/
	   
	  /*首次加载*/
//	  getData(pageStart, pageSize);
	   
	  /*监听加载更多*/
	  $(document).on('click', '.js-load-more', function(){
	    counter ++;
	    pageStart = counter * pageSize;
	     
	    getData(pageStart, pageSize);
	  });
	});

function getData(offset,size){
	  $.ajax({
		  type:'post',
			url:"loadMore.action",
	    dataType: 'json',
	    success: function(json){
	      var data = json;
	      var sum = json.length;
	   
	      var result = '';
	       
	      /****业务逻辑块：实现拼接html内容并append到页面*********/
	       
	      //console.log(offset , size, sum);
	       
	      /*如果剩下的记录数不够分页，就让分页数取剩下的记录数
	      * 例如分页数是5，只剩2条，则只取2条
	      *
	      * 实际MySQL查询时不写这个不会有问题
	      */
	      if(sum - offset < size ){
	        size = sum - offset;
	      }
	       
	      /*使用for循环模拟SQL里的limit(offset,size)*/
	      for(var i=offset; i< (offset+size); i++){
	        result +="<tr><td   >"+(i+1)+"</td><td   >"+data[i].textbook_name+"</td><td   >"+(data[i].zb?data[i].zb:'') +"</td><td   >"+(data[i].fb?data[i].fb:'')+"</td><td   >"+(data[i].bw?data[i].bw:'')+"</td></tr>";
	      }
	   
	      $("#messageTable").append(result);
	      
	      /*******************************************/
	   
	      /*隐藏more按钮*/
	      if ( (offset + size) >= sum){
	        $(".js-load-more").hide();
	      }else{
	        $(".js-load-more").show();
	      }
	    },
	    error: function(xhr, type){
	     
	    }
	  });
	}

