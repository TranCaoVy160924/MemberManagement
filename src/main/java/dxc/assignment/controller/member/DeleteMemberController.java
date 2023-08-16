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
public class DeleteMemberController {
	private final MemberMapper memberMapper;

	public DeleteMemberController(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@GetMapping("/confirmDelete/{id}")
	public String confirmDelete(@PathVariable int id, ModelMap model) {
		Member member = memberMapper.selectById(id);

		model.addAttribute("member", member);
		model.addAttribute("title", "Delete Member");
		model.addAttribute("action", "/confirmDelete/" + member.getId());
		model.addAttribute("currentAction", "/update/" + member.getId());
		return "confirm";
	}

	@PostMapping("/confirmDelete/{id}")
	public String confirmRegister(@PathVariable int id) {
		memberMapper.delete(id);

		return "redirect:/";
	}
}
