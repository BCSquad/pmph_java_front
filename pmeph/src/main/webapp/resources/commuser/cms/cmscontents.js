$(function(){

	$('#page-size-select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
        
    });
	//切换分页每页数据数量时 刷新
	$("#page-size-select").find("li").bind("click",function(){
		
		$("#page-num-temp").val(1);
		queryMain();
	});
	queryMain();
});


//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find($("input[name='page-size-select']")).val()
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'/cms/toPage.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			addColum(json.page);
			$("#total").val(json.total);
			
			//刷新分页栏
			 Page({
                num: json.total,					//页码数
                startnum: $("#page-num-temp").val(),//指定页码
                elem: $('#page1'),
                callback: function (n){     //点击页码后触发的回调函数
                	$("#page-num-temp").val(n);
                	queryMain();
                }
                });
		}
	});
}


function addColum(list){
	//清空
	$("#tbody1").html("");
	var str='';
	$.each(list,function(i,n){
		str+= '<div class="item behind" onclick="window.location.href=\''+contextpath+'articledetail/toPage.action?wid='+n.id+'\'">';
		
		str +='<div class="command">';
			str +='<span style="margin-left: 5px">推荐</span>';
				str +='</div>';
					str +='<div  class="content" >';
						str +='<div class="content-image">';
							str +='<img src="'+contextpath+'statics/testfile/p2.png" />';
							str +='</div>';
								str +='<p  class="content-title"  >'+n.title+'</p>';
								str +='<p  class="content-text">'+(n.summary?n.summary:'')+'';
								str +='<div  class="foot">';
									str +=' <div  class="msg">';
										str +='<span id="span_1"></span><span id="span_4">'+n.clicks+'</span>';
										str +='<span id="span_2"></span><span id="span_4">'+n.likes+'</span>';
										str +='<span id="span_3"></span><span id="span_4">'+n.comments+'</span>';
										str +='</div>';
											str +='</div>';
												str +='<div class="ryxx">';
													str +='<div class="ryxx_foot"><img src="';
            if (n.avatar == '' || n.avatar == 'DEFAULT' || n.avatar == null) {
                str += contextpath + 'statics/image/rwtx.png';
            } else {
            	str+=contextpath+'image/'+n.avatar+'.action';
            }
            str += '" class="ryxx_tx"/>';
		
            	str +='<span class="ryxx_xm" style="cursor:pointer;" onclick="window.location.href='+contextpath+'personalhomepage/tohomepage.action?userId='+n.userId+'">'+n.realname+'</span>';
            	str +='<span class="ryxx_sj">'+n.auth_date+'</span>';
            	str +='</div>';
            		str +='</div>';
            			str +='</div>';
            				str +='</div>';
		
	});
	$("#tbody1").append(str);
}



//选择每页数据数量
function selectPageSize(){
	queryMain();
}