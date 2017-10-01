package view;

import model.Member;

public class ConsoleOptions {

	public void welcomeOptions(int selection, Console console) {
		if (selection == 1) {
			console.addMemberWindow();
		} else if (selection == 2) {
			console.viewCompactWindow();
		} else if (selection == 3) {
			System.out.println("Retriving verbose list..." + "\n------------------------------");
			viewVerboseList();
			memberOptions();
		} else
			System.err.println("Invalid choice! Chose from 1 to 3!");
	}
	
	
	public void memberListOptions(int selection, Console console) {
		if (selection == 1) {
			console.
		} else if (selection == 2) {
			console.welcomeWindow();
		} else
			System.err.println("Invalid choice! Try again ");

	}
	
	
	
}
