package com.airefresco.app.Security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
		)
public class webSecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	CustomUserDetailsService cuds;
	
	@Autowired
	CustomEntryPoint unauthorizedHandler;
			
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(cuds)
            .passwordEncoder(passwordEncoder());
    }
	
	
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
	     	.cors()
	     		.and()
	        .csrf()
            	.disable()
        .exceptionHandling()
            	.authenticationEntryPoint(unauthorizedHandler)
            .and()
        .sessionManagement()
            	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .authorizeRequests()
            	.antMatchers("/supportSources/**","/script/public/**","/certificados/**","/login/**","/")
            		.permitAll()
            	.antMatchers("/administrador","/controlUsuarios")
            		.hasAuthority("ADMIN")
            	.anyRequest()
            		.authenticated()
             .and()
        .formLogin()
        	.loginPage("/portal")
        	.permitAll()
        	.and()
        .logout()
        	//.logoutUrl("/logout")
        	.clearAuthentication(true)
        	.logoutSuccessUrl("/portal");
		
		// Add our custom JWT security filter
		httpSecurity.addFilterBefore( new JwtAuthenticationFilter(cuds), UsernamePasswordAuthenticationFilter.class );
	}

	
}
	    
