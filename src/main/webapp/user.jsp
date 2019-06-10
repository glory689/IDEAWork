<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>
<script>
    $(function () {
        $("#userList").jqGrid({
            url: "${pageContext.request.contextPath}/user/showAll",
            editurl: "${pageContext.request.contextPath}/user/edit",
            //定义返回的数据类型
            datatype: "json",
            //定义列名
            colNames: ["手机号", "密码", "姓名", "法名", "性别", "省份", "城市", "签名", "注册日期", "状态", "头像"],
            colModel: [
                {name: 'phoneNum', editable: true},
                {name: 'password', editable: true},
                {name: 'name', editable: true},
                {name: 'dharma', editable: true},
                {name: 'sex', editable: true},
                {name: 'province', editable: true},
                {name: 'city', editable: true},
                {name: 'sign', editable: true},
                {name: 'createDate'},
                {
                    name: 'status', editable: true, edittype: "select",
                    editoptions: {value: "正常:正常;冻结:冻结"}
                },
                // {name:"create_date",align:"center"},
                {
                    name: 'headPic', editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:30px;width:30' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"

                    }
                },

            ],
            styleUI: "Bootstrap",
            pager: "#userPager",
            rowNum: 3,
            rowList: [3, 6, 9, 12],
            viewrecords: true,
            autowidth: true,
            height: '200px',
            multiselect: true,
            viewrecords: true,

            rownumbers: true,
        })
    })

    function outUserMsg() {
        location.href = "${pageContext.request.contextPath}/user/easyPoiOut"
    }

    /*function xxx() {
        location.href="${pageContext.request.contextPath}/jsp/echarts.jsp"
}
function zzz() {
    location.href="${pageContext.request.contextPath}/jsp/echarts2.jsp"
}*/
</script>
<h3>用户管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">用户信息</a></li>
        <li><a href="#" onclick="outUserMag()">导出用户信息</a></li>
        <li><a href="#" onclick="xxx()">cmfz用户图</a></li>
        <li><a href="#" onclick="zzz()">cmfz线条图</a>
        </li>
    </ul>
</div>
<table id="userList" class="table table-striped"></table>
<div id="userPager" style="height: 50px"></div>
