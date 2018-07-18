package com.util;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class DocumentSizeListener implements DocumentListener{
	private JLabel tipLabel;
	private int maxSize;
	public DocumentSizeListener(JLabel tipLabel,int maxSize) {
		this.tipLabel=tipLabel;
		this.maxSize=maxSize;
	}
	public void insertUpdate(DocumentEvent e) {
		setTipText(e);
	}
	public void removeUpdate(DocumentEvent e) {
		setTipText(e);
	}
	public void changedUpdate(DocumentEvent e) {
		setTipText(e);
	}
	private void setTipText(DocumentEvent e) {
		Document doc=e.getDocument();//获得文档对象
		tipLabel.setForeground(Color.BLACK);//设置字体颜色
		if(doc.getLength()>(maxSize*4/5))//如果已输入字符长度大于最大长度的80%
		{
			tipLabel.setForeground(Color.RED);//使用红色显示提示信息
		}
		else
		{
			tipLabel.setForeground(Color.BLACK);//使用黑色显示提示信息
		}
		tipLabel.setText("提示信息"+doc.getLength()+"/"+maxSize);
	}

	

}
