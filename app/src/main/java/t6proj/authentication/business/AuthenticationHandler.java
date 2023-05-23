package t6proj.authentication.business;

import org.springframework.stereotype.Component;
import t6proj.authentication.dto.AuthenticationRequest;
import t6proj.authentication.dto.AuthenticationResult;
import t6proj.user.UserService;

import java.util.Objects;

@Component
public class AuthenticationHandler {
    private final UserService userService;

    public AuthenticationHandler(
            UserService userService
    ) {
        this.userService = userService;
    }

    public AuthenticationResult handle(AuthenticationRequest request)
    {
        var user = this.userService.getUserByEmail(request.email);
        if (user == null) {
            return new AuthenticationResult(false, null);
        }

        // @todo replace with hashing algorithm
        if (!user.password.equals(request.password)) {
            return new AuthenticationResult(false, null);
        }

        // @todo it's better to be replaced with jwt
        return new AuthenticationResult(true, user.email);
    }
}
