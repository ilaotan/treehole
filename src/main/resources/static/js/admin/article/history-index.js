$(function () {
    /**
     * 发布
     */
    $(".btn-publish").click(function () {
        var id = $(this).val();
        var index = layer.load(1);
        $.ajax({
            url: "/admin/articles/publish/"+id,
            type: "PUT",
            success: function (data) {
                layer.close(index);
                if(data.code === 200){
                    layer.msg("发布成功",{icon: 6,time:500},function(){
                        window.location.href = "/admin/articles/history";
                    })
                }else{
                    layer.msg("发布失败：" + data.message);
                }
            },
            error: function(){
                layer.close(index);
                layer.msg("发布失败");
            }
        })
    })

    /**
     * 编辑
     */
    $(".btn-edit").click(function () {
        var id = $(this).val();
        window.location.href = "/admin/articles/" + id;
    })

    $(".btn-delete").click(function () {
        var id = $(this).val();
        layer.confirm('要彻底删除吗', {
            btn: ['是的','当然不']
        }, function(){
            deleteArticle(id,"delete");
        }, function(){
            deleteArticle(id, "state");
        });
    })

    var deleteArticle = function (id,state) {
        var index = layer.load(1);
        $.ajax({
            url: "/admin/articles/"+id+"?state="+state,
            type: "DELETE",
            success: function (data) {
                layer.close(index);
                if(data.code === 200){
                    layer.msg("删除成功",{icon: 6,time:500},function(){
                        window.location.href = "/admin/articles/history";
                    })
                }else{
                    layer.msg("删除失败：" + data.message);
                }
            },
            error: function(){
                layer.close(index);
                layer.msg("删除失败");
            }
        })
    }
})