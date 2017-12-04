   function toBookList(id,nameid){
	   var name=$("#title"+nameid).text();
	   window.location.href=contxtpath+"/bookcollection/tobookcollectionlist.action?favoriteId="+id+"&&favoriteName="+name;
   }