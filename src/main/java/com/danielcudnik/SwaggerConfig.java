package com.danielcudnik;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;


/**
 * Created by Bidzis on 11/3/2016.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(apiPaths()).build();
    }
    private com.google.common.base.Predicate<String> apiPaths() {
        return or(regex("/quizAndroid/.*"));
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("QuizAndroid").description("Quiz na Androida").termsOfServiceUrl("Terms of Service")
                .contact("Daniel Cudnik email: danielcudnik93@gmail.com").license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE").version("2.0").build();
    }
}
