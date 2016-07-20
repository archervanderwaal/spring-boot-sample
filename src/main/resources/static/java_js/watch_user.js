$(document).ready(function () {
    $("#create-user-form").on("submit", create_user);
});

/**
 * 新增用户信息
 *
 * @param event
 */
function create_user(event) {
    alert("test");
    event.preventDefault();
    $.ajax({
        url: "/create_user.html",
        type: "POST",
        data: $("#create-user-form").serializeJSON(),
        //dataType : "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert("data=" + data);
            if (data.code == 1) {
                window.location.reload(true);
                alert("增加成功！");
            } else {
                alert("增加失败！");
            }
        },
        error: function (error) {
            alert("error=" + error);
        }
    });
}








//$(document).ready(function () {
//    $("#update-user-form").on("submit", update_user);
//});
//
///**
// * 修改用户信息
// *
// * @param event
// */
//function update_user(id) {
//    $.ajax({
//        method: "post",
//        url: "/update_user.html",
//        data: {
//            id: id
//        },
//        dataType: "json",
//        success: function (data) {
//            if (data.code == 1) {
//                alert(data.message);
//                window.location.reload(true);
//            } else {
//                alert(data.message);
//            }
//        },
//        error: function (error) {
//            alert(error);
//        }
//    });
//}