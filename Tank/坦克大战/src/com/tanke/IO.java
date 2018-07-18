package com.tanke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class IO {
	
	public void IOWrite(Object[] obj,String path) {
		Object o = new Object();
		List list = new ArrayList<Object>();
		for(int i = 0;i<obj.length;i++) {
			o = obj[i];
			list.add(o);
		}
		File file = new File(path);
		FileOutputStream out;
		try {
			out = new FileOutputStream(file);
			ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(list);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
	}
	
	public List IORead(String path) {
		List list = null;
        File file =new File(path);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            	list=(ArrayList<Object>)objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
    	//	System.out.println(((User)list.get(1)).getUserid());
            return list;
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
	}
	
}
