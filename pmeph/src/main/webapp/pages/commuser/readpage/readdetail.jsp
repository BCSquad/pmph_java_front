<%@ page import="java.util.Map" %>
<%@ page import="com.bc.pmpheep.back.util.Const" %>
<%@ page import="org.apache.commons.collections.MapUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <title>读书详情</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link href="${ctx}/statics/css/base.css?t=${_timestamp}" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.selectlist.css?t=${_timestamp}"/>
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css?t=${_timestamp}" type="text/css">
    <link rel="stylesheet" href="${ctx}/resources/comm/layui/css/layui.css">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.tipso.css?t=${_timestamp}" type="text/css">
    <link href="${ctx}/statics/commuser/readpage/readdetail.css?t=${_timestamp}" type="text/css" rel="stylesheet">
    <script src="${ctx}/resources/comm/jquery/jquery.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.fileupload.js?t=${_timestamp}?t=${_timestamp}" type="text/javascript"></script>
    <script src="${ctx}/resources/comm/jquery/jquery-validate.js?t=${_timestamp}" type="text/javascript"></script>

    <script src="${ctx}/resources/comm/jquery/jquery.selectlist.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/jquery/jquery.tipso.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/base.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/reload.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/comm/layui/layui.js?t=${_timestamp}"></script>
    <script type="text/javascript" src="${ctx}/resources/comm/ckplayer/ckplayer.js?t=${_timestamp}"></script>
    <script src="${ctx}/resources/commuser/readpage/readdetail.js?t=${_timestamp}"></script>
</head>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
<input type="hidden" id="bookname" value="${map.bookname}"/>
<input type="hidden" id="booksn" value="${map.sn}"/>
<input type="hidden" id="bookid" value="${map.id}"/>
<input type="hidden" id="userid" value="<%
   Map<String, Object> userInfo  = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
   if(userInfo!=null){
        out.print(MapUtils.getString(userInfo,"id"));
   }
%>"/>
<div class="body">
    <input type="hidden" value="${map.type}" id="type_id">
    <div class="content-wrapper">
    	<div class="content">
        <input type="hidden" id="book_id" value="${id}">
        <input type="hidden" id="marks" value="${map.bookmarks}">
        <!-- 图书纠错悬浮框 -->
        <div class="bookmistake" id="bookmistake">
                <div class="apache">
                    <div class="mistitle">图书纠错</div>
                    <div class="x" onclick="hideup()"></div>
                </div>
                <div class="input">
                    <div style="float:left;">
                        <label style="margin-left: 20px" class="labell"><font style="color: red">*</font>页码:</label>
                        <input type="text" style="width: 150px;" class="text required" id="page"
                               onblur="javascript:LengthLimit(this,4);"/>
                    </div>
                    <div style="float:right;margin-right: 50px"><label style="margin-left: 10px"class="labell"><font style="color: red">*</font>行数:</label>
                        <input type="text" style="width: 150px;" class="text" id="line"
                               onblur="javascript:LengthLimit(this,4);"/></div>

                </div>
                <div class="info">
                    <label style="margin-left: 20px;" class="labell"><font style="color: red">*</font>纠错内容</label>
                    <div style="margin-top: 5px;">
                         <textarea class="misarea" style="width:470px;" id="content"
                                   onkeyup="javascript:LengthLimit(this,500);"
                                   onblur="javascript:LengthLimit(this,500);"></textarea>
                    </div>
                </div>
                <div class="upload">
                    <label style="margin-left: 20px" class="labell">纠错内容附件</label>
                    <div style="position: relative">
                        <div id="uploadFile" class="upbutten  "><%--选择文件</div>--%>
                            选择文件
                        </div>
                    </div>
                    <label class="uploadfile" id="upname">未选择任何文件!</label>
                    <input type="hidden" id="attachment"/>
                    <input type="hidden" id="attachment_name"/>
                    <input type="hidden" id="upload_status"/>
                </div>
                <div class="">
                    <button class="btn" type="button" onclick="correction()">确认</button>
                </div>
        </div>
            <div class="bookmistake" id="sourceUp">
                <div class="apache">
                    <div class="mistitle">资源上传</div>
                    <div class="x" onclick="hideup()"></div>
                </div>
                <div class="upload">
                    <label style="margin-left: 20px" class="labell">资源附件</label>
                    <div style="position: relative">
                        <div id="uploadFile2" class="upbutten  "><%--选择文件</div>--%>
                            选择文件
                        </div>
                    </div>
                    <label class="uploadfile" id="upname2">未选择任何文件!</label>
                    <input type="hidden" id="attachment2"/>
                    <input type="hidden" id="attachment_name2"/>
                    <input type="hidden" id="upload_status2"/>
                </div>
                <div class="">
                    <button class="btn" type="button" onclick="sourceCommit()">确认</button>
                </div>
            </div>
        <!-- 读者反馈悬浮框 -->
        <div class="bookmistake" id="bookfeedback">
            <form id="bookfeedbackform">
                <div class="apache">
                    <div class="mistitle">读者反馈</div>
                    <div class="x" onclick="hideup()"></div>
                </div>
                
                <div class="info">
                    <div style="margin-top: 5px;">
                         <textarea class="misarea required" style="width:470px;" id="bookfeedback_content"
                                   onkeyup="javascript:LengthLimit(this,500);"
                                   onblur="javascript:LengthLimit(this,500);"></textarea>
                    </div>
                </div>
                
                <div class="">
                    <button class="btn" type="button" onclick="bookfeedback()">确认</button>
                </div>
            </form>
        </div>
        <!-- 读者反馈悬浮框end -->
        
        <!--左边区域-->
        <div class="leftarea">
            <div class="title" style="margin-top: -20px"><span>读书 >${map.bookname}</span></div>
            <div class="bt"><span>${map.bookname}</span></div>
            <div class="lj" style="margin-top: -10px">
                <span class="span_1">分类路径：</span>
                <c:forEach items="${typeList}" var="list" varStatus="status">
                  <span class="span_2">${list.type_name }
                  	 <c:if test="${status.index < typeList.size()-1}">
                         >
                     </c:if>
                  </span>
                </c:forEach>
            </div>
            <div style="width: 100%;">
                <div class="dzsc">
                    <div class="addlikediv" onclick="addlikes()">
                        <c:if test="${flag=='no'}">
                            <div class="addlike"  id="dz"/></div>
                            <div class="addliketext">点赞</div>
                         </c:if>
                    </div>

                    <div class="addlikediv" onclick="addlikes()">
                        <c:if test="${flag=='yes'}">
                            <div class="left2"  id="dz"/></div>
                            <div class="addliketext">点赞</div>
                        </c:if>
                    </div>

                    <div class="addlikediv" style="margin-left: 10px" onclick="addmark()">
                        <div class="${mark=='yes' ? 'left3':'left4'}"  id="sc"/></div>
                        <div class="addliketext">收藏</div>
                    </div>
                    <div style="display: inline-block;vertical-align: top;margin-right: 8px;text-align:left;">
                    </div>
                </div>
            </div>
            <div class="xqbf1">
                <div class="xl1"><img src="${map.image_url }"
                                      style="display: block;margin-bottom: 25px;height: 150px;margin: auto"/></div>
                <div class="xl2">
                    <ul>
                        <li><span class="author">作者：</span><span class="writer"
                                                                 style="color: #489299">${map.author}</span></li>
                        <li class="author">出版社： ${map.publisher}</li>
                        <li class="author">出版日期：${map.publish_date}</li>
                        <li class="author">ISBN：${map.isbn}</li>
                    </ul>
                </div>
                <div class="xl3"></div>
                <div class="xl4">
                    <ul>
                        <li class="author">读者对象：${map.reader}</li>
                        <li class="author">版次：${map.revision}</li>
                        <li class="author">被浏览：${map.clicks}次</li>
                        <div class="author">评分：</div>
                        <c:if test="${map.score<=3}">
                            <div class="star">
                                <span class="rwtx1"></span>
                                <span class="rwtx2"></span>
                                <span class="rwtx2"></span>
                                <span class="rwtx2"></span>
                                <span class="rwtx2"></span>
                            </div>
                        </c:if>
                        <c:if test="${map.score<=5 and map.score>3}">
                            <div class="star">
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx2"></span>
                                <span class="rwtx2"></span>
                                <span class="rwtx2"></span>
                            </div>
                        </c:if>
                        <c:if test="${map.score<=7 and map.score>5}">
                            <div class="star">
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx2"></span>
                                <span class="rwtx2"></span>
                            </div>
                        </c:if>
                        <c:if test="${map.score<=9 and map.score>7}">
                            <div class="star">
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx2"></span>
                            </div>
                        </c:if>
                        <c:if test="${map.score>9}">
                            <div class="star">
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                                <span class="rwtx1"></span>
                            </div>
                        </c:if>
                        <div class="eight">${map.score}</div>
                        <!-- <div class="zero">0</div> -->
                    </ul>
                </div>
            </div>
            <div class="xqbf2">

                <div class="xsp" style="float: left;">
                    <div id="xsp"></div>
                    <%--<a href="#001" onclick="writeablut()" style="text-decoration: none"><span id="xsp1">写书评</span></a>  --%>
                    <a  style="text-decoration: none;cursor: pointer;" onclick="$('.rd_name.tag').first().trigger('click');$('#content_book').focus()" ><span id="xsp1">写书评</span></a>

                </div>
                <div class="mistake" onclick="showup(1)">
                    <div class="mis_pic" ></div>
                    <div class="mis_content">图书纠错</div>
                </div>
                <div class="mistake">
                    <%
                        Map<String, Object> userInfo2 = null;
                        if ("1".equals(session.getAttribute(Const.SESSION_USER_CONST_TYPE))) {
                            userInfo2 = (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_WRITER);
                        }

                        if (userInfo2 == null || userInfo2.isEmpty()) {
                            request.setAttribute("userInfo", null);
                        } else {
                            request.setAttribute("userInfo", userInfo2);
                        }
                    %>
                    <div class="vid_pic"></div>
                    <div class="mis_content"  <c:if test="${userInfo==null}"> onclick="validLogin()" </c:if>>上传微视频</div>
                    <c:if test="${userInfo!=null}">
                        <input id="upload-video" type="file" class='hidden-upload' name='file' accept="video/*"
                               style="width: 0px;height: 33px;padding-left: 100px;cursor: pointer;">
                    </c:if>

                </div>
                <div class="mistake" onclick="showup(2)">
                    <div class="feedback_pic" ></div>
                    <div class="mis_content">读者反馈</div>
                </div>
                <div class="mistake" onclick="sourceUpload()">
                    <div class="vid_pic" ></div>
                    <div class="mis_content">资源上传</div>
                </div>
                
                <c:choose>
                    <c:when test="${empty map.pdf_url}">
                        <div class="left1" id="dpf" style="cursor:not-allowed;">
                            <div id="xsp3"></div>
                            <input type="hidden" id="pdf-hidden" value="${map.pdf_code}">
                            <a href="${map.pdf_url}" style="text-decoration: none" id="pdf_a"><span class="xsp2">PDF试读</span></a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="left1" id="dpf">
                            <div id="xsp3"></div>
                            <input type="hidden" id="pdf-hidden" value="${map.pdf_code}">
                            <a href="${map.pdf_url}" style="text-decoration: none" id="pdf_a"><span class="xsp2">PDF试读</span></a>
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="left1" style="margin-right: 10px;">
                    <div id="xsp4"></div>
                    <a href="${map.buy_url}" style="text-decoration: none"><span class="xsp2">立即购买</span>
                    </a>
                </div>
            </div>
            <div class="block">
                <div class="title">
                    <div class="line"></div>
                    <div class="rd_name">图书详情</div>
                </div>
                <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                <div class="aticle">
                    ${map.detail}
                </div>
            </div>
            <c:if test="${ not empty Video}">
                <div class="block">
                    <div class="title">
                        <div class="line"></div>
                        <div class="rd_name">相关视频
                            <div onclick="window.location.href='${ctx}/readdetail/morebookvideo.action?id=${id}'"  style="float: right;margin-left: 50px;color: #489299;font-size: 14px;cursor: pointer;">更多>></div>
                        </div>
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <c:forEach items="${Video}" var="list" varStatus="status">
                        <div class="video" ${status.index=='0' ? 'style="margin-left: 20px"':'style="margin-left: 40px"'} >
                                <%--<video class="video-a" src="http://120.76.221.250:11000/pmph_vedio/file/${list.file_name};"
                                       poster="${ctx}/image/${list.cover}.action" type="mp4" controls>
                                </video>--%>
                            <div class="video-a" id="video-${list.id}"
                                 src="http://${_remoteVideoUrl}/v/play/${list.file_name}"
                                 poster="${ctx}/image/${list.cover}.action" type="mp4">

                            </div>

                            <div class="video-btn">微视频</div>
                            <div class="video-name">${list.title}</div>
                        </div>
                    </c:forEach>
                    <script type="text/javascript">
                        $(function () {
                            $(".video-a").each(function () {
                                var $this = $(this);
                                var videoObject = {
                                    container: "#" + $this.attr("id"),
                                    variable: 'player',
                                    autoplay: false,
                                    /*flashplayer: true,*/
                                    video: $this.attr("src"),
                                    poster: $this.attr("poster")

                                };
                                var player = new ckplayer(videoObject);
                            })
                        })

                    </script>
                </div>
            </c:if>
            <c:if test="${ not empty source}">
                <div class="block">
                    <div class="title">
                        <div class="line"></div>
                        <div class="rd_name">相关资源
                            <div onclick="window.location.href='${ctx}/readdetail/morebookvideo.action?id=${id}'"  style="float: right;margin-left: 50px;color: #489299;font-size: 14px;cursor: pointer;">更多>></div>
                        </div>
                    </div>
                    <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                    <c:forEach items="${source}" var="list" varStatus="status">
                        <div class="right_20">
                            <a href="#"><div class="downimg" onclick="window.location.href='${ctx}/file/download/${list.file_id}.action'">${list.source_name}</div></a>
                            <div class="right_22"></div>
                            <br />
                        </div>
                    </c:forEach>

                </div>
            </c:if>
            <div class="block">
                <div class="title">
                    <div class="line"></div>
                    <div class="rd_name tag active" tagName="changepage">图书评论(共${ComNum}条)</div>
                    <div class="rd_name tag" tagName="correctpage">图书纠错(共${CorrNum}条)</div>
                    <div class="rd_name tag" tagName="feedpage">读者反馈(共${FeedNum}条)</div>
                </div>
                <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 0px;">

                <div class="pl_add changepage list-page">
                    <a name="001" id="001"></a>
                    <textarea class="tarea textarea_content" id="content_book" onkeyup="javascript:LengthLimit(this,500);"
                              onblur="javascript:LengthLimit(this,500);"></textarea>
                    <div style="border-top:1px solid rgba(180, 239, 205, 0.5);margin-left: 16px;margin-rihgt: 16px;text-align: right;font-size: 12px;width: 870px;">
                     评论最多500字</div>
                    <div class="star_num">星级评分:</div>
                    <div class="scorestar" id="star">
                        <div class="scorestar1" id="score1"></div>
                        <div class="scorestar1" id="score2"></div>
                        <div class="scorestar1" id="score3"></div>
                        <div class="scorestar1" id="score4"></div>
                        <div class="scorestar1" id="score5"></div>
                    </div>
                    <div class="aticle">
                        <div class="user_score">
                            <!-- <span>评分：</span> -->
                            <span style="color: #FFD200" id="last_score">10.0</span>
                        </div>
                        <div class="button">
                            <button id="span_4" onclick="insert()">发表</button>
                        </div>
                    </div>
                </div>
                <div class="">

                    <divlistCom class="list-page changepage" id="changepage">
                        <c:forEach items="${listCom}" var="list" begin="0" end="1">
                            <div class="item">
                                <div class="item_title">
                                    <div style="float: left;">
                                        <c:if test="${list.avatar=='DEFAULT'}"><img
                                                src="${ctx}/statics/image/default_image.png" class="picturesize"></c:if>
                                        <c:if test="${list.avatar!='DEFAULT'}"><img
                                                src="${ctx}/image/${list.avatar}.action" class="picturesize"></c:if>

                                            <%-- <img src="${ctx}/statics/image/rwtx.png"
                                                                           class="picturesize"/> --%>
                                    </div>
                                    <div style="float: left;margin-left: 10px;margin-top: 5px;">
                                        <c:if test="${list.nickname==null or list.nickname==''}">
                                            ${list.username}
                                        </c:if>
                                        <c:if test="${list.nickname!=null and list.nickname!=''}">
                                            ${list.nickname}
                                        </c:if>
                                    </div>
                                    <div style="float: left;margin-left: 10px;">
                                        <c:if test="${list.score<=3}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score<=5 and list.score>3}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score<=7 and list.score>5}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score<=9 and list.score>7}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score>9}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                        </c:if>
                                    </div>
                                    <div class="date_content">
                                        <div class="date">${list.gmt_create}</div>
                                    </div>
                                </div>
                                <div class="item_content">${list.content}</div>
                                <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                            </div>
                        </c:forEach>
                    </divlistCom>

                    <divlistCorrect class="list-page correctpage" id="correctpage">
                        <c:forEach items="${listCorr}" var="list" begin="0" end="1">
                            <div class="item">
                                <div class="item_title">
                                    <div style="float: left;">
                                        <c:if test="${list.avatar=='DEFAULT'}"><img
                                                src="${ctx}/statics/image/default_image.png" class="picturesize"></c:if>
                                        <c:if test="${list.avatar!='DEFAULT'}"><img
                                                src="${ctx}/image/${list.avatar}.action" class="picturesize"></c:if>
                                    </div>
                                    <div style="float: left;margin-left: 10px;margin-top: 5px;">
                                        <c:if test="${list.nickname==null or list.nickname==''}">
                                            ${list.username}
                                        </c:if>
                                        <c:if test="${list.nickname!=null and list.nickname!=''}">
                                            ${list.nickname}
                                        </c:if>
                                    </div>

                                    <div class="date_content">
                                        <div class="date">${list.gmt_create}</div>
                                    </div>
                                </div>
                                <div class="item_content">
                                       第${list.page}页，第${list.line}行:
                                </div>
                                <div class="item_content">
                                        ${list.content}
                                </div>
                                <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                            </div>
                        </c:forEach>
                    </divlistCorrect>

                    <divlistFeed class="list-page feedpage" id="feedpage">
                        <c:forEach items="${listFeed}" var="list" begin="0" end="1">
                            <div class="item">
                                <div class="item_title">
                                    <div style="float: left;">
                                        <c:if test="${list.avatar=='DEFAULT'}"><img
                                                src="${ctx}/statics/image/default_image.png" class="picturesize"></c:if>
                                        <c:if test="${list.avatar!='DEFAULT'}"><img
                                                src="${ctx}/image/${list.avatar}.action" class="picturesize"></c:if>
                                    </div>
                                    <div style="float: left;margin-left: 10px;margin-top: 5px;">
                                        <c:if test="${list.nickname==null or list.nickname==''}">
                                            ${list.username}
                                        </c:if>
                                        <c:if test="${list.nickname!=null and list.nickname!=''}">
                                            ${list.nickname}
                                        </c:if>
                                    </div>

                                    <div class="date_content">
                                        <div class="date">${list.gmt_create}</div>
                                    </div>
                                </div>
                                <div class="item_content">${list.content}</div>
                                <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                            </div>
                        </c:forEach>
                    </divlistFeed>


                    <div class="morecon">
                        <input type="hidden" value="${start}" class="start changepage">
                        <input type="hidden" value="${start}" class="start correctpage">
                        <input type="hidden" value="${start}" class="start feedpage">

                        <span class="moreothers" onclick="changepage()"
                              id="moreothers">${shortcom=='nothing' ? '[暂无评论]':'加载更多...'}</span>
                        <div class="morecom" style="display: none;"></div>
                    </div>
                    <%--<div id="longcompage">
                        <div class="comm">
                            <div class="longcom">图书长评</div>
                            <div class="writecom" onclick="writeablut()">写书评</div>
                        </div>
                        <c:forEach items="${longList}" var="list" varStatus="status" begin="0" end="1">
                            <div class="item">
                                <div class="item_title">
                                    <div style="float: left;">

                                        <c:if test="${list.avatar=='DEFAULT'}"><img
                                                src="${ctx}/statics/image/default_image.png" class="picturesize"></c:if>
                                        <c:if test="${list.avatar!='DEFAULT'}"><img
                                                src="${ctx}/image/${list.avatar}.action" class="picturesize"></c:if>

                                            &lt;%&ndash; <img src="${ctx}/statics/image/rwtx.png"
                                                                           class="picturesize"/> &ndash;%&gt;
                                    </div>
                                    <div style="float: left;margin-left: 10px;margin-top: 5px;">${list.nickname}</div>
                                    <div style="float: left;margin-left: 10px;">
                                        <c:if test="${list.score<=3}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score<=5 and list.score>3}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score<=7 and list.score>5}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score<=9 and list.score>7}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx2"></span>
                                        </c:if>
                                        <c:if test="${list.score>9}">
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                            <span class="rwtx1"></span>
                                        </c:if>
                                    </div>
                                    <div class="date_content">
                                        <div class="date">${list.gmt_create}</div>
                                    </div>
                                </div>
                                <div class="longcom_title">"${list.title}"</div>
                                <div id="${status.index }con" class="item_content"
                                     name="item_content">${list.content}</div>
                                <span id="${status.index }more" style="cursor: pointer;color: #333333;display: none;"
                                      onclick="more('${status.index }con','${status.index }more')">...(展开)</span>
                                <hr style=" height:1px;border:none;border-top:1px solid #f1f1f1;margin-top: 10px;">
                                <input type="hidden" id="long-hidden" value="2">
                            </div>
                        </c:forEach>
                    </div>
                    <div class="morecon">
                        <input type="hidden" value="${start}" id="longstart">
                        <span class="moreothers" id="longothers"
                              onclick="longcom()">${longcom=='nothing' ? '[暂无长评]':'加载更多...'}</span>
                        <div class="morecom" style="display: none;"></div>
                    </div>--%>
                </div>
            </div>
        </div>
        <!--右边区域-->
        <div class="rightarea">
            <div class="right_1">
                <div>
                    <span id="ptts"></span>
                    <span style="font-size: 16px;color: #000000;margin-left: 5px;"><B>配套图书</B></span>
                </div>
                <div style="margin-top: 20px;">
                    <div style="float: left;width: 90px;height: 116px">
                        <input type="hidden" id="sup-hidden" value="${supMap.code}">
                        <img src="${supMap.image_url}" class="righttopbook"/>
                    </div>
                    <div style="float: left;width: 170px;margin-left: 10px;">
                        <div class="ptts_sp1">${supMap.bookname}</div>
                        <div class="watch" onclick="todetail('${supMap.id}')">
                            <div class="lookbook">去看看</div>
                        </div>
                        <div class="ptts_sp2">${supMap.author}</div>
                        <div class="ptts_sp3">${supMap.detail}</div>
                    </div>
                </div>
            </div>
            <div class="right_3">
                <div class="right_4">
                    <div class="right_5">
                        <div class="right_6"></div>
                        <div class="right_7">
                            <span id="span_3">教材关联图书</span>
                        </div>
                    </div>
                    <div class="right_8 relatiedBookPageSwitchWrapper 1" style="display: none;">
                        <img src="../statics/image/refresh.png" style="float:left;margin-left:80px">
                        <!-- <div class="refresh" onclick='fresh("6")'>换一批</div> -->
                        <div class="refresh" onclick='relatiedBookPageSwitch("1")'>换一批</div>
                    </div>
                </div>
                <div id="about">
                	<input class="relation_page" value="0" type="hidden"></input>
                	<input class="relation_totalPage" value="-1" type="hidden" ></input>
                    <c:forEach items="${frList}" var="list">
                        <div class="right_9" onclick="todetail('${list.id}')">
                            <div class="right_10">
                                <img src="${list.image_url}" class="right_12">
                            </div>
                            <div class="right_11">${list.bookname}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="right_3">
                <div class="right_4">
                    <div class="right_5">
                        <div class="right_6a"></div>
                        <div class="right_7">
                            <span id="span_3">相关推荐</span>
                        </div>
                    </div>
                    <div class="right_8 relatiedBookPageSwitchWrapper 2" style="display: none;">
                        <img src="../statics/image/refresh.png" style="float:left;margin-left:80px">
                        <div class="refresh" onclick='relatiedBookPageSwitch("2")'>换一批</div>
                    </div>
                </div>
                <div id="change">
                	<input class="relation_page" value="0" type="hidden"></input>
                	<input class="relation_totalPage" value="-1" type="hidden" ></input>
                    <c:forEach items="${auList}" var="list">
                        <div class="right_9" onclick="todetail('${list.id}')">
                            <div class="right_10">
                                <img src="${list.image_url}" class="right_12">
                            </div>
                            <div class="right_11">${list.bookname}</div>
                        </div>
                    </c:forEach>
                    <c:forEach items="${tMaps}" var="list">
                        <div class="right_9" onclick="todetail('${list.id}')">
                            <div class="right_10">
                                <img src="${list.image_url}" class="right_12">
                            </div>
                            <div class="right_11">${list.bookname}</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="right_13"></div>
            <div class="right_14">
                <div class="right_15">
                    <div class="right_16">
                        <div class="right_17"></div>
                        <div class="right_18">人卫推荐</div>
                    </div>
                </div>
                <div class="right_19 relatiedBookPageSwitchWrapper 3" style="display: none;">
                    <div class="picture1"></div>
                    <div class="refresh1" onclick="relatiedBookPageSwitch('3')">换一批</div>
                </div>
                <div id="comment">
                	<input class="relation_page" value="0" type="hidden"></input>
                	<input class="relation_totalPage" value="-1" type="hidden" ></input>
                    <c:forEach items="${eMap}" var="list">
                        <div class="right_20">
                            <div class="right_21" onclick="todetail('${list.id}')">${list.bookname}</div>
                            <div class="right_22">（${list.author}）</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
	</div>
</div>
<div style="clear:both;"></div>
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
<script type="text/javascript">
    $(document).ready(function () {
        //为所有的class为scorestar1绑定mouseout和mouseover事件。bind({事件名：function(){},事件名：function(){}})的方法绑定多个事件
        $(".scorestar1").bind({
            mouseover: function () {
                $(this).css("background-position", "-183px -174px").prevAll().css("background-position", "-183px -174px");
                $(this).nextAll().css({"background-position": "-183px -153px"});
                var score = parseInt($(this).attr("id").substring(5)) * 2 + '.0';
                $("#last_score").html(score);
            }
        });
    });
</script>
<%--<div style="display: none" id="pop-upload">
    <div>
        <div class='pop-body'>
            <div class='title'>当前上传：幼儿护理学</div>
            <div class='remark'>视频上传中，请勿关闭页面</div>
            <div class='layui-progress layui-progress-big' lay-filter='demo' lay-showPercent='true'>
                <div class='layui-progress-bar' lay-percent='0%'></div>
            </div>
            <div class='relate-img'>
                <img src="" alt="">
                <div class="shade"></div>
                <div class="add-icon" id="add-icon">+</div>
            </div>
        </div>
    </div>
</div>--%>
</body>
</html>

