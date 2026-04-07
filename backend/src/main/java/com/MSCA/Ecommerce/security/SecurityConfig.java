package com.MSCA.Ecommerce.security;

import com.MSCA.Ecommerce.jwt.JwtAuthEntryPoint;
import com.MSCA.Ecommerce.jwt.JwtAuthFilter;
import com.MSCA.Ecommerce.services.CustomerDetailServiceImpl;
import com.MSCA.Ecommerce.services.EmployeeDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Bean
//    public AuthenticationManager authenticationManager(
//            HttpSecurity http,
//            //@Qualifier("customerAuthenticationProvider")
//            AuthenticationProvider customerAuthenticationProvider,
//            //@Qualifier("employeeAuthenticationProvider")
//            AuthenticationProvider employeeAuthenticationProvider
//    ) throws Exception {
//
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .authenticationProvider(customerAuthenticationProvider)
//                .authenticationProvider(employeeAuthenticationProvider)
//                .build();
//    }

    // Custom Authentication Manager //
    @Bean
    @Primary
    public AuthenticationManager customerAuthenticationManager(HttpSecurity http,
                                                               AuthenticationProvider customerAuthenticationProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customerAuthenticationProvider)
                .build();
    }

    @Bean
    public AuthenticationManager employeeAuthenticationManager(HttpSecurity http,
                                                               AuthenticationProvider employeeAuthenticationProvider) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(employeeAuthenticationProvider)
                .build();
    }



    @Bean
    public AuthenticationProvider customerAuthenticationProvider(CustomerDetailServiceImpl customerDetailServiceImpl){

        DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider(customerDetailServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationProvider employeeAuthenticationProvider(EmployeeDetailServiceImpl employeeDetailServiceImpl){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(employeeDetailServiceImpl);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        return httpSecurity
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/api/public/**").permitAll()
                        .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
                        .requestMatchers("/api/superadmin/**").hasRole("SUPER_ADMIN")
                        .requestMatchers("/api/warehouseadmin/**").hasRole("WAREHOUSE_ADMIN")
                        .requestMatchers("/api/warehosemanager/**").hasRole("WAREHOUSE_MANAGER")
                        .requestMatchers("/api/warehousesupervisor/**").hasRole("WAREHOUSE_SUPERVISOR")
                        .requestMatchers("/api/warehousestaffhead/**").hasRole("WAREHOUSE_STAFF_HEAD")
                        .requestMatchers("/api/warehousestaff/**").hasRole("WAREHOUSE_STAFF")
                        .requestMatchers("/api/product/**").hasAnyRole("SUPER_ADMIN", "WAREHOUSE_ADMIN", "CUSTOMER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthEntryPoint))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    //    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
}
