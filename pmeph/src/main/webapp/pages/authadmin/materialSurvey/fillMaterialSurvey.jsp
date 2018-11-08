<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <title>填写调研表</title>
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/authadmin/accountset/adminattest.js?t=${_timestamp}" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/json2.js?t=${_timestamp}" type="text/javascript"></script>
    <link href="${ctx}/statics/css/base.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>

    <link href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
    <script type="text/javascript">

    </script>
    <style type="text/css">


        .textarea {

            display: block;
            resize: vertical;
            padding: 5px 7px;
            line-height: 1.5;
            box-sizing: border-box;
            width: 100%;
            font-size: 14px;
            color: rgb(31, 43, 61);
            background-color: #fff;
            background-image: none;
            border: 1px solid rgb(191, 201, 217);
            border-radius: 4px;
            transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
            margin-left: 15px;
        }

        .textarea {
            min-height: 33px;
        }

        .input {

            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            background-color: #fff;
            background-image: none;
            border-radius: 4px;
            border: 1px solid rgb(191, 201, 217);
            border-top-color: rgb(191, 201, 217);
            border-right-color: rgb(191, 201, 217);
            border-bottom-color: rgb(191, 201, 217);
            border-left-color: rgb(191, 201, 217);
            box-sizing: border-box;
            color: rgb(31, 43, 61);
            display: inline-block;
            font-size: inherit;
            height: 36px;
            line-height: 1;
            outline: none;
            padding: 3px 10px;
            transition: border-color .2s cubic-bezier(.645, .045, .355, 1);
            width: 100%;
            margin-left: 15px;

        }

        .sxy-radio {

            margin-left: 15px;
            margin-bottom: 10px;
            display: inline

        }

        .qt {
            float: left;
            font-size: 16px;
            margin-bottom: 10px
        }

        .sxy-checkbox {
            margin-left: 15px;
            margin-bottom: 10px;
            display: inline
        }

        .q {
            margin-left: 15%;

        }

        .btn-2 {
            border: none;
            width: 120px;
            height: 40px;
            background-color: #70bcc3;

            color: #ffffff;
            font-size: 16px;
            border-radius: 5px;
            margin-top: 75px;
            margin-left: 30px;
            margin-bottom: 50px;

        }

        .div-content {
            width: 80%;

            background-color: #ffffff;

            margin-left: 10%;

        }
        .form-table{
            width: 85%;
            margin-top: 20px;
        }

        .q .form-table input[type="radio"]{
            margin-left: 15px;
            padding: 0;
            border: none;
        }
        .q .form-table input[type="checkbox"]{
            margin-left: 15px;
            padding: 0;
            border: none;
        }




    </style>
</head>
<body>
<jsp:include page="/pages/comm/headGreenBackGround.jsp"/>
<div class="body">

        <div class="content-wrapper" style="width:100%;background-color: #f6f6f6">
            <input type="text" name="surveyId" id="id" value="${res.survey.id}" hidden>
            <div style="height:30px"></div>

            <div style="height:14px"></div>
            <div class="div-content">
                <div style="padding-top: 20px;text-align: center">
                    <h2>${res.survey.title}</h2>
                </div>

                <div class="q">
                    <span>${res.survey.intro}</span>
                    <div style="margin-top: 10px;margin-left: 20px"><img alt=""
                                                                         src="${ctx}/statics/image/_cupline.jpg"/>
                    </div>
                    <table border="0" class="form-table">

                        <c:forEach items="${res.survey.materialSurveyQuestionList}" var="QuestionList"
                                   varStatus="QuestionListStatus">

                            <td><span class="qt">${QuestionListStatus.index+1}.${QuestionList.title}
                                  <c:if test="${QuestionList.isAnswer==1 }">
                                      <span style="color:#ff3d38">*</span>
                                  </c:if>
                                :</span></td>

                            <tr style="height: 50px">
                                <%--类型是单选--%>
                                <c:if test="${QuestionList.type==1 }">
                                    <td style="margin-top: 10px">
                                        <div class="sxy-radio">
                                            <c:forEach items="${QuestionList.materialSurveyQuestionOptionList}"
                                                       var="OptionList" varStatus="OptionLiStstatus">

                                                <input type="radio" id="${QuestionList.id}" value="${OptionList.id}"
                                                       name="radio_${QuestionListStatus.index+1}"/>${OptionList.optionContent}

                                            </c:forEach>
                                        </div>
                                    </td>

                                </c:if>
                                        <%--类型是多选--%>
                                <c:if test="${QuestionList.type==2 }">

                                    <td>
                                        <div class="sxy-checkbox" id="cb${QuestionListStatus.index+1}">
                                            <c:forEach items="${QuestionList.materialSurveyQuestionOptionList}"
                                                       var="OptionList" varStatus="OptionLiStstatus">

                                                <input type="checkbox" name="checkbox_${QuestionListStatus.index+1}"
                                                       value="${OptionList.id}"
                                                       id="${QuestionList.id}">${OptionList.optionContent}

                                            </c:forEach>
                                        </div>
                                    </td>

                                </c:if>
                                        <%--类型是下拉框--%>
                                <c:if test="${QuestionList.type==3 }">

                                    <td>

                                        <select name="optionContent" id="${QuestionList.id}">
                                            <c:forEach items="${QuestionList.materialSurveyQuestionOptionList}"
                                                       var="OptionList" varStatus="OptionLiStstatus">
                                                <option value="${OptionList.optionContent}" id="OptionList.id"></option>
                                            </c:forEach>
                                        </select>
                                    </td>

                                </c:if>
                                        <%--类型是输入框--%>
                                <c:if test="${QuestionList.type==4 }">

                                    <td>

                                        <input class="input" id="${QuestionList.id}" type="text">
                                    </td>

                                </c:if>

                                        <%--类型是文本域--%>
                                <c:if test="${QuestionList.type==5 }">

                                    <td>

                                        <textarea id="${QuestionList.id}" class="textarea"></textarea>

                                    </td>

                                </c:if>
                                        <%--类型是文件--%>
                                <c:if test="${QuestionList.type==6 }">

                                    <td>

                                        <input type="file" id="${QuestionList.id}">

                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                    </table>
                    <div style="text-align: center;margin-right: 30%;">
                        <button class="btn-2" onclick="commit()"> 提交</button>
                        <button class="btn-2" onclick="back()">关闭</button>
                    </div>
                </div>
            </div>

        </div>

</div>

<jsp:include page="/pages/comm/tail.jsp"></jsp:include>

<script type="text/javascript">
    var survey = {
        id: '',
        quesions: []
    };
    var quesions = new Array();
    var question = {
        id: '',
        type: '',
        answerId: '',
        answerInput: '',
        answerTextArea: ''
    };
    $(function () {
        /*初始化表单提交数据*/
        <c:forEach items="${res.survey.materialSurveyQuestionList}" var="QuestionList"
                                      varStatus="QuestionListStatus">
        var quesion = {
            id: 0,
            type: 0
        };
        quesion.id = parseInt("${QuestionList.id}");
        quesion.type = parseInt("${QuestionList.type}");
        quesions.push(quesion);
        </c:forEach>
        console.log(quesions);
    });

    /*获取表单数据*/
    function getForm() {
        for (var i = 0; i < this.quesions.length; i++) {
            switch (quesions[i].type) {
                case 1:
                    quesions[i].answerId = $("#" + quesions[i].id).val();
                    break;
                case 2:
                    var arr = new Array();
                    $($("#cb"+(i+1)+" input:checkbox:checked")).each(function (i) {
                        arr[i] = $(this).val();
                    });
                    quesions[i].answerId = arr.join(",");
                    break;
                case 3:
                    quesions[i].answerId = $("#" + quesions[i].id).val();
                    break;
                case 4:
                    quesions[i].answerInput = $("#" + quesions[i].id).val();
                    break;
                case 5:
                    quesions[i].answerTextArea = $("#" + quesions[i].id).val();
                    break;

            }
        }
        var survey = {};
        survey.id = "${res.survey.id}";
        survey.quesions = quesions;
        console.log(this.survey);
        return survey;


    }
    function back(){
        window.history.back()
    }

    function commit() {
        /*提交问卷  JSON字符串提交*/
        var formDate = getForm();
        console.log(formDate);
        console.log(JSON.stringify(formDate));
        $.ajax({
            type: 'post',
            data: JSON.stringify(formDate),
            url: "${ctx}/orgSurvey/fillSurveyQuestion.action",
            async: false,
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (res) {
                if (res.code >= 1) {
                    window.message.success("填写成功");
                    window.location.href="${ctx}/schedule/scheduleList.action";

                } else {
                    window.message.error("后台错误");
                    window.location.href="${ctx}/schedule/scheduleList.action";
                }


            }
        });


    }
</script>

</body>
</html>
