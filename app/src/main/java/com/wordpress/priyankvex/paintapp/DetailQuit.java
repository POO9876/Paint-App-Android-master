package com.wordpress.priyankvex.paintapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wordpress.priyankvex.paintapp.Sqlite.Databasepointin;
import com.wordpress.priyankvex.paintapp.Sqlite.Databaseregister;

import java.util.ArrayList;


public class DetailQuit extends Activity {
	 
	private Databasepointin data;
	private Databaseregister databaserejister;
	private ListView listview;
	 
     private String[] text  ;
	 private String[] text2  ;
	 private int image[]={R.drawable.answers,R.drawable.answers1,R.drawable.answers3} ;
	 private int imagePoint[];
	 private ArrayList<String> text_sort = new ArrayList<String>();
	 private ArrayList<String> text_sort2 = new ArrayList<String>();
	 private String Code;
	 TextView detailquit;
	 public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.listviewdetailquit);
	  databaserejister=new Databaseregister(this);
	  
	  data=new Databasepointin(this);
	  Code = Confixe_SettingdataIn_test.nameLoginCode;	
 
	  TextView textView1sss = (TextView) findViewById(R.id.textView1_view);

	  textView1sss.setText("รายละเอียด");
	 
    		  
    	 detailquit  = (TextView) findViewById(R.id.detailquit);
	    listview = (ListView) findViewById(R.id.ListView01);
		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
		 
				
			}
		});
	 }

	 class MyCustomAdapter extends BaseAdapter
	 {

	  String[] data_text;
	  String[] data_text2;
	  MyCustomAdapter(){}

	  MyCustomAdapter(String[] text, String[] text2){
		  
	   data_text = text;

	   data_text2 = text2;
	  }
	  
 

	  public int getCount()
	  {
	   return data_text.length;
	  }

	  public String getItem(int position)
	  {
	   return null;
	  }

	  public long getItemId(int position)
	  {
	   return position;
	  }

	  public View getView(int position, View convertView, ViewGroup parent)
	  {

	   LayoutInflater inflater = getLayoutInflater();
	   View row;

	   row = inflater.inflate(R.layout.list_row_view, parent, false);
	   //mTypeface = Typeface.createFromAsset(getBaseContext().getAssets(),"fonts/TH_Mali_Grade6_Bold.ttf");
	   TextView textview1 = (TextView) row.findViewById(R.id.text_1);
	   TextView textview2 = (TextView) row.findViewById(R.id.text_2);
	   TextView textview3 = (TextView) row.findViewById(R.id.text_3);
	   ImageView list_image = (ImageView) row.findViewById(R.id.list_image);
 
	   int number=position;
	   number++;
	   String strI = Integer.toString(number);
	   textview1.setText(" ");
	   
	   textview2.setText(data_text[position]);
	   textview2.setSelected(true);
	   textview3.setText(data_text2[position]);
	   textview3.setSelected(true);
	   list_image.setBackgroundResource(image[imagePoint[position]]);


	   return (row);

	  }
	 }
 



		
		protected void onStart ( ){
		    super.onStart ( );
		    
		    String[] selectDataCode=databaserejister.SelectDataCode(Confixe_SettingdataIn_test.nameLoginCode);
 			text2=new String[Confixe_SettingdataIn_test.ArrayAnsweranswer.length];
 			text=new String[Confixe_SettingdataIn_test.ArrayAnsweranswer.length];
 			imagePoint=new int[Confixe_SettingdataIn_test.ArrayAnsweranswer.length]; 
 			int h=0;
 			int Answer1=0;
    		int Answer2=0;
    		int Answer3=0;
    		
			for(int i=0;i<Confixe_SettingdataIn_test.ArrayAnsweranswer.length;i++){
				h++;
				if(Confixe_SettingdataIn_test.ArrayAnsweranswer[i]==1){
					Answer1++;
					text2[i]="letter : "+selectDataCode[3]+"นามสกุล"+selectDataCode[4]+" อายุ "+selectDataCode[5]+" เวลา  " +Confixe_SettingdataIn_test.TimeApp+"นาที";
					text[i]="ข้อที่ : "+h  +"ถูกต้อง" ;
					imagePoint[i]=0;
				}else if(Confixe_SettingdataIn_test.ArrayAnsweranswer[i]==2){
					Answer2++;
					text2[i]="letter : "+selectDataCode[3]+"นามสกุล"+selectDataCode[4]+" อายุ "+selectDataCode[5]+" เวลา  " +Confixe_SettingdataIn_test.TimeApp+" นาที";
					text[i]="ข้อที่ : "+h  +" ไม่แน่ใจ" ;
					imagePoint[i]=2;
				}else if(Confixe_SettingdataIn_test.ArrayAnsweranswer[i]==3){
					Answer3++;
					text2[i]="letter : "+selectDataCode[3]+" "+selectDataCode[4]+"อายุ"+selectDataCode[5]+" เวลาͺ  " +Confixe_SettingdataIn_test.TimeApp+" นาที";
					text[i]=" ข้อที่: "+h  +" ตอบผิด " ;
					imagePoint[i]=1;
				}else{
					
				}
			}
			
			detailquit.setText("ถูก  :" +Answer1+"   ผิด  :"+Answer3 +"   ไม่แน่ใจ  : "+ Answer2);
	 	listview.setAdapter(new MyCustomAdapter(text,text2));
		}

	
		@Override
		public void onBackPressed() {
			Intent i=new Intent(DetailQuit.this,TabSample.class);
			startActivity(i);
			DetailQuit.this.finish();

	    }
	}