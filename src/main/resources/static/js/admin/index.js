$(function(){
    var chart_line_visit = echarts.init(document.getElementById("chart-line-visit"));
    var option = {
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
            data: ['周一','周二','周三','周四','周五','周六','周日']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name:'邮件营销',
                type:'line',
                stack: '总量',
                data:[0, 3, 8, 2, 5, 12, 4]
            }
        ]
    };
    chart_line_visit.setOption(option)
})