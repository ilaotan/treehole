$(function () {
    var data = [];
    for (var i = 0;i<100;i++){
        data.push({
            id:i,
            title:'title',
            subpath:'123'
        })
    }
    new FancyGrid({
        renderTo: 'article_container',
        title: ' ',
        data: data,
        paging: true,
        paging: {
            pageSize: 20,
            refreshButton:true
        },
        height:700,
        selModel: 'rows',
        trackOver: true,
        defaults: {
            type: 'string',
            width: 100,
            editable: true,
            sortable: true,
            resizable: true
        },
        tbar: [{
            type: 'button',
            text: '增加一行',
            width:100,
            action: 'add'
        },{
            type: 'button',
            text: '编辑',
            width:100,
            action: 'edit'
        },{
            type: 'button',
            text: '删除',
            width:100,
            action: 'remove'
        }],
        columns: [{
            index: 'id',
            locked: true,
            editable: false,
            title: '编号',
            type: 'string',
            width: 100
        },{
            index: 'title',
            title: '标题',
            type: 'string',
            width: 200,
            filter: {
                header: true,
                emptyText: '查询'
            }
        },{
            index: 'subpath',
            title: '自定义访问路径',
            type: 'string',
            width: 200
        },{
            index: 'intro',
            title: '简介',
            type: 'string',
            width: 300
        },{
            index: 'kind',
            title: '类别',
            type: 'string',
            width: 100
        },{
            index: 'usecommont',
            title: '允许评论',
            type: 'string',
            width: 100
        },{
            index: 'flag',
            title: '状态',
            type: 'string',
            width: 100
        }]
    });
})