package com.wordpress.priyankvex.paintapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.wordpress.priyankvex.paintapp.Sqlite.Databaseregister;

/**
 * @author Adil Soomro
 *
 */
public class Tab1_HomeActivity extends Activity {
	Databaseregister dataname;
	String code;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabpang1);  
        dataname=new Databaseregister(this);
        Bundle extras = getIntent().getExtras();
      
         
        if (extras != null) {
        	code = extras.getString("code");
            // and get whatever type user account id is 
        }    
        String[] selectDataCode= dataname.SelectDataCode(code);
        		TextView Welcome = (TextView)findViewById(R.id.Welcome);
        		Welcome.setText("Nane :"+ selectDataCode[3]+"     "+ selectDataCode[4]);
        		  Button test = (Button) findViewById(R.id.button222);
        		  test.setOnClickListener(new OnClickListener() {
        		            public void onClick(View v) {
        		                // TODO Auto-generated method stub  TestClass Quiz_test_in
        		            	
        		            	Intent i=new Intent(Tab1_HomeActivity.this,Quiz_test_in.class);
        		            	startActivity(i);
        		            } 
        		        }); 
    }
}