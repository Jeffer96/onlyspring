package com.airefresco.app.Security;

import com.airefresco.app.Security.CustomEntryPoint;
//import com.airefresco.app.Security.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
	
	/**@Autowired
	private UserDetailsService uds;
	
	@Autowired
	CustomEntryPoint unauthorizedHandler;
	
	public webSecurityConfig(UserDetailsService userDetailsService) {
		this.uds = userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(uds)
                .passwordEncoder(passwordEncoder());
    }
	**/
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
        //.exceptionHandling()
            //.authenticationEntryPoint(unauthorizedHandler)
            //.and()
        .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .authorizeRequests()
            .antMatchers("/supportSources/**","/script/public/**","/registro/**").permitAll()
            .anyRequest().authenticated()
             .and()
        .formLogin()
        	.loginPage("/login").permitAll()
        	.and()
             	.cors()
                .and()
                .csrf()
                    .disable();
		
		// Add our custom JWT security filter
		//httpSecurity.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	
}
	    
