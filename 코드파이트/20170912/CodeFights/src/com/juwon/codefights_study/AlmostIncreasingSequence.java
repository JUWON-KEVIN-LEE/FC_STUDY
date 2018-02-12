package com.juwon.codefights_study;

public class AlmostIncreasingSequence {
	public boolean almostIncreasingSequence(int[] sequence) {
boolean result = false;
		
		if(sequence.length==2) {
			if(sequence[1]-sequence[0] == 1);
				result = true;
			return result;
		} else if(sequence.length == 3) {
			if(sequence[1]-sequence[0] == 1||sequence[2]-sequence[1] == 1);
				result = true;
			return result;
		}
		
		int[] testBoolean = new int[sequence.length-1];
		for(int i=0; i<sequence.length-1; i++)
			testBoolean[i] = sequence[i+1] - sequence[i];
		
		int count = 0;
		for(int i : testBoolean) {
			if(i<0||i>1)
				count++;
			if(count >= 4)
				return result;
        }
        if((count==1 && testBoolean[0] > 1) || (count==1 && testBoolean[0] < 0)) {
            result = true;
            return result;
        }
            
		return result;
	}
}
