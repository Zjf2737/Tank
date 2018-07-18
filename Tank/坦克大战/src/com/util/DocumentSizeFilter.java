package com.util;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocumentSizeFilter extends DocumentFilter{
	private int maxSize;//����ı�����󳤶�
	public DocumentSizeFilter(int maxSize) {
		this.maxSize=maxSize;//����û��������󳤶�
	}
	
	public void insertString(FilterBypass fb,int offset,String string,AttributeSet attr)throws BadLocationException{
		if((fb.getDocument().getLength()+string.length())<=maxSize) {//������������ɺ�С����󳤶�
			super.insertString(fb, offset, string, attr);
		}
		else {
			Toolkit.getDefaultToolkit().beep();//������ʾ��
		}
		
	}
	
	public void replace(FilterBypass fb,int offset,int length,String text,AttributeSet attrs)throws BadLocationException{
		if((fb.getDocument().getLength()+text.length()-length)<=maxSize) {//������������ɺ�С����󳤶�
			super.replace(fb, offset,length, text, attrs);
		}
		else {
			Toolkit.getDefaultToolkit().beep();//������ʾ��
		}
		
	}
}
