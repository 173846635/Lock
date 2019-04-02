package com.dhy.yycompany.lock.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.Administrator;
import com.dhy.yycompany.lock.service.AdminService.AdministratorService;
import com.dhy.yycompany.lock.service.indexService.IndexService;
import com.dhy.yycompany.lock.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("lock")
public class indexController {

    @Autowired
    private AdministratorService administratorServiceImpl;

    @Autowired
    private IndexService indexServiceImpl;

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
}
