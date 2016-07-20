function show_history() {
    var id = $("#userId").val();
    $.ajax({
        url: "/play_history",
        type: "POST",
        // dataType : "json",
        data: {
            userId: id
        },
        success: function (data) {
            alert(data);
            var $play_history = $("#play_history");
            $play_history.empty();
            $play_history.append(
                "<tr>" +
                "<th>时间</th>" +
                "<th>距离</th>" +
                "<th>消耗</th>" +
                "</tr>");
            for (var i = 1; i <= data.list; i++) {
                var $playInfo = data.list[i];
                alert($playInfo);
                $play_history.append(
                    "<tr>" +
                    "<td>data.list[i].date</td>" +
                    "<td>$playInfo.distance</td>" +
                    "<td>$playInfo.calories</td>" +
                    "</tr>");
            }
            $play_history.append(
                "<tr>" +
                "<td colspan='3'>" +
                "<input type='button' class='btn' style='width:100px;height:30px;text-align: center;display:block;margin:0 auto;border-radius:10px;background:#e3783b;' value='确定'>" +
                "</td>" +
                "</tr>"
            );
        },
        error: function (error) {
            alert(error);
        }
    });
}