package t6proj.authorization.business;

import org.springframework.stereotype.Component;
import t6proj.jwt.JwtService;
import t6proj.jwt.exceptions.InvalidTokenException;
import t6proj.user.UserService;

import java.util.Date;

@Component
public class TokenAuthorizationHandler {
    private final UserStorage userStorage;
    private final UserService userService;
    private final JwtService jwtService;

    public TokenAuthorizationHandler(
            UserStorage userStorage,
            UserService userService,
            JwtService jwtService
    ) {
        this.userStorage = userStorage;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public void handle(String tokenString)
    {
        try {
            var token = this.jwtService.decodeJwtToken(tokenString);

            if (token.expirationDate.before(new Date())) {
                return;
            }

            var user = this.userService.getUserById(token.userId);
            if (user == null) {
                return;
            }

            this.userStorage.setCurrentUser(user);
        } catch (InvalidTokenException ignored) {}
    }
}
