$(function(){

    //INIT FUNCTI
    $('#scene1').css({height: $(window).height() - 75});
    $(window).resize(myResize);
});


function myResize(){
    $('#scene1').css({height: $(window).height() - 75});
}