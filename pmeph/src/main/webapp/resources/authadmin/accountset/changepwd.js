$(function () {
    $('#newpassword').tipso({validator: "isNonEmpty|isPassword", message: "密码不能为空|密码必须由 密码必须由 8-16位大写字母，小写字母，数字，字符至少三种组成"});
    $('#confirmpassword').tipso({validator: "isNonEmpty|isPassword", message: "密码不能为空|密码必须由 密码必须由 8-16位大写字母，小写字母，数字，字符至少三种组成"});
})

function confirupd() {

	// 获取到密码值
	var conformedPassword = $("#confirmpassword").val();
	var newPassword = $("#newpassword").val();
	// 校验规则 正则表达式 只允许输入 数字跟字母
	//var reg = /^[A-Za-z0-9!@#$%^&*]{6,16}$/;

	// 通过正则的test方法 可以拿到一个boolean类型的值 判断即可ss
	//var flagconformedPwd = reg.test(conformedPassword);
	//var flagnewPwd = reg.test(newPassword);

    if($.fireValidator()){
        if (newPassword != conformedPassword) {
            message.error('“新密码”与“确认密码”不一致！');
            return;
        } else {
            modpassword();
        }
    }
}

/**
 * 获取表单数据
 * 
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
        url: contextpath + 'userinfo/updateorguserpassword.action',
        async: false,
       /* contentType: 'application/json',*/
        dataType: 'json',
        /*data: JSON2.stringify(getform()),*/
        data:getform(),
        success: function (responsebean) {
            if (responsebean.code == 1) {
                message.success("密码修改成功");
                window.location = contextpath + 'admininfocontroller/toadmininfo.action'
            }
        }
    });
}

