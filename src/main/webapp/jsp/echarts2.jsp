<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--引入jquery-->
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <!--引入echarts插件-->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
</head>
<body>


<!-- 为Echarts 装备一个具备大小（高度）的DOM-->
<div id="main" style="width:600px; height: 400px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '近七天注册用户走势图' //鼠标移入时的提示
            //subtext:'副标题'
        },
        tooltip: {},
        legend: { //选项卡
            data: ['注册用户量']
        },
        xAxis: {  // x轴
            data: ["第一天", "第二天", "第三天", "第四天", "第五天", "第六天", "第七天"]
        },
        yAxis: {},  // y轴
        series: [{
            name: '注册用户量',
            type: 'line', //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
            data: [1, 20, 40, 60, 30, 34, 78] //y的具体值
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

    //发送ajax异步请求 从数据库中查询 数据
    $.ajax({

        url: "${pageContext.request.contextPath}/User/getecharts",
        type: "get",
        datatype: "json",
        success: function (data) {
            myChart.setOption({
                series: [{  //需要统计的具体内容
                    name: '注册用户量',
                    type: 'line', //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
                    data: data,//y的具体值  需要处理
                }
                ]
            })
        }
    });


</script>
</body>
</html>