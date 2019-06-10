<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; utf-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src=""></script>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="echarts.min.js"></script>
</head>
<body>

<script>
    var goEasy = new goEasy({
        appkey: ""
    });
    goEasy.subscribe({
        channel: "",
        onMessage: function (message) {
            alert("Channel:" + message.channel + "content:" + message.content);
        }
    });
</script>
</body>
</html>