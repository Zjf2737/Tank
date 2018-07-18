package com.tanke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


/*
 * ×Óµ¯
 */
public class bullet {
	int x,y;
	Dir d=Dir.U;
	int r=Yard.H;
    int style;
	boolean isvasible=true;
	bullet(int s,Dir d){	
		style=s;
		this.d=d;
	}
	
	bullet(int s){
		style = s;
	}
	
	public void fly(){
		if(d==Dir.L){
			x=x-r;
		}
		else if(d==Dir.R){
			x+=r;
		}
		else if(d==Dir.U){
			y-=r;
		}
		else if(d==Dir.D){
			y+=r;
		}else if(d==Dir.LD){
			y+=r;
			x-=r;
		}else if(d==Dir.LU){
			y-=r;
			x-=r;
		}else if(d==Dir.RD){
			x+=r;
			y+=r;
		}else if(d==Dir.RU){
			x+=r;
			y-=r;
		}
	}
	public boolean judge(){
		if(x<0||y<0||x>590||y>590)return false;
		else return true;
	}
	public void draw(Graphics g){
		if(judge()){
			g.drawImage(Yard.bullet_, x, y,null);
		    fly();
		}
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,r,r);
	}
	public boolean hitWall(Wall w){
		if(this.isvasible&&this.getRect().intersects(w.getRect())){
			this.isvasible = false;
			return true;
		}
		return false;
		
	}
	public  void bang(tank[] t){
		int i;
		if(style!=0)
		for(i=3;i<t.length;i++){
			/*if( (t[i]!=null)&& (x>=t[i].centre.x-10) &&(x<=t[i].centre.x+40)&&(y>=t[i].centre.y-10) &&(y<=t[i].centre.y+40) ) {
				//t[i].move = 20;
				isvasible=false;
				t[i].isvasible=false;
			}*/
			if(t[i]!=null&&this!=null)
			if(this.getRect().intersects(t[i].getRect())&&t[i].isvasible){
				isvasible=false;
				t[i].isvasible=false;
			}
			
		}
		else 
			for(i=1;i<3;i++){
				if(t[i]!=null&&this!=null&&i!=2)
					if(this.getRect().intersects(t[i].getRect())&&t[i].isvasible){
						t[i].setLife(t[i].getLife()-20);
						if(t[i].getLife()<=0){
						    t[i].isvasible=false;
						}
						isvasible=false;
					}
			}
	}
		
}
















