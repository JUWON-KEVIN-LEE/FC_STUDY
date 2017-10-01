package com.immymemine.kevin.tetrisgame;

import android.graphics.Canvas;

/**
 * Created by quf93 on 2017-09-29.
 */

public class Preview implements Block.Parent {
    int x, y;
    float unit;
    int columns, rows;

    // 현재 프리뷰에 있는 블럭
    Block block;

    public Preview(int x, int y, int columns, int rows, float unit) {
        this.x = x; this.y = y; this.columns = columns; this.rows = rows; this.unit = unit;
    }


    public void addBlock(Block block) {
        block.setParent(this);
        this.block = block;
    }
    public Block getBlockFromPreview() {
        return block;
    }
    public void onDraw(Canvas canvas) {
        // 자기 자신 그리기 ; preview 를 보여줄 프레임

        // 가지고 있는 block 그리기
        block.onDraw(canvas);
    }

    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
}
