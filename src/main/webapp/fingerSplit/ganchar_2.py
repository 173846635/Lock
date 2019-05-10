import time
import serial
import string
import binascii
s=serial.Serial('COM4',115200)
GenChar_2= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X02,0X02,0X00,0X09]
s.write(GenChar_2)
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
            # print("生成特征成功")
            break
        else:
            # print("重新发送")
            s.write(GenChar_2)
            time_start=time.time()
    time_end=time.time()
    if (time_end-time_start)>2:
        # print("超过2秒没有应答包,重新发送")
        s.write(GenChar_2)
        time_start=time.time()
# print('totally cost',time_end-time_start)
s.close()


# time.sleep(2)
# number=s.inWaiting()
# print(number)
# read= str(binascii.b2a_hex(s.read(number)))[2:-1]
# print(read)
# if read[19]=='0':
#     print("指纹存入缓冲区成功")
# else:
#     print("指纹存入缓冲区失败")
# s.close()
