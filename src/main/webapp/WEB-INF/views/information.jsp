<%@ page import="com.dhy.yycompany.lock.bean.UserInfo" %>
<%@ page import="java.util.List" %>
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
    <link rel="stylesheet" href="../../css/tc.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/public.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/information.js"></script>
    <script type="text/javascript" src="../../js/public.js"></script>
    <script type="text/javascript" src="../../js/tc.js"></script>
</head>

<body >
<!--主体-->
<div class="main_xia" style="min-height:100%;">

    <!--房态-->
    <div class="house_status dw">
        <!--左导航实体-->
        <div class="left_navigation dw">
            <ul id="zbnr" class="houses">
                <%--<li>--%>
                <%--<a class="house"  href="#"><i class="iconfont">&#xe60f;</i><span >1号楼</span><button class="xiugai" >修改</button></a>--%>
                <%--<ul>--%>
                <%--<li class="floor"><a href="#">1楼</a></li>--%>
                <%--<li class="floor"><a href="#">2楼</a></li>--%>
                <%--<li class="floor"><a href="#">3楼</a></li>--%>
                <%--</ul>--%>
                <%--</li>--%>

            </ul>
        </div>
    </div>

        <%--<!--左导航虚-->--%>
        <%--<div class="left_navigation xt zxt">--%>
        <!--右边房间窗口-->
        <div class="house_status1 dw">
            <div class="right_window dw">
            <div class="card">
                <a class="ft_card ft" href="index">房态</a><span  class="ft"> > </span><a class="ft_card ft"  href="information?roomId=${requestScope.roomX.rId}">${requestScope.roomX.rApartmentName}-${requestScope.roomX.rFloor}楼-${requestScope.roomX.rNum}</a>
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
                                <button class="bd" onclick="tfpopBox(${requestScope.roomX.rId})">退房</button>

                            </div>


                            <div class="butt2">
                                <button class="bd">绑定</button>

                            </div>

                            <div class="message3s">
                                <div class="message3 ke">
                                    <label class="border-input">租聘时间:</label><div readonly="readonly" id="kaishi" class="form-control border-input">${requestScope.roomX.rentTime}</div><HR class="border-input" align=center width=5% color: #dadada SIZE=2 style="margin-top: 10px; margin-left: 8px;  "><div readonly="readonly" id="jieshu" class="form-control border-input">${requestScope.roomX.retreatTime}</div>
                                    <br/>
                                    <br/>

                                    <label class="border-input">居住人数:</label><div readonly="readonly"  class="form-control border-input">${requestScope.roomX.rResidentNum}</div>
                                </div>

                                <div class="message3 ke">
                                    <label style="float:left;">门锁控制</label>
                                    <div id="onOffbj" style="display: none;">${requestScope.onOff}</div>
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
                                </div>
                                <%
                                    int i;
                                    List<UserInfo> users = (List<UserInfo>) request.getAttribute("users");
                                    for(i=0;i<users.size();i++)
                                    {
                                        UserInfo userInfo = users.get(i);
                                        int id = userInfo.getuId();//用户id
                                        String name = userInfo.getuName();
                                        String sexStr = userInfo.getSexStr();//性别
                                        String phone = userInfo.getuPhone();//手机号
                                        String rztime = userInfo.getuStayTime();//入住时间
                                        String idCard = userInfo.getuIdCard();//身份证
                                        int bj = userInfo.getuPrimaryUser();//是否是户主
                                %>
                                <%
                                    if(users.get(i).getuDelete()==0)
                                    {
                                %>
                                <div id="inuser_<%=id%>" class="message31 ke">
                                    <label class="border-input">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><div readonly="readonly" class="form-control"><%=name%></div>
                                    <br/>

                                    <label class="border-input">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label><div readonly="readonly"  class="form-control"><%=sexStr%></div>
                                    <br/>

                                    <label class="border-input">手&nbsp;&nbsp;机&nbsp;&nbsp;号：</label><div readonly="readonly"  class="form-contro2"><%=phone%></div>
                                    <br/>
                                    <label class="border-input">入住时间：</label><div readonly="readonly"  class="form-contro2"><%=rztime%></div>
                                    <br/>
                                    <label class="border-input">身份证号：</label><div readonly="readonly" class="form-contro2"><%=idCard%></div>
                                    <%if(bj==0)
                                    {%>
                                    <div class="butt butt2">
                                        <button style="cursor:pointer" class="bd" onclick="querengpopBox(<%=id%>)">退宿</button>

                                    </div>
                                    <%}%>
                                </div>
                                <%}else if(users.get(i).getuDelete()==1)
                                {
                                %>

                                <div id="inuser_<%=id%>" class="message32 ke">
                                    <label class="border-input">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</label><div readonly="readonly"  class="form-control"><%=name%></div>
                                    <br/>

                                    <label class="border-input">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</label><div readonly="readonly"  class="form-control"><%=sexStr%></div>
                                    <br/>

                                    <label class="border-input">手&nbsp;&nbsp;机&nbsp;&nbsp;号：</label><div readonly="readonly"  class="form-contro2"><%=phone%></div>
                                    <br/>
                                    <label class="border-input">入住时间：</label><div readonly="readonly"  class="form-contro2"><%=rztime%></div>
                                    <br/>
                                    <label class="border-input">身份证号：</label><div readonly="readonly" class="form-contro2"><%=idCard%></div>
                                </div>
                                <%
                                        }
                                    }
                                %>
                            </div>

                        </div>
                </div>
            </div>

        </div>
                </div>
    </div>

</div>

    <%--实--%>
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
            <a href="#">
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
            <div class="ke ys">
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
            <div class="ke ys">
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

<!--退宿确认 -->
<div id="querengBox" class="popBox ke">
    <!--退宿确认-->
    <div class="close">
        <a  href="javascript:void(0)" onclick="querengcloseBox()" class="iconfont">&#xe613;</a>
    </div>
    <div class="content">
        <h4>删除租客信息确认</h4>
        <br/>
        <div class="tsbai ke">
            <div id="scduserId" style="display: none;"></div>
            <%--<HR align=center width=100% color=#ccc SIZE=2>--%>
            <div style="height: 80px; display:flex;
             justify-content:center;
             align-items:center;">确定要删除租客信息?</div>
            <%--<HR align=center width=100% color=#ccc SIZE=2>--%>
        </div>
    </div>

    <div style="margin-top: 8px;">
        <div ><div class="right1 xq33" onclick="quit(${requestScope.roomX.rId})">确认</div><div onclick="querengcloseBox()"  class="right1 xq33">取消</div></div>
    </div>

</div>


<!--退房确认 -->
<div id="tfcloseBox" class="popBox ke">
    <!--退房确认-->
    <div class="close">
        <a  href="javascript:void(0)" onclick="tfcloseBox()" class="iconfont">&#xe613;</a>
    </div>
    <div class="content">
        <h4>退房确认</h4>
        <br/>
        <div class="tsbai ke">
            <div id="tfRoomId" style="display: none;"></div>
            <%--<HR align=center width=100% color=#ccc SIZE=2>--%>
            <div style="height: 80px; display:flex;
             justify-content:center;
             align-items:center;">确定要退房，并删除租客信息?</div>
            <%--<HR align=center width=100% color=#ccc SIZE=2>--%>
        </div>
    </div>

    <div style="margin-top: 8px;">
        <div ><div class="right1 xq33" onclick="checkOut()">确认</div><div onclick="tfcloseBox()"  class="right1 xq33">取消</div></div>
    </div>

</div>


<!--修改租金-->
<div id="popLayer"></div>
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



</body>
<script>

</script>
</html>
