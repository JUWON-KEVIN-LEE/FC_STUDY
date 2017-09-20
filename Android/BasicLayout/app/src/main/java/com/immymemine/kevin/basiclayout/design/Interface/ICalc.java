package com.immymemine.kevin.basiclayout.design.Interface;

/** Calculation Design
 * Created by quf93 on 2017-09-15.
 */

public interface ICalc {
    // 입력 가능한 값인지 판단
    String numberIsPossible(String input, String expression);
    String dotIsPossible(String dot, String expression);
    String operatorIsPossible(String input, String expression);
    String perIsPossible(String input, String expression);
    String bracketIsPossible(String input, String expression);

    // 1. 괄호 확인
    boolean isBracket(String expression); // true bracketCalculation 또는 false noneBracketCalculation

    // 2. 괄호에 따라 split 의 방식을 나눈다
    String calculationWithBracket(String expression);

    // 3. 나눈 값들을 받아 사칙연산의 우선순위에 따라 계산
    String calculation(String expression);

    // 4. 계산 결과를 Model에 Update
    void update(String expression, String result);

    void sendExpressionAndResultToSave(String expressionAndResult);
}
