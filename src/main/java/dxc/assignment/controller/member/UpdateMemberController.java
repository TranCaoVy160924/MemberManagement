package dxc.assignment.controller.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
public class UpdateMemberController {
	private final MemberMapper memberMapper;

	public UpdateMemberController(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, ModelMap model) {
		Member member = memberMapper.selectById(id);
		model.addAttribute("member", member);

		return "update";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("member") Member member,
			BindingResult bindingResult, RedirectAttributes redirectAttribute) {
		if (bindingResult.hasErrors()) {
			return "update";
		}

		redirectAttribute.addFlashAttribute("member", member);
		return "redirect:/confirmUpdate";
	}

	@GetMapping("/confirmUpdate")
	public String confirmUpdate(@ModelAttribute("member") Member member, ModelMap model) {
		model.addAttribute("member", member);
		model.addAttribute("title", "Update member");
		model.addAttribute("action", "/confirmUpdate");
		model.addAttribute("currentAction", "/update/" + member.getId());
		return "confirm";
	}

	@PostMapping("/confirmUpdate")
	public String confirmRegister(@ModelAttribute("member") Member member) {
		memberMapper.update(member);

		return "redirect:/";
	}
}
