package controller.member;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.security.auth.message.AuthException;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.NestedServletException;

import dxc.assignment.controller.member.UpdateMemberController;
import dxc.assignment.helper.EncoderHelper;
import dxc.assignment.service.MemberService;
import helper.MemberSecurityHelper;

@RunWith(SpringRunner.class)
@WebAppConfiguration
public class UpdateMemberControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private UpdateMemberController controllerUnderTest;

	@Mock
	private MemberService memberService;

	@Mock
	private EncoderHelper encoderHelper;

	@Before
	public void initTest() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(controllerUnderTest)
				.setViewResolvers(viewResolver)
				.build();
	}

	@Test
	public void testGetUpdateMemberNotExistRedirectToIndex() throws Exception {
		when(memberService.selectById(0)).thenReturn(null);

		mockMvc.perform(get("/update/0")
				.with(user(MemberSecurityHelper.getAdminUser())))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"))
				.andExpect(flash().attribute("getInfoError", "idが0のユーザーは存在しません。"))
				.andReturn();
	}

	@Test(expected = AuthException.class)
	public void testGetUpdateMemberEditUpdateAdminThrowException() throws Exception {
		when(memberService.selectById(1)).thenReturn(MemberSecurityHelper.getValidTestAdminMember());
		
		try {
			mockMvc.perform(get("/update/1")
					.with(user(MemberSecurityHelper.getEditUser()))
					.sessionAttr("memberRole", "ROLE_EDIT"));
		}
		catch (NestedServletException e) {
			Exception causeEx = (Exception) e.getCause();
			assertEquals("Access is denied", causeEx.getMessage());
			throw causeEx;
		}
	}

//	@Test
//	public void testPostRegisterInvalidRequestReturnRegister() throws Exception {
//		mockMvc.perform(post("/register")
//				.with(user(MemberSecurityHelper.getAdminUser()))
//				.flashAttr("member", Member.getDefault()))
//				.andExpect(status().isOk())
//				.andExpect(view().name("register"))
//				.andExpect(model().attributeHasErrors("member"))
//				.andReturn();
//	}
//
//	@Test
//	public void testPostRegisterValidRequestRedirectConfirmRegister() throws Exception {
//		Member member = MemberSecurityHelper.getValidTestMember();
//
//		MvcResult result = mockMvc.perform(post("/register")
//				.with(user(MemberSecurityHelper.getAdminUser()))
//				.flashAttr("member", member))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/confirmRegister"))
//				.andReturn();
//
//		HttpSession session = result.getRequest().getSession();
//		Member expected = (Member) session.getAttribute("newMember");
//		assertThat(member).usingRecursiveComparison().isEqualTo(expected);
//	}
//
//	@Test
//	public void testGetConfirmRegisterNoNewMemberRedirectRegister() throws Exception {
//		mockMvc.perform(get("/confirmRegister")
//				.with(user(MemberSecurityHelper.getAdminUser())))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/register"))
//				.andReturn();
//	}
//
//	@Test
//	public void testGetConfirmRegisterValidNewMember() throws Exception {
//		Member validTestMember = MemberSecurityHelper.getValidTestMember();
//		MvcResult result = mockMvc.perform(get("/confirmRegister")
//				.with(user(MemberSecurityHelper.getAdminUser()))
//				.sessionAttr("newMember", validTestMember))
//				.andExpect(status().isOk())
//				.andExpect(view().name("confirm"))
//				.andReturn();
//
//		ModelMap model = result.getModelAndView().getModelMap();
//		assertThat(model.getAttribute("member"))
//				.usingRecursiveComparison()
//				.isEqualTo(validTestMember);
//		assertEquals("会員を登録します", model.getAttribute("title"));
//		assertEquals("confirmRegister", model.getAttribute("confirmAction"));
//		assertEquals("cancelRegister", model.getAttribute("cancelAction"));
//	}
//
//	@Test
//	public void testGetCancelRegisterReturnRegister() throws Exception {
//		Member validTestMember = MemberSecurityHelper.getValidTestMember();
//		MvcResult result = mockMvc.perform(get("/cancelRegister")
//				.with(user(MemberSecurityHelper.getAdminUser()))
//				.sessionAttr("newMember", validTestMember))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/register"))
//				.andReturn();
//
//		HttpSession session = result.getRequest().getSession();
//		Member expectedNewMember = (Member) session.getAttribute("newMember");
//		assertEquals(null, expectedNewMember);
//	}
}
