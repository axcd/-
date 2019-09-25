package com.mao.record;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.content.*;
import android.widget.PopupMenu.*;
import com.mao.record.vw.CalendarView;
import com.mao.record.io.FileManager;
import com.mao.record.io.Log;

public class MainActivity extends Activity
{
	private static CalendarView calendarView;

	public static void setCalendarView(CalendarView calendarView)
	{
		MainActivity.calendarView = calendarView;
	}

	public static CalendarView getCalendarView()
	{
		return calendarView;
	}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {	
	
		Log.info("****** start ******* ");
		Log.isAppend = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		calendarView = (CalendarView)findViewById(R.id.calendarView);

	}

    @Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.mainMenuSub:
				Intent sub = new Intent(MainActivity.this,SubActivity.class);
				startActivity(sub);
				return true;
			case R.id.mainMenuSet:
				Intent set = new Intent(MainActivity.this,SetActivity.class);
				startActivity(set);
				return true;
			case R.id.mainMenuExit:
				finish();
				return true;
				
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override  
	public boolean onKeyDown(int keyCode,KeyEvent event){  
		if(keyCode==KeyEvent.KEYCODE_BACK){   
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}  
	
}
