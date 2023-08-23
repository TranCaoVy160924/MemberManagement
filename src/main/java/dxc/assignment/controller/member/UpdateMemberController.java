package dxc.assignment.controller.member;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import dxc.assignment.constant.MemberRole;
import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.helper.ValidationHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
@Secured({ MemberRole.ADMIN, MemberRole.EDIT })
public class UpdateMemberController {
	private final MemberMapper memberMapper;
	private final EncoderHelper encoderHelper;

	public UpdateMemberController(MemberMapper memberMapper,
			EncoderHelper encoderHelper) {
		this.memberMapper = memberMapper;
		this.encoderHelper = encoderHelper;
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, ModelMap model, HttpServletRequest request)
			throws AccessDeniedException {
		String memberRole = (String) request.getSession().getAttribute("memberRole");
		Member member = memberMapper.selectById(id);
		if (memberRole.equals("ROLE_EDIT") && member.getRole().equals("ROLE_ADMIN")) {
			throw new AccessDeniedException("Access is denied");
		}

		model.addAttribute("member", member);

		return "update";
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("member") Member member,
			BindingResult bindingResult, HttpServletRequest request) {
		// Ignore password validation when password is blank
		if (member.getPassword().isBlank()) {
			if (!ValidationHelper.hasErrorOnlyForField(bindingResult, "password")) {
				return "update";
			}
		} else if (bindingResult.hasErrors()) {
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

		System.out.println("Reach here: GET - confirmUpdate");

		model.addAttribute("member", member);
		model.addAttribute("title", "会員を編集します");
		model.addAttribute("confirmAction", "confirmUpdate");
		model.addAttribute("cancelAction", "cancelUpdate/" + member.getId());
		return "confirm";
	}

	@GetMapping("/cancelUpdate/{id}")
	public String cancelUpdate(@PathVariable int id, HttpServletRequest request) {
		request.getSession().removeAttribute("editingMember");
		return "redirect:/update/" + id;
	}

	@PostMapping("/confirmUpdate")
	public String confirmUpdate(@ModelAttribute("member") Member member) {
		System.out.println("Reach here: POST - cofirmUpdate");
		encoderHelper.encodeMemberPassword(member);
		memberMapper.update(member);

		return "redirect:/";
	}
}
