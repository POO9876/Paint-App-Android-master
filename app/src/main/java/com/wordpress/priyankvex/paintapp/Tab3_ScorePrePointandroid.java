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

import java.util.ArrayList;
import java.util.HashMap;


public class Tab3_ScorePrePointandroid extends Activity {
	 
	private Databasepointin data;
	 
	private ListView listview;
	 
     private String[] text  ;
	 private String[] text2  ;
	 private int image[]={R.drawable.exam_icon} ;
	 
	 private ArrayList<String> text_sort = new ArrayList<String>();
	 private ArrayList<String> text_sort2 = new ArrayList<String>();
	 private String Code;
	 public void onCreate(Bundle savedInstanceState){

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.main_searchs);
	  data=new Databasepointin(this);
	  
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Code = extras.getString("code");	
		}
		
	  TextView textView1sss = (TextView) findViewById(R.id.textView1_view);
	  textView1sss.setText("คะแนนทั้งหมดที่สอบ");
	 
	  //edittext = (EditText) findViewById(R.id.EditText01);
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
	  
	  MyCustomAdapter(ArrayList<String> text, ArrayList<String> text2)
	  { 
	  
	   data_text = new String[text.size()];
	   data_text2 = new String[text2.size()];

	   for(int i=0;i<text.size();i++)
	   {
	    data_text[i] = text.get(i);
	    data_text2[i] = text2.get(i);
	   }

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
	   textview1.setText(strI+".");
	   
	   textview2.setText(data_text[position]);
	   textview2.setSelected(true);
	   textview3.setText(data_text2[position]);
	   textview3.setSelected(true);
	   list_image.setBackgroundResource(image[0]);


	   return (row);

	  }
	 }
 
		
		protected void onStart ( ){
		    super.onStart ( );
		    
		    ArrayList<HashMap<String, String>> selectAllData= data.SelectAllData();
 			text2=new String[selectAllData.size()];
 			text=new String[selectAllData.size()];
 			
 			
			for(int i=0;i<selectAllData.size();i++){
				if(selectAllData.get(i).get("col6").equals(Code)){
					text2[i]="letter : "+selectAllData.get(i).get("col1")+"นามสกุล"+selectAllData.get(i).get("col2")+" อายุ "+selectAllData.get(i).get("col3")+" เวลา  :"+selectAllData.get(i).get("col5");
					text[i]="ถูกต้อง : "+selectAllData.get(i).get("col4")+"ข้อ "+" ผิด :"+selectAllData.get(i).get("col7")+"ข้อ "+"  ไม่แน่ใจ  :"+selectAllData.get(i).get("col8")+"ข้อ ";
				}
			}
	 	listview.setAdapter(new MyCustomAdapter(text,text2));
		}

	
		@Override
		public void onBackPressed() {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			Tab3_ScorePrePointandroid.this.finish();

	    }
	}