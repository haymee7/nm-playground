package kr.co.zzimcar.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  public static final Contact DEFAULT_CONTACT = new Contact(
    "NM Playground", "http://nm-playground.io", "development@naturemobility.com");

  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
    "Playground API", "교육용", "1.0", "urn:tos",
    DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(Arrays.asList(
    "application/json", "text/plain"));

  @Bean
  public Docket api(TypeResolver typeResolver) {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(DEFAULT_API_INFO)
      .produces(DEFAULT_PRODUCES_AND_CONSUMES)
      .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
      .select()
      .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
      .paths(PathSelectors.any())
      .build();
  }
}