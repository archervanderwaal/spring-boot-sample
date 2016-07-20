/**
 * Created by yinhanwen on 2015/12/28.
 */
$(function () {
    //获取所有子节点
    function getChilds(parent, type) {
        var type = type == undefined ? true : type;
        var sons = parent.childNodes;
        var arr = [];
        for (var i = 0; i < sons.length; i++) {
            if (type == true) {
                if (!(sons[i].nodeType == 3 || sons[i].nodeType == 8)) {
                    arr.push(sons[i]);
                }
            } else {
                if (!((sons[i].nodeType == 3 && trim(sons[i].nodeValue) == "") || sons[i].nodeType == 8)) {
                    arr.push(sons[i]);
                }
            }
        }
        return arr;
    }

    //获取最后一个子节点
    function getLast(parent, type) {
        var childs = getChilds(parent, type);
        return childs[childs.length - 1];
    }

    //操作提示框的显示
    function display(obj, val) {
        obj.style.display = "block";
        obj.innerHTML = val;
        var t = setTimeout(function () {
            obj.style.display = "none";
            clearTimeout(t);
        }, 1500);
    }

    //修改表格中的内容
    var btn = $(".add")[0];
    var table = $("table")[0];
    var tishi = $(".tishi")[0];

    //事件委托  删除按钮
    //table.onclick=function(e){
    //    var ev=e||window.event;
    //    var obj=ev.target||ev.srcElement;
    //    if (obj.nodeName=="TD"&&obj.innerHTML=="X"){
    //        var flag=window.confirm("确认删除？");
    //        if (!flag){
    //            return;
    //        }
    //        display(tishi,"操作成功");
    //        $(obj).parent().remove();
    //        //ajax({
    //        //    url:"delete.php",
    //        //    data:{num:parseInt(obj.getAttribute("num"))},
    //        //    success:function(e){
    //        //        if (e=="删除成功"){
    //        //            display(tishi,e);
    //        //            table.removeChild(obj.parentNode);
    //        //        }
    //        //    }
    //        //})
    //    }
    //}

    //插入一个空行
    //btn.onclick=function(){
    //    var num;
    //    var trs=$(table).find("tr");
    //    if (trs.length==1){
    //        num=1;
    //    }else {
    //        num=  parseInt($(table).find("tr").last().find("td").eq(0).attr("num"))+1;
    //    }
    //    //ajax({
    //        //url:"insert.php",
    //        //data:{num:num},
    //        //success:function(e){
    //            //if(e=="插入成功"){
    //                display(tishi,"操作成功");
    //                $(table).append($("<tr><td attr='queue' num="+num+" class='none' style='width:50px'>"+num+"</td><td attr='name' num="+num+" style='width:180px'>&nbsp;</td><td attr='user' num="+num+" style='width:180px'>&nbsp;</td><td attr='lable' num="+num+" style='width:180px'>&nbsp;</td><td attr='password' num="+num+" style='width:180px'>&nbsp;</td><td class='none'></td></tr>"));
    //}

    //}
    // })
    //}

    //事件委托  双击修改内容
    table.ondblclick = function (e) {
        var ev = e || window.event;
        var obj = ev.target || ev.srcElement;
        if (obj.nodeName == "TD" && (!$(obj).hasClass("none"))) {
            var attr = obj.getAttribute("attr");
            var num = obj.getAttribute("num");
            var oldv = obj.innerHTML;
            if (oldv == "&nbsp;") {
                oldv = "";
            }
            obj.innerHTML = "";
            console.log(oldv);
            var input = $("<input type='text' size='10' value=" + oldv + ">");
            $(obj).append(input);
            input.focus();
            document.onkeydown = input[0].onblur = function (e) {
                var ev = e || window.event;
                if (ev.type == "blur" || (ev.type == "keydown" && ev.keyCode == 13)) {
                    var val = $(input).val();
                    //console.log(val);
                    //ajax({
                    //url:"update.php",
                    //data:{attr:attr,num:parseInt(num),val:val},
                    //success:function(e){
                    //if (e=="更新成功"){
                    obj.innerHTML = val;
                    // }
                    //}
                    //});
                }
            }
        }
    }
});