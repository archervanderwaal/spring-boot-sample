/**
 * Created by Vega on 2016/5/24 0024.
 */
$(document).ready(function () {
    $("#createVolunteer").on("submit", createVolunteer);
});

function createVolunteer(event) {
    alert("test");
    event.preventDefault();
    $.ajax({
        url: "/createVolunteer.html",
        type: "POST",
        data: $("#createVolunteer").serializeJSON(),
        //dataType : "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert("data=" + data);
            if (data.code == 1) {
                window.location.reload(true);

            } else {
                alert("增加失败！");
            }
        },
        error: function (error) {
            alert("error=" + error);
        }
    });
}
