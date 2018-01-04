




//输入长度限制校验，ml为最大字节长度
function LengthLimit(obj,ml){
	
	var va = obj.value;
	var vat = "";
	for ( var i = 1; i <= va.length; i++) {
		vat = va.substring(0,i);
		//把双字节的替换成两个单字节的然后再获得长度，与限制比较
		if (vat.replace(/[^\x00-\xff]/g,"aa").length <= ml) {
			maxStrlength=i;
		}else{
			
			break;
		}
	}
	obj.maxlength=maxStrlength;
	//把双字节的替换成两个单字节的然后再获得长度，与限制比较
	if (va.replace(/[^\x00-\xff]/g,"aa").length > ml) {
		obj.value=va.substring(0,maxStrlength);
		window.message.warning("不可超过输入最大长度"+ml+"字节！");
	}
}
