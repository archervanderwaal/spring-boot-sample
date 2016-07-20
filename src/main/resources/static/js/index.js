/**
 * Created by yinhanwen on 2015/12/28.
 */
$(function () {
    //收起和展开logo栏
    $(".Arrow").click(function () {
        $(".logo").slideToggle();
    });
    //收起和展开菜单list
    var flag = true;
    $(".slide").click(function () {
        if (flag) {
            $(".listview").animate({width: 20, height: 30}, 100);
            $(".listview").find(".title").find(".list-nav").hide();
            $(".listshow").animate({width: '96%'}, 100);
            $(".listview").find(".title").find(".slide").css({
                "margin-right": 0, "background": "url(images/you.png) no-repeat 4px 0"
            });
        } else {
            $(".listview").animate({width: '13%', height: 1300}, 100);
            $(".listview").find(".title").find(".list-nav").show();
            $(".listshow").animate({width: '84%'}, 100);
            $(".listview").find(".title").find(".slide").css({
                "margin-right": 10 + "px", "background": "url(images/zuo.png) no-repeat 4px 0"
            });
        }
        flag = !flag;
    });
    //收起展开子菜单
    $(".box").click(function () {
        $(this).toggleClass("open");
        $(this).next().slideToggle();
    });
    //获取iframe框架标签
    var iframe = $("#iframe");
    //初始为隐藏
    iframe.css("display", "none");
    //box为右侧显示标签卡容器
    var box = $(".listshow .top");
    //左侧按钮点击
    $(".listBtn").click(function () {
        //获取自定义属性
        var src = $(this).attr("data-src");
        //获取文本内容
        var text = $(this).text();
        //获取自定义id号码
        var id = $(this).attr("data-id");
        //console.log(id);
        //查找右侧标签卡中数量
        //var lis=box.find("li");
        //for (var i=0;i<lis.length;i++){
        //    //判断右侧标签卡是否已存在具有该id的卡，如果有则显示相应内容，并且当前卡选中
        //    if ($(lis[i]).attr("data-id")==id){
        //        //console.log("you");
        //        iframe.attr("src",src);
        //        $(".ListBtn").removeClass("active");
        //        $(".ListBtn[data-id="+id+"]").addClass("active");
        //        return;
        //    }
        //}

        //选中变色
        $(".listBtn").removeClass("checked");
        $(this).addClass("checked");
        //box添加对应的卡并且带有active类
        box.empty();
        box.append($("<li class='listBtn ListBtn active' data-src=" + src + " data-id=" + id + "><span>" + text + "</span><span class='delete'>X</span></li>"));
        //iframe显示
        iframe.css("display", "block");
        //初始提示框消失
        $("#tishi").css("display", "none");
        //iframe显示对应内容
        iframe.attr("src", src);
    });
    //事件委托  右侧box中卡和对应内容的切换
    //box.delegate(".ListBtn","click",function(){
    //    $(this).addClass("active").siblings().removeClass("active");
    //    iframe.attr("src",$(this).attr("data-src"));
    //})
    //事件委托  右侧box中卡的移除与内容的切换
    box.delegate(".delete", "click", function () {
        box.empty();
        iframe.removeAttr("src");
        iframe.css("display", "none");
        $("#tishi").css("display", "block");
        check();
        //if (!delPar.hasClass("active")){
        //    delPar.remove();
        //    delPar=null;
        //    check();
        //}else {
        //    delPar.remove();
        //    delPar = null;
        //    box.find("li").last().addClass("active");
        //    check();
        //}
    });
    //检测是否右侧box中是否还有卡，若无则回复初始
    function check() {
        var lis = box.find("li");
        if (lis.length === 0) {
            $(".listBtn").removeClass("checked");
            iframe.removeAttr("src");
            iframe.css("display", "none");
            $("#tishi").css("display", "block");
        }
    }
});