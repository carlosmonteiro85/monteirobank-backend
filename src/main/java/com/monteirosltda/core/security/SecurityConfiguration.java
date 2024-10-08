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
import static com.monteirosltda.domain.model.enuns.Role.SON;
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
          .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name(), SON.name())
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
    // http
    //     .authorizeHttpRequests(auth -> auth
    //         .requestMatchers(
    //             AntPathRequestMatcher.antMatcher("/h2-console/**"),
    //             AntPathRequestMatcher.antMatcher("/api/v1/auth/**"),
    //             AntPathRequestMatcher.antMatcher("/v2/api-docs"),
    //             AntPathRequestMatcher.antMatcher("/swagger-resources"),
    //             AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
    //             AntPathRequestMatcher.antMatcher("/configuration/ui"),
    //             AntPathRequestMatcher.antMatcher("/configuration/security"),
    //             AntPathRequestMatcher.antMatcher("/webjars/**"),
    //             AntPathRequestMatcher.antMatcher("/swagger-ui.htm"),
    //             AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
    //             AntPathRequestMatcher.antMatcher("/api/v1/auth/**")).permitAll()
    //         .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
    //         .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
    //         .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
    //         .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
    //         .requestMatchers(DELETE, "/api/v1/management/**")
    //         .hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name()))
    //     .logout(l -> l.logoutUrl("/api/v1/auth/logout")
    //         .addLogoutHandler(logoutHandler)
    //         .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
    //     .authenticationProvider(authenticationProvider)
    //     .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
    //     .headers(headers -> headers.frameOptions().disable())
    //     .csrf(csrf -> csrf
    //         .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")));
    // return http.build();

  //    http
  //       .csrf(csrf -> csrf
  //           .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
  //       .headers(headers -> headers.frameOptions().disable())
  //       .authorizeHttpRequests()
  //       .requestMatchers(
  //               "/api/v1/auth/**",
  //               "/v2/api-docs",
  //               "/v3/api-docs",
  //               "/v3/api-docs/**",
  //               "/swagger-resources",
  //               "/swagger-resources/**",
  //               "/configuration/ui",
  //               "/configuration/security",
  //               "/swagger-ui/**",
  //               "/webjars/**",
  //               "/swagger-ui.html"
  //       ).permitAll()
  //                   .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
  //         // Mapeamento de Permissão de acesso por Roles 
  //       .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
  //       .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
  //       .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
  //       .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
  //       .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

  //       .anyRequest()
  //         .authenticated()
  //       .and()
  //         .sessionManagement()
  //         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
  //       .and()
  //       .authenticationProvider(authenticationProvider)
  //       .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
  //       .logout()
  //       .logoutUrl("/api/v1/auth/logout") // logout api
  //       .addLogoutHandler(logoutHandler)
  //       .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
  //   return http.build();
  // }
}
