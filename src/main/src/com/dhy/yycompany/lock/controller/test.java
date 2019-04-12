package com.dhy.yycompany.lock.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("lock")
public class test {
    @RequestMapping("test")
    public String test()
    {
        String path = System.getProperty("evan.webapp");
        System.out.println(path);
        return "test.html";
    }
}
