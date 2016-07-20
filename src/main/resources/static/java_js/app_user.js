//获取用户信息
function get_app_user_detail(id) {
    $.ajax({
        url: "/appUser_details.html",
        type: "POST",
        data: {
            id: id
        },
        dataType: "json",
        //contentType: "application/json; charset=UTF-8",
        success: function (data) {
            if (data.code == 1) {
                $("#detail_name").val(data.data.name);
                $("#detail_avatar").attr("src", data.data.avatar);
                $("#detail_age").val(data.data.age);
                $("#detail_registerTime").val(data.data.registerTime);
                $("#detail_gender").val(data.data.gender);
                $("#detail_birthday").val(data.data.birthday);
                $("#detail_phone").val(data.data.phone);
                $("#detail_password").val(data.data.password);
                $("#detail_balance").val(data.data.balance);
                $("#detail_lastLoginTime").val(data.data.lastLoginTime);
                $("#detail_lastLoginIp").val(data.data.lastLoginIp);
                $("#detail_loginCount").val(data.data.loginCount);
                $("#detail_sign").val(data.data.sign);
                $("#detail_remark").val(data.data.remark);
                $("#detail_height").val(data.data.height);
                $("#detail_weight").val(data.data.weight);
                //$("#detail_longitude").val(data.data.longitude);
                //$("#detail_latitude").val(data.data.latitude);
                //$("#detail_reward").val(data.data.reward);
                $("#detail_filialScore").val(data.data.filialScore);

                $(".app_user_detail_box").css("display", "block");
            }else {
                alert(data.message);
            }
        },
        error: function (error) {
            alert("error=" + error);
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
                url: 'appUpdate_user.html',
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