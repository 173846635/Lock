/*点击弹出按钮*/
function popBox() {
    console.log("弹出")
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    popLayer.style.display = "block";

};

/*点击关闭按钮*/
function closeBox() {
    console.log("关闭")
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}


/*点击弹出新建密码*/
function xjmmpopBox() {
    console.log("弹出新建密码")
    var xjmm = document.getElementById("xjmm");
    var popLayer = document.getElementById("popLayer");
    xjmm.style.display = "block";
    popLayer.style.display = "block";

};

/*点击关闭新建密码*/
function xjmmcloseBox() {
    console.log("关闭新建密码")
    var xjmm = document.getElementById("xjmm");
    var popLayer = document.getElementById("popLayer");
    xjmm.style.display = "none";
    popLayer.style.display = "none";
}





/*点击头像显示个人信息*/
function popBox2() {
    console.log("个人信息弹出")
    var popBox = document.getElementById("xxxx");
    //var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
    // popLayer.style.display = "block";

};

function closeBox2() {
    console.log("个人信息关闭")
    var popBox = document.getElementById("xxxx");
    // var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
    // popLayer.style.display = "none";
}


//获取个人管理员信息
function getmyMessage() {
    $.ajax({
        url: '/lock/myMessage',
        type: 'GET',
        dataType: 'json',
        async: true,
        success:function(data){
            //console.log(data);
            var myAccount=data["adminAccount"];
            var myPassword=data["adminPassword"];
            var myName=data["adminName"];
            var myPermission=data["adminPermission"];
            $('#myAccount').val(myAccount);
            $('#myPassword').val(myPassword);
            $('#myName').val(myName);
            $('#myPermission').val(myPermission);
        },
        function(){
            console.log("错误");
        }
    });
}

//获取其他管理员信息
function getOtherMessage(id) {
    var dq1 =$.trim($('#dq').val());//当前页
    var zg1 =$.trim($('#zg').val());//总共
    var dq = parseInt(dq1);
    var zg = parseInt(zg1);
    console.log("zg="+dq)
    console.log("dq="+zg)
    var jsona = {};

    jsona["pageNum"] =  1;
    if(id=="shouye")
    {
        jsona["pageNum"] =  1;
    }else if(id=="shang")
    {
        if(dq>1)
        {jsona["pageNum"] =  dq-1;
        }else {
            jsona["pageNum"] =  1;
        }

    }
    else if(id=="xia")
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
    else if(id=="wei")
    {
        jsona["pageNum"] =  zg;
    }
    else{
        jsona["pageNum"] =  id;
    }
    console.log(jsona["pageNum"]);

    console.log(jsona);



    $.ajax({
        url: '/lock/otherMessage',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            //console.log(data);
            var pageHelperMessage = data["pageHelperMessage"];
            $("#page").empty();
            var txt2 ="当前"+pageHelperMessage["pageNum"]+"页，总共"+pageHelperMessage["pages"]+"页，共"+pageHelperMessage["total"]+"条记录";
            $("#page").append(txt2);
            var administratorList=data["administratorList"];
            txt1="";
            for(var key in administratorList)
            {
                var show=administratorList[key];
                console.log(show);
                console.log(show[4]);
                txt1=txt1+"<tr>\n" +
                    "                <td class=\"dx\"><input readonly=\"value\"  style=\"background:#CCCCCC;\"  class=\"kp\" value=\""+show["adminAccount"]+"\" ></input></td>\n" +
                    "                <td class=\"dx\"><input readonly=\"value\"   class=\"kp\" style=\"background:#CCCCCC;\"  value=\""+show["adminPassword"]+"\"></td>\n" +
                    "                <td class=\"dx\"><input readonly=\"value\"   class=\"kp\" style=\"background:#CCCCCC;\"  value=\""+show["adminName"]+"\"></td>\n" +
                    "                <td class=\"dx\"><input readonly=\"value\"  style=\"background:#CCCCCC;\"   class=\"kp\"value=\""+show["adminPermission"]+"\" ></td>\n" +
                    "                <td class=\"dx\"><button id=\""+show["adminId"]+"\"  class=\"qrxg\" onclick=\"delectOntherMessage(this.id)\"  >删除</button></td>\n" +
                    "            </tr>";

            }
            $("#neirong2").empty();
            $("#neirong2").append(txt1);

            var text3="<input id=\"dq\" type=\"hidden\" value=\""+pageHelperMessage["pageNum"]+"\">\n" +
                "                                        <input id=\"zg\" type=\"hidden\" value=\""+pageHelperMessage["pages"]+"\">";
            $("#jihao").empty();
            $("#jihao").append(text3);
        },
        function(){
            console.log("错误");
        }
    });
}

//修改本人信息
function updateMyMessage(){
    var myPassword0=$.trim($('#myPassword').val());
    var myName0=$.trim($('#myName').val());
    var jsona = {};
    jsona["password"] =  myPassword0;
    jsona["newName"] =  myName0;
    $.ajax({
        url: '/lock/updateMyMessage',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            var content=data["content"];
            var myAccount=content["adminAccount"];
            var myPassword=content["adminPassword"];
            var myName=content["adminName"];
            var myPermission=content["adminPermission"];
            $('#myAccount').val(myAccount);
            $('#myPassword').val(myPassword);
            $('#myName').val(myName);
            $('#myPermission').val(myPermission);
        },
        function(){
            console.log("错误");
        }
    });
}

//删除其他低级管理员
function delectOntherMessage(admin){
    var adminid0=admin;
    var adminid1=parseInt(adminid0);
    console.log("adminId2="+adminid1);
    var jsona = {};
    jsona["adminId"] =adminid1;
    var dq1 =$.trim($('#dq').val());//当前页
    var dq = parseInt(dq1);
    console.log("jsona="+jsona["adminId"])
    $.ajax({
        url: '/lock/deleteOtherMessage',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            console.log(dq);
            getOtherMessage(dq);
        },
        function(){
            console.log("错误");
        }
    });
}

//新增管理员
function addMyMessage(){
    var account=$.trim($('#account2').val());
    var password=$.trim($('#password2').val());
    var permission=$('input[name="permission"]:checked').val();
    var name=$.trim($('#name2').val());
    console.log(account+":"+password+":"+permission+":"+name)
    var dq1 =$.trim($('#dq').val());//当前页
    var dq = parseInt(dq1);
    var jsona = {};
    if(account==null||account=="")
    {
        alert("账号不能为空");
        return 0;
    }
    if(password==null||password=="")
    {
        alert("密码不能为空");
        return 0;
    }
    if(name==null||name=="")
    {
        alert("姓名不能为空");
        return 0;
    }
    jsona["account"] =  account;
    jsona["password"] =  password;
    jsona["permission"] =  permission;
    jsona["name"] =  name;
    console.log(jsona);
    $.ajax({
        url: '/lock/addOtherMessage',
        type: 'POST',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            if(data["result"]==1)
            {

                getOtherMessage(dq);
                xjglycloseBox();
            }
        },
        function(){
            console.log("错误");
        }
    });
}

//弹出修改公寓楼
function tcxiugailou(apartment){
    console.log("弹出修改公寓楼")

    var x=apartment.indexOf("_");
    var apartmentId=apartment.substring(0,x);
    var apartmentName=apartment.substring(x+1);
    var nr="floor_"+apartmentId;
    console.log("nr="+nr)
    var floors = $("."+nr+"").map(function(){return $(this).text();}).toArray()
    console.log("floors="+floors)
    var ttt="";
    for(var floor1 in floors)
    {
        console.log("floor1="+floors[floor1])
        var floor=floors[floor1].substring(0,floors[floor1].length-1)
        console.log("floor="+floor)
        ttt=ttt+"<div style=\"text-align:center; margin:0 auto;\">\n" +
            "                <label style=\"font-size: 15px; font-weight:bold \">楼层：</label>\n" +
            "\n" +
            "                <span style=\"padding-top:6px\" class=\"pull-left\">第</span>\n" +
            "                <input readonly='value'  style=\"width: 30px; margin: auto; text-align:center; background:#CCCCCC;\" class=\"zjfjk3 \" type=\"text\" name=\"floorname\" id=\"floorid_"+apartmentId+"_"+floor+"\" value=\""+floor+"\" placeholder>\n" +
            "                <span class=\"pull-left mr20\" style=\"padding-top:6px\">  层</span><span id=\"spanId_"+apartmentId+"_"+floor+"\" style=\"margin-left: 30px;\" class=\"xin lousc lcscjh\">删除</span>\n" +
            "            </div>\n" +
            "            <br/>";
    }
    $(".name1").val(apartmentName);
    $(".name1").attr('id',"tcApartmentId_"+apartmentId);
    $(".scSpan").attr('id',"scSpanId_"+apartmentId);
    $("#floor_div").empty();
    $("#floor_div").append(ttt);
    popBox();
}

//添加楼层（前端）
function addfloorQ() {
    ttt="<div style=\"text-align:center; margin:0 auto 0 -53px;\">\n" +
        "                <label style=\"font-size: 15px; font-weight:bold \">楼层：</label>\n" +
        "\n" +
        "                <span style=\"padding-top:6px\" class=\"pull-left\">第</span>\n" +
        "                <input   style=\"width: 30px; margin: auto; text-align:\" class=\"zjfjk3 fsfloor\" type=\"text\" name=\"floorname\" id=\"floor1id\" value=\"\" placeholder>\n" +
        "<span class=\"pull-left mr20\" style=\"padding-top:6px\">  层</span>\n" +
        "            </div>\n" +
        "            <br/>";
    $("#floor_div").append(ttt);
}
//删除公寓楼
function deleteApartment(apartmentId1) {
    console.log("apartmentId1="+apartmentId1);
    var apartmentId=apartmentId1.substring(9);
    console.log("apartmentId="+apartmentId);
    jsona={};
    jsona["apartmentId"]=apartmentId;
    $.ajax({
        url: '/lock/deleteApartment',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            if(data["result"]==0)
            {
                window.location.replace("./index");
            }
            // $("#zbnr").empty();
            // $("#zbnr").append(text);
            //console.log(dq);

        },
        function(){
            console.log("错误");
        }
    });
}

//修改楼名和楼层数
function updateApartmentNameAndFloor(apartmentId1) {
    var apartmentId1=$.trim($('.scSpan').attr("id"));
    console.log("apartmentId1="+apartmentId1);
    var apartmentId=apartmentId1.substring(9);
    console.log("apartmentId="+apartmentId);


    var floors = $('.fsfloor').map(function(){return $(this).val();}).toArray()//楼层s


    var apartmentName= $(".name1").val();

    console.log("apartmentName="+apartmentName);
    if(apartmentName=="")
    {
        alert("楼名不能为空");
        return 0;
    }
    jsona={};
    jsona["apartmentId"]=apartmentId;
    jsona["floors"]=floors;
    if(floors==''||floors==null){
        jsona["floors"]=null;
    }
    jsona["apartmentName"]=apartmentName;
    console.log("floors="+jsona["floors"])
    $.ajax({
        url: '/lock/updateApartmentNameAndFloor',
        traditional: true,
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            var updateFloor=data["updateFloor"];//增加楼层
            var updateApartmentName=data["updateApartmentName"];//修改公寓楼名
            if(updateFloor!=-1&&updateApartmentName!=-1)
            {
                if(updateFloor["result"]==0&&updateApartmentName["result"]==0)
                {
                    alert("修改成功");
                }
                else if(updateFloor["result"]!=0){
                    alert(updateFloor["message"])
                }else if(updateApartmentName["result"]!=0)
                {
                    alert(updateApartmentName["message"])
                }
            }else if(updateFloor==-1&&updateApartmentName["result"]==0)
            {
                alert("修改成功");
            }else if(updateFloor==-1&&updateApartmentName["result"]!=0)
            {
                alert(updateApartmentName["message"])
            }
            window.location.replace("./index");
            closeBox();
        },
        function(){
            console.log("错误");
        }
    });
}

//删除楼层spanId_"+apartmentId+"_"+floor
function deleteFloorH(id1) {
    console.log("id1="+id1);
    var id2=id1.substring(7);
    var x=id2.indexOf("_");
    var apartmentId=id2.substring(0,x)
    var floor=id2.substring(x+1);
    console.log("apartmentId="+apartmentId);
    console.log("floor="+floor);
    jsona={};
    jsona["apartmentId"]=apartmentId;
    jsona["floor"]=floor;
    var rt=0;
    $.ajax({
        url: '/lock/deleteFloor',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async:false,
        success:function(data){
            console.log(data);
            alert(data["message"])
            if(data["result"]==0)
            {
                rt=0;
            }else {
                rt=1;
            }
            // $("#zbnr").empty();
            // $("#zbnr").append(text);
            //console.log(dq);

        },
        function(){
            console.log("错误");
        }
    });
    return rt;

}

//读取左边
function left(){
    console.log("left");
    $.ajax({
        url: '/lock/left',
        type: 'GET',
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            var text="";
            for(var apartments1 in data)
            {
                var apartments=  data[apartments1];
                console.log(apartments);
                var apartmentID=apartments["ApartmentID"];
                var apartmentName=apartments["ApartmentName"];
                var rooms=apartments["Rooms"];
                var leftnr=apartmentID+"_"+apartmentName;

                text=text+"<li>\n" +
                    "                    <a id=\""+leftnr+"\" class=\"house\"  href=\"#mao_"+apartmentID+"\"><i class=\"iconfont\">&#xe60f;</i><span >"+apartmentName+"</span><button onclick=\"tcxiugailou('"+leftnr+"')\" class=\"xiugai\" >修改</button></a>\n" +
                    "                    <ul>\n"
                var ttx=0;
                for(var floor in rooms)
                {
                    text=text+"<li class=\"floor\"><a class='"+"floor_"+apartmentID+"' href=\"/lock/index#mao_"+apartmentID+"_"+floor+"\">"+floor+"楼</a></li>\n";
                    ttx++;
                }
                text=text+"</ul>\n" +
                    "</li>\n"
            }
            $("#zbnr").empty();
            $("#zbnr").append(text);
            //console.log(dq);

        },
        function(){
            console.log("错误");
        }
    });


}


/*点击弹出绑定门锁*/
function bdmspopBox() {
    console.log("弹出")
    var bdmspopBox = document.getElementById("bdmspopBox");
    var popLayer = document.getElementById("popLayer");
    bdmspopBox.style.display = "block";
    popLayer.style.display = "block";

};

/*点击关闭绑定门锁*/
function bdmscloseBox() {
    console.log("关闭")
    var bdmspopBox = document.getElementById("bdmspopBox");
    var popLayer = document.getElementById("popLayer");
    bdmspopBox.style.display = "none";
    popLayer.style.display = "none";
}

/**
 * 绑定门锁
 */
function bdms(){
    var introduction= $("#bdmssbh").val();
    var roomId= $("#roomId").text();
    var jsona={};
    jsona["roomId"]=roomId;
    jsona["introduction"]=introduction;
            $.ajax({
                url: '/lock/binding',
                type: 'POST',
                data:jsona,
                dataType: 'json',
                async: true,
                success:function(data){
                console.log(data)
                    if(data["result"]==0)
                    {
                        alert(data["detail"]);
                        bdmscloseBox();
                    }else {
                        alert("错误："+data["detail"]+"\n错误代码:"+data["result"]);
                    }
        }
    })
}

//公共读取
$(function() {
    //获取本人信息
    getmyMessage()
    //获取其他管理员信息
    getOtherMessage("shouye")
    //获取左边
    left();

    //删除楼层（前端）
    $(document).on("click",".lcscjh",function() {
        var jg=deleteFloorH($(this).attr('id'))
        console.log("jg="+jg);
        if(jg==0)
        {
            $(this).parent().next().remove();
            $(this).parent().remove();
        }

    })




    var onOffjh= $("#onOffbj").text();
    console.log("onoff="+onOffjh);
    if(onOffjh==1)
    {
        console.log("门开着")
        $(".div1").attr("name","on")
        $(".div1").toggleClass('close1');
        $(".div1").toggleClass('open1');
        $(".div1").children(".div2").toggleClass('close2');
        $(".div1").children(".div2").toggleClass('open2');
    }

    $(".div1").on('click',function() {
        console.log('className======='+$(this).prop("className"))
        if($(".div1").attr("name")=="off")
        {
            var jg=1;
            console.log("门关着")


            var jsona={};
            jsona["lockId"]=$("#msLockId").text();
            console.log(jsona["lockId"])
            $.ajax({
                url: '/lock/open',
                type: 'POST',
                timeout : 5000,
                data:jsona,
                dataType: 'json',
                async: false,
                success:function(data){
                    jg=data["result"];
                    console.log(data);
                    alert(data["detail"]);
                },
                function(){
                    console.log("错误");
                }
            });
            console.log(jg)
            if(jg==0)
            {
                console.log("开门")
                $(this).attr("name","on")
                $(this).toggleClass('close1');
                $(this).toggleClass('open1');
                $(this).children(".div2").toggleClass('close2');
                $(this).children(".div2").toggleClass('open2');
            }
        }
    })

})