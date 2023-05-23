package t6proj.authorization;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import t6proj.authorization.communication.http.filter.AuthenticationServletFilter;

@Configuration(proxyBeanMethods = true)
public class AuthorizationConfiguration {
    @Bean
    FilterRegistrationBean<AuthenticationServletFilter> createAuthenticationServletFilter(
            AuthenticationServletFilter servletFilter
    ) {
        return new FilterRegistrationBean<>(servletFilter);
    }
}
