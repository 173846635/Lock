package com.dhy.yycompany.lock.service.loginService;


import com.alibaba.fastjson.JSON;

import com.dhy.yycompany.lock.bean.AdminAndKey;
import com.dhy.yycompany.lock.bean.AdminAndKeyExample;
import com.dhy.yycompany.lock.bean.Administrator;
import com.dhy.yycompany.lock.bean.AdministratorExample;
import com.dhy.yycompany.lock.dao.AdminAndKeyMapper;
import com.dhy.yycompany.lock.dao.AdministratorMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /**
     * <p>登录方法</p>
     * <p>传入账号，密码</p>
     * <p>输出：</p>
     * <p>10=成功，21=账号不存在，22=密码错误，23=账号或密码为空</p>
     * @return
     */
    @Override
    public JSON login(String account,String password) {
        System.out.println(account+":::"+password);
        int code=0;
        int id=-1;
        int userId=-1;
        if(account==null||password==null)
        {
            code=23;
        }
        SqlSession openSession = sqlSessionFactory.openSession();
        AdminAndKeyMapper adminAndKeyMapper = openSession.getMapper(AdminAndKeyMapper.class);
        //System.out.println(borrowSellMapper);
        AdminAndKeyExample adminAndKeyExample = new AdminAndKeyExample();
        //System.out.println(borrowSellExample);
        AdminAndKeyExample.Criteria criteria = adminAndKeyExample.createCriteria();
        criteria.andAdminAccountEqualTo(account);
        criteria.andAdminDeleteEqualTo(0);
        List<AdminAndKey> adminAndKeys = adminAndKeyMapper.selectByExample(adminAndKeyExample);
        if(adminAndKeys.size()==0)
        {
            openSession.close();
            code= 21;
        }
        else{

            AdminAndKey adminAndKey = adminAndKeys.get(0);
            id = adminAndKey.getAdminId();
            userId=adminAndKey.getUserId();
            System.out.println("id="+id);
            //System.out.println(borrowSell.getPassword());
            if(!password.equals(adminAndKey.getAdminPassword()))
            {
                openSession.close();
                code= 22;
            }else
            {
                openSession.close();
                code= 10;
            }
        }
        String msg=null;
        if(code==10)
        {
            msg="登录成功";
        }else if(code==21)
        {
            msg="账号不存在";
        }
        else if(code==22)
        {
            msg="密码错误";
        }
        else if(code==23)
        {
            msg="账号或密码为空";
        }
        String jsStr = ("{\"code\":\""+code+"\",\"id\":\""+id+"\",\"msg\":\""+msg+"\",\"userId\":\""+userId+"\"}");
        JSON jsonObject = JSON.parseObject(jsStr);
        openSession.close();
        return jsonObject;
    }
}
