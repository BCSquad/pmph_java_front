
$(function(){
    $('#page-size-select').selectlist({
        zIndex: 100,
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#select-search-status').selectlist({
        zIndex: 100,
        width: 208,
        height: 42,
        optionHeight: 30
    });
    $("#page-size-select").find("li").bind("click",function(){
        $("#page-num-temp").val(1);
        queryMain();
    });
    //切换状态时查询 刷新
    $("#select-search-status").find("li").bind("click",function(){
        $("#page-num-temp").val(1);
        queryMain();
    });
    $("#search-name").bind('keydown', function (event) {
        if (event.keyCode == "13") {
            queryBtnClick();
        }
    });

    queryMain();
})


//条件设定完成后查询的实现  点击查询 翻页 更换查询条件等都要先设定条件 不能直接调用此实现
function queryMain(){
    data = {
        pageNum:$("#page-num-temp").val(),
        pageSize:$("#page-size-select").find("input[name='page-size-select']").val(),
        name:$("#search-name-temp").val(),
        status:$("#select-search-status").find("input.select-button").val()
    };

    $.ajax({
        type:'post',
        url:contextpath+'course/teacher/getTeacherCourseList.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:data,
        success:function(json){
            $("#zebra-table").html(json.html);

            if (json.html.trim() == "") {
                $(".pagination-wrapper").hide();
                $(".no-more").show();
            }else{
                $(".no-more").hide();
                $(".pagination-wrapper").show();
                $(".pagination").css("display","inline-block");
                $(".pageJump").css("display","inline-block");
                $(".pagination").next("div").css("display","inline-block");
            }
            $('#page1').html("");
            $("#totoal_count").html(json.totoal_count);
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
    //$("#select-search-status").find("input[name='select-search-status']").val($("#select-search-status").find("li[class='selected']").attr("data-value"));
    queryMain();
}

/**
 * 跳转到详情页 （修改/查看）
 */
function course_detail(id) {
    window.location.href = contextpath+'course/teacher/toCourseDetail.action?id='+id+'&t='+new Date().getTime();
}

/**
 * 复制新增
 * @param id
 */
function course_copy(id) {
    window.location.href = contextpath+'course/teacher/toCourseDetail.action?id='+id+'&copyNew=1&t='+new Date().getTime();
}

/**
 * 删除
 * @param id
 */
function course_delete(id,name) {
    window.message.confirm("确定删除“"+name+"”吗？",{title:'课程删除',btn:["确定","取消"]},function(index){

    });
}

/**
 * 撤回
 * @param id
 */
function course_pull_back(id,name) {
    window.message.confirm("确定撤回“"+name+"”吗？",{title:'提示',btn:["确定","取消"]},function(index){

    });
}