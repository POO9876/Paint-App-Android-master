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

import com.wordpress.priyankvex.paintapp.DrawingView.DrawingViewEN2;
import com.wordpress.priyankvex.paintapp.File_Confix_Data;
import com.wordpress.priyankvex.paintapp.MainActivityAndroidGridView;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.TinyDB.TinyDB;

import java.util.ArrayList;
import java.util.UUID;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    int[] ButtonColors = {Color.parseColor("#eeeeee"),Color.parseColor("#ffffff")};
    private DrawingViewEN2 mDrawingView;
    private ImageButton currPaint, drawButton, eraseButton, newButton, saveButton ,buttonBack ,buttonHome ;
           public static ImageButton button_ture_EN, button_flase_EN;
    private float smallBrush, mediumBrush, largeBrush;
    private int Image_next_black=0;
    private MediaPlayer playimgButton;
    private MediaPlayer playimgS;
    private int  NumberCheckData ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            NumberCheckData = bundle.getInt("EXTRA_SESSION_ID_DATA_2");

            Log.e("url", String.valueOf(NumberCheckData));
        }

        AddDataXY();

        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, ButtonColors);
        gradientDrawable.setCornerRadius(0f);

        playimgButton = MediaPlayer.create(MainActivity2.this, R.raw.button);


        mDrawingView = (DrawingViewEN2)findViewById(R.id.drawing);


        Drawable myDrawable = getResources().getDrawable(File_Confix_Data.imgMenu2[NumberCheckData]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mDrawingView.setBackground(myDrawable);
        }
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

        button_ture_EN = (ImageButton) findViewById(R.id.bt_button_ture_ab);
        button_ture_EN.setOnClickListener(this);

        button_flase_EN = (ImageButton) findViewById(R.id.bt_button_flase_ab);
        button_flase_EN.setOnClickListener(this);


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
                    File_Confix_Data.position_id_vdo1=Image_next_black;
                    mDrawingView.setNextImage(Image_next_black);
                    Drawable myDrawable = getResources().getDrawable(File_Confix_Data.imgMenu2[NumberCheckData]);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mDrawingView.setBackground(myDrawable);

                        TinyDB tinydb = new TinyDB(v.getContext());
                        tinydb.putInt("data", NumberCheckData);
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
                if(NumberCheckData<File_Confix_Data.imgMenu2.length-1){
                    NumberCheckData++;
                    File_Confix_Data.position_id_vdo1=Image_next_black;
                    mDrawingView.setNextImage(Image_next_black);
                    Drawable myDrawable = getResources().getDrawable(File_Confix_Data.imgMenu2[NumberCheckData]);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mDrawingView.setBackground(myDrawable);

                        TinyDB tinydb = new TinyDB(v.getContext());
                        tinydb.putInt("data", NumberCheckData);

                        button_ture_EN.setVisibility(View.INVISIBLE);
                        button_flase_EN.setVisibility(View.INVISIBLE);
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
                Intent myIntent = new Intent(getApplicationContext(), VideoStream2.class);
                myIntent.putExtra("key_2",NumberCheckData);
               startActivity(myIntent);

            }
        });
        ImageButton button_sound = (ImageButton) findViewById(R.id.buttonsound);
        button_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playimgS = MediaPlayer.create(MainActivity2.this, File_Confix_Data.imgMenu1MediaPlayer1[NumberCheckData]);
                playimgS.start();

            }
        });

        ImageButton bt_button_ture = (ImageButton) findViewById(R.id.bt_button_ture_ab);
        bt_button_ture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ShowPointThai.class);
                myIntent.putExtra("show_point_key_2",2);
                startActivity(myIntent);
            }
        });


        ImageButton bt_button_flase_k = (ImageButton) findViewById(R.id.bt_button_flase_ab);
        bt_button_flase_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), ShowPointThai.class);
                myIntent.putExtra("show_point_key_2",2);
                startActivity(myIntent);
            }
        });

    }


    private void AddDataXY(){

        ArrayList<Integer> arrlistEN = new ArrayList<Integer>();

        // use add() method to add elements in the list
//                arrlist.add(touchX);
//                arrlist.add(touchY);
//                arrlist.add(30);
//                arrlist.add(40);

        // adding element 25 at third position
        arrlistEN.add(0, 728); //จุดก่อน X A
        arrlistEN.add(1, 779);//จุดสิ้นสุด x B
        arrlistEN.add(2, 1200);//จุดสิ้นสุด x C
        arrlistEN.add(3, 815);//จุดสิ้นสุด x D
        arrlistEN.add(4, 798);//จุดสิ้นสุด x E
        arrlistEN.add(5, 728);//จุดสิ้นสุด x F
        arrlistEN.add(6, 1181);//จุดสิ้นสุด x G
        arrlistEN.add(7, 733);//จุดสิ้นสุด x H
        arrlistEN.add(8, 881);//จุดสิ้นสุด x I
        arrlistEN.add(9, 980);//จุดสิ้นสุด x J


        arrlistEN.add(10, 805);//จุดสิ้นสุด x K
        arrlistEN.add(11, 803);//จุดสิ้นสุด x L
        arrlistEN.add(12, 657);//จุดสิ้นสุด x M
        arrlistEN.add(13, 710);//จุดสิ้นสุด x N
        arrlistEN.add(14, 669);//จุดสิ้นสุด x O
        arrlistEN.add(15, 754);//จุดสิ้นสุด x P
        arrlistEN.add(16, 1013);//จุดสิ้นสุด x Q
        arrlistEN.add(17, 814);//จุดสิ้นสุด x R
        arrlistEN.add(18, 1138);//จุดสิ้นสุด x S
        arrlistEN.add(19, 710);//จุดสิ้นสุด x T


        arrlistEN.add(20, 789);//จุดสิ้นสุด x U
        arrlistEN.add(21, 714);//จุดสิ้นสุด x V
        arrlistEN.add(22, 724);//จุดสิ้นสุด x W
        arrlistEN.add(23, 749);//จุดสิ้นสุด x X
        arrlistEN.add(24, 729);//จุดสิ้นสุด x Y
        arrlistEN.add(25, 777);//จุดสิ้นสุด x Y
        arrlistEN.add(26, 185);//จุดสิ้นสุด x Y

        //

        TinyDB tinydb = new TinyDB(getApplicationContext());

        tinydb.putListInt("MyPaint1", arrlistEN);
    }

    /**
     * Method is called when color is clicked from pallet.
     * @param view ImageButton on which click took place.
     */
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

                Intent i = new Intent(MainActivity2.this, MainActivityAndroidGridView.class);
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
