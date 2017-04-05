package com.wordpress.priyankvex.paintapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpress.priyankvex.paintapp.Model.ItemPoint;
import com.wordpress.priyankvex.paintapp.R;

import java.util.ArrayList;
import java.util.stream.IntStream;

//PointTHAdapter

public class PointTHAdapter extends RecyclerView.Adapter<PointTHAdapter.MyViewHolder>  {

    private ArrayList<ItemPoint> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title_name, point, number, letter;

        public MyViewHolder(View view) {
            super(view);
            title_name = (TextView) view.findViewById(R.id.title_name);
            number = (TextView) view.findViewById(R.id.number);
            point = (TextView) view.findViewById(R.id.point);
            letter = (TextView) view.findViewById(R.id.letter);
        }
    }

    public PointTHAdapter(ArrayList<ItemPoint> moviesList) {
        this.moviesList = moviesList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.container_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemPoint itemPoint = moviesList.get(position);
        holder.title_name.setText(itemPoint.getUsername());
        holder.number.setText(itemPoint.getNumber());
        holder.letter.setText(itemPoint.getLetterthai());
        holder.point.setText(itemPoint.getPointthai());
        // holder.title_name.setText(itemPoint.s());

        Log.e("itemPoint.getUsername", itemPoint.getUsername());
        Log.e("itemPoint.getLetterthai", itemPoint.getLetterthai());
        Log.e("itemPoint.getPointthai", itemPoint.getPointthai());



    }



    @Override
    public int getItemCount() {

        Log.e("moviesList.size()", String.valueOf(moviesList.size()));
        return moviesList.size();


    }




}
