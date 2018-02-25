/**
 * Created by lihuan on 2018/1/17.
 */
$(function () {

    var $menu = $("<div class='float-menu '>" +
        "<a class='item' href='" + contextpath + "personalhomepage/tohomepage.action?pagetag=jcsb'>教材申报</a>" +
        "<a class='item orange' href='" + contextpath + "bookdeclare/toBookdeclareAdd.action'>我要出书</a>" +
        "<a class='item blue' href='" + contextpath + "group/list.action'>交互服务</a>" +
        "<a class='item black' href='" + contextpath + "group/list.action'>帮助</a>" +
        "</div>");

    var wrapper = $("body .content-wrapper");

    var winHeight = 0;

    // 获取窗口高度
    if($(window).height()){
        winHeight = $(window).height();
    }else if (window.innerHeight) {
        winHeight = window.innerHeight;
    } else if ((document.body) && (document.body.clientHeight)) {
        winHeight = document.body.clientHeight;
    }


    $menu.css("left", (wrapper.offset().left + 1210) + "px");
    $menu.css("top", (winHeight - 210) / 2 + "px");

    wrapper.parent().append($menu)

});