package model;

import java.io.File;
import java.text.ParseException;
import java.util.Iterator;

public class Registry {
	
	private MemberCatalog members = new MemberCatalog();
	private Member currentMember = new Member();
	private MembersDAO dao = new MembersDAO();
	private File file;
	
	/**
	 * On registry initialization database is attempted to be loaded. If database.xml exists
	 * MemberCatalog is created using information from the file, if database file does not exist,
	 * empty file is created and is ready to save information.
	 */
	public Registry(){
		file = new File("database.xml");
		try {
			// Checks if file exists. If not, xml with proper tags should be created.
			if (!file.exists()) {
				dao.saveRegistry(members, file);
			}
			members = dao.loadRegistry(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Sets current member
	 * @param member Member object that is being worked with
	 */
	public void setCurrentMember(Member member) {
		if (member instanceof model.Member) {
			this.currentMember = member;
		}
		else {
			throw new RuntimeException ("Make sure to use Member from BoatClub.model package");
		}
	}
	/**
	 * @returnMember object that is being worked with
	 */
	public Member getCurrentMember() {
		return currentMember;
	}
	
	/**
	 * Gets member by their ID
	 * @param choice ID as int
	 * @return member object as model.Member
	 */
	public Member getSelectedMember(int choice) {
		Member temp = null;
		if (choice <= members.membersSize()) {
			for (Member m : members.getMemberList()) {
				if (m.getMemberId() == choice) {
					temp = m;
				}
			}
		}
		else {
			throw new RuntimeException("Choice is incorret, such member doesn't exist!");
		}
		return temp;
	}
	
	/**
	 * Gets boat by their ID
	 * @param choice ID as int
	 * @return boat object as model.Boat
	 */
	public Boat getSelectedBoat(int choice) {
		Boat temp = null;
		if (choice <= currentMember.getBoatListSize()) {
			for (Boat b : currentMember.getBoatList()) {
				if (b.getId() == choice) {
					temp = b;
				}
			}
		}
		else {
			throw new RuntimeException("Choice is incorret, such boat doesn't exist!");
		}
		return temp;
	}
	/**
	 * @return Iterable object of members list from MembersCatalog
	 */
	public Iterable<Member> getMemberList() {
		return members.getMemberList();
	}
	/**
	 * @return Iterable object of boat list from Member 
	 */
	public Iterable<Boat> getBoatList() {
		return currentMember.getBoatList();
	}
	
	public int getBoatListSize() {
		return currentMember.getBoatListSize();
	}
	
	/**
	 * @return true if empty
	 */
	public boolean isEmptyMembers() {
		return members.isMembersEmpty();
	}
	
	/**
	 * @return true if empty
	 */
	public boolean isEmptyBoats() {
		return currentMember.isBoatsEmpty();
	}
	
	/**
	 * Sets current boat that is being worked on
	 * @param boat as model.Boat
	 */
	public void setCurrentBoat(Boat boat) {
		if (boat instanceof model.Boat) {
			currentMember.setCurrentBoat(boat);
		}
		else {
			throw new RuntimeException("Make sure to use Boat class of BoatClub.model package");
		}
	}
	
	/**
	 * @return Boat object as model.Boat
	 */
	public Boat getCurrentBoat() {
		return currentMember.getCurrentBoat();
	}
	/**
	 * Adds member, writes new information to database.
	 * @param name new member name
	 * @param personnumber new member person number
	 * @throws ParseException
	 */
	public void addMember(String name, String personnumber) throws ParseException {
		members.addMember(name, personnumber);
		setCurrentMember(getLastMember());
		try {
			dao.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Adds boat to currently selected member, writes new information to database.
	 * @param type Type of a boat
	 * @param size Size of a boat
	 */
	public void addBoat(Boat.boatType type, double size) {
		currentMember.addBoat(type, size);
		try {
			dao.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Updates currently selected boat, writes new information to database
	 * @param type New type of a boat
	 * @param size New size of a boat
	 */
	public void updateBoat(Boat.boatType type, double size) {
		Boat currentBoat = currentMember.getCurrentBoat();
		currentMember.updateBoat(currentBoat, type, size);
		try {
			dao.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Removes currently selected boat, writes to database.
	 */
	public void removeBoat () {
		currentMember.removeBoat(currentMember.getCurrentBoat());
		try {
			dao.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Removes currently selected boat, writes to database
	 */
	public void removeMember () {
		members.removeMember(currentMember);
		if (!isEmptyMembers()) {
			currentMember = getLastMember();
		}
		else {
			currentMember = null;
		}
		try {
			dao.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Updates currently selected member, writes to database.
	 * @param name New name of the member
	 * @param personnumber New person number of the member
	 * @throws ParseException
	 */
	public void updateMember (String name, String personnumber) throws ParseException {
		members.updateMember(currentMember, name, personnumber);
		try {
			dao.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper method to get the very last member of members list
	 * @return Last member of member's list
	 */
	private Member getLastMember() {
		Iterator<Member> it = getMemberList().iterator();
		Member lastMember = null;
		while (it.hasNext()) {
			lastMember = it.next();
		}
		
		return lastMember;
	}
}