<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <script type="text/javascript">
        var contextpath = '${pageContext.request.contextPath}/';
    </script>
    <title>热门书评列表</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <link href="${ctx}/statics/css/base.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/statics/css/jquery.pager.css" type="text/css">
    <link rel="stylesheet" href="${ctx}/statics/commuser/hotComment/hotCommentList.css" type="text/css">
    <script src="${ctx}/resources/comm/jquery/jquery.js"></script>
    <script src="${ctx}/resources/comm/base.js"></script>
    <script src="${ctx}/resources/comm/jquery/jquery.pager.js"></script>
    <script src="${ctx}/resources/commuser/hotComment/hotCommentList.js"></script>
</head>
<style type="text/css">

        #right .select-wrapper {
            border: none;
        }
         #right .select-button {
            background: #f6f6f6;
        }
</style>
<body>
<jsp:include page="/pages/comm/head.jsp"></jsp:include>
	<div class="body"> 
		<div class="content-wrapper">
			<div class="bigLeft">
				<div class="navDiv">
					<div class="nav">文章 > 查看长评</div>
				</div>
				<div class="oneComment">
					<div class="top">
						<div class="pic"><img style="width: 32px;height: 32px" src="${ctx}/statics/pictures/head.png"></div>
						<div class="nameAndTime">
							<div class="name">阿酷拉玛塔塔</div>
							<div class="star">
							<div class="oneStar">
								</div><div class="oneStar"></div>
							</div>
							<div class="time">2008-01-31 14:53:48</div>
						</div>
					</div>
					<div class="mid">
						<div class="title">"陪他走的愈远，愈怕从此不见。"</div>
					</div>
					<div class="bottom">
						<div class="contentDiv">最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散
  						</div>
  						<div class="open">(展开)</div>
					</div>
				</div> 
				<div class="oneComment">
					<div class="top">
						<div class="pic"><img style="widows: 32px;height: 32px" src="${ctx}/statics/pictures/head.png"></div>
						<div class="nameAndTime">
							<div class="name">阿酷拉玛塔塔</div>
							<div class="star">
							<div class="oneStar">
								</div><div class="oneStar"></div>
							</div>
							<div class="time">2008-01-31 14:53:48</div>
						</div>
					</div>
					<div class="mid">
						<div class="title">"陪他走的愈远，愈怕从此不见。"</div>
					</div>
					<div class="bottom">
						<div class="contentDiv">最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散
  						</div>
  						<div class="open">(展开)</div>
					</div>
				</div> 
				<div class="oneComment">
					<div class="top">
						<div class="pic"><img style="widows: 32px;height: 32px" src="${ctx}/statics/pictures/head.png"></div>
						<div class="nameAndTime">
							<div class="name">阿酷拉玛塔塔</div>
							<div class="star">
							<div class="oneStar">
								</div><div class="oneStar"></div>
							</div>
							<div class="time">2008-01-31 14:53:48</div>
						</div>
					</div>
					<div class="mid">
						<div class="title">"陪他走的愈远，愈怕从此不见。"</div>
					</div>
					<div class="bottom">
						<div class="contentDiv">最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散
  						</div>
  						<div class="open">(展开)</div>
					</div>
				</div> 
				<div class="oneComment">
					<div class="top">
						<div class="pic"><img style="widows: 32px;height: 32px" src="${ctx}/statics/pictures/head.png"></div>
						<div class="nameAndTime">
							<div class="name">阿酷拉玛塔塔</div>
							<div class="star">
							<div class="oneStar">
								</div><div class="oneStar"></div>
							</div>
							<div class="time">2008-01-31 14:53:48</div>
						</div>
					</div>
					<div class="mid">
						<div class="title">"陪他走的愈远，愈怕从此不见。"</div>
					</div>
					<div class="bottom">
						<div class="contentDiv">最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散
  						</div>
  						<div class="open">(展开)</div>
					</div>
				</div> 
				<div class="oneComment">
					<div class="top">
						<div class="pic"><img style="widows: 32px;height: 32px" src="${ctx}/statics/pictures/head.png"></div>
						<div class="nameAndTime">
							<div class="name">阿酷拉玛塔塔</div>
							<div class="star">
							<div class="oneStar">
								</div><div class="oneStar"></div>
							</div>
							<div class="time">2008-01-31 14:53:48</div>
						</div>
					</div>
					<div class="mid">
						<div class="title">"陪他走的愈远，愈怕从此不见。"</div>
					</div>
					<div class="bottom">
						<div class="contentDiv">最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散最近学车，在市区与郊区间奔波。于是在班车上看完杨绛先生的《我们仨》。 说实话这本早就被捧得沸沸扬扬的书之所以拖到今天，是因为我对 
											   钱钟书的了解还仅限于《围城》。可就在翻到目录的一瞬间，我知道这次它给我的感动将不同以往。 第一部：我们俩老了。 第二部：我们仨失散
  						</div>
  						<div class="open">(展开)</div>
					</div>
				</div> 
				
				<div class="pageDiv">
                <div style="float: right;">
                    <ul class="pagination" id="page1">
                    </ul>
                    <div style="display: inline-block;vertical-align: top">
                        <select id="edu" name="edu" >
                            <option value="2" >每页2条</option>
                            <option value="3" >每页3条</option>
                            <option value="4" >每页4条</option>
                        </select>
                    </div>
                    <div class="pageJump">
                        <span>共页，跳转到</span>
                        <input type="text" id="toPage"/>
                        <span class="pp">页</span>
                        <button type="button" class="button">确定</button>
                    </div>
                </div>
            </div>
			</div>
			<div class="bigRight">
				<div class="right_1">
	                <div>
	                    <span id="ptts"></span>
	                    <span style="font-family: MicrosoftYaHei;font-size: 16px;color: #000000;margin-left: 5px;"><B>配套图书</B></span>
	                </div>
	                <div style="margin-top: 20px;">
	                    <div style="float: left;width: 90px;height: 116px">
	                        <img src="<%-- ${supMap.image_url} --%>${ctx}/statics/image/ts_06.png" class="righttopbook"/>
	                    </div>
	                    <div style="float: left;width: 170px;margin-left: 10px;">
	                        <div class="ptts_sp1">${supMap.book_name}麻醉解剖学学习指导与习题集（第3版）</div>
	                        <div class="watch" onclick="todetail('<%-- ${supMap.id} --%>168')">
	                            <div class="lookbook">去看看</div>
	                        </div>
	                        <div class="ptts_sp2">${supMap.realname}张励才</div>
	                        <div class="ptts_sp3">${supMap.detail}全国高等学校麻醉学专业第四轮规划教材配套教材，根据麻醉...</div>
	                    </div>
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
	                    <div class="right_8">
	                        <img src="../statics/image/refresh.png" style="float:left;margin-left:80px">
	                        <div class="refresh" onclick='fresh("9")'>换一批</div>
	                    </div>
	                </div>
	                <div id="change">
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
	                <div class="right_19">
	                    <div class="picture1"></div>
	                    <div class="refresh1" onclick="change()">换一批</div>
	                </div>
	                <div id="comment">
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
<div style="clear:both;"></div>
<div style="background-color: #f8f8f8">
<jsp:include page="/pages/comm/tail.jsp"></jsp:include>
</div>
<script>
    Page({
        num: parseInt(10),					//页码数
        startnum: parseInt(1),				//指定页码
        elem: $('#page1'),		//指定的元素
        callback: function (n) {	//回调函数
            console.log(n);
            window.location.href="${ctx}/schedule/scheduleList.action?currentPage="+n+"&pageSize="+$("input[name='edu']").val(); 
        }
    });
    $(function () {
        $('#edu').selectlist({
            width: 110,
            height: 30,
            optionHeight: 30,
            onChange:function (){
            	window.location.href="";
            }
            
        });
    })
</script>
</body>
</html>