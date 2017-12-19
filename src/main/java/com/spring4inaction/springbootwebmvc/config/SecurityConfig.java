package com.spring4inaction.springbootwebmvc.config;

import com.spring4inaction.springbootwebmvc.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomAuthenticationProvider authenticationProvider;

    public SecurityConfig(CustomAuthenticationProvider authenticationProvider){
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("We're going to configure authentication to use our custom authentication provider " + authenticationProvider);
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/**")
                .hasRole("ADMIN").antMatchers("/content").hasRole("USER")
                .antMatchers("**").permitAll()
                .and().csrf().disable().headers().frameOptions().disable().and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/loggedOut");
    }

}
