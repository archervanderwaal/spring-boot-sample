/*   修改     */
$(function () {
    $(".revise").click(function () {
        $(".alert_box").css("display", "block");
        $(".name").val($("#name").html());
        $(".gender").val($("#gender").html());
        $(".age").val($("#age").html());
        $(".imei").val($("#imei").html());
        $(".phone").val($("#phone").html());
        $(".contacts").val($("#contacts").html());
        $(".contacts_phone").val($("#contact_phone").html());
        $(".medicare").val($("#medicare").html());
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
        $(".deep_sleep").val($("#deep_sleep").html());
        $(".light_sleep").val($("#light_sleep").html());
        $(".sleep_duration").val($("#sleep_duration").html());
        $(".sleep_quality").val($("#sleep_quality").html());
        $(".note_taker").val($("#note_taker").html());
        $(".history").val($("#history").html());
        $(".open_state").val($("#open_state").html());
        $(".remarks").val($("#remarks").html());
        $(".level").val($("#level").html());
        $(".describe").val($("#describe").html());
        $(".assign").val($("#assign").html());
        $(".alert_box input[type='button']").click(function () {
            $("#level").html($(".level").val());
            $("#describe").html($(".describe").val());
            $("#assign").html($(".assign").val());
            $("#deep_sleep").html($(".deep_sleep").val());
            $("#light_sleep").html($(".light_sleep").val());
            $("#sleep_duration").html($(".sleep_duration").val());
            $("#sleep_quality").html($(".sleep_quality").val());
            $("#note_taker").html($(".note_taker").val());
            $("#history").html($(".history").val());
            $("#open_state").html($(".open_state").val());
            $("#name").html($(".name").val());
            $("#gender").html($(".gender").val());
            $("#age").html($(".age").val());
            $("#imei").html($(".imei").val());
            $("#phone").html($(".phone").val());
            $("#contacts").html($(".contacts").val());
            $("#contact_phone").html($(".contacts_phone").val());
            $("#medicare").html($(".medicare").val());
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
            $(".alert_box").css("display", "none");
        })
    })
});
/*      增加               */
$(function () {
    $(".increase").click(function () {
        $(".increase_box").css("display", "block");
        $(".name").val($("#name").html());
        $(".gender").val($("#gender").html());
        $(".age").val($("#age").html());
        $(".imei").val($("#imei").html());
        $(".phone").val($("#phone").html());
        $(".contacts").val($("#contacts").html());
        $(".contacts_phone").val($("#contact_phone").html());
        $(".medicare").val($("#medicare").html());
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
        $(".deep_sleep").val($("#deep_sleep").html());
        $(".light_sleep").val($("#light_sleep").html());
        $(".sleep_duration").val($("#sleep_duration").html());
        $(".sleep_quality").val($("#sleep_quality").html());
        $(".note_taker").val($("#note_taker").html());
        $(".history").val($("#history").html());
        $(".open_state").val($("#open_state").html());
        $(".remarks").val($("#remarks").html());
        $(".level").val($("#level").html());
        $(".describe").val($("#describe").html());
        $(".assign").val($("#assign").html());
        //$(".language_competence").val($("#language_competence").html());
        //$(".address").val($("#address").html());
        $(".increase_box input[type='button']").click(function () {
            $("#level").html($(".level").val());
            $("#describe").html($(".describe").val());
            $("#assign").html($(".assign").val());
            $("#deep_sleep").html($(".deep_sleep").val());
            $("#light_sleep").html($(".light_sleep").val());
            $("#sleep_duration").html($(".sleep_duration").val());
            $("#sleep_quality").html($(".sleep_quality").val());
            $("#note_taker").html($(".note_taker").val());
            $("#history").html($(".history").val());
            $("#open_state").html($(".open_state").val());
            $("#name").html($(".name").val());
            $("#sex").html($(".sex").val());
            $("#age").html($(".age").val());
            $("#imei").html($(".imei").val());
            $("#phone").html($(".phone").val());
            $("#contacts").html($(".contacts").val());
            $("#contact_phone").html($(".contacts_phone").val());
            $("#medicare").html($(".medicare").val());
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

            //$("#language_competence").html($(".language_competence").val());
            //$("#address").html($(".address").val());
        })
    });
    $("div.box table tr td a").click(function () {
        $(".from").toggle();
    });
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