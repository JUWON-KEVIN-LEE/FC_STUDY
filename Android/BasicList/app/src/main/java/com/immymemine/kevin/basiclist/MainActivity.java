package com.immymemine.kevin.basiclist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 *  List
 */

public class MainActivity extends AppCompatActivity {
    // 1. Data 2. Adapter 3. Connect
    List<String> data = new ArrayList<>();

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = (View) inflater.inflate(R.layout.activity_main, null);
//        setContentView(view);

        for(int i=0; i<100; i++)
            data.add("temp = "+i);
        CustomAdapter customAdapter = new CustomAdapter(this, data); // Adapter
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(customAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

class CustomAdapter extends BaseAdapter {
    // 데이터 저장소를 아답터 내부에 두는 것이 컨트롤하기 편하다.
    List<String> data;
    Context context;
    public CustomAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override                         // listView
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView==null) { // Item View 를 재사용하기 위해서 null 체크를 해준다
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            // 홀더를 초기화 하면서 레이아웃의 TextView 와 연동해서
            holder = new Holder(convertView);
            // 홀더를 View 에 붙여놓는다
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.textView.setText(data.get(position));
        return convertView; // > getView(position, convertView, parent)
    }

    class Holder {
        TextView textView;

        public Holder(View view) {
            textView = (TextView) view.findViewById(R.id.textView);
            init();
        }

        public void init() {
            textView.setOnClickListener(new View.OnClickListener() {
                // 화면에 보여지는 View 는 기본적으로 자신이 속한 Component 의 Context 를 그대로 가지고 있다.
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetailActivity.class);
                    intent.putExtra("valueKey", textView.getText());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

}