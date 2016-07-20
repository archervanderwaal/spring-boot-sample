/**
 * Created by yinhanwen on 2015/12/29.
 */
$(function () {
    //事件委托  认证权限
    $("table").delegate("td.open", "click", function () {
        $(this).toggleClass("over");
    });

    //事件委托  修改咨询回复内容或者新闻内容
    $("table").delegate("td.reset", "click", function () {
        var tet = $(this).parent().find("td.tst");
        $(".reset_box").css("display", "block");
        $(".reset_box").find(".sub").click(function () {
            $(".reset_box").css("display", "none");
            tet.find("span").html($(".reset_box").find("textarea").val());
        });
        $(".reset_box").find(".cancel").click(function () {
            $(".reset_box").css("display", "none");
        })
    });

    //事件委托  双击也可以咨询回复内容或者新闻内容
    $("table").delegate("span", "dblclick", function () {
        var tet = $(this).parent().parent().find("td.tst");
        $(".reset_box").css("display", "block");
        $(".reset_box").find(".sub").click(function () {
            $(".reset_box").css("display", "none");
            tet.find("span").html($(".reset_box").find("textarea").val());
        });
        $(".reset_box").find(".cancel").click(function () {
            $(".reset_box").css("display", "none");
        })
    });

    //事件委托   删除按钮
    $("table").delegate("td", "click", function () {
        if ($(this).html() == "X") {
            var flag = window.confirm("确认删除？");
            if (flag) {
                $(this).parent().remove();
            }
        }
    });
});