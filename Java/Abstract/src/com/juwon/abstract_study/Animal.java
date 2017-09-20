package com.juwon.abstract_study;

interface a { // 강제성을 부여..
	void create();
	void delete();
	void list();
	void update();
	void read();
}

abstract class Animal implements a {
	public abstract String type();
	
	@Override
	public void create() {
	}
	@Override
	public void delete() {
	}
	@Override
	public void list() {
	}
	@Override
	public void update() {
	}
	@Override
	public void read() {
	}
}

class Room extends Animal {
	String type = "포유류";
	public String type() { return type; }
}