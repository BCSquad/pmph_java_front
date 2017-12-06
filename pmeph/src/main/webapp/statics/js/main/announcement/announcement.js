
function ChangeDiv(type){
    if(type=='infoReport'){
        document.getElementById("infoReport").setAttribute("class","clicked");
        document.getElementById("selectAnnco").setAttribute("class","clickbefore");
        //document.getElementById("filesgx").setAttribute("class","hidden");
        //document.getElementById("commnions").setAttribute("class","show");
    }else{
        document.getElementById("selectAnnco").setAttribute("class","clicked");
        document.getElementById("infoReport").setAttribute("class","clickbefore");
        //document.getElementById("commnions").setAttribute("class","hidden");
        //document.getElementById("filesgx").setAttribute("class","show");
    }


}