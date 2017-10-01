package view;

import java.text.ParseException;
import java.util.Scanner;

import model.Boat;
import model.Boat.boatType;
import model.Member;
import model.Registry;
import utils.ErrorChecker;

public class Console {

	Scanner sc = new Scanner(System.in);
	ConsoleOptions consoleOptions = new ConsoleOptions();
	ErrorChecker errorHandler = new ErrorChecker();
	private Registry registry;
	private int selection;
	private double in;

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
		System.out.println("\n------------------------------" 
				+ "\nSelect one of the following choices:"
				+ "\n 1 Create a new member" + "\n 2 View a compact list of members "
				+ "\n 3 View a verbose list of members");
		System.out.print("\n>");
		String input = sc.next();
		if (errorHandler.isInteger(input)) {
			consoleOptions.welcomeOptions(Integer.parseInt(input), this);
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
			int perNum = sc.nextInt();
			if (perNum+"".length() == 12) {
				registry.addMember(name, Integer.parseInt(input));
				System.out.println("New member was added!!");
			}
		}
		else {
			System.out.println("Personnumber has to be 12 digits and integers only!");
			addMemberWindow();
		}
	}
	
	public void viewCompactWindow() {
		System.out.println("Retriving compact list..." + "\n------------------------------");
		viewCompactList();
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 View Member" + "\n 2 Return ");
		String input = sc.next();
		if (errorHandler.isInteger(input)) {
			consoleOptions.memberListOptions(Integer.parseInt(input), this);
		}
		else {
			System.out.println("Please input a choice number");
			viewCompactWindow();
		}
	}
	
	public void viewMemberWindow() {
		System.out.println("------------------------------" + "\nPlease input the number by the member to view them\n");

	}

	public void memberOptions() {
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 View Member" + "\n 2 return ");

		selection = sc.nextInt();
		Member member;

		// Establishing choice
		if (selection == 1) {
			System.out.println("------------------------------" + "\nTo view the Member...\n");
			member = getChosenMember();
			showSelectedMember(member);
			viewMember(member);
		} else if (selection == 2) {
			System.out.println("Return...");
			welcomeOptions();
		} else
			System.err.println("Invalid choice! Try again ");

	}

	public void viewMember(Member member) {
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Update Member" + "\n 2 Delete Member" + "\n 3 View Boat details" + "\n 4 return ");

		selection = sc.nextInt();
		Boat boat;

		// Establishing choice
		if (selection == 1) {
			System.out.println("update Member...");
			updateMember(member, getNameFromInput(), getPersonnumberFromInput());
			memberOptions();
		} else if (selection == 2) {
			System.out.println("To delete the Member...\n");
			member = getChosenMember();
			removeMember(member);
			welcomeOptions();
		} else if (selection == 3) {
			System.out.println("view Boat details...");
			boat = getChosenBoat(member);
			viewBoatDetails(member, boat);
		} else if (selection == 4) {
			System.out.println("return...");
			memberOptions();
		} else
			System.err.println("Invalid choice! Try again ");

	}

	public void viewBoatDetails(Member member, Boat boat) {
		System.out.println("\n------------------------------" + "\nSelect one of the following choices:"
				+ "\n 1 Add a Boat" + "\n 2 Edit Boat" + "\n 3 Remove Boat" + "\n 4 return ");

		selection = sc.nextInt();

		if (selection == 1) {
			System.out.println("Add Boat...");
			addBoat(member, getTypeFromInput(), getLengthFromInput());
			// TODO
		} else if (selection == 2) {
			System.out.println("Edit Boat...");
			// TODO
		} else if (selection == 3) {
			System.out.println("Remove Boat...");
			// TODO
		} else if (selection == 4) {
			System.out.println("return...");

		} else
			System.err.println("Invalid choice! Try again ");
	}

	public void updateMember(Member member, String name, String personnumber) {
		try {
			registry.updateMember(member, name, personnumber);
			System.out.println("Member Info has been updated");
		} catch (ParseException e) {
			System.err.println("Incorrect something"); // FIXME Errorhandling
		}
	}

	public void removeMember(Member member) {
		registry.removeMember(member);
		System.out.println("\nMember deleted!!" + "\n------------------------------");
	}

	public Member getChosenMember() {
		if (registry.getMemberList().size() == 0) {
			System.err.println("No members in List");
			welcomeOptions();
		}
		viewCompactList();
		System.out.print("\n...Enter the Member's ID\n>");
		selection = sc.nextInt();

		// if wrong Input DO something FIXME

		return registry.getMemberList().get(selection-1);
	}

	public void showSelectedMember(Member selectedMember) {
		System.out.println("\nMember ID: " + selectedMember.getMemberId());
		System.out.printf("Name: " + selectedMember.getName() + "\t Personal number: "
				+ selectedMember.getPersonnumber() + "\t Number of Boats: " + selectedMember.getNumberOfBoats());
		if (selectedMember.getNumberOfBoats() > 0) {
			for (Boat boat : selectedMember.getBoatList())
				System.out.println("\nBoat Type: " + boat.getType() + "\t Boat Length: " + boat.getSize());
		}
	}

	/**
	 * List methods
	 */

	public void viewCompactList() {
		//Members array is never a null as it is initiated at the beginning
		if (registry.getMemberList().size() == 0)
			System.out.println("No members found");
		else {
			for (int i = 0; i < registry.getMemberList().size(); i++) {
				System.out.println("Name: " + registry.getMemberList().get(i).getName() + "\t Id Number: "
						+ registry.getMemberList().get(i).getMemberId() + "\t Number of boats registered: "
						+ registry.getMemberList().get(i).getNumberOfBoats());
			}
		}

	}

	public void viewVerboseList() {
		if (registry.getMemberList().size() == 0)
			System.err.println("No members found");
		else {
			for (int i = 0; i < registry.getMemberList().size(); i++) {
				System.out.println("Name: " + registry.getMemberList().get(i).getName() + "\t Personal number: "
						+ registry.getMemberList().get(i).getPersonnumber() + "\t Id Number: "
						+ registry.getMemberList().get(i).getMemberId() + "\t Boats registered: "
						+ registry.getMemberList().get(i).getBoatList());
			}
		}
	}

	/**
	 * Boat methods
	 */

	public void addBoat(Member member, boatType type, double boatLength) {
		try {
			member.addBoat(type, boatLength);
			System.out.println("New boat was added!!");
		} catch (Exception e) {
			System.err.println("Incorrect something"); // FIXME Errorhandling
		}
	}

	public void updateBoat(Member member, boatType type, double boatLength, Boat boat) {
		try {
			member.updateBoat(boat, type, boatLength);
			System.out.println("Boat Info has been updated");
		} catch (Exception e) {
			System.err.println("Incorrect something"); // FIXME Errorhandling
		}
	}

	public void removeBoat(Member member, Boat boat) {
		member.removeBoat(boat);
		System.out.println("Boat deleted!!");
	}

	public String getPersonnumberFromInput() {
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String in = sc.next();
		return in;
	}

	private Boat getChosenBoat(Member member) {
		//TODO
		return null;
	}

	private boatType getTypeFromInput() {
		//TODO
		return null;
	}

	private double getLengthFromInput() { //FIXME Errorhandling
		System.out.print("Enter Length in meters\n>");
		in = sc.nextDouble();
		return in;
	}

}
