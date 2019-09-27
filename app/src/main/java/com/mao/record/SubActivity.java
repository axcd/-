package com.mao.record;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;
import android.widget.TextView.*;
import android.text.*;
import java.util.*;
import com.mao.record.view.*;
import com.mao.record.settings.*;

public class SubActivity extends Activity
{
	
	private final EditText editTexts[] = new EditText[18];
	private ArrayList<String> dayList;
	private float h1 = 0;
	private float h2 = 0;
	private float h3 = 0;
	private int nd = 0;
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

		final EditText editText = (EditText)findViewById(R.id.mainEditText);
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

		if(Settings.get("base")!=null){
			editTexts[0].setText(Settings.get("base"));
		}
		if(Settings.get("achi")!=null){
			editTexts[1].setText(Settings.get("achi"));
		}
		editTexts[2].setText(del(String.valueOf(h1)));
		editTexts[3].setText(del(String.valueOf(nd)));
		editTexts[4].setText(del(String.valueOf(h2)));
		editTexts[5].setText(del(String.valueOf(h3)));
		if(Settings.get("diff")!=null){
			editTexts[6].setText(Settings.get("diff"));
		}
		editTexts[11].setText(del(String.valueOf(off)));
		editTexts[12].setText(del(String.valueOf(leave)));
		editTexts[13].setText(del(String.valueOf(sick)));
		if(Settings.get("noun")!=null){
			editTexts[14].setText(Settings.get("noun"));
		}
		if(Settings.get("fund")!=null){
			editTexts[15].setText(Settings.get("fund"));
		}
		
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
	
	public String del(String str){
		if(str.endsWith(".0")){
			str = str.replace(".0","");
		}
		return str;
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
				
			double f = ba+ac+ba/21.75/8*(1.5*(h1-bo)+2*h2+3*h3-l-0.3*s)+nd*na+wa+tra+tea+oa-(nn+fd+tax+od);
			str = String.format("%.2f",f);
		}catch(Exception e){
			str = "";
		}
		
		return str;
	}
	
}
