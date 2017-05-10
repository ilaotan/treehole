$(function(){
    var simplemde = new SimpleMDE({
        element: $("#editor")[0],
        hideIcons: ["guide", "heading"]
    });

    $("#a-tags").tagsInput({
        width: 'auto'
    })
})