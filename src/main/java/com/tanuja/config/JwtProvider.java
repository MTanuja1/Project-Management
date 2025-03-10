package com.tanuja.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;

import java.util.Date;

import static org.yaml.snakeyaml.tokens.Token.ID.Key;


public class JwtProvider {
	static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

	public static String generateToken(Authentication auth){
		String jwt=Jwts.builder().setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email",auth.getName())
				.signWith(key)
				.compact();
	return jwt;
	}

	// get email from jwt token
	public static String getEmailFromToken(String jwt){
		jwt=jwt.substring(7);
		Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		String email = String.valueOf(claims.get("email"));
		return String.valueOf(claims.get("email"));
	}
}
