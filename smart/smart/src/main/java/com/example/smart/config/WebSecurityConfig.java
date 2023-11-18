package com.example.smart.config;

import com.example.smart.service.impl.StaffDetailsServiceImpl;
import com.example.smart.until.CookieUtil;
import com.example.smart.until.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final CookieUtil cookieUtil;

    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;

    private final SessionUtil sessionUtil;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(staffDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/login/**", "/assets/**").permitAll();
        http.authorizeRequests().antMatchers("/staff").hasAnyAuthority("ROLE_ADMIN")
                .and().exceptionHandling().accessDeniedPage("/");
        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin()
                .loginProcessingUrl("/login-check")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?status=login_false")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler((request, response, authentication) -> {
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    String username = userDetails.getUsername();
                    log.info("Login to " + username + " account successfully!");
                    System.out.println(request.getContextPath());
                    response.sendRedirect("");
                    System.out.println(username + " config");
                    cookieUtil.add("username", username, 168); //7 days
                    sessionUtil.addObject("username", username);
                })
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?status=logout");
       // http.addFilter(customerAuthenticationFilter);

        http.authorizeRequests().and() //
                .rememberMe().rememberMeParameter("remember")
                .tokenValiditySeconds(24 * 60 * 60); // 24h
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }
}
