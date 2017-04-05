package com.wordpress.priyankvex.paintapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wordpress.priyankvex.paintapp.Actvity.MenuActivityTH;
import com.wordpress.priyankvex.paintapp.Actvity.MenuActivityAZ;
import com.wordpress.priyankvex.paintapp.Adapter.CustomAdapterAndroidGridView;
import com.wordpress.priyankvex.paintapp.Model.ItemObjectAndroidGridView;

import java.util.ArrayList;
import java.util.List;


public class MainActivityAndroidGridView extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gridview);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        List<ItemObjectAndroidGridView> allItems = getAllItemObject();
        CustomAdapterAndroidGridView customAdapter = new CustomAdapterAndroidGridView(MainActivityAndroidGridView.this, allItems);
        gridview.setAdapter(customAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){

                    Intent i = new Intent(MainActivityAndroidGridView.this, MenuActivityTH.class);
                    startActivity(i);
                }else   if(position==1){
                   // File_Confix_Data.position_id_vdo1=0;
                    Intent i = new Intent(MainActivityAndroidGridView.this, MenuActivityAZ.class);
                    startActivity(i);
                }
//                else    if(position==2)
//                {
//                    File_Confix_Data.position_id_vdo1=0;
//                    Intent i = new Intent(MainActivityAndroidGridView.this, Login.class);
//                    startActivity(i);
//                }
                else    if(position==2){
                 //   File_Confix_Data.position_id_vdo1=0;
                    Intent i = new Intent(MainActivityAndroidGridView.this, Profile.class);
                    startActivity(i);



                }else    if(position==4){
//                    File_Confix_Data.position_id_vdo1=0;
//                    Intent i = new Intent(MainActivityAndroidGridView.this, MainActivity4.class);
//                    startActivity(i);


                }else    if(position==5){


//                    File_Confix_Data.position_id_vdo1=0;
//                    Intent i = new Intent(MainActivityAndroidGridView.this, Login.class);
//                    startActivity(i);
                }


            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<ItemObjectAndroidGridView> getAllItemObject(){
        ItemObjectAndroidGridView itemObject = null;
        List<ItemObjectAndroidGridView> items = new ArrayList<>();
       // items.add(new ItemObjectAndroidGridView("ฝึกเขียน", "three"));
        items.add(new ItemObjectAndroidGridView("ฝึกเขียน ก - ฮ", "kkhk"));
        items.add(new ItemObjectAndroidGridView("ฝึกเขียน A - Z", "abc"));
//        items.add(new ItemObjectAndroidGridView("ฝึกเขียน 0 - 9", "three"));
//        items.add(new ItemObjectAndroidGridView("ฝึกเขียน 0 - ๙", "three"));
     //   items.add(new ItemObjectAndroidGridView("แบบทดสอบ", "korkai"));
        items.add(new ItemObjectAndroidGridView("จัดทำโดย", "two"));


        return items;
    }
}
