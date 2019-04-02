package com.dhy.yycompany.lock.controller.loginController;

import com.alibaba.fastjson.JSON;

import com.dhy.yycompany.lock.service.indexService.IndexService;
import com.dhy.yycompany.lock.service.loginService.LoginService;
import com.dhy.yycompany.lock.utils.GetSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("lock")
@SessionAttributes(value={"administrator","store","storeId","nickname","username"})
public class LoginController {
    @Autowired
    private LoginService loginServiceImpl;

    @Autowired
    private IndexService indexServiceImpl;

    @RequestMapping(value="loginverify")
    @ResponseBody
    public JSON loginverify(@RequestParam("username")String username, @RequestParam("password") String password,ModelMap mod)
    {
        System.out.println("ppppp1");
        //System.out.println(username+password);
        JSON login = loginServiceImpl.login(username, password);
        Map<String,String> map5= (Map<String,String>)login;
        String code = map5.get("code");
        String sellId = map5.get("id");
        int i = Integer.parseInt(sellId);
        if(code.equals("10"))
        {
            System.out.println("登录成功");
            HttpSession session = GetSessionUtil.getSession();
            session.setAttribute("adminId", i);
        }
        //code为10登录成功
            return login;
    }




}
