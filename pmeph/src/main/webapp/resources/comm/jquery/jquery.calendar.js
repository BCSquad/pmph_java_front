/*
 * Author: ����־
 */
Date.prototype.format = function (fmt) {
    var o = {
        "m+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "M+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

jQuery.fn.extend({
    mycalendar: function (c) {
        function r() {
            $("#" + c.controlId).find(".tabD a").mouseup(function () {
                var a = new Date($("#" + c.controlId).find(".currentYear").text() + "/" + $("#" + c.controlId).find(".currentMonth").text() + "/1");
                if ($(this).hasClass("prevD")) {
                    a.setMonth(a.getMonth() - 1);
                    a.setDate($(this).text());
                    var b = c.speed;
                    c.speed = 0;
                    $("#" + c.controlId).find(".prevMonth").triggerHandler("mouseup");
                    c.speed = b
                } else if ($(this).hasClass("nextD")) {
                    a.setMonth(a.getMonth() + 1);
                    a.setDate($(this).text());
                    b = c.speed;
                    c.speed = 0;
                    $("#" + c.controlId).find(".nextMonth").triggerHandler("mouseup");
                    c.speed = b
                }
                var d = $(this).text();
                a.setDate(d);
                //a = a.getFullYear() + "-" + (Number(a.getMonth() + 1) < 10 ? "0" + Number(a.getMonth() + 1) : Number(a.getMonth() + 1)) + "-" + (Number(d) < 10 ? "0" + d : d);
                //n.val(a);
                $("#" + c.controlId + " div table a").removeClass("select");
                $("#" + c.controlId + " .tabD a:contains('" + d + "')").each(function () {
                    d == $(this).text() && !$(this).hasClass("prevD") && !$(this).hasClass("nextD") && $(this).addClass("select")
                });
                $("#" + c.controlId).hide();
                $(n).trigger('timeChange', [a, c]);
                c.callback.call(n[0], 'date', a, a.format(c.format));
                $(n).trigger("change");
                $("#close" + c.controlId).remove();

            }).hover(function () {
                    $(this).addClass("hover")
                },
                function () {
                    $(this).removeClass("hover")
                })
        }

        function u() {
            $("#" + c.controlId).find(".tabM a").mouseup(function () {
                if (c.view == 'date') {
                    var a = s(Number($("#" + c.controlId).find(".currentYear").text()), Number($(this).attr("val")));
                    D(a);
                    r();
                    $("#" + c.controlId).find(".currentMonth").text(Number($(this).attr("val")) + 1)
                } else if (c.view == 'month') {
                    $("#" + c.controlId).find(".currentMonth").text(Number($(this).attr("val")) + 1)
                    var a = new Date($("#" + c.controlId).find(".currentYear").text() + "/" + $("#" + c.controlId).find(".currentMonth").text() + "/1")
                    $("#" + c.controlId).hide();
                    $(n).trigger('timeChange', [a, c]);
                    c.callback.call(n[0], 'month', a, a.format(c.format));
                    $(n).trigger("change");
                    $("#close" + c.controlId).remove();
                }

            }).hover(function () {
                    $(this).addClass("hover")
                },
                function () {
                    $(this).removeClass("hover")
                })
        }

        function v() {
            $("#" + c.controlId).find(".tabY a").mouseup(function () {
                if (!($("#" + c.controlId).find(".enabled > .tabM").length > 0)) {
                    $("#" + c.controlId).find(".currentYear").text($(this).text());
                    var a = z(Number($("#" + c.controlId).find(".currentYear").text()));
                    E(a);
                    u()
                }
                /* var a = s(Number($(this).text()), Number($("#" + c.controlId).find(".currentMonth").text()) - 1);
                 D(a);
                 r();
                 $("#" + c.controlId).find(".currentYear").text(Number($(this).text()))*/
            }).hover(function () {
                    $(this).addClass("hover")
                },
                function () {
                    $(this).removeClass("hover")
                })
        }

        function s(a, b) {
            newDate = new Date(a, b, 1);
            newDate.setDate(0);
            var d = 1,
                h = newDate.getDate();
            newDate.setDate(1);
            newDate.setMonth(newDate.getMonth() + 1);
            var m = newDate.getDay();
            if (m == 0) m = 7;
            h = h - m + 1;
            newDate.setMonth(newDate.getMonth() + 1);
            newDate.setDate(0);
            var o = newDate.getDate(),
                g = "<table class='tabD'>";
            g += "<tr><th>\u65e5</th><th>\u4e00</th><th>\u4e8c</th><th>\u4e09</th><th>\u56db</th><th>\u4e94</th><th>\u516d</th></tr>";
            var i = w(),
                l = "",
                p = "",
                t = "";
            c.complement || (t = "style='display:none'");
            for (var x = 0; x < 6; x++) {
                g += "<tr>";
                for (var y = 0; y < 7; y++) {
                    var j = x * 7 + y + 1 - m;
                    p = l = "";
                    if (c.lowerLimit != NaN && c.lowerLimit >= new Date(newDate.getFullYear(), newDate.getMonth(), j + 1) || c.upperLimit != NaN && new Date(newDate.getFullYear(), newDate.getMonth(), j) > c.upperLimit) if (0 < j && j <= o) {
                        if (newDate.getFullYear() == e && newDate.getMonth() == f && j == q) l = "current";
                        g += "<td><span class='" + l + "'>" + j + "</span></td>"
                    } else if (j <= 0) {
                        if (newDate.getFullYear() == e && newDate.getMonth() - 1 == f && h == q) l = "current";
                        g += "<td><span class='" + l + "' " + t + ">" + h + "</span></td>";
                        h++
                    } else {
                        if (j > o) {
                            if (newDate.getFullYear() == e && newDate.getMonth() + 1 == f && d == q) l = "current";
                            g += "<td><span class='" + l + "' " + t + ">" + d + "</span></td>";
                            d++
                        }
                    } else if (0 < j && j <= o) {
                        if (newDate.getFullYear() == e && newDate.getMonth() == f && j == q) l = "current";
                        if (newDate.getFullYear() == i.getFullYear() && newDate.getMonth() == i.getMonth() && j == i.getDate()) p = "select";
                        g += "<td><a class='" + p + " " + l + "'>" + j + "</a></td>"
                    } else if (j <= 0) {
                        if (newDate.getFullYear() == e && newDate.getMonth() - 1 == f && h == q) l = "current";
                        if (newDate.getFullYear() == i.getFullYear() && newDate.getMonth() - 1 == i.getMonth() && h == i.getDate()) p = "select";
                        g += "<td><a class='prevD " + p + " " + l + "' " + t + ">" + h + "</a></td>";
                        h++
                    } else if (j > o) {
                        if (newDate.getFullYear() == e && newDate.getMonth() + 1 == f && d == q) l = "current";
                        if (newDate.getFullYear() == i.getFullYear() && newDate.getMonth() + 1 == i.getMonth() && d == i.getDate()) p = "select";
                        g += "<td><a class='nextD " + p + " " + l + "' " + t + ">" + d + "</a></td>";
                        d++
                    }
                    g = g.replace("class=' '", "")
                }
                g += "</tr>"
            }
            g += "</table>";
            return g
        }

        function z(a) {
            var b = w(),
                d = "<table class='tabM'>";
            d += "<tr>";
            d += "<td><a val='0' " + (a == b.getFullYear() && 0 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 0 == f ? "class='current'" : "") + ">1\u6708</a></td>";
            d += "<td><a val='1' " + (a == b.getFullYear() && 1 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 1 == f ? "class='current'" : "") + ">2\u6708</a></td>";
            d += "<td><a val='2' " + (a == b.getFullYear() && 2 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 2 == f ? "class='current'" : "") + ">3\u6708</a></td>";
            d += "<td><a val='3' " + (a == b.getFullYear() && 3 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 3 == f ? "class='current'" : "") + ">4\u6708</a></td>";
            d += "</tr>";
            d += "<tr>";
            d += "<td><a val='4' " + (a == b.getFullYear() && 4 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 4 == f ? "class='current'" : "") + ">5\u6708</a></td>";
            d += "<td><a val='5' " + (a == b.getFullYear() && 5 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 5 == f ? "class='current'" : "") + ">6\u6708</a></td>";
            d += "<td><a val='6' " + (a == b.getFullYear() && 6 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 6 == f ? "class='current'" : "") + ">7\u6708</a></td>";
            d += "<td><a val='7' " + (a == b.getFullYear() && 7 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 7 == f ? "class='current'" : "") + ">8\u6708</a></td>";
            d += "</tr>";
            d += "<tr>";
            d += "<td><a val='8' " + (a == b.getFullYear() && 8 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 8 == f ? "class='current'" : "") + ">9\u6708</a></td>";
            d += "<td><a val='9' " + (a == b.getFullYear() && 9 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 9 == f ? "class='current'" : "") + ">10\u6708</a></td>";
            d += "<td><a val='10' " + (a == b.getFullYear() && 10 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 10 == f ? "class='current'" : "") + ">11\u6708</a></td>";
            d += "<td><a val='11' " + (a == b.getFullYear() && 11 == b.getMonth() ? "class='select'" : "") + " " + (a == e && 11 == f ? "class='current'" : "") + ">12\u6708</a></td>";
            d += "</tr>";
            d += "</table>";
            return d
        }

        function A(a) {
            a = Math.floor(a / 10) * 10;
            var b = "<table class='tabY'>",
                d = w(),
                h = "",
                m = "",
                o = "";
            c.complement || (o = "style='display:none'");
            for (var g = 0; g < 3; g++) {
                b += "<tr>";
                for (var i = 0; i < 4; i++) {
                    m = h = "";
                    if (g + 1 * i + 1 != 1 && (g + 1) * (i + 1) != 12) {
                        if (a == d.getFullYear()) h = "select";
                        if (a == e) m = "current";
                        b += "<td><a class='" + h + " " + m + "' >" + a + "</a></td>";
                        a++
                    } else if (g + 1 * i + 1 == 1) {
                        if (a - 1 == d.getFullYear()) h = "select";
                        if (a - 1 == e) m = "current";
                        b += "<td><a class='prevY " + h + " " + m + "' " + o + ">" + (a - 1) + "</a></td>"
                    } else {
                        if (a == d.getFullYear()) h = "select";
                        if (a == e) m = "current";
                        b += "<td><a class='nextY " + h + " " + m + "' " + o + ">" + a + "</a></td>"
                    }
                }
                b += "</tr>"
            }
            b += "</table>";
            return b
        }

        function B(a) {
            var b = $("#" + c.controlId).find(".reserve"),
                d = $("#" + c.controlId).find(".enabled");
            b.stop();
            d.stop();
            b.removeClass("reserve").addClass("enabled");
            d.removeClass("enabled").addClass("reserve");
            b.css({
                "margin-left": d.width() + "px",
                "margin-top": "0px"
            });
            b.empty().append(a);
            b.animate({
                    "margin-left": "0px"
                },
                c.speed);
            d.animate({
                    "margin-left": "-" + d.width() + "px"
                },
                c.speed,
                function () {
                    d.empty()
                })
        }

        function C(a) {
            var b = $("#" + c.controlId).find(".reserve"),
                d = $("#" + c.controlId).find(".enabled");
            b.stop();
            d.stop();
            b.removeClass("reserve").addClass("enabled");
            d.removeClass("enabled").addClass("reserve");
            b.css({
                "margin-left": "-" + 210 + "px",
                "margin-top": "0px"
            });
            b.empty().append(a);
            b.animate({
                    "margin-left": "0px"
                },
                c.speed);
            d.animate({
                    "margin-left": 210 + "px"
                },
                c.speed,
                function () {
                    d.empty()
                })
        }

        function D(a) {
            var b = $("#" + c.controlId).find(".reserve"),
                d = $("#" + c.controlId).find(".enabled");
            b.stop();
            d.stop();
            b.removeClass("reserve").addClass("enabled");
            d.removeClass("enabled").addClass("reserve");
            $("#" + c.controlId).css({
                "z-index": 1000
            });

            b.css({
                "z-index": 999
            });
            d.css({
                "z-index": 999
            });
            b.css({
                "margin-left": "0px",
                "margin-top": (d.height() + 50) + "px"
            });
            b.empty().append(a);
            b.animate({
                    "margin-top": "0px"
                },
                c.speed);
            d.animate({
                    "margin-top": "-" + (d.height() + 50) + "px"
                },
                c.speed,
                function () {
                    d.empty();
                    $("#" + c.controlId).css({
                        "z-index": 1000
                    });
                    b.css({
                        "z-index": 1000
                    });
                    d.css({
                        "z-index": 1000
                    })
                })
        }

        function E(a) {
            var b = $("#" + c.controlId).find(".reserve"),
                d = $("#" + c.controlId).find(".enabled");
            b.stop();
            d.stop();
            b.removeClass("reserve").addClass("enabled");
            d.removeClass("enabled").addClass("reserve");
            $("#" + c.controlId).css({
                "z-index": 1000
            });
            b.css({
                "z-index": 999
            });
            d.css({
                "z-index": 999
            });
            b.css({
                "margin-left": "0px",
                "margin-top": "-" + (d.height() + 50) + "px"
            });
            b.empty().append(a);
            b.animate({
                    "margin-top": "0px"
                },
                c.speed);
            d.animate({
                    "margin-top": (d.height() + 50) + "px"
                },
                c.speed,
                function () {
                    d.empty();
                    $("#" + c.controlId).css({
                        "z-index": 1000
                    });
                    b.css({
                        "z-index": 1000
                    });
                    d.css({
                        "z-index": 1000
                    })
                })
        }

        function w() {
            re = /(\d\d\d\d)(\W)?(\d\d)(\W)?(\d\d)/g;
            var a = n.val();
            a = a.replace(re, "$1/$3/$5@").split("@")[0];
            return new Date(a)
        }

        function F(a) {
            var b = [];
            b.x = a.offsetLeft;
            for (b.y = a.offsetTop; a = a.offsetParent;) {
                b.x += a.offsetLeft;
                b.y += a.offsetTop
            }
            return b
        }

        var n = $(this);
        c = jQuery.extend({
                controlId: $(this).attr("id") + (parseInt(Math.random() * 10000000000000000, 10) + "") + "Calendar",
                speed: 100,
                complement: true,
                readonly: true,
                view: 'date',
                format: 'yyyy-mm-dd',
                upperLimit: NaN,
                lowerLimit: NaN,
                refresh: function () {

                    var a = $("#" + this.controlId).find(".currentYear"),
                        b = $("#" + this.controlId).find(".currentMonth"),
                        d = s(Number(a.text()), Number(b.text()) - 1);
                    C(d);
                    if (Number(b.text()) != 1) b.text(Number(b.text()));
                    else {
                        a.text(Number(a.text()) - 1);
                        b.text("12")
                    }
                    r()


                    /*var parent = $("#" + this.controlId).find(".tabD").parent();
                     $("#" + this.controlId).find(".tabD").remove();
                     var g = s(Number($("#" + this.controlId).find(".currentYear").text()), Number($("#" + this.controlId).find(".currentMonth").text()))
                     parent.append(g);*/
                },
                callback: function (view, date, dateString) {
                    $(this).val(dateString);
                }
            },
            c || {});
        if (c.view == 'month' && c.format.lastIndexOf("mm") + 2 < c.format.length) {
            c.format = 'yyyy-mm';
        }
        if (c.readonly) {
            n.attr("readonly", true);
            n.bind("keydown",
                function () {
                    if (event.keyCode == 8) event.keyCode = 0
                })
        }


        today = new Date;
        var e = today.getFullYear(),
            f = today.getMonth(),
            q = today.getDate(),
            k = "";
        k += "<div id='" + c.controlId + "'class='calendar'>";
        k += "  <div class='calMain'>";
        k += "    <div class='calTitle'>";
        k += "      <a class='prevMonth'>&lt;</a><span class='t_date'><span class='currentYearText'><a class='currentYear'>" + e + "</a>\u5e74</span><span class='currentMonthText'><a class='currentMonth'>" + (f + 1) + "</a>\u6708</span></span><a class='nextMonth'>&gt;</a>";
        k += "    </div>";
        k += "    <div class='calContent'>";
        k += "      <div class='reserve'>";
        k += "      </div>";
        k += "      <div class='enabled'>";
        if (c.view == 'date') {
            k += s(e, f);
        } else if (c.view == 'month') {
            k += z(e);
        }
        k += "      </div>";
        k += "    </div>";
        k += "  </div>";
        k += "</div>";
        $("body").append(k);
        if (c.view == 'date') {
            r();
        } else if (c.view == 'month') {
            u();
        }

        $("#" + c.controlId).find(".prevMonth").mouseup(function () {
            if ($("#" + c.controlId).find(".enabled > .tabD").length > 0) {
                var a = $("#" + c.controlId).find(".currentYear"),
                    b = $("#" + c.controlId).find(".currentMonth"),
                    d = s(Number(a.text()), Number(b.text()) - 2);
                C(d);
                if (Number(b.text()) != 1) b.text(Number(b.text()) - 1);
                else {
                    a.text(Number(a.text()) - 1);
                    b.text("12")
                }
                r()
            } else if ($("#" + c.controlId).find(".enabled > .tabM").length > 0) {
                d = z(Number($("#" + c.controlId).find(".currentYear").text()) - 1);
                C(d);
                u();
                $("#" + c.controlId).find(".currentYear").text(Number($("#" + c.controlId).find(".currentYear").text()) - 1)
            } else if ($("#" + c.controlId).find(".enabled > .tabY").length > 0) {
                d = A(Number($("#" + c.controlId).find(".currentYear").text()) - 10);
                C(d);
                v();
                $("#" + c.controlId).find(".currentYear").text(Number($("#" + c.controlId).find(".currentYear").text()) - 10)
            }
        });
        $("#" + c.controlId).find(".nextMonth").mouseup(function () {
            if ($("#" + c.controlId).find(".enabled > .tabD").length > 0) {
                var a = $("#" + c.controlId).find(".currentYear"),
                    b = $("#" + c.controlId).find(".currentMonth"),
                    d = s(Number(a.text()), Number(b.text()));
                B(d);
                if (Number(b.text()) != 12) b.text(Number(b.text()) + 1);
                else {
                    a.text(Number(a.text()) + 1);
                    b.text("1")
                }
                r()
            } else if ($("#" + c.controlId).find(".enabled > .tabM").length > 0) {
                d = z(Number($("#" + c.controlId).find(".currentYear").text()) + 1);
                B(d);
                u();
                $("#" + c.controlId).find(".currentYear").text(Number($("#" + c.controlId).find(".currentYear").text()) + 1)
            } else if ($("#" + c.controlId).find(".enabled > .tabY").length > 0) {
                d = A(Number($("#" + c.controlId).find(".currentYear").text()) + 10);
                B(d);
                v();
                $("#" + c.controlId).find(".currentYear").text(Number($("#" + c.controlId).find(".currentYear").text()) + 10)
            }
        });
        $("#" + c.controlId).find(".currentMonthText").mouseup(function () {
            if (!($("#" + c.controlId).find(".enabled > .tabM").length > 0)) {
                var a = z(Number($("#" + c.controlId).find(".currentYear").text()));
                E(a);
                u()
            }
        });
        $("#" + c.controlId).find(".currentYearText").mouseup(function () {
            if (!($("#" + c.controlId).find(".enabled > .tabY").length > 0)) {
                var a = A(Number($("#" + c.controlId).find(".currentYear").text()));
                E(a);
                v()
            }
        });
        n.data("calendar-id", c);
        n.bind("click",
            function () {
                if ($("#" + c.controlId + ":hidden").length != 0) {
                    $(".calendar").hide();

                    var a = $("#" + c.controlId),
                        b = F(n[0]),
                        d = b.x + Number(n.attr("clientLeft")) - 1;
                    b = b.y + Number(n.attr("clientTop")) + Number(n.attr("clientHeight")) - 1;
                    a.css({
                        top: b + "px",
                        left: d + "px"
                    });
                    d = $("#" + c.controlId).width();
                    b = $("#" + c.controlId).height();
                    var $this = $(this);
                    a.css({
                        left: $this.offset().left + 'px',
                        top: ($this.offset().top + $this.outerHeight()) + 'px',
                    });
                    /*a.css('left', '0px');
                     a.css('top', '400px');*/
                    a.css('z-index', '1000');
                    /*a.width(0);*/
                    /*a.height(0);*/


                    a.show().height(260);


                    var $clear = $("<img title='清除' id='close" + c.controlId + "' style='cursor: pointer;' src='" + contextpath + "statics/image/close.png'/>");
                    $clear.css("position", "absolute");
                    $clear.css("display", "block");
                    $clear.css("height", "12px");
                    $clear.css("width", "12px");
                    $clear.css("left", (n.offset().left + n.outerWidth() - 20) + 'px');
                    $clear.css("top", (n.offset().top + (n.outerHeight() - 12) / 2) + "px");
                    $clear.appendTo("body");
                    $clear.mouseup(function (e) {
                        e.stopPropagation();
                    });
                    $clear.click(function (e) {
                        $this.val("");
                        //取消时间 应该激活改变事件 以使其他依赖于当前改变的calendar的其他calendar刷新上下限
                        $this.trigger("timeChange");
                        //同时出发tipso的改变事件 激发其校验
                        $this.trigger("change.tipso");
                        $this.text("");
                        $("#" + c.controlId).height(0);
                        $("#" + c.controlId).hide();
                        $("#close" + c.controlId).remove();
                        //e.stopPropagation();
                    })

                    a.bind("selectstart",
                        function () {
                            return false
                        }).bind("mousedown",
                        function () {
                            return false
                        })
                }
            });
        $("#" + c.controlId).mouseup(function (e) {
            e.stopPropagation();
        })
        $(document).mouseup(function (a) {
            $("#" + c.controlId).height(0);
            $("#" + c.controlId).hide();
            $("#close" + c.controlId).remove();
        })
    }
});

$.fn.calendar = function () {

    $(this).find("input[calendar]").each(function (i) {
        var options = {
            'max': NaN,
            'min': NaN,
            'view': 'date',
            'format': 'yyyy-mm-dd',
            'onselected': function (view, date, dateString) {
                $(this).val(dateString)
            }
        };
        for (var index = 0; index < this.attributes.length; index++) {
            if (options[this.attributes[index].name] !== undefined && this.attributes[index].value) {
                options[this.attributes[index].name] = eval("(" + this.attributes[index].value + ")");
            }
        }

        var can = $(this).mycalendar({
            upperLimit: NaN,
            lowerLimit: NaN,
            view: options.view,
            format: options.format,
            callback: options.onselected
        });

        if (typeof(options.max) == "string") {
            if (options.max.indexOf("$") == 0) {
                var ele = options.max.substr(1, options.max.length - 1);
                var that = this;
                $(ele).on('timeChange', function (event, val) {
                    calendar = $(that).data("calendar-id");
                    calendar.upperLimit = val;
                    calendar.refresh();
                });
            } else if (options.min.indexOf("$") == 0) {
                var ele = options.min.substr(1, options.min.length - 1);
                var that = this;
                $(ele).on('timeChange', function (event, val) {
                    calendar = $(that).data("calendar-id");
                    calendar.lowerLimit = val;
                    calendar.refresh();
                });
            }
        }

        if (typeof(options.min) == "string") {
            if (options.min.indexOf("$") == 0) {
                var ele = options.min.substr(1, options.min.length - 1);
                var that = this;
                $(ele).on('timeChange', function (event, val) {
                    calendar = $(that).data("calendar-id");
                    calendar.lowerLimit = val;
                    calendar.refresh();
                });
            }
        }

    })
};
$(function () {
    $("input[calendar]").each(function (i) {
        var options = {
            'max': NaN,
            'min': NaN,
            'view': 'date',
            'format': 'yyyy-mm-dd',
            'onselected': function (view, date, dateString) {
                $(this).val(dateString)
            }
        };
        for (var index = 0; index < this.attributes.length; index++) {
            if (options[this.attributes[index].name] !== undefined && this.attributes[index].value) {
                options[this.attributes[index].name] = eval("(" + this.attributes[index].value + ")");
            }
        }

        var can = $(this).mycalendar({
            upperLimit: NaN,
            lowerLimit: NaN,
            view: options.view,
            format: options.format,
            callback: options.onselected
        });

        if (typeof(options.max) == "string") {
            if (options.max.indexOf("$") == 0) {
                var ele = options.max.substr(1, options.max.length - 1);
                var that = this;
                $(ele).on('timeChange', function (event, val) {
                    calendar = $(that).data("calendar-id");
                    //console.log("1max "+val);
                    calendar.upperLimit = val;
                    calendar.refresh();
                });
            } else if (options.min.indexOf("$") == 0) {
                var ele = options.min.substr(1, options.min.length - 1);
                var that = this;
                $(ele).on('timeChange', function (event, val) {
                    calendar = $(that).data("calendar-id");
                    //console.log("2min"+val);
                    calendar.lowerLimit = val;
                    calendar.refresh();
                });
            }
        }

        if (typeof(options.min) == "string") {
            if (options.min.indexOf("$") == 0) {
                var ele = options.min.substr(1, options.min.length - 1);
                var that = this;
                $(ele).on('timeChange', function (event, val) {
                    calendar = $(that).data("calendar-id");
                    //console.log(val);
                    calendar.lowerLimit = val;
                    calendar.refresh();
                });
            }
        }
    });
    setTimeout(function () {
        $("input[calendar]").each(function (i) {
            $(this).trigger('timeChange');
        })
    },0)

})