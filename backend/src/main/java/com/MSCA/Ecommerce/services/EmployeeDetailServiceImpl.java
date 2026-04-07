package com.MSCA.Ecommerce.services;

import com.MSCA.Ecommerce.entities.Employee;
import com.MSCA.Ecommerce.repository.EmployeeRepo;
import com.MSCA.Ecommerce.security.EmployeeDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        if(emailId != null && emailId.substring(emailId.length()-1).equals("&")){

            try{
                System.out.println("inside try block");
                //Employee employee = employeeRepo.findByEmail(emailId);
                Employee employee = employeeRepo.getUserDetailsByUsername(emailId.substring(0, emailId.length()-1));
                if(employee == null){
                    System.out.println("inside if block");
                    throw new UsernameNotFoundException("No userfound");
                }
                else{

                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    employee.setPassword(passwordEncoder.encode(employee.getPassword()));

                    System.out.println("at the stage of returning the object");
                    return new EmployeeDetailService(employee);
                }
            }catch(Exception e){
                throw e;
            }

        }
        else{
            try{
                System.out.println("inside try block");
                //Employee employee = employeeRepo.findByEmail(emailId);
                Employee employee = employeeRepo.getUserDetailsByEmail(emailId);
                if(employee == null){
                    System.out.println("inside if block");
                    throw new UsernameNotFoundException("No userfound");
                }
                else{

                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    employee.setPassword(passwordEncoder.encode(employee.getPassword()));

                    System.out.println("at the stage of returning the object");
                    return new EmployeeDetailService(employee);
                }
            }catch(Exception e){
                throw e;
            }
        }
    }
}
