package com.naturemobility.api.config;

import com.google.common.collect.ImmutableList;
import com.naturemobility.api.exception.ApiAccessDeniedHandler;
import com.naturemobility.api.util.JwtTokenUtil;
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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenUtil jwtTokenUtil;

  // security 패스
  private static final String[] AUTH_WHITELIST = {
    // social login
    "/kakao/login",
    "/kakao/code",

    "/client/token",
    "/license/return",
    "/test/**",
    "/error",

    // nice-id
    "/nice-id",
    "/nice-id/**",

    // billgate
    "/billgate/pay",
    "/billgate/pay-return",
    "/billgate/test",

    // google analytics batch
    "/batch/active-user"
  };

  // NM_USER 권한 필요
  private static final String[] NM_USER_PATH = {"/member/**", "/message/**", "/find/**"};

  // ADMIN 권한 필요
  private static final String[] ADMIN_PTAH = {"/admin", "/admin/**"};

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
      .httpBasic().disable()  // 비인증 시 로그인폼 사용 안함
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 토큰 사용하므로 세션 사용 안함
      .and()
      .authorizeRequests()    // 다음 요청에 대해 사용권한 체크
      .antMatchers(AUTH_WHITELIST).permitAll()
      .antMatchers(NM_USER_PATH).hasRole(ApiConstant.ROLE.NM_USER)
      .antMatchers(ADMIN_PTAH).hasRole(ApiConstant.ROLE.ADMIN)
      .anyRequest().authenticated()   // 모든 인증된 회원만 접근 가능(예외처리는 web.ignoring() 에서 진행) 및 메소드 단위 설정함
      .and()
      .exceptionHandling().authenticationEntryPoint(new ApiAuthenticationEntryPoint())
      .and()
      .exceptionHandling().accessDeniedHandler(new ApiAccessDeniedHandler())
      .and()
      .addFilterBefore(new JwtAuthenticationFilter(jwtTokenUtil), UsernamePasswordAuthenticationFilter.class); // JWT인증 필터를 ID/PW인증 필터 전에 삽입
  }

  @Override
  public void configure(WebSecurity web) {
    String[] swaggerPath = new String[]{"/v2/api-docs", "/swagger-resources/**",
      "/", "/swagger-ui.html", "/webjars/**", "/swagger/**", "/csrf", "/favicon.ico"};

    web.ignoring().antMatchers(swaggerPath)
      .antMatchers("/exception/**", "/kakao/login", "/kakao/code", "/license/return", "/test/**", "/nice-id", "/nice-id/**", "/billgate/pay",
        "/billgate/pay-return", "/billgate/test", "/batch/active-user");
  }

  /**
   * 리뉴얼 이전 암호화 방식(SHA-256) 지원하기위해 PasswordEncoderFactories 사용함
   *
   * @return
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
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
