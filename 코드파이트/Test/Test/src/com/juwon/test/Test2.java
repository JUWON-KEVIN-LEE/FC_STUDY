package com.juwon.test;

public class Test2 {
	CalcView calcView = new CalcView();
	
	public static void main(String[] args) {
		Test2 t = new Test2();
		String str = t.operatorIsPossible("+");
		System.out.println(str);
	}
	
	public String operatorIsPossible(String input) {
        String expression = calcView.getExpression(); // 수식 받아와서
        System.out.println(expression);
        if(expression.length()==0||expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("*")||expression.endsWith("/")||expression.endsWith("(")) {
            return "";
        }
        else if(expression.endsWith("%")||expression.endsWith(")")||expression.endsWith(".")){
            return input; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
        } else
            return input; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
    }
}

class CalcView{
	Test2 calc = new Test2();
	String exp;
	public String getExpression() {
		return "get Exp";
	}
	
	public void appendOperator() {
        String operator = "+";
        System.out.println(calc.operatorIsPossible(operator));
        appendExpression(calc.operatorIsPossible(operator));
//        if(!calc.isBracket(getExpression())) {
//            String result = calc.calculation(getExpression().substring(0, getExpression().length() - 1));
//            setResult(result);
//        } else if(calc.bracketCount==0) {
//            String result = calc.calculationWithBracket(getExpression().substring(0, getExpression().length() - 1));
//            setResult(result);
//        }
    }
	
	public void appendExpression(String str) {
		exp = str;
	}
}