import port
# 数据处理函数，处理后将数据存入数据库
def deal_data(ph,Turbidity,temperature):
    print("开始存入数据库")
    Turbidity = float(Turbidity)
    # 处理公式（具体参见硬件）
    temperature = temperature / 10
    Turbidity = -1120.4 * (Turbidity ** 2) + 5742.3 * Turbidity - 4352.9
    ph = ph / 10
    Turbidity = "{:.2f}".format(Turbidity)
    port.port_to_data(str(ph),str(Turbidity),str(temperature))

