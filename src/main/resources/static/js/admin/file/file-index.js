$(function () {
    var fileContainer = $("#file_container");
    var table = fileContainer.find("table");
    var refreshBtn = fileContainer.find(".refresh-link");

    refreshBtn.click(function () {
        getFileInfos();
    })

    function getFileInfos() {
        var index = layer.load(1)
        $.ajax({
            url: "/admin/files/list",
            type: "GET",
            dataType: "json",
            success: function (data) {
                if (data) {
                    if (data.code === 200) {
                        bulidTable(data.result.data)
                    } else {
                        layer.alert(data.message);
                    }
                } else {
                    layer.alert("获取数据失败")
                }
                layer.close(index);
            },
            error: function () {
                layer.close(index);
            }
        })
    }

    function bulidTable(files) {
        var url = window.location.href;
        var tbody = fileContainer.find("table tbody")
        //清空表格中的数据
        tbody.html("")
        var bodyHtml = "";
        $.each(files, function () {
            var uurl = url + "/" + this.alias;
            bodyHtml += "<tr>";
            bodyHtml += "<td>" + this.id + "</td>";
            bodyHtml += "<td>" + this.name + "</td>";
            bodyHtml += "<td>" + this.contentType + "</td>";
            bodyHtml += "<td><span>" + uurl + "</span><a class='copyContent'>复制<a></td>";
            bodyHtml += "<td><i class='fa fa-eye showDetail'></i></td>";
            bodyHtml += "<td><button class='btn btn-danger btn-sm deleteBtn' value='"+this.id+"'>删除</td>";
            bodyHtml += "</tr>";
        });
        tbody.html(bodyHtml);

        $(".deleteBtn").click(function () {
            layer.msg("delete :"+$(this).val())
        })

        $(".showDetail").click(function () {
            var url = $(this).parent().prev().text();
            var contentType = $(this).parent().prev().prev().text();
            if(contentType!="image/png"){
                layer.msg("暂时无法预览此类文件");
                return;
            }
            layer.open({
                type: 1,
                title: false,
                closeBtn: 0,
                shadeClose: true,
                skin: 'img-show',
                content: "<img src='"+url+"'>"
            });
        });

        $(".copyContent").click(function () {
            var text = $(this).prev().text();
            copyToClipboard(text)
            layer.msg("小主，内容已经复制到剪贴板");
        });
    }
    getFileInfos();
    function copyToClipboard(txt) {
        if (window.clipboardData) {
            window.clipboardData.clearData();
            window.clipboardData.setData("Text", txt);
            alert("已经成功复制到剪帖板上！");
        } else if (navigator.userAgent.indexOf("Opera") != -1) {
            window.location = txt;
        } else if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            } catch (e) {
                alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
            }
            var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
            if (!clip) return;
            var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
            if (!trans) return;
            trans.addDataFlavor('text/unicode');
            var str = new Object();
            var len = new Object();
            var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var copytext = txt;
            str.data = copytext;
            trans.setTransferData("text/unicode", str, copytext.length * 2);
            var clipid = Components.interfaces.nsIClipboard;
            if (!clip) return false;
            clip.setData(trans, null, clipid.kGlobalClipboard);
            alert("已经成功复制到剪帖板上！");
        }
    }
});