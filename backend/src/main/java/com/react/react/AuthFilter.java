package com.react.react;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.react.react.model.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)  {
        Client client = null;
        try {
            client = new ObjectMapper().readValue(req.getInputStream(), Client.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                client.getUsername(),
                client.getPassword(),
                new ArrayList<>())
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain filter, Authentication auth) {
        Date date = new Date();
        LocalDateTime localDt = LocalDateTime.from(date.toInstant()).plusDays(1);
        Date expTime = Date.from(localDt.atZone(ZoneId.systemDefault()).toInstant());

        String token = Jwts.builder()
                .setSubject(((Client) auth.getPrincipal()).getUsername())
                .setExpiration(expTime)
                .signWith(SignatureAlgorithm.HS512, "MY_SECRET_KEY_101234564")
                .compact();

        res.addHeader("Authorization", "Bearer " + token);
    }
}
