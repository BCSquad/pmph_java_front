//添加好友 按钮触发
function addFriendfun(uid,realname,status){
	var data={uid:uid
			,status:status};
    window.message.confirm("您将添加该作家为好友！",{title:'好友申请',btn:["确定","取消"]},function(index){
        layer.close(index);
        $.ajax({
            type:'post',
            url:contextpath+'addFriend/addFriendfun.action?t='+new Date().getTime(),
            async:false,
            dataType:'json',
            data:data,
            success:function(json){
                if (status == 2) {
                    window.message.success("已和 "+realname+" 成为好友！");
                    $("#friend"+uid).removeClass("isBeenRequest").addClass("isFriend").html("<B>好友</B>").attr("title","已是您的好友！").unbind();
                } else {
                    window.message.success("已向 "+realname+" 发起好友申请！");
                    $("#friend"+uid).removeClass("add").addClass("hasRequest").attr("title","已申请加为好友，请等待对方同意。").unbind();

                }

            }
        });
    },function(index){layer.close(index);});
}