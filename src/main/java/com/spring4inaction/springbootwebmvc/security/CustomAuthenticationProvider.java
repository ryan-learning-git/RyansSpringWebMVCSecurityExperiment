package com.spring4inaction.springbootwebmvc.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider(){
        System.out.println("CustomAuthenticationProvider created");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication returnMe = null;
        System.out.println("Authentication is " + authentication);
        if (authentication != null){
            System.out.println("Authentication user is " + authentication.getName() + " and credentials are " + authentication.getCredentials());
            if (authentication.getName().equals("admin") && authentication.getCredentials().equals("test1234")){
                System.out.println("Admin user is good.");
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                returnMe = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuthorities);
            } else if (authentication.getName().equals("user") && authentication.getCredentials().equals("test1234")){
                System.out.println("User user is good.");
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                returnMe = new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), grantedAuthorities);
            }
        }
        System.out.println("Authenticate - we're going to return " + returnMe);
        return returnMe;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
