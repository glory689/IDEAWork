<%@page pageEncoding="utf-8" isELIgnored="false" contentType="text/html; utf-8" %>
<script>
    $(function () {
        $("#testlist").jqGrid({
            url: "${pageContext.request.contextPath}/banner/showAll",
            editurl: "${pageContext.request.contextPath}/banner/edit",
            //定义返回的数据类型
            datatype: "json",
            //定义列名
            colNames: ["ID", "标题", "状态", "创建时间", "描述", "图片"],
            styleUI: "Bootstrap",
            pager: "#pager",
            rowNum: 3,
            rowList: [3, 6, 9, 12],
            viewrecords: true,
            autowidth: true,
            height: '200px',
            multiselect: true,
            viewrecords: true,
            rownumbers: true,
            //通过ColModel填充具体的数据内容
            colModel: [
                {name: 'id'},
                {name: 'title', editable: true},
                {
                    name: 'status', editable: true, edittype: "select",
                    editoptions: {value: "展示:展示;不展示:不展示"}
                },
                {name: 'createDate'/*, editable: true,edittype:"date"*/},
                {name: 'description', editable: true},
                {
                    name: 'imgPic', editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        console.log(cellvalue);
                        console.log(rowObject);
                        return "<img style='height:30px;width:30' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
                    }
                }
            ],

        }).jqGrid("navGrid", "#pager",
            {addtext: "添加", edittext: "编辑", deltext: "删除", search: false},
            {//修改
                afterSubmit: function (response) {
                    var bannerId = response.responseJSON.bannerId;
                    console.log(bannerId)
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "imgPic",
                        type: "post",
                        data: {bannerId: bannerId},
                        success: function () {
                            $("#testlist").trigger("reloadGrid");
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 3000);
                        }
                    })
                    return response;
                }
            },
            {//添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseJSON.bannerId;
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/upload",
                        fileElementId: "imgPic",
                        type: "post",
                        data: {bannerId: bannerId},
                        success: function () {
                            $("#testlist").trigger("reloadGrid");
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 3000);
                        }
                    })
                    return response;
                }
            },
            {//删除
                afterComplete: function (response) {
                    var msg = response.responseJSON.msg;
                    $("#bannerMsgId").show().html(msg);
                    setTimeout(function () {
                        $("#bannerMsgId").hide()
                    }, 3000);
                }
            }
        );
    });
</script>
<h3>轮播图管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图信息</a>
        </li>
    </ul>
</div>
<table id="testlist" class="table table-striped"></table>
<div id="pager" style="height: 50px"></div>
<div id="bannerMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <strong>Warning!</strong>
</div>















