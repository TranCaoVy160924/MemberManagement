package dxc.assignment.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dxc.assignment.constant.MemberRole;
import dxc.assignment.helper.EncoderHelper;
//import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
@Secured({MemberRole.ADMIN, MemberRole.EDIT})
public class AddMemberController {
	private final MemberMapper memberMapper;
	private final EncoderHelper encoderHelper;

	public AddMemberController(MemberMapper memberMapper, EncoderHelper encoderHelper) {
		this.memberMapper = memberMapper;
		this.encoderHelper = encoderHelper;
	}

	// Go to the register page
	@GetMapping("/register")
	public String register(ModelMap model) {
		// Set default member for binding
		model.addAttribute("member", Member.getDefault());
		return "register";
	}

	// Validate member field and redirect to confirmation
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("member") Member member,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		request.getSession().setAttribute("newMember", member);
		return "redirect:/confirmRegister";
	}

	// Display the confirmation page for register
	@GetMapping("/confirmRegister")
	public String confirmRegister(HttpServletRequest request, ModelMap model) {
		// Try get the member from session
		Member member = (Member) request.getSession().getAttribute("newMember");
		if (member == null) {
			return "redirect:/register";
		}

		// Set the information for confirm page
		model.addAttribute("member", member);
		model.addAttribute("title", "会員を登録します");
		model.addAttribute("confirmAction", "confirmRegister");
		model.addAttribute("cancelAction", "cancelRegister");
		return "confirm";
	}

	// When user cancel the confirmation of register process
	@GetMapping("/cancelRegister")
	public String cancelRegister(HttpServletRequest request) {
		request.getSession().removeAttribute("newMember");

		return "redirect:/register";
	}

	// Insert the new member
	@PostMapping("/confirmRegister")
	public String confirmRegister(@ModelAttribute("member") Member member,
			ModelMap modelMap) {
		try {
			// Encode the new member password before insert
			encoderHelper.encodeMemberPassword(member);
			memberMapper.insert(member);

			return "redirect:/";
		} catch (Exception e) {
			if (e.getMessage()
					.contains("duplicate key value violates unique constraint")) {
				modelMap.addAttribute("registerError",
						"Email " + member.getEmail() + "already exist!");
			}
			System.out.println(e.getMessage());
			return "register";
		}
	}
}
