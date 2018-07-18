package com.tanke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;



public class tank {
	int id=0;
	private Yard.tankBackGround T = null;
	private Yard Y=null;
	public Node centre = new Node(300, 300, Dir.S);  //中心结点
	boolean bL=false,bR=false,bU=false,bD=false;
	bullet[] bn = new bullet[200]; 
	int n=1;
	int supern=21;
	int move=10;           //结点大小
	boolean islaunch=false;
	int style = 0;                //分别子弹类型
	boolean isvasible=true;//是不是可见
	int []buff=new int[100];//控制坦克移动轨迹
	int bfi=1;
	private Dir ptdir=Dir.D;//炮筒的方向
	boolean good=true;
	Explore exp=new Explore(centre.x,centre.y,this);//爆炸现场
	private static int mv=3;//坦克移动的速度
	private static Random ra=new Random();
	int step=ra.nextInt(12)+3;
	static double pai=Math.PI;
	boolean issuperlaunch =false;//是否发射超级子弹；
	int life=100;//生命值
	private BloodBar bb=new BloodBar();
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	tank(Yard.tankBackGround T,int x,int y1,boolean good) {
		this.T = T;
		centre.x=x;
		centre.y=y1;
		this.good=good;
	}
	tank(Yard.tankBackGround T,boolean good,int x,int y1){
		this.T=T;
		centre.x=x;
		centre.y=y1;
		this.good=good;
	}
	
	public void launch(Graphics g){         //子弹发射
		bn[n]=new bullet(style);
		if(!good){
			int goodtankx=Y.t[1].centre.x+20;
			int goodtanky=Y.t[1].centre.y+20;
			double gy=goodtanky-centre.y;
			double gx=goodtankx-centre.x;
			double tan=gy/gx;
			//System.out.println(tan);
			if(centre.x<goodtankx){
				if(tan>Math.tan(pai*3/8)){
					ptdir=Dir.D;
				}else if(tan>Math.tan(pai/8)){
					ptdir=Dir.RD;
				}else if(tan>Math.tan(-pai/8)){
					ptdir=Dir.R;
				}else if(tan>Math.tan(-pai*3/8)){
					ptdir=Dir.RU;
				}else{
					ptdir=Dir.U;
				}
			}else if(centre.x>goodtankx){
				if(tan>Math.tan(pai*3/8)){
					ptdir=Dir.U;
				}else if(tan>Math.tan(pai/8)){
					ptdir=Dir.LU;
				}else if(tan>Math.tan(-pai/8)){
					ptdir=Dir.L;
				}else if(tan>Math.tan(-pai*3/8)){
					ptdir=Dir.LD;
				}else{
					ptdir=Dir.D;
				}
			}else{
				if(centre.y<goodtanky)ptdir=Dir.U;
				else ptdir=Dir.D;
			}
		}
		if(ptdir==Dir.U){
			bn[n].x=this.centre.x+15;
			bn[n].y=this.centre.y-10;
		}else if(ptdir==Dir.D){
			bn[n].x=this.centre.x+15;
			bn[n].y=this.centre.y+40;
		}else if(ptdir==Dir.L){
			bn[n].x=this.centre.x-10;
			bn[n].y=this.centre.y+15;
		}else if(ptdir==Dir.R){
			bn[n].x=this.centre.x+40;
			bn[n].y=this.centre.y+15;
		}else if(ptdir==Dir.LU){
			bn[n].x=this.centre.x-5;
			bn[n].y=this.centre.y-5;
		}else if(ptdir==Dir.LD){
			bn[n].x=this.centre.x;
			bn[n].y=this.centre.y+35;
		}else if(ptdir==Dir.RU){
			bn[n].x=this.centre.x+40;
			bn[n].y=this.centre.y;
		}else if(ptdir==Dir.RD){
			bn[n].x=this.centre.x+35;
			bn[n].y=this.centre.y+35;
		}
		
		bn[n].d=ptdir;
		n=n%20+1;
	}
	
	public void superLanch(Graphics g){
		Dir[] dirs=Dir.values();
		for(int i=0;i<8;i++){
			bn[supern++]=new bullet(1,dirs[i]);
			bn[supern-1].x=this.centre.x+20;
			bn[supern-1].y=this.centre.y+20;
			supern=supern%161+21;
		}
	}
	//玩家一操作设置
	public void keypress(KeyEvent e) {
		if (e.getKeyCode() == e.VK_UP) {
				bU=true;
		} else if (e.getKeyCode() == e.VK_DOWN) {
				bD=true;
		} else if (e.getKeyCode() == e.VK_LEFT) {
				bL=true;
		} else if (e.getKeyCode() == e.VK_RIGHT) {
				bR=true;
		}else if(e.getKeyCode() == e.VK_Z){
			islaunch=true;
			style = 1;
		}else if(e.getKeyCode() == e.VK_X){
			issuperlaunch=true;
		}
		locateDirection();
	}
	//玩家二操作设置
	public void keypress2(KeyEvent e) {
		if (e.getKeyCode() == e.VK_W) {
			if (centre.dir == Dir.U) {
				centre.y -= 20;
				//Goup();
			} else {
				centre.dir = Dir.U;
				//Goup();
			}
		} else if (e.getKeyCode() == e.VK_S) {
			if (centre.dir == Dir.D) {
				centre.y += 20;
				//Godown();
			} else {
				centre.dir = Dir.D;
				//Godown();
			}
		} else if (e.getKeyCode() == e.VK_A) {
			if (centre.dir == Dir.L) {
				centre.x -= 20;
				//GoLeft();
			} else {
				centre.dir = Dir.L;
				//GoLeft();
			}
		} else if (e.getKeyCode() == e.VK_D) {
			if (centre.dir == Dir.R) {
				centre.x += 20;
				//GoRight();
			} else {
				centre.dir = Dir.R;
				//GoRight();
			}
		}else if(e.getKeyCode() == e.VK_J){
			islaunch=true;
			style = 2;
		}
	}

	public void draw(Graphics g) {
		if(isvasible==false){
			centre.dir=Dir.S;
			exp.setXY(centre.x, centre.y);
			exp.draw(g);
		}else{
			move();
			centre.draw(g);
			
		}
		if(good&&this.isvasible&&this.id!=Yard.otherId)bb.draw(g);
		if(islaunch){
			launch(g);
			islaunch=false;
		}
		if(issuperlaunch){
			superLanch(g);
			issuperlaunch=false;
		}
		for(int i=1;i<180;i++){
			if(bn[i]!=null&&bn[i].isvasible){
				bn[i].draw(g);
				bn[i].bang(Y.t);
				for(int j=0;j<Y.wall.length;j++)
					if(Y.wall[j]!=null){
						bn[i].hitWall(Y.wall[j]);
					}
			}
		}
	}
	public void move(){
		centre.oldx=centre.x;
		centre.oldy=centre.y;
		switch(centre.dir){
		case U:
			centre.y -=5;
			break;
		case D:
			centre.y +=5;
			break;
		case L:
			centre.x -=5;
			break;
		case R:
			centre.x +=5;
			break;
		case LU:
			centre.x-=5;
			centre.y-=5;
			break;
		case LD:
			centre.x-=5;
			centre.y+=5;
			break;
		case RU:
			centre.x+=5;
			centre.y-=5;
			break;
		case RD:
			centre.x+=5;
			centre.y+=5;
			break;
		case S:
			break;
		}
		
		if(centre.dir!=Dir.S){
			ptdir=centre.dir;
		}
		if(centre.x<5)centre.x=5;
		if(centre.y<5)centre.y=5;
		if(centre.x>555)centre.x=555;
		if(centre.y>530)centre.y=530;
		if(!good){
			Dir[] dirs= Dir.values();
			if(step==0){
				step=ra.nextInt(12)+3;
				int rn = ra.nextInt(dirs.length);
				centre.dir=dirs[rn];
			}
			step--;
			if(ra.nextInt(40)>37){
				islaunch=true;
				style = 0;
			}
			for(int i=0;i<Y.wall.length;i++){
				if(Y.wall[i]!=null){
					this.hitWall(Y.wall[i]);
				}
			}
		}
		this.collidesWithTanks(Y.t);
	}
	void locateDirection(){
		Dir oldDir=centre.dir;
		if(bL&&!bU&&!bR&&!bD)centre.dir=Dir.L;
		else if(!bL&&bU&&!bR&&!bD)centre.dir=Dir.U;
		else if(!bL&&!bU&&bR&&!bD)centre.dir=Dir.R;
		else if(!bL&&!bU&&!bR&&bD)centre.dir=Dir.D;
		else if(bL&&bU&&!bR&&!bD)centre.dir=Dir.LU;
		else if(bL&&!bU&&!bR&&bD)centre.dir=Dir.LD;
		else if(!bL&&bU&&bR&&!bD)centre.dir=Dir.RU;
		else if(!bL&&!bU&&bR&&bD)centre.dir=Dir.RD;
		else if(!bL&&!bU&&!bR&&!bD)centre.dir=Dir.S;
		if(centre.dir!=oldDir&&good){
			TankMoveMsg msg = new TankMoveMsg(id, centre.dir,Yard.levelnum);
			T.nc.send(msg);
		}
	}
	public class Node {
		Dir dir = Dir.U;
		int x, y;
		int oldx,oldy;
		int h = Yard.H;
		Node(int x, int y, Dir dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			oldx=x;
			oldy=y;
		}

		public void draw(Graphics g) {
			Color c = g.getColor();
			if(good&&tank.this.id!=Yard.otherId)
				g.setColor(Color.red);
			else if(good&&tank.this.id==Yard.otherId)
				g.setColor(Color.LIGHT_GRAY);
			else if(!good)g.setColor(Color.blue);
			g.fillOval(x, y, 40, 40);
			g.setColor(c);
			if(good)g.drawString("id:"+ id, x, y+20);
			if(good&&tank.this.id!=Yard.otherId)
			switch(ptdir){
			case U:
				g.drawLine(x+20, y+20, x+20, y-5);
				break;
			case D:
				g.drawLine(x+20, y+20, x+20, y+45);
				break;
			case L:
				g.drawLine(x+20, y+20, x-5, y+20);
				break;
			case R:
				g.drawLine(x+20, y+20, x+45, y+20);
				break;
			case LU:
				g.drawLine(x+20, y+20, x, y);
				break;
			case LD:
				g.drawLine(x+20, y+20, x, y+40);
				break;
			case RU:
				g.drawLine(x+20, y+20, x+40, y);
				break;
			case RD:
				g.drawLine(x+20, y+20, x+40, y+40);
				break;
			case S:
				break;
			}
		}
	}
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == e.VK_UP) {
			if (centre.y>0) {
				bU=false;
				//Goup();
			}
		} else if (e.getKeyCode() == e.VK_DOWN) {
			if (centre.y<560) {
				bD=false;
				//Godown();
			}
		} else if (e.getKeyCode() == e.VK_LEFT) {
			if (centre.x>0) {
				bL=false;
				//GoLeft();
			}
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			if (centre.x<560) {
				bR=false;
				//GoRight();
			}
		}
		locateDirection();
	}
	public Rectangle getRect() {
		return new Rectangle(centre.x,centre.y,40,40);
	}
	public boolean hitWall(Wall w){
		if(this.isvasible&&this.getRect().intersects(w.getRect())){
			centre.x=centre.oldx;
			centre.y=centre.oldy;
			return true;
		}
		return false;
	}
	public boolean collidesWithTanks(tank []t){
		for(int i =0;i<t.length;i++){
			if(this!=t[i]&&this!=null&&t[i]!=null&&i!=2){
				if(this.isvasible&&t[i].isvasible&&this.getRect().intersects(t[i].getRect())){
					centre.x=centre.oldx;
					centre.y=centre.oldy;
					return true;
				}
			}
		}
		return false;
	}
	
	private class BloodBar{
		public void draw(Graphics g){
			Color c=g.getColor();
			g.setColor(Color.orange);
			g.drawRect(centre.x, centre.y-10, 40, 10);
			int w = 40*life/100;
			g.fillRect(centre.x,centre.y-10,w,10);
			g.setColor(c);
		}
	}
	
	
	
	
	
	
	
}
