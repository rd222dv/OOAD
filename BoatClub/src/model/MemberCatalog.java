package model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import model.Member;

@XmlRootElement(name = "MemberCatalog")
@XmlType(propOrder = {"memberList"})
@XmlAccessorType(XmlAccessType.FIELD)
public class MemberCatalog {

	@XmlElement(name = "MemberList")
	private List<Member> memberList = new ArrayList<Member>();

	/**
	 * Constructor
	 */
	public MemberCatalog() {
		
	}

	/**
	 * @return Iterable object of members list
	 */
	public Iterable<Member> getMemberList() {
		return memberList;
	}
	
	/**
	 * @return a size of members list as int
	 */
	public int membersSize() {
		return memberList.size();
	}
	
	/**
	 * @return true if empty
	 */
	public boolean isMembersEmpty() {
		return memberList.size() == 0;
	}
	
	/**
	 * Adds a member to members list
	 * @param name as string
	 * @param personnumber as String
	 * @throws ParseException
	 */
	public void addMember(String name, String personnumber) throws ParseException {
		int id = memberList.size() + 1;
		this.memberList.add(new Member(name, personnumber,  id));
	}
	
	/**
	 * Updates a member from members list
	 * @param currentMember member to be updated of type model.Member
	 * @param name as string
	 * @param personnumber as string
	 * @throws ParseException
	 */
	public void updateMember(Member currentMember, String name, String personnumber) throws ParseException { // FIXME not sure if this is enough
		int id = currentMember.getMemberId();
		Member temp = null;
		for (int i = 0; i < memberList.size(); i++) {
			if (memberList.get(i).getMemberId() == id) {
				temp = memberList.get(i);
			}
		}
		temp.setName(name);
		temp.setPersonnumber(personnumber);
	}

	/**
	 * Removes a member from members list
	 * @param member as model.Member
	 */
	public void removeMember(Member member) {
		int id = member.getMemberId();
		int temp = 0;
		Member tempMem = null;
		this.memberList.remove(member);
		//Fixes IDs for other members to ignore removed member
		for (int i = id-1; i < memberList.size(); i++) {
			tempMem = memberList.get(i);
			temp = tempMem.getMemberId();
			tempMem.setMemberId(temp - 1);
		}
	}
}