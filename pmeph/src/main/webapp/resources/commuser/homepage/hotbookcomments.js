$(function(){
	var startnum= parseInt($("#pagenum").val());
	var  pagetotal=parseInt($("#pagetotal").val());
	Page({
        num:pagetotal,					
        startnum: startnum,
        elem:$("#page1"),
        callback: function (n) {
          var size=$("input[name='edu']").val();

           location.href=contextpath+"homepage/morecomments.action?pagenum="+n+"&&size="+size;
        }
 });
$('select').selectlist({
    zIndex: 10,
    width: 110,
    height: 30,
    optionHeight: 30,
    onChange: function () {
    	var size=$("input[name='edu']").val();
    	location.href=contextpath+"homepage/morecomments.action?pagenum=1&&size="+size;
    }
});
	
});