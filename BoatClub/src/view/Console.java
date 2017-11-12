package view;

import java.text.ParseException;
import java.util.Scanner;

import model.Boat;
import model.Member;
import model.Registry;

public class Console {

	Scanner sc = new Scanner(System.in);
	ConsoleOptions consoleOptions = new ConsoleOptions();
	ErrorChecker errorHandler = new ErrorChecker();
	private Registry registry;
	private int currentChoice = 0;

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
	public void welcomeWindow() {
		//Presents user with options possible while in main menu
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Create a new member" + "\n 2 View a compact list of members "
				+ "\n 3 View a verbose list of members");
		System.out.print("\n>");
		//Takes input that is what user chose to do next
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			try {
				consoleOptions.welcomeOptions(Integer.parseInt(input), this);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Please input a choice number");
			welcomeWindow();
		}
	}
	/**
	 * Since add and update methods are so similar, they are merged into one method.
	 * This method handles displaying text and taking users input when adding or modifying 
	 * boat club member.
	 * @param choice 1 - add, 2 - update
	 * @throws ParseException
	 */
	public void updateOrAddMember(int choice) throws ParseException {
		if (choice == 1) {
			System.out.println("Create new Member...");
		}
		else if (choice == 2) {
			System.out.println("Edit Member...");
		}
		System.out.print("Name\n>");
		// New member can wish to use a username, therefore numbers/characters
		// allowed
		String name = sc.next() + sc.nextLine();
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String input = sc.next();
		//Checks if personal number is correct (12 digits, all numbers)
		if (errorHandler.isInteger(input)) {
			if (errorHandler.isTwelveDigits(input)) {
				//Adds new member, returns to main menu
				if (choice == 1) {
					registry.addMember(name, input);
					System.out.println("New member was added!!");
				}
				else if (choice == 2) {
					registry.updateMember(name, input);
					System.out.println("Member was successfully updated!");
				}
				welcomeWindow();
			} else {
				System.out.println("Personnumber has to be 12 digits!");
				updateOrAddMember(choice);
			}
		} else {
			System.out.println("Personnumber has to be integers only!");
			updateOrAddMember(choice);
		}
	}
	/**
	 * Displays either verbose or compact list, also gives menu options.
	 * @param choice 2 - compact, 3 - verbose
	 */
	public void viewMembers(int choice) {
		// Check if memberList is empty
		if (!registry.isEmptyMembers()) {
			if (choice == 2) {
				System.out.println("Retriving compact list..." + "\n------------------------------");
				//Prints compact list 
				viewCompactList();
			}
			else if (choice == 3){
				System.out.println("Retriving verbose list..." + "\n------------------------------");
				//Prints out verbose list
				viewVerboseList();
			}
			//Presents user with options possible while viewing the list
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 View Member" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.memberListOptions(Integer.parseInt(input), this);
			} else {
				System.out.println("Please input a choice number");
				viewMembers(choice);
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
		System.out.println("------------------------------" + "\nPlease input the number of the member to view them\n");
		System.out.print("\n>");
		//Checks if chosen option is a number
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			//Catches exception is user chose member number that doesn't exist
			try {
				viewMember(Integer.parseInt(input));
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Choice not found. Please start over");
				welcomeWindow();
			}
		} else {
			System.out.println("Please input a choice number");
			viewMemberWindow();
		}

	}

	/**
	 * Helper method to print out choices possible when a single member is
	 * viewed
	 * 
	 * @param choice
	 *            member number in the list
	 */
	private void viewMember(int choice) {
		currentChoice = choice;
		// Set the member user is currently working with
		registry.setCurrentMember(registry.getSelectedMember(choice));
		//Member information
		printMemberInfo();
		//Presents user with options possible while viewing single member information
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Update Member" + "\n 2 Delete Member" + "\n 3 View Boats" + "\n 4 Return ");

		System.out.print("\n>");
		String input = sc.next();
		//Check if chosen option is correct
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			consoleOptions.memberOptions(Integer.parseInt(input), this);
		} else {
			System.out.println("Please input a choice number");
			viewMember(choice);
		}
	}

	/**
	 * Helper method to print member information
	 */
	private void printMemberInfo() {
		System.out.println("\nMember ID: " + registry.getCurrentMember().getMemberId());
		System.out.println("Name: " + registry.getCurrentMember().getName() + "\t Personal number: "
				+ registry.getCurrentMember().getPersonnumber() + "\t Number of Boats: "
				+ registry.getCurrentMember().getNumberOfBoats());
		viewBoatList();
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
	 * Displayed when person chooses to view a list of boats
	 */
	public void viewBoatListWindow() {
		//Output to user is different when member has no boats, so checks if member has any boats
		if (!registry.isEmptyBoats()) {
			viewBoatList();
			//Presents user with options possible while viewing boat list
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 View Boat" + "\n 2 Add Boat" + "\n 3 Return ");

			System.out.print("\n>");
			String input = sc.next();
			//Checks if choice was correct
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.viewBoatListOptions(Integer.parseInt(input), this);
			} else {
				System.out.println("Please input a choice number");
				viewBoatListWindow();
			}
		} 
		// Member owns no boats, therefore options menu has to be different too
		else {
			System.out.println("No boats added!");
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 Add a Boat" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			//Checks if choice was correct
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.viewBoatListEmptyOptions(Integer.parseInt(input), this);
			} else {
				System.out.println("Please input a choice number");
				viewBoatListWindow();
			}
		}
	}
	/**
	 * Displayed when person decided to view information about a boat
	 */
	public void viewBoatWindow() {
		System.out.println("------------------------------" + "\nPlease input the number of the boat to view it\n");
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			viewBoat(Integer.parseInt(input));
		} else {
			System.out.println("Please input a choice number");
			viewBoatWindow();
		}
	}
	/**
	 * Displays single boat information
	 */
	public void viewBoat(int choice) {
		//Sets which is the current boat being worked with so registry knows
		registry.setCurrentBoat(registry.getSelectedBoat(choice));
		//Boat information
		printBoat();
		//Presents user with options possible while viewing single boat information
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Edit Boat" + "\n 2 Remove Boat" + "\n 3 Return ");
		System.out.print("\n>");
		String input = sc.next();
		//Checks if chosen option is correct
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			consoleOptions.viewBoatOptions(Integer.parseInt(input), this);
		} else {
			System.out.println("Please input a choice number");
			viewBoatWindow();
		}
	}
	/**
	 * Handles adding and editing boats, since code is so similar, it's merged into
	 * a single method.
	 * @param choice 1 for edit boat, 2 for add boat
	 */
	public void addOrEditBoatWindow(int choice) {
		System.out.println("Please input boat type (Sailboat, Motorsailer, Canoe, Other)");
		System.out.print("\n>");
		//Takes input of boat type
		String type = sc.next();
		//Checks if selected type is valid
		if (errorHandler.isValidType(type)) {
			System.out.println("Please input boat size");
			System.out.print("\n>");
			//Takes input of boat size
			String size = sc.next();
			//Checks if boat size input was valid
			if (errorHandler.isInteger(size)) {
				//Adds boat, prints out successful message, goes back to main menu
				if (choice == 1) {
					registry.updateBoat(Boat.boatType.valueOf(type.toUpperCase()), Double.parseDouble(size));
					System.out.println("Boat successfully modified!");
				}
				else {
					registry.addBoat(Boat.boatType.valueOf(type.toUpperCase()), Double.parseDouble(size));
					System.out.println("Boat successfully added!");
				}
				viewMember(currentChoice);
			} else {
				//Handles incorrect size input, restarts add boat
				System.out.println("Please input a boat size as an integer!");
				addOrEditBoatWindow(choice);
			}
		} else {
			//Handles incorrect type input, restarts add boat
			System.out.println("Please chose one of the existing options!");
			addOrEditBoatWindow(choice);
		}
	}

	/**
	 * Removes selected boat, goes back to boat list and presents user with other possible options
	 */
	public void removeBoatWindow() {
		registry.removeBoat();
		System.out.println("Boat sucessfully removed!");
		viewBoatListWindow();

	}

	/**
	 * Helper method to print boat list
	 */
	private void viewBoatList() {
		System.out.println("Boat list: ");
		int i = 0;
		for (Boat b : registry.getBoatList()) {
			System.out.println((i + 1) + " " + toString(b));
			i++;
		}
	}

	/**
	 * Prints boat information
	 */
	private void printBoat() {
		System.out.println(toString(registry.getCurrentBoat()));
	}

	/**
	 * Prints out compact list
	 */
	private void viewCompactList() {
		int i = 0;
		for (Member m : registry.getMemberList()) {
			System.out.println((i + 1) + " Name: " + m.getName() + "\t Id Number: "
					+ m.getMemberId() + "\t Number of boats registered: "
					+ m.getNumberOfBoats());
			i++;
		}
	}

	/**
	 * Prints out verbose list
	 */
	private void viewVerboseList() {
		int i = 0;
		for (Member m : registry.getMemberList()) {
			System.out.println((i + 1) + " Name: " + m.getName() + "\t Personal number: "
					+ m.getPersonnumber() + "\t Id Number: "
					+ m.getMemberId() + "\t Number of boats registered: "
					+ m.getNumberOfBoats() + "\t Boats registered: "
					+ getBoatList(m));
			i++;
		}
	}
	
	/**
	 * Prints out nice looking boat information
	 * @param b boat to have information printed out about
	 * @return nicely looking string
	 */
	private String toString(Boat b){
        StringBuilder sb = new StringBuilder();
        sb.append("Type: " + b.getType() + " - Size: " + b.getSize() + " - ID: " + b.getId());
        return sb.toString();
    }
	
	/**
	 * Prints out nice looking boat list
	 * @param m member that owns boats
	 * @return nicely looking string
	 */
	private String getBoatList (Member m) {
		StringBuilder sb = new StringBuilder();
		registry.setCurrentMember(m);
		for (Boat b : registry.getBoatList()) {
			// Boat list contained only one boat, comma/dot is not needed
			if (m.getBoatListSize() == 1) {
				sb.append(toString(b));
			}
			else {
					sb.append(toString(b) + " ");
			}
		}
		return sb.toString();
	}
}
