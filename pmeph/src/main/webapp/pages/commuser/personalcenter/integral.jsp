<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>积分</title>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/commuser/personalcenter/integral.css?t=${_timestamp}" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/reload.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/personalcenter/integral.js?t=${_timestamp}"></script>
</head>
<style>
    .input {
        margin: 0px 15px 0px 5px;
        border: solid 1px #ededed;
        font-size: 14px;
        font-weight: normal;
        font-stretch: normal;
        letter-spacing: 0px;
        color: #333333;
        outline: none;
        padding: 0.5em 0.5em 0.5em 0.5em;
        border-radius: 5px;
        box-sizing: border-box;
        float: left;
        height: 40px;
        line-height: 40px;
        width: 250px;
    }

    .lable {
        float: left;
        height: 40px;
        line-height: 40px;
    }
    {

    }

    .pointchange table { display: table }/*默认为表格显示*/
    .pointchange tr { display: table-row }/*默认为表格行显示*/
    .pointchange thead { display: table-header-group }/*默认为表格头部分组显示*/
    .pointchange tbody { display: table-row-group }/*默认为表格行分组显示*/
    .pointchange tfoot { display: table-footer-group }/*默认为表格底部分组显示*/
    .pointchange col { display: table-column }/*默认为表格列显示*/
    .pointchange colgroup { display: table-column-group }/*默认为表格列分组显示*/
    .pointchange td, th { display: table-cell; }/*默认为单元格显示*/
    .pointchange caption { display: table-caption }/*默认为表格标题显示*/
    .pointchange table tr:hover{background:none;}

</style>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<div  style="background: #f6f6f6;" class="body">
    <input id="total" value="${total.total }" hidden>
    <div class="content-wrapper">
        <div style="height: 30px"></div>
        <div class="top">
            <div class="top1">积分</div>
        </div>

        <div id="table">
            <div class="top2">总积分：</div>
            <div class="top3">${total.total }</div>

            <div style="float: right">
                <button type="button" style="display: none" class="dhBtn" onclick="redeemPoints()">积分兑换</button>
                <button type="button" style="display: none" class="dhBtn" onclick="showMallPoints()">商城积分</button>
            </div>
            <div class="line"></div>
            <div class="tb2">
                <div class="top-three">
                    <div class="top4">积分记录：</div>
                    <div class="wen" title="积分规则" onclick="showPoints()"></div>
                    <div class="top5">筛选：</div>

                    <div class="top6" style="    margin-top: -22px;">
                        <select id="sele" name="sele" title="请选择">
                            <option value="0" ${condition=='0' ?'selected':''}>-全部-</option>
                            <option value="1" ${condition=='1' ?'selected':''}>一周内</option>
                            <option value="2" ${condition=='2' ?'selected':''}>三月内</option>
                            <option value="3" ${condition=='3' ?'selected':''}>一年内</option>
                        </select>
                    </div>
                </div>
                <div class="message">
                    <table class="table">
                        <thead>
                        <tr>
                            <td class="">来源/用途</td>
                            <td class="">积分变化</td>
                            <td class="">时间</td>
                        </tr>
                        </thead>
                        <tbody id="messageTable">
                        </tbody>
                    </table>
                    <div class="no-more">
                        <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                        <span>木有内容呀~~</span>
                    </div>
                </div>
            </div>
            <div class="dialog" id="redeemPoints-dialog">
                <div class="apache">
                    <div class="mistitle">积分兑换:</div>
                    <div class="xx" onclick="$('#redeemPoints-dialog').fadeOut(500);"></div>
                </div>
                <div style="margin: 30px" class="pointchange">
                    <table>
                        <tr>
                            <td><label class="label" for="exchangePointCout">兑换积分数量:</label></td>
                            <td width="50px"></td>
                            <td><input class="input" id="exchangePointCout"
                                       name="exchangePointCout"/></td>
                            <td><label style="font-size: 12px;color: gray" id="pointLab"></label></td>
                        </tr>

                    </table>
                    <div style="margin-right: 170px;margin-top: 50px;">
                        <button class="dhBtn" onclick="confirmExcheang()">确认兑换</button>
                    </div>
                </div>
                <div id="info" style="margin-left: 50px;margin-top: 130px;height: 50px;">
                </div>
            </div>

            <div class="dialog2" id="points-dialog" style="width: 60%">
                <div class="apache">
                    <div class="mistitle">积分规则:</div>
                    <div class="xx" onclick="$('#points-dialog').fadeOut(500);"></div>
                </div>
                <table style="width: 100%;height: 80%">
                    <thead>
                    <tr>
                        <th>积分规则名称</th>
                        <th>积分值</th>
                        <th>规则描述</th>
                    </tr>
                    </thead>
                    <tbody id="pointsTab">


                    </tbody>

                </table>

            </div>
            <div style="clear: both"></div>
            <!-- <div class="jzgd">加载更多...</div> -->
            <div class="js-load-more" style="cursor: pointer;">加载更多...</div>
        </div>
    </div>
    <div style="height: 60px; width: 100%;"></div>
</div>
<div style="clear: both"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
<script>


    function showMallPoints() {
        window.location.href = "http://www.pmphmall.com/buyerscore/index";
    }

    function confirmExcheang() {
        var count = parseInt($("#exchangePointCout").val());
        var total = parseInt($("#total").val());
        var point = parseInt($("#point").val());
        var exchange_point=parseInt($("#exchange_point").val());
        if (!count) {
            window.message.warning("您输入不能为空");
            return;
        }
        if (total < point) {
            window.message.warning("您的积分小于最小兑换数");
            return;
        }


        if (count % point != 0) {
            window.message.warning("请输入兑换的的倍数");
            return;
        }
        if (total < count) {
            window.message.warning("您输入的兑换积分大于你的总积分");
            return;
        }

        $("#info").html("");
        $.ajax({
            type: 'get',
            url: contextpath + 'integral/confirmPointExchange.action?ruleCode=buss',
            data: {count: count},
            async: false,
            success: function (json) {
                console.log(json.code);
                if (json.code == 1) {
                    window.message.success(json.msg);
                    window.location.reload();
                }

            }
        })
    }

    function redeemPoints() {
        $("#info").html("")
        $.ajax({
            type: 'get',
            url: contextpath + 'integral/findPointExchange.action?ruleCode=buss',
            async: false,
            dataType: 'json',
            success: function (json) {
                console.log(json);
                $("#redeemPoints-dialog").show();


                if(!json.activity){

                var html = '<div><lable id="infolable">兑换描述:';
                html += json.rule.description;
                html += "<lable>";
                html += '<input style="display: none" id="point" value="' + json.rule.point + '" />';
                html += '<input style="display: none" id="exchange_point" value="' + json.rule.exchange_point + '"/></div>';
                $("#pointLab").text("请输入"+json.rule.point +"的倍数");
                }else{
                    var html = '<table><tr><span id="infolable">活动兑换描述:';
                    html += "活动期间"+json.ruleActivity.point+"积分可兑换"+json.ruleActivity.rule_name+json.ruleActivity.exchange_point+"积分";
                    html += "<span></tr>";
                    html += '<tr><input style="display: none" id="point" value="' + json.ruleActivity.point + '" />';
                    html += '<input style="display: none" id="exchange_point" value="' + json.ruleActivity.exchange_point + '"/></tr><table>';
                    $("#pointLab").text("请输入"+json.rule.point +"的倍数");
                    $("#info").append(html)
                }


            }
        })


    }

    function showPoints() {
        $("#pointsTab").html("");


        $.ajax({
            type: 'get',
            url: contextpath + 'integral/exchangeList.action',
            async: false,
            dataType: 'json',
            success: function (json) {
                console.log(json);
                var html = "";
                for (i in json) {
                    console.log(json[i]);
                    html += "<tr>";
                    html += '<td>' + json[i].ruleName + '</td><td>+' + json[i].point + '</td><td>' + json[i].description + '</td>'
                    html += '</tr>'

                }
                $("#pointsTab").append(html);
                $("#points-dialog").show();
            }
        })

    }
</script>
</html>