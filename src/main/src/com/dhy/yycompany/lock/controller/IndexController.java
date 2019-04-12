package com.dhy.yycompany.lock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.Administrator;
import com.dhy.yycompany.lock.service.AdminService.AdministratorService;
import com.dhy.yycompany.lock.service.ApartmentService.ApartmentService;
import com.dhy.yycompany.lock.service.indexService.IndexService;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoService;
import com.dhy.yycompany.lock.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("lock")
public class IndexController {

    @Autowired
    private AdministratorService administratorServiceImpl;

    @Autowired
    private IndexService indexServiceImpl;

    @Autowired
    private RoomInfoService roomInfoServiceImpl;

    @Autowired
    private ApartmentService apartmentServiceImpl;

    @RequestMapping(value="index")
    public String loging(ModelMap mod)
    {
        System.out.println("index");

        HttpSession session = GetSessionUtil.getSession();
        int adminId = (int) session.getAttribute("adminId");
        //获得管理员信息
        JSON info = administratorServiceImpl.getInfo(adminId);
        String s = info.toJSONString();
        Administrator administrator=(Administrator) JSONObject.parseObject(s, Administrator.class);
        String adminName = administrator.getAdminName();//姓名
        String adminAvator = administrator.getAdminAvator();//头像地址
        int adminPermission = administrator.getAdminPermission();//权限

        List<Map<String, Object>> rooms = indexServiceImpl.getRooms();
        System.out.println(rooms.size());

        mod.addAttribute("rooms",rooms);

        mod.addAttribute("administrator",administrator);
        mod.addAttribute("adminName",adminName);
        mod.addAttribute("adminAvator",adminAvator);
        mod.addAttribute("adminPermission",adminPermission);

        return "index.jsp";
    }

    //读取左边
    @RequestMapping(value="left")
    @ResponseBody
    public JSON left()
    {
        System.out.println("left");
        List<Map<String, Object>> rooms = indexServiceImpl.getRooms();
        JSON  toJSON= (JSON) JSONArray.toJSON(rooms);
        System.out.println(rooms.size());

        return toJSON;
    }

    //获取个人信息
    @RequestMapping("myMessage")
    @ResponseBody
    public JSON getMyMessage()
    {
        HttpSession session = GetSessionUtil.getSession();
        int adminId = (int) session.getAttribute("adminId");
        JSON info = administratorServiceImpl.getInfo(adminId);
        return info;
    }

    //获取其他管理员信息
    @RequestMapping("otherMessage")
    @ResponseBody
    public JSON getotherMessage(@RequestParam("pageNum") int pageNum)
    {
        HttpSession session = GetSessionUtil.getSession();
        int adminId = (int) session.getAttribute("adminId");
        JSON info = administratorServiceImpl.getInfos(adminId,pageNum);
        return info;
    }

    //修改我的信息
    @RequestMapping("updateMyMessage")
    @ResponseBody
    public JSON updateMyMessage(@RequestParam("password") String password,@RequestParam("newName") String newName)
    {
        HttpSession session = GetSessionUtil.getSession();
        int adminId = (int) session.getAttribute("adminId");
        Map<String, Object> stringStringMap = administratorServiceImpl.modifyAdminInfo(adminId, password, newName);
        JSON info=null;
        info = administratorServiceImpl.getInfo(adminId);
        System.out.println(info);
        stringStringMap.put("content",info);
        JSON jsonObject = new JSONObject(stringStringMap);
        return jsonObject;
    }

    //删除其他管理员
    @RequestMapping("deleteOtherMessage")
    @ResponseBody
    public JSON deleteOtherMessage(@RequestParam("adminId") int adminId)
    {
        System.out.println(adminId);
        HttpSession session = GetSessionUtil.getSession();
        int myAdminId = (int) session.getAttribute("adminId");
        Map<String, Object> stringStringMap = administratorServiceImpl.deleteAdmin(adminId);
        JSON jsonObject = new JSONObject(stringStringMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //增加管理员
    @RequestMapping("addOtherMessage")
    @ResponseBody
    public JSON addOtherMessage(@RequestParam(value="img",required=false) MultipartFile img, @RequestParam("account") String account, @RequestParam("password") String password, @RequestParam("permission") int permission, @RequestParam("name") String name)
    {
        Administrator administrator = new Administrator();
        administrator.setAdminAvator("1.jpg");
        administrator.setAdminAccount(account);
        administrator.setAdminPassword(password);
        administrator.setAdminPermission(permission);
        administrator.setAdminName(name);
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        administrator.setAdminUuid(s);
        Map<String, Object> stringObjectMap = administratorServiceImpl.addInfo(administrator);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //增删房间卡片页面
    @RequestMapping("getRoomsNum")
    @ResponseBody
    public JSON getRoomsNum(@RequestParam("apartmentId") int apartmentId,@RequestParam("floor") int floor)
    {
        JSON roomsNum = roomInfoServiceImpl.getRoomsNum(apartmentId, floor);
        return roomsNum;
    }

    //新增房间
    @RequestMapping("addRoomsNum")
    @ResponseBody
    public JSON addRoomsNum(@RequestParam(value="rooms") String[] rooms,@RequestParam("apartmentId") int apartmentId,@RequestParam("floor") int floor)
    {
        int i=0;
        String typeName=rooms.getClass().getName();
        int length= typeName.lastIndexOf(".");
        String type =typeName.substring(length+1);
        System.out.println(type);
        System.out.println("============");
        while(i<rooms.length)
        {
            System.out.println(rooms[i]);
            i++;
        }
        System.out.println("============");
        Map<String, Object> stringObjectMap = roomInfoServiceImpl.addRoom(apartmentId, floor, rooms);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //删除房间
    @RequestMapping("deleteRoom")
    @ResponseBody
    public JSON deleteRoom(@RequestParam("roomId") int roomId)
    {
        Map<String, Object> stringObjectMap = roomInfoServiceImpl.deleteRoom(roomId);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //删除公寓楼
    @RequestMapping("deleteApartment")
    @ResponseBody
    public JSON deleteApartment(@RequestParam("apartmentId") int apartmentId)
    {
        Map<String, Object> stringStringMap = apartmentServiceImpl.deleteApartment(apartmentId);
        JSON jsonObject = new JSONObject(stringStringMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //修改楼名和楼层数
    @RequestMapping("updateApartmentNameAndFloor")
    @ResponseBody
    public JSON updateApartmentNameAndFloor(@RequestParam("apartmentId") int apartmentId,@RequestParam("apartmentName") String apartmentName,@RequestParam(value="floors") String[] floors)
    {
        System.out.println("updateApartmentNameAndFloor");
        Map<String, Object> map = new HashMap<>();

        map.put("updateFloor",-1);
        map.put("updateApartmentName",-1);
        System.out.println("apartmentName="+apartmentName);
        System.out.println("floorsSize="+floors.length);
        if(apartmentName!=null&&apartmentName!="") {
            System.out.println("1");
            Map<String, Object> stringObjectMap = apartmentServiceImpl.updateApartment(apartmentId, apartmentName);
            map.put("updateApartmentName",stringObjectMap);
        }
        if(floors!=null&&floors.length!=0) {
            System.out.println("2");
            Map<String, Object> stringObjectMap1 = apartmentServiceImpl.updateApartmentFloor(apartmentId, floors);
            map.put("updateFloor",stringObjectMap1);
        }



        JSON jsonObject = new JSONObject(map);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //删除楼层
    @RequestMapping("deleteFloor")
    @ResponseBody
    public JSON deleteFloor(@RequestParam("apartmentId") int apartmentId,@RequestParam("floor") int floor)
    {
        Map<String, Object> stringObjectMap = roomInfoServiceImpl.deleteFloor(apartmentId, floor);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }
}
