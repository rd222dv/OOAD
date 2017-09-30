package view;

import java.text.ParseException;
import java.util.Scanner;

import model.Boat;
import model.Member;
import model.MemberCatalog;
import model.Boat.boatType;

public class Console {

	Scanner sc = new Scanner(System.in);
	private MemberCatalog members;
	private int selection;

	public Console() {
		members = new MemberCatalog();
	}

	// Selection options
	public void welcomeOptions() {
		System.out.println("Welcome to the Jolly Pirate! " + "\n Select one of the following choices:"
				+ "\n 1 to create a new member" + "\n 2 to view a compact list of members "
				+ "\n 3 to view a verbose list of members");
		
		selection = sc.nextInt();
		
		// Establishing choice
		if (selection == 1) {
			System.out.println("Creating new member...");
			addMember(getNameFromInput(), getPersonnumberFromInput());
			welcomeOptions(); 
		} else if (selection == 2) {
			System.out.println("Retriving compact list...");
			viewCompactList();
			welcomeOptions();
		} else if (selection == 3) {
			System.out.println("Retriving verbose list...");
			viewVerboseList();
			welcomeOptions();
		} else
			System.err.println("Invalid choice! Try again ");

	}

	/**
	 * Member methods starting
	 **/
	public void addMember(String name, String personnumber) { //TODO checkers for invalid input needed
		try {
			members.addMember(name, personnumber);
			System.out.println("New member was added!!");
		} catch (ParseException e) {
			System.err.println("Incorrect something"); // FIXME
		}
	}

	public void updateMember(Member member, String name, String personnumber) { 
		try {
			members.updateMember(member, name, personnumber);     //memberList.remove(currentMember);
			System.out.println("Member Info has been updated");   //memberList.add(new Member(name, personnumber, newID));
		} catch (ParseException e) {
			System.err.println("Incorrect something"); // FIXME
		}
	}
	
	public void removeMember(Member member) {
		members.removeMember(member);
		System.out.println("Member deleted!!");
	}
	
	/**
	 * Boat methods starting
	 **/
	
	public void registerBoat(Member member, boatType type, double boatLength) {
		try {
			member.addBoat(type, boatLength);
			System.out.println("New boat was added!!");
		} catch (Exception e) {
			System.err.println("Incorrect something"); // FIXME
		}
	}
	
	public void updateBoat(Member member, boatType type, double boatLength, Boat boat) {
		try {
			member.updateBoat(boat, type, boatLength);             //boatList.remove(currentBoat);
			System.out.println("Boat Info has been updated");      //boatList.add(new Boat(type, size));
		} catch (Exception e) {
			System.err.println("Incorrect something"); // FIXME
		}
	}	
	
	public void removeBoat(Member member, Boat boat) {
		member.removeBoat(boat);
		System.out.println("Boat deleted!!");
	}

	/**
	 * input Methods
	 * @return
	 **/
	
	private String getNameFromInput() {
		System.out.print("Name\n>");
		String in = sc.next() + sc.nextLine();
		return in;
	}

	private String getPersonnumberFromInput() {
		System.out.print("Personnumber in format YYYYMMDDXXXX\n>");
		String in = sc.next();
		return in;
	}
	
	
	public void viewCompactList() { 
		if (members==null)
			System.out.println ("No members found");
		else{
			for (int i = 0; i < members.getMemberList().size(); i++){
				System.out.println("Name: " + members.getMemberList().get(i).getName()
											+"\t Id Number: " + members.getMemberList().get(i).getMemberId() 
											+ "\t Number of boats registered: " + members.getMemberList().get(i).getNumberOfBoats());
			}
		}
		
	}

	public void viewVerboseList (){ 
		if (members==null)
			System.err.println ("No members found");
		else{
			for (int i = 0; i < members.getMemberList().size(); i++){
				System.out.println("Name: " + members.getMemberList().get(i).getName()
						+"\t Personal number: "+ members.getMemberList().get(i).getPersonnumber()
						+"\t Id Number: " + members.getMemberList().get(i).getMemberId() 
						+ "\t Boats registered: " + members.getMemberList().get(i).getBoatList());
			}
		}
	}

}
