<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>学校管理员认证</title>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/authadmin/accountset/adminattest.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/json2.js?t=${_timestamp}" type="text/javascript"></script>
    <link href="${ctx}/statics/css/base.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/publicStyle.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/authadmin/accountset/adminattest.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>

    <script type="text/javascript">
        $(function () {

            $('#title').selectlist({
                zIndex: 10,
                width: 284,
                height: 40,
                optionHeight: 40,
                initValue:$("#titlehidden").val()
            });

        })

        $('#realName')
                .validate(
                        {
                            debug: true,
                            onFocus: function () {
                                this.removeClass("input-error");
                                return false;
                            },
                            onBlur: function () {
                                var $parent = this.parent();
                                var _status = parseInt(this.attr('data-status'));
                                if (!_status) {
                                    this.addClass("input-error");
                                    return false;
                                }
                                return true;
                            },
                            type: {
                                maxLength: function (value, errorMsg, el) {
                                    var v = $.trim(value);
                                    var n = '';
                                    var b = 0;
                                    var tooLong = false;
                                    var param = 9;
                                    for (var i = 0; i < v.length; i++) {
                                        var c = v.slice(i, i + 1);
                                        if (b <= param) {
                                            n += c;
                                        } else {
                                            tooLong = true;
                                            break;
                                        }
                                        b++;
                                    }
                                    $(el).val(n);
                                    if (tooLong) {
                                        return errorMsg;
                                    }

                                }
                            }
                            /* ,
                             submitHandler:function(){
                             getElementById("certForm").submit();

                             } */
                        });


    </script>
    <style type="text/css">
        .select-wrapper {
            background: #f1f1f1;
        }

        .select-button {
            background: #f1f1f1;
            padding-left: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp"/>
<div class="body">
    <div class="content-wrapper" style="width:100%;background-color: #f6f6f6">
        <div style="height:30px"></div>
        <div style="width:1200px;background-color: #ffffff;margin-left: 76px;">
            <div id="div-child">
                <span class="span1"></span>
                <span class="div-menu-main">学校管理员认证</span>
                <a class="return-menu-main"
                   onclick="window.location='${ctx}/admininfocontroller/toadmininfo.action'"><< 返回个人资料</a>
            </div>
        </div>
        <div style="height:14px"></div>
        <div class="div-content" style="width:1200px;background-color: #ffffff;margin-left: 76px">
            <div style="padding-top: 20px"><font class="td-title">机构管理员认证说明</font></div>
            <div id="divtop">
                <div>如果您是机构管理员，为了更好保证您和广大平台用户的合法权益，请您认真填写以下信息。</div>
                <div>认证信息审核通过后，您可以：</div>
                <div>1、协助平台管理自己学校的全体教师与学生；</div>
                <div>2、协助平台管理自己学校的教师进行规划教材申报的资料审核；</div>
                <div>3、享有机构管理员权限的其他功能。</div>
            </div>
            <div>
                <div style="margin-top: 10px;margin-left: 20px"><img alt="" src="${ctx}/statics/image/_cupline.jpg"/>
                </div>
                <input type="hidden" id="titlehidden" name="titlehidden" value="${admininfo.title}">
                <input type="hidden" id="id" value="${admininfo.id}"/>
                
                <input type="hidden" id="proxy" value="${admininfo.proxy}"/>
                <input type="hidden" id="sex" value="${admininfo.sex}"/>
                <input type="hidden" id="handphone" value="${admininfo.handphone}"/>
                <%-- <input type="hidden" id="postCode" value="${admininfo.postCode}"/> --%>
                <input type="hidden" id="fax" value="${admininfo.fax}"/>
                <!-- 已通过或已提交 全部输入框不可修改 -->
                <input type="hidden" id="disabled_all" value="${admininfo.progress==1||(admininfo.progress==0 && admininfo.is_proxy_upload==true)}"/>
                <form>
                    <table border="0" class="form-table">
                        <tr>
                            <td colspan="6">
                                <font class="td-title">机构管理员信息登记
                                    （<font color="#fd9a2e">
                                        <c:choose>
                                            <c:when test="${admininfo.is_proxy_upload==false}">
                                                未提交
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${admininfo.progress==0}">已提交</c:if>
                                                <c:if test="${admininfo.progress==1}">已通过</c:if>
                                                <c:if test="${admininfo.progress==2}">已退回</c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </font>）
                                </font>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:400px">
                                <div class="label-input">
                                    <label>学校名称</label>
                                    <div class="input-wrapper">
                                        <input style="width: 258px;" class="txt" type="text"
                                               value="${admininfo.org_name}" readonly="readonly"/>
                                    </div>
                                </div>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <td style="width:400px">
                                <div class="label-input">
                                    <label class="require">管理员姓名</label>
                                    <div class="input-wrapper">
                                        <input ${(admininfo.progress!=1)?"":"readonly='readonly'"}
                                                style="width: 258px" class="txt required" type="text" id="realName"
                                                value="${admininfo.realname}" data-valid="isNonEmpty||maxLength"
                                                data-length data-error="真实姓名不能为空||姓名不能超过10个中文字符"  maxLength="20" />
                                    </div>
                                </div>
                            </td><!--   value="${admininfo.realname} -->

                            <td colspan="2">
                                <div style="margin-bottom: 25px;margin-left: 20px">
                                    <font class="td-font-3">请填写与上传委托书上一致的管理员真实姓名</font>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:400px">
                                <div class="label-input">
                                    <label class="require">邮箱地址</label>
                                    <div class="input-wrapper">
                                        <input ${(admininfo.progress!=1)?"":"readonly='readonly'"}
                                                style="width: 258px" class="txt required" type="text" id="email"
                                                value="${admininfo.email}" data-valid="isNonEmpty||isEmail"
                                                data-error="邮箱不能为空||请填写正确的邮箱格式"  maxLength="40"/>
                                    </div>
                                </div>
                            </td><!--   value="${admininfo.email} -->

                            <td colspan="2">
                                <div style="margin-bottom: 25px;margin-left: 20px">
                                    <font class="td-font-3">请填写正确的邮件地址，我们有些资料有可能会通过邮箱发送给您</font>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:400px">
                                <div class="label-input">
                                    <label class="require">手机</label>
                                    <div class="input-wrapper">
                                        <input ${(admininfo.progress!=1)?'class="txt required"':'class="txt" readonly="readonly"'}
                                                style="width: 258px" type="text" id="telephone"
                                                value="${admininfo.handphone}"
                                                data-valid="isNonEmpty||isMobile"
                                                data-error="手机号码不能为空||请填写正确的电话格式"  maxLength="20"/>
                                    </div>
                                </div>
                            </td><!--   value="${admininfo.handphone} -->

                            <td colspan="2">
                                <div style="margin-bottom: 25px;margin-left: 20px">
                                    <font class="td-font-3">请填写正确的电话，以使我们能及时联系到您</font>
                                </div>
                            </td>
                        </tr>
                        <tr style="height: 40px;">
                            <td style="width: 600px;height: 40px;padding-left: 15px" colspan="2">

                                <div class="td-font-1" style="float: left;"><font color="#ff3d38">*</font>上传委托书</div>
                                <div class="input-wrapper">
		                                <div id="fileNameDiv" style="float: left;cursor: pointer;padding-left:10px;">
		                                    <div style="float:left">
		                                        <img alt="" src="${ctx}/statics/testfile/_al.jpg"/>
		                                    </div>
		                                    <div style="float:left;margin-left: 5px">
		                                        <a href="#javascript:" class="filename" onclick="downLoad()" id="fileName">${admininfo.proxyName}</a>
		                                    </div>
		                                </div>
		                                <div style="float: left;" id="proxyDiv">
		                                    <c:if test="${admininfo.proxy!=null}">
		                                        <div class="td-font-2" style="float: left;" id="proxyName">
		                                            <div style="float:left">
		                                                <img alt="" src="${ctx}/statics/testfile/_al.jpg"/>
		                                            </div>
		                                            <div style="float:left;margin-left: 5px">
		                                                <a href="javascript:" class="filename"
		                                                   onclick="downLoadProxy('${admininfo.proxy}')">${admininfo.proxyName}</a>
		                                            </div>
		                                        </div>
		                                    </c:if>
		                                </div>
		                                <div class="td-font-2" style="float: left;">
		                                	
		                                	<input type="hidden" id="fileid" name="fileid" class="required" data-valid="isNonEmpty"
														data-error="请上传委托书!" value="${admininfo.proxy}"/>
		                                    <c:if test="${admininfo.progress!=1&&!(admininfo.progress==0 && admininfo.is_proxy_upload==true)}">
		                                        <span style="cursor: pointer;" class="uploadClick" id="uploadFile">点击上传</span>
		                                    </c:if>
		                                    
		                                </div>
	                                </div>
	                                <div style="float:left;margin-left: 20px">
	                                    <div style="float:left">
	                                        <img style="margin-left: 20px" alt="暂无图片"
	                                             src="${ctx}/statics/testfile/a01.png"/>&nbsp;
	                                    </div>
	                                    <div style="float:left;">
	                                        <a href="${ctx}/statics/files/weituoshu.doc">
		                                <span class="a-td">
		                                    	委托书模板下载
		                                </span>
	                                        </a>
	                                    </div>
	                                </div>
                                
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4"><font style="margin-left: 422px;" class="td-font-3">请下载委托书模板，按要求填写后打印盖章，并上传bmp，jpg，gif，jpeg，png的照片或者扫描件</font>
                            </td>
                        </tr>
                        <tr>
                            <td style="width:260px">
                                <div class="label-input">
                                    <label class="require">职务</label>
                                    <div class="input-wrapper">
                                        <input ${(admininfo.progress!=1)?"":"readonly='readonly'"}
                                                style="width: 258px" class="txt required" type="text" id="position"
                                                value="${admininfo.position}" data-valid="isNonEmpty"
                                                data-error="职务不能为空"  maxLength="35" />
                                    </div>
                                </div>
                            </td><!--   value="${admininfo.email} -->

                            <td colspan="2">
                                <div style="margin-bottom: 25px;margin-left: 20px">
                                    <font class="td-font-3">请填写您的职务名称</font>
                                </div>
                            </td>

                        </tr>
                        <tr>
                            <td style="width:260px">
                                <div class="label-input">
                                    <label class="require">职称</label>
                                    <div class="input-wrapper">
                                    	<c:choose>
                                    		<c:when test="${admininfo.progress==1||(admininfo.progress==0 && admininfo.is_proxy_upload==true)}">
                                    			<input type="text" class="txt" name="title" disabled="disabled" style="width: 258px;"
													value="${admininfo.title}">
                                    		</c:when>
                                    		<c:otherwise>
                                    			<select ${(admininfo.progress!=1)?"":"disabled='disabled'"} class="select-td" id="title" name="title" style="padding-left: 15px"
		                                                data-valid="isNonEmpty" data-error="职称不能为空">
		                                            <option value="讲师" ${admininfo.title=='讲师' ?'selected':''}>讲师
		                                            </option>
		                                            <option value="副教授" ${admininfo.title=='副教授' ?'selected':''}>副教授
		                                            </option>
		                                            <option value="教授" ${admininfo.title=='教授' ?'selected':''}>教授
		                                            </option>
		                                            <option value="院士" ${admininfo.title=='院士' ?'selected':''}>院士
		                                            </option>
		                                        </select>
                                    		</c:otherwise>
                                    	</c:choose>
                                        
                                    </div>
                                </div>
                            </td>
                            <td colspan="2">
                                <div style="margin-bottom: 25px;margin-left: 20px">
                                    <font class="td-font-3">请选择您的职称名称</font>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="label-input">
                                    <label class="require">地址</label>
                                    <div class="input-wrapper">
                                        <input ${(admininfo.progress!=1)?"":"readonly='readonly'"}
                                                style="width: 700px" class="txt required" type="text" id="address"
                                                value="${admininfo.address}" data-valid="isNonEmpty"
                                                data-error="地址不能为空" maxLength="50" />
                                    </div>
                                </div>
                            </td><!--   value="${admininfo.address} -->

                        </tr>
                        <tr>
                            <td style="width:400px">
                                <div class="label-input">
                                    <label class="require">邮编</label>
                                    <div class="input-wrapper">
                                        <input ${(admininfo.progress!=1)?"":"readonly='readonly'"}
                                                style="width: 258px" class="txt required" type="text" id="postCode"
                                                value="${admininfo.postcode}" data-valid="isNonEmpty||onlyInt"
                                                data-error="邮编不能为空||请填写正确的邮编" maxLength="25" />
                                    </div>
                                </div>
                            </td><!--   value="${admininfo.email} -->

                            <td colspan="2">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div style="margin-left: 20px"><img alt="" src="${ctx}/statics/image/_cupline.jpg"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <c:if test="${admininfo.progress==2||(admininfo.is_proxy_upload==false && admininfo.progress==0)}">
                                <td colspan="3" align="center">
                                    <div style="cursor: pointer;" class="btnSubmit" onclick="submit()">提交</div>
                                </td>
                            </c:if>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div style="background-color: #f6f6f6;height: 60px">
        </div>
    </div>
</div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</body>
</html>
