package com.mao.record.settings;
import java.util.*;
import com.mao.record.io.FileManager;

public class Settings
{
	private static String filename = "settings.txt";
	private static Map<String,String> map = new HashMap<String,String>();
	
	public static void put(String key, String value){
		map.put(key,del(String.valueOf(Float.parseFloat(value))));
	}
	
	public static String get(String key){
		return map.get(key);
	}
	
	public static void getSettings(){
		
		List<String> settings = new ArrayList<String>();
		FileManager.ReadFile("",filename,settings);
		
		for(String tmp:settings){
			String[] setting = tmp.split(":");
			put(setting[0],setting[1]);
		}
	}
	
	public static void updateSettings(){
		
		List<String> settings = new ArrayList<String>();
		
		for (String key : map.keySet()) {
			settings.add(key + ":" + map.get(key));
		}

		FileManager.WriteFile("",filename,settings);
	}

	public static String del(String str){
		if(str.endsWith(".0")){
			str = str.replace(".0","");
		}
		return str;
	}
	
}
