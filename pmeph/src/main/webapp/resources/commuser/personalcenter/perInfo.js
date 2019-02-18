$(function () {
    $('.tab_1 .select-input').selectlist({
        zIndex: 100000,
        width: 192,
        height: 30,
        optionHeight: 30
    });

    $('.tab_3 .select-input').selectlist({
        zIndex: 100000, //每生成一个 就会自减1 已使各下拉框不互相遮盖 但一旦小于0 就会被0层元素遮盖而看不到下拉框 故设大
        width: 110,
        height: 30,
        optionHeight: 30
    });
    addTipso($(".tab_1"));

    $(".tab_3").each(function (table) {
        var $tab = $(this);
        var lineNum = $tab.children("tbody").children("tr").length;
        if(lineNum >1){
            addTipso($tab);
        }
    });




    $("#tables").calendar();
});

window.onload = function(){
    $("input[calendar]").each(function(i,n){
        var $t = $(n);
        $t.trigger("timeChange",new Date($t.val()));
    });
};

/**
 * 新增行方法
 * @param t 按钮传入的this 即被点击的按钮
 */
function add_line(t) {
    var $tr =$(t).parent("td").parent("tr");
    var $tbody = $tr.parent();
    //复制被点击行的outerHTML作为新行模板
    var $tr_new = $($tr.prop("outerHTML"));
    //输入框清空
    $tr_new.find("input:not([type])").val("");
    //隐藏域清空
    $tr_new.find("input[type='hidden']").val("");
    //单选默认选中一个
    $tr_new.find("input[type='radio']").each(function(i,n){
        //同name的被选中将会取消其他的选中，故会选中每组最后一个
        $(this).prop("checked",true);
    });
    //行号
    var count = $tbody.children("tr").length+1;
    //替换掉模板中关键属性的行号
    $tr_new.find("input").each(function (n) {
        var $t = $(this);
        replace_count($t,"id",count);
        replace_count($t,"name",count);
        if(typeof($t.attr("calendar"))!="undefined" ){
            replace_count($t,"max",count);
            replace_count($t,"min",count);
        }
    })
    replace_count($tr_new,"id",count);

    var $img = $tr_new.find("img.add_img");
    $img.attr("src",$img.attr("src").replace(/add\.png/ig,'del.png'));
    $img.attr("onclick",$img.attr("onclick").replace(/add_line/ig,'del_tr'));

    $tbody.append($tr_new);
    if(typeof($tr_new.find("input[calendar]").length>0)){
        $tr_new.calendar();
    }

    if(count == 2){
        addTipso($tbody);
    }else{
        addTipso($tr_new);
    }


}

function del_tr(t){
    var $tr =$(t).parent("td").parent("tr");
    var $tbody = $tr.parent();
    var count = $tbody.children("tr").length;
    if(count == 2){
        //$tbody.tipso("destroy");
        addTipso($tbody,"destroy");
    }else{
        //$tr.tipso("destroy");
        addTipso($tr,"destroy");
    }
    $tr.remove();
}

/**
 * 给jquery对象$t激活tipso插件(若有destroy传入则删除插件)
 * @param $w
 * @param destroy
 */
function addTipso($w,destroy) {
    $w.find("input.cg_input[validator]").each(function (n) {
        var $t = $(this);
        if(destroy){
            //删除
            $t.tipso("destroy");
        }else{
            //新增
            var validator = $t.attr("validator");
            var message = "";
            if (typeof($t.attr("message")) != "undefined") {
                message = $t.attr("message");
            } else {
                var placeholder = $t.attr("placeholder") || "";
                var va = validator.split("|");
                va.forEach(function (v) {
                    switch (v) {
                        case "isNonEmpty":
                            message += "|" + placeholder + "必填";
                            break
                        case "onlyInt":
                            message += "|" + placeholder + "必须是数字";
                            break
                        case "isEmail":
                            message += "|" + "邮箱格式不正确";
                            break
                        case "isMobile":
                            message += "|" + "手机号码格式不正确";
                            break
                        case "isCard":
                            message += "|" + "证件号格式不正确";
                            break
                        case "isPassport":
                            message += "|" + "护照号格式不正确";
                            break
                        case "isOfficialCard":
                            message += "|" + "军官证号格式不正确";
                            break
                    }
                })
            }
            message = message.replace(/^\|/ig, "");
            $t.tipso({validator: validator, message: message});
        }

    });
}

/**
 * 将$t的attr属性末尾的编号替换为count
 * @param $t jquery对象
 * @param attr 属性
 * @param count 编号
 */
function replace_count($t,attr,count){
    if(typeof($t.attr(attr))!="undefined" ){
        $t.attr(attr,$t.attr(attr).replace(/(_\d*)?(?='?$)/i,"_"+count));
    }
}


function btnSave(){
    if($.fireValidator()){
        window.message.confirm(
            "确定保存吗？"
            ,{icon: 7, title:'提示',btn:["确定","取消"]}
            ,function(index){
                layer.close(index);
                var username = $("#username").val();
                var realname = $("#realname").val();

                if (username == realname) {
                    if (confirm("您填写的申报姓名和账号一致，是否以当前姓名作为申报姓名！")) {
                        commit();
                    } else {
                        $("#realname")[0].focus();
                    }
                } else {
                    commit();
                }
            }
            ,function(index){
                layer.close(index);
            }
        );
    }
}

//提交
function commit(){
    $.ajax({
        type: "POST",
        url: contextpath + 'personalhomepage/perInfoSave.action',
        data: $('#objForm').serialize(),// 您的formid
        async: false,
        success: function (json) {
            if (json.msg == 'OK') {
                window.message.success("操作成功");
            }
        }
    });
}