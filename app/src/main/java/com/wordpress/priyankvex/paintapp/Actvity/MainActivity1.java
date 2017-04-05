package com.wordpress.priyankvex.paintapp.Actvity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wordpress.priyankvex.paintapp.DrawingView.DrawingViewTH;
import com.wordpress.priyankvex.paintapp.File_Confix_Data;
import com.wordpress.priyankvex.paintapp.MainActivityAndroidGridView;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseThai;
import com.wordpress.priyankvex.paintapp.TinyDB.TinyDB;

import java.util.ArrayList;
import java.util.UUID;


public class MainActivity1 extends AppCompatActivity implements View.OnClickListener{
    int[] ButtonColors = {Color.parseColor("#eeeeee"),Color.parseColor("#ffffff")};
    private DrawingViewTH mDrawingView;
    private ImageButton currPaint, drawButton, eraseButton, newButton, saveButton ,buttonBack , buttonHome ;

     public static ImageButton button_ture;
    public static ImageButton button_flase;
    private float smallBrush, mediumBrush, largeBrush;
    // private int Image_next_black=0;
    private MediaPlayer playimgButton;
    private MediaPlayer playimgS;
    private  int NumberCheckData ;
    private DatabaseThai databaseThai ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thai);

        databaseThai = new DatabaseThai(this);

        AddDataXY();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            NumberCheckData = bundle.getInt("EXTRA_SESSION_ID");

            Log.e("url", String.valueOf(NumberCheckData));
        }



//        int  = 0 ;
//        for( i = 0 ; i<= 43 ; i++){
//            if(NumberCheckData == 1){
//
//            }
//            else if(NumberCheckData == 2){
//
//
//            }
//
//        }





        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, ButtonColors);
        gradientDrawable.setCornerRadius(0f);

        playimgButton = MediaPlayer.create(MainActivity1.this, R.raw.button);


        mDrawingView = (DrawingViewTH)findViewById(R.id.drawing);
        Drawable myDrawable = getResources().getDrawable(File_Confix_Data.image_confixe1[NumberCheckData]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mDrawingView.setBackground(myDrawable);
        }
        // Getting the initial paint color.
        LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
        // 0th child is white color, so selecting first child to give black as initial color.
        currPaint = (ImageButton)paintLayout.getChildAt(1);
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.pallet_pressed));
        drawButton = (ImageButton) findViewById(R.id.buttonBrush);

        drawButton.setOnClickListener(this);
        eraseButton = (ImageButton) findViewById(R.id.buttonErase);

        eraseButton.setOnClickListener(this);
        newButton = (ImageButton) findViewById(R.id.buttonNew);

        newButton.setOnClickListener(this);
        saveButton = (ImageButton) findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(this);

        buttonBack = (ImageButton) findViewById(R.id.button_black_page);
        buttonBack.setOnClickListener(this);

        buttonHome = (ImageButton) findViewById(R.id.button_home);
        buttonHome.setOnClickListener(this);

        button_ture = (ImageButton) findViewById(R.id.bt_button_ture_k);
        button_ture.setOnClickListener(this);

        button_flase = (ImageButton) findViewById(R.id.bt_button_flase_k);
        button_flase.setOnClickListener(this);



        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        // Set the initial brush size
        mDrawingView.setBrushSize(mediumBrush);

        ImageButton smallBtn = (ImageButton) findViewById(R.id.button_black);

        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playimgButton.start();
                if(NumberCheckData>0){
                    NumberCheckData--;
                    File_Confix_Data.position_id_vdo1=NumberCheckData;
                    mDrawingView.setNextImage(NumberCheckData);
                    Drawable myDrawable = getResources().getDrawable(File_Confix_Data.image_confixe1[NumberCheckData]);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mDrawingView.setBackground(myDrawable);

                        TinyDB tinydb = new TinyDB(v.getContext());
                        tinydb.putInt("data", NumberCheckData);

                        button_ture.setVisibility(View.INVISIBLE);
                        button_flase.setVisibility(View.INVISIBLE);
                    }
                    mDrawingView.startNew();



                }else{
                    Toast savedToast = Toast.makeText(getApplicationContext(), "รูปแรกแล้วครับ", Toast.LENGTH_SHORT);
                    savedToast.show();
                }

            }
        });

        ImageButton button_next = (ImageButton) findViewById(R.id.button_next);

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playimgButton.start();
                if(NumberCheckData < File_Confix_Data.image_confixe1.length-1){
                    NumberCheckData++;
                    File_Confix_Data.position_id_vdo1=NumberCheckData;
                    mDrawingView.setNextImage(NumberCheckData);
                    Drawable myDrawable = getResources().getDrawable(File_Confix_Data.image_confixe1[NumberCheckData]);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mDrawingView.setBackground(myDrawable);

                        TinyDB tinydb = new TinyDB(v.getContext());
                        tinydb.putInt("data", NumberCheckData);

                        button_ture.setVisibility(View.INVISIBLE);
                        button_flase.setVisibility(View.INVISIBLE);
                    }

                    mDrawingView.startNew();

                }else{
                    Toast savedToast = Toast.makeText(getApplicationContext(),"รูปสุดท้ายแล้วครับ", Toast.LENGTH_SHORT);
                    savedToast.show();
                }

            }
        });
        ImageButton button_VDO = (ImageButton) findViewById(R.id.buttonVDO);
        button_VDO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playimgButton.start();
                Intent myIntent = new Intent(getApplicationContext(), VideoStream1.class);
                myIntent.putExtra("key",NumberCheckData);
                startActivity(myIntent);

            }
        });
        ImageButton button_sound = (ImageButton) findViewById(R.id.buttonsound);
        button_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playimgS = MediaPlayer.create(MainActivity1.this, File_Confix_Data.sound1[NumberCheckData]);
                playimgS.start();

            }
        });


        ImageButton bt_button_ture = (ImageButton) findViewById(R.id.bt_button_ture_k);
        bt_button_ture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ShowPointThai.class);
                myIntent.putExtra("show_point_key_2",1);
                startActivity(myIntent);
            }
        });


        ImageButton bt_button_flase = (ImageButton) findViewById(R.id.bt_button_flase_k);
        bt_button_flase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ShowPointThai.class);
                myIntent.putExtra("show_point_key_2",1);
                startActivity(myIntent);
            }
        });

    }


    private void AddDataXY(){

        ArrayList<Integer> arrlist = new ArrayList<Integer>();

        // use add() method to add elements in the list
//                arrlist.add(touchX);
//                arrlist.add(touchY);
//                arrlist.add(30);
//                arrlist.add(40);

        // adding element 25 at third position
        arrlist.add(0, 786); //จุดก่อน X ก
        arrlist.add(1, 800);//จุดสิ้นสุด x ข
        arrlist.add(2, 475);//จุดสิ้นสุด x ฃ
        arrlist.add(3, 851);//จุดสิ้นสุด x ค
        arrlist.add(4, 849);//จุดสิ้นสุด x ค
        arrlist.add(5, 728);//จุดสิ้นสุด x ฆ
        arrlist.add(6, 1046);//จุดสิ้นสุด x ง
        arrlist.add(7, 954);//จุดสิ้นสุด x จ
        arrlist.add(8, 860);//จุดสิ้นสุด x ฉ
        arrlist.add(9, 740);//จุดสิ้นสุด x ฉ


        arrlist.add(10, 701);//จุดสิ้นสุด x ซ
        arrlist.add(11, 753);//จุดสิ้นสุด x ฌ
        arrlist.add(12, 796);//จุดสิ้นสุด x ญ
        arrlist.add(13, 788);//จุดสิ้นสุด x ฎ
        arrlist.add(14, 814);//จุดสิ้นสุด x ฏ
        arrlist.add(15, 860);//จุดสิ้นสุด x ฐ
        arrlist.add(16, 643);//จุดสิ้นสุด x ฑ
        arrlist.add(17, 874);//จุดสิ้นสุด x ฒ
        arrlist.add(18, 807);//จุดสิ้นสุด x ณ
        arrlist.add(19, 931);//จุดสิ้นสุด x ด


        arrlist.add(20, 895);//จุดสิ้นสุด x ต
        arrlist.add(21, 806);//จุดสิ้นสุด x ถ
        arrlist.add(22, 815);//จุดสิ้นสุด x ท
        arrlist.add(23, 794);//จุดสิ้นสุด x ท
        arrlist.add(24, 825);//จุดสิ้นสุด x น
        arrlist.add(25, 818);//จุดสิ้นสุด x บ
        arrlist.add(26, 801);//จุดสิ้นสุด x ป
        arrlist.add(27, 798);//จุดสิ้นสุด x ผ
        arrlist.add(28, 743);//จุดสิ้นสุด x ฝ
        arrlist.add(29, 808);//จุดสิ้นสุด x พ


        arrlist.add(30, 737);//จุดสิ้นสุด x ฟ
        arrlist.add(31, 800);//จุดสิ้นสุด x ภ
        arrlist.add(32, 898);//จุดสิ้นสุด x ม
        arrlist.add(33, 732);//จุดสิ้นสุด x ย
        arrlist.add(34, 1051);//จุดสิ้นสุด x ร
        arrlist.add(35, 828);//จุดสิ้นสุด x ล
        arrlist.add(36, 700);//จุดสิ้นสุด x ว
        arrlist.add(37, 808);//จุดสิ้นสุด x ศ
        arrlist.add(38, 832);//จุดสิ้นสุด x ษ
        arrlist.add(39, 1115);//จุดสิ้นสุด x ส
        arrlist.add(40, 856);//จุดสิ้นสุด x ส
        arrlist.add(41, 834);//จุดสิ้นสุด x ฬ
        arrlist.add(42, 700);//จุดสิ้นสุด x อ
        arrlist.add(43, 801);//จุดสิ้นสุด x อ
        //

        TinyDB tinydb = new TinyDB(getApplicationContext());

        tinydb.putListInt("MyPaint1", arrlist);
    }


    public void paintClicked(View view){
        if (view != currPaint){
            // Update the color
            ImageButton imageButton = (ImageButton) view;
            String colorTag = imageButton.getTag().toString();
            mDrawingView.setColor(colorTag);
            // Swap the backgrounds for last active and currently active image button.
            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.pallet_pressed));
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.pallet));
            currPaint = (ImageButton)view;
            mDrawingView.setErase(false);
            mDrawingView.setBrushSize(mDrawingView.getLastBrushSize());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case R.id.buttonBrush:
                // Show brush size chooser dialog
                playimgButton.start();
                showBrushSizeChooserDialog();
                break;
            case R.id.buttonErase:
                // Show eraser size chooser dialog
                showEraserSizeChooserDialog();
                break;
            case R.id.buttonNew:
                // Show new painting alert dialog
                playimgButton.start();
                showNewPaintingAlertDialog();
                break;
            case R.id.buttonSave:
                // Show save painting confirmation dialog.
                playimgButton.start();
                showSavePaintingConfirmationDialog();
                break;
            case R.id.button_black_page :

                onBackPressed();

                break;

            case R.id.button_home :

                Intent i = new Intent(MainActivity1.this, MainActivityAndroidGridView.class);
                startActivity(i);
                break;
        }
    }

    private void showBrushSizeChooserDialog(){
        final Dialog brushDialog = new Dialog(this);
        brushDialog.setContentView(R.layout.dialog_brush_size);
        brushDialog.setTitle("Brush size:");
        ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(smallBrush);
                mDrawingView.setLastBrushSize(smallBrush);
                brushDialog.dismiss();
            }
        });
        ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(mediumBrush);
                mDrawingView.setLastBrushSize(mediumBrush);
                brushDialog.dismiss();
            }
        });

        ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDrawingView.setBrushSize(largeBrush);
                mDrawingView.setLastBrushSize(largeBrush);
                brushDialog.dismiss();
            }
        });
        mDrawingView.setErase(false);
        brushDialog.show();
    }

    private void showEraserSizeChooserDialog(){
        final Dialog brushDialog = new Dialog(this);
        brushDialog.setTitle("Eraser size:");
        brushDialog.setContentView(R.layout.dialog_brush_size);
        ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
        smallBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(smallBrush);
                brushDialog.dismiss();
            }
        });
        ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
        mediumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(mediumBrush);
                brushDialog.dismiss();
            }
        });
        ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
        largeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawingView.setErase(true);
                mDrawingView.setBrushSize(largeBrush);
                brushDialog.dismiss();
            }
        });
        brushDialog.show();
    }

    private void showNewPaintingAlertDialog(){
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("New drawing");
        newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
        newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mDrawingView.startNew();
                button_ture.setVisibility(View.INVISIBLE);
                button_flase.setVisibility(View.INVISIBLE);
                dialog.dismiss();

            }
        });
        newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        newDialog.show();
    }

    private void showSavePaintingConfirmationDialog(){
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                //save drawing
                mDrawingView.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), mDrawingView.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");
                if(imgSaved!=null){
                    Toast savedToast = Toast.makeText(getApplicationContext(),
                            "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                    savedToast.show();
                }
                else{
                    Toast unsavedToast = Toast.makeText(getApplicationContext(),
                            "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                    unsavedToast.show();
                }
                // Destroy the current cache.
                mDrawingView.destroyDrawingCache();
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
    }

}
