package com.immymemine.kevin.pic_memo_orm;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.immymemine.kevin.pic_memo_orm.DAO.PicNoteDAO;
import com.immymemine.kevin.pic_memo_orm.Util.FileUtil;
import com.immymemine.kevin.pic_memo_orm.model.PicNote;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DrawActivity extends AppCompatActivity {
    Button postBtn;
    RadioGroup rg1, rg2;
    RadioButton black, yellow, cyan, magenta;
    SeekBar seekBar;
    TextView progressBar; EditText editTitle;
    FrameLayout stage; DrawView drawView;
    PicNoteDAO dao;
    SimpleDateFormat sdf;
    int prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        initView();
        initListener();
        init();
    }
    private void init() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dao = new PicNoteDAO(this);
        prog = 50;
    }

    private void initListener() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                prog = progress;
                progressBar.setText(progress + "");
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

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int id) {
                switch (id) {
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
                }
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = editTitle.getText().toString();
                if(filename.equals("")) {
                    Log.d("finish",filename);
                    finish();
                }
                PicNote note = setInfoToPicNote(filename);
                captureCanvas(note.getBitmap_path());
                dao.create(note);
                finish();
            }
        });
    }
    private void captureCanvas(String filename) {
        stage.destroyDrawingCache();
        stage.buildDrawingCache();
        Bitmap bitmap = stage.getDrawingCache();
        try {
            FileUtil.writeBitmap(this, filename, bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap.recycle();
    }
    private PicNote setInfoToPicNote(String filename) {
        PicNote note = new PicNote();
        note.setTitle(filename);
        note.setBitmap_path(filename + ".jpg");
        note.setPn_datetime(sdf.format(System.currentTimeMillis()));
        return note;
    }

    private void initView() {
        postBtn = (Button) findViewById(R.id.postBtn);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        black = (RadioButton) findViewById(R.id.black);
        yellow = (RadioButton) findViewById(R.id.yellow);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        cyan = (RadioButton) findViewById(R.id.cyan);
        magenta = (RadioButton) findViewById(R.id.magenta);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        progressBar = (TextView) findViewById(R.id.progress);
        stage = (FrameLayout) findViewById(R.id.stage);
        editTitle = (EditText)findViewById(R.id.editTextTitle);

        drawView = new DrawView(this);
        stage.addView(drawView);
    }
}
