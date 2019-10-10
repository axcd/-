package com.mao.record;

import android.app.*;
import android.os.*;
import android.view.*;

public class AboutActivity extends Activity
{
 
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }
	
	public void onOK(View view){
		finish();
	}
}
