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
	
	private final EditText editTexts[] = new EditText[10];
	private ArrayList<String> dayList;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);
		
		dayList = MainActivity.getCalendarView().getList();
	
		editTexts[0] = (EditText)findViewById(R.id.mainEditText1);
		editTexts[1] = (EditText)findViewById(R.id.mainEditText2);
		editTexts[2] = (EditText)findViewById(R.id.mainEditText3);
		editTexts[3] = (EditText)findViewById(R.id.mainEditText4);
		editTexts[4] = (EditText)findViewById(R.id.mainEditText5);
		editTexts[5] = (EditText)findViewById(R.id.mainEditText6);
		editTexts[6] = (EditText)findViewById(R.id.mainEditText7);
		editTexts[7] = (EditText)findViewById(R.id.mainEditText8);
		editTexts[8] = (EditText)findViewById(R.id.mainEditText9);
		editTexts[9] = (EditText)findViewById(R.id.mainEditText0);

		final EditText editText = (EditText)findViewById(R.id.mainEditText);

		editTexts[0].setText(Settings.getBase());
		editTexts[1].setText(Settings.getAchi());
		editTexts[2].setText(get2(dayList));
		editTexts[3].setText(get3(dayList));
		editTexts[4].setText(get4(dayList));
		editTexts[5].setText(Settings.getDiff());
		editTexts[6].setText(get6(dayList));
		editTexts[7].setText(get7(dayList));
		editTexts[8].setText(Settings.getNoun());
		editTexts[9].setText(Settings.getFund());
		
		editText.setText(getStr());
		for(int i=0;i<editTexts.length;i++){
			
			final EditText editText0 = editTexts[i];
			editText0.addTextChangedListener(new TextWatcher(){
				String str;
				public void beforeTextChanged(CharSequence p1,int p2,int p3,int p4){
					
				}	
				
				public void onTextChanged(CharSequence ss,int start,int before,int count){
					String s =ss.toString();
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
	
	public String get2(List<String> list){

		float sum = 0;
		for(String tmp:list){
			if(tmp.split(" ")[4].equals("加班") && tmp.split(" ")[3].equals("1.5倍") && tmp.split(" ")[2].contains("h") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				sum += Float.parseFloat(s);
			}
		}
		return sum+"";
	}
	
	public String get3(List<String> list){

		float sum = 0;
		for(String tmp:list){
			if(tmp.split(" ")[4].equals("加班") && tmp.split(" ")[3].equals("2.0倍") && tmp.split(" ")[2].contains("h") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				sum += Float.parseFloat(s);
			}
		}
		return sum+"";
	}
	
	public String get4(List<String> list){

		int sum = 0;
		for(String tmp:list){
			if(tmp.split(" ")[1].equals("夜班")){
				sum += 1;
			}
		}
		return sum+"";
	}
	
	public String get6(List<String> list){
		
		float sum = 0;
		for(String tmp:list){
			if(tmp.split(" ")[2].contains("h") && tmp.split(" ")[4].contains("调休")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				sum += Float.parseFloat(s);
			}
		}
		return sum+"";
	}
	
	public String get7(List<String> list){

		float sum = 0;
		for(String tmp:list){
			if(tmp.split(" ")[4].equals("加班") && tmp.split(" ")[3].equals("3.0倍") && tmp.split(" ")[2].contains("h") && !tmp.split(" ")[1].equals("休息")){
				String s = tmp.split(" ")[2].substring(0,tmp.split(" ")[2].length()-1);
				sum += Float.parseFloat(s);
			}
		}
		return sum+"";
	}
	
	public String getStr(){

		String str;
		
		try{
			Float f1 = Float.parseFloat(editTexts[0].getText().toString());
			Float f2 = Float.parseFloat(editTexts[1].getText().toString());
			Float f3 = Float.parseFloat(editTexts[2].getText().toString());
			Float f4 = Float.parseFloat(editTexts[3].getText().toString());
			Float f5 = Float.parseFloat(editTexts[4].getText().toString());
			Float f6 = Float.parseFloat(editTexts[5].getText().toString());
			Float f7 = Float.parseFloat(editTexts[6].getText().toString());
			Float f8 = Float.parseFloat(editTexts[7].getText().toString());
			Float f9 = Float.parseFloat(editTexts[8].getText().toString());
			Float f0 = Float.parseFloat(editTexts[9].getText().toString());
			double f = 0;
			if(f3>f7){
				f = f1+f2+f1/21.75/8*(1.5*(f3-f7)+2*f4+3*f8)+f5*f6-f9-f0;
			}else if(f4+f3>f7){
				f = f1+f2+f1/21.75/8*(-1.5*f3+2*(f4+f3-f7)+3*f8)+f5*f6-f9-f0;
			}else{
				f = f1+f2+f1/21.75/8*(1.5*(f3-f7)+2*f4+3*f8)+f5*f6-f9-f0;
			}
			str = String.format("%.2f",f);
		}catch(Exception e){
			str = "";
		}
		return str;
	}
	
}
