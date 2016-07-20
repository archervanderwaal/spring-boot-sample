/**
 * Created by yinhanwen on 2015/12/29.
 */
$(function () {
    //事件委托  认证信息的问题
    $("table").delegate("td.open", "click", function () {
        //var first=change(this);
        $(this).toggleClass("over");
        //var flag=$(this).hasClass("over")||$(this).next().hasClass("over")||$(this).prev().hasClass("over");
        //if (!flag){
        $(this).parent().find("td.mutiple").find("input").prop("checked", false);
        //}
        //var second=change(this);
        //if (first==second){
        //    alert(true);
        //}
    });
    var obj = null;
    //事件委托  根据认证可选权限的问题
    $("table").delegate("td.mutiple", "click", function () {
        obj = $("table").find("td.open");
        //var flag=obj.hasClass("over");
        //if (!flag){
        //    obj=null;
        //    $(this).find("input").prop("checked",false);
        //return alert("该用户未认证权限");
        //}
        //$(this).find("ul").slideToggle();
        //$(this).find("ul").find("li").click(function(event){
        //    event.stopPropagation();
        //    $(this).toggleClass("active");
        //    return;
        //})
    });
    //检测是否修改
    function change(obj) {
        return $(obj).html() || $(obj).parent().html();
    }

    //信币数量调整
    $("table").delegate("td.money", "dblclick", function () {
        var _this = this;
        var number = $(_this).text();
        $(_this).empty().append($("<input type='text' value=" + number + " style='width:98px;text-align: center'>"));
        $(_this).find("input").focus().blur(function () {
            var num = $(this).val();
            $(_this).html(num);
        })
    });
    //事件委托  密码重置功能
    $("table").delegate("td.reset", "click", function () {
        //$.ajax()
        alert("重置成功");
    });
    //事件委托  删除按钮
    $("table").delegate("td.delete", "click", function () {
        var _this = this;
        var flag = window.confirm("确定删除？");
        if (flag) {
            $(_this).parent().remove();
        } else {

        }
    });

    //事件委托  双击平台用户名称显示相关资料
    $("table").delegate("td[data-role='plant_name']", "dblclick", function () {
        var parent = $(this).parent();
        $(".plant_information").find("li").find(".plantName").html($(this).html());
        //$(".plant_information").find("li").find(".plantUrl").html("域名");
        $(".plant_information").find("li").find(".plantPhone").html("手机号码");
        $(".plant_information").find("li").find(".money").html("money");

        $(".plant_information").css("display", "block");
    });

    //点击资料卡中的确定关闭资料
    $(".alert_box").find("input").click(function () {
        $(".alert_box").css("display", "none");
    })
});