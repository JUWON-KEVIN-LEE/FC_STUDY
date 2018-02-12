package com.juwon.test;
import java.util.*;

public class A {
	A a;
	String cc = "A내용";
	B b;
	List<String> list;
	public void init() {
		a = new A();
		B b = new B();
		b.c2 = "B내용바뀜";
		a.cc = b.c2;
		list.add(b.c2);
	}
	public void print() {
		for(String b : list)
			System.out.println(b);
	}
	public void change() {
		cc = "A 내용 바뀜";
		print();
	}
	public static void main(String[] args) {
		A a = new A();
		a.init();
		a.change();
	}
}

class B extends A {
	int c1 = 8;
	String c2 = "내용";
	public String toString() {
		return c2;
	}
}