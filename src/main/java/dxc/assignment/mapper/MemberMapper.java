package dxc.assignment.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dxc.assignment.model.*;

@Mapper
public interface MemberMapper {
	public List<Member> select();

	public Member selectById(int id);
	
	public void insert(Member member);
	
	public void update(Member member);
	
	public void delete(int id);
}
