package com.MSCA.Ecommerce.security;

import com.MSCA.Ecommerce.entities.Customer;
import com.MSCA.Ecommerce.entities.CustomerOrder;
import com.MSCA.Ecommerce.enums.Role;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


public class CustomerDetailService implements UserDetails {

    Customer customer;
    RolePermissionMappping rolePermissionMappping;

    public CustomerDetailService(Customer customer){
        this.customer = customer;
    }

    Set<SimpleGrantedAuthority> authority = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(customer.getCustomerRole().name()));

        // Holding this permission logic for sometime will continue later //
//        authority.add(new SimpleGrantedAuthority(customer.getCustomerRole().name()));
//
//        Set<SimpleGrantedAuthority> permissions = rolePermissionMappping.getPermissions((customer.getCustomerRole()));
//        authority.addAll(permissions);
//
//        return authority;
    }

    @Override
    public @Nullable String getPassword() {
        return customer.getCustomerPassword();
    }

    @Override
    public String getUsername() { // Customer username is mapped with Email.
        return customer.getCustomerEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//        return true;
        if(customer.getAccountStatus().name() == "LOCKED"){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }

    public boolean isWarehouseworking(){
        return true;
    }

    public String accountStatus(){
        return customer.getAccountStatus().name();
    }

}
