package com.ibm.fsd.smc.ms.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
//@EnableWebSecurity
public class EurekaSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security-config.admin-name}")
	private String EUREKA_SERVER_USERNAME;
	
	@Value("${security-config.admin-pwd}")
	private String EUREKA_SERVER_PASSWORD;
	
	@Value("${security-config.admin-role}")
	private String EUREKA_SERVER_ROLE;
	
	@Value("${security-config.client-name}")
	private String EUREKA_CLIENT_USERNAME;
	
	@Value("${security-config.client-pwd}")
	private String EUREKA_CLIENT_PASSWORD;
	
	@Value("${security-config.client-role}")
	private String EUREKA_CLIENT_ROLE;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
		http.csrf().disable();
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
		.withUser(EUREKA_SERVER_USERNAME)
		.password(EUREKA_SERVER_PASSWORD)
		.roles(EUREKA_SERVER_ROLE)
		.and()
		.withUser(EUREKA_CLIENT_USERNAME)
		.password(EUREKA_CLIENT_PASSWORD)
		.roles(EUREKA_CLIENT_ROLE);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
