$(function () {
    new FancyGrid({
        renderTo: 'article_container',
        data: [],
        columns: [{
            index: 'id',
            title: '编号',
            type: 'string',
            width: 100
        },{
            index: 'title',
            title: '标题',
            type: 'string',
            width: 200
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