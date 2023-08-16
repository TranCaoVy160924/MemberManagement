package dxc.assignment.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import dxc.assignment.model.*;

@Mapper
public interface MemberMapper {
	public List<Member> select();
}
