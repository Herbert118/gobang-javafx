package com.liu.util;

import com.alibaba.fastjson.JSON;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

/**
 * @author 刘海波
 * @description 工具类，封装了json格式和serialization两种读取方法
 * @param <T>
 * 
 * TODO:泛型的实现显然是不够好的, 待改进
 */
public class ObjectInputUtil <T>{
	private static final long serialVersionUID = 7495446426670326572L;
	private BufferedReader reader;
	private ObjectInputStream ois;
	public static final int SERIALFORMAT = 1;
	public static final int JSONFORMAT = 2;
	private Class<T> cla;
	public ObjectInputUtil(String path, Class<T> cla,int choice) throws FileNotFoundException {
		this.cla = cla;
		switch(choice) {
		case JSONFORMAT:
		
				reader = new BufferedReader(new FileReader(path));
			break;
		case SERIALFORMAT:
			try {
				ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("wrong argument!");
		
		}
	}

	public ObjectInputUtil(String path)throws FileNotFoundException{
		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	public T readSerialObject() throws Exception {
		if(ois == null) {
			throw new Exception("wrong choice!");
		}
		else {
			Object object = ois.readObject();
			if(object != null) {
					return (T)object;
			}
			else {
				throw new Exception("Can't read Object");
			}
		}
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public T readSingalJsonObject() throws Exception {
		if(reader == null) {
			throw new Exception("wrong choice!");
		}
	
		Object object = JSON.parseObject(readJsonString(),cla);
		return (T)object;
		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> readJsonArrayList() throws Exception {
		if(reader == null) {
			throw new Exception("wrong choice!");
		}
		return (ArrayList<T>)JSON.parseArray(readJsonString(), cla);
	}
	public ObservableList<T> readJsonObservableList() throws Exception {
		if(reader == null) {
			throw new Exception("wrong choice!");
		}
		return (ObservableList<T>)JSON.parseArray(readJsonString(), cla);
	}
	
	public String readALine() {
		String line = null;
		try {
		  line = reader.readLine();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return line;
	}
	
	public String readJsonString() {
		String JsonString = "";
		String line;
		while((line = this.readALine())!= null) {
			JsonString += line;
		}
		return JsonString;
	}
	
	
}
