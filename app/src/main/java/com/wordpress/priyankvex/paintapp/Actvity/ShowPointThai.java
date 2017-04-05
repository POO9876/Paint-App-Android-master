package com.wordpress.priyankvex.paintapp.Actvity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wordpress.priyankvex.paintapp.Adapter.PointTHAdapter;
import com.wordpress.priyankvex.paintapp.Model.ItemPoint;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseEnglish;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseThai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowPointThai extends AppCompatActivity {


    private DatabaseThai databaseThai;
    private DatabaseEnglish databaseEnglish;
    //  private ItemPoint itempoint ;


    private RecyclerView recyclerView;
    private PointTHAdapter mAdapter;
    public ArrayList<ItemPoint> ItemPoints;
    public ArrayList<ItemPoint> TtemPomitsEN;

    private String pointthai;
    private String username;
    private String letterthai;
    private String code;
    private   int sum = 0;

    ArrayList<Integer> aa = new ArrayList<Integer>();
    ArrayList<Integer> bb = new ArrayList<Integer>();

    int value;
    private TextView point2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point_thai);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getInt("show_point_key_2");

            Log.e("value", String.valueOf(value));

            if (value == 1) {


                ItemPoints = new ArrayList<ItemPoint>();
                databaseThai = new DatabaseThai(this);


                ArrayList<HashMap<String, String>> SelectAllData = databaseThai.SelectAllData();
                Log.e("mAdapter Thai", SelectAllData.toString());
                String pointthai1 = SelectAllData.get(1).get("pointthai");
                String pointthai2 = SelectAllData.get(2).get("pointthai");

                Log.e("SelectAllData pointthai", pointthai1);
                Log.e("SelectAllData pointthai", pointthai2);

                String json = new Gson().toJson(SelectAllData);
                Log.e("json", json);

                int Sum01 = 0;


                JSONArray jArray = null;
                try {
                    jArray = new JSONArray(json);
                    Log.e("jArray", jArray.toString());
                    for (int i = 0; i < jArray.length(); i++) {

                        JSONObject json_data = null;
                        try {
                            json_data = jArray.getJSONObject(i);

                            ItemPoint itempoint = new ItemPoint();


                            pointthai = json_data.getString("pointthai");
                            username = json_data.getString("username");
                            letterthai = json_data.getString("letterthai");
                            code = json_data.getString("code");

                            Log.e("pointthai", pointthai);
                            Log.e("datalist", username);
                            Log.e("letterthai", letterthai);


                            itempoint.setPointthai(pointthai);
                            itempoint.setUsername(username);
                            itempoint.setLetterthai(letterthai);
                            itempoint.setNumber(code);
                            ItemPoints.add(itempoint);

                            int a = Integer.parseInt(ItemPoints.get(i).getPointthai());

                            aa.add(a);


                            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            mAdapter = new PointTHAdapter(ItemPoints);

                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    Log.e("Sum01pointthai", String.valueOf(Sum01));

                } catch (JSONException e) {
                    e.printStackTrace();
                }



                for (int u : aa) {
                    sum += u;
                }

                Log.e("sum sum ", String.valueOf(sum));


            } else if (value == 2) {


                TtemPomitsEN = new ArrayList<ItemPoint>();


                databaseEnglish = new DatabaseEnglish(this);


                ArrayList<HashMap<String, String>> SelectAllData = databaseEnglish.SelectAllData();


                Log.e("mAdapter Thai", SelectAllData.toString());

//                String pointthai1 =  SelectAllData.get(1).get("pointthai");
//                String pointthai2 =  SelectAllData.get(2).get("pointthai");
//
//                Log.e("SelectAllData pointthai", pointthai1);
//                Log.e("SelectAllData pointthai", pointthai2);

                String json = new Gson().toJson(SelectAllData);
                Log.e("jsonEN", json);


                JSONArray jArray = null;
                try {
                    jArray = new JSONArray(json);

                    Log.e("jArrayEN", jArray.toString());

                    for (int i = 0; i < jArray.length(); i++) {

                        JSONObject json_data = null;
                        try {
                            json_data = jArray.getJSONObject(i);

                            ItemPoint itempoint = new ItemPoint();


                            pointthai = json_data.getString("pointenglish");
                            username = json_data.getString("username");
                            letterthai = json_data.getString("letterenglish");
                            code = json_data.getString("code");

                            Log.e("pointthai", pointthai);
                            Log.e("datalist", username);
                            Log.e("letterthai", letterthai);


                            itempoint.setPointthai(pointthai);
                            itempoint.setUsername(username);
                            itempoint.setLetterthai(letterthai);
                            itempoint.setNumber(code);
                            TtemPomitsEN.add(itempoint);

                            int a = Integer.parseInt(TtemPomitsEN.get(i).getPointthai());

                            bb.add(a);

                            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                            mAdapter = new PointTHAdapter(TtemPomitsEN);

                            recyclerView.setHasFixedSize(true);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


                for (int u : bb) {
                    sum += u;
                }

                Log.e("sum sum ", String.valueOf(sum));


            }
            //The key argument here must match that used in the other activity
        }


//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                ItemPoint itemPoint = ItemPoints.get(position);
//                Toast.makeText(getApplicationContext(), itemPoint.getLetterthai() + " is selected!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }


        //        for(int i=0;i<json.length();i++){
//
//            JSONObject json_data = json.getJSONObjec;
//            pointthai =  SelectAllData.get(i).get("pointthai");
//            username =  SelectAllData.get(i).get("username");
//            letterthai =  SelectAllData.get(i).get("letterthai");
//
//            itempoint.setPointthai(pointthai);
//            itempoint.setUsername(username);
//            itempoint.setLetterthai(letterthai);
//
//            Log.e("datalist",username);
//            Log.e("letterthai",letterthai);
//            Log.e("pointthai",pointthai);
//            ItemPoints.add(itempoint);
//
//            mAdapter.notifyDataSetChanged();
//        }


        // for(int y= 0 ;y< 42 ; y++){
        //.  Log.e("ItemPoints letterthai",ItemPoints.get(22).getLetterthai());

        //  }


        initView();
    }

    private void initView() {
        point2 = (TextView) findViewById(R.id.point_2);

        point2.setText(String.valueOf(sum));
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
