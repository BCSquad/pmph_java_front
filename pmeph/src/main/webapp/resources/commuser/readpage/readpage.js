//初始化加载
$(function () {
	//重点推荐
	searchBook("JKDiv_0",0);
	//新书推荐
	searchXstjBook("JKFYDiv_0",0);
	//畅销
	searchTscxBook("JKCXDiv_0",0);
});

//重点推荐表格切换
function ChangeDiv(divId,divName,zDivCount)
{
    for(var i=0;i<=zDivCount;i++)
    {
        document.getElementById(divName+i).style.display="none";
        document.getElementById("ZKDiv_"+i).setAttribute("class","tab recommend");
        
//将所有的层都隐藏
    }
    document.getElementById(divName+divId).style.display="block";
    document.getElementById("ZKDiv_"+divId).setAttribute("class","tab recommend active");
    searchBook(divName+divId,divId);
    
//显示当前层
}

//新书推荐
function ChangeFYDiv(divId,divName,zDivCount)
{
    for(var i=0;i<=zDivCount;i++)
    {
        document.getElementById(divName+i).style.display="none";
        document.getElementById("FYDiv_"+i).setAttribute("class","tab type");
//将所有的层都隐藏
    }
    document.getElementById(divName+divId).style.display="block";
    document.getElementById("FYDiv_"+divId).setAttribute("class","tab type active");
    searchXstjBook(divName+divId,divId);
//显示当前层
}

//畅销榜表格切换
function ChangeCXDiv(divId,divName,zDivCount)
{

    for(var i=0;i<=zDivCount;i++)
    {
        document.getElementById(divName+i).style.display="none";
        document.getElementById("CXDiv_"+i).setAttribute("class","ts_type");
//将所有的层都隐藏
    }
    document.getElementById(divName+divId).style.display="block";
    document.getElementById("CXDiv_"+divId).setAttribute("class","ts_type ts_type1");
    searchTscxBook(divName+divId,divId);
    //显示当前层
}

//图书分类
function ChangeFLDiv(divId,divName)
{
    $(".ChangeFLDiv").css("display",'none');
    $(".ts_type").removeClass("ts_type1");

    $("#FLDiv_"+divId).addClass("ts_type1");
    $("#ChangeFLDiv_"+divId).css("display",'block');

  /*  for(var i=0;i<=zDivCount;i++)
    {
        document.getElementById(divName+i).style.display="none";
        document.getElementById("FLDiv_"+i).setAttribute("class","ts_type");
//将所有的层都隐藏
    }
    document.getElementById(divName+divId).style.display="block";
    document.getElementById("FLDiv_"+divId).setAttribute("class","ts_type ts_type1");
//显示当前层*/
}

//重点推荐
function searchBook(divId,type){
	$.ajax({
		type:'post',
		url:contxtpath+'readpage/searchZdtjBook.action?type='+type,
		async:false,
		dataType:'json',
		success:function(json){
			//alert(json.pagebook);
			$("#"+divId).html(json.pagebook);
		}
	});
}

//新书推荐
function searchXstjBook(divId,type){
	$.ajax({
		type:'post',
		url:contxtpath+'readpage/searchXstjBook.action?type='+type,
		async:false,
		dataType:'json',
		success:function(json){
			//alert(json.pagebook);
			$("#"+divId).html(json.pagebook);
		}
	});
}

//图书畅销
function searchTscxBook(divId,type){
	$.ajax({
		type:'post',
		url:contxtpath+'readpage/searchTscxBook.action?type='+type,
		async:false,
		dataType:'json',
		success:function(json){
			//alert(json.pagebook);
			$("#"+divId).html(json.pagebook);
		}
	});
}

//路径跳转
function openUrl(id){
	window.open(contxtpath+'readdetail/todetail.action?id='+id);
}
