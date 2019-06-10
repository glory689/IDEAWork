<%@page pageEncoding="UTF-8" contentType="text/html; utf-8" isELIgnored="false" %>

<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${pageContext.request.contextPath}/album/showAll",
            editurl: "${pageContext.request.contextPath}/album/edit",
            //定义返回的数据类型
            datatype: "json",
            //定义列名
            colNames: ["标题", "分数", "作者", "播音员", "章节数", "上架时间", "简介", "插图"],
            styleUI: "Bootstrap",
            pager: "#albumPager",
            rowNum: 3,
            rowList: [3, 6, 9, 12],
            viewrecords: true,
            autowidth: true,
            height: '200px',
            multiselect: true,
            viewrecords: true,
            rownumbers: true,
            subGrid: true,
            //调用子表的function方法
            subGridRowExpanded: function (subGrId, albumId) {
                addSubGrid(subGrId, albumId);
            },
            //通过ColModel填充具体的数据内容
            colModel: [
                {name: 'title', editable: true},
                {name: 'score', editable: true},
                {name: 'author', editable: true},
                {name: 'broadcast', editable: true},
                {name: 'count'},
                {name: 'createDate'},
                {name: 'brief', editable: true},
                {
                    name: 'coverPic', editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:30px;width:30' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
                    }
                },
            ],
        }).jqGrid("navGrid", "#albumPager",
            {addtext: "添加", edittext: "编辑", deltext: "删除", search: false},
            {
                //修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var albumId = response.responseJSON.albumId;
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "coverPic",
                        type: "post",
                        data: {albumId: albumId},
                        success: function () {
                            $("#albumList").trigger("reloadGrid");
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 3000);
                        }
                    })
                    return response;
                }

            },
            {
                //添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var albumId = response.responseJSON.albumId;
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/album/upload",
                        fileElementId: "coverPic",
                        type: "post",
                        data: {albumId: albumId},
                        success: function () {
                            $("#albumList").trigger("reloadGrid");
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 3000);
                        }
                    })
                    return response;
                }
            }, {//删除
                afterComplete: function (response) {
                    var msg = response.responseJSON.msg;
                    $("#bannerMsgId").show().html(msg);
                    setTimeout(function () {
                        $("#bannerMsgId").hide()
                    }, 3000);
                }


            }
        );
    })

    //=============================================子表=================================================
    function addSubGrid(subGrId, albumId) {
        console.log(subGrId);
        console.log(albumId);
        var subGridTableId = subGrId + "table";
        var subGridPagerId = subGrId + "pager";
        $("#" + subGrId).html(
            "<table id='" + subGridTableId + "' class=\"table table-striped\"></table>" +
            "<div id='" + subGridPagerId + "' style=\"height: 50px\"></div>")
        $("#" + subGridTableId).jqGrid({
            url: "${pageContext.request.contextPath}/chapter/showAll?albumId=" + albumId,//----------chapter的后台
            editurl: "${pageContext.request.contextPath}/chapter/edit?albumId=" + albumId,
            //定义返回的数据类型
            datatype: "json",
            //定义列名
            colNames: ["标题", "大小", "时长", "音频", "操作"],
            styleUI: "Bootstrap",
            pager: "#" + subGridPagerId,
            rowNum: 3,
            rowList: [3, 6, 9, 12],
            viewrecords: true,
            autowidth: true,
            height: '150px',
            multiselect: true,
            viewrecords: true,
            rownumbers: true,

            subGridRowExpanded: function (subGrId, albumId) {
                addSubGrid(subGrId, albumId);
            },
            //通过ColModel填充具体的数据内容
            colModel: [
                {name: 'title', editable: true},
                {name: 'size'},
                {name: 'duration'},
                {name: 'audio', editable: true, edittype: "file"},
                {
                    name: 'audio',
                    formatter: function (cellValue, options, value) {
                        return "<a href='#' onclick=\"playAudio('" + cellValue + "')\"><span class='glyphicon glyphicon-play-circle'></span></a>&nbsp;&nbsp;&nbsp;&nbsp;" +
                            "<a href='#' onclick=\"downLoadAudio('" + cellValue + "')\"><span class='glyphicon glyphicon-download'></span></a>";
                    }
                },
            ],
        }).jqGrid("navGrid", "#" + subGridPagerId,
            {addtext: "添加", edittext: "编辑", deltext: "删除", search: false},
            {
                //修改
                closeAfterEdit: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseJSON.chapterId;
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/upload",
                        fileElementId: "audio",
                        type: "post",
                        data: {chapterId: chapterId},
                        success: function () {
                            $("#albumList").trigger("reloadGrid");
                            $("#bannerMsgId").show().html(msg);
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 3000);
                        }
                    })
                    return response;
                }
            },
            {
                //添加
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseJSON.chapterId;
                    var msg = response.responseJSON.msg;
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/upload",
                        fileElementId: "audio",
                        type: "post",
                        data: {chapterId: chapterId},
                        success: function () {
                            $("#albumList").trigger("reloadGrid");
                            setTimeout(function () {
                                $("#bannerMsgId").hide()
                            }, 3000);
                        }
                    })
                    return response;
                }
            }, {//删除
                afterComplete: function (response) {
                    var msg = response.responseJSON.msg;
                    $("#bannerMsgId").show().html(msg);
                    setTimeout(function () {
                        $("#bannerMsgId").hide()
                    }, 3000);
                }
            }
        );
    }

    function playAudio(cellvalue) {
        $("#playAudio").modal("show")
        $("#audioId").attr("src", "${pageContext.request.contextPath}/upload/audio/" + cellvalue);
    }

    function downLoadAudio(cellvalue) {
        location.href = "${pageContext.request.contextPath}/chapter/downLoadAudio?audioName=" + cellvalue;
    }

</script>
<h3>专辑管理</h3>
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">专辑信息</a>
        </li>
    </ul>
</div>
<div id="playAudio" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div><!-- /.modal-dialog -->
</div>
<table id="albumList" class="table table-striped"></table>
<div id="albumPager" style="height: 50px"></div>
<div id="bannerMsgId" class="alert alert-warning alert-dismissible" role="alert" style="display:none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <strong>Warning!</strong>
</div>





