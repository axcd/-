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
	
	private final EditText editTexts[] = new EditText[17];
	private ArrayList<String> dayList;
	private float h1 = 0;
	private float h2 = 0;
	private float h3 = 0;
	private int nd = 0;
	private float leave = 0;
	private float sick = 0;
	private float off = 0;
	private float atax = 0;
	
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
		
		editTexts[16] = (EditText)findViewById(R.id.other_deduction);

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
		if(Settings.get("work")!=null){
			editTexts[7].setText(Settings.get("work"));
		}
		if(Settings.get("traf")!=null){
			editTexts[8].setText(Settings.get("traf"));
		}
		if(Settings.get("temp")!=null){
			editTexts[9].setText(Settings.get("temp"));
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
		
		if(Settings.get("atax")!=null){
			atax =Float.parseFloat( Settings.get("atax"));
		}

		getStr();
		
		editTexts[0].addTextChangedListener(new DecimalInputTextWatcher(editTexts[0], 4, 1));
		editTexts[1].addTextChangedListener(new DecimalInputTextWatcher(editTexts[1], 4, 1));
		editTexts[2].addTextChangedListener(new DecimalInputTextWatcher(editTexts[2], 2, 1));
		editTexts[3].addTextChangedListener(new DecimalInputTextWatcher(editTexts[3], 2, 1));
		editTexts[4].addTextChangedListener(new DecimalInputTextWatcher(editTexts[4], 3, 1));
		editTexts[5].addTextChangedListener(new DecimalInputTextWatcher(editTexts[5], 2, 1));
		editTexts[6].addTextChangedListener(new DecimalInputTextWatcher(editTexts[6], 2, 1));
		editTexts[7].addTextChangedListener(new DecimalInputTextWatcher(editTexts[7], 3, 1));
		editTexts[8].addTextChangedListener(new DecimalInputTextWatcher(editTexts[8], 3, 1));
		editTexts[9].addTextChangedListener(new DecimalInputTextWatcher(editTexts[9], 3, 1));
		editTexts[10].addTextChangedListener(new DecimalInputTextWatcher(editTexts[10], 4, 1));
		editTexts[11].addTextChangedListener(new DecimalInputTextWatcher(editTexts[11], 2, 1));
		editTexts[12].addTextChangedListener(new DecimalInputTextWatcher(editTexts[12], 2, 1));
		editTexts[13].addTextChangedListener(new DecimalInputTextWatcher(editTexts[13], 2, 1));
		editTexts[14].addTextChangedListener(new DecimalInputTextWatcher(editTexts[14], 4, 1));
		editTexts[15].addTextChangedListener(new DecimalInputTextWatcher(editTexts[15], 4, 1));
		editTexts[16].addTextChangedListener(new DecimalInputTextWatcher(editTexts[16], 4, 1));
		
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
	
	public double getTax(double f){
		double tax = 0;
		if(f<5000){
			tax = 0;
		}else if(f<=8000){
			tax = (f-5000)*0.03;
		}else if(f<=17000){
			tax = (f-8000)*0.1+90;
		}else if(f<=30000){
			tax = (f-17000)*0.2+900+90;
		}else if(f<=40000){
			tax = (f-30000)*0.25+1300+900+90;
		}else if(f<=60000){
			tax = (f-40000)*0.3+2500+1300+900+90;
		}else if(f<=85000){
			tax = (f-60000)*0.35+6000+2500+1300+900+90;
		}else if(f>85000){
			tax = (f-85000)*0.45+8750+6000+2500+1300+900+90;
		}
		return tax;
	}
	public void getStr(){

		EditText editText = (EditText)findViewById(R.id.mainEditText);
		EditText editText1 = (EditText)findViewById(R.id.tax);
		
		try{
			float ba = Float.parseFloat(editTexts[0].getText().toString());
			float ac = Float.parseFloat(editTexts[1].getText().toString());
			float h1 = Float.parseFloat(editTexts[2].getText().toString());
			float nd = Float.parseFloat(editTexts[3].getText().toString());
			float h2 = Float.parseFloat(editTexts[4].getText().toString());
			float h3 = Float.parseFloat(editTexts[5].getText().toString());
			float na = Float.parseFloat(editTexts[6].getText().toString());
			float wa = Float.parseFloat(editTexts[7].getText().toString());
			float tra = Float.parseFloat(editTexts[8].getText().toString());
			float tea = Float.parseFloat(editTexts[9].getText().toString());
			float oa = Float.parseFloat(editTexts[10].getText().toString());
			float bo = Float.parseFloat(editTexts[11].getText().toString());
			float l = Float.parseFloat(editTexts[12].getText().toString());
			float s = Float.parseFloat(editTexts[13].getText().toString());
			float nn = Float.parseFloat(editTexts[14].getText().toString());
			float fd = Float.parseFloat(editTexts[15].getText().toString());
			
			float od = Float.parseFloat(editTexts[16].getText().toString());	
			
			double f = ba+ac+ba/21.75/8*(1.5*(h1-bo)+2*h2+3*h3-l-0.3*s)+nd*na+wa+tra+tea+oa;
			
			double tax = getTax(f-nn-fd-atax-od);
			tax = Double.parseDouble(String.format("%.1f",tax));
			
			editText1.setText(del(String.format("%.1f",tax)));
			editText.setText(String.format("%.2f",f-nn-fd-tax-od));
			
		}catch(Exception e){
			editText1.setText("0");
			editText.setText("");
		}
	}
	
	class DecimalInputTextWatcher implements TextWatcher {

		private static final int DEFAULT_DECIMAL_DIGITS = 2;//默认 小数的位数  2 位

		private EditText editText;
		private int decimalDigits;// 小数的位数
		private int integerDigits;// 整数的位数

		public DecimalInputTextWatcher(EditText editText) {
			this.editText = editText;
			this.decimalDigits = DEFAULT_DECIMAL_DIGITS;
		}

		public DecimalInputTextWatcher(EditText editText, int decimalDigits) {
			this.editText = editText;
			if (decimalDigits <= 0)
				throw new RuntimeException("decimalDigits must > 0");
			this.decimalDigits = decimalDigits;
		}

		public DecimalInputTextWatcher(EditText editText, int integerDigits, int decimalDigits) {
			this.editText = editText;
			if (integerDigits <= 0)
				throw new RuntimeException("integerDigits must > 0");
			if (decimalDigits <= 0)
				throw new RuntimeException("decimalDigits must > 0");
			this.decimalDigits = decimalDigits;
			this.integerDigits = integerDigits;
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {

		}

		@Override
		public void afterTextChanged(Editable editable) {
			String s = editable.toString();
			editText.removeTextChangedListener(this);

			if (s.contains(".")) {
				if (integerDigits > 0) {
					editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(integerDigits + decimalDigits + 1)});
				}
				if (s.length() - 1 - s.indexOf(".") > decimalDigits) {
					s = s.substring(0, s.indexOf(".") + decimalDigits + 1);
					editable.replace(0, editable.length(), s.trim());//不输入超出位数的数字
				}
			} else {
				if (integerDigits > 0) {
					editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(integerDigits + 1)});
					if (s.length() > integerDigits) {
						s = s.substring(0, integerDigits);
						editable.replace(0, editable.length(), s.trim());
					}
				}

			}
			if (s.trim().equals(".")) {//小数点开头，小数点前补0
				s = "0" + s;
				editable.replace(0, editable.length(), s.trim());
			}
			if (s.startsWith("0") && s.trim().length() > 1) {//多个0开头，只输入一个0
				if (!s.substring(1, 2).equals(".")) {
					editable.replace(0, editable.length(),s.substring(1, 2));
				}
			}
			editText.addTextChangedListener(this);
			getStr();
		}
	}
	
}
