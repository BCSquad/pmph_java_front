$(function () {

	
	//文件上传
	$("#uploadFile").uploadFile({
		
		accept:"image/*",
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
            console.log("上传完成：name " + filename + " fileid " + fileid);
            $("#avatar").val(fileid);
            $("#sxy-img1").attr("src",contextpath+"file/download/"+fileid+".action");
        },
        progressall: function (loaded, total, bitrate) {
            console.log("正在上传。。。" + loaded / total);
        }
    });	
	
	
    $('select').selectlist({
        zIndex: 10,
        width: 260,
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
        submitHandler:function(){
            saveobject();

        }
    });
    function saveobject() {
    }

})


function getform(){

    var json={};
    json.avatar=$("#avatar").val();
    json.realName=$("#realName").val();
    json.position=$("#position").val();
    json.telephone=$("#telephone").val();
    json.sex=$("input[name='sex']:checked").val();
    json.title=$("input[name='title']").val();
    json.handphone=$("#handphone").val();
    json.postCode=$("#postCode").val();
    json.email=$("#email").val();
    json.fax=$("#fax").val();
    json.address=$("#address").val();

    return json;

}


function save(){
    if($("form").validate('submitValidate')){
        $.ajax({
            type:'post',
            url:contextpath+'admininfocontroller/updateorguser.action',
            async:false,
            dataType:'json',
            data:getform(),
            success:function(code){
                if (code=="success"){
	                message.success("保存成功");
	                $("#sxy-img1").attr("src",contextpath+"file/download/"+fileid+".action");
	                location.href=contextpath+'admininfocontroller/toadmininfo.action?id='+json.id;
	            }else{
	            	message.error("保存失败");
	            }
            }
        });
    };

}

