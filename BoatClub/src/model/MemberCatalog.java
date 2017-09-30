package model;

import java.text.ParseException;
import java.util.ArrayList;

import model.Member;

public class MemberCatalog {

	private ArrayList<Member> memberList;
	private int newID = 0;

	public MemberCatalog() {
		memberList = new ArrayList<Member>();
	}

	public ArrayList<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(ArrayList<Member> memberCatalog) {
		this.memberList = memberCatalog;
	}

	public int getNewID() {
		return newID;
	}
	
	public void addMember(String name, String personnumber) throws ParseException { 
		this.memberList.add(new Member(name, personnumber, ++newID));
	}

	public void updateMember(Member currentMember, String name, String personnumber) throws ParseException { // FIXME not sure if this is enough
		memberList.remove(currentMember);
		memberList.add(new Member(name, personnumber, newID));
	}

	public void removeMember(Member member) { 
		this.memberList.remove(member);
	}
	
	public Member searchMember(int Id) {
		for (Member member : this.memberList) {
			if (member.getMemberId() == Id)
				return member;
		}
		return null;
	}
}