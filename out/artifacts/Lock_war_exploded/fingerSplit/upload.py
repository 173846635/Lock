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

#现在开始上传指纹数据
print("PS_LoadChar")
s.write(PS_LoadChar)
time.sleep(1)
number=s.inWaiting()
#print(s.read(number))
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
#read=s.read(number)
print("从flash中读取指定页码的指纹")
print (read)
if read[19]=='0':
    print("指纹读取成功")
else:
    print("指纹读取失败")
s.write(PS_UpChar)
time.sleep(1)
number=s.inWaiting()
print(number)
#print(s.read(number))
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
print(read)

if read[19]=='0':
    print("指纹上传，接收后续数据包")
else:
    print("指纹上传失败")

s.write(PS_UpChar)
time.sleep(1)
number=s.inWaiting()
f=open('fingerModel.FBI','wb')
s.read(12)
number1=s.inWaiting()
print(number1)
f.write(s.read(number-12))

f.close()
s.close()
print("---------------------------")
#save_to_file('fingerModel.txt', read[24:])

