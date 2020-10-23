package com.employe.api.configuration;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.employee.api.controller")
public class SwaggerConfiguration{

@Value("${prop.swagger.enabled:false}")
private boolean enableSwagger;

@Bean
public Docket api() {
	// @formatter:off
	System.out.println("SWAGGERRRRRRR****************");
	return new Docket(DocumentationType.SWAGGER_2)
//			.globalOperationParameters(globalParameterList()) //Global authentication
			.select()
			//.apis(RequestHandlerSelectors.any())
			.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
			//.paths(PathSelectors.any())
			//.paths(PathSelectors.ant("/swagger2-demo"))
			.build()
			;
	// @formatter:on
}

private List<Parameter> globalParameterList() {
	Parameter authTokenHeader =
	        new ParameterBuilder()
	            .name("Authorization") // name of the header
	            .modelRef(new ModelRef("string")) // data-type of the header
	            .required(true) // required/optional
	            .parameterType("header") // for query-param, this value can be 'query'
	            .description("Basic Auth Token")
	            .build();

	    return Collections.singletonList(authTokenHeader);
}

public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
}

}
