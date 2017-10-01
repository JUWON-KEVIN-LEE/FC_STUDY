package com.immymemine.kevin.tetris;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by quf93 on 2017-09-28.
 */

public class Block {
    float x, y, width, height;
    Paint paint;

    // 블럭은 기본 크기와 좌표를 가지고 생성
    public Block(float x, float y, float width, float height) {
        paint = new Paint();
        paint.setColor(Color.GREEN);
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public void moveRight() {
        x = x + width;
    }
    public void moveLeft() {
        x = x - width;
    }
    public void moveUp() {
        y = y + height;
    }
    public void moveDown() {
        y = y - height;
    }
}
