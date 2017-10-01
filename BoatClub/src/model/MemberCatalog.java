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

	public List<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<Member> memberCatalog) {
		this.memberList = memberCatalog;
	}
	
	public void addMember(String name, double personnumber) throws ParseException {
		int id = getMemberList().size() + 1;
		this.memberList.add(new Member(name, personnumber,  id));
	}

	public void updateMember(Member currentMember, String name, double personnumber) throws ParseException { // FIXME not sure if this is enough
		memberList.remove(currentMember);
		int id = getMemberList().size() + 1;
		memberList.add(new Member(name, personnumber, id));
	}

	public void removeMember(Member member) { 
		this.memberList.remove(member);
	}
}