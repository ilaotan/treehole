$(function () {
    kindFlag();
    function kindFlag(){
        var map = {
            0:"正常",
            9:"删除"
        }
        var flags = $(".kind-flag");
        $.each(flags,function(){
            const value = $.trim($(this).text());
            var className = "";
            if(value === "9"){
                className = "danger";
            }
            $(this).html("<span class='tag "+className+"'>"+map[value]+"</span>");
        })
    }

    $(".handEdit").click(function () {
        var index = $(this).attr("index")
        console.log(index)
    })
    $(".handDelete").click(function () {
        var index = $(this).attr("index")
        console.log(index)
    })
})