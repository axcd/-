package com.mao.record;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.text.*;
import com.mao.record.vw.*;

public class InfoActivity extends Activity
{
	
	private Spinner spDown1;
	private Spinner spDown2;
	private Spinner spDown3;

	private Spinner spDown4;
	private List<String> list1;
	private List<String> list2;
	private List<String> list3;
	private List<String> list4;
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	private ArrayAdapter<String> adapter4;
	private DayView dayView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {	

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
		dayView = MainActivity.getCalendarView().getDayView();

		spDown1=(Spinner) findViewById(R.id.calendarviewSpinner1);
		list1=new ArrayList<String>();
		list1.add("白班");
		list1.add("夜班");
		list1.add("休息");
		adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list1);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDown1.setAdapter(adapter1);
		
		spDown2=(Spinner) findViewById(R.id.calendarviewSpinner2);
		list2=new ArrayList<String>();
		list2.add("0.0h");
		list2.add("0.5h");
		list2.add("1.0h");
		list2.add("1.5h");
		list2.add("2.0h");
		list2.add("2.5h");
		list2.add("3.0h");
		list2.add("3.5h");
		list2.add("4.0h");
		list2.add("4.5h");
		list2.add("5.0h");
		list2.add("5.5h");
		list2.add("6.0h");
		list2.add("6.5h");
		list2.add("7.0h");
		list2.add("7.5h");
		list2.add("8.0h");
		list2.add("8.5h");
		list2.add("9.0h");
		list2.add("9.5h");
		list2.add("10.0h");
		list2.add("10.5h");
		list2.add("11.0h");
		list2.add("11.5h");
		list2.add("12.0h");
		list2.add("12.5h");
		adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDown2.setAdapter(adapter2);
		spDown2.setSelection(6);
	
		spDown3=(Spinner) findViewById(R.id.calendarviewSpinner3);
		list3=new ArrayList<String>();
		list3.add("1.5倍");
		list3.add("2.0倍");
		list3.add("3.0倍");
		adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list3);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDown3.setAdapter(adapter3);
		
		spDown4=(Spinner) findViewById(R.id.calendarviewSpinner4);
		list4=new ArrayList<String>();
		list4.add("加班");
		list4.add("调休");
		list4.add("事假");
		list4.add("病假");
		list4.add("年休");
		adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list4);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spDown4.setAdapter(adapter4);
		
		setSpinnerValue(spDown1,dayView.getInfoByIndex(1));
		setSpinnerValue(spDown2,dayView.getInfoByIndex(2));
		setSpinnerValue(spDown3,dayView.getInfoByIndex(3));
		setSpinnerValue(spDown4,dayView.getInfoByIndex(4));

	}

	public void onCancel(View view){
		finish();
	}
	

	public void onOk(View view){

		String s1 = spDown1.getSelectedItem().toString();
		dayView.setInfoByIndex(1,s1);
		String s2 = spDown2.getSelectedItem().toString();
		dayView.setInfoByIndex(2,s2);
		String s3 = spDown3.getSelectedItem().toString();
		dayView.setInfoByIndex(3,s3);
		String s4 = spDown4.getSelectedItem().toString();
		dayView.setInfoByIndex(4,s4);
		
		MainActivity.getCalendarView().updateInfos(dayView.getInfoString());
		dayView.invalidate();
		finish();
	}

	private void setSpinnerValue(Spinner spinner, String value) {
		SpinnerAdapter apsAdapter = spinner.getAdapter();
		int size = apsAdapter.getCount();
		for (int i = 0; i < size; i++) {
			if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
				spinner.setSelection(i);
				break;
			}
		}
	}
	
}
