package com.dhy.yycompany.lock.service.AdminService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.*;
import com.dhy.yycompany.lock.dao.AdministratorMapper;
import com.dhy.yycompany.lock.dao.KeyAndAdminMapper;
import com.dhy.yycompany.lock.dao.KeyInfoMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Map<String, Object> addkey(int lockId,int adminId,String password,int num,String time) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        KeyAndAdminMapper keyAndAdminMapper = sqlSession.getMapper(KeyAndAdminMapper.class);
        KeyAndAdminExample keyAndAdminExample = new KeyAndAdminExample();
        KeyAndAdminExample.Criteria criteria = keyAndAdminExample.createCriteria();
        criteria.andAdminIdEqualTo(adminId);
        List<KeyAndAdmin> keyAndAdmins = keyAndAdminMapper.selectByExample(keyAndAdminExample);
        Map<String, Object> map=new HashMap<>();
        if(keyAndAdmins.size()==0)
        {
            map.put("result", 1);
            map.put("message", "添加密码失败");
            return map;
        }
        KeyAndAdmin keyAndAdmin = keyAndAdmins.get(0);
        int userId = keyAndAdmin.getUserId();
        System.out.println("添加密码的userId="+userId);
        KeyInfo keyInfo=new KeyInfo();
        keyInfo.setkUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        keyInfo.setkLockId(lockId);
        keyInfo.setkUserId(userId);
        keyInfo.setkPassword(password);
        keyInfo.setkAvailableTimes(num);
        if(!time.equals("0")) {
            keyInfo.setkFailureTime(time);
        }
        keyInfo.setkIsModify(1);
        keyInfo.setkDelete(0);

        KeyInfoMapper keyInfoMapper=sqlSession.getMapper(KeyInfoMapper.class);
        int num1=keyInfoMapper.insert(keyInfo);
        sqlSession.close();
        if(num1==1){
            System.out.println("添加密码成功");
            map.put("result", 0);
            map.put("message", "添加密码成功");
            return map;
        }else{
            System.out.println("添加密码失败");
            map.put("result", 2);
            map.put("message", "添加密码失败");
            return map;
        }
    }

    @Override
    public Map<String, Object> modifyAdminInfo(int adminID, String password, String newName) {
        System.out.println("password:"+password);
        System.out.println("newName:"+newName);
        if(password==null||newName==null||password==""||newName=="")
        {
            Map<String, Object> map=new HashMap<>();
            map.put("result", 0);
            map.put("message", "修改管理员失败");
            return map;
        }
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AdministratorMapper administratorMapper=sqlSession.getMapper(AdministratorMapper.class);
        Administrator administrator=new Administrator();
        administrator.setAdminId(adminID);
        administrator.setAdminPassword(password);
        administrator.setAdminName(newName);
        int num=administratorMapper.updateByPrimaryKeySelective(administrator);
        System.out.println("修改了管理员"+num+"条信息");
        Map<String, Object> map=new HashMap<>();
        if(num==1){
            map.put("result", 0);
            map.put("message", "修改管理员成功");
        }else{
            map.put("result", 1);
            map.put("message", "修改管理员失败");
        }
        sqlSession.close();
        return map;
    }

    @Override
    public JSON getInfo(int adminID) {
        AdministratorExample administratorExample=new AdministratorExample();
        AdministratorExample.Criteria criteria=administratorExample.createCriteria();
        criteria.andAdminIdEqualTo(adminID);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AdministratorMapper administratorMapper=sqlSession.getMapper(AdministratorMapper.class);
        Administrator administrator=administratorMapper.selectByPrimaryKey(adminID);
        System.out.println("administrator="+administrator);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(administrator).toString());
        System.out.println("000="+jsonObject);
        sqlSession.close();
        return jsonObject;
    }

    @Override
    public JSON getInfos(int adminID, int pageNum) {
        AdministratorExample administratorExample=new AdministratorExample();
        AdministratorExample.Criteria criteria=administratorExample.createCriteria();
        criteria.andAdminIdNotEqualTo(adminID);
        criteria.andAdminDeleteEqualTo(0);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        PageHelper.startPage(pageNum, 8);
        AdministratorMapper administratorMapper=sqlSession.getMapper(AdministratorMapper.class);
        List<Administrator> administratorList=administratorMapper.selectByExample(administratorExample);
        System.out.println("administratorList="+administratorList);


        Page<Administrator> listCountry = (Page<Administrator>) administratorList;
        int pages = listCountry.getPages();//administratorList
        int pageNum1 = listCountry.getPageNum();//当前页
        long total = listCountry.getTotal();//总数据数
        System.out.println(listCountry.getTotal());
        System.out.println(listCountry.toString());
        HashMap<String, Object> messages = new HashMap<>();
        HashMap<String, Object> pageHelperMessage = new HashMap<>();
        pageHelperMessage.put("pageNum", pageNum1);
        pageHelperMessage.put("pages", pages);
        pageHelperMessage.put("total", total);
        messages.put("pageHelperMessage", pageHelperMessage);
        JSON toJSON = (JSON) JSONArray.toJSON(administratorList);
        messages.put("administratorList", toJSON);
        JSONObject json = new JSONObject(messages);
        System.out.println("000="+json);
        sqlSession.close();
        return json;
    }

    @Override
    public Map<String,Object> deleteAdmin(int adminID) {
        Administrator administrator=new Administrator();
        administrator.setAdminId(adminID);
        administrator.setAdminDelete(1);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        AdministratorMapper administratorMapper=sqlSession.getMapper(AdministratorMapper.class);
        //把administrator表中的delete置1
        int num=administratorMapper.updateByPrimaryKeySelective(administrator);
        Map<String, Object> map = new HashMap<>();
        if(num==1){
            //管理员删除成功
            KeyAndAdminExample keyAndAdminExample=new KeyAndAdminExample();
            KeyAndAdminExample.Criteria criteria=keyAndAdminExample.createCriteria();
            criteria.andAdminIdEqualTo(adminID);
            KeyAndAdminMapper keyAndAdminMapper=sqlSession.getMapper(KeyAndAdminMapper.class);
            List<KeyAndAdmin> keyAndAdminList = keyAndAdminMapper.selectByExample(keyAndAdminExample);
            System.out.println("keyAndAdminList="+keyAndAdminList);
            if(keyAndAdminList!=null && keyAndAdminList.size()!=0){
                KeyInfoExample keyInfoExample=new KeyInfoExample();
                KeyInfoExample.Criteria criteria1=keyInfoExample.createCriteria();
                System.out.println("userid="+keyAndAdminList.get(0).getUserId());
                criteria1.andKUserIdEqualTo(keyAndAdminList.get(0).getUserId());
                KeyInfo keyinfo=new KeyInfo();
                keyinfo.setkDelete(1);
                KeyInfoMapper keyInfoMapper=sqlSession.getMapper(KeyInfoMapper.class);
                int num1=keyInfoMapper.updateByExampleSelective(keyinfo,keyInfoExample);
                System.out.println("num1="+num1);
                if(num1>0){
                    //删除管理员创建的密码成功
                    System.out.println("删除管理员创建的密码成功");
                    map.put("result", 0);
                    map.put("message", "删除管理员成功");
                }
            }else if(keyAndAdminList.size()==0){
                //管理员秘钥删除失败
                System.out.println("无管理员创建的密码");
                map.put("result", 0);
                map.put("message", "删除管理员成功");
            }else{
                //管理员秘钥删除失败
                System.out.println("管理员秘钥删除失败");
                map.put("result", 2);
                map.put("message", "删除管理员成功，但其创建的秘钥删除失败");
            }
        }else{
            //管理员删除失败
            System.out.println("管理员删除失败");
            map.put("result", 1);
            map.put("message", "删除管理员失败");
        }
        sqlSession.close();
        return map;
    }

    public Map<String,Object> addInfo(Administrator administrator)
    {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdministratorMapper administratorMapper = sqlSession.getMapper(AdministratorMapper.class);
        AdministratorExample administratorExample = new AdministratorExample();
        AdministratorExample.Criteria criteria = administratorExample.createCriteria();
        criteria.andAdminAccountEqualTo(administrator.getAdminAccount());
        List<Administrator> administrators = administratorMapper.selectByExample(administratorExample);
        Map<String, Object> map = new HashMap<>();
        if(administrators.size()==0)
        {
            int i = administratorMapper.insertSelective(administrator);
            if(i!=0)
            {
                KeyAndAdminMapper keyAndAdminMapper = sqlSession.getMapper(KeyAndAdminMapper.class);
                KeyAndAdmin keyAndAdmin = new KeyAndAdmin();
                keyAndAdmin.setAdminId(administrator.getAdminId());
                keyAndAdmin.setUserId((-(administrator.getAdminId())));
                int i1 = keyAndAdminMapper.insertSelective(keyAndAdmin);
                if(i1==1) {
                    System.out.println("创建成功");
                    map.put("result", 1);
                    map.put("message", "创建成功");
                }else if(i1==0)
                {
                    System.out.println("创建失败");
                    map.put("result", 4);
                    map.put("message", "创建失败");
                }
            }
            else {
                System.out.println("创建失败");
                map.put("result", 3);
                map.put("message", "创建失败");
            }
        }
        else {
            System.out.println("账号存在");
            map.put("result", 2);
            map.put("message", "创建失败，账号以存在");
        }
        sqlSession.close();
        return map;
    }
}
