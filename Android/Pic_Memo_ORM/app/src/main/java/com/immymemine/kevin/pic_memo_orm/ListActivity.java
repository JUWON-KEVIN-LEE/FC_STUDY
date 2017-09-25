package com.immymemine.kevin.pic_memo_orm;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.immymemine.kevin.pic_memo_orm.DAO.PicNoteDAO;
import com.immymemine.kevin.pic_memo_orm.Util.PermissionUtil;
import com.immymemine.kevin.pic_memo_orm.model.PicNote;

import java.util.List;

public class ListActivity extends AppCompatActivity implements PermissionUtil.CallBack{
    private RecyclerView recyclerView;
    private Button postBtn;
    PicNoteDAO dao;
    List<PicNote> data;
    CustomAdapter adapter;

    private static final int REQ_QODE = 111;
    private static final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    PermissionUtil permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initVIew();
        initListener();
        permission = new PermissionUtil(REQ_QODE, permissions);
        permission.checkPermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permission.permissionResult(this, requestCode, grantResults);
    }

    @Override
    protected void onStart() {
        init();
        super.onStart();
    }

    public void init() {
        dao = new PicNoteDAO(this);
        data = dao.read();
        adapter = new CustomAdapter();
        adapter.setData(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initListener() {
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DrawActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initVIew() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        postBtn = (Button) findViewById(R.id.postBtn);
    }
}
