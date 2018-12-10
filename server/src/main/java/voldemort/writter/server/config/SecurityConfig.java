package voldemort.writter.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import voldemort.writter.server.security.AuthenticationHandlerInterceptor;
import voldemort.writter.server.security.TokenAuthenticationService;

/**
 * Spring configuration class for web security.
 * @author Alvin Quach
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.anyRequest().permitAll();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");	
			}
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
			    registry.addInterceptor(new AuthenticationHandlerInterceptor(tokenAuthenticationService));
			}
		};
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
