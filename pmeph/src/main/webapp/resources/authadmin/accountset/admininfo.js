$(function () {
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
        alert(123);
    }
    /* $('form').on('submit', function (event) {
     alert(111);
     //save();
     /!* event.preventDefault();
     $(this).validate('submitValidate'); //return boolean;*!/
     });*/
})
function getform(){

    var json={};
    json.realName=$("#realName").val();
    json.position=$("#position").val();
    json.telephone=$("#telephone").val();
    json.sex=$("input[name='sex']:checked").val();
    json.title=$("input[name='title']").val();
    json.handphone=$("#handphone").val();
    json.postCode=$("#postCode").val();
    json.email=$("#email").val();$("#tesetSelect").find("option:selected").text()
    json.fax=$("#fax").val();
    json.address=$("#address").val();
    /*json.birthday=$("#birthday").val();
    json.experience=$("#experience").val();
    json.workplace=$("#workplace").val();*/
    console.log(json);
    return json;

}


function save(){
    if($("form").validate('submitValidate')){
        $.ajax({
            type:'post',
            url:contextpath+'admininfocontroller/updateorguser.action',
            async:false,
           /* contentType: 'application/json',*/
            dataType:'json',
            /*data:JSON2.stringify(getform()),*/
            data:getform(),
            success:function(code){
                /*if (responsebean.code==1){
                    message.success("保存成功");
                }*/
                if (code=="success"){
	                message.success("提交成功");
	            }else{
	            	message.error("提交失败");
	            }
            }
        });
    };

}