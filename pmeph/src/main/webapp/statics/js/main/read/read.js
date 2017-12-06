//重点推荐表格切换
function ChangeDiv(divId,divName,zDivCount)
{ 
for(i=0;i<=zDivCount;i++) 
{ 
document.getElementById(divName+i).style.display="none"; 
document.getElementById("ZKDiv_"+i).setAttribute("class","tab recommend");
//将所有的层都隐藏 
} 
document.getElementById(divName+divId).style.display="block"; 
document.getElementById("ZKDiv_"+divId).setAttribute("class","tab recommend active");
//显示当前层 
} 

//分页表格切换
function ChangeFYDiv(divId,divName,zDivCount)
{ 
	
for(i=0;i<=zDivCount;i++) 
{ 
document.getElementById(divName+i).style.display="none"; 
document.getElementById("FYDiv_"+i).setAttribute("class","tab type");
//将所有的层都隐藏 
} 
document.getElementById(divName+divId).style.display="block"; 
document.getElementById("FYDiv_"+divId).setAttribute("class","tab type active");
//显示当前层 
} 

//畅销榜表格切换
function ChangeCXDiv(divId,divName,zDivCount)
{ 
	
for(i=0;i<=zDivCount;i++) 
{ 
document.getElementById(divName+i).style.display="none"; 
document.getElementById("CXDiv_"+i).setAttribute("class","tab favorite");
//将所有的层都隐藏 
} 
document.getElementById(divName+divId).style.display="block"; 
document.getElementById("CXDiv_"+divId).setAttribute("class","tab favorite active");
//显示当前层 
} 

//图书分类
function ChangeFLDiv(divId,divName,zDivCount)
{ 
for(i=0;i<=zDivCount;i++) 
{ 
document.getElementById(divName+i).style.display="none"; 
document.getElementById("FLDiv_"+i).setAttribute("class","ts_type");
//将所有的层都隐藏 
} 
document.getElementById(divName+divId).style.display="block"; 
document.getElementById("FLDiv_"+divId).setAttribute("class","ts_type1");
//显示当前层 
} 
