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
	
	public List<Member> getMemberList() {
		return members.getMemberList();
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
	
	public void removeMember (Member member) {
		members.removeMember(member);
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateMember (Member currentMember, String name, int personnumber) throws ParseException {
		members.updateMember(currentMember, name, personnumber);
		try {
			fileUtil.saveRegistry(members, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}