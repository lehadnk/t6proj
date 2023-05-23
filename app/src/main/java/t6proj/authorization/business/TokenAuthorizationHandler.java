package t6proj.authorization.business;

import org.springframework.stereotype.Component;
import t6proj.user.UserService;

@Component
public class TokenAuthorizationHandler {
    private final UserStorage userStorage;
    private final UserService userService;

    public TokenAuthorizationHandler(
            UserStorage userStorage,
            UserService userService
    ) {
        this.userStorage = userStorage;
        this.userService = userService;
    }

    public void handle(String token)
    {
        var user = this.userService.getUserByEmail(token);
        if (user == null) {
            return;
        }

        this.userStorage.setCurrentUser(user);
    }
}
