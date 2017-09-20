package com.immymemine.kevin.basiclayout.design.Interface;

/**
 * Created by quf93 on 2017-09-15.
 */

public interface IModel {
    // 쓰는 도중에 임시 저장
    void update(String expression, String result);

    // update 마지막을 저장
    // sendExpressionAndResultToSave 값을 받아 저장한다.
    void save(String expressionAndResult);
}
