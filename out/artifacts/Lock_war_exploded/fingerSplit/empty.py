import serial
import string
import binascii
import time
s=serial.Serial('/dev/ttyUSB0',115200)
Empty=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X0D,0X00,0X11]
print('Empty:')
s.write(Empty)
time.sleep(2)
number=s.inWaiting()
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
print(read[19])
if read[19]=='0':
    print("ok")
else:
    print("no")
print(read)
s.close()
