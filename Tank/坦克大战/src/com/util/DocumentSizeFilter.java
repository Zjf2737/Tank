package com.util;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentSizeFilter extends DocumentFilter{
	private int maxSize;//获得文本的最大长度
	public DocumentSizeFilter(int maxSize) {
		this.maxSize=maxSize;//获得用户输入的最大长度
	}
	
	public void insertString(FilterBypass fb,int offset,String string,AttributeSet attr)throws BadLocationException{
		if((fb.getDocument().getLength()+string.length())<=maxSize) {//如果插入操作完成后小于最大长度
			super.insertString(fb, offset, string, attr);
		}
		else {
			Toolkit.getDefaultToolkit().beep();//发出提示音
		}
		
	}
	
	public void replace(FilterBypass fb,int offset,int length,String text,AttributeSet attrs)throws BadLocationException{
		if((fb.getDocument().getLength()+text.length()-length)<=maxSize) {//如果插入操作完成后小于最大长度
			super.replace(fb, offset,length, text, attrs);
		}
		else {
			Toolkit.getDefaultToolkit().beep();//发出提示音
		}
		
	}
}
