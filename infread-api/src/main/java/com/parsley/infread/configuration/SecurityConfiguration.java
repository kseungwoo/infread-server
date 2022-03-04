package com.parsley.infread.configuration;

import com.parsley.infread.component.PasswordEncoderDecorator;
import com.parsley.infread.component.JwtAuthenticationEntryPoint;
import com.parsley.infread.service.UserPrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final UserPrincipalService userPrincipalService;
    private final PasswordEncoderDecorator passwordEncoder;

    private static final String[] EXCLUDE_SWAGGER_REQUEST_PATH = {
            "/v2/api-docs/**", "/swagger*/**", "/webjars/**",
            "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security",
            "/swagger-ui/",
    };

    private static final String[] EXCLUDE_POST_REQUEST_PATH = {
            "/v1/auth/sign-up",
            "/v1/auth/sign-in",
            "/v1/auth/tokens"
    };

    private static final String[] EXCLUDE_GET_REQUEST_PATH = {
    };

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userPrincipalService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "/csrf")
                .permitAll()
                .antMatchers(EXCLUDE_SWAGGER_REQUEST_PATH)
                .permitAll()
                .antMatchers(EXCLUDE_GET_REQUEST_PATH)
                .permitAll()
                .antMatchers(EXCLUDE_POST_REQUEST_PATH)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
