package com.frame;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.util.SwingUtil;

public class MainFrame extends JFrame{
	private static final long serialVersionUID=-7360122511568073830L;
	private JPanel contentPane;
	
	
	
	public MainFrame() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置单击关闭窗体按钮时执行的操作
	setSize(450,300);//设置窗体大小
	contentPane=new JPanel();//创建面板
	contentPane.setLayout(new BorderLayout(0,0));//设置面板布局使用边界布局
	setContentPane(contentPane);//应用面板
	JLabel tipLabel=new JLabel("恭喜您成功登陆游戏系统!");//创建标签
	tipLabel.setFont(new Font("微软雅黑",Font.PLAIN,40));//设置标签字体
	contentPane.add(tipLabel,BorderLayout.CENTER);//应用标签
	setLocation(SwingUtil.centreContainer(getSize()));//让窗体居中
	}
}
