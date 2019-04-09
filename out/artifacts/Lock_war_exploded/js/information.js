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
        if( $(".div1").attr("name")=="on")
        {
            console.log("门开着")
            console.log("关门")
            $(this).attr("name","off")

        }else if($(".div1").attr("name")=="off")
        {
            console.log("门关着")
            console.log("开门")
            $(this).attr("name","on")
        }
        $(this).toggleClass('close1');
        $(this).toggleClass('open1');
        $(this).children(".div2").toggleClass('close2');
        $(this).children(".div2").toggleClass('open2');



    })


    $(document).on("click","#xgzj",function() {
            $("#zj1").removeAttr("readonly");
    })

    $("#xgzj").click(function(){
        $("#zj1").val("").focus();
    })
})