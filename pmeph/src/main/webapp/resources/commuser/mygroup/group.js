
function ChangeDiv(type){
    if(type=='commnions'){
        document.getElementById("commnions_top").setAttribute("class","clicked");
        document.getElementById("filesgx_top").setAttribute("class","clickbefore");
        document.getElementById("filesgx").setAttribute("class","hidden");
        document.getElementById("commnions").setAttribute("class","show");
    }else{
        document.getElementById("filesgx_top").setAttribute("class","clicked");
        document.getElementById("commnions_top").setAttribute("class","clickbefore");
        document.getElementById("commnions").setAttribute("class","hidden");
        document.getElementById("filesgx").setAttribute("class","show");
    }


}