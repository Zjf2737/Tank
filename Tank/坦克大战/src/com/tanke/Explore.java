package com.tanke;

import java.awt.Color;
import java.awt.Graphics;


public class Explore {
	int x,y;
	private boolean live = true;
	private Yard Y;
	private tank tk;
	int[] diameter = {4,6,9,13,18,24,31,39,48,30,14,6};
	int step=0;
	public Explore(int x,int y,tank tk) {
		this.x=x;
		this.y=y;
		this.tk=tk;
	}
	public void draw(Graphics g){
		if(!live)return;
		
		if(step==diameter.length){
			live=false;
			step=0;
			return;
		}
		Color c=g.getColor();
		g.setColor(Color.orange);
		g.fillOval(x, y, diameter[step], diameter[step]);
		g.setColor(c);
		step++;
	}
	public void setXY(int x,int y){
		this.x=x;
		this.y=y;
	}
	public boolean getLive(){
		return live;
	}

}
