package com.immymemine.kevin.tetrisgame;

/**
 * Created by quf93 on 2017-09-29.
 */

public class Setting {
    float width, height;
    int columns, rows;
    float unit;

    public Setting(float width, float height, int columns, int rows) {
        this.width = width; this.height = height;
        this.columns = columns; this.rows = rows;
        this.unit = width / columns;
    }
}