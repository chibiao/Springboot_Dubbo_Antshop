$(function () {
    $.ajax(
        {
            url: '/category/allCategory',
            type: 'POST',
            success: function (datas) {
                if (datas) {
                    for (var i = 0; i < datas.length; i++) {
                        /*取出每一个菜单 */
                        var data = datas[i];
                        $("#nav_category").append("<li><a href='/product/getProductByCategory?id=" + data.id + "' name='category'>" + data.name + "</a></li>");
                    }
                } else {
                    alert("添加失败");
                }
            },
            error: function (response) {
                alert("error");
            },
            dataType: 'json'
        }
    );
});