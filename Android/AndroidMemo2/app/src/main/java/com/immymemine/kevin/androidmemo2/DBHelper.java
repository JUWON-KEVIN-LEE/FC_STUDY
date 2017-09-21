package com.immymemine.kevin.androidmemo2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by quf93 on 2017-09-21.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "sqlite.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // super 에서 넘겨받은 DB 가 생성되어 있는지 확인한 후
        // 없으면 onCreate 호출
        // 있으면 버전 체크해서 존재하는 DB 보다 버전이 높으면 onUpgrade 를 호출해준다
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 최초 생성할 Table 을 정의
        // DB 가 업데이트되면 모든 히스토리가 Query 에 반영되어 있어야 한다
        String createQuery = "CREATE TABLE `note` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT, `content` TEXT, `n_datetime` TEXT )";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // oldVersion 과 newVersion 을 비교해서
        // 히스토리를 모두 반영해야 한다
    }
}
