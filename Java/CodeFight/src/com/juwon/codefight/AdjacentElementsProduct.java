package com.juwon.codefight;

class AdjacentElementsProduct {
	public int adjacentElementsProduct(int[] inputArray) {
		int[] resultArray = new int[inputArray.length - 1];
		int result = 0;
		for(int i = 1; i < inputArray.length; i++) {
		  	resultArray[i-1] = inputArray[i-1] * inputArray[i];
		}
	    
	    if(resultArray.length == 1) {
	        result = resultArray[0];
	        return result;
	    }
	    
		if(resultArray[0] > resultArray[1])
	        result = resultArray[0];
	    else
	        result = resultArray[1];
	    
		for(int i = 0; i < resultArray.length; i++) {
		   	if(resultArray[i] > result)
		   		result = resultArray[i];
		}
		    
		return result;       
	}
	
	public static void main(String[] args) {
		
	}
}
