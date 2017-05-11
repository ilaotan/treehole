$(function(){
    var simplemde = new SimpleMDE({
        element: $("#editor")[0],
        hideIcons: ["guide", "heading"]
    });
    simplemde.value("这里是文章简介 \n\n <!-- more --> \n\n 这里是文章内容")
    $("#a-tags").tagsInput({
        width: 'auto'
    })
})