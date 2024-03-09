package com.example.demoreplay.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class SecurityUtil {
    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        for (GrantedAuthority authority : roles) {
            if (authority.getAuthority().contains(role)) {
                return true;
            }
        }
        return false;
    }

    public static String getCurrentRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        return ((GrantedAuthority)((List) roles).get(0)).getAuthority();
    }
}
