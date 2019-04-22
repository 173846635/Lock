package com.dhy.yycompany.lock.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.Administrator;
import com.dhy.yycompany.lock.bean.RoomX;
import com.dhy.yycompany.lock.service.AdminService.AdministratorService;
import com.dhy.yycompany.lock.service.LockService.LockService;
import com.dhy.yycompany.lock.service.roomInfoService.RoomInfoService;
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
public class keyController {
    @Autowired
    RoomInfoService roomInfoServiceImpl;


    @Autowired
    LockService lockServiceImpl;

    @Autowired
    AdministratorService administratorServiceImpl;


    /**
     * 跳转到key页面
     * @return
     */
    @RequestMapping("information/key")
    public String toKey(@RequestParam("roomId") int roomId, ModelMap mod){
        try {
            HttpSession session = GetSessionUtil.getSession();
            int adminId = (int) session.getAttribute("adminId");
        }catch (Exception e)
        {
            return "redirect:/lock/hint ";
        }
        Map<String, Object> roomInfo = roomInfoServiceImpl.getRoomInfo(roomId);
        System.out.println("key");
        RoomX roomX = (RoomX) roomInfo.get("roomX");
        int onOff = roomX.getOnOff();
        System.out.println("roomX:"+roomX.toString());
//        System.out.println("--------------");
//        System.out.println("-----"+onOff+"------");
//        System.out.println("--------------");
//        System.out.println("users="+roomInfo.get("users").toString());
        mod.addAttribute("roomX",roomInfo.get("roomX"));//房间信息
        return "key.jsp";
    }

    //获得锁信息
    @RequestMapping(value = {"getLockMessage"})
    @ResponseBody
    public JSON getLockMessage(@RequestParam("lockId") int lockId)
    {
        JSON lockMessage = lockServiceImpl.getLockMessage(lockId);
        return lockMessage;
    }

    //获得本锁的所有密码
    @RequestMapping(value = {"getAllKey"})
    @ResponseBody
    public JSON getAllKey(@RequestParam("lockId") int lockId, @RequestParam("pageNum") int pageNum)
    {
        JSON allKey = lockServiceImpl.getAllKeyByLockId(lockId,pageNum);
        System.out.println(allKey);
        return allKey;
    }


    //获得本锁的所有开门记录
    @RequestMapping(value = {"getOpenRecord"})
    @ResponseBody
    public JSON getOpenRecord(@RequestParam("lockId") int lockId, @RequestParam("pageNum") int pageNum)
    {
        JSON allKey = lockServiceImpl.getOpenRecord(lockId,pageNum);
        System.out.println(allKey);
        return allKey;
    }


    //新建密码
    @RequestMapping(value = {"addKey"})
    @ResponseBody
    public JSON addKey(@RequestParam("lockId") int lockId, @RequestParam("pageNum") int pageNum, @RequestParam("password") String password,@RequestParam("yxnum") String num,@RequestParam("sxtime") String time)
    {

        HttpSession session = GetSessionUtil.getSession();

        int adminId = (int) session.getAttribute("adminId");
        System.out.println("num="+num);
        int i1 = num.indexOf(".");
        System.out.println("i1="+i1);
        int i =0;
        if(i1!=-1) {
            i = Integer.parseInt(num.substring(0, i1));
        }else {
            i =Integer.parseInt(num);
        }
        System.out.println("次数="+i);
        Map<String, Object> addkey = administratorServiceImpl.addkey(lockId, adminId, password, i, time);
        JSON jsonObject = new JSONObject(addkey);
        System.out.println("jsonObject="+jsonObject);
        return jsonObject;
    }



}
