
var is_pmph_textbook_required;
var is_textbook_required;

$(function () {
    setTimeout(function () {
        $('#edu1').tipso({validator: "isNonEmpty", message: "请选择申报的图书"});
        $('#realname').tipso({validator: "isNonEmpty", message: "姓名不能为空"});
        $('#birthday').tipso({validator: "isNonEmpty", message: "出生日期不能为空"});
        $('#experience').tipso({validator: "isNonEmpty|onlyInt", message: "教龄不能为空|教龄必须是数字"});
        $('#org_name').tipso({validator: "isNonEmpty", message: "工作单位不能为空"});
        $('#position').tipso({validator: "isNonEmpty", message: "职务不能为空"});
        $('#zc').tipso({validator: "isNonEmpty", message: "职称不能为空"});
        $('#email').tipso({validator: "isNonEmpty|isEmail", message: "邮箱不能为空|邮箱格式不正确"});
        $('#handphone').tipso({validator: "isNonEmpty|isMobile", message: "手机号码不能为空|手机号码格式不正确"});
        $('#zjlx').tipso({validator: "isNonEmpty", message: "证件类型不能为空"});
        $('#idcard').tipso({validator: "isNonEmpty", message: "证件号码不能为空"});
        $('#address').tipso({validator: "isNonEmpty", message: "地址不能为空"});
        checkExtra();
    },0)


    var id = $("#material_id").val();
    setTimeout(function (){
        upload();
    },1000);
    queryMaterialMap(id);  //执行查询方法
    $('#pmph_rank').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#pmph_position').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#jcb_rank').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#jcb_position').selectlist({
        zIndex: 10,
        width: 110,
        height: 30,
        optionHeight: 30
    });

    $('.select-input').selectlist({
        zIndex: 10,
        width: 192,
        height: 30,
        optionHeight: 30
    });
    $('.book').selectlist({
        zIndex: 10,
        width: 200,
        height: 30,
        optionHeight: 30
    });
    $('#zclx').selectlist({
        width: 192,
        height: 30,
        optionHeight: 30
    });
    $('#degree').selectlist({
        width: 192,
        height: 30,
        optionHeight: 30
    });
    //人卫社教材编写-级别
    selectOption("pmph_rank_sl");
    //人卫社教材编写-职务
    selectOption("pmph_sl");
    //其他社教材-级别
    selectOption("jcb_rank_sl");
    //其他社教材-职务
    selectOption("jcjb_sl");

});

//下拉框格式优化
function selectOption(name){
    var els =document.getElementsByName(name);
    for (var i = 0, j = els.length; i < j; i++){
        $('#'+els[i].value).selectlist({
            width: 110,
            height: 30,
            optionHeight: 30
        });
    }
}

//附件上传方法
function upload(){
    $("#dwyjsc").uploadFile({
        start: function () {
            console.log("开始上传。。。");
        },
        done: function (filename, fileid) {
            $("#fileNameDiv").empty(); //清楚内容
            $("#fileNameDiv").append("<span><div class=\"filename\"><a href='javascript:' class='filename'  onclick='downLoadProxy("+fileid+")' title='\"+filename+\"'>"+filename+"</a></div></span>");
            $("#fileNameDiv").css("display","inline");
            $("#syllabus_id").val(fileid);
            $("#syllabus_name").val(filename);
            console.log("上传完成：name " + filename + " fileid " + fileid);
        },
        valid:function(file){
            if(file.size/1024/1024>=100){ //判断文件上传大小
                window.message.warning("不得上传100M以上文件!");
                return false;
            }
            return true;
        }

    });
}

//页面组合方法
function queryMaterialMap(id){
    $.ajax({
        type: "POST",
        url:contextpath+'expertation/queryMaterialMap.action',
        data:{material_id:id},// 您的formid
        dataType:"json",
        success: function(json) {
            chooseModel(json);
        }
    });
}

//模块显示与隐藏判断
function chooseModel(data){
    //学习经历
    if(data.is_edu_exp_used == "1"){
        $("#zyxxjl").css("display","block");
        //学习经历必填
        if(data.is_edu_exp_required == "1"){
            $("#zyxxjl_bt").css("display","inline");
            $('#xx_kssj').tipso({validator: "isNonEmpty", message: "学习经历起止时间必填"})
            //给其他值默认为无
            $("#xx_jssj").val(getNowFormatDate());
            $("#xx_school_name").val("无");
            $("#xx_major").val("无");
            $("#xx_degree").val("无");
        }else{
            $("#zyxxjl_xt").css("display","inline");

        }
    }
    //工作经历
    if(data.is_work_exp_used == "1"){
        $("#gzjl").css("display","block");
        //工作经历必填
        if(data.is_work_exp_required == "1"){
            $("#gzjl_bt").css("display","inline");
            $('#gz_kssj').tipso({validator: "isNonEmpty", message: "工作经历必填"})
            //给其他值默认为无
            $("#gz_jssj").val(getNowFormatDate());
            $("#gz_org_name").val("无");
            $("#gz_position").val("无");
        }else{
            $("#gzjl_xt").css("display","inline");
        }
    }
    //教学经历
    if(data.is_teach_exp_used == "1"){
        $("#jxjl").css("display","block");
        //教学经历必填
        if(data.is_teach_exp_required == "1"){
            $("#jxjl_bt").css("display","inline");
            $('#jx_kssj').tipso({validator: "isNonEmpty", message: "教学经历必填"})
            //给其他值默认为无
            $("#jx_jssj").val(getNowFormatDate());
            $("#jx_school_name").val("无");
            $("#jx_subject").val("无");
        }else{
            $("#jxjl_xt").css("display","inline");
        }
    }
    //个人成就情况
    if(data.is_achievement_used == "1"){
        $("#grcjqk").css("display","block");
        //个人成就必填
        if(data.is_achievement_required == "1"){
            $("#grcj_bt").css("display","inline");
            $('#gr_content').tipso({validator: "isNonEmpty", message: "个人成就必填"})
        }else{
            $("#grcj_xt").css("display","inline");
        }
    }
    //主要学术兼职
    if(data.is_acade_used == "1"){
        $("#xsjz").css("display","block");
        //主要学术兼职必填
        if(data.is_acade_required == "1"){
            $("#xsjz_bt").css("display","inline");
            $('#xs_org_name').tipso({validator: "isNonEmpty", message: "学术兼职必填"})
            //给其他值默认为无
            $("#xs_position").val("无");
        }else{
            $("#xsjz_xt").css("display","inline");
        }
    }
    //上版教材参编情况
    if(data.is_last_position_used == "1"){
        $("#sbjccb").css("display","block");
        //上版教材参编情况必填
        if(data.is_last_position_required == "1"){
            $("#sbjccb_bt").css("display","inline");
            $('#jc_material_name').tipso({validator: "isNonEmpty", message: "本套上板教材参编情况必填"})
            //    jsonStr=jsonStr+"{\"id\":\"jc_material_name\",\"content\":\"本套上板教材参编情况必填\"},{\"id\":\"jc_publish_date\",\"content\":\"发版时间必填\"},";
            //给其他值默认为无
            $("#jc_publish_date").val(getNowFormatDate());
        }else{
            $("#sbjccb_xt").css("display","inline");
        }
    }
    //主编国家规划教材情况
    if(data.is_national_plan_used == "1"){
        $("#zbgjjgh").css("display","block");
        //主编国家规划教材情况必填
        if(data.is_national_plan_required == "1"){
            $("#zbgjjgh_bt").css("display","inline");
            $('#hj_material_name').tipso({validator: "isNonEmpty", message: "主编国家规划教材情况必填"})
            //给其他值默认为无
            $("#hj_rank_text").val("无");
            $("#hj_isbn").val("无");
        }else{
            $("#zbgjjgh_xt").css("display","inline");
        }
    }
    //人卫教材编写情况
    if(data.is_pmph_textbook_used == "1"){
        $("#rwsjcbx").css("display","block");
        //人卫教材编写情况必填
        if(data.is_pmph_textbook_required == "1"){
            $("#rwsjcbx_bt").css("display","inline");
            $('#pmph_material_name').tipso({validator: "isNonEmpty", message: "人卫社教材编写情况必填"})
            //给其他值默认为无
            $("#pmph_publish_date").val(getNowFormatDate());
            $("#pmph_isbn").val("978-7-117-1");
        }else{
            $("#rwsjcbx_xt").css("display","inline");
        }
    }
    is_pmph_textbook_required = data.is_pmph_textbook_required;
    //其他社教材编写情况
    if(data.is_textbook_used == "1"){
        $("#qtjcbxqk").css("display","block");
        //其他社教材编写情况必填
        if(data.is_textbook_required == "1"){
            $("#qtjcbxqk_bt").css("display","inline");
            $('#jcb_material_name').tipso({validator: "isNonEmpty", message: "其他社教材名称不能为空"})
            //给其他值默认为无
            $("#jcb_publish_date").val(getNowFormatDate());
            $("#jcb_publisher").val("无");
            $("#jcb_isbn").val("978-7-1");
        }else{
            $("#qtjcbxqk_xt").css("display","inline");
        }
    }
    is_textbook_required = data.is_textbook_required;
    //参加人卫慕课、数字教材编写情况
    if(data.is_mooc_digital_used == "1"){
        $("#digital").css("display","block");
        //参加人卫慕课、数字教材编写情况必填
        if(data.is_mooc_digital_required == "1"){
            $("#digital_bt").css("display","inline");
            $('#mooc_content').tipso({validator: "isNonEmpty", message: "人卫慕课、数字教材编写情况必填"})
        }else{
            $("#digital_xt").css("display","inline");
        }
    }
    //精品课程建设情况
    if(data.is_course_used == "1"){
        $("#gjjpkcjs").css("display","block");
        //精品课程建设情况必填
        if(data.is_course_required == "1"){
            $("#gjjpkcjs_bt").css("display","inline");
            $('#gj_course_name').tipso({validator: "isNonEmpty", message: "精品课程建设情况必填"})
            //给其他值默认为无
            $("#gj_class_hour").val("无");
        }else{
            $("#gjjpkcjs_xt").css("display","inline");
        }
    }
    //科研情况
    if(data.is_research_used == "1"){
        $("#zjkyqk").css("display","block");
        //科研情况必填
        if(data.is_research_required == "1"){
            $("#zjkyqk_bt").css("display","inline");
            $('#zjk_research_name').tipso({validator: "isNonEmpty", message: "科研情况必填"})
            //给其他值默认为无
            $("#zjk_award").val("无");
            $("#zjk_approval_unit").val("无");
        }else{
            $("#zjkyqk_xt").css("display","inline");
        }
    }
    //主编学术专著情况
    if(data.is_monograph_used == "1"){
        $("#zbxszz").css("display","block");
        //主编学术专著情况必填
        if(data.is_monograph_required == "1"){
            $("#zbxszz_bt").css("display","inline");
            $('#zb_monograph_name').tipso({validator: "isNonEmpty", message: "专著名称必填"})
            //给其他值默认为无
            $("#zb_monograph_date").val(getNowFormatDate());
            $("#zb_publish_date").val(getNowFormatDate());
            $("#zb_publisher").val("无");
        }else{
            $("#zbxszz_xt").css("display","inline");
        }
    }
    //出版行业获奖情况
    if(data.is_publish_reward_used == "1"){
        $("#publish").css("display","block");
        //出版行业获奖情况必填
        if(data.is_publish_reward_required == "1"){
            $("#publish_bt").css("display","inline");
            $('#pu_reward_name').tipso({validator: "isNonEmpty", message: "出版行业获奖情况必填"})
            //给其他值默认为无
            $("#pu_reward_date").val(getNowFormatDate());
            $("#pu_award_unit").val("无");
        }else{
            $("#publish_xt").css("display","inline");
        }
    }
    //SCI论文投稿及影响因子情况
    if(data.is_sci_used == "1"){
        $("#sci").css("display","block");
        //SCI论文投稿及影响因子情况必填
        if(data.is_sci_required == "1"){
            $("#sci_bt").css("display","inline");
            $('#sci_paper_name').tipso({validator: "isNonEmpty", message: "SCI论文投稿及影响因子情况必填"})
            //给其他值默认为无
            $("#sci_journal_name").val("无");
            $("#sci_factor").val("无");
            $("#sci_publish_date").val(getNowFormatDate());
        }else{
            $("#sci_xt").css("display","inline");
        }
    }
    //临床医学获奖情况
    if(data.is_clinical_reward_used == "1"){
        $("#clinical").css("display","block");
        //临床医学获奖情况必填
        if(data.is_clinical_reward_required == "1"){
            $("#clinical_bt").css("display","inline");
            $('#cl_reward_name').tipso({validator: "isNonEmpty", message: "临床医学获奖情况必填"})
            //给其他值默认为无
            $("#cl_reward_date").val(getNowFormatDate());
        }else{
            $("#clinical_xt").css("display","inline");
        }
    }
    //学术荣誉授予情况
    if(data.is_acade_reward_used == "1"){
        $("#acade").css("display","block");
        //学术荣誉授予情况必填
        if(data.is_acade_reward_required == "1"){
            $("#acade_bt").css("display","inline");
            $('#ac_reward_name').tipso({validator: "isNonEmpty", message: "学术荣誉授予情况必填"})
            //给其他值默认为无
            $("#ac_reward_date").val(getNowFormatDate());
        }else{
            $("#acade_xt").css("display","inline");
        }
    }
    //编写内容意向表
    if(data.is_intention_used == "1"){
        $("#intention").css("display","block");
        //编写内容意向表必填
        if(data.is_intention_required == "1"){
            $("#intention_bt").css("display","inline");
            $('#intention_content').tipso({validator: "isNonEmpty", message: "意向内容必填"})
        }else{
            $("#intention_xt").css("display","inline");
        }
    }

}

//生成随机数
function only(ele,arr){
    if(arr.length==0){
        return true;
    }
    for(var j=0;j<arr.length;j++){
        if(ele==arr[j]){
            return false;
        }else{
            return true;
        }
    }
}

var arr=[0,1,2,3,4,5,6,"a","b","c","d","e","f","g"];

function fnt(){
    var randNum=null;
    var old=[];
    var str="";
    function done(){
        randNum=Math.floor(Math.random()*14);
        if(only(randNum,old)){
            str=str+arr[randNum];
            old.push(randNum);
        }
        else{
            done();
        }
    }
    for(var index=0;index<4;index++){
        done();
    }
    return str;
};

//删除内容
function delTsxz(str){
    $("#"+str).remove();
}

//追加学习经历tr
function add_xxjl(){
    var num = fnt();
    var $table = $("#tab_xxjl");
    var $tr = $("<tr id='xxjl_"+num+"'>"+
        "<td><input class='cg_input' placeholder='开始时间'  calendar format=\"'yyyy-mm-dd'\"  z-index='100' max=\"'$#xx_jssj_"+num+"'\" id='xx_kssj_"+num+"'  name='xx_kssj' value='' style='width: 80px;'/>"+
        " - <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' min=\"'$#xx_kssj_"+num+"'\" id='xx_jssj_"+num+"' name='xx_jssj' value='' style='width: 80px;'/></td>"+
        "<td><input class='cg_input' maxlength='80' style='width: 230px' name='xx_school_name' id='xx_school_name_"+num+"' value='' placeholder='学校名称'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='xx_major' value='' id='xx_major_"+num+"' placeholder='所学专业'/></td>"+
        "<td><input class='cg_input' maxlength='30' name='xx_degree' value='' id='xx_degree_"+num+"' style='width: 110px;' placeholder='学历'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='xx_note' value='' style='width: 240px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='xx_kssj_"+num+"' />" +
        "<input type='hidden' name='xx_id' value=''>"+
        // "<input type='hidden' name='zdjy' value='xx_kssj_"+num+",xx_jssj_"+num+",xx_school_name_"+num+",xx_major_"+num+",xx_degree_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xxjl_"+num+"')\"/></td>"
    );
    $table.append($tr);
    $tr.calendar();
}

//追加工作经历tr
function add_gzjl(){
    var num = fnt();
    var $table = $("#tab_gzjl");
    var $tr = $("<tr id='gzjl_"+num+"'>"+
        "<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' max=\"'$#gz_jssj_"+num+"'\" id='gz_kssj_"+num+"'  name='gz_kssj' value='' style='width: 80px;'/>"+
        " - <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" z-index='100' min=\"'$#gz_kssj_"+num+"'\" id='gz_jssj_"+num+"'  name='gz_jssj' value='' style='width: 80px;'/></td>"+
        "<td><input class='cg_input' maxlength='100' style=\"width: 370px\" name='gz_org_name' id='gz_org_name_"+num+"' value='' placeholder='工作单位'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='gz_position' id='gz_position_"+num+"' value='' placeholder='职位'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='gz_note' value='' style='width: 230px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='gz_kssj_"+num+" '/>" +
        "<input type='hidden' name='gz_id' value=''>"+
        //  "<input type='hidden' name='zdjy' value='gz_kssj_"+num+",gz_jssj_"+num+",gz_org_name_"+num+",gz_position_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('gzjl_"+num+"')\"/></td>"
    );
    $table.append($tr);
    $tr.calendar();
}

//追加教学经历
function add_jxjl(){
    var num = fnt();
    var $table = $("#tab_jxjz");
    var $tr = $("<tr id='jxjz_"+num+"'>"+
        "<td><input class='cg_input' placeholder='开始时间' calendar format=\"'yyyy-mm-dd'\" max=\"'$#jx_jssj_"+num+"'\" id='jx_kssj_"+num+"'   z-index='100'  name='jx_kssj' value='' style='width: 80px;'/>"+
        " - <input class='cg_input' placeholder='结束时间' calendar format=\"'yyyy-mm-dd'\" min=\"'$#jx_kssj_"+num+"'\" id='jx_jssj_"+num+"'  z-index='100' name='jx_jssj' value='' style='width: 80px;'/></td>"+
        "<td><input class='cg_input' maxlength='100' style=\"width: 320px;\" name='jx_school_name' id='jx_school_name_"+num+"' value='' placeholder='学校名称'/></td>"+
        "<td><input class='cg_input' maxlength='150' style=\"width: 290px;\" name='jx_subject' id='jx_subject_"+num+"' value='' placeholder='教学科目'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='jx_note' value='' style='width: 180px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='jx_kssj_"+num+"' />" +
        "<input type='hidden' name='jx_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='jx_kssj_"+num+",jx_jssj_"+num+",jx_school_name_"+num+",jx_subject_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jxjz_"+num+"')\"/></td>"
    );
    $table.append($tr);
    $tr.calendar();
}

//追加学术兼职
function add_xsjz(){
    var num = fnt();
    var $table = $("#tab_xsjz");
    var $tr = $("<tr id='xsjz_"+num+"'>"+
        "<td><input class='cg_input' name='xs_org_name' style=\"width: 370px\" maxlength='100' id='xs_org_name_"+num+"' value='' placeholder='学术组织'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 100%;'><tr>"+
        "<td><input type='radio' name='xs_rank_"+num+"' checked='checked' value='0'/>无</td>"+
        "<td><input type='radio' name='xs_rank_"+num+"' value='1'/>国际</td>"+
        "<td><input type='radio' name='xs_rank_"+num+"' value='2'/>国家</td>"+
        "<td><input type='radio' name='xs_rank_"+num+"' value='3'/>省部</td>"+
        "<td><input type='radio' name='xs_rank_"+num+"' value='4'/>市级</td>"+
        "</tr></table>"+
        "<input type='hidden' name='xs_rank' value='xs_rank_"+num+"' />"+
        "<td><input class='cg_input' maxlength='50' name='xs_position' id='xs_position_"+num+"' value='' placeholder='职务'/></td>"+
        "<td><input maxlength='33' class='cg_input' maxlength='100' name='xs_note' value='' style='width: 180px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='xs_org_name_"+num+"'/>" +
        "<input type='hidden' name='xs_id' value=''>"+
        //   "<input type='hidden' name='zdjy' value='xs_org_name_"+num+",xs_position_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xsjz_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
}

//上版教材参编情况
function add_jccb(){
    var num = fnt();
    var $table = $("#tab_jccb");
    var $tr = $("<tr id='jccb_"+num+"'>"+
        "<td><input class='cg_input' style=\"width: 320px\" maxlength='100' id='jc_material_name_"+num+"' name='jc_material_name' value='' style='width: 260px;' placeholder='教材名称'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 100%;'><tr>"+
        "<td><input type='radio' name='jc_position_"+num+"' checked='checked' value='0'/>无</td>"+
        "<td><input type='radio' name='jc_position_"+num+"' value='1'/>主编</td>"+
        "<td><input type='radio' name='jc_position_"+num+"' value='2'/>副主编</td>"+
        "<td><input type='radio' name='jc_position_"+num+"' value='3'/>编委</td>"+
        "</tr></table>"+
        "<input type='hidden' name='jc_position' value='jc_position_"+num+"'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 80px;'><tr>"+
        "<td><input type='radio' name='jc_is_digital_editor_"+num+"' value='1' />是</td>"+
        "<td><input type='radio' name='jc_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
        "</tr></table>"+
        "<input type='hidden' name='jc_is_digital_editor' value='jc_is_digital_editor_"+num+"' /></td>"+
        "<td><input maxlength='20' class='cg_input' name='jc_publisher' value='人民卫生出版社' readonly='true' style='width: 100px;' /></td>" +
        "<td><input class='cg_input' name='jc_publish_date' id='jc_publish_date_"+num+"' value='' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100'  style='width: 100px;'/></td>"+
        "<td><input maxlength='100' class='cg_input' name='jc_note' value='' style='width: 100px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='jc_material_name_"+num+"' />" +
        "<input type='hidden' name='jc_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='jc_material_name_"+num+",jc_publish_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jccb_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}

//精品课程建设情况
function add_jpkcjs(str,dim){
    var num = fnt();
    var $table = $("#"+str);
    var $tr = $("<tr id='jpkcjs_"+num+"'>"+
        "<td><input class='cg_input' maxlength='50' name='gj_course_name' id='gj_course_name_"+num+"' value='' style='width: 420px;' placeholder='课程名称'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='gj_class_hour'  id='gj_class_hour_"+num+"' value='' style='width: 130px;' placeholder='课时数'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width:100%;'><tr>"+
        "<td><input type='radio' name='gj_type_"+num+"' checked='checked' value='0'/>无</td>"+
        "<td><input type='radio' name='gj_type_"+num+"' value='1'/>国际</td>"+
        "<td><input type='radio' name='gj_type_"+num+"' value='2'/>国家</td>"+
        "<td><input type='radio' name='gj_type_"+num+"' value='3'/>省部</td>"+
        "</tr></table>"+
        "<input type='hidden' name='gj_type' value='gj_type_"+num+"' /></td>"+
        "<td><input maxlength='100' class='cg_input' name='gj_note' value='' style='width: 240px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='gj_course_name_"+num+"' />" +
        "<input type='hidden' name='gj_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='gj_course_name_"+num+",gj_class_hour_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('jpkcjs_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
}

//主编国家级规划教材
function add_gjghjc(){
    var num = fnt();
    var $table = $("#tab_gjghjc");
    var $tr = $("<tr id='gjghjc_"+num+"'>"+
        "<td><input class='cg_input' maxlength='100' name='hj_material_name' id='hj_material_name_"+num+"' value='' style='width: 300px;' placeholder='教材名称'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='hj_isbn' value='' id='hj_isbn_"+num+"' style='width: 110px;' placeholder='标准书号'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='hj_rank_text' id='hj_rank_text_"+num+"' value='' style='width: 300px;' placeholder='教材级别' /></td>"+
        "<td><input class='cg_input' maxlength='100' name='hj_note' value='' style='width: 250px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='hj_material_name_"+num+"' />" +
        "<input type='hidden' name='hj_id' value=''>"+
        //   "<input type='hidden' name='zdjy' value='hj_material_name_"+num+",hj_isbn_"+num+",hj_rank_text_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('gjghjc_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
}

//人卫社教材编写情况
function add_rwsjcbx(){
    var num = fnt();
    var $table = $("#tab_rwsjcbx");
    var $tr = $("<tr id='pmph_"+num+"'>"+
        "<td><input class='cg_input' maxlength='100' name='pmph_material_name' id='pmph_material_name_"+num+"' value='' style='width: 320px;' placeholder='教材名称'/></td>"+
        "<td><select id='pmph_rank_"+num+"' name='pmph_rank'>"+
        "<option value='0'>无</option>"+
        "<option value='1'>国家</option>"+
        "<option value='2'>省部</option>"+
        "<option value='3'>协编</option>"+
        "<option value='4'>校本</option>"+
        "<option value='5'>其他</option>"+
        "</select></td>"+
        "<td><select id='pmph_position_"+num+"' name='pmph_position'>"+
        "<option value='0'>无</option>"+
        "<option value='1'>主编</option>"+
        "<option value='2'>副主编</option>"+
        "<option value='3'>编委</option>"+
        "</select></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 80px;'><tr>"+
        "<td><input type='radio' name='pmph_is_digital_editor_"+num+"' value='1' />是</td>"+
        "<td><input type='radio' name='pmph_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
        "</tr></table>"+
        "<input type='hidden' name='pmph_is_digital_editor' value='pmph_is_digital_editor_"+num+"' /></td>"+
        "<td><input class='cg_input' id='pmph_publish_date_"+num+"' placeholder='出版时间' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='pmph_publish_date' value='' style='width: 100px;'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='pmph_isbn' value='' id='pmph_isbn_"+num+"'  style='width: 100px;' placeholder='978-7-117-'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='pmph_note' value='' placeholder='备注' style='width: 140px;'/>" +
        "<input type='hidden' name='zdjy' value='pmph_material_name_"+num+"' />" +
        "<input type='hidden' name='pmph_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='pmph_material_name_"+num+",pmph_isbn_"+num+",pmph_publish_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('pmph_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $('#pmph_rank_'+num).selectlist({
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#pmph_position_'+num).selectlist({
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $tr.calendar();
}
//其他社教材编写情况
function add_jcbx(){
    var num = fnt();
    var $table = $("#tab_qtjcbxqk");
    var $tr = $("<tr id='qtjcbxqk_"+num+"'>"+
        "<td><input class='cg_input' maxlength='100' name='jcb_material_name' id='jcb_material_name_"+num+"' value='' style='width: 200px;' placeholder='教材名称'/></td>"+
        "<td><select id='jcb_rank_"+num+"' name='jcb_rank'>"+
        "<option value='0'>无</option>"+
        "<option value='1'>国家</option>"+
        "<option value='2'>省部</option>"+
        "<option value='3'>协编</option>"+
        "<option value='4'>校本</option>"+
        "<option value='5'>其他</option>"+
        "</select></td>"+
        "<td><select id='jcb_position_"+num+"' name='jcb_position'>"+
        "<option value='0'>无</option>"+
        "<option value='1'>主编</option>"+
        "<option value='2'>副主编</option>"+
        "<option value='3'>编委</option>"+
        "</select></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 80px;'><tr>"+
        "<td><input type='radio' name='jcb_is_digital_editor_"+num+"' value='1' />是</td>"+
        "<td><input type='radio' name='jcb_is_digital_editor_"+num+"' value='0' checked='checked'/>否</td>"+
        "</tr></table>"+
        "<input type='hidden' name='jcb_is_digital_editor' value='jcb_is_digital_editor_"+num+"' /></td>"+
        "<td><input class='cg_input' maxlength='50' name='jcb_publisher' id='jcb_publisher_"+num+"' value='' style='width: 100px;' placeholder='出版社'/></td>"+
        "<td><input class='cg_input' placeholder='出版时间' id='jcb_publish_date_"+num+"' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='jcb_publish_date' value='' style='width: 100px;'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='jcb_isbn' id='jcb_isbn_"+num+"' value='' style='width: 100px;' placeholder='978-7-'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='jcb_note' value='' placeholder='备注' style='width:130px;'/>" +
        "<input type='hidden' name='zdjy' value='jcb_material_name_"+num+"' />" +
        "<input type='hidden' name='jcb_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='jcb_material_name_"+num+",jcb_publisher_"+num+",jcb_isbn_"+num+",jcb_publish_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('qtjcbxqk_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $('#jcb_position_'+num).selectlist({
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#jcb_rank_'+num).selectlist({
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $tr.calendar();
}
//作家科研
function add_zjky(){
    var num = fnt();
    var $table = $("#tab_zjky");
    var $tr = $("<tr id='zjky_"+num+"'>"+
        "<td><input class='cg_input' maxlength='150' name='zjk_research_name' value='' id='zjk_research_name_"+num+"' style='width: 300px;' placeholder='课题名称'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='zjk_approval_unit' value='' id='zjk_approval_unit_"+num+"' style='width: 300px;' placeholder='审批单位'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='zjk_award' value='' id='zjk_award_"+num+"' style='width: 300px;' placeholder='获奖情况'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='zjk_note' value='' style='width: 90px;' placeholder='备注'/>" +
        "<input type='hidden' name='zdjy' value='zjk_research_name_"+num+"' />" +
        "<input type='hidden' name='zjk_id' value=''>"+
        //   "<input type='hidden' name='zdjy' value='zjk_research_name_"+num+",zjk_approval_unit_"+num+",zjk_award_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zjky_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
}
//主编学术专著情况表
function add_zbxszz(){
    var num = fnt();
    var $table = $("#tab_zbxszz");
    var $tr = $("<tr id='zbxszz_"+num+"'>"+
        "<td><input class='cg_input' maxlength='50' name='zb_monograph_name' id='zb_monograph_name_"+num+"' value='' style='width: 200px;' placeholder='教材名称' maxlength='16'/></td>"+
        "<td><input class='cg_input' name='zb_monograph_date' id='zb_monograph_date_"+num+"' value='' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\" placeholder='发表日期'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 140px;'><tr>"+
        "<td><input type='radio' name='is_self_paid_"+num+"' value='0' checked='checked'/>公费</td>"+
        "<td><input type='radio' name='is_self_paid_"+num+"' value='1' />自费</td>"+
        "</tr></table>"+
        "<input type='hidden' name='is_self_paid' value='is_self_paid_"+num+"' /></td>"+
        "<td><input class='cg_input' maxlength='50' name='zb_publisher' value='' id='zb_publisher_"+num+"' style='width: 180px;' placeholder='出版单位'  maxlength='16'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='zb_publish_date' value='' id='zb_publish_date_"+num+"' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\" placeholder='出版时间'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='zb_note' value='' style='width: 200px;' placeholder='备注'  maxlength='33'/>" +
        "<input type='hidden' name='zdjy' value='zb_monograph_name_"+num+"' />" +
        "<input type='hidden' name='zb_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='zb_monograph_name_"+num+",zb_monograph_date_"+num+",zb_publisher_"+num+",zb_publish_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zbxszz_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}
//出版行业获奖情况表
function add_publish(){
    var num = fnt();
    var $table = $("#tab_publish");
    var $tr = $("<tr id='publish_"+num+"'>"+
        "<td><input class='cg_input' maxlength='50' name='pu_reward_name' id='pu_reward_name_"+num+"' value='' style='width: 300px;' placeholder='奖项名称' maxlength='16'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='pu_award_unit' id='pu_award_unit_"+num+"' value='' style='width: 300px;' placeholder='评奖单位' maxlength='16'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='pu_reward_date' id='pu_reward_date_"+num+"' value='' style='width: 120px;' calendar format=\"'yyyy-mm-dd'\"  placeholder='获奖时间'/>"+
        "</td>"+
        "<td><input class='cg_input' maxlength='100' name='pu_note' value='' style='width: 250px;' placeholder='备注' maxlength='33'/>" +
        "<input type='hidden' name='zdjy' value='pu_reward_name_"+num+"' />" +
        "<input type='hidden' name='pu_id' value=''>"+
        //    "<input type='hidden' name='zdjy' value='pu_reward_name_"+num+",pu_award_unit_"+num+",pu_reward_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('publish_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}
//SCI论文投稿及影响因子情况表
function add_sci(){
    var num = fnt();
    var $table = $("#tab_sci");
    var $tr = $("<tr id='sci_"+num+"'>"+
        "<td><input class='cg_input' name='sci_paper_name' id='sci_paper_name_"+num+"' value='' style='width: 410px;' placeholder='论文名称' maxlength='100'/></td>"+
        "<td><input class='cg_input' name='sci_journal_name' id='sci_journal_name_"+num+"' value='' style='width: 130px;' placeholder='期刊名称' maxlength='50'/></td>"+
        "<td><input class='cg_input' name='sci_factor' id='sci_factor_"+num+"' value='' style='width: 170px;' placeholder='期刊SCI影响因子' maxlength='20'/></td>"+
        "<td><input class='cg_input' name='sci_publish_date' id='sci_publish_date_"+num+"' value='' style='width: 110px;' calendar format=\"'yyyy-mm-dd'\" placeholder='发表时间'/></td>"+
        "<td><input class='cg_input' name='sci_note' value='' style='width: 140px;' placeholder='备注' maxlength='100'/>" +
        "<input type='hidden' name='zdjy' value='sci_paper_name_"+num+"' />" +
        "<input type='hidden' name='sci_id' value=''>"+
        //  "<input type='hidden' name='zdjy' value='sci_paper_name_"+num+",sci_journal_name_"+num+",sci_factor_"+num+",sci_publish_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('sci_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}

//临床医学获奖情况表
function add_clinical(){
    var num = fnt();
    var $table = $("#tab_clinical");
    var $tr = $("<tr id='clinical_"+num+"'>"+
        "<td><input class='cg_input' name='cl_reward_name' id='cl_reward_name_"+num+"' maxlength='50' value='' style='width: 410px;' placeholder='奖项名称'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width: 180px;'><tr>"+
        "<td><input type='radio' name='cl_award_unit_"+num+"' value='0' checked='checked'/>无</td>"+
        "<td><input type='radio' name='cl_award_unit_"+num+"' value='1'/>国际</td>"+
        "<td><input type='radio' name='cl_award_unit_"+num+"' value='2' />国家</td>"+
        "</tr></table>"+
        "<input type='hidden' name='cl_award_unit' value='cl_award_unit_"+num+"' /></td>"+
        "<td><input class='cg_input' name='cl_reward_date' id='cl_reward_date_"+num+"' value='' style='width: 180px;' calendar format=\"'yyyy-mm-dd'\" placeholder='获奖时间'/></td>"+
        "<td><input class='cg_input' name='cl_note' value='' style='width: 230px;' placeholder='备注' maxlength='100'/>" +
        "<input type='hidden' name='zdjy' value='cl_reward_name_"+num+"' />" +
        "<input type='hidden' name='cl_id' value=''>"+
        //      "<input type='hidden' name='zdjy' value='cl_reward_name_"+num+",cl_reward_date_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('clinical_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}
//学术荣誉授予情况表
function add_acade(){
    var num = fnt();
    var $table = $("#tab_acade");
    var $tr = $("<tr id='acade_"+num+"'>"+
        "<td><input class='cg_input' name='ac_reward_name' id='ac_reward_name_"+num+"' maxlength='50' id='acade_reward_name' value='' style='width: 410px;' placeholder='荣誉名称'/></td>"+
        "<td style='color: #333333;'>"+
        "<table class='radio_tb' style='width:280px;'><tr>"+
        "<td><input type='radio' name='ac_award_unit_"+num+"' value='0' checked='checked'/>无</td>"+
        "<td><input type='radio' name='ac_award_unit_"+num+"' value='1'/>国际</td>"+
        "<td><input type='radio' name='ac_award_unit_"+num+"' value='2'/>国家</td>"+
        "<td><input type='radio' name='ac_award_unit_"+num+"' value='3'/>省部</td>"+
        "<td><input type='radio' name='ac_award_unit_"+num+"' value='4' />市级</td>"+
        "</tr></table>"+
        "<input type='hidden' name='ac_award_unit' value='ac_award_unit_"+num+"' /></td>"+
        "<td><input class='cg_input' name='ac_reward_date' id='ac_reward_date_"+num+"' value='' style='width: 150px;' calendar format=\"'yyyy-mm-dd'\" placeholder='授予时间'/></td>"+
        "<td><input class='cg_input' name='ac_note' value='' style='width: 180px;' placeholder='备注' maxlength='100'/>" +
        "<input type='hidden' name='zdjy' value='ac_reward_date_"+num+"' />" +
        "<input type='hidden' name='ac_id' value=''>"+
        //      "<input type='hidden' name='zdjy' value='ac_reward_date_"+num+",ac_reward_name_"+num+"' />" +
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('acade_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
}

//删除表格tr
function del_tr(trId){
  //  document.getElementById(trId).remove();
    $("#"+trId).remove();
}

//提交   类型1 表示提交  2 表示暂存
function buttAdd(type) {
    if (type == '2') { //表示暂存
        //避免重复点击
        document.getElementById('buzc').onclick = function () {
            window.message.warning("请不要重复点击");
        };
        document.getElementById('butj').onclick = function () {
            window.message.warning("请不要重复点击");
        };
        $.ajax({
            type: "POST",
            url: contextpath + 'expertation/doExpertationAdd.action?sjump=1&type=' + type,
            data: $('#objForm').serialize(),// 您的formid
            async: false,
            success: function (json) {
                if (json.msg == 'OK') {
                    window.message.success("操作成功,正在跳转页面");
                    window.location.href = contextpath + "expertation/declare.action";
                }
            }
        });
    } else {  //表示提交
        checkLb();
        if ($.fireValidator()) {
            $.ajax({
                type: "POST",
                url: contextpath + 'expertation/doExpertationAdd.action?sjump=1&type=' + type,
                data: $('#objForm').serialize(),// 您的formid
                async: false,
                dataType: "json",
                success: function (json) {
                    if (json.msg == 'OK') {
                        window.message.success("操作成功,正在跳转页面");
                        window.location.href = contextpath + "expertation/declare.action";
                    }
                }
            });
        }
    }
}

//判断checkbox是否被选中
function checkBoxInfo() {
    var els =document.getElementsByName("preset_position");
    for (var i = 0, j = els.length; i < j; i++){
        var a = $("input[name='"+els[i].value+"']:checked").val();
        if(a == undefined){
            window.message.warning("请选择申报的职位！");
            return false;
        }
    }
    return true;
}

//根据name判断获取的值是否有重复的
function checkEqual(name){
    //获取name属性的对象数组(节点数组)
    var map = $('input[name^="textbook_id"]').map(
        function(){return this.value
        }).get();
    //遍历数组并比较是否存在相同值
    var nary=map.sort();
    for(var i=0;i<map.length;i++){
        if(nary[i] == ""){
            window.message.warning("申报书籍不能为空，请选择书籍");
            return false;
        }
        if (nary[i]==nary[i+1]){
            window.message.warning("不能选择相同书籍!请重新选择书籍");
            return false;
        }
    }
    return true;
}

//列表填报校验
function checkLb(){
    var map = $('input[name^="zdjy"]').map(
        function(){return this.value
        }).get();
    if(map!=null){
        for(var i=0;i<map.length;i++){
            var strs= new Array(); //定义一数组
            strs=map[i].split(","); //字符分割
            //遍历
            for ( var j = 0; j < strs.length; j++) {
                jsonStr=jsonStr+"{\"id\":\""+strs[j]+"\",\"content\":\"该项不能为空\"},";
            }
        }
    }
    /*if(xtMap!=null){
        xtMap.forEach(function (value, key, map) {
            var strs= new Array(); //定义一数组
            strs=value.split(","); //字符分割
            for ( var j = 0; j < strs.length; j++) {
                if($("#"+str[j]).val() !=""){
                    jsonStr=jsonStr+"{\"id\":\""+strs[j]+"\",\"content\":\"请把该项资料填写完整\"},";
                }
            }
        })
    }*/
}

//提交
function commit(type){
        $.ajax({
            type: "POST",
            url: contextpath + 'material/doMaterialAdd.action?sjump=2&type=' + type,
            data: $('#objForm').serialize(),// 您的formid
            async: false,
            success: function (json) {
                if (json.msg == 'OK') {
                    window.message.success("操作成功,正在跳转页面");
                    /**企业微信消息**/
                    /*if (json.org_name=="人民卫生出版社") {
                    	var exportWordBaseUrl = "http://"+remoteUrl+"/pmpheep";
                    	$.ajax({
                            type: 'get',
                            url: exportWordBaseUrl + '/frontWxMsg/projectEditorPleaseAdit/'+json.declaration_id,
                            dataType: 'jsonp',
                            jsonp:"callback", //这里定义了callback在后台controller的的参数名
                			jsonpCallback:"getMessage", //这里定义了jsonp的回调函数名。 那么在后台controller的相应方法其参数“callback”的值就是getMessage
                            success:function(wxResult){
                            	if(wxResult=="1"){
                            		window.message.success("微信消息发送成功");
                            		setTimeout(function(){
                            			window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=jcsb";
                            		},800);
                            	}
                            	
                            },
                            error:function(XMLHttpRequest, textStatus){
                            	//console.log("error "+wxResult);
                            	setTimeout(function(){
                        			window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=jcsb";
                        		},800);
                            }
                    		
                            });
					}else{
						setTimeout(function(){
                			window.location.href = contextpath + "personalhomepage/tohomepage.action?pagetag=jcsb";
                		},800);
					}*/
                    window.location.href = contextpath + "expertation/declare.action";
                }
            }
        });
}
//放弃
function buttGive(){
    window.location.href=contextpath+"personalhomepage/tohomepage.action?pagetag=jcsb";
}
/**
 * 表单校验方法
 */
//必填校验  layer.tips('默认就是向右的', '#id或者.class');
function toisNah(content,id){
    var value = $("#"+id).val();
    if(value == ""){
        layer.tips(content, '#'+id);
        $("#"+id)[0].focus();  //聚焦
    }
}

//固定电话号码校验
function checkTel(id){
    var value = $("#"+id).val();
    if(!/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(value)){
        layer.tips('固定电话有误，请重填', '#'+id);
        $("#"+id)[0].focus();  //聚焦
        return false;
    }
}

//手机号码校验
function checkHandphone(id){
    var value = $("#"+id).val();
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(value))){
        layer.tips('手机号码有误，请重填', '#'+id);
        $("#"+id)[0].focus();  //聚焦
        return false;
    }
}
//身份证号码校验
function checkIdCard(id){
    var num = $("#"+id).val();
    if ( !(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)) ){
        layer.tips('身份证号码错误，请重填', '#'+id);
        $("#"+id)[0].focus();  //聚焦
        return false;
    }
}

//非空验证   首先获取 非空模块的值进行非空 判断，必填模块通过方法获取ID进行判断
function checkNull(jsonStr){
    var s = "["+jsonStr.substring(0, jsonStr.length-1)+"]";
    var objs = $.parseJSON(s);
    var b = true;
    $.each(objs, function(k, obj){
        var value = $("#"+obj.id).val();
        if(obj.id=="zjlx"){ //判断是否为身份证
            if($("input[name='idtype']").val() == '0'){
                var num = $("#idcard").val();
                if ( !(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num)) ){
                    layer.tips('身份证号码错误，请重填', '#idcard');
                    $("#idcard")[0].focus();  //聚焦
                    b = false;
                    return false;
                }
            }
        }else if(obj.id=="handphone"){ //手机号码
            var num = $("#handphone").val();
            if(!(/^1(3|4|5|7|8)\d{9}$/.test(num))){
                layer.tips('手机号码有误，请重填', '#handphone');
                $("#handphone")[0].focus();  //聚焦
                b = false;
                return false;
            }
        }else if(/^pmph_isbn_?.*/.test(obj.id)){
        	if (!check_pmph_isbn(obj.id)) {
        		b = false;
        		return false;
			}
        }else if(/^jcb_isbn_?.*/.test(obj.id)){
        	if (!check_jcb_isbn(obj.id)) {
        		b = false;
        		return false;
			}
        }else if(value == ""){
            layer.tips(obj.content, '#'+obj.id);
            $("#"+obj.id)[0].focus();  //聚焦2
            b = false;
            window.message.warning(obj.content);
            return false;
        }
    });
    return b;
}


/**
 * 人卫社教材编写情况的标准书号，校验标准按照：978-7-117-*****-*的格式去校验，且默认加载出978-7-117-或者978-7-117-写定；
 */
function check_pmph_isbn(id){
	/*var num = $("#"+id).val();
	if(num != undefined){
		if (is_pmph_textbook_required==1 && $.trim(num)=="") {
			layer.tips("教材标准书号不能为空", '#'+id);
	        $("#"+id)[0].focus();  //聚焦2
	        b = false;
	        window.message.warning("教材标准书号不能为空");
	        return false;
		}else if($.trim(num)!="" && !(/^978-7-117-(\d|[A-z])+$/.test(num))){
	        layer.tips('教材标准书号格式：978-7-117-********* ,*代表数字或字母', "#"+id);
	        $("#"+id)[0].focus();  //聚焦
	        b = false;
	        return false;
	    }
	}	*/
	return true;
}
/**
 * 其他社教材编写情况的标准书号，校验标准按照：978-7-*********的格式去校验，且默认加载出978-7-或者978-7-写定；
 */
function check_jcb_isbn(id){
	/*var num = $("#"+id).val();
	if(num != undefined){
		if (is_textbook_required==1 && $.trim(num)=="") {
			layer.tips("教材标准书号不能为空", '#'+id);
	        $("#"+id)[0].focus();  //聚焦2
	        b = false;
	        window.message.warning("教材标准书号不能为空");
	        return false;
		}else if($.trim(num)!="" && !(/^978-7-(\d|[A-z])+$/.test(num))){
	        layer.tips('教材标准书号格式：978-7-********* ,*代表数字或字母', "#"+id);
	        $("#"+id)[0].focus();  //聚焦
	        b = false;
	        return false;
	    }
	}	*/
	return true;
}

//机构选择
function orgAdd(material_id){
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'申报单位选择',
        maxmin: true,
        content: contextpath+"material/toSearchOrg.action?material_id="+material_id
    });
}

//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){

    var va = obj.value;
    var vat = "";
    for ( var i = 1; i <= va.length; i++) {
        vat = va.substring(0,i);
        //把双字节的替换成两个单字节的然后再获得长度，与限制比较
        if (vat.replace(/[^\x00-\xff]/g,"aaa").length <= ml) {
            maxStrlength=i;
        }else{

            break;
        }
    }
    obj.maxlength=maxStrlength;
    //把双字节的替换成两个单字节的然后再获得长度，与限制比较
    if (va.replace(/[^\x00-\xff]/g,"aaa").length > ml) {
        obj.value=va.substring(0,maxStrlength);
        //window.message.warning("不可超过输入最大长度"+ml+"字节！");
    }
}

//根据name判断获取的值是否有重复的
function checkEqual(name){
    //获取name属性的对象数组(节点数组)
    var map = $('input[name^="textbook_id"]').map(
        function(){return this.value
        }).get();
    //遍历数组并比较是否存在相同值
    var nary=map.sort();
    for(var i=0;i<map.length;i++){
        if(nary[i] == ""){
            window.message.warning("申报书籍不能为空，请选择书籍");
            return false;
        }
        if (nary[i]==nary[i+1]){
            window.message.warning("不能选择相同书籍!请重新选择书籍");
            return false;
        }
    }
    return true;
}

//验证扩展项必填
function checkExtra(){
    var a=document.getElementsByName("zjkzxx");
    var b;
    var c;
    for(var i=0;i<a.length;i++){
        b=a[i].value;
        c=b.split("_");
        if(c[0]=="true"){
            $('#true_'+(i+1)).tipso({validator: "isNonEmpty", message: "拓展项必填"});
        }
    }
}
//列表填报校验
function checkLb(){
    var map = $('input[name^="zdjy"]').map(
        function(){return this.value
        }).get();
    if(map!=null){
        for(var i=0;i<map.length;i++){
            var strs= new Array(); //定义一数组
            strs=map[i].split(","); //字符分割
            //遍历
            for ( var j = 0; j < strs.length; j++) {
                jsonStr=jsonStr+"{\"id\":\""+strs[j]+"\",\"content\":\"该项不能为空\"},";
            }
        }
    }
}

//文件下载
function downLoadProxy(fileId){
    window.location.href=contextpath+'file/download/'+fileId+'.action';
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}



//添加学科分类span标签元素
function addSubject(str){
    var box = document.getElementById("xkfladd");
    box.innerHTML = box.innerHTML+str;
}
//添加内容分类span标签元素
function addContent(str){
    var box = document.getElementById("nrfladd");
    box.innerHTML = box.innerHTML+str;
}

//打印
function toprint(){
    $(".yijian").css("display","block");
    $("#button_cz").css("display","none");
    $("#ifprint").jqprint();
    $(".yijian").css("display","none");
    $("#button_cz").css("display","block");
}

//学科选择
function SubjectdAdd(material_id){
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'学科分类选择',
        maxmin: true,
        content: contextpath+"expertation/querySubject.action?material_id="+material_id
    });
}

//内容选择
function ContentAdd(material_id){
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'内容分类选择',
        maxmin: true,
        content: contextpath+"expertation/queryContent.action?material_id="+material_id
    });
}