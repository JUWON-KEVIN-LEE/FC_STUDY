package com.immymemine.kevin.pic_memo_orm.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by quf93 on 2017-09-22.
 */
@DatabaseTable(tableName = "picnote")
public class PicNote {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String title;
    @DatabaseField
    private String bitmap_path;
    @DatabaseField
    private String pn_datetime;

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

    public String getPn_datetime() {
        return pn_datetime;
    }
    public void setPn_datetime(String pn_datetime) {
        this.pn_datetime = pn_datetime;
    }
}
