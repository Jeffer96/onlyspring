package com.airefresco.app.Components;


import java.util.Date;
import org.springframework.security.core.Authentication;
import com.airefresco.app.Security.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import static com.airefresco.app.Security.Constants.*;

public class TokenProvider {

	public static String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		//System.out.println(new Date().getTime() + "TOKEN_EXPIRATION_TIME");
		return Jwts.builder()
				.setSubject(Integer.toString(userPrincipal.getId()))
				.signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY)
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + TOKEN_EXPIRATION_TIME))
				.compact();
	}
	

	public static int getUserId(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SUPER_SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return Integer.parseInt(claims.getSubject());
	}
	
	public static boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(SUPER_SECRET_KEY).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty.");
        }
        return false;
    }
	
}
