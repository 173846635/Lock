package com.dhy.yycompany.lock.service.roomInfoService;

import com.alibaba.fastjson.JSON;
import com.dhy.yycompany.lock.bean.Room;

import java.util.Map;

public interface RoomInfoService {

    //点击房间按钮进入
    //获得单个房间信息

    /**
     * map对象的信息
     * users：住户信息list
     * roomX：房间信息门锁状态（1开   0关）
     * @param room_id
     * @return
     */
    Map<String,Object> getRoomInfo(int room_id);

    /**
     * 获取房间信息
     * @param roomId
     */
    public Room getRoomMessage(int roomId);



    //点击门锁管理按钮进入
    //获得门锁的信息：

    /**
     * 锁的版本信息，
     * 门锁开关状态
     * 门锁密码管理
     * 开门记录
     *
     * map对象的信息
     * lockID:门锁id
     * software:软件版本
     * hardware:硬件版本
     * status:门锁状态
     * keyList:密码bean的list
     * openlist:开门bean的list
     * @param lock_id
     * @return
     */
    Map<String,Object> getLockInfo(int lock_id);



    /**
     * 删除单个房间
     * @param roomID
     */
    Map<String, Object> deleteRoom(int roomID);


    /**
     * 增加房间
     * 提供房间号创建房间名
     */
    public Map<String, Object> addRoom(int apartmentID, int floor,String[] roomNum);

    /**
     * 增删房间界面，获得楼层中所有房间的信息，参数 （公寓楼id， 楼层）
     * @param apartmentID
     * @param floor
     * @return
     */
    JSON getRoomsNum(int apartmentID, int floor);

    /**
     * 删除楼层
     * @param apartmentID
     * @param floor
     * @return
     */
    public Map<String, Object> deleteFloor(int apartmentID, int floor);

    /**
     *修改房间租金
     */
    Map<String,Object> modifyPrice(int roomID,int price);

    //管理员直接开门
    /**
     * 1.在指令表中添加开门指令
     * @param lockID
     * @param userID
     * @return
     */
    Map<String,Object> openDoor(int lockID,int userID);


    //绑定房间和门锁
    Map<String,Object> bindRoomLock(int roomID,String introduction);
}
