$(function () {
    $("#edit-profile").click(function(){
        var win = window.newWindow($("#bloginfo-profile-edit"), {
            width: '700px',
            height: '350px'
        },function () {
            return false;
        },function () {
            return false;
        });
    })
})