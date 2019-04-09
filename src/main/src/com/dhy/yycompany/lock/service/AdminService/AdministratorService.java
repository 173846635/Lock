package com.dhy.yycompany.lock.service.AdminService;

import com.alibaba.fastjson.JSON;
import com.dhy.yycompany.lock.bean.Administrator;

import java.util.Map;

public interface AdministratorService {

    //管理员生成密码
    int addkey(Map<String, String> map);
    //修改管理员信息
    Map<String,Object> modifyAdminInfo(int adminID, String password, String newName);
    //获取单个管理员信息
    JSON getInfo(int adminID);
    //获取所有管理员信息
    JSON getInfos(int adminID, int pageNum);
    //删除管理员
    Map<String,Object> deleteAdmin(int adminID);
    //新增管理员
    Map<String,Object> addInfo(Administrator administrator);

}
