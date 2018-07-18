package com.tanke;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TankMoveMsg implements Msg{
	int msgType=Msg.TANK_MOVE_MSG;
	int id;
	Dir dir;
	Yard Y;
	int level;
	public TankMoveMsg(int id,Dir dir,int level){
		this.id=id;
		this.dir=dir;
		this.level=level;
	}
	public TankMoveMsg(Yard Y) {
		this.Y=Y;
	}
	public void send(DatagramSocket ds, String IP, int udpPort) {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		DataOutputStream dos =new DataOutputStream(baos);
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(level);
			dos.writeInt(dir.ordinal());
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
			int level=dis.readInt();
			Dir dir= Dir.values()[dis.readInt()];
			if(Y.t[2].id==id){
				Y.t[2].centre.dir=dir;
				Y.otherLevelNum=level;
				Y.otherId=id;
System.out.println(id);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
