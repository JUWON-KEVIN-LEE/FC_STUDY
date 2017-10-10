package com.immymemine.kevin.myandroidstudy.Output;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.immymemine.kevin.myandroidstudy.R;

/**
 * Created by quf93 on 2017-09-28.
 */

public class CustomViewDrawables extends View {
    private ShapeDrawable upperDrawable;
    private ShapeDrawable lowerDrawable;

    @TargetApi(Build.VERSION_CODES.M)
    public CustomViewDrawables(Context context) {
        super(context);
        // 윈도우 매니저를 이용해 뷰의 폭과 높이 확인
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE); // TODO WindowManager 에 대해 알아보자 ~
        Display display = manager.getDefaultDisplay();
        Point sizePoint = new Point(); // Point... 에 대해 알고가자
        display.getSize(sizePoint);
        int width = sizePoint.x;
        int height = sizePoint.y;

        int blackColor = ContextCompat.getColor(context, R.color.black);
        int whiteColor = ContextCompat.getColor(context, R.color.white);
        int redColor = ContextCompat.getColor(context, R.color.red);

        upperDrawable = new ShapeDrawable();
        RectShape rectangle = new RectShape();
        rectangle.resize(width, height*2/3);
        upperDrawable.setShape(rectangle);
        upperDrawable.setBounds(0, 0, width, height*2/3);
        // LinearGradient
        LinearGradient gradient = new LinearGradient(0, 0, 0, height*2/3, blackColor, whiteColor, Shader.TileMode.CLAMP);
        // Drawable 객체로 부터 Paint 를 가져옴...
        Paint paint = upperDrawable.getPaint();
        paint.setShader(gradient); // Paint 객체에 LinearGradient 객체를 shader 로 설정

        lowerDrawable = new ShapeDrawable();
        RectShape rectangle_lower = new RectShape();
        rectangle_lower.resize(width, height/3);

        lowerDrawable.setShape(rectangle_lower);
        lowerDrawable.setBounds(0,height*2/3, width, height);

        LinearGradient gradient_lower = new LinearGradient(0, 0, 0, height/3, whiteColor, redColor, Shader.TileMode.CLAMP);

        paint = lowerDrawable.getPaint();
        paint.setShader(gradient_lower);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        upperDrawable.draw(canvas);
        lowerDrawable.draw(canvas);
    }
}
