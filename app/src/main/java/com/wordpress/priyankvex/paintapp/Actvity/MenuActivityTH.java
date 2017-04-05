package com.wordpress.priyankvex.paintapp.Actvity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;

import com.wordpress.priyankvex.paintapp.Adapter.RecyclerViewAdapter;
import com.wordpress.priyankvex.paintapp.Model.ItemObject;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseThai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuActivityTH extends ActionBarActivity {

    private GridLayoutManager lLayout;
    private DatabaseThai databaseThai ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        databaseThai = new DatabaseThai(this);


        ArrayList<HashMap<String, String>> SelectAllDataThai = databaseThai.SelectAllData();
        Log.e("SelectAllDataThai", SelectAllDataThai.toString());
        String joined = TextUtils.join(", ", SelectAllDataThai);
        Log.e("joined",joined);

        SelectAllDataThai = databaseThai.SelectAllData();
        Log.e("SelectAllDataThai", SelectAllDataThai.toString());


        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(MenuActivityTH.this, 4);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MenuActivityTH.this, rowListItem,1);
        rView.setAdapter(rcAdapter);
    }

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject(getResources().getString(R.string.k_1), R.drawable.k_one,0));
        allItems.add(new ItemObject(getResources().getString(R.string.k_2), R.drawable.k_two,1));
        allItems.add(new ItemObject(getResources().getString(R.string.k_3), R.drawable.k_three,2));
        allItems.add(new ItemObject(getResources().getString(R.string.k_4), R.drawable.k_four,3));
        allItems.add(new ItemObject(getResources().getString(R.string.k_5), R.drawable.k_five,4));
        allItems.add(new ItemObject(getResources().getString(R.string.k_6), R.drawable.k_six,5));
        allItems.add(new ItemObject(getResources().getString(R.string.k_7), R.drawable.k_seven,6));
        allItems.add(new ItemObject(getResources().getString(R.string.k_8), R.drawable.k_eight,7));
        allItems.add(new ItemObject(getResources().getString(R.string.k_9), R.drawable.k_nine,8));
        allItems.add(new ItemObject(getResources().getString(R.string.k_10), R.drawable.k_ten,9));

        allItems.add(new ItemObject(getResources().getString(R.string.k_11), R.drawable.k_eleven,10));
        allItems.add(new ItemObject(getResources().getString(R.string.k_12), R.drawable.k_twelve,11));
        allItems.add(new ItemObject(getResources().getString(R.string.k_13), R.drawable.k_thirteen,12));
        allItems.add(new ItemObject(getResources().getString(R.string.k_14), R.drawable.k_fifteen,13));
        allItems.add(new ItemObject(getResources().getString(R.string.k_15), R.drawable.k_fifteen,14));
        allItems.add(new ItemObject(getResources().getString(R.string.k_16), R.drawable.k_sixteen,15));
        allItems.add(new ItemObject(getResources().getString(R.string.k_17), R.drawable.k_seventeen,16));
        allItems.add(new ItemObject(getResources().getString(R.string.k_18), R.drawable.k_eighteen,17));
        allItems.add(new ItemObject(getResources().getString(R.string.k_19), R.drawable.k_nineteen,18));
        allItems.add(new ItemObject(getResources().getString(R.string.k_20), R.drawable.k_twenty,19));

        allItems.add(new ItemObject(getResources().getString(R.string.k_21), R.drawable.k_twenty_one,20));
        allItems.add(new ItemObject(getResources().getString(R.string.k_22), R.drawable.k_twenty_two,21));
        allItems.add(new ItemObject(getResources().getString(R.string.k_23), R.drawable.k_twenty_three,22));
        allItems.add(new ItemObject(getResources().getString(R.string.k_24), R.drawable.k_thirty_four,23));
        allItems.add(new ItemObject(getResources().getString(R.string.k_25), R.drawable.k_twenty_five,24));
        allItems.add(new ItemObject(getResources().getString(R.string.k_26), R.drawable.k_twenty_six,25));
        allItems.add(new ItemObject(getResources().getString(R.string.k_27), R.drawable.k_twenty_seven,26));
        allItems.add(new ItemObject(getResources().getString(R.string.k_28), R.drawable.k_twenty_eight,27));
        allItems.add(new ItemObject(getResources().getString(R.string.k_29), R.drawable.k_twenty_nine,28));
        allItems.add(new ItemObject(getResources().getString(R.string.k_30), R.drawable.k_thirty,29));

        allItems.add(new ItemObject(getResources().getString(R.string.k_31), R.drawable.k_thirty_one,30));
        allItems.add(new ItemObject(getResources().getString(R.string.k_32), R.drawable.k_thirty_two,31));
        allItems.add(new ItemObject(getResources().getString(R.string.k_33), R.drawable.k_thirty_three,32));
        allItems.add(new ItemObject(getResources().getString(R.string.k_34), R.drawable.k_thirty_four,33));
        allItems.add(new ItemObject(getResources().getString(R.string.k_35), R.drawable.k_thirty_five,34));
        allItems.add(new ItemObject(getResources().getString(R.string.k_36), R.drawable.k_thirty_six,35));
        allItems.add(new ItemObject(getResources().getString(R.string.k_37), R.drawable.k_thirty_seven,36));
        allItems.add(new ItemObject(getResources().getString(R.string.k_38), R.drawable.k_thirty_eight,37));
        allItems.add(new ItemObject(getResources().getString(R.string.k_39), R.drawable.k_twenty_nine,38));
        allItems.add(new ItemObject(getResources().getString(R.string.k_40), R.drawable.k_forty,39));

        allItems.add(new ItemObject(getResources().getString(R.string.k_41), R.drawable.k_forty_one,40));
        allItems.add(new ItemObject(getResources().getString(R.string.k_42), R.drawable.k_forty_two,41));
        allItems.add(new ItemObject(getResources().getString(R.string.k_43), R.drawable.k_forty_three,42));
        allItems.add(new ItemObject(getResources().getString(R.string.k_44), R.drawable.k_forty_four,43));

        return allItems;
    }
}
