package com.dhy.yycompany.lock.controller;

import com.dhy.yycompany.lock.bean.Administrator;
import com.dhy.yycompany.lock.service.AdminService.AdministratorService;
import com.dhy.yycompany.lock.service.indexService.IndexService;
import com.dhy.yycompany.lock.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Administrator administrator = administratorServiceImpl.GetAdminMessage(adminId);
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
}
