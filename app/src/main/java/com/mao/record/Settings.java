package com.mao.record;
import java.util.*;

public class Settings
{
	private static String filename = "settings.txt";
	
	private static String day = "1";
	private static String base;
	private static String achi;
	private static String diff;
	private static String noun;
	private static String fund;

	public static void setDay(String day)
	{
		Settings.day = day;
	}

	public static String getDay()
	{
		return day;
	}

	public static void setBase(String base)
	{
		Settings.base = base;
	}

	public static String getBase()
	{
		return base;
	}

	public static void setAchi(String achi)
	{
		Settings.achi = achi;
	}

	public static String getAchi()
	{
		return achi;
	}

	public static void setDiff(String diff)
	{
		Settings.diff = diff;
	}

	public static String getDiff()
	{
		return diff;
	}

	public static void setNoun(String noun)
	{
		Settings.noun = noun;
	}

	public static String getNoun()
	{
		return noun;
	}

	public static void setFund(String fund)
	{
		Settings.fund = fund;
	}

	public static String getFund()
	{
		return fund;
	}

	
	
	public static void getSettings(){
		
		List<String> settings = new ArrayList<String>();
		FileManager.ReadFile(filename,settings);
		
		for(String tmp:settings){
			String[] setting = tmp.split(":");
			if(setting[0].equals("day")){
				setDay(setting[1]);
			}else if(setting[0].equals("base")){
				setBase(setting[1]);
			}else if(setting[0].equals("achi")){
				setAchi(setting[1]);
			}else if(setting[0].equals("diff")){
				setDiff(setting[1]);
			}else if(setting[0].equals("noun")){
				setNoun(setting[1]);
			}else if(setting[0].equals("fund")){
				setFund(setting[1]);
			}
		}
	}
	
	public static void updateSettings(){
		
		List<String> settings = new ArrayList<String>();
		
		settings.add("day:" + day);
		settings.add("base:" + base);
		settings.add("achi:" + achi);

		settings.add("diff:" + diff);
		settings.add("noun:" + noun);
		settings.add("fund:" + fund);
		
		FileManager.WriteFile(filename,settings);
	}
	
}
