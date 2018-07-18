package com.tanke;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TankNewMsg implements Msg {
	int msgType = Msg.TANK_NEW_MSG;
	tank tank;
	Yard Y;
	public TankNewMsg(tank tank){
		this.tank=tank;
	}
	public TankNewMsg(Yard Y){
		this.Y=Y;
	}
	public void send(DatagramSocket ds,String IP,int udpPort) {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		DataOutputStream dos =new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(tank.id);
			dos.writeInt(tank.centre.x);
			dos.writeInt(tank.centre.y);
			dos.writeInt(tank.centre.dir.ordinal());
			dos.writeBoolean(tank.good);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] buf= baos.toByteArray();
		try {
			DatagramPacket dp = new DatagramPacket(buf,buf.length,new InetSocketAddress(IP,udpPort));
			ds.send(dp);
		} catch (SocketException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void parse(DataInputStream dis) {
		try {
			int id=dis.readInt();
			if(Y.t[1].id==id)return;
			int x=dis.readInt();
			int y = dis.readInt();
			Dir dir= Dir.values()[dis.readInt()];
			boolean good=dis.readBoolean();
			tank t=new tank(Y.tbg,x,y,good);
			t.id=id;
			Y.t[2]=t;
			
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
