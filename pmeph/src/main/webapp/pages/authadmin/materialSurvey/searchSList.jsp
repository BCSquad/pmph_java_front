<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .dybtitle{
        letter-spacing: 0px;
        font-weight: normal;
        font-stretch: normal;
        font-weight: bold;
        color: #333333;

    }
</style>
<body>
<div style="margin-top: 20px" >
    <div class="dybtitle"><img src="/statics/image/txdyb.png" style="width: 20px;
	height: 20px;"> &nbsp; &nbsp;调研表
    </div>
    <div class="txdyb"></div>
    <div id="jcdyb"></div>
    <div id="tsdyb"></div>
</div>
</body>
</html>
<script>
    //查询书籍对应调研表
    function querySearchByTextbookId() {
        sta=true;

        var str=[];
            <c:forEach var="list" items="${tssbList}">
        str='${list.textbook_id}';
            </c:forEach>


        $.ajax({
            type: "POST",
            url:contextpath+'orgSurvey/querySearchByTextbookId.action',
            data:{
                textbook_id:JSON.stringify(str)
            },
            async : false,
            success: function(json) {
                var str='';
                $.each(json,function (i,n) {
                    let ber=i+1;
                    str+='<div style="margin-top: 5px">\n' ;

                    if(n.gmt_create!=null && n.gmt_create!=''){
                        if(n.required){
                            str+='<div style="float: left;"><font color="red">*</font>'+ber+').'+n.title+'</div>\n' ;
                        }else{
                            str+='<div style="float: left;">'+ber+').'+n.title+'</div>\n' ;
                        }
                        str+='<div style="float: left;color: #23527C;margin-left: 10px">'+
                            '(已填)</div>\n'+
                            '<div class="wrt">' +
                            '<img src="${ctx}/statics/image/tobb.png" style="background-size: 15px;width: 15px" onclick="tolook('+n.id+')">' +
                            '</div>\n' +
                            '</div>';
                    }
                    str+='<div style="clear: both"></div>';
                });
                $("#tsdyb").html(str);
            }
        });
    }
</script>