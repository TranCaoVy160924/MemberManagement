package dxc.assignment.controller.member;

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
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		redirectAttribute.addFlashAttribute("member", member);
		return "redirect:/confirmRegister";
	}

	@GetMapping("/confirmRegister")
	public String confirmRegister(@ModelAttribute("member") Member member,
			ModelMap model) {
		model.addAttribute("member", member);
		model.addAttribute("title", "Register new member");
		model.addAttribute("action", "/confirmRegister");
		model.addAttribute("currentAction", "/register");
		return "confirm";
	}
	
	@PostMapping("/confirmRegister")
	public String confirmRegister(@ModelAttribute("member") Member member) {
		memberMapper.insert(member);
		
		return "redirect:/";
	}
}
