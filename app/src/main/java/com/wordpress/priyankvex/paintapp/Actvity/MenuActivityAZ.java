package com.wordpress.priyankvex.paintapp.Actvity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wordpress.priyankvex.paintapp.Adapter.RecyclerViewAdapter;
import com.wordpress.priyankvex.paintapp.Model.ItemObject;
import com.wordpress.priyankvex.paintapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PongPloy2016 on 24/1/2560.
 */

public class MenuActivityAZ extends ActionBarActivity {

    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(MenuActivityAZ.this, 4);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MenuActivityAZ.this, rowListItem,2);
        rView.setAdapter(rcAdapter);
    }



    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("A", R.drawable.a,0));
        allItems.add(new ItemObject("B", R.drawable.b,1));
        allItems.add(new ItemObject("C", R.drawable.c,2));
        allItems.add(new ItemObject("D", R.drawable.d,3));
        allItems.add(new ItemObject("E", R.drawable.e,4));
        allItems.add(new ItemObject("F", R.drawable.f,5));
        allItems.add(new ItemObject("G", R.drawable.g,6));
        allItems.add(new ItemObject("H", R.drawable.h,7));
        allItems.add(new ItemObject("I", R.drawable.i,8));
        allItems.add(new ItemObject("J", R.drawable.j,9));

        allItems.add(new ItemObject("K", R.drawable.k,10));
        allItems.add(new ItemObject("L", R.drawable.l,11));
        allItems.add(new ItemObject("M", R.drawable.m,12));
        allItems.add(new ItemObject("N", R.drawable.n,13));
        allItems.add(new ItemObject("O", R.drawable.o,14));
        allItems.add(new ItemObject("P", R.drawable.p,15));
        allItems.add(new ItemObject("Q", R.drawable.q,16));
        allItems.add(new ItemObject("R", R.drawable.r,17));
        allItems.add(new ItemObject("S", R.drawable.s,18));
        allItems.add(new ItemObject("T", R.drawable.t,19));

        allItems.add(new ItemObject("U", R.drawable.u,20));
        allItems.add(new ItemObject("V", R.drawable.v,21));
        allItems.add(new ItemObject("W", R.drawable.w,22));
        allItems.add(new ItemObject("X", R.drawable.x,23));
        allItems.add(new ItemObject("Y", R.drawable.y,24));
        allItems.add(new ItemObject("Z", R.drawable.z,24));


        return allItems;
    }

}
