package view;

import java.text.ParseException;
import java.util.Scanner;

import model.Boat;
import model.Registry;

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
	public void welcomeWindow() {
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
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
		} else {
			System.out.println("Please input a choice number");
			welcomeWindow();
		}
	}

	/**
	 * Shown if user decided to add new member
	 * 
	 * @throws ParseException
	 */
	public void addMemberWindow() throws ParseException {
		System.out.println("Create new Member...");
		System.out.print("Name\n>");
		// New member can wish to use a username, therefore numbers/characters
		// allowed
		String name = sc.next() + sc.nextLine();
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input)) {
			if (errorHandler.isTwelveDigits(input)) {
				registry.addMember(name, input);
				System.out.println("New member was added!!");
				welcomeWindow();
			} else {
				System.out.println("Personnumber has to be 12 digits!");
				addMemberWindow();
			}
		} else {
			System.out.println("Personnumber has to be integers only!");
			addMemberWindow();
		}
	}

	/**
	 * Window that displays compact list
	 */
	public void viewCompactWindow() {
		System.out.println("Retriving compact list..." + "\n------------------------------");
		// Check if memberList is empty
		if (!registry.isEmptyMembers()) {
			viewCompactList();
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 View Member" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.memberListOptions(Integer.parseInt(input), this);
			} else {
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
		System.out.println("Retriving verbose list..." + "\n------------------------------");
		// Check if memberList is empty
		if (!registry.isEmptyMembers()) {
			viewVerboseList();
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 View Member" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.memberListOptions(Integer.parseInt(input), this);
			} else {
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
		System.out.println("------------------------------" + "\nPlease input the number of the member to view them\n");
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			try {
				viewMember(Integer.parseInt(input));
			} catch (IndexOutOfBoundsException e) {
				System.err.println("Choice not found. Please start over");
				welcomeWindow();
			}
		} else {
			System.out.println("Please input a choice number");
			viewMemberWindow();
		}

	}
	// TODO you probably won't like the last errorhandling xD

	/**
	 * Helper method to print out choices possible when a single member is
	 * viewed
	 * 
	 * @param choice
	 *            member number in the list
	 */
	private void viewMember(int choice) {
		// Set the member user is currently working with
		registry.setCurrentMember(registry.getMemberList().get(choice - 1));
		printMemberInfo();
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Update Member" + "\n 2 Delete Member" + "\n 3 View Boats" + "\n 4 Return ");

		System.out.print("\n>");
		String input = sc.next();
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
	 * Window that is displayed when user is updating member
	 */
	public void updateMemberWindow() {
		System.out.print("Name\n>");
		// New member can wish to use a username, therefore numbers/characters
		// allowed
		String name = sc.next() + sc.nextLine();
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input)) {
			if (errorHandler.isTwelveDigits(input)) {
				try {
					registry.updateMember(name, input);
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
				}
				System.out.println("Member has been updated");
				welcomeWindow();
			} else {
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

	public void viewBoatListWindow() {
		if (!registry.isEmptyBoats()) {
			viewBoatList();
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 View Boat" + "\n 2 Add Boat" + "\n 3 Return ");

			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.viewBoatListOptions(Integer.parseInt(input), this);
			} else {
				System.out.println("Please input a choice number");
				viewBoatListWindow();
			}
		} else {
			System.out.println("No boats added!");
			System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
					+ "\n 1 Add a Boat" + "\n 2 Return ");
			System.out.print("\n>");
			String input = sc.next();
			if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
				consoleOptions.viewBoatListEmptyOptions(Integer.parseInt(input), this);
			} else {
				System.out.println("Please input a choice number");
				viewBoatListWindow();
			}
		}
	}

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
	// TODO not sure what to do here, I think it is throwing no errors

	/**
	 * Displays single boat info
	 */
	public void viewBoat(int choice) {
		registry.setCurrentBoat(registry.getCurrentMember().getBoatList().get(choice - 1));
		printBoat();
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Edit Boat" + "\n 2 Remove Boat" + "\n 3 Return ");
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input) && errorHandler.isOptionInput(input)) {
			consoleOptions.viewBoatOptions(Integer.parseInt(input), this);
		} else {
			System.out.println("Please input a choice number");
			viewBoatWindow();
		}
	}

	public void addBoatWindow() {
		System.out.println("Please input boat type (Sailboat, Motorsailer, Canoe, Other)");
		System.out.print("\n>");
		String type = sc.next();
		if (errorHandler.isValidType(type)) {
			System.out.println("Please input boat size");
			System.out.print("\n>");
			String size = sc.next();
			if (errorHandler.isInteger(size)) {
				registry.addBoat(Boat.boatType.valueOf(type.toLowerCase()), Double.parseDouble(size));
				System.out.println("Boat successfully added!");
				welcomeWindow();
			} else {
				System.out.println("Please input a boat size as an integer!");
				addBoatWindow();
			}
		} else {
			System.out.println("Please chose one of the existing options!");
			addBoatWindow();
		}
	}

	public void editBoatWindow() {
		System.out.println("Please input boat type (Sailboat, Motorsailer, Canoe, Other)");
		System.out.print("\n>");
		String type = sc.next();
		if (errorHandler.isValidType(type)) {
			System.out.println("Please input boat size");
			System.out.print("\n>");
			String size = sc.next();
			if (errorHandler.isInteger(size)) {
				registry.addBoat(Boat.boatType.valueOf(type.toLowerCase()), Double.parseDouble(size));
				System.out.println("Boat successfully modified!");
				viewBoatListWindow();
			} else {
				System.out.println("Please input a boat size as an integer!");
				editBoatWindow();
			}
		} else {
			System.out.println("Please chose one of the existing options!");
			editBoatWindow();
		}
	}

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
		for (int i = 0; i < registry.getBoatList().size(); i++) {
			System.out.println((i + 1) + " Type: " + registry.getCurrentMember().getBoatList().get(i).getType()
					+ " Size: " + registry.getCurrentMember().getBoatList().get(i).getSize());
		}
	}

	private void printBoat() {
		System.out.println(
				"Type: " + registry.getCurrentBoat().getType() + " Size: " + registry.getCurrentBoat().getSize());
	}

	/**
	 * Prints out compact list
	 */
	private void viewCompactList() {
		for (int i = 0; i < registry.getMemberList().size(); i++) {
			System.out.println((i + 1) + " Name: " + registry.getMemberList().get(i).getName() + "\t Id Number: "
					+ registry.getMemberList().get(i).getMemberId() + "\t Number of boats registered: "
					+ registry.getMemberList().get(i).getNumberOfBoats());
		}
	}

	/**
	 * Prints out verbose list
	 */
	private void viewVerboseList() {
		for (int i = 0; i < registry.getMemberList().size(); i++) 
			System.out.println((i + 1) + " Name: " + registry.getMemberList().get(i).getName() + "\t Personal number: "
					+ registry.getMemberList().get(i).getPersonnumber() + "\t Id Number: "
					+ registry.getMemberList().get(i).getMemberId() + "\t Number of boats registered: "
					+ registry.getMemberList().get(i).getNumberOfBoats() + "\t Boats registered: "
					+ registry.getMemberList().get(i).getBoatList().toString());
	}
}
