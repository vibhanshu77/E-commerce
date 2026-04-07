package com.MSCA.Ecommerce.controller;

import com.MSCA.Ecommerce.jwt.JwtUtility;
import com.MSCA.Ecommerce.requestDto.LoginDto;
import com.MSCA.Ecommerce.security.CustomerDetailService;
import com.MSCA.Ecommerce.security.EmployeeDetailService;
import com.MSCA.Ecommerce.services.EmployeeDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/employee")
public class EmployeePublicController {

    @Autowired
    @Qualifier("employeeAuthenticationManager")
    AuthenticationManager employeeAuthenticationManager;

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    EmployeeDetailServiceImpl employeeDetailServiceImpl;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/login")
    public ResponseEntity loginEmployee(@RequestBody LoginDto loginDto){

        Authentication authentication = employeeAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
//        try {
//            EmployeeDetailService employeeDetailService = (EmployeeDetailService) employeeDetailServiceImpl.loadUserByUsername(loginDto.getEmail());
//            if (employeeDetailService != null) {
//                bCryptPasswordEncoder.matches(loginDto.getPassword(), employeeDetailService.getPassword());
//                String token = jwtUtility.generateToken(employeeDetailService);

//                System.out.println("token is: "+token);
//
//                return token;
//            }
//        }catch (Exception e){
//            throw e;
//        }
       System.out.println(authentication.getPrincipal().getClass());
        EmployeeDetailService employeeDetailService = (EmployeeDetailService) authentication.getPrincipal();
        String token = jwtUtility.generateToken(employeeDetailService);

//        System.out.println("token is: "+token);
//
        return ResponseEntity.ok("Employee Token: "+token);
    }

}
