package com.wordpress.priyankvex.paintapp.DrawingView;

import android.content.Context;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.wordpress.priyankvex.paintapp.Actvity.MainActivity2;
import com.wordpress.priyankvex.paintapp.R;
import com.wordpress.priyankvex.paintapp.Sqlite.DatabaseEnglish;
import com.wordpress.priyankvex.paintapp.TinyDB.TinyDB;

/**
 * Created by Priyank(@priyankvex) on 5/9/15.
 *
 * Class which provides the view on which drawing takes place.
 */
public class DrawingViewEN2 extends View{
    private int getheight;
    private int getWidth;

    private int getheight_image;
    private int getWidth_image;

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
    private DatabaseEnglish databaseEnglish ;
    private String DatagetName ;
    private int next_image;
    private int black_image;
    public DrawingViewEN2(Context context, AttributeSet attrs){
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

                databaseEnglish = new DatabaseEnglish(getContext());

                TinyDB tinydb = new TinyDB(getContext());
                tinydb.getInt("data");

                Log.e("Myname",tinydb.getString("Myname"));

                DatagetName = tinydb.getString("Myname") ;




                //**********************************************************************************
                //A
                if( tinydb.getInt("data") == 0) {
                    Log.e("data", String.valueOf(tinydb.getInt("data")));

                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
                    tinydb.getListString("MyPaint1");

                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));

                    if (touchX >= tinydb.getListInt("MyPaint1").get(0)) //1180
                    {
                        //A = 356
                        Log.e("touchmoveTo Ture ", String.valueOf(touchY)); // ก Y = 486

                        if ((406 <= touchY) && (touchY <= 456)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);

                            databaseEnglish.UpdateData("1",DatagetName,"A","1");

                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");


                        } else if ((406 <= touchY) && (touchY < 436)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("1",DatagetName,"A","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");


                        } else if (touchY > 506) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("1",DatagetName,"A","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("1",DatagetName,"A","0");
                        }

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

                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
                    {

                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));

                        //B = Y = 472

                        if ((522 <= touchY) && (touchY <= 572)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);

                            databaseEnglish.UpdateData("2",DatagetName,"ฺB","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((522 <= touchY) && (touchY < 552)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("2",DatagetName,"ฺB","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 622) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("2",DatagetName,"ฺB","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("2",DatagetName,"ฺB","0");
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
                        //C = Y = 190
                        if ((240 <= touchY) && (touchY <= 290)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);

                            databaseEnglish.UpdateData("3",DatagetName,"ฺC","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((240 <= touchY) && (touchY < 270)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("3",DatagetName,"ฺC","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 340) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("3",DatagetName,"ฺC","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("3",DatagetName,"ฺC","0");
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
                        //D Y =180
                        if ((230 <= touchY) && (touchY <= 280)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("4",DatagetName,"D","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((230 <= touchY) && (touchY < 280)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("4",DatagetName,"D","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 330) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("4",DatagetName,"D","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }

                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("4",DatagetName,"D","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //E Y =470
                        if ((520 <= touchY) && (touchY <= 570)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("5",DatagetName,"E","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((520 <= touchY) && (touchY < 550)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("5",DatagetName,"E","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 620) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("5",DatagetName,"E","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("5",DatagetName,"E","0");
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
                        if ((360 <= touchY) && (touchY <= 410)) {

                            //F Y =310
                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("6",DatagetName,"F","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((360 <= touchY) && (touchY < 410)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("6",DatagetName,"F","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 460) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("6",DatagetName,"F","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("6",DatagetName,"F","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //G Y =200
                        if ((250 <= touchY) && (touchY <= 300)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("7",DatagetName,"G","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((250 <= touchY) && (touchY < 280)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("7",DatagetName,"G","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 350) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("7",DatagetName,"G","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("7",DatagetName,"G","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //H = Y =460
                        if ((510 <= touchY) && (touchY <= 560)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("8",DatagetName,"H","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((510 <= touchY) && (touchY < 540)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("8",DatagetName,"H","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 610) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("8",DatagetName,"H","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("8",DatagetName,"H","0");
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
                        //ฉ Y =177
                        if ((227 <= touchY) && (touchY <=277)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("9",DatagetName,"I","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((227 <= touchY) && (touchY < 257)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"I","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 327) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"I","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"I","0");
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
                        if ((167 <= touchY) && (touchY <=217)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"J","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((167 <= touchY) && (touchY < 197)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"J","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 267) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"J","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("9",DatagetName,"J","0");
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
                        //J Y =360
                        if ((410 <= touchY) && (touchY <=460)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("11",DatagetName,"K","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((410 <= touchY) && (touchY < 440)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("11",DatagetName,"K","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 510) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("11",DatagetName,"K","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("11",DatagetName,"K","0");
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
                        //K=  Y =453
                        if ((503 <= touchY) && (touchY <=553)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("12",DatagetName,"L","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((503 <= touchY) && (touchY < 533)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("12",DatagetName,"L","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 403) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("12",DatagetName,"L","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("12",DatagetName,"L","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //L= Y =473
                        if ((523 <= touchY) && (touchY <=573)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("13",DatagetName,"M","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((523 <= touchY) && (touchY < 553)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("13",DatagetName,"M","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 623) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("13",DatagetName,"M","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("13",DatagetName,"M","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //M Y =460
                        if ((510 <= touchY) && (touchY <=560)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("14",DatagetName,"N","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((510 <= touchY) && (touchY < 540)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("14",DatagetName,"N","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 610) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("14",DatagetName,"N","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("14",DatagetName,"N","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //N =  Y = 180
                        if ((230 <= touchY) && (touchY <=280)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("15",DatagetName,"O","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((230 <= touchY) && (touchY < 260)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("15",DatagetName,"O","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 330) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("15",DatagetName,"O","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("15",DatagetName,"O","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //o Y =170
                        if ((220 <= touchY) && (touchY <=270)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");
                            databaseEnglish.UpdateData("16",DatagetName,"P","1");

                        } else if ((220 <= touchY) && (touchY < 250)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("16",DatagetName,"P","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 320) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("16",DatagetName,"P","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("16",DatagetName,"P","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //ฑ Y =170
                        if ((220 <= touchY) && (touchY <=270)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("17",DatagetName,"Q","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((220 <= touchY) && (touchY < 250)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("17",DatagetName,"Q","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 320) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("17",DatagetName,"Q","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("17",DatagetName,"Q","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //Q Y =472
                        if ((522 <= touchY) && (touchY <=572)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("18",DatagetName,"R","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((522 <= touchY) && (touchY < 552)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("18",DatagetName,"R","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 622) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("18",DatagetName,"R","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("18",DatagetName,"R","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //R Y =452
                        if ((502 <= touchY) && (touchY <=552)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("19",DatagetName,"S","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((502 <= touchY) && (touchY < 532)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("19",DatagetName,"S","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 602) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("19",DatagetName,"S","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("19",DatagetName,"S","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //S= Y =406
                        if ((456 <= touchY) && (touchY <=506)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("20",DatagetName,"T","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((456 <= touchY) && (touchY < 486)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("20",DatagetName,"T","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 556) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("20",DatagetName,"T","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("20",DatagetName,"T","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //T Y =484
                        if ((534 <= touchY) && (touchY <=584)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("21",DatagetName,"U","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((534 <= touchY) && (touchY < 564)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("21",DatagetName,"U","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 634) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("21",DatagetName,"U","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("21",DatagetName,"U","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }
                //**********************************************************************************

                //ต
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
                        //U Y =200
                        if ((250 <= touchY) && (touchY <= 300)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);

                            databaseEnglish.UpdateData("22",DatagetName,"V","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((250 <= touchY) && (touchY < 280)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("22",DatagetName,"V","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 350) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("22",DatagetName,"V","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("22",DatagetName,"V","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //V Y =213
                        if ((263 <= touchY) && (touchY <=313)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("23",DatagetName,"W","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((263 <= touchY) && (touchY < 293)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("23",DatagetName,"W","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 363) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("23",DatagetName,"W","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("23",DatagetName,"W","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
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
                        //W= Y =230
                        if ((280 <= touchY) && (touchY <=330)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("24",DatagetName,"X","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((280 <= touchY) && (touchY < 310)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
                            databaseEnglish.UpdateData("24",DatagetName,"X","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 380) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("24",DatagetName,"X","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("24",DatagetName,"X","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

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
                        //X Y =500
                        if ((550 <= touchY) && (touchY <=600)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("25",DatagetName,"Y","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((550 <= touchY) && (touchY < 580)) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("25",DatagetName,"Y","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 650) {

                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("25",DatagetName,"Y","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
                        }
                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);


                            databaseEnglish.UpdateData("25",DatagetName,"Y","0");
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
                        //Y= Y =453
                        if ((503 <= touchY) && (touchY <=553)) {

                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
                            databaseEnglish.UpdateData("26",DatagetName,"Z","1");
                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");

                        } else if ((503 <= touchY) && (touchY < 533)) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("26",DatagetName,"Z","0");
                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
                        } else if (touchY > 603) {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("26",DatagetName,"Z","0");
                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");

                        }

                        else {
                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);

                            databaseEnglish.UpdateData("26",DatagetName,"Z","0");
                        }
                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
                    }
                }

//                if( tinydb.getInt("data") == 26) {
//                    Log.e("data", String.valueOf(tinydb.getInt("data")));
//                    Log.e("touchXMOVE", String.valueOf(touchX));
//                    Log.e("touchYMOVE", String.valueOf(touchY));
//
//                    Log.e("touchdrawPath moveTo", String.valueOf(touchX) + ":" + String.valueOf(touchY));
//                    tinydb.getListString("MyPaint1");
//                    Log.e("datacheck", String.valueOf(tinydb.getListInt("MyPaint1").get(1)));
//
//                    if (touchX >= tinydb.getListInt("MyPaint1").get(1)) //1180
//                    {
//                        Log.e("touchmoveTo Ture ", String.valueOf(touchY));
//                        //Z= Y =467
//                        if ((517 <= touchY) && (touchY <=567)) {
//
//                            MainActivity2.button_ture_EN.setVisibility(VISIBLE);
//                            MainActivity2.button_flase_EN.setVisibility(INVISIBLE);
//                            Log.e("touchmoveTo Ture2 ", "touchmoveTo Ture22");
//
//                        } else if ((517 <= touchY) && (touchY < 567)) {
//
//                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
//                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
//                            Log.e("touchmoveTo Flast 3 ", "touchX moveTo Flast 3 ");
//                        } else if (touchY > 617) {
//                            MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
//                            MainActivity2.button_flase_EN.setVisibility(VISIBLE);
//
//                            Log.e("touchmoveTo Flast 4 ", "touchX moveTo Flast 4 ");
//                        }
//                        //  drawingView1listener.onUpdateEngineNumberComplte(); // event object :)
//                    }
//                    else {
//                        MainActivity2.button_ture_EN.setVisibility(INVISIBLE);
//                        MainActivity2.button_flase_EN.setVisibility(VISIBLE);
//                    }
//                }


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
