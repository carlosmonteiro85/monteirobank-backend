package com.monteirosltda.core.security;

import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_CREATE;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_DELETE;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_READ;
import static com.monteirosltda.domain.model.enuns.Permission.ADMIN_UPDATE;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_CREATE;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_DELETE;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_READ;
import static com.monteirosltda.domain.model.enuns.Permission.MANAGER_UPDATE;
import static com.monteirosltda.domain.model.enuns.Role.ADMIN;
import static com.monteirosltda.domain.model.enuns.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.monteirosltda.core.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf()
          .disable()
          .authorizeHttpRequests()
          .requestMatchers(
                  "/api/dominios",
                  "/api/dominios/**",
                  "/api/categoria",
                  "/api/lancamento",
                  "/api/lancamento/**",
                  "/api/lancamento/impacto/**",
                  "/api/v1/auth/**",
                  "/v2/api-docs",
                  "/v3/api-docs",
                  "/v3/api-docs/**",
                  "/swagger-resources",
                  "/swagger-resources/**",
                  "/configuration/ui",
                  "/configuration/security",
                  "/swagger-ui/**",
                  "/webjars/**",
                  "/swagger-ui.html"
          ).permitAll()
          .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
          .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
          .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
          .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
          .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
  
          .anyRequest()
            .authenticated()
          .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authenticationProvider(authenticationProvider)
          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
          .logout()
          .logoutUrl("/api/v1/auth/logout") // logout api
          .addLogoutHandler(logoutHandler)
          .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
      ;
  
      return http.build();
    }
}
