package com.lenam.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import com.lenam.laptopshop.service.CustomUserDetailsService;
import com.lenam.laptopshop.service.UserService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Custom login
    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }
    // End Custom login

    // Redirect after login
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    // Remember me
    @Bean
    public SpringSessionRememberMeServices rememberMeServices() {
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        // optionally customize
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Allow transfer the call FORWARD and INCLUDE
                        .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                DispatcherType.INCLUDE)
                        .permitAll()

                        // Everybody can access
                        .requestMatchers("/", "/product/**", "/login", "/client/**", "/css/**", "/js/**",
                                "/images/**", "/register")
                        .permitAll()

                        // Only admin can access
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        // All request are been authentication
                        .anyRequest().authenticated())

                .sessionManagement((sessionManagement) -> sessionManagement
                        // Alway create new session if user doesn't have session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        // Logout when session timeout
                        .invalidSessionUrl("/logout?expired")
                        // Limit account login simultaneous
                        .maximumSessions(1)
                        // User login previous will logout if new user(same username) login
                        .maxSessionsPreventsLogin(false))

                // Delete JSESSIONID and speak to server session timeout(invalidateHttpSession)
                .logout(logout -> logout.deleteCookies("JSESSIONID").invalidateHttpSession(true))

                // remember me
                .rememberMe(r -> r.rememberMeServices(rememberMeServices()))

                // custom form login
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        // Redirect after login
                        .successHandler(customSuccessHandler())
                        .permitAll())
                .exceptionHandling(ex -> ex.accessDeniedPage("/access-deny"));

        return http.build();
    }

}
