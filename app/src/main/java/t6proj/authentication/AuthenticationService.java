package t6proj.authentication;

import adminlte.authentication.AuthenticationServiceInterface;
import org.springframework.stereotype.Service;
import t6proj.authentication.business.AuthenticationHandler;
import t6proj.authentication.dto.AuthenticationRequest;
import t6proj.authentication.dto.AuthenticationResult;

@Service
public class AuthenticationService implements AuthenticationServiceInterface {
    private final AuthenticationHandler authenticationHandler;

    public AuthenticationService(
            AuthenticationHandler authenticationHandler
    ) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public Integer getCurrentUserIdentifier() {
        return 1;
    }

    public AuthenticationResult authenticate(AuthenticationRequest request)
    {
        return this.authenticationHandler.handle(request);
    }
}
