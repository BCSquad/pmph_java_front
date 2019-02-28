var courseId ;
var published ;

$(function(){
    courseId = $("#courseId").val();
    published = eval($("#published").val());
    //queryMain();
    $('#course-dialog .page-size-select').selectlist({
        zIndex: 100,
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#student-dialog .page-size-select').selectlist({
        zIndex: 100,
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $(".page-size-select").find("li").bind("click",function(){
        $("#page-num-temp").val(1);
        queryDialog();
    });

    $("#courseForm").find("input[tipso]").each(function () {
        var $t = $(this);
        var validator = $t.attr("validator") ?$t.attr("validator"):"isNonEmpty";
        var message = $t.attr("message")?$t.attr("message"):"不能为空";
        $t.tipso({validator:validator,message:message});
    })

    $.addValidatRule("writerUserNameExist",function (value) {
        var result = true;
        if(value){
            $.ajax({
                type:"get",
                url:contextpath+"course/existWriterUserByUsername.action?username="+value+"&t=" + new Date().getTime(),
                async:false,//关闭异步 这样调用ajax的外层方法才会等待ajax执行完后再继续返回result
                dataType:"text",
                success:function(res) {
                    result = eval(res);
                }
            })
        }
        return result;
    })

    $("#courseForm input[name='stuRepreUsername']").tipso({validator:"writerUserNameExist",message:"该用户名不存在，请核实。"})

    /**
     * 备注的修改初始化
     */
    courseBookNoteUpdate();



    /*$("#beginDate").calendar();
    $("#endDate").calendar();*/

})

window.onload = function(){
    $("input[calendar]").each(function(i,n){
        var $t = $(n);
        $t.trigger("timeChange",new Date($t.val()));
    });
};

function queryCourseBook() {
    $.ajax({
        type:'post',
        url:contextpath+'course/getCourseBookList.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:{id:$("#courseId").val()},
        success:function(json){

            if(json.length<1){
                $(".table-area .no-more").show();
            }else{
                $(".table-area .no-more").hide();
            }
            var html = "";
            for(i=0;i<json.length;i++){
                var courseBook = json[i];
                html += "<tr>\n" +
                    "                                <td>"+(i+1)+"</td>\n" +
                    "                                <td>"+courseBook.bookname+"</td>\n" +
                    "                                <td>"+courseBook.isbn+"</td>\n" +
                    "<td class=\"courseBookNote\">\n" +
                    "                                    <textarea class=\"courseBookNote\" courseBookId=\""+courseBook.id+"\">"+courseBook.note+"</textarea>\n" +
                    "                                    <span class=\"courseBookNote\" courseBookId=\""+courseBook.id+"\">"+courseBook.note+"</span>\n" +
                    "                                </td>"+
                    "                                <td><a class=\"not-like-an-a course-handler\" href=\"javascript:course_book_delete("+courseBook.id+",'"+courseBook.bookname+"')\">删除</a></td>\n" +
                    "                            </tr>";
            }
            $("#zebra-table").html(html);
             // 备注的修改初始化
            courseBookNoteUpdate();
        }
    })
}

//点击显示弹窗
function showup(type,courseBookId,bookname) {
    if((type=='bookAdd' && $("#course-dialog").css("display")=="none")
        ||
        (type=='student' && $("#student-dialog").css("display")=="none")
        ){
        $.ajax({
            type: 'post',
            url: contextpath + 'readdetail/tologin.action',
            async: false,
            dataType: 'json',
            success: function (json) {
                if (json == "OK") {
                    $("#student-dialog .mistitle").html(bookname);
                    $("#"+(type=='bookAdd'?"course-dialog":"student-dialog")).show();
                    $("#page-num-temp").val(1);
                    addBookId = [];
                    queryDialog(courseBookId);
                }
            }
        });
    }

}

/**
 * 弹出窗列表查询
 */
function queryDialog(courseBookId){
    if(published){ //已发布查询学生
        if(courseBookId){
            $("#courseBookId").val(courseBookId);
        }
        queryStudent();
    }else{ //未发布查询图书
        queryBook();
    }
}



//查询按钮点击事件触发
function queryBtnClick(){
    $("#page-num-temp").val(1);
    queryDialog();
}

/**
 * 跳转到详情页 （修改/查看）
 */
function course_detail(id) {
    window.location.href = contextpath+'course/teacher/toCourseDetail.action?id='+id+'&t='+new Date().getTime();
}


/**
 * 删除
 * @param id
 */
function course_book_delete(id,bookname,a_dom) {
    window.message.confirm("确定删除《"+bookname+"》吗？",{title:'提示',btn:["确定","取消"]},function(index){

        $("#zebra-table input.data[courseBookId='']")
        //$(a_dom).parents("tr").find("input.data[deleted=0]").attr("deleted",1);
        $(a_dom).parents("tr").remove();

        $("#zebra-table td.row_count").each(function (i) {
            $(this).html(i+1);
        })


        layer.close(index);
        /*$.ajax({
            type:'post',
            url:contextpath+'course/courseBookDelete.action?t='+new Date().getTime(),
            async:false,
            dataType:'json',
            data:{id:id},
            success:function(json){
                layer.close(index);
                queryCourseBook();
            }
        })*/
    });
}


/**
 * publish 1 为发布 0 为暂存
 */
function save(publish) {



    if($.fireValidator()){
        window.message.confirm("确定"+(publish?"发布":"保存")+"吗？",{title:'提示',btn:["确定","取消"]},function(index){


            var data = {
                id:$("#courseId").val(),
                name:$("#courseForm input[name='name']").val(),
                stuRepreUsername:$("#courseForm input[name='stuRepreUsername']").val(),

                note:$("#courseForm textarea[name='note']").val(),
                published:publish
            };

            var beginDate=$("#courseForm input[name='beginDate']").val();
            var endDate=$("#courseForm input[name='endDate']").val();
            if(beginDate){
                data.beginDate= beginDate?beginDate+" 00:00:00.0":null;
            }
            if(endDate){
                data.endDate= endDate?endDate+" 00:00:00.0":null;
            }

            var books = [];

            $("#zebra-table").find("input[deleted='0']").parents("tr").each(function () {
                var $data = $(this).find("textarea.courseBookNote");
                $data.val();
                $data.attr("bookId");

                books.push(
                    JSON.stringify({
                        bookId:$data.attr("bookId"),
                        note:$data.val()
                        //courseId:$("#courseId").val()
                    })
                )
            })

            data.books='['+books.join()+']';


            $.ajax({
                type:'post',
                url:contextpath+'course/saveCourse.action?t='+new Date().getTime(),
                async:false,
                dataType:'json',
                data:data,
                success:function(json){
                    layer.close(index);
                    if(publish){
                        window.location.href = contextpath + "course/teacher/toCourseList.action";
                    }
                    //queryCourseBook();
                }
            })
        });
    }
}



/**
 * 备注的修改初始化
 */
function courseBookNoteUpdate() {
    if($("#readOnly").val() == "false"){
        $("span.courseBookNote").each(function () {
            $(this).unbind().bind("click",function () {
                $span = $(this);
                $span.hide();
                var $textarea = $(this).parent("td.courseBookNote").children("textarea.courseBookNote");
                $textarea.show().focus();

                $textarea.unbind().bind("blur",function () {
                    if($textarea.val() != $span.html()){//发生了修改
                        /*$.ajax({
                            type:'post',
                            url:contextpath+'course/updateCourseBookNote.action?t='+new Date().getTime(),
                            async:false,
                            dataType:'json',
                            data:{id:$span.attr("courseBookId"),note:$textarea.val()},
                            success:function(json){
                                if(json > 0){
                                    $span.html($textarea.val());
                                }
                            },
                            complete:function () {
                                $textarea.hide();
                                $textarea.unbind();
                                $span.show();
                            }
                        })*/
                        $span.html($textarea.val());
                        $textarea.hide();
                        $textarea.unbind();
                        $span.show();
                    }else{
                        $textarea.hide();
                        $textarea.unbind();
                        $span.show();
                    }

                })
            })
        })
    }
}

/**
 * 打开选书学生列表弹出层
 */
function course_book_student(courseBookId,bookname,t) {

}