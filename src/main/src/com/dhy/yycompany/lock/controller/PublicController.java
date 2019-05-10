package com.dhy.yycompany.lock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.service.LockService.LockService;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoService;
import com.dhy.yycompany.lock.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("lock")
public class PublicController {

    @Autowired
    LockService lockServiceImpl;

    @Autowired
    private RoomInfoService roomInfoServiceImpl;



    //开门
    @RequestMapping(value = {"open"})
    @ResponseBody
    public JSON open(@RequestParam(value="lockId") int lockId)
    {
        HttpSession session = GetSessionUtil.getSession();
        int userId = (int) session.getAttribute("userId");
        Map<String, Object> stringObjectMap = roomInfoServiceImpl.openDoor(lockId, userId);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }

    //门锁绑定
    @RequestMapping(value = {"binding"})
    @ResponseBody
    public JSON binding(@RequestParam("roomId") int roomId,@RequestParam("introduction") String introduction)
    {
        Map<String, Object> stringObjectMap = roomInfoServiceImpl.bindRoomLock(roomId, introduction);
        JSON jsonObject = new JSONObject(stringObjectMap);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }


    //查询是否以登录
    @RequestMapping(value = {"account"})
    @ResponseBody
    public JSON account()
    {
        HttpSession session = GetSessionUtil.getSession();
        Map<String, Object> map = new HashMap<>();

        try {
            int userId = (int) session.getAttribute("userId");
        }catch (Exception e)
        {
            System.out.println("未登录");
            map.put("result",-1);
            JSON jsonObject = new JSONObject(map);
            return jsonObject;
        }
        System.out.println("已登录");
        map.put("result",0);
        JSON jsonObject = new JSONObject(map);
        return jsonObject;
    }

    @RequestMapping("hint")
    public String toKey(){
        return "hint.html";
    }

    //查询是否以登录
    @RequestMapping(value = {"lockoff"})
    @ResponseBody
    public JSON lockoff(@RequestParam("lockId") int lockId)
    {
        JSON lockStatus = lockServiceImpl.getLockStatus(lockId);
        return lockStatus;
    }
}
