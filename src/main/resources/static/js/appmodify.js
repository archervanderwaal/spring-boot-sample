/**
 * Created by Administrator on 2016-05-27.
 */
/*   修改     */
$(function () {
    $(".revise").click(function () {
        $(".alert_box").css("display", "block");
        $(".name").val($("#name").html());
        $(".id").val($("#id").html());
        $(".registerTime").val($("#registerTime"));
        $(".gender").val($("#gender").html());
        $(".birthday").val($("#birthday").html());
        $(".phone").val($("#phone").html());
        $(".password").val($("#password").html());
        $(".avatar").val($("#avatar").html());
        $(".balance").val($("#balance").html());
        $(".lastLoginTime").val($("#lastLoginTime").html());
        $(".lastLoginIp").val($("#lastLoginIp").html());
        $(".loginCount").val($("#loginCount").html());
        $(".sign").val($("#sign").html());
        $(".remark").val($("#remark").html());
        $(".height").val($("#height").html());
        $(".weight").val($("#weight").html());
        $(".longitude").val($("#longitude").html());
        $(".latitude").val($("#latitude").html());
        $(".reward").val($("#reward").html());
        $(".age").val($("#age").html());
        $(".filialScore").val($("#filialScore").html());

        $(".alert_box input[type='button']").click(function () {
            $(".name").val($("#name").html());
            $(".id").val($("#id").html());
            $(".registerTime").val($("#registerTime"));
            $(".gender").val($("#gender").html());
            $(".birthday").val($("#birthday").html());
            $(".phone").val($("#phone").html());
            $(".password").val($("#password").html());
            $(".avatar").val($("#avatar").html());
            $(".balance").val($("#balance").html());
            $(".lastLoginTime").val($("#lastLoginTime").html());
            $(".lastLoginIp").val($("#lastLoginIp").html());
            $(".loginCount").val($("#loginCount").html());
            $(".sign").val($("#sign").html());
            $(".remark").val($("#remark").html());
            $(".height").val($("#height").html());
            $(".weight").val($("#weight").html());
            $(".longitude").val($("#longitude").html());
            $(".latitude").val($("#latitude").html());
            $(".reward").val($("#reward").html());
            $(".age").val($("#age").html());
            $(".filialScore").val($("#filialScore").html());
            $(".alert_box").css("display", "none");
        })
    })
});






/*      增加               */
$(function () {
    $(".increase").click(function () {
        $(".increase_box").css("display", "block");
        $(".name").val($("#name").html());
        $(".id").val($("#id").html());
        $(".registerTime").val($("#registerTime"));
        $(".gender").val($("#gender").html());
        $(".birthday").val($("#birthday").html());
        $(".phone").val($("#phone").html());
        $(".password").val($("#password").html());
        $(".avatar").val($("#avatar").html());
        $(".balance").val($("#balance").html());
        $(".lastLoginTime").val($("#lastLoginTime").html());
        $(".lastLoginIp").val($("#lastLoginIp").html());
        $(".loginCount").val($("#loginCount").html());
        $(".sign").val($("#sign").html());
        $(".remark").val($("#remark").html());
        $(".height").val($("#height").html());
        $(".weight").val($("#weight").html());
        $(".longitude").val($("#longitude").html());
        $(".latitude").val($("#latitude").html());
        $(".reward").val($("#reward").html());
        $(".age").val($("#age").html());
        $(".filialScore").val($("#filialScore").html());

        $(".increase_box input[type='button']").click(function () {
            $(".id").val($("#id").html());
            $(".registerTime").val($("#registerTime"));
            $(".name").val($("#name").html());
            $(".gender").val($("#gender").html());
            $(".birthday").val($("#birthday").html());
            $(".phone").val($("#phone").html());
            $(".password").val($("#password").html());
            $(".avatar").val($("#avatar").html());
            $(".balance").val($("#balance").html());
            $(".lastLoginTime").val($("#lastLoginTime").html());
            $(".lastLoginIp").val($("#lastLoginIp").html());
            $(".loginCount").val($("#loginCount").html());
            $(".sign").val($("#sign").html());
            $(".remark").val($("#remark").html());
            $(".height").val($("#height").html());
            $(".weight").val($("#weight").html());
            $(".longitude").val($("#longitude").html());
            $(".latitude").val($("#latitude").html());
            $(".reward").val($("#reward").html());
            $(".age").val($("#age").html());
            $(".filialScore").val($("#filialScore").html());
            $(".increase_box").css("display", "none");

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