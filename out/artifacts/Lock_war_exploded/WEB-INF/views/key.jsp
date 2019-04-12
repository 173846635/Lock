<%@ page import="com.dhy.yycompany.lock.bean.UserInfo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>

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
    <link rel="stylesheet" href="../../css/key.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/public.css">
    <link rel="stylesheet" href="../../css/tc.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="../../js/openjia.js"></script>
    <script type="text/javascript" src="../../js/index.js"></script>
    <script type="text/javascript" src="../../js/num-alignment.js"></script>
    <script type="text/javascript" src="../../js/key.js"></script>
    <script type="text/javascript" src="../../js/public.js"></script>
    <script type="text/javascript" src="../../js/tc.js"></script>

</head>

<body>


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
    <!--右边房间窗口-->
    <div class="house_status1 dw">
        <div class="right_window dw">
            <div class="card">
                <a class="ft_card ft" href="../index">房态</a><span  class="ft"> > </span><a class="ft_card ft"  href="../information?roomId=${requestScope.roomX.rId}">${requestScope.roomX.rApartmentName}-${requestScope.roomX.rFloor}楼-${requestScope.roomX.rNum}</a><span class="ft"> > </span><a class="ft_card ft" href="key?roomId=${requestScope.roomId}">门锁状态</a>
            </div>
            <div class="information">
                <div class="information_t">
                    <div class="room_name">${requestScope.roomX.rApartmentName}-${requestScope.roomX.rFloor}楼-${requestScope.roomX.rNum}<span id="zt" class="zt"></span></div>
                    <div class="message2 ">
                        <div class="message3s">
                            <div class="message3 ke">
                                <div id="rlockId0" style="display: none;">${requestScope.roomX.rLockId}</div>
                                <label style="color: #2D2C3B" class="border-input">设&nbsp;&nbsp;备&nbsp;&nbsp;id:</label> <div style="margin-left: 80px"  id="lockId1"></div>
                                <br>
                                <label style="color: #2D2C3B" class="border-input">软件版本:</label> <div style="margin-left: 80px" id="softVer1"></div>
                                <br>
                                <label style="color: #2D2C3B" class="border-input">硬件版本:</label> <div style="margin-left: 80px" id="hardVer1"></div>
                                <br>
                                <label style="color: #2D2C3B"  class="border-input">门锁状态:</label><div style="margin-left: 80px" id="lockStatus"></div>
                            </div>

                            <div class="mmgl"><div class="kmjl mm">密码管理<span id="xjPassword" class="xin" onclick="xjmmpopBox()">新&nbsp;建</span></div></div>
                            <div  class="message31 ke">
                                <table class="table table-striped">
                                    <thead>
                                    <th>密码</th>
                                    <th>持有者</th>
                                    <th>密码类型</th>
                                    <th>密码状态</th>
                                    </thead>
                                    <tbody id="neirong_mm">


                                    </tbody>
                                </table>

                                <div id="jihao_mm" style="display: none;"></div>
                                <nav  class="fenye navs" aria-label="Page navigation">
                                    <ul class="col-md-offset-4 pagination">
                                        <li><a name="shouye_mm" onclick="getAllKey(this.name)">首&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
                                        <li><a name="shang_mm" onclick="getAllKey(this.name)">上一页</a></li>
                                        <li><a name="xia_mm" onclick="getAllKey(this.name)">下一页</a></li>
                                        <li><a name="wei_mm" onclick="getAllKey(this.name)">尾&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>

                                    </ul>

                                </nav>
                                <div id="page_mm" class="zxc clearfix"></div>
                            </div>

                            <div class="kmjl">开门记录</div>
                            <div  class="message31 ke">
                                <table class="table table-striped">
                                    <thead>
                                    <th>开&nbsp;&nbsp;门&nbsp;&nbsp;者</th>
                                    <th>&nbsp;&nbsp;身&nbsp;&nbsp;&nbsp;&nbsp;份&nbsp;&nbsp;</th>
                                    <th>联系方式</th>
                                    <th>操作时间</th>

                                    </thead>
                                    <tbody id="operating_srecord">
                                    </tbody>
                                </table>

                                <div id="jihao_jl" style="display: none;"></div>
                                <nav  class="fenye navs" aria-label="Page navigation">
                                    <ul class="col-md-offset-4 pagination">
                                        <li><a  name="shouye_jl" class="shouye_jl" onclick="getOpenRecord(this.name)">首&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>
                                        <li><a  name="shang_jl" class="shang_jl" onclick="getOpenRecord(this.name)">上一页</a></li>
                                        <li><a  name="xia_jl" class="xia_jl" onclick="getOpenRecord(this.name)">下一页</a></li>
                                        <li><a  name="wei_jl" class="wei_jl" onclick="getOpenRecord(this.name)">尾&nbsp;&nbsp;&nbsp;&nbsp;页</a></li>

                                    </ul>

                                </nav>
                                <div id="page_jl" class="zxc clearfix"></div>
                            </div>
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

<!--新建开门密码-->
<div id="xjmm" class="popBox ke">
    <div class="close">
        <a  href="javascript:void(0)" onclick="xjmmcloseBox()" class="gb iconfont">&#xe613;</a>
    </div>
    <div class="content">
        <h4>新建密码</h4>

        <label>密码：</label><input  id="password" class="form-control" type="text">
        <br/>
        <br/>
        <label>密码性质：</label><label><input name="Fruit" onclick="radio1(1)" class="radio1" type="radio" value="1" />时间型 </label>
        <label><input class="radio1" name="Fruit" onclick="radio1(2)" type="radio" value="2" />次数型 </label>
        <br/>
        <br/>
        <div id="timeX" style=" display: none;">
            <label>密码失效时间</label><br/><input id="sxTime" type="datetime-local"  class="form-control" >
        </div>
        <div id="numberX" class="number" style=" display: none;">
            <label>密码有效次数</label>
            <input id="yxnum" class="alignment" data-edit="true" value="1"/><br><br>
        </div>
    </div>
    <div class="gb"><div id="b1" onclick="xjmm()" class="bd xyb finger">确认</div></div>

</div>

</body>
</html>