package com.immymemine.kevin.tetrisgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;

/**
 * Created by quf93 on 2017-09-29.
 */

public class Controller extends View {
    Context context;
    // 게임 세팅값
    Setting setting;
    // 게임판 < Controller 에서 그려준다
    Stage stage;
    // 미리보기 < Controller 에서 그려준다
    Preview preview;
    Button resetBtn;
    public Controller(Context context, Setting setting) {
        super(context);
        this.context = context;
        this.setting = setting;
        // TODO
        stage = new Stage(1, 0, 12, 16, setting.unit);
        preview = new Preview(14, 1, 4, 4, setting.unit);
        // preview 에 block 넣고.. stage 로 넘기고 다시 preview 에 block 만들어 넣기..
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        preview.onDraw(canvas);
        stage.onDraw(canvas, checkLose);
    }

    // 0. 화면을 최초에 그리기[addView] 전에 기본세팅
    public void init() {
        addBlockToPreview();
        addBlockToStageFromPreview();
        addBlockToPreview();
    }

    // 1. 블럭을 생성하는 함수
    public Block newBlock() {
        Property property = new Property();
        property.x = 0; //
        property.y = 0;
        property.unit = setting.unit;
        property.paint = new Paint();

        return new Block(property);
    }

    // 2. 블럭을 preview 에 담는 함수
    public void addBlockToPreview() {
        Block block = newBlock();
        preview.addBlock(block);
    }

    // 3. 블럭을 preview 에서 stage 로 넘기는 함수
    public void addBlockToStageFromPreview() {
        stage.addBlock(preview.getBlockFromPreview());
    }

    public void right() { // Block... 명령?
        stage.right();
        invalidate();
    }

    public void left() {
        stage.left();
        invalidate();
    }

    public void down() {
        boolean check = true;

        try {
            check = stage.down();
            if(check) {
                addBlockToStageFromPreview();
                addBlockToPreview();
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            lose();
        }

        postInvalidate();
    }

    public void up() {
        stage.up();
        invalidate();
    }

    private static boolean RUNNING = true;
    public void runGame() {
        RUNNING = true;
        new Thread() {
            public void run() {
                while(RUNNING) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    down();
                }
            }
        }.start();
    }
    boolean checkLose = false;

    public boolean lose() {
        stopGame();
        switchResetButtonVisibility();
        checkLose = true;
        return checkLose;
        // 화면을 회색으로 ... ok
        // 대화 상자 띄워서 다시하겠습니까?
        // 다시하기를 누르면... stage 초기화
        // 아니오(게임 종료) 하면 앱을 종료
    }

    private void switchResetButtonVisibility() {
        resetBtn = (Button) findViewById(R.id.resetBtn);
        if(resetBtn.getVisibility() == INVISIBLE)
            resetBtn.setVisibility(VISIBLE);
        else
            resetBtn.setVisibility(INVISIBLE);
    }

    public void reset() {
        checkLose = false;
        this.stage = new Stage(1, 0, 12, 16, setting.unit);
        // stage.reset(); // 화면 초기화
        init();
        invalidate();
        runGame();
    }

    public void stopGame() {
        RUNNING = false;
    }
}