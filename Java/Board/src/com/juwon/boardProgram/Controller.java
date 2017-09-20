package com.juwon.boardProgram;

import java.util.*;

public class Controller {
	View view = new View();
	Model model = new Model();
	Memo memo = new Memo();
	
	public void modeSelector(Scanner sc, String command) {
		while(!(command=="x")) {
			switch(command) {
			case "w":
				String[] memoArray = view.writeMemo();
				memo = model.writeMemo(memoArray);
				view.printMemo(memo);
				view.workCompleteAndModeSelectMessage();
				break;
			case "r":
				memo = model.readMemo(view.readMessage());
				view.printMemo(memo);
				view.workCompleteAndModeSelectMessage();
				break;
			case "u":
				int updateIndex = view.updateMessage(sc);
				Memo memo = model.readMemo(updateIndex);
				view.printMemo(memo);
				model.updateMemo(view.updateMemoReturn(), updateIndex);
				view.workCompleteAndModeSelectMessage();
				break;
			case "d":
				int deleteIndex = view.deleteMessage(sc);
				model.deleteMemo(deleteIndex);
				model.updateIndex(deleteIndex);
				view.workCompleteAndModeSelectMessage();
				break;
			case "l":
				view.listPrint(model.getMemoList());
				view.workCompleteAndModeSelectMessage();
				break;
			case "x":
				view.outroMessage();
				break;
			default:
				view.wrongInputMessage();
				break;
			}
			command = sc.nextLine();
		}
		return;
	}
}
