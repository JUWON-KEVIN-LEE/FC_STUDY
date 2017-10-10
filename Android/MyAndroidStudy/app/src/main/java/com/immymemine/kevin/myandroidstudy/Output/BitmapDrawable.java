package com.immymemine.kevin.myandroidstudy.Output;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.immymemine.kevin.myandroidstudy.R;

public class BitmapDrawable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DrawView view = new DrawView(this);
        setContentView(view);
        Toast toast = new Toast(this);
        // toast.setView(); view 를 toast 할 수 있다..
    }

    class DrawView extends android.view.View {
        Context context;
        Paint paint;
        public DrawView(Context context) {
            super(context);
            this.context = context;

            paint = new Paint();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.melon_main);
            canvas.drawBitmap(bitmap, null, new Rect(0,0,600,1000), null);
        }
    }
}
