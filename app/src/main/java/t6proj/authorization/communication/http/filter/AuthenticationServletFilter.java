package t6proj.authorization.communication.http.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import t6proj.authorization.AuthorizationService;

import java.io.IOException;

@Component
public class AuthenticationServletFilter implements Filter {
    private final AuthorizationService authorizationService;

    public AuthenticationServletFilter(
            AuthorizationService authorizationService
    ) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var servletRequest = (HttpServletRequest) request;
        var servletResponse = (HttpServletResponse) response;

        var cookies = servletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authToken")) {
                    this.authorizationService.handleAuthorizationRequest(cookie.getValue());
                }
            }
        }

//        try {
            chain.doFilter(request, response);
//        } finally {
//
//        }
    }
}
