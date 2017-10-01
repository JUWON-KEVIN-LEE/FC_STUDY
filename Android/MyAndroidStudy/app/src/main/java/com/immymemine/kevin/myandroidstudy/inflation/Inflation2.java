package com.immymemine.kevin.myandroidstudy.inflation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.immymemine.kevin.myandroidstudy.R;

import java.io.File;

public class Inflation2 extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflation2);
        btn = (Button)findViewById(R.id.new_message);
        File file = new File("asdas.txt");
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout rel = (RelativeLayout)View.inflate(Inflation2.this, R.layout.new_message_practice, null);
                // 미리 정의해놓은 new_message_practice 를 가져와서 inflation2 액티비티에 붙여줬다
                LinearLayout linear = (LinearLayout)findViewById(R.id.linear);
                // 가독성을 위해 색상을 달리해줄수 있다.
                if(linear.getChildCount()%2==0) {
                    rel.setBackgroundColor(Color.LTGRAY);
                    TextView newMessageNoti = (TextView) rel.findViewById(R.id.newM);
                    TextView textOpen = (TextView) rel.findViewById(R.id.textOpen);
                    ImageView imageView = (ImageView) rel.findViewById(R.id.imageView);

                    newMessageNoti.setTextColor(Color.WHITE);
                    textOpen.setTextColor(Color.WHITE);
                }

                linear.addView(rel);
            }
        });
    }
}
