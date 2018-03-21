$(function () {
	$("#disabled_all").val();
	if ($("#disabled_all").val()=="true") {
		$("input").attr("disabled",true);
	}
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
    if ($("#uploadFile").length>0) {
    	$("#uploadFile").uploadFile({
        	/*accept:	"application/msword",*/
            accept : "image/*",  //限制上传类型只能为图片
        	valid:function(file){
        		console.log(file);
        		console.log(file.name.substring(file.name.lastIndexOf("."),file.name.lastIndexOf(".")+4));
        		/*if(file.type=="application/msword"){ */
        		var f=file.name.substring(file.name.lastIndexOf("."),file.name.lastIndexOf(".")+4);
        		if(f==".gif"||f==".jpg"||f==".jpeg"||f==".gif"||f==".png"||f==".bmp"||f=='.jpe'){
        			return true;
        		}else{
        			message.error("请选择图片文件");
        			return false;
        		}
        		
        	},
            start: function () {
                console.log("开始上传。。。");
            },
            done: function (filename, fileid) {
            	$('#fileName').html(filename);
            	$("#fileNameDiv").show();
            	
            	 /*$.ajax({
            	        type:'post',
            	        url:contextpath+'admininfocontroller/uploadProxy.action?',
            	        async:false,
            	        data:{"fileid":fileid,"id":$("#id").val()},
            	        success:function(code){
            	            if (code==1){
            	                message.success("上传成功");
            	                $("#fileNameDiv").show();
            	            }
            	        }
            	    });*/
            	 
            	$("#fileid").val(fileid);
            	$("#proxyDiv").hide();
                console.log("上传完成：name " + filename + " fileid " + fileid);
            },
            progressall: function (loaded, total, bitrate) {
                console.log("正在上传。。。" + loaded / total);
            }
        });
	}
    
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
    //json.title=$("#title").find("option:selected").val();
    json.fax=$("#fax").val();
    json.address=$("#address").val();
    json.id=$("#id").val();
    json.proxy=$("#fileid").val();
    json.isProxyUpload = 1; //提交 后台判断是否提交验证的标志
    /*json.birthday=$("#birthday").val();
    json.experience=$("#experience").val();
    json.workplace=$("#workplace").val();*/
    console.log(json);
    return json;

}
function submit(){
	if($("form").validate('submitValidate')){
	    $.ajax({
	        type:'post',
	        url:contextpath+'admininfocontroller/updateorguser.action',
	        async:false,
	       /* contentType: 'application/json',*/
	        dataType:'json',
	       /* data:JSON2.stringify(getform()),*/
	        data:getform(),
	        success:function(code){
	            if (code=="success"){
	                message.success("提交成功");
					//window.location.reload();
	                setTimeout(function(){
	                	window.location.href = contextpath + "admininfocontroller/toadmininfo.action";
	                }, 800);
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
	window.location.href=contextpath+'file/download/'+fileId+'.action';
}