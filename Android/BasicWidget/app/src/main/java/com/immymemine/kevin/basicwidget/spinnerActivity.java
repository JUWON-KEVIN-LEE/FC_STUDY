package com.immymemine.kevin.basicwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class spinnerActivity extends AppCompatActivity {
        // 1. 스피너에 입력될 데이터를 정의
        String   data[] = {"월","화","수","목","금","토","일"};
        TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_spinner);

            // 2. 스피너와 데이터를 연결하는 아답터를 정의
            ArrayAdapter<String> adapter
                    = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    data
            );

            // 3. 아답터와 스피너(리스트)를 연결
            Spinner spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
            textView = (TextView) findViewById(R.id.textResult);

            // 4. 스피너에 리스너를 달아준다.
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    String selectedValue = data[position];
                    textView.setText(selectedValue);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
}
