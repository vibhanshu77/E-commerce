package com.MSCA.Ecommerce.controller;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.jwt.JwtUtility;
import com.MSCA.Ecommerce.repository.CustomerRepo;
import com.MSCA.Ecommerce.requestDto.LoginDto;
import com.MSCA.Ecommerce.security.CustomerDetailService;
import com.MSCA.Ecommerce.security.EmployeeDetailService;
import com.MSCA.Ecommerce.services.CustomerDetailServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
public class CustomerPublicController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    @Qualifier("customerAuthenticationManager")
    private AuthenticationManager customerAuthenticationManager;

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    CustomerDetailServiceImpl customerDetailServiceImpl;

//    @Autowired
//    UserDetails userDetails;

    @GetMapping("/search")
    public String search(){

        Customer customerdetails = customerRepo.findByCustomerEmail("customer1@mail.com");

        System.out.println(customerdetails);

        return "Public search";
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody LoginDto loginDto, HttpServletRequest request){

//         CustomerDetailService customerDetailService = (CustomerDetailService) customerDetailServiceImpl.loadUserByUsername(loginDto.getEmail());

        Authentication authenticated =  customerAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        //UserDetails userDetails = (UserDetails) authenticated.getPrincipal();

        CustomerDetailService customerDetailService = (CustomerDetailService) authenticated.getPrincipal();

//        customerDetailService.isWarehouseworking();

//        SecurityContextHolder.getContext().setAuthentication(unauthenticateObject);
//
//        HttpSession session = request.getSession(true);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        String token = jwtUtility.generateToken(customerDetailService);
        System.out.println(loginDto.getEmail());

        System.out.println(authenticated.getPrincipal());

        return ResponseEntity.ok("Generated token :"+ token);
    }


}
