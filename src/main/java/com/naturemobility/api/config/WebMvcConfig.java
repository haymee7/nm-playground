package com.naturemobility.api.config;

import com.naturemobility.api.dao.ApiLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Autowired
  private ApiLogDao logDao;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      .allowedMethods("HEAD", "GET", "PUT", "POST", "OPTIONS", "DELETE", "PATCH");
  }

  @Bean
  public FilterRegistrationBean<LoggingFilter> loggingFilter() {
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new LoggingFilter(logDao));

    String[] filterUrlPatterns = {
      "/admin", "/admin/*",
      "/biztalk", "/biztalk/*",
      "/credit", "/credit/*",
      "/find", "/find/*",
      "/license", "/license/*",
      "/member", "/member/*",
      "/message", "/message/*",
      "/payment", "/payment/*",
      "/point", "/point/*",
      "/kakao", "/kakao/*",
      "/welfare", "/welfare/*",
      "/car", "/car/*"
    };

    bean.addUrlPatterns(filterUrlPatterns);
    return bean;
  }
}
