/**
 * Created by lihuan on 2017/11/24.
 */
/**
 * Created by huang on 2017/11/22.
 * 仿照element-ui封装一个消息提醒的功能，常用于主动操作后的反馈提示。依赖库：jquery
 * 提示状态success/warning/info/error
 * 使用示例：message.success('这里是提示信息');
 *
 */
(function () {


    /*  var contextpath = contextpath ;
     if (contextpath.lastIndexOf("/") == contextpath.length - 1) {
     contextpath = contextpath.substring(0, contextpath.length - 1);
     }*/
    /*   $('head').append('<style type="text/css" href="'
     + contextpath +
     '/resources/comm/layer/theme/default/layer.css"></style>');*/
    $('head').append('<script  src="'
        + contextpath +
        'resources/comm/layer/layer.js"></script>');


    var bg_img = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAKAAAAAoCAMAAABO8gGqAAAAeFBMVEVQv//3uioTzmb/SUn///+o3///u7v/urr73JVs4aD/3d3x/Pb1+/9ixf/++/Km7MU/14PT9uIi0XDD6f9xy//6YWP/VFT5x1Lzwkky0nzq9/+S1/9hvPr+9uRMwsb86byZu7L85bD61Hr4vjeclr+X6byckrky033aXjaWAAABNklEQVRYw+3ZzW7CMBAE4OAMMTGOS36atFCgQH/e/w0rLZFyWeQ9mESqdk4+fpfRWHZ2+jWifL3monxUmSifbytRsncji9CXC30Z+SRAI0wuTCbMSoEKVKACFahAIfDg/UECHEIYFgF6wEuAG2CTDNg7ez9Y18eAe2D/DOB33TwGOsCSD3AxYNe23ROAL0D9GFgAsORDISlJQuDkQ8kDJ+HomxO4azgfARkh+aLAqm2rVMASdcP7TM4KC0GL18A6FXALEt59EaAFADszcEdCzmcYHwnTAW+Xyy0GJOGW8xnGV1BTRMAEUzcJWZ9hfGOXZwNOwtLEgI58JHSpgMP5HNviUVhKpq4Yu+z6VMAjcFzgsiAHBiAsAuy8F23xNYSrXlgVqEAFKlCBcuB/eUQ/CYU/C31D/AFyLi+YoY/uFQAAAABJRU5ErkJggg==';


    //引入html
    var html = '<div class="message-tips">' +
        '	<div class="message-icon"></div>' +
        '	<div class="message-text"></div>' +
        '</div>';

    var confirm_firm = "";


    var css = '.message-tips{position: fixed;top: -20px;left: 50%;max-width: 600px;height: 40px;line-height:40px;margin-left: -150px;}' +
        '.message-tips .message-icon{float: left;width: 40px;height: 40px;color:#fff;text-align:center;}' +
        '.message-tips .message-icon{display:none;background-image:url(' + bg_img + ');background-repeat:no-repeat;}' +
        '.message-tips .message-text{min-width: 243px;height: 39px;padding:0 8px;background:#fff;box-shadow: 2px 2px 2px rgba(0,0,0,.08);border-bottom: 1px solid #e1e1e1;border-right: 1px solid #e1e1e1;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;}' +
        '.message-tips.msg-success .message-icon{display:block;background-position:0 0;}' +
        '.message-tips.msg-warning .message-icon{display:block;background-position:-120px 0;}' +
        '.message-tips.msg-error .message-icon{display:block;background-position:-40px 0;}' +
        '.message-tips.msg-info .message-icon{display:block;background-position:-80px 0;}'

    var typeClass = {
        success: 'msg-success',
        warning: 'msg-warning',
        info: 'msg-info',
        error: 'msg-error'
    };
    var zIndex = 1000;//设一个z-index初始值
    var hasCreated = false;
    var layerInit = false;
    /**
     * CreatMessage类用于创建一个message实体类提供
     * @param type
     * @param text
     * @constructor
     */
    var CreatMessage = function (type, text) {
        this.type = type;
        this.text = text;
        this.typeClass = typeClass[this.type] || 'msg-success';
        this.domHanddler = null;
        //初始化
        this.init()
    };
    CreatMessage.prototype = {
        init: function () {
            var self = this;
            this.creat();
            //将dom插入文档中
            $('body').append(this.domHanddler);
            //将css插入到文档中
            if (!hasCreated) {
                $('head').append('<style type="text/css">' + css + '</style>');
            }

            //渐入渐出
            this.domHanddler.animate({top: '20px', opacity: 1}, 280, 'linear', function () {
                //驻留时间设为2000ms
                var tempTimer = setTimeout(function () {
                    self.domHanddler.animate({top: '-20px', opacity: 0}, 'fast', function () {
                        self.remove();
                    });
                    clearTimeout(tempTimer);
                }, 2000)
            });
            hasCreated = true;
        },
        creat: function () {
            this.domHanddler = $(html);
            this.domHanddler.addClass(this.typeClass);
            this.domHanddler.css({'z-index': zIndex++});
            this.domHanddler.find('.message-text').html(this.text);
        },
        remove: function () {
            this.domHanddler.remove();
        }
    }

    window.message = {
        success: function (text) {
            return new CreatMessage('success', text);
        },
        warning: function (text) {
            return new CreatMessage('warning', text);
        },
        info: function (text) {
            return new CreatMessage('info', text);
        },
        error: function (text) {
            return new CreatMessage('error', text);
        },
        confirm: function (e, t, n, a) {
            return layer.confirm(e, t, n, a);
        }
    }

})();


(function ($, undefined) {
    /**
     * 配置信息，用于定义各种异常情况处理方法
     * @type {{debug: boolean, timeout: config.timeout, unAuth: config.unAuth, forbidden: config.forbidden, error: config.error}}
     */
    var config = {
        //后台出错了
        error: function (data) {
            window.message.error("出错啦！");

        }
    }


    $.ajaxSetup({
        type: "POST",
        dataType: "json",
        timeout: 10000,
        async: true,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            var errmsg = XMLHttpRequest.responseText;
            window.message.error("出错啦！");
        },
        beforeSend: function (xhr, global) {

            //处理异常情况需要中断success方法
            var global_copy = jQuery.extend({}, global);
            global.success = function (data, textStatus) {

                if (data instanceof String) {
                    try {
                        data = JSON.parse(data);
                    } catch (e) {
                        return global_copy.success(data, textStatus);
                    }
                }

                if (data && data.code) {
                    if (data.code == '100') {
                        window.location.href = data.data;
                    } else if (data.code != '1') {
                        window.message.error(data.msg);
                    } else {
                        if (global_copy.success) {
                            global_copy.success(data.data, textStatus);
                        }
                    }
                    return;
                }
                global_copy.success(data, textStatus);
            }
        }
    });


})(jQuery);


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
        var search = function () {

            /*$.ajax({
                type:'post',
                url:contextpath+'booksearch/bookOrArtSpliter.action?search=' + encodeURI(encodeURI($("#search-input").val()))+'&t='+new Date().getTime(),
                async:false,
                dataType:'json',
                data:data,
                success:function(json){

                }
            });*/

            window.location.href = contextpath + "booksearch/bookOrArtSpliter.action?search=" + encodeURI(encodeURI($("#search-input").val()));
        }
        if (!input_open) {
            $(".delete").css("display", "block");
            $(".search-input").css("display", "block");
            input_open = true;

            $(".search-input").bind('keydown', function (event) {
                if (event.keyCode == "13") {
                    search();
                }
            });

        } else {
            search();
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
var t_Time;
// 鼠标 移动到头像上展示 提示语
function getImageTips(id,htmlId){
    // type 小组成员 --好友 非好友  非好友提示语 他还不是您的好友呢！
    // 好友提示语 昵称：魏大勋 电话：13233427657 邮箱：123456@qq.com
    // htmlId 是所属最上层的div 或li
    var self = $(htmlId);
   // va_id = id;
    var str = ''
    console.log(1111111);

    if($(".image_tips").length>0){
        $(".image_tips").remove();
    }
    var flag = true;
    self.css({"position":"relative"});
    self.append('<div class="image_tips" id="image_tips"><span></span></div>');

        // if(var_id ==id )
        if(flag){
            flag = false;
            t_Time = setTimeout(function(){ //为了防止 鼠标快速移动 发送多次请求 鼠标需要等待1s中 才发送 提示语 的请求
                $.ajax({
                type:'get',
                url :contextpath+'myFriendInfo/'+id+'.action',
                contentType: 'application/json',
                dataType:'json',
                beforeSend:function(){
                    clearTimeout(t_Time);
                },
                complete:function(){
                    flag = true;

                },
                success:function(responsebean){
                    if(Empty(responsebean)){
                        str += '他还不是您的好友呢！';

                    }else{
                        str += '昵称：'+responsebean.nickname+' 电话：'+responsebean.telephone+' 邮箱：'+responsebean.email;
                    }

                    $("#image_tips").append(str);

                }
            })
        },1000);
        }


}




// 鼠标离开 提示语 消失
function removeImageTips(){
    clearTimeout(t_Time);
    if($(".image_tips").length>0){
        setTimeout("$(\".image_tips\").remove()",1000);
    }
}
/*
 * 判断变量是否空值
 * undefined, null, '', false, 0, [], {} 均返回true，否则返回false 
 */
function Empty(v) {
    switch (typeof v) {
        case 'undefined':
            return true;
            break;
        case 'string':
            if (v.length == 0)
                return true;
            break;
        case 'boolean':
            if (!v)
                return true;
            break;
        case 'number':
            if (0 === v)
                return true;
            break;
        case 'object':
            if (null == v)
                return true;
            if (undefined !== v.length && v.length == 0)
                return true;
            for (var k in v) {
                return false;
            }
            return false;
            break;
    }
    return false;
}
