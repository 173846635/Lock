import time
import serial
import string
import binascii


s=serial.Serial('COM4',115200)
# GetImage = [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X01,0X00,0X05]
# GenChar_1= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X02,0X01,0X00,0X08]
# GenChar_2= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X02,0X02,0X00,0X09]
RegModel=  [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X05,0X00,0X09]
#最后的校验和要修改
# StoreChar= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X06,0X06,0X01,0X00,0X07,0X00,0X15]

#最后的校验和要修改
# PS_LoadChar=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X06,0X07,0X01,0X00,0X07,0X00,0X16]
# PS_UpChar=  [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X08,0X01,0X00,0X0E]

s.write(RegModel)
time_start=time.time()
while True:
    #time.sleep(0.5)
    # print("inWaiting")
    number=s.inWaiting()
    # print("number==",number)
    # 应答包12个字节
    if number>=12:
        # time_end=time.time()
        # print(number)
        read= str(binascii.b2a_hex(s.read(number)))[2:-1]
        # print(read)
        # print(read[19])
        if read[19]=='0':
            # print("指纹收集成功")
            break
        else:
            # print("重发指纹信息")
            s.write(RegModel)
            time_start=time.time()
    time_end=time.time()
    if (time_end-time_start)>2:
        # print("超过2秒没有应答包,重新发送")
        s.write(RegModel)
        time_start=time.time()
# print('totally cost',time_end-time_start)
s.close()
# time.sleep(2)
# number=s.inWaiting()
# read= str(binascii.b2a_hex(s.read(number)))[2:-1]
# if read[19]=='0':
#     print("指纹存为模版成功")
# else:
#     print("指纹存为模版失败")
# s.close()
