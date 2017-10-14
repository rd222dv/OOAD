package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Boat")
@XmlType(propOrder = {"type", "size"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Boat {

	@XmlElement(name = "BoatType")
	private boatType type;
	@XmlElement(name = "BoatSize")
	private double size;

	public enum boatType {
		SAILBOAT, MOTORSAILER, CANOE, OTHER
	}

	public Boat() {
	}

	public Boat(boatType type, double size) {
		this.type = type;
		this.size = size;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public boatType getType() {
		return type;
	}

	public void setType(boatType type) {
		this.type = type;
	}

}