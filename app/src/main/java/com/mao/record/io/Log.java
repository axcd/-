package com.mao.record.io;

import java.io.*;
import android.os.Environment;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Log
{
	public static boolean isAppend = false;
	public static boolean isLogging = true;
	
	public static void info(String info){
		
		if(!isLogging){
			return;
		}

		try {
			File dir = new File(Environment.getExternalStorageDirectory(),"Android/data/com.mao.record/log/");

			if(!dir.exists()){   
				dir.mkdir();  
			}

			File file = new File(Environment.getExternalStorageDirectory(),"Android/data/com.mao.record/log/log.txt");

			if(!file.exists()){
				file.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(file, isAppend));
			SimpleDateFormat sdf = new SimpleDateFormat("[ yyyy-MM-dd HH:mm:ss ]  ");
			String date = sdf.format(Calendar.getInstance().getTime());
			bw.write(date+info+"\r\n");
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
