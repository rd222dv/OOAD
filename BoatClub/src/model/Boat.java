package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Boat")
@XmlType(propOrder = {"type", "size", "id"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Boat {

	@XmlElement(name = "BoatType")
	private boatType type;
	@XmlElement(name = "BoatSize")
	private double size;
	@XmlElement(name = "BoatId")
	private int id;

	public enum boatType {
		SAILBOAT, MOTORSAILER, CANOE, OTHER
	}

	/**
	 * Empty constructor (required by javax.xml)
	 */
	public Boat() {
	}
	
	/**
	 * Constructor of Boat class
	 * @param type
	 * @param size
	 * @param id
	 */
	public Boat(boatType type, double size, int id) {
		if (size > 0 
				&& type instanceof model.Boat.boatType
				&& id > 0) {
			this.type = type;
			this.size = size;
			this.id = id;
		}
		else {
			throw new RuntimeException ("Please make sure to input correct parameters!");
		}
	}

	/**
	 * @return the size of boat as double
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Set the size of a boat
	 * @param size as double
	 */
	public void setSize(double size) {
		if (size > 0) {
			this.size = size;
		}
		else {
			throw new RuntimeException ("Please make sure to input a correct size!");
		}
	}

	/**
	 * @return type of boat as model.Boat.boatType
	 */
	public boatType getType() {
		return type;
	}

	/**
	 * Sets type of a boat
	 * @param type as model.Boat.boatType
	 */
	public void setType(boatType type) {
		if (type instanceof model.Boat.boatType) {
			this.type = type;
		}
		else {
			throw new RuntimeException ("Please make sure to use boatType from Boat class!");
		}
	}
	/**
	 * @return boat ID as int
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets boat ID
	 * @param i as int
	 */
	public void setId(int i) {
		if (id > 0) {
			this.id = i;
		}
		else {
			throw new RuntimeException ("Make sure ID is above 0!");
		}
	}

}