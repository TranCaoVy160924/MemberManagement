package dxc.assignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig{
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

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//				.anyRequest().authenticated().and().formLogin().loginPage("/login")
//				.permitAll().and().logout().permitAll();
//	}
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("email").password("password")
//				.roles("USER");
//	}
}