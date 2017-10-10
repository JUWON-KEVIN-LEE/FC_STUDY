package com.juwon.codefight;

import java.util.*;

class SortByHeight {
	int[] sortByHeight(int[] a) {
	    List<Integer> list = new ArrayList<>();
	    for(int i=0; i<a.length; i++) {
	        if(a[i]>0) {
	            list.add(a[i]); // 
	        }
	    }
	    for(int i=0; i<list.size(); i++) {
	        for(int j=0; j<i; j++) { 
	            if (list.get(i) < list.get(j)) { // 자신[list.get(i)]의 앞에 elements[0 ~ i-1] 검사 
	                list.add(j, list.get(i)); // 그 자리에 list.get(i) 를 넣고
	                list.remove(i+1); // 이전의 자신을 삭제..
	                break; // 다음 list.get(i+1) 에 대해서 동일 작업
	            }
	        }
	    }
	    
	    int count = 0;
	    for(int v=0; v<a.length; v++) {
	        if(a[v] > 0) { // 배열에 element 가 0  보다 크면
	            a[v] = list.get(count); // 그 자리에 순서대로 list 값들을 넣어준다.
	            count++;
	        }
	    }
	    return a; // 0 이하의 값들을 건드리지 않고, 0 보다 큰 값들만 정렬...
	}
}
