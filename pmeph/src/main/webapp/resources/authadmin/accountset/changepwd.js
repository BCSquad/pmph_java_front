function confirupd(){
    if($("#newpassword").val()==$("#confirmpassword").val()){
        modpassword();
    }else{
        message.error("两次输入密码不一致");
    }
}

/**
 * 获取表单数据
 * @returns {{}}
 */
function getform(){
    var json={};
    json.password=$("#newpassword").val();
    return json;
}

/**
 * 修改密码
 */
function modpassword() {
    $.ajax({
        type: 'post',
        url: contextpath + 'admininfocontroller/updateorguserpassword.action',
        async: false,
        contentType: 'application/json',
        dataType: 'json',
        data: JSON2.stringify(getform()),
        success: function (responsebean) {
            if (responsebean.code == 1) {
                message.success("密码修改成功");
            }
        }
    });
}