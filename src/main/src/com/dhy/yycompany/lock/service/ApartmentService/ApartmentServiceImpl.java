package com.dhy.yycompany.lock.service.ApartmentService;

import com.dhy.yycompany.lock.bean.Apartment;
import com.dhy.yycompany.lock.bean.Room;
import com.dhy.yycompany.lock.bean.RoomExample;
import com.dhy.yycompany.lock.dao.ApartmentMapper;
import com.dhy.yycompany.lock.dao.RoomMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 删除公寓楼，同时需要删除公寓中的所有房间，如果房间有人，则无法删除公寓楼
     * @param apartmentID
     * @return
     */
    @Override
    public Map<String,Object> deleteApartment(int apartmentID) {

        Map<String,Object> map=new HashMap<>();

        RoomExample roomExample=new RoomExample();
        RoomExample.Criteria criteria=roomExample.createCriteria();
        criteria.andRApartmentIdEqualTo(apartmentID).andRResidentNumNotEqualTo(0);
        criteria.andRDeleteEqualTo(0);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        RoomMapper roomMapper=sqlSession.getMapper(RoomMapper.class);
        List<Room> roomList = roomMapper.selectByExample(roomExample);
        if(roomList!=null && roomList.size()!=0){
            System.out.println("存在有人的房屋，不能删除");
            map.put("result",1);
            map.put("message","操作失败,该楼栋房间有人住或预定了,不能进行删除操作!");
        }else{
            Apartment apartment=new Apartment();
            apartment.setaId(apartmentID);
            apartment.setaDelete(1);
            ApartmentMapper apartmentMapper=sqlSession.getMapper(ApartmentMapper.class);
            int num=apartmentMapper.updateByPrimaryKeySelective(apartment);
            if(num==1){
                System.out.println("delete ok");
                RoomExample roomExample1=new RoomExample();
                RoomExample.Criteria criteria1 = roomExample1.createCriteria();
                criteria1.andRDeleteEqualTo(0);
                criteria1.andRApartmentIdEqualTo(apartmentID);
                Room room=new Room();
                room.setrDelete(1);
                int num1=roomMapper.updateByExampleSelective(room,roomExample1);
                System.out.println("delete rooms number="+num1);
                map.put("result",0);
                map.put("message","成功删除公寓，同时删除公寓内的"+num1+"间房间");
            }else{
                System.out.println("update error");
                map.put("result",2);
                map.put("message","删除公寓楼失败");
            }
        }
        sqlSession.commit();
        sqlSession.close();
        return map;
    }

    @Override
    public Map<String, Object> updateApartment(int apartmentID,String newName) {
        Map<String, Object> map = new HashMap<>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApartmentMapper apartmentMapper = sqlSession.getMapper(ApartmentMapper.class);
        Apartment apartment = new Apartment();
        apartment.setaId(apartmentID);
        apartment.setaName(newName);
        int num = apartmentMapper.updateByPrimaryKeySelective(apartment);

        if (num == 1) {
            System.out.println("update name ok");
            map.put("result", 0);
            map.put("message", "成功修改公寓名称");
        } else {
            System.out.println("update error");
            map.put("result", 1);
            map.put("message", "修改公寓楼名失败");
        }
        System.out.println(map);
        sqlSession.commit();
        sqlSession.close();
        return map;
    }

    @Override
    public Map<String, Object> updateApartmentFloorNum(int apartmentID, int num) {

        Map<String, Object> map = new HashMap<>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ApartmentMapper apartmentMapper = sqlSession.getMapper(ApartmentMapper.class);
        Apartment apartment = new Apartment();
        apartment.setaId(apartmentID);
        apartment.setaFloorNum(String.valueOf(num));
        int num1 = apartmentMapper.updateByPrimaryKeySelective(apartment);

        if (num1 == 1) {
            System.out.println("update name ok");
            map.put("result", 0);
            map.put("message", "成功修改公寓楼层");
        } else {
            System.out.println("update error");
            map.put("result", 1);
            map.put("message", "修改公寓楼楼层失败");
        }
        System.out.println(map);
        sqlSession.commit();
        sqlSession.close();
        return map;
    }

    public Map<String,Object> updateApartmentFloor(int apartmentId, String[] floors){
        Map<String, Object> map = new HashMap<>();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoomMapper roomMapper = sqlSession.getMapper(RoomMapper.class);
        RoomExample roomExample = new RoomExample();
        RoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andRApartmentIdEqualTo(apartmentId);
        List<String> stringB = Arrays.asList(floors);
        List<Integer> IntegerList = new ArrayList<Integer>();
        for (String x : stringB) {
            Integer z = Integer.parseInt(x);
            IntegerList.add(z);
        }

        criteria.andRFloorIn(IntegerList);
        criteria.andRDeleteEqualTo(0);
        List<Room> roomList = roomMapper.selectByExample(roomExample);
        if(roomList.size()!=0)
        {
            System.out.println("insert error");
            map.put("result", 3);
            map.put("message", "添加楼层失败,楼层已存在");
            return map;
        }
        Room room = new Room();
        int i1=1;
        for(int i=0;i<floors.length;i++) {
            int floor = Integer.parseInt(floors[i]);
            room.setrApartmentId(apartmentId);
            room.setrFloor(floor);
            room.setrNum("-1");
            room.setrUuid(UUID.randomUUID().toString().replaceAll("-", ""));
            i1 = roomMapper.insertSelective(room);
            if(i1==0){
                break;
            }
        }

        if(i1==0)
        {
            System.out.println("insert error");
            map.put("result", 1);
            map.put("message", "添加楼层失败");
        }
        else  if(i1==1){
            ApartmentMapper apartmentMapper = sqlSession.getMapper(ApartmentMapper.class);
            Apartment apartment = apartmentMapper.selectByPrimaryKey(apartmentId);
            apartment.setaFloorNum(apartment.getaFloorNum()+floors.length);
            int i = apartmentMapper.updateByPrimaryKeySelective(apartment);
            if(i==0)
            {
                System.out.println("update floorNum error");
                map.put("result", 2);
                map.put("message", "修改楼层数失败");
            }
            else if(i==1) {
                System.out.println("insert ok");
                map.put("result", 0);
                map.put("message", "添加楼层成功");
            }
        }
        sqlSession.close();
        return map;

    }
}
