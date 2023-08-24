package controller.home;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import config.TestConfig;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;
import dxc.assignment.service.MemberService;
import helper.MemberSecurityHelper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		TestConfig.class
})
@WebAppConfiguration
public class HomeControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private MemberService memberService;

	@Before
	public void initTest() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(springSecurity())
				.build();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetIndex() throws Exception {
		List<Member> members = new ArrayList<>();
		// Add some mock data to members list
		members.add(MemberSecurityHelper.getDefaultTestMember());

		when(memberService.select("")).thenReturn(members);

		mockMvc.perform(get("/")
				.with(user(MemberSecurityHelper.getAdminUser()))
				.queryParam("searchString", ""))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("members"));
	}

	@Test
	public void testGetLogin() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}

	@Test
	public void testAuthenticate() throws Exception {
		MvcResult result = mockMvc.perform(get("/login-sucess")
				.with(user(MemberSecurityHelper.getAdminUser())))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"))
				.andReturn();

		HttpSession session = result.getRequest().getSession();

		String memberEmail = (String) session.getAttribute("memberEmail");
		String memberRole = (String) session.getAttribute("memberRole");

		assertEquals("caovy@gmail.com", memberEmail);
		assertEquals("ROLE_ADMIN", memberRole);
	}

	@Test
	public void testLoginError() throws Exception {
		MvcResult result = mockMvc.perform(get("/login-error"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"))
				.andReturn();

		ModelMap model = result.getModelAndView().getModelMap();
		assertEquals("Invalid email or password", model.getAttribute("loginError"));
	}
}
