package com.juwon.test;

public class Test2 {
	CalcView calcView = new CalcView();
	
	public static void main(String[] args) {
		Test2 t = new Test2();
		String str = t.operatorIsPossible("+");
		System.out.println(str);
	}
	
	public String operatorIsPossible(String input) {
        String expression = calcView.getExpression(); // ���� �޾ƿͼ�
        System.out.println(expression);
        if(expression.length()==0||expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("*")||expression.endsWith("/")||expression.endsWith("(")) {
            return "";
        }
        else if(expression.endsWith("%")||expression.endsWith(")")||expression.endsWith(".")){
            return input; // �տ� �� �� �ִ� ��� ����� ���� ���� ������ �������ְ�
        } else
            return input; // �� �ܿ� �տ� ���ڰ� �� ��쿡�� �߰��� �϶�� ����
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