package com.spring4inaction.springbootwebmvc.config;

        import com.spring4inaction.springbootwebmvc.security.CustomAuthenticationProvider;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        //https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d hints that this should be done with JPA.
//        auth.inMemoryAuthentication().withUser("user1").password("test1234")
//                .roles("USER").and().withUser("admin1").password("test1234")
//                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/content").hasRole("USER")
                .antMatchers("**").permitAll()
                .and().csrf().disable().headers().frameOptions().disable()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/loggedOut");
    }



}
