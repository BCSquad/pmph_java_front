<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>临床申报表修改</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/materialdec/materialadd.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.calendar.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.min.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.jqprint-0.3.js?t=${_timestamp}"></script>
    <script src="http://www.jq22.com/jquery/jquery-migrate-1.2.1.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery-validate.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.calendar.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/layer/layer.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/resources/commuser/materialdec/expertZc.js?t=${_timestamp}"></script>

    <style>
        .el-tag{
            background-color: #838fa5;
        }
    </style>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="body">
    <div class="content-wrapper">
        <div style="color: red;font-size: 16px;margin-top: 28px;">（提示：为确保填写成功，请用360极速浏览器或谷歌浏览器）</div>
        <div class="sbxq_title">
            <span><a style="text-decoration: none;color: #999999;"
                     href="${ctx}/personalhomepage/tohomepage.action?pagetag=dt">个人中心</a> ><a
                    style="text-decoration: none;color: #999999;"
                    href="${ctx}/personalhomepage/tohomepage.action?pagetag=lcjc
"> 临床决策专家申报 </a> > <span id="product_name"></span></span>
        </div>
        <div id="ifprint">
        <form id="objForm">
            <input type="hidden" id="material_id" name="material_id" value="${materialMap.expert_type}"/>
            <input type="hidden" id="product_id" name="product_id" value="${gezlList.product_id}"/>
            <input type="hidden" id="expert_type" name="expert_type" value="${materialMap.expert_type}"/>
            <input type="hidden" id="expertation_id" name="expertation_id" value="${materialMap.declaration_id}"/>
            <!-- 专家信息-->
            <div class="sbxq_item1">
                <div>
                    <span id="tsxz_span2"></span>
                    <span class="tsxz_title">专家信息</span>
                    <span class="tsxz_ts1"><img src="${ctx}/statics/image/btxx.png"/></span>
                </div>
                <div class="content">
                    <table class="tab_1">
                        <tr>
                            <td><span class="btbs">*</span><span>姓&emsp;&emsp;名：</span>
                                <input class="cg_input" disabled name="realname" id="realname" value="${gezlList.realname}" maxlength="20"/>
                                <input class="cg_input" name="user_id" type="hidden" value="${userMap.id}" />
                            </td>
                            <td><span class="btbs">*</span><span>性&emsp;&emsp;别：</span>
                                <select class="select-input" disabled id="sex" name="sex">
                                    <option value="1" ${gezlList.sex=='1'?'selected':'' }>男</option>
                                    <option value="2" ${gezlList.sex=='2'?'selected':'' }>女</option>
                                </select></td>
                            <td><span class="btbs">*</span><span>出生年月：</span>
                                <input class="cg_input" disabled calendar format="'yyyy-mm-dd'"  name="birthday" value="${gezlList.birthday}"  id="birthday"  /></td>
                            <td><span class="btbs">*</span><span>工作单位：</span>
                                <input class="cg_input" name="org_name" value="${gezlList.org_name}" id="org_name"  maxlength="36"/></td>
                        </tr>
                        <tr>
                            <td><span class="btbs">*</span><span>职&emsp;&emsp;务：</span>
                                <input class="cg_input" name="position" value="${gezlList.position}" id="position"  maxlength="36"/></td>
                            <td><span class="btbs">*</span><span>职&emsp;&emsp;称：</span>
                                <select id="zclx" name="title">
                                    <c:forEach items="${writerUserTitle}" var="dic">
                                        <option value="${dic.code}" ${gezlList.title == dic.code ? 'selected':''}>${dic.name}</option>
                                    </c:forEach>
                                    <%--<option value="院士" ${gezlList.title=='院士'?'selected':'' }>院士</option>
                                    <option value="教授"  ${gezlList.title=='教授'?'selected':'' }>教授</option>
                                    <option value="正高"  ${gezlList.title=='正高'?'selected':'' }>正高</option>
                                    <option value="副教授" ${gezlList.title=='副教授'?'selected':'' }>副教授</option>
                                    <option value="副高" ${gezlList.title=='副高'?'selected':'' }>副高</option>
                                    <option value="高级讲师" ${gezlList.title=='高级讲师'?'selected':'' }>高级讲师</option>
                                    <option value="讲师" ${gezlList.title=='讲师'?'selected':'' }>讲师</option>
                                    <option value="主任药师" ${gezlList.title=='主任药师'?'selected':'' }>主任药师</option>
                                    <option value="副主任药师" ${gezlList.title=='副主任药师'?'selected':'' }>副主任药师</option>
                                    <option value="主管药师" ${gezlList.title=='主管药师'?'selected':'' }>主管药师</option>
                                    <option value="其他" ${gezlList.title=='其他'?'selected':'' }>其他</option>--%>
                                </select></td>
                            <td><span>&ensp;联系电话：</span>
                                <input class="cg_input" name="telephone" value="${gezlList.telephone}" id="telephone"
                                       onblur="LengthLimit(this,30)"
                                       maxlength="30"/>
                            </td>
                            <td><span class="btbs">*</span><span>手&emsp;&emsp;机：</span>
                                <input class="cg_input" name="handphone" value="${gezlList.handphone}" id="handphone" maxlength="30"/>
                            </td>

                        </tr>
                        <tr>
                            <td><span class="btbs">*</span><span style="width: 70px">邮&emsp;&emsp;箱：</span>
                                <input class="cg_input" name="email" value="${gezlList.email}" id="email"  maxlength="40"/></td>

                            <td><span class="btbs">*</span><span>证件类型：</span>
                                <select class="select-input" id="zjlx" name="idtype">
                                    <option value="0" ${gezlList.idtype=='0'?'selected':'' }>身份证</option>
                                    <option value="1" ${gezlList.idtype=='1'?'selected':'' }>护照</option>
                                    <option value="2" ${gezlList.idtype=='2'?'selected':'' }>军官证</option>
                                </select></td>
                            <td><span class="btbs">*</span><span>证件号码：</span>
                                <input class="cg_input" disabled name="idcard" value="${gezlList.idcard}" id="idcard"  maxlength="18"/></td>
                            <td><span class="btbs">*</span><span>学&emsp;&emsp;历：</span>
                                <select id="education" name="education">
                                    <c:forEach items="${writerUserDegree}" var="dic">
                                        <option value="${dic.code}" ${gezlList.education==dic.code?'selected':'' }>${dic.name}</option>
                                    </c:forEach>
                                    <%--<option value="0" ${gezlList.education=='0'?'selected':'' }>专科</option>
                                    <option value="1" ${gezlList.education=='1'?'selected':'' }>本科</option>
                                    <option value="2" ${gezlList.education=='2'?'selected':'' }>硕士</option>
                                    <option value="4" ${gezlList.education=='4'?'selected':'' }>博士</option>
                                    <option value="3" ${gezlList.education=='3'?'selected':'' }>博士后</option>--%>
                                </select></td>
                        </tr>
                        <tr>
                            <td><span>&ensp;邮&emsp;&emsp;编：</span>
                                <input class="cg_input" name="postcode" value="${gezlList.postcode}" id="postcode"
                                       onblur="LengthLimit(this,20)"
                                       maxlength="20"/>
                            </td>
                            <td colspan="2"><span class="btbs">*</span><span>地&emsp;&emsp;址：</span>
                                <input class="cg_input" style="width: 488px;" name="address" value="${gezlList.address}" id="address"  maxlength="50"/></td>
                            <td><span>&ensp;专业特长：</span>
                                <input class="cg_input" name="expertise" value="${gezlList.expertise}" id="expertise"  maxlength="50" placeholder="疾病诊治及研究方向"/></td>
                        </tr>
                        <tr>
                            <td><span>&ensp;卡&emsp;&emsp;号：</span>
                                <input class="cg_input" name="banknumber" value="${gezlList.banknumber}" id="banknumber"
                                       onblur="LengthLimit(this,20)"
                                       maxlength="20"/>
                            </td>
                            <td colspan="3"><span>&ensp;开户行&emsp;：</span>
                                <input class="cg_input" style="width: 488px;" name="bankaddress" value="${gezlList.bankaddress}" id="bankaddress"  maxlength="100"/>
                                <span style="color: red">(具体到银行详细名称)</span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <!--主要学习经历-->
            <div class="sbxq_item" id="zyxxjl" wrapper_key="is_edu_exp">
                <div>
                    <span id="tsxz_span3"></span>
                    <span class="tsxz_title">学习经历</span>
                    <span class="tsxz_ts" id="zyxxjl_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span class="tsxz_xt" id="zyxxjl_xt">（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_xxjl">
                        <thead>
                        <tr>
                            <td width="220px">起止时间</td>
                            <td width="260px">学校名称</td>
                            <td width="210px">所学专业</td>
                            <td width="138px">学历</td>
                            <td>备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty stuList[0]}">
                            <tr>
                                <td>
                                    <input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" max="'$#xx_jssj'" name="xx_kssj" id="xx_kssj" value=""
                                           style="width: 80px;" maxlength="20"/>
                                    -
                                    <input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" min="'$#xx_kssj'" name="xx_jssj" id="xx_jssj" value=""
                                           style="width: 80px;" maxlength="20"/>
                                </td>
                                <td><input class="cg_input" id="xx_school_name" style="width: 230px"
                                           name="xx_school_name" value="" placeholder="学校名称" maxlength="80"/></td>
                                <td><input class="cg_input" id="xx_major" name="xx_major" value="" placeholder="所学专业"
                                           maxlength="50"/></td>
                                <td><input class="cg_input" id="xx_degree" name="xx_degree" value=""
                                           style="width: 110px;" placeholder="学历" maxlength="30"/></td>
                                <td><input class="cg_input" name="xx_note" value="" style="width: 240px;"
                                           placeholder="备注" maxlength="100"/>
                                    <input type="hidden" name="xx_id" value="">
                                </td>
                                <td><img class="add_img" src="${ctx}/statics/image/add.png"
                                         onclick="javascript:add_xxjl()"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="list" items="${stuList}" varStatus="status">
                            <tr id="xxjl_${status.count}">
                                <td>
                                    <input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" name="xx_kssj" max="'$#xx_jssj_${status.count}'"
                                           id="xx_kssj_${status.count}" value="${list.date_begin}"
                                           style="width: 80px;"/>
                                    -
                                    <input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" name="xx_jssj" min="'$#xx_kssj_${status.count}'"
                                           id="xx_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
                                </td>
                                <td><input class="cg_input" style="width: 230px" name="xx_school_name"
                                           id="xx_school_name_${status.count}" value="${list.school_name}"
                                           placeholder="学校名称" maxlength="80"/></td>
                                <td><input class="cg_input" name="xx_major" id="xx_major_${status.count}"
                                           value="${list.major}" placeholder="所学专业" maxlength="50"/></td>
                                <td><input class="cg_input" name="xx_degree" id="xx_degree_${status.count}"
                                           value="${list.degree}" style="width: 110px;" placeholder="学历"
                                           maxlength="30"/></td>
                                <td><input class="cg_input" name="xx_note" value="${list.note}" style="width: 240px;"
                                           placeholder="备注" maxlength="100"/>
                                    <input type="hidden" name="zdjy" value="xx_kssj_${status.count}"/>
                                    <input type="hidden" name="xx_id" value="${list.per_id}">
                                        <%--<input type="hidden" name="zdjy" value="xx_kssj_${status.count},xx_jssj_${status.count},xx_school_name_${status.count},xx_major_${status.count},xx_degree_${status.count}"/>--%>
                                </td>
                                <td>
                                    <c:choose>
                                        <%-- <c:when test="${status.count == fn:length(stuList)}"> --%>
                                        <c:when test="${status.count == 1}">
                                            <img class="add_img" src="${ctx}/statics/image/add.png"
                                                 onclick="javascript:add_xxjl()"/>
                                        </c:when>
                                        <c:otherwise>
                                            <img class="add_img" src="${ctx}/statics/image/del.png"
                                                 onclick="javascript:del_tr('xxjl_${status.count}')"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--主要工作经历-->
            <div class="sbxq_item" id="gzjl" wrapper_key="is_work_exp">
                <div>
                    <span id="tsxz_span4"></span>
                    <span class="tsxz_title">工作经历</span>
                    <span class="tsxz_ts" id="gzjl_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span class="tsxz_xt" id="gzjl_xt">（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_gzjl">
                        <thead>
                        <tr>
                            <td width="220px">起止时间</td>
                            <td width="400px">工作单位</td>
                            <td width="220px">职位</td>
                            <td>备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty workList[0]}">
                            <tr>
                                <td>
                                    <input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" name="gz_kssj" max="'$#gz_jssj'" id="gz_kssj" value=""
                                           style="width: 80px;"/>
                                    -
                                    <input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" name="gz_jssj" min="'$#gz_kssj'" id="gz_jssj" value=""
                                           style="width: 80px;"/>
                                </td>
                                <td><input class="cg_input" id="gz_org_name" style="width: 370px;" name="gz_org_name"
                                           value="" placeholder="工作单位" maxlength="100"/></td>
                                <td><input class="cg_input" id="gz_position" name="gz_position" value=""
                                           placeholder="职位" maxlength="100"/></td>
                                <td><input class="cg_input" name="gz_note" value="" style="width: 230px;"
                                           placeholder="备注" maxlength="100"/>
                                    <input type="hidden" name="gz_id" value=""></td>
                                <td><img class="add_img" src="${ctx}/statics/image/add.png"
                                         onclick="javascript:add_gzjl()"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="list" items="${workList}" varStatus="status">
                            <tr id="gzjl_${status.count}">
                                <td>
                                    <input class="cg_input" placeholder="开始时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" name="gz_kssj" max="'$#gz_jssj_${status.count}'"
                                           id="gz_kssj_${status.count}" value="${list.date_begin}"
                                           style="width: 80px;"/>
                                    -
                                    <input class="cg_input" placeholder="结束时间" calendar format="'yyyy-mm-dd'"
                                           z-index="100" name="gz_jssj" min="'$#gz_kssj_${status.count}'"
                                           id="gz_jssj_${status.count}" value="${list.date_end}" style="width: 80px;"/>
                                </td>
                                <td><input class="cg_input" style="width: 370px;" name="gz_org_name"
                                           id="gz_org_name_${status.count}" value="${list.org_name}" placeholder="工作单位"
                                           maxlength="100"/></td>
                                <td><input class="cg_input" name="gz_position" id="gz_position_${status.count}"
                                           value="${list.position}" placeholder="职位" maxlength="100"/></td>
                                <td><input class="cg_input" name="gz_note" value="${list.note}" style="width: 230px;"
                                           placeholder="备注" maxlength="100"/>
                                    <input type="hidden" name="zdjy" value="gz_kssj_${status.count}"/>
                                    <input type="hidden" name="gz_id" value="${list.per_id}">
                                        <%--<input type="hidden" name="zdjy" value="gz_kssj_${status.count},gz_jssj_${status.count},gz_org_name_${status.count},gz_position_${status.count}"/>--%>
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png"
                                             onclick="javascript:add_gzjl()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png"
                                             onclick="javascript:del_tr('gzjl_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--主要学术兼职-->
            <div class="sbxq_item" id="xsjz" wrapper_key="is_acade">
                <div>
                    <span id="tsxz_span10"></span>
                    <span class="tsxz_title">主要学术兼职</span>
                    <span class="tsxz_ts" id="xsjz_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span id="xsjz_xt" class="tsxz_xt">（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_xsjz">
                        <thead>
                        <tr>
                            <td width="400px">兼职学术组织</td>
                            <td width="280px">级别</td>
                            <td width="220px">职务</td>
                            <td>备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty zjxsList[0]}">
                            <tr>
                                <td><input class="cg_input" maxlength="100" style="width: 370px" name="xs_org_name"
                                           id="xs_org_name" value="" placeholder="学术组织"/></td>
                                <td style="color: #333333;">
                                    <table class="radio_tb" style="width: 100%;">
                                        <tr>
                                            <td><input type="radio" name="xs_rank_a" value="0" checked="checked"/>无</td>
                                            <td><input type="radio" name="xs_rank_a" value="1"/>国际</td>
                                            <td><input type="radio" name="xs_rank_a" value="2"/>国家</td>
                                            <td><input type="radio" name="xs_rank_a" value="3"/>省部</td>
                                            <td><input type="radio" name="xs_rank_a" value="4"/>市级</td>
                                        </tr>
                                    </table>
                                    <input type="hidden" name="xs_rank" value="xs_rank_a"/>
                                </td>
                                <td><input class="cg_input" maxlength="50" id="xs_position" name="xs_position" value=""
                                           placeholder="职务"/></td>
                                <td><input class="cg_input" maxlength="100" name="xs_note" value=""
                                           style="width: 180px;" placeholder="备注"/>
                                    <input type="hidden" name="xs_id" value=""></td>
                                </td>
                                <td><img class="add_img" src="${ctx}/statics/image/add.png"
                                         onclick="javascript:add_xsjz()"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="list" items="${zjxsList}" varStatus="status">
                            <tr id="xsjz_${status.count}">
                                <td><input class="cg_input" maxlength="100" style="width: 370px" name="xs_org_name"
                                           id="xs_org_name_${status.count}" value="${list.org_name}"
                                           placeholder="学术组织"/></td>
                                <td style="color: #333333;">
                                    <table class="radio_tb" style="width: 100%;">
                                        <tr>
                                            <td><input type="radio" name="xs_rank_${status.count}"
                                                       value="0" ${list.rank=='0'?'checked':'' }/>无
                                            </td>
                                            <td><input type="radio" name="xs_rank_${status.count}"
                                                       value="1" ${list.rank=='1'?'checked':'' }/>国际
                                            </td>
                                            <td><input type="radio" name="xs_rank_${status.count}"
                                                       value="2" ${list.rank=='2'?'checked':'' }/>国家
                                            </td>
                                            <td><input type="radio" name="xs_rank_${status.count}"
                                                       value="3" ${list.rank=='3'?'checked':'' }/>省部
                                            </td>
                                            <td><input type="radio" name="xs_rank_${status.count}"
                                                       value="4" ${list.rank=='4'?'checked':'' }/>其他
                                            </td>
                                        </tr>
                                    </table>
                                    <input type="hidden" name="xs_rank" value="xs_rank_${status.count}"/>
                                </td>
                                <td><input class="cg_input" maxlength="50" id="xs_position_${status.count}"
                                           name="xs_position" value="${list.position}" placeholder="职务"/></td>
                                <td><input class="cg_input" maxlength="100" name="xs_note" value="${list.note}"
                                           style="width: 180px;" placeholder="备注"/>
                                    <input type="hidden" name="zdjy" value="xs_org_name_${status.count}"/>
                                    <input type="hidden" name="xs_id" value="${list.per_id}">
                                        <%--<input type="hidden" name="zdjy" value="xs_org_name_${status.count},xs_position_${status.count}"/>--%>
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png"
                                             onclick="javascript:add_xsjz()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png"
                                             onclick="javascript:del_tr('xsjz_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--人卫社教材编写情况-->
            <div class="sbxq_item" id="rwsjcbx" wrapper_key="is_pmph_textbook">
                <div>
                    <span id="tsxz_span5"></span>
                    <span class="tsxz_title">人卫社教材编写情况</span>
                    <span class="tsxz_ts" id="rwsjcbx_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span class="tsxz_xt" id="rwsjcbx_xt">（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_rwsjcbx">
                        <thead>
                        <tr>
                            <td width="350px">教材名称</td>
                            <td width="120px">级别</td>
                            <td width="120px">编写职务</td>
                            <td width="100px">数字编委</td>
                            <td width="120px">出版时间</td>
                            <td width="120px">标准书号</td>
                            <td>备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty rwsjcList[0]}">
                            <tr>
                                <td><input class="cg_input" maxlength="100" style="width: 320px;"
                                           name="pmph_material_name" id="pmph_material_name" value=""
                                           style="width: 200px;" placeholder="教材名称"/></td>
                                <td>
                                    <select id="pmph_rank" name="pmph_rank">
                                        <option value="0">无</option>
                                        <option value="1">国家</option>
                                        <option value="2">省部</option>
                                        <option value="3">协编</option>
                                        <option value="4">校本</option>
                                        <option value="5">其他</option>
                                    </select>
                                </td>
                                <td>
                                    <select id="pmph_position" name="pmph_position">
                                        <option value="0">无</option>
                                        <option value="1">主编</option>
                                        <option value="2">副主编</option>
                                        <option value="3">编委</option>
                                    </select>
                                </td>
                                <td style="color: #333333;">
                                    <table class="radio_tb" style="width: 80px;">
                                        <tr>
                                            <td><input type="radio" name="pmph_is_digital_editor_a" value="1"/>是</td>
                                            <td><input type="radio" name="pmph_is_digital_editor_a" value="0"
                                                       checked="checked"/>否
                                            </td>
                                        </tr>
                                    </table>
                                    <input type="hidden" name="pmph_is_digital_editor"
                                           value="pmph_is_digital_editor_a"/>
                                </td>
                                <td><input class="cg_input" id="pmph_publish_date" name="pmph_publish_date"
                                           placeholder="出版时间" calendar format="'yyyy-mm-dd'" z-index="100" value=""
                                           style="width: 100px;"/></td>
                                <td><input class="cg_input" maxlength="50" id="pmph_isbn" name="pmph_isbn" value=""
                                           style="width: 100px;" placeholder="978-7-117-"/></td>
                                <td><input class="cg_input" maxlength="100" id="pmph_note" name="pmph_note" value=""
                                           placeholder="备注" style="width: 140px;"/>
                                    <input type="hidden" name="pmph_id" value=""></td>
                                </td>
                                <td><img class="add_img" src="${ctx}/statics/image/add.png"
                                         onclick="javascript:add_rwsjcbx()"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="list" items="${rwsjcList}" varStatus="status">
                            <tr id="rwsjcbx_${status.count}">
                                <td><input class="cg_input" maxlength="100" style="width: 320px;"
                                           name="pmph_material_name" id="pmph_material_name_${status.count}"
                                           value="${list.material_name}" style="width: 200px;" placeholder="教材名称"/></td>
                                <td>
                                    <select id="pmph_rank_${status.count}" name="pmph_rank">
                                        <option value="0" ${list.rank=='0'?'selected':'' }>无</option>
                                        <option value="1" ${list.rank=='1'?'selected':'' }>国家</option>
                                        <option value="2" ${list.rank=='2'?'selected':'' }>省部</option>
                                        <option value="3" ${list.rank=='3'?'selected':'' }>协编</option>
                                        <option value="4" ${list.rank=='4'?'selected':'' }>校本</option>
                                        <option value="5" ${list.rank=='5'?'selected':'' }>其他</option>
                                    </select>
                                    <input type="hidden" id="pmph_rank_sl" name="pmph_rank_sl"
                                           value="pmph_rank_${status.count}"/>
                                </td>
                                <td>
                                    <select id="pmph_position_${status.count}" name="pmph_position">
                                        <option value="0" ${list.position=='0'?'selected':'' }>无</option>
                                        <option value="1" ${list.position=='1'?'selected':'' }>主编</option>
                                        <option value="2" ${list.position=='2'?'selected':'' }>副主编</option>
                                        <option value="3" ${list.position=='3'?'selected':'' }>编委</option>
                                    </select>
                                    <input type="hidden" id="pmph_sl" name="pmph_sl"
                                           value="pmph_position_${status.count}"/>
                                </td>
                                <td style="color: #333333;">
                                    <table class="radio_tb" style="width: 80px;">
                                        <tr>
                                            <td><input type="radio" name="pmph_is_digital_editor_${status.count}"
                                                       value="1" ${list.is_digital_editor=='1'?'checked':'' } />是
                                            </td>
                                            <td><input type="radio" name="pmph_is_digital_editor_${status.count}"
                                                       value="0" ${list.is_digital_editor=='0'?'checked':'' }/>否
                                            </td>
                                        </tr>
                                    </table>
                                    <input type="hidden" name="pmph_is_digital_editor"
                                           value="pmph_is_digital_editor_${status.count}"/>
                                </td>
                                <td><input class="cg_input" placeholder="出版时间" id="pmph_publish_date_${status.count}"
                                           calendar format="'yyyy-mm-dd'" z-index="100" name="pmph_publish_date"
                                           value="${list.publish_date}" style="width: 100px;"/></td>
                                <td><input class="cg_input" name="pmph_isbn" id="pmph_isbn_${status.count}"
                                           value="${list.isbn}" style="width: 100px;" placeholder="标准书号"
                                           maxlength="50"/></td>
                                <td><input class="cg_input" maxlength="100" name="pmph_note" value="${list.note}"
                                           style="width: 140px;" placeholder="备注"/>
                                    <input type="hidden" name="zdjy" value="pmph_material_name_${status.count}"/>
                                    <input type="hidden" name="pmph_id" value="${list.per_id}">
                                        <%--<input type="hidden" name="zdjy" value="pmph_material_name_${status.count},pmph_publish_date_${status.count},pmph_isbn_${status.count}"/>--%>
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png"
                                             onclick="javascript:add_rwsjcbx()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png"
                                             onclick="javascript:del_tr('rwsjcbx_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!--图书出版情况-->
            <div class="sbxq_item" id="zbxszz" wrapper_key="is_monograph">
                <div>
                    <span id="tsxz_span7"></span>
                    <span class="tsxz_title">图书出版情况</span>
                    <span class="tsxz_ts" id="zbxszz_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span class="tsxz_xt" id="zbxszz_xt">（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_zbxszz">
                        <thead>
                        <tr>
                            <td width="230px">专著名称</td>
                            <td width="150px">专著发表日期</td>
                            <td width="140px">出版方式</td>
                            <td width="200px">出版单位</td>
                            <td width="150px">出版时间</td>
                            <td>备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty monographList[0]}">
                            <tr>
                                <td><input class="cg_input" name="zb_monograph_name" id="zb_monograph_name" value=""
                                           style="width: 200px;" placeholder="教材名称" maxlength="50"/></td>
                                <td><input class="cg_input" name="zb_monograph_date" id="zb_monograph_date" value=""
                                           style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="发表日期"/>
                                </td>
                                <td style="color: #333333;">
                                    <table class="radio_tb" style="width: 140px;">
                                        <tr>
                                            <td><input type="radio" name="is_self_paid_a" value="0" checked="checked"/>公费
                                            </td>
                                            <td><input type="radio" name="is_self_paid_a" value="1"/>自费</td>
                                        </tr>
                                    </table>
                                    <input type="hidden" name="is_self_paid" value="is_self_paid_a"/>
                                </td>
                                <td><input class="cg_input" id="zb_publisher" name="zb_publisher" value=""
                                           style="width: 180px;" placeholder="出版单位" maxlength="50"/></td>
                                <td><input class="cg_input" id="zb_publish_date" name="zb_publish_date" value=""
                                           style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/>
                                </td>
                                <td><input class="cg_input" name="zb_note" value="" style="width: 200px;"
                                           placeholder="备注" maxlength="100"/>
                                    <input type="hidden" name="zb_id" value=""></td>
                                </td>
                                <td><img class="add_img" src="${ctx}/statics/image/add.png"
                                         onclick="javascript:add_zbxszz()"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="list" items="${monographList}" varStatus="status">
                            <tr id="zbxszz_${status.count}">
                                <td><input class="cg_input" name="zb_monograph_name"
                                           id="zb_monograph_name_${status.count}" value="${list.monograph_name}"
                                           style="width: 200px;" placeholder="教材名称" maxlength="50"/></td>
                                <td><input class="cg_input" name="zb_monograph_date"
                                           id="zb_monograph_date_${status.count}" value="${list.monograph_date}"
                                           style="width: 120px;" calendar format="'yyyy-mm-dd'" placeholder="出版时间"/>
                                </td>
                                <td style="color: #333333;">
                                    <table class="radio_tb" style="width: 140px;">
                                        <tr>
                                            <td><input type="radio" name="is_self_paid_${status.count}"
                                                       value="0" ${list.is_self_paid=='0'?'checked':'' }/>公费
                                            </td>
                                            <td><input type="radio" name="is_self_paid_${status.count}"
                                                       value="1"  ${list.is_self_paid=='1'?'checked':'' }/>自费
                                            </td>
                                        </tr>
                                    </table>
                                    <input type="hidden" name="is_self_paid" value="is_self_paid_${status.count}"/>
                                </td>
                                <td><input class="cg_input" name="zb_publisher" id="zb_publisher_${status.count}"
                                           value="${list.publisher}" style="width: 180px;" placeholder="出版单位"
                                           maxlength="50"/></td>
                                <td><input class="cg_input" name="zb_publish_date" id="zb_publish_date_${status.count}"
                                           value="${list.publish_date}" style="width: 120px;"
                                           calendar format="'yyyy-mm-dd'" placeholder="出版时间"/></td>
                                <td><input class="cg_input" name="zb_note" value="${list.note}" style="width: 200px;"
                                           placeholder="备注" maxlength="100"/>
                                    <input type="hidden" name="zdjy" value="zb_monograph_name_${status.count}"/>
                                    <input type="hidden" name="zb_id" value="${list.per_id}">
                                        <%-- <input type="hidden" name="zdjy" value="zb_monograph_name_${status.count},zb_publisher_${status.count},zb_publish_date_${status.count}"/>--%>
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png"
                                             onclick="javascript:add_zbxszz()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png"
                                             onclick="javascript:del_tr('zbxszz_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!--主编或参编图书情况-->
            <div class="sbxq_item" id="zbcbtsqk" wrapper_key="is_edit_book">
                <div>
                    <span id="tsxz_span6"></span>
                    <span class="tsxz_title">主编或参编图书情况</span>
                    <span class="tsxz_ts" id="zbcb_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span class="tsxz_xt" id="zbcb_xt">（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_zbtsqk">
                        <thead>
                        <tr>
                            <td width="350px">名称</td>
                            <td width="330px">出版单位</td>
                            <td width="160px">出版时间</td>
                            <td>备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty editorList[0]}">
                            <tr>
                                <td><input class="cg_input" maxlength="100" style="width: 320px"
                                           name="zbts_material_name" id="zbts_material_name" value="" style="width: 260px;"
                                           placeholder="教材名称"/></td>
                                <td><input class="cg_input" name="zbts_publisher" value=""  placeholder="出版单位"
                                           style="width: 300px;" maxlength="20"/></td>
                                <td><input class="cg_input" name="zbts_publish_date" id="zbts_publish_date" value=""
                                           placeholder="出版时间" calendar format="'yyyy-mm-dd'" z-index="100"
                                           style="width: 130px;"/></td>
                                <td><input class="cg_input" maxlength="100" name="zbts_note" value=""
                                           style="width: 240px;" placeholder="备注"/>
                                    <input type="hidden" name="zbts_id" value=""></td>
                                </td>
                                <td><img class="add_img" src="${ctx}/statics/image/add.png"
                                         onclick="javascript:add_zbtsqk()"/></td>
                            </tr>
                        </c:if>
                        <c:forEach var="list" items="${editorList}" varStatus="status">
                            <tr id="zbtsqk_${status.count}">
                                <td><input class="cg_input" maxlength="100" style="width: 320px" name="zbts_material_name"
                                           id="zbts_material_name_${status.count}" value="${list.material_name}"
                                           style="width: 260px;" placeholder="教材名称"/></td>
                                <td><input class="cg_input" name="zbts_publisher" value="${list.publisher}"  placeholder="出版单位"
                                           style="width: 300px;" maxlength="20"/></td>
                                <td><input class="cg_input" name="zbts_publish_date" id="zbts_publish_date_${status.count}"
                                           value="${list.publish_date}" placeholder="出版时间" calendar
                                           format="'yyyy-mm-dd'" z-index="100" style="width: 130px;"/></td>
                                <td><input class="cg_input" maxlength="100" name="zbts_note" value="${list.note}"
                                           style="width: 240px;" placeholder="备注"/>
                                    <input type="hidden" name="zdjy" value="zbts_material_name_${status.count}"/>
                                    <input type="hidden" name="zbts_id" value="${list.per_id}">
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png"
                                             onclick="javascript:add_zbtsqk()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png"
                                             onclick="javascript:del_tr('zbtsqk_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!--文章发表情况-->
            <div class="sbxq_item" id="wzfbqk" wrapper_key="is_article_published">
                <div>
                    <span id="tsxz_span6"></span>
                    <span class="tsxz_title">文章发表情况</span>
                    <span class="tsxz_ts" id="wzfbqk_bt"><img src="${ctx}/statics/image/btxx.png"/></span>
                    <span class="tsxz_xt" id="wzfbqk_xt" >（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_wzfbqk">
                        <thead>
                        <tr>
                            <td width="250px">题目</td>
                            <td width="130px">期刊名称</td>
                            <td width="160px">年、卷、期</td>
                            <td width="220px">期刊级别（SCI或国内核心期刊）</td>
                            <td width="100px">备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty wzfbqkList[0]}">
                            <tr>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100"  id="wzfbqk_material_name" name="wzfb_name" <%--id="jc_material_name"--%> value="" placeholder="文章题目"/></td>
                                <td class="xztd"><input class="cg_input xzip" name="wzfb_qkmc" value=""  maxlength="20" placeholder="期刊名称"/></td>
                                <td class="xztd"><input class="cg_input xzip" name="wzfb_njq"  value="" placeholder="年、卷、期"/></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="wzfb_qklb" value=""  placeholder="期刊级别（SCI或国内核心期刊）"/></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="wzfb_note" value=""  placeholder="备注"/></td>
                                <td class="xztd"><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_wzfbqk()"/></td>
                                <input type="hidden" name="wzfbxq_id" value="">
                            </tr></c:if>
                        <c:forEach var="list" items="${wzfbqkList}" varStatus="status">
                            <tr id="wzfbxq_${status.count}">
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="wzfb_name" id="wzfbqk_name_${status.count}" value="${list.title}"/></td>
                                <td class="xztd"><input class="cg_input xzip" name="wzfb_qkmc" value="${list.periodical_title}" placeholder="期刊名称" maxlength="20"/></td>
                                <td class="xztd"><input class="cg_input xzip" name="wzfb_njq"  value="${list.year_volume_period}" placeholder="年、卷、期" /></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="wzfb_qklb" value="${list.periodical_level}"  placeholder="期刊级别（SCI或国内核心期刊）"/></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="wzfb_note" value="${list.note}" placeholder="备注"/>
                                    <input type="hidden" name="wzfb" value="wzfb_name_${status.count}"/>
                                    <input type="hidden" name="wzfbxq_id" value="${list.id}">
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_wzfbqk()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('wzfbxq_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!--本专业获奖情况-->
            <div class="sbxq_item" id="bzyhjqk" wrapper_key="is_profession_award">
                <div>
                    <span id="bzyhjqk_img"></span>
                    <span class="tsxz_title">本专业获奖情况</span>
                    <span class="tsxz_ts" id="bzyhjqk_bt"><img src="${ctx}/statics/image/btxx.png" /></span>
                    <span class="tsxz_xt" id="bzyhjqk_xt" >（选填）</span>
                </div>
                <div class="content">
                    <table class="tab_2" id="tab_bzyhjqk">
                        <thead>
                        <tr>
                            <td width="350px">名称</td>
                            <td width="350px">级别（国家、省、市、单位）</td>
                            <td width="150px">备注</td>
                            <td width="78px">添加</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty bzyhjqkList[0]}">
                            <tr>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100"  id="bzyhjqk_material_name" name="hjqk_name" value="" placeholder="名称"/></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="hjqk_jb" value=""  placeholder="级别（国家、省、市、单位）"/></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="hjqk_note" value=""  placeholder="备注"/></td>
                                <td class="xztd"><img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_bzyhjqk()"/></td>
                                <input type="hidden" name="bzyhqqk_id" value="">
                            </tr></c:if>
                        <c:forEach var="list" items="${bzyhjqkList}" varStatus="status">
                            <tr id="bzyhjqk_${status.count}">
                                <td class="xztd"><input class="cg_input xzip" maxlength="100"  name="hjqk_name" id="bzyhjqk_name_${status.count}" value="${list.title}" placeholder="名称"/></td>
                                <td class="xztd"><input class="cg_input xzip" name="hjqk_jb" value="${list.rank}"  maxlength="20" placeholder="级别（国家、省、市、单位）"/></td>
                                <td class="xztd"><input class="cg_input xzip" maxlength="100" name="hjqk_note" value="${list.note}"  placeholder="备注"/>
                                    <input type="hidden" name="bzyhqqk" value="zbts_material_name_${status.count}"/>
                                    <input type="hidden" name="bzyhqqk_id" value="${list.id}">
                                </td>
                                <td><c:choose>
                                    <c:when test="${status.count == 1}">
                                        <img class="add_img" src="${ctx}/statics/image/add.png" onclick="javascript:add_bzyhjqk()"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img class="add_img" src="${ctx}/statics/image/del.png" onclick="javascript:del_tr('bzyhjqk_${status.count}')"/>
                                    </c:otherwise>
                                </c:choose></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!--扩展信息-->
            <c:forEach var="zjkzxx" items="${zjkzqkList}" varStatus="status">
                <div class="sbxq_item1">
                    <div>
                        <span id="tsxz_span9"></span>
                        <span class="tsxz_title">${zjkzxx.extension_name}</span>
                        <c:choose>
                            <c:when test="${zjkzxx.is_required == true}">
                                <span class="tsxz_ts" style="display: inline;"><img
                                        src="${ctx}/statics/image/btxx.png"/></span>
                            </c:when>
                            <c:otherwise>
                                <span class="tsxz_xt" style="display: inline;">（选填）</span>
                            </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="extension_id" value="${zjkzxx.id}"/>
                    </div>
                    <div class="content">
                        <textarea class="text_cl" id="${zjkzxx.is_required}_${status.count}" name="kz_content"
                                  maxlength="1000">${zjkzxx.content}</textarea>
                        <input type="hidden" name="zjkzxx" value="${zjkzxx.is_required}_${status.count}"/>
                    </div>
                </div>
            </c:forEach>

            <!--备注-->
            <div class="sbxq_item1" style="display: block">
                <div>
                    <span id="beizhu"></span>
                    <span class="tsxz_title">备注</span>
                    <span class="tsxz_xt" style="display: inline;">（选填）</span>
                </div>
                <div class="content">
                    <textarea class="text_cl" name="remark" placeholder="可填写您擅长的专业或者学科方向等相关内容......">${gezlList.remark}</textarea>
                </div>
            </div>

            <!-- 学科分类-->
            <div class="sbxq_item1" id="xkfl_qy">
                <div>
                    <input type="hidden" id="xkflbt">
                    <span id="tsxz_span8"></span>
                    <span class="tsxz_title"><img src="${ctx}/statics/image/btxx.png" id="xkflbx"/>学科分类(可多选)</span>
                    <span class="el-button" onclick="javascript:SubjectdAdd('${materialMap.expert_type}')">添加学科分类</span>
                </div>
                <div class="sbdw" id="xkfladd">
                    <span class="btmc">学科分类：</span>
                    <c:forEach var="subject" items="${subjectList}" varStatus="status">
                    <span class="el-tag" id="xkfl_${status.count}">${subject.type_name}
                    	<input name="subjectId" type="hidden" value="${subject.product_subject_type_id}"/>
                        <span style="margin-left: 8px;cursor: pointer;" onclick="del('xkfl_${status.count}')">X</span></span>
                    </c:forEach>
                </div>
            </div>

            <!-- 内容分类-->
            <div class="sbxq_item1" id="nrfl_qy">
                <div>
                    <input type="hidden" id="nrflbt">
                    <span id="tsxz_span12"></span>
                    <span class="tsxz_title"><img src="${ctx}/statics/image/btxx.png" id="nrflbx"/>内容分类(可多选)</span>
                    <span class="el-button" onclick="javascript:ContentAdd('${materialMap.expert_type}')">添加内容分类</span>
                </div>
                <div class="sbdw" id="nrfladd">
                    <span class="btmc">内容分类：</span>
                    <c:forEach var="content" items="${contentList}" varStatus="status">
                    <span class="el-tag" id="nrfl_${content.product_content_type_id}">${content.type_name}<input name="contentId" type="hidden" value="${content.product_content_type_id}"/>
                        <span style="margin-left: 8px;cursor: pointer;" onclick="del('nrfl_${content.product_content_type_id}')">X</span></span>
                    </c:forEach>
                </div>
            </div>

            <!-- 申报专业-->
            <div class="sbxq_item1" id="sbzy_qy">
                <div>
                    <input type="hidden" id="sbzybt">
                    <span id="sbzytb"></span>
                    <span class="tsxz_title"><img src="${ctx}/statics/image/btxx.png" id="sbzybx"/>申报专业(可多选)</span>
                    <span class="el-button" onclick="javascript:sbzyAdd('${materialMap.expert_type}')">添加申报专业</span>
                </div>
                <div class="sbdw" id="sbzyadd">
                    <span class="btmc">申报专业：</span>
                    <c:forEach var="sbzy" items="${sbzyList}" varStatus="status">
                    <span class="el-tag" id="sbzy_${status.count}">${sbzy.type_name}<input name="sbzyId" type="hidden" value="${sbzy.product_profession_type_id}"/>
                        <span style="margin-left: 8px;cursor: pointer;" onclick="del('sbzy_${status.count}')">X</span></span>
                    </c:forEach>
                </div>
            </div>
            <%--<div class="sbxq_item" id="szdwyj">
                <div>
                    <span id="tsxz_span13"></span>
                    <span class="tsxz_title"><img src="${ctx}/statics/image/btxx.png" />所在单位意见<span style="color: red">(上传单位盖章的申报表)</span></span>
                </div>
                <div style="height: 30px;margin-top: 10px;">
                    <div class="scys" id="dwyjsc"><span>上传文件</span></div>
                    <div id="fileNameDiv" class="fileNameDiv"></div>
                    <input type="hidden" name="syllabus_id" id="syllabus_id" value="${gezlList.unit_advise}"/>
                    <input type="hidden" name="syllabus_name" id="syllabus_name" value="${gezlList.syllabus_name}"/>
                    <div class="filename"><a href="javascript:" onclick="downLoadProxy('${gezlList.unit_advise}')"
                                             title="${gezlList.syllabus_name}">${gezlList.syllabus_name}</a></div>
                </div>
            </div>--%>
            
            <!-- 申报单位-->
            <div class="sbxq_item1">
                <div>
                    <span id="tsxz_span8"></span>
                    <span class="tsxz_title">请选择您的申报单位</span>
                </div>
                <div class="sbdw">
                    <span class="btbs">*</span><span class="btmc">申报单位：</span>
                    <input class="cg_input" id="sbdw_name" name="sbdw_name" value="${org.org_name}"
                           style="width: 300px;" onclick="javascript:orgAdd('${materialMap.product_id}')"
                           readonly="readonly"/>
                    <input type="hidden" id="sbdw_id" name="sbdw_id" value="${gezlList.org_id}" style="width: 300px;"/>
                </div>
            </div>
            
            
            <!-- 院校推荐意见-->
            <div class="yijian">
                <div class="tujian01">院校推荐意见:</div>
                <div class="tujian02">
                    <div class="qianzi">负责人签字:</div>
                    <div class="gaizhang">(院校盖章)</div>
                </div>
                <div class="tujian03">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</div>
            </div>
            <hr style=" height:1px;border:none;border-top:1px #c1c1c1 dashed;margin-top: 30px;">
            <div class="button" id="button_cz">
                <div class="div_butt">
                    <div class="bt_tj" id="buzc" onclick="javascript:buttAdd('2')">暂存</div>
                    <div class="bt_tj" id="print" onclick="toprint()">打印</div>
                    <div class="bt_tj" id="butj" onclick="javascript:buttAdd('1')">提交</div>
                </div>
                <div style="color: red;font-size: 16px;margin-top: 15px;">（提示：如暂存或提交不成功请使用360浏览器极速模式或谷歌浏览器，请使用本人账号登录进行申报，否则可能会影响遴选结果）</div>
            </div>
        </form>

            <!-- 退回原因显示悬浮框 -->
            <div class="bookmistake" id="return_cause_div">
                <div class="apache">
                    <div class="mistitle">退回原因:</div>
                    <div class="xx" onclick="$('#return_cause_div').fadeOut(500);"></div>
                </div>

                <div class="info">
                    <%--bug 4586 已被出版社退回的用户 应该提示退回原因--%>
                    <input id="return_cause_hidden" type="hidden" value="${gezlList.return_cause }">
                    <input id="online_progress_hidden" type="hidden" value="${gezlList.online_progress }">
                    <textarea class="misarea" disabled="disabled">${gezlList.return_cause }</textarea>
                </div>

                <div class="">
                    <button class="btn" type="button" onclick="$('#return_cause_div').fadeOut(500);">确认</button>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
