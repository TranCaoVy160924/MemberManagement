package dxc.assignment.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
public class AddMemberController {
	private final MemberMapper memberMapper;

	public AddMemberController(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@GetMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("member", Member.getDefault());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("member") Member member,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		request.getSession().setAttribute("newMember", member);
		return "redirect:/confirmRegister";
	}

	@GetMapping("/confirmRegister")
	public String confirmRegister(HttpServletRequest request, ModelMap model) {
		Member member = (Member) request.getSession().getAttribute("newMember");

		if (member == null) {
			return "redirect:/register";
		}

		model.addAttribute("member", member);
		model.addAttribute("title", "Register new member");
		model.addAttribute("confirmAction", "/confirmRegister");
		model.addAttribute("cancelAction", "/cancelRegister");
		return "confirm";
	}
	
	@GetMapping("/cancelRegister")
	public String cancelRegister(HttpServletRequest request) {
		request.getSession().removeAttribute("newMember");
		
		return "redirect:/register";
	}

	@PostMapping("/confirmRegister")
	public String confirmRegister(@ModelAttribute("member") Member member) {
		memberMapper.insert(member);

		return "redirect:/";
	}
}
