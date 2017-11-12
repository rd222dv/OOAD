package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import java.text.ParseException;

@XmlRootElement(name = "Member")
@XmlType(propOrder = {"name", "personnumber", "memberId", "numberOfBoats", "boatList"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Member {
	@XmlTransient
	private Boat currentBoat;

	@XmlElement(name = "MemberName")
	private String name;
	@XmlElement(name = "PersonalNum")
	private String personnumber;
	@XmlElement(name = "MemberID")
	private int memberId;
	@XmlElement(name = "BoatNum")
	private int numberOfBoats;
	@XmlElement(name = "BoatList")
	private List<Boat> boatList = new ArrayList<Boat>();

	/**
	 * Member constructor
	 * @param name
	 * @param personnumber
	 * @param memberId
	 * @throws ParseException
	 */
	public Member(String name, String personnumber, int memberId) throws ParseException {
		if (name.length() > 0 && personnumber.length() == 12 && memberId > 0) {
			this.name = name;
			this.personnumber = personnumber;
			this.memberId = memberId;
		}
		else {
			throw new RuntimeException ("Please make sure to input correct parameters!");
		}
	}
	/**
	 * Empty constructor (required by javax.xml)
	 */
	public Member() {
		
	}

	/**
	 * @return name as string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets member name
	 * @param name a string
	 */
	public void setName(String name) {
		if (name.length() > 0) {
			this.name = name;
		}
		else {
			throw new RuntimeException ("Make sure name lenght is more than 0");
		}
	}

	/**
	 * @return personal number as string
	 */
	public String getPersonnumber() {
		return personnumber;
	}
	
	/**
	 * Sets personal number
	 * @param personnumber a string
	 */
	public void setPersonnumber(String personnumber) {
		if (personnumber.length() == 12) {
			this.personnumber = personnumber;
		}
		else {
			throw new RuntimeException ("Make sure personal number is 12 digits long");
		}
	}
	
	/**
	 * @return member ID as int
	 */
	public int getMemberId() {
		return memberId;
	}

	/**
	 * Sets member ID
	 * @param memberId an int
	 */
	public void setMemberId(int memberId) {
		if (memberId > 0) {
			this.memberId = memberId;
		}
		else {
			throw new RuntimeException ("ID cannot be 0 or less!");
		}
	}

	/**
	 * @return The size of arraylist containing boats owned by a member
	 */
	public int getNumberOfBoats() {
		return boatList.size();
	}
	
	/**
	 * Sets the boat that is currently being worked with
	 * @param boat of type model.Boat
	 */
	public void setCurrentBoat (Boat boat) {
		if (boat instanceof model.Boat) {
			this.currentBoat = boat;
		}
		else {
			throw new RuntimeException ("Make sure you are using Boat class from BoatClub.model package");
		}
	}
	
	/**
	 * @return currently worked on boat of type model.Boat
	 */
	public Boat getCurrentBoat () {
		return currentBoat;
	}
	
	/**
	 * @return true if empty
	 */
	public boolean isBoatsEmpty() {
		return boatList.size() == 0;
	}
	
	/**
	 * @return size of the arraylist containing member's boats as int
	 */
	public int getBoatListSize() {
		return boatList.size();
	}
	
	/**
	 * @return Iterable object of boat arraylist
	 */
	public Iterable<Boat> getBoatList() {
		return boatList;
	}
	
	/**
	 * Adds a boat to member's owned boat arraylist
	 * @param type as model.Boat.boatType
	 * @param size as double
	 */
	public void addBoat(Boat.boatType type, double size) {
		int id = boatList.size() + 1;
		boatList.add(new Boat(type, size, id));
	}

	/**
	 * Updates currently set boat with specified parameters
	 * @param currentBoat as model.Boat
	 * @param type as model.Boat.boatType
	 * @param size as double
	 */
	public void updateBoat(Boat currentBoat, Boat.boatType type, double size) {
		int id = currentBoat.getId();
		Boat temp = null;
		// Finds the boat with the specified ID
		for (int i = 0; i < boatList.size(); i++) {
			if (boatList.get(i).getId() == id) {
				temp = boatList.get(i);
			}
		}
		temp.setSize(size);
		temp.setType(type);
	}

	/**
	 * Removes boat, updates boat IDs
	 * @param boat as model.Boat
	 */
	public void removeBoat(Boat boat) {
		int id = boat.getId();
		int temp = 0;
		Boat tempMem = null;
		boatList.remove(boat);
		// Updates other boat IDs to ignore removed boat
		for (int i = id-1; i < boatList.size(); i++) {
			tempMem = boatList.get(i);
			temp = tempMem.getId();
			tempMem.setId(temp - 1);
		}
	}
	
}