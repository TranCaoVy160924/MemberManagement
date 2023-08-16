package dxc.assignment.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@Controller
public class HomeController {
	private final MemberMapper memberMapper;

	@Autowired
	public HomeController(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}

	@GetMapping("/")
	public String index(ModelMap model) {
		List<Member> members = memberMapper.select();
		model.addAttribute("members", members);

		return "index";
	}

	@GetMapping("/update")
	public String update(@PathVariable int id) {
		return "update";
	}
}
