package com.MSCA.Ecommerce.security;

import com.MSCA.Ecommerce.entities.Employee;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class EmployeeDetailService implements UserDetails {

    Employee employee;

    RolePermissionMappping rolePermissionMappping;

    Set<SimpleGrantedAuthority> authority = new HashSet<>();

    public EmployeeDetailService(Employee employee){
        this.employee = employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return employee.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

//        employee.getRoles().forEach(
//                role -> {
//                    authority.add(new SimpleGrantedAuthority(role.name()));
//
//                    Set<SimpleGrantedAuthority> permission = rolePermissionMappping.getPermissions(role);
//                    authority.addAll(permission);
//                }
//        );
//        return authority;
    }

    @Override
    public @Nullable String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
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
}
