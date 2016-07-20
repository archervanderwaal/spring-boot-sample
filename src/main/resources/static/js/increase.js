$(function () {
    $(".increase").click(function () {
        $(".alert_box2").css("display", "block");
        /*       $(".name").val($("#name").html());
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
         $(".demands_state").val($("#demands_state").html());
         $(".remarks").val($("#remarks").html());*/

        $(".alert_box2 input[type='button']").click(function () {
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
            $(".alert_box2").css("display", "none");
        })
    })
});

//function btn(){
//	var nr = document.getElementsByClassName("appeal_content1").value;
//	var xz = document.getElementsByClassName("appeal_content").value;
//	var xx = xz.options[xz.selectedIndex].value;
//	
//	if(xx == '2'){
//		if (nr == '') {
//			alert(内容不能为空);
//			return false;
//		} 
//	} else{
//		return true;
//	}
//	
//}
