package cn.zjgsu.waterwatch.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataBase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String URL = "jdbc:mysql://localhost:3306/waterwatch";
    static final String USER = "root";
    static final String PASS = "123456";
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
	public DataBase() {
		this.init();
	}
	
	public void init() {
		try{
            // ע�� JDBC ����
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(URL,USER,PASS);
        
            System.out.println(" ʵ����Statement����...");
            stmt = conn.createStatement();
           
        }catch(Exception e){
            // ���� Class.forName ����
            e.printStackTrace();
        }
	}

	public ResultSet search(int stationid) {
		   try{
	            String sql;
	            String station_id = Integer.toString(stationid);
	            sql = "select date,ph,Turbidity from water where station_id = " + station_id + ";";  //���ݿ�mysql��������
	            rs = stmt.executeQuery(sql);
	            return rs;
	        }catch(SQLException se){
	            se.printStackTrace();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		   return null;
	}
	
	public void insert(String date,String ph,String Turbidity,String station_id) {
		try{
            String sql;
            sql = "INSERT INTO water" + "(date, ph, Turbidity,station_id)" + 
            		"VALUES" + 
            		"('" + date + "'," + ph + "," + Turbidity  +","+ station_id + ");";  //���ݿ�mysql��������
            stmt.executeUpdate(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
	}

	public void close() {
		 try{
			 rs.close();
             if(stmt!=null) stmt.close();
         }catch(SQLException se2){
         }// ʲô������
         try{
             if(conn!=null) conn.close();
         }catch(Exception se){
             se.printStackTrace();
         }
	}

}
