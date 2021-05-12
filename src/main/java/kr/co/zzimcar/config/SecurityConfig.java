package kr.co.zzimcar.config;

import com.google.common.collect.ImmutableList;
import kr.co.zzimcar.exception.ApiAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
      .headers().frameOptions().disable()
      .addHeaderWriter(new StaticHeadersWriter("X-Frame-Options", "ALLOW-FROM http://newfront.benepia.co.kr, ALLOW-FROM http://*.ezwel.com"))
      .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and().exceptionHandling().accessDeniedHandler(new ApiAccessDeniedHandler()).authenticationEntryPoint(new ApiAuthenticationEntryPoint());
  }

  @Override
  public void configure(WebSecurity web) {
    String[] swaggerPath = new String[]{"/v2/api-docs", "/swagger-resources/**",
      "/", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/csrf", "/favicon.ico"};

    web.ignoring().antMatchers(swaggerPath)
      .antMatchers("/exception/**", "/kakao/login", "/kakao/code", "/license/return", "/test/**", "/nice-id", "/nice-id/**", "/billgate/pay",
        "/billgate/pay-return", "/billgate/test", "/batch/active-user");
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowedOrigins(ImmutableList.of("*"));
    corsConfiguration.setAllowedMethods(ImmutableList.of("HEAD", "POST", "GET", "PUT", "DELETE", "PATCH",
      "OPTIONS"));

    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type",
      "Access-Control-Allow-Headers", "Content-Length", "X-Requested-With",
      "xMemberToken", "xClientToken"));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
  }
}
