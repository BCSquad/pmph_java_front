
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
    console.log(json);
    return json;

}
function save(){
    $.ajax({
        type:'post',
        url:contxtpath+'/admininfocontroller/updateorguser.action',
        async:false,
        contentType: 'application/json',
        dataType:'json',
        data:JSON2.stringify(getform()),
        success:function(responsebean){
            if (responsebean.code==1){
                message.success("保存成功");
            }
        }
    });
}