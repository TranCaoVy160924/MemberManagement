package dxc.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
public class HomeController {
	private final MemberMapper memberMapper;
//	private final EncoderHelper encoderHelper;

	public HomeController(MemberMapper memberMapper
//			, EncoderHelper encoderHelper
			) {
		this.memberMapper = memberMapper;
//		this.encoderHelper = encoderHelper;
	}

	@GetMapping("/")
	public String index(ModelMap model) {
		List<Member> members = memberMapper.select();
		model.addAttribute("members", members);

		return "index";
	}

//	@GetMapping("/logout")
//	public String logout() {
//		return "redirect:/login";
//	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

//	@PostMapping("/login") 
//	public String logout(@ModelAttribute LoginRequest loginRequest) {
//		return "redirect:/";
//	}
}
