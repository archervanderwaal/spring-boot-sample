/*  修改  */
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