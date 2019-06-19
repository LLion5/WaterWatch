import serial

import deal_data

serialport = serial.Serial ("com5", 9600, timeout=1)
temperature,ph,Turbidity = '', '', ''
flag1, flag2 = 0, 0  #接收判断标志，以后增加模块后可以继续添加
try:
    print ('start recive')
    # 循环接收数据
    while True:
        all_data = serialport.readall()
        print(all_data)
        # 将接收的数据判断，11位数据是ph和温度，16位数据是浊度，将有效位截取出来
        if len(all_data) == 11:
            temperature = all_data[6]
            ph = all_data[8]
            flag1 = 1
        if len(all_data) == 16:
            Turbidity = all_data[6:10].decode()
            flag2 = 1
        # 当两个模块的数据都接收到后，将数据进行分析、处理存储
        if flag1==1 and flag2==1:
            if ph < 5:
                print("水质酸性太大")
            deal_data.deal_data(ph,Turbidity,temperature)
            flag1,flag2 = 0,0
except EnvironmentError as err:
    print (err)
finally:
    serialport.close ()

