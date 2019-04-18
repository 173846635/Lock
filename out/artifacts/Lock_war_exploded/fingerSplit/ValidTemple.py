#读取存在flash中有效的指纹数量
import serial
import string
import binascii
import time
import sys

pathF = sys.argv[1]
s=serial.Serial('COM4',115200)
valid=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X03,0X1D,0X00,0X21]
#最后的校验和要修改
StoreChar= [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X06,0X06,0X01,0X00,0X07,0X00,0X15]
#最后的校验和要修改
PS_LoadChar=[0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X06,0X07,0X01,0X00,0X07,0X00,0X16]
PS_UpChar=  [0XEF,0X01,0XFF,0XFF,0XFF,0XFF,0X01,0X00,0X04,0X08,0X01,0X00,0X0E]
#print('validTempleteNum:')
s.write(valid)
time.sleep(2)
number=s.inWaiting()
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
# print(read[19])
# if read[19]=='0':
#     print("OK")
# else:
#     print("NO")

num=read[22:24]
#print(num)
#指纹pageID
num3=0X00
#storeChar 校验和
num4=0X00
#LoadChar校验和
num5=0X00
num1=num[0:1]
num2=num[1:2]
if num1=='0':
	if num2=='0':
		num3=0X00
		num4=0X0E
		num5=0X0F
	elif num2=='1':
		num3=0X01
		num4=0X0F
		num5=0X10
	elif num2=='2':
		num3=0X02
		num4=0X10
		num5=0X11
	elif num2=='3':
		num3=0X03
		num4=0X11
		num5=0X12
	elif num2=='4':
		num3=0X04
		num4=0X12
		num5=0X13
	elif num2=='5':
		num3=0X05
		num4=0X13
		num5=0X14
	elif num2=='6':
		num3=0X06
		num4=0X14
		num5=0X15
	elif num2=='7':
		num3=0X07
		num4=0X15
		num5=0X16
	elif num2=='8':
		num3=0X08
		num4=0X16
		num5=0X17
	elif num2=='9':
		num3=0X09
		num4=0X17
		num5=0X18
	elif num2=='a':
		num3=0X0A
		num4=0X18
		num5=0X19
	elif num2=='b':
		num3=0X0B
		num4=0X19
		num5=0X1A
	elif num2=='c':
		num3=0X0C
		num4=0X1A
		num5=0X1B
	elif num2=='d':
		num3=0X0D
		num4=0X1B
		num5=0X1C
	elif num2=='e':
		num3=0X0E
		num4=0X1C
		num5=0X1D
	elif num2=='f':
		num3=0X0F
		num4=0X1D
		num5=0X1E
elif num1=='1':
	if num2=='0':
		num3=0X10
		num4=0X1E
		num5=0X1F
	elif num2=='1':
		num3=0X11
		num4=0X1F
		num5=0X20
	elif num2=='2':
		num3=0X12
		num4=0X20
		num5=0X21
	elif num2=='3':
		num3=0X13
		num4=0X21
		num5=0X22
	elif num2=='4':
		num3=0X14
		num4=0X22
		num5=0X23
	elif num2=='5':
		num3=0X15
		num4=0X23
		num5=0X24
	elif num2=='6':
		num3=0X16
		num4=0X24
		num5=0X25
	elif num2=='7':
		num3=0X17
		num4=0X25
		num5=0X26
	elif num2=='8':
		num3=0X18
		num4=0X26
		num5=0X27
	elif num2=='9':
		num3=0X19
		num4=0X27
		num5=0X28
	elif num2=='a':
		num3=0X1A
		num4=0X28
		num5=0X29
	elif num2=='b':
		num3=0X1B
		num4=0X29
		num5=0X2A
	elif num2=='c':
		num3=0X1C
		num4=0X2A
		num5=0X2B
	elif num2=='d':
		num3=0X1D
		num4=0X2B
		num5=0X2C
	elif num2=='e':
		num3=0X1E
		num4=0X2C
		num5=0X2D
	elif num2=='f':
		num3=0X1F
		num4=0X2D
		num5=0X2E
StoreChar[12]=num3
StoreChar[14]=num4
PS_LoadChar[12]=num3
PS_LoadChar[14]=num5
#print(StoreChar[12])


s.write(StoreChar)
time.sleep(2)
number=s.inWaiting()
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
# print(read)
# if read[19]=='0':
#     print("指纹存到flash成功")
# else:
#     print("指纹存到flash失败")



#现在开始上传指纹数据
s.write(PS_LoadChar)
time.sleep(2)
number=s.inWaiting()
#print(s.read(number))
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
#read=s.read(number)
# print (read)
# if read[19]=='0':
#     print("指纹读取成功")
# else:
#     print("指纹读取失败")
s.write(PS_UpChar)
time.sleep(2)
number=s.inWaiting()
#print(number)
#print(s.read(number))
read= str(binascii.b2a_hex(s.read(number)))[2:-1]
#print(read)

# if read[19]=='0':
#     print("指纹上传，接收后续数据包")
# else:
#     print("指纹上传失败")

s.write(PS_UpChar)
time.sleep(2)
number=s.inWaiting()
f=open(pathF+'/'+num+'.FBI','wb')
#print(f)
s.read(12)
number1=s.inWaiting()
#print(number1)
f.write(s.read(number-12))

f.close()
s.close()
#print("---------------------------")
#save_to_file('fingerModel.txt', read[24:])
print(num)


