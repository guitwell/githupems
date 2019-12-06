<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/6
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>主页</title>
    <!--引入css bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/bootstrap.min.css">
    <!--引入jqgrid的css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/boot/css/ui.jqgrid-bootstrap.css">
    <!--引入 jquery -->
    <script src="${pageContext.request.contextPath}/boot/js/jquery-3.4.1.min.js"></script>
    <!--引入 jqgrid-->
    <script src="${pageContext.request.contextPath}/boot/js/jquery.jqGrid.min.js"></script>
    <!--引入 jqgrid 国际化-->
    <script src="${pageContext.request.contextPath}/boot/js/grid.locale-cn.js"></script>
    <!--引入 boot的js-->
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(function () {
            // 创建父级JqGrid表格
            $("#table").jqGrid(
                {
                    url: "${pageContext.request.contextPath}/Dept/findByPage",
                    datatype: "json",
                    height: 500,
                    colNames: ['编号', '部门号', '名字', '人数'],
                    colModel: [
                        {name: 'id', index: 'invdate', width: 55, align: 'center'},
                        {name: 'number', index: 'invdate', editable: true, width: 90, align: 'center'},
                        {name: 'name', index: 'invdate', editable: true, width: 90, align: 'center'},
                        {name: 'count', index: 'invdate', width: 90, align: 'center'},
                    ],
                    rowNum: 5,
                    rowList: [5, 10, 15, 20],
                    pager: '#page',
                    viewrecords: true,
                    multiselect: false,
                    editurl: "${pageContext.request.contextPath}/Dept/control",//开启编辑时执行编辑操作的url路径  添加  修改  删除
                    // 开启多级表格支持
                    subGrid: true,
                    caption: "部门表",
                    autowidth: true,
                    styleUI: "Bootstrap",
                    // 重写创建子表格方法
                    subGridRowExpanded: function (subgrid_id, row_id) {
                        addTable(subgrid_id, row_id);
                    },
                    // 删除表格方法
                    subGridRowColapsed: function (subgrid_id, row_id) {

                    }
                }).navGrid(
                '#page',
                {
                    add: true, edit: true, del: true,
                    edittext: "编辑", addtext: "添加", deltext: "删除"
                },
                {
                    addCaption: "更新部门", closeAfterEdit: true
                },
                {
                    closeAfterAdd: true, addCaption: "添加部门", closeAfterAdd: true,
                },
                {},
            );
        });

        // subgrid_id 下方空间的id  row_id 当前行id数据
        function addTable(subgrid_id, row_id) {
            // 声明子表格|工具栏id
            var subgridTable = subgrid_id + "table";
            var subgridPage = subgrid_id + "page";
            // 根据下方空间id 创建表格及工具栏
            $("#" + subgrid_id).html("<table id='" + subgridTable + "'></table><div style='height: 50px' id='" + subgridPage + "'></div>")
            // 子表格JqGrid声明
            $("#" + subgridTable).jqGrid({
                url: "${pageContext.request.contextPath}/Emp/findByPage?deptid=" + row_id + "",
                datatype: "json",
                colNames: ['编号', '名字', '性别', '年龄', '生日'],
                colModel: [
                    {name: "id", index: "num", width: 80, key: true},
                    {name: "name", editable: true, index: "item", width: 130},
                    {
                        name: "sex", editable: true, align: 'center', formatter: function (data) {
                            if (data == "1") {
                                return "男";
                            } else return "女";
                        }, editable: true, edittype: "select", editoptions: {value: "1:男;2:女"}
                    },
                    {name: "age", index: "item", editable: true, width: 130},
                    {name: "birthday", index: "item", editable: true, width: 130},

                ],
                rowNum: 5,
                rowList: [5, 10, 15, 20],
                pager: "#" + subgridPage,
                editurl: "${pageContext.request.contextPath}/Emp/control?deptid=" + row_id + "",//开启编辑时执行编辑操作的url路径  添加  修改  删除
                height: '100%',
                viewrecords: true,
                styleUI: "Bootstrap",
                autowidth: true
            }).navGrid(
                "#" + subgridPage + "",
                {
                    add: true, edit: false, del: true,
                    addtext: "添加", deltext: "删除"
                },
                {//编辑时
                    closeAfterEdit: true, editCaption: "编辑员工"
                },
                {//添加时
                    closeAfterAdd: true, addCaption: "添加员工"
                },
                {//删除时

                }
            )
        }

    </script>

</head>
<body>


<table id="table"></table>
<div style="height: 50px" id="page"></div>

</body>
</html>
