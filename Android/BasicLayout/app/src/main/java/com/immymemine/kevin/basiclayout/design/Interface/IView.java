package com.immymemine.kevin.basiclayout.design.Interface;

import android.view.View;

/**
 * Created by quf93 on 2017-09-15.
 */

public interface IView { // 입출력
    // 버튼 입력
    void appendNumber(View view);
    void appendDot();
    void appendPer();
    void appendLeftBracket();
    void appendRightBracket();
    void appendOperator(View view);

    // 입력된 값 삭제
    void delete();
    void allClear();
    // 현재까지 입력된 수식 가져오기
    String getExpression();
    // Calc 에서 Permission 을 받아 expression 을 set
    void setExpression(String str);
    void appendExpression(String str);
    // 결과값 출력
    void setResult(String res);
    void showResult(String result);
}