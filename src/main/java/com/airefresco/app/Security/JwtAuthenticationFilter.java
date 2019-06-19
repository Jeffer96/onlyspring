package com.airefresco.app.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.airefresco.app.Components.TokenProvider;

import static com.airefresco.app.Security.Constants.*;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
		private CustomUserDetailsService cuds;
	
		public JwtAuthenticationFilter(CustomUserDetailsService cuds) {
			this.cuds = cuds;
		}
		
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc)
				throws ServletException, IOException {
			try {
				String token = getJwtFromRequest(request);
				
				if (StringUtils.hasText(token) && TokenProvider.validateToken(token)) {
					//System.out.println(" 2. el token es valido");
					int userId = TokenProvider.getUserId(token);
					//System.out.println(" 3. el id del usuario es: "+userId);
					//Aqui esta el triple hp error-------------------------------------------------------------------------------------------------------------
					UserDetails ud = cuds.loadUserById(userId);
					//System.out.println(" 4. cargo el ud");
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud,null,ud.getAuthorities());
					//System.out.println(" 5. carga el upat");
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					//System.out.println(" 6. establece los detalles de autentication");
					SecurityContextHolder.getContext().setAuthentication(auth);
					//System.out.println(" 7. finaliza bien hp vida");
				}
			}catch (Exception e) {
				System.out.println("Error from jwt filter: "+e);
			}
			
			fc.doFilter(request, response);
		}
		
		private String getJwtFromRequest(HttpServletRequest request) {
			String ans=null;
			String bearerToken = request.getHeader(HEADER_AUTHORIZACION_KEY);	
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX)) {
				ans = bearerToken.substring(7,bearerToken.length());
				//System.out.println(" 1. el token no es nulo");
			}
			return ans;
		}
	}