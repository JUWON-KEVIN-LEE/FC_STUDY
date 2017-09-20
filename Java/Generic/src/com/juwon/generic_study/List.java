package com.juwon.generic_study;

import java.lang.reflect.Array;

class List<T> {
	int count = 0;
	T[] array;
	
	public List() {
		int capacity = 16;
		array = (T[])new Object[capacity];
	}
	public List(int index) {
		int capacity = index;
		array = (T[])new Object[capacity];
	}
	public T[] ListCapacityAdd() {
		T[] newArray = (T[])new Object[array.length + 32];
		for(int i=0; i<array.length; i++)
			newArray[i] = array[i];
		return newArray;
	}
	//add
	public void add(T t) {
		try {
			array[count] = t;
			Array.set(array, count, t);
			count++;
		} catch (IndexOutOfBoundsException e) {
			Array.set(ListCapacityAdd(), count, t);
			count++;
		}
	}
	//size
	public int size() {
		return count+1;
	}
	//get
	public T get(int index) {
		if(index > count) {
			System.out.println("Exception in thread "+'"'+"main"+'"'+" java.lang.IndexOutOfBoundsException: Index: "+ index +", Size: " + (count+1));
			return null;
		}
		else
			return array[index];
	}
	//remove
	public void remove(int index) {
		if(index > count) {
			System.out.println("Exception in thread "+'"'+"main"+'"'+" java.lang.IndexOutOfBoundsException: Index: "+ index +", Size: " + (count+1));
			return;
		}
		for(int i=index; i<count; i++)
			array[i] = array[i+1];
		array[count] = null;
		count--;
	}
}
