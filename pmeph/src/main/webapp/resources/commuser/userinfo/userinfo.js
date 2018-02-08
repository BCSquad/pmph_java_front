
$(function () {
	//文件上传
	$("#uploadFile").uploadFile({
		accept:"image/*",
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
            console.log("上传完成：name " + filename + " fileid " + fileid);
             $("#fileid").val(fileid);
             $("#sxy-img1").attr("src",contextpath+"file/download/"+fileid+".action");
        },
        progressall: function (loaded, total, bitrate) {
            console.log("正在上传。。。" + loaded / total);
        }
    });

    $('select').selectlist({
        zIndex: 10,
        width: 264,
        height: 40,
        optionHeight: 40,
        initValue:$("#title-hidden").val()
    });
    $('form').validate({
        onFocus: function () {
            this.removeClass("input-error");
            return false;
        },
        onBlur: function () {
            var $parent = this.parent();
            var _status = parseInt(this.attr('data-status'));
            if (!_status) {
                this.addClass("input-error");
            }
            return false;
        },
    });

    choosesex();
});

//根据code选择性别
function choosesex(){
    var ste=$("#sex_hidden").val();	
    if(ste==1){
    	$("#sex1").attr("checked",true);
    }else if(ste==2){
    	$("#sex2").attr("checked",true);
    }
}

function getform(){
    var json={
    		fileid:$("#fileid").val(),
		    realname:$("#realname").val(),
		    experience:$("#experience").val(),
		    fax:$("#fax").val(),
		    sex:$("input[name='radio-set']:checked").val(),
		    title:$("input[name='title']").val(),
		    handphone:$("#handphone").val(),
		    postcode:$("#postcode").val(),
		    birthday:$("#birthday").val(),
		    email:$("#email").val(),
		    telephone:$("#telephone").val(),
		    org_id:$("input[name='edu1']").val(),
		    position:$("#position").val(),
		    address:$("#address").val(),
		    note:$("#note").val(),
		    signature:$("#signature").val(),
		    workplace:$("#workplace").val(),
		    id:$("#id").val(),
    };
    return json;
}

//普通用户信息编辑方法
function save(){
	
    if($("#orgForm").validate('submitValidate')){
        $.ajax({
            type:'post',
            url:contextpath+'userinfo/update.action',
            async:false,
            dataType:'json',
            data:getform(),
            success:function(json){
                if (json.returncode=="OK"){
                	 window.message.success("保存成功！");
                	 $("#sxy-img1").attr("src",contextpath+"file/download/"+fileid+".action");
                	window.location.href=contextpath+'personalhomepage/tohomepage.action?userId='+json.id;
                }else{
                	message.error("错误，请填写所有必填项!");
                }
            }
        });
    };
}

//教师认证
function teacher(){
	location.href=contextpath+'teacherauth/toPage.action';
}

//取消
function cancel(){
	window.location.href=contextpath+'personalhomepage/tohomepage.action';
}

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){
	
	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"aa").length <= ml) {
			maxStrlength=i;
		}else{
			
			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"aa").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}