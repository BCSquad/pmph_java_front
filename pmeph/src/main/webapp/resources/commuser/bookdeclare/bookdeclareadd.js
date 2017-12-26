
$(function () {
    $('.dzdx').selectlist({
        width: 308,
        height: 30,
        optionHeight: 30
    });
    $('.xzly').selectlist({
    	width: 308,
    	height: 30,
    	optionHeight: 30
    });

});


//提交   类型1 表示提交  2 表示暂存
function buttAdd(type){
	if(checkNull(jsonStr)){
		$.ajax({
			type: "POST",
			url:contextpath+'material/doMaterialAdd.action?type='+type,
		//	url:contextpath+'material/doMaterialTest.action',
			data:$('#objForm').serialize(),// 你的formid
			async: false,
			dataType:"json",
		    success: function(msg) {
			    if(msg=='OK'){
			    	window.location.href=contextpath+"personalhomepage/tohomepageone.action";
			    }
		    }
		});
	}	
}

//放弃
function buttGive(){
	window.location.href=contextpath+"personalhomepage/tohomepageone.action";
}
