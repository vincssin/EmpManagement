package com.employe.api.Jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
  @Bean
  public JWTFilter authenticationTokenFilterBean() throws Exception {
      return new JWTFilter();
  }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	System.out.println("ebConfigggggggg**********");
        http
        	.csrf().disable()
            .authorizeRequests()               
            .antMatchers(
                    HttpMethod.GET,
                    "/",
                    "/v2/api-docs",           // swagger
                    "/webjars/**",            // swagger-ui webjars
                    "/swagger-resources/**",  // swagger-ui resources
                    "/configuration/**",      // swagger configuration
                    "/*.html",
                    "/favicon.ico",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js"
            ).permitAll()
            .antMatchers("/emp").permitAll();
//            .anyRequest().authenticated();
        
        // Custom JWT based security filter
//        http
//                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // disable page caching
//        http.headers().cacheControl();

    }
    
    
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")
//            .allowedOrigins("http://localhost:8102","http://localhost:3000"
//                           );
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}