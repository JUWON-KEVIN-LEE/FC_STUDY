package com.juwon.boardProgram;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		View view = new View();
		Controller ctrl = new Controller();
		
		Scanner sc = new Scanner(System.in);
		String command = view.introMessage(sc);
		ctrl.modeSelector(sc, command);
	}
}