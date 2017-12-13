//初始化加载
$(function () {
    //重点推荐
    searchBook($(".tab.recommend.active").attr("typeid"));
    //新书推荐
    searchXstjBook($(".tab.type.active").attr("typeid"));
    //畅销
    searchTscxBook($(".ts_type.ts_type1").attr("typeid"));
});

//重点推荐表格切换
function ChangeDiv(typeid) {

    $(".tab.recommend").removeClass("active");

    $("#ZKDiv_" + typeid).addClass("active");
    searchBook(typeid);

//显示当前层
}

//新书推荐
function ChangeFYDiv(typeid) {
    $(".tab.type").removeClass("active");

    $("#JKFYDiv_" + typeid).addClass("active");

    searchXstjBook(typeid);
//显示当前层
}

//畅销榜表格切换
function ChangeCXDiv(typeid) {

    $(".ts_type").removeClass("ts_type1");

    $("#CXDiv_" + typeid).addClass("ts_type1");
    searchTscxBook(typeid);
    //显示当前层
}

//图书分类
function ChangeFLDiv(divId, divName) {
    $(".ChangeFLDiv").css("display", 'none');
    $(".ts_type").removeClass("ts_type1");

    $("#FLDiv_" + divId).addClass("ts_type1");
    $("#ChangeFLDiv_" + divId).css("display", 'block');

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
function searchBook(typeid) {
    $.ajax({
        type: 'post',
        url: contextpath + 'readpage/searchZdtjBook.action?type=' + typeid,
        async: false,
        dataType: 'json',
        success: function (json) {
            //alert(json.pagebook);
            $("#JKDiv_0").html(json.pagebook);
        }
    });
}

//新书推荐
function searchXstjBook(typeid) {
    $.ajax({
        type: 'post',
        url: contextpath + 'readpage/searchXstjBook.action?type=' + typeid,
        async: false,
        dataType: 'json',
        success: function (json) {
            //alert(json.pagebook);
            $("#JKFYDiv_0").html(json.pagebook);
        }
    });
}

//图书畅销
function searchTscxBook(typeid) {
    $.ajax({
        type: 'post',
        url: contextpath + 'readpage/searchTscxBook.action?type=' + typeid,
        async: false,
        dataType: 'json',
        success: function (json) {
            //alert(json.pagebook);
            $("#JKCXDiv_0").html(json.pagebook);
        }
    });
}

//路径跳转
function openUrl(id) {
    window.open(contextpath + 'readdetail/todetail.action?id=' + id);
}
