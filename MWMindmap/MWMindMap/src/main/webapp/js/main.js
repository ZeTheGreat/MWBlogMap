$('.menu__trigger').click(function() {
    $('.menu__ul').toggle();
});
$("a[rel='external']").click(function() {
    window.open($(this).attr('href'));
    return false;
});

$('.link-topo').hide();
$(window).scroll(function() {
    var scrolltop = $(window).scrollTop();
    if (scrolltop > 0) {
        $('.link-topo').fadeIn();
    } else {
        $('.link-topo').fadeOut();
    }
});
