package com.juwon.codefights_study;

import java.util.Arrays;

public class MakeArrayConsecutive {
	public static void main(String[] args) {

	}
	
	int makeArrayConsecutive(int[] statues) {
		int result = 0;
		
		Arrays.sort(statues);
		int min = statues[0];
		int max = statues[statues.length-1];
		result = max - min + 1 - statues.length;
		
		return result;
	}
}