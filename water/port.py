import pymysql
import time

def port_to_data(ph,Turbidity,temperature):
    # 打开数据库连接
    db = pymysql.connect ("localhost", "root", "123456", "waterdb")

    # 使用cursor()方法获取操作游标
    cursor = db.cursor ()

    # SQL 插入语句
    s = time.strftime ('%Y.%m.%d.%H.%M.%S', time.localtime (time.time ()))

    sql = "INSERT INTO water" + "(date,ph,Turbidity,station_id,temperature)"\
        + "VALUES" + "('" + s + "'," + ph + "," + Turbidity + ","+ "1" + ","\
          + temperature+ ");"
    try:
        # 执行sql语句
        cursor.execute (sql)
        # 提交到数据库执行
        db.commit ()
        print("存储成功")
    except:
        print ("存储失败")

    # 关闭数据库连接
    db.close ()

