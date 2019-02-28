

//查询学生
function queryStudent(){
    data = {
        pageNum:$("#student-dialog .page-num-temp").val(),
        pageSize:$("#student-dialog .page-size-select").find("input[name='page-size-select']").val(),
        studentName:$("#search-name").val(),
        studentId:$("#search-studentId").val(),
        className:$("#search-className").val(),
        courseBookId:$("#courseBookId").val(),
        contextpath:contextpath
    };

    $.ajax({
        type:'post',
        url:contextpath+'course/querybookStudent.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:data,
        success:function(json){
            $("#student-dialog .dialog-table").html(json.html);

            if (json.html.trim() == "") {
                $("#student-dialog .pagination-wrapper").hide();
                $("#student-dialog .no-more").show();
            }else{
                $("#student-dialog .no-more").hide();
                $("#student-dialog .pagination-wrapper").show();
                $("#student-dialog .pagination").css("display","inline-block");
                $("#student-dialog .pageJump").css("display","inline-block");
                $("#student-dialog .pagination").next("div").css("display","inline-block");
            }
            $('#student-dialog .pagination').html("");
            $("#student-dialog .totoal_count").html(json.totoal_count);
            //刷新分页栏
            Page({
                num: json.totoal_count,					//页码数
                startnum: $("#student-dialog .page-num-temp").val(),				//指定页码
                elem: $('#student-dialog .pagination'),
                callback: function (n){     //点击页码后触发的回调函数
                    $("#student-dialog .page-num-temp").val(n);
                    queryDialog();
                }
            });

        }
    });
}

/**
 * teacherCanceled状态切换
 * @param courseBookStudentId 所切换数据主键
 * @param teacherCanceled 原状态
 */
function switchTeacherCanceled(courseBookStudentId,teacherCanceled,t) {
    var $count_span = $("span.countRes[courseBookId='"+$("#courseBookId").val()+"']");
    var $t = $(t);

    $.ajax({
        type:'post',
        url:contextpath+'course/switchTeacherCanceled.action?t='+new Date().getTime(),
        async:false,
        dataType:'json',
        data:{id:courseBookStudentId,teacherCanceled:!!!teacherCanceled,courseBookId:$("#courseBookId").val()},
        success:function(json) {
            if(json.code == "OK"){
                $count_span.html(json.countRes);
                $t.parents("tr").removeClass("teacherCanceled").addClass(teacherCanceled?"":"teacherCanceled");
                $t.html(teacherCanceled?"取消订购":"恢复订购");
                $t.attr("onclick","javascript:switchTeacherCanceled("+courseBookStudentId+","+!teacherCanceled+",this)");
            }
        }
    })


}