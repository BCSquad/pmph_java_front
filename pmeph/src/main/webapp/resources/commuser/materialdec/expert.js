//定义一个全局变量
 var jsonStr = "";
 var is_unit_advise_used = "0";
 var expertMap;
 var expertMapforNewVali;
 
$(function () {
    setTimeout(function () {
        $('#realname').tipso({validator: "isNonEmpty", message: "姓名不能为空"});
        $('#birthday').tipso({validator: "isNonEmpty", message: "出生日期不能为空"});
        $('#org_name').tipso({validator: "isNonEmpty", message: "工作单位不能为空"});
        $('#position').tipso({validator: "isNonEmpty", message: "职务不能为空"});
        $('#zc').tipso({validator: "isNonEmpty", message: "职称不能为空"});
        $('#email').tipso({validator: "isNonEmpty|isEmail", message: "邮箱不能为空|邮箱格式不正确"});
        $('#handphone').tipso({validator: "isNonEmpty|isMobile", message: "手机号码不能为空|手机号码格式不正确"});
        $('#zjlx').tipso({validator: "isNonEmpty", message: "证件类型不能为空"});
        $('#idcard').tipso({validator: "isNonEmpty", message: "证件号码不能为空"});
        $('#address').tipso({validator: "isNonEmpty", message: "地址不能为空"});
        //checkExtra();
    },0)

   // setTimer();
    var expert_type = $("#expert_type").val();
    var id = $("#product_id").val();
    /*setTimeout(function (){
        upload();
    },1000);*/
     //附件上传
    queryMaterialMap(id);  //执行查询方法
    

    $('.select-input').selectlist({
        zIndex: 10,
        width: 192,
        height: 30,
        optionHeight: 30
    });
    $('#zclx').selectlist({
        width: 192,
        height: 30,
        optionHeight: 30
    });
    $('#education').selectlist({
        zIndex: 10,
        width: 192,
        height: 30,
        optionHeight: 30
    });
    $('#pmph_rank').selectlist({
        width: 110,
        height: 30,
        optionHeight: 30
    });
    $('#pmph_position').selectlist({
        width: 110,
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

window.onload = function(){
	$("input[calendar]").each(function(i,n){
    	var $t = $(n);
    	$t.trigger("timeChange",new Date($t.val()));
    });
};

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
            $("#fileNameDiv").append("<span><div class=\"filename whetherfile\"><a href='javascript:' class='filename'  onclick='downLoadProxy("+fileid+")' title='\"+filename+\"'>"+filename+"</a></div></span>");
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
        data:{product_id:id},// 您的formid
        dataType:"json",
        success: function(json) {
            //chooseModel(json);
        	expertMapforNewVali = json;
        	usedAndRequired(expertMapforNewVali);  
            //expertMap = json;
        }
    });
}

/**
 * 可配置项的显示及校验初始化
 * @param data
 */
function usedAndRequired(data){
	$("div[wrapper_key] input").tipso("destroy");
	$("textarea[name='kz_content']").tipso("destroy");
	$("div[wrapper_key]").each(function(){
		var $d = $(this);
		if(data[$d.attr("wrapper_key")+"_used"]){
			$d.show();
			if(data[$d.attr("wrapper_key")+"_required"]){
				$d.find(".tsxz_ts").show();
				$d.find("td input[type!='select'][type!='radio'][type!='checkbox'][placeholder!='备注'][type!='hidden']").each(function(){
					var $in = $(this);
					var message =$in.attr("placeholder")+"必填";
					$in.tipso({validator: "isNonEmpty", message: message});
				});
			}
		}
	});
	checkExtra();
	typeUseRequired(data);
}

/**
 * 分类的校验
 */
function typeUseRequired(data){
	//学科分类
    if(data.is_subject_type_used == "1"){
        $("#xkfl_qy").css("display","block");
        //主编学术专著情况必填
        if(data.is_subject_type_required != "1"){
            $("#xkflbx").attr("style","display:none");
        }else{
            $("#xkflbt").val("yes");
        }

    }else{
        $("#xkfl_qy").css("display","none");
    }

    //内容分类
    if(data.is_content_type_used == "1") {
        $("#nrfl_qy").css("display", "block");
        //主编学术专著情况必填
        if (data.is_content_type_required != "1") {
            $("#nrflbx").attr("style", "display:none");
        }else{
            $("#nrflbt").val("yes");
        }
    }else{
        $("#nrfl_qy").css("display", "none");
    }

    //申报专业
    if(data.is_profession_type_used == "1") {
        $("#sbzy_qy").css("display", "block");
        //主编学术专著情况必填
        if (data.is_profession_type_required != "1") {
            $("#sbzybx").attr("style", "display:none");
        }else{
            $("#sbzybt").val("yes");
        }
    }else{
        $("#sbzy_qy").css("display", "none");
    }
};


//模块显示与隐藏判断
function chooseModel(data){
    //所在单位意见
    if(data.is_unit_advise_used == "1"){
        $("#szdwyj").css("display","block");
        is_unit_advise_used = data.is_unit_advise_used;
    }
    //学习经历
    if(data.is_edu_exp_used == "1"){
        $("#zyxxjl").css("display","block");
        //学习经历必填
        if(data.is_edu_exp_required == "1"){
            $("#zyxxjl_bt").css("display","inline");
            jsonStr=jsonStr+"{\"id\":\"xx_kssj\",\"content\":\"学习经历起止时间必填\"},";
            
            tipsoAllByName("xx_kssj","isNonEmpty","学习开始时间必填");
            tipsoAllByName("xx_jssj","isNonEmpty","学习结束时间必填");
            
            /*$('#xx_kssj').tipso({validator: "isNonEmpty", message: "学习开始时间必填"})
            $('#xx_jssj').tipso({validator: "isNonEmpty", message: "学习结束时间必填"})*/
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
            jsonStr=jsonStr+"{\"id\":\"gz_kssj\",\"content\":\"工作经历必填\"},";
            tipsoAllByName("gz_kssj","isNonEmpty","工作开始时间必填");
            tipsoAllByName("gz_jssj","isNonEmpty","工作结束时间必填");
            /*$('#gz_kssj').tipso({validator: "isNonEmpty", message: "工作开始时间必填"})
            $('#gz_jssj').tipso({validator: "isNonEmpty", message: "工作结束时间必填"})*/
            //给其他值默认为无
            $("#gz_jssj").val(getNowFormatDate());
            $("#gz_org_name").val("无");
            $("#gz_position").val("无");
        }else{
            $("#gzjl_xt").css("display","inline");
        }
    }
    //主要学术兼职
    if(data.is_acade_used == "1"){
        $("#xsjz").css("display","block");
        //主要学术兼职必填
        if(data.is_acade_required == "1"){
            $("#xsjz_bt").css("display","inline");
            jsonStr=jsonStr+"{\"id\":\"xs_org_name\",\"content\":\"学术兼职必填\"},";
            tipsoAllByName("xs_org_name","isNonEmpty","学术兼职必填");
            /*$('#xs_org_name').tipso({validator: "isNonEmpty", message: "学术兼职必填"})*/
            //给其他值默认为无
            $("#xs_position").val("无");
        }else{
            $("#xsjz_xt").css("display","inline");
        }
    }
    //人卫教材编写情况
    if(data.is_pmph_textbook_used == "1"){
        $("#rwsjcbx").css("display","block");
        //人卫教材编写情况必填
        if(data.is_pmph_textbook_required == "1"){
            $("#rwsjcbx_bt").css("display","inline");
            jsonStr=jsonStr+"{\"id\":\"pmph_material_name\",\"content\":\"人卫社教材编写情况必填\"},";
            tipsoAllByName("pmph_material_name","isNonEmpty","人卫社教材编写情况必填");
            /*$('#pmph_material_name').tipso({validator: "isNonEmpty", message: "人卫社教材编写情况必填"})*/
            //给其他值默认为无
            $("#pmph_publish_date").val(getNowFormatDate());
            $("#pmph_isbn").val("标准书号");
        }else{
            $("#rwsjcbx_xt").css("display","inline");
        }
    }
    is_pmph_textbook_required = data.is_pmph_textbook_required;
    //主编学术专著情况
    if(data.is_monograph_used == "1"){
        $("#zbxszz").css("display","block");
        //主编学术专著情况必填
        if(data.is_monograph_required == "1"){
            $("#zbxszz_bt").css("display","inline");
            jsonStr=jsonStr+"{\"id\":\"zb_monograph_name\",\"content\":\"专著名称必填\"},";
            tipsoAllByName("zb_monograph_name","isNonEmpty","专著名称必填");
            /*$('#zb_monograph_name').tipso({validator: "isNonEmpty", message: "专著名称必填"})*/
            //给其他值默认为无
            $("#zb_monograph_date").val(getNowFormatDate());
            $("#zb_publish_date").val(getNowFormatDate());
            $("#zb_publisher").val("无");
        }else{
            $("#zbxszz_xt").css("display","inline");
        }
    }
    //主编或参编图书情况
    if(data.is_edit_book_used == "1"){
        $("#zbcbtsqk").css("display","block");
        //主编学术专著情况必填
        if(data.is_edit_book_required == "1"){
            $("#zbcb_bt").css("display","inline");
            tipsoAllByName("zbts_material_name","isNonEmpty","专著名称必填");
            /*$('#zbts_material_name').tipso({validator: "isNonEmpty", message: "专著名称必填"})*/
            //给其他值默认为无
            $("#zbts_publish_date").val(getNowFormatDate());
            $("#zbts_publisher").val("无");
        }else{
            $("#zbcb_xt").css("display","inline");
        }
    }

    //文章发表情况
    if(data.is_article_published_used == "1"){
        $("#wzfbqk").css("display","block");
        //主编学术专著情况必填
        if(data.is_article_published_required == "1"){
            $("#wzfbqk_bt").css("display","inline");
            $('#wzfbqk_material_name').tipso({validator: "isNonEmpty", message: "专著名称必填"})
        }else{
            $("#wzfbqk_bt").css("display","none");
            $("#wzfbqk_xt").css("display","inline");
        }
    }

    //本专业获奖情况
    if(data.is_profession_award_used == "1"){
        $("#bzyhjqk").css("display","block");
        //主编学术专著情况必填
        if(data.is_profession_award_required == "1"){
            $("#bzyhjqk_bt").css("display","inline");
            $('#bzyhjqk_material_name').tipso({validator: "isNonEmpty", message: "专著名称必填"})
        }else{
            $("#bzyhjqk_bt").css("display","none");
            $("#bzyhjqk_xt").css("display","inline");
        }
    }


    //学科分类
    if(data.is_subject_type_used == "1"){
        $("#xkfl_qy").css("display","block");
        //主编学术专著情况必填
        if(data.is_subject_type_required != "1"){
            $("#xkflbx").attr("style","display:none");
        }else{
            $("#xkflbt").val("yes");
        }

    }else{
        $("#xkfl_qy").css("display","none");
    }

    //内容分类
    if(data.is_content_type_used == "1") {
        $("#nrfl_qy").css("display", "block");
        //主编学术专著情况必填
        if (data.is_content_type_required != "1") {
            $("#nrflbx").attr("style", "display:none");
        }else{
            $("#nrflbt").val("yes");
        }
    }else{
        $("#nrfl_qy").css("display", "none");
    }

    //申报专业
    if(data.is_profession_type_used == "1") {
        $("#sbzy_qy").css("display", "block");
        //主编学术专著情况必填
        if (data.is_profession_type_required != "1") {
            $("#sbzybx").attr("style", "display:none");
        }else{
            $("#sbzybt").val("yes");
        }
    }else{
        $("#sbzy_qy").css("display", "none");
    }
}

/**
 * 给所有当前已存在的某name的input新增validStr的tipso校验，提示为message
 */
function tipsoAllByName(name,validStr,message){
	$("input[name='"+name+"']").each(function(){
		var $t = $(this);
		$t.tipso({validator: validStr, message: message});
	});
};




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
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xxjl_"+num+"')\"/></td>"
    );
    $table.append($tr);
    $tr.calendar();
    if(expertMap.is_edu_exp_required == "1"){
	    $('#xx_kssj_'+num).tipso({validator: "isNonEmpty", message: "学习开始时间必填"});
	    $('#xx_jssj_'+num).tipso({validator: "isNonEmpty", message: "学习结束时间必填"});
    }
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
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('gzjl_"+num+"')\"/></td>"
    );
    $table.append($tr);
    $tr.calendar();
    if(expertMap.is_work_exp_required == "1"){
	    $('#gz_kssj_'+num).tipso({validator: "isNonEmpty", message: "工作开始时间必填"});
	    $('#gz_jssj_'+num).tipso({validator: "isNonEmpty", message: "工作开始时间必填"});
    }
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
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('xsjz_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    if(expertMap.is_acade_required == "1"){
    	$('#xs_org_name_'+num).tipso({validator: "isNonEmpty", message: "学术兼职必填"});
    }
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
        "<td><input class='cg_input' maxlength='50' name='pmph_isbn' value='' id='pmph_isbn_"+num+"'  style='width: 100px;' placeholder='标准书号'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='pmph_note' value='' placeholder='备注' style='width: 140px;'/>" +
        "<input type='hidden' name='zdjy' value='pmph_material_name_"+num+"' />" +
        "<input type='hidden' name='pmph_id' value=''>"+
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
    if(expertMap.is_pmph_textbook_used == "1"){
    	$('#pmph_material_name_'+num).tipso({validator: "isNonEmpty", message: "人卫社教材编写情况必填"});
    }
}

//主编或参编图书情况
function add_zbtsqk(){
    var num = fnt();
    var $table = $("#tab_zbtsqk");
    var $tr = $("<tr id='zbtsqk_"+num+"'>"+
        "<td><input class='cg_input' maxlength='100' name='zbts_material_name' id='zbts_material_name_"+num+"' value='' style='width: 320px;' placeholder='教材名称'/></td>"+
        "<td><input class='cg_input' maxlength='50' name='zbts_publisher' id='zbts_publisher_"+num+"' value='' style='width: 300px;' placeholder='出版社'/></td>"+
        "<td><input class='cg_input' placeholder='出版时间' id='zbts_publish_date_"+num+"' calendar format=\"'yyyy-mm-dd'\"  z-index='100' name='zbts_publish_date' value='' style='width: 130px;'/></td>"+
        "<td><input class='cg_input' maxlength='100' name='zbts_note' value='' placeholder='备注' style='width:240px;'/>" +
        "<input type='hidden' name='zdjy' value='zbts_material_name_"+num+"' />" +
        "<input type='hidden' name='zbts_id' value=''>"+
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zbtsqk_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
    if(expertMap.is_edit_book_required == "1"){
    	$('#zbts_material_name_'+num).tipso({validator: "isNonEmpty", message: "专著名称必填"});
    }
}

//文章发表情况
function add_wzfbqk() {
    var num = fnt();
    var $table = $("#tab_wzfbqk");
    var $tr=$("<tr id='wzfbqk_"+num+"'>\n" +
        "<input type='hidden' name='wzfbxq_id' value=''>"+
        "<td class=\"xztd\"><input class=\"cg_input xzip\" maxlength=\"100\"  id=\"wzfbqk_material_name_"+num+"\" name=\"wzfb_name\" value=\"\" placeholder=\"文章题目\"/></td>\n" +
        "<td class=\"xztd\"><input class=\"cg_input xzip\" name=\"wzfb_qkmc\" value=\"\"  maxlength=\"20\" placeholder=\"期刊名称\"/></td>\n" +
        "<td class=\"xztd\"><input class=\"cg_input xzip\" name=\"wzfb_njq\" value=\"\" placeholder=\"年、卷、期\"/></td>\n" +
        "<td class=\"xztd\"><input class=\"cg_input xzip\" maxlength=\"100\" name=\"wzfb_qklb\" value=\"\"  placeholder=\"期刊级别（SCI或国内核心期刊）\"/></td>\n" +
        "<td class=\"xztd\"><input class=\"cg_input xzip\" maxlength=\"100\" name=\"wzfb_note\" value=\"\"  placeholder=\"备注\"/></td>\n" +
        "<td class=\"xztd\"><img class=\"add_img\" src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('wzfbqk_"+num+"')\"/></td>\n" +
        "</tr>");
    $table.append($tr);
    $tr.calendar();
    if(expertMap.is_article_published_required == "1"){
        $('#wzfbqk_material_name_'+num).tipso({validator: "isNonEmpty", message: "文章发表情况必填"});
    }
}

//本专业获奖情况
function add_bzyhjqk() {
    var num = fnt();
    var $table = $("#tab_bzyhjqk");
    var $tr=$("<tr id='bzyhjqk_"+num+"'>\n" +
        "<input type='hidden' name='bzyhqqk_id' value=''>"+
        "<td class=\"xztd\"><input class=\"cg_input xzip\" maxlength=\"100\"  id=\"bzyhjqk_material_name_"+num+"\" name=\"hjqk_name\"  value=\"\" placeholder=\"名称\"/></td>\n" +
        "<td class=\"xztd\"><input class=\"cg_input xzip\" maxlength=\"100\" name=\"hjqk_jb\" value=\"\"  placeholder=\"级别（国家、省、市、单位）\"/></td>\n" +
        "<td class=\"xztd\"><input class=\"cg_input xzip\" maxlength=\"100\" name=\"hjqk_note\" value=\"\"  placeholder=\"备注\"/></td>\n" +
        "<td class=\"xztd\"><img class=\"add_img\" src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('bzyhjqk_"+num+"')\"/></td>\n" +
        "</tr>");
    $table.append($tr);
    $tr.calendar();
    if(expertMap.is_profession_award_required == "1"){
        $('#bzyhjqk_material_name_'+num).tipso({validator: "isNonEmpty", message: "本专业获奖情况必填"});
    }
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
        "</td>"+
        "<td><img class='add_img' src='"+contextpath+"statics/image/del.png' onclick=\"javascript:del_tr('zbxszz_"+num+"')\"/></td>"+
        "</tr>");
    $table.append($tr);
    $tr.calendar();
    if(expertMap.is_monograph_required == "1"){
    	$('#zb_monograph_name_'+num).tipso({validator: "isNonEmpty", message: "专著名称必填"});
    }
}

//删除表格tr
function del_tr(trId){
	$("#"+trId).find("input").each(function(){
		var $t = $(this);
		$t.tipso("destroy");
	});
    document.getElementById(trId).remove();
}


//提交   类型1 表示提交  2 表示暂存
function buttAdd(type){
            if(type == '2') { //表示暂存
                //避免重复点击
                //    document.getElementById('buzc').onclick=function(){window.message.warning("请不要重复点击");};
                //    document.getElementById('butj').onclick=function(){window.message.warning("请不要重复点击");};
                $.ajax({
                    type: "POST",
                    url:contextpath+'expertation/doExpertationAdd.action?sjump=1&type='+type,
                    data:$('#objForm').serialize(),// 您的formid
                    async: false,
                    success: function (json) {
                        if (json.msg == 'OK') {
                            window.message.success("操作成功,正在跳转页面");
                            window.location.href = contextpath + "expertation/declare.action";
                        }
                    }
                });
            }else {  //表示提交
            	usedAndRequired(expertMapforNewVali);  
                checkLb();
                    if ($.fireValidator() ) {
                                if(!$("#xkfladd").children().hasClass("el-tag")&&($("#xkflbt").val()=="yes")){
                                    window.message.info("请选择学科分类！");
                                    return ;
                                }else if(!$("#nrfladd").children().hasClass("el-tag")&&($("#nrflbt").val()=="yes")){
                                    window.message.info("请选择内容分类！");
                                    return ;
                                }else if(!$("#sbzyadd").children().hasClass("el-tag")&&($("#sbzybt").val()=="yes")){
                                    window.message.info("请选择申报专业！");
                                    return ;
                                }
                            	if($("#sbdw_id").val()){
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
                            	}else{
                            		window.message.info("请选择申报单位！");
                            	}
                            }
                    }

            //  }
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
	var num = $("#"+id).val();
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
	}	
	return true;
}
/**
 * 其他社教材编写情况的标准书号，校验标准按照：978-7-*********的格式去校验，且默认加载出978-7-或者978-7-写定；
 */
function check_jcb_isbn(id){
	var num = $("#"+id).val();
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
	}	
	return true;
}

//学科选择
function SubjectdAdd(expert_type){
var chooseArr = [];
	
	$("input[name='subjectId']").each(function(){
		var $t = $(this);
		chooseArr.push($t.val());
	});
	var chooseId = chooseArr.toString()
		.replace(/\[/g, '(')
		.replace(/\]/g, ')').replace(/"/g, "");
	
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'学科分类选择',
        maxmin: true,
        content: contextpath+"expertation/querySubject.action?product_type="+expert_type+"&chooseId="+chooseId
    });
}

//内容选择
function ContentAdd(product_type){
	var chooseArr = [];
	
	$("input[name='contentId']").each(function(){
		var $t = $(this);
		chooseArr.push($t.val());
	});
	var chooseId = chooseArr.toString()
		.replace(/\[/g, '(')
		.replace(/\]/g, ')').replace(/"/g, "");
	
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'内容分类选择',
        maxmin: true,
        content: contextpath+"expertation/queryContent.action?product_type="+product_type+"&chooseId="+chooseId
    });
}

//申报专业选择
function sbzyAdd(product_type){
	var chooseArr = [];
	$("input[name='sbzyId']").each(function(){
		var $t = $(this);
		chooseArr.push($t.val());
	});
	var chooseId = chooseArr.toString()
		.replace(/\[/g, '(')
		.replace(/\]/g, ')').replace(/"/g, "");
	
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'申报专业选择',
        maxmin: true,
        content: contextpath+"expertation/toSearchZy.action?product_type="+product_type+"&chooseId="+chooseId
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

//单选
function tocheckbox(){
    $("input[name='tsxzq']").click(function(){
        //alert($(this).attr("checked"));
        if($(this).attr("checked") == undefined){ //如果不是checked状态，则变为checked状态，且清除checked状态
            //清除其他checked状态
            $("input[name='tsxzq']").attr("checked",false);
            $(this).attr('checked', 'checked');
        }else{
            //清除其他checked状态
        }
    });
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

//定时器自动提交

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

//判断radio是否被选中
function radioInfo(){
    //  alert(document.getElementById("zw_1").checked);
    var els =document.getElementsByName("preset_position");
    for (var i = 0, j = els.length; i < j; i++) {
        var zw = els[i].value;
        var list = $('input:radio[name='+zw+']:checked').val();
        if (list == null) {
            window.message.warning("请选择申报的职位！");
            return false;
        }
    }
    return true;
}

//删除
function del(id){
    $('#'+id).remove();
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

//添加申报专业span标签元素
function addSbzy(str){
    var box = document.getElementById("sbzyadd");
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

//机构选择
function orgAdd(product_id){
    layer.open({
        type: 2,
        area: ['800px', '600px'],
        fixed: false, //不固定
        title:'申报单位选择',
        maxmin: true,
        content: contextpath+"expertation/toSearchOrg.action?product_id="+product_id
    });
}

