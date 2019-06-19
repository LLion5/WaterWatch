package cn.zjgsu.waterwatch.UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.zjgsu.waterwatch.database.DataBase;

public class Window extends JFrame implements ActionListener{

	private JTextField stationText = new JTextField("�������վID��",10);
	private JTextArea textArea;
	
	public Window() {
		this.setTitle("ˮ�ʼ��ϵͳ");
		this.getContentPane().setLayout(new BorderLayout());
		
		
		JPanel cp = new JPanel();
		
		JButton button = new JButton("��ѯ");
		button.addActionListener(this);
		JButton clc = new JButton("���");
		clc.addActionListener(this);
		cp.add(stationText);
		cp.add(button);
		cp.add(clc);
		
		this.textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(this.textArea);
		
		this.getContentPane().add("South",cp);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		this.setBounds(600,250,700,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton temp=(JButton)e.getSource();
		String str = temp.getText();
		if(str.equals("��ѯ")) {
			int station_id = Integer.parseInt(stationText.getText());
			System.out.println("�����id�ǣ�" + station_id);
			DataBase data = new DataBase();
		    ResultSet rs = data.search(station_id);
		    textArea.append("��վ" + station_id +"��ˮ�����\n" + "����		ph	���Ƕ�\n");
			try {
				while(rs.next()){	
					    String date = rs.getString("date");     
				        float ph = rs.getFloat("ph");
				        float Turbidity = rs.getFloat("Turbidity");
				        /*System.out.print("	date: " + date);
				        System.out.print("	ph: " + ph);
				        System.out.print("	Turbidity: " + Turbidity);
				        System.out.print("\n");*/
				        
				        textArea.append(date + "	" +Float.toString(ph) + "	" +Float.toString(Turbidity) + "\n");
				        
				    }
			data.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}else if(str.equals("���")) {
			this.textArea.setText("");
		}
	}
}
