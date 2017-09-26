package com.immymemine.kevin.customgallery;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GalleryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GalleryAdapter adapter;
    List<String> thumb;
    Map<String, String> content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        init();
    }
    private void init() {
        thumb = thumnailLoad();
        content = contentLoad();
        adapter = new GalleryAdapter(this);
        adapter.setData(thumb, content);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    // Content Resolver 를 통해서 이미지 목록을 가져온다
    private List<String> thumnailLoad() {
        List<String> list = new ArrayList<>();

        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI; // 시간 순서대로 uri 값 가져와진다...
        String[] projections = { MediaStore.Images.Thumbnails.DATA };
        Cursor cursor = resolver.query(uri, projections, null, null, null);

        if(cursor != null) {
            while(cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndex(projections[0]));
                list.add(path);
            }
        }
        System.out.println(list);
        return list;
    }

    private Map<String, String> contentLoad() {
        Map<String, String> map = new LinkedHashMap<>();

        ContentResolver resolver = getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI; // 시간 무관...
        String[] projections = { MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_TAKEN };
        Cursor cursor = resolver.query(uri, projections, null, null, projections[1] + " ASC"); // 시간순으로 sort

        if(cursor != null) {
            while(cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndex(projections[0]));
                String datetime = cursor.getString(cursor.getColumnIndex(projections[1]));
                map.put(path,datetime);
            }
        }

        for(String item : map.keySet()) {
            System.out.println("key = " + item);
            System.out.println("value = " + map.get(item));
        }
        return map;
    }

}
