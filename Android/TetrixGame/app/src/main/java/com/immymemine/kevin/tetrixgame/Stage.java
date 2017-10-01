package com.immymemine.kevin.tetrixgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

/**
 * Created by quf93 on 2017-09-28.
 */

public class Stage extends View {
    Context context;
    Paint paint;

    public Stage(Context context) {
        super(context);

        paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawStage(canvas);
        drawLongBlock(canvas);
    }

    private void drawStage(Canvas canvas) {
        for(int i=0; i<500; i+= 50) {
            for(int j=0; j<800; j+= 50) {
                canvas.drawRect(i, j, i+50, j+50, paint);
                Rect rect = new Rect();
            }
        }

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        for(int i=0; i<500; i+= 50) {
            for(int j=0; j<800; j+= 50) {
                canvas.drawRect(i, j, i+50, j+50, paint);
            }
        }
    }
    BlockList data;
    public void setBlockList(BlockList data) {
        this.data = data;
    }
    // drawBlock(getRandomPosition());
    private int getRandomPosition() {
        Random random = new Random();
        return random.nextInt(7);
    }
    // position 값을 랜덤하게...
    Block block;
    public void drawBlock(Canvas canvas) {
        int position = getRandomPosition();
        block = data.blockList.get(position);
        int[][][] shape = block.shape;
        switch (shape.length) {
            case 1:
                // shape[0][0][0] [0][0][1] [0][1][0] [0][1][1]
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void drawLongBlock(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        for(int i= -150; i<=0; i+=50)
            canvas.drawRect(250,i,300,i+50, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        for(int i= -150; i<=0; i+=50)
            canvas.drawRect(250,i,300,i+50, paint);
    }
}
