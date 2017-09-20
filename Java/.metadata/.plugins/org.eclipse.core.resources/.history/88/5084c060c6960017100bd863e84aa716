package com.juwon.interface_study;

import com.juwon.interface_study2.C;
import com.juwon.interface_study2.InterfaceC;

public class A implements InterfaceC {
	public void process() {
		C c = new C();
		c.process(this); // InterfaceC ifc = [this 와 new A() 같다]; 즉, memory 에 올라가면서 초기화된다.
	}
	
	public void afterProcess() {
		System.out.println("Complete!");
	}

	@Override
	public void callback() {
		afterProcess();
	}
}
