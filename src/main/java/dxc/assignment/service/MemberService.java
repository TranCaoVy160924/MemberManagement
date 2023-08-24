package dxc.assignment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Service
public class MemberService {
	private final MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public List<Member> select(String searchString) {
		return memberMapper.select(searchString);
	}
	
	public Member selectById(int id) {
		return memberMapper.selectById(id);
	}
	
	public Member selectByEmail(String email) {
		return memberMapper.selectByEmail(email);
	}
	
	public void insert(Member member) {
		memberMapper.insert(member);
	}
	
	public void update(Member member) {
		memberMapper.update(member);
	}
	
	public void delete(int id) {
		memberMapper.delete(id);
	}
}
