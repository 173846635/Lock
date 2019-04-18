package com.dhy.yycompany.lock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.RoomX;
import com.dhy.yycompany.lock.service.FingerService.FingerPrintService;
import com.dhy.yycompany.lock.service.UserService.UserService;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("lock")
public class VacantController {
    @Autowired
    RoomInfoService roomInfoServiceImpl;

    @Autowired
    UserService userServiceImpl;

    @Autowired
    FingerPrintService fingerPrintServiceImpl;

    @RequestMapping("vacant")
    public String vacant(@RequestParam("roomId") int roomId, ModelMap mod)
    {
        Map<String, Object> roomInfo = roomInfoServiceImpl.getRoomInfo(roomId);
        System.out.println("key");
        RoomX roomX = (RoomX) roomInfo.get("roomX");
        int onOff = roomX.getOnOff();
        System.out.println("roomX:"+roomX.toString());

        mod.addAttribute("roomX",roomInfo.get("roomX"));//房间信息
        return "vacant.jsp";
    }


    //入住
    @RequestMapping(value = {"check_in"})
    @ResponseBody
    public JSON checkIn(@RequestParam("json") String json)
    {
        System.out.println("入住");
        System.out.println(json);
        JSON jsonbean = JSON.parseObject(json);
        Map<String, Object> stringObjectMap = userServiceImpl.addHomeMaster(jsonbean);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println(jsonObject);
        return jsonObject;
    }

    //指纹luru
    @RequestMapping(value = {"fingerPrint"})
    @ResponseBody
    public JSON fingerPrint(@RequestParam("userId") int userId,@RequestParam("lockId") int lockId,@RequestParam("process") int process)
    {
        System.out.println("指纹录入");
        System.out.println("userId="+userId);
        System.out.println("lockId="+lockId);
        System.out.println("process="+process);
        Map<String, Object> stringObjectMap = fingerPrintServiceImpl.addFingerPrint(userId, lockId, process);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("返回");
        System.out.println(jsonObject);
        return jsonObject;
    }
}
