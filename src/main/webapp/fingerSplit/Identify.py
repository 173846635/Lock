import serial
import string
import binascii
import time
#import RPi.GPIO as GPIO
#GPIO.setmode(GPIO.BOARD)
#GPIO.setup(37, GPIO.OUT)
#GPIO.output(37, GPIO.LOW)
s=serial.Serial('COM4',115200)
GetImage=[0xEF,0x01,0xFF,0xFF,0xFF,0xFF,0x01,0x00,0x03,0x01,0x00,0x05]
PS_Enroll=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X10,0X00,0X14]
Identify=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X11,0X00,0X15]
s.write(Identify)
time.sleep(2)
number=s.inWaiting()
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
#read=s.read(number)
if read[19]=='0':
    print("OK")
else:
    print("NO")
str=read[22:24]
print(str)
s.close()

