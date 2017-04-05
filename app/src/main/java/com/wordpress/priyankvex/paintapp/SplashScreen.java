package com.wordpress.priyankvex.paintapp;




import android.content.Intent;

import android.os.Bundle;
 

import android.app.Activity;

import com.wordpress.priyankvex.paintapp.Actvity.Login;


public class SplashScreen extends Activity  {

	 
	// private ProgressDialog progressDialog;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_splash);

			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) { }

				//	Intent i = new Intent(SplashScreen.this, MainActivityAndroidGridView.class);
					Intent i = new Intent(SplashScreen.this, Login.class);
					startActivity(i);
					finish();
				}
			}).start();

 
           } 
			
		 

		 
	 
	}
