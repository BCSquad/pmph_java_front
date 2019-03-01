<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/20
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String contextpath=request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script>
        var contextpath = "${pageContext.request.contextPath}/";
    </script>
    <title>课程选书详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=path%>/statics/css/base.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/commuser/course/teacher/course.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.pager.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.calendar.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="<%=path%>/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
    <script src="<%=path%>/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/comm/base.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.calendar.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="<%=path%>/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/course/teacher/course-searchBook.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/course/teacher/course-searchStudent.js?t=${_timestamp}"></script>
    <script src="<%=path%>/resources/commuser/course/teacher/course.js?t=${_timestamp}"></script>



</head>
<body>
<!-- 隐藏域 -->
<input type="hidden" id="published" value="${course.published}">
<input type="hidden" id="isNew" value="${isNew}">
<input type="hidden" id="courseId" value="${course.id}">
<input type="hidden" id="readOnly" value="${readOnly}">




<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<div class="content-wrapper" >

    <div class="wrapper">

        <div class="list" style="background-color: white" >

            <form id = "courseForm">
                <div>
                    <input type="hidden" name="id" value="${course.id}">
                    <span class="lable">课程名称<font style="color:red;">*</font>：</span>
                    <input class="input " tipso
                           name="name"
                           ${course.published?"disabled":""}
                           value="${course.name}"
                           validator="isNonEmpty"
                           message="课程名称不能为空"
                           maxlength="50" placeholder="请输入课程名称">
                    <span class="lable">学生代表 ：</span>
                    <input class="input " placeholder="请输入学生代表的登录账号"
                           name="stuRepreUsername"
                           value="${course.stuRepreUsername}"
                           maxlength="40">
                    <span class="lable note">(该账号可代替您打印核对清单)</span>
                </div>
                <div>
                    <span class="lable">开始日期 ：</span>
                    <input class="input " placeholder="开始日期" calendar format="'yyyy-mm-dd'"
                           z-index="100" max="'$#endDate'" id="beginDate"
                           value="<fmt:formatDate pattern="yyyy-MM-dd"
                                  value="${course.beginDate}"/>"
                           ${course.published?"disabled":""}
                           name="beginDate">
                    <span class="lable">截止日期 ：</span>
                    <input class="input " placeholder="截止日期" calendar format="'yyyy-mm-dd'"
                           z-index="100" min="'$#beginDate'" id="endDate"
                           value="<fmt:formatDate pattern="yyyy-MM-dd"
                                  value="${course.endDate}"/>"
                           ${course.published?"disabled":""}
                           name="endDate">
                    <span class="lable note">(供学生选书的时间段，不设置即为无相应日期限制)</span>
                </div>
                <div>
                    <span class="lable">课程说明 ：</span>
                    <textarea class="input " ${course.published?"disabled":""} name="note" rows="3"  onkeydown="LengthLimit(this,500)" >${course.note}</textarea>
                </div>
            </form>

            <div style="float: left;clear: both;margin-top: 3em;margin-bottom: 0.5em;width:100%;">
                课程图书列表:
                <c:if test="${!course.published}">
                    <button id="btn-book-add" onclick="showup('bookAdd')">新增</button>
                </c:if>

            </div>
            <div class="table-area">
                <table >
                    <tr>
                        <th>序号</th>
                        <th>图书名称</th>
                        <th>ISBN</th>
                        <th>课程备注</th>
                        <c:if test="${course.published}">
                            <th>订购数/人数</th>
                        </c:if>
                        <th>操作</th>
                    </tr>
                    <tbody id="zebra-table">
                        <c:forEach var="courseBook" items="${courseBookList}" varStatus="status">
                            <tr>
                                <input type="hidden" class="data" value="${courseBook.bookId}" courseBookId="${courseBook.id}" deleted="0">
                                <td class="row_count">${status.index+1}</td>
                                <td>${courseBook.bookname}</td>
                                <td>${courseBook.isbn}</td>
                                <td class="courseBookNote">
                                    <textarea class="courseBookNote" bookId="${courseBook.bookId}" courseBookId="${courseBook.id}">${courseBook.note}</textarea>
                                    <span class="${readOnly?"":"courseBookNote"}" courseBookId="${courseBook.id}">${courseBook.note}</span>
                                </td>
                                <c:if test="${course.published}">
                                    <td courseBookId="${courseBook.id}">
                                        <span class="countRes" courseBookId="${courseBook.id}">${courseBook.countRes}</span>/
                                        <span >${courseBook.countStu}</span>
                                    </td>
                                </c:if>
                                <td>
                                    <c:choose>
                                        <c:when test="${course.published}">
                                            <a class="not-like-an-a course-handler"
                                               onclick="javascript:showup('student',${courseBook.id},'${courseBook.bookname}',this)">
                                                调整</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="not-like-an-a course-handler"
                                               onclick="javascript:course_book_delete(${courseBook.id},'${courseBook.bookname}',this)">
                                                删除</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="no-more" style="display: none;">
                    <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                    <span>木有内容呀~~</span>
                </div>
            </div>

            <div class="btn-wrapper">
                <c:choose>
                    <c:when test="${!course.published}">
                        <button class="btn publish"  onclick="save(1)">发布</button>
                        <button class="btn save"  onclick="save(0)">保存</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn save"  onclick="save(1)">保存</button>
                    </c:otherwise>
                </c:choose>

                <button class="btn"  onclick="javascript:window.location.href='<c:url value="/course/teacher/toCourseList.action"></c:url>'">返回</button>
            </div>

            <!-- 图书选择悬浮框 -->
            <div class="dialog" id="course-dialog">
                <div class="apache">
                    <div class="mistitle">图书选择:</div>
                    <div class="xx" onclick="$('#course-dialog').fadeOut(500);"></div>
                </div>
                <div class="info">
                    <div>
                        <div class="select-search-status-wrapper">
                            <span>图书名称：</span>
                            <input class="search-condition " id="search-bookname">
                            <span>ISBN：</span>
                            <input class="search-condition " id="search-ISBN">
                            <button class="btn-search" onclick="queryBtnClick()">查询</button>
                        </div>
                    </div>
                    <div class="table-area" >
                        <table >
                            <tr>
                                <th><input type="checkbox" id="check-all"></th>
                                <th>图书名称</th>
                                <th>ISBN</th>
                            </tr>
                            <tbody class="dialog-table">

                            </tbody>
                        </table>
                        <div class="no-more" style="display: none;">
                            <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                            <span>木有内容呀~~</span>
                        </div>
                    </div>

                    <div class="pagination-wrapper" >
                        <input type="hidden" class="page-num-temp" value="1">
                        <ul class="pagination" >
                        </ul>
                        <div style="display: inline-block;    vertical-align: top">
                            <select class="page-size-select" name="page-size-select" id="page-size-select-1">
                                <option value="5">每页5条</option>
                                <option value="10">每页10条</option>
                                <option value="15">每页15条</option>
                                <option value="20">每页20条</option>

                            </select>
                        </div>
                        <div class="pageJump">
                            <span>共<span class="totoal_count" >${totoal_count }</span>页，跳转到</span>
                            <input type="text"/>
                            <span class="pp">页</span>
                            <button type="button" class="button">确定</button>
                        </div>
                    </div>

                    <div class="btn-wrapper">
                        <button class="btn btn-confirm" onclick="teacherAddBook()">确定</button>
                        <button class="btn btn-cancle"  onclick="$('#course-dialog').fadeOut(500);">取消</button>
                    </div>

                </div>
            </div>


            <!-- 图书学生调整悬浮框 -->
            <div class="dialog" id="student-dialog">
                <div class="apache">
                    <div class="mistitle"></div>
                    <div class="xx" onclick="$('#student-dialog').fadeOut(500);"></div>
                </div>
                <div class="info">
                    <div>
                        <div class="select-search-status-wrapper">
                            <span>学生姓名：</span>
                            <input class="search-condition " id="search-name">
                            <span>学号：</span>
                            <input class="search-condition " id="search-studentId">
                            <span>班级：</span>
                            <input class="search-condition " id="search-className">
                            <input type="hidden" id="courseBookId" value="">
                            <button class="btn-search" onclick="queryBtnClick()">查询</button>
                        </div>
                    </div>
                    <div class="table-area">
                        <table >
                            <tr>
                                <th>序号</th>
                                <th>学生姓名</th>
                                <th>学号</th>
                                <th>班级</th>
                                <th>电话</th>
                                <th>操作</th>
                            </tr>
                            <tbody class="dialog-table">

                            </tbody>
                        </table>
                        <div class="no-more" style="display: none;">
                            <img src="<c:url value="/statics/image/aaa4.png"></c:url>">
                            <span>木有内容呀~~</span>
                        </div>
                    </div>

                    <div class="pagination-wrapper" >
                        <input type="hidden" class="page-num-temp" value="1">
                        <ul class="pagination" >
                        </ul>
                        <div style="display: inline-block;    vertical-align: top">
                            <select class="page-size-select" name="page-size-select" id="page-size-select-2">
                                <option value="5">每页5条</option>
                                <option value="10">每页10条</option>
                                <option value="15">每页15条</option>
                                <option value="20">每页20条</option>

                            </select>
                        </div>
                        <div class="pageJump">
                            <span>共<span class="totoal_count" >${totoal_count }</span>页，跳转到</span>
                            <input type="text"/>
                            <span class="pp">页</span>
                            <button type="button" class="button">确定</button>
                        </div>
                    </div>

                    <div class="btn-wrapper">
                        <%--<button class="btn btn-confirm"  onclick="teacherAddBook()">确定</button>--%>
                        <button class="btn btn-cancle"  onclick="$('#student-dialog').fadeOut(500);">关闭</button>
                    </div>

                </div>
            </div>
            
        </div>

    </div>
</div>

</body>
</html>
