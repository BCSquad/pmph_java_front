$("#male").click( function () {
    $(this).siblings("span").addClass("active");
    $(this).parents("div").siblings("div").children("span").removeClass("active");
});
$("#female").click( function () {
    $(this).siblings("span").addClass("active");
    $(this).parents("div").siblings("div").children("span").removeClass("active");
});