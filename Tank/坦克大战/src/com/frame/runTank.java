package com.frame;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.tanke.Yard;


public class runTank {

	/**
	 * @param args
	 */
	public JFrame f=new JFrame("坦克大战");
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//Yard y=new Yard("My SuperTank!");
		new runTank().init();

	}
	public void init(){
		f.setLayout(new BorderLayout(30,5));
		Button b1=new Button("排名");
		Button b2=new Button("开始游戏");
		b2.addActionListener(new Monitor());
		b1.addActionListener(new Monitor1());
		Panel p=new Panel();
		Panel p2=new Panel();
		Panel p3=new Panel();
		Panel p4=new Panel();
		p.add(b1);
		p2.add(b2);
		p.setBackground(Color.RED);
		p2.setBackground(Color.green);
		p3.setBackground(Color.blue);
		p.setBounds(200, 200, 200, 200);
		p2.setBounds(200, 400, 200, 200);
		p3.setBounds(400, 200, 200, 200);
		f.addWindowListener(new Mywindows());
		f.add(p);
		f.add(new Button("上"),BorderLayout.NORTH);
		f.add(p2,BorderLayout.WEST);
		f.add(p3,BorderLayout.EAST);
		f.setBounds(200, 200, 600, 600);
		//f.pack();
		f.setVisible(true);
	}
	class Monitor implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
				Yard y=new Yard();
			    y.init();
			    f.setVisible(false);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	class Monitor1 implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			try {
				new Ranking().init();
			    f.setVisible(false);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
	public class Mywindows extends WindowAdapter {         //关闭窗口
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}

}
