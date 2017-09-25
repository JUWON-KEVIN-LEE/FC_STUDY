package com.immymemine.kevin.androidmemoorm.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *  Data Modeling ; Domain 추출 ; 개념 모델링
 */
// Annotaion 을 참조해서 TableUtils.create....table 을 생성해준다
@DatabaseTable(tableName = "picnote") // tableName 을 소문자로 바꿔주어서... 명령과 구분해줄 수 있다.
public class PicNote {
    @DatabaseField
    private long id; // 식별자
    @DatabaseField
    private String title; // 제목
    @DatabaseField
    private String bitmap_path; // 그림
    @DatabaseField
    private long datetime; // 날짜

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getBitmap_path() {
        return bitmap_path;
    }
    public void setBitmap_path(String bitmap_path) {
        this.bitmap_path = bitmap_path;
    }
    public long getDatetime() {
        return datetime;
    }
    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }
}
