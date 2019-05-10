#录入指纹存于imageBuffer
import serial
import string
import binascii
import time

s=serial.Serial('COM4',115200)
GetImage=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X01,0X00,0X05]
# 应答包12个字节
#print("GetImage")

s.write(GetImage)
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
            s.write(GetImage)
            time_start=time.time()
    time_end=time.time()
    if (time_end-time_start)>2:
        # print("超过2秒没有应答包,重新发送")
        s.write(GetImage)
        time_start=time.time()
# print('totally cost',time_end-time_start)
s.close()
