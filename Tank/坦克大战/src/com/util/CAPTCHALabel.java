package com.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
/*
 * 获取及绘制验证码
 */
public class CAPTCHALabel extends JLabel{
	private static final long seriaVersionUID=-963570191302793615L;
	private String text;//用于保存生成验证图片的字符串
	public CAPTCHALabel(String text) {
		this.text=text;
		setPreferredSize(new Dimension(60,36));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setFont(new Font("微软雅黑",Font.PLAIN,16));
		g.drawString(text, 5, 25);//绘制字符串
	}

}
