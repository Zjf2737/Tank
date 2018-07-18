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
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���õ����رմ��尴ťʱִ�еĲ���
	setSize(450,300);//���ô����С
	contentPane=new JPanel();//�������
	contentPane.setLayout(new BorderLayout(0,0));//������岼��ʹ�ñ߽粼��
	setContentPane(contentPane);//Ӧ�����
	JLabel tipLabel=new JLabel("��ϲ���ɹ���½��Ϸϵͳ!");//������ǩ
	tipLabel.setFont(new Font("΢���ź�",Font.PLAIN,40));//���ñ�ǩ����
	contentPane.add(tipLabel,BorderLayout.CENTER);//Ӧ�ñ�ǩ
	setLocation(SwingUtil.centreContainer(getSize()));//�ô������
	}
}
