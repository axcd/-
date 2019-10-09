package com.mao.record.view;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.view.*;
import java.util.*;
import java.text.*;
import java.util.zip.*;
import android.graphics.*;
import android.widget.AdapterView.*;
import android.text.*;
import java.io.*;
import android.os.*;
import android.app.*;
import com.mao.record.settings.*;
import com.mao.record.io.FileManager;
import com.mao.record.*;

public class CalendarView extends LinearLayout
{
	private ImageView btnPre;
	private ImageView btnNext;
	private TextView txtDate;
	private GridView grid;
	
	private Calendar curDate = Calendar.getInstance();
	private String[] filenames = {"",""};
	private List<String>[] infos = {new ArrayList(),new ArrayList()};
	
	private DayView dayView;
	private int startDay=1;
	private Date startDate;
	private Date endDate;
	
    public CalendarView(Context context) {
        super(context);
        initView(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
	
	public void initView(Context context){

		//保证startDay以后显示下月
		Settings.getSettings();
		if(Settings.get("day")!=null){
			startDay = Integer.parseInt(Settings.get("day"));
		}
		if(curDate.get(Calendar.DAY_OF_MONTH)>=startDay){
			curDate.add(Calendar.MONTH,1);
		}
		
		//绑定数据
		bindControl(context);
		bindControlEvent();	

		//渲染calendar
		renderCalendar();
		
	}
	
	public void bindControl(Context context){

		LayoutInflater inflate = LayoutInflater.from(context);
		inflate.inflate(R.layout.calendar_view,this);
		
		btnPre = (ImageView)findViewById(R.id.pre);
		btnNext = (ImageView)findViewById(R.id.next);
		txtDate = (TextView)findViewById(R.id.date);
		grid = (GridView)findViewById(R.id.days);
	}
	
	public void bindControlEvent(){
		btnPre.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				curDate.add(Calendar.MONTH,-1);
				renderCalendar();
			}
		});
		
		btnNext.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				curDate.add(Calendar.MONTH,1);
				renderCalendar();
			}
		});
			
	}
	
	public void renderCalendar(){
		
		//设置日历开始结束日期
		if(Settings.get("day")!=null){
			startDay = Integer.parseInt(Settings.get("day"));
		}
		Calendar calendar = (Calendar)curDate.clone();	
		calendar.add(Calendar.MONTH,-1);
		calendar.set(Calendar.DAY_OF_MONTH,startDay);
		this.setStartDate(calendar);
		this.setEndDate(calendar);
		
		//显示工资月份
		this.setTextView(calendar);

		//读取数据
		this.setFilenames(calendar);
		this.readInfos();

		//设置数组开始日期
		int preDays = calendar.get(Calendar.DAY_OF_WEEK)-1;
		calendar.add(Calendar.DAY_OF_MONTH,-preDays);
		
		//设置数组长度
		int preMonthDays = calendar.getActualMaximum(calendar.DATE);
		int maxDate = preMonthDays+preDays;
		int maxCellCount = (int)Math.ceil(maxDate/7.0)*7;
		
		//填充数组
		ArrayList<Date> cells = new ArrayList<>();
		while(cells.size()<maxCellCount)
		{ 
			cells.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH,1);
		}
		
		//适配器
		grid.setAdapter(new CalendarAdapter(getContext(),cells));
	}
	
	
	public void setSelect(DayView vi){
		if(this.dayView!=null){
			this.dayView.isSelected=false;
			this.dayView.invalidate();
		}
		this.dayView = vi;
		this.dayView.isSelected=true;
		this.dayView.invalidate();
	}
	
	public void updateInfos(String info){

		int day = Integer.parseInt(info.split(" ")[0]);
		if(day>=this.startDay){
			for(String tmp:this.infos[0]){
				if(tmp.split(" ")[0].equals(info.split(" ")[0])){
					this.infos[0].remove(tmp);
					break;
				}
			}
			this.infos[0].add(info);
			Collections.sort(this.infos[0]);
			FileManager.WriteFile(filenames[0].substring(0,4)+"/",filenames[0],infos[0]);
		}else{
			for(String tmp:this.infos[1]){
				if(tmp.split(" ")[0].equals(info.split(" ")[0])){
					this.infos[1].remove(tmp);
					break;
				}
			}
			this.infos[1].add(info);
			Collections.sort(this.infos[1]);
			FileManager.WriteFile(filenames[1].substring(0,4)+"/",filenames[1],infos[1]);
		}
	}
	
	public DayView getDayView(){
		return this.dayView;
	}
	
	public void readInfos(){
		for(int i=0;i<2;i++){
			this.infos[i] = new ArrayList<String>();
			FileManager.ReadFile(filenames[i].substring(0,4)+"/",filenames[i],this.infos[i]);
		}
	}

	public Date getStartDate(){
		return this.startDate;
	}
	
	public void setStartDate(Calendar calendar){
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.DATE,-1);
		this.startDate = cal.getTime();
	}
	
	public Date getEndDate(){
		return this.endDate;
	}

	public void setEndDate(Calendar calendar){
		Calendar cal = (Calendar) calendar.clone();
		cal.add(Calendar.MONTH,1);
		this.endDate = cal.getTime();
	}
	
	public void setTextView(Calendar calendar){
		if(startDay==1){
			Calendar cal = (Calendar) calendar.clone();
			cal.add(Calendar.DATE,1);
			txtDate.setText(getFormat(cal.getTime(),"yyyy年MM月"));
		}else{
			txtDate.setText(getFormat(this.endDate,"yyyy年MM月"));
		}
	}
	
	public void setFilenames(Calendar calendar){
		if(startDay==1){
			Calendar cal = (Calendar) calendar.clone();
			cal.add(Calendar.DATE,1);
			this.filenames[0] = getFormat(cal.getTime(),"yyyyMM");
			this.filenames[1] = getFormat(cal.getTime(),"yyyyMM");
		}else{
			this.filenames[0] = getFormat(this.startDate,"yyyyMM");
			this.filenames[1] = getFormat(this.endDate,"yyyyMM");
		}
	}
	
	public String getFormat(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String sformat = sdf.format(date);
		return sformat;
	}
	
	public String[] getFilenames(){
		return this.filenames;
	}
	
	public void clearDay(int day){
		
		if(day>=this.startDay){
			for(String tmp:this.infos[0]){
				if(tmp.split(" ")[0].equals(addZero(day))){
					this.infos[0].remove(tmp);
					break;
				}
			}
			Collections.sort(this.infos[0]);
			FileManager.WriteFile(filenames[0].substring(0,4)+"/",filenames[0],infos[0]);
		}else{
			for(String tmp:this.infos[1]){
				if(tmp.split(" ")[0].equals(addZero(day))){
					this.infos[1].remove(tmp);
					break;
				}
			}
			Collections.sort(this.infos[1]);
			FileManager.WriteFile(filenames[1].substring(0,4)+"/",filenames[1],infos[1]);
		}
	}
	
	public ArrayList<String> getList(){
		ArrayList<String> list = new ArrayList<String>();
		for(String tmp:infos[0]){
			if(Integer.parseInt(tmp.split(" ")[0])>=startDay){
				list.add(tmp);
			}
		}
		
		for(String tmp:infos[1]){
			if(Integer.parseInt(tmp.split(" ")[0])<startDay){
				list.add(tmp);
			}
		}
		
		return list;
	}
	
	public void openDailog(){
		Activity activity = (Activity)getContext();
		Intent info = new Intent(activity,InfoActivity.class);
		activity.startActivity(info);
	}
	
	public String addZero(int day){
		return day>9?String.valueOf(day):"0"+String.valueOf(day);
	}
	
	private class CalendarAdapter extends ArrayAdapter{

		private LayoutInflater inflater;

		public CalendarAdapter(Context context,ArrayList<Date> days){
			super(context,R.layout.day_view,days);
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{

			Date date = (Date)getItem(position);
			if(convertView == null){
				convertView = inflater.inflate(R.layout.day_view,parent,false);
			}

			
			final DayView cv= (DayView)convertView;
			final int day = date.getDate();	
			Date now = new Date();
			
			if(date.after(CalendarView.this.getStartDate()) && date.before(CalendarView.this.getEndDate())){
				cv.setTextColor(Color.parseColor("#000000"));
				cv.setText(String.valueOf(day));
				cv.setInfoByIndex(0,addZero(day));
				
				if(day>=startDay){
					for(String tmp:infos[0]){
						if(tmp.split(" ")[0].equals(cv.getInfoByIndex(0))){
							cv.setInfo(tmp.split(" "));
							break;
						}
					}					
				}else{
					for(String tmp:infos[1]){
						if(tmp.split(" ")[0].equals(cv.getInfoByIndex(0))){
							cv.setInfo(tmp.split(" "));
							break;
						}
					}		
				}

			}else{
				cv.setTextColor(Color.parseColor("#666666"));
			}

			if(now.getDate() == date.getDate() && now.getMonth() == date.getMonth() && now.getYear() == date.getYear()){
				cv.setTextColor(Color.parseColor("#FF0000"));
				cv.isToday = true;			
			}
			
			cv.setOnClickListener(new OnClickListener(){		
					public void onClick(View view){
						if(!cv.getText().toString().equals("")){
							setSelect(cv);
						}
					}
				});
				
			cv.setOnLongClickListener( new OnLongClickListener(){
				public boolean onLongClick(View view){
					setSelect(cv);
					openDailog();
					return true;
				}
			});

			 return cv;
		}
	}
	
}
