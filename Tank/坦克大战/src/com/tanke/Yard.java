package com.tanke;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.frame.runTank;
import com.frame.runTank.Mywindows;



public class Yard {

	/**
	 * ̹�˵ĳ���
	 */
	PaintThread paintThread=new PaintThread();
	runTank rtk=new runTank();
	bgmMusic bgm=new bgmMusic();
	public int soon=50;        //�̵߳ȴ�ʱ��
	public static int H = 10;  //��С
	public static tank[] t = new tank[50];
	public static Wall wall[]=new Wall[10];
	int yanchi=0;
	boolean isPause=false;
	PopupMenu pop=new PopupMenu();
	MenuItem gameExit= new MenuItem("�˳�",new MenuShortcut(KeyEvent.VK_X));
	JFrame f=new JFrame("̹�˴�ս");
	tankBackGround tbg=new tankBackGround();
	static Level level=new Level();
	static int levelnum=1;
	int otherLevelNum=1;
	static int otherId=1;
	BufferedImage table;
	static BufferedImage up_;
	static BufferedImage down_;
	static BufferedImage left_;
	static BufferedImage right_;
	static BufferedImage bullet_;
	static BufferedImage boom_;
	private MenuBar mb=new MenuBar();
	Menu file = new Menu("�ļ�");
	MenuItem saveItem = new MenuItem("����");
	MenuItem exitItem=new MenuItem("�˳�",new MenuShortcut(KeyEvent.VK_X));
	boolean isBGM=true;
    public static void main(String[] args) {
	    Yard y=new Yard();
	    y.init();
	}
	public void init() {
		try {
			table=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\yard1.jpg"));
			up_=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\tanke��.jpg"));
			down_=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\tanke��.jpg"));
			left_=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\tanke��.jpg"));
			right_=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\tanke��.jpg"));
			bullet_=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\bullet.jpg"));
			boom_=ImageIO.read(new File("D:\\ͼƬ\\̹��\\image\\image\\boom.jpg"));
		t[1] = new tank(tbg,300,300,true);
		f.setLayout(new BorderLayout(20,5));
		tbg.setBounds(0, 0, 600, 600);
		f.setBounds(300, 300, 720, 650);
		f.setBackground(Color.WHITE);
		//pop.add(quick);
		tbg.addMouseListener(new Mymouse());
		f.addKeyListener(new Mykey());
		new Thread(paintThread).start();
		new Thread(bgm).start();
		f.add(tbg);
		f.setVisible(true);
		f.setResizable(false);
		exitItem.addActionListener(new myActionListener());
		gameExit.addActionListener(new myActionListener());
		pop.add(gameExit);
		//final Panel p= new Panel();
		//p.setPreferredSize(new Dimension(300,160));
		tbg.add(pop);
		tbg.addMouseListener(new Mymouse1());
		file.add(saveItem);
		file.add(exitItem);
		mb.add(file);
		f.setMenuBar(mb);
		f.addWindowListener(new Mywindows());
		tbg.nc.connect("127.0.0.1", TankServer.TCP_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public class myActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("�˳�")){
				new runTank().init();
				System.exit(0);
			}
		}
		
	}
	public class Mymouse extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getX()>650&&e.getY()>45&&e.getX()<670&&e.getY()<65){
				if(isPause){
					isPause=false;
					cls();                                 
					Start();                                
				}else{
					isPause=true;
					cls();                                  
					Stop();
				}
			}else if(e.getX()>650&&e.getY()>85&&e.getX()<670&&e.getY()<105){
				if(isBGM){
					isBGM=false;
					bgm.aau.stop();
				}
				else {
					bgm.aau.play();
					isBGM=true;
				} 
			}
		}
	}
	public class Mymouse1 extends MouseAdapter{
		public void mouseReleased(MouseEvent e){
			if(e.isPopupTrigger()){
				pop.show(tbg,e.getX(),e.getY());
			}
		}
	}
	public void cls(){
		tbg.repaint();
	}
	public void Stop(){
		paintThread.pause();
	}
	public void Start(){
		paintThread.start();
	}
	public class Mywindows extends WindowAdapter {         //�رմ���
		public void windowClosing(WindowEvent e) {
			f.setVisible(false);
			bgm.aau.stop();
			new runTank().init();
		}
	}
	
	public class Mykey extends KeyAdapter {             //ʵ��̹���ƶ�
		@Override
		public void keyReleased(KeyEvent e) {
			if(t[1].isvasible)
				t[1].keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			int k = e.getKeyCode();
			//System.out.println(k);
			if(t[1].isvasible)
			t[1].keypress(e);
			if(k==e.VK_P){
				if(isPause){
					isPause=false;
					cls();                                 
					Start();                                
				}else{
					isPause=true;
					cls();                                  
					Stop();
				}
			}
		}
		
	}
    class tankBackGround extends JPanel
	{
    	NetClient nc=new NetClient(Yard.this);
		public void paint(Graphics g){
			g.clearRect(600, 0, 100, 600);
			g.drawImage(table, 0, 0,600,610, null);
			g.drawString("tanks   life:" + t[1].getLife(),10, 50);
			g.drawString("Levels:" +"��"+ levelnum+"��",10, 30);
			if(t[2]==null)g.drawString("��ǰ�������ӣ�",10,80);
			else g.drawString("Id:"+otherId+" ��ǰ�ؿ���:"+otherLevelNum, 10, 80);
			int killed=0;
			for(int i=0,j=3;i<level.tankLayout[levelnum].length;i++,j++){
				/*if(t[i]!=null&&yanchi==i)
					t[i].tankRun();*/
				if(t[j]==null)
					t[j]=new tank(this,false,level.tankLayout[levelnum][i].x,level.tankLayout[levelnum][i].y);
				/*if(t[i]!=null&&t[i].isvasible==false&&!t[i].exp.getLive()){
					t[i]=new tank(this,false);
				}*/
				if(t[j].isvasible==false&&!t[j].exp.getLive()){
					killed++;
					if(killed==level.tankLayout[levelnum].length){
						levelnum++;
						if(levelnum==4){
							paintThread.setrunning(false);
							Font f=g.getFont();
							Color c=g.getColor();
							g.setColor(Color.red);
							g.setFont(new Font("Dialog",2,50));
							g.drawString("�����ؿ����ȿ�����!",100,200);
							g.setFont(f);
							g.setColor(c);
							return;
						}
						int tid=t[1].id;
						//t[1] = new tank(tbg,0,0,true);
						//t[1].id=tid;
						for(int i1=3;i1<t.length;i1++)
						t[i1]=null;
						wall=new Wall[level.wallLayout[levelnum].length];
					}
				}
			}
			for(int i=0,j=0;i<level.wallLayout[levelnum].length;i++,j++){
				if(wall[i]==null){
					wall[i]=new Wall(level.wallLayout[levelnum][j].x,level.wallLayout[levelnum][j].y,
							level.wallLayout[levelnum][j].w,level.wallLayout[levelnum][j].h);
				}
			}
			for(int i=1;i<t.length;i++){
				if(t[i]!=null)
					t[i].draw(g);
			}
			//System.out.println(isPause);
			Color c = g.getColor();
			g.setColor(Color.black);
			if(!isPause){   //����ͣ
				g.drawString("��ͣ:", 610, 60);
				g.drawRect(650, 45, 20, 20);
				//g.fillRect(650, 45, 20, 20);
			}else{       //��ͣ
				g.drawString("��ͣ:", 610, 60);
				g.drawRect(650, 45, 20, 20);
				g.fillRect(650, 45, 20, 20);
			}
			if(isBGM){
				g.drawString("BGM", 610, 100);
				g.drawRect(650, 85, 20, 20);
			}else{
				g.drawString("BGM", 610, 100);
				g.drawRect(650, 85, 20, 20);
				g.fillRect(650, 85, 20, 20);
			}
			g.setColor(c);
			for(int i=0;i<wall.length;i++){
				if(wall[i]!=null){
					wall[i].draw(g);
				}
			}
			if(t[1].life<=0){
				paintThread.setrunning(false);
				Font f=g.getFont();
				Color c1=g.getColor();
				g.setColor(Color.red);
				g.setFont(new Font("Dialog",2,50));
				g.drawString("��Ϸ����!",200,200);
				g.setFont(f);
				g.setColor(c1);
				return;
			}
		}
	}
	private class PaintThread implements Runnable{       //�߳̿��ƽ���ˢ��
		private boolean running = true;
		private boolean pause = false;
		public void run(){
			while(running){
				//if(gameover==true)pause=true;
				if(pause)continue;
				else cls();
				try{
					//System.out.println("�߳��Զ�ˢ����");
					Thread.sleep(soon);
					if(yanchi<15)yanchi++;
					else {
						yanchi-=13;
						if(t[1].getLife()<100&&t[1].isvasible)
						t[1].setLife(t[1].getLife()+10);
					}
				}catch(InterruptedException e){
					e.printStackTrace();  
				}
			}
		}
		public void pause() {
			this.pause = true;
		}
		public void start(){
			this.pause=false;
		}
		public void setrunning(boolean r){
			running=r;
		}
	}

    class bgmMusic implements Runnable{//�����������߳�
             
			 //String url;
    	AudioClip aau=null;
        // public boolean StopThread_bgmMusic = false;//���ڽ������������߳�
			public void run(){
                try{
                    File  f = new File("C:\\andriod\\text\\src\\image\\bgm.wav");
                    URI uri = f.toURI();
                    URL url = uri.toURL();  //������ַ
                    aau = Applet.newAudioClip(url);
                    aau.loop();
                      //ѭ������
                    } catch (Exception e){ 
                     e.printStackTrace();
                    }     
			  }
          
          }

}
