package view;

import java.text.ParseException;


public class ConsoleOptions {

	/**
	 * Deals with user options, decides what action is going to happen next depending 
	 * on what user has chosen. Used in main menu
	 * @param selection users choice on what should happen next
	 * @param console object of Console that is being used
	 * @throws ParseException
	 */
	public void welcomeOptions(int selection, Console console) {
		if (selection == 1) {
			try {
				console.updateOrAddMember(1);
			}catch (ParseException e) {
				
			}
		} else if (selection == 2 || selection == 3) {
			console.viewMembers(selection);
		} else {
			System.out.println("Invalid choice! Chose from 1 to 3!");
			console.welcomeWindow();
		}
	}
	/**
	 * Deals with user options, decides what action is going to happen next depending 
	 * on what user has chosen. Used when displayed member list (both types)
	 * @param selection users choice on what should happen next
	 * @param console object of Console that is being used
	 */
	public void memberListOptions(int selection, Console console) {
		if (selection == 1) {
			console.viewMemberWindow();
		} else if (selection == 2) {
			console.welcomeWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
			console.welcomeWindow();
		}
	}
	/**
	 * Deals with user options, decides what action is going to happen next depending 
	 * on what user has chosen. Used when single member is displayed
	 * @param selection users choice on what should happen next
	 * @param console object of Console that is being used
	 * @throws ParseException 
	 */
	public void memberOptions(int selection, Console console) {
		if (selection == 1) {
			try {
				console.updateOrAddMember(2);
			}catch (ParseException e) {
				
			}
		} else if (selection == 2) {
			console.removeMemberWindow();
		} else if (selection == 3) {
			console.viewBoatListWindow();
		} else if (selection == 4) {
			console.welcomeWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
			console.welcomeWindow();
		}
	}
	
	/**
	 * Deals with user options, decides what action is going to happen next depending 
	 * on what user has chosen. Used when single boat is displayed
	 * @param selection users choice on what should happen next
	 * @param console object of Console that is being used
	 */
	public void viewBoatOptions(int selection, Console console) {
		if (selection == 1) {
			console.addOrEditBoatWindow(1);
		} else if (selection == 2) {
			console.removeBoatWindow();
		} else if (selection == 3) {
			console.welcomeWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
			console.viewBoatListWindow();
		}
	}
	/**
	 * Deals with user options, decides what action is going to happen next depending 
	 * on what user has chosen. Used when boat list is displayed
	 * @param selection users choice on what should happen next
	 * @param console object of Console that is being used
	 */
	public void viewBoatListOptions(int selection, Console console) {
		if (selection == 1) {
			console.viewBoatWindow();
		} else if (selection == 2) {
			console.addOrEditBoatWindow(2);
		} else if (selection == 3) {
			console.viewMemberWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
			console.viewBoatListWindow();
		}
	}
	/**
	 * Deals with user options, decides what action is going to happen next depending 
	 * on what user has chosen. Used when boat list is empty
	 * @param selection users choice on what should happen next
	 * @param console object of Console that is being used
	 */
	public void viewBoatListEmptyOptions(int selection, Console console) {
		if (selection == 1) {
			console.addOrEditBoatWindow(2);
		} else if (selection == 2) {
			console.viewMemberWindow();
		} else {
			System.out.println("Invalid choice! Try again ");
			console.viewBoatListWindow();
		}
	}
}
