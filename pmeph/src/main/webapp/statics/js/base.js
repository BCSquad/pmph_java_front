$(function () {

    //个人中心选择
    var open_user_select = false;
    var show = function () {
        $(".user-select").css("display", "block");
    }
    var hidden = function () {
        $(".user-select").css("display", "none");
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

    $(".user-info .user-icon").mouseover(function () {
        enter_user_select = false;
        show();

    });

    $(".user-select").mouseover(function () {
        enter_user_select = true;
    });

    $(".user-info .user-icon").mouseleave(function () {
        setTimeout(function () {
            if (!enter_user_select) {
                hidden();
            }
        }, 300);

    })
    $(".user-select").mouseleave(function () {
        hidden();
    })


    //搜索框效果
    var input_open = false;
    $(".search-icon").click(function () {
        if (!input_open) {
            $(".delete").css("display", "block");
            $(".search-input").css("display", "block");
            $(this).bind('keydown', function (event) {
                if (event.keyCode == "13") {

                }
            });
        } else {

        }
    });
    $(".delete").click(function () {
        $(".delete").css("display", "none");
        $(".search-input").css("display", "none");
        $(".search-icon").unbind('keydown');
    });


    //下载二维码

    $(".download").mouseover(function () {

        $(".download-pic").css("display", "block");
    });
    $(".download").mouseleave(function () {
        $(".download-pic").css("display", "none");

    })
})