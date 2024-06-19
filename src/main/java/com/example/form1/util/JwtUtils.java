package com.example.form1.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.form1.common.AccessDeniedException;
import com.example.form1.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {
    private static String secret = "This_is_secret";
    private static long expiryDuration = 60 * 60;

    public String generateJwt(User user){
    	
        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration * 1000;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);


    
        Claims claims = Jwts.claims()
                .setIssuer(user.getEmailId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);
    	
    	
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
}
    public Claims verify(String authorization) throws Exception {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch(Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }

}