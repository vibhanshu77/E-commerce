package com.MSCA.Ecommerce.jwt;

import com.MSCA.Ecommerce.security.CustomerDetailService;
import com.MSCA.Ecommerce.security.EmployeeDetailService;
import com.MSCA.Ecommerce.services.CustomerDetailServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.MSCA.Ecommerce.services.EmployeeDetailServiceImpl;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    CustomerDetailServiceImpl customerDetailServiceImpl;

    @Autowired
    EmployeeDetailServiceImpl employeeDetailServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

//        System.out.println(authHeader);

        String token = null;
        String  username = null;

        if(authHeader != null && authHeader.startsWith("Bearer")){

            token = authHeader.substring(7);

//            System.out.println("employee Jwt token is: "+token);

            username = jwtUtility.getValidUserName(token);

            System.out.println("getting username: "+ username);
        }

        if(username != null && (username.substring(username.length()-8)).equals("msca.com") && SecurityContextHolder.getContext().getAuthentication() == null){

            System.out.println("inside employee block"+username);

            String modifiedUsername = username+"&";

            EmployeeDetailService employeeDetailService = (EmployeeDetailService) employeeDetailServiceImpl.loadUserByUsername(modifiedUsername);

            if(jwtUtility.validate(token,username)){

                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(employeeDetailService, null, employeeDetailService.getAuthorities());

                System.out.println("JwtFilter block customerAuthentication"+authentication.getPrincipal());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        if(username != null && (username.substring(0)!=("msca.com")) && SecurityContextHolder.getContext().getAuthentication() == null){

            System.out.println("inside customer Block");

            CustomerDetailService customerDetailService = (CustomerDetailService) customerDetailServiceImpl.loadUserByUsername(username);

            if(jwtUtility.validate(token,username)){

                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(customerDetailService, null, customerDetailService.getAuthorities());

                System.out.println("JwtFilter block customerAuthentication: "+authentication.getPrincipal());

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        filterChain.doFilter(request, response);

    }
}
