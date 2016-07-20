/**
 * 增加
 */
$(document).ready(function () {
    $("#save_department").on("submit", save_department);
});

function save_department(event) {
    alert("test");
    event.preventDefault();
    $.ajax({
        url: "/save_department.html",
        type: "POST",
        data: $("#save_department").serializeJSON(),
        //dataType : "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert("data=" + data);
            alert("增加成功！");
            window.location.reload(true);

            /* if (data.code == 1) {
             System.out.print("增加成功！");
             window.location.reload(true);

             } */
        },
        error: function (error) {
            alert("error=" + error);
            alert("增加失败！");
        }
    });
}

/**
 *  修改
 */
$(function () {

    $(".revise").click(function () {
        $(".alert_box").css("display", "block");
        var chked;
        var chklength = $("table tr td :checkbox:checked").length;
        var deptId;
        var deptNameInput;
        if (chklength > 1) {
            $(".alert_box").css("display", "none");
            alert("选中的行数大于1!");
            return;
        } else if (chklength == 1) {
            chked = $("table tr td :checkbox:checked");
            deptId = $(chked).parent().next();
            deptNameInput = $(chked).parent().next().next();
        } else {
            $(".alert_box").css("display", "none");
            alert("请选择一行!");
            return;
        }
        $(".id").val(deptId.html());
        $(".name").val(deptNameInput.html());
        $(".alert_box input[type='button']").click(function () {
            $($("table tr td :checkbox:checked").parent().next().next()).html($(".name").val());
            $.ajax({
                url: 'update_department.html',
                data: {id: $(".id").val(), name: $(".name").val()},
                dataType: "json",
                type: "get",
                cache: false,
                success: function (data) {
                    alert(data);
                }
            });
            $(".alert_box").css("display", "none");
        })
    })

});
function fx() {
    var values = document.getElementsByName("fvs");
    for (var i = 0; i < values.length; i++) {
        /*
         //开始判断??
         // true = false
         // false = true
         if(values[i].checked==true)
         {
         values[i].checked=false;
         }else  //否则
         {
         values[i].checked=true;
         }
         */
        // ! 非
        values[i].checked = !values[i].checked;
    }
}