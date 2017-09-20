package com.immymemine.kevin.androidmemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.immymemine.kevin.androidmemo.domain.Memo;
import com.immymemine.kevin.androidmemo.utility.FileUtil;

import java.io.File;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    Button post;
    ArrayList<Memo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initView();
        initListener();
        init();
    }
    private void initView() {
        listView = (ListView)findViewById(R.id.listView);
        post = (Button)findViewById(R.id.postBtn);
    }
    private void initListener() {
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, WriteActivity.class);
                intent.putExtra("length", list.size());
                startActivityForResult(intent, WRITE);
            }
        });
    }
    private void init() {
        list = loadData();
        CustomAdapter customAdapter = new CustomAdapter(this, list);
        listView.setAdapter(customAdapter);
    }

    private final int WRITE = 1001;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case WRITE:
                if(resultCode==RESULT_OK) {
                    init();
                }
                break;
        }
    }

    private ArrayList<Memo> loadData() {
        // File file = new File(getFilesDir().getAbsolutePath());
        list = new ArrayList<>();
        for(File file : getFilesDir().listFiles()) {
            try {
                String text = FileUtil.readAllFromFile(ListActivity.this, file.getName());
                Memo memo = new Memo();
                memo.setMemo(text);
                list.add(memo);
            } catch (Exception e) {
                Toast.makeText(this, "Error = " + e.toString(), Toast.LENGTH_LONG);
            }
        }

        return list;
    }
}

class CustomAdapter extends BaseAdapter {
    ArrayList<Memo> list;
    Context context;

    public CustomAdapter(Context context, ArrayList<Memo> list) {
        this.context = context; this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView==null) {
            // View 생성
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            // Holder 생성
            holder = new Holder(convertView);
            // View 에 Holder 를 붙인다
            convertView.setTag(holder);
        } else {
            // View 에 붙인 홀더를 꺼낸다
            holder = (Holder)convertView.getTag();
        }
        Memo memo = list.get(position);
        holder.setPosition(position);
        holder.setTitle(memo.getSubject());
        holder.setDatetime(memo.getDateTime());

        return convertView;
    }

    class Holder { // tv 두개
        // LinearLayout item_list;
        private int position;
        private TextView title, datetime;

        public Holder(View convertView) {
            // item_list = (LinearLayout)convertView.findViewById(R.id.item_list);
            title = (TextView)convertView.findViewById(R.id.titleView);
            datetime = (TextView)convertView.findViewById(R.id.datetime);
            initListener(convertView);
        }
        public void setTitle(String title) {
            this.title.setText(title);
        }
        public void setDatetime(String datetime) {
            this.datetime.setText(datetime);
        }
        public void setPosition(int position) {
            this.position = position;
        }
        Intent intent = new Intent(context, DetailActivity.class);
        public void initListener(View convertView) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });
        }
    }
}