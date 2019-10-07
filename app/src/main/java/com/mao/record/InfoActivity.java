package com.mao.record;

import android.app.*;
import android.os.*;
import android.view.*;
import android.content.*;
import android.widget.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.view.WindowManager.*;
import android.text.*;
import com.mao.record.view.*;
import android.transition.*;

public class InfoActivity extends Activity
{
	private Spinner[] spinner = new Spinner[4];
	private DayView dayView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {	

        super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
	
		Display display = getWindowManager().getDefaultDisplay(); 
		Window window = getWindow();	
		LayoutParams windowLayoutParams = window.getAttributes(); 
		windowLayoutParams.width = (int) (display.getWidth()*1.0); 
		windowLayoutParams.height = (int) (display.getHeight()*0.65); 	
		window.setGravity(Gravity.BOTTOM);	
		window.setWindowAnimations(R.style.MyDialogAnimation);
	
		setFinishOnTouchOutside(false);	
		bind();
		setSpinner();
	}
	
	public void setSpinner(){
		List<String> list=new ArrayList<String>();
		list.add("白班");
		list.add("夜班");
		list.add("休息");
		setAdapter(0,list);
	
		list=new ArrayList<String>();
		list.add("0h");
		list.add("0.5h");
		list.add("1h");
		list.add("1.5h");
		list.add("2h");
		list.add("2.5h");
		list.add("3h");
		list.add("3.5h");
		list.add("4h");
		list.add("4.5h");
		list.add("5h");
		list.add("5.5h");
		list.add("6h");
		list.add("6.5h");
		list.add("7h");
		list.add("7.5h");
		list.add("8h");
		list.add("8.5h");
		list.add("9h");
		list.add("9.5h");
		list.add("10h");
		list.add("10.5h");
		list.add("11h");
		list.add("11.5h");
		list.add("12h");
		list.add("12.5h");
		setAdapter(1,list);
	
		list=new ArrayList<String>();
		list.add("1.5倍");
		list.add("2.0倍");
		list.add("3.0倍");
		setAdapter(2,list);
		
		list = new ArrayList<String>();
		list.add("加班");
		list.add("调休");
		list.add("事假");
		list.add("病假");
		list.add("年休");
		setAdapter(3,list);
	}
	
	public void bind(){
		dayView = MainActivity.getCalendarView().getDayView();
		spinner[0]=(Spinner) findViewById(R.id.calendarviewSpinner1);
		spinner[1]=(Spinner) findViewById(R.id.calendarviewSpinner2);
		spinner[2]=(Spinner) findViewById(R.id.calendarviewSpinner3);
		spinner[3]=(Spinner) findViewById(R.id.calendarviewSpinner4);
	}
	
	public void setAdapter(int i, List list){
		ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner[i].setAdapter(adapter);
		setSpinnerValue(spinner[i],dayView.getInfoByIndex(i+1));
	}

	@Override
	public void finish()
	{
		super.finish();
		overridePendingTransition(R.anim.dialog_exit,0); 
	}
	
	

	public void onCancel(View view){
		finish();
	}
	

	public void onOk(View view){

		String s1 = spinner[0].getSelectedItem().toString();
		dayView.setInfoByIndex(1,s1);
		String s2 = spinner[1].getSelectedItem().toString();
		dayView.setInfoByIndex(2,s2);
		String s3 = spinner[2].getSelectedItem().toString();
		dayView.setInfoByIndex(3,s3);
		String s4 = spinner[3].getSelectedItem().toString();
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
