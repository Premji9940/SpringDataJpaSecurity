package com.nit.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nit.service.IUserInfoService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class UserSecurityConfig {
	@Autowired
	private DataSource ds;
	@Autowired
	private IUserInfoService service;
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder end=	new BCryptPasswordEncoder();
	String s=	end.encode("4567");
	System.out.println(s);
		return new BCryptPasswordEncoder();
	}
	
	/*  @Bean public JdbcUserDetailsManager userDetailsService() { //UserDetails
//	  admin=User.withUsername("prem").password("{noop}1234").roles("MANAGER").build
//	  (); //UserDetails
//	  user=User.withUsername("kumar").password("{noop}4567").roles("USER").build();
//	  
	  //return new InMemoryUserDetailsManager(admin,user);
	  
	  
	  return new JdbcUserDetailsManagerConfigurer<>().dataSource(ds)
	  .passwordEncoder(new BCryptPasswordEncoder()).
	  usersByUsernameQuery("SELECT UNAME,PWD,STATUS FROM USERS WHERE UNAME=?").
	  authoritiesByUsernameQuery("SELECT UNAME,ROLE FROM USER_ROLES WHERE UNAME=?")
	  .getUserDetailsService();
	  
	  }*/
	@Bean public DaoAuthenticationConfigurer<AuthenticationManagerBuilder,IUserInfoService> userDetailsService() { //UserDetails
//		  admin=User.withUsername("prem").password("{noop}1234").roles("MANAGER").build
//		  (); //UserDetails
//		  user=User.withUsername("kumar").password("{noop}4567").roles("USER").build();
//		  
		  //return new InMemoryUserDetailsManager(admin,user);
		  
		  
		  return  new DaoAuthenticationConfigurer<AuthenticationManagerBuilder,IUserInfoService>(service).
				  passwordEncoder(new BCryptPasswordEncoder());
		  
		  }
	 
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	return	http.csrf().disable().
			authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/")).permitAll().
			requestMatchers(new AntPathRequestMatcher("/balance")).hasAuthority("MANAGER").
			requestMatchers(new AntPathRequestMatcher("/offers")).authenticated().
			requestMatchers(new AntPathRequestMatcher("/deposite")).hasAnyAuthority("CLERK","MANAGER").
			anyRequest().authenticated()

		  .and().formLogin().and().exceptionHandling().accessDeniedPage("/denied").and().
		  build();
		
	}
	



	
}
