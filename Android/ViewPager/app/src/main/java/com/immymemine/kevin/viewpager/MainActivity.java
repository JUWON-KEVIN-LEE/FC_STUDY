package com.immymemine.kevin.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *  ViewPager 사용하기
 */

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        List<String> data = new ArrayList<>();
        for(int i=0; i<100; i++)
            data.add("position = " + i);

        CustomAdapter adapter = new CustomAdapter(this, data);
        // viewPager.setAdapter(adapter);
        adapter.setView(viewPager);
    }
}
// MVC.. Web 에서나 가능 안드로이드나 자바에서는 끼워맞추기식..
class CustomAdapter extends PagerAdapter {
    List<String> data;
    Context context;

    public void setView(ViewPager viewPager) {
        viewPager.setAdapter(this);
    }
    public CustomAdapter(Context context, List<String> data) {
        this.context = context; this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    // instantiateItem method 에서 리턴된 object 가 view 와 동일한지 check
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) { // GetView 의 역할
        // 레이아웃 파일을 inflate 해서 view 객체를 만들어준다
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager, null);
        // data 를 만들어놓은 view 에 세팅
        String value = data.get(position);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(value);
        // view group 에 만들어놓은 view 를 add 한다
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) { // 현재 사용하지 않는 View 는 제거
        container.removeView((View)object);
        //super.destroyItem(container, position, object);
    }
}