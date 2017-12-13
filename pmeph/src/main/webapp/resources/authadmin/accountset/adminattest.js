$(function () {
	
	/*var proxy = $("#proxy").val();
	if(null!=proxy&&''!=proxy){
		$.ajax({
	        type:'get',
	        dataType:'json',
	        url:contextpath+'file/'+proxy+'/name.action',
	        async:false,
	        success:function(name){
	        	alert(name);
	        	$("#proxyName").html(name);
	        }
	    });
	}
	*/
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
    
    
    $("#fileNameDiv").hide();
    $("#uploadFile").uploadFile({
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
        	$('#fileName').html(filename);
        	$("#fileNameDiv").show();
        	
        	 $.ajax({
        	        type:'post',
        	        url:contextpath+'admininfocontroller/uploadProxy.action?',
        	        async:false,
        	        data:{"fileid":fileid,"id":$("#id").val()},
        	        success:function(code){
        	            if (code==1){
        	                message.success("上传成功");
        	            }
        	        }
        	    });
        	 
        	$("#fileid").val(fileid);
        	$("#proxyDiv").hide();
            console.log("上传完成：name " + filename + " fileid " + fileid);
        },
        progressall: function (loaded, total, bitrate) {
            console.log("正在上传。。。" + loaded / total);
        }
    });
    
    
    
});
function getform() {
	
	//alert($("#title").find("option:selected").text());
    var json={};
    json.realName=$("#realName").val();
    json.position=$("#position").val();
    json.telephone=$("#telephone").val();
    json.sex=$("input[name='sex']:checked").val();
    json.title=$("input[name='title']").val();
    json.handphone=$("#handphone").val();
    json.postCode=$("#postCode").val();
    json.email=$("#email").val();
    json.title=$("#title").find("option:selected").val();
    json.fax=$("#fax").val();
    json.address=$("#address").val();
    json.id=$("#id").val();
    console.log(json);
    alert(json.title);
    return json;

}
function submit(){
	if($("form").validate('submitValidate')){
	    $.ajax({
	        type:'post',
	        url:contextpath+'admininfocontroller/updateorguser.action',
	        async:false,
	        contentType: 'application/json',
	        dataType:'json',
	        data:JSON2.stringify(getform()),
	        success:function(code){
	            if (code=="success"){
	                message.success("提交成功");
	            }else{
	            	message.error("提交失败");
	            }
	        }
	    });
	}
}
//下载委托书
function downLoad(){
        window.location.href=contextpath+'file/download/'+$('#fileid').val()+'.action';
}
//下载老委托书
function downLoadProxy(fileId){
	alert(fileId);
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}