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
	            if (list.get(i) < list.get(j)) { // �ڽ�[list.get(i)]�� �տ� elements[0 ~ i-1] �˻� 
	                list.add(j, list.get(i)); // �� �ڸ��� list.get(i) �� �ְ�
	                list.remove(i+1); // ������ �ڽ��� ����..
	                break; // ���� list.get(i+1) �� ���ؼ� ���� �۾�
	            }
	        }
	    }
	    
	    int count = 0;
	    for(int v=0; v<a.length; v++) {
	        if(a[v] > 0) { // �迭�� element �� 0  ���� ũ��
	            a[v] = list.get(count); // �� �ڸ��� ������� list ������ �־��ش�.
	            count++;
	        }
	    }
	    return a; // 0 ������ ������ �ǵ帮�� �ʰ�, 0 ���� ū ���鸸 ����...
	}
}
