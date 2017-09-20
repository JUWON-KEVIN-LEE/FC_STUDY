package com.immymemine.kevin.basiclayout;

import com.immymemine.kevin.basiclayout.design.Interface.ICalc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by quf93 on 2017-09-15.
 */

public class Calc implements ICalc {

    // 일부에서는 조건문을 합칠 수 있으나 숫자와 숫자를 제외한 기능키들을 분리해서 조건문에 넣어줬다
    public String getResult(String expression) {
        if(expression.equals("")) // 아무것도 입력하지 않았을 때
            return "";

        if(expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("×")||expression.endsWith("/")) { // 연산자로 끝이 날 때
            if(expression.endsWith("+")||expression.endsWith("-")) {
                expression = expression + 0;
            }
            else {
                expression = expression + 1;
            }
        }

        if(bracketCount>0) { // ")" 를 덜 채웠을 때
            for (int i = 0; i < bracketCount; i++)
                expression += ")";
        }

        String result;
        if(isBracket(expression)) {
            result = calculationWithBracket(expression);
        } else {
            result = calculation(expression);
        }
        return result;
    }
    @Override
    public String numberIsPossible(String input, String expression) {
        if(expression.length()==0||expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("×")||expression.endsWith("/")||expression.endsWith(".")||expression.endsWith("(")) {
            return input;
        }
        else if(expression.endsWith("%")||expression.endsWith(")")){
            return "×"+input; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
        } else
            return input; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
    }
    @Override
    public String dotIsPossible(String dot, String expression) {
        if(expression.length()==0||expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("×")||expression.endsWith("/")||expression.endsWith("(")) {
            return "0"+dot;
        }
        else if(expression.endsWith("%")||expression.endsWith(")")){
            return "×0"+dot;
        } else if(expression.endsWith(".")) {
            return ""; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
        } else
            return dot; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
    }
    @Override
    public String operatorIsPossible(String input, String expression) {
        System.out.println(expression);
        if(expression.length()==0||expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("×")||expression.endsWith("÷")||expression.endsWith("(")) {
            return "";
        }
        else if(expression.endsWith("%")||expression.endsWith(")")||expression.endsWith(".")){
            return input; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
        } else
            return input; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
    }
    @Override
    public String perIsPossible(String input, String expression) {
        if(expression.length()==0||expression.endsWith("+")||expression.endsWith("-")||expression.endsWith("×")||expression.endsWith("÷")||expression.endsWith("(")||expression.endsWith("%")) {
            return "";
        }
        else if(expression.endsWith(")")||expression.endsWith(".")){
            return input; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
        } else
            return input; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
    }
    int bracketCount = 0; //TODO AC 누르거나 지우면 차감하거나 초기화 해줘야한다!
    @Override
    public String bracketIsPossible(String input, String expression) {
        if (input.equals("(")) {
            if (expression.length() == 0 || expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("×") || expression.endsWith("÷") || expression.endsWith("(")) {
                bracketCount++;
                return input;
            } else if (expression.endsWith(")") || expression.endsWith(".")) {
                bracketCount++;
                return "×" + input; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
            } else {
                bracketCount++;
                return "×" + input; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
            }
        } else {
            if (bracketCount > 0) {
                if (expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("×") || expression.endsWith("÷") || expression.endsWith("(")) {
                    return "";
                } else if (expression.endsWith(".") || expression.endsWith(")")) {
                    bracketCount--;
                    return input; // 앞에 올 수 있는 모든 경우의 수에 대한 반응을 설정해주고
                } else {
                    bracketCount--;
                    return input; // 그 외에 앞에 숫자가 올 경우에는 추가만 하라고 설정
                }
            } else {
                return "";
            }
        }
    }
    @Override
    public boolean isBracket(String expression) {
        return expression.contains("(");
    }
    @Override
    public String calculationWithBracket(String expression) { // isBracket == true
        String[] array = expression.split("");
        List<String> list = asList(array);
        list = new ArrayList<>(list);

        while(true) {
            int index = expression.indexOf(")"); // 가장 가까운 ) 찾고
            if(index==-1) // ) 없으면 while 문 나가라
                break;
            for(int i=index; i>0; i--) {
                if(list.get(i).equals("(")) { // ) 앞에 있는 ( 를 찾아서
                    list.set(i, calculation(expression.substring(i+1, index))); // 그 사이에 있는 식을 계산해서 ( 자리에 담는다. //TODO 계산식에 넣어야함
                    for(int j=i; j<index; j++) // ( 이후로는 삭제한다.
                        list.remove(i+1); // EX) (17+(5*4+3)*3) > (17+23*3) > 86
                    break;
                }
            }
        }

        if(!(list.size()==1)) {
            expression = "";
            for (String item : list)
                expression += item;
            String result = calculation(expression);
            return result;
        } else
            return list.get(0);
    }
    @Override
    public String calculation(String expression) { // Queue || 정규식 사용하는 방법도 있다.
        expression = expression.replace("+"," + ").replace("-"," - ").replace("×"," × ").replace("÷"," ÷ ");
        List<String> list;

        if(expression.contains("%")) {
            String[] temp = expression.split(" ");
            for(int i=0; i<temp.length; i++)
                if(temp[i].endsWith("%"))
                    temp[i] = String.valueOf(Double.parseDouble(temp[i].substring(0,temp[i].length()-1)) / 100);
            list = new ArrayList<>(Arrays.asList(temp));
        } else {
            String[] temp = expression.split(" ");
            list = new ArrayList<>(Arrays.asList(temp));
        }
        int count = 0;
        while(true) {
            if (expression.contains("×") || expression.contains("÷")) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).equals("×") || list.get(i).equals("÷")) {
                        if (list.get(i).equals("×")) {
                            double d1 = Double.parseDouble(list.get(i - 1));
                            double d2 = Double.parseDouble(list.get(i + 1));
                            list.set(i - 1, String.valueOf(d1 * d2));
                            list.remove(i); // TODO 곱하기나 나누기가 두개 이상이면 빈자리를 불러들여서 Exception call
                            list.remove(i);
                            break;
                            //String result = BigDecimal.valueOf(d1).multiply(BigDecimal.valueOf(d2)).toString(); API 문제 발생
                        } else {
                            double d1 = Double.parseDouble(list.get(i - 1));
                            double d2 = Double.parseDouble(list.get(i + 1));
                            list.set(i - 1, String.valueOf(d1 / d2));
                            list.remove(i);
                            list.remove(i);
                            break;
                        }
                    }
                }
            }
            else
                break;
        }
        while(list.size()!=1) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals("+")) {
                    double d1 = Double.parseDouble(list.get(i - 1));
                    double d2 = Double.parseDouble(list.get(i + 1));
                    list.set(i - 1, String.valueOf(d1 + d2));
                    list.remove(i);
                    list.remove(i);
                    break;
                } else if (list.get(i).equals("-")) {
                    double d1 = Double.parseDouble(list.get(i - 1));
                    double d2 = Double.parseDouble(list.get(i + 1));
                    list.set(i - 1, String.valueOf(d1 - d2));
                    list.remove(i);
                    list.remove(i);
                    break;
                }
            }
        }
        return list.get(0);
    }
    Model model = new Model();
    @Override
    public void update(String expression, String result) {
        model.update(expression, result);
    }

    @Override
    public void sendExpressionAndResultToSave(String expressionAndResult) {
        model.save(expressionAndResult);
    }

}
