package dxc.assignment.controller.member;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dxc.assignment.constant.MemberRole;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
@Secured({ MemberRole.ADMIN, MemberRole.EDIT })
public class DeleteMemberController {
	private final MemberMapper memberMapper;

	public DeleteMemberController(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	// Display the confirmation page for deleting
	@GetMapping("/confirmDelete/{id}")
	public String confirmDelete(@PathVariable int id, ModelMap model,
			HttpServletRequest request) throws AccessDeniedException {
		// Get the current user and deleting user, check if deleting an higher level member
		String memberRole = (String) request.getSession().getAttribute("memberRole");
		Member member = memberMapper.selectById(id);
		if (memberRole.equals("ROLE_EDIT") && member.getRole().equals("ROLE_ADMIN")) {
			throw new AccessDeniedException("Access is denied");
		}

		// Set the information for delete confimation page
		model.addAttribute("member", member);
		model.addAttribute("title", "会員を削除します");
		model.addAttribute("confirmAction", "confirmDelete/" + member.getId());
		model.addAttribute("cancelAction", "update/" + member.getId());
		return "confirm";
	}

	
	// Delete the member
	@PostMapping("/confirmDelete/{id}")
	public String confirmRegister(@PathVariable int id) {
		memberMapper.delete(id);

		return "redirect:/";
	}
}
