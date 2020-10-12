package com.employe.api.Jwt.config;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employe.api.service.TokenService;

@Configuration
public class JWTFilter extends GenericFilterBean {

	private TokenService tokenService;

	JWTFilter() {
		this.tokenService = new TokenService();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String token = request.getHeader("Authorization");

//        if (header == null || !header.startsWith("Bearer ")) {
//            throw new JwtTokenMissingException("No JWT token found in request headers");
//        }

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
			response.sendError(HttpServletResponse.SC_OK, "success");
			return;
		}

		if (allowRequestWithoutToken(request)) {
			response.setStatus(HttpServletResponse.SC_OK);
			filterChain.doFilter(req, res);
		} else {
			if (token == null) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED); 
			} else {
				HashMap map = tokenService.isTokenValid(token);
				if (map.get("status").toString().equalsIgnoreCase("failed")) {
					response.sendError(HttpServletResponse.SC_FORBIDDEN,
							"Authentication Failed: " + map.get("message").toString());
				} else {
					ObjectId userId = new ObjectId(map.get("id").toString());
					request.setAttribute("userId", userId);
					filterChain.doFilter(req, res);
				}
			}
		}

	}

	public boolean allowRequestWithoutToken(HttpServletRequest request) {
		if (request.getRequestURI().contains("/register")) {
			return true;
		}
//		if (request.getRequestURI().contains("/get")) {
//			return true;
//		}
		if (request.getRequestURI().contains("/login")) {
			return true;
		}
		if (request.getRequestURI().contains("/isAuthorized")) {
	          return true;
			}
        if (request.getRequestURI().contains("/add")) {
            return true;
        }
		if (request.getRequestURI().contains("/getall")) {
          return true;
		}
		return false;
	}
}