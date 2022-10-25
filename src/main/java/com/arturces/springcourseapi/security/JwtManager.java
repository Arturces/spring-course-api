package com.arturces.springcourseapi.security;

import com.arturces.springcourseapi.constant.SecurityConstants;
import com.arturces.springcourseapi.dto.UserLoginResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public UserLoginResponseDto createToken(String email, List<String> roles){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);

        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstants.JWT_ROLE_KEY,roles)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.API_KEY.getBytes())
                .compact();

        Long expireIn = calendar.getTimeInMillis();

        return new UserLoginResponseDto(jwt, expireIn, SecurityConstants.JWT_PROVIDER);
    }

    public Claims parseToken(String jwt) throws JwtException {
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.API_KEY)
                .parseClaimsJws(jwt)
                .getBody();

        return claims;
    }
}
