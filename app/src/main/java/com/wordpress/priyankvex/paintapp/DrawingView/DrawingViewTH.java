package com.wordpress.priyankvex.paintapp.DrawingView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;


import com.wordpress.priyankvex.paintapp.Actvity.MainActivity1;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseThai;
import com.wordpress.priyankvex.paintapp.TinyDB.TinyDB;

/**
 * Created by Priyank(@priyankvex) on 5/9/15.
 *
 * Class which provides the view on which drawing takes place.
 */
public class DrawingViewTH extends View{
    private int getheight;
    private int getWidth;

    private int getheight_image;
    private int getWidth_image;
    private String DatagetName ;
    // To hold the path that will be drawn.
    private Path drawPath;
    // Paint object to draw drawPath and drawCanvas.
    private Paint drawPaint, canvasPaint;
    // initial color
    private int paintColor = 0xff000000;
    private int previousColor = paintColor;
    // canvas on which drawing takes place.
    private Canvas drawCanvas;
    // canvas bitmap
    private Bitmap canvasBitmap;
    // Brush stroke width
    private float brushSize, lastBrushSize;
    // To enable and disable erasing mode.
    private boolean erase = false;
    private DatabaseThai databaseThai ;

    private int next_image;
    private int black_image;
    public DrawingViewTH(Context context, AttributeSet attrs){
        super(context, attrs);
        setUpDrawing();
    }

    /**
     * Initialize all objects required for drawing here.
     * One time initialization reduces resource consumption.
     */
    private void setUpDrawing(){
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        // Making drawing smooth.
        drawPaint.setAntiAlias(true);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);

        // Initial brush size is medium.
        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;
        drawPaint.setStrokeWidth(brushSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        getWidth=0;
        getheight= 0;


        getWidth_image=drawCanvas.getWidth() ;
        getheight_image=drawCanvas.getHeight() ;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//getWidth
//        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), File_Confix_Data.imgMenu2[next_image]);
//        myBitmap= getResizedBitmap(myBitmap,getWidth_image,getheight_image);
//        canvas.drawBitmap(myBitmap, getWidth, getheight, null);

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // X and Y position of user touch.
        float touchX = event.getX();
        float touchY = event.getY();
        // Draw the path according to the touch event taking place.
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);

                Log.e("touchX_EN", String.valueOf(touchX));
                Log.e("touchY_EN", String.valueOf(touchY));


                TinyDB tinydb = new TinyDB(getContext());
                tinydb.getInt("data");

                tinydb.getString("Myname");

                Log.e("Myname",tinydb.getString("Myname"));

                DatagetName = tinydb.getString("Myname") ;


                databaseThai = new DatabaseThai(getContext());

                                if( tinydb.getInt("data") == 0) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));

//                Log.e("touchXMOVE", String.valueOf(touchX));
//                Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");

//                Log.e("touchYmoveTinyDB1",  tinydb.getListInt("MyPaint1").get(0).toString());
//                Log.e("touchYmoveTinyDB2",  tinydb.getListInt("MyPaint1").get(1).toString());

                    // arrlist.add(0, 791); //จุดก่อน
                    // arrlist.add(1, 1186);//จุดสิ้นสุด

                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    //  arrlist.add(0, 786); //จุดก่อน X ก

                    if (touchX >= tinydb.getListInt("MyPaint1").get(0)) //1180
                    {

                        Log.e("touchmoveTo Ture ", String.valueOf(touchY)); // ก Y = 486

                        if ((450 <= touchY) && (touchY <= 550)) {
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("1",DatagetName,getResources().getString(R.string.k_1),"1");


                        } else if ((450 <= touchY) && (touchY < 490)) {
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");

                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("1",DatagetName,getResources().getString(R.string.k_1),"0");

                        } else if (touchY > 550) {
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("1",DatagetName,getResources().getString(R.string.k_1),"0");
                        }
                        else {

                            Log.e("touchmoveTo Flase ", String.valueOf(touchY)); // ก Y = 486
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("1",DatagetName,getResources().getString(R.string.k_1),"0");
                        }

//                        else {
//                            MainActivity1.button_flase_EN.setVisibility(VISIBLE);
//                            MainActivity1.button_ture_EN.setVisibility(INVISIBLE);
//                        }

                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)

                    }



                }


                if( tinydb.getInt("data") == 1) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));

                Log.e("touchXMOVE", String.valueOf(touchX));
                Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");

                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                 //   arrlist.add(1, 800);//จุดสิ้นสุด x ข

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {

                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));

                        //ข Y = 215

                        if ((250 <= touchY) && (touchY <= 300)) {
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");
                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("2",DatagetName,getResources().getString(R.string.k_2),"1");


                        } else if ((250 <= touchY) && (touchY < 280)) {
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");

                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("2",DatagetName,getResources().getString(R.string.k_2),"0");

                        } else if (touchY > 350) {
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("2",DatagetName,getResources().getString(R.string.k_2),"0");
                        }

                        else {

                            Log.e("touchmoveTo Flase ", String.valueOf(touchY)); // ก Y = 486
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("2",DatagetName,getResources().getString(R.string.k_2),"0");
                        }

                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)


                    }

                }
//*********************************************************************************************

                //ฃ
                if( tinydb.getInt("data") == 2) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฃ Y = 225
                        if ((275 <= touchY) && (touchY <= 325)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("3",DatagetName,getResources().getString(R.string.k_3),"1");

                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((275 <= touchY) && (touchY < 325)) {

                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("3",DatagetName,getResources().getString(R.string.k_3),"0");

                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 375) {

                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("3",DatagetName,getResources().getString(R.string.k_3),"0");

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {

                            Log.e("touchmoveTo Flase ", String.valueOf(touchY)); // ก Y = 486
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("3",DatagetName,getResources().getString(R.string.k_3),"0");

                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //*****************************************************************************
                //ค
                if( tinydb.getInt("data") == 3) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ค Y =500
                        if ((550 <= touchY) && (touchY <= 600)) {
                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                            databaseThai.UpdateData("4",DatagetName,getResources().getString(R.string.k_4),"1");

                        } else if ((550 <= touchY) && (touchY < 580)) {

                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");

                            databaseThai.UpdateData("4",DatagetName,getResources().getString(R.string.k_4),"0");
                        } else if (touchY > 650) {
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            MainActivity1.button_ture.setVisibility(INVISIBLE);

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                            databaseThai.UpdateData("4",DatagetName,getResources().getString(R.string.k_4),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }

                    else {

                        Log.e("touchmoveTo Flase ", String.valueOf(touchY)); // ก Y = 486
                        MainActivity1.button_flase.setVisibility(VISIBLE);
                        MainActivity1.button_ture.setVisibility(INVISIBLE);

                        databaseThai.UpdateData("4",DatagetName,getResources().getString(R.string.k_4),"0");
                    }
                }



                //***********************************************************************************
                //ต
                if( tinydb.getInt("data") == 4) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ค Y =507
                        if ((500 <= touchY) && (touchY <= 607)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("5",DatagetName,getResources().getString(R.string.k_5),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((557 <= touchY) && (touchY < 587)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("5",DatagetName,getResources().getString(R.string.k_5),"0");

                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 657) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("5",DatagetName,getResources().getString(R.string.k_5),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else  {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("5",DatagetName,getResources().getString(R.string.k_5),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //**********************************************************************************

                //ฆ
                if( tinydb.getInt("data") == 5) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฆ Y =202
                        if ((257 <= touchY) && (touchY <= 307)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("6",DatagetName,getResources().getString(R.string.k_6),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((257 <= touchY) && (touchY < 287)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("6",DatagetName,getResources().getString(R.string.k_6),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 357) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                            databaseThai.UpdateData("6",DatagetName,getResources().getString(R.string.k_6),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else  {

                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("6",DatagetName,getResources().getString(R.string.k_6),"0");
                        Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                    }
                }

                //*********************************************************************************
              //ง
                if( tinydb.getInt("data") == 6) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ง Y =340
                        if ((390 <= touchY) && (touchY <= 440)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("7",DatagetName,getResources().getString(R.string.k_7),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((390 <= touchY) && (touchY < 420)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("7",DatagetName,getResources().getString(R.string.k_7),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 490) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("7",DatagetName,getResources().getString(R.string.k_7),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("7",DatagetName,getResources().getString(R.string.k_7),"0");
                    }
                }

                //***********************************************************************************


                //จ
                if( tinydb.getInt("data") == 7) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //จ Y =257
                        if ((200 <= touchY) && (touchY <= 357)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("8",DatagetName,getResources().getString(R.string.k_8),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((307 <= touchY) && (touchY < 337)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("8",DatagetName,getResources().getString(R.string.k_8),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 407) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("8",DatagetName,getResources().getString(R.string.k_8),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("8",DatagetName,getResources().getString(R.string.k_8),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //**********************************************************************************

                //ฉ

                if( tinydb.getInt("data") == 8) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฉ Y =240
                        if ((200 <= touchY) && (touchY <=340)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");
                            databaseThai.UpdateData("9",DatagetName,getResources().getString(R.string.k_9),"1");

                        } else if ((290 <= touchY) && (touchY < 320)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("9",DatagetName,getResources().getString(R.string.k_9),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 390) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("9",DatagetName,getResources().getString(R.string.k_9),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("9",DatagetName,getResources().getString(R.string.k_9),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //*********************************************************************************

                //ช
                if( tinydb.getInt("data") == 9) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ช Y =117
                        if ((140 <= touchY) && (touchY <=217)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("10",DatagetName,getResources().getString(R.string.k_10),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((167 <= touchY) && (touchY < 197)) {
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("10",DatagetName,getResources().getString(R.string.k_10),"0");
                        } else if (touchY > 267) {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("10",DatagetName,getResources().getString(R.string.k_10),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("10",DatagetName,getResources().getString(R.string.k_10),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //*********************************************************************************

                //ซ
                if( tinydb.getInt("data") == 10) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ซ Y =160
                        if ((130 <= touchY) && (touchY <=260)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("11",DatagetName,getResources().getString(R.string.k_11),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((210 <= touchY) && (touchY < 240)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("11",DatagetName,getResources().getString(R.string.k_11),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 310) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("11",DatagetName,getResources().getString(R.string.k_11),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("11",DatagetName,getResources().getString(R.string.k_11),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }
                //***********************************************************************************
                //ฌ
                if( tinydb.getInt("data") == 11) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฌ Y =180
                        if ((230 <= touchY) && (touchY <=280)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("12",DatagetName,getResources().getString(R.string.k_12),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((280 <= touchY) && (touchY < 320)) {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            databaseThai.UpdateData("12",DatagetName,getResources().getString(R.string.k_12),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 330) {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("12",DatagetName,getResources().getString(R.string.k_12),"0");

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("12",DatagetName,getResources().getString(R.string.k_12),"0");
                    }
                }

                //*********************************************************************************
                //ญ
                if( tinydb.getInt("data") == 12) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ญ Y =500
                        if ((500 <= touchY) && (touchY <=550)) {
                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("13",DatagetName,getResources().getString(R.string.k_13),"1");

                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((550 <= touchY) && (touchY < 580)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("13",DatagetName,getResources().getString(R.string.k_13),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 650) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                            databaseThai.UpdateData("13",DatagetName,getResources().getString(R.string.k_13),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("13",DatagetName,getResources().getString(R.string.k_13),"0");

                        }
                    }

                }

                //*********************************************************************************
                //ฎ
                if( tinydb.getInt("data") == 13) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฎ Y =473
                        if ((473 <= touchY) && (touchY <=573)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("14",DatagetName,getResources().getString(R.string.k_14),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((523 <= touchY) && (touchY < 573)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("14",DatagetName,getResources().getString(R.string.k_14),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 623) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("14",DatagetName,getResources().getString(R.string.k_14),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("14",DatagetName,getResources().getString(R.string.k_14),"0");
                        }
                    }

                }

                //**********************************************************************************

                //ฏ
                if( tinydb.getInt("data") == 14) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฎ Y =474
                        if ((474 <= touchY) && (touchY <=574)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);

                            databaseThai.UpdateData("15",DatagetName,getResources().getString(R.string.k_15),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((524 <= touchY) && (touchY < 574)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("15",DatagetName,getResources().getString(R.string.k_15),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 624) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("15",DatagetName,getResources().getString(R.string.k_15),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("15",DatagetName,getResources().getString(R.string.k_15),"0");
                    }
                }

                //**********************************************************************************

                //ฐ
                if( tinydb.getInt("data") == 15) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฐ Y =490
                        if ((540 <= touchY) && (touchY <=590)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("16",DatagetName,getResources().getString(R.string.k_16),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((540 <= touchY) && (touchY < 470)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("16",DatagetName,getResources().getString(R.string.k_16),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 640) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                            databaseThai.UpdateData("16",DatagetName,getResources().getString(R.string.k_16),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("16",DatagetName,getResources().getString(R.string.k_16),"0");
                    }
                }
            //**************************************************************************************
               //ฑ
                if( tinydb.getInt("data") == 16) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฑ Y =497
                        if ((497 <= touchY) && (touchY <=557)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("17",DatagetName,getResources().getString(R.string.k_17),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((557 <= touchY) && (touchY < 477)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("17",DatagetName,getResources().getString(R.string.k_17),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 647) {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("17",DatagetName,getResources().getString(R.string.k_17),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("17",DatagetName,getResources().getString(R.string.k_17),"0");
                    }
                }

                //*********************************************************************************

                //ฒ
                if( tinydb.getInt("data") == 17) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        // Y =214
                        if ((214 <= touchY) && (touchY <=264)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("18",DatagetName,getResources().getString(R.string.k_18),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((264 <= touchY) && (touchY < 319)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("18",DatagetName,getResources().getString(R.string.k_18),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 364) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("18",DatagetName,getResources().getString(R.string.k_18),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("18",DatagetName,getResources().getString(R.string.k_18),"0");
                    }
                }
                //**********************************************************************************

                //ณ
                if( tinydb.getInt("data") == 18) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฌ Y =214
                        if ((214 <= touchY) && (touchY <=264)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("19",DatagetName,getResources().getString(R.string.k_19),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((264 <= touchY) && (touchY < 319)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("19",DatagetName,getResources().getString(R.string.k_19),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 364) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("19",DatagetName,getResources().getString(R.string.k_19),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }

                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("19",DatagetName,getResources().getString(R.string.k_19),"0");
                    }
                }

                //*********************************************************************************
                //ด
                if( tinydb.getInt("data") == 19) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ด Y =505
                        if ((505 <= touchY) && (touchY <=555)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("20",DatagetName,getResources().getString(R.string.k_20),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((555 <= touchY) && (touchY < 585)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("20",DatagetName,getResources().getString(R.string.k_20),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 645) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("20",DatagetName,getResources().getString(R.string.k_20),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("20",DatagetName,getResources().getString(R.string.k_20),"0");
                    }
                }

                //*********************************************************************************

                //ต
                if( tinydb.getInt("data") == 20) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ต Y =495
                        if ((500 <= touchY) && (touchY <=555)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("21",DatagetName,getResources().getString(R.string.k_21),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((555 <= touchY) && (touchY < 585)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("21",DatagetName,getResources().getString(R.string.k_21),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 645) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("21",DatagetName,getResources().getString(R.string.k_21),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }

                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("21",DatagetName,getResources().getString(R.string.k_21),"0");
                    }
                }
                //**********************************************************************************

                //ถ
                if( tinydb.getInt("data") == 21) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ถ Y =485
                        if ((485 <= touchY) && (touchY <=535)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("22",DatagetName,getResources().getString(R.string.k_22),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((535 <= touchY) && (touchY < 565)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("22",DatagetName,getResources().getString(R.string.k_22),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 635) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("22",DatagetName,getResources().getString(R.string.k_22),"0");

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("22",DatagetName,getResources().getString(R.string.k_22),"0");

                        }
                    }


                }

                //**********************************************************************************
                //ท
                if( tinydb.getInt("data") == 22) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ท Y =475
                        if ((475 <= touchY) && (touchY <=525)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("23",DatagetName,getResources().getString(R.string.k_23),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((525 <= touchY) && (touchY < 555)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("23",DatagetName,getResources().getString(R.string.k_23),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 625) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("23",DatagetName,getResources().getString(R.string.k_23),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("23",DatagetName,getResources().getString(R.string.k_23),"0");
                        }
                    }


                }
                //**********************************************************************************

                //ธ
                if( tinydb.getInt("data") == 23) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ธ Y =221
                        if ((221 <= touchY) && (touchY <=271)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("24",DatagetName,getResources().getString(R.string.k_24),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((271 <= touchY) && (touchY < 301)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("24",DatagetName,getResources().getString(R.string.k_24),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 371) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("24",DatagetName,getResources().getString(R.string.k_24),"0");

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)


                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("24",DatagetName,getResources().getString(R.string.k_24),"0");

                        }
                    }

                }
                //***********************************************************************************

                //น
                if( tinydb.getInt("data") == 24) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //น Y =200
                        if ((200 <= touchY) && (touchY <=250)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("25",DatagetName,getResources().getString(R.string.k_25),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((250 <= touchY) && (touchY < 280)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("25",DatagetName,getResources().getString(R.string.k_25),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 350) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("25",DatagetName,getResources().getString(R.string.k_25),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("25",DatagetName,getResources().getString(R.string.k_25),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************

                //บ
                if( tinydb.getInt("data") == 25) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //บ Y =214
                        if ((214 <= touchY) && (touchY <=264)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("26",DatagetName,getResources().getString(R.string.k_26),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((264 <= touchY) && (touchY < 284)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("26",DatagetName,getResources().getString(R.string.k_26),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 364) {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("26",DatagetName,getResources().getString(R.string.k_26),"0");

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("26",DatagetName,getResources().getString(R.string.k_26),"0");

                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //ป
                if( tinydb.getInt("data") == 26) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //บ Y =102
                        if ((102 <= touchY) && (touchY <=152)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("27",DatagetName,getResources().getString(R.string.k_27),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((152 <= touchY) && (touchY < 182)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("27",DatagetName,getResources().getString(R.string.k_27),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 252) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                            databaseThai.UpdateData("27",DatagetName,getResources().getString(R.string.k_27),"0");

                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }

                    else {
                        MainActivity1.button_ture.setVisibility(INVISIBLE);
                        MainActivity1.button_flase.setVisibility(VISIBLE);

                        databaseThai.UpdateData("27",DatagetName,getResources().getString(R.string.k_27),"0");

                    }
                }


                //**********************************************************************************


                //ผ
                if( tinydb.getInt("data") == 27) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ผ Y =226
                        if ((222 <= touchY) && (touchY <=272)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("28",DatagetName,getResources().getString(R.string.k_28),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((272 <= touchY) && (touchY < 317)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("28",DatagetName,getResources().getString(R.string.k_28),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 377) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("28",DatagetName,getResources().getString(R.string.k_28),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("28",DatagetName,getResources().getString(R.string.k_28),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //**********************************************************************************

                //ฝ
                if( tinydb.getInt("data") == 28) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฝ Y =105
                        if ((105 <= touchY) && (touchY <=155)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("29",DatagetName,getResources().getString(R.string.k_29),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((155 <= touchY) && (touchY < 185)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("29",DatagetName,getResources().getString(R.string.k_29),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 255) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("29",DatagetName,getResources().getString(R.string.k_29),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("29",DatagetName,getResources().getString(R.string.k_29),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //**********************************************************************************
                //พ
                if( tinydb.getInt("data") == 29) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //พ Y =241
                        if ((241 <= touchY) && (touchY <=291)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("30",DatagetName,getResources().getString(R.string.k_30),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((291 <= touchY) && (touchY < 321)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("30",DatagetName,getResources().getString(R.string.k_30),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 391) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("30",DatagetName,getResources().getString(R.string.k_30),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("30",DatagetName,getResources().getString(R.string.k_30),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //**********************************************************************************

                //ฟ
                if( tinydb.getInt("data") == 30) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฟ Y =111
                        if ((111 <= touchY) && (touchY <=161)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("31",DatagetName,getResources().getString(R.string.k_31),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((161 <= touchY) && (touchY < 302)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("31",DatagetName,getResources().getString(R.string.k_31),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 272) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("31",DatagetName,getResources().getString(R.string.k_31),"0");

                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("31",DatagetName,getResources().getString(R.string.k_31),"0");

                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************

                //ภ
                if( tinydb.getInt("data") == 31) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ภ Y =536
                        if ((536 <= touchY) && (touchY <=586)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("32",DatagetName,getResources().getString(R.string.k_32),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((586 <= touchY) && (touchY < 626)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("32",DatagetName,getResources().getString(R.string.k_32),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 686) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("32",DatagetName,getResources().getString(R.string.k_32),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("32",DatagetName,getResources().getString(R.string.k_32),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //********************************************************************************

                //ม
                if( tinydb.getInt("data") == 32) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ม Y =202
                        if ((202 <= touchY) && (touchY <=252)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("33",DatagetName,getResources().getString(R.string.k_33),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((252 <= touchY) && (touchY < 282)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("33",DatagetName,getResources().getString(R.string.k_33),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 352) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("33",DatagetName,getResources().getString(R.string.k_33),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("33",DatagetName,getResources().getString(R.string.k_33),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************

                //ย
                if( tinydb.getInt("data") == 33) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ย Y =214
                        if ((214<= touchY) && (touchY <=264)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("34",DatagetName,getResources().getString(R.string.k_34),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((264 <= touchY) && (touchY < 294)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("34",DatagetName,getResources().getString(R.string.k_34),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 364) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("34",DatagetName,getResources().getString(R.string.k_34),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("34",DatagetName,getResources().getString(R.string.k_34),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //***********************************************************************************

                //ร
                if( tinydb.getInt("data") == 34) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ร Y =226
                        if ((226<= touchY) && (touchY <=276)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("35",DatagetName,getResources().getString(R.string.k_35),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((276 <= touchY) && (touchY < 306)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("35",DatagetName,getResources().getString(R.string.k_35),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 376) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("35",DatagetName,getResources().getString(R.string.k_35),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("35",DatagetName,getResources().getString(R.string.k_35),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //**********************************************************************************

                //ล
                if( tinydb.getInt("data") == 35) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ล Y =272
                        if ((272<= touchY) && (touchY <=322)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("36",DatagetName,getResources().getString(R.string.k_36),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");


                        } else if ((322 <= touchY) && (touchY < 352)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("36",DatagetName,getResources().getString(R.string.k_36),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 422) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("36",DatagetName,getResources().getString(R.string.k_36),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("36",DatagetName,getResources().getString(R.string.k_36),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************

                //ว
                if( tinydb.getInt("data") == 36) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));
                    //789
                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ว Y =289
                        if ((289<= touchY) && (touchY <=339)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("37",DatagetName,getResources().getString(R.string.k_37),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((339 <= touchY) && (touchY < 369)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("37",DatagetName,getResources().getString(R.string.k_37),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 439) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("37",DatagetName,getResources().getString(R.string.k_37),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("37",DatagetName,getResources().getString(R.string.k_37),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //***********************************************************************************

                //ศ
                if( tinydb.getInt("data") == 37) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));
                    //1215
                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ศ Y =165
                        if ((155<= touchY) && (touchY <=170)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("38",DatagetName,getResources().getString(R.string.k_38),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((170 <= touchY) && (touchY < 200)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("38",DatagetName,getResources().getString(R.string.k_38),"0");

                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 315) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("38",DatagetName,getResources().getString(R.string.k_38),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("38",DatagetName,getResources().getString(R.string.k_38),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

                //ษ
                if( tinydb.getInt("data") == 38) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ษ Y =309
                        if ((309<= touchY) && (touchY <=359)) {
                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("39",DatagetName,getResources().getString(R.string.k_39),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((359 <= touchY) && (touchY < 389)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("39",DatagetName,getResources().getString(R.string.k_39),"0");

                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 469) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("39",DatagetName,getResources().getString(R.string.k_39),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("39",DatagetName,getResources().getString(R.string.k_39),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


             //***************************************************************************************
                //ส
                if( tinydb.getInt("data") == 39) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ส Y =166
                        if ((155<= touchY) && (touchY <=190)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("40",DatagetName,getResources().getString(R.string.k_40),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((190 <= touchY) && (touchY < 246)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("40",DatagetName,getResources().getString(R.string.k_40),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 316) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("40",DatagetName,getResources().getString(R.string.k_40),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("40",DatagetName,getResources().getString(R.string.k_40),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //********************************************************************************


                //ส
                if( tinydb.getInt("data") == 40) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ห Y =515
                        if ((515<= touchY) && (touchY <=565)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("41",DatagetName,getResources().getString(R.string.k_41),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((565 <= touchY) && (touchY < 595)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("41",DatagetName,getResources().getString(R.string.k_41),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 665) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("41",DatagetName,getResources().getString(R.string.k_41),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("41",DatagetName,getResources().getString(R.string.k_41),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************


                //ฬ
                if( tinydb.getInt("data") == 41) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฬ Y =154
                        if ((154<= touchY) && (touchY <=204)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("42",DatagetName,getResources().getString(R.string.k_42),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((204 <= touchY) && (touchY < 234)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("42",DatagetName,getResources().getString(R.string.k_42),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 304) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("42",DatagetName,getResources().getString(R.string.k_42),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("42",DatagetName,getResources().getString(R.string.k_42),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************


                //อ
                if( tinydb.getInt("data") == 42) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //อ Y =272
                        if ((272<= touchY) && (touchY <=352)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("43",DatagetName,getResources().getString(R.string.k_43),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((352 <= touchY) && (touchY < 382)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("43",DatagetName,getResources().getString(R.string.k_43),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 452) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("43",DatagetName,getResources().getString(R.string.k_43),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("43",DatagetName,getResources().getString(R.string.k_43),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }


                //*********************************************************************************

                //ฮ
                if( tinydb.getInt("data") == 43) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));
                    Log.e("touchXMOVE", String.valueOf(touchX));
                    Log.e("touchYMOVE", String.valueOf(touchY));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");
                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
                        //ฮ Y =158
                        if ((158<= touchY) && (touchY <=208)) {

                            MainActivity1.button_ture.setVisibility(VISIBLE);
                            MainActivity1.button_flase.setVisibility(INVISIBLE);
                            databaseThai.UpdateData("44",DatagetName,getResources().getString(R.string.k_44),"1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((208 <= touchY) && (touchY < 238)) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("44",DatagetName,getResources().getString(R.string.k_44),"0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 308) {

                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("44",DatagetName,getResources().getString(R.string.k_44),"0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                        }
                        else {
                            MainActivity1.button_ture.setVisibility(INVISIBLE);
                            MainActivity1.button_flase.setVisibility(VISIBLE);

                            databaseThai.UpdateData("44",DatagetName,getResources().getString(R.string.k_44),"0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }



                break;
            case MotionEvent.ACTION_UP:
                if (erase){
                    drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                }
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                drawPaint.setXfermode(null);
                break;
            default:
                return false;
        }

        // invalidate the view so that canvas is redrawn.
        invalidate();
        return true;
    }

    public void setColor(String newColor){
        // invalidate the view
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
        previousColor = paintColor;
    }

    public void setBrushSize(float newSize){
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize=pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }

    public void setNextImage(int nextImage){
        next_image=nextImage;
        setUpDrawing();

    }
    public float getNextImag(){
        return next_image;
    }
    public void setErase(boolean isErase){
        //set erase true or false
        erase = isErase;
        if(erase) {
            drawPaint.setColor(Color.WHITE);
            //drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        else {
            drawPaint.setColor(previousColor);
            drawPaint.setXfermode(null);
        }
    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
}
