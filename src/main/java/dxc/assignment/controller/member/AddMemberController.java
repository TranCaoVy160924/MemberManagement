package dxc.assignment.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dxc.assignment.constant.MemberRole;
import dxc.assignment.helper.EncoderHelper;
//import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;
import dxc.assignment.service.MemberService;

@Controller
@Secured({MemberRole.ADMIN, MemberRole.EDIT})
public class AddMemberController {
	private final MemberService memberService;
	private final EncoderHelper encoderHelper;

	public AddMemberController(MemberService memberService, EncoderHelper encoderHelper) {
		this.memberService = memberService;
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
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		session.setAttribute("newMember", member);
		return "redirect:/confirmRegister";
	}

	// Display the confirmation page for register
	@GetMapping("/confirmRegister")
	public String confirmRegister(HttpSession session, ModelMap model) {
		// Try get the member from session
		Member member = (Member) session.getAttribute("newMember");
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
			memberService.insert(member);

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
