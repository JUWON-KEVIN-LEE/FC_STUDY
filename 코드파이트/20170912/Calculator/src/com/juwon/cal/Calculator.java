package com.juwon.cal;


public class Calculator {
	public static void main(String[] args) {
		String str = "10+2/2*3+3";
		
		char[] strChar = str.toCharArray();
		
		str = strChar.toString();
		System.out.println(str);
	}
}
