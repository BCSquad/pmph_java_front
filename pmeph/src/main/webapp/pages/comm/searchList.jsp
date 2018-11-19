<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--调研表-->
<div style="margin-top: 20px">
    <div class="txdyb">（ 温馨提醒: 根据您申报的书籍，需要填写以下<font style="color: red">标记*</font>的调研表才能提交, 如果没有请忽略。<font style="color: red">填写调研表前请先暂存申报表, 防止内容丢失。</font>)</div>
    <div id="jcdyb"></div>
    <div id="tsdyb"></div>
</div>
</body>
</html>
<script>
    //查询教材对应调研表
    function querydyb() {
        var id = $("#material_id").val();
        $.ajax({
            type: "POST",
            url:contextpath+'research/querySearch.action',
            data:{material_id:id},// 您的formid
            async : false,
            success: function(json) {
                var str='';
                $.each(json,function (i,n) {
                    var c=i+1;
                    num=json.length;
                    str+='<div style="margin-top: 5px">\n' ;
                    if(n.required_for_material){
                        str+='<div style="float: left;"><font color="red">*</font>'+c+').'+n.title+'</div>\n' ;
                    }else{
                        str+='<div style="float: left;">'+c+').'+n.title+'</div>\n' ;
                    }
                    if(n.gmt_create!=null && n.gmt_create!=''){
                        str+='<div style="float: left;color: #23527C;margin-left: 10px">'+
                            '(已填)</div>\n'+
                            '<div class="wrt">' +
                            '<img src="'+contextpath+'statics/image/tobb.png" style="background-size: 100%;width: 100%" onclick="tolook('+n.id+')">' +
                            '</div>\n' +
                            '</div>';
                    }else{
                        if(n.required_for_material){
                            sta=false;
                        }
                        str+='<div style="float: left;color: #23527C;margin-left: 10px" onclick="toinsert('+n.id+')">'+
                            '(未填)</div>\n'+
                            '<div class="wrt">' +
                            '<img src="'+contextpath+'statics/image/tobb.png" style="background-size: 100%;width: 100%" onclick="toinsert('+n.id+')">' +
                            '</div>\n' +
                            '</div>';
                    }
                    str+='<div style="clear: both"></div>';
                });
                $("#jcdyb").html(str);
            }
        });
    }

    //查询书籍对应调研表
    function querySearchByTextbookId() {
        sta=true;
        var ber= num;
        var ids=document.getElementsByName("textbook_id");
        var str=[];
        for(var i=0;i<ids.length;i++){
            str.push(ids[i].value);
        }
        $.ajax({
            type: "POST",
            url:contextpath+'research/querySearchByTextbookId.action',
            data:{
                textbook_id:JSON2.stringify(str)
            },
            async : false,
            success: function(json) {
                var str='';
                $.each(json,function (i,n) {
                    ber=ber+1;
                    str+='<div style="margin-top: 5px">\n' ;
                    if(n.required){
                        str+='<div style="float: left;"><font color="red">*</font>'+ber+').'+n.title+'</div>\n' ;
                    }else{
                        str+='<div style="float: left;">'+ber+').'+n.title+'</div>\n' ;
                    }
                    if(n.gmt_create!=null && n.gmt_create!=''){
                        str+='<div style="float: left;color: #23527C;margin-left: 10px">'+
                            '(已填)</div>\n'+
                            '<div class="wrt">' +
                            '<img src="'+contextpath+'statics/image/tobb.png" style="background-size: 100%;width: 100%" onclick="tolook('+n.id+')">' +
                            '</div>\n' +
                            '</div>';
                    }else{
                        if(n.required){
                            sta=false;
                        }
                        str+='<div style="float: left;color: #23527C;margin-left: 10px" onclick="toinsert('+n.id+')">'+
                            '(未填)</div>\n'+
                            '<div class="wrt">' +
                            '<img src="'+contextpath+'statics/image/tobb.png" style="background-size: 100%;width: 100%" onclick="toinsert('+n.id+')">' +
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