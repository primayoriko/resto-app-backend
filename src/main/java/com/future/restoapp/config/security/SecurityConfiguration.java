package com.future.restoapp.config.security;

import com.future.restoapp.constant.UrlBasePath;
import com.future.restoapp.controller.path.UserControllerPath;
import com.future.restoapp.filter.JwtAuthenticationFilter;
import com.future.restoapp.filter.JwtAuthorizationFilter;
import com.future.restoapp.repository.UserRepository;
import com.future.restoapp.service.impl.UserPrincipalDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserPrincipalDetailsServiceImpl userPrincipalDetailsService;
    private final UserRepository userRepository;

    public SecurityConfiguration(UserPrincipalDetailsServiceImpl userPrincipalDetailsService, UserRepository userRepository) {
        this.userPrincipalDetailsService = userPrincipalDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
            .authorizeRequests()
            // configure access rules
            .antMatchers(HttpMethod.GET, "/").permitAll()
            .antMatchers(HttpMethod.GET, "/version").permitAll()
            .antMatchers(HttpMethod.GET, "/docs*/**").permitAll()
            .antMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
            .antMatchers(HttpMethod.GET, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.POST, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.OPTIONS, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.HEAD, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.TRACE, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.PATCH, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.PUT, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.DELETE, UrlBasePath.CURRENT_PUBLIC + "/**").permitAll()
            .antMatchers(HttpMethod.POST, UserControllerPath.REGISTER_ADMIN).permitAll() // OPENED FOR TESTING PURPOSE
            .antMatchers(UrlBasePath.CURRENT_ADMIN + "/**").hasRole("ADMIN")
            .antMatchers(UrlBasePath.CURRENT_CLIENT + "/**").hasRole("CLIENT")
            .anyRequest().authenticated();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
