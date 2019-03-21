(function ($) {
    $.fn.extend({
        datalist: function (options, func) {
            var ul, cur, count = 0, css, hover, visible, scroll;
            $(".datalist_li").hover(
                function () {
                    $(".datalist_li").removeClass("datalist_li_hover");
                    $(this).addClass("datalist_li_hover");
                    count = $(this).index();
                }, function () {
                    $(this).removeClass("datalist_li_hover");
                }
            );
            $("[data-list]").on({
                "click focus": function () {
                    cur = $(this);
                    ul = $("[data-list-id='" + cur.attr("id") + "']").eq(0);
                    console.log(ul);
                    if (!!ul) {
                        cur.css({
                            "border-bottom-right-radius": "0px",
                            "border-bottom-left-radius": "0px"
                        });
                        css = {
                            "top": cur.height()+27 +"px",
                            "left": cur.position().left + "px",
                            "display": "block",
                            "width": cur.width()+50 +"px"
                        };
                        !!options && (css = $.extend(css, options));                     //ul.show().css(css);
                        for (var i in css) {
                            ul[0].style[i] = css[i];
                        }
                    }
                },
                "keyup": function (e) {
                    ul.show();
                    hover = ul.find(".datalist_li_hover");
                    visible = ul.find(".datalist_li:visible");
                    switch (e.keyCode) {
                        case 13:
                            if (hover.length > 0) {
                                cur.val(hover.text());
                                ul.hide().find(".datalist_li").removeClass("datalist_li_hover");
                            }
                            !!func && func();
                            break;
                        case 38:
                            if (hover.index() == 0) {
                                count = visible.length;
                            }
                            if (hover.length > 0) {
                                count--;
                                visible.removeClass("datalist_li_hover");
                                visible.eq(count).addClass("datalist_li_hover");
                            } else {
                                count = visible.length - 1;
                                visible.eq(count).addClass("datalist_li_hover");
                            }
                            break;
                        case 40:
                            if (count == visible.length - 1) {
                                count = -1;
                            }
                            if (hover.length > 0) {
                                count++;
                                visible.removeClass("datalist_li_hover");
                                visible.eq(count).addClass("datalist_li_hover");
                            } else {
                                count = 0;
                                visible.eq(0).addClass("datalist_li_hover");
                            }
                            break;
                        default:
                            ul.find(".datalist_li").hide().removeClass("datalist_li_hover").each(function () {
                                if ($(this).text().indexOf(cur.val()) >= 0 || $(this).text() == cur.val()) {
                                    $(this).show();
                                }
                            });
                    }

                },
                "blur": function () {
                    hover = ul.find(".datalist_li_hover");
                    if (hover.length != 0) {
                        cur.val(hover.text());
                        cur.attr("value", hover.text());
                        ul.find(".datalist_li").each(function () {
                            $(this).removeClass("datalist_li_hover");
                        });

                        ul.hide();
                        count = 0;
                        !!func && func(hover);
                    } else {
                        ul.hide();
                    }
                    cur.css({
                        "border-bottom-right-radius": "",
                        "border-bottom-left-radius": ""
                    });
                }
            });
        }
    })
})(jQuery);


$(function(){

    var open_user_select = false;
    var show = function () {
        $(".user-detail").css("display", "block");
    }
    var hidden = function () {
        $(".user-detail").css("display", "none");
    }
    /*  $(".user-info .user-icon").click(function () {
          if (open_user_select) {
              hidden();
          } else {
              show();
          }
      });*/
    /* $(".user-select").mouseleave(function () {
         console.log("mouseleave")
        // hidden();
     })*/

    $(".logininfo").mouseover(function () {
        user_info = false;
        show();

    });

    $(".user-detail").mouseover(function () {
        user_info = true;
    });

    $(".logininfo").mouseleave(function () {
        setTimeout(function () {
            if (!user_info) {
                hidden();
            }
        }, 300);

    })
    $(".user-detail").mouseleave(function () {
        hidden();
    })





});