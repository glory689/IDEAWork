<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>持名法州管理系统</title>
    <link rel="icon" href="img/favicon.ico">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boot/css/bootstrap.css">
    <!--引入jqgrid相关的 css-->
    <link rel="stylesheet" href="jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <!--必须先引入 jquery.js-->
    <script src="boot/js/jquery-2.2.1.min.js"></script>
    <!-- bootstrap的js -->
    <script src="boot/js/bootstrap.js"></script>
    <!--jqgrid  js-->
    <script src="jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%-- ajaxFileUpload  --%>
    <script src="boot/js/ajaxfileupload.js"></script>
    <script charset="UTF-8" src="kindeditor/kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="kindeditor/lang/zh-CN.js"></script>


</head>
<body>
<!--=========================导航栏=========================-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法州后台管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:<span class="text-primary">admin</span></a></li>
                <li><a href="#">安全退出&nbsp;<span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<!-- ==========================栅格系统======================== -->
<div class="container-fluid">
    <div class="row">
        <!-- =====栅格2====手风琴========= -->
        <div class="col-xs-2">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                用户
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('user.jsp')">用户管理</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                上师
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <li><a href="">上师管理</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('article.jsp')">文章管理</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour" aria-expanded="false" aria-controls="collapseThree">
                                专辑
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFour">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('album.jsp')">专辑管理</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive" aria-expanded="false" aria-controls="collapseThree">
                                轮播图
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFive">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('banner.jsp')">轮播图管理</a></li>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- =======栅格10===轮播图======= -->
        <div id="changeContent" class="col-xs-10">
            <div class="jumbotron">
                <h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎来到持名法州后台管理系统</h3>
            </div>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- 指示器 -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- 轮播图片及说明文字 -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/shouye.jpg" alt="图片1">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/1.jpg" alt="图片2">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/0.jpg" width="1366" height="200" alt="图片3">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/2.jpg" alt="图片4">
                        <div class="carousel-caption">
                            ...
                        </div>
                    </div>
                </div>
                <!-- 控制按钮：左右切换 -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<br/>


<!-- ============================网页底部============================ -->
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div>
        <h5 class="text-center">@百知教育@baizhi.com.cn</h5>
    </div>
</nav>
</body>
</html>