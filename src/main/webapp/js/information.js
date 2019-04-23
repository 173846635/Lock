//退宿
function quit(roomId){
    var userId=$("#scduserId").text();
    console.log("userId="+userId);
    console.log("roomId="+roomId);
    var jsona={};
    jsona["roomId"]=roomId;
    jsona["userId"]=userId;
    $.ajax({
        url: 'quit',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            if(data["result"]!=0)
            {
                window.location.replace("./information?roomId="+roomId);
                //querengcloseBox();
            }
            // var myAccount=data["adminAccount"];
            // var myPassword=data["adminPassword"];
            // var myName=data["adminName"];
            // var myPermission=data["adminPermission"];
            // $('#myAccount').val(myAccount);
            // $('#myPassword').val(myPassword);
            // $('#myName').val(myName);
            // $('#myPermission').val(myPermission);
        },
        function(){
            console.log("错误");
        }
    });
}
//修改租金
function changeRent(){
    var price=$("#zjinput").val();
    var roomId=$("#xgzjRoomId").text();
    console.log("price="+price);
    console.log("roomId="+roomId);
    var jsona={};
    jsona["price"]=price;
    jsona["roomId"]=roomId;
    $.ajax({
        url: 'changeRent',
        type: 'GET',
        data:jsona,
        dataType: 'json',
        async: true,
        success:function(data){
            console.log(data);
            alert(data["message"])
            if(data["result"]==0)
            {
                $("#zjinput").val(price);
                $("#zzj").val(price);
            }
            xgzjcloseBox();

            // var myAccount=data["adminAccount"];
            // var myPassword=data["adminPassword"];
            // var myName=data["adminName"];
            // var myPermission=data["adminPermission"];
            // $('#myAccount').val(myAccount);
            // $('#myPassword').val(myPassword);
            // $('#myName').val(myName);
            // $('#myPermission').val(myPermission);
        },
        function(){
            console.log("错误");
        }
    });
}

//退房
function checkOut(){
    var roomId=$("#tfRoomId").text();
    console.log("roomId="+roomId);
    var jsona={};
    jsona["roomId"]=roomId;
    $.ajax({
        url: 'checkOut',
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

            // var myAccount=data["adminAccount"];
            // var myPassword=data["adminPassword"];
            // var myName=data["adminName"];
            // var myPermission=data["adminPermission"];
            // $('#myAccount').val(myAccount);
            // $('#myPassword').val(myPassword);
            // $('#myName').val(myName);
            // $('#myPermission').val(myPermission);
        },
        function(){
            console.log("错误");
        }
    });
}

$(function() {




    $(document).on("click","#xgzj",function() {
            $("#zj1").removeAttr("readonly");
    })

    $("#xgzj").click(function(){
        $("#zj1").val("").focus();
    })
})