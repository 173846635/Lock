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


//指纹录入
function zwlr(process,userId) {
    console.log(process);
    jsona={};
    jsona["userId"]=userId;
    jsona["lockId"]=$('#msLockId').text();
    jsona["process"]=process;

    $.ajax({
        url: '/lock/fingerPrint',
        type: 'POST',
        data:jsona,
        dataType: 'json',
        timeout:40000,
        async: true,
        success:function(data){
            console.log(data);
            switch (data["result"]) {
                case 0:
                    console.log("请按下手指");
                    $('#ts').text("请按下手指");
                    $('#ts').attr("name",0);
                    break;
                case 1:
                    console.log("第一次指纹录入完毕，放开手指");
                    $('#ts').text("第一次指纹录入完毕，放开手指");
                    $('#ts').attr("name",1);
                    break;
                case 2:
                    console.log("请再次放下手指");
                    $('#ts').text("请再次放下手指");
                    $('#ts').attr("name",2);
                    break;
                case 3:
                    console.log("第二次指纹录入完毕，请放开手指");
                    $('#ts').text("第二次指纹录入完毕，请放开手指");
                    $('#ts').attr("name",3);
                    break;
                case 4:
                    console.log("创建指纹文件成功");
                    $('#ts').text("创建指纹文件成功");
                    $('#ts').attr("name",4);
                    $("#qx").text("完成")
                    $("#qx").attr("id","gb");
                    break;
                case -1:
                    console.log("第一次指纹录入失败");
                    $('#ts').text("第一次指纹录入失败");
                    $('#ts').attr("name",-1);
                    break;
                case -3:
                    console.log("第二次指纹录入失败");
                    $('#ts').text("第二次指纹录入失败");
                    $('#ts').attr("name",-3);
                    break;
                case -4:
                    console.log("创建指纹文件失败");
                    $('#ts').text("创建指纹文件失败");
                    $('#ts').attr("name",-4);
                    break;

            }
            process=data["result"]+1;
            if(process<5&&process>=0)
            {
                console.log('process：'+process);
                zwlr(process,userId);
            }



            //console.log(dq);

        },
        function(){
            console.log("错误");
        }
    });
}

/**
 * 入住
 */
function rz(){
    var kaishi=$('#kaishi').val();
    var jieshu=$('#jieshu').val();
    var name=$('#name0').val();
    var time=$('#time0').val();
    var sfz=$('#sfz0').val();
    var phone=$('#phone0').val();
    var roomId=$('#roomId').text();

    var sex1=$("input[name='sex']:checked").val();
    var sex="";
    if(sex1==1)
    {
        sex="m";
    }else if(sex1==0)
    {
        sex="w";
    }


    var json = {
        "roomID": roomId,
        "name":name,
        "sex": sex,
        "idCard": sfz,
        "phone": phone,
        "stayTime": kaishi,
        "retreatTime": jieshu,
        // "introduction": introduction,
};
    var map=JSON.stringify(json);
    var t={};
    t["json"]= map;

    $.ajax({
        url: '/lock/check_in',
        type: 'GET',
        data:t,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);

            if(data["result"]==0)
            {
                var userId=data["userID"]
                $('#lockId').text(userId);
                ruzhupopBox();
            }else {
                alert("错误："+data["message"]+"\n错误代码:"+data["result"])
            }

            //console.log(dq);

        },
        function(){
            console.log("错误");
        }
    });

}

//读取入住时间（当前）
function dateX(){
    console.log("dateX")
    var myDate = new Date;
    var year = myDate.getFullYear(); //获取当前年
    var mon = myDate.getMonth() + 1; //获取当前月
    mon=""+mon;
    console.log(mon.length)
    if(mon.length==1)
    {
        mon="0"+mon;
    }


    var date = myDate.getDate(); //获取当前日
    date=""+date;
    console.log(date.length)
    if(date.length==1)
    {
        date="0"+date;
    }
    // var h = myDate.getHours();//获取当前小时数(0-23)
    // var m = myDate.getMinutes();//获取当前分钟数(0-59)
    // var s = myDate.getSeconds();//获取当前秒
    var shj=year + "-" +mon + "-" + date;
    console.log(shj)
    $("#kaishi").val(shj);
}



$(function() {
    account();//查询是否以登录；
    dateX();
var b=2;
//结束
    $(document).on("click","#gb",function() {

        $(this).text("开始")
        $(this).attr("id","ks");
        window.location.replace("/lock/information?roomId="+$('#roomId').text());
    })
//开始
    $(document).on("click","#ks",function() {
        console.log("ks")
        $(this).text("取消")
        $(this).attr("id","qx");
        var userId=$('#lockId').text();
        zwlr(0,userId)
    })
    //取消
    $(document).on("click","#qx",function() {
        console.log("qx")
        $(this).text("开始")
        $(this).attr("id","ks");
        ruzhucloseBox();
    })

    // //新建密码
    // $(document).on("click","#gb",function() {
    //     //ajax
    //     closeBox();
    // })



})