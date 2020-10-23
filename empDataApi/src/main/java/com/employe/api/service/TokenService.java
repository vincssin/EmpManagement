package com.employe.api.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenService {

	public static final String TOKEN_SECRET = "s4T2zOIWHMM1sxq";
	public static final long EXPIRATION_TIME = 864_000_000;//864_000_000 -10days 60000-1minute; 900_000; // 15 mins

	public String createToken(ObjectId userId) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			String token = JWT.create().withClaim("userId", userId.toString()).withClaim("createdAt", new Date())
					.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.sign(algorithm);
			return token;
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			// log WRONG Encoding message
		} catch (JWTCreationException exception) {
			exception.printStackTrace();
			// log Token Signing Failed
		}
		return null;
	}

	public HashMap getUserIdFromToken(String token) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			map.put("status", "Success");
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			System.out.println("---->>>>>>>>>>"+jwt.getClaim("userId").asString());
			map.put("id", jwt.getClaim("userId").asString());
//			return jwt.getClaim("userId").asString();
			return map;
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
			// log WRONG Encoding message
			System.out.println("1111111111111111");
			map.put("status", "Failed");
			map.put("message", "Encoding failed");
//			return null;
			return map;
		} catch (JWTVerificationException exception) {
			exception.printStackTrace();
			System.out.println("2222222222222"+exception.getMessage());
			map.put("status", "Failed");
			map.put("message", "Access denied-Invalid token");
			if(exception.getMessage().contains("The Token has expired ")) {
				map.put("message", exception.getMessage());
			}else if(exception.getMessage().contains("Signature resulted invalid")) {
				map.put("message", "Invalid token");
			}
			// log Token Verification Failed
//			return null;
			return map;
		}
	}

	public HashMap isTokenValid(String token) {
//		String userId = this.getUserIdFromToken(token);
//		return userId != null;
		return this.getUserIdFromToken(token);
	}
	
	
	
}