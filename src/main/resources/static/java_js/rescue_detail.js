/**
 * 修改诉求状态为完成 诉求信息编号
 * @param id .
 */
function update_appeal_status(id) {
    $.ajax({
        method: "post",
        url: "/update_appeal_status",
        data: {
            id: id
        },
        dataType: "json",
        success: function (data) {
            if (data.code == 1) {
                alert(data.message);
                window.location.reload(true);
            } else {
                alert(data.message);
            }
        },
        error: function (error) {
            alert(error);
        }
    });
}
/**
 * 通过用户的手表标识获取用户信息
 * @param imei 手表标识
 */
function getAppealInfo(imei) {
    $.ajax({
        type: "POST",//请求方式
        url: "ajax_searchByImei.html",//发送请求地址
        dataType: "json",
        data: {watchImei: imei},
        success: function (data) {
            $("#addName").val(data.name);
            $("#uId").val(data.id);
            $("#addAge").val(data.age);
            $("#addPhone").val(data.phone);
            if (data.gender = 1) {
                $("#addGender").find("option[value='1']").attr("selected", true);
            }
            if (data.gender = 0) {
                $("#Gender").find("option[value='0']").attr("selected", true);
            }
        },
        error: function () {
            alert("查询失败,请重新确认输入标识号.");
        }
    });
}

/**
 * 新增诉求订单
 */
function saveAppealInfo() {
    var id = $("#uId").val();
    if (id == '') {
        alert("请输入标识号后再新增订单诉求.");
        $("#uId").focus();
        return false;
    }
    var status = $("#status option:selected").val();
    if (status == null) {
        alert("请选择诉求状态后再新增订单诉求.");
        return false;
    }
    var address = $("#address").val();
    if (address == null) {
        alert("请输入地址后再新增订单诉求.");
        return false;
    }
    var urgent = $("#addContent option:selected").val();
    if (urgent == null) {
        alert("请选择紧急状态后再新增订单诉求.");
        return false;
    }
    var content = $("#content").val();
    if (content == null) {
        alert("请输入诉求内容后再新增订单诉求.");
        return false;
    }
    $.ajax({
        type: "POST",//请求方式
        url: "save_post.html",//发送请求地址
        dataType: "text",
        data: {userId: id, status: status, address: address, urgent: urgent, content: content},
        success: function (data) {
            alert(data);
            alert("增加成功！");
            window.location.reload(true);
        },
        error: function () {
            alert("增加失败！");
        }
    });
}
