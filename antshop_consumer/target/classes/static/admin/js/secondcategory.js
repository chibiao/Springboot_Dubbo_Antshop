$(function () {
    $("#secondcategory_datagrid").datagrid({
        url: "/secondCategory/secondCategoryList",
        columns: [[
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {
                field: 'parent', title: '父菜单', width: 100, align: 'center', formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            }
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#secondcategory_toolbar'
    });
    /*
     * 初始化新增/编辑对话框
     */
    $("#secondcategory_dialog").dialog({
        width: 300,
        height: 300,
        closed: true,
        buttons: '#secondcategory_dialog_bt'
    });
    /*监听添加按钮的点击*/
    $("#add").click(function () {
        $("#secondcategory_dialog").dialog("open");
        $("#secondcategory_dialog").dialog("setTitle", "添加商品分类");
        $("#secondcategory_dialog").form("clear");
    });
    /*监听编辑按钮的点击*/
    $("#edit").click(function () {
        $("#secondcategory_form").form("clear");
        /*获取当前选中的行*/
        var rowData = $("#secondcategory_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行编辑");
            return;
        }
        /*回显父级菜单*/
        rowData["parent.id"] = rowData["parent"].id;
        $("#secondcategory_dialog").dialog("setTitle", "编辑菜单");
        $("#secondcategory_dialog").dialog("open");
        /*选中的数据回显*/
        $("#secondcategory_form").form("load",rowData);
    });
    /*父菜单 下拉列表*/
    $("#parentCategory").combobox({
        width: 150,
        panelHeight: 'auto',
        editable: false,
        url: "/category/allCategory",
        textField: 'name',
        valueField: 'id',
        onLoadSuccess: function () { /*数据加载完毕之后回调*/
            $("#parentCategory").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });
    /*监听删除按钮的点击*/
    $("#del").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#secondcategory_datagrid").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("提示", "选择一行数据进行删除");
            return;
        }
        /*提醒用户,是否做删除操作*/
        $.messager.confirm("确认", "是否做删除操作", function (res) {
            if (res) {
                /*做删除操作*/
                $.ajax({
                    type:"delete",
                    url:"/secondCategory/deleteSecondCategory/"+rowData.id,
                    dataType:"json",
                    success:function (data) {
                        if(data.success){
                            $.messager.alert("温馨提示", data.msg);
                            /*重新加载数据表格*/
                            $("#secondcategory_datagrid").datagrid("reload");
                            $("#parentCategory").combobox("reload");
                        }else{
                            $.messager.alert("温馨提示", data.msg);
                        }
                    }
                });
                /*$.get("/secondCategory/deleteSecondCategory?id=" + rowData.id, function (data) {
                    /!*get请求返回的是json数据  不需要解析*!/
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /!*重新加载数据表格*!/
                        $("#secondcategory_datagrid").datagrid("reload");
                        $("#parentCategory").combobox("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                });*/
            }
        });
    });
    /*监听保存按钮*/
    $("#save1").click(function () {
        /*判断当前是添加 还是编辑 编辑操作带有id*/
        var id = $("[name='id']").val();
        var url;
        if (id) {
            /*编辑*/
            url = "/secondCategory/updateSecondCategory";
            /*提交表单*/
            $("#secondcategory_form").form("submit", {
                url: url,
                onSubmit:function(param){  /*传递额外参数  已选择的权限*/
                    console.log(param);
                    param._method = "put";
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /*关闭对话框*/
                        $("#secondcategory_dialog").dialog("close");
                        /*重新加载数据*/
                        $("#secondcategory_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        } else {
            /*添加*/
            url = "/secondCategory/addSecondCategory";
            $("#secondcategory_form").form("submit", {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg);
                        /*关闭对话框*/
                        $("#secondcategory_dialog").dialog("close");
                        /*重新加载数据*/
                        $("#secondcategory_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示", data.msg);
                    }
                }
            });
        }

    });
    $("#reload").click(function () {
        /*重新加载*/
        $("#secondcategory_datagrid").datagrid("load")
    });
    $("#cancel").click(function () {
        $("#secondcategory_dialog").dialog("close");
    });
});