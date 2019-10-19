package com.mao.record;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.view.*;
import android.content.*;
import android.widget.PopupMenu.*;
import com.mao.record.view.CalendarView;
import com.mao.record.io.FileManager;
import com.mao.record.io.Log;
import android.support.v4.app.*;
import android.*;
import android.content.pm.*;

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
		checkPermission();
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
				Intent sub = new Intent(MainActivity.this, SubActivity.class);
				startActivity(sub);
				return true;
			case R.id.mainMenuSet:
				Intent set = new Intent(MainActivity.this, SetActivity.class);
				startActivity(set);
				return true;
			case R.id.mainMenuAbout:
				Intent about = new Intent(this, AboutActivity.class);
				startActivity(about);
				return true;

		}
		return super.onOptionsItemSelected(item);
	}

	@Override  
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{  
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{   
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}  

	private void checkPermission()
	{
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
		{
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		}
	}
}

