package com.mao.record.view;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.graphics.*;
import android.os.*;
import java.io.*;
import com.mao.record.io.Log;
import android.widget.GridLayout.*;

public class DayView extends TextView 
{
	public boolean isToday = false;
	public boolean isSelected = false;
	private String[] info = {"","","","",""};        
	private Paint paint = new Paint();
	private int w = 45;
	private int h = 60;
	
	public DayView(Context context){
		super(context);
		initView(context);
	}
	
	public DayView(Context context,AttributeSet attrs){
		super(context,attrs);
		initView(context);
	}
	
	public DayView(Context context,AttributeSet attrs, int defStyleAttr) {
		super(context,attrs,defStyleAttr);
		initView(context);
	}

	private void initView(Context context){
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		w = getWidth()/2;
		h = getHeight()/2;
		canvas.translate(w,h);

		if(!getText().toString().equals("")){
			if(info[1].equals("休息")){
				drawRect(canvas,3-w,h/2,w-2,h,"#00F00F",40);
				drawShift(canvas,"#0F0F0F",200,"#F0F0F0","休");
			}else if(info[4].equals("加班")){
				drawRect(canvas,3-w,h/2,w-2,h,"#F0000F",20);
				if(info[1].contains("白班")){
					drawShift(canvas,"#F0000F",150,"#FFFFF0","白");
				}else if(info[1].contains("夜班")){
					drawShift(canvas,"#0000FF",150,"#FFFFF0","夜");
				}
				if(info[3].equals("1.5倍") && !info[2].equals("0h")){
					drawHour(canvas,info[2],"#0000FF",255);
				}else if(info[3].equals("2.0倍")){
					drawHour(canvas,info[2],"#FF0000",200);
				}else if(info[3].equals("3.0倍")){
					drawHour(canvas,info[2],"#FF0FF0",255);
				}
			}else if(!info[4].equals("")){
				drawRect(canvas,3-w,h/2,w-2,h,"#0000FF",30);
				if(info[1].contains("白班")){
					drawShift(canvas,"#F0000F",150,"#FFFFF0",info[4]);
				}else if(info[1].contains("夜班")){
					drawShift(canvas,"#0000FF",150,"#FFFFF0",info[4]);
				}
				if(!info[2].equals("0h")){
					drawHour(canvas,info[2],"#666666",255);
				}
			}
			
			//标记今天
			if(isToday){
				drawCircle(canvas,"#FF0000",60);
			}
			
			//标记选中
			if(isSelected){
				drawCircle(canvas,"#0000FF",60);
			}
			drawRect(canvas,4-w,3-h,w+1,h,"#999999",80);
			drawRect(canvas,w-2,3-h,w+1,h,"#000000",80);
			drawRect(canvas,3-w,h-3,w,h,"#000000",80);
		}else{
			drawRect(canvas,4-w,3-h,w+1,h,"#222222",80);
		}
	}
	
	public void drawRect(Canvas canvas,int x1,int y1,int x2,int y2,String color,int alpha){
		paint.setColor(Color.parseColor(color));
		paint.setAlpha(alpha);
		canvas.drawRect(x1,y1,x2,y2,paint);	
	}
	
	public void drawShift(Canvas canvas,String bkgColor,int alpha,String fontColor,String shift){
		paint.setTextSize(0.38f*h);
		int x = (int)paint.measureText(shift.substring(0,1))/2;
		int y = (int) Math.abs(paint.ascent() + paint.descent())/2;
		int r = (int)Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		paint.setColor(Color.parseColor(bkgColor));
		paint.setAlpha(alpha);
		canvas.drawCircle(r-w+7,r-h+7,r+2,paint);	
		paint.setColor(Color.parseColor(fontColor));
		canvas.drawText(shift,0,1,r-w-x+7,r-h+y+7,paint);	
	}
	
	public void drawHour(Canvas canvas, String hour, String hcolor, int alpha){
		paint.setTextSize(0.5f*h);
		paint.setColor(Color.parseColor(hcolor));
		paint.setAlpha(alpha);
		int x = -(int)(paint.measureText(hour)/2);
		int y = getHeight()/2-5;
		canvas.drawText(hour,x,y,paint);	
	}
	
	public void drawCircle(Canvas canvas,String bkgColor,int alpha){
		paint.setColor(Color.parseColor(bkgColor));
		paint.setAlpha(alpha);
		canvas.drawCircle(0,0,w>h?h-2:w-2,paint);
	}
	
	public void setInfo(String[] info){
		this.info = info;
	}
	
	public String[] getInfo(){
		return this.info;
	}
	
	public void setInfoByIndex(int i, String str){
		this.info[i] = str;
	}
	
	public String getInfoByIndex(int i){
		return info[i];
	}
	
	public String getInfoString(){
		String infostr=info[0];
		for(int i=1;i<info.length;i++){
			infostr += " "+info[i];
		}
		return infostr;
	}
	
	@Override  
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)     
	{   
		int width = MeasureSpec.getSize(widthMeasureSpec);  
		int height = (int)(1.25*width);      
		setMeasuredDimension(width,height);
	} 

}
