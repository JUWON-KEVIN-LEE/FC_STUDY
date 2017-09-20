package com.immymemine.kevin.basiclayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.immymemine.kevin.basiclayout.design.Interface.IView;

/**
 * Created by quf93 on 2017-09-15.
 */

public class CalcView extends AppCompatActivity implements IView, View.OnClickListener{
    TextView expression, result;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    Button btnAdd, btnSub, btnMul, btnDiv, btnResult, btnAC, btnPer, btnBack, btnDot, btnLeftBra, btnRightBra;
    Calc calc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcview);
        init();
        calc = new Calc();
    }

    public void init() {
        initView();
        initListener();
    }
    public void resultButtonClicked() {
        String result = calc.getResult(getExpression());
        showResult(result);
        calc.sendExpressionAndResultToSave(getExpression()+" = "+result);
    }
    @Override
    public void appendNumber(View view) {
        String number = ((Button)view).getText().toString(); // 버튼의 넘버
        appendExpression(calc.numberIsPossible(number, getExpression()));

        // 어떤 형태로 넣을 수 있는지 판단해서 반환해주는 값을 수식에 추가
    }
    @Override
    public void appendDot() {
        String dot = ".";
        appendExpression(calc.dotIsPossible(dot, getExpression()));
    }
    @Override
    public void appendPer() {
        String per = "%";
        appendExpression(calc.perIsPossible(per, getExpression()));
    }
    @Override
    public void appendLeftBracket() {
        String leftBrac = "(";
        appendExpression(calc.bracketIsPossible(leftBrac, getExpression()));
        if(calc.bracketCount==1) {
            if(calc.isBracket(getExpression().substring(0,getExpression().length()-1))) {
                String result = calc.calculationWithBracket(getExpression().substring(0,getExpression().length()-1));
                setResult(result);
            } else {
                String result = calc.calculation(getExpression().substring(0, getExpression().length()-1));
                setResult(result);
            }
        }
    }
    @Override
    public void appendRightBracket() {
        String rightBrac = ")";
        appendExpression(calc.bracketIsPossible(rightBrac, getExpression()));
        if(calc.bracketCount==0) {
            String result = calc.calculationWithBracket(getExpression());
            setResult(result);
        }
    }
    @Override
    public void appendOperator(View view) {
        String operator = ((Button)view).getText().toString();
        System.out.println(calc.operatorIsPossible(operator, getExpression()));
        appendExpression(calc.operatorIsPossible(operator, getExpression()));
        if(!calc.isBracket(getExpression())) {
            String result = calc.calculation(getExpression().substring(0, getExpression().length() - 1));
            setResult(result);
        } else if(calc.bracketCount==0) {
            String result = calc.calculationWithBracket(getExpression().substring(0, getExpression().length() - 1));
            setResult(result);
        }
    }
    @Override
    public void delete() {
        setExpression(getExpression().substring(0,getExpression().length()-1));
        if(!calc.isBracket(getExpression())) {
            String result = calc.calculation(getExpression());
            setResult(result);
        } else if(calc.bracketCount==0) {
            String result = calc.calculationWithBracket(getExpression());
            setResult(result);
        }
    }
    @Override
    public void allClear() {
        setExpression("");
        setResult("");
    }
    @Override
    public String getExpression() {
//        if(expression.getText().toString().equals(null)) {
//            expression.setText("");
//        }
        try{
            return expression.getText().toString();
        } catch(NullPointerException e) {
            return "";
        }
    }
    @Override
    public void setExpression(String str) {
        expression.setText(str);
    }
    @Override
    public void appendExpression(String str) {
        expression.append(str);
    }
    @Override
    public void setResult(String res) {
        calc.update(getExpression(), result.getText().toString());
        result.setText(res);
    }
    @Override
    public void showResult(String res) {
        calc.sendExpressionAndResultToSave(getExpression()+" = "+res);
        expression.setText(res);
        result.setText("");
    }

    private void initView() {
        expression = (TextView)findViewById(R.id.expression);
        result = (TextView)findViewById(R.id.resultView);
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnSub = (Button)findViewById(R.id.btnSubstract);
        btnMul = (Button)findViewById(R.id.btnMultiply);
        btnDiv = (Button)findViewById(R.id.btnDivide);
        btnResult = (Button)findViewById(R.id.btnResult);
        btnAC = (Button)findViewById(R.id.btnAC);
        btnPer = (Button)findViewById(R.id.btnPer);
        btnBack = (Button)findViewById(R.id.btnBack);
        btnDot = (Button)findViewById(R.id.btnDot);
        btnLeftBra = (Button)findViewById(R.id.btnLeftBracket);
        btnRightBra = (Button)findViewById(R.id.btnRightBracket);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                appendNumber(v);
                break;
            case R.id.btnAdd:
            case R.id.btnSubstract:
            case R.id.btnMultiply:
            case R.id.btnDivide:
                appendOperator(v);
                break;
            case R.id.btnAC:
                allClear();
                break;
            case R.id.btnPer:
                appendPer();
                break;
            case R.id.btnBack:
                delete();
                break;
            case R.id.btnResult:
                resultButtonClicked();
                break;
            case R.id.btnDot:
                appendDot();
                break;
            case R.id.btnLeftBracket:
                appendLeftBracket();
                break;
            case R.id.btnRightBracket:
                appendRightBracket();
                break;
        }
    }
    private void initListener() {
        btn0.setOnClickListener(this); btn1.setOnClickListener(this);
        btn2.setOnClickListener(this); btn3.setOnClickListener(this);
        btn4.setOnClickListener(this); btn5.setOnClickListener(this);
        btn6.setOnClickListener(this); btn7.setOnClickListener(this);
        btn8.setOnClickListener(this); btn9.setOnClickListener(this);
        btnAdd.setOnClickListener(this); btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this); btnDiv.setOnClickListener(this);
        btnAC.setOnClickListener(this); btnPer.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnResult.setOnClickListener(this); btnDot.setOnClickListener(this);
        btnLeftBra.setOnClickListener(this); btnRightBra.setOnClickListener(this);
    }
}
