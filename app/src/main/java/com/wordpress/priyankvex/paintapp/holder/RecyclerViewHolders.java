package com.wordpress.priyankvex.paintapp.holder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.priyankvex.paintapp.Actvity.MainActivity1;
import com.wordpress.priyankvex.paintapp.Actvity.MainActivity2;
import com.wordpress.priyankvex.paintapp.Model.ItemObject;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.TinyDB.TinyDB;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView countryName;
    public ImageView countryPhoto;
    private ItemObject itemObject ;
    private List<ItemObject> itemList1 ;
    private int data ;
    private Context context1 ;
    private int check1 ;

    public RecyclerViewHolders(View itemView,List<ItemObject> itemList , Context context, int check) {
        super(itemView);

        context1 =context ;
        itemList1 = itemList ;
        check1 = check ;


        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }

    @Override
    public void onClick(View view) {
        data = itemList1.get(getPosition()).getNumber();

        TinyDB tinydb = new TinyDB(view.getContext());

        tinydb.putInt("data", data);

        Log.e("checkData", String.valueOf(check1));
        if(check1 == 1){
            Intent intent = new Intent(context1, MainActivity1.class);
            intent.putExtra("EXTRA_SESSION_ID", data);
            context1.startActivity(intent);
        }
        else if(check1 == 2){
            Intent intent = new Intent(context1, MainActivity2.class);
            intent.putExtra("EXTRA_SESSION_ID_DATA_2", data);
            context1.startActivity(intent);

        }


      //  Toast.makeText(view.getContext(), " คุณเลือกลำดับที่ = " + getPosition() + "\t" +" "+ + data , Toast.LENGTH_SHORT).show();
    }
}