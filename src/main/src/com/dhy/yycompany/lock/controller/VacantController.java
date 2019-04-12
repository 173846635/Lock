package com.dhy.yycompany.lock.controller;

import com.alibaba.fastjson.JSON;
import com.dhy.yycompany.lock.bean.RoomX;
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
    public JSON checkIn(@RequestParam("roomId") int roomId)
    {
//        userServiceImpl.addHomeMaster
//        return lockMessage;
        return null;
    }
}
