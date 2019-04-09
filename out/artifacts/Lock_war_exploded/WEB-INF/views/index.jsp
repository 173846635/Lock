<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.dhy.yycompany.lock.bean.Room" %>
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
    <link rel="stylesheet" href="../../css/font.css">
    <link rel="stylesheet" href="../../css/index.css">
    <link rel="stylesheet" href="../../css/information.css">
    <link rel="stylesheet" href="../../css/tc.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/public.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../../js/index.js"></script>
    <script type="text/javascript" src="../../js/public.js"></script>
    <script type="text/javascript" src="../../js/tc.js"></script>
</head>

<body >


<!--主体-->
<div class="mair n_xia" style="min-height:100%;">

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
        <!--右边房间窗口-->
    <div class="house_status1 dw">
        <div class="right_window dw">
            <div class="card">
                <a class="ft_card ft" href="index.jsp">房态</a>
            </div>
            <div>
             <%
             List<Map<String, Object>> rooms = (List<Map<String, Object>>) request.getAttribute("rooms");
             for (int i=0;i<rooms.size();i++)
             {
                 Map<String, Object> stringObjectMap = rooms.get(i);
                 String apartmentID = (String) stringObjectMap.get("ApartmentID");//公寓id
                 String apartmentName = (String) stringObjectMap.get("ApartmentName");//公寓名称
                 String emptyRooms = (String) stringObjectMap.get("EmptyRooms");//空置房间数
                 Map<String, Object> rooms1 = (Map<String, Object>) stringObjectMap.get("Rooms");//楼层房间

             %>
                <div class="bean">
                    <div id="mao_<%=apartmentID%>" class="right_house">
                        <span id="ygy<%=apartmentID%>" class="right_house_number"><%=apartmentName%></span>
                        <div id="" style=" display: none;"></div>
                        <span class="right_house_number1">总空置:<%=emptyRooms%></span>
                    </div>
                    <div class="right_floors">
                    <%
                        //遍历map
                        Set set = rooms1.entrySet();
                        Iterator iterator = set.iterator();
                        String key=null;
                        List<Room> value =null;
                        //楼层循环
                        while (iterator.hasNext())
                        {
                            Map.Entry mapentry = (Map.Entry) iterator.next();
                            key = (String) mapentry.getKey();
                            value=(List<Room>)mapentry.getValue();
                            if(value.get(0)==null)
                            {
                                value=null;
                            }
                            System.out.println(value);

                    %>
                        <div  id="mao_<%=apartmentID%>_<%=key%>" class="right_floor">
                            <div class="right_floor_z">
                                <span><%=key%>楼</span>
                            </div>
                            <!--房间s-->
                            <div class="right_rooms">
                                <%
                                    if(value!=null)
                                    {
                                for(int z=0;z<value.size();z++)
                                {

                                    Room room = value.get(z);
                                    if(room.getrNum().equals("-1"))
                                    {
                                        continue;
                                    }
                                    System.out.println(room.toString());
                                    int id = room.getrId();//房id
                                    String roomNum = room.getrNum();//房号

                                %>
                                    <%
                                    if(room.getrResidentNum()!=0)
                                    {
                                    %>
                                    <a href="information?roomId=<%=id%>">
                                        <button class="finger right_room">
                                            <div class="right_room_number">
                                                <span><%=roomNum%></span>
                                            </div>
                                            <div class="right_room_icons">
                                                <i class="iconfont">&#xe650;</i>
                                            </div>
                                        </button>
                                    </a>
                                <%}else{
                                %>
                                    <a href="open?roomId=<%=id%>">
                                        <button class="finger finger right_room">
                                            <div class="right_room_number">
                                                <span><%=roomNum%></span>
                                            </div>
                                            <div class="right_room_icons">
                                                <i class="iconfont" style="color: rgba(255,255,255,0)">&#xe650; </i>
                                            </div>
                                        </button>
                                    </a>
                                <%
                                        }
                                    }
                                    }
                                %>
                                <button id="<%=apartmentID%>_<%=key%>" class="finger right_room" onclick="getRoomsNum(this.id)">
                                    <div class="right_room_number">
                                        <span style=" font-size: 10px;">增删房间</span>
                                    </div>
                                    <div class="right_room_icons">
                                        <i class="iconfont">&#xe659;</i>
                                    </div>
                                </button>

                                <!--分割线-->
                                <div  class="wire" style="height:1px;margin-top:6px;margin-bottom:6px;padding:1px;background-color:#ccc"></div>
                            </div>
                        </div>

                            <%

                            }
                        %>

                        <!--分割线-->
                        <div class="wire">
                        </div>
                    </div>
                </div>
                <HR align=center width=90% color=#ccc SIZE=2>
                <%
                    }
                %>
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
        <div class="table ke ys">
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
        <div class="table ke ys">
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

<!--增删房间弹窗-->
<div id="popLayer3"></div>
<div id="zsfjtc" class="popBox ke">
    <div id="gyId"  style="display: none;"></div>
    <div id="floor" style="display: none;"></div>
    <div class="close">
        <a  href="javascript:void(0)" onclick="zsfjcloseBox()" class="iconfont">&#xe613;</a>
    </div>
    <div  class="wj">
        <div class="bt"><h4>修改信息</h4></div>
        <HR align=center width=100% color=#ccc SIZE=2>
        <div id="zjfjDiv" class="nr1">

        </div>

    </div>
    <div class="xiamian">
        <div class="xian">
    <HR align=center width=100% color=#ccc SIZE=2>
        </div>
        <div>
            <div ><div onclick="addroom()"  class="right1 xq22">提交</div><div onclick="zsfjcloseBox()"  class="right1 xq22">取消</div></div>
        </div>
    </div>

</div>

</body>
</html>
