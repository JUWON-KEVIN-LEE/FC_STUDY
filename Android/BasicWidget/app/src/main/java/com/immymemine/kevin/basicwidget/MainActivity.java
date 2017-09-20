package com.immymemine.kevin.basicwidget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TextView textView, textSeekBarResult;
    ToggleButton toggleButton; Switch Wswitch;
    CheckBox checkBox1, checkBox2, checkBox3;
    RadioGroup radioGroup;
    RadioButton radioRed, radioGreen, radioBlue, spinner;
    ProgressBar progressBar; SeekBar seekBar; RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        toggleButton.setOnCheckedChangeListener(checkListener);
        Wswitch.setOnCheckedChangeListener(checkListener);
        radioGroup.setOnCheckedChangeListener(radioListener);

        checkBox1.setOnCheckedChangeListener(checkBoxListener);
        checkBox2.setOnCheckedChangeListener(checkBoxListener);
        checkBox3.setOnCheckedChangeListener(checkBoxListener);

        progressBar.setVisibility(View.INVISIBLE);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSeekBarResult.setText(progress + "");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    RadioGroup.OnCheckedChangeListener radioListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int radio_id) {
            switch (radio_id) {
                case R.id.radioRed:
                    textView.setText("빨간불이 켜졌습니다.");
                    break;
                case R.id.radioGreen:
                    textView.setText("초록불이 켜졌습니다.");
                    break;
                case R.id.radioBlue:
                    textView.setText("파란불이 켜졌습니다.");
                    break;
                case R.id.spinner:
                    Intent intent = new Intent(MainActivity.this, spinnerActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    CompoundButton.OnCheckedChangeListener checkListener  = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.toggleButton:
                    if(isChecked)
                        textView.setText("토글버튼이 켜졌습니다.");
                    else
                        textView.setText("토글버튼이 꺼졌습니다.");
                    break;
                case R.id.Wswitch:
                    if(isChecked)
                        progressBar.setVisibility(View.VISIBLE);
                    else
                        progressBar.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    };

    List<String> checkedList = new ArrayList<>();
    CompoundButton.OnCheckedChangeListener checkBoxListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            switch (compoundButton.getId()) {
                case R.id.checkBox:
                    if(isChecked) {
                        checkedList.add("소");
                    } else
                        checkedList.remove("소");
                    break;
                case R.id.checkBox2:
                    if(isChecked)
                        checkedList.add("개");
                    else
                        checkedList.remove("개");
                    break;
                case R.id.checkBox3:
                    if(isChecked)
                        checkedList.add("돼지");
                    else
                        checkedList.remove("돼지");
                    break;
            }

            String temp = "";
            for(String item : checkedList) {
                temp += item + " ";
            }
            textView.setText(temp + "(이)가 체크되었습니다.");
        }
    };

    private void initView() {
        textView = (TextView)findViewById(R.id.textView);
        textSeekBarResult = (TextView)findViewById(R.id.textSeekBarResult);

        toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
        Wswitch = (Switch)findViewById(R.id.Wswitch);
        checkBox1 = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox3);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioRed = (RadioButton)findViewById(R.id.radioRed);
        radioGreen = (RadioButton)findViewById(R.id.radioGreen);
        radioBlue = (RadioButton)findViewById(R.id.radioBlue);
        spinner = (RadioButton)findViewById(R.id.spinner);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

    }
}
