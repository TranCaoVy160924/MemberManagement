package dxc.assignment.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;
import dxc.assignment.service.MemberService;

@Controller
public class HomeController {
	private final MemberService memberService;

	public HomeController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/")
	public String index(ModelMap model,
			@RequestParam(name = "searchString", required = false, defaultValue = "") String searchString) {
		List<Member> members = memberService.select(searchString);
		model.addAttribute("members", members);

		return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/login-sucess")
	public String authenticate(Authentication authentication,
			HttpSession session) {
		// Get current user detail
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetails
				.getAuthorities();
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}

		// Set current user email and role to session
		session.setAttribute("memberEmail", userDetails.getUsername());
		session.setAttribute("memberRole", roles.get(0));

		return "redirect:/";
	}

	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("loginError", "Invalid email or password");
		return "login";
	}
}
