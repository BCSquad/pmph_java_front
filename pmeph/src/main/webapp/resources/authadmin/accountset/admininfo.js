$(function () {

    $('#realname').tipso({validator: "isNonEmpty", message: "姓名不能为空"});
    $('#handphone').tipso({validator: "isNonEmpty|isMobile", message: "手机号码不能为空|手机号码格式不正确"});
    $('#postCode').tipso({validator: "isNonEmpty", message: "邮编不能为空"});
    $('#email').tipso({validator: "isNonEmpty|isEmail", message: "邮箱不能为空|邮箱格式不正确"});


	
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
        zIndex: 100,
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
	var data = getform();
    if($.fireValidator()){ //通过校验
    	if ($("#progress_original").val()==1
    			&&($("#realName_original").val()!=$("#realName").val()
    			||$("#handphone_original").val()!=$("#handphone").val()
    			||$("#email_original").val()!=$("#email").val())) {
    		//修改了敏感项，若提交需要重审，询问是否提交
    		window.message.confirm(
    				'<font color="red">真实姓名、手机、E-mail</font> 的修改将需要重新进行管理员认证!</br><font color="red"></font>'
    				,{icon: 7, title:'资料修改提醒',btn:["确定","取消"]}
    				
    				,function(index){
    					layer.close(index);
                        //提交，我要重新认证
                        data.progress=0;
                        updateorguser(data);
    					
    				}
    				,function(index){
    					layer.close(index);
                        //仅修改非敏感项
                        // data.realName=$("#realName_original").val();
                        // data.handphone=$("#handphone_original").val();
                        // data.email=$("#email_original").val();
                        // data.progress=-1;
                        // updateorguser(data);
    				}
    				);
		}else{
			//本来就未修改敏感项 直接修改
			data.progress=-1;
			updateorguser(data);
		}
    	//内部修改方法
    	function updateorguser(data){
    		$.ajax({
                type:'post',
                url:contextpath+'admininfocontroller/updateorguser.action',
                async:false,
                dataType:'json',
                data:data,
                success:function(code){
                    if (code=="success"){
    	                message.success("保存成功");
    	                //$("#sxy-img1").attr("src",contextpath+"file/download/"+fileid+".action");
    	                setTimeout(function(){
    	                	location.href=contextpath+'admininfocontroller/toadmininfo.action';
    	                }, 800);
    	                
    	            }else{
    	            	message.error("保存失败");
    	            }
                }
            });
    	}
        
    };

}

