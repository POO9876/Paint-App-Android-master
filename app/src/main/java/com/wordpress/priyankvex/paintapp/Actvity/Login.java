package com.wordpress.priyankvex.paintapp.Actvity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.wordpress.priyankvex.paintapp.Confixe_SettingdataIn_test;
import com.wordpress.priyankvex.paintapp.MainActivityAndroidGridView;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseThai;
import com.wordpress.priyankvex.paintapp.Sqlite.Databaseregister;
import com.wordpress.priyankvex.paintapp.TabSample;
import com.wordpress.priyankvex.paintapp.ui.AppActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Adil Soomro
 */
public class Login extends AppActivity implements OnClickListener {
    Databaseregister databaserejister;
    DatabaseThai databaseThai ;

    @NotEmpty(message = "กรุณากรอก user")
    private EditText user;

    @NotEmpty(message = "กรุณากรอก pass")
    private EditText pass;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        databaserejister = new Databaseregister(this);
        databaseThai = new DatabaseThai(this);
        user = (EditText) findViewById(R.id.editText1);
        pass = (EditText) findViewById(R.id.editText2);

        Button login = (Button) findViewById(R.id.button21);

        login.setOnClickListener(this);







//        String strArr[] = new String[SelectAllData.size()];
//        int i = 0;
//        for (HashMap<String, String> hash : SelectAllData) {
//            for (String current : hash.values()) {
//                strArr[i] = current;
//
//                Log.e("strArr",strArr[i]);
//                i++;
//            }
//        }



    //    toJSON(SelectAllData);


     //   Log.e("SelectAllData123",toJSON(SelectAllData));

        // getting JSON string from URL


        // Getting JSON Array node




        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Login.this.finish();
    }


    private JSONObject getJsonFromMap(Map<String, Object> map) throws JSONException {
        JSONObject jsonData = new JSONObject();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof Map<?, ?>) {
                value = getJsonFromMap((Map<String, Object>) value);
            }
            jsonData.put(key, value);
        }
        return jsonData;
    }






    String toJSON( ArrayList<HashMap<String, String>> list) {
        Gson gson = new Gson();
        StringBuilder sb = new StringBuilder();
        for(HashMap<String, String> d : list) {

            Log.e(" Thai toJSON", gson.toJson(d));
            sb.append(gson.toJson(d));
        }
        return sb.toString();
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);  //AppActivity


        //validate ถ้าจริง ให้ แสดงว่า ข้อท้วน
        if (validate()) {

            String namecode = "";
            ArrayList<HashMap<String, String>> SelectAllData = databaserejister.SelectAllData();


            Log.e("SelectAllData", SelectAllData.toString());

            int i;

            boolean checkT = false;
            for (i = 0; i < SelectAllData.size(); i++) {
                if (SelectAllData.get(i).get("col1").equals(user.getText().toString()) && SelectAllData.get(i).get("col2").equals(pass.getText().toString())) {

                    namecode = SelectAllData.get(i).get("Code");
                    String col1 = SelectAllData.get(i).get("col1");
                    String col2 = SelectAllData.get(i).get("col2");
                    String col3 = SelectAllData.get(i).get("col3");
                    String col4 = SelectAllData.get(i).get("col4");
                    String col5 = SelectAllData.get(i).get("col5");
                    String col6 = SelectAllData.get(i).get("col6");
                    String col7 = SelectAllData.get(i).get("col7");
                    String col8 = SelectAllData.get(i).get("col8");
                    String col9 = SelectAllData.get(i).get("col9");
                    String col10 = SelectAllData.get(i).get("col10");
                    //String col11=SelectAllData.get(i).get("col11");


                    Log.e("col1", col1);
                    Log.e("col2", col2);
                    Log.e("col3", col3);
                    Log.e("col4", col4);
                    Log.e("col5", col5);
                    Log.e("col6", col6);
                    Log.e("col7", col7);
                    Log.e("col8", col8);
                    Log.e("col9", col9);
                    Log.e("col10", col10);
                    //Log.e("col11",col11);
                    checkT = true;
                }
            }
            if (checkT) {
                Confixe_SettingdataIn_test.nameLoginCode = namecode;
                // Intent i=new Intent(Login.this,TabSample.class);

                Snackbar.with(getApplicationContext()) // context
                        .text("ยินดีต้อนรับคุณ " + SelectAllData.get(0).get("col1")) // text to display
                        .show(Login.this);




                Intent k = new Intent(Login.this, MainActivityAndroidGridView.class);
                startActivity(k);

            } else {
                // Toast.makeText(Login.this,"  username หรือ password ผิด ", Toast.LENGTH_LONG).show();

                Snackbar.with(getApplicationContext()) // context
                        .text("username หรือ password ผิด") // text to display
                        .show(Login.this);
            }

        } else {  // validate ข้อมูล ไม่ครบท้วน

            Snackbar.with(getApplicationContext()) // context
                    .text(" กรุณากรอกข้อมูลให้ครบค่ะ"); // text to displa.show(Login.this);
        }

    }
}