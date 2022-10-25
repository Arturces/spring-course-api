package com.arturces.springcourseapi.security;

import com.arturces.springcourseapi.constant.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

    public String createToken(String email, List<String> roles){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);

        String jwt = Jwts.builder()
                .setSubject(email)
                .setExpiration(calendar.getTime())
                .claim(SecurityConstants.JWT_ROLE_KEY,roles)
                .signWith(SignatureAlgorithm.HS512,SecurityConstants.API_KEY.getBytes())
                .compact();

        return jwt;

    }
}
