package dxc.assignment.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dxc.assignment.helper.EncoderHelper;
//import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
public class HomeController {
	private final MemberMapper memberMapper;
	private final EncoderHelper encoderHelper;

	public HomeController(MemberMapper memberMapper, EncoderHelper encoderHelper) {
		this.memberMapper = memberMapper;
		this.encoderHelper = encoderHelper;
	}

	@GetMapping("/")
	public String index(ModelMap model,
			@RequestParam(name = "searchString", required = false, defaultValue = "") String searchString,
			Authentication authentication, HttpServletRequest request) {
		List<Member> members = memberMapper.select(searchString);
		model.addAttribute("members", members);

		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}
		request.getSession().setAttribute("memberRole", roles);

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

	@GetMapping("/login-error")
	public String logout(ModelMap model) {
		model.addAttribute("loginError", "Invalid email or password");
		return "login";
	}
}
