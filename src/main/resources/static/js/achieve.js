$(function () {
    $(".achieve").click(function () {
        $(".achieve_box").css("display", "block");
        $(".state").val($("#state").html());
        $(".visit").val($("#visit").html());

        $(".achieve_box input[type='button']").click(function () {
            $("#state").html($(".state").val());
            $("#visit").html($(".visit").val());
            $(".achieve_box").css("display", "none");
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