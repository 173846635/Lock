package com.dhy.yycompany.lock.service.roomInfoService;

import com.dhy.yycompany.lock.bean.*;
import com.dhy.yycompany.lock.dao.*;
import com.dhy.yycompany.lock.utils.AllChange;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomInfoServiceImpl implements RoomInfoService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 1.通过room_id找到任意一位住户，可以得到租住时间。
     * 2.在room表中获得居住人数
     * 3.通过room_id找到lock_id，然后获得门锁状态（开或关）
     * 4.根据room_id找到所有住户
     */
    @Override
    public Map<String, Object> getRoomInfo(int roomId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomXMapper roomXMapper = sqlSession.getMapper(RoomXMapper.class);
        RoomXExample roomXExample = new RoomXExample();
        RoomXExample.Criteria criteria = roomXExample.createCriteria();
        criteria.andRIdEqualTo(roomId);
        List<RoomX> roomXES = roomXMapper.selectByExample(roomXExample);
        RoomX roomX =null;
        if(roomXES.size()==1) {
            roomX = roomXES.get(0);
            int apartmentId = roomX.getrApartmentId();
            ApartmentMapper apartmentMapper = sqlSession.getMapper(ApartmentMapper.class);
            Apartment apartment = apartmentMapper.selectByPrimaryKey(apartmentId);
            roomX.setrApartmentName(apartment.getaName());

            int integer = roomX.getrLockId();
            LockInfoMapper lockInfoMapper = sqlSession.getMapper(LockInfoMapper.class);
            LockInfo lockInfo = lockInfoMapper.selectByPrimaryKey(integer);
            roomX.setOnOff(lockInfo.getlStatus());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("roomX",roomX);

        UserInfoExample userInfoExample=new UserInfoExample();
        UserInfoExample.Criteria criteria1=userInfoExample.createCriteria();
        criteria1.andURoomIdEqualTo(roomId);
        UserInfoMapper userInfoMapper=sqlSession.getMapper(UserInfoMapper.class);
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
        AllChange.changeSex(userInfoList);
        System.out.println("-----------------------");
        for (UserInfo u: userInfoList){
            System.out.println(u);
        }
        map.put("users",userInfoList);
        System.out.println("map=="+map);
        sqlSession.close();
        return map;
    }


    public Room getRoomMessage(int roomId){
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);
        Room room = roomMapper.selectByPrimaryKey(roomId);
        int integer = room.getrApartmentId();
        ApartmentMapper apartmentMapper = sqlSession.getMapper(ApartmentMapper.class);
        Apartment apartment = apartmentMapper.selectByPrimaryKey(integer);

        room.setrApartmentName(apartment.getaName());
        return room;
    }

    /**
     * 1。lock_id查出lock_info表的信息
     * 2。lock_id 查出v_key_username视图中的信息
     * 3。lock_id 查出v_daily_userinfo视图中的信息
     *
     *
     * map对象的信息
     *      * lockID:门锁id
     *      * software:软件版本
     *      * hardware:硬件版本
     *      * status:门锁状态
     *      * keyList:密码bean的list
     *      * openlist:开门bean的list
     * @param lock_id
     * @return
     */
    @Override
    public Map<String, Object> getLockInfo(int lock_id) {
        LockInfoExample lockInfoExample=new LockInfoExample();
        LockInfoExample.Criteria criteria=lockInfoExample.createCriteria();
        criteria.andLIdEqualTo(lock_id);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        LockInfoMapper lockInfoMapper=sqlSession.getMapper(LockInfoMapper.class);
        List<LockInfo> lockInfoList=lockInfoMapper.selectByExample(lockInfoExample);
        System.out.println("lockInfoList="+lockInfoList);

        VKeyUsernameExample vKeyUsernameExample=new VKeyUsernameExample();
        VKeyUsernameExample.Criteria criteria1=vKeyUsernameExample.createCriteria();
        criteria1.andKLockIdEqualTo(lock_id);
        VKeyUsernameMapper vKeyUsernameMapper=sqlSession.getMapper(VKeyUsernameMapper.class);
        List<VKeyUsername> vKeyUsernameList=vKeyUsernameMapper.selectByExample(vKeyUsernameExample);
        System.out.println("vKeyUsernameList"+vKeyUsernameList);

        VDailyUserInfoExample vDailyUserInfoExample=new VDailyUserInfoExample();
        VDailyUserInfoExample.Criteria criteria2=vDailyUserInfoExample.createCriteria();
        criteria2.andDLockIdEqualTo(lock_id);
        VDailyUserInfoMapper vDailyUserInfoMapper=sqlSession.getMapper(VDailyUserInfoMapper.class);
        List<VDailyUserInfo> vDailyUserInfoList=vDailyUserInfoMapper.selectByExample(vDailyUserInfoExample);
        System.out.println("vDailyUserInfoList"+vDailyUserInfoList);


        Map<String,Object> map=new HashMap<>();

        map.put("LockID",lockInfoList.get(0).getlId());
        map.put("software",lockInfoList.get(0).getlSoftVer());
        map.put("hardware",lockInfoList.get(0).getlHardVer());
        map.put("status",lockInfoList.get(0).getlStatus());
        map.put("keyList",vKeyUsernameList);
        map.put("openlist",vDailyUserInfoList);

        System.out.println("map="+map);

        sqlSession.commit();
        sqlSession.close();
        return null;
    }

    @Override
    public void deleteRoom(int roomID) {

    }

    @Override
    public Map<String, String> addRoom(int roomNum) {

        return null;
    }
}
