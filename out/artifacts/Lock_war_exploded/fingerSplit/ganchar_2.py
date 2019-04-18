import time
import serial
import string
import binascii
s=serial.Serial('COM4',115200)
GetImage = [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X01,0X00,0X05]
GenChar_1= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X02,0X01,0X00,0X08]
GenChar_2= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X02,0X02,0X00,0X09]
RegModel=  [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X05,0X00,0X09]
#最后的校验和要修改
StoreChar= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X06,0X06,0X01,0X00,0X07,0X00,0X15]

#最后的校验和要修改
PS_LoadChar=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X06,0X07,0X01,0X00,0X07,0X00,0X16]
PS_UpChar=  [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X08,0X01,0X00,0X0E]


s.write(GenChar_2)
time.sleep(1)
number=s.inWaiting()
print(number)
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
print(read)
if read[19]=='0':
    print("指纹存入缓冲区成功")
else:
    print("指纹存入缓冲区失败")
s.close()
