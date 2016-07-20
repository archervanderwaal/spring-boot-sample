$(function () {
    $(".equipment").click(function () {
        $(".alert_box5").css("display", "block");

        $(".alert_box5 input[type='button']").click(function () {
            $(".alert_box5").css("display", "none");
        })
    });
    $(".alert_box5 .li03 .btn").click(function () {
        $(".alert_box5").hide();
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