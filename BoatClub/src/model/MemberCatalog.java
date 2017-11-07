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

	public MemberCatalog() {
		
	}

	public Iterable<Member> getMemberList() {
		return memberList;
	}
	
	public int membersSize() {
		return memberList.size();
	}
	
	public boolean isMembersEmpty() {
		return memberList.size() == 0;
	}

	public void setMemberList(ArrayList<Member> memberCatalog) {
		this.memberList = memberCatalog;
	}
	
	public void addMember(String name, String personnumber) throws ParseException {
		int id = memberList.size() + 1;
		this.memberList.add(new Member(name, personnumber,  id));
	}

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

	public void removeMember(Member member) {
		int id = member.getMemberId();
		int temp = 0;
		Member tempMem = null;
		this.memberList.remove(member);
		for (int i = id-1; i < memberList.size(); i++) {
			tempMem = memberList.get(i);
			temp = tempMem.getMemberId();
			tempMem.setMemberId(temp - 1);
		}
	}
}