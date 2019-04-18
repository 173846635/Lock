import time
import serial
import string
import binascii

def getImage():
    #获取指纹图像
    #print("请按下手指")
    while True:
        s.write(GetImage)
        time.sleep(2)
        number=s.inWaiting()
        read= str(binascii.b2a_hex(s.read(number)))[2:-1]
        if read[19]=='0':
            #print("指纹收集成功，请放开手指")
            break
    

s=serial.Serial('COM4',115200)
GetImage = [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X01,0X00,0X05]

#获取指纹图像
getImage()
s.close()
