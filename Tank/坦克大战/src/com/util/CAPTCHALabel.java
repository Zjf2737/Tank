package com.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
/*
 * ��ȡ��������֤��
 */
public class CAPTCHALabel extends JLabel{
	private static final long seriaVersionUID=-963570191302793615L;
	private String text;//���ڱ���������֤ͼƬ���ַ���
	public CAPTCHALabel(String text) {
		this.text=text;
		setPreferredSize(new Dimension(60,36));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("΢���ź�",Font.PLAIN,16));
		g.drawString(text, 5, 25);//�����ַ���
	}

}
