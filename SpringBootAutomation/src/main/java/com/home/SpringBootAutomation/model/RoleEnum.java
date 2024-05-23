package com.home.SpringBootAutomation.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {

    ;

    @Override
    public String getAuthority() {
        return null;
    }
}
