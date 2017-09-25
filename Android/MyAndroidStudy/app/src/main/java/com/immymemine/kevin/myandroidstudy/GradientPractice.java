package com.immymemine.kevin.myandroidstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by quf93 on 2017-09-24.
 */

public class GradientPractice extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 객체 생성
//        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
        ShaderPractice shaderPractice = new ShaderPractice(this);
        // layout 에 추가
//        linearLayout.addView(shaderPractice);
        setContentView(shaderPractice);
    }

    class ShaderPractice extends View {
        Paint paint;

        public ShaderPractice(Context context) {
            super(context);
            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setAntiAlias(true);

            int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.WHITE};
            float[] pos = {0.0f, 0.1f, 0.6f, 0.9f, 1.0f};
            // 수평
            paint.setShader(new LinearGradient(0, 0, 200, 0, Color.BLACK, Color.WHITE, Shader.TileMode.CLAMP));
            // 0,0 에서 100,0 으로 Black > White 로 Shade 를 줘라
            canvas.drawRect(0, 0, 200, 200, paint);
            // 우하향
            paint.setShader(new LinearGradient(220, 0, 420, 200, Color.BLUE, Color.WHITE, Shader.TileMode.CLAMP));
            canvas.drawRect(220,0, 420,200, paint);
            // 110, 0 왼쪽 위 모서리 에서 210, 100 오른쪽 아래 모서리로 Shade
            // 우상향
            paint.setShader(new LinearGradient(440,200,640,0, Color.CYAN, Color.MAGENTA, Shader.TileMode.CLAMP));
            canvas.drawRect(440,0,640,200,paint);
            //가장자리 색 반복
            paint.setShader(new LinearGradient(0,0,200,0,Color.DKGRAY, Color.GREEN, Shader.TileMode.CLAMP));
            canvas.drawRect(0,220, 640,300,paint); // setting 된  Shader 보다 더 크게...
            // 무늬 반복
            paint.setShader(new LinearGradient(0,0,200,0, Color.BLUE, Color.RED, Shader.TileMode.REPEAT));
            canvas.drawRect(0,320,640,400,paint);
            // 무늬 반사 반복
            paint.setShader(new LinearGradient(0,0,200,0, Color.BLUE, Color.RED, Shader.TileMode.MIRROR));
            canvas.drawRect(0,420,640,500,paint);
            // 여러 가지 색상 균등 배치
            paint.setShader(new LinearGradient(0, 0, 640, 0, colors, null, Shader.TileMode.CLAMP));
            canvas.drawRect(0,520,640,600, paint);
            // 여러 가지 색상 임의 배치
            paint.setShader(new LinearGradient(0,0,640,0,colors,pos, Shader.TileMode.CLAMP));
            canvas.drawRect(0,620,640,700,paint);
        }
    }
}
