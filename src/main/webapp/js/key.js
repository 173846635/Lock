
// /*点击弹出按钮*/
// function popBox() {
//     var popBox = document.getElementById("popBox");
//     var popLayer = document.getElementById("popLayer");
//     popBox.style.display = "block";
//     popLayer.style.display = "block";
//     $("#timeX").hide();
//     $("#numberX").hide();
// };
//
// /*点击关闭按钮*/
// function closeBox() {
//     var popBox = document.getElementById("popBox");
//     var popLayer = document.getElementById("popLayer");
//     popBox.style.display = "none";
//     popLayer.style.display = "none";
// }

function radio1(a){
    if(a==1)
    {
        $("#numberX").hide();
        $("#timeX").show();
    }else if(a==2)
    {
        $("#timeX").hide();
        $("#numberX").show();
    }
}

/**
 * 获得lock信息
 */
function lockMessage(){
    var jsona={};
    jsona["lockId"]=$("#rlockId0").text();
    $.ajax({
        url: '/lock/getLockMessage',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async:false,
        success:function(data){
            console.log(data)
            // $("#zbnr").empty();
            // $("#zbnr").append(text);
            var lockId =data["lId"];//lockId
            var softVer =data["lSoftVer"];//软件版本
            var hardVer =data["lHardVer"];//硬件版本
            var statusStr =data["lStatusStr"];//门锁状态
            var introduction =data["l_introduction"];//门锁介绍，可能会用来做设备号
            $("#lockId1").text(lockId);
            $("#softVer1").text(softVer);
            $("#hardVer1").text(hardVer);
            $("#lockStatus").text(statusStr);
            $("#zt").text(statusStr);
        },
        function(){
            console.log("错误");
        }
    });
}

/**
 * 获取所有密码
 */
function getAllKey(id){

    var dq1 =$.trim($('#dq_mm').val());//当前页
    var zg1 =$.trim($('#zg_mm').val());//总共
    var dq = parseInt(dq1);
    var zg = parseInt(zg1);
    console.log("zg="+dq)
    console.log("dq="+zg)
    var jsona = {};

    jsona["pageNum"] =  1;
    if(id=="shouye_mm")
    {
        jsona["pageNum"] =  1;
    }else if(id=="shang_mm")
    {
        if(dq>1)
        {jsona["pageNum"] =  dq-1;
        }else {
            jsona["pageNum"] =  1;
        }

    }
    else if(id=="xia_mm")
    {
        console.log("hahahaha");
        console.log(dq);
        console.log(zg);
        if(dq<zg)
        {
            jsona["pageNum"] =  dq+1;
        }else {
            jsona["pageNum"] =  zg;
        }
    }
    else if(id=="wei_mm")
    {
        jsona["pageNum"] =  zg;
    }
    else{
        jsona["pageNum"] =  id;
    }
    console.log(jsona["pageNum"]);

    console.log(jsona);
    jsona["lockId"]=$("#rlockId0").text();
    $.ajax({
        url: '/lock/getAllKey',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async:false,
        success:function(data){
            console.log(data)
            var pageHelperMessage = data["pageHelperMessage"];
            $("#page_mm").empty();
            var txt2 ="当前"+pageHelperMessage["pageNum"]+"页，总共"+pageHelperMessage["pages"]+"页，共"+pageHelperMessage["total"]+"条记录";
            $("#page_mm").append(txt2);
            var keyInfos=data["keyInfos"];
            txt1="";
            for(var key in keyInfos)
            {
                var show=keyInfos[key];
                console.log(show);
                txt1=txt1+"<tr>\n" +
                    "                                            <td>"+show["kPassword"]+"</td>\n" +
                    "                                            <td>"+show["userName"]+"</td>\n" +
                    "                                            <td>"+show["typeStr"]+"</td>\n" +
                    "                                            <td>"+show["statusStr"]+"</td>\n" +
                    "                                        </tr>";

            }
            $("#neirong_mm").empty();
            $("#neirong_mm").append(txt1);

            var text3="<input id=\"dq_mm\" type=\"hidden\" value=\""+pageHelperMessage["pageNum"]+"\">\n" +
                "                                        <input id=\"zg_mm\" type=\"hidden\" value=\""+pageHelperMessage["pages"]+"\">";
            $("#jihao_mm").empty();
            $("#jihao_mm").append(text3);
        },
        function(){
            console.log("错误");
        }
    });
}


$(function() {
    lockMessage()//获得lock信息
    getAllKey("shouye_mm");//获取本锁所有密码
    console.log("hah");
    // 自定义类型：参数为数组，可多条数据
   // alignmentFns.createType([{"test": {"step" : 1, "min" : 10, "max" : 999, "digit" : 0}}]);
   //  var a={"step":1, "min": 0, "max" : 99, "digit" : 0};

    // 初始化
    alignmentFns.initialize();

    //新建密码
    $(document).on("click",".gb",function() {

        //ajax

        closeBox();
    })

})