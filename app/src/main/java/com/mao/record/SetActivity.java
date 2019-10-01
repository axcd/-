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

public class SetActivity extends Activity
{
	
	private EditText editText;
	private EditText editText1;
	private EditText editText2;
	private EditText editText3;
	private EditText editText4;
	private EditText editText5;;
	private EditText editText6;
	private EditText editText7;
	private EditText editText8;;
	private EditText editText9;;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
		
		editText = (EditText)findViewById(R.id.editText);
		editText1 = (EditText)findViewById(R.id.editText1);
		editText2 = (EditText)findViewById(R.id.editText2);
		editText3 = (EditText)findViewById(R.id.editText3);
		editText4 = (EditText)findViewById(R.id.editText4);		
		editText5 = (EditText)findViewById(R.id.editText5);		
		editText6 = (EditText)findViewById(R.id.editText6);
		editText7 = (EditText)findViewById(R.id.editText7);		
		editText8 = (EditText)findViewById(R.id.editText8);		
		editText9 = (EditText)findViewById(R.id.editText9);	
		
		this.getSettings();
	}
	
	public void getSettings(){
		
		editText.setText(Settings.get("day"));
		editText1.setText(Settings.get("base"));
		editText2.setText(Settings.get("achi"));
		editText3.setText(Settings.get("diff"));
		editText4.setText(Settings.get("noun"));
		editText5.setText(Settings.get("fund"));		
		editText6.setText(Settings.get("work"));
		editText7.setText(Settings.get("traf"));
		editText8.setText(Settings.get("temp"));
		editText9.setText(Settings.get("atax"));	
	}

	public void set(View view){
		
		String  tip = "修改成功";
		
		String atax = editText9.getText().toString();
		if(!atax.equals("")){
			Settings.put("atax",atax);
		}else{
			tip = "请输入累计扣除";
		}
		
		String temp = editText8.getText().toString();
		if(!temp.equals("")){
			Settings.put("temp",temp);
		}else{
			tip = "请输入高温补贴";
		}

		String traf = editText7.getText().toString();
		if(!traf.equals("")){
			Settings.put("traf",traf);
		}else{
			tip = "请输入交通补贴";
		}

		String work = editText6.getText().toString();
		if(!work.equals("")){
			Settings.put("work",work);
		}else{
			tip = "请输入岗位补贴";
		}
		
		String fund = editText5.getText().toString();
		if(!fund.equals("")){
			Settings.put("fund",fund);
		}else{
			tip = "请输入住房公积金";
		}

		String noun = editText4.getText().toString();
		if(!noun.equals("")){
			Settings.put("noun",noun);
		}else{
			tip = "请输入社会保险";
		}
	
		String diff = editText3.getText().toString();
		if(!diff.equals("")){
			Settings.put("diff",diff);
		}else{
			tip = "请输入夜班补贴";
		}
		
		String achi = editText2.getText().toString();
		if(!fund.equals("")){
			Settings.put("achi",achi);
		}else{
			tip = "请输入本月绩效";
		}

		String base = editText1.getText().toString();
		if(!base.equals("")){
			Settings.put("base",base);
		}else{
			tip = "请输入基本工资";
		}

		String daystr = editText.getText().toString();
		if(!daystr.equals("")){
			int day = Integer.parseInt(daystr);
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
}
