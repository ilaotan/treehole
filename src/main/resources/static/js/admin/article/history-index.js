$(function () {
    var vhObj = new Vue({
        el: "#article_container",
        data: {
            tableCoTable1: [
                {
                    title: '姓名',
                    key: 'name'
                },
                {
                    title: '年龄',
                    key: 'age'
                },
                {
                    title: '地址',
                    key: 'address'
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 150,
                    align: 'center',
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: () => {
                                        show(params.index)
                                    }
                                }
                            }, '查看'),
                            h('Button', {
                                props: {
                                    type: 'erro r',
                                    size: 'small'
                                },
                                on: {
                                    click: () => {
                                        vhObj.remove(params.index)
                                    }
                                }
                            }, '删除')
                        ]);
                    }
                }
            ],
            tableData1: [
                {
                    name: '王小明',
                    age: 18,
                    address: '北京市朝阳区芍药居'
                },
                {
                    name: '张小刚',
                    age: 25,
                    address: '北京市海淀区西二旗'
                },
                {
                    name: '李小红',
                    age: 30,
                    address: '上海市浦东新区世纪大道'
                },
                {
                    name: '周小伟',
                    age: 26,
                    address: '深圳市南山区深南大道'
                }
            ]
        },
        methods: {
            changePage () {
                console.log(this)
                this.$Message.info("this is message")
            },
            remove (index) {
                vhObj.$Message.info("this is remove")
                vhObj.tableData1.splice(index, 1);
            }
        }
    })

    function show (index) {

    }
    function remove (index) {
        vhObj.$Message.info("this is remove")
        vhObj.tableData1.splice(index, 1);
    }
})