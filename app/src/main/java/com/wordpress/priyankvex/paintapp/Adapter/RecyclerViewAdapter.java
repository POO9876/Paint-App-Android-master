package com.wordpress.priyankvex.paintapp.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.priyankvex.paintapp.Model.ItemObject;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.holder.RecyclerViewHolders;

import java.util.List;

/**
 * Created by PongPloy2016 on 21/1/2560.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ItemObject> itemList;
    private Context context;
    private int checkData;

    public RecyclerViewAdapter(Context context, List<ItemObject> itemList, int check ) {


        this.itemList = itemList;
        this.context = context;
        this.checkData = check ;
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView,itemList,context, checkData);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
        holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
