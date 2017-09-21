package com.immymemine.kevin.androidmemo2.domain;

/**
 * Created by quf93 on 2017-09-21.
 */

public class Note {
    int id;
    String title;
    String n_datetime;
    String content;
    final String DELIMETER = " | ";
    @Override
    public String toString() {
        return id + DELIMETER + title +DELIMETER + n_datetime + DELIMETER + content + "\n";
    }
}
