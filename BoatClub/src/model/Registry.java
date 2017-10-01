package model;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import utils.FileUtil;

public class Registry {
	
	private MemberCatalog members;
	private Member currentMember;
	private FileUtil fileUtil = new FileUtil();
	private File file;
	
	public Registry(){
		file = new File("database.xml");
		try {
			// Checks if file exists. If not, xml with proper tags should be created.
			if (!file.exists()) {
				fileUtil.saveRegistry(members, file);
			}
			members = fileUtil.loadRegistry(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setCurrentMember(Member member) {
		this.currentMember = member;
	}
	
	public Member getCurrentMember() {
		return currentMember;
	}
	
	public List<Member> getMemberList() {
		return members.getMemberList();
	}
	
	public boolean isEmptyMembers() {
		return getMemberList().isEmpty();
	}
	
	public boolean isEmptyBoats() {
		return currentMember.getBoatList().isEmpty();
	}
	
	public void addMember(String name, int personnumber) throws ParseException {
		members.addMember(name, personnumber);
		setCurrentMember(members.getMemberList().get(members.getMemberList().size()-1));
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addBoat(Boat.boatType type, double size) {
		currentMember.addBoat(type, size);
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateBoat(Boat.boatType type, double size) {
		Boat currentBoat = currentMember.getCurrentBoat();
		currentMember.updateBoat(currentBoat, type, size);
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeBoat (Boat boat) {
		currentMember.removeBoat(boat);
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeMember () {
		members.removeMember(currentMember);
		if (!isEmptyMembers()) {
			currentMember = getMemberList().get(getMemberList().size()-1);
		}
		else {
			currentMember = null;
		}
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMember (String name, double personnumber) throws ParseException {
		members.updateMember(currentMember, name, personnumber);
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}