
$(function () {
    //全选主动初事件始化 当全选复选框被主动点击（而非被动改变）时 全选或取消图书复选框
    //并触发改变事件 更新选中集合
    $("#check-all").click(function () {
        $(".book-check").prop("checked",$("#check-all").prop("checked"));
        $(".book-check").trigger("change");
    })

})

var courseAllBookId = [];
var addBookId = [];
var addBook = new Map();
var allChecked =false;

//全选的被动初始化
//在列表刷新后和图书复选框被点击时 影响全选复选框的选中状态 使之状态保持同步
function initAllCheck(){
    allChecked = $(".book-check").length>0;
    $(".book-check").each(function(){
        allChecked = allChecked && $(this).prop("checked");
    })
    $("#check-all").prop("checked",allChecked);
}

//为数组对象增加删除某元素的方法
Array.prototype.removeByValue = function(val) {
    for(var i=0; i<this.length; i++) {
        if(this[i] == val) {
            this.splice(i, 1);
            break;
        }
    }
};

//查询所有图书以供选择
function queryBook(){
    data = {
        pageNum:$("#course-dialog .page-num-temp").val(),
        pageSize:$("#course-dialog .page-size-select").find("input[name='page-size-select']").val(),
        bookname:$("#search-bookname").val(),
        isbn:$("#search-ISBN").val(),
        courseId:$("#courseId").val(),
        contextpath:contextpath
    };

    $.ajax({
        type:'post',
        url:contextpath+'course/querybooklist.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:data,
        success:function(json){
            $("#course-dialog .dialog-table").html(json.html);

            if (json.html.trim() == "") {
                $("#course-dialog .pagination-wrapper").hide();
                $("#course-dialog .no-more").show();
            }else{
                $("#course-dialog .no-more").hide();
                $("#course-dialog .pagination-wrapper").show();
                $("#course-dialog .pagination").css("display","inline-block");
                $("#course-dialog .pageJump").css("display","inline-block");
                $("#course-dialog .pagination").next("div").css("display","inline-block");
            }
            $('#course-dialog .pagination').html("");
            $("#course-dialog .totoal_count").html(json.totoal_count);
            //刷新分页栏
            Page({
                num: json.totoal_count,					//页码数
                startnum: $("#course-dialog .page-num-temp").val(),				//指定页码
                elem: $('#course-dialog .pagination'),
                callback: function (n){     //点击页码后触发的回调函数
                    $("#course-dialog .page-num-temp").val(n);
                    queryDialog();
                }
            });

            //图书复选框初始化
            initBookCheck();
            //全选的被动初始化
            initAllCheck();
        }
    });
}

/**
 * 获取课程已选图书 bookId
 * 暂启用 不需查询
 */
function getCourseAllBookId() {
    $.ajax({
        type:'post',
        url:contextpath+'course/getCourseAllBookId.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:{id:$("#courseId").val()},
        success:function(json){
            courseAllBookId = json;
        }
    })
}

//图书复选框初始化
function initBookCheck(){
    $(".book-check").each(function () {
        var $t = $(this);
        $t.unbind();
        //刷新 搜索 翻页等后 根据addBookId回填复选框
        if ($.inArray($t.attr("bookid"),addBookId)>-1) {
            $t.prop("checked",true);
        }else{
            $t.prop("checked",false);
        }
        //影响选中集合的事件初始化
        $t.unbind().bind("change",function (e) {
            if($t.prop("checked")){
                if($.inArray($t.attr("bookid"),addBookId)<0){
                    addBookId.push($t.attr("bookid"));
                    addBook.set($t.attr("bookid"),
                                    {bookId:$t.attr("bookid"),
                                    bookname:$t.attr("bookname"),
                                    isbn:$t.attr("isbn")}
                                );
                }

            }else{
                addBookId.removeByValue($t.attr("bookid"));
                addBook.delete($t.attr("bookid"));
            }
            console.log(addBook);
        }).bind("click",function (e) {
            //全选的被动初始化
            initAllCheck();
        })

    })
}

/**
 * 新增教师选择图书
 */
function teacherAddBook() {

    var $add = "";

    var count = $("#zebra-table").find("tr").length;

    for(var book of addBook){
        count++;
        $add =
            "<tr>\n" +
            "<input type=\"hidden\" class=\"data\" value=\""+book[1].bookId+"\" deleted=\"0\">\n"+
            "                                <td class=\"row_count\">"+count+"</td>\n" +
            "                                <td>"+book[1].bookname+"</td>\n" +
            "                                <td>"+book[1].isbn+"</td>\n" +
            "                                <td class=\"courseBookNote\">\n" +
            "                                    <textarea class=\"courseBookNote\" bookId=\""+book[1].bookId+"\" courseBookId=\"\"></textarea>\n" +
            "                                    <span class=\"courseBookNote\" courseBookId=\"\"></span>\n" +
            "                                </td>\n" +
            "                                \n" +
            "                                <td>\n" +
            "                                    \n" +
            "                                        \n" +
            "                                        \n" +
            "                                            <a class=\"not-like-an-a course-handler\" onclick=\"javascript:course_book_delete('','"+book[1].bookname+"',this)\">\n" +
            "                                                删除</a>\n" +
            "                                        \n" +
            "                                    \n" +
            "                                </td>\n" +
            "                            </tr>";
        $("#zebra-table").append($add);
    }

    courseBookNoteUpdate();

    $('#course-dialog').fadeOut(0);
    /*
    $.ajax({
        type:'post',
        url:contextpath+'course/teacher/addBook.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:{addBookIdStr:addBookId.toString(),courseId:$("#courseId").val()},
        success:function(json){
            $('#course-dialog').fadeOut(500);
            queryCourseBook();
        }
    })*/
}

