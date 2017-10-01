package com.immymemine.kevin.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by quf93 on 2017-09-28.
 */

public class Stage extends View {
    Context context;
    float width, height, unit_width, unit_height;

    public Stage(Context context, int width, int height, int unit_count) {
        super(context);
        this.width = width;
        this.height = height;
        unit_width = width / unit_count;
        unit_height = height / unit_count;
        this.context = context;

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
    }

    Block block = null;
    float x, y;
    public void setBlock(float x, float y, float width, float height) {
        block = new Block(x, y, width, height);
        this.x = x;
        this.y = y;
    }
    public void moveBlock(float x, float y) {
        block.x = x; block.y = y;
        this.x = x; this.y = y;
    }
    public void drawBlock(Canvas canvas) {
        canvas.drawCircle(block.x, block.y, unit_width, block.paint);
    }

    Paint paint;
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        drawBackGround(canvas);
        // 블럭을 그리는 코드..
        drawBlock(canvas);
    }
    private void drawBackGround(Canvas canvas) {
        // 배경 그리기..
        for (int i = 0; i <= width; i += unit_width) {
            for (int j = 0; j <= height; j += unit_height) {
                canvas.drawRect(i, j, i + unit_width, j + unit_height, paint);
            }
        }
    }
}
