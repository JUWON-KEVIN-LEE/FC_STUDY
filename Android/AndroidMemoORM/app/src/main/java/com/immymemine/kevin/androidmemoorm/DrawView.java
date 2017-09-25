package com.immymemine.kevin.androidmemoorm;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-09-18.
 */

public class DrawView extends View {
    // 그림이 그려지는 좌표
//    ArrayList<Float> xs = new ArrayList<>();
//    ArrayList<Float> ys = new ArrayList<>();

    Path currentPath;
    Paint paint;
    PathTool pathTool;
    List<PathTool> paths = new ArrayList<>();

    public DrawView(Context context) {
        super(context);
        paint = new Paint();
        currentPath = new Path();
    }
    public void init() {
        pathTool = new PathTool();
        pathTool.setColor(Color.BLACK);
        pathTool.setStrokeWidth(5f);
        currentPath = pathTool;
        paths.add(pathTool);
        paint.setStyle(Paint.Style.STROKE);
    }
    public void makeTool() {
        pathTool = new PathTool();
    }
    public void addTool() {
        paths.add(pathTool);
    }
    public void setWidth(int progress) {
        float width = (float)progress/10;
        pathTool.setStrokeWidth(width);
    }
    public void setColor(int color) {
        pathTool.setColor(color);
    }
    public void sendToolToCP() {
        currentPath = pathTool;
    }
    // 화면을 그려주는 onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        for(PathTool pathTool : paths) {
            paint.setColor(pathTool.getColor());
            paint.setStrokeWidth(pathTool.getWidth());
            canvas.drawPath(pathTool, paint);
        }

        //화면에 터치가 되면... 연속해서 그림을 그려준다
//        if(xs.size()>0){
//            for(int i=0; i<xs.size(); i++) {
//                canvas.drawCircle(xs.get(i), ys.get(i), r, paint);
//            }
//        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //터치된 좌표로 세팅
//        xs.add(event.getX());
//        ys.add(event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                // nothing to do
                break;
        }

        // onDraw 를 호출하는 메서드를 호출
        invalidate();
        // return 이 false 일 경우에는 touch 이벤트를 연속해서 발생시키지 않는다
        // 즉, drag 를 하면 onTouchEvent가 재 호출되지 않는다
        return true;
    }
}

class PathTool extends Path {
    private int color;
    private float width;

    public void setColor(int color){
        this.color = color;
    }
    public int getColor(){
        return this.color;
    }
    public void setStrokeWidth(float width) {
        this.width = width;
    }
    public float getWidth() {
        return width;
    }
}
