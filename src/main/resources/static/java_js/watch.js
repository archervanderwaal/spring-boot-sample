/**
 * Created by Administrator on 2016-05-13.
 */
/*
 * 设备管理数据导出
 * */
function ajax_output() {
    $.ajax({
        type: "post",//请求方式
        url: "ajax_outputdata",//发送请求地址
        dataType: "text",
        type: "POST",
        success: function (data) {
            alert(data);
        },
        error: function () {
            alert("导出文件出现错误，请与我联系.");
        }
    });
}