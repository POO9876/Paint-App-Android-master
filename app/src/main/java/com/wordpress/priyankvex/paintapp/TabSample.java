package com.wordpress.priyankvex.paintapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


/**
 * @author Adil Soomro
 *
 */
public class TabSample extends TabActivity {
	/** Called when the activity is first created. */
	String code;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab);
		Bundle extras = getIntent().getExtras();
		 code = Confixe_SettingdataIn_test.nameLoginCode;
		setTabs() ;
	}
	private void setTabs()
	{
		addTab("แบบทดสอบ", R.drawable.test, Tab1_HomeActivity.class);

		addTab("คะแนนทั้งหมด", R.drawable.score1, Tab3_ScorePrePointandroid.class);

	}
	
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		intent.putExtra("code",code);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}