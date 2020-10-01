package com.postservice.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.postservice.api.controller"))
                .build()
                .apiInfo(getApiInfo());
    }
	
	private ApiInfo getApiInfo() {
		
	       
	       return new ApiInfoBuilder()
	               .title("Request Manager API")
	               .description("Microservice of Send Posts by Users")
	               .license("")
	               .licenseUrl("http://unlicense.org")
	               .termsOfServiceUrl("")
	               .version("1.0.0")
	               .contact(new Contact("Julián Amuedo Franzoni","https://www.linkedin.com/in/julián-amuedo-franzoni-51330416a/", "amuedojulian05@gmail.com"))
	               .build();
	    }
	
	
}
