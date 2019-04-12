<%@ page import="com.dhy.yycompany.lock.bean.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dhy.yycompany.lock.bean.RoomX" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>gy公寓</title>
    <link rel="stylesheet" href="../../css/tou.css">
    <link rel="stylesheet" href="../../css/iconfont.css">
    <link rel="stylesheet" href="../../css/leftNavigation.css">
    <link rel="stylesheet" href="../../css/rightWindow.css">
    <link rel="stylesheet" href="../../css/information.css">
    <link rel="stylesheet" href="../../css/xin.css">
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/public.css">
    <link rel="stylesheet" href="../../css/tc.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="../../js/openjia.js"></script>
    <script type="text/javascript" src="../../js/index.js"></script>
    <script type="text/javascript" src="../../js/fingerprint.js"></script>
    <script type="text/javascript" src="../../js/public.js"></script>
    <script type="text/javascript" src="../../js/tc.js"></script>

</head>
<body>
<!--头实体-->
<div class="main_container dw">
    <div class="navbar-header">
        <div class="versions">
            <a href="#">
                gy公寓
                <span>版本v0.1</span>
            </a>
        </div>
        <div class="main_date">
            <a href="#">1月27日星期日</a>
        </div>
    </div>
    <div class="portrait" onmousemove="popBox2()" >
        <a href="#" on>
            <div class="tx"><img class="tp" src="../../img/tx1.jpg"></div>

        </a>

    </div>
</div>
<!--主体-->
<div class="main_xia" style="min-height:100%;">

    <!--房态-->
    <div class="house_status dw">
        <!--左导航实体-->
        <div class="left_navigation dw">
            <ul id="zbnr" class="houses">

            </ul>
        </div>

    </div>
    <!--右边房间窗口-->
    <div class="house_status1 dw">
        <%--房间id标记--%>
        <div id="roomId" style="display: none;">${requestScope.roomX.rId}</div>
        <div class="right_window dw">
            <div class="card">
                <a class="ft_card ft" href="index">房态</a><span  class="ft"> > </span><a class="ft_card ft"  href="vacant?roomId=${requestScope.roomX.rId}">${requestScope.roomX.rApartmentName}-${requestScope.roomX.rFloor}楼-${requestScope.roomX.rNum}</a>
            </div>
            <div class="information">
                <div class="information_t">
                    <div class="room_name">${requestScope.roomX.rApartmentName}-${requestScope.roomX.rFloor}楼-${requestScope.roomX.rNum}</div>
                        <div class="message2 ">
                            <div class="zxx">
                                月租金： <span id="zzj" class="zj">${requestScope.roomX.rPrice}</span>
                                <span id="xgzj" onclick="xgzjpopBox()" class="xgzj finger">修改租金</span>
                            </div>
                            <div class="butt2">
                                <button class="bd">绑定</button>

                            </div>

                            <div class="message3s">
                                <div class="message3 ke">
                                    <label class="border-input">租聘时间:</label><input  id="kaishi" class="form-control border-input" type="date"><HR class="border-input" align=center width=5% color=#0000 SIZE=2 style="margin-top: 10px; margin-left: 8px;  "><input  id="jieshu" class="form-control border-input" type="date">
                                    <br/>
                                    <br/>

                                    <label >居住人数:</label><input 54  class="form-control"></input>
                                </div>

                                <div class="message3 ke">
                                    <label style="float:left;">门锁控制</label>
                                    <%
                                        RoomX roomX = (RoomX) request.getAttribute("roomX");
                                        if(roomX.getrLockId()!=0)
                                        {
                                    %>
                                    <div id="onOffbj" style="display: none;">${requestScope.onOff}</div>
                                    <div id="msLockId" style="display: none;">${requestScope.roomX.rLockId}</div>
                                    <div style="margin-left: 10px">
                                        <span style="margin-left: 10px" class="left1">关</span>
                                        <div name="off" style="margin-left: 5px; margin-right: 5px" class="div1 left1 open1">

                                            <span class="left2"></span>
                                            <span class="right2"></span>

                                            <div class="div2 open2"></div>
                                        </div>
                                        <span class="left1">开</span>
                                    </div>

                                    <div class="butt1">
                                        <a href="information/key?roomId=${requestScope.roomX.rId}">
                                            <button style="cursor:pointer" class="gl bd " >门锁管理</button>
                                        </a>
                                    </div>
                                    <%
                                        }else{
                                    %>
                                    <div style="margin-left: 10px;width: 260px;font-size: 10px;color: black; margin-top: 2px" class="left">
                                        无绑定的门锁，请先绑定门锁
                                    </div>

                                    <div class="butt1">
                                        <button style="cursor:pointer" class="gl bd " onclick="bdmspopBox()" >绑定门锁</button>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>


                                <div  class="message31 ke">
                                    <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><input  name="name0" class="form-control" type="text" >
                                    <br/>
                                    <br/>
                                    <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label><input  name="sex0" class="form-control" type="text" >
                                    <br/>
                                    <br/>
                                    <label>手&nbsp;&nbsp;机&nbsp;&nbsp;号：</label><input  name="phone0" class="form-control" type="text" >
                                    <br/><br/>
                                    <label>入住时间：</label><input  name="time0" class="form-control" type="text" >
                                    <br/>
                                    <br/>
                                    <label>身份证号：</label><input  name="sfz0" class="form-control" type="text">
                                    <div id="confirm" class="bd confirm" onclick="ruzhupopBox()">确认入住</div>
                                </div>
                                <%--<div id="order" class="order ke">--%>

                                <%--</div>--%>
                            </div>

                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="db1 dw">
        <div class="ld_footer">
            <div class="ld_footer_in">
                <p>地址：宁波财经学院</p>
                <p>联系方式：13355908909</p>
            </div>
        </div>
    </div>


    <!--头实体-->
    <div class="main_container dw">
        <div class="navbar-header">
            <div class="versions">
                <a href="/lock/index">
                    gy公寓
                    <span>版本v0.1</span>
                </a>
            </div>
            <div class="main_date">
                <a href="#">1月27日星期日</a>
            </div>
        </div>
        <div class="portrait" onmousemove="popBox2()"  onmouseout="closeBox2()">
            <a href="#" on>
                <div class="tx"><img class="tp" src="../../img/tx1.jpg"></div>

            </a>
            <!--角色详细信息弹窗-->
            <div id="xxxx" class="you dw"  >
                <div class="content2">
                    <h4 class="toubt">管理员信息</h4>
                    <div class="wk">
                        <div  class="jz jz1"><label class="nr left">昵称：</label><div  class="nr xx left">琥珀川小黑</div></div>
                        <br/>
                        <div class="jz jz2"><label class="nr left">信息：</label><div class="nr xx left">无</div></div>
                        <br/>
                        <div class="jz jz2"><label class="nr left">权限：</label><div class="nr xx left">一级权限</div></div>
                    </div>

                </div>
                <HR align=center width=100% color=#ccc SIZE=2>
                <div  ><div  class="xm mmxg" onclick="glypopBox()">管理员管理</div><div class="xm tc">退出</div></div>

            </div>

        </div>
    </div>
    <!--修改楼弹窗-->
    <div id="popLayer"></div>
    <div id="popBox" style="height: 400px; width: 500px;" class="popBox ke">
        <div class="close">
            <a  href="javascript:void(0)" onclick="closeBox()" class="iconfont">&#xe613;</a>
        </div>
        <div  style="width: auto;" class="xgltcnr ke">
            <div class="xgltcnr1 content">
                <h4>修改信息</h4>
                <br/>
                <br/>
                <br/>
                <div ><label style="font-size: 15px; font-weight:bold ">楼&nbsp;&nbsp;&nbsp;名：</label><input    id="111" class="name1 form-control zjfjk3" type="text" value=""><span id="sclc" class="scSpan xin lousc" onclick="deleteApartment(this.id)">删除</span></div>
                <br/>
                <div id="floor_div" style="width: 210px; text-align:center; margin:0 auto;" class="lNumber">

                </div>

            </div>
            <div style="text-align:center; margin:20px auto 0 auto;"><div onclick="addfloorQ()"   style="width: 80px; text-align:center; margin:0 auto; " class="bd finger">添加楼层</div></div>
        </div>
        <div onclick="updateApartmentNameAndFloor()" style="text-align:center; margin:20px auto 0 auto;" class="gb"><div id="b2" style="width: 80px; text-align:center; margin:0 auto;" class="bd xyb finger">确认</div></div>
    </div>

    <!--管理管理员弹窗-->
    <div class="gly">
        <div id="popLayer1"></div>
        <div id="glygl" class="popBox ke">
            <div class="close">
                <a  href="javascript:void(0)" onclick="glycloseBox()" class="iconfont">&#xe613;</a>
            </div>
            <div class="myxx"><div class="myxx2">我的信息</div></div>
            <div class="content">
                <div class=" ke ys">
                    <table >
                        <thead>
                        <th>账号</th>
                        <th>密码</th>
                        <th>姓名</th>
                        <th>权限</th>
                        <th>操作</th>
                        </thead>
                        <tbody id="neirong">
                        <tr >
                            <td class="dx"><input id="myAccount" readonly="value" style="background:#CCCCCC;" value="${requestScope.administrator.adminAccount}" class="kp"></input></td>
                            <td class="dx"><input id="myPassword" value="${requestScope.administrator.adminPassword}"  class="kp"></td>
                            <td class="dx"><input id="myName" value="${requestScope.administrator.adminName}"  class="kp"></td>
                            <td class="dx"><input id="myPermission" readonly="value"  style="background:#CCCCCC;"   class="kp"value="${requestScope.administrator.adminPermission}" ></td>
                            <td class="dx"><button  onclick="updateMyMessage()" class="qrxg">确认修改</button></td>
                        </tr>

                        </tbody>
                    </table>
                </div>
                <div class="myxx"><div class="myxx2">其他管理员<span  class="xin" onclick="xjglypopBox()">新&nbsp;建</span></div></div>
                <div class=" ke ys">
                    <table id="other">
                        <thead>
                        <th>账号</th>
                        <th>密码</th>
                        <th>姓名</th>
                        <th>权限</th>
                        <th>操作</th>
                        </thead>
                        <tbody id="neirong2">

                        </tbody>
                    </table>
                    <div>
                        <div id="jihao" style="display: none;">
                            <input id="dq" type="hidden" value="1">
                            <input id="zg" type="hidden" value="1">
                        </div>

                        <nav  class="fenye navs" aria-label="Page navigation">
                            <ul class="col-md-offset-4 pagination">
                                <li><a class="shouye" id="shouye" onclick="getOtherMessage(this.id)">首&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
                                <li><a class="shang" id="shang" onclick="getOtherMessage(this.id)">上一页</a></li>
                                <li><a class="xia" id="xia" onclick="getOtherMessage(this.id)">下一页</a></li>
                                <li><a class="wei" id="wei" onclick="getOtherMessage(this.id)">尾&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
                            </ul>

                        </nav>
                        <div id="page" class="zxc clearfix"></div>
                    </div>
                </div>

            </div>
            <div  >
                <div onclick="glycloseBox()" id="b0" class="xq22">确认</div>
                <%--<div onclick="glycloseBox()" id="b2" class="xq22">取消</div>--%>
            </div>

        </div>
    </div>

    <!--新建管理员弹窗-->
    <div id="popLayer2"></div>
    <div id="xjglypopBox" class="popBox ke">
        <!--新建管理员弹窗1-->
        <div class="close">
            <a  href="javascript:void(0)" onclick="xjglycloseBox()" class="iconfont">&#xe613;</a>
        </div>
        <div class="content">
            <h4>新建管理员</h4>
            <br/>

            <div style="text-align:center; ">
                <label style="text-align:center;">
                    <input  style="position:absolute; width: 60px;height: 60px; opacity:0; text-align:center; cursor: pointer;" type="file" name="file" id="Album_img" accept="image/gif,image/jpeg,image/x-png"/>
                    <img  style="display: inline; width: 60px;height: 60px; text-align:center;" src="../../img/head.jpg">
                </label>
            </div>
            <br/>
            <div ><label>账号：</label><input  id="account2" class="form-control" type="text" value=""></div>
            <br/>
            <div ><label>密码：</label><input   id="password2" class="form-control" type="password" value=""></div>
            <br/>
            <div ><label>权限：</label>
                <label class="permission2"><input type="radio" name="permission" value="2" />2级权限</label>
                <label class="permission2"><input type="radio" name="permission" value="3" />3级权限</label>
                <label class="permission2"><input type="radio" name="permission" value="4" />4级权限</label>
            </div>
            <br/>
            <div ><label>姓名：</label><input  id="name2" class="form-control" type="text" value=""></div>
            <br/>
        </div>
        <div onclick="addMyMessage()" class="gb"><div id="b22" class="bd xyb finger">确认</div></div>

    </div>


<!--修改租金-->
<div id="xgzjtc" class="popBox ke">
    <div class="close">
        <a  href="javascript:void(0)" onclick="xgzjcloseBox()" class="gb iconfont">&#xe613;</a>
    </div>
    <div class="content">
        <h4>修改租金</h4>
        <div id="xgzjRoomId" style="display: none;">${requestScope.roomX.rId}</div>
        <div class="zj"><label>租金：</label><input  id="zjinput" class="form-control" type="text" value="${requestScope.roomX.rPrice}"></div>
        <br/>


    </div>
    <div onclick="changeRent()"><div id="b1" class="bd xyb finger">确认</div></div>

</div>



<!--登录-->
<div id="ruzhupopBox" class="popBox ke">
    <div class="close">
        <a id="gb" href="javascript:void(0)" onclick="" class="iconfont">&#xe613;</a>
    </div>
    <div class="content">
        <h4>现在进行指纹录入</h4>
        <img id="content" style="display:inline;" class="avatar" src="../../img/zw.gif" alt="..."/>
    </div>
    <div id="ks"><div id="b3" class="bd xyb finger">开始</div></div>

</div>



<!--绑定门锁-->
<div id="bdmspopBox" class="popBox ke">
    <div class="close">
        <a  href="javascript:void(0)" onclick="bdmscloseBox()" class="gb iconfont">&#xe613;</a>
    </div>
    <div class="content">
        <h4>绑定门锁</h4>
        <div id="bdmsRoomId" style="display: none;">${requestScope.roomX.rId}</div>
        <div class="zj"><label>设备号：</label><input  id="bdmssbh" class="form-control" type="text" value=""></div>
        <br/>


    </div>
    <div onclick="bdms()"><div id="b4" class="bd xyb finger">确认</div></div>

</div>
</body>
</html>
