import serial
import string
import binascii
import time

s=serial.Serial('COM4',115200)
Search=[0xEF,0x01,0xFF,0xFF,0xFF,0xFF,0x01,0X00,0X08,0X04,0X01,0X00,0X00,0X00,0X20,0X00,0X2E]
print('search')
s.write(Search)
time.sleep(2)
number=s.inWaiting()
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
#read=s.read(number)
print("---------------")
if read[19]=='0':
    print("ok")
            #GPIO.output(37, GPIO.HIGH)
                #time.sleep(0.5)
                    #GPIO.output(37, GPIO.LOW)
else:
    print("no")
print(read)
str=read[22:24]
print(str)
s.close()
