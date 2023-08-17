package dxc.assignment.controller.member;

import javax.servlet.http.HttpServletRequest;
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
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "update";
		}

		request.getSession().setAttribute("editingMember", member);
		return "redirect:/confirmUpdate";
	}

	@GetMapping("/confirmUpdate")
	public String confirmUpdate(HttpServletRequest request, ModelMap model) {
		Member member = (Member) request.getSession().getAttribute("editingMember");
		if (member == null) {
			return "redirect:/";
		}

		model.addAttribute("member", member);
		model.addAttribute("title", "Update member");
		model.addAttribute("confirmAction", "/confirmUpdate");
		model.addAttribute("cancelAction", "/cancelUpdate/" + member.getId());
		return "confirm";
	}

	@GetMapping("/cancelUpdate/{id}")
	public String cancelUpdate(@PathVariable int id, HttpServletRequest request) {
		request.getSession().removeAttribute("editingMember");
		return "redirect:/update/" + id;
	}

	@PostMapping("/confirmUpdate")
	public String confirmRegister(@ModelAttribute("member") Member member) {
		memberMapper.update(member);

		return "redirect:/";
	}
}
