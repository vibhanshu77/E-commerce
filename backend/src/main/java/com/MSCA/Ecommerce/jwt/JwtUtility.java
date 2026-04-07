package com.MSCA.Ecommerce.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

//import java.util.Date;

@Component
public class JwtUtility {

    @Value("${jwt.SECRETKEY}")
    private String jwtSecretKey;

    @Value("${jwt.tokenvalidity}")
    private Long tokenValidity;

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidity))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }

    public String getValidUserName(String token){

        String username = extractClaims(token).getSubject();
        System.out.println("JWT username: "+username);
        return username;
    }

    public Claims extractClaims(String token){
        JwtParser claims;
        try{
            claims =  Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e+"fucking exception");
        }
        Jws cl;
        try{
            cl  =  claims.parseClaimsJws(token);
        } catch (Exception e) {
            throw new RuntimeException(e+"slutty exception");
        }

        try{
            return (Claims) cl.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e+"bitch error");
        }

//        System.out.println("Claims: "+claims);
//        return claims;
    }

    public Boolean validate(String token, String username){

        if(extractClaims(token).getSubject().equals(username) && !get_expiration_time(token)){
//            System.out.println(extractClaims(token).getSubject() + username);
            return true;
        }

        return false;
    }

    public Boolean get_expiration_time(String token){

        return extractClaims(token).getExpiration().before(new Date());
    }

}
