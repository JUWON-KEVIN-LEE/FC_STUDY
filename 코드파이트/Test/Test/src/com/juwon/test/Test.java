package com.juwon.test;

import java.util.*;

class Test {
	String reversedSumOfDigits(int p, int n) {
	    int result = 0;
	    String[] arr = new String[n];
	    int start = (int)Math.pow(10, n-1);
	    if(n==1)
		    start = 0;
	    
	    if(p==0&&n>1) {
	        return -1+"";
	    }
	    
	    for(int i=start; i<Math.pow(10, n);i++) {
	        result = 0;
	        arr = String.valueOf(i).split("");
	        for(String item:arr) {
	            System.out.println(item);
	            result += Integer.parseInt(item);
	        }
	        if(result == p) {
	            return i+"";
	        }
	    }

	    return -1+"";
	}
	
	public static void main(String[] args) {

	}
}