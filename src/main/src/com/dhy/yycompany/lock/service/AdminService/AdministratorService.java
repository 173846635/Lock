package com.dhy.yycompany.lock.service.AdminService;

import com.alibaba.fastjson.JSON;
import com.dhy.yycompany.lock.bean.Administrator;

import java.util.Map;

public interface AdministratorService {

    //管理员生成密码

    /**
     * 门锁id，用户id，密码，次数（-1表示可以无限次数使用;，-2表示有失效时间），失效时间
     * @param lockId
     * @param userId
     * @param password
     * @param num
     * @param time
     * @return
     */
    Map<String, Object> addkey(int lockId,int userId,String password,int num,String time);
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
