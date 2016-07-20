/* 一键设定*/
$(function () {
    $(".a_key_set").click(function () {
        $(".a_key_set_box").css("display", "block");

        $(".a_key_set_box input[type='button']").click(function () {
            $(".a_key_set_box").css("display", "none");
        })
    })
});
/* 历史详情*/
$(function () {
    $("#historical_details").click(function () {
        $(".historical_details_box").css("display", "block");

        $(".historical_details_box input[type='button']").click(function () {
            $(".historical_details_box").css("display", "none");
        })
    });
    $(".xiaoshi").click(function () {
        $(".message_box").hide();
    })
});
/* 统计*/
$(function () {
    $(".statistics").click(function () {
        $(".statistics_box").css("display", "block");

        $(".statistics_box input[type='button']").click(function () {
            $(".statistics_box").css("display", "none");
        })
    })
});
/* 历史详情*/
$(function () {
    $("#historical_details").click(function () {
        $(".historical_details_box").css("display", "block");

        $(".historical_details_box input[type='button']").click(function () {
            $(".historical_details_box").css("display", "none");
        })
    })
});
/* 短信发送*/
$(function () {
    $(".message").click(function () {
        $(".message_box").css("display", "block");

        $(".message_box input[type='button']").click(function () {
            $(".message_box").css("display", "none");
        })
    })
});
/* 饮食建议*/
$(function () {
    $(".proposal").click(function () {
        $(".proposal_box").css("display", "block");

        $(".proposal_box input[type='button']").click(function () {
            $(".proposal_box").css("display", "none");
        })
    })
});
/* 一键提醒*/
$(function () {
    $(".key_reminder").click(function () {
        $(".key_reminder_box").css("display", "block");

        $(".key_reminder_box input[type='button']").click(function () {
            $(".key_reminder_box").css("display", "none");
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