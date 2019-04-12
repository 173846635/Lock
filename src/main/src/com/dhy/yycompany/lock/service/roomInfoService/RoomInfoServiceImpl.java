package com.dhy.yycompany.lock.service.roomInfoService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dhy.yycompany.lock.bean.*;
import com.dhy.yycompany.lock.dao.*;
import com.dhy.yycompany.lock.utils.AllChange;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        criteria.andRDeleteNotEqualTo(1);
        List<RoomX> roomXES = roomXMapper.selectByExample(roomXExample);
        RoomX roomX =null;
        if(roomXES.size()==1) {
            roomX = roomXES.get(0);
            int apartmentId = roomX.getrApartmentId();
            ApartmentMapper apartmentMapper = sqlSession.getMapper(ApartmentMapper.class);
            Apartment apartment = apartmentMapper.selectByPrimaryKey(apartmentId);
            roomX.setrApartmentName(apartment.getaName());

            int integer = roomX.getrLockId();
            System.out.println("lock-------------------"+integer);
            if(integer!=0) {
                LockInfoMapper lockInfoMapper = sqlSession.getMapper(LockInfoMapper.class);
                LockInfo lockInfo = lockInfoMapper.selectByPrimaryKey(integer);
                roomX.setOnOff(lockInfo.getlStatus());
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("roomX",roomX);

        UserInfoExample userInfoExample=new UserInfoExample();
        UserInfoExample.Criteria criteria1=userInfoExample.createCriteria();
        criteria1.andURoomIdEqualTo(roomId);
        criteria1.andUDeleteNotEqualTo(1);
        UserInfoMapper userInfoMapper=sqlSession.getMapper(UserInfoMapper.class);
        userInfoExample.setOrderByClause("u_delete asc,u_primary_user desc");
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
    public Map<String, Object> deleteRoom(int roomID) {
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andRIdEqualTo(roomID);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);
        List<Room> roomList = roomMapper.selectByExample(roomExample);
        Map<String, Object> map = new HashMap<>();
        if (roomList.get(0).getrResidentNum() == 0) {
            //没有住户，可以删除房间
            Room room = new Room();
            room.setrId(roomID);
            room.setrDelete(1);
            int num = roomMapper.updateByPrimaryKeySelective(room);
            if (num == 1) {
                map.put("result", 0);
                map.put("message", "删除房间成功");
            }
        } else {
            //存在用户，不能删除房间
            map.put("result", 1);
            map.put("message", "房间存在用户，不能删除");
        }
        System.out.println(map);
        sqlSession.close();
        return map;
    }

    @Override
    public Map<String, Object> addRoom(int apartmentID,int floor, String[] roomNums) {
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        List<String> list = Arrays.asList(roomNums);
        criteria.andRNumIn(list);
        criteria.andRDeleteEqualTo(0);
        criteria.andRApartmentIdEqualTo(apartmentID);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);
        List<Room> roomList = roomMapper.selectByExample(roomExample);
        JSON  toJSON= (JSON) JSONArray.toJSON(roomList); // List转json
        Map<String, Object> map = new HashMap<>();
        if (roomList.size() != 0) {
            //该房间号存在
            map.put("result", 2);
            map.put("message", "房间已经存在");
            map.put("repetitionRooms",toJSON);//重复房间
        } else {
            //房间号不存在
            Room room = new Room();
            int i1 =1;
            for(int i=0;i<list.size();i++) {
                String roomNum = list.get(i);
                String uuid = UUID.randomUUID()
                        .toString().replaceAll("-", "");
                room.setrUuid(uuid);
                room.setrApartmentId(apartmentID);
                room.setrFloor(floor);
                room.setrNum(roomNum);
                room.setrPrice(1000);
                room.setrResidentNum(0);
                room.setrDelete(0);
                room.setrModify(0);
                i1 = roomMapper.insertSelective(room);
                if(i1==0)
                {
                    break;
                }
            }
            if(i1==0)
            {
                map.put("result", 3);
                map.put("message", "添加房间失败");
            }
            map.put("result", 1);
            map.put("message", "添加房间成功");
        }
        return map;
    }

    @Override
    public JSON getRoomsNum(int apartmentID, int floor) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RoomExample roomExample=new RoomExample();
        RoomExample.Criteria criteria=roomExample.createCriteria();
        criteria.andRApartmentIdEqualTo(apartmentID).andRFloorEqualTo(floor);
        criteria.andRDeleteEqualTo(0);
        criteria.andRNumNotEqualTo("-1");
        RoomMapper roomMapper=sqlSession.getMapper(RoomMapper.class);
        List<Room> rooms=roomMapper.selectByExample(roomExample);
        Collections.sort(rooms, new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                // TODO Auto-generated method stub
                if (o1.getrNumInt() < o2.getrNumInt()) {
                    return -1;
                }
                if (o1.getrNumInt() == o2.getrNumInt())
                    return 0;
                return 1;
            }
        });

        JSON  toJSON= (JSON) JSONArray.toJSON(rooms);
        System.out.println("json="+toJSON);
        return toJSON;
    }

    public Map<String, Object> deleteFloor(int apartmentID, int floor){
        Map<String, Object> map = new HashMap<>();
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);
        RoomExample roomExample=new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andRApartmentIdEqualTo(apartmentID);
        criteria.andRFloorEqualTo(floor);
        RoomExample roomExample1=new RoomExample();
        RoomExample.Criteria criteria1 = roomExample1.createCriteria();
        criteria1.andRApartmentIdEqualTo(apartmentID);
        criteria1.andRFloorEqualTo(floor);
        criteria1.andRResidentNumNotEqualTo(0);
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        if(rooms.size()!=0)
        {
            map.put("result",2);
            map.put("message", "操作失败,该楼层房间有人住或预定了,不能进行删除操作!");
        }

        Room room = new Room();
        room.setrDelete(1);
        int i = roomMapper.updateByExampleSelective(room, roomExample);
        System.out.println(i);
        if(i==0)
        {
            map.put("result", 1);
            map.put("message", "操作成功,删除楼层失败");
        }else {
            map.put("result", 0);
            map.put("message", "操作成功，删除楼层成功");
        }
        return map;
    }

    @Override
    public Map<String,Object> modifyPrice(int roomID,int price) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RoomMapper roomMapper=sqlSession.getMapper(RoomMapper.class);
        Room room=new Room();
        room.setrId(roomID);
        room.setrPrice(price);
        int num=roomMapper.updateByPrimaryKeySelective(room);
        Map<String, Object> map = new HashMap<>();
        if(num==1){
            map.put("result", 0);
            map.put("message", "修改租金成功");
        }else{
            map.put("result", 1);
            map.put("message", "修改租金失败");
        }
        return map;
    }


    //管理员直接开门
    @Override
    public Map<String, Object> openDoor(int lockID, int userID) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Instruction instruction = new Instruction();
        instruction.setiUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        instruction.setiLockId(lockID);
        instruction.setiUserId(userID);
        instruction.setiIsDelete(0);
        instruction.setiIsModify(1);
        instruction.setiIsUser(0);
        instruction.setiIsLock(1);
        instruction.setiIsKey(0);
        instruction.setiIsFinger(0);
        instruction.setiUserInfo("");
        instruction.setiLockInfo("{\"result\":\"ok\",\"method\":\"openDoor\"}");
        instruction.setiKeyInfo("");
        instruction.setiFingerInfo("");
        InstructionMapper instructionMapper=sqlSession.getMapper(InstructionMapper.class);
        int result=instructionMapper.insert(instruction);
        Map<String,Object> map=new HashMap<>();
        if(result==1){
            map.put("result",0);
            map.put("detail","开门指令生成成功");
        }else{
            map.put("result",1);
            map.put("detail","开门指令生成失败");
        }

        return map;
    }


    @Override
    public Map<String, Object> bindRoomLock(int roomID, String introduction) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        LockInfoExample lockInfoExample=new LockInfoExample();
        LockInfoExample.Criteria criteria=lockInfoExample.createCriteria();
        criteria.andLIntroductionEqualTo(introduction);
        LockInfoMapper lockInfoMapper=sqlSession.getMapper(LockInfoMapper.class);
        List<LockInfo> lockInfos=lockInfoMapper.selectByExample(lockInfoExample);
        Map<String,Object> map=new HashMap<>();
        if(lockInfos!=null && lockInfos.size()!=0){
            System.out.println(lockInfos.get(0).getlIntroduction());
            int lock_id=lockInfos.get(0).getlId();
            RoomExample roomExample=new RoomExample();
            RoomExample.Criteria criteria1=roomExample.createCriteria();
            criteria1.andRIdEqualTo(roomID);
            Room room=new Room();
            room.setrLockId(lock_id);
            RoomMapper roomMapper=sqlSession.getMapper(RoomMapper.class);
            int resultRoomMapper=roomMapper.updateByExampleSelective(room,roomExample);
            if(resultRoomMapper==1){
                map.put("result",0);
                map.put("detail","房间绑定门锁成功");
            }else{
                map.put("result",1);
                map.put("detail","房间绑定门锁失败");
            }
        }else{
            map.put("result",1);
            map.put("detail","房间绑定门锁失败");
        }
        return map;
    }
}
