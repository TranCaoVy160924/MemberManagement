package controller.member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.http.HttpSession;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

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
import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;
import helper.MemberSecurityHelper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		TestConfig.class
})
@WebAppConfiguration
public class AddMemberControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private MemberMapper memberMapper;

	@Mock
	private EncoderHelper encoderHelper;

	@Before
	public void initTest() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(springSecurity())
				.build();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetRegister() throws Exception {
		MvcResult result = mockMvc.perform(get("/register")
				.with(user(MemberSecurityHelper.getAdminUser())))
				.andExpect(status().isOk())
				.andExpect(view().name("register"))
				.andReturn();

		ModelMap model = result.getModelAndView().getModelMap();
		Member actual = (Member) model.getAttribute("member");
		Member expected = Member.getDefault();
		assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	public void testPostRegisterInvalidRequestReturnRegister() throws Exception {
		mockMvc.perform(post("/register")
				.with(user(MemberSecurityHelper.getAdminUser()))
				.flashAttr("member", Member.getDefault()))
				.andExpect(status().isOk())
				.andExpect(view().name("register"))
				.andExpect(model().attributeHasErrors("member"))
				.andReturn();
	}

	@Test
	public void testPostRegisterValidRequestRedirectConfirmRegister() throws Exception {
		Member member = MemberSecurityHelper.getValidTestAdminMember();

		MvcResult result = mockMvc.perform(post("/register")
				.with(user(MemberSecurityHelper.getAdminUser()))
				.flashAttr("member", member))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/confirmRegister"))
				.andReturn();

		HttpSession session = result.getRequest().getSession();
		Member expected = (Member) session.getAttribute("newMember");
		assertThat(member).usingRecursiveComparison().isEqualTo(expected);
	}

	@Test
	public void testGetConfirmRegisterNoNewMemberRedirectRegister() throws Exception {
		mockMvc.perform(get("/confirmRegister")
				.with(user(MemberSecurityHelper.getAdminUser())))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/register"))
				.andReturn();
	}

	@Test
	public void testGetConfirmRegisterValidNewMember() throws Exception {
		Member validTestMember = MemberSecurityHelper.getValidTestAdminMember();
		MvcResult result = mockMvc.perform(get("/confirmRegister")
				.with(user(MemberSecurityHelper.getAdminUser()))
				.sessionAttr("newMember", validTestMember))
				.andExpect(status().isOk())
				.andExpect(view().name("confirm"))
				.andReturn();

		ModelMap model = result.getModelAndView().getModelMap();
		assertThat(model.getAttribute("member"))
				.usingRecursiveComparison()
				.isEqualTo(validTestMember);
		assertEquals("会員を登録します", model.getAttribute("title"));
		assertEquals("confirmRegister", model.getAttribute("confirmAction"));
		assertEquals("cancelRegister", model.getAttribute("cancelAction"));
	}
	
	@Test
	public void testGetCancelRegisterReturnRegister() throws Exception {
		Member validTestMember = MemberSecurityHelper.getValidTestAdminMember();
		MvcResult result = mockMvc.perform(get("/cancelRegister")
				.with(user(MemberSecurityHelper.getAdminUser()))
				.sessionAttr("newMember", validTestMember))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/register"))
				.andReturn();

		HttpSession session = result.getRequest().getSession();
		Member expectedNewMember = (Member) session.getAttribute("newMember");
		assertEquals(null, expectedNewMember);
	}
}
