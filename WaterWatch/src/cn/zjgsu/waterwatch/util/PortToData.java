package cn.zjgsu.waterwatch.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.zjgsu.waterwatch.database.DataBase;
import serialPort.SerialView;

public class PortToData {
	private String str;

	public PortToData(String str) {
		this.str = str;
		this.insert();
		
	}
	
	public void insert() {
		DataBase data = new DataBase();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");//小写的mm表示的是分钟
		Date temp = new Date();
		String time = temp.toString();
		String[] id = this.str.split(";");
		
		data.insert(time, id[0], id[1], id[2]);
		
		System.out.println(time + id[0] + id[1] + id[2]);
		//System.out.println(this.str);
	}
	
	
}
