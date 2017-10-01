package com.immymemine.kevin.melon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    LinearLayout content1, content2, content3, content4, content5, content6, content7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTap();
    }

    private void initView() {
        tabHost = (TabHost) findViewById(R.id.tabHost);
        content1 = (LinearLayout) findViewById(R.id.content1);
        content2 = (LinearLayout) findViewById(R.id.content2);
        content3 = (LinearLayout) findViewById(R.id.content3);
        content4 = (LinearLayout) findViewById(R.id.content4);
        content5 = (LinearLayout) findViewById(R.id.content5);
        content6 = (LinearLayout) findViewById(R.id.content6);
        content7 = (LinearLayout) findViewById(R.id.content7);
    }

    private void initTap() {
        tabHost.setup();
        // 태그 달고 > content 랑 연결하고 > tab 이름 정해준다.
        TabHost.TabSpec ts1 = tabHost.newTabSpec("뮤직"); ts1.setContent(R.id.content1); ts1.setIndicator("뮤직");
        TabHost.TabSpec ts2 = tabHost.newTabSpec("비디오"); ts2.setContent(R.id.content2); ts2.setIndicator("비디오");
        TabHost.TabSpec ts3 = tabHost.newTabSpec("MY"); ts3.setContent(R.id.content3); ts3.setIndicator("MY");
        TabHost.TabSpec ts4 = tabHost.newTabSpec("For U"); ts4.setContent(R.id.content4); ts4.setIndicator("For U");
        TabHost.TabSpec ts5 = tabHost.newTabSpec("스타"); ts5.setContent(R.id.content5); ts5.setIndicator("스타");
        TabHost.TabSpec ts6 = tabHost.newTabSpec("피드"); ts6.setContent(R.id.content6); ts6.setIndicator("피드");
        TabHost.TabSpec ts7 = tabHost.newTabSpec("콘서트"); ts7.setContent(R.id.content7); ts7.setIndicator("콘서트");
        // tabHost 에 tab 을 추가...
        tabHost.addTab(ts1); tabHost.addTab(ts2); tabHost.addTab(ts3); tabHost.addTab(ts4); tabHost.addTab(ts5); tabHost.addTab(ts6); tabHost.addTab(ts7);
    }

}

