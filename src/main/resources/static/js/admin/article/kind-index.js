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
        var index = $(this).attr("index");
        console.log(index)
    });
    $(".handDelete").click(function () {
        var load = layer.load(1);
        var index = $(this).attr("index")
        $.ajax({
            url: "/admin/articles/kinds/"+index,
            type: "DELETE",
            success: function(data){
                if(data){
                    if(data.code===200){
                        layer.close(load);
                        window.location.href = "/admin/articles/kinds";
                    }
                }
                layer.msg("无返回值");
                layer.close(load)
            },
            error:function () {
                layer.msg("请求出错啦");
                layer.close(load);
            }
        })
    })
})