package com.social.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.social.security.FacebookConnectionSignup;
import com.social.security.FacebookSignInAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.social.security" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Autowired
	private UsersConnectionRepository usersConnectionRepository;

	@Autowired
	private FacebookConnectionSignup facebookConnectionSignup;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		// auth.userDetailsService(userDetailsService);
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf().disable().authorizeRequests().antMatchers("/index*","/login*", "/signin/**", "/signup/**", "/api/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and()
				.logout();
	} // @formatter:on

	@Bean
	public ProviderSignInController providerSignInController() {
		((InMemoryUsersConnectionRepository) usersConnectionRepository).setConnectionSignUp(facebookConnectionSignup);
		ProviderSignInController providerSigninController = new ProviderSignInController(connectionFactoryLocator,
				usersConnectionRepository, new FacebookSignInAdapter());
		providerSigninController.setPostSignInUrl("/index.html");
		providerSigninController.setSignInUrl("/login");
		providerSigninController.setSignUpUrl("/login");
		return providerSigninController;
	}
}