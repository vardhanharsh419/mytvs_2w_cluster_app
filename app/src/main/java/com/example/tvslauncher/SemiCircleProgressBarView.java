package com.example.tvslauncher;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.shapes.ArcShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.RequiresApi;

public class SemiCircleProgressBarView extends View {

    private Path mClippingPath;
    private Context mContext;
    private Bitmap mBitmap;
    private float mPivotX;
    private float mPivotY;

    private Paint paint;

    public SemiCircleProgressBarView(Context context) {
        super(context);
        mContext = context;
//        initilizeImage();
    }

    public SemiCircleProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
//        initilizeImage();
    }

    private void initilizeImage() {

        try {

            mClippingPath = new Path();

            //Top left coordinates of image. Give appropriate values depending on the position you wnat image to be placed
            mPivotX = getScreenGridUnit();
            mPivotY = 0;

            //Adjust the image size to support different screen sizes
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.circle_color);
            int imageWidth = (int) (getScreenGridUnit() * 12);
            int imageHeight = (int) (getScreenGridUnit() * 12);
            mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void setClipping(float progress) {

        mClippingPath = new Path();
        //Convert the progress in range of 0 to 100 to angle in range of 0 180. Easy math.
        float angle = (progress * 180) / 100;
        mClippingPath.reset();
        //Define a rectangle containing the image
        RectF oval = new RectF(mPivotX, mPivotY, mPivotX + mBitmap.getWidth(), mPivotY + mBitmap.getHeight());
        //Move the current position to center of rect
        mClippingPath.moveTo(oval.centerX(), oval.centerY());
        //Draw an arc from center to given angle
        mClippingPath.addArc(oval, 355, angle);
        //Draw a line from end of arc to center
        mClippingPath.lineTo(oval.centerX(), oval.centerY());
        //Redraw the canvas
        invalidate();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Clip the canvas
//        canvas.clipPath(mClippingPath);
//        canvas.drawBitmap(mBitmap, mPivotX, mPivotY, null);
        int[] mColors = new int[] { Color.RED, Color.RED, Color.RED, Color.YELLOW, Color.GREEN, Color.GREEN };
//
//        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mClippingPath = new Path();
//        int radius = 500;
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(20f);
//        // Use Color.parseColor to define HTML colors
////        paint.setColor(Color.parseColor("#00FFFFFF"));
////        paint.setColor(Color.TRANSPARENT);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
//        paint.setAntiAlias(true);
//        paint.setShader(new LinearGradient(0f, 0f, getMeasuredWidth(), 0f, mColors, null, Shader.TileMode.CLAMP));
//
//        canvas.drawPaint(paint);
//
//
//
//        float angle = (100 * 360) / 100;
//        mClippingPath.reset();
//        //Define a rectangle containing the image
        RectF oval = new RectF(50,20,100,80);
//        //Move the current position to center of rect
//        mClippingPath.moveTo(oval.centerX(), oval.centerY());
//        //Draw an arc from center to given angle
//        mClippingPath.addArc(oval, 0, angle);
//        //Draw a line from end of arc to center
//        mClippingPath.lineTo(oval.centerX(), oval.centerY());
//        //Redraw the canvas
//        invalidate();
//
//        canvas.clipPath(mClippingPath);
//        canvas.drawColor(Color.TRANSPARENT);
//        canvas.drawCircle(mPivotX, mPivotY, radius, paint);

//        Point center = new Point(canvas.getWidth()/2, canvas.getHeight()/2);
//        int inner_radius = 490;
//        int outer_radius = 500;
//        int arc_sweep = 90;
//        int arc_ofset = -20;
//
//        RectF outer_rect = new RectF(center.x-outer_radius, center.y-outer_radius, center.x+outer_radius, center.y+outer_radius);
//        RectF inner_rect = new RectF(center.x-inner_radius, center.y-inner_radius, center.x+inner_radius, center.y+inner_radius);
//
//        Path path = new Path();
//        path.arcTo(outer_rect, arc_ofset, arc_sweep);
//        path.arcTo(inner_rect, arc_ofset + arc_sweep, -arc_sweep);
//        path.close();
//
//        Paint fill = new Paint();
//        fill.setStrokeWidth(200f);
//        fill.setShader(new LinearGradient(0f, 0f, getMeasuredWidth(), 0f, mColors, null, Shader.TileMode.CLAMP));
//        canvas.drawPath(path, fill);
//
//        Paint border = new Paint();
//        border.setStyle(Paint.Style.STROKE);
//        canvas.drawPath(path, border);

        Bitmap bm;
        Canvas cv;
        Paint eraser;

        int w = getWidth();
        int h = getHeight();
        int radius = w > h ? h / 2 : w / 2;

        bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cv = new Canvas(bm);

        eraser = new Paint();
//        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setAntiAlias(true);


        eraser.setShader(new LinearGradient(0f, 0f, getMeasuredWidth(), 0f, mColors, null, Shader.TileMode.CLAMP));
        canvas.drawColor(Color.argb(0, 0, 0, 0));
        canvas.drawArc(oval, 75f, 0.7f*(-100f), false, eraser);

//        canvas.drawArc(100f, 100f, 100f, 100f, 0f,0.7f * (-100f), true, eraser);
//        cv.drawCircle(w / 2, h / 2, radius, eraser);
        canvas.drawBitmap(bm, 0, 0, null);
        super.onDraw(canvas);



    }

    private float getScreenGridUnit() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels / 90;
    }

}
