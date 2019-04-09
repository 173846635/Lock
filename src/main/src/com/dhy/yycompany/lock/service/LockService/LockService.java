package com.dhy.yycompany.lock.service.LockService;

import com.alibaba.fastjson.JSON;

public interface LockService {

    /**
     *获得门锁信息
     * @return
     */
    public JSON getLockMessage(int lockId);

    /**
     * 获得本锁的所有密码
     * @param lockId
     * @return
     */
    public JSON getAllKeyByLockId(int lockId,int pageNum);
}
