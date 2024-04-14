package com.madbeats.config;
/**
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain defaultFilterChain (HttpSecurity httpSecurity) {
		try {
			return httpSecurity
					.csrf(csrf->csrf.disable())
					.authorizeHttpRequests(auth -> auth.requestMatchers("/register","/error").permitAll()
					.anyRequest().authenticated())
					.httpBasic(Customizer.withDefaults())
					.formLogin(Customizer.withDefaults())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	@Bean
	public UserDetailsService userDetailsService () {
		Collection <UserDetails> users = new ArrayList<>();
		UserDetails userDetails = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		users.add(userDetails);
		UserDetails userDetailsAdmin = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("admin")
				.roles("ADMIN")
				.build();
		users.add(userDetailsAdmin);
		return new InMemoryUserDetailsManager(users);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder () {
		 return NoOpPasswordEncoder.getInstance();  // return new BCryptPasswordEncoder();
	}
}
**/