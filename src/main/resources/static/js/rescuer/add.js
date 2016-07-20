/*    增加         */
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
        $(".remarks").val($("#remarks").html());
        $(".jurisdiction").val($("#jurisdiction").html());
        $(".service").val($("#service").html());
        $(".frequency").val($("#frequency").html());

        $(".increase_box input[type='button']").click(function () {
            $("#jurisdiction").html($(".jurisdiction").val());
            $("#service").html($(".service").val());
            $("#frequency").html($(".frequency").val());
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
            $.ajax({
                url: 'create_rescue.html',
                data: {name: $(".name").val()},
                dataType: "json",
                type: "get",
                cache: false,
                success: function (data) {
                    alert(data);

                }
            });
            $(".increase_box").css("display", "none");
        })
    })
});