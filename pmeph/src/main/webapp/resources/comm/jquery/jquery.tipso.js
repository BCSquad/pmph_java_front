/*!
 * tipso - A Lightweight Responsive jQuery Tooltip Plugin v1.0.1
 * Copyright (c) 2014 Bojan Petkovski
 * http://tipso.object505.com
 * Licensed under the MIT license
 * http://object505.mit-license.org/
 */
;(function ($, window, document, undefined) {
    var pluginName = "tipso",
        defaults = {
            speed: 400,
            background: '#D75253',
            color: '#ffffff',
            position: 'top',
            width: 120,
            delay: 200,
            offsetX: 0,
            offsetY: 0,
            content: null,
            message: null,
            ajaxContentUrl: null,
            useTitle: false,
            onBeforeShow: null,
            onShow: null,
            onHide: null,
            group:0
        };

    function Plugin(element, options) {
        this.element = $(element);
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this._title = this.element.attr('title');
        this.mode = 'hide';
        this.init();
    }

    var list = [];

    var rules = {};

    $.extend({
        fireValidator: function (group) {
            var isshow = false;
            if(group>0){

            }else{
                group=0;
            }
            for (var i = 0; i < list.length; i++) {
                var item = list[i];
                //console.log(item);

                item.hideStyle();
                item.hide();

                if(item.settings.group!=group){
                    continue;
                }

                var value = "";
                if (item.element.find("input[type='hidden']").length > 0) {
                    value = item.element.find("input[type='hidden']").val()
                } else {
                    value = item.element.val()
                }
                if (item.settings.content = startValidate.call(item.element, item.settings.validator, value, item.settings.message)) {
                    item.showStyle();
                    if (!isshow) {
                        item.element.focus();
                        item.show();
                        console.log(item);
                        isshow = true;
                    }
                }
            }
            return !isshow;
        }
    })

    $.extend({
        addValidatRule: function (rulename, rule) {
            rules[rulename] = rule;
        }
    })

    var startValidate = function (obj, value, content) {
        if (typeof obj === 'string') {
            var rule_list = obj.split("|");
            for (var i = 0; i < rule_list.length; i++) {
                var rule = rules[$.trim(rule_list[i])]
                if (!rule(value)) {
                    return content.split("|")[i];
                }
            }
            return "";

        } else if (typeof obj === 'function') {
            if (obj(value)) {
                return "";
            } else {
                return content;
            }
        }

    }

    $.addValidatRule("isNonEmpty", function (value) {
        //不能为空
        if (!value.length) {
            return false;
        } else {
            return true;
        }
    });

    $.addValidatRule("isMobile", function (value) {
        //不能为空
        if (!/^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/.test(value)) {
            return false;
        } else {
            return true;
        }
    });


    $.addValidatRule("isEmail", function (value) {
        //不能为空
        if (!/(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/.test(value)) {
            return false;
        } else {
            return true;
        }
    });

    $.addValidatRule("onlyInt", function (value) {
        //不能为空
        if (!/^[0-9]*$/.test(value)) {
            return false;
        } else {
            return true;
        }
    });

    $.addValidatRule("isPassword", function (value) {
        //校验密码  只能为数字和字母
        if (!/^[A-Za-z0-9!@#$%^&*]{6,16}$/.test(value)) {
            return false;
        } else {
            return true;
        }
    });

    $.addValidatRule("isCard", function (value) {
        //校验身份证号码
        if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(value)) {
            return false;
        } else {
            return true;
        }
    });
    
    $.addValidatRule("isPassport", function (value) {
        //校验护照号码
        if (!/^[a-zA-Z0-9]{3,21}$/.test(value)) {
            return false;
        } else {
            return true;
        }
    });
    
    $.addValidatRule("isOfficialCard", function (value) {
        //军官证号码
        if (!/^[a-zA-Z0-9]{7,21}$/.test(value)) {
            return false;
        } else {
            return true;
        }
    });

    $.extend(Plugin.prototype, {
        init: function () {
            var obj = this,
                $e = this.element;
            
            var updateTempIndex = -1 ;
        	for ( var i = 0 ;i<list.length;i++) {
        		var item = list[i];
        		if(this.element[0] == item.element[0]){
        			updateTempIndex = i; 
        			break;	
        		}
			}
        	
        	if(updateTempIndex>=0){
        		list[updateTempIndex] = obj;
        	}else{
        		list.push(this);
        	}

            if (isTouchSupported()) {
                $e.on('click' + '.' + pluginName, function (e) {
                    obj.mode == 'hide' ? obj.show() : obj.hide();
                    e.stopPropagation();
                });
                $(document).on('click', function () {
                    if (obj.mode == 'show') {
                        obj.hide();
                    }
                });
            } else {
                /*setTimeout(function () {
                 obj.show();
                 }, 0);*/

                $e.unbind('mouseover' + '.' + pluginName).on('mouseover' + '.' + pluginName, function () {
                    obj.show();
                });
                $e.unbind('mouseout' + '.' + pluginName).on('mouseout' + '.' + pluginName, function () {
                    obj.hide();
                });
                $e.unbind('change'+ '.' + pluginName).on('change' + '.' + pluginName,function () {
                    var value = "";
                    if (obj.element.find("input[type='hidden']").length > 0) {
                        value = obj.element.find("input[type='hidden']").val()
                    } else {
                        value = obj.element.val()
                    }
                    //if (!(obj.settings.content = startValidate.call(obj.element, obj.settings.validator, value, obj.settings.message))) {
                    if (!(obj.settings.content = startValidate.call(obj.element,$(obj.element).data("plugin_"+pluginName).settings.validator, value, $(obj.element).data("plugin_"+pluginName).settings.message))) {
                        obj.hideStyle();
                        obj.hide();
                    }else {
                        obj.showStyle();
                        obj.show();
                    }
                })
                /*$e.change(function () {
                    var value = "";
                    if (obj.element.find("input[type='hidden']").length > 0) {
                        value = obj.element.find("input[type='hidden']").val()
                    } else {
                        value = obj.element.val()
                    }
                    //if (!(obj.settings.content = startValidate.call(obj.element, obj.settings.validator, value, obj.settings.message))) {
                    if (!(obj.settings.content = startValidate.call(obj.element,$(obj.element).data("plugin_"+pluginName).settings.validator, value, $(obj.element).data("plugin_"+pluginName).settings.message))) {
                        obj.hideStyle();
                        obj.hide();
                    }else {
                        obj.showStyle();
                        obj.show();
                    }
                });*/

            }
            /*if(updateTempIndex>=0){
        		$e.trigger("change");
        	}*/
        },
        tooltip: function () {
            if (!this.tipso_bubble) {
                this.tipso_bubble = $(
                    '<div class="tipso_bubble"><div class="tipso_content"></div><div class="tipso_arrow"></div></div>'
                );
            }
            return this.tipso_bubble;
        },
        showStyle: function () {
            this.element.addClass('tipso_style').removeAttr('title');
        },
        hideStyle: function () {
            this.element.removeClass('tipso_style').removeAttr('title');
        },
        show: function () {

            if (!this.element.hasClass("tipso_style")) {
                return;
            }

            var tipso_bubble = this.tooltip(),
                obj = this,
                $win = $(window);

            if ($.isFunction(obj.settings.onBeforeShow)) {
                obj.settings.onBeforeShow($(this));
            }
            tipso_bubble.css({
                background: obj.settings.background,
                color: obj.settings.color,
                width: obj.settings.width,
                'max-width': '200px'
            }).hide();
            tipso_bubble.find('.tipso_content').html(obj.content());
            reposition(obj);
            $win.resize(function () {
                reposition(obj);
            });
            obj.timeout = window.setTimeout(function () {
                tipso_bubble.appendTo('body').stop(true, true).fadeIn(obj.settings
                    .speed, function () {
                    obj.mode = 'show';
                    if ($.isFunction(obj.settings.onShow)) {
                        obj.settings.onShow($(this));
                    }
                });
            }, obj.settings.delay);
        },
        hide: function () {
            var obj = this,
                tipso_bubble = this.tooltip();
            window.clearTimeout(obj.timeout);
            obj.timeout = null;
            tipso_bubble.stop(true, true).fadeOut(obj.settings.speed,
                function () {
                    $(this).remove();
                    if ($.isFunction(obj.settings.onHide) && obj.mode == 'show') {
                        obj.settings.onHide($(this));
                    }
                    obj.mode = 'hide';
                });
        },
        destroy: function () {
            var $e = this.element;
            $e.off('.' + pluginName);
            $e.removeData(pluginName);
            $e.removeClass('tipso_style').attr('title', this._title);

        },
        removeFormFireVali: function(){
        	this.hide();
        	this.hideStyle();
        	var desTempIndex = -1 ;
        	for ( var i = 0 ;i<list.length;i++) {
        		var item = list[i];
        		if(this.element.attr("id") == item.element.attr("id")){
        			desTempIndex = i; 
        			break;	
        		}
			}
        	//从fireValidator校验列表中删除此项
        	if(desTempIndex>=0){
        		list.splice(desTempIndex, 1); 
        	}
            //$.data(this, 'plugin_' + pluginName, null);
        
        },
        
        content: function () {
            var content,
                $e = this.element,
                obj = this,
                title = this._title;
            if (obj.settings.ajaxContentUrl) {
                content = $.ajax({
                    type: "GET",
                    url: obj.settings.ajaxContentUrl,
                    async: false
                }).responseText;
            } else if (obj.settings.content) {
                content = obj.settings.content;
            } else {
                if (obj.settings.useTitle === true) {
                    content = title;
                } else {
                    content = $e.data('tipso');
                }
            }
            return content;
        },
        update: function (key, value) {
            var obj = this;
            if (value) {
                obj.settings[key] = value;
            } else {
                return obj.settings[key];
            }
        }
    });

    function isTouchSupported() {
        var msTouchEnabled = window.navigator.msMaxTouchPoints;
        var generalTouchEnabled = "ontouchstart" in document.createElement(
                "div");
        if (msTouchEnabled || generalTouchEnabled) {
            return true;
        }
        return false;
    }

    function realHeight(obj) {
        var clone = obj.clone();
        clone.css("visibility", "hidden");
        $('body').append(clone);
        var height = clone.outerHeight();
        clone.remove();
        return height;
    }

    function reposition(thisthat) {
        var tipso_bubble = thisthat.tooltip(),
            $e = thisthat.element,
            obj = thisthat,
            $win = $(window),
            arrow = 10,
            pos_top, pos_left, diff;
        switch (obj.settings.position) {
            case 'top':
                pos_left = $e.offset().left + ($e.outerWidth() / 2) - (tipso_bubble
                        .outerWidth() / 2);
                pos_top = $e.offset().top - realHeight(tipso_bubble) - arrow;
                tipso_bubble.find('.tipso_arrow').css({
                    marginLeft: -8
                });
                if (pos_top < $win.scrollTop()) {
                    pos_top = $e.offset().top + $e.outerHeight() + arrow;
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-bottom-color': obj.settings.background,
                        'border-top-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass('bottom');
                } else {
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-top-color': obj.settings.background,
                        'border-bottom-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass('top');
                }
                break;
            case 'bottom':
                pos_left = $e.offset().left + ($e.outerWidth() / 2) - (tipso_bubble
                        .outerWidth() / 2);
                pos_top = $e.offset().top + $e.outerHeight() + arrow;
                tipso_bubble.find('.tipso_arrow').css({
                    marginLeft: -8
                });
                if (pos_top + realHeight(tipso_bubble) > $win.scrollTop() + $win.outerHeight()) {
                    pos_top = $e.offset().top - realHeight(tipso_bubble) - arrow;
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-top-color': obj.settings.background,
                        'border-bottom-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass('top');
                } else {
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-bottom-color': obj.settings.background,
                        'border-top-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass(obj.settings.position);
                }
                break;
            case 'left':
                pos_left = $e.offset().left - tipso_bubble.outerWidth() - arrow;
                pos_top = $e.offset().top + ($e.outerHeight() / 2) - (realHeight(
                        tipso_bubble) / 2);
                tipso_bubble.find('.tipso_arrow').css({
                    marginTop: -8,
                    marginLeft: ''
                });
                if (pos_left < $win.scrollLeft()) {
                    pos_left = $e.offset().left + $e.outerWidth() + arrow;
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-right-color': obj.settings.background,
                        'border-left-color': 'transparent',
                        'border-top-color': 'transparent',
                        'border-bottom-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass('right');
                } else {
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-left-color': obj.settings.background,
                        'border-right-color': 'transparent',
                        'border-top-color': 'transparent',
                        'border-bottom-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass(obj.settings.position);
                }
                break;
            case 'right':
                pos_left = $e.offset().left + $e.outerWidth() + arrow;
                pos_top = $e.offset().top + ($e.outerHeight() / 2) - (realHeight(
                        tipso_bubble) / 2);
                tipso_bubble.find('.tipso_arrow').css({
                    marginTop: -8,
                    marginLeft: ''
                });
                if (pos_left + arrow + obj.settings.width > $win.scrollLeft() +
                    $win.outerWidth()) {
                    pos_left = $e.offset().left - tipso_bubble.outerWidth() - arrow;
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-left-color': obj.settings.background,
                        'border-right-color': 'transparent',
                        'border-top-color': 'transparent',
                        'border-bottom-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass('left');
                } else {
                    tipso_bubble.find('.tipso_arrow').css({
                        'border-right-color': obj.settings.background,
                        'border-left-color': 'transparent',
                        'border-top-color': 'transparent',
                        'border-bottom-color': 'transparent'
                    });
                    tipso_bubble.removeClass('top bottom left right');
                    tipso_bubble.addClass(obj.settings.position);
                }
                break;
        }
        if (pos_left < $win.scrollLeft() && (obj.settings.position == 'bottom' ||
            obj.settings.position == 'top')) {
            tipso_bubble.find('.tipso_arrow').css({
                marginLeft: pos_left - 8
            });
            pos_left = 0;
        }
        if (pos_left + obj.settings.width > $win.outerWidth() && (obj.settings.position ==
            'bottom' || obj.settings.position == 'top')) {
            diff = $win.outerWidth() - (pos_left + obj.settings.width);
            tipso_bubble.find('.tipso_arrow').css({
                marginLeft: -diff - 8,
                marginTop: ''
            });
            pos_left = pos_left + diff;
        }
        if (pos_left < $win.scrollLeft() && (obj.settings.position == 'left' ||
            obj.settings.position == 'right')) {
            pos_left = $e.offset().left + ($e.outerWidth() / 2) - (tipso_bubble.outerWidth() /
                2);
            tipso_bubble.find('.tipso_arrow').css({
                marginLeft: -8,
                marginTop: ''
            });
            pos_top = $e.offset().top - realHeight(tipso_bubble) - arrow;
            if (pos_top < $win.scrollTop()) {
                pos_top = $e.offset().top + $e.outerHeight() + arrow;
                tipso_bubble.find('.tipso_arrow').css({
                    'border-bottom-color': obj.settings.background,
                    'border-top-color': 'transparent',
                    'border-left-color': 'transparent',
                    'border-right-color': 'transparent'
                });
                tipso_bubble.removeClass('top bottom left right');
                tipso_bubble.addClass('bottom');
            } else {
                tipso_bubble.find('.tipso_arrow').css({
                    'border-top-color': obj.settings.background,
                    'border-bottom-color': 'transparent',
                    'border-left-color': 'transparent',
                    'border-right-color': 'transparent'
                });
                tipso_bubble.removeClass('top bottom left right');
                tipso_bubble.addClass('top');
            }
            if (pos_left + obj.settings.width > $win.outerWidth()) {
                diff = $win.outerWidth() - (pos_left + obj.settings.width);
                tipso_bubble.find('.tipso_arrow').css({
                    marginLeft: -diff - 8,
                    marginTop: ''
                });
                pos_left = pos_left + diff;
            }
            if (pos_left < $win.scrollLeft()) {
                tipso_bubble.find('.tipso_arrow').css({
                    marginLeft: pos_left - 8
                });
                pos_left = 0;
            }
        }
        if (pos_left + obj.settings.width > $win.outerWidth() && (obj.settings.position ==
            'left' || obj.settings.position == 'right')) {
            pos_left = $e.offset().left + ($e.outerWidth() / 2) - (tipso_bubble.outerWidth() /
                2);
            tipso_bubble.find('.tipso_arrow').css({
                marginLeft: -8,
                marginTop: ''
            });
            pos_top = $e.offset().top - realHeight(tipso_bubble) - arrow;
            if (pos_top < $win.scrollTop()) {
                pos_top = $e.offset().top + $e.outerHeight() + arrow;
                tipso_bubble.find('.tipso_arrow').css({
                    'border-bottom-color': obj.settings.background,
                    'border-top-color': 'transparent',
                    'border-left-color': 'transparent',
                    'border-right-color': 'transparent'
                });
                tipso_bubble.removeClass('top bottom left right');
                tipso_bubble.addClass('bottom');
            } else {
                tipso_bubble.find('.tipso_arrow').css({
                    'border-top-color': obj.settings.background,
                    'border-bottom-color': 'transparent',
                    'border-left-color': 'transparent',
                    'border-right-color': 'transparent'
                });
                tipso_bubble.removeClass('top bottom left right');
                tipso_bubble.addClass('top');
            }
            if (pos_left + obj.settings.width > $win.outerWidth()) {
                diff = $win.outerWidth() - (pos_left + obj.settings.width);
                tipso_bubble.find('.tipso_arrow').css({
                    marginLeft: -diff - 8,
                    marginTop: ''
                });
                pos_left = pos_left + diff;
            }
            if (pos_left < $win.scrollLeft()) {
                tipso_bubble.find('.tipso_arrow').css({
                    marginLeft: pos_left - 8
                });
                pos_left = 0;
            }
        }
        tipso_bubble.css({
            left: pos_left + obj.settings.offsetX,
            top: pos_top + obj.settings.offsetY
        });
    }

    $[pluginName] = $.fn[pluginName] = function (options) {
        var args = arguments;
        if (options === undefined || typeof options === 'object') {
            if (!(this instanceof $)) {
                $.extend(defaults, options);
            }
            return this.each(function () {
                if (!$.data(this, 'plugin_' + pluginName)) {
                    $.data(this, 'plugin_' + pluginName, new Plugin(this, options));
                }else{
                	$.removeData(this,'plugin_' + pluginName);
                	$.data(this, 'plugin_' + pluginName, new Plugin(this, options));
                }
            });
        } else if (typeof options === 'string' && options[0] !== '_' && options !==
            'init') {
            var returns;
            this.each(function () {
                var instance = $.data(this, 'plugin_' + pluginName);
                if (!instance) {
                    instance = $.data(this, 'plugin_' + pluginName, new Plugin(
                        this, options));
                }
                if (instance instanceof Plugin && typeof instance[options] ===
                    'function') {
                    returns = instance[options].apply(instance, Array.prototype.slice
                        .call(args, 1));
                }
                if (options === 'destroy') {
                	var desTempIndex = -1 ;
                	for ( var i = 0 ;i<list.length;i++) {
                		var item = list[i];
                		if(this.id == item.element.attr("id")){
                			desTempIndex = i; 
                			break;	
                		}
					}
                	//从fireValidator校验列表中删除此项
                	if(desTempIndex>=0){
                	    //隐藏泡泡
                        list[desTempIndex].hide();
                        //删除该输入框的改变事件，该事件会生成泡泡（虽然fireValidator不再校验）
                        list[desTempIndex].element.unbind('change'+ '.' + pluginName);
                        //从fireValidator校验列表中删除此项
                		list.splice(desTempIndex, 1); 
                	}
                    //$.data(this, 'plugin_' + pluginName, null);
                }
                
               
                
            });
            return returns !== undefined ? returns : this;
        }
    };
})(jQuery, window, document);