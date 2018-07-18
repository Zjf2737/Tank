package com.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.frame.runTank.Mywindows;
import com.model.User;
import com.util.DBHelper;

public class Ranking {
	JFrame jf=new JFrame("排名");
	JTable table;
	Object[][] tableData=new Object[10][10];
	Object[] columnTitle = {"姓名","关卡","时间"};
	DBHelper d=new DBHelper();
	BufferedImage head;
	public void init(){
		int i=0;
		List<User> result=d.mysort();
		for (User u : result) {
			/*Object[] o=new Object[]{u.getId(),u.getLevel(),u.getTime()};
			tableData[i]=o;*/
			tableData[i][0]=u.getUsername();
			tableData[i][1]=u.getLevel();
			tableData[i][2]=u.getTime();
			i++;
		}
		try {
			head=ImageIO.read(new File("D:\\\\图片\\\\坦克\\\\image\\\\image\\\\yard1.jpg"));
			//jf.setLayout(new BoxLayout(jf,BoxLayout.Y_AXIS));
			myhead p=new myhead();
			p.setBounds(300, 300, 200, 200);
			table =new JTable(tableData,columnTitle);
			//jf.setLayout(null);
			JScrollPane js=new JScrollPane(table);
			
			jf.add(js);
			jf.pack();
			jf.addWindowListener(new Mywindows());
			//js.setVisible(false);
			jf.setVisible(true);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		new Ranking().init();
	}
	public class Mywindows extends WindowAdapter {         //关闭窗口
		public void windowClosing(WindowEvent e) {
			jf.setVisible(false);
			new runTank().init();
			
		}
	}
	class myhead extends JPanel
	{
		public void paint(Graphics g){
			g.drawImage(head,0,0,null);
		}
	}
	
	
	
	
	
	
}
