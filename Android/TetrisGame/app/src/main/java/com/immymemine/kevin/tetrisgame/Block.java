package com.immymemine.kevin.tetrisgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by quf93 on 2017-09-29.
 */

public class Block {
    int x, y;
    float unit;
    Paint paint;

    int number = 0; // 형태를 결정
    int rotation = 0; // 회전에 따른 형태를 결정
    int current[][]; // 현재 Block number 와 rotation 값에 맞는 가져오는 배열

    int colors[] ={ // 색상
            Color.RED, Color.BLACK, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.BLUE, Color.GRAY
    };

    int blocks[][][][] = // 형태
            {
                    {
                            {
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0},
                                    {0, 1, 0, 0}
                            },
                            {
                                    {0, 0, 0, 0},
                                    {1, 1, 1, 1},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            }
                    },
                    {
                            {
                                    {0, 0, 2, 0},
                                    {0, 2, 2, 2},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 2, 0},
                                    {0, 0, 2, 2},
                                    {0, 0, 2, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 0, 0, 0},
                                    {0, 2, 2, 2},
                                    {0, 0, 2, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 0, 2, 0},
                                    {0, 2, 2, 0},
                                    {0, 0, 2, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 0, 0, 0},
                                    {0, 3, 3, 0},
                                    {0, 3, 3, 0},
                                    {0, 0, 0, 0}
                            }
                    },
                    {
                            {
                                    {0, 0, 4, 0},
                                    {0, 4, 4, 0},
                                    {0, 4, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 4, 4, 0},
                                    {0, 0, 4, 4},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 5, 0, 0},
                                    {0, 5, 5, 0},
                                    {0, 0, 5, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 5, 5},
                                    {0, 5, 5, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 6, 0, 0},
                                    {0, 6, 6, 6},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 6, 6, 0},
                                    {0, 6, 0, 0},
                                    {0, 6, 0, 0},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {6, 6, 6, 0},
                                    {0, 0, 6, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 6, 0},
                                    {0, 0, 6, 0},
                                    {0, 6, 6, 0},
                                    {0, 0, 0, 0},
                            }
                    },
                    {
                            {
                                    {0, 0, 0, 7},
                                    {0, 7, 7, 7},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 7, 0},
                                    {0, 0, 7, 0},
                                    {0, 0, 7, 7},
                                    {0, 0, 0, 0},
                            },
                            {
                                    {0, 7, 7, 7},
                                    {0, 7, 0, 0},
                                    {0, 0, 0, 0},
                                    {0, 0, 0, 0}
                            },
                            {
                                    {0, 0, 7, 7},
                                    {0, 0, 0, 7},
                                    {0, 0, 0, 7},
                                    {0, 0, 0, 0},
                            }
                    }
            };

    public Block(Property property) {
        x = property.x; // block 의 좌표값
        y = property.y;
        unit = property.unit; // block 의 unit
        paint = property.paint;
        Random random = new Random();
        number = random.nextInt(7); // random 하게 형태를 가져온다
        paint.setColor(colors[number]); // 그 형태에 따른 color 값을 세팅해준다.
    }

    public void onDraw(Canvas canvas) {
        current = blocks[number][rotation]; // 현재 block
//        paint.setColor(colors[number]);
        for(int v=0; v<current.length; v++) {
            for(int h=0; h<current[0].length; h++) {
                if(current[v][h] > 0) {
                    canvas.drawRect(
                            (parent.getX() + x + h) * unit,
                            (parent.getY() + y + v) * unit,
                            (parent.getX() + x + h + 1) * unit,
                            (parent.getY() + y + v + 1) * unit,
                            paint
                    );
                }
            }
        }
    }

//    public void up() {
//        y = y - unit;
//    }
//    public void down() {
//        y = y + unit;
//    }
//    public void right() {
//        x = x + unit;
//    }
//    public void left() {
//        x = x - unit;
//    }
    public void rotate() {
        rotation = (rotation + 1) % blocks[number].length;
    }
    public int[][] getRotatedBlock() {
        int newRotation = (rotation + 1) % blocks[number].length;
        return blocks[number][newRotation];
    }

    Parent parent;
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public interface Parent {
        int getX();
        int getY();
    }
}