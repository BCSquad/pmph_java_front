

$(function(){
//	$(':input').labelauty();
	$('#page-size-select').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });
	var selId=$("#selected").val();
	var mid=$("input[name='radio"+selId+"']:checked ").val();
	getSort('',mid,selId,'sort');
	
	//切换分页每页数据数量时 刷新
	$("#page-size-select").find("li").bind("click",function(){
		$("#page-num-temp").val(1);
		var selId=$("#selected").val();
    	var mid=$("input[name='radio"+selId+"']:checked ").val();
    	getSort('',mid,selId,'page');
	});
	
	$("#search-name").keyup(function(event){
		if(event.keyCode ==13){ //回车键弹起事件
			queryBtnClick();
		  }
	});
	
	var typeFromRedirectFullPath = $("#typeFromRedirectFullPath").val();
	var selectedTypeChain = typeFromRedirectFullPath.split("-");
	for ( var t in selectedTypeChain) {
		console.log(selectedTypeChain[t]+" "+$("input.labelauty[type=radio][value="+selectedTypeChain[t]+"]").val());
		
		$("input.labelauty[type=radio][value="+selectedTypeChain[t]+"]").trigger("click");
	}

});

function getSort(order,id,pid,tag){
	if(tag=="sort"){
		$("#page-num-temp").val(1);
	}
	
	$("#search-name-temp").val($("#search-name").val());
	toTopAfterQuery();
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find("input[name='page-size-select']").val(),
			queryName:$("#search-name-temp").val(),
			contextpath:contextpath,
			id:id,
			order:order
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'booksearch/querybooklist.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
//			if(id !=null && !(id=="")&& json.count>0){
				var str= '<div id="sort'+json.parent.order+'" style="background-color: #ffffff;float:left;padding-bottom: 10px;width:1200px">'+
				'<div style="float:left;min-width:100px;height:56px;line-height: 56px;text-align: center;margin-left:10px">'+
				json.parent.type_name+
				':</div>'+
				' <div style="float:left;max-width:1090px">'+
				'<ul class="dowebok" >';
				$.each(json.child,function(i,n){
					str +='<li style="float:left;margin-left: 10px;margin-top: 10px" ><input type="radio" name="radio'+n.parent_id+'" value="'+n.id+'" onclick="getSort(\''+json.parent.order+'\',\''+n.id+'\',\''+n.parent_id+'\',\'sort\')" data-labelauty="'+n.type_name+'"></li>'
				});
				str +='</ul></div></div>';
				if(tag=='sort'){
					    $("#sort"+order).nextAll().remove();
						if(json.child.length>0){
						    $("#mysort").append(str);
							$("input[name=radio"+json.child[0].parent_id+"]").labelauty();
						}
				}else if(tag=="page"){
					   
				}else if(tag=="search"){
					if(json.child.length>0){
					 $("#mysort").html(str);
					 $("input[name=radio"+json.child[0].parent_id+"]").labelauty();
					}else{
						 $("#mysort").html('');
					 }
				}
//			}else{
//				$("#sort"+order).nextAll().remove();
//			}
			$("#book-list-table").html(json.html);
			$("#selected").val(pid);
			if (json.html.trim() == "") {
				$(".pageDiv").hide();
				$(".no-more").show();
			}else{
				$(".no-more").hide();
				$(".pageDiv").show();
				$(".pagination").css("display","inline-block");
				$(".pageJump").css("display","inline-block");
				$(".pagination").next("div").css("display","inline-block");
			}
			$('#page1').html("");	
			$("#totoal_count").html(json.totoal_count);
			redQuery();
			//刷新分页栏
			 Page({
                num: json.totoal_count,					//页码数
                startnum: $("#page-num-temp").val(),				//指定页码
                elem: $('#page1'),
                callback: function (n){     //点击页码后触发的回调函数
                	$("#page-num-temp").val(n);
                	var selId=$("#selected").val();
                	var mid=$("input[name='radio"+selId+"']:checked ").val();
                	getSort('',mid,selId,'page');
                }
                });
			 
		}
	});
}

//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
	toTopAfterQuery();
	data = {
			pageNum:$("#page-num-temp").val(),
			pageSize:$("#page-size-select").find("input[name='page-size-select']").val(),
			queryName:$("#search-name-temp").val(),
			contextpath:contextpath
			};
	
	$.ajax({
		type:'post',
		url:contextpath+'booksearch/querybooklist.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			
			$("#book-list-table").html(json.html);
			
			if ($.trim(json.html) == "") {
				$(".pageDiv").hide();
				$(".no-more").show();
			}else{
				$(".no-more").hide();
				$(".pageDiv").show();
				$(".pagination").css("display","inline-block");
				$(".pageJump").css("display","inline-block");
				$(".pagination").next("div").css("display","inline-block");
			}
			$('#page1').html("");	
			$("#totoal_count").html(json.totoal_count);
			redQuery();
			//刷新分页栏
			 Page({
                num: json.totoal_count,					//页码数
                startnum: $("#page-num-temp").val(),				//指定页码
                elem: $('#page1'),
                callback: function (n){     //点击页码后触发的回调函数
                	$("#page-num-temp").val(n);
                	queryMain();
                }
                });
			 
		}
	});
}



//查询按钮点击事件触发 
function queryBtnClick(){
	$("#page-num-temp").val(1);
	$("#search-name-temp").val($("#search-name").val());
	var selId=$("#selected").val();
	var mid=$("input[name='radio"+selId+"']:checked ").val();
	getSort('','',selId,'search');
}

//选择每页数据数量
function selectPageSize(){
	var selId=$("#selected").val();
	var mid=$("input[name='radio"+selId+"']:checked ").val();
	getSort('',mid,selId,'');
}

//点赞按钮 点击 触发 点赞和取消赞
function likeSwitch(bookId,icon){
	var $icon = $(icon);
	var data={
			bookId:bookId
	};
	$.ajax({
		type:'post',
		url:contextpath+'booksearch/likeSwitch.action?t='+new Date().getTime(),
		async:false,
		dataType:'json',
		data:data,
		success:function(json){
			if (json != null && json.switchResult != null) {
				if (json.switchResult == "active") {
					$icon.addClass("active");
					$icon.attr("title","取消赞");
				}else if(json.switchResult == "non-active"){
					$icon.removeClass("active");
					$icon.attr("title","点赞");
				}
				$icon.prev("div").html(json.like_count);
			}
		}
	});
}

//搜索条件在相应部位红色
function redQuery(){
	var qsArray = $("#search-name-temp").val().trim().split(" ");
	for ( var i in qsArray) {
		var re=new RegExp(qsArray[i],"ig");
		var $tag= $(".book-name-span");
		$tag.each(function(){
			var html = $(this).html();
			var fit_html = '<font style="color:red;">'+qsArray[i]+'</font>';
			$(this).html(html.replace(re,fit_html));
		});
	}
	//var qs=$("#search-name-temp").val();
}

//搜索之后跳转到页面顶部
function toTopAfterQuery(){
	//scroll(0,0);
	pageScroll();
}

//
function pageScroll(){
    //获取scrollTop值，声明了DTD的标准网页取document.documentElement.scrollTop，否则取document.body.scrollTop；因为二者只有一个会生效，另一个就恒为0，所以取和值可以得到网页的真正的scrollTop值
    var sTop=document.documentElement.scrollTop+document.body.scrollTop;
    //把内容滚动指定的像素数（第一个参数是向右滚动的像素数，第二个参数是向下滚动的像素数）
    window.scrollBy(0,-sTop);
    //延时递归调用，模拟滚动向上效果
    //scrolldelay = setTimeout('pageScroll()',1);
    //判断当页面到达顶部，取消延时代码（否则页面滚动到顶部会无法再向下正常浏览页面）
    //if(sTop==0) clearTimeout(scrolldelay);
}


function switchBetweenBookAndArticle(n){
	if (n=="article") {
		window.location=contextpath+'articlesearch/queryall.action?title='+encodeURI(encodeURI($("#search-name").val()))+'&&real_title='+encodeURI(encodeURI($("#search-name-temp").val()));
	}else{
		/*window.location=contextpath+'booksearch/toPage.action?search='+$("#search-name").val()+'&&real_search='+$("#search-name-temp").val();*/
	}
}
