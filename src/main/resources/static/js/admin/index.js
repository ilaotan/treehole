$(function(){
    var charts = echartsInit();
    var line_date = [
        ['2017-05-08','2017-05-07','2017-05-06','2017-05-05','2017-05-04','2017-05-03','2017-05-02'],
        [0, 3, 8, 2, 5, 12, 4]
    ]
    charts.visit.line.init(line_date).create();

    var pie_data = [
        {value:335, name:'直接访问'},
        {value:310, name:'邮件营销'},
        {value:234, name:'联盟广告'},
        {value:135, name:'视频广告'},
        {value:1548, name:'搜索引擎'}
    ];
    charts.visit.pie.init(pie_data).create();
})
function echartsInit(){
    var charts = {
        visit:{
            line:{
                chart: '',
                option:'',
                init:function(optiondate){
                    this.echartInit();
                    this.optionInit(optiondate);
                    return this;
                },
                echartInit:function(){
                    this.chart = echarts.init(document.getElementById("chart-line-visit"));
                },
                optionInit:function(date){
                    this.option = {
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data:['访问量']
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'category',
                            boundaryGap: false,
                            data: date[0]
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name:'当日访问量',
                                type:'line',
                                data: date[1]
                            }
                        ]
                    }
                },
                create:function(){
                    this.chart.setOption(this.option)
                }
            },
            pie:{
                chart:'',
                option:'',
                init:function(optiondata){
                    this.echartInit();
                    this.optionInit(optiondata);
                    return this;
                },
                echartInit:function(){
                    this.chart = echarts.init(document.getElementById("chart-pie-visit"));
                },
                optionInit:function(data){
                    this.option = {
                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },
                        series : [
                            {
                                name: '访问来源',
                                type: 'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data: data,
                                itemStyle: {
                                    emphasis: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    }
                },
                create:function(){
                    this.chart.setOption(this.option)
                }
            }
        }
    }
    return charts;
}