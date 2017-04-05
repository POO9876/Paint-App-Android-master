package com.wordpress.priyankvex.paintapp.Actvity;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Or;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseEnglish;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseThai;
import com.wordpress.priyankvex.paintapp.Sqlite.Databaseregister;
import com.wordpress.priyankvex.paintapp.TinyDB.TinyDB;
import com.wordpress.priyankvex.paintapp.ui.AppActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Adil Soomro
 * 
 */
public class Register extends AppActivity  implements AdapterView.OnItemSelectedListener {

	private Databaseregister databaserejister;

	private DatabaseThai databaseThai ;
	private DatabaseEnglish databaseEnglish;

	@NotEmpty(message = "Please enter UserName")
	private EditText editText1;
	@Password(min = 6, message = "Password must have at-least 6 characters")
	private EditText editText2;
	@NotEmpty(message = "Please enter Name")
	private EditText editText3;
	@NotEmpty(message = "Please enter Lastname")
	private EditText editText4;

//	@Min(value = 2)
//	@Or
//	@Max(value = 10)
//	private EditText editText5;
	private String item ;
	private RadioGroup radioGroup;
	private  RadioButton rb ;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		databaserejister = new Databaseregister(this);
		databaseThai = new DatabaseThai(this);
		databaseEnglish= new DatabaseEnglish(this);


		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		editText3 = (EditText) findViewById(R.id.editText3);
		editText4 = (EditText) findViewById(R.id.editText4);
		//editText5 = (EditText) findViewById(R.id.editText5);


//		submit = (Button) findViewById(R.id.submit);
//		submit.setOnClickListener(this);


		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.clearCheck();

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				 rb = (RadioButton) group.findViewById(checkedId);
				if (null != rb && checkedId > -1) {
					Toast.makeText(getApplication(), rb.getText(), Toast.LENGTH_SHORT).show();
				}

			}
		});


		Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
		// Spinner element
		Spinner spinner = (Spinner) findViewById(R.id.spinner);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);



	//	Log.e(" SelectAllDataThai get", SelectAllDataThai.get(0).toString());


		// Spinner Drop down elements
		ArrayList<String> categories = new ArrayList<String>();
		int i ;
		for( i = 1 ;i<=100 ;i++){
			categories.add(String.valueOf(i));

		}


		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

		// Drop down layout style - list view with radio button
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);

		btnSubmit.setOnClickListener(this);
//		  btnSubmit.setOnClickListener(new OnClickListener() {
//	            public void onClick(View v) {
//	                // TODO Auto-generated method stub
//
//
//					if (validated) {
//						// Our form is successfully validated, so, do your stuffs here...
//						Toast.makeText(getApplication(), "Form Successfully Validated", Toast.LENGTH_LONG).show();



	}


//	            	if(editText1.getText().toString().equals("")||editText2.getText().toString().equals("")
//	            			||editText3.getText().toString().equals("")||editText4.getText().toString().equals("")
//	            			||editText5.getText().toString().equals("")){
//	            		Toast.makeText(Register.this,"กรุณากรอกข้อมูล ", Toast.LENGTH_LONG).show();
//
//	            	}else{
//
//
//	            	}
//	            }
//	        });

		  
//	  Button btnCancel = (Button) findViewById(R.id.btnCancel);
//
//	  btnCancel.setOnClickListener(new OnClickListener() {
//	            public void onClick(View v) {
//	                // TODO Auto-generated method stub
//	            	Register.this.finish();
//	            }
//	        });
//
//
//	}


	@Override
	public void onClick(View v) {
		super.onClick(v);

		if (validated) {
			// Our form is successfully validated, so, do your stuffs here...

			RadioButton rb = (RadioButton) radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
			Toast.makeText(getApplication(), rb.getText(), Toast.LENGTH_SHORT).show();


			Toast.makeText(this, "Form Successfully Validated", Toast.LENGTH_LONG).show();

						ArrayList<HashMap<String, String>> SelectAllData= databaserejister.SelectAllData();
						boolean checkT=true;
						for(int i=0;i<SelectAllData.size();i++){
							if(SelectAllData.get(i).get("col1").equals(editText1.getText().toString())){
								checkT=false;
								editText1.setText(" ");

							}
						}
						if(checkT){


							TinyDB tinydb = new TinyDB(getApplicationContext());
							tinydb.putString("Myname", editText1.getText().toString());

							databaserejister.InsertData(null, editText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString()
									, editText4.getText().toString(), item, " ",  " ",  " ",  " ", rb.getText().toString());

							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_1),"0");

							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_2),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_3),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_4),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_5),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_6),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_7),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_8),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_9),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_10),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_11),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_12),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_13),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_14),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_15),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_16),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_17),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_18),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_19),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_20),"0");

							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_21),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_22),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_23),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_24),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_25),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_26),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_27),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_28),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_29),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_30),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_31),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_32),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_33),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_34),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_35),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_36),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_37),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_38),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_39),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_40),"0");

							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_41),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_42),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_43),"0");
							databaseThai.InsertData(null,editText1.getText().toString(),getResources().getString(R.string.k_44                        ),"0");



							//***********************************************************************

//							public static  int [] imgMenu2 = new int[] { R.drawable.a, R.drawable.b,
//									R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
//									R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j,
//									R.drawable.k, R.drawable.l, R.drawable.m, R.drawable.n,
//									R.drawable.o, R.drawable.p, R.drawable.q, R.drawable.r,
//									R.drawable.s, R.drawable.t, R.drawable.u, R.drawable.v,
//									R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z };


							databaseEnglish.InsertData(null,editText1.getText().toString(),"a","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"b","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"c","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"d","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"e","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"f","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"g","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"h","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"i","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"j","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"k","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"l","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"m","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"n","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"o","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"p","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"q","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"r","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"s","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"t","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"u","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"v","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"w","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"x","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"y","0");
							databaseEnglish.InsertData(null,editText1.getText().toString(),"z","0");


							ArrayList<HashMap<String, String>> SelectAllDataThai = databaseThai.SelectAllData();
							Log.e("SelectAllDataThai", SelectAllDataThai.toString());




							Toast.makeText(Register.this," บันทึกเรียบร้อย", Toast.LENGTH_LONG).show();

							Log.e("rb.getText().toString()",rb.getText().toString());
							Register.this.finish();

						}else{
							Toast.makeText(Register.this,"   username นี้มีอยู่แล้ว  ", Toast.LENGTH_LONG).show();
						}
		}

	}




	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		 item = parent.getItemAtPosition(position).toString();

		// Showing selected spinner item
		Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
