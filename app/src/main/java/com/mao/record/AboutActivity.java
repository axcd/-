package com.mao.record;

import android.app.*;
import android.os.*;
import android.view.*;
import java.io.*;
import android.widget.*;
import android.*;
import android.content.pm.*;
import android.util.*;

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

