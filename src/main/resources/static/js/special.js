/**
 * Created by yinhanwen on 2016/1/4.
 */
$(function () {
    //搜索
    $("div.checked").click(function () {
        alert(1);
    });
    //事件委托  点击预览专题图片
    $("table").delegate("td.pic", "click", function () {
        var num = $(this).attr("num");
        var img = $("<img src='../img/dibulogo.png' style='width:400px;position:fixed;top:15%;left:50%;margin-left:-200px;z-index: 6'>");
        var mask = $("<div style='width:100%;height:100%;position:fixed;top:0;left:0;background:rgba(0,0,0,0.7);z-index: 5'></div>");
        $("body").append(mask);
        $("body").append(img);
        mask.click(function () {
            mask.remove();
            img.remove();
            mask = null;
            img = null;
        });
    });
    //事件委托  点击修改专题
    var box = $(".alert_box");
    $("table").delegate("td.reset", "click", function () {
        var _this = $(this).parent();
        box.css("display", "block");
        box.find(".name span").html(_this.children().eq(0).text());
        box.find(".description textarea").html(_this.children().eq(2).text());
        box.delegate("input[type='button']", "click", function () {
            box.css("display", "none");
            _this.children().eq(2).html(box.find(".description textarea").val());
            //  此处先上传图片到数据库
            // $.ajax();
        });
    });
    //删除专栏
    $("table").delegate("td.delete", "click", function () {
        var flag = window.confirm("确认删除？");
        if (flag) {
            $(this).parent().remove();
        }
    });
    //添加专题到下方
    var num = 0;
    $("#add_spe .sub").click(function () {
        num = parseInt($("#manager_spe").find("tr").last().find("td").attr("num")) + 1;
        $("#manager_spe").append($("<tr><td style='width:110px' class='name' num=" + num + ">" + $("#add_spe").find(".name").val() + "</td><td style='width:100px;' class='pic' num=" + num + ">图片预览</td><td style='width:400px;' class='description' num=" + num + ">" + $("#add_spe").find(".description").val() + "</td><td style='width:100px;' class='reset' num=" + num + ">修改</td><td style='width:100px;' class='delete' num=" + num + ">删除</td><td style='width:80px' class='kaocha' num=" + num + "><select name='' id='' ><option value='ok' selected='selected'>通过</option><option value='no'>未通过</option></select></td><td style='width:80px;' class='sub'>确定</td><td style='width:625px;' num=" + num + "></td></tr>"))
    })
});