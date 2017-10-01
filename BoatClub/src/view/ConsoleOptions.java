package view;

import java.text.ParseException;


public class ConsoleOptions {

	public void welcomeOptions(int selection, Console console) throws ParseException {
		if (selection == 1) {
			console.addMemberWindow();
		} else if (selection == 2) {
			console.viewCompactWindow();
		} else if (selection == 3) {
			console.viewVerboseWindow();
		} else
			System.out.println("Invalid choice! Chose from 1 to 3!");
	}
	
	public void memberListOptions(int selection, Console console) {
		if (selection == 1) {
			console.viewMemberWindow();
		} else if (selection == 2) {
			console.welcomeWindow();
		} else
			System.out.println("Invalid choice! Try again ");
	}
	
	public void memberOptions(int selection, Console console) {
		if (selection == 1) {
			console.updateMemberWindow();
		} else if (selection == 2) {
			console.removeMemberWindow();
		} else if (selection == 3) {
			console.viewBoatListWindow();
		} else if (selection == 4) {
			console.viewMemberWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
		}
	}
	
	public void viewBoatOptions(int selection, Console console) {
		if (selection == 1) {
			console.editBoatWindow();
		} else if (selection == 2) {
			console.removeBoatWindow();
		} else if (selection == 3) {
			console.viewMemberWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
		}
	}
	
	public void viewBoatListOptions(int selection, Console console) {
		if (selection == 1) {
			console.viewBoatWindow();
		} else if (selection == 2) {
			console.addBoatWindow();
		} else if (selection == 3) {
			console.viewMemberWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
		}
	}
	
	public void viewBoatListEmptyOptions(int selection, Console console) {
		if (selection == 1) {
			console.addBoatWindow();
		} else if (selection == 2) {
			console.viewMemberWindow();;
		} else {
			System.out.println("Invalid choice! Try again ");
		}
	}
}
