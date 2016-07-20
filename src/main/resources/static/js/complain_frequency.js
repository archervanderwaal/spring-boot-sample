/*    投诉次数         */
$(function () {
    $(".complain_frequency").click(function () {
        $(".alert_box4").css("display", "block");
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
        $(".remarks").val($("#remarks").html());

        $(".alert_box4 input[type='button']").click(function () {
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
            $(".alert_box4").css("display", "none");
        })
    })
});