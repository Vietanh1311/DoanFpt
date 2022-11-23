package com.company.demo.util;

import com.company.demo.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtils {
    public static CustomUserDetails getCurrentUserUtils(){
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
