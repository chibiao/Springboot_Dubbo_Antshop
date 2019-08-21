var total = parseInt($("#summoney").html());//定义总价
layui.use('layer', function () {
    var layer = layui.layer;
});

//减法操作
function sub(a) {
    var t = $(a).next().html();
    t = parseInt(t);
    if (t == 1) {
        $(a).next().html(1);
    } else {
        t = t - 1;
        $(a).next().html(t);
        //找到选中框
        var c = $(a).parent().parent().children()[0].childNodes[0].nextSibling;
        if(c.checked==true) {//如果商品被选中
            var money = parseInt($("#summoney").html());
            //alert(parseInt($(a).parent().prev().children()[0].innerHTML));
            // alert(money>parseInt($(a).parent().prev().children()[0].innerHTML));
            if (money > parseInt($(a).parent().prev().children()[0].innerHTML)) {//防止出现负数
                money = money - parseInt($(a).parent().prev().children()[0].innerHTML);
                $("#summoney").html(money);
            }
        }
    }

}//减法结束
//加一个商品数量，如果商品处于选中状态，那么，总价格会变，否则，不变
//加法结束
function add(b) {
    var t = $(b).prev().html();
    t = parseInt(t);
    t = t + 1;
    var count = $(b).next().val();
    //判断商品是否选中，先找到商品选中的框
    var c = $(b).parent().parent().children()[0].childNodes[0].nextSibling;
    if (t > count) {
        alert("库存就这么多啦,先买这些吧");
        $(b).disabled = true;
    } else {
        $(b).prev().html(t);
        if(c.checked==true) {//如果商品被选中
            var money = parseInt($("#summoney").html());
            money = money + parseInt($(b).parent().prev().children()[0].innerHTML);
            $("#summoney").html(money);
        }
    }
}


//全选实现
function selectAll(a) {
    var length = $(".xixi").length;
    for (var x = 0; x < length; x++) {
        $(".xixi")[x].checked = a;
    }
}

function continueshop() {
    window.location = "/index"
}

/*-----------计算选中商品的总价格-------------  */
function calMoney(e) {
    /* 这是购物车中所有的商品长度 */
    var a = parseFloat($(e).parent().siblings()[3].childNodes[0].nextSibling.innerText);//获取选中商品的单价
    var b = parseInt($(e).parent().siblings()[4].childNodes[2].nextSibling.innerText);//获取商品的数量
    var money = a * b;
    if (e.checked) {// 如果本件商品被选中
        total = parseFloat($("#summoney").html()) + money;
    } else {
        total = parseFloat($("#summoney").html()) - money;
    }
    $("#summoney").html(total);
}


/* ----------删除购物车中的东西--------------- */
function deletegoods(a) {
    index = layer.confirm('确定删除该商品吗？', function (index) {
        var id = $(a).parent().prevAll()[4].value;
        layer.close(index);
        $.ajax({
            type: "post",
            url: "/cart/deleteCart",
            data: {id: id},
            dataType: "json",
            success: function (data) {
                layer.msg(data.msg, {
                    time: 2000
                });
                setTimeout(function () {
                    window.location.reload();
                }, 1000);
            }
        })
    })
}


/* 提交订单 */
function Buy() {
    var ids = new Array();
    var counts = new Array();
    var shopListArr=[];
    var shop={};
    var length = $(".xixi").length;
    var price = $(".price");//获取价格的数组
    var count = $(".count");//获取个数的数组
    var gidss = $(".id");//获取商品编号的数组
    var total = parseFloat($("#summoney").html());//定义总价
    console.log("商品总价格: " + $("#summoney").html());
    if (total == 0) {
        alert("您还没选则任何商品");
    } else {
        for (var x = 0; x < length; x++) {
            if ($(".xixi")[x].checked == true) {// 如果有商品被选中
                shop={
                    id:gidss[x].value,
                    count:parseInt(count[x].innerHTML)
                };
                shopListArr.push(shop);
                /*ids.push(gidss[x].value);
                counts.push(parseInt(count[x].innerHTML));*/
            }
        }
        var data={};
        data["carts"]=shopListArr;
        data["total"]=total;
        $.ajax({
            type: "post",
            url: "/order/addOrders",
            traditional: true,
            data: JSON.stringify(data),
            contentType : "application/json;charsetset=UTF-8",//必须
            dataType: "json",
            success: function (data) {
                if (data != null) {
                    window.location = "/order/orderIndex";
                } else {
                    alert("网络错误，请重试")
                }
            }
        })
    }

}

//	清空购物车	
function deleteAll() {
    index = layer.confirm('确定清空购物车吗？', function (index) {
        layer.close(index);
        $.ajax({
            type: "post",
            url: "/Shopcar/deleteShopCar",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data.isSuccess) {
                    layer.msg(data.msg, {
                        time: 2000
                    });
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                } else {
                    layer.msg(data.msg, {
                        time: 2000
                    });
                    setTimeout(function () {
                        window.location.reload();
                    }, 1000);
                }
            }
        })

    })
}




