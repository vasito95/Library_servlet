$(document).ready(function() {
    $(".dropdown-toggle").click(function() {
        $(".dropdown").toggleClass("open");
    });
    $(".author-input").click(function(){
        let input = $("#author").clone();
        input.val("");
        input.attr('value',' ');
        $(".author-box").append(input);
    })
});