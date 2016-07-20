$(function () {
    $(".revise").click(function () {
        $(".revise_box").css("display", "block");
        $(".name").val($("#name").html());
        $(".sex").val($("#sex").html());
        $(".age").val($("#age").html());
        $(".num").val($("#num").html());
        $(".phone").val($("#phone").html());
        $(".contacts").val($("#contacts").html());
        $(".contacts_phone").val($("#contact_phone").html());
        $(".medical").val($("#medical").html());
        $(".self_care").val($("#self_care").html());
        $(".medicine").val($("#medicine").html());
        $(".reside").val($("#reside").html());
        $(".site").val($("#site").html());
        $(".history").val($("#history").html());
        $(".state").val($("#state").html());
        $(".data").val($("#data").html());
        $(".whether_rescue").val($("#whether_rescue").html());
        $(".height").val($("#height").html());
        $(".weight").val($("#weight").html());
        $(".blood_pressure").val($("#blood_pressure").html());
        $(".blood_fat").val($("#blood_fat").html());
        $(".vision").val($("#vision").html());
        $(".hearing").val($("#hearing").html());
        $(".like").val($("#like").html());
        $(".eating_habit").val($("#eating_habit").html());
        $(".demands_state").val($("#demands_state").html());
        $(".remarks").val($("#remarks").html());

        $(".revise_box input[type='button']").click(function () {
            $("#name").html($(".name").val());
            $("#sex").html($(".sex").val());
            $("#age").html($(".age").val());
            $("#num").html($(".num").val());
            $("#phone").html($(".phone").val());
            $("#contacts").html($(".contacts").val());
            $("#contact_phone").html($(".contacts_phone").val());
            $("#medical").html($(".medical").val());
            $("#self_care").html($(".self_care").val());
            $("#medicine").html($(".medicine").val());
            $("#reside").html($(".reside").val());
            $("#site").html($(".site").val());
            $("#history").html($(".history").val());
            $("#state").html($(".state").val());
            $("#data").html($(".data").val());
            $("#remarks").html($(".remarks").val());
            $("#whether_rescue").html($(".whether_rescue").val());
            $("#demands_state").html($(".demands_state").val());
            $("#height").html($(".height").val());
            $("#weight").html($(".weight").val());
            $("#blood_pressure").html($(".blood_pressure").val());
            $("#blood_fat").html($(".blood_fat").val());
            $("#vision").html($(".vision").val());
            $("#hearing").html($(".hearing").val());
            $("#like").html($(".like").val());
            $("#eating_habit").html($(".eating_habit").val());
            $(".revise_box").css("display", "none");
        })
    })
});
$(function () {
    $(".increase").click(function () {
        $(".increase_box").css("display", "block");
        $(".name").val($("#name").html());
        $(".sex").val($("#sex").html());
        $(".age").val($("#age").html());
        $(".num").val($("#num").html());
        $(".phone").val($("#phone").html());
        $(".contacts").val($("#contacts").html());
        $(".contacts_phone").val($("#contact_phone").html());
        $(".medical").val($("#medical").html());
        $(".self_care").val($("#self_care").html());
        $(".medicine").val($("#medicine").html());
        $(".reside").val($("#reside").html());
        $(".site").val($("#site").html());
        $(".history").val($("#history").html());
        $(".state").val($("#state").html());
        $(".data").val($("#data").html());
        $(".whether_rescue").val($("#whether_rescue").html());
        $(".height").val($("#height").html());
        $(".weight").val($("#weight").html());
        $(".blood_pressure").val($("#blood_pressure").html());
        $(".blood_fat").val($("#blood_fat").html());
        $(".vision").val($("#vision").html());
        $(".hearing").val($("#hearing").html());
        $(".like").val($("#like").html());
        $(".eating_habit").val($("#eating_habit").html());
        $(".demands_state").val($("#demands_state").html());
        $(".remarks").val($("#remarks").html());

        $(".increase_box input[type='button']").click(function () {
            $("#name").html($(".name").val());
            $("#sex").html($(".sex").val());
            $("#age").html($(".age").val());
            $("#num").html($(".num").val());
            $("#phone").html($(".phone").val());
            $("#contacts").html($(".contacts").val());
            $("#contact_phone").html($(".contacts_phone").val());
            $("#medical").html($(".medical").val());
            $("#self_care").html($(".self_care").val());
            $("#medicine").html($(".medicine").val());
            $("#reside").html($(".reside").val());
            $("#site").html($(".site").val());
            $("#history").html($(".history").val());
            $("#state").html($(".state").val());
            $("#data").html($(".data").val());
            $("#remarks").html($(".remarks").val());
            $("#whether_rescue").html($(".whether_rescue").val());
            $("#demands_state").html($(".demands_state").val());
            $("#height").html($(".height").val());
            $("#weight").html($(".weight").val());
            $("#blood_pressure").html($(".blood_pressure").val());
            $("#blood_fat").html($(".blood_fat").val());
            $("#vision").html($(".vision").val());
            $("#hearing").html($(".hearing").val());
            $("#like").html($(".like").val());
            $("#eating_habit").html($(".eating_habit").val());
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