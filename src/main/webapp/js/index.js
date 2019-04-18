
//删除房间（页面删除）
function fjscjh(id){
    console.log(id)
    var tr="'#"+id+"'"
     $("#"+id+"").remove();

}




//增删房间页面
function getRoomsNum(id){
    var ids= id.split('_');
    var apartmentId= ids[0];
    var floor= ids[1];
   // console.log("id="+adminid1);
    var jsona = {};
    jsona["apartmentId"] =apartmentId;
    jsona["floor"] =floor;
    $.ajax({
        url: 'getRoomsNum',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            var txt ="";
            for(var key in data) {
                var room=data[key];
                console.log(key["rNum"])
                if(room["rResidentNum"]==0) {
                    txt = txt + "<div id=\"tcfj" + room["rId"] + "\" class=\" zsfjdiv\">\n" +
                        "                <div class=\"right_room_number1\">\n" +
                        "                    <div class=\"fh\">"+ room["rNum"]+"</div><div  class=\"finger right1 iconfont scfj fjscjh\" onclick=\"delectRoom('tcfj" + room["rId"] + "')\">&#xe613;</div>\n" +
                        "                </div>\n" +
                        "            </div>\n"
                }
                else if(room["rResidentNum"]!=0){
                    txt = txt + "    <div id='tcfj" + room["rId"] +"' class=\" zsfjdiv1\">\n" +
                        "                <div class=\"right_room_number1\">\n" +
                        "                    <div class=\"fh1\">"+ room["rNum"]+"</div>\n" +
                        "                </div>\n" +
                        "            </div>\n"
                }
            }
            txt=txt+"            <div id=\"zjfj\" class=\" zsfjdiv finger\">\n" +
                "                <div  class=\"right_room_number1\">\n" +
                "                    <div class=\"finger left iconfont zjfjbj size jh\">&#xe626;</div> <div class=\"fh1 zjfj\">增加房间</div>\n" +
                "                </div>\n" +
                "            </div>"
            $("#zjfjDiv").empty();
            $("#zjfjDiv").append(txt);

            $("#gyId").empty();
            $("#gyId").append(apartmentId);

            $("#floor").empty();
            $("#floor").append(floor);
            zsfjpopBox();
        },
        function(){
            console.log("错误");
        }
    });
}

//新增房间同时关闭弹窗
function addroom(){
    var btns = $('.xzfjbj1').map(function(){return $(this).val();})
    console.log("新增房间号="+btns.toArray());
    if(btns.toArray()=="")
    {
        alert("没有任何修改");
        console.log("新增房间号1");
        zsfjcloseBox();
        return 0;
    }
    var jsona = {};
    jsona["rooms"] =btns.toArray();//数组化
    jsona["apartmentId"] =$.trim($('#gyId').text());
    jsona["floor"] =$.trim($('#floor').text());
    console.log("rooms="+jsona["rooms"]);
    console.log("apartmentId="+jsona["apartmentId"]);
    console.log("floor="+jsona["floor"]);
    $.ajax({
        url: 'addRoomsNum',
        type: 'POST',
        traditional: true,
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            if(data["result"]==1)
            {
            alert(data["message"]);
                window.location.replace("./index");
            }else if(data["result"]==2)
            {
                var err="";
                for(var key in data["repetitionRooms"])
                {
                    console.log(data["repetitionRooms"][key]);
                    err=err+data["repetitionRooms"][key]["rNum"]+",";
                }
                err=err.substring(0,err.length-1);

                alert(err+"号房间以存在")
            }
            else if(data["result"]==3)
            {
                alert(data["message"]);
            }

        },
        function(){
            console.log("错误");
        }
    });
}

//删除房间
function delectRoom(roomId){
    var id=roomId.substring(4);
    console.log("roomId="+id);
    var jsona = {};
    jsona["roomId"] =id;
    $.ajax({
        url: 'deleteRoom',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            if(data["result"]==0)
            {
                fjscjh(roomId);
            }

            //console.log(dq);

        },
        function(){
            console.log("错误");
        }
    });
}

$(function() {
    var n=1;

    //添加房间前端
    $(document).on("click","#zjfj",function(){
        console.log("添加房间")
        var h="fjcj"+n;
        var i="gb"+n;
        var txt="<div class=\"left zjfjk\" id='"+h+"'>\n" +
            "                <div class=\"right1 iconfont zjfjkgb finger\" onclick=\"fjscjh('"+h+"')\">&#xe613;</div>\n" +
            "                <div class=\"zjfjk2\">\n" +
            "                    <input id=\""+i+"\" class=\"zjfjk3 xzfjbj1 shuzi\" placeholder=\"输入房号\" name=\"houseelementname\">\n" +
            "                </div>\n" +
            "\n" +
            "            </div>"
        $(this).before(txt)
        $("#"+i+"").focus()
        n++;

    })


})



