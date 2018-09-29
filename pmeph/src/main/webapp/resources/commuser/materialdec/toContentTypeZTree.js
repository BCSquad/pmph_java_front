
var expertation_id = $("#expertation_id",window.parent.document).val();
var product_id = $("#product_id",window.parent.document).val();
var product_type = $("#expert_type",window.parent.document).val();

// 已被选中并产生标签于页面的分类id数组
var chooseArr = [];

$("input[name='contentId']",window.parent.document).each(function(){
	var $t = $(this);
	chooseArr.push($t.val());
});
var chooseId = chooseArr.toString()
	.replace(/\[/g, '(')
	.replace(/\]/g, ')').replace(/"/g, "");

var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "parent_id",
					rootPId: 0
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "", "N": "" }
			},
			view: {
				showIcon: false,
				showTitle: false
			}
   };
   // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
   /*var zNodes = [
   {name:"test1", open:true, children:[
      {name:"test1_1"}, {name:"test1_2"}]},
   {name:"test2", open:true, children:[
      {name:"test2_1"}, {name:"test2_2"}]}
   ];*/
   $(document).ready(function(){
	   //弹出框所在框架页面加载完成，请求分类树数据
	   queryTree();
      //zTreeObj = $.fn.zTree.init($("#typeZtree"), setting, zNodes);
   });


function queryTree(){
	
	$.ajax({
        type: "POST",
        url:contextpath+'expertation/queryContentTree.action',
        data:{
        	product_type:product_type,
        	expertation_id:expertation_id,
        	chooseId:chooseId
        	},// 您的formid
        dataType:"json",
        success: function(json) {
            var list = json.rows;
            
            // 和页面已生成的标签对应选中状态 
            for(var j in list){
            	for(var i in chooseArr){
                	var cid = chooseArr[i];
                	list[j].checked = false;
                	if(list[j].id == cid){
            			list[j].checked = true;
            			break;
            		}
                }
        		
        	}
            //初始化ztree
            zTreeObj = $.fn.zTree.init($("#typeZtree"), setting, list);
            
            //已选中的节点 其父级展开
            var nodes = zTreeObj.getCheckedNodes(true);
            for(var i in nodes){
            	zTreeObj.expandNode(nodes[i].getParentNode(), true, false, false);
            }
            //初始化模糊查询（来自ztree，前台查询）
            query();
        }
    });
	
	
}


   
   


/*Page({
    num: parseInt("${pageResult.pageTotal}"),					//页码数
    startnum: parseInt("${pageResult.pageNumber}"),				//指定页码
    elem: $('#page1'),		//指定的元素
    callback: function (n) {	//回调函数
    	var namepath =$("#namepath").val();
        window.location.href="${ctx}/expertation/queryContent.action?currentPage="+n+"&pageSize="+$("input[name='pageSize']").val()+"&product_type="+product_type+"&namepath="+encodeURI(encodeURI(namepath))+"&chooseId="+chooseId;
    }
});
$(function () {
    $('#page-size-select').selectlist({
        width: 100,
        height: 30,
        optionHeight: 30,
        onChange:function (){
        	var namepath =$("#namepath").val();
        	window.location.href="${ctx}/expertation/queryContent.action?currentPage="+n+"&pageSize="+$("input[name='pageSize']").val()+"&product_type="+product_type+"&namepath="+encodeURI(encodeURI(namepath))+"&chooseId="+chooseId;
        }
    });
    $('#org_tab tr:last').find('td').addClass('end'); 
    
    
    
    
    
    
    
});

function tojump(){
	var toPage = $("#toPage").val();
	window.location.href="${ctx}/expertation/queryContent.action?currentPage="+toPage+"&pageSize="+$("input[name='pageSize']").val()+"&product_type="+product_type+"&namepath="+encodeURI(encodeURI(namepath))+"&chooseId="+chooseId;
}*/
//查询
function query(){
	/**
	 * @param zTreeId ztree对象的id,不需要#
	 * @param searchField 输入框选择器 ,需要#但是api没强调，很恶心
	 * @param isHighLight 是否高亮,默认高亮,传入false禁用
	 * @param isExpand 是否展开,默认合拢,传入true展开
	 */
	fuzzySearch ( "typeZtree", "#namepath", true , false);
	/*var namepath =$("#namepath").val();
	window.location.href="${ctx}/expertation/queryContent.action?product_type="+product_type+"&namepath="+encodeURI(encodeURI(namepath))+"&chooseId="+chooseId;*/
}

//确认选择
function selectAdd(){

	var checkedNodes = zTreeObj.getCheckedNodes(true);
	var unCheckedNodes = zTreeObj.getCheckedNodes(false);
	
	// ztree选中的节点要产生标签
	for(var i in checkedNodes){
		var type = checkedNodes[i];
		if($("#nrfl_"+type.id,window.parent.document).length<1 ){
			var str = '<span class="el-tag" id="nrfl_'+type.id+'">'+type.name.replace(/<\/?[A-z]+(\s+.*?)?>/g,"")+'<input name="contentId" type="hidden" value="'+type.id+'"/><span style="margin-left: 8px;cursor: pointer;" onclick="del(\'nrfl_'+type.id+'\')">X</span></span>';
			 window.parent.addContent(str);  //调用父页面方法
		}
	}
	
	// ztree未选中的节点要删除标签
	for(var i in unCheckedNodes){
		var type = unCheckedNodes[i];
		$("#nrfl_"+type.id,window.parent.document).remove();
		
	}

	/*for ( var i in typeNodes) {
		var type = typeNodes[i];
		if(type.checked && $("#nrfl_"+type.id,window.parent.document).length<1 ){
			var str = '<span class="el-tag" id="nrfl_'+type.id+'">'+type.name.replace(/<\/?[A-z]+(\s+.*?)?>/g,"")+'<input name="contentId" type="hidden" value="'+type.id+'"/><span style="margin-left: 8px;cursor: pointer;" onclick="del(\'nrfl_'+type.id+'\')">X</span></span>';
			 window.parent.addContent(str);  //调用父页面方法
		}else if(!type.checked && $("#nrfl_"+type.id,window.parent.document).length>=1){
			$("#nrfl_"+type.id,window.parent.document).remove();
		}
	}*/
	
	//--关闭 当前页面 开始--
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    //--关闭 当前页面 结束--
    
	//获取radio值
	/*var strs= new Array(); //定义一数组 
	var chkObjs =document.getElementsByName("radio_id"); 
	 for(var i=0;i<chkObjs.length;i++){
         if(chkObjs[i].checked){
			 strs = chkObjs[i].value.split("_");
			 var str = '<span class="el-tag" id="nrfl_'+strs[0]+'">'+strs[1]+'<input name="contentId" type="hidden" value="'+strs[0]+'"/><span style="margin-left: 8px;cursor: pointer;" onclick="del(\'nrfl_'+strs[0]+'\')">X</span></span>';
			 window.parent.addContent(str);  //调用父页面方法
         }
     }
	 //--关闭 当前页面 开始--
     var index = parent.layer.getFrameIndex(window.name);
     parent.layer.close(index);*/
     //--关闭 当前页面 结束--
}

//键盘监听
/*$(document).keydown(function(event){ 
	if(event.keyCode == 13){//回车键
		query();
	}
}); */