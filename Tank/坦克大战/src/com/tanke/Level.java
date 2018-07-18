package com.tanke;


public class Level {
	
	 int tanknum[]={2,3,4,5,6};
	 node tankLayout[][]={
			new node[]{new node(150,50),new node(225,50),new node(300,50)},
			new node[]{new node(150,50),new node(225,50),new node(300,50)},
			new node[]{new node(150,50),new node(225,50),new node(300,50),
			           new node(150,400),new node(225,400),new node(300,400)},
			new node[]{new node(150,50),new node(225,50),new node(300,50)},
		    new node[]{new node(150,50),new node(225,50),new node(300,50)},
	};
	int wallnum[]={2,2,2,4};
	wnode wallLayout[][]={
			new wnode[]{new wnode(1,1,1,1)},
			new wnode[]{new wnode(100,200,355,20),new wnode(100,360,355,20)},
			new wnode[]{new wnode(255,100,50,330),new wnode(100,240,355,50)},
			new wnode[]{new wnode(125,115,300,60),new wnode(245,175,60,200),new wnode(75,375,400,60)}
	};
    class node{
    	int x,y;
    	node(int x,int y){
    		this.x=x;
    		this.y=y;
    	}
    }
    class wnode{
    	int x,y,w,h;
    	public wnode(int x, int y, int w, int h) {
    		this.x = x;
    		this.y = y;
    		this.w = w;
    		this.h = h;
    	}
    }

}
