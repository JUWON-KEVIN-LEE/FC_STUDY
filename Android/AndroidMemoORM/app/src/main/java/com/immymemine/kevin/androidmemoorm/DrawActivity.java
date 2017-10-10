package com.immymemine.kevin.androidmemoorm;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.immymemine.kevin.androidmemoorm.DAO.PicNote_DAO;
import com.immymemine.kevin.androidmemoorm.FileUtil.FileUtil;
import com.immymemine.kevin.androidmemoorm.model.PicNote;

import java.io.IOException;

/**
 * Created by quf93 on 2017-09-18.
 */

public class DrawActivity extends AppCompatActivity {
    FrameLayout stage; DrawView drawView;
    RadioGroup radioGroup; SeekBar seekBar; TextView seekBarResultView; Button saveBtn;
    int prog;
    PicNote_DAO dao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        initView();
        initListener();
        init();
    }
    private void init() {
        dao = new PicNote_DAO(this);
    }
    private void initView() {
        // view 선언
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        stage = (FrameLayout)findViewById(R.id.stage);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarResultView = (TextView) findViewById(R.id.seekBarResultView);
        saveBtn = (Button)findViewById(R.id.saveBtn);

        prog = 50;
        drawView = new DrawView(this);
        drawView.init();
        // layout 에 customView 를 그린다
        stage.addView(drawView);
    }
    private void initListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                seekBarResultView.setText(progress + "");
                int color = drawView.pathTool.getColor();
                drawView.makeTool();
                drawView.setWidth(progress);
                drawView.setColor(color);
                drawView.sendToolToCP();
                drawView.addTool();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int id) {
                switch (id) {
                    case R.id.cyan:
                        drawView.makeTool();
                        drawView.setWidth(prog);
                        drawView.setColor(Color.CYAN);
                        drawView.sendToolToCP();
                        drawView.addTool();
                        break;
                    case R.id.magenta:
                        drawView.makeTool();
                        drawView.setWidth(prog);
                        drawView.setColor(Color.MAGENTA);
                        drawView.sendToolToCP();
                        drawView.addTool();
                        break;
                    case R.id.yellow:
                        drawView.makeTool();
                        drawView.setWidth(prog);
                        drawView.setColor(Color.YELLOW);
                        drawView.sendToolToCP();
                        drawView.addTool();
                        break;
                    case R.id.black:
                        drawView.makeTool();
                        drawView.setWidth(prog);
                        drawView.setColor(Color.BLACK);
                        drawView.sendToolToCP();
                        drawView.addTool();
                        break;
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureCanvas(view);
            }
        });
    }
    public void captureCanvas(View view) {
        // 드로잉 캐시를 지우고
        stage.destroyDrawingCache();
        // 캐시를 다시 만든다
        stage.buildDrawingCache();
        // 레이아웃에 그려진 내용을 bitmap 형태로 가져온다
        Bitmap bitmap = stage.getDrawingCache();

        String filename = "temp.jpg";

        try {
            FileUtil.write(this, filename, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PicNote picNote = new PicNote();
        picNote.setBitmap_path(filename);
        picNote.setTitle(filename);
        picNote.setDatetime(System.currentTimeMillis());
        dao.create(picNote);

        bitmap.recycle();

        finish();
    }
}
