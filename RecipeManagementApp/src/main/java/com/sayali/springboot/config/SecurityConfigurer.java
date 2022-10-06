package com.sayali.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sayali.springboot.filters.RecipeManagementFilter;
import com.sayali.springboot.services.serviceImpl.MyUserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsServiceImpl myUserDetailsService;

	@Autowired
	private RecipeManagementFilter recipeManagementFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/v1/recipes/authenticate").permitAll().antMatchers("/api")
				.permitAll().antMatchers("/v1/swagger-ui.html").permitAll().antMatchers("/v3/api-docs","/v1/api-docs").permitAll()
				.antMatchers("/v1/swagger-ui/index.html").permitAll().antMatchers("/actuator/health").permitAll()
				.antMatchers("/actuator/info").permitAll().antMatchers("**/**").permitAll().antMatchers("/actuator")
				.permitAll().antMatchers("/docs-ui.html").permitAll()
				.antMatchers("/csrf", "/service-status/v1/task/status", "/swagger-ui.html","/api-docs/*","/v1/api-docs/*",
						"/*.html", "/*.js", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.png", "/webjars/**",
						"/configuration/**", "/v2/**", "/swagger-resources/**", "/**/*.js","/**/*.css.map","/**/*.js.map")
				.permitAll().antMatchers("**/v1/recipes/**").permitAll().antMatchers("/api-docs").permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(recipeManagementFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
