/*点击弹出入住按钮*/
function ruzhupopBox() {
    var ruzhupopBox = document.getElementById("ruzhupopBox");
    var popLayer = document.getElementById("popLayer");
    ruzhupopBox.style.display = "block";
    popLayer.style.display = "block";
};

/*点击关闭按钮*/
function ruzhucloseBox() {
    var ruzhupopBox = document.getElementById("ruzhupopBox");
    var popLayer = document.getElementById("popLayer");
    ruzhupopBox.style.display = "none";
    popLayer.style.display = "none";
}

$(function() {
var b=2;
//关闭
    $(document).on("click","#gb",function() {
        var tet = "<div id=\"b1\" class=\"bd xyb finger\">开始</div>";
        $("#ks").empty();
        $("#ks").append(tet);
        b=2;
        closeBox();
    })
//下一步
    $(document).on("click","#ks",function() {
        console.log("ks")
        //ajax
            var text="<div></div>"

        if(b!=5) {
            var tet = "<div id=\"b" + b + "\" class=\"bd xyb finger\">下一步</div>";
        }else {
            var tet = "<a href='index.jsp'><div  id=\"b" + b + "\" class=\"bd xyb finger\">完成</div></a>";
        }
        if(b<6) {
            $("#ks").empty();
            $("#ks").append(tet);
            b++;
        }else {
            var tet = "<div id=\"b1\" class=\"bd xyb finger\">开始</div>";
            $("#ks").empty();
            $("#ks").append(tet);
            b=2;
            closeBox();
        }
    })

    // //新建密码
    // $(document).on("click","#gb",function() {
    //     //ajax
    //     closeBox();
    // })


})