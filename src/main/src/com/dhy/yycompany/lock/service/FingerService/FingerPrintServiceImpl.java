package com.dhy.yycompany.lock.service.FingerService;

import com.dhy.yycompany.lock.bean.FingerPrint;
import com.dhy.yycompany.lock.bean.Instruction;
import com.dhy.yycompany.lock.dao.FingerPrintMapper;
import com.dhy.yycompany.lock.dao.InstructionMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class FingerPrintServiceImpl implements FingerPrintService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Map<String, Object> addFingerPrint(int u_id, int l_id, int process) {
        System.out.println(process);
        //等待完成
        Process ps = null;
        int status1 = 0;
        Map<String, Object> map = new HashMap<>();
        if (process == 0 || process == 2) {
            map.put("result", process);
            return map;
        }
        String path1 = System.getProperty("evan.webapp");
        try {
            if (process == 1) {

                System.out.println("请按下指纹");
                //Thread.sleep(3000);
                ps = Runtime.getRuntime().exec("python " + path1 + "/fingerSplit/getImage.py");
                status1 = ps.waitFor();//返回0为成功
                System.out.println(status1);
                System.out.println("第一次指纹录入完毕，放开手指");
                // Thread.sleep(3000);
                ps = Runtime.getRuntime().exec("python " + path1 + "/fingerSplit/ganchar_1.py");
                status1 = ps.waitFor();//返回0为成功
                System.out.println(status1);
                if (status1 == 0) {
                    map.put("result", 1);
                    map.put("detail", "第一次指纹录入完毕，放开手指");
                } else {
                    map.put("result", -1);
                    map.put("detail", "第一次指纹录入失败");
                }

            } else if (process == 3) {
                System.out.println("请再次按下指纹");
                //Thread.sleep(3000);
                ps = Runtime.getRuntime().exec("python " + path1 + "/fingerSplit/getImage.py");
                status1 = ps.waitFor();//返回0为成功
                System.out.println(status1);
                System.out.println("第二次指纹录入完毕，放开手指");
                //Thread.sleep(3000);
                ps = Runtime.getRuntime().exec("python " + path1 + "/fingerSplit/ganchar_2.py");
                status1 = ps.waitFor();//返回0为成功
                System.out.println(status1);

                ps = Runtime.getRuntime().exec("python " + path1 + "/fingerSplit/regmodel.py");
                status1 = ps.waitFor();//返回0为成功
                System.out.println(status1);
                if (status1 == 0) {
                    map.put("result", 3);
                    map.put("detail", "第二次指纹录入完毕，请放开手指");
                } else {
                    map.put("result", -3);
                    map.put("detail", "第二次指纹录入失败");
                }
            } else if (process == 4) {
                //创建指纹目录,并生成指纹文件
                //System.out.println("path1=="+path1);
                //String path1 = this.getClass().getClassLoader().getResource("./finger/").getPath();
                String path = path1 + "\\imagesA\\finger\\" + l_id + "\\" + u_id;
                String pathLockUser = l_id + "/" + u_id + "/";
                System.out.println("path=" + path);
                File file = new File(path);
                Boolean bbc = file.mkdirs();// ture

                String position1 = "";

                try {
                    String[] args = new String[]{"python", path1 + "fingerSplit\\ValidTemple.py", path};
                    ps = Runtime.getRuntime().exec(args);
                    InputStreamReader ir = new InputStreamReader(ps.getInputStream());
                    LineNumberReader input = new LineNumberReader(ir);
                    String str = "";
                    while ((str = input.readLine()) != null) {
                        System.out.println("result==" + str);
                        position1 = str;
                    }
                    status1 = ps.waitFor();//返回0为成功
                    System.out.println(status1);
                    input.close();
                    ir.close();
//            process.waitFor();
                } catch (IOException e) {
                    //logger.error("调用python脚本并读取结果时出错：" + e.getMessage());
                    e.printStackTrace();
                }
                //生成指纹完整路径
                System.out.println("position1=" + position1);
                String fingerContent = path + "/" + position1;
                System.out.println("fingerContent=" + fingerContent);
                //生成uuid
                String uuid = "";
                uuid = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println(uuid);

                SqlSession sqlSession = sqlSessionFactory.openSession();
                FingerPrint fp = new FingerPrint();
                fp.setfContentPath(fingerContent);
                fp.setfLockId(l_id);
                fp.setfName("指纹1");
                fp.setfUserId(Integer.valueOf(u_id));
                fp.setfDelete(0);
                fp.setfIsModify(0);
                fp.setfUuid(uuid);
                fp.setfId(null);
                FingerPrintMapper fingerPrintMapper = sqlSession.getMapper(FingerPrintMapper.class);

                int num1 = fingerPrintMapper.insert(fp);
                if (num1 == 1) {
                    System.out.println("添加指纹成功");
                } else {
                    System.out.println("添加指纹失败");
                }


                System.out.println("fingerPrint=" + fp);


                InstructionMapper instructionMapper = sqlSession.getMapper(InstructionMapper.class);

                Instruction instruction = new Instruction();
                instruction.setiUuid(UUID.randomUUID().toString().replaceAll("-", ""));
                instruction.setiLockId(l_id);
                instruction.setiUserId(u_id);
                instruction.setiIsDelete(0);
                instruction.setiIsModify(1);
                instruction.setiIsUser(0);
                instruction.setiIsLock(0);
                instruction.setiIsKey(0);
                instruction.setiIsFinger(1);
                instruction.setiUserInfo("");
                instruction.setiLockInfo("");
                instruction.setiKeyInfo("");
                instruction.setiFingerInfo("{\"result\":\"ok\",\"fName\":\""+position1+".FBI\",\"method\":\"addFingerFile\",\"path\":\""+pathLockUser+"\",\"userID\":\""+u_id+"\"}");
                int num = instructionMapper.insert(instruction);
                if (num == 1) {
                    System.out.println("添加到指令表成功");
                } else {
                    System.out.println("添加到指令表失败");
                }
                if (status1 == 0) {
                    map.put("result", 4);
                    map.put("detail", "创建指纹文件成功");
                } else {
                    map.put("result", -4);
                    map.put("detail", "创建指纹文件失败");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return map;
    }
}
