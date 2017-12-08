   function toBookList(id,nameid){
	   var name=$("#title"+nameid).text();
	   window.location.href=contextpath+"bookcollection/tobookcollectionlist.action?favoriteId="+id+"&&favoriteName="+name;
   }