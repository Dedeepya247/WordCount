package com.wordcount.demo.wordcount.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {

	//Setup a user with role ROLE_USER
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("password")).authorities("ROLE_ADMIN");
	 }
	 //Setup basic authentication and disable CSRF
	 protected void configure(HttpSecurity http) throws Exception {
	  http.httpBasic().and().csrf().disable();
	 }
	 
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	 }
}
