package com.immymemine.kevin.contact;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.immymemine.kevin.contact.Util.PermissionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *  Content Resolver 사용하기
 *  1. Content Resolver 를 불러오기
 *  2. 데이터 URI 정의 < 일종의 DB 에서의 테이블 이름
 *  3. 데이터 URI 에서 가져올 컬럼명 정의
 *  -. 조건절을 사용할 수 있다
 *  4. Content Resolver 로 쿼리한 데이터를 Cursor 에 담는다
 *  5. Cursor 에 담긴 데이터를 처리...
 */
public class MainActivity extends AppCompatActivity implements PermissionUtil.CallInit {
    List<Contact> contacts;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    PermissionUtil perm;
    private static final int REQ_QODE = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        perm = new PermissionUtil(REQ_QODE);
        perm.versionCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        perm.requestResult(this, requestCode, grantResults);
    }

    public void init() {
        contacts = load();

        adapter = new CustomAdapter();
        adapter.setData(contacts);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }

    private List<Contact> load() {
        contacts = new ArrayList<>();
        // 1. Content Resolver 불러오기
        ContentResolver resolver = getContentResolver();
        // 2. 데이터 URI 정의
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        // 3. 가져올 컬럼 정의
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };
        // 4. 쿼리 결과 > Cursor
        Cursor cursor = resolver.query(uri, projection, null, null, null);
        // 5. cursor 반복 처리
        if(cursor != null) {
            while(cursor.moveToNext()) {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndex(projection[0])));
                contact.setName(cursor.getString(cursor.getColumnIndex(projection[1])));
                contact.setNumber(cursor.getString(cursor.getColumnIndex(projection[2])));
                contacts.add(contact);
            }
        }
        return contacts;
    }
}

class Contact {
    private int id;
    private String name;
    private String number;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}