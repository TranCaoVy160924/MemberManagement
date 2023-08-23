package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityWebTestConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails admin = User.builder()
				.username("haha@gmail.com")
				.password("12345678")
				.authorities("ROLE_ADMIN")
				.build();
		
		UserDetails edit = User.builder()
				.username("thanhDung@gmail.com")
				.password("12345678")
				.authorities("ROLE_EDIT")
				.build();
		
		UserDetails view = User.builder()
				.username("dkNhien@gmail.com")
				.password("12345678")
				.authorities("ROLE_VIEW")
				.build();

		return new InMemoryUserDetailsManager(admin, edit, view);
	}
}
