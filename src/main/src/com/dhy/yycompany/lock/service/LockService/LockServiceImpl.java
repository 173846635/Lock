package com.dhy.yycompany.lock.service.LockService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dhy.yycompany.lock.bean.*;
import com.dhy.yycompany.lock.dao.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class LockServiceImpl implements LockService{

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 获得门锁信息
     * @param lockId
     * @return
     */
    public JSON getLockMessage(int lockId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LockInfoMapper lockInfoMapper = sqlSession.getMapper(LockInfoMapper.class);
        LockInfo lockInfo = lockInfoMapper.selectByPrimaryKey(lockId);
        int status = lockInfo.getlStatus();
        if(status==1)
        {
            lockInfo.setlStatusStr("开");
        }else if(status==0)
        {
            lockInfo.setlStatusStr("关");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSON(lockInfo).toString());
        sqlSession.close();
        return jsonObject;
    }

    /**
     * 获得本门锁所有密码
     * @param lockId
     * @param pageNum
     * @return
     */
    public JSON getAllKeyByLockId(int lockId,int pageNum){
        System.out.println("=-=-=-=-=-=-=-=+"+lockId);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        KeyInfoMapper keyInfoMapper = sqlSession.getMapper(KeyInfoMapper.class);
        KeyInfoExample keyInfoExample = new KeyInfoExample();
        KeyInfoExample.Criteria criteria = keyInfoExample.createCriteria();
        criteria.andKLockIdEqualTo(lockId);
        keyInfoExample.setOrderByClause("k_delete ASC");
        PageHelper.startPage(pageNum, 8);
        List<KeyInfo> keyInfos = keyInfoMapper.selectByExample(keyInfoExample);
        if(keyInfos.size()==0)
        {
            return null;
        }
        System.out.println("keyInfos:"+keyInfos.size());
        List<Integer> users1 = new ArrayList<>();
        List<Integer> users0 = new ArrayList<>();
        for(int i=0;i<keyInfos.size();i++)
        {
            System.out.println("1");
            KeyInfo keyInfo = keyInfos.get(i);
            int userId = keyInfo.getkUserId();
            if(userId>0)
            {
                users1.add(userId);
            }else if(userId<0)
            {
                users0.add(userId);
            }

            int availableTimes = keyInfo.getkAvailableTimes();
            int delete = keyInfo.getkDelete();
            if(availableTimes==-1)//永久型
            {
                keyInfo.setType(1);
                keyInfo.setTypeStr("永久型");
                if(delete==0)
                {
                    keyInfo.setStatus(1);
                    keyInfo.setStatusStr("使用中");
                }
                else if(delete==1)
                {
                    keyInfo.setStatus(2);
                    keyInfo.setStatusStr("以失效");
                }
            }else if(availableTimes==-2)//时限型
            {
                keyInfo.setType(2);
                keyInfo.setTypeStr("时限型");
                if(delete==0)
                {
                    keyInfo.setStatus(1);
                    keyInfo.setStatusStr("时限内");
                }
                else if(delete==1)
                {
                    keyInfo.setStatus(2);
                    keyInfo.setStatusStr("以过期");
                }
            }else if(availableTimes>=0)//次限制
            {
                keyInfo.setType(0);
                keyInfo.setTypeStr("次限制");
                keyInfo.setStatus(0);
                keyInfo.setStatusStr("剩"+Integer.toString(availableTimes)+"次");

            }

        }
        List<UserInfo> userInfos =null;
        if(users1.size()!=0) {
            //查用户表
            UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
            UserInfoExample userInfoExample = new UserInfoExample();
            UserInfoExample.Criteria criteria1 = userInfoExample.createCriteria();
            System.out.println("users=" + users1.size());
            criteria1.andUIdIn(users1);
            userInfos = userInfoMapper.selectByExample(userInfoExample);
        }
        List<AdminAndKey> adminAndKeys=null;
        if(users0.size()!=0) {
            //查管理员表
            AdminAndKeyMapper adminAndKeyMapper = sqlSession.getMapper(AdminAndKeyMapper.class);
            AdminAndKeyExample adminAndKeyExample = new AdminAndKeyExample();
            AdminAndKeyExample.Criteria criteria2 = adminAndKeyExample.createCriteria();
            criteria2.andUserIdIn(users0);
            adminAndKeys = adminAndKeyMapper.selectByExample(adminAndKeyExample);
        }

        for (int y=0;y<keyInfos.size();y++)
        {
            if(keyInfos.get(y).getkUserId()>0) {
                for (int t = 0; t < userInfos.size(); t++) {
                    if (userInfos.get(t).getuId() == keyInfos.get(y).getkUserId()) {
                        keyInfos.get(y).setUserName(userInfos.get(t).getuName());
                    }
                }
            }else if(keyInfos.get(y).getkUserId()<0)
            {
                for (int t = 0; t < adminAndKeys.size(); t++) {
                    if (adminAndKeys.get(t).getUserId() == keyInfos.get(y).getkUserId()) {
                        keyInfos.get(y).setUserName(adminAndKeys.get(t).getAdminName()+"(管)");
                    }
                }
            }
        }

        Page<KeyInfo> listCountry = (Page<KeyInfo>) keyInfos;
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
        JSON toJSON = (JSON) JSONArray.toJSON(keyInfos);
        messages.put("keyInfos", toJSON);
        JSONObject json = new JSONObject(messages);
        System.out.println("000="+json);
        sqlSession.close();
        return json;
    }

    public JSON getOpenRecord(int lockId,int pageNum){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DailyRecordInfoMapper dailyRecordInfoMapper = sqlSession.getMapper(DailyRecordInfoMapper.class);
        DailyRecordInfoExample dailyRecordInfoExample = new DailyRecordInfoExample();
        DailyRecordInfoExample.Criteria criteria = dailyRecordInfoExample.createCriteria();
        criteria.andDLockIdEqualTo(lockId);
        PageHelper.startPage(pageNum, 8);
        List<DailyRecordInfo> dailyRecordInfos = dailyRecordInfoMapper.selectByExample(dailyRecordInfoExample);

        if(dailyRecordInfos.size()==0)
        {
            return null;
        }
        System.out.println("dailyRecordInfos:"+dailyRecordInfos.size());
        List<Integer> users1 = new ArrayList<>();
        List<Integer> users0 = new ArrayList<>();
        for(int i=0;i<dailyRecordInfos.size();i++)
        {
            System.out.println("1");
            DailyRecordInfo dailyRecordInfo = dailyRecordInfos.get(i);
            int userId = dailyRecordInfo.getdUserId();
            if(userId>0)
            {
                users1.add(userId);
                dailyRecordInfo.setIdentity("用户");
            }else if(userId<0)
            {
                users0.add(userId);
                dailyRecordInfo.setIdentity("管理员");
            }



        }
        List<UserInfo> userInfos =null;
        if(users1.size()!=0) {
            //查用户表
            UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
            UserInfoExample userInfoExample = new UserInfoExample();
            UserInfoExample.Criteria criteria1 = userInfoExample.createCriteria();
            System.out.println("users=" + users1.size());
            criteria1.andUIdIn(users1);
            userInfos = userInfoMapper.selectByExample(userInfoExample);
        }
        List<AdminAndKey> adminAndKeys=null;
        if(users0.size()!=0) {
            //查管理员表
            AdminAndKeyMapper adminAndKeyMapper = sqlSession.getMapper(AdminAndKeyMapper.class);
            AdminAndKeyExample adminAndKeyExample = new AdminAndKeyExample();
            AdminAndKeyExample.Criteria criteria2 = adminAndKeyExample.createCriteria();
            criteria2.andUserIdIn(users0);
            adminAndKeys = adminAndKeyMapper.selectByExample(adminAndKeyExample);
        }

        for (int y=0;y<dailyRecordInfos.size();y++)
        {
            if(dailyRecordInfos.get(y).getdUserId()>0) {
                for (int t = 0; t < userInfos.size(); t++) {
                    if (userInfos.get(t).getuId() == dailyRecordInfos.get(y).getdUserId()) {
                        dailyRecordInfos.get(y).setUserName(userInfos.get(t).getuName());
                        dailyRecordInfos.get(y).setPhone(userInfos.get(t).getuPhone());
                    }
                }
            }else if(dailyRecordInfos.get(y).getdUserId()<0)
            {
                for (int t = 0; t < adminAndKeys.size(); t++) {
                    if (adminAndKeys.get(t).getUserId() == dailyRecordInfos.get(y).getdUserId()) {
                        dailyRecordInfos.get(y).setUserName(adminAndKeys.get(t).getAdminName());
                        dailyRecordInfos.get(y).setPhone(adminAndKeys.get(t).getAdminPhone());
                    }
                }
            }
        }

        Page<DailyRecordInfo> listCountry = (Page<DailyRecordInfo>) dailyRecordInfos;
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
        JSON toJSON = (JSON) JSONArray.toJSON(dailyRecordInfos);
        messages.put("dailyRecordInfos", toJSON);
        JSONObject json = new JSONObject(messages);
        System.out.println("000="+json);
        sqlSession.close();
        return json;
    }
}
