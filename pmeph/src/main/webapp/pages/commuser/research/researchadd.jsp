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
            width: 95%;
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
            width: 95%;
            margin-left: 15px;

        }

        .sxy-radio {

            margin-bottom: 10px;
            display: inline

        }

        .qt {
            float: left;
            font-size: 16px;
            margin-bottom: 10px
        }

        .sxy-checkbox {

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
            cursor: pointer;
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

        .form-table {
            width: 85%;
            margin-top: 20px;
        }

        .q .form-table input[type="radio"] {
            margin-left: 15px;
            padding: 0;
            border: none;
        }

        .q .form-table input[type="checkbox"] {
            margin-left: 15px;
            padding: 0;
            border: none;
        }


    </style>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp">
    <jsp:param value="homepage" name="pageTitle"/>
</jsp:include>
<input type="hidden" value="${state}" id="state">
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

                                  </c:if>
                                :</span></td>

                        <tr style="height: 50px">
                                <%--类型是单选--%>
                            <c:if test="${QuestionList.type==1 }">
                                <td style="margin-top: 10px">
                                    <div class="sxy-radio">
                                        <c:forEach items="${QuestionList.materialSurveyQuestionOptionList}"
                                                   var="OptionList" varStatus="OptionLiStstatus">
                                            <c:if test="${QuestionList.optionAnswer==OptionList.id }">
                                                <input type="radio" checked id="${QuestionList.id}"
                                                       value="${OptionList.id}"
                                                       name="radio_${QuestionListStatus.index+1}"/>${OptionList.optionContent}
                                            </c:if>
                                            <c:if test="${QuestionList.optionAnswer!=OptionList.id }">
                                                <input type="radio" id="${QuestionList.id}" value="${OptionList.id}"
                                                       name="radio_${QuestionListStatus.index+1}"/>${OptionList.optionContent}
                                            </c:if>
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


                                            <c:if test="${QuestionList.optionAnswer== OptionList.id }">
                                            <input type="checkbox" checked
                                                   name="checkbox_${QuestionListStatus.index+1}"
                                                   value="${OptionList.id}"
                                                   id="${OptionList.id}">${OptionList.optionContent}
                                            </c:if>
                                            <c:if test="${QuestionList.optionAnswer!=OptionList.id  }">
                                                <input type="checkbox"
                                                       name="checkbox_${QuestionListStatus.index+1}"
                                                       value="${OptionList.id}"
                                                       id="${OptionList.id}">${OptionList.optionContent}
                                            </c:if>

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
                                            <c:if test="${QuestionList.optionAnswers}">
                                                <option value="${OptionList.optionContent}" selected
                                                        id="OptionList.id"></option>
                                            </c:if>
                                            <c:if test="${QuestionList.optionAnswers}">
                                                <option value="${OptionList.optionContent}" id="OptionList.id"></option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>

                            </c:if>
                                <%--类型是输入框--%>
                            <c:if test="${QuestionList.type==4 }">

                                <td>
                                    <c:if test="${QuestionList.optionContent!=null }">
                                        <input class="input" id="${QuestionList.id}" type="text"
                                               value="${QuestionList.optionContent}">
                                    </c:if>
                                    <c:if test="${QuestionList.optionContent==null }">
                                        <input class="input" id="${QuestionList.id}" type="text">
                                    </c:if>
                                </td>

                            </c:if>

                                <%--类型是文本域--%>
                            <c:if test="${QuestionList.type==5 }">

                                <td>
                                    <c:if test="${QuestionList.optionContent!=null }">
                                        <textarea id="${QuestionList.id}"
                                                  class="textarea">${QuestionList.optionContent}</textarea>
                                    </c:if>
                                    <c:if test="${QuestionList.optionContent==null }">
                                        <textarea id="${QuestionList.id}" class="textarea"></textarea>
                                    </c:if>

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
                <div style="text-align: center;margin-right: 15%;">
                    <button id="commit" class="btn-2" onclick="commit()"> 提交</button>
                    <button class="btn-2" onclick="back()">返回</button>
                </div>
            </div>
        </div>

    </div>
<input type="hidden" id="from" value="${res.from}">
</div>
<input type="hidden" value="${res.material_id}" id="material_id">
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
        var type = "${res.type}";
        if (type) {
            if ("view" == type) {
                $("input").attr("disabled", "disabled");
                $("input").css("background-color", "#eaeaea");
                $("textarea").attr("disabled", "disabled");
                $("textarea").css("background-color", "#EAEAEA");
                $("#commit").hide();
            }
        }


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

        if ("${QuestionList.type}" == 2) {
            <c:forEach items="${QuestionList.optionAnswers}" var="AnswersList"
                       varStatus="QuestionListStatus">

            $("#"+${AnswersList}).attr("checked", 'true');
            </c:forEach>
        }





    </c:forEach>
    console.log(quesions);
    })
    ;

    /*获取表单数据*/
    function getForm() {
        for (var i = 0; i < this.quesions.length; i++) {
            switch (quesions[i].type) {
                case 1:
                    var radio=document.getElementsByName("radio_"+(i+1));
                    var selectvalue=null;   //  selectvalue为radio中选中的值
                    for(var j=0;j<radio.length;j++){
                        if(radio[j].checked==true) {
                            selectvalue=radio[j].value;
                            break;
                        }
                    }
                    quesions[i].answerId = selectvalue;
                    break;
                case 2:
                    var arr = new Array();
                    $($("#cb" + (i + 1) + " input:checkbox:checked")).each(function (i) {
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

    function back() {
       window.history.back();
    }

    function commit() {
        /*提交问卷  JSON字符串提交*/
        var formDate = getForm();
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
                    var from=$("#from").val();
                    if(from=='fromwrtlist'){
                        window.location.href=contextpath+'/research/tolist.action';
                    }else{
                        var material_id=$("#material_id").val();
                        window.location.href=contextpath+'/material/MaterialDetailRedirect.action?material_id='+material_id;
                    }
                } else {
                    window.message.error("请填写所有的未填项");

                }


            }
        });


    }
</script>

</body>
</html>
