package dxc.assignment.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

public class MemberDetailService implements UserDetailsService {
	private final MemberMapper memberMapper;

	public MemberDetailService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Member member = memberMapper.selectByEmail(username);
		if (member == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		System.out.println("Email: " + member.getPasswordHash());
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPasswordHash()) // Encoded
				.roles(member.getRole())
				.build();
	}
}
