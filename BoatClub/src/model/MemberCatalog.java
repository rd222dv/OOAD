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
		memberList.remove(currentMember);
		memberList.add(new Member(name, personnumber, id));
	}

	public void removeMember(Member member) { 
		this.memberList.remove(member);
	}
}