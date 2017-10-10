package com.juwon.codefight;

import java.util.*;

class ReverseParentheses {
	public static String reverseParentheses(String s) {
		// reverse 하는 logic => reverse(String s)
		// parentheses 를 검사하는 logic => 
		List<String> list = Arrays.asList(s.split(""));
		
		
		for(int i=0; i<list.size(); i++) {
			if( ")".equals(list.get(i)) ) {
				List<String> temp = new ArrayList<>();
				for(int j=i-1; j>=0; j--) {
					if( "(".equals(list.get(j)) ) {
						for(int k=j+1; k<=i-1; k++) {
							list.set(k, temp.get(k-j-1));
						}
						break;
					} else {
						temp.add(list.get(j));
					}
				}
			}
			list.remove(i);
			list.remove(j);
		}
		String result = "";
		for(String item : list) {
			result += item;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(reverseParentheses("ab(kd(dajw)dwa)"));
	}
}
