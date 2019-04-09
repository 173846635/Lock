package com.dhy.yycompany.lock.service.ApartmentService;

import java.util.Map;

public interface ApartmentService {
    /**
     * 删除公寓楼，同时需要删除公寓中的所有房间，如果房间有人，则无法删除公寓楼
     * @param apartmentID
     * @return
     */
    Map<String,Object> deleteApartment(int apartmentID);
    Map<String,Object> updateApartment(int apartmentID, String newName);
    Map<String,Object> updateApartmentFloorNum(int apartmentID, int num);

    /**
     * 增加楼层
     * @param apartmentId
     * @param floors
     * @return
     */
    Map<String,Object> updateApartmentFloor(int apartmentId, String[] floors);

}
