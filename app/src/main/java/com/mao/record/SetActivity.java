package com.mao.record;

import android.app.*;
import android.os.*;
import java.util.*;
import android.widget.*;
import android.text.*;
import android.view.*;
import android.graphics.*;
import android.content.*;
import com.mao.record.settings.*;
import com.mao.record.io.FileManager;
import android.view.View.*;
import com.mao.record.view.*;

public class SetActivity extends Activity
{
	
	private EditText[] editTexts = new EditText[10];
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
		setFinishOnTouchOutside(false);	
		
		editTexts[0] = (EditText)findViewById(R.id.editText);
		editTexts[1] = (EditText)findViewById(R.id.editText1);
		editTexts[2] = (EditText)findViewById(R.id.editText2);
		editTexts[3] = (EditText)findViewById(R.id.editText3);		
		editTexts[4] = (EditText)findViewById(R.id.editText4);		
		editTexts[5] = (EditText)findViewById(R.id.editText5);
		editTexts[6] = (EditText)findViewById(R.id.editText6);		
		editTexts[7] = (EditText)findViewById(R.id.editText7);		
		editTexts[8] = (EditText)findViewById(R.id.editText8);	
		editTexts[9] = (EditText)findViewById(R.id.editText9);	
		
		editTexts[0].addTextChangedListener(new DecimalInputTextWatcher(editTexts[0], 2, 1));
		editTexts[1].addTextChangedListener(new DecimalInputTextWatcher(editTexts[1], 4, 1));
		editTexts[2].addTextChangedListener(new DecimalInputTextWatcher(editTexts[2], 4, 1));
		editTexts[3].addTextChangedListener(new DecimalInputTextWatcher(editTexts[3], 2, 1));
		editTexts[4].addTextChangedListener(new DecimalInputTextWatcher(editTexts[4], 4, 1));
		editTexts[5].addTextChangedListener(new DecimalInputTextWatcher(editTexts[5], 4, 1));
		editTexts[6].addTextChangedListener(new DecimalInputTextWatcher(editTexts[6], 4, 1));
		editTexts[7].addTextChangedListener(new DecimalInputTextWatcher(editTexts[7], 3, 1));
		editTexts[8].addTextChangedListener(new DecimalInputTextWatcher(editTexts[8], 3, 1));
		editTexts[9].addTextChangedListener(new DecimalInputTextWatcher(editTexts[9], 4, 1));
	
		this.getSettings();
	}
	
	public void getSettings(){
		
		editTexts[0].setText(Settings.get("day"));
		editTexts[1].setText(Settings.get("base"));
		editTexts[2].setText(Settings.get("achi"));
		editTexts[3].setText(Settings.get("diff"));
		editTexts[4].setText(Settings.get("noun"));
		editTexts[5].setText(Settings.get("fund"));		
		editTexts[6].setText(Settings.get("work"));
		editTexts[7].setText(Settings.get("traf"));
		editTexts[8].setText(Settings.get("temp"));
		editTexts[9].setText(Settings.get("atax"));	
	}

	public void set(View view){
		
		String  tip = "修改成功";
		
		String atax = editTexts[9].getText().toString();
		if(!atax.equals("")){
			Settings.put("atax",atax);
		}else{
			tip = "请输入累计扣除";
		}
		
		String temp = editTexts[8].getText().toString();
		if(!temp.equals("")){
			Settings.put("temp",temp);
		}else{
			tip = "请输入高温补贴";
		}

		String traf = editTexts[7].getText().toString();
		if(!traf.equals("")){
			Settings.put("traf",traf);
		}else{
			tip = "请输入交通补贴";
		}

		String work = editTexts[6].getText().toString();
		if(!work.equals("")){
			Settings.put("work",work);
		}else{
			tip = "请输入岗位补贴";
		}
		
		String fund = editTexts[5].getText().toString();
		if(!fund.equals("")){
			Settings.put("fund",fund);
		}else{
			tip = "请输入住房公积金";
		}

		String noun = editTexts[4].getText().toString();
		if(!noun.equals("")){
			Settings.put("noun",noun);
		}else{
			tip = "请输入社会保险";
		}
	
		String diff = editTexts[3].getText().toString();
		if(!diff.equals("")){
			Settings.put("diff",diff);
		}else{
			tip = "请输入夜班补贴";
		}
		
		String achi = editTexts[2].getText().toString();
		if(!fund.equals("")){
			Settings.put("achi",achi);
		}else{
			tip = "请输入本月绩效";
		}

		String base = editTexts[1].getText().toString();
		if(!base.equals("")){
			Settings.put("base",base);
		}else{
			tip = "请输入基本工资";
		}

		String daystr = editTexts[0].getText().toString();
		if(!daystr.equals("")){
			int day = (int) Float.parseFloat(daystr);
			if(day>=1 && day<=28){
				Settings.put("day",String.valueOf(day));
			}else{
				tip = "周期开始日期不正确";
			}
		}else{
			tip = "请输入周期开始日期";
		}

		Toast.makeText(this,tip,Toast.LENGTH_LONG).show();
		
		if(tip.equals("修改成功")){
			Settings.updateSettings();
			Intent intent = new Intent(SetActivity.this,MainActivity.class);
			startActivity(intent);
			finish();	
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
					editable.replace(0, editable.length(), s.substring(1, 2));
				}
			}
			editText.addTextChangedListener(this);
		}
	}

}
