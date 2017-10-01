package view;

import java.text.ParseException;
import java.util.Scanner;

import model.Boat;
import model.Registry;
import utils.ErrorChecker;

public class Console {

	Scanner sc = new Scanner(System.in);
	ConsoleOptions consoleOptions = new ConsoleOptions();
	ErrorChecker errorHandler = new ErrorChecker();
	private Registry registry;

	public Console() {
		registry = new Registry();
	}

	public void welcomeMessage() {
		System.out.println("Welcome to the Jolly Pirate! ");
		welcomeWindow();
	}

	/**
	 * Start of program
	 */
	public void welcomeWindow(){
		System.out.println("\n------------------------------" 
				+ "\nSelect one of the following choices:"
				+ "\n 1 Create a new member" + "\n 2 View a compact list of members "
				+ "\n 3 View a verbose list of members");
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			try {
				consoleOptions.welcomeOptions(Integer.parseInt(input), this);
			} catch (NumberFormatException | ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Please input a choice number");
			welcomeWindow();
		}
	}
	
	/**
	 * Shown if user decided to add new member
	 * @throws ParseException 
	 */
	public void addMemberWindow() throws ParseException {
		System.out.println("Create new Member...");
		System.out.print("Name\n>");
		//New member can wish to use a username, therefore numbers/characters allowed
		String name = sc.next() + sc.nextLine();
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input)) {
			double perNum = Double.parseDouble(input);
			if (errorHandler.isTwelveDigits(perNum+"")) {
				registry.addMember(name, Integer.parseInt(input));
				System.out.println("New member was added!!");
			}
			else {
				System.out.println("Personnumber has to be 12 digits!");
				addMemberWindow();
			}
		}
		else {
			System.out.println("Personnumber has to be integers only!");
			addMemberWindow();
		}
	}
	
	/**
	 * Window that displays compact list
	 */
	public void viewCompactWindow() {
		System.out.println("Retriving compact list..." 
				+ "\n------------------------------");
		// Check if memberList is empty
		if (!registry.isEmptyMembers()) {
			viewCompactList();
			System.out.println("\n------------------------------" 
					+ "\nSelect one of the following choices:"
					+ "\n 1 View Member" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.memberListOptions(Integer.parseInt(input), this);
			}
			else {
				System.out.println("Please input a choice number");
				viewCompactWindow();
			}
		}
		// Member list was empty
		else {
			System.out.println("No members found");
			welcomeWindow();
		}
	}
	/**
	 * Window for verbose list
	 */
	public void viewVerboseWindow() {
		System.out.println("Retriving verbose list..." 
				+ "\n------------------------------");
		// Check if memberList is empty
		if (!registry.isEmptyMembers()) {
			viewVerboseList();
			System.out.println("\n------------------------------" 
					+ "\nSelect one of the following choices:"
					+ "\n 1 View Member" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.memberListOptions(Integer.parseInt(input), this);
			}
			else {
				System.out.println("Please input a choice number");
				viewVerboseList();
			}
		}
		// Member list was empty
		else {
			System.out.println("No members found");
			welcomeWindow();
		}
	}
	
	/**
	 * Window that displays ONE member information
	 */
	public void viewMemberWindow() {
		System.out.println("------------------------------" 
				+ "\nPlease input the number of the member to view them\n");
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			viewMember(Integer.parseInt(input));
		}
		//TODO
			
	}
	
	/**
	 * Helper method to print out choices possible when a single member is viewed
	 * @param choice member number in the list
	 */
	private void viewMember(int choice) {
		// Set the member user is currently working with
		registry.setCurrentMember(registry.getMemberList().get(choice-1));
		printMemberInfo();
		System.out.println("\n------------------------------" 
				+ "\nSelect one of the following choices:"
				+ "\n 1 Update Member" + "\n 2 Delete Member" 
				+ "\n 3 View Boats" + "\n 4 return ");
		
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			consoleOptions.memberOptions(Integer.parseInt(input), this);
		}
		else {
			System.out.println("Please input a choice number");
			viewMember(choice);
		}
	}
	
	/**
	 * Helper method to print member information
	 */
	private void printMemberInfo() {
		System.out.println("\nMember ID: " + registry.getCurrentMember().getMemberId());
		System.out.printf("Name: " + registry.getCurrentMember().getName() 
				+ "\t Personal number: " + (int) registry.getCurrentMember().getPersonnumber() 
				+ "\t Number of Boats: " + registry.getCurrentMember().getNumberOfBoats());
		viewBoatList();
	}
	
	/**
	 * Window that is displayed when user is updating member
	 */
	public void updateMemberWindow() {
		System.out.println("Update member...");
		System.out.print("Name\n>");
		//New member can wish to use a username, therefore numbers/characters allowed
		String name = sc.next() + sc.nextLine();
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input)) {
			double perNum = Double.parseDouble(input);
			if (errorHandler.isTwelveDigits(perNum+"")) {
				try {
					registry.updateMember(name, Integer.parseInt(input));
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
				}
				System.out.println("Member has been updated");
				viewMemberWindow();
			}
			else {
				System.out.println("Personnumber has to be 12 digits!");
				updateMemberWindow();
			}
		}
	}
	
	/**
	 * Displayed when member is removed
	 */
	public void removeMemberWindow() {
		registry.removeMember();
		System.out.println("Member successfully removed!");
		welcomeWindow();
	}
	
	/**
	 * Displays boat list
	 */
	public void viewBoatsWindow() {
		if (!registry.isEmptyBoats()) {
			viewBoatList();
			System.out.println("\n------------------------------" 
					+ "\nSelect one of the following choices:"
					+ "\n 1 Add a Boat" + "\n 2 Edit Boat" 
					+ "\n 3 Remove Boat" + "\n 4 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.viewBoatOptions(Integer.parseInt(input), this);
			}
			else {
				System.out.println("Please input a choice number");
				viewBoatsWindow();
			}
		}
		else {
			System.out.println("No boats added!");
			System.out.println("\n------------------------------" 
					+ "\nSelect one of the following choices:"
					+ "\n 1 Add a Boat" + "\n 2 return ");
		}
		
		
	}
	
	public void addBoatWindow() {
		System.out.println("Please input boat type (Sailboat(s), Motorsailer(m), Canoe(c), Other(o))");
		System.out.print("\n>");
		String type = sc.next();
		if (errorHandler.isValidType(type)) {
			System.out.println("Please input boat size");
			System.out.print("\n>");
			String size = sc.next();
			if (errorHandler.isInteger(size)) {
				registry.addBoat(Boat.boatType.valueOf(type), Double.parseDouble(size));
				System.out.println("Boat successfully added!");
				viewBoatsWindow();
			}
			else {
				System.out.println("Please input a boat size as an integer!");
				addBoatWindow();
			}
		}
		else {
			System.out.println("Please chose one of the existing options!");
			addBoatWindow();
		}
	}
	
	public void editBoatWindow() {
		
	}
	/**
	 * Helper method to print boat list
	 */
	private void viewBoatList() {
		for (int i = 0; i < registry.getCurrentMember().getBoatList().size(); i++) {
			System.out.println((i+1) 
					+ " Type: " + registry.getCurrentMember().getBoatList().get(i).getType() 
					+ "\nSize: " + registry.getCurrentMember().getBoatList().get(i).getSize() + "\n"); 
		}
	}

	/**
	 * Prints out compact list
	 */
	private void viewCompactList() {
		for (int i = 0; i < registry.getMemberList().size(); i++) {
			System.out.println((i+1)+ " Name: " + registry.getMemberList().get(i).getName() + "\t Id Number: "
					+ registry.getMemberList().get(i).getMemberId() + "\t Number of boats registered: "
					+ registry.getMemberList().get(i).getNumberOfBoats());
		}
	}

	/**
	 * Prints out verbose list
	 */
	//TODO doesn't list boats, pls fix
	private void viewVerboseList() {
		for (int i = 0; i < registry.getMemberList().size(); i++) {
				System.out.println((i+1)+ " Name: " + registry.getMemberList().get(i).getName() + "\t Personal number: "
						+ (int) registry.getMemberList().get(i).getPersonnumber() + "\t Id Number: "
						+ registry.getMemberList().get(i).getMemberId() + "\t Boats registered: "
						+ registry.getMemberList().get(i).getBoatList());
		}
	}

}
