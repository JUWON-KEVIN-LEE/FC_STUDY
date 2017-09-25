package com.immymemine.kevin.pic_memo_orm.DAO;

import android.content.Context;

import com.immymemine.kevin.pic_memo_orm.DBHelper;
import com.immymemine.kevin.pic_memo_orm.model.PicNote;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by quf93 on 2017-09-22.
 */

public class PicNoteDAO {
    DBHelper helper;
    Dao<PicNote, Long> dao;
    List<PicNote> list;
    public PicNoteDAO(Context context) {
        helper = new DBHelper(context);
        try {
            dao = helper.getDao(PicNote.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(PicNote picNote) {
        try {
            dao.create(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<PicNote> read() {
        list = null;
        try {
            list = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void update() {

    }
    public void delete() {

    }
}
