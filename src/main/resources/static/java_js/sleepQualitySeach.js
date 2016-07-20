/**
 * Created by Administrator on 2016-05-08.
 */
function SleepSeach() {
    var name = $("#seachName").val();
    var imei = $("#imei").val();
    alert(name);
    alert(imei);
    $.ajax({
        type: "post",//请求方式
        url: "ajax_seach_sleep.html",//发送请求地址
        dataType: "html",
        type: "POST",
        data: {
            name: name,
            imei: imei
        },
        success: function (data) {
            alert("Seach Result OK");
            alert(data);
        },
        error: function () {
            alert("获取数据失败,请与我联系.");
        }
    });
}