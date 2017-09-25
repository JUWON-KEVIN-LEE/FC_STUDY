package com.immymemine.kevin.androidmemoorm.DAO;

import android.content.Context;

import com.immymemine.kevin.androidmemoorm.DBHelper;
import com.immymemine.kevin.androidmemoorm.model.PicNote;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;

import java.sql.SQLException;
import java.util.List;

/**
 *  DAO > 의존성을 낮춰준다 > 관리 용이(?)
 * Created by quf93 on 2017-09-22.
 */

public class PicNote_DAO {
    DBHelper helper;
    Dao<PicNote, Long> dao = null;
    public PicNote_DAO(Context context) {
        helper = new DBHelper(context);

        try {
            dao = helper.getDao(PicNote.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // <D extends Dao<T, ?>, T> D   ...   getDao(Class<T> clazz)
    }
    public void create(PicNote picNote) {
        try {
            dao.create(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<PicNote> readAll() {
        List<PicNote> result = null;
        try {
            result = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public PicNote readOneById(long id) {
        PicNote picNote = null;
        try {
            dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return picNote;
    }
    public List<PicNote> search(String word) {
        String query = String.format("select * from picnote where title like '%%%s%%'", word);
        List<PicNote> result = null;
        try {
            GenericRawResults<PicNote> temp = dao.queryRaw(query, dao.getRawRowMapper());
            result = temp.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public void update(PicNote picNote) {
        try {
            dao.update(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(PicNote picNote) {
        try {
            dao.delete(picNote);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
