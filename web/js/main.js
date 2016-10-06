
$(function(){
    
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
        $(this).find("i").toggleClass("glyphicon-chevron-left glyphicon-chevron-right");
    });
    
});
