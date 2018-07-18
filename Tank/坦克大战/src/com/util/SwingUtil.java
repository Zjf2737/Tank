package com.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

public class SwingUtil {
	/*
	 * 根据容器的大小，计算剧中显示时左上角坐标
	 */
	public static Point centreContainer(Dimension size) {
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		int x=(screenSize.width-size.width)/2;//计算左上角的x坐标
		int y=(screenSize.height-size.height)/2;//计算左上角的y坐标
		return new Point(x,y);
	}

}
