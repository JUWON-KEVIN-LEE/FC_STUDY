package com.immymemine.kevin.tetrixgame;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quf93 on 2017-09-28.
 */

public class Block {
    float x;
    float y;
    int[][][] shape;
    int color;

    public Block(int[][][] shape, int color, int row) {
        this.x = 250;
        this.y = -50 * (row-1);
        // 중간에 위치하도록..
        this.shape = shape;
        this.color = color;
    }
    // 도형 갯수만큼의 range 값들에서 random 하게 가져와서
    // switch 문으로 다음에 나올 도형을 결정한다.
}

class BlockList {
    // 네모 블럭
    private final int[][][] block_one = {{ {1,1},
            {1,1} }};
    // 일자 블럭
    private final int[][][] block_two = {{{0,0,0,0},
            {2,2,2,2},
            {0,0,0,0},
            {0,0,0,0}}, {{0,0,2,0},
            {0,0,2,0},
            {0,0,2,0},
            {0,0,2,0}}};
    // ㄱ 블럭
    private final int[][][] block_three = {{{3,3,3},
            {0,0,3},
            {0,0,0}}, {{0,0,3},
            {0,0,3},
            {0,3,3}}, {{0,0,0},
            {3,0,0},
            {3,3,3}}, {{3,3,0},
            {3,0,0},
            {3,0,0}}};
    // ㄴ 블럭
    private final int[][][] block_four = {{{4,0,0},
            {4,4,4},
            {0,0,0}}, {{4,4,0},
            {4,0,0},
            {4,0,0}}, {{4,4,4},
            {0,0,4},
            {0,0,0}}, {{0,0,4},
            {0,0,4},
            {0,4,4}}};
    // ㅗ 블럭
    private final int[][][] block_five = {{{0,5,0},
            {5,5,5},
            {0,0,0}}, {{5,0,0},
            {5,5,0},
            {5,0,0}}, {{5,5,5},
            {0,5,0},
            {0,0,0}}, {{0,0,5},
            {0,5,5},
            {0,0,5}}};
    private final int[][][] block_six = {{{6,6,0},
            {0,6,6},
            {0,0,0}}, {{0,0,6},
            {0,6,6},
            {0,6,0}}, {{0,0,0},
            {6,6,0},
            {0,6,6}}, {{0,6,0},
            {6,6,0},
            {6,0,0}}};

    private final int[][][] block_seven = {{{0,7,7},
            {7,7,0},
            {0,0,0}}, {{0,7,0},
            {0,7,7},
            {0,0,7}}, {{0,0,0},
            {0,7,7},
            {7,7,0}}, {{7,0,0},
            {7,7,0},
            {0,7,0}}};
    Block block1, block2, block3, block4, block5, block6, block7;
    List<Block> blockList;

    public BlockList() {
        blockList = new ArrayList<>();
        block1 = new Block(block_one, Color.CYAN, 2);
        block2 = new Block(block_two, Color.GREEN, 4);
        block3 = new Block(block_three, Color.RED, 3);
        block4 = new Block(block_four, Color.BLUE, 3);
        block5 = new Block(block_five, Color.MAGENTA, 3);
        block6 = new Block(block_six, Color.YELLOW, 3);
        block7 = new Block(block_seven, Color.BLACK, 3);

        blockList.add(block1);
        blockList.add(block2);
        blockList.add(block3);
        blockList.add(block4);
        blockList.add(block5);
        blockList.add(block6);
        blockList.add(block7);
    }
}
