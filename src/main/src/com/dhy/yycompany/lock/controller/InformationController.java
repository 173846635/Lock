package com.dhy.yycompany.lock.controller;

import com.dhy.yycompany.lock.bean.Room;
import com.dhy.yycompany.lock.bean.RoomX;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("lock")
public class InformationController {

    @Autowired
    RoomInfoService roomInfoServiceImpl;

    /**
     * 进入以居住房间页面
     * @param roomId
     * @param mod
     * @return
     */
    @RequestMapping("information")
    public String gotoinformation(@RequestParam("roomId") int roomId, ModelMap mod)
    {
        System.out.println(roomId);
        Map<String, Object> roomInfo = roomInfoServiceImpl.getRoomInfo(roomId);
        System.out.println("information2");
        RoomX roomX = (RoomX) roomInfo.get("roomX");
        int onOff = roomX.getOnOff();
        System.out.println("onOff:"+onOff);
        String kg=null;
        if(onOff==1)
        {
            kg="checked";
        }
        mod.addAttribute("roomX",roomInfo.get("roomX"));//房间信息
        mod.addAttribute("users",roomInfo.get("users"));//用户信息
        mod.addAttribute("onOff",kg);//门锁开关
        return "information.jsp";

    }
}
