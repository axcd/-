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
		
		this.getSettings();
	}
	
	public void getSettings(){
		
		editText.setText(Settings.getDay());
		editText1.setText(Settings.getBase());
		editText2.setText(Settings.getAchi());
		editText3.setText(Settings.getDiff());
		editText4.setText(Settings.getNoun());
		editText5.setText(Settings.getFund());		
	}

	public void set(View view){
		
		String  tip = "修改成功";
		
		String fund = editText5.getText().toString();
		if(!fund.equals("")){
			Settings.setFund(fund);
		}else{
			tip = "请输入住房公积金";
		}

		String noun = editText4.getText().toString();
		if(!noun.equals("")){
			Settings.setNoun(noun);
		}else{
			tip = "请输入社会保险";
		}
	
		String diff = editText3.getText().toString();
		if(!diff.equals("")){
			Settings.setDiff(diff);
		}else{
			tip = "请输入夜班补贴";
		}
		
		String achi = editText2.getText().toString();
		if(!fund.equals("")){
			Settings.setAchi(achi);
		}else{
			tip = "请输入本月绩效";
		}

		String base = editText1.getText().toString();
		if(!base.equals("")){
			Settings.setBase(base);
		}else{
			tip = "请输入基本工资";
		}

		String daystr = editText.getText().toString();
		if(!daystr.equals("")){
			int day = Integer.parseInt(daystr);
			if(day>=1 && day<=28){
				Settings.setDay(String.valueOf(day));
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
