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

	public Member(String name, String personnumber, int memberId) throws ParseException {
		this.name = name;
		this.personnumber = personnumber;
		this.memberId = memberId;	
	}
	
	public Member() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonnumber() {
		return personnumber;
	}

	public void setPersonnumber(String personnumber) {
		this.personnumber = personnumber;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getNumberOfBoats() {
		return boatList.size();
	}

	public void setNumberOfBoats(int numberOfBoats) {
		this.numberOfBoats = numberOfBoats;
	}
	
	public void setCurrentBoat (Boat boat) {
		this.currentBoat = boat;
	}
	
	public Boat getCurrentBoat () {
		return currentBoat;
	}
	
	public boolean isBoatsEmpty() {
		return boatList.size() == 0;
	}
	
	public int getBoatListSize() {
		return boatList.size();
	}
	
	public Iterable<Boat> getBoatList() {
		return boatList;
	}
	
	public void addBoat(Boat.boatType type, double size) {
		int id = boatList.size() + 1;
		boatList.add(new Boat(type, size, id));
	}

	public void updateBoat(Boat currentBoat, Boat.boatType type, double size) {
		int id = currentBoat.getId();
		Boat temp = null;
		for (int i = 0; i < boatList.size(); i++) {
			if (boatList.get(i).getId() == id) {
				temp = boatList.get(i);
			}
		}
		temp.setSize(size);
		temp.setType(type);
	}

	public void removeBoat(Boat boat) {
		int id = boat.getId();
		int temp = 0;
		Boat tempMem = null;
		boatList.remove(boat);
		for (int i = id-1; i < boatList.size(); i++) {
			tempMem = boatList.get(i);
			temp = tempMem.getId();
			tempMem.setId(temp - 1);
		}
	}
	
}