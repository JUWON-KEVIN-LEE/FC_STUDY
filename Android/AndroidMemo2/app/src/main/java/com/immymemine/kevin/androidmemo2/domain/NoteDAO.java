package com.immymemine.kevin.androidmemo2.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.immymemine.kevin.androidmemo2.DBHelper;

import java.util.ArrayList;

/**
 * DAO Data Access Object
 * 데이터 조작을 담당
 * Created by quf93 on 2017-09-21.
 */

public class NoteDAO {
    // 1. DB 에 연결 2. 조작 3. 연결을 해제
    DBHelper helper;
    public NoteDAO(Context context) {
        helper = new DBHelper(context);
    }

    // C 생성
    public void create(Note note) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        String query = " insert into note (title, content, n_datetime)" +
                " values('"+ note.title +"',"+"'"+ note.content +"', datetime('now', 'localtime'))";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.close();
    }
    public void close() {
        helper.close();
    }
    // R 읽기
    public ArrayList<Note> read(String query) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        ArrayList<Note> list = new ArrayList<>();
        while(cursor.moveToNext()) {
            Note note = new Note();
            for(String item : cursor.getColumnNames()) {
                switch (item) {
                    case "id":
                        note.id = cursor.getInt(cursor.getColumnIndex("id"));
                        break;
                    case "title":
                        note.title = cursor.getString(cursor.getColumnIndex("title"));
                        break;
                    case "content":
                        note.content = cursor.getString(cursor.getColumnIndex("content"));
                        break;
                    case "n_datetime":
                        note.n_datetime = cursor.getString(cursor.getColumnIndex("n_datetime"));
                        break;
                }
            }
            list.add(note);
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
    // U 수정
    public void update() {

    }
    // D 삭제
    public void delete() {

    }
}
