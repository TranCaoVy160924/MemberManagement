package controller.home;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import config.SpringSecurityWebTestConfig;
import dxc.assignment.mapper.MemberMapper;
import dxc.assignment.model.Member;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/Spring-servlet.xml"
}, loader = {
		SpringSecurityWebTestConfig.class
})
@WebAppConfiguration
public class HomeControllerTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Mock
	private MemberMapper memberMapper;

	@Before
	public void initTest() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetIndex() throws Exception {
		List<Member> members = new ArrayList<>();
		members.add(Member.getDefault());
		// Add some mock data to members list

		when(memberMapper.select("")).thenReturn(members);

		mockMvc.perform(MockMvcRequestBuilders.get("/")
				.queryParam("searchString", ""))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("index"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("members"));
	}

	@Test
	public void testGetLogin() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("login"));
	}

	@Test
	@WithUserDetails("user@company.com")
	public void testLoginSuccess() throws Exception {
		MockHttpSession session = new MockHttpSession();
		mockMvc.perform(MockMvcRequestBuilders.get("/login-sucess")
				.session(session))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("index"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("members"));
		assertEquals(session.getAttribute("memberEmail"), "");
	}
}
