//package dxc.assignment.helper;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import dxc.assignment.model.Member;
//
//@Component
//public class EncoderHelper {
//	private final PasswordEncoder passwordEncoder;
//	
//	public EncoderHelper(PasswordEncoder passwordEncoder) {
//		this.passwordEncoder =passwordEncoder;
//	}
//	
//	public void encodeMemberPassword(Member member) {
//		String password = member.getPassword();
//		member.setPasswordHash(passwordEncoder.encode(password));
//	}
//}
