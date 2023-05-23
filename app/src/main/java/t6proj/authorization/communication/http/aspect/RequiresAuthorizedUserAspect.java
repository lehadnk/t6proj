package t6proj.authorization.communication.http.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import t6proj.authorization.AuthorizationService;

@Aspect
@Component
public class RequiresAuthorizedUserAspect {
    private final AuthorizationService authorizationService;

    public RequiresAuthorizedUserAspect(
            AuthorizationService authorizationService
    ) {
        this.authorizationService = authorizationService;
    }

    @Before("@annotation(t6proj.authorization.communication.http.RequiresAuthorizedUser)")
    public void checkAuthorizedUser() throws ResponseStatusException
    {
        var user = this.authorizationService.getCurrentUser();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
