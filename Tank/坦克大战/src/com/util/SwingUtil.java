package com.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class SwingUtil {
	/*
	 * ���������Ĵ�С�����������ʾʱ���Ͻ�����
	 */
	public static Point centreContainer(Dimension size) {
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(screenSize.width-size.width)/2;//�������Ͻǵ�x����
		int y=(screenSize.height-size.height)/2;//�������Ͻǵ�y����
		return new Point(x,y);
	}

}
