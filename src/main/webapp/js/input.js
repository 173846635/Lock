
$(function() {
    console.log("000000000")
    /*JQuery 限制文本框只能输入数字*/
    $(document).on("keydown",".shuzi",function() {
        if(event.keyCode==13)event.keyCode=9

    })
    $(document).on("keypress",".shuzi",function() {
        if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false

    })


})