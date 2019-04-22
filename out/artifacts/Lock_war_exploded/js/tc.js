/*点击弹出按钮*/
function glypopBox() {
    console.log("弹出管理管理员")
    var popBox = document.getElementById("glygl");
    var popLayer1 = document.getElementById("popLayer1");
    popLayer1.style.display = "block";
    popBox.style.display = "block";


};

/*点击关闭按钮*/
function glycloseBox() {
    console.log("关闭管理管理员")
    var popBox = document.getElementById("glygl");
    var popLayer1 = document.getElementById("popLayer1");
    popBox.style.display = "none";
    popLayer1.style.display = "none";
}


/*点击弹出增删房间*/
function zsfjpopBox() {
    console.log("弹出增删房间页面")
    var zsfjtc = document.getElementById("zsfjtc");
    var popLayer2 = document.getElementById("popLayer2");
    popLayer2.style.display = "block";
    zsfjtc.style.display = "block";


};

/*点击关闭增删房间*/
function zsfjcloseBox() {
    // zjfjk3
    console.log("关闭增删房间页面")
    var zsfjtc = document.getElementById("zsfjtc");
    var popLayer2 = document.getElementById("popLayer2");
    zsfjtc.style.display = "none";
    popLayer2.style.display = "none";
}

/*点击弹出修改租金*/
function xgzjpopBox() {
    console.log("弹出")
    var popBox = document.getElementById("xgzjtc");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";

};

/*点击关闭修改租金*/
function xgzjcloseBox() {
    zjzy()
    var popBox = document.getElementById("xgzjtc");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}
// 修改租金
function zjzy() {
    var zj=$("#zjinput").val();
    console.log(zj)
    $("#zzj").text(zj);

}

/*点击弹出新建管理员按钮*/
function xjglypopBox() {
    console.log("弹出")
    glycloseBox();
    var xjglypopBox = document.getElementById("xjglypopBox");
    var popLayer2 = document.getElementById("popLayer2");
    xjglypopBox.style.display = "block";
    popLayer2.style.display = "block";

};

/*点击关闭按钮*/
function xjglycloseBox() {
    $('#tpzs').attr("src","../../img/tx/tx0.jpg");
    var xjglypopBox = document.getElementById("xjglypopBox");
    var popLayer2 = document.getElementById("popLayer2");
    xjglypopBox.style.display = "none";
    popLayer2.style.display = "none";
    console.log("关闭新建管理员弹窗")
    glypopBox();
}


/*点击弹出确认删除界面*/
function querengpopBox( userId) {
    var txt="";
    txt=txt+userId;
    $("#scduserId").empty();
    $("#scduserId").append(txt);
    console.log("弹出确认删除界面")
    var querengBox = document.getElementById("querengBox");
    var popLayer2 = document.getElementById("popLayer2");
    popLayer2.style.display = "block";
    querengBox.style.display = "block";


};

/*点击关闭删除界面*/
function querengcloseBox() {
    console.log("关闭删除界面")
    var querengBox = document.getElementById("querengBox");
    var popLayer2 = document.getElementById("popLayer2");
    querengBox.style.display = "none";
    popLayer2.style.display = "none";
}


/*点击弹出确认退房界面*/
function tfpopBox( roomId) {
    var txt="";
    txt=txt+roomId;
    $("#tfRoomId").empty();
    $("#tfRoomId").append(txt);
    console.log("弹出确认退房界面")
    var tfcloseBox = document.getElementById("tfcloseBox");
    var popLayer2 = document.getElementById("popLayer2");
    popLayer2.style.display = "block";
    tfcloseBox.style.display = "block";


};

/*点击关闭确认退房界面*/
function tfcloseBox() {
    console.log("关闭确认退房界面")
    var tfcloseBox = document.getElementById("tfcloseBox");
    var popLayer2 = document.getElementById("popLayer2");
    tfcloseBox.style.display = "none";
    popLayer2.style.display = "none";
}

$(function() {


    // //新建门锁密码
    // $(document).on("click",".gb",function() {
    //
    //     //ajax
    //
    //     closeBox();
    // })

})

