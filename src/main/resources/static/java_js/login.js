$(document).ready(function () {
    $("input").focus(function () {
        $("#result-message").text("");
    });

    $("input").blur(function () {
        if ($(this).val() == "" || $(this).val() == null) {
            $(this).focus();
            $("#result-message").text("请输入用户名及密码！");
        } else
            $("#result-message").text("");
    });

    $("#admin-login-form").on("submit", adminLogin);
});

function adminLogin(event) {
    event.preventDefault();
    $.ajax({
        url: "/admin_login.html",
        type: "POST",
        data: $("#admin-login-form").serializeJSON(),
        contentType: "application/json; charset=UTF-8",
        success: function () {
            window.location.replace("index.html");
        },
        error: function () {
            $("#result-message").text("用户名或密码错误！");
        }
    });
}