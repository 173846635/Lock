
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
    var popBox = document.getElementById("popBox");
    var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
    popLayer.style.display = "none";
}







/*点击弹出按钮*/
function popBox2() {
    console.log("弹出")
    var popBox = document.getElementById("xxxx");
    //var popLayer = document.getElementById("popLayer");
    popBox.style.display = "block";
   // popLayer.style.display = "block";

};

function closeBox2() {
    var popBox = document.getElementById("xxxx");
   // var popLayer = document.getElementById("popLayer");
    popBox.style.display = "none";
   // popLayer.style.display = "none";
}

//删除房间
function fjscjh(id){
    console.log(id)
    var tr="'#"+id+"'"
     $("#"+id+"").remove();

}

//获取个人管理员信息
function getmyMessage() {
    $.ajax({
        url: 'myMessage',
        type: 'GET',
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
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
    console.log(jsona["pageNum"]);

    console.log(jsona);



    $.ajax({
        url: 'otherMessage',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
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
                    "                <td class=\"dx\"><button id=\""+show["adminId"]+"\" class=\"qrxg\">删除</button></td>\n" +
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
        url: 'updateMyMessage',
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
$(function() {
    //获取本人信息
    getmyMessage()
    //获取其他管理员信息
    getOtherMessage("shouye")















    var n=1;
    //弹出
    $(document).on("click",".xiugai",function(){
        var name =$(this).prev().text();

        console.log(name);
        $("#name1").val(name);
        popBox();
    })
    //删除楼
    $(document).on("click",".xiugai",function(){
        //删除楼层ajax，记得检测有不有人住
    })

    //添加房间弹窗
    $(document).on("click","#zjfj",function(){
        console.log("添加房间")
        var h="fjcj"+n;
        var txt="<div class=\"left zjfjk\" id='"+h+"'>\n" +
            "                <div class=\"right1 iconfont zjfjkgb finger\" onclick=\"fjscjh('"+h+"')\">&#xe613;</div>\n" +
            "                <div class=\"zjfjk2\">\n" +
            "                    <input class=\"zjfjk3\" placeholder=\"输入房号\" name=\"houseelementname\">\n" +
            "                </div>\n" +
            "\n" +
            "            </div>"
        $(this).before(txt)
        n++;

    })


})



