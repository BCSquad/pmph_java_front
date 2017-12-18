$(function () {
	//文件上传
	$("#uploadFile").uploadFile({
		accept:"image/*",
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
            console.log("上传完成：name " + filename + " fileid " + fileid);
            var id=$("#id").val();
            $.ajax({
                type:'post',
                url:contextpath+'userinfo/updateavatar.action?id='+id+'&&avatar='+fileid,
                async:false,
                dataType:'json',
                success:function(json){
                    if (json.returncode=="OK"){
                    	$("#sxy-img1").attr("src",contextpath+"file/download/"+fileid+".action");
                    }
                }
            });
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
 //               	message.success("保存成功!");
                	location.href=contextpath+'userinfo/touser.action?id='+json.id;
                }else if(json.returncode=="DEFAULT"){
                	message.error("错误，请填写所有必填项!");
                }
            }
        });
    };

}