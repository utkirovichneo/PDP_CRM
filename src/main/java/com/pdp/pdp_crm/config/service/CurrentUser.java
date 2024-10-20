package com.pdp.pdp_crm.config.service;

import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.service.CenterAuthService;
import com.pdp.pdp_crm.service.impl.CenterAuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private final CenterAuthService service;

    public CurrentUser(@Lazy CenterAuthService service) {
        this.service = service;
    }

//    public UserDetails getCurrentUserDetails() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//            return (UserDetails) authentication.getPrincipal();
//        }
//        return null;
//    }
//
//    public User getCurrentUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(authentication != null && authentication.getPrincipal() instanceof User) {
//            return (User) authentication.getPrincipal();
//        }
//        return null;
//    }
//
//    public String getCurrentUsername(){
//        UserDetails userDetails = getCurrentUserDetails();
//        return userDetails != null ? userDetails.getUsername() : null;
//    }


    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public UserDetails getCurrentUser() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails != null) {
            return service.loadUserByUsername(userDetails.getUsername());
        }
        return null;
    }


    public String getCurrentUsername() {
        UserDetails userDetails = getCurrentUserDetails();
        return userDetails != null ? userDetails.getUsername() : null;
    }

}
