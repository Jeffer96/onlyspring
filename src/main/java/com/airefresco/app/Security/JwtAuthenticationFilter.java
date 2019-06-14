package com.airefresco.app.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.airefresco.app.Components.TokenProvider;

import static com.airefresco.app.Security.Constants.*;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
		@Autowired 
		private TokenProvider tp;
		
		@Autowired
		private CustomUserDetailsService cuds;

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc)
				throws ServletException, IOException {
			try {
				String token = getJwtFromRequest(request);
				
				if (StringUtils.hasText(token) && tp.validateToken(token)) {
					int userId = TokenProvider.getUserId(token);
					UserDetails ud = cuds.loadUserById(userId);
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}catch (Exception e) {
				
			}
			
			fc.doFilter(request, response);
		}
		
		private String getJwtFromRequest(HttpServletRequest request) {
			String ans=null;
			String bearerToken = request.getHeader(HEADER_AUTHORIZACION_KEY);	
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX)) {
				ans = bearerToken.substring(7,bearerToken.length());
			}
			return ans;
		}
	}