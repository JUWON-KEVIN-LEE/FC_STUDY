package com.immymemine.kevin.tetrisgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *  게임 전체를 그려주는 Class
 */

public class Stage implements Block.Parent{
    // 크기 단위
    float unit;
    // 좌표
    int x, y;
    // Column, Row
    int columns, rows;

    Block block;

    int[][] map = {
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,0,0,0,0,0,0,0,0,0,0,9},
            {9,9,9,9,9,9,9,9,9,9,9,9},
    };

    public Stage(int x, int y, int columns, int rows, float unit) {
        this.x = x; this.y = y; this.columns = columns; this.rows = rows; this.unit = unit;
        paint = new Paint();
        paint.setColor(Color.LTGRAY);
        setTextPaint();
    }

    public void addBlock(Block block) {
        this.block = block;
        block.x = 4;
        block.y = -3;
        block.setParent(this);
    }
    Paint paint;
    private Paint fitColor(int colorNumber) {
        if(colorNumber == 9)
            paint.setColor(Color.LTGRAY);
        else
            paint.setColor(block.colors[colorNumber-1]);
        return paint;
    }

    Paint textPaint = new Paint();
    private void setTextPaint() {
        textPaint.setTextSize(100);
        textPaint.setColor(Color.BLACK);
    }
    public void onDraw(Canvas canvas, boolean checkLose) {
        // 틀 그리기
        if(!checkLose) {
            for (int v = 0; v < map.length; v++) {
                for (int h = 0; h < map[0].length; h++) {
                    if (map[v][h] > 0) {
                        canvas.drawRect(
                                (x + h) * unit,
                                (y + v) * unit,
                                (x + h + 1) * unit,
                                (y + v + 1) * unit,
                                //paint
                                fitColor(map[v][h])
                        );
                    }
                }
            }
            // 블럭 그리기
            block.onDraw(canvas);
            // 점수 그리기
            canvas.drawText(String.valueOf(score), (x+5)*unit, (y+8)*unit, textPaint);
        } else {
            for (int v = 0; v < map.length; v++) {
                for (int h = 0; h < map[0].length; h++) {
                    if (map[v][h] > 0) {
                        canvas.drawRect(
                                (x + h) * unit,
                                (y + v) * unit,
                                (x + h + 1) * unit,
                                (y + v + 1) * unit,
                                paint
                                //fitColor(map[v][h])
                        );
                    }
                }
            }
            // 블럭 그리기
            block.paint.setColor(Color.LTGRAY);
            block.onDraw(canvas);
            // 점수 그리기
            canvas.drawText(String.valueOf(score), (x+5)*unit, (y+8)*unit, textPaint);
        }

    }
    public void right() {
        if(!checkCollision("right"))
            block.x += 1;
    }

    public void left() {
        if(!checkCollision("left"))
            block.x -= 1;
    }

    public void saveBlockToStage() {
        for(int v=0; v<4; v++) {
            for(int h=0; h<4; h++) {
                if(block.current[v][h] > 0)
                    try {
                        map[block.y + v][block.x + h] = block.current[v][h];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw e;
                    }
            }
        }
    }

    public boolean down() throws ArrayIndexOutOfBoundsException {
        if(!checkCollision("down")) {
            block.y += 1;
            return false;
        }
        else {
            saveBlockToStage();
            deleteCompletedLine();
            return true;
        }
    }

    public void up() {
        if(!checkCollision("up"))
            block.rotate();
    }
    public boolean checkCollision(String direction) {
        boolean result = false;
        int[][] checkBlocks = block.current;
        if("up".equals(direction)) {
            checkBlocks = block.getRotatedBlock();
        }

        for(int v = 0; v < checkBlocks.length; v++) {
            for(int h = 0; h < checkBlocks[0].length; h++) {
                int valueInBlockArray = checkBlocks[v][h];

                int mapY = block.y+v;
                int mapX = block.x+h;

                if("right".equals(direction)) {
                    mapX += 1;
                }
                if("left".equals(direction)) {
                    mapX -= 1;
                }
                if("down".equals(direction)) {
                    mapY += 1;
                }

                if(mapX >= map[0].length || mapX < 0 ||
                        mapY >= map.length || mapY < 0 )
                    continue;

                int valueInMapArray = map[mapY][mapX];

                if(valueInBlockArray > 0 && valueInMapArray>0) {
                    return true;
                }
            }
        }

        return result;
    }
    int[] clearLine = {9,0,0,0,0,0,0,0,0,0,0,9};
    int score = 0;
    public void deleteCompletedLine() {
        int count = 0;
        for(int v=0; v<map.length-1; v++) {
            int[] temp = map[v];
            for(int item : temp) {
                if (item > 0 && item < 9)
                    count++;
            }
            if (count == 10) {
                for(int i = v; i > 0; i--) {
                    map[i] = map[i-1];
                }
                map[0] = clearLine;
                score += 1;
            }
            count = 0;
        }
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