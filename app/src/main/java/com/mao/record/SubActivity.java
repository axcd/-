package com.mao.record;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.widget.TextView.*;
import android.text.*;
import java.util.*;
import com.mao.record.vw.*;
import com.mao.record.settings.*;

public class SubActivity extends Activity
{
	
	private final EditText editTexts[] = new EditText[18];
	private ArrayList<String> dayList;
	private float h1 = 0;
	private float h2 = 0;
	private float h3 = 0;
	private float nd = 0;
	private float leave = 0;
	private float sick = 0;
	private float off = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);
		
		dayList = MainActivity.getCalendarView().getList();
		getData();
	
		editTexts[0] = (EditText)findViewById(R.id.base);
		editTexts[1] = (EditText)findViewById(R.id.achi);
		editTexts[2] = (EditText)findViewById(R.id.h1);
		editTexts[3] = (EditText)findViewById(R.id.night_days);
		editTexts[4] = (EditText)findViewById(R.id.h2);
		editTexts[5] = (EditText)findViewById(R.id.h3);
		editTexts[6] = (EditText)findViewById(R.id.night_allowance);
		editTexts[7] = (EditText)findViewById(R.id.work_allowance);
		editTexts[8] = (EditText)findViewById(R.id.traffic_allowance);
		editTexts[9] = (EditText)findViewById(R.id.temperature_allowance);
		editTexts[10] = (EditText)findViewById(R.id.other_allowance);
		editTexts[11] = (EditText)findViewById(R.id.break_off);
		editTexts[12] = (EditText)findViewById(R.id.compassionate_leave);
		editTexts[13] = (EditText)findViewById(R.id.sick);
		editTexts[14] = (EditText)findViewById(R.id.noun);
		editTexts[15] = (EditText)findViewById(R.id.fund);
		editTexts[16] = (EditText)findViewById(R.id.tax);
		editTexts[17] = (EditText)findViewById(R.id.other_deduction);

		final EditText editText = (EditText)findViewById(R.id.mainEditText);

		editTexts[0].setText(Settings.getBase());
		editTexts[1].setText(Settings.getAchi());
		editTexts[2].setText(String.valueOf(h1));
		editTexts[3].setText(String.valueOf(nd));
		editTexts[4].setText(String.valueOf(h2));
		editTexts[5].setText(String.valueOf(h3));
		editTexts[6].setText(Settings.getDiff());
	//	editTexts[7].setText(Settings.getBase());
	//	editTexts[8].setText(Settings.getAchi());
	//	editTexts[9].setText(Settings.getDiff());
	//	editTexts[10].setText(Settings.getDiff());
		editTexts[11].setText(String.valueOf(off));
		editTexts[12].setText(String.valueOf(leave));
		editTexts[13].setText(String.valueOf(sick));
		editTexts[14].setText(Settings.getNoun());
		editTexts[15].setText(Settings.getFund());
	//	editTexts[16].setText(Settings.getDiff());
	//	editTexts[17].setText(Settings.getDiff());
		
		editText.setText(getStr());
		for(int i=0;i<editTexts.length;i++){
			
			final EditText editText0 = editTexts[i];
			editText0.addTextChangedListener(new TextWatcher(){
				String str;
				public void beforeTextChanged(CharSequence p1,int p2,int p3,int p4){
					
				}	
				
				public void onTextChanged(CharSequence cs,int start,int before,int count){
					String s =cs.toString();
					if((s.length()>1&&s.startsWith("0")&&s.charAt(1)!='.')||(s.toString().startsWith("."))){
						editText0.setText(s.substring(1));
						editText0.setSelection(s.length()-1);
					}
				}
				
				public void afterTextChanged(Editable p1){
					str = getStr();
					editText.setText(str);
				}		
			});
			
			editText0.setOnFocusChangeListener(new OnFocusChangeListener(){
				public void onFocusChange(View v,boolean b){
					String s =editText0.getText().toString();
					if(!editText0.isFocused()){
						if(s.endsWith(".")){
							editText0.setText(s.substring(0,s.length()-1));
							editText0.setSelection(s.length()-1);
						}
					}
				}
			});
		}
	}
	

	public void getData(){
		
		for(String tmp:dayList){		
			if(tmp.split(" ")[4].equals("加班") && tmp.split(" ")[3].equals("1.5倍") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				h1 += Float.parseFloat(s);
			}
			
			if(tmp.split(" ")[4].equals("加班") && tmp.split(" ")[3].equals("2.0倍") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				h2 += Float.parseFloat(s);
			}
			
			if(tmp.split(" ")[4].equals("加班") && tmp.split(" ")[3].equals("3.0倍") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				h3 += Float.parseFloat(s);
			}
			
			if(tmp.split(" ")[1].equals("夜班")){
				nd += 1;
			}
			
			if(tmp.split(" ")[2].contains("h") && tmp.split(" ")[4].contains("调休")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				off += Float.parseFloat(s);
			}
			
			if(tmp.split(" ")[4].equals("事假") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				leave += Float.parseFloat(s);
			}
			
			if(tmp.split(" ")[4].equals("病假") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				sick += Float.parseFloat(s);
			}
			
		}
	}
	
	public String getStr(){

		String str;
		try{
			Float ba = Float.parseFloat(editTexts[0].getText().toString());
			Float ac = Float.parseFloat(editTexts[1].getText().toString());
			Float h1 = Float.parseFloat(editTexts[2].getText().toString());
			Float nd = Float.parseFloat(editTexts[3].getText().toString());
			Float h2 = Float.parseFloat(editTexts[4].getText().toString());
			Float h3 = Float.parseFloat(editTexts[5].getText().toString());
			Float na = Float.parseFloat(editTexts[6].getText().toString());
			Float wa = Float.parseFloat(editTexts[7].getText().toString());
			Float tra = Float.parseFloat(editTexts[8].getText().toString());
			Float tea = Float.parseFloat(editTexts[9].getText().toString());
			Float oa = Float.parseFloat(editTexts[10].getText().toString());
			Float bo = Float.parseFloat(editTexts[11].getText().toString());
			Float l = Float.parseFloat(editTexts[12].getText().toString());
			Float s = Float.parseFloat(editTexts[13].getText().toString());
			Float nn = Float.parseFloat(editTexts[14].getText().toString());
			Float fd = Float.parseFloat(editTexts[15].getText().toString());
			Float tax = Float.parseFloat(editTexts[16].getText().toString());
			Float od = Float.parseFloat(editTexts[17].getText().toString());
			double f = 0;
			
			/*
			if(f3>f7){
				f = f1+f2+f1/21.75/8*(1.5*(f3-f7)+2*f4+3*f8)+f5*f6-f9-f0;
			}else if(f4+f3>f7){
				f = f1+f2+f1/21.75/8*(-1.5*f3+2*(f4+f3-f7)+3*f8)+f5*f6-f9-f0;
			}else{
				f = f1+f2+f1/21.75/8*(1.5*(f3-f7)+2*f4+3*f8)+f5*f6-f9-f0;
			}
			*/
			f = ba+ac+ba/21.75/8*(1.5*(h1-bo)+2*h2+3*h3-l-0.3*s)+nd*na+wa+tra+tea+oa-(nn+fd+tax+od);
			str = String.format("%.2f",f);
		}catch(Exception e){
			str = "";
		}
		
		return str;
	}
	
}
