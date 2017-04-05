package com.wordpress.priyankvex.paintapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.priyankvex.paintapp.Sqlite.Databasepointin;
import com.wordpress.priyankvex.paintapp.Sqlite.Databaseregister;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


public class Quiz_test_in extends Activity {
	private MediaPlayer mediaPlayer ;
	boolean checkDTimeMediaPlayer=true;
	public static final int REQUEST_GALLERY = 1;
	//private TextView timeandroid;
	private long startTime = 0L;
	private Handler customHandler = new Handler();
	private long timeInMilliseconds = 0L;
	private long timeSwapBuff = 0L;
	private long updatedTime = 0L;
	private String strDates ;
 
	
	private String[] text ;
	private String[] text1 ;
	private String[] text2 ;
	private String[] text3 ;
//	private String[] text4 ;
//	private String[] text5 ;
 
	private int[] imagechio={

			 R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1
			,R.drawable.image1


	};
	
	private int[] dataRandome = new int[10];
	private int nextQuestion=0;
	
	
	int [] myStringArrayAnswer  ;
	int [] myStringArrayAnswerCheckOn  ;
	boolean checkRadioGroup=true;
	boolean checkRadio =true;
	private Databaseregister databaserejister;
	boolean checkRadioAng =true;
	private Databasepointin data;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quizintest);
		  databaserejister=new Databaseregister(this);
		  data=new Databasepointin(this);
		  
		text = new String[Confixe_SettingdataIn_test.text.length ];
		text1 = new String[Confixe_SettingdataIn_test.text1.length];
		text2 = new String[Confixe_SettingdataIn_test.text2.length];
		text3 = new String[Confixe_SettingdataIn_test.text3.length];
//		text4 = new String[Confixe_SettingdataIn_test.text4.length];
//		text5 = new String[Confixe_SettingdataIn_test.text5.length];
		
		text=Confixe_SettingdataIn_test.text;
		text1=Confixe_SettingdataIn_test.text1;
		text2=Confixe_SettingdataIn_test.text2;
		text3=Confixe_SettingdataIn_test.text3;
//		text4=Confixe_SettingdataIn_test.text4;
//		text5=Confixe_SettingdataIn_test.text5;
		
		 mediaPlayer = MediaPlayer.create(Quiz_test_in.this, R.raw.mp3);
		 
		setWitget();

		dataRandome = randomIntArray(dataRandome.length, 0, text.length-1);
		myStringArrayAnswer = new int[text.length];
		myStringArrayAnswerCheckOn= new int[text.length];
		for(int g=0;g<myStringArrayAnswer.length;g++){
			myStringArrayAnswer[g]=0;
			myStringArrayAnswerCheckOn[g]=0;
		}
 
		setQuestionView();
 
		rda.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=1;
					rda.setChecked(true);
					rdb.setChecked(false);
					rdc.setChecked(false);
//					rdd.setChecked(false);
//					rde.setChecked(false);
					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rda.setChecked(false);
					checkRadioAng=true;
				}
				
				 
			}
		});
		
		rdb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=2;
					rda.setChecked(false);
					rdb.setChecked(true);
					rdc.setChecked(false);
//					rdd.setChecked(false);
//					rde.setChecked(false);
					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rdb.setChecked(false);
					checkRadioAng=true;
				}
				
				 
			}
		});
		rdc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadioAng){
					myStringArrayAnswer [nextQuestion]=3;
					rda.setChecked(false);
					rdb.setChecked(false);
					rdc.setChecked(true);
//					rdd.setChecked(false);
//					rde.setChecked(false);
					checkRadioAng=false;
				}else{
					myStringArrayAnswer[nextQuestion]=0;
					rdc.setChecked(false);
					checkRadioAng=true;
				}
				
				 
			}
		});
//		rdd.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(checkRadioAng){
//					myStringArrayAnswer [nextQuestion]=4;
//					rda.setChecked(false);
//					rdb.setChecked(false);
//					rdc.setChecked(false);
//					rdd.setChecked(true);
//					rde.setChecked(false);
//					checkRadioAng=false;
//				}else{
//					myStringArrayAnswer[nextQuestion]=0;
//					rdd.setChecked(false);
//					checkRadioAng=true;
//				}
//
//
//			}
//		});
//		rde.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if(checkRadioAng){
//					myStringArrayAnswer [nextQuestion]=5;
//					rda.setChecked(false);
//					rdb.setChecked(false);
//					rdc.setChecked(false);
//					rdd.setChecked(false);
//					rde.setChecked(true);
//					checkRadioAng=false;
//				}else{
//					myStringArrayAnswer[nextQuestion]=0;
//					rde.setChecked(false);
//					checkRadioAng=true;
//				}
//
//
//			}
//		});
//
		btnNEXT.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
 
				if(nextQuestion<text.length-1){					
					//currentQ=quesList.get(qid);
					nextQuestion++;
					setQuestionView();
				}else{
					 
						Toast.makeText(Quiz_test_in.this,"ข้อนี้สุดม้ายแล้ว" , Toast.LENGTH_LONG).show();
					  
					
				}
			}
		});
		
	
		
		btnBACK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	 
				if(nextQuestion>0){					
					//currentQ=quesList.get(qid);
					nextQuestion--;
					setQuestionView();
				}else{
					 
						Toast.makeText(Quiz_test_in.this,"ข้อแรก" , Toast.LENGTH_LONG).show();
				  
					
				}
			}
		});
		
		senddataTest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			       AlertDialog.Builder dialog = new AlertDialog.Builder(Quiz_test_in.this);
			        dialog.setTitle("ส่งคำตอบ");
			        dialog.setCancelable(true);
			        dialog.setMessage("คุณต้องการส่งคำตอบ หรือ ไม่?");
			        dialog.setPositiveButton("ตกลง", new OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            	Toast.makeText(Quiz_test_in.this,"ส่งคำตอบเรียบร้อย" , Toast.LENGTH_LONG).show();
			        		 
			            	//Confixe_Settingdata.nameLoginCode;
			        		
			        		int checkTrueang[]=Confixe_SettingdataIn_test.answer;
			        		int[]  ArrayAnswer = new int[checkTrueang.length];
			        		
			        		int Answer1=0;
			        		int Answer2=0;
			        		int Answer3=0;
			        		for(int f=0;f<checkTrueang.length;f++){
			        			if(myStringArrayAnswer[f]==checkTrueang[f]){
			        				ArrayAnswer[f]=1;
			        				Answer1++;
			        			}else if(myStringArrayAnswer[f]==0){
			        				ArrayAnswer[f]=2;
			        				Answer2++;
			        			}else{
			        				ArrayAnswer[f]=3;
			        				Answer3++;
			        			}
			        		}
			        		Confixe_SettingdataIn_test.ArrayAnsweranswer=ArrayAnswer;
			        		//Confixe_SettingdataIn_test.TimeApp=timeandroid.getText().toString();
			        		Calendar calendar = Calendar.getInstance();
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
							final String strDate = simpleDateFormat.format(calendar.getTime());
							
							 String[] selectDataCode=databaserejister.SelectDataCode(Confixe_SettingdataIn_test.nameLoginCode);
						//  databasepoint.InsertData(null, selectDataCode[3], selectDataCode[4], selectDataCode[5], ""+score, strDate, Code, " ", " ", " ");
							 
							data.InsertData(null, selectDataCode[3], selectDataCode[4], selectDataCode[5], ""+Answer1, strDate, Confixe_SettingdataIn_test.nameLoginCode, " "+Answer3, " "+Answer2, " ");
			        		
							Toast.makeText(Quiz_test_in.this,"ถูกต้อง :" +Answer1+" ผิด :"+Answer3 +" ไม่ได้ทำ  : "+ Answer2+"    "   , Toast.LENGTH_LONG).show();
			        		Intent i=new Intent(Quiz_test_in.this,DetailQuit.class);
    		            	startActivity(i);	
			            }
			        });
			        
			        dialog.setNegativeButton("ยกเลิก", new OnClickListener() {
			            public void onClick(DialogInterface dialog, int which) {
			            	dialog.cancel();
			            	dialog.dismiss();	 
			            }
			        });
			        
			        dialog.show();   
			}
		});
		 
	 
		onChoi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(checkRadio){
					myStringArrayAnswerCheckOn[nextQuestion]=1;
					onChoi.setChecked(true);
					checkRadio=false;
				}else{
					myStringArrayAnswerCheckOn[nextQuestion]=0;
					onChoi.setChecked(false);
					checkRadio=true;
				}
				
				 
			}
		});
	}



	
	private void setQuestionView(){
		  
	
		textchoitype.setText(text[dataRandome[nextQuestion]]);
		rda.setText(text1[dataRandome[nextQuestion]]);
		rdb.setText(text2[dataRandome[nextQuestion]]);
		rdc.setText(text3[dataRandome[nextQuestion]]);
//		rdd.setText(text4[dataRandome[nextQuestion]]);
//		rde.setText(text5[dataRandome[nextQuestion]]);
		
		textchoitype.setTextColor(Color.BLACK);
		rda.setTextColor(Color.BLACK);
		rdb.setTextColor(Color.BLACK);
		rdc.setTextColor(Color.BLACK);
//		rdd.setTextColor(Color.BLACK);
//		rde.setTextColor(Color.BLACK);
		
		
		//setimageviewchoi.setImageResource (imagechio[dataRandome[nextQuestion]]);

		if(myStringArrayAnswer[nextQuestion]==1) {
			//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
			rda.setChecked(true);
			rdb.setChecked(false);
    		rdc.setChecked(false);
//    		rdd.setChecked(false);
//    		rde.setChecked(false);
        } else if(myStringArrayAnswer[nextQuestion]==2) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	rdb.setChecked(true);
        	rda.setChecked(false);
    		 
    		rdc.setChecked(false);
//    		rdd.setChecked(false);
//    		rde.setChecked(false);
        }else if(myStringArrayAnswer[nextQuestion]==3) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	rdc.setChecked(true);
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		 
//    		rdd.setChecked(false);
//    		rde.setChecked(false);
        }else if(myStringArrayAnswer[nextQuestion]==4) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	//rdd.setChecked(true);
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		rdc.setChecked(false);
    		 
    		//rde.setChecked(false);
        }else if(myStringArrayAnswer[nextQuestion]==5) {
        	//Toast.makeText(TestClass.this,nextQuestion+" "+myStringArrayAnswer[nextQuestion] , Toast.LENGTH_LONG).show();
        	//rde.setChecked(true);
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		rdc.setChecked(false);
    		//.setChecked(false);
    	 
        }else if(myStringArrayAnswer[nextQuestion]==0)  { 
        	rda.setChecked(false);
    		rdb.setChecked(false);
    		rdc.setChecked(false);
//    		rdd.setChecked(false);
//    		rde.setChecked(false);
        }else    { 
        	 
        }
		if(myStringArrayAnswerCheckOn[nextQuestion]==1 ){
			onChoi.setChecked(true);
		}else{
			onChoi.setChecked(false);
		}
		
	 
	}
	private void setWitget() {
		// TODO Auto-generated method stub
		//timeandroid= (TextView)findViewById(R.id.timeandroid);
		title=(Button)findViewById(R.id.button18);
		
		btnBACK=(Button)findViewById(R.id.btnBACK);
		btnNEXT=(Button)findViewById(R.id.btnNEXT);
		
		senddataTest=(Button)findViewById(R.id.senddataTest);
		
		
		textchoitype=(TextView)findViewById(R.id.textchoitype);
		
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
//		rdd=(RadioButton)findViewById(R.id.radio3);
//		rde=(RadioButton)findViewById(R.id.radio4);
		
		 
		
		//onChoi
		onChoi=(RadioButton)findViewById(R.id.ondata);
	
		//setimageviewchoi=(ImageView)findViewById(R.id.setimageview);
		
		 
		Calendar calendars = Calendar.getInstance();
		SimpleDateFormat simpleDateFormats = new SimpleDateFormat("dd:MMMM:yyyy HH:mm:ss a");
		 strDates = simpleDateFormats.format(calendars.getTime());
		startTime = SystemClock.uptimeMillis();
		customHandler.postDelayed(updateTimerThread, 0);
	}
	

	//private ImageView setimageviewchoi;
	private Button title,btnBACK,btnNEXT,senddataTest;
	private TextView textchoitype;
	private RadioButton rda, rdb, rdc,onChoi;
//, rdd,rde
	public int[] randomIntArray(int count, int min, int max) {
		Random r = new Random();
		int[] data = new int[count];
		for(int i = 0 ; i < count ; i++) 
			data[i] = -1;
		for(int i = 0 ; i < count ; i++) {
			int n = -1;
	    	boolean st = true; 
			while(st) {
				st = false;
				n = r.nextInt((max - min) + 1) + min;
				for(int j = 0 ; j < data.length ; j++) 
					if(n == data[j]) 
						st = true;
			}
			data[i] = n;
		}
		return data;
	}
	private Runnable updateTimerThread = new Runnable() {
		public void run() {
			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
			updatedTime = timeSwapBuff + timeInMilliseconds;
			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			int mex =mins/ 60;
			int milliseconds = (int) (updatedTime % 1000);
		//	timeandroid.setText(""+mex+":" + mins + ":"+ String.format("%02d", secs) + ":"+ String.format("%03d", milliseconds));
		
			if(mins==165 &&checkDTimeMediaPlayer){
				checkDTimeMediaPlayer=false;
 			 Paymedia();

			}
			customHandler.postDelayed(this, 0);
			
		}
	};
	
    private void Paymedia() {
		// TODO Auto-generated method stub
 		 mediaPlayer.start();

	}
	@Override
	public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(Quiz_test_in.this);
        dialog.setTitle("คุณต้องการเลิกไหม");
        dialog.setCancelable(true);
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("ตกลง", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            	Toast.makeText(Quiz_test_in.this,"Quiz fial "+" No save data  " , Toast.LENGTH_LONG).show();
            	Quiz_test_in.this.finish();

            }
        });
        
        dialog.setNegativeButton("ยกเลิก", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	dialog.cancel();
            	dialog.dismiss();	 
            }
        });
        
        dialog.show();   
		
	}
}