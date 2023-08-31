package com.example.tvslauncher;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        Clip the canvas
//        canvas.clipPath(mClippingPath);
//        canvas.drawBitmap(mBitmap, mPivotX, mPivotY, null);
        int[] mColors = new int[] { Color.RED, Color.RED, Color.YELLOW, Color.GREEN };

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mClippingPath = new Path();
        int radius = 160;
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors
//        paint.setColor(Color.parseColor("#CD5C5C"));
        paint.setShader(new LinearGradient(0f, 0f, getMeasuredWidth(), 0f, mColors, null, Shader.TileMode.CLAMP));
        canvas.drawCircle(mPivotX, mPivotY, radius, paint);
        canvas.clipPath(mClippingPath);

    }

    private float getScreenGridUnit() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels / 90;
    }

}
