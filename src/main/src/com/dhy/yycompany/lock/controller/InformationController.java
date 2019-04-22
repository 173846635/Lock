package com.dhy.yycompany.lock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.Room;
import com.dhy.yycompany.lock.bean.RoomX;
import com.dhy.yycompany.lock.service.UserService.UserService;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoService;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoServiceImpl;
import com.dhy.yycompany.lock.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("lock")
public class InformationController {

    @Autowired
    RoomInfoService roomInfoServiceImpl;

    @Autowired
    UserService userServiceImpl;

    /**
     * 进入已居住房间页面
     * @param roomId
     * @param mod
     * @return
     */
    @RequestMapping("information")
    public String gotoinformation(@RequestParam("roomId") int roomId, ModelMap mod)
    {
        try {
            HttpSession session = GetSessionUtil.getSession();
            int adminId = (int) session.getAttribute("adminId");
        }catch (Exception e)
        {
            return "redirect:/lock/hint ";
        }
        System.out.println(roomId);
        Map<String, Object> roomInfo = roomInfoServiceImpl.getRoomInfo(roomId);
        System.out.println("information2");
        RoomX roomX = (RoomX) roomInfo.get("roomX");
        int onOff = roomX.getOnOff();
        System.out.println("onOff:"+onOff);
        System.out.println("--------------");
        System.out.println("-----"+onOff+"------");
        System.out.println("--------------");
        System.out.println("users="+roomInfo.get("users").toString());
        mod.addAttribute("roomX",roomInfo.get("roomX"));//房间信息
        mod.addAttribute("users",roomInfo.get("users"));//用户信息
        mod.addAttribute("onOff",onOff);//门锁开关
        return "information.jsp";

    }

    //删除用户
    @RequestMapping(value = {"quit"})
    @ResponseBody
    public JSON quit(@RequestParam("roomId") int roomId,@RequestParam("userId")int userId)
    {
        Map<String, Object> stringObjectMap = userServiceImpl.deleteUser(roomId, userId);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //修改租金
    @RequestMapping("changeRent")
    @ResponseBody
    public JSON changeRent(@RequestParam("roomId") int roomId,@RequestParam("price")int price)
    {
        Map<String, Object> stringStringMap = roomInfoServiceImpl.modifyPrice(roomId, price);
        JSON jsonObject = new JSONObject(stringStringMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //退房
    @RequestMapping("checkOut")
    @ResponseBody
    public JSON checkOut(@RequestParam("roomId") int roomId)
    {
        Map<String, Object> stringObjectMap = userServiceImpl.deleteAllUsers(roomId);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

//    //开门
//    @RequestMapping("open")
//    @ResponseBody
//    public JSON open(@RequestParam("roomId") int roomId)
//    {
//        Map<String, Object> stringObjectMap = userServiceImpl.deleteAllUsers(roomId);
//        JSON jsonObject = new JSONObject(stringObjectMap);
//        System.out.println("jsonObject="+jsonObject);
//        return jsonObject;
//    }


}
