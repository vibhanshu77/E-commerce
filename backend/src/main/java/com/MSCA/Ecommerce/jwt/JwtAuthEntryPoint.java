package com.MSCA.Ecommerce.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        //Set 401 Unauthorized status//
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        //Set response type to json
        response.setContentType("application/json");

        //Write error message
        response.getWriter().write("{error: Unauthorised user}");

    }
}
