package com.dhy.yycompany.lock.utils;

import com.dhy.yycompany.lock.bean.UserInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;

public  class AllChange {
    /**
     * 性别转换
     * @param userInfoList
     */
      public static void changeSex(List<UserInfo> userInfoList)
    {
        for(int i=0;i<userInfoList.size();i++)
        {
            UserInfo userInfo = userInfoList.get(i);
            String s = userInfo.getuSex();
            if(s.equals("m"))
            {
                userInfo.setSexStr("男");
            }else if(s.equals("w"))
            {
                userInfo.setSexStr("女");
            }
        }
    }

//文件地址转换
/* sFile:原文件地址，tFile目标文件地址 */
    public static void fileChannelCopy(String sFile, String tFile) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        File s = new File(sFile);
        File t = new File(tFile);
        if(!t.exists()){
            t.mkdirs();
        }
        if (s.exists() && s.isFile()) {
            try {
                fi = new FileInputStream(s);
                fo = new FileOutputStream(t+"\\tx0.png");
                in = fi.getChannel();// 得到对应的文件通道
                out = fo.getChannel();// 得到对应的文件通道
                in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fi.close();
                    in.close();
                    fo.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
