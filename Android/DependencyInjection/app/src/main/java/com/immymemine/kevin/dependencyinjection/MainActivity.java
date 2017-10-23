package com.immymemine.kevin.dependencyinjection;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    // @BindView(R.id.text)
    @ViewById
    TextView text;

    @AfterViews
    public void init() {
        text.setText("Hello AA");
        back();
    }

    @Background
    public void back() {
        for(int i=0; i<10; i++) {
            main(i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @UiThread
    public void main(int i) {
        text.setText(i+"");
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // ButterKnife.bind(this);
//        text.setText("Hello AA");
//    }
}
