package com.example.Authorization_Authentication_2;

import org.glassfish.jaxb.runtime.v2.runtime.output.Encoded;
import org.h2.command.ddl.CreateUser;
import org.h2.engine.UserBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import ch.qos.logback.core.encoder.Encoder;

@Configuration
public class KiranSecurity {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
		
	
		return new InMemoryUserDetailsManager(
				
				createuser("Bhavana", "Bhavana@123", "Employee", passwordEncoder),
				createuser("Subbu", "Subbu@123", "Employee", passwordEncoder),
				createuser("Siva", "Siva@123", "Employee", passwordEncoder),
				createuser("Kiran", "Kiran@123", "Employee", passwordEncoder),
				createuser("Shareej", "Shareej@123", "Manager", passwordEncoder)
				
				);
		
	}
	
	
	
	public UserDetails createuser(String username,String rawPassword,String role,PasswordEncoder passwordEncoder) {
		
		
		return User.builder().username(username).password(passwordEncoder.encode(rawPassword)).roles(role).build();
		
	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	//Authorization
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
				
		http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.POST, "/kiran/save").hasRole("Manager")
				
				.requestMatchers(HttpMethod.GET, "/kiran/getData").hasRole("Manager")
				.requestMatchers(HttpMethod.GET, "/kiran/getDatabyId/{userid}").hasRole("Employee")
				.anyRequest().authenticated())
		
		.csrf().disable().httpBasic(Customizer.withDefaults());
		
		
		
		
		
		//disable csrf
	//	http.csrf().disable();
		
		//ENable basic security
		// http.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	}
	
	
}
