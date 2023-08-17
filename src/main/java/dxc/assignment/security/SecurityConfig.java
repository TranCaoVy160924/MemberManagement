package dxc.assignment.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import dxc.assignment.mapper.MemberMapper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final MemberMapper memberMapper;
	
	public SecurityConfig(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		UserDetails admin = User.builder().username("admin").password("password")
//				.roles("ADMIN", "USER").build();
//		
//		UserDetails user = User.builder().username("user").password("password")
//				.roles("ADMIN", "USER").build();
//		
//		return new InMemoryUserDetailsManager(admin, user);
//	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**")
				.permitAll()
				.anyRequest().authenticated();

		http.formLogin().loginPage("/login").permitAll();

		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true);
	}

	@Bean
	public MemberDetailService springDataUserDetailsService() {
		return new MemberDetailService(memberMapper);
	}
}